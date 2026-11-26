package com.navitool.bridge;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 统一入口网关：让手机 / 浏览器只连 18766 一个地址，
 * 音乐（18765）和记录仪（18767）由这里转发。
 *
 * 为什么能做成纯 TCP 透传：
 *   音乐端是「单端口」服务 —— 普通 GET 返回页面，
 *   带 Upgrade: websocket 的请求走协议 v1。记录仪是普通 HTTP。
 *   网关不需要理解 HTTP / WebSocket 帧，把字节双向搬运即可。
 *
 * 路由（在 HttpServer.handle 之前判断）：
 *   - 带 Upgrade: websocket            -> 音乐后端（车控从不用 WS，所以根路径的 WS 一定是音乐）
 *   - /music  或 /music/...            -> 音乐后端，去掉 /music 前缀再转发
 *   - /api    或 /api/...              -> 音乐后端，路径原样转发（日志、音源切换这些接口在这）
 *   - /dvr    或 /dvr/...              -> 18767，路径原样转发（记录仪的路由就是 /dvr/*）
 *                                         只在用户打开记录仪页时才走，网关不做任何后台探测
 *   - 其余                              -> 交回 18766 本地处理
 *
 * 音乐后端只有 18765（云音乐车机版）。原来 18764 是旧桥接线，
 * 2026-11 桥接代码删掉后端口一并作废，这里不再兜底探测。
 */
final class Gateway {

    static final int DVR_PORT = 18767;
    private static final int[] MUSIC_PORTS = {18765};
    private static volatile int musicPort = 0;
    private static volatile long musicPortAt = 0;
    private static final long MUSIC_TTL = 3000L;

    private static final int CONNECT_MS = 500;
    private static final int BUF = 8192;

    private Gateway() {
    }

    /** 给统一外壳探活：音乐后端是哪个端口（0=没在跑）。
     *  故意不探 18767 —— 记录仪只在用户点「连接」时才连，
     *  网关一旦顺手探一下，用户点过「断开」之后又会被后台连上。 */
    static String statusJson() {
        int music = pickMusic();
        return "{\"ok\":1,\"music\":" + music + "}";
    }

    private static boolean reachable(int port) {
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress("127.0.0.1", port), CONNECT_MS);
            return true;
        } catch (Throwable ignored) {
            return false;
        } finally {
            closeQuietly(s);
        }
    }

    /** 这条连接是否要被网关接管。ws 升级请求也算（转音乐）。 */
    static boolean shouldHandle(String path, boolean websocket) {
        if (websocket) {
            return true;
        }
        return path.equals("/music") || path.startsWith("/music/")
                || path.equals("/api") || path.startsWith("/api/")
                || path.equals("/dvr") || path.startsWith("/dvr/");
    }

    /**
     * 接管一条连接。client 的第一次请求已经由调用方读过第一行和请求头，
     * 这里拿到原始字节流做透传。rawRequest 是已读到的请求头字节（含首行），
     * 用于重放给后端；对 /music 前缀要做一次路径改写。
     */
    static void handle(Socket client, String path, boolean websocket, byte[] rawRequest,
                       android.content.Context ctx) {
        int port;
        byte[] replay = rawRequest;
        if (websocket) {
            port = pickMusic();
            if (port == 0) {
                Bridge.note("gateway: no music backend for websocket");
                closeQuietly(client);
                return;
            }
        } else if (path.equals("/dvr") || path.startsWith("/dvr/")) {
            port = DVR_PORT;
        } else {
            port = pickMusic();
            if (port == 0) {
                Bridge.note("gateway: no music backend for " + path);
                closeQuietly(client);
                return;
            }
            // /music/* 是「把音乐页挂在网关下的别名」，要去掉前缀；
            // /api/* 是音乐服务自己的接口，路径原样转过去。
            if (path.equals("/music") || path.startsWith("/music/")) {
                replay = stripPrefix(rawRequest, "/music");
            }
        }

        // 记录仪后端没起时，先叫醒记录仪代理再重试一次。手机端访问记录仪页第一次会慢两三秒，
        // 但比「记录仪服务没起，请到车机上打开 App」好。
        for (int attempt = 1; ; attempt++) {
            Socket backend = new Socket();
            try {
                backend.connect(new InetSocketAddress("127.0.0.1", port), CONNECT_MS);
                backend.setSoTimeout(0);
                client.setSoTimeout(0);
                bridge(client, backend, replay);
                Bridge.note("gateway: " + (websocket ? "ws" : path) + " -> " + port);
                return;
            } catch (Throwable t) {
                Bridge.note("gateway: connect " + port + ": " + Bridge.root(t));
                // 音乐后端连不上（ws 或普通 HTTP 都算）就清缓存，下次重探；DVR 失败与音乐无关
                if (port != DVR_PORT && musicPort == port) {
                    musicPort = 0;
                }
                boolean woke = false;
                if (port == DVR_PORT && attempt == 1) {
                    // 记录仪代理已并进本进程（2026-11-25）：幂等 start 后重试一次，
                    // 覆盖「服务刚起、18767 还没 bind 完」那一小段窗口。
                    // 不再跨包发广播叫记录仪代理。
                    com.navitool.bridge.dvr.Dvr.start(ctx);
                    woke = com.navitool.bridge.dvr.Dvr.running();
                    Bridge.note("gateway: dvr 重试 " + (woke ? "端口在" : "端口仍不通"));
                }
                if (!woke) {
                    closeQuietly(client);
                    return;
                }
            } finally {
                closeQuietly(backend);
            }
        }
    }

    /** 探测音乐后端：缓存有效期内先复查还活着再用，否则重探 18765。 */
    private static int pickMusic() {
        long now = System.currentTimeMillis();
        if (musicPort != 0 && now - musicPortAt < MUSIC_TTL && reachable(musicPort)) {
            return musicPort;
        }
        for (int p : MUSIC_PORTS) {
            if (reachable(p)) {
                musicPort = p;
                musicPortAt = now;
                return p;
            }
        }
        musicPort = 0;
        return 0;
    }

    /** 把请求首行的 /music/xxx 改成 /xxx；没有前缀就原样。只动第一行第二个 token。 */
    private static byte[] stripPrefix(byte[] req, String prefix) {
        String head;
        int eol = indexOf(req, (byte) '\n');
        if (eol < 0) {
            return req;
        }
        try {
            head = new String(req, 0, eol, "ISO-8859-1");
        } catch (Exception e) {
            return req;
        }
        String[] parts = head.split(" ", 3);
        if (parts.length < 2 || !parts[1].startsWith(prefix)) {
            return req;
        }
        String newPath = parts[1].substring(prefix.length());
        if (newPath.length() == 0 || newPath.charAt(0) != '/') {
            newPath = "/" + newPath;
        }
        String newHead = parts[0] + " " + newPath
                + (parts.length > 2 ? " " + parts[2] : "");
        byte[] nh;
        try {
            nh = newHead.getBytes("ISO-8859-1");
        } catch (Exception e) {
            return req;
        }
        // nh + 1 个 '\n' + 首行之后剩余的全部字节
        byte[] out = new byte[nh.length + 1 + (req.length - eol - 1)];
        System.arraycopy(nh, 0, out, 0, nh.length);
        out[nh.length] = (byte) '\n';
        System.arraycopy(req, eol + 1, out, nh.length + 1, req.length - eol - 1);
        return out;
    }

    /** 双向透传：先重放已读到的请求头，再把两条 socket 的字节互相搬运，直到任一端关闭。 */
    private static void bridge(final Socket client, final Socket backend, byte[] replay)
            throws IOException {
        final OutputStream toBack = backend.getOutputStream();
        if (replay != null && replay.length > 0) {
            toBack.write(replay);
            toBack.flush();
        }
        Thread c2b = copy(client, backend, "gw-c2b");
        copy(backend, client, "gw-b2c");
        // b2c 返回说明后端那半边结束了。先给 c2b 一点时间把在途的客户端数据转完，
        // 再关 socket —— 反过来先关 client 会直接打断 c2b 的 read，丢尾部数据。
        try {
            c2b.join(400);
        } catch (InterruptedException ignored) {
        }
        closeQuietly(client);
        closeQuietly(backend);
    }

    private static Thread copy(final Socket from, final Socket to, String name) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    InputStream in = from.getInputStream();
                    OutputStream out = to.getOutputStream();
                    byte[] buf = new byte[BUF];
                    int n;
                    while ((n = in.read(buf)) > 0) {
                        out.write(buf, 0, n);
                        out.flush();
                    }
                } catch (Throwable ignored) {
                } finally {
                    closeQuietly(to);
                }
            }
        }, name);
        t.setDaemon(true);
        t.start();
        return t;
    }

    private static int indexOf(byte[] a, byte b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b) return i;
        }
        return -1;
    }

    private static void closeQuietly(Socket s) {
        if (s != null) {
            try {
                s.close();
            } catch (Throwable ignored) {
            }
        }
    }
}
