package com.luna.music.car;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * 行车记录仪代理 —— 只干一件事：把记录仪自带的文件系统网页翻成 JSON / 转发字节。
 *
 * 发现：车机各网卡网段里，TCP 连得上 7878 的就是它（只判通不通，不发命令）。
 * 列表：GET http://<ip>/SD0/NOR/M_video/ 这种目录页，扫 href 拿文件夹和文件。
 * 文件：GET http://<ip><完整路径>，Range 原样透传，边下边发。
 *
 * 不解析记录仪的私有协议，也不做缩略图、不转码，够用就行。
 */
final class DvrProxy {

    /** 认设备用：7878 通了就是记录仪 */
    private static final int MARK_PORT = 7878;
    private static final int HTTP_PORT = 80;
    private static final int CONNECT_MS = 1500;
    private static final int HTTP_CONNECT_MS = 3500;
    /** 目录页上限：一页几十 KB，给 512KB 足够，别让它把内存吃掉 */
    private static final int FS_MAX = 512 * 1024;
    /** 正文读取预算：记录仪吐 130KB 的目录页要几秒 */
    private static final int FS_READ_MS = 9000;
    /**
     * 首字节预算。以前一个形态一直挂到 9 秒，三个形态就是 27 秒 —— 记录仪明明活得好好的，
     * 用户也要等半分钟。现在分开给：记住的形态给足（弱设备慢启动实测 3~6 秒），
     * 换形态试探只给短的，不对就赶紧换下一个。
     */
    private static final int HEAD_MS = 6000;
    private static final int HEAD_RETRY_MS = 4500;
    private static final int HEAD_PROBE_MS = 3000;
    /**
     * 请求形态。
     * 0 = HTTP/1.1 + Connection: close
     * 1 = HTTP/1.0（老式极简 httpd 只认这个）
     * 2 = HTTP/1.1 不带 Connection 头（keep-alive，Chrome 的默认行为）
     *
     * 这台记录仪（192.168.228.x）实测：形态 0 一律不应答，1 / 2 才回。
     * 所以试探顺序不再拿 0 打头，成功一次就按 IP 记住、落盘，重启也不用重试。
     */
    private static final int MODE_CLOSE = 0;
    private static final int MODE_10 = 1;
    private static final int MODE_KEEP = 2;
    /** 目录列表缓存：新鲜期直接返回不打设备；读失败时用旧结果兜底 */
    private static final long CACHE_FRESH_MS = 45 * 1000;
    private static final long CACHE_STALE_MS = 5 * 60 * 1000;
    private static final java.util.HashMap<String, String> DIR_CACHE =
            new java.util.HashMap<String, String>();
    private static final java.util.HashMap<String, String> DIR_CACHE_STAT =
            new java.util.HashMap<String, String>();
    private static final java.util.HashMap<String, Long> DIR_CACHE_T =
            new java.util.HashMap<String, Long>();
    /** 记录仪网页服务很弱：同一时刻只放一个请求过去，其余等结果或吃缓存 */
    private static final Object FS_LOCK = new Object();
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final char[] HEX = "0123456789ABCDEF".toCharArray();

    /** DVR 操作日志：连接、目录抓取、命中/失败都记一条，网页可导出。 */
    private static final String[] LOGBUF = new String[600];
    private static int logHead = 0;
    private static int logCount = 0;
    private static final java.text.SimpleDateFormat LOGFMT =
            new java.text.SimpleDateFormat("MM-dd HH:mm:ss", java.util.Locale.US);

    /** 最近一次探测到的记录仪 IP */
    private static volatile String cachedIp = "";
    /** 每台记录仪认的请求形态：ip → mode，成功过就记住，落盘跨重启 */
    private static final java.util.HashMap<String, Integer> MODE_BY_IP =
            new java.util.HashMap<String, Integer>();
    private static volatile int lastOkMode = -1;
    /** 整轮都不应答之后的冷却：同一个 ip+path 短时间内不再重复打，避免连点把设备打穿 */
    private static volatile String coolKey = "";
    private static volatile long coolUntil = 0;
    private static final long FAIL_COOL_MS = 3000;
    /** 连点「断开」的合并窗口：日志里一次能刷十几条，顺手把记忆的形态也清了 */
    private static volatile long lastDisconnectT = 0;
    /** 页面「指定 IP」；留空则自动探测 */
    private static volatile String fixedIp = "";
    /** 按过「断开」后置真：此后不再往记录仪发任何字节 */
    private static volatile boolean offline = false;

    private DvrProxy() {
    }

    // ------------------------------------------------------------------ 配置

    static void setFixedIp(String ip) {
        fixedIp = ip == null ? "" : ip.trim();
        cachedIp = "";
        offline = true;              // 改地址后要重新点「连接」
        clearCache();
        note("fixedIp=" + (fixedIp.length() == 0 ? "(auto)" : fixedIp));
    }

    static String disconnect() {
        long now = System.currentTimeMillis();
        if (offline && now - lastDisconnectT < 1500) {
            return stateJson();          // 连点合并：日志里一次刷十几条，还把记忆的形态冲掉了
        }
        lastDisconnectT = now;
        offline = true;
        clearCache();
        note("disconnect (ip=" + cachedIp + ")");
        return stateJson();
    }

    /** 换设备/断开时把旧列表丢掉；协议记忆按 IP 存，不受影响 */
    private static void clearCache() {
        synchronized (FS_LOCK) {
            DIR_CACHE.clear();
            DIR_CACHE_STAT.clear();
            DIR_CACHE_T.clear();
            coolKey = "";
            coolUntil = 0;
        }
    }

    // ------------------------------------------------------- 请求形态记忆（落盘）

    private static File modeFile;

    private static void loadModes() {
        if (modeFile == null || !modeFile.isFile()) return;
        try {
            java.io.BufferedReader r = new java.io.BufferedReader(
                    new java.io.InputStreamReader(new java.io.FileInputStream(modeFile), "UTF-8"));
            String line;
            while ((line = r.readLine()) != null) {
                int sp = line.indexOf(' ');
                if (sp <= 0) continue;
                try {
                    MODE_BY_IP.put(line.substring(0, sp).trim(),
                            Integer.valueOf(Integer.parseInt(line.substring(sp + 1).trim())));
                } catch (Throwable ignored) {
                }
            }
            r.close();
        } catch (Throwable ignored) {
        }
    }

    private static void saveModes() {
        if (modeFile == null) return;
        try {
            StringBuilder sb = new StringBuilder();
            synchronized (MODE_BY_IP) {
                for (java.util.Map.Entry<String, Integer> e : MODE_BY_IP.entrySet()) {
                    sb.append(e.getKey()).append(' ').append(e.getValue()).append('\n');
                }
            }
            java.io.FileOutputStream out = new java.io.FileOutputStream(modeFile);
            out.write(sb.toString().getBytes("UTF-8"));
            out.close();
        } catch (Throwable ignored) {
        }
    }

    /** 这台设备记住的形态；没记录返回 -1 */
    private static int modeFor(String ip) {
        synchronized (MODE_BY_IP) {
            Integer m = MODE_BY_IP.get(ip);
            return m == null ? -1 : m.intValue();
        }
    }

    private static void rememberMode(String ip, int mode) {
        synchronized (MODE_BY_IP) {
            MODE_BY_IP.put(ip, Integer.valueOf(mode));
        }
        lastOkMode = mode;
        saveModes();
    }

    static String stateJson() {
        return "{\"ok\":1,\"ip\":" + q(cachedIp)
                + ",\"fixed\":" + q(fixedIp)
                + ",\"port\":" + HTTP_PORT
                + ",\"log\":" + logCount
                + ",\"mode\":" + lastOkMode
                + ",\"cacheN\":" + cacheCount()
                + ",\"cacheMB\":" + (cacheBytes() / 1024 / 1024)
                + ",\"off\":" + (offline ? 1 : 0) + "}";
    }

    static String ip() {
        return cachedIp;
    }

    /**
     * 诊断：一步一步告诉用户卡在哪。
     * 7878 通不通 → 80 建连要多久 → 根路径有没有字节回、回的状态行是什么。
     * 「连不上」和「路径不对」在页面上长得一样，这个能把它们分开。
     */
    static String diag() {
        String ip = cachedIp.length() > 0 ? cachedIp : fixedIp;
        StringBuilder sb = new StringBuilder(256);
        sb.append("{\"ok\":1,\"ip\":").append(q(ip));
        if (ip.length() == 0) return sb.append(",\"err\":\"还没找到记录仪\"").append('}').toString();

        long markT = System.currentTimeMillis();
        boolean mark = reachable(ip);
        sb.append(",\"mark\":").append(mark ? 1 : 0)
                .append(",\"markMs\":").append(System.currentTimeMillis() - markT);

        Socket s = new Socket();
        boolean open = false;
        long t0 = System.currentTimeMillis();
        try {
            s.connect(new InetSocketAddress(ip, HTTP_PORT), HTTP_CONNECT_MS);
            open = true;
        } catch (Throwable t) {
            sb.append(",\"port80\":0,\"port80Err\":").append(q(root(t)));
        }
        long connMs = System.currentTimeMillis() - t0;
        if (!open) {
            closeQuiet(s);
            return sb.append('}').toString();
        }
        sb.append(",\"port80\":1,\"connMs\":").append(connMs);

        try {
            s.setSoTimeout(4000);
            OutputStream os = s.getOutputStream();
            os.write(("GET / HTTP/1.1\r\nHost: " + ip + "\r\nAccept: */*\r\n\r\n")
                    .getBytes("ISO-8859-1"));
            os.flush();
            InputStream in = s.getInputStream();
            long t1 = System.currentTimeMillis();
            int c = in.read();
            long ms = System.currentTimeMillis() - t1;
            if (c < 0) {
                sb.append(",\"root\":\"closed\",\"rootMs\":").append(ms);
            } else {
                StringBuilder head = new StringBuilder();
                head.append((char) c);
                byte[] b = new byte[512];
                try {
                    int n = in.read(b);
                    if (n > 0) head.append(new String(b, 0, n, "ISO-8859-1"));
                } catch (Throwable ignored) {}
                String h = head.toString();
                int e = h.indexOf("\r\n");
                String statusLine = e > 0 ? h.substring(0, Math.min(e, 100)) : h.substring(0, Math.min(h.length(), 100));
                sb.append(",\"root\":\"bytes\",\"rootMs\":").append(ms)
                        .append(",\"status\":").append(q(statusLine))
                        .append(",\"bytes\":").append(head.length());
            }
        } catch (Throwable t) {
            sb.append(",\"root\":\"none\",\"rootErr\":").append(q(root(t)));
        } finally {
            closeQuiet(s);
        }
        return sb.append('}').toString();
    }

    static int httpPort() {
        return HTTP_PORT;
    }

    // ------------------------------------------------------------- 文件磁盘缓存

    /**
     * 录像文件缓存：第一次看/下载时边转发边落盘，之后同一文件直接从车机本地出。
     *
     * 为什么值得缓存：手机到记录仪要穿两层无线（手机→车机热点→车机→记录仪），
     * 每次播放、拖动进度、下载都要在弱设备上重新走一遍；缓存后第二次起只走一层，
     * 进度条任意拖、下载立即出。目录列表缓存（DIR_CACHE）只解决"看得见"，这里解决"播得顺"。
     *
     * 上限 512MB，按最后访问时间淘汰；只缓存视频，不改记录仪上的任何东西。
     */
    private static volatile File fileCacheDir = null;
    private static final long FILE_CACHE_MAX = 512L * 1024 * 1024;
    private static final long PART_TTL_MS = 30L * 60 * 1000;

    static void init(android.content.Context c) {
        if (fileCacheDir != null) return;
        File base = null;
        try {
            base = c.getExternalFilesDir(null);
        } catch (Throwable ignored) {
        }
        if (base == null) base = c.getCacheDir();
        File d = new File(base, "dvrfile");
        if (!d.isDirectory() && !d.mkdirs()) {
            d = new File(c.getCacheDir(), "dvrfile");
            if (!d.isDirectory()) d.mkdirs();
        }
        fileCacheDir = d;
        trimCache();
        note("文件缓存 " + d.getAbsolutePath() + " 上限 " + (FILE_CACHE_MAX / 1024 / 1024) + "MB");
        try {
            modeFile = new File(c.getFilesDir(), "dvr_mode.txt");
            loadModes();
        } catch (Throwable ignored) {
        }
    }

    /** 已完成缓存的本地文件；没有就 null */
    static File cachedFile(String path) {
        File d = fileCacheDir;
        if (d == null || path == null || path.length() == 0) return null;
        File f = new File(d, cacheKey(path));
        return (f.isFile() && f.length() > 0) ? f : null;
    }

    /** 正在写入的半成品：名字带纳秒，两个请求同时落盘也不会互相覆盖 */
    static File cacheTemp(String path) {
        File d = fileCacheDir;
        if (d == null || path == null || path.length() == 0) return null;
        return new File(d, cacheKey(path) + ".part." + System.nanoTime());
    }

    /** 完整收完才改名成正式缓存，半截文件一律不算命中 */
    static void cacheDone(File tmp, String path) {
        File d = fileCacheDir;
        if (tmp == null || d == null || path == null) return;
        try {
            File dest = new File(d, cacheKey(path));
            if (dest.exists()) dest.delete();
            if (tmp.renameTo(dest)) {
                note("文件已缓存 " + path + " (" + (dest.length() / 1024) + "KB)");
            } else {
                tmp.delete();
            }
        } catch (Throwable t) {
            try { tmp.delete(); } catch (Throwable ignored) {}
        }
        trimCache();
    }

    static void cacheDrop(File tmp) {
        if (tmp == null) return;
        try { tmp.delete(); } catch (Throwable ignored) {}
    }

    static int cacheCount() {
        File d = fileCacheDir;
        if (d == null) return 0;
        File[] fs = d.listFiles();
        if (fs == null) return 0;
        int n = 0;
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].isFile() && fs[i].getName().indexOf(".part") < 0) n++;
        }
        return n;
    }

    static long cacheBytes() {
        File d = fileCacheDir;
        if (d == null) return 0;
        File[] fs = d.listFiles();
        if (fs == null) return 0;
        long total = 0;
        for (int i = 0; i < fs.length; i++) {
            if (fs[i].isFile() && fs[i].getName().indexOf(".part") < 0) total += fs[i].length();
        }
        return total;
    }

    /** 单个文件太大就不落盘，免得一次把上限吃满、还把别的缓存挤掉 */
    static boolean cacheAllows(long len) {
        return len <= 0 || len <= 400L * 1024 * 1024;
    }

    private static String cacheKey(String path) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(path.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(32);
            for (int i = 0; i < b.length; i++) {
                sb.append(HEX[(b[i] >> 4) & 15]).append(HEX[b[i] & 15]);
            }
            return sb.toString();
        } catch (Throwable t) {
            return "f" + Integer.toHexString(path.hashCode());
        }
    }

    /** 先清半小时前留下的半截文件，再按最后访问时间淘汰到上限以内 */
    private static void trimCache() {
        File d = fileCacheDir;
        if (d == null) return;
        File[] fs = d.listFiles();
        if (fs == null) return;
        long now = System.currentTimeMillis();
        long total = 0;
        for (int i = 0; i < fs.length; i++) {
            File f = fs[i];
            if (!f.isFile()) continue;
            if (f.getName().indexOf(".part") >= 0) {
                if (now - f.lastModified() > PART_TTL_MS) f.delete();
                continue;
            }
            total += f.length();
        }
        if (total <= FILE_CACHE_MAX) return;
        java.util.Arrays.sort(fs, new java.util.Comparator<File>() {
            public int compare(File a, File b) {
                long x = a.lastModified();
                long y = b.lastModified();
                return x < y ? -1 : (x > y ? 1 : 0);
            }
        });
        for (int i = 0; i < fs.length && total > FILE_CACHE_MAX; i++) {
            File f = fs[i];
            if (!f.isFile() || f.getName().indexOf(".part") >= 0) continue;
            long len = f.length();
            if (f.delete()) total -= len;
        }
    }

    // ------------------------------------------------------------------ 发现

    /**
     * 探测单飞。
     *
     * 页面上的「连接」是可以连点的，以前每次都真起一轮全网段扫描（160 线程 × 2032 个 IP），
     * 五次连点就是上万个 TCP 连接砸过去。记录仪是单芯片的小 HTTP 服务，
     * 被这么打一遍之后 80 端口还接 SYN、但业务线程已经不应答了 ——
     * 日志里「TCP 连上，请求发出去，9 秒一个字节都没回来」就是这么来的。
     * 所以同一时刻只允许一轮扫描，后面的等它的结果。
     */
    private static final Object PROBE_LOCK = new Object();
    private static boolean probing = false;
    private static String probeLast = null;

    static String probe() {
        synchronized (PROBE_LOCK) {
            if (probing) {
                long until = System.currentTimeMillis() + 16000;
                while (probing && System.currentTimeMillis() < until) {
                    try {
                        PROBE_LOCK.wait(400);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                if (probing) return "{\"ok\":0,\"err\":\"正在扫描记录仪，稍等再点\"}";
                if (probeLast != null) return probeLast;
                return "{\"ok\":0,\"err\":\"没找到记录仪\"}";
            }
            probing = true;
        }
        try {
            probeLast = probeInner();
            return probeLast;
        } finally {
            synchronized (PROBE_LOCK) {
                probing = false;
                PROBE_LOCK.notifyAll();
            }
        }
    }

    /** 点「连接」：固定 IP → 缓存 IP → 扫各网卡网段 */
    private static String probeInner() {
        offline = false;
        note("probe 开始" + (fixedIp.length() > 0 ? " fixed=" + fixedIp : " 自动扫描"));
        if (fixedIp.length() > 0) {
            boolean mark = reachable(fixedIp);
            boolean http = httpPortOpen(fixedIp);
            boolean ok = mark || http;
            if (ok) cachedIp = fixedIp;
            note("probe fixed 结果 " + (ok ? "命中 " + fixedIp : "不通")
                    + "（7878 " + (mark ? "通" : "不通") + "，80 " + (http ? "通" : "不通") + "）");
            return probeJson(ok, cachedIp, "fixed");
        }
        if (cachedIp.length() > 0) {
            boolean mark = reachable(cachedIp);
            boolean http = httpPortOpen(cachedIp);
            if (mark || http) {
                note("probe cache 命中 " + cachedIp
                        + "（7878 " + (mark ? "通" : "不通") + "，80 " + (http ? "通" : "不通") + "）");
                return probeJson(true, cachedIp, "cache");
            }
            note("probe cache 失效 " + cachedIp + "（7878/80 都不通），重新扫描");
            cachedIp = "";
        }
        java.util.List<String> ips = localIps();
        if (ips.isEmpty()) {
            return "{\"ok\":0,\"err\":\"车机没有可用的内网 IPv4 地址\"}";
        }
        final java.util.LinkedHashSet<String> prefixes = new java.util.LinkedHashSet<String>();
        for (int i = 0; i < ips.size(); i++) {
            String s = ips.get(i);
            String ip = s.substring(s.indexOf(':') + 1);
            int dot = ip.lastIndexOf('.');
            if (dot > 0) prefixes.add(ip.substring(0, dot + 1));
        }
        final java.util.List<String> cand = new java.util.ArrayList<String>();
        for (String p : prefixes) {
            for (int last = 1; last <= 254; last++) cand.add(p + last);
        }
        final java.util.List<String> marks =
                java.util.Collections.synchronizedList(new java.util.ArrayList<String>());
        java.util.concurrent.ExecutorService pool =
                java.util.concurrent.Executors.newFixedThreadPool(160);
        java.util.concurrent.CountDownLatch done =
                new java.util.concurrent.CountDownLatch(cand.size());
        for (int i = 0; i < cand.size(); i++) {
            final String ip = cand.get(i);
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        // 只扫 7878 认设备；80 是否讲 HTTP 放到扫完之后再校，
                        // 免得扫的过程中对同一台设备连两次、把它那点连接数吃光
                        if (reachable(ip)) marks.add(ip);
                    } finally {
                        done.countDown();
                    }
                }
            });
        }
        try {
            done.await(12, java.util.concurrent.TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {
        }
        pool.shutdownNow();

        // 7878 通的可能不止一台（路由器、别的车机模块），真正能用的是 80 也接得上的那台
        java.util.List<String> list = new java.util.ArrayList<String>(marks);
        java.util.Collections.sort(list);
        String hit = null;
        for (int i = 0; i < list.size(); i++) {
            String ip = list.get(i);
            boolean http = httpPortOpen(ip);
            note("probe 候选 " + ip + " 80 " + (http ? "通" : "不通"));
            if (http) { hit = ip; break; }
        }
        if (hit == null && !list.isEmpty()) hit = list.get(0);   // 80 全不通就先用第一台试
        if (hit != null) {
            cachedIp = hit;
            note("probe 扫描命中 " + hit + "（扫了 " + cand.size() + " 个，7878 通 "
                    + list.size() + " 台）");
            return probeJson(true, hit, "scan");
        }
        note("probe 扫描无结果（扫了 " + cand.size() + " 个）");
        return "{\"ok\":0,\"err\":\"扫了所有网卡网段都没找到记录仪\","
                + "\"local\":" + q(ips.toString()) + "}";
    }

    private static String probeJson(boolean ok, String ip, String via) {
        return "{\"ok\":" + (ok ? 1 : 0)
                + ",\"ip\":" + q(ip)
                + ",\"via\":" + q(via) + "}";
    }

    private static boolean reachable(String ip) {
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress(ip, MARK_PORT), CONNECT_MS);
            return true;
        } catch (Throwable t) {
            return false;
        } finally {
            closeQuiet(s);
        }
    }

    /** 80 端口能建连才算"能用"：只有 7878 通的，往往根本不是记录仪 */
    private static boolean httpPortOpen(String ip) {
        if (ip == null || ip.length() == 0) return false;
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress(ip, HTTP_PORT), CONNECT_MS);
            return true;
        } catch (Throwable t) {
            return false;
        } finally {
            closeQuiet(s);
        }
    }

    /**
     * 车机所有在用网卡的 IPv4，格式 eth0:192.168.1.5。
     * 刻意不过滤 isVirtual()：车机的 wan1 常是网桥/VLAN，硬过滤会把记录仪整段漏掉。
     */
    private static java.util.List<String> localIps() {
        java.util.List<String> out = new java.util.ArrayList<String>();
        try {
            java.util.Enumeration<java.net.NetworkInterface> nis =
                    java.net.NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                java.net.NetworkInterface ni = nis.nextElement();
                if (!ni.isUp() || ni.isLoopback()) continue;
                java.util.Enumeration<java.net.InetAddress> addrs = ni.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    java.net.InetAddress a = addrs.nextElement();
                    if (a instanceof java.net.Inet4Address && !a.isLoopbackAddress()) {
                        out.add(ni.getName() + ":" + a.getHostAddress());
                    }
                }
            }
        } catch (Throwable t) {
            note("localIps: " + root(t));
        }
        return out;
    }

    // ------------------------------------------------------------------ 列表

    /** "normal" → 普通录像目录，其它 → 事件录像目录 */
    static String dir(String type) {
        return "normal".equals(type) ? "/SD0/NOR/M_video" : "/SD0/EVT/M_video";
    }

    /** 浏览记录仪某个目录，返回文件夹 + 文件（已按时间倒序，最新在前） */
    static String browse(String path) {
        if (offline) return "{\"ok\":0,\"off\":1,\"err\":\"未连接\"}";
        String ip = ensureIp();
        if (ip.length() == 0) return "{\"ok\":0,\"err\":\"没找到记录仪\"}";
        String dir = normalizeDir(path);
        if (dir == null) return "{\"ok\":0,\"err\":\"路径不合法\"}";

        String key = ip + "|" + dir;
        long now = System.currentTimeMillis();
        // 新鲜缓存直接用：同一个目录 45 秒内不再打记录仪，页面展开/收起也走这里
        String hit = cacheGet(key, now, CACHE_FRESH_MS);
        if (hit != null) { note("browse " + dir + " 缓存命中"); return hit; }

        String[] page;
        synchronized (FS_LOCK) {
            /*
             * 等锁期间别的线程可能已经抓到了，所以这里要复核缓存。
             * 关键是时间戳必须取「进锁之后」的：以前沿用进锁前的 now，
             * 等锁的那一两秒里别的线程写进来的新缓存，时间比 now 还晚，
             * 算出来是负数、判成过期，于是每个并发请求都要再打一次设备 ——
             * 十来路并发就变成「一秒抓一次、连抓一分钟」，记录仪直接被打软。
             */
            long t2 = System.currentTimeMillis();
            hit = cacheGet(key, t2, CACHE_FRESH_MS);
            if (hit != null) {
                note("browse " + dir + " 合并命中");
                return hit;
            }
            page = httpFetch(ip, dir, FS_MAX);
            now = t2;
        }

        if (page[0].length() > 0) {
            String json = parseList(page[0], dir, ip, page[1]);
            synchronized (FS_LOCK) {
                DIR_CACHE.put(key, json);
                DIR_CACHE_STAT.put(key, page[1]);
                DIR_CACHE_T.put(key, Long.valueOf(now));
            }
            return json;
        }

        // 抓空了：5 分钟内拿上次成功的列表顶上，页面标个「旧」，总比白屏强
        String stale = cacheGet(key, now, CACHE_STALE_MS);
        if (stale != null) {
            note("browse " + dir + " 抓空，用旧缓存兜底");
            return stale.replace("\"ok\":1", "\"ok\":1,\"stale\":1");
        }
        note("browse " + dir + " 失败，无缓存可用");
        String hint = page[1].indexOf("没有任何应答") >= 0
                ? "记录仪没应答（多半在休眠）：再点一次「连接」，或先打开原厂记录仪 App 再关掉"
                : "记录仪网页没有应答";
        return "{\"ok\":0,\"src\":\"fs\",\"path\":" + q(dir)
                + ",\"stat\":" + q(page[1])
                + ",\"err\":" + q(hint) + "}";
    }

    /** 取缓存；expire 内的原样返回，超出返回 null */
    private static String cacheGet(String key, long now, long expire) {
        synchronized (FS_LOCK) {
            Long t = DIR_CACHE_T.get(key);
            if (t == null) return null;
            String v = DIR_CACHE.get(key);
            if (v == null || now - t.longValue() > expire) return null;
            return v;
        }
    }

    /** 把记录仪目录页解析成前端 JSON（browse 抽出来的纯解析，不再发请求） */
    private static String parseList(String body, String dir, String ip, String stat) {
        java.util.List<String[]> dirs = new java.util.ArrayList<String[]>();
        java.util.List<String[]> files = new java.util.ArrayList<String[]>();
        scanLinks(body, dir, dirs, files);
        // 页面不是普通 href 列表（JS 拼的、JSON 里带文件名）时，直接从正文认文件名
        if (files.isEmpty()) scanNames(body, dir, files);

        java.util.Collections.sort(files, new java.util.Comparator<String[]>() {
            public int compare(String[] a, String[] b) {
                int c = b[2].compareTo(a[2]);        // 时间倒序，空的自然沉底
                if (c != 0) return c;
                return b[0].compareTo(a[0]);
            }
        });

        StringBuilder fd = new StringBuilder("[");
        for (int i = 0; i < dirs.size(); i++) {
            if (i > 0) fd.append(',');
            fd.append("{\"n\":").append(q(dirs.get(i)[0]))
                    .append(",\"p\":").append(q(dirs.get(i)[1])).append('}');
        }
        fd.append(']');
        StringBuilder ff = new StringBuilder("[");
        for (int i = 0; i < files.size(); i++) {
            if (i > 0) ff.append(',');
            ff.append("{\"f\":").append(q(files.get(i)[0]))
                    .append(",\"s\":").append(q(files.get(i)[1]))
                    .append(",\"t\":").append(q(files.get(i)[2])).append('}');
        }
        ff.append(']');

        return "{\"ok\":1,\"src\":\"fs\",\"ip\":" + q(ip)
                + ",\"path\":" + q(dir)
                + ",\"parent\":" + q(parentOf(dir))
                + ",\"dirs\":" + fd
                + ",\"list\":" + ff
                + ",\"n\":" + files.size()
                + ",\"stat\":" + q(stat) + "}";
    }

    private static String ensureIp() {
        if (cachedIp.length() > 0) return cachedIp;
        return probeIp();
    }

    private static String probeIp() {
        String json = probe();
        int i = json.indexOf("\"ip\":\"");
        if (i < 0) return "";
        int s = i + 6;
        int e = json.indexOf('"', s);
        return e > s ? json.substring(s, e) : "";
    }

    /** 扫 HTML 里的 href，分成子目录和视频文件 */
    private static void scanLinks(String body, String base,
                                  java.util.List<String[]> dirs, java.util.List<String[]> files) {
        java.util.Set<String> seenD = new java.util.LinkedHashSet<String>();
        java.util.Set<String> seenF = new java.util.LinkedHashSet<String>();
        String low = body.toLowerCase();
        int i = 0;
        while (i < low.length()) {
            int h = low.indexOf("href", i);
            if (h < 0 || h + 4 > body.length()) break;
            if (h > 0) {
                char prev = low.charAt(h - 1);
                if (Character.isLetterOrDigit(prev) || prev == '-' || prev == '_') {
                    i = h + 4;
                    continue;
                }
            }
            int j = h + 4;
            while (j < body.length() && isSpace(body.charAt(j))) j++;
            if (j >= body.length() || body.charAt(j) != '=') { i = h + 4; continue; }
            j++;
            while (j < body.length() && isSpace(body.charAt(j))) j++;

            String val;
            if (j < body.length() && (body.charAt(j) == '"' || body.charAt(j) == '\'')) {
                char qc = body.charAt(j);
                int e = body.indexOf(qc, j + 1);
                if (e <= j) { i = h + 4; continue; }
                val = body.substring(j + 1, e);
                i = e + 1;
            } else {
                int e = j;
                while (e < body.length() && !isSpace(body.charAt(e)) && body.charAt(e) != '>') e++;
                val = body.substring(j, Math.min(e, body.length()));
                i = Math.max(e, h + 4);
            }

            String ctx = body.substring(i, Math.min(body.length(), i + 260));
            String v = pctDecode(val).trim();
            if (v.length() == 0 || ".".equals(v) || "..".equals(v)) continue;
            String lv = v.toLowerCase();
            if (lv.startsWith("#") || lv.startsWith("?") || lv.startsWith("javascript:")
                    || lv.startsWith("mailto:") || lv.startsWith("data:")) continue;
            // 绝对地址（http://192.168.196.16/SD0/...）只留路径，别整条丢掉
            String abs = stripHost(v);
            if (abs != null) v = abs;
            while (v.startsWith("./")) v = v.substring(2);
            int qm = v.indexOf('?');
            if (qm > 0) v = v.substring(0, qm);
            int hs = v.indexOf('#');
            if (hs > 0) v = v.substring(0, hs);
            if (v.length() == 0) continue;

            String full = v.charAt(0) == '/' ? v : base + v;
            String name = lastSeg(full);
            if (name.length() == 0 || name.length() > 120) continue;
            if (full.equals(base) || (full + "/").equals(base)) continue;   // 自己这一级

            if (v.endsWith("/") || name.indexOf('.') < 0) {
                if (!full.endsWith("/")) full = full + "/";
                if (seenD.add(full)) dirs.add(new String[] { name, full });
            } else {
                if (!isVideo(name) || !seenF.add(name)) continue;
                String size = sizeIn(ctx);
                String time = timeFromName(name);
                if (time.length() == 0) time = timeIn(ctx);
                files.add(new String[] { name, size, time });
            }
        }
    }

    /** 兜底：正文里没有可用的 href 时，直接按扩展名认录像文件名 */
    private static void scanNames(String body, String base, java.util.List<String[]> files) {
        java.util.Set<String> seen = new java.util.LinkedHashSet<String>();
        try {
            java.util.regex.Matcher m = java.util.regex.Pattern
                    .compile("[A-Za-z0-9][A-Za-z0-9_.\\-]{3,}\\.(?:mp4|avi|mov|mkv|ts|3gp|m4v|flv|mpg)",
                            java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(body);
            while (m.find() && files.size() < 5000) {
                String name = m.group();
                if (!isVideo(name) || !seen.add(name)) continue;
                files.add(new String[] { name, "", timeFromName(name) });
            }
        } catch (Throwable ignored) {
        }
    }

    // ------------------------------------------------------- 原厂页代理（HTML 改写）

    /**
     * 把记录仪自己的目录页整页转出来，链接改写成走 18767：
     * 子目录 → /dvr/fs?p=…，录像 → /dvr/file?p=…&dl=1。
     * 解析不出列表时页面直接 iframe 这个地址，照样能翻能下。
     */
    static String page(String path) {
        String tip = "<!doctype html><meta charset=\"utf-8\">"
                + "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">"
                + "<body style=\"background:#0b0b0d;color:#9a9aa0;font:14px/1.7 sans-serif;padding:22px\">";
        if (offline) return tip + "已断开</body>";
        String ip = ensureIp();
        String dir = normalizeDir(path);
        if (ip.length() == 0 || dir == null) return tip + "没有记录仪地址</body>";
        String[] r = httpFetch(ip, dir, FS_MAX);
        if (r[0].length() == 0) return tip + "记录仪没有应答（" + esc(r[1]) + "）</body>";
        return rewriteLinks(r[0], dir);
    }

    /** 逐条改写 href；其它内容原样保留 */
    private static String rewriteLinks(String html, String base) {
        StringBuilder out = new StringBuilder(html.length() + 256);
        String low = html.toLowerCase();
        int i = 0;
        while (i < html.length()) {
            int h = low.indexOf("href", i);
            if (h < 0 || h + 4 > html.length()) break;
            if (h > 0) {
                char prev = low.charAt(h - 1);
                if (Character.isLetterOrDigit(prev) || prev == '-' || prev == '_') {
                    out.append(html, i, h + 4);
                    i = h + 4;
                    continue;
                }
            }
            out.append(html, i, h);
            int j = h + 4;
            while (j < html.length() && isSpace(html.charAt(j))) j++;
            if (j >= html.length() || html.charAt(j) != '=') {
                out.append(html, h, j);
                i = j;
                continue;
            }
            j++;
            while (j < html.length() && isSpace(html.charAt(j))) j++;
            out.append(html, h, j);
            if (j < html.length() && (html.charAt(j) == '"' || html.charAt(j) == '\'')) {
                char qc = html.charAt(j);
                int e = html.indexOf(qc, j + 1);
                if (e <= j) { i = j; continue; }
                out.append(qc).append(targetOf(html.substring(j + 1, e), base)).append(qc);
                i = e + 1;
            } else {
                int e = j;
                while (e < html.length() && !isSpace(html.charAt(e)) && html.charAt(e) != '>') e++;
                out.append(targetOf(html.substring(j, e), base));
                i = e;
            }
        }
        if (i < html.length()) out.append(html, i, html.length());
        return injectStyle(out.toString());
    }

    /** 相对 / 绝对链接 → 走 18767 的地址 */
    private static String targetOf(String val, String base) {
        String v = (val == null ? "" : val).trim();
        String lv = v.toLowerCase();
        if (v.length() == 0 || lv.startsWith("#") || lv.startsWith("?")
                || lv.startsWith("javascript:") || lv.startsWith("mailto:")
                || lv.startsWith("data:")) {
            return val;
        }
        String abs = stripHost(v);
        if (abs != null) v = abs;
        int qm = v.indexOf('?');
        if (qm > 0) v = v.substring(0, qm);
        int hs = v.indexOf('#');
        if (hs > 0) v = v.substring(0, hs);
        String full = resolve(base, pctDecode(v));
        if (full == null) return val;
        String name = lastSeg(full);
        boolean isDir = v.endsWith("/") || name.indexOf('.') < 0;
        if (isDir) {
            if (!full.endsWith("/")) full = full + "/";
            return "/dvr/fs?p=" + pctEncodePath(full);
        }
        if (isVideo(name)) return "/dvr/file?p=" + pctEncodePath(full) + "&dl=1";
        return "/dvr/file?p=" + pctEncodePath(full);
    }

    /** 相对路径 → 绝对路径 */
    private static String resolve(String base, String rel) {
        String r = (rel == null ? "" : rel.trim());
        if (r.length() == 0) return base;
        if (r.indexOf('\\') >= 0) return null;
        String all = r.charAt(0) == '/' ? r : base + r;
        String[] seg = all.split("/");
        java.util.ArrayList<String> keep = new java.util.ArrayList<String>();
        for (int i = 0; i < seg.length; i++) {
            String s = seg[i];
            if (s.length() == 0 || ".".equals(s)) continue;
            if ("..".equals(s)) {
                if (keep.size() > 0) keep.remove(keep.size() - 1);
                continue;
            }
            keep.add(s);
        }
        StringBuilder b = new StringBuilder("/");
        for (int i = 0; i < keep.size(); i++) {
            if (i > 0) b.append('/');
            b.append(keep.get(i));
        }
        return b.toString();
    }

    /** 原厂页套一层深色 + 移动 viewport，别让它一片惨白 */
    private static String injectStyle(String html) {
        String style = "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">"
                + "<style>html,body{background:#0b0b0d !important;color:#ddd !important}"
                + "a{color:#79b8ff !important}pre,table,td,th{color:#ddd !important;font-size:13px}"
                + "body{font:14px/1.6 -apple-system,'PingFang SC',sans-serif;padding:10px}"
                + "img{max-width:100%}</style>";
        String low = html.toLowerCase();
        int head = low.indexOf("<head");
        int body = low.indexOf("<body");
        int at = -1;
        if (head >= 0) at = html.indexOf('>', head);
        else if (body >= 0) at = html.indexOf('>', body);
        if (at > 0) return html.substring(0, at + 1) + style + html.substring(at + 1);
        return style + html;
    }

    // ------------------------------------------------------------------ 抓页
    /**
     * 抓记录仪网页（80），返回 {body, 状态行}。
     *
     * 这台设备有个明显的脾气：TCP 能连上，但某种请求形态它就是不吐字节，一直挂到超时。
     * 实测（11-24/11-25 两份现场日志）：形态 0 一次都没回过，形态 1 / 2 才有。
     *
     * 以前的顺序是「0 → 2 → 1」，每个形态挂满 9 秒，于是打开一次要 27 秒 —— 这就是
     * 「太难连接」。现在：
     * 1. 有记忆形态就只用它，首字节 6 秒、失败再补 4.5 秒（弱设备常被上一个请求拖着）；
     * 2. 没记忆才依次试 1 → 2 → 0，试探形态只给 3 秒；
     * 3. 成功一次按 IP 记住并落盘，断线重连、重启车机都不用再试错。
     */
    private static String[] httpFetch(String ip, String path, int max) {
        String key = ip + "|" + path;
        long now = System.currentTimeMillis();
        if (key.equals(coolKey) && now < coolUntil) {
            long left = (coolUntil - now + 999) / 1000;
            note("fs " + path + " 冷却中（" + left + "s）");
            return new String[] { "", "记录仪不应答，冷却 " + left + " 秒" };
        }

        String stat = "";
        int known = modeFor(ip);
        if (known >= 0) {
            String[] r = httpOnce(ip, path, max, known, HEAD_MS);
            if (r[0].length() > 0) return r;
            stat = r[1];
            // 连上了却一个字节不回：弱设备常被上一个请求拖着，同一形态再给一次
            if (stat.indexOf("没有任何应答") >= 0) {
                r = httpOnce(ip, path, max, known, HEAD_RETRY_MS);
                if (r[0].length() > 0) return r;
                stat = r[1];
            }
            // 记忆的形态万一不对（换过固件/换过设备），再试一个别的，别死等
            int alt = known == MODE_10 ? MODE_KEEP : MODE_10;
            r = httpOnce(ip, path, max, alt, HEAD_PROBE_MS);
            if (r[0].length() > 0) {
                rememberMode(ip, alt);
                note("形态修正 " + ip + " -> " + alt);
                return r;
            }
        } else {
            int[] order = new int[] { MODE_10, MODE_KEEP, MODE_CLOSE };
            for (int i = 0; i < order.length; i++) {
                String[] r = httpOnce(ip, path, max, order[i], i == 0 ? HEAD_MS : HEAD_PROBE_MS);
                if (r[0].length() > 0) {
                    rememberMode(ip, order[i]);
                    note("记住形态 " + ip + " -> " + order[i]);
                    return r;
                }
                stat = r[1];
                // 连都连不上，换形态也没用（别白等后面几个超时）
                if (stat.toLowerCase().indexOf("connect") >= 0) break;
            }
        }

        baseline(ip);
        coolKey = key;
        coolUntil = System.currentTimeMillis() + FAIL_COOL_MS;
        return new String[] { "", stat.length() > 0 ? stat : "ERR 记录仪没有应答" };
    }

    private static String[] httpOnce(String ip, String path, int max, int mode, int headMs) {
        Socket s = new Socket();
        try {
            long t0 = System.currentTimeMillis();
            s.connect(new InetSocketAddress(ip, HTTP_PORT), HTTP_CONNECT_MS);
            long tConn = System.currentTimeMillis() - t0;
            s.setSoTimeout(headMs);
            OutputStream os = s.getOutputStream();

            /*
             * 只发浏览器必备的几个头。以前还带 Accept-Language / Accept-Encoding / Referer，
             * 这些对正常设备无害，但对这种极简 httpd 反而可能把它的请求解析带偏 —— 越少越稳。
             */
            StringBuilder req = new StringBuilder(160);
            req.append("GET ").append(pctEncodePath(path))
                    .append(mode == MODE_10 ? " HTTP/1.0\r\n" : " HTTP/1.1\r\n");
            if (mode != MODE_10) req.append("Host: ").append(ip).append("\r\n");
            req.append("User-Agent: Mozilla/5.0 (Linux; Android 9; Car) AppleWebKit/537.36")
                    .append(" (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36\r\n")
                    .append("Accept: */*\r\n");
            if (mode == MODE_CLOSE) req.append("Connection: close\r\n");
            req.append("\r\n");
            os.write(req.toString().getBytes("ISO-8859-1"));
            os.flush();

            InputStream in = s.getInputStream();
            /*
             * 关键：把「响应头」和「正文」分开读。
             * 老代码 while(read>0) 一直读到服务器关连接才返回；记录仪发完 HTML 常常不关，
             * 于是一头扎进 9 秒 soTimeout，浏览器按 Content-Length 读够就走所以顺畅。
             * 这里拿到 Content-Length 就只读那么多字节，读满立刻返回，不再等 FIN。
             */
            long tTx = System.currentTimeMillis();
            String first;
            try {
                first = ServerIo.readLine(in);
            } catch (java.net.SocketTimeoutException te) {
                // 状态行就超时：这个形态不对，或者设备没醒 —— 交给上层换形态/重试
                long waited = System.currentTimeMillis() - tTx;
                note("fs " + path + " 形态" + mode + " 失败：连上 " + tConn + "ms，等 "
                        + waited + "ms 没有状态行");
                return new String[] { "", "ERR 连上但没有任何应答（等 " + waited + "ms）" };
            }
            long tFirst = System.currentTimeMillis() - tTx;
            if (first == null || first.length() == 0) {
                note("fs " + path + " 形态" + mode + " 失败：连上 " + tConn + "ms，等 "
                        + tFirst + "ms 一个字节都没回");
                return new String[] { "", "ERR 连上但没有任何应答（等 " + tFirst + "ms）" };
            }
            // 状态行到了，说明设备在应答：正文按正常预算读，别被首字节的短预算拖累
            s.setSoTimeout(FS_READ_MS);

            StringBuilder headSb = new StringBuilder(1024);
            headSb.append(first).append("\r\n");
            String line;
            int clen = -1;
            boolean chunked = false;
            int colon0 = first.indexOf(':');
            if (colon0 > 0) {
                String k0 = first.substring(0, colon0).trim().toLowerCase();
                if ("content-length".equals(k0)) {
                    try { clen = Integer.parseInt(first.substring(colon0 + 1).trim()); }
                    catch (Throwable ignored) {}
                }
            }
            while ((line = ServerIo.readLine(in)) != null) {
                if (line.length() == 0) break;              // 空行 = 头结束
                headSb.append(line).append("\r\n");
                int colon = line.indexOf(':');
                if (colon <= 0) continue;
                String k = line.substring(0, colon).trim().toLowerCase();
                String v = line.substring(colon + 1).trim();
                if ("content-length".equals(k)) {
                    try { clen = Integer.parseInt(v); } catch (Throwable ignored) {}
                } else if ("transfer-encoding".equals(k) && v.toLowerCase().indexOf("chunked") >= 0) {
                    chunked = true;
                }
            }
            headSb.append("\r\n");

            ByteArrayOutputStream all = new ByteArrayOutputStream(
                    headSb.length() + (clen > 0 ? Math.min(clen, 16384) : 16384));
            all.write(headSb.toString().getBytes("ISO-8859-1"));
            byte[] buf = new byte[8192];
            if (clen > 0) {
                // 有长度：读满即走。上限护一下，别让异常 Content-Length 把内存吃爆。
                int remain = Math.min(clen, max);
                while (remain > 0) {
                    int n;
                    try {
                        n = in.read(buf, 0, Math.min(buf.length, remain));
                    } catch (java.net.SocketTimeoutException te) {
                        break;                              // 有部分正文就先用，别整段丢
                    }
                    if (n < 0) break;
                    all.write(buf, 0, n);
                    remain -= n;
                }
            } else if (!chunked) {
                // 没有长度也没有 chunked：读到 </html> 就停，没等到才靠关连接/超时
                while (all.size() < max) {
                    int n;
                    try {
                        n = in.read(buf);
                    } catch (java.net.SocketTimeoutException te) {
                        break;
                    }
                    if (n <= 0) break;
                    all.write(buf, 0, n);
                    if (endsHtml(all)) break;
                }
            } else {
                // chunked：读到结束块 0\r\n\r\n，避免干等 FIN
                while (all.size() < max) {
                    int n;
                    try {
                        n = in.read(buf);
                    } catch (java.net.SocketTimeoutException te) {
                        break;
                    }
                    if (n <= 0) break;
                    all.write(buf, 0, n);
                    if (endsChunk(all)) break;
                }
            }

            byte[] raw = all.toByteArray();
            String status = statusOf(raw);
            String text = decodeHttp(raw);
            note("fs " + path + " [" + status + "] 形态" + mode + " 连" + tConn
                    + "ms 首字节" + tFirst + "ms len=" + clen + " -> " + text.length());
            return new String[] { text, status };
        } catch (Throwable t) {
            note("fs " + path + " 形态" + mode + " err: " + root(t));
            return new String[] { "", "ERR " + root(t) };
        } finally {
            closeQuiet(s);
        }
    }

    /**
     * 基线：整轮都没应答之后，拿根路径按最简请求打一次（4 秒）。
     * 只为在日志里分清两种情况：根路径也不回 → 这台设备的 80 服务不认我们；
     * 根路径回了 → 是目录路径或请求形态的问题，不是"连不上"。
     */
    private static void baseline(String ip) {
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress(ip, HTTP_PORT), HTTP_CONNECT_MS);
            s.setSoTimeout(4000);
            OutputStream os = s.getOutputStream();
            os.write(("GET / HTTP/1.1\r\nHost: " + ip + "\r\nAccept: */*\r\n\r\n")
                    .getBytes("ISO-8859-1"));
            os.flush();
            InputStream in = s.getInputStream();
            long t0 = System.currentTimeMillis();
            int c = in.read();
            long ms = System.currentTimeMillis() - t0;
            if (c < 0) {
                note("基线 / 对端直接关闭（0 字节，连上后立刻 FIN）");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append((char) c);
                byte[] b = new byte[256];
                try {
                    int n = in.read(b);
                    if (n > 0) sb.append(new String(b, 0, n, "ISO-8859-1"));
                } catch (Throwable ignored) {}
                String head = sb.toString().replace("\r", " ").replace("\n", " ");
                if (head.length() > 90) head = head.substring(0, 90);
                note("基线 / 首字节 " + ms + "ms：" + head);
            }
        } catch (Throwable t) {
            note("基线 / 失败 " + root(t));
        } finally {
            closeQuiet(s);
        }
    }

    /**
     * 目录页末尾是不是已经收完了。
     * 这台设备的应答没有 Content-Length，也不一定主动关连接，只能等到超时；
     * 认出 </html> 就立刻收工，一次目录抓取从「等满超时」变成「传完就走」。
     */
    private static boolean endsHtml(ByteArrayOutputStream b) {
        byte[] a = b.toByteArray();
        int n = a.length;
        if (n < 8) return false;
        int from = Math.max(0, n - 16);
        for (int i = n - 7; i >= from; i--) {
            if (a[i] == '<' && a[i + 1] == '/' && (a[i + 2] == 'h' || a[i + 2] == 'H')
                    && (a[i + 3] == 't' || a[i + 3] == 'T') && (a[i + 4] == 'm' || a[i + 4] == 'M')
                    && (a[i + 5] == 'l' || a[i + 5] == 'L') && a[i + 6] == '>') return true;
        }
        return false;
    }

    /** chunked 正文的结束块：末尾出现 0\r\n\r\n（或 0\r\n 后跟尾字段）。 */    private static boolean endsChunk(ByteArrayOutputStream b) {
        byte[] a = b.toByteArray();
        int n = a.length;
        for (int i = n - 5; i >= 0 && i >= n - 32; i--) {
            if (a[i] == '0' && a[i + 1] == '\r' && a[i + 2] == '\n'
                    && (i + 4 >= n || (a[i + 3] == '\r' && a[i + 4] == '\n'))) return true;
        }
        return false;
    }

    private static String statusOf(byte[] all) {
        if (all == null || all.length < 5) return "";
        int e = 0;
        while (e < all.length && all[e] != '\r' && all[e] != '\n' && e < 100) e++;
        String line = new String(all, 0, e, java.nio.charset.Charset.forName("ISO-8859-1")).trim();
        return line.toLowerCase().startsWith("http/") ? line : "";
    }

    /** 剥响应头（含 chunked / gzip），再按 UTF-8、GBK 依次解 */
    private static String decodeHttp(byte[] all) {
        if (all == null || all.length == 0) return "";
        int sep = -1;
        for (int i = 0; i + 3 < all.length; i++) {
            if (all[i] == '\r' && all[i + 1] == '\n' && all[i + 2] == '\r' && all[i + 3] == '\n') {
                sep = i;
                break;
            }
        }
        if (sep < 0) return decodeText(all, all.length);
        String head = new String(all, 0, sep, java.nio.charset.Charset.forName("ISO-8859-1"))
                .toLowerCase();
        byte[] body = new byte[all.length - sep - 4];
        System.arraycopy(all, sep + 4, body, 0, body.length);
        if (head.indexOf("transfer-encoding: chunked") >= 0) body = dechunk(body);
        boolean gz = head.indexOf("content-encoding: gzip") >= 0;
        boolean def = head.indexOf("content-encoding: deflate") >= 0;
        if (!gz && !def && body.length > 2 && (body[0] & 0xff) == 0x1f && (body[1] & 0xff) == 0x8b) {
            gz = true;
        }
        if (gz || def) body = inflate(body, gz);
        return decodeText(body, body.length);
    }

    private static byte[] inflate(byte[] b, boolean gz) {
        try {
            java.io.ByteArrayInputStream bin = new java.io.ByteArrayInputStream(b);
            InputStream in = gz ? new java.util.zip.GZIPInputStream(bin)
                    : new java.util.zip.InflaterInputStream(bin);
            ByteArrayOutputStream out = new ByteArrayOutputStream(b.length * 3);
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
            in.close();
            return out.toByteArray();
        } catch (Throwable t) {
            return b;
        }
    }

    private static String decodeText(byte[] b, int len) {
        String s = new String(b, 0, len, UTF8);
        if (s.indexOf('\uFFFD') >= 0) {
            try {
                return new String(b, 0, len, "GBK");
            } catch (Throwable ignored) {
            }
        }
        return s;
    }

    private static byte[] dechunk(byte[] b) {
        ByteArrayOutputStream out = new ByteArrayOutputStream(b.length);
        int i = 0;
        while (i < b.length) {
            int e = i;
            while (e < b.length && b[e] != '\r' && b[e] != '\n') e++;
            String hex = new String(b, i, e - i, java.nio.charset.Charset.forName("ISO-8859-1")).trim();
            int semi = hex.indexOf(';');
            if (semi > 0) hex = hex.substring(0, semi);
            int size;
            try {
                size = Integer.parseInt(hex, 16);
            } catch (Throwable t) {
                break;
            }
            if (size <= 0) break;
            i = e;
            while (i < b.length && (b[i] == '\r' || b[i] == '\n')) i++;
            int take = Math.min(size, b.length - i);
            out.write(b, i, take);
            i += take;
            while (i < b.length && (b[i] == '\r' || b[i] == '\n')) i++;
        }
        return out.toByteArray();
    }

    // ------------------------------------------------------------------ 解析

    private static boolean isVideo(String name) {
        String n = name.toLowerCase();
        String[] ext = { ".mp4", ".avi", ".mov", ".mkv", ".ts", ".3gp", ".flv", ".m4v", ".mpg" };
        for (int i = 0; i < ext.length; i++) {
            if (n.endsWith(ext[i])) return true;
        }
        return false;
    }

    /** 大小：优先链接旁的 "12.3 MB" / "30M"，其次表格里的纯数字字节 */
    private static String sizeIn(String ctx) {
        try {
            java.util.regex.Matcher m = java.util.regex.Pattern
                    .compile("(\\d+(?:\\.\\d+)?)\\s*([KMGkmg])(?:i?[Bb])?(?![A-Za-z])").matcher(ctx);
            if (m.find()) {
                double v = Double.parseDouble(m.group(1));
                char u = Character.toUpperCase(m.group(2).charAt(0));
                double mul = u == 'K' ? 1024d : (u == 'M' ? 1048576d : 1073741824d);
                return String.valueOf((long) (v * mul));
            }
            m = java.util.regex.Pattern.compile(">\\s*(\\d{6,})\\s*<").matcher(ctx);
            if (m.find()) return m.group(1);
        } catch (Throwable ignored) {
        }
        return "";
    }

    /** 时间：从链接附近找 2026-11-17 13:10:02 */
    private static String timeIn(String ctx) {
        try {
            java.util.regex.Matcher m = java.util.regex.Pattern
                    .compile("(20\\d{2})-(\\d{2})-(\\d{2})[ T](\\d{2}):(\\d{2})(?::(\\d{2}))?")
                    .matcher(ctx);
            if (m.find()) {
                return m.group(1) + "-" + m.group(2) + "-" + m.group(3) + " "
                        + m.group(4) + ":" + m.group(5) + ":" + (m.group(6) == null ? "00" : m.group(6));
            }
        } catch (Throwable ignored) {
        }
        return "";
    }

    /** 时间：从文件名里 20261117_131002 抠 */
    private static String timeFromName(String name) {
        try {
            java.util.regex.Matcher m = java.util.regex.Pattern
                    .compile("(20\\d{2})(\\d{2})(\\d{2})[_\\- ]?(\\d{2})(\\d{2})(\\d{2})").matcher(name);
            if (!m.find()) return "";
            int mo = Integer.parseInt(m.group(2));
            int d = Integer.parseInt(m.group(3));
            int hh = Integer.parseInt(m.group(4));
            int mi = Integer.parseInt(m.group(5));
            int ss = Integer.parseInt(m.group(6));
            if (mo < 1 || mo > 12 || d < 1 || d > 31 || hh > 23 || mi > 59 || ss > 59) return "";
            return m.group(1) + "-" + m.group(2) + "-" + m.group(3) + " "
                    + m.group(4) + ":" + m.group(5) + ":" + m.group(6);
        } catch (Throwable ignored) {
            return "";
        }
    }

    /** 目录路径规范化：以 / 开头、不许 .. 和 ? # \，折叠重复 /，末尾补 / */
    static String normalizeDir(String path) {
        if (path == null) return null;
        String p = path.trim();
        if (p.length() == 0) p = "/";
        if (p.indexOf("..") >= 0) return null;
        if (p.indexOf('?') >= 0 || p.indexOf('#') >= 0 || p.indexOf('\\') >= 0) return null;
        if (p.charAt(0) != '/') p = "/" + p;
        StringBuilder b = new StringBuilder(p.length());
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '/' && b.length() > 0 && b.charAt(b.length() - 1) == '/') continue;
            b.append(c);
        }
        p = b.toString();
        if (p.length() > 1 && p.charAt(p.length() - 1) != '/') p = p + "/";
        return p;
    }

    private static String parentOf(String dir) {
        if (dir == null || "/".equals(dir)) return "/";
        String s = dir.substring(0, dir.length() - 1);
        int i = s.lastIndexOf('/');
        return i <= 0 ? "/" : s.substring(0, i + 1);
    }

    private static String lastSeg(String p) {
        String s = p;
        while (s.length() > 1 && s.charAt(s.length() - 1) == '/') s = s.substring(0, s.length() - 1);
        int i = s.lastIndexOf('/');
        return i >= 0 ? s.substring(i + 1) : s;
    }

    /** http://host/a → /a；//host/a → /a；不是绝对地址给 null */
    private static String stripHost(String v) {
        String lv = v.toLowerCase();
        int start;
        if (lv.startsWith("http://")) start = 7;
        else if (lv.startsWith("https://")) start = 8;
        else if (lv.startsWith("//")) start = 2;
        else return null;
        int slash = v.indexOf('/', start);
        return slash < 0 ? "/" : v.substring(slash);
    }

    private static boolean isSpace(char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n';
    }

    // ------------------------------------------------------------------ 工具

    private static String pctEncodePath(String path) {
        StringBuilder b = new StringBuilder(path.length() + 8);
        byte[] bytes;
        try {
            bytes = path.getBytes("UTF-8");
        } catch (Throwable t) {
            return path;
        }
        for (int i = 0; i < bytes.length; i++) {
            int c = bytes[i] & 0xff;
            boolean ok = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                    || (c >= '0' && c <= '9') || c == '/' || c == '_' || c == '-'
                    || c == '.' || c == '~';
            if (ok) b.append((char) c);
            else b.append('%').append(HEX[c >> 4]).append(HEX[c & 15]);
        }
        return b.toString();
    }

    private static String pctDecode(String s) {
        if (s == null || s.indexOf('%') < 0) return s == null ? "" : s;
        ByteArrayOutputStream out = new ByteArrayOutputStream(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '%' && i + 2 < s.length()) {
                int hi = Character.digit(s.charAt(i + 1), 16);
                int lo = Character.digit(s.charAt(i + 2), 16);
                if (hi >= 0 && lo >= 0) {
                    out.write((hi << 4) + lo);
                    i += 2;
                    continue;
                }
            }
            byte[] bs;
            try {
                bs = String.valueOf(c).getBytes("UTF-8");
            } catch (Throwable t) {
                bs = new byte[] { (byte) c };
            }
            out.write(bs, 0, bs.length);
        }
        return decodeText(out.toByteArray(), out.size());
    }

    private static String esc(String s) {
        if (s == null) return "";
        StringBuilder b = new StringBuilder(s.length() + 8);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '<') b.append("&lt;");
            else if (c == '>') b.append("&gt;");
            else if (c == '&') b.append("&amp;");
            else if (c == '"') b.append("&quot;");
            else b.append(c);
        }
        return b.toString();
    }

    private static String q(String s) {
        if (s == null) return "null";
        StringBuilder b = new StringBuilder(s.length() + 2).append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"': b.append("\\\""); break;
                case '\\': b.append("\\\\"); break;
                case '\n': b.append("\\n"); break;
                case '\r': b.append("\\r"); break;
                case '\t': b.append("\\t"); break;
                default:
                    if (c < 0x20) b.append(String.format("\\u%04x", (int) c));
                    else b.append(c);
                    break;
            }
        }
        return b.append('"').toString();
    }

    /** 导出 DVR 日志：时间顺序，一行一条。 */
    static synchronized String dumpLogs() {
        StringBuilder sb = new StringBuilder();
        sb.append("# 行车记录仪日志  time  event\n");
        if (logCount == 0) {
            sb.append("(还没有记录。点「连接」或浏览目录后会有。)\n");
            return sb.toString();
        }
        sb.append("已记录 ").append(logCount).append(" 条\n");
        int start = (logHead - logCount + LOGBUF.length) % LOGBUF.length;
        for (int i = 0; i < logCount; i++) {
            String line = LOGBUF[(start + i) % LOGBUF.length];
            if (line != null) sb.append(line).append('\n');
        }
        return sb.toString();
    }

    private static void note(String s) {
        String line = LOGFMT.format(new java.util.Date()) + "  " + s;
        synchronized (DvrProxy.class) {
            LOGBUF[logHead] = line;
            logHead = (logHead + 1) % LOGBUF.length;
            if (logCount < LOGBUF.length) logCount++;
        }
        Log.i(Cfg.TAG, "DVR " + s);
    }

    private static String root(Throwable t) {
        if (t == null) return "";
        String m = t.getMessage();
        return t.getClass().getSimpleName() + (m == null ? "" : ": " + m);
    }

    private static void closeQuiet(Socket s) {
        if (s != null) {
            try { s.close(); } catch (Throwable ignored) {}
        }
    }
}
