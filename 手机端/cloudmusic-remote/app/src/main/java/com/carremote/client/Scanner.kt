package com.carremote.client

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.net.Inet4Address
import java.net.InetSocketAddress
import java.net.NetworkInterface
import java.net.Socket

/**
 * 网段扫描。
 *
 * 手机开热点时车机 IP 每次都可能变（192.168.43.x / 192.168.124.x / 172.20.10.x），
 * mDNS 在部分热点和车机上也经常发现不了。这里直接把本机所在 /24 扫一遍，
 * 谁能连上 18765 就是车机——比让用户去车上看 IP 靠谱得多。
 */
object Scanner {

    private const val TAG = "CarRemote"

    private val _scanning = MutableStateFlow(false)
    val scanning: StateFlow<Boolean> = _scanning.asStateFlow()

    private val _results = MutableStateFlow<List<String>>(emptyList())
    val results: StateFlow<List<String>> = _results.asStateFlow()

    /** 本机所有可用 IPv4 的 /24 前缀，例如 192.168.43。 */
    fun subnets(): List<String> = runCatching {
        NetworkInterface.getNetworkInterfaces()?.asSequence()
            ?.filter { it.isUp && !it.isLoopback }
            ?.flatMap { ni -> ni.inetAddresses.asSequence().map { ni.name to it } }
            ?.filter { (_, addr) -> addr is Inet4Address && addr.isSiteLocalAddress }
            ?.map { (_, addr) -> addr.hostAddress.orEmpty() }
            ?.filter { it.isNotEmpty() }
            ?.map { it.substringBeforeLast('.') }
            ?.distinct()
            ?.toList()
            .orEmpty()
    }.getOrDefault(emptyList())

    suspend fun scan(port: Int = Protocol.PORT, timeoutMs: Int = 350): List<String> =
        withContext(Dispatchers.IO) {
            val prefixes = subnets()
            if (prefixes.isEmpty()) {
                Log.w(TAG, "no local ipv4, skip scan")
                return@withContext emptyList()
            }
            _scanning.value = true
            val hits = java.util.Collections.synchronizedList(mutableListOf<String>())
            try {
                // 每批 64 个并发，400ms 内没开端口就算没有
                prefixes.forEach { prefix ->
                    (1..254).chunked(64).forEach { chunk ->
                        coroutineScope {
                            chunk.map { n ->
                                async {
                                    val ip = "$prefix.$n"
                                    if (reachable(ip, port, timeoutMs)) {
                                        hits.add(ip)
                                        Log.i(TAG, "hit $ip:$port")
                                    }
                                }
                            }.forEach { it.await() }
                        }
                    }
                }
            } finally {
                _scanning.value = false
            }
            _results.value = hits.toList().sorted()
            hits.toList()
        }

    private fun reachable(ip: String, port: Int, timeoutMs: Int): Boolean = runCatching {
        Socket().use { socket ->
            socket.connect(InetSocketAddress(ip, port), timeoutMs)
            true
        }
    }.getOrDefault(false)
}
