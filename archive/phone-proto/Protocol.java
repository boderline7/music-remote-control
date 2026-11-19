package carremote.proto;

/**
 * 遥控协议常量 —— 车机端与手机端各自复制一份，内容必须与 proto/protocol.json 一致。
 * 校验：python proto/test_protocol.py
 *
 * 纯常量类，不依赖任何第三方库，也不依赖 Android API，
 * 这样桌面 JVM 上的协议自测与两端 APK 都能直接用同一份。
 */
public final class Protocol {

    private Protocol() {}

    /** 协议版本，改协议必须 +1。 */
    public static final int VERSION = 1;

    /** 默认监听端口。集中在这里，不要在别处硬编码。 */
    public static final int PORT = 18765;

    /** NSD/mDNS 服务类型。 */
    public static final String NSD_SERVICE_TYPE = "_cloudmusic-car._tcp";
    public static final String NSD_SERVICE_NAME_PREFIX = "cloudmusic-car";

    // ---- 消息类型 ----
    public static final String TYPE_PAIR = "pair";
    public static final String TYPE_PAIR_RESULT = "pair_result";
    public static final String TYPE_AUTH = "auth";
    public static final String TYPE_AUTH_RESULT = "auth_result";
    public static final String TYPE_COMMAND = "command";
    public static final String TYPE_ACK = "ack";
    public static final String TYPE_STATE = "state";
    public static final String TYPE_PING = "ping";
    public static final String TYPE_PONG = "pong";
    public static final String TYPE_REVOKED = "revoked";
    public static final String TYPE_ERROR = "error";
    public static final String TYPE_LIBRARY = "library";
    public static final String TYPE_TRACKS = "tracks";
    public static final String TYPE_LYRICS = "lyrics";

    // 资料库动作（异步 ack）
    public static final String ACT_LIBRARY = "library";
    public static final String ACT_GET_TRACKS = "getTracks";
    public static final String ACT_PLAY_TRACKS = "playTracks";
    public static final String ACT_ADD_TO_PLAYLIST = "addToPlaylist";
    public static final String ACT_REFRESH = "refresh";

    // ---- 指令动作 ----
    public static final String ACTION_PLAY = "play";
    public static final String ACTION_PAUSE = "pause";
    public static final String ACTION_NEXT = "next";
    public static final String ACTION_PREV = "prev";
    public static final String ACTION_SEEK = "seek";
    public static final String ACTION_SET_VOLUME = "setVolume";
    public static final String ACTION_SET_PLAY_MODE = "setPlayMode";
    public static final String ACTION_GET_STATE = "getState";
    public static final String ACTION_GET_LYRICS = "getLyrics";

    // ---- 状态推送原因 ----
    public static final String REASON_TICK = "tick";
    public static final String REASON_EVENT = "event";
    public static final String REASON_REPLY = "reply";

    // ---- 撤销原因 ----
    public static final String REVOKE_REMOVED = "removed_by_car";
    public static final String REVOKE_TOKEN_RESET = "token_reset";
    public static final String REVOKE_DEVICE_LIMIT = "device_limit";

    // ---- 错误码 ----
    public static final int ERR_INVALID_MESSAGE = 1000;
    public static final int ERR_UNSUPPORTED_VERSION = 1001;
    public static final int ERR_UNKNOWN_TYPE = 1002;

    public static final int ERR_NOT_AUTHENTICATED = 2000;
    public static final int ERR_PIN_INVALID = 2001;
    public static final int ERR_PIN_EXPIRED = 2002;
    public static final int ERR_PIN_ATTEMPTS_EXCEEDED = 2003;
    public static final int ERR_TOKEN_INVALID = 2004;
    public static final int ERR_DEVICE_REVOKED = 2005;
    public static final int ERR_DEVICE_LIMIT_REACHED = 2006;

    public static final int ERR_UNSUPPORTED_ACTION = 3000;
    public static final int ERR_ACTION_FAILED = 3001;
    public static final int ERR_VALUE_OUT_OF_RANGE = 3002;
    public static final int ERR_NOT_AVAILABLE = 3003;

    public static final int ERR_RATE_LIMITED = 4000;
    public static final int ERR_INTERNAL = 9000;

    // ---- 时间参数（毫秒/秒）----
    public static final long PIN_TTL_SEC = 120L;
    public static final int PIN_MAX_ATTEMPTS = 5;
    public static final long AUTH_TIMEOUT_SEC = 10L;
    public static final long STATE_TICK_MS = 1000L;
    public static final long PING_INTERVAL_SEC = 20L;
    public static final long PING_TIMEOUT_SEC = 60L;
    public static final int MAX_FRAME_BYTES = 8192;

    /** 错误码转文字，用于日志与 ack.message。 */
    public static String errorName(int code) {
        switch (code) {
            case ERR_INVALID_MESSAGE: return "INVALID_MESSAGE";
            case ERR_UNSUPPORTED_VERSION: return "UNSUPPORTED_VERSION";
            case ERR_UNKNOWN_TYPE: return "UNKNOWN_TYPE";
            case ERR_NOT_AUTHENTICATED: return "NOT_AUTHENTICATED";
            case ERR_PIN_INVALID: return "PIN_INVALID";
            case ERR_PIN_EXPIRED: return "PIN_EXPIRED";
            case ERR_PIN_ATTEMPTS_EXCEEDED: return "PIN_ATTEMPTS_EXCEEDED";
            case ERR_TOKEN_INVALID: return "TOKEN_INVALID";
            case ERR_DEVICE_REVOKED: return "DEVICE_REVOKED";
            case ERR_DEVICE_LIMIT_REACHED: return "DEVICE_LIMIT_REACHED";
            case ERR_UNSUPPORTED_ACTION: return "UNSUPPORTED_ACTION";
            case ERR_ACTION_FAILED: return "ACTION_FAILED";
            case ERR_VALUE_OUT_OF_RANGE: return "VALUE_OUT_OF_RANGE";
            case ERR_NOT_AVAILABLE: return "NOT_AVAILABLE";
            case ERR_RATE_LIMITED: return "RATE_LIMITED";
            case ERR_INTERNAL: return "INTERNAL";
            default: return "UNKNOWN";
        }
    }

    /** 该动作是否需要认证（除配对/认证/保活外都要）。 */
    public static boolean isAction(String action) {
        return ACTION_PLAY.equals(action) || ACTION_PAUSE.equals(action)
                || ACTION_NEXT.equals(action) || ACTION_PREV.equals(action)
                || ACTION_SEEK.equals(action) || ACTION_SET_VOLUME.equals(action)
                || ACTION_SET_PLAY_MODE.equals(action) || ACTION_GET_STATE.equals(action);
    }
}
