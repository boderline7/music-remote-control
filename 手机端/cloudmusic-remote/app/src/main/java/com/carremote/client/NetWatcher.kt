package com.carremote.client

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log

/**
 * 手机端网络变化监听。
 *
 * 车机端 2.1 已经有 `NetworkMonitor`，手机端一直没这块：断网、切 Wi-Fi、车机热点重启之后，
 * 只能等退避定时器轮到下一次 —— 而退避最长已经涨到 15 秒，用户就盯着「重连中」发呆。
 *
 * 这里只做一件事：系统说「网回来了」就打断等待，立刻重试；说「网断了」就主动断掉，
 * 不用等 OkHttp 自己发现（它可能要几十秒才报错）。
 *
 * 不区分 Wi-Fi / 移动数据：车机热点本来就是一个 Wi-Fi。
 */
object NetWatcher {

    private const val TAG = "NetWatcher"

    private var manager: ConnectivityManager? = null
    private var callback: ConnectivityManager.NetworkCallback? = null

    @Synchronized
    fun start(context: Context) {
        if (callback != null) return
        val cm = context.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager ?: return
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        val cb = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.i(TAG, "network available")
                RemoteClient.onNetworkAvailable()
            }

            override fun onLost(network: Network) {
                Log.i(TAG, "network lost")
                RemoteClient.onNetworkLost()
            }
        }
        runCatching { cm.registerNetworkCallback(request, cb) }
            .onSuccess {
                manager = cm
                callback = cb
            }
            .onFailure { Log.w(TAG, "register failed", it) }
    }

    @Synchronized
    fun stop() {
        val cm = manager ?: return
        val cb = callback ?: return
        runCatching { cm.unregisterNetworkCallback(cb) }
        manager = null
        callback = null
    }
}
