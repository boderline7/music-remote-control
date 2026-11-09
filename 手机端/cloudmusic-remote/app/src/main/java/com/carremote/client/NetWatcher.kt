package com.carremote.client

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log

/**
 * 网络变化监听。
 *
 * 2.0.0 起没有原生 WebSocket 客户端了，这里不再直接操作连接对象，
 * 只在「网回来了」时通知壳层：由它决定刷新页面还是重新发现车机。
 * 回调不在主线程，调用方要自己切回 UI 线程。
 */
object NetWatcher {

    private const val TAG = "NetWatcher"

    private var manager: ConnectivityManager? = null
    private var callback: ConnectivityManager.NetworkCallback? = null

    @Volatile
    var onAvailable: (() -> Unit)? = null

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
                onAvailable?.invoke()
            }

            override fun onLost(network: Network) {
                Log.i(TAG, "network lost")
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
