package com.luna.music.car;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * 行车记录仪独立 HTTP 服务，监听 18767，跟音乐服务的 18764/18765 完全解耦。
 *
 * 只做两件事：GET / 返回 assets/dvr.html 页面；/dvr/* 交给 {@link DvrHttp}。
 * 没有 WebSocket、不碰 Ncm / Mode / 音源判定，音乐怎么切都不影响这个端口。
 *
 * 由 {@link DvrSvc} 前台服务无条件拉起，进程在端口就在。
 */
final class DvrServer {
    static final int PORT = 18767;
    static final DvrServer I = new DvrServer();

    private Context ctx;
    private ServerSocket ss;
    private Thread acceptThread;
    private byte[] page;

    private DvrServer() {
    }

    synchronized boolean start(Context c) {
        if (ss != null) return true;
        ctx = c.getApplicationContext();
        if (page == null) {
            try {
                page = readAsset("dvr.html");
            } catch (Throwable t) {
                Log.e(Cfg.TAG, "dvr.html unreadable", t);
                LogCapture.logAlways("DVR ASSET 读取 dvr.html 失败：" + t);
                return false;
            }
        }
        try {
            ServerSocket s = new ServerSocket();
            s.setReuseAddress(true);
            s.bind(new InetSocketAddress(PORT));
            ss = s;
            acceptThread = new Thread(new Loop(), "dvr-accept");
            acceptThread.setDaemon(true);
            acceptThread.start();
            Log.i(Cfg.TAG, "dvr server started on " + PORT);
            LogCapture.logAlways("DVR SERVER 已启动，端口 " + PORT);
            return true;
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "dvr server bind failed", t);
            LogCapture.logAlways("DVR SERVER 绑定 " + PORT + " 失败：" + t);
            ss = null;
            return false;
        }
    }

    synchronized void stop() {
        if (ss != null) {
            try { ss.close(); } catch (Throwable ignored) {}
        }
        ss = null;
        acceptThread = null;
    }

    boolean isRunning() {
        return ss != null;
    }

    private byte[] readAsset(String name) throws Exception {
        InputStream in = ctx.getAssets().open(name);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
            return out.toByteArray();
        } finally {
            in.close();
        }
    }

    private final class Loop implements Runnable {
        public void run() {
            while (isRunning()) {
                try {
                    Socket s = ss.accept();
                    new Thread(new Conn(s), "dvr-conn").start();
                } catch (Throwable t) {
                    if (isRunning()) Log.w(Cfg.TAG, "dvr accept failed: " + t);
                    return;
                }
            }
        }
    }

    /** 一个连接一条短线程：读请求行+头，路由一次，回完即关。视频流由 DvrHttp 边读边写。 */
    private final class Conn implements Runnable {
        private final Socket socket;

        Conn(Socket s) {
            socket = s;
        }

        public void run() {
            try {
                socket.setSoTimeout(120000);
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                String first = ServerIo.readLine(in);
                if (first == null) return;

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
                String target = "/";
                int sp = first.indexOf(' ');
                if (sp > 0) {
                    method = first.substring(0, sp);
                    target = first.substring(sp + 1);
                    int sp2 = target.indexOf(' ');
                    if (sp2 > 0) target = target.substring(0, sp2);
                }
                int qm = target.indexOf('?');
                String path = qm >= 0 ? target.substring(0, qm) : target;

                if (path.startsWith("/dvr/")) {
                    DvrHttp.handle(out, headers, method, path, target);
                } else if ("GET".equals(method) && ("/".equals(path) || "/dvr".equals(path)
                        || "/dvr.html".equals(path))) {
                    ServerIo.http(out, 200, "text/html; charset=utf-8", page);
                } else if ("/ping".equals(path)) {
                    ServerIo.http(out, 200, "application/json; charset=utf-8",
                            ("{\"ok\":1,\"port\":" + PORT + ",\"ip\":" + jstr(Ip.local()) + "}")
                                    .getBytes("UTF-8"));
                } else {
                    ServerIo.http(out, 404, "text/plain; charset=utf-8", new byte[0]);
                }
            } catch (Throwable t) {
                Log.d(Cfg.TAG, "dvr conn closed: " + t);
            } finally {
                try { socket.close(); } catch (Throwable ignored) {}
            }
        }
    }

    private static String jstr(String s) {
        if (s == null) return "null";
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }
}
