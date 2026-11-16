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

    /** 两个都探。车机上开了哪个就列哪个，用户不用先知道一共有几个服务。 */
    val PORTS = listOf(PORT_CAR, PORT)

    fun role(port: Int): String = when (port) {
        PORT_CAR -> "车控"
        PORT -> "音乐"
        else -> "端口 $port"
    }
}
