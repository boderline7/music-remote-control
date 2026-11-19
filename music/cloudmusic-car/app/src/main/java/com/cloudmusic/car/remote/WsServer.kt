package com.cloudmusic.car.remote

import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.security.MessageDigest
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread
import android.util.Base64

/**
 * 极简 WebSocket 服务端（RFC 6455 子集）。
 *
 * 只实现本案需要的部分：握手、单帧文本收发、ping/pong、close。
 * 不支持的（分片续帧、二进制帧、扩展协商）明确拒绝并断开，不做半吊子兼容。
 * 不引 OkHttp 是因为它要靠 Gradle 依赖拉取，这里只有百来行，够用且可控。
 *
 * 稳定性相关（2.1）：
 * - **握手带 5 秒读超时**，否则一条只连不说话的连接能永久占住一个线程；
 * - **并发连接数封顶**（[MAX_CONNS]），accept 不为每个 SYN 无限起线程；
 * - 发送队列换成 [OutQueue]（帧数 + 字节双预算、状态可合并）；
 * - 写线程用带超时的 poll，且记录「本次写开始时间」，由上层 watchdog 判定慢客户端；
 * - [stop] 会关掉所有已建立的连接，读线程不会卡在 read 里；
 * - 客户端帧必须是掩码帧、必须是 FIN，RSV 必须为 0，否则直接断开。
 */
class WsServer(
    private val port: Int,
    private val listener: Listener,
    /**
     * 同一个端口顺带服务普通 HTTP GET（手机浏览器打开遥控页面）。
     * 传 null 就退化成纯 WebSocket 服务器。
     */
    private val httpHandler: ((head: String, socket: Socket) -> Unit)? = null,
) {

    interface Listener {
        fun onOpen(c: Conn)
        fun onText(c: Conn, text: String)
        fun onClose(c: Conn)
    }

    class Conn internal constructor(private val socket: Socket, val id: Long) {
        private val out = socket.getOutputStream()

        /**
         * 所有写都在这条独立线程上做：调用方（包括主线程）只入队。
         * 否则主线程调 sendText 会抛 NetworkOnMainThreadException 直接崩进程。
         */
        private val queue = OutQueue()
        private val writer = thread(name = "ws-write-$id", isDaemon = true) { writeLoop() }

        @Volatile
        var closed = false
            private set

        /** 本次 write 开始的时间；0 表示当前没在写。用来抓「客户端不读导致写永久阻塞」。 */
        @Volatile
        private var writeStartedAt = 0L

        val remote: String get() = socket.inetAddress?.hostAddress ?: "?"

        val pendingFrames: Int get() = queue.pendingFrames
        val pendingBytes: Long get() = queue.pendingBytes

        /** 已经卡在一次 write 里多久。正常应该在毫秒级，>10s 说明对端不收了。 */
        fun writeStalledMs(): Long {
            val started = writeStartedAt
            return if (started == 0L) 0L else System.currentTimeMillis() - started
        }

        private fun writeLoop() {
            try {
                while (!closed) {
                    val frame = queue.poll(200) ?: continue
                    writeStartedAt = System.currentTimeMillis()
                    out.write(frame.data)
                    out.flush()
                    writeStartedAt = 0L
                    RemoteHealth.framesOut.incrementAndGet()
                    RemoteHealth.bytesOut.addAndGet(frame.data.size.toLong())
                }
            } catch (e: InterruptedException) {
                // stop() / close() 主动打断，属于正常退出
            } catch (e: IOException) {
                closeQuietly()
            } finally {
                writeStartedAt = 0L
                queue.close()
            }
        }

        /**
         * @param kind 缺省按「重要消息」处理；状态推送必须显式传 [OutQueue.Kind.STATE]，
         * 否则慢客户端会把状态也当重要消息攒着，越攒越多。
         */
        fun sendText(text: String, kind: OutQueue.Kind = OutQueue.Kind.CRITICAL) {
            if (closed) return
            var data = text.toByteArray(Charsets.UTF_8)
            if (data.size > RemoteConfig.MAX_FRAME_BYTES) {
                // 静默丢包会让手机端永远停在「加载中」，宁可回一条明确的错误
                Log.w(RemoteConfig.TAG, "drop oversized frame ${data.size}, reply error instead")
                val fallback = """{"type":"error","data":{"error":${RemoteConfig.ERR_INTERNAL},""" +
                    """"message":"结果过大（${data.size} 字节），已中断，请缩小范围"}}"""
                data = fallback.toByteArray(Charsets.UTF_8)
            }
            val header = when {
                data.size < 126 -> byteArrayOf(0x81.toByte(), data.size.toByte())
                else -> byteArrayOf(
                    0x81.toByte(),
                    126.toByte(),
                    ((data.size shr 8) and 0xFF).toByte(),
                    (data.size and 0xFF).toByte(),
                )
            }
            when (queue.offer(header + data, kind)) {
                OutQueue.Result.OVERFLOW -> {
                    // 重要消息都塞不下了，说明这条连接已经救不回来：断开让手机重连重拉
                    RemoteHealth.overflowCloses.incrementAndGet()
                    Log.w(RemoteConfig.TAG, "send queue overflow, closing conn $id")
                    close()
                }

                OutQueue.Result.COALESCED -> RemoteHealth.stateCoalesced.incrementAndGet()
                OutQueue.Result.DROPPED_STATE -> RemoteHealth.stateDropped.incrementAndGet()
                else -> Unit
            }
        }

        internal fun sendPong(payload: ByteArray) {
            if (closed) return
            val body = payload.copyOf(minOf(payload.size, 125))
            queue.offer(byteArrayOf(0x8A.toByte(), body.size.toByte()) + body, OutQueue.Kind.CRITICAL)
        }

        fun close() {
            closed = true
            queue.close()
            runCatching { socket.close() }
            writer.interrupt()
        }

        private fun closeQuietly() {
            closed = true
            queue.close()
            runCatching { socket.close() }
        }
    }

    private val nextId = AtomicLong(1)
    private val conns = ConcurrentHashMap<Long, Conn>()
    private val activeCount = AtomicInteger()

    @Volatile
    private var running = false
    private var serverSocket: ServerSocket? = null

    /** @return false 表示端口被占用或绑定失败（调用方据此提示，不影响原播放器）。 */
    @Synchronized
    fun start(): Boolean {
        if (running) return true
        return try {
            val socket = ServerSocket()
            socket.reuseAddress = true
            socket.bind(InetSocketAddress(port))
            serverSocket = socket
            running = true
            thread(name = "ws-accept", isDaemon = true) { acceptLoop(socket) }
            Log.i(RemoteConfig.TAG, "ws listening on $port")
            true
        } catch (e: Exception) {
            Log.w(RemoteConfig.TAG, "ws bind failed on $port", e)
            RemoteHealth.error("ws 端口 $port 绑定失败", e)
            runCatching { serverSocket?.close() }
            serverSocket = null
            running = false
            false
        }
    }

    @Synchronized
    fun stop() {
        running = false
        runCatching { serverSocket?.close() }
        serverSocket = null
        // 关键：已建立的连接也要关。否则读线程卡在 input.read() 上，stop 之后线程还在。
        conns.values.forEach { runCatching { it.close() } }
        conns.clear()
        activeCount.set(0)
    }

    fun connCount(): Int = conns.size

    fun pendingFrames(): Int = conns.values.sumOf { it.pendingFrames }

    fun pendingBytes(): Long = conns.values.sumOf { it.pendingBytes }

    fun isRunning(): Boolean = running

    private fun acceptLoop(socket: ServerSocket) {
        while (running) {
            val client = try {
                socket.accept()
            } catch (e: IOException) {
                // 只有「我们自己关了」（running 已被 stop 置 false）才算正常退出。
                // 如果还在 running 却 accept 不出来，说明监听 socket 已经废了：
                // 必须把 running 也置 false，否则 isRunning() 会一直返回 true，
                // 上层的健康检查就永远发现不了「端口还在、但没人 accept」这种假死。
                if (running) {
                    Log.w(RemoteConfig.TAG, "accept loop died, mark stopped", e)
                    RemoteHealth.error("accept 循环异常退出", e)
                    running = false
                }
                runCatching { socket.close() }
                break
            }
            if (activeCount.get() >= MAX_CONNS) {
                // 握手都不做，直接关：这是兜底，正常情况下 maxClients 早就拦住了
                RemoteHealth.rejected.incrementAndGet()
                Log.w(RemoteConfig.TAG, "too many conns (${activeCount.get()}), reject ${client.inetAddress?.hostAddress}")
                runCatching { client.close() }
                continue
            }
            thread(name = "ws-client-${client.port}", isDaemon = true) { serve(client) }
        }
    }

    private fun serve(socket: Socket) {
        var conn: Conn? = null
        try {
            socket.tcpNoDelay = true
            // 握手必须有超时：只连不发的客户端否则会永久占一个线程
            socket.soTimeout = HANDSHAKE_TIMEOUT_MS
            val head = readHead(socket)
            if (head == null) {
                socket.close()
                return
            }
            // 浏览器的普通 GET 不带升级头，走静态页；只有带 Sec-WebSocket-Key 的才升级成 WebSocket
            if (!head.contains("Sec-WebSocket-Key:", ignoreCase = true)) {
                val handler = httpHandler
                if (handler == null) socket.close() else handler(head, socket)
                return
            }
            if (!upgrade(socket, head)) {
                // 手机端网段扫描会先连一下端口再断开，这里会频繁出现，不当作错误
                Log.d(RemoteConfig.TAG, "handshake rejected from ${socket.inetAddress?.hostAddress}")
                socket.close()
                return
            }
            // 握手完成后不设读超时：连接可能长时间只收不发，靠心跳和上层 watchdog 判断死活
            socket.soTimeout = 0
            val c = Conn(socket, nextId.getAndIncrement())
            conn = c
            conns[c.id] = c
            activeCount.incrementAndGet()
            RemoteHealth.connects.incrementAndGet()
            runCatching { listener.onOpen(c) }
                .onFailure { RemoteHealth.error("onOpen 异常", it) }

            val input = socket.getInputStream()
            while (running && !c.closed) {
                val frame = readFrame(input) ?: break
                RemoteHealth.framesIn.incrementAndGet()
                RemoteHealth.bytesIn.addAndGet(frame.second.size.toLong())
                when (frame.first) {
                    0x1 -> {
                        RemoteHealth.messagesIn.incrementAndGet()
                        runCatching { listener.onText(c, String(frame.second, Charsets.UTF_8)) }
                            .onFailure { RemoteHealth.error("onText 异常", it) }
                    }

                    0x8 -> break
                    0x9 -> c.sendPong(frame.second)
                    else -> Unit
                }
            }
        } catch (e: IOException) {
            // 客户端断开是常态，不记错误
        } catch (e: Exception) {
            Log.w(RemoteConfig.TAG, "client error", e)
            RemoteHealth.error("连接处理异常", e)
        } finally {
            conn?.let {
                conns.remove(it.id)
                activeCount.decrementAndGet()
                RemoteHealth.disconnects.incrementAndGet()
                it.close()
                runCatching { listener.onClose(it) }
                    .onFailure { e -> RemoteHealth.error("onClose 异常", e) }
            }
        }
    }

    private fun readHead(socket: Socket): String? {
        val input = socket.getInputStream()
        val request = StringBuilder()
        while (request.length < MAX_HEAD_BYTES) {
            val b = input.read()
            if (b < 0) return null
            request.append(b.toChar())
            if (request.endsWith("\r\n\r\n")) break
        }
        return request.toString().takeIf { it.endsWith("\r\n\r\n") }
    }

    private fun upgrade(socket: Socket, head: String): Boolean {
        val key = head.lineSequence()
            .firstOrNull { it.startsWith("Sec-WebSocket-Key:", ignoreCase = true) }
            ?.substringAfter(':')?.trim()
            ?.takeIf(String::isNotEmpty)
            ?: return false
        val accept = Base64.encodeToString(
            MessageDigest.getInstance("SHA-1").digest((key + WS_GUID).toByteArray(Charsets.US_ASCII)),
            Base64.NO_WRAP,
        )
        val response = buildString {
            append("HTTP/1.1 101 Switching Protocols\r\n")
            append("Upgrade: websocket\r\n")
            append("Connection: Upgrade\r\n")
            append("Sec-WebSocket-Accept: ").append(accept).append("\r\n\r\n")
        }
        socket.getOutputStream().apply {
            write(response.toByteArray(Charsets.US_ASCII))
            flush()
        }
        return true
    }

    @Throws(IOException::class)
    private fun readFrame(input: InputStream): Pair<Int, ByteArray>? {
        val b0 = input.read()
        if (b0 < 0) return null
        val b1 = input.read()
        if (b1 < 0) return null
        val fin = (b0 and 0x80) != 0
        val rsv = b0 and 0x70
        val opcode = b0 and 0x0F
        val masked = (b1 and 0x80) != 0
        // 这三条都是 RFC 6455 的硬要求。我们不支持续帧，与其半吊子拼包，不如明确断开。
        if (rsv != 0) throw IOException("rsv bits set")
        if (!fin) throw IOException("fragmented frame unsupported")
        if (!masked) throw IOException("client frame not masked")
        var length = (b1 and 0x7F).toLong()
        if (length == 126L) {
            val hi = input.read()
            val lo = input.read()
            if (hi < 0 || lo < 0) return null
            length = (((hi shl 8) or lo) and 0xFFFF).toLong()
        } else if (length == 127L) {
            var value = 0L
            repeat(8) {
                val b = input.read()
                if (b < 0) return null
                value = (value shl 8) or b.toLong()
            }
            length = value
        }
        if (length < 0 || length > RemoteConfig.MAX_FRAME_BYTES) {
            throw IOException("frame too large: $length")
        }
        val mask = ByteArray(4)
        if (!readFully(input, mask)) return null
        val payload = ByteArray(length.toInt())
        if (!readFully(input, payload)) return null
        for (i in payload.indices) {
            payload[i] = (payload[i].toInt() xor mask[i % 4].toInt()).toByte()
        }
        return opcode to payload
    }

    private fun readFully(input: InputStream, target: ByteArray): Boolean {
        var offset = 0
        while (offset < target.size) {
            val read = input.read(target, offset, target.size - offset)
            if (read <= 0) return false
            offset += read
        }
        return true
    }

    private companion object {
        const val WS_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"

        /** 握手读超时。只连不发（或发一半）的连接最多占线程 5 秒。 */
        const val HANDSHAKE_TIMEOUT_MS = 5_000

        /** 请求头上限，防止有人灌一个巨大的 header 把内存吃掉。 */
        const val MAX_HEAD_BYTES = 8192

        /** 并发 socket 上限。是兜底，真正的业务上限在 maxClients。 */
        const val MAX_CONNS = 32
    }
}
