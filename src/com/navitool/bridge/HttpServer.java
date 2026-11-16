package com.navitool.bridge;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 极简 HTTP 服务器。只用 JDK 自带的 ServerSocket，不引入任何依赖。
 *
 * 路由：
 *   GET  /ping   存活 + 绑定状态
 *   GET  /cards  车机卡片数据（含样式参数）
 *   GET  /probe  能力自检报告
 *   GET  /diag   诊断文本（纯文本；网页里复制或下载）
 *   POST /act    执行一个动作
 */
final class HttpServer implements Runnable {

    private static volatile HttpServer instance;

    private final Context ctx;
    private volatile boolean running;
    private ServerSocket server;

    HttpServer(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * 幂等启动。provider（进程一起来）和 service（开机/被拉活）都会调它，
     * 谁先到谁启，后来的直接返回。
     */
    static void start(Context ctx) {
        if (ctx == null) {
            return;
        }
        synchronized (HttpServer.class) {
            if (instance != null && instance.running) {
                return;
            }
            HttpServer s = new HttpServer(ctx.getApplicationContext());
            Thread t = new Thread(s, "bridge-http");
            t.setDaemon(true);
            t.start();
            instance = s;
            Bridge.note("http start requested");
        }
    }

    boolean isRunning() {
        return running;
    }

    public void run() {
        // 绑定失败重试：开机瞬间端口可能还被上一次的进程占着
        int attempt = 0;
        while (!running) {
            attempt++;
            try {
                server = new ServerSocket();
                server.setReuseAddress(true);
                server.bind(new InetSocketAddress(Bridge.PORT));
                running = true;
                Bridge.bound = true;
                Bridge.lastError = "";
                Bridge.note("http listening on " + Bridge.PORT + " (try " + attempt + ")");
            } catch (Throwable t) {
                Bridge.lastError = "bind " + Bridge.PORT + " (try " + attempt + "): " + Bridge.root(t);
                Bridge.note(Bridge.lastError);
                if (attempt >= 15) {
                    return;
                }
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

        while (running) {
            try {
                final Socket s = server.accept();
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        handle(s);
                    }
                }, "bridge-conn");
                t.setDaemon(true);
                t.start();
            } catch (Throwable t) {
                if (running) {
                    Bridge.note("accept: " + Bridge.root(t));
                }
            }
        }
    }

    // -------------------------------------------------------------- handle

    private void handle(Socket s) {
        try {
            s.setSoTimeout(8000);
            InputStream in = s.getInputStream();
            String requestLine = readLine(in);
            if (requestLine == null) {
                return;
            }
            String[] parts = requestLine.split(" ");
            String method = parts.length > 0 ? parts[0] : "GET";
            String path = parts.length > 1 ? parts[1] : "/";
            int q = path.indexOf('?');
            if (q >= 0) {
                path = path.substring(0, q);
            }

            int contentLength = 0;
            String line;
            while ((line = readLine(in)) != null && line.length() > 0) {
                int c = line.indexOf(':');
                if (c > 0 && "content-length".equalsIgnoreCase(line.substring(0, c).trim())) {
                    try {
                        contentLength = Integer.parseInt(line.substring(c + 1).trim());
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
            String body = "";
            if (contentLength > 0) {
                byte[] buf = new byte[contentLength];
                int off = 0;
                while (off < contentLength) {
                    int n = in.read(buf, off, contentLength - off);
                    if (n <= 0) {
                        break;
                    }
                    off += n;
                }
                body = new String(buf, 0, off, "UTF-8");
            }

            String out;
            String ct = "application/json; charset=utf-8";
            String extra = null;
            if ("OPTIONS".equals(method)) {
                send(s, 204, "", ct);
                return;
            } else if ("/ping".equals(path)) {
                out = pingJson();
            } else if ("/acts".equals(path)) {
                out = Acts.json();
            } else if ("/act".equals(path)) {
                String a = Json.pick(body, "a");
                if (a == null || a.length() == 0) {
                    out = "{\"ok\":0,\"err\":\"missing 'a'\"}";
                } else {
                    int v = -1;
                    String vs = Json.pick(body, "v");
                    if (vs != null) {
                        try {
                            v = Integer.parseInt(vs.trim());
                        } catch (Throwable ignored) {
                        }
                    }
                    long t0 = System.currentTimeMillis();
                    String r = Acts.run(ctx, a, v);
                    // 新动作表里没有的，退回旧的命名匹配（老链接仍能用）
                    if (r.indexOf("no such method") >= 0) {
                        r = Actions.json(ctx, body);
                    }
                    long cost = System.currentTimeMillis() - t0;
                    boolean ok = r.startsWith("{\"ok\":1");
                    OpLog.add(a, Acts.labelOf(a), v, ok, cost, ok ? "" : Json.pick(r, "err"));
                    out = r;
                }
            } else if ("/fan".equals(path)) {
                int fv = intOf(Json.pick(body, "v"), -1);
                int rv = intOf(Json.pick(body, "r"), -1);
                long t0 = System.currentTimeMillis();
                String r = Acts.fan(ctx, fv, rv);
                long cost = System.currentTimeMillis() - t0;
                boolean ok = r.startsWith("{\"ok\":1");
                if (fv >= 0 || rv >= 0) {
                    StringBuilder lb = new StringBuilder("风量");
                    if (fv >= 0) {
                        lb.append(" 前").append(fv);
                    }
                    if (rv >= 0) {
                        lb.append(fv >= 0 ? " /" : "").append(" 后").append(rv);
                    }
                    OpLog.add("fan", lb.toString(), rv >= 0 ? rv : fv, ok, cost,
                            ok ? "" : Json.pick(r, "err"));
                }
                out = r;
            } else if ("/temp".equals(path)) {
                int tv = intOf(Json.pick(body, "v"), -1);
                long t0 = System.currentTimeMillis();
                String r = Acts.temp(ctx, tv);
                boolean ok = r.startsWith("{\"ok\":1");
                if (tv >= 0) {
                    OpLog.add("temp", "温度 " + tv + "°C", tv, ok,
                            System.currentTimeMillis() - t0, ok ? "" : Json.pick(r, "err"));
                }
                out = r;
            } else if ("/seat".equals(path)) {
                int sv = intOf(Json.pick(body, "v"), -1);
                String sp = Json.pick(body, "p");
                String sk = Json.pick(body, "k");
                long t0 = System.currentTimeMillis();
                String r = Acts.seat(ctx, sp, sk, sv);
                boolean ok = r.startsWith("{\"ok\":1");
                if (sp != null && sv >= 0) {
                    boolean vent = "vent".equalsIgnoreCase(sk);
                    OpLog.add("seat", "座椅 " + sp + (vent ? " 通风" : " 加热"), sv, ok,
                            System.currentTimeMillis() - t0, ok ? "" : Json.pick(r, "err"));
                }
                out = r;
            } else if ("/cards".equals(path)) {
                out = Cards.json(ctx);
            } else if ("/probe".equals(path)) {
                out = Probe.json(ctx);
            } else if ("/diag".equals(path) || "/diag.txt".equals(path)) {
                out = Diag.text(ctx);
                ct = "text/plain; charset=utf-8";
                if ("/diag.txt".equals(path)) {
                    // 直接点链接就能存成文件，不依赖页面里的 Blob
                    extra = "Content-Disposition: attachment; filename=\"car-diag.txt\"";
                }
            } else if ("/acts.log".equals(path) || "/acts.log.txt".equals(path)) {
                out = OpLog.text();
                ct = "text/plain; charset=utf-8";
                if (path.endsWith(".txt")) {
                    extra = "Content-Disposition: attachment; filename=\"act-log.txt\"";
                }
            } else if ("/cards.txt".equals(path)) {
                out = Cards.text(ctx);
                ct = "text/plain; charset=utf-8";
                extra = "Content-Disposition: attachment; filename=\"car-cards.txt\"";
            } else if ("/acts.txt".equals(path)) {
                out = Acts.text();
                ct = "text/plain; charset=utf-8";
                extra = "Content-Disposition: attachment; filename=\"act-table.txt\"";
            } else if ("/dump".equals(path) || "/dump.txt".equals(path)) {
                // 一份打包：诊断 + 车况 + 动作表 + 操作记录
                out = Diag.dump(ctx);
                ct = "text/plain; charset=utf-8";
                if ("/dump.txt".equals(path)) {
                    extra = "Content-Disposition: attachment; filename=\"car-dump.txt\"";
                }
            } else if ("/log/clear".equals(path)) {
                OpLog.clear();
                out = "{\"ok\":1}";
            } else if ("/restart".equals(path)) {
                // 音频桥接包的「重启」按钮会打这里：车控不会自己发现引擎掉了，
                // 只能靠这个软重启把 Activity / Helper 重新抓一遍，顺带再要一次前台服务。
                out = restartJson(ctx);
            } else if ("/".equals(path) || "/index.html".equals(path) || "/ui".equals(path)) {
                out = Page.html(ctx);
                ct = "text/html; charset=utf-8";
            } else {
                out = "{\"ok\":0,\"err\":\"unknown path\"}";
            }
            send(s, 200, out, ct, extra);
        } catch (Throwable t) {
            Bridge.note("handle: " + Bridge.root(t));
            try {
                send(s, 500, "{\"ok\":0,\"err\":" + Bridge.q(Bridge.root(t)) + "}",
                        "application/json; charset=utf-8");
            } catch (Throwable ignored) {
            }
        } finally {
            try {
                s.close();
            } catch (Throwable ignored) {
            }
        }
    }

    private void send(Socket s, int code, String body, String contentType) throws IOException {
        send(s, code, body, contentType, null);
    }

    private void send(Socket s, int code, String body, String contentType, String extra)
            throws IOException {
        byte[] data = body.getBytes("UTF-8");
        OutputStream os = s.getOutputStream();
        StringBuilder h = new StringBuilder(256);
        h.append("HTTP/1.1 ").append(code).append(' ')
                .append(code == 200 ? "OK" : (code == 204 ? "No Content" : "Internal Server Error"))
                .append("\r\n");
        h.append("Content-Type: ").append(contentType).append("\r\n");
        h.append("Content-Length: ").append(data.length).append("\r\n");
        h.append("Access-Control-Allow-Origin: *\r\n");
        h.append("Access-Control-Allow-Headers: *\r\n");
        h.append("Access-Control-Allow-Methods: GET,POST,OPTIONS\r\n");
        if (extra != null) {
            h.append(extra).append("\r\n");
        }
        h.append("Cache-Control: no-store\r\n");
        h.append("Connection: close\r\n\r\n");
        os.write(h.toString().getBytes("UTF-8"));
        os.write(data);
        os.flush();
    }

    /** body 里的整数字段，取不到或不是数字就给默认值 */
    private static int intOf(String s, int def) {
        if (s == null) {
            return def;
        }
        try {
            return Integer.parseInt(s.trim());
        } catch (Throwable ignored) {
            return def;
        }
    }

    private static String readLine(InputStream in) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream(128);
        int c;
        while ((c = in.read()) != -1) {
            if (c == '\n') {
                break;
            }
            if (c != '\r') {
                buf.write(c);
            }
        }
        if (c == -1 && buf.size() == 0) {
            return null;
        }
        return new String(buf.toByteArray(), "UTF-8");
    }

    /**
     * 软重启：重抓 Activity / Helper，再向前台服务要一次启动（幂等）。
     * 不杀进程——杀了要等系统按 START_STICKY 拉起，中间那几秒车控是真的断的。
     */
    private static String restartJson(Context ctx) {
        Bridge.lastError = "";
        boolean regrab = false;
        if (Bridge.activity != null) {
            Bridge.grab(Bridge.activity);
            regrab = true;
        }
        try {
            BridgeService.kick(ctx);
        } catch (Throwable t) {
            Bridge.note("restart kick: " + Bridge.root(t));
        }
        HttpServer.start(ctx);
        boolean bound = Bridge.helper != null;
        Bridge.note("restart: helper=" + bound + " activity=" + (Bridge.activity != null));
        return "{\"ok\":" + (bound ? 1 : 0)
                + ",\"helper\":" + (bound ? 1 : 0)
                + ",\"activity\":" + (Bridge.activity != null ? 1 : 0)
                + ",\"regrab\":" + (regrab ? 1 : 0)
                + ",\"ops\":" + OpLog.total()
                + ",\"err\":" + Bridge.q(Bridge.lastError)
                + "}";
    }

    private static String pingJson() {
        return "{\"ok\":1,\"v\":\"1.2\",\"port\":" + Bridge.PORT
                + ",\"bound\":" + (Bridge.bound ? 1 : 0)
                + ",\"helper\":" + (Bridge.helper != null ? 1 : 0)
                + ",\"activity\":" + (Bridge.activity != null ? 1 : 0)
                + ",\"ops\":" + OpLog.total()
                + ",\"opsFail\":" + OpLog.failed()
                + ",\"at\":" + System.currentTimeMillis()
                + ",\"err\":" + Bridge.q(Bridge.lastError)
                + "}";
    }
}
