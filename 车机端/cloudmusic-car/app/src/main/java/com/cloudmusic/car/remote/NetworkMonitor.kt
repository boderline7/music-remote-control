package com.cloudmusic.car.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log

/**
 * 网络变化监听（车机侧）。
 *
 * 为什么要它：热点重建、Wi-Fi 切换、网卡重新拿到地址这几件事，5 秒轮询能兜住，
 * 但最坏要等 5 秒 + mDNS 重建 3 秒才恢复，手机上就是「明明有网却连不上」。
 * 这里只是把「该刷新了」提前，**正确性仍然靠轮询** —— 回调丢了也不会一直错下去。
 *
 * 注意不能用 `registerDefaultNetworkCallback`：车机开热点时那个网络通常没有
 * INTERNET 能力，不是 default network，回调根本不会来。所以用带 transport 的请求。
 */
object NetworkMonitor {

    private var manager: ConnectivityManager? = null
    private var callback: ConnectivityManager.NetworkCallback? = null

    @Volatile
    private var lastNotifyAt = 0L

    fun start(context: Context, onChange: () -> Unit) {
        stop()
        val cm = context.applicationContext.getSystemService(ConnectivityManager::class.java) ?: run {
            RemoteHealth.warn("没有 ConnectivityManager，只能靠轮询")
            return
        }
        val cb = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) = notify("available")

            override fun onLost(network: Network) = notify("lost")

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) =
                notify("link")

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) = notify("caps")

            private fun notify(why: String) {
                val now = System.currentTimeMillis()
                // 热点起来时这几个回调会连着来一串，1 秒内只处理第一次
                if (now - lastNotifyAt < 1_000L) return
                lastNotifyAt = now
                Log.i(RemoteConfig.TAG, "network $why")
                RemoteHealth.log("网络回调：$why")
                runCatching(onChange).onFailure { RemoteHealth.error("网络回调处理失败", it) }
            }
        }
        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        runCatching { cm.registerNetworkCallback(request, cb) }
            .onSuccess {
                manager = cm
                callback = cb
            }
            .onFailure {
                RemoteHealth.error("注册网络回调失败", it)
            }
    }

    fun stop() {
        val cm = manager
        val cb = callback
        manager = null
        callback = null
        if (cm != null && cb != null) runCatching { cm.unregisterNetworkCallback(cb) }
    }
}
