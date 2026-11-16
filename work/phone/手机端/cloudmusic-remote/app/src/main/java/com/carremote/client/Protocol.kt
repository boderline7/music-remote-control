package com.carremote.client

/**
 * 2.0.0 起安卓只是 WebView 壳：协议解析、命令收发、UI 全在车机端
 * assets/remote.html 里完成，这里只保留「去哪儿找车机」需要的常量。
 * 完整协议定义见 proto/protocol.json。
 */
object Protocol {
    const val VERSION = 1

    /** 音乐遥控：车机端桥接包提供的 remote.html */
    const val PORT = 18765

    /** 车控页：车机助手常驻的 18766，跟音乐模式无关 */
    const val PORT_CAR = 18766

    const val SERVICE_TYPE = "_cloudmusic-car._tcp"

    /**
     * 统一入口先探 18766：车控页自带「车机控制 / 音乐控制」两个 tab，18765 由它 iframe 贴进来。
     * 18765 可能根本不是我们的页面（第三方网易云自己提供的），直接进它就再也回不来。
     * 18766 不通时才退回 18765，保证老环境照样能用。
     */
    val PORTS = listOf(PORT_CAR, PORT)

    fun role(port: Int): String = when (port) {
        PORT_CAR -> "车控"
        PORT -> "音乐"
        else -> "端口 $port"
    }
}
