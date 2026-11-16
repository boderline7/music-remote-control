package com.carremote.client

/**
 * 安卓只是 WebView 壳：协议解析、命令收发、UI 全在车机端页面里，
 * 这里只保留「去哪儿找车机」需要的常量。完整协议定义见 proto/protocol.json。
 */
object Protocol {
    const val VERSION = 1

    /** 音乐遥控：第三方播放器自己发的页面 */
    const val PORT_THIRD = 18765

    /** 音乐遥控：我们自己桥接包发的页面（官方网易云模式） */
    const val PORT_BRIDGE = 18764

    /** 车控页：车机助手常驻的 18766，跟音乐模式无关 */
    const val PORT_CAR = 18766

    /** 老代码里的「音乐端口」；两个音乐端口互斥，默认指第三方那条 */
    const val PORT = PORT_THIRD

    const val SERVICE_TYPE = "_cloudmusic-car._tcp"

    /**
     * App 两个原生 tab，三个端口都要探：
     * 车控固定 18766，音乐先 18764（自己的桥接）再 18765（第三方）。
     * 18765 不属于我们也不影响 —— 切换由原生 tab 负责。
     */
    val PORTS = listOf(PORT_CAR, PORT_BRIDGE, PORT_THIRD)

    fun role(port: Int): String = when (port) {
        PORT_CAR -> "车控"
        PORT_BRIDGE -> "音乐·桥接"
        PORT_THIRD -> "音乐·第三方"
        else -> "端口 $port"
    }
}
