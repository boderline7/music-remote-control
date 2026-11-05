package com.cloudmusic.car.remote

import android.content.Context
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.ArrayDeque
import java.util.Date
import java.util.Locale
import java.util.concurrent.atomic.AtomicLong

/**
 * 轻量诊断。**只记内存，不落盘**（除手动导出），所以不会因为「每秒写播放状态」拖慢车机。
 *
 * 定位是「上车出问题时，一眼看出是哪一段坏了」：
 * - 连接/消息计数对不上 → 卡在握手或队列；
 * - 线程数一直涨 → 某个 loop 没退出；
 * - 队列积压 → 有慢客户端；
 * - 宣告计数不涨 → mDNS 保活死了；
 * - 网易云耗时飙升 → 资料库慢，和播放器无关。
 *
 * 所有计数都是 Atomic，读的时候允许短暂不一致，不需要加锁。
 */
object RemoteHealth {

    private const val TAG = "RemoteDiag"
    private const val RING_CAP = 240

    private class Entry(val at: Long, val level: Char, val text: String)

    val startedAt: Long = System.currentTimeMillis()

    // ---- 连接 ----
    val connects = AtomicLong()
    val disconnects = AtomicLong()
    val rejected = AtomicLong()
    val peakClients = AtomicLong()

    // ---- 消息 ----
    val framesIn = AtomicLong()
    val framesOut = AtomicLong()
    val bytesIn = AtomicLong()
    val bytesOut = AtomicLong()
    val messagesIn = AtomicLong()
    val messagesOut = AtomicLong()

    // ---- 队列 ----
    val stateCoalesced = AtomicLong()
    val stateDropped = AtomicLong()
    val overflowCloses = AtomicLong()
    val slowClientCloses = AtomicLong()
    val writeStalls = AtomicLong()

    // ---- 网络 ----
    val networkChanges = AtomicLong()
    val mdnsRebuilds = AtomicLong()

    // ---- 网易云 ----
    private val netLast = AtomicLong(-1L)
    private val netMax = AtomicLong(0L)
    private val netTotal = AtomicLong(0L)
    private val netCount = AtomicLong(0L)
    private val netFailures = AtomicLong(0L)

    private val ring = ArrayDeque<Entry>()
    private val ringLock = Any()

    fun noteNetease(elapsedMs: Long, ok: Boolean) {
        netLast.set(elapsedMs)
        netMax.accumulateAndGet(elapsedMs) { a, b -> maxOf(a, b) }
        netTotal.addAndGet(elapsedMs)
        netCount.incrementAndGet()
        if (!ok) netFailures.incrementAndGet()
        if (!ok || elapsedMs > 3000) {
            log(if (ok) "netease ${elapsedMs}ms（偏慢）" else "netease 失败（${elapsedMs}ms）")
        }
    }

    fun log(text: String) = append('I', text)

    fun warn(text: String) = append('W', text)

    fun error(where: String, t: Throwable? = null) {
        append('E', if (t == null) where else "$where: ${t.javaClass.simpleName}: ${t.message}")
        Log.w(TAG, where, t)
    }

    private fun append(level: Char, text: String) {
        val e = Entry(System.currentTimeMillis(), level, text)
        synchronized(ringLock) {
            ring.addLast(e)
            while (ring.size > RING_CAP) ring.removeFirst()
        }
    }

    fun recent(limit: Int = 8): List<String> = synchronized(ringLock) {
        ring.toList().takeLast(limit).map { fmt(it.at) + " " + it.level + " " + it.text }
    }

    /** 环形缓冲全量（导出用）。 */
    fun dump(): List<String> = synchronized(ringLock) {
        ring.map { fmt(it.at) + " " + it.level + " " + it.text }
    }

    data class Snapshot(
        val uptimeMs: Long,
        val clients: Int,
        val peakClients: Long,
        val threads: Int,
        val remoteThreads: Int,
        val connects: Long,
        val disconnects: Long,
        val rejected: Long,
        val framesIn: Long,
        val framesOut: Long,
        val bytesIn: Long,
        val bytesOut: Long,
        val messagesIn: Long,
        val messagesOut: Long,
        val pendingFrames: Int,
        val pendingBytes: Long,
        val stateCoalesced: Long,
        val stateDropped: Long,
        val overflowCloses: Long,
        val slowClientCloses: Long,
        val writeStalls: Long,
        val networkChanges: Long,
        val mdnsRebuilds: Long,
        val neteaseLastMs: Long,
        val neteaseMaxMs: Long,
        val neteaseAvgMs: Long,
        val neteaseCount: Long,
        val neteaseFailures: Long,
        val errors: List<String>,
    )

    fun snapshot(clients: Int, pendingFrames: Int = 0, pendingBytes: Long = 0L): Snapshot {
        val all = runCatching { Thread.getAllStackTraces().keys }.getOrDefault(emptySet())
        val remoteThreads = all.count { it.name.startsWith("ws-") || it.name.startsWith("mdns") }
        val count = netCount.get()
        return Snapshot(
            uptimeMs = System.currentTimeMillis() - startedAt,
            clients = clients,
            peakClients = peakClients.get(),
            threads = all.size,
            remoteThreads = remoteThreads,
            connects = connects.get(),
            disconnects = disconnects.get(),
            rejected = rejected.get(),
            framesIn = framesIn.get(),
            framesOut = framesOut.get(),
            bytesIn = bytesIn.get(),
            bytesOut = bytesOut.get(),
            messagesIn = messagesIn.get(),
            messagesOut = messagesOut.get(),
            pendingFrames = pendingFrames,
            pendingBytes = pendingBytes,
            stateCoalesced = stateCoalesced.get(),
            stateDropped = stateDropped.get(),
            overflowCloses = overflowCloses.get(),
            slowClientCloses = slowClientCloses.get(),
            writeStalls = writeStalls.get(),
            networkChanges = networkChanges.get(),
            mdnsRebuilds = mdnsRebuilds.get(),
            neteaseLastMs = netLast.get(),
            neteaseMaxMs = netMax.get(),
            neteaseAvgMs = if (count > 0) netTotal.get() / count else -1L,
            neteaseCount = count,
            neteaseFailures = netFailures.get(),
            errors = recent(6),
        )
    }

    /**
     * 导出诊断文本。唯一的磁盘写入点 —— 只在用户点「导出」时发生。
     * @return 写好的文件；失败返回 null。
     */
    fun export(context: Context, clients: Int, pendingFrames: Int, pendingBytes: Long): File? {
        val s = snapshot(clients, pendingFrames, pendingBytes)
        val body = buildString {
            appendLine("# cloudmusic-car 遥控诊断")
            appendLine("导出时间: ${fmt(System.currentTimeMillis())}")
            appendLine("运行时长: ${fmtDuration(s.uptimeMs)}")
            appendLine("连接: 当前 ${s.clients} / 峰值 ${s.peakClients} / 累计 ${s.connects} / 断开 ${s.disconnects} / 拒绝 ${s.rejected}")
            appendLine("线程: 总 ${s.threads}，遥控相关 ${s.remoteThreads}")
            appendLine("消息: 入 ${s.messagesIn} 条 / 出 ${s.messagesOut} 条")
            appendLine("帧: 入 ${s.framesIn} (${s.bytesIn}B) / 出 ${s.framesOut} (${s.bytesOut}B)")
            appendLine("发送队列: 待发 ${s.pendingFrames} 帧 / ${s.pendingBytes}B")
            appendLine("队列策略: 合并 ${s.stateCoalesced} / 丢弃状态 ${s.stateDropped} / 溢出断开 ${s.overflowCloses}")
            appendLine("慢客户端: 断开 ${s.slowClientCloses} / 写阻塞 ${s.writeStalls}")
            appendLine("网络变化: ${s.networkChanges} 次，mDNS 重建 ${s.mdnsRebuilds} 次")
            appendLine("网易云请求: ${s.neteaseCount} 次，最近 ${s.neteaseLastMs}ms，最大 ${s.neteaseMaxMs}ms，平均 ${s.neteaseAvgMs}ms，失败 ${s.neteaseFailures}")
            appendLine("最近错误:")
            s.errors.forEach { appendLine("  $it") }
            appendLine()
            appendLine("## 环形日志（最近 ${dump().size} 条）")
            dump().forEach { appendLine(it) }
        }
        return runCatching {
            val dir = (context.getExternalFilesDir("diag") ?: File(context.filesDir, "diag"))
                .apply { mkdirs() }
            val name = "remote-diag-" +
                SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(Date()) + ".txt"
            File(dir, name).apply {
                writeText(body)
                Log.i(TAG, "diag exported: $absolutePath")
            }
        }.getOrNull()
    }

    fun fmtDuration(ms: Long): String {
        val total = ms / 1000
        val d = total / 86400
        val h = total % 86400 / 3600
        val m = total % 3600 / 60
        val s = total % 60
        return when {
            d > 0 -> "${d}d${h}h${m}m"
            h > 0 -> "${h}h${m}m${s}s"
            m > 0 -> "${m}m${s}s"
            else -> "${s}s"
        }
    }

    private fun fmt(ms: Long): String =
        SimpleDateFormat("HH:mm:ss", Locale.US).format(Date(ms))
}
