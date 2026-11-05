package com.cloudmusic.car.remote

/** 遥控服务集中配置。端口等不散落硬编码。 */
object RemoteConfig {
    const val PORT = 18765
    const val SERVICE_TYPE = "_cloudmusic-car._tcp"
    const val SERVICE_NAME_PREFIX = "cloudmusic-car"

    /**
     * 网页遥控的固定域名：车机热点网段每次开机都可能变，靠 mDNS 把名字钉死。
     * 手机浏览器打开 http://carremote.local:18765/ 即可，和 IP 无关。
     */
    const val MDNS_HOST = "carremote.local"

    const val PROTOCOL_VERSION = 1

    /**
     * v2.0 起没有 PIN 了：连上 18765 就是已认证设备，能连几台由 [RemotePrefs.maxClients] 决定。
     * 这个超时只是「连上之后一直没说过话」的兜底清理，不再是「等用户输 PIN」的窗口。
     */
    const val AUTH_TIMEOUT_MS = 10_000L
    const val STATE_TICK_MS = 1_000L
    const val MAX_FRAME_BYTES = 8192

    /** 未认证连接只允许这几类消息。v2.0 起连接即认证，这几个类型保留给老客户端做兼容。 */
    val PRE_AUTH_TYPES = setOf("pair", "auth", "ping")

    // 错误码（与 proto/protocol.json 一致）
    const val ERR_INVALID_MESSAGE = 1000
    const val ERR_UNSUPPORTED_VERSION = 1001
    const val ERR_UNKNOWN_TYPE = 1002

    const val ERR_NOT_AUTHENTICATED = 2000
    const val ERR_PIN_INVALID = 2001
    const val ERR_PIN_EXPIRED = 2002
    const val ERR_PIN_ATTEMPTS_EXCEEDED = 2003
    const val ERR_TOKEN_INVALID = 2004
    const val ERR_DEVICE_REVOKED = 2005
    const val ERR_DEVICE_LIMIT_REACHED = 2006

    const val ERR_UNSUPPORTED_ACTION = 3000
    const val ERR_ACTION_FAILED = 3001
    const val ERR_VALUE_OUT_OF_RANGE = 3002
    const val ERR_NOT_AVAILABLE = 3003

    const val ERR_RATE_LIMITED = 4000
    const val ERR_INTERNAL = 9000

    const val TAG = "CloudRemote"
}
