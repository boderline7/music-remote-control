package com.navitool.bridge.dvr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
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
            } else if ("/dvr/diag".equals(path)) {
                json(out, DvrProxy.diag());
            } else if ("/dvr/state".equals(path)) {
                json(out, DvrProxy.stateJson());
            } else if ("/dvr/logs".equals(path)) {
                ServerIo.httpDownload(out, 200, "text/plain; charset=utf-8",
                        "dvr-log.txt", DvrProxy.dumpLogs().getBytes(UTF8));
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
            } else if ("/dvr/info".equals(path)) {
                String p = safePath(param(query, "p"));
                if (p == null) {
                    json(out, "{\"ok\":0,\"err\":\"bad path\"}");
                    return;
                }
                json(out, DvrProxy.fileInfo(p));
            } else if ("/dvr/file".equals(path)) {
                String p = safePath(param(query, "p"));
                if (p == null) {
                    json(out, "{\"ok\":0,\"err\":\"bad path\"}");
                    return;
                }
                boolean dl = "1".equals(param(query, "dl"));
                stream(out, headers, p, dl, p.substring(p.lastIndexOf('/') + 1), method);
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
     * 一个文件的出口：本地缓存命中就直接按 Range 出，没有才去记录仪取；
     * 去取的时候顺手落盘（完整收完才认），第二次播放/下载就只走车机本地。
     *
     * 为什么必须本地支持 Range：手机播放器（AVPlayer / ExoPlayer）会先要
     * `bytes=0-1` 探长度，再按需拖进度；把 Range 交给记录仪转一圈，弱设备
     * 经常回 200 全量或干脆不应答，表现就是转圈、拖不动。缓存命中后这些都变成读本地文件。
     */
    private static void stream(OutputStream out, HashMap<String, String> headers,
                               String resource, boolean download, String filename,
                               String method) throws Exception {
        File cached = DvrProxy.cachedFile(resource);
        if (cached != null) {
            serveLocal(out, headers, cached, resource, download, filename, method);
            return;
        }

        String ip = DvrProxy.ip();
        if (ip == null || ip.length() == 0) {
            ServerIo.http(out, 502, "application/json; charset=utf-8",
                    "{\"ok\":0,\"err\":\"no dvr\"}".getBytes(UTF8));
            return;
        }
        String range = headers == null ? null : headers.get("range");
        // 只要从 0 开始的请求都算「从头」，都值得落盘：
        // bytes=0-（整段）、bytes=0-1023（探长度）、bytes=0-<总长>（ExoPlayer 常见）。
        // bytes=5000- 这种才是拖进度，落不落无所谓，直接透传。
        String rangeLow = range == null ? "" : range.trim().toLowerCase();
        boolean fromZero = range == null || rangeLow.length() == 0
                || rangeLow.startsWith("bytes=0-");

        Socket up = new Socket();
        File tmp = null;
        FileOutputStream fos = null;
        long expect = -1;
        long got = 0;
        boolean complete = false;
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
            String transferEncoding = null;
            String line;
            while ((line = ServerIo.readLine(uin)) != null && line.length() > 0) {
                int c = line.indexOf(':');
                if (c <= 0) continue;
                String k = line.substring(0, c).trim().toLowerCase();
                String v = line.substring(c + 1).trim();
                if ("content-type".equals(k)) contentType = v;
                else if ("content-range".equals(k)) contentRange = v;
                else if ("content-length".equals(k)) contentLength = v;
                else if ("transfer-encoding".equals(k)) transferEncoding = v;
            }
            boolean chunked = transferEncoding != null
                    && transferEncoding.toLowerCase().indexOf("chunked") >= 0;
            if (chunked) contentLength = null;      // 分片没有总长度，别给客户端假头
            if (contentLength != null) {
                try { expect = Long.parseLong(contentLength.trim()); } catch (Throwable ignored) {}
            }

            // 缓存条件：GET、视频、从头整段、长度可接受；分片没有总长度，不落盘
            if (!"HEAD".equals(method) && fromZero && !chunked && isCacheable(resource)
                    && DvrProxy.cacheAllows(expect)) {
                tmp = DvrProxy.cacheTemp(resource);
                if (tmp != null) {
                    try { fos = new FileOutputStream(tmp); } catch (Throwable t) { tmp = null; fos = null; }
                }
            }

            StringBuilder head = new StringBuilder();
            head.append(status).append("\r\n")
                    .append("Content-Type: ").append(guessType(contentType, resource)).append("\r\n")
                    .append("Accept-Ranges: bytes\r\n");
            if (contentRange != null) head.append("Content-Range: ").append(contentRange).append("\r\n");
            if (contentLength != null) head.append("Content-Length: ").append(contentLength).append("\r\n");
            if (download) head.append("Content-Disposition: attachment; filename=\"")
                    .append(safeName(fileNameOf(filename))).append("\"\r\n");
            head.append("Access-Control-Allow-Origin: *\r\n")
                    .append("Connection: close\r\n")
                    .append("Cache-Control: no-store\r\n\r\n");
            out.write(head.toString().getBytes("ISO-8859-1"));
            out.flush();

            if ("HEAD".equals(method)) return;

            byte[] buf = new byte[32768];
            boolean[] gone = new boolean[] { false };
            // 播放器一拖进度就把连接断了。以前这里跟着退出，缓存永远半截；
            // 现在只要还在落盘就继续把它下完（最多再花 3 分钟），下次就整段本地出。
            long keepUntil = System.currentTimeMillis() + 180_000L;
            if (chunked) {
                got = copyChunked(uin, out, fos, buf, gone);
            } else {
                int n;
                while ((n = uin.read(buf)) > 0) {
                    if (!gone[0]) {
                        try {
                            out.write(buf, 0, n);
                        } catch (Throwable t) {
                            gone[0] = true;
                            DvrProxy.note("文件 " + fileNameOf(resource) + " 客户端断开，继续落缓存");
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.write(buf, 0, n);
                        } catch (Throwable t) {
                            // 车机存储写满不能把播放一起打断：放弃这一份缓存，继续转发
                            try { fos.close(); } catch (Throwable ignored) {}
                            fos = null;
                            if (tmp != null) {
                                tmp.delete();
                                tmp = null;
                            }
                        }
                    }
                    got += n;
                    if (gone[0]) {
                        if (fos == null) break;                       // 没在缓存就别白读
                        if (System.currentTimeMillis() > keepUntil) break;
                    }
                }
            }
            if (!gone[0]) out.flush();
            complete = true;
        } finally {
            if (fos != null) {
                try { fos.flush(); fos.close(); } catch (Throwable ignored) {}
            }
            try { up.close(); } catch (Throwable ignored) {}
            if (tmp != null) {
                if (complete && got > 0 && (expect < 0 || got == expect)) {
                    DvrProxy.cacheDone(tmp, resource);
                } else {
                    DvrProxy.cacheDrop(tmp);
                }
            }
        }
    }

    /** 分片应答（Transfer-Encoding: chunked）去帧后转发：客户端读到连接关闭为止 */
    private static long copyChunked(InputStream in, OutputStream out, FileOutputStream fos,
                                    byte[] buf, boolean[] gone) throws Exception {
        long total = 0;
        while (true) {
            String line = ServerIo.readLine(in);
            if (line == null) break;
            int semi = line.indexOf(';');
            String hex = (semi >= 0 ? line.substring(0, semi) : line).trim();
            if (hex.length() == 0) continue;
            long size;
            try { size = Long.parseLong(hex, 16); } catch (Throwable t) { break; }
            if (size == 0) {
                String tail;
                while ((tail = ServerIo.readLine(in)) != null && tail.length() > 0) {
                    // 吃掉 trailer
                }
                break;
            }
            long left = size;
            while (left > 0) {
                int want = (int) Math.min(buf.length, left);
                int n = in.read(buf, 0, want);
                if (n <= 0) throw new Exception("chunk eof");
                if (!gone[0]) {
                    try {
                        out.write(buf, 0, n);
                    } catch (Throwable t) {
                        gone[0] = true;
                    }
                }
                if (fos != null) fos.write(buf, 0, n);
                left -= n;
                total += n;
            }
            ServerIo.readLine(in);          // 每个 chunk 后面的 CRLF
        }
        return total;
    }

    /** 缓存命中：完全按请求的 Range 从本地文件出 200 / 206 / 416，支持 HEAD */
    private static void serveLocal(OutputStream out, HashMap<String, String> headers, File f,
                                   String resource, boolean download, String filename,
                                   String method) throws Exception {
        long total = f.length();
        long start = 0;
        long end = total - 1;
        boolean partial = false;
        String range = headers == null ? null : headers.get("range");
        if (range != null && range.toLowerCase().startsWith("bytes=")) {
            String spec = range.substring(6).trim();
            int dash = spec.indexOf('-');
            if (dash >= 0) {
                String a = spec.substring(0, dash).trim();
                String b = spec.substring(dash + 1).trim();
                try {
                    if (a.length() == 0) {
                        long suffix = Long.parseLong(b);
                        if (suffix > 0) start = Math.max(0, total - suffix);
                    } else {
                        start = Long.parseLong(a);
                        if (b.length() > 0) end = Long.parseLong(b);
                    }
                } catch (Throwable ignored) {
                    start = 0;
                    end = total - 1;
                }
            }
            if (start < 0) start = 0;
            if (end >= total) end = total - 1;
            if (start > end || start >= total) {
                String h = "HTTP/1.1 416 Range Not Satisfiable\r\n"
                        + "Content-Range: bytes */" + total + "\r\n"
                        + "Content-Length: 0\r\nConnection: close\r\n\r\n";
                out.write(h.getBytes("ISO-8859-1"));
                out.flush();
                return;
            }
            partial = true;
        }

        long len = end - start + 1;
        StringBuilder head = new StringBuilder();
        head.append(partial ? "HTTP/1.1 206 Partial Content\r\n" : "HTTP/1.1 200 OK\r\n")
                .append("Content-Type: ").append(guessType(null, resource)).append("\r\n")
                .append("Accept-Ranges: bytes\r\n")
                .append("Content-Length: ").append(len).append("\r\n");
        if (partial) {
            head.append("Content-Range: bytes ").append(start).append('-').append(end)
                    .append('/').append(total).append("\r\n");
        }
        if (download) head.append("Content-Disposition: attachment; filename=\"")
                .append(safeName(fileNameOf(filename))).append("\"\r\n");
        head.append("Access-Control-Allow-Origin: *\r\n")
                .append("Connection: close\r\n")
                .append("Cache-Control: no-store\r\n\r\n");
        out.write(head.toString().getBytes("ISO-8859-1"));
        out.flush();
        if ("HEAD".equals(method)) return;

        RandomAccessFile raf = new RandomAccessFile(f, "r");
        try {
            raf.seek(start);
            byte[] buf = new byte[32768];
            long left = len;
            while (left > 0) {
                int want = (int) Math.min(buf.length, left);
                int n = raf.read(buf, 0, want);
                if (n <= 0) break;
                out.write(buf, 0, n);
                left -= n;
            }
            out.flush();
        } finally {
            try { raf.close(); } catch (Throwable ignored) {}
        }
    }

    private static boolean isCacheable(String resource) {
        String r = resource == null ? "" : resource.toLowerCase();
        return r.endsWith(".mp4") || r.endsWith(".m4v") || r.endsWith(".mov")
                || r.endsWith(".ts") || r.endsWith(".mkv") || r.endsWith(".avi")
                || r.endsWith(".flv");
    }

    private static String fileNameOf(String fn) {
        String s = fn == null ? "video.mp4" : fn;
        int slash = s.lastIndexOf('/');
        return slash >= 0 ? s.substring(slash + 1) : s;
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
