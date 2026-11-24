package com.luna.music.car;

import org.json.JSONObject;

/**
 * 通用常量与 JSON 小工具。
 *
 * <p>与官方网易云相关的常量（包名、MediaBrowser 服务、公开 API、歌单 id、metadata key）
 * 已随桥接实现一起移到 {@code src/parked/java/com/luna/music/car/MusicCfg.java}，
 * 默认不编译、不进 APK。这里只留服务本身要用的：端口、mDNS、错误码。
 */
final class Cfg {
    static final String TAG = "NcmRemote";
    static final int PORT = 18764;
    static final int V = 1;
    static final String NSD_TYPE = "_cloudmusic-car._tcp";
    static final String NSD_NAME = "cloudmusic-car";

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
