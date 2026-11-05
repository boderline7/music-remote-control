package com.carremote.client

/** 与 proto/protocol.json 一致的协议常量。改协议必须同步改车机端。 */
object Protocol {
    const val VERSION = 1
    const val PORT = 18765
    const val SERVICE_TYPE = "_cloudmusic-car._tcp"

    const val ACTION_PLAY = "play"
    const val ACTION_PAUSE = "pause"
    const val ACTION_NEXT = "next"
    const val ACTION_PREV = "prev"
    const val ACTION_SEEK = "seek"
    const val ACTION_SET_VOLUME = "setVolume"
    const val ACTION_GET_STATE = "getState"
    const val ACTION_LIKE = "like"

    // 资料库 / 榜单 / 搜索，返回都是 library / catalog / tracks 消息
    const val ACTION_LIBRARY = "library"
    const val ACTION_CATALOG = "catalog"
    const val ACTION_SEARCH = "search"
    const val ACTION_GET_TRACKS = "getTracks"
    const val ACTION_PLAY_TRACKS = "playTracks"

    const val ERR_NOT_AUTHENTICATED = 2000
    const val ERR_PIN_INVALID = 2001
    const val ERR_PIN_EXPIRED = 2002
    const val ERR_PIN_ATTEMPTS_EXCEEDED = 2003
    const val ERR_TOKEN_INVALID = 2004
    const val ERR_DEVICE_REVOKED = 2005
}
