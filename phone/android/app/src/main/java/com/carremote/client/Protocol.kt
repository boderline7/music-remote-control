package com.carremote.client

/**
 * 安卓只是 WebView 壳：协议解析、命令收发、UI 全在车机端页面里，
 * 这里只保留「去哪儿找车机」需要的常量。完整协议定义见 proto/protocol.json。
 */
object Protocol {
    const val VERSION = 1

    /** 音乐遥控：第三方播放器自己发的页面 */
    const val PORT_THIRD = 18765

    /** 音乐遥控：车控页（官方网易云模式） */
    const val PORT_BRIDGE = 18764

    /** 车控页：车机助手常驻的 18766，跟音乐模式无关 */
    const val PORT_CAR = 18766

    /** 老代码里的「音乐端口」；两个音乐端口互斥，默认指第三方那条 */
    const val PORT = PORT_THIRD

    const val SERVICE_TYPE = "_cloudmusic-car._tcp"

    /**
     * App 依次探三个端口：先 18766（车机助手常驻，页面里再去探音乐），
     * 不通再退 18765（第三方）。
     * 顺序和页面里的探活一致，从哪个端口进看到的都是同一套顶栏。
     */
    val PORTS = listOf(PORT_CAR, PORT_THIRD, PORT_BRIDGE)

    fun role(port: Int): String = when (port) {
        PORT_CAR -> "车控"
        PORT_BRIDGE -> "音乐·桥接"
        PORT_THIRD -> "音乐·第三方"
        else -> "端口 $port"
    }
}
