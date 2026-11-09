package com.carremote.client

/**
 * 2.0.0 起安卓只是 WebView 壳：协议解析、命令收发、UI 全在车机端
 * assets/remote.html 里完成，这里只保留「去哪儿找车机」需要的两个常量。
 * 完整协议定义见 proto/protocol.json。
 */
object Protocol {
    const val VERSION = 1
    const val PORT = 18765
    const val SERVICE_TYPE = "_cloudmusic-car._tcp"
}
