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
     * 3.0 起 App 自己有两个原生 tab：车控走 18766、音乐走 18765，所以两个都要探。
     * 18765 可能不属于我们（第三方网易云自己发的页面），不影响 —— 切换由原生 tab 负责。
     */
    val PORTS = listOf(PORT_CAR, PORT)

    fun role(port: Int): String = when (port) {
        PORT_CAR -> "车控"
        PORT -> "音乐"
        else -> "端口 $port"
    }
}
