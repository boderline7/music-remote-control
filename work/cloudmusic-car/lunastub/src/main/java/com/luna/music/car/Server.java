package com.luna.music.car;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 单端口 18765：普通 HTTP 请求返回 assets/remote.html；带 Upgrade: websocket 的走协议 v1。
 * 与第三方自研播放器的遥控协议完全兼容（手机端 / iOS / 浏览器壳不用改）。
 */
final class Server {
    static final Server I = new Server();
    private static final String WS_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private static final int MAX_FRAME = 8192;
    private static final int MAX_CLIENTS = 16;

    private Context ctx;
    private ServerSocket ss;
    private ScheduledExecutorService timer;
    private ExecutorService writer;
    /** 最近一次判定结果：18765 被第三方占用，本包已让位。UI 直接读它，不用反复探测端口。 */
    volatile boolean yielded;
    private Thread acceptThread;
    private byte[] page;
    private final CopyOnWriteArrayList<Client> clients = new CopyOnWriteArrayList<Client>();
    private final AtomicLong seq = new AtomicLong(0);

    private Server() {}

    synchronized boolean start(Context c) {
        if (ss != null) return true;
        ctx = c.getApplicationContext();
        if (page == null) {
            try {
                page = readAsset("remote.html");
            } catch (Throwable t) {
                Log.e(Cfg.TAG, "remote.html unreadable", t);
                LogCapture.logAlways("ASSET 读取 remote.html 失败：" + t);
                return false;
            }
        }
        try {
            // 必须先建空 ServerSocket → 设 SO_REUSEADDR → 再 bind：
            // 直接 new ServerSocket(port) 时 reuseAddress 已经来不及生效，
            // 服务重启 / 音源切换时旧 socket 还挂在 TIME_WAIT 上，新绑定会失败，
            // 表现为「手机链接不上端口」。改成分步绑定后，切换完立刻能重新监听。
            ServerSocket s = new ServerSocket();
            s.setReuseAddress(true);
            s.bind(new InetSocketAddress(Cfg.PORT));
            ss = s;
        } catch (Throwable t) {
            // 端口被别人（第三方自研播放器）监听：不抢、不重试，让第三方继续提供服务。
            // 本包只负责探测和让位，角色判定完全交给端口，避免两个 App 互相顶。
            Log.w(Cfg.TAG, "port " + Cfg.PORT + " busy, yield: " + t);
            LogCapture.logAlways("PORT 绑定 " + Cfg.PORT + " 失败，让给占用方：" + t);
            ss = null;
            return false;
        }
        acceptThread = new Thread(new AcceptLoop(this), "ncm-accept");
        acceptThread.setDaemon(true);
        acceptThread.start();
        writer = Executors.newSingleThreadExecutor();
        timer = Executors.newScheduledThreadPool(1);
        timer.scheduleWithFixedDelay(new Tick(), 1, 1, TimeUnit.SECONDS);
        Log.i(Cfg.TAG, "server started on port " + Cfg.PORT);
        LogCapture.logAlways("SERVER 已启动，端口 " + Cfg.PORT + "，accept 线程已拉起");
        return true;
    }

    /** 探测 18765 是否已被别的进程监听。用 SO_REUSEADDR 试绑定：
     *  能绑上说明只有上次的 TIME_WAIT 残留，算空闲；绑不上说明真有人在 listen。 */
    static boolean portTaken() {
        ServerSocket probe = null;
        try {
            probe = new ServerSocket();
            probe.setReuseAddress(true);
            probe.bind(new InetSocketAddress(Cfg.PORT));
            LogCapture.logAlways("PORT " + Cfg.PORT + " 空闲");
            return false;
        } catch (Throwable t) {
            LogCapture.logAlways("PORT " + Cfg.PORT + " 已被占用（判定第三方在服务）："
                    + t.getClass().getSimpleName());
            return true;
        } finally {
            if (probe != null) try { probe.close(); } catch (Throwable ignored) {}
        }
    }

    synchronized void stop() {
        if (timer != null) timer.shutdownNow();
        timer = null;
        if (ss != null) { try { ss.close(); } catch (Throwable ignored) {} }
        ss = null;
        acceptThread = null;
        List<Client> all = clients;
        for (int i = 0; i < all.size(); i++) all.get(i).close();
        clients.clear();
        if (writer != null) writer.shutdownNow();
        writer = null;
    }

    /** 统一写队列；服务已停或执行器关闭时静默丢弃，避免抛异常打断回调线程。 */
    void postWrite(Runnable r) {
        ExecutorService w = writer;
        if (w == null || w.isShutdown()) return;
        try { w.execute(r); } catch (Throwable ignored) {}
    }

    boolean isRunning() { return ss != null; }

    /** 给 {@link Api} 用：切音源、申请白名单都要 Context。 */
    Context context() { return ctx; }

    int clientCount() { return clients.size(); }

    private byte[] readAsset(String name) throws Exception {
        InputStream in = ctx.getAssets().open(name);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
            return out.toByteArray();
        } finally { in.close(); }
    }

    // ---------- 连接接入 ----------

    void accept(Socket socket) {
        if (clients.size() >= MAX_CLIENTS) { closeQuietly(socket); return; }
        new Thread(new ConnHandler(this, socket), "ncm-conn").start();
    }

    private static void closeQuietly(Socket s) { try { s.close(); } catch (Throwable ignored) {} }

    private static String readLine(InputStream in) throws Exception {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        int c;
        while ((c = in.read()) >= 0) {
            if (c == '\n') break;
            if (c != '\r') b.write(c);
            if (b.size() > 8192) throw new Exception("header too long");
        }
        if (c < 0 && b.size() == 0) return null;
        return new String(b.toByteArray(), "ISO-8859-1");
    }

    private static void readFully(InputStream in, byte[] b) throws Exception {
        int off = 0;
        while (off < b.length) {
            int n = in.read(b, off, b.length - off);
            if (n < 0) throw new Exception("eof");
            off += n;
        }
    }

    private static String wsAccept(String key) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest((key + WS_GUID).getBytes("ISO-8859-1"));
        return Base64.encodeToString(digest, Base64.NO_WRAP);
    }

    private static void writeHttp(OutputStream out, int code, String type, byte[] body) throws Exception {
        String head = "HTTP/1.1 " + code + (code == 200 ? " OK" : " Not Found") + "\r\n"
                + "Content-Type: " + type + "\r\n"
                + "Content-Length: " + body.length + "\r\n"
                + "Connection: close\r\n"
                + "Cache-Control: no-store\r\n\r\n";
        out.write(head.getBytes("ISO-8859-1"));
        if (body.length > 0) out.write(body);
        out.flush();
    }

    private static void writeFrame(OutputStream out, int opcode, byte[] payload) throws Exception {
        int len = payload.length;
        out.write(0x80 | opcode);
        if (len < 126) out.write(len);
        else if (len < 65536) {
            out.write(126);
            out.write((len >> 8) & 0xff);
            out.write(len & 0xff);
        } else {
            out.write(127);
            for (int i = 7; i >= 0; i--) out.write((int) (((long) len >> (8 * i)) & 0xff));
        }
        if (len > 0) out.write(payload);
        out.flush();
    }

    // ---------- 协议 ----------

    void onText(Client c, String text) {
        JSONObject msg;
        try { msg = new JSONObject(text); } catch (Throwable t) {
            c.sendText(J.err(null, Cfg.ERR_INVALID_MESSAGE, "not a json object"));
            return;
        }
        if (msg.optInt("v", 0) != Cfg.V) {
            c.sendText(J.err(msg.optString("id"), Cfg.ERR_UNSUPPORTED_VERSION, "protocol v1 only"));
            return;
        }
        String type = msg.optString("type", "");
        String id = msg.optString("id", "");
        if (id.length() == 0) id = null;
        JSONObject data = msg.optJSONObject("data");
        if (data == null) data = J.o();
        if ("ping".equals(type)) {
            JSONObject d = J.o();
            J.p(d, "t", data.optLong("t"));
            J.p(d, "serverTs", System.currentTimeMillis());
            c.sendText(J.env("pong", id, d));
        } else if ("pair".equals(type)) {
            c.deviceId = data.optString("deviceId", "");
            JSONObject d = J.o();
            J.p(d, "ok", true);
            J.p(d, "autoApproved", true);
            J.p(d, "token", "open");
            J.p(d, "carId", "cloudmusic-car");
            J.p(d, "carName", Build.MODEL == null ? "车机" : Build.MODEL);
            J.p(d, "protocolVersion", Cfg.V);
            c.sendText(J.env("pair_result", id, d));
            c.sendText(stateMessage("reply"));
            sendLyricsTo(c);
        } else if ("auth".equals(type)) {
            c.deviceId = data.optString("deviceId", "");
            JSONObject d = J.o();
            J.p(d, "ok", true);
            J.p(d, "carId", "cloudmusic-car");
            J.p(d, "carName", Build.MODEL == null ? "车机" : Build.MODEL);
            J.p(d, "protocolVersion", Cfg.V);
            c.sendText(J.env("auth_result", id, d));
            c.sendText(stateMessage("reply"));
            sendLyricsTo(c);
        } else if ("command".equals(type)) {
            onCommand(c, id, data);
        } else {
            c.sendText(J.err(id, Cfg.ERR_UNKNOWN_TYPE, "unknown type: " + type));
        }
    }

    private void onCommand(Client c, String id, JSONObject data) {
        String action = data.optString("action", "");
        JSONObject args = data.optJSONObject("args");
        if (args == null) args = J.o();
        if ("library".equals(action)) {
            c.sendText(J.env("library", id, libraryData()));
            return;
        }
        if ("getTracks".equals(action)) {
            // 抓公开页是网络请求，不能卡住 WS 读线程
            new Thread(new TracksTask(c, id, args.optString("id")), "ncm-tracks").start();
            return;
        }
        if ("refresh".equals(action)) {
            Ncm.I.clearTrackCache();
            c.sendText(J.env("library", id, libraryData()));
            c.sendText(stateMessage("reply"));
            sendLyricsTo(c);
            c.sendText(ack(id, action, 0));
            return;
        }
        int code = Ncm.I.execute(action, args);
        // 每条遥控指令都留一行结果：后续排查「点了没反应」时，能直接看到是哪一步返回非 0
        LogCapture.log("CMD " + action + " -> " + (code == 0 ? "ok" : "fail(" + code + ")"));
        if ("getLyrics".equals(action) && code == 0) sendLyricsTo(c);
        c.sendText(ack(id, action, code));
        if (code == 0) pushState("event");
    }

    private JSONObject libraryData() {
        JSONObject d = J.o();
        J.p(d, "ok", true);
        JSONObject liked = J.o();
        J.p(liked, "name", "我喜欢的音乐");
        J.p(liked, "count", Liked.I.size());
        J.p(d, "liked", liked);
        JSONObject cloud = J.o();
        J.p(cloud, "name", "音乐云盘");
        J.p(d, "cloud", cloud);
        JSONArray pl = new JSONArray();
        String[] names = Ncm.I.playlists();
        for (int i = 0; i < names.length; i++) {
            JSONObject o = J.o();
            J.p(o, "id", names[i]);
            J.p(o, "name", names[i]);
            pl.put(o);
        }
        J.p(d, "playlists", pl);
        return d;
    }

    private static String ack(String id, String action, int code) {
        JSONObject d = J.o();
        J.p(d, "ok", code == 0);
        J.p(d, "action", action);
        if (code != 0) {
            J.p(d, "error", code);
            J.p(d, "message", J.errName(code));
        }
        return J.env("ack", id, d);
    }

    private String stateMessage(String reason) {
        JSONObject d = Ncm.I.snapshot();
        J.p(d, "seq", seq.incrementAndGet());
        J.p(d, "reason", reason);
        return J.env("state", null, d);
    }

    void pushState(String reason) {
        if (clients.isEmpty()) return;
        broadcast(stateMessage(reason));
        maybePushLyrics();
    }

    /** 歌词指纹按客户端各自记：新连上的设备不会被别的设备「已推过」的判断漏掉。 */
    private void maybePushLyrics() {
        String key = Ncm.I.lyricKey();
        String msg = null;
        List<Client> all = clients;
        for (int i = 0; i < all.size(); i++) {
            Client c = all.get(i);
            if (key.equals(c.lyricKey)) continue;
            c.lyricKey = key;
            if (msg == null) msg = J.env("lyrics", null, Ncm.I.lyricsData());
            c.sendText(msg);
        }
    }

    private void sendLyricsTo(Client c) {
        c.lyricKey = Ncm.I.lyricKey();
        c.sendText(J.env("lyrics", null, Ncm.I.lyricsData()));
    }

    void onBridgeChanged() {
        pushState("event");
    }

    private void broadcast(String msg) {
        List<Client> all = clients;
        for (int i = 0; i < all.size(); i++) all.get(i).sendText(msg);
    }

    void remove(Client c) { clients.remove(c); }

    void add(Client c) { clients.add(c); }
    byte[] pageBytes() { return page; }
    ServerSocket ssRef() { return ss; }
    String stateText(String reason) { return stateMessage(reason); }
}

final class Client {
    final Socket socket;
    final OutputStream out;
    volatile String deviceId = "";
    volatile String lyricKey = "";
    private volatile boolean closed;

    Client(Socket s) throws Exception {
        socket = s;
        out = s.getOutputStream();
    }

    /** 只入队，不碰网络：主线程（MediaSession 回调）也能安全调用。 */
    void sendText(String text) {
        if (closed) return;
        Server.I.postWrite(new SendText(this, text));
    }

    synchronized void writeText(String text) {
        if (closed) return;
        try {
            ServerWrite.frame(out, 1, text.getBytes("UTF-8"));
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "write failed, closing client: " + t);
            close();
        }
    }

    boolean isClosed() { return closed; }

    void sendPong(byte[] payload) {
        if (closed) return;
        Server.I.postWrite(new SendPong(this, payload));
    }

    synchronized void writePong(byte[] payload) {
        if (closed) return;
        try { ServerWrite.frame(out, 10, payload); } catch (Throwable t) { close(); }
    }

    void close() {
        if (closed) return;
        closed = true;
        Log.i(Cfg.TAG, "client closed on " + Thread.currentThread().getName());
        try { socket.close(); } catch (Throwable ignored) {}
        Server.I.remove(this);
    }
}

/** 写任务：全部在 Server 的单条后台线程上按入队顺序执行。 */
class SendText implements Runnable {
    private final Client c;
    private final String text;
    SendText(Client c, String text) { this.c = c; this.text = text; }
    @Override public void run() { c.writeText(text); }
}

class SendPong implements Runnable {
    private final Client c;
    private final byte[] payload;
    SendPong(Client c, byte[] payload) { this.c = c; this.payload = payload; }
    @Override public void run() { c.writePong(payload); }
}

/** 单独抽出写帧，Client 和握手后的 ping 复用同一个实现。 */
final class ServerWrite {
    static void frame(OutputStream out, int opcode, byte[] payload) throws Exception {
        int len = payload.length;
        out.write(0x80 | opcode);
        if (len < 126) out.write(len);
        else if (len < 65536) {
            out.write(126);
            out.write((len >> 8) & 0xff);
            out.write(len & 0xff);
        } else {
            out.write(127);
            for (int i = 7; i >= 0; i--) out.write((int) (((long) len >> (8 * i)) & 0xff));
        }
        if (len > 0) out.write(payload);
        out.flush();
    }
    private ServerWrite() {}
}

class AcceptLoop implements Runnable {
    private final Server s;
    AcceptLoop(Server s) { this.s = s; }
    @Override public void run() {
        while (s.isRunning()) {
            try {
                Socket socket = ServerAccess.socket(s).accept();
                s.accept(socket);
            } catch (Throwable t) {
                if (s.isRunning()) Log.w(Cfg.TAG, "accept failed: " + t);
                return;
            }
        }
    }
}

/** 同包内取 ServerSocket，不额外暴露 public 字段。 */
final class ServerAccess {
    static ServerSocket socket(Server s) { return s.ssRef(); }
    private ServerAccess() {}
}

class ConnHandler implements Runnable {
    private final Server server;
    private final Socket socket;
    ConnHandler(Server s, Socket socket) { this.server = s; this.socket = socket; }

    @Override public void run() {
        Client c = null;
        try {
            socket.setSoTimeout(120000);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            String first = ServerIo.readLine(in);
            if (first == null) { socket.close(); return; }
            HashMap<String, String> headers = new HashMap<String, String>();
            String line;
            while ((line = ServerIo.readLine(in)) != null && line.length() > 0) {
                int colon = line.indexOf(':');
                if (colon > 0) {
                    headers.put(line.substring(0, colon).trim().toLowerCase(),
                            line.substring(colon + 1).trim());
                }
            }
            String method = "GET";
            String path = "/";
            int sp = first.indexOf(' ');
            if (sp > 0) {
                method = first.substring(0, sp);
                String target = first.substring(sp + 1);
                int sp2 = target.indexOf(' ');
                if (sp2 > 0) target = target.substring(0, sp2);
                int qm = target.indexOf('?');
                path = qm >= 0 ? target.substring(0, qm) : target;
            }
            String upgrade = headers.get("upgrade");
            boolean ws = upgrade != null && upgrade.toLowerCase().contains("websocket");
            if (!ws) {
                if (path.startsWith("/api/")) {
                    // 车机设置接口：请求体是小 JSON，读满再交给 Api
                    String body = "";
                    String cl = headers.get("content-length");
                    if (cl != null && cl.length() > 0) {
                        int n = 0;
                        try { n = Integer.parseInt(cl.trim()); } catch (Throwable ignored) {}
                        if (n > 0 && n <= 65536) {
                            byte[] buf = new byte[n];
                            ServerIo.fully(in, buf);
                            body = new String(buf, "UTF-8");
                        }
                    }
                    byte[] resp = Api.handle(server, method, path, body);
                    if ("GET".equals(method) && "/api/logs".equals(path)) {
                        // 日志导出：带下载头，让容器 WebView 也走系统下载流程
                        ServerIo.httpDownload(out, 200, "text/plain; charset=utf-8",
                                "car-log.txt", resp);
                    } else {
                        ServerIo.http(out, 200, "application/json; charset=utf-8", resp);
                    }
                    Log.i(Cfg.TAG, "api " + method + " " + path + " -> " + resp.length);
                    LogCapture.log("HTTP " + method + " " + path + " -> " + resp.length + "B");
                } else if ("GET".equals(method) && !"/favicon.ico".equals(path)) {
                    ServerIo.http(out, 200, "text/html; charset=utf-8", server.pageBytes());
                } else {
                    ServerIo.http(out, 404, "text/plain; charset=utf-8", new byte[0]);
                }
                socket.close();
                return;
            }
            String key = headers.get("sec-websocket-key");
            if (key == null) { socket.close(); return; }
            String head = "HTTP/1.1 101 Switching Protocols\r\n"
                    + "Upgrade: websocket\r\n"
                    + "Connection: Upgrade\r\n"
                    + "Sec-WebSocket-Accept: " + ServerIo.accept(key) + "\r\n\r\n";
            out.write(head.getBytes("ISO-8859-1"));
            out.flush();
            c = new Client(socket);
            server.add(c);
            LogCapture.log("WS 接入，当前 " + server.clientCount() + " 台设备");
            c.sendText(Server.I.stateText("open"));
            while (!c.isClosed()) {
                int b1 = in.read();
                if (b1 < 0) break;
                int b2 = in.read();
                if (b2 < 0) break;
                int opcode = b1 & 0x0f;
                boolean masked = (b2 & 0x80) != 0;
                long len = b2 & 0x7f;
                if (len == 126) {
                    byte[] ext = new byte[2];
                    ServerIo.fully(in, ext);
                    len = ((ext[0] & 0xff) << 8) | (ext[1] & 0xff);
                } else if (len == 127) {
                    byte[] ext = new byte[8];
                    ServerIo.fully(in, ext);
                    long v = 0;
                    for (int i = 0; i < 8; i++) v = (v << 8) | (ext[i] & 0xff);
                    len = v;
                }
                if (len > 8192) {
                    LogCapture.logAlways("WS 帧超限 " + len + "B，断开该客户端（上限 8192）");
                    break;
                }
                byte[] mask = new byte[4];
                if (masked) ServerIo.fully(in, mask);
                byte[] payload = new byte[(int) len];
                ServerIo.fully(in, payload);
                if (masked) {
                    for (int i = 0; i < payload.length; i++) payload[i] = (byte) (payload[i] ^ mask[i % 4]);
                }
                if (opcode == 1) server.onText(c, new String(payload, "UTF-8"));
                else if (opcode == 8) break;
                else if (opcode == 9) c.sendPong(payload);
            }
        } catch (Throwable t) {
            Log.d(Cfg.TAG, "conn closed: " + t);
            // 客户端拔网线/息屏是常态，只记一句，不刷栈
            LogCapture.log("连接结束：" + t.getClass().getSimpleName());
        } finally {
            if (c != null) { c.close(); LogCapture.log("WS 断开，剩 " + server.clientCount() + " 台设备"); }
            else try { socket.close(); } catch (Throwable ignored) {}
        }
    }
}

/** 握手 / 帧读取的静态工具，全部顶层类，无匿名类。 */
final class ServerIo {
    static String readLine(InputStream in) throws Exception {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        int c;
        while ((c = in.read()) >= 0) {
            if (c == '\n') break;
            if (c != '\r') b.write(c);
            if (b.size() > 8192) throw new Exception("header too long");
        }
        if (c < 0 && b.size() == 0) return null;
        return new String(b.toByteArray(), "ISO-8859-1");
    }

    static void fully(InputStream in, byte[] b) throws Exception {
        int off = 0;
        while (off < b.length) {
            int n = in.read(b, off, b.length - off);
            if (n < 0) throw new Exception("eof");
            off += n;
        }
    }

    static String accept(String key) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest((key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes("ISO-8859-1"));
        return Base64.encodeToString(digest, Base64.NO_WRAP);
    }

    static void http(OutputStream out, int code, String type, byte[] body) throws Exception {
        String head = "HTTP/1.1 " + code + (code == 200 ? " OK" : " Not Found") + "\r\n"
                + "Content-Type: " + type + "\r\n"
                + "Content-Length: " + body.length + "\r\n"
                + "Connection: close\r\n"
                + "Cache-Control: no-store\r\n\r\n";
        out.write(head.getBytes("ISO-8859-1"));
        if (body.length > 0) out.write(body);
        out.flush();
    }

    /** 带 Content-Disposition: attachment 的下载响应，文件名 ASCII 化，避免编码问题。 */
    static void httpDownload(OutputStream out, int code, String type, String filename, byte[] body) throws Exception {
        String head = "HTTP/1.1 " + code + (code == 200 ? " OK" : " Not Found") + "\r\n"
                + "Content-Type: " + type + "\r\n"
                + "Content-Disposition: attachment; filename=\"" + filename + "\"\r\n"
                + "Content-Length: " + body.length + "\r\n"
                + "Connection: close\r\n"
                + "Cache-Control: no-store\r\n\r\n";
        out.write(head.getBytes("ISO-8859-1"));
        if (body.length > 0) out.write(body);
        out.flush();
    }
    private ServerIo() {}
}

class Tick implements Runnable {
    @Override public void run() {
        try { Server.I.pushState("tick"); } catch (Throwable ignored) {}
        // 点播状态机的超时兜底：确认阶段可能一条 metadata 都收不到，
        // 只靠回调会永远卡在 CONFIRMING，这里每秒推一次。
        try { Ncm.I.poTick(); } catch (Throwable ignored) {}
    }
}

/** 后台抓歌单曲目，完成后回一条 tracks 消息。 */
class TracksTask implements Runnable {
    private final Client c;
    private final String id;
    private final String listId;
    TracksTask(Client c, String id, String listId) {
        this.c = c;
        this.id = id;
        this.listId = listId;
    }
    @Override public void run() {
        try {
            c.sendText(J.env("tracks", id, Ncm.I.tracksData(listId)));
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "tracks task failed", t);
        }
    }
}
