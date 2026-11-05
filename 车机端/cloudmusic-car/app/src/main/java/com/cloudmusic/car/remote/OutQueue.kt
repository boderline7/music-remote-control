package com.cloudmusic.car.remote

/**
 * 单连接的发送队列。**纯 Kotlin，不依赖 Android**，所以可以直接跑单元测试。
 *
 * 为什么不能只限帧数：128 帧 × 8 KB 上限 ≈ 1 MB，4 台手机就是 4 MB；而且慢客户端
 * 积压时真正要防的是字节数不是条数。
 *
 * 三级优先级（对应「状态可合并、回执不能随便丢」）：
 *
 * | 类型 | 内容 | 满了怎么办 |
 * |---|---|---|
 * | [Kind.STATE] | 播放状态（每秒一次） | 合并到上一条未发车的 state；合并不了就丢自己 |
 * | [Kind.BULK] | tracks / library / catalog | 先牺牲未发车的 state 腾地方，仍不够才溢出 |
 * | [Kind.CRITICAL] | ack / error / pong | 不丢；实在塞不下就溢出，由上层断开这条连接 |
 *
 * 「溢出」不是静默丢包：调用方收到 [Result.OVERFLOW] 后必须关掉这条连接，
 * 让手机端重连重新拉一次 —— 这比让它停在「加载中」强。
 */
class OutQueue(
    /** 软上限：正常状态下允许的帧数与字节数。 */
    private val softFrames: Int = 128,
    private val softBytes: Int = 256 * 1024,
    /** 硬上限：重要消息可以短暂突破软上限，但不能无限突破。 */
    private val hardFrames: Int = 256,
    private val hardBytes: Int = 512 * 1024,
) {

    enum class Kind { STATE, BULK, CRITICAL }

    enum class Result { ACCEPTED, COALESCED, DROPPED_STATE, OVERFLOW, REJECTED }

    class Frame(val data: ByteArray, val kind: Kind)

    private val lock = Object()
    private val items = ArrayDeque<Frame>()
    private var bytes = 0
    private var closed = false

    var coalesced: Long = 0L
        private set
    var droppedState: Long = 0L
        private set
    var overflow: Long = 0L
        private set

    val pendingFrames: Int get() = synchronized(lock) { items.size }
    val pendingBytes: Long get() = synchronized(lock) { bytes.toLong() }

    fun offer(data: ByteArray, kind: Kind): Result = synchronized(lock) {
        if (closed) return Result.REJECTED
        val size = data.size

        if (kind == Kind.STATE) {
            // 合并：上一条还没发出去的状态直接替换，进度条不需要历史
            val idx = items.indexOfLast { it.kind == Kind.STATE }
            if (idx >= 0) {
                val old = items[idx]
                bytes += size - old.data.size
                items[idx] = Frame(data, Kind.STATE)
                coalesced++
                return Result.COALESCED
            }
            if (fits(size, softFrames, softBytes)) {
                items.addLast(Frame(data, Kind.STATE))
                bytes += size
                return Result.ACCEPTED
            }
            // 队里全是回执/列表，这条状态没有价值，丢自己
            droppedState++
            return Result.DROPPED_STATE
        }

        if (fits(size, softFrames, softBytes)) {
            items.addLast(Frame(data, kind))
            bytes += size
            return Result.ACCEPTED
        }
        // 超出软上限：先把可丢的状态消息清掉，尽量保住列表和回执
        var guard = 0
        while (guard++ < 512 && dropOldestStateLocked()) {
            if (fits(size, softFrames, softBytes)) {
                items.addLast(Frame(data, kind))
                bytes += size
                return Result.ACCEPTED
            }
        }
        if (fits(size, hardFrames, hardBytes)) {
            items.addLast(Frame(data, kind))
            bytes += size
            return Result.ACCEPTED
        }
        overflow++
        Result.OVERFLOW
    }

    /** 取一帧；没有就返回 null（不阻塞）。 */
    fun take(): Frame? = synchronized(lock) {
        if (items.isEmpty()) null else {
            val f = items.removeFirst()
            bytes -= f.data.size
            f
        }
    }

    /** 最多等 [timeoutMs]，让写线程既能及时退出又不空转。 */
    fun poll(timeoutMs: Long): Frame? = synchronized(lock) {
        if (items.isEmpty() && !closed) {
            runCatching { lock.wait(timeoutMs) }
        }
        if (items.isEmpty()) null else {
            val f = items.removeFirst()
            bytes -= f.data.size
            f
        }
    }

    fun close() {
        synchronized(lock) {
            closed = true
            items.clear()
            bytes = 0
            lock.notifyAll()
        }
    }

    private fun fits(size: Int, frames: Int, budget: Int): Boolean =
        items.size + 1 <= frames && bytes + size <= budget

    /** @return 是否真的丢掉了一条状态消息。 */
    private fun dropOldestStateLocked(): Boolean {
        val idx = items.indexOfFirst { it.kind == Kind.STATE }
        if (idx < 0) return false
        val f = items[idx]
        items.removeAt(idx)
        bytes -= f.data.size
        droppedState++
        return true
    }
}
