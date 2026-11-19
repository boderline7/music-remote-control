package com.cloudmusic.car.remote

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * mDNS 服务注册（`_cloudmusic-car._tcp`）。手机侧靠它自动发现车机，手动 IP 永远是兜底。
 *
 * 注册失败不影响 WebSocket 服务本身 —— 手动 IP 仍可连接，所以这里只记日志、不外抛。
 */
class NsdRegistrar(private val context: Context) {

    private var manager: NsdManager? = null
    private var listener: NsdManager.RegistrationListener? = null
    private var serviceName: String? = null

    private val _registered = MutableStateFlow(false)
    val registered: StateFlow<Boolean> = _registered.asStateFlow()

    fun register(port: Int) {
        unregister()
        if (port <= 0) return
        val nsd = context.getSystemService(Context.NSD_SERVICE) as? NsdManager ?: run {
            Log.w(RemoteConfig.TAG, "no NsdManager on this device")
            return
        }
        manager = nsd
        val info = NsdServiceInfo().apply {
            serviceType = RemoteConfig.SERVICE_TYPE
            setPort(port)
            serviceName = RemoteConfig.SERVICE_NAME_PREFIX
        }
        val reg = object : NsdManager.RegistrationListener {
            override fun onServiceRegistered(info: NsdServiceInfo) {
                serviceName = info.serviceName
                _registered.value = true
                Log.i(RemoteConfig.TAG, "nsd registered as ${info.serviceName}")
            }

            override fun onRegistrationFailed(info: NsdServiceInfo, errorCode: Int) {
                _registered.value = false
                Log.w(RemoteConfig.TAG, "nsd register failed: $errorCode")
            }

            override fun onServiceUnregistered(info: NsdServiceInfo) {
                _registered.value = false
            }

            override fun onUnregistrationFailed(info: NsdServiceInfo, errorCode: Int) {
                _registered.value = false
                Log.w(RemoteConfig.TAG, "nsd unregister failed: $errorCode")
            }
        }
        listener = reg
        runCatching { nsd.registerService(info, NsdManager.PROTOCOL_DNS_SD, reg) }
            .onFailure {
                _registered.value = false
                Log.w(RemoteConfig.TAG, "nsd register threw", it)
            }
    }

    fun unregister() {
        val nsd = manager
        val reg = listener
        listener = null
        manager = null
        serviceName = null
        if (nsd != null && reg != null) runCatching { nsd.unregisterService(reg) }
        _registered.value = false
    }
}
