package com.yuhan.dvrcar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 记录仪 7878 控制口（原厂 App 走的就是这条），JSON 报文，一问一答。
 *
 * 协议来自对 `com.skyworthauto.dvrchery` 5.0 的反编译（`a/l/b/a/b/a/p2.java` CmdChannel）：
 *
 *   开会话  {"token":0,"msg_id":257}
 *           ← {"rval":0,"msg_id":257,"param":"<token>"}   token 从 param 里取数字
 *   要列表  {"token":T,"msg_id":268435458,"type":"normal"}   type: normal / event / photo
 *           ← {"rval":0,"msg_id":268435458,"listing":[{"filename":"xxx.mp4","duration":"..","height":".."}]}
 *   设备信息 {"token":T,"msg_id":11}
 *           ← {"rval":0,"msg_id":11,"SD":"..."}            SD 为 "-1" 表示没卡
 *
 * 报文就是裸 JSON 字节，没有分隔符；应答可能是多个对象粘在一起（原厂按 "}{" 拆）。
 * 所以这里逐个扫平衡的 {...}，只认 msg_id 对得上的那一个。
 *
 * 为什么值得走这条：网页那套要现场生成 135KB 的目录页，弱设备一被掐就卡死；
 * 而 7878 只回几十字节 JSON，原厂 App 的列表就是秒出的。
 */
final class DvrCmd {

    private static final int PORT = 7878;
    private static final int CONNECT_MS = 3000;
    private static final int SESSION_MS = 4000;
    private static final int LIST_MS = 6000;
    private static final int INFO_MS = 4000;

    private static final int MSG_START = 257;
    private static final int MSG_LIST = 268435458;
    private static final int MSG_INFO = 11;

    /** 会话复用窗口：原厂是长连接，这里没必要一直挂着，90 秒内复用。 */
    private static final long IDLE_MS = 90_000;

    private static Socket socket;
    private static InputStream in;
    private static OutputStream out;
    private static String sessIp = "";
    private static int token = 0;
    private static long lastUse = 0;

    private DvrCmd() {
    }

    /** 断开：换设备、用户点断开、确认卡死时都要清掉，别拿旧 token 去问新设备 */
    static synchronized void close() {
        token = 0;
        sessIp = "";
        lastUse = 0;
        try {
            if (socket != null) socket.close();
        } catch (Throwable ignored) {
        }
        socket = null;
        in = null;
        out = null;
    }

    static synchronized boolean alive() {
        return socket != null && !socket.isClosed() && token > 0;
    }

    /**
     * 要一个目录的文件名列表；失败（连不上 / rval 非 0 / 超时）返回 null，
     * 调用方据此回落到网页抓取。
     */
    static synchronized String[] list(String ip, String type) {
        try {
            if (!ensure(ip)) return null;
            String body = "{\"token\":" + token + ",\"msg_id\":" + MSG_LIST
                    + ",\"type\":\"" + type + "\"}";
            JSONObject r = request(body, MSG_LIST, LIST_MS);
            if (r == null) return null;
            lastUse = System.currentTimeMillis();
            if (r.optInt("rval", -1) != 0) {
                DvrProxy.note("7878 列表 rval=" + r.optInt("rval", -1));
                return null;
            }
            JSONArray arr = r.optJSONArray("listing");
            if (arr == null) return null;
            int n = arr.length();
            String[] names = new String[n];
            int k = 0;
            for (int i = 0; i < n; i++) {
                JSONObject o = arr.optJSONObject(i);
                if (o == null) continue;
                String name = o.optString("filename", "");
                if (name.length() == 0) continue;
                names[k++] = name;
            }
            if (k == 0) return new String[0];
            String[] outNames = new String[k];
            System.arraycopy(names, 0, outNames, 0, k);
            return outNames;
        } catch (Throwable t) {
            DvrProxy.note("7878 列表异常: " + t);
            close();
            return null;
        }
    }

    /** 设备信息，只用于日志：能读到 SD 状态就知道是不是没卡 */
    static synchronized String info(String ip) {
        try {
            if (!ensure(ip)) return "";
            JSONObject r = request("{\"token\":" + token + ",\"msg_id\":" + MSG_INFO + "}", MSG_INFO, INFO_MS);
            if (r == null) return "";
            lastUse = System.currentTimeMillis();
            return r.optString("SD", "");
        } catch (Throwable t) {
            close();
            return "";
        }
    }

    // ------------------------------------------------------------------ 内部

    private static boolean ensure(String ip) {
        if (socket != null && !socket.isClosed() && ip.equals(sessIp)
                && token > 0 && System.currentTimeMillis() - lastUse < IDLE_MS) {
            return true;
        }
        close();
        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress(ip, PORT), CONNECT_MS);
            s.setSoTimeout(SESSION_MS);
            s.setTcpNoDelay(true);
            socket = s;
            in = s.getInputStream();
            out = s.getOutputStream();
            sessIp = ip;

            JSONObject r = request("{\"token\":0,\"msg_id\":" + MSG_START + "}", MSG_START, SESSION_MS);
            if (r == null || r.optInt("rval", -1) != 0) {
                DvrProxy.note("7878 开会话失败");
                close();
                return false;
            }
            int t = firstInt(r.optString("param", ""));
            if (t <= 0) {
                DvrProxy.note("7878 开会话没拿到 token（param=" + r.optString("param", "") + "）");
                close();
                return false;
            }
            token = t;
            lastUse = System.currentTimeMillis();
            DvrProxy.note("7878 会话建立 token=" + token);
            return true;
        } catch (Throwable e) {
            DvrProxy.note("7878 连不上: " + e);
            close();
            return false;
        }
    }

    private static JSONObject request(String body, int wantId, int timeoutMs) throws Exception {
        out.write(body.getBytes("UTF-8"));
        out.flush();
        return readMsg(wantId, timeoutMs);
    }

    /** 读到 msg_id 匹配的完整 JSON 对象为止 */
    private static JSONObject readMsg(int wantId, int timeoutMs) throws Exception {
        long until = System.currentTimeMillis() + timeoutMs;
        StringBuilder sb = new StringBuilder();
        byte[] buf = new byte[4096];
        socket.setSoTimeout(600);
        try {
            while (System.currentTimeMillis() < until) {
                int n;
                try {
                    n = in.read(buf);
                } catch (java.net.SocketTimeoutException te) {
                    continue;
                }
                if (n <= 0) return null;
                sb.append(new String(buf, 0, n, "UTF-8"));
                JSONObject o = pick(sb.toString(), wantId);
                if (o != null) return o;
                if (sb.length() > 512 * 1024) return null;
            }
        } finally {
            try {
                socket.setSoTimeout(SESSION_MS);
            } catch (Throwable ignored) {
            }
        }
        return null;
    }

    /** 从粘在一起的报文里挑出 msg_id 匹配的那个完整对象 */
    private static JSONObject pick(String buf, int wantId) {
        int i = 0;
        while (i < buf.length()) {
            int s = buf.indexOf('{', i);
            if (s < 0) return null;
            int depth = 0;
            boolean str = false;
            boolean esc = false;
            int e = -1;
            for (int k = s; k < buf.length(); k++) {
                char c = buf.charAt(k);
                if (str) {
                    if (esc) esc = false;
                    else if (c == '\\') esc = true;
                    else if (c == '"') str = false;
                    continue;
                }
                if (c == '"') {
                    str = true;
                } else if (c == '{') {
                    depth++;
                } else if (c == '}') {
                    depth--;
                    if (depth == 0) {
                        e = k;
                        break;
                    }
                }
            }
            if (e < 0) return null;                 // 还没收全，等下一批
            String cand = buf.substring(s, e + 1);
            try {
                JSONObject o = new JSONObject(cand);
                if (o.optInt("msg_id", -1) == wantId) return o;
                i = e + 1;
            } catch (Throwable t) {
                i = s + 1;
            }
        }
        return null;
    }

    private static int firstInt(String s) {
        int v = 0;
        boolean got = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                v = v * 10 + (c - '0');
                got = true;
            } else if (got) {
                break;
            }
        }
        return got ? v : 0;
    }
}
