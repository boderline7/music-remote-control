package com.carremote.client

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.Inet4Address

/**
 * 车机自动发现（mDNS）。失败不影响使用：手动输入 IP 一直是可用的兜底路径。
 */
object Discovery {

    data class Found(val name: String, val host: String, val port: Int)

    private const val TAG = "CarRemote"

    private val _found = MutableStateFlow<List<Found>>(emptyList())
    val found: StateFlow<List<Found>> = _found.asStateFlow()

    private var manager: NsdManager? = null
    private var listener: NsdManager.DiscoveryListener? = null
    private var pendingResolve = 0

    fun start(context: Context) {
        if (listener != null) return
        val nsd = context.applicationContext.getSystemService(Context.NSD_SERVICE) as? NsdManager ?: return
        manager = nsd
        _found.value = emptyList()
        val l = object : NsdManager.DiscoveryListener {
            override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.w(TAG, "discovery start failed: $errorCode")
            }

            override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.w(TAG, "discovery stop failed: $errorCode")
            }

            override fun onDiscoveryStarted(serviceType: String) {
                Log.i(TAG, "discovery started")
            }

            override fun onDiscoveryStopped(serviceType: String) {
                Log.i(TAG, "discovery stopped")
            }

            override fun onServiceFound(info: NsdServiceInfo) {
                if (info.serviceType != Protocol.SERVICE_TYPE) return
                // 部分机型不允许并发 resolve，串行化
                if (pendingResolve > 0) return
                pendingResolve++
                runCatching {
                    nsd.resolveService(info, object : NsdManager.ResolveListener {
                        override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                            pendingResolve = 0
                            Log.w(TAG, "resolve failed: $errorCode")
                        }

                        override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
                            pendingResolve = 0
                            val address = serviceInfo.host
                            val host = (address as? Inet4Address)?.hostAddress ?: address?.hostAddress ?: return
                            val item = Found(serviceInfo.serviceName.orEmpty(), host, serviceInfo.port)
                            _found.value = (_found.value.filterNot { it.host == item.host } + item)
                            Log.i(TAG, "found ${item.name} at ${item.host}:${item.port}")
                        }
                    })
                }.onFailure {
                    pendingResolve = 0
                    Log.w(TAG, "resolve threw", it)
                }
            }

            override fun onServiceLost(info: NsdServiceInfo) {
                _found.value = _found.value.filterNot { it.name == info.serviceName }
            }
        }
        listener = l
        runCatching { nsd.discoverServices(Protocol.SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, l) }
            .onFailure {
                Log.w(TAG, "discover threw", it)
                listener = null
            }
    }

    fun stop() {
        val nsd = manager
        val l = listener
        listener = null
        manager = null
        pendingResolve = 0
        if (nsd != null && l != null) runCatching { nsd.stopServiceDiscovery(l) }
    }
}
