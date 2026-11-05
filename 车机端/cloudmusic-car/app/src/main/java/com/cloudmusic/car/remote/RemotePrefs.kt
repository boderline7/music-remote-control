package com.cloudmusic.car.remote

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 遥控服务的本机设置（原 PairingStore 的位置）。
 *
 * v2.0 起**取消 PIN 和设备配对**：车机上开启遥控就是「谁连上谁能控」。
 * 理由是这套东西本来就只在自家车内热点里跑 —— 同一个 Wi-Fi 下的人要是想发指令，
 * 一个 6 位 PIN 也挡不住，反倒是每次上车都要去车里看 PIN、输 PIN，摩擦远大于收益。
 *
 * 于是「同时在线设备数上限」成了唯一的闸门：默认 4 台，可调 1~8。
 * 调小到 1 就是「只认一台手机」，多出来的连接会被车机直接关掉。
 */
object RemotePrefs {

    const val MIN_CLIENTS = 1
    const val MAX_CLIENTS = 8
    private const val DEFAULT_CLIENTS = 4

    /**
     * 资料库（歌单 / 搜索 / 榜单）的内存缓存上限，单位是「份」。
     *
     * 一份 = 一个列表（可能上千首）或一个聚合页（library / catalog）。
     * 以前写死 10 份，痛点有两个：手机上翻七八张歌单就会把最早那张挤掉、回头再点要重新拉；
     * 而车机内存本来就紧，用户又没法往下压。所以改成可调：1~30，默认 10。
     *
     * 真正的内存占用还受第二道闸门约束（见 RemoteLibrary 的字节预算），
     * 调大份数不会线性吃内存 —— 单份太大时按字节先回收最久没用的。
     */
    const val MIN_CACHE = 1
    const val MAX_CACHE = 30

    /**
     * 默认 12 份。为什么不是 10：一屏能看到的歌单大概 6 张，手机端还会往上翻一屏多，
     * 12 份正好覆盖「翻两屏再回头点回去」这个最常见的动作，又不至于把车机内存吃满
     * （真正的上限由 RemoteLibrary 的字节闸门兜底，8 MB 封顶）。
     */
    private const val DEFAULT_CACHE = 12

    private const val PREFS = "remote_settings"
    private const val KEY_MAX_CLIENTS = "maxClients"
    private const val KEY_CACHE_LIMIT = "cacheLimit"

    private lateinit var prefs: SharedPreferences

    private val _maxClients = MutableStateFlow(DEFAULT_CLIENTS)
    val maxClients: StateFlow<Int> = _maxClients.asStateFlow()

    private val _cacheLimit = MutableStateFlow(DEFAULT_CACHE)
    val cacheLimit: StateFlow<Int> = _cacheLimit.asStateFlow()

    @Synchronized
    fun init(context: Context) {
        if (::prefs.isInitialized) return
        prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        // 旧版本存过 autoApprove / 设备列表，不管它，读不到就落回默认值
        _maxClients.value = prefs.getInt(KEY_MAX_CLIENTS, DEFAULT_CLIENTS)
            .coerceIn(MIN_CLIENTS, MAX_CLIENTS)
        _cacheLimit.value = prefs.getInt(KEY_CACHE_LIMIT, DEFAULT_CACHE)
            .coerceIn(MIN_CACHE, MAX_CACHE)
    }

    /** 给后台线程用，不走 StateFlow，避免频繁装箱。 */
    val currentMaxClients: Int get() = _maxClients.value

    val currentCacheLimit: Int get() = _cacheLimit.value

    @Synchronized
    fun setMaxClients(value: Int) {
        val v = value.coerceIn(MIN_CLIENTS, MAX_CLIENTS)
        _maxClients.value = v
        if (::prefs.isInitialized) prefs.edit().putInt(KEY_MAX_CLIENTS, v).apply()
    }

    /** 调小立即生效：调用方随后要触发一次缓存回收（RemoteLibrary.trim）。 */
    @Synchronized
    fun setCacheLimit(value: Int) {
        val v = value.coerceIn(MIN_CACHE, MAX_CACHE)
        _cacheLimit.value = v
        if (::prefs.isInitialized) prefs.edit().putInt(KEY_CACHE_LIMIT, v).apply()
    }
}
