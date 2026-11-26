package com.yuhan.dvrcar;

import org.json.JSONObject;

/**
 * 通用常量与 JSON 小工具。
 *
 * <p>这个包现在只做文件传输（小绿传）和行车记录仪（18767）。原来给音乐桥接留的
 * 18764 端口、mDNS 广播类型随 {@code src/parked} 一起删了，这里只剩日志 TAG、
 * 信封版本号和错误码——后者是 18767 那几个 HTTP 接口出错时回给页面的。
 */
final class Cfg {
    static final String TAG = "DvrCar";
    static final int V = 1;

    static final int ERR_INVALID_MESSAGE = 1000;
    static final int ERR_UNSUPPORTED_VERSION = 1001;
    static final int ERR_UNKNOWN_TYPE = 1002;
    static final int ERR_UNSUPPORTED_ACTION = 3000;
    static final int ERR_ACTION_FAILED = 3001;
    static final int ERR_VALUE_OUT_OF_RANGE = 3002;
    static final int ERR_NOT_AVAILABLE = 3003;
    static final int ERR_INTERNAL = 9000;

    private Cfg() {}
}

/** org.json 在 Java 里是检查异常，统一吞掉，调用处不必到处 throws。 */
final class J {
    static JSONObject o() {
        return new JSONObject();
    }

    static void p(JSONObject o, String k, Object v) {
        try { o.put(k, v); } catch (Throwable ignored) {}
    }

    static String env(String type, String id, JSONObject data) {
        JSONObject e = o();
        p(e, "v", Cfg.V);
        p(e, "type", type);
        p(e, "ts", System.currentTimeMillis());
        p(e, "data", data);
        if (id != null) p(e, "id", id);
        return e.toString();
    }

    static String err(String id, int code, String msg) {
        JSONObject d = o();
        p(d, "error", code);
        p(d, "message", msg);
        return env("error", id, d);
    }

    static String errName(int code) {
        switch (code) {
            case Cfg.ERR_INVALID_MESSAGE: return "INVALID_MESSAGE";
            case Cfg.ERR_UNSUPPORTED_VERSION: return "UNSUPPORTED_VERSION";
            case Cfg.ERR_UNKNOWN_TYPE: return "UNKNOWN_TYPE";
            case Cfg.ERR_UNSUPPORTED_ACTION: return "UNSUPPORTED_ACTION";
            case Cfg.ERR_ACTION_FAILED: return "ACTION_FAILED";
            case Cfg.ERR_VALUE_OUT_OF_RANGE: return "VALUE_OUT_OF_RANGE";
            case Cfg.ERR_NOT_AVAILABLE: return "NOT_AVAILABLE";
            default: return "INTERNAL";
        }
    }

    private J() {}
}
