package com.luna.music.car;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * 18767 上的记录仪路由。只有这几类：
 *   /dvr/probe /dvr/state /dvr/setip /dvr/disconnect   连接管理
 *   /dvr/list?path=           目录 → JSON（文件夹 + 文件，时间倒序）
 *   /dvr/fs?p=                原厂目录页整页代理（列表解析不出时兜底）
 *   /dvr/file?p=&dl=1         单个文件，Range 透传，边下边发
 *
 * 不做缩略图、不做转码、不做播放页。
 */
final class DvrHttp {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int CONNECT_MS = 3000;
    private static final int READ_MS = 30000;
    private static final char[] HEX = "0123456789ABCDEF".toCharArray();

    private DvrHttp() {
    }

    static void handle(OutputStream out, HashMap<String, String> headers,
                       String method, String path, String target) {
        String query = "";
        int qm = target == null ? -1 : target.indexOf('?');
        if (qm >= 0) query = target.substring(qm + 1);

        try {
            if ("/dvr/probe".equals(path)) {
                json(out, DvrProxy.probe());
            } else if ("/dvr/state".equals(path)) {
                json(out, DvrProxy.stateJson());
            } else if ("/dvr/disconnect".equals(path)) {
                json(out, DvrProxy.disconnect());
            } else if ("/dvr/setip".equals(path)) {
                DvrProxy.setFixedIp(param(query, "ip"));
                json(out, DvrProxy.stateJson());
            } else if ("/dvr/list".equals(path)) {
                String p = param(query, "path");
                if (p.length() == 0) {
                    // 只带 type（老页面/手敲）时按默认目录读
                    String t = param(query, "type");
                    p = DvrProxy.dir(t.length() > 0 ? t : "normal") + "/";
                }
                json(out, DvrProxy.browse(p));
            } else if ("/dvr/fs".equals(path)) {
                html(out, DvrProxy.page(param(query, "p")));
            } else if ("/dvr/file".equals(path)) {
                String p = safePath(param(query, "p"));
                if (p == null) {
                    json(out, "{\"ok\":0,\"err\":\"bad path\"}");
                    return;
                }
                boolean dl = "1".equals(param(query, "dl"));
                stream(out, headers, p, dl, p.substring(p.lastIndexOf('/') + 1));
            } else {
                ServerIo.http(out, 404, "text/plain; charset=utf-8", "not found".getBytes(UTF8));
            }
        } catch (Throwable t) {
            try {
                json(out, "{\"ok\":0,\"err\":\"" + root(t).replace("\"", "'") + "\"}");
            } catch (Throwable ignored) {
            }
        }
    }

    /**
     * 从记录仪 80 端口流式转发一个文件。客户端 Range 原样透传；
     * 记录仪回 206 就转发 206 + Content-Range，回 200 就转发 200。
     */
    private static void stream(OutputStream out, HashMap<String, String> headers,
                               String resource, boolean download, String filename) throws Exception {
        String ip = DvrProxy.ip();
        if (ip == null || ip.length() == 0) {
            ServerIo.http(out, 502, "application/json; charset=utf-8",
                    "{\"ok\":0,\"err\":\"no dvr\"}".getBytes(UTF8));
            return;
        }
        String range = headers == null ? null : headers.get("range");

        Socket up = new Socket();
        try {
            up.connect(new InetSocketAddress(ip, DvrProxy.httpPort()), CONNECT_MS);
            up.setSoTimeout(READ_MS);
            InputStream uin = up.getInputStream();
            OutputStream uout = up.getOutputStream();

            StringBuilder req = new StringBuilder();
            req.append("GET ").append(encode(resource)).append(" HTTP/1.1\r\n")
                    .append("Host: ").append(ip).append("\r\n")
                    .append("User-Agent: Mozilla/5.0 (Linux; Android 9; Car)\r\n")
                    .append("Accept: */*\r\n");
            if (range != null && range.length() > 0) {
                req.append("Range: ").append(range).append("\r\n");
            }
            req.append("Connection: close\r\n\r\n");
            uout.write(req.toString().getBytes("ISO-8859-1"));
            uout.flush();

            String status = ServerIo.readLine(uin);
            if (status == null || status.length() == 0) {
                ServerIo.http(out, 502, "application/json; charset=utf-8",
                        "{\"ok\":0,\"err\":\"empty upstream\"}".getBytes(UTF8));
                return;
            }
            String contentType = "video/mp4";
            String contentRange = null;
            String contentLength = null;
            String line;
            while ((line = ServerIo.readLine(uin)) != null && line.length() > 0) {
                int c = line.indexOf(':');
                if (c <= 0) continue;
                String k = line.substring(0, c).trim().toLowerCase();
                String v = line.substring(c + 1).trim();
                if ("content-type".equals(k)) contentType = v;
                else if ("content-range".equals(k)) contentRange = v;
                else if ("content-length".equals(k)) contentLength = v;
            }

            StringBuilder head = new StringBuilder();
            head.append(status).append("\r\n")
                    .append("Content-Type: ").append(guessType(contentType, resource)).append("\r\n")
                    .append("Accept-Ranges: bytes\r\n");
            if (contentRange != null) head.append("Content-Range: ").append(contentRange).append("\r\n");
            if (contentLength != null) head.append("Content-Length: ").append(contentLength).append("\r\n");
            if (download) {
                String fn = filename == null ? "video.mp4" : filename;
                int slash = fn.lastIndexOf('/');
                if (slash >= 0) fn = fn.substring(slash + 1);
                head.append("Content-Disposition: attachment; filename=\"").append(safeName(fn)).append("\"\r\n");
            }
            head.append("Connection: close\r\n")
                    .append("Cache-Control: no-store\r\n\r\n");
            out.write(head.toString().getBytes("ISO-8859-1"));
            out.flush();

            byte[] buf = new byte[8192];
            int n;
            while ((n = uin.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
            out.flush();
        } finally {
            try { up.close(); } catch (Throwable ignored) {}
        }
    }

    // ------------------------------------------------------------------ 工具

    /** 请求行里的路径：只留 ASCII 安全字符，其余 %XX */
    private static String encode(String path) {
        StringBuilder b = new StringBuilder(path.length() + 8);
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            boolean ok = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                    || (c >= '0' && c <= '9') || c == '/' || c == '_' || c == '-'
                    || c == '.' || c == '~';
            if (ok) {
                b.append(c);
                continue;
            }
            byte[] bs;
            try {
                bs = String.valueOf(c).getBytes("UTF-8");
            } catch (Throwable t) {
                bs = new byte[] { (byte) c };
            }
            for (int k = 0; k < bs.length; k++) {
                b.append('%').append(HEX[(bs[k] >> 4) & 15]).append(HEX[bs[k] & 15]);
            }
        }
        return b.toString();
    }

    private static String param(String query, String key) {
        if (query == null || query.length() == 0) return "";
        String[] parts = query.split("&");
        for (int i = 0; i < parts.length; i++) {
            int e = parts[i].indexOf('=');
            if (e < 0) continue;
            if (!key.equals(parts[i].substring(0, e))) continue;
            try {
                return URLDecoder.decode(parts[i].substring(e + 1), "UTF-8");
            } catch (Throwable t) {
                return parts[i].substring(e + 1);
            }
        }
        return "";
    }

    private static void json(OutputStream out, String body) throws Exception {
        ServerIo.http(out, 200, "application/json; charset=utf-8", body.getBytes(UTF8));
    }

    private static void html(OutputStream out, String body) throws Exception {
        ServerIo.http(out, 200, "text/html; charset=utf-8", body.getBytes(UTF8));
    }

    /** 记录仪回的内容常常是 octet-stream，按扩展名纠一下，浏览器才肯存/播 */
    private static String guessType(String ct, String res) {
        String c = ct == null ? "" : ct.trim();
        String lc = c.toLowerCase();
        if (lc.length() > 0 && lc.indexOf("octet-stream") < 0 && lc.indexOf("text/plain") < 0) return c;
        String r = res == null ? "" : res.toLowerCase();
        if (r.endsWith(".mp4") || r.endsWith(".m4v")) return "video/mp4";
        if (r.endsWith(".ts")) return "video/mp2t";
        if (r.endsWith(".mov")) return "video/quicktime";
        if (r.endsWith(".avi")) return "video/x-msvideo";
        if (r.endsWith(".mkv")) return "video/x-matroska";
        if (r.endsWith(".flv")) return "video/x-flv";
        if (r.endsWith(".jpg") || r.endsWith(".jpeg")) return "image/jpeg";
        if (r.endsWith(".png")) return "image/png";
        return c.length() == 0 ? "application/octet-stream" : c;
    }

    /** 记录仪上的路径：必须以 / 开头、不许 .. 和反斜杠 */
    private static String safePath(String p) {
        if (p == null) return null;
        String s = p.trim();
        if (s.length() == 0 || s.length() > 400) return null;
        if (s.charAt(0) != '/' || s.indexOf("..") >= 0 || s.indexOf('\\') >= 0) return null;
        return s;
    }

    private static String safeName(String s) {
        if (s == null) return "video.mp4";
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < s.length() && i < 120; i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                    || (c >= '0' && c <= '9') || c == '.' || c == '_' || c == '-') {
                b.append(c);
            }
        }
        return b.length() == 0 ? "video.mp4" : b.toString();
    }

    private static String root(Throwable t) {
        if (t == null) return "";
        String m = t.getMessage();
        return t.getClass().getSimpleName() + (m == null ? "" : ": " + m);
    }
}
