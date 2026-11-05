package com.cloudmusic.car.remote

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import java.io.IOException
import java.net.DatagramPacket
import java.net.Inet4Address
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.MulticastSocket
import java.net.NetworkInterface
import java.net.SocketAddress
import java.net.SocketTimeoutException
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

/**
 * 极简 mDNS 响应器：把 `carremote.local` 永远指向车机当前的热点 IP。
 *
 * 车机开热点时网段每次开机都可能变，浏览器书签里的 IP 就废了。
 * mDNS 把「名字」和「IP」解耦：名字固定，IP 一变就换答案。
 *
 * 只做网页遥控需要的最小集合，但每一条都按 RFC 6762 来，否则 iOS 会静默丢包：
 * - 一个查询包里可能有**多条** question（iOS 常把 A 和 AAAA 塞在同一个包里，顺序不定），
 *   所以先解析全部 question，只要有 A 就答 A。
 * - 纯 AAAA 查询回一条 NSEC（「有 A、没有 AAAA」），让对方立刻回落，不用等 IPv6 超时。
 *   有 A 的包里绝不掺 NSEC，避免一条怪记录把 A 一起带坏。
 * - 应答默认用**组播**发回 224.0.0.251:5353（查询里 QU 位置位时才单播）。
 *   iOS 的 mDNSResponder 对没有请求单播的查询只认组播应答。
 * - 组播包 TTL = 255（多播 TTL 就是它），这是 mDNS 的硬性要求。
 * - 答复的地址取**收包那块网卡**的 IP，不再第二次枚举网卡去猜。
 * - 启动和 IP 变化时主动 announce 两次，让手机侧不用等查询就有缓存。
 *
 * ## 为什么必须周期性重播 announce（v2.0 的关键修复）
 *
 * 上一版只在启动时宣告一次，之后靠「客户端来查 → 我们回」续命。实测结果是：
 * 刚启动能打开，过一两分钟就再也进不去，而同一时刻用 IP 访问完全正常。
 * 原因不在应答逻辑，而在**应答这条路本身不可靠**：
 * 车机热点网卡的组播收方向经常是断的（驱动过滤、AP 模式下的 IGMP snooping、
 * 5353 端口被系统 mDNS 分走），我们压根收不到查询；而手机侧的缓存 TTL 只有 120 秒，
 * 一到期就必须重新查询，于是「启动时那一下能进」变成「两分钟后就进不去」。
 *
 * 修法是绕开查询路径：**每 45 秒主动重播一次 A 记录**，让对端缓存永远不到期。
 * 代价是每 45 秒一个 43 字节的组播包，可以忽略；换来的是域名解析不再依赖双向组播。
 * 重播带 cache-flush 位，对端会直接用新答案覆盖旧记录，IP 变了也能立刻纠正。
 *
 * 组播包在部分机型上默认被 WiFi 驱动过滤，所以必须持有 MulticastLock。
 */
class MdnsResponder(private val context: Context) {

    /**
     * 一个 question。`start` 是名字第一个字节的偏移，用来在应答里用压缩指针指回来；
     * `end` 是 class 字段之后的偏移（即下一条 question 的起点）。
     */
    private class Question(
        val name: String,
        val type: Int,
        val start: Int,
        val end: Int,
        val unicast: Boolean,
    )

    @Volatile
    private var running = false
    /** 跨线程读写（收包线程建、watchdog 与亮屏回调读），必须 volatile。 */
    @Volatile
    private var socket: MulticastSocket? = null
    private var multicastLock: WifiManager.MulticastLock? = null
    private var worker: Thread? = null
    private var announcer: Thread? = null

    /** 连续宣告失败次数。到 3 就重建 socket。 */
    private var announceFailures = 0

    /** 所有候选网卡，形如 `ap0=192.168.43.1, wlan0=192.168.1.7`，排查选错网卡用。 */
    @Volatile
    var candidates: String = ""
        private set

    /** 下面几个字段给设置页的自检面板读，都是只读对外。 */
    @Volatile
    var interfaceName: String = ""
        private set

    @Volatile
    var hostIp: String = ""
        private set

    @Volatile
    var queryCount: Long = 0L
        private set

    @Volatile
    var answerCount: Long = 0L
        private set

    @Volatile
    var locked: Boolean = false
        private set

    @Volatile
    var lastError: String = ""
        private set

    /** 最近一次收到的查询，形如 `A carremote.local`，排查时比数字有用。 */
    @Volatile
    var lastQuery: String = ""
        private set

    /** 累计发出的宣告包数。每 45 秒应该稳定 +2，不涨就说明保活线程已经死了。 */
    @Volatile
    var announceCount: Long = 0L
        private set

    /** 距上次宣告过了多久（毫秒）。设置页显示成秒，超过 60 说明保活没转起来。 */
    @Volatile
    var sinceAnnounceMs: Long = -1L
        private set

    /** socket 实际绑定时用的 IP。只由 [loop] 写，[refresh] 拿它当重建基准。 */
    @Volatile
    private var boundIp: String = ""

    @Volatile
    private var lastAnnounceAt: Long = 0L

    @Volatile
    private var announcedIp: String = ""

    /**
     * worker 代次。stop 之后旧线程可能还卡在 receive 里没退，
     * 紧接着 start 又把 running 置回 true，旧线程就会活过来和新线程抢 5353。
     * 每代线程只认自己那一个 gen，stop 一加，旧的立刻失效。
     */
    private val generation = AtomicInteger(0)

    fun isRunning(): Boolean = running

    @Synchronized
    fun start() {
        if (running) return
        running = true
        val gen = generation.incrementAndGet()
        acquireLock()
        worker = thread(name = "mdns-responder", isDaemon = true) {
            runCatching { loop(gen) }.onFailure { Log.w(RemoteConfig.TAG, "mdns worker died", it) }
        }
        // 宣告独立成一条线程，和收包互不影响（详见 announceLoop 注释）
        announcer = thread(name = "mdns-announce", isDaemon = true) {
            runCatching { announceLoop(gen) }.onFailure { Log.w(RemoteConfig.TAG, "mdns announcer died", it) }
        }
        Log.i(RemoteConfig.TAG, "mdns responder starting")
    }

    @Synchronized
    fun stop() {
        running = false
        generation.incrementAndGet() // 让旧线程彻底失效
        runCatching { socket?.close() }
        socket = null
        runCatching { multicastLock?.release() }
        multicastLock = null
        locked = false
        interfaceName = ""
        boundIp = ""
        announcedIp = ""
        lastAnnounceAt = 0L
        sinceAnnounceMs = -1L
        announceFailures = 0
        announcer?.interrupt()
        announcer = null
        worker = null
    }

    /**
     * 5 秒一次的体检（由 RemoteServer 的 watchdog 调）。热点重连后网卡名或地址都可能变：
     * 换网卡 / 换地址就把 socket 关掉让 [loop] 重建。
     *
     * 这里必须拿 [boundIp] 当基准，**不能拿 [hostIp]**：hostIp 会被 [onPacket] 实时改成
     * 收包时读到的地址，拿它比较等于永远相等，socket 就再也不会重建了。
     *
     * 日常续命交给 [loop] 里的 keepAlive，这里不做重复宣告。
     */
    fun refresh() {
        candidates = describeCandidates()
        val ni = pickInterface()
        if (ni == null) {
            lastError = "没找到局域网网卡（热点还没起来？）"
            return
        }
        val ip = ipv4Of(ni) ?: return
        if (ni.name != interfaceName || ip != boundIp) {
            // socket == null 说明 loop 已经在重建了（forceRebuild 之后要重新 bind），
            // 这时候再触发一次只会把计数刷成噪声，还会把刚绑好的 socket 又关掉。
            if (socket == null) return
            Log.i(RemoteConfig.TAG, "mdns 网卡/地址变化 ${interfaceName}/${boundIp} -> ${ni.name}/$ip，重建")
            RemoteHealth.log("mDNS 网卡/地址变化 ${interfaceName}/${boundIp} -> ${ni.name}/$ip")
            forceRebuild()
            return
        }
        hostIp = ip
        sinceAnnounceMs = if (lastAnnounceAt == 0L) -1L else System.currentTimeMillis() - lastAnnounceAt
        // 兜底：宣告线程卡死（或 socket 半死不活）时，超过 3 个间隔没喊就重建
        val last = lastAnnounceAt
        if (last != 0L && System.currentTimeMillis() - last > ANNOUNCE_INTERVAL_MS * 3) {
            RemoteHealth.error("mDNS 宣告停滞 ${(System.currentTimeMillis() - last) / 1000}s，重建 socket")
            forceRebuild()
        }
    }

    /**
     * 强制重建：关掉 socket 让 [loop] 重新绑定，并把 lastAnnounceAt 清零，
     * 新 socket 一起来就立刻宣告，不用再等 45 秒。
     *
     * 同时清掉 [boundIp] / [announcedIp]：宣告线程是独立跑 5 秒一跳的，
     * 如果不清，重建的空窗里它还会拿着**旧地址**再喊一遍 —— 手机刚拿到新 IP 又被改回旧 IP，
     * 表现为「域名一会儿能进一会儿不能」。清掉之后宣告线程读不到地址就跳过，等新 socket。
     */
    fun forceRebuild() {
        boundIp = ""
        announcedIp = ""
        lastAnnounceAt = 0L
        runCatching { socket?.close() }
        RemoteHealth.mdnsRebuilds.incrementAndGet()
    }

    /**
     * 该不该重建？给亮屏、网络回调这类「外部事件」用。
     *
     * 判据是「多久没成功宣告过」，而不是「socket 是否为空」：
     * 熄屏后热点被回收时 socket 常常不报错，只是发不出去了，光看状态看不出来。
     * 两个宣告间隔（90 秒）没成功过一次，就认为它已经僵了。
     *
     * socket 为空说明 [loop] 正在重建，这时候不要插手。
     */
    fun needsRebuild(): Boolean {
        if (socket == null) return false
        val last = lastAnnounceAt
        if (last == 0L) return true
        return System.currentTimeMillis() - last > ANNOUNCE_INTERVAL_MS * 2
    }

    // ---------- 内部 ----------

    private fun acquireLock() {
        runCatching {
            val wm = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager
                ?: return@runCatching
            val lock = wm.createMulticastLock("cloudmusic-car-mdns")
            lock.setReferenceCounted(false)
            lock.acquire()
            multicastLock = lock
            locked = true
        }.onFailure {
            locked = false
            lastError = "组播锁获取失败：${it.message}"
            Log.w(RemoteConfig.TAG, "multicast lock failed", it)
        }
    }

    /**
     * 收包循环。注意 [MulticastSocket.soTimeout] 设了 1 秒：
     * `receive` 不能死等，因为「没人来查」是常态，而**宣告必须照发**。
     * 超时分支就是保活心跳，顺便当作网卡状况的探针。
     */
    private fun loop(gen: Int) {
        while (running && gen == generation.get()) {
            var soc: MulticastSocket? = null
            try {
                val ni = pickInterface()
                if (ni == null) {
                    lastError = "没找到局域网网卡（热点还没起来？）"
                    Thread.sleep(2000)
                    continue
                }
                val ip = ipv4Of(ni)
                if (ip == null) {
                    lastError = "网卡 ${ni.name} 上没有 IPv4 地址"
                    Thread.sleep(2000)
                    continue
                }
                interfaceName = ni.name
                hostIp = ip
                boundIp = ip

                // 先 reuseAddress 再 bind：顺序反了的话，端口被系统 mDNS 占着时直接 BindException
                soc = MulticastSocket(null as SocketAddress?)
                soc.reuseAddress = true
                try {
                    soc.bind(InetSocketAddress(5353))
                } catch (e: java.net.BindException) {
                    // 5353 被系统 mDNS / 其它应用占了。此时「收查询」这条路废了，
                    // 但组播「发宣告」仍然可能成功 —— 域名还有机会用，所以不直接放弃。
                    lastError = "5353 被占用（系统 mDNS？），收查询不可用，仅靠宣告"
                    RemoteHealth.error("mDNS 5353 绑定失败", e)
                    throw e
                }
                soc.networkInterface = ni
                soc.timeToLive = 255
                soc.soTimeout = RECV_TIMEOUT_MS
                soc.joinGroup(InetSocketAddress(GROUP, 5353), ni)
                socket = soc
                lastError = ""
                Log.i(RemoteConfig.TAG, "mdns listening on ${ni.name} / $ip")
                // 启动时背靠背发两份：组播是 UDP，连发两份能明显提高新客户端一次就收到的概率
                sendAnnounce(soc, ip, 2)

                val buffer = ByteArray(1500)
                while (running && gen == generation.get()) {
                    val packet = DatagramPacket(buffer, buffer.size)
                    try {
                        soc.receive(packet)
                    } catch (e: SocketTimeoutException) {
                        // 1 秒没包是常态（没人查询）。宣告已经交给独立线程，这里只更新统计。
                        sinceAnnounceMs =
                            if (lastAnnounceAt == 0L) -1L else System.currentTimeMillis() - lastAnnounceAt
                        continue
                    }
                    sinceAnnounceMs =
                        if (lastAnnounceAt == 0L) -1L else System.currentTimeMillis() - lastAnnounceAt
                    runCatching { onPacket(packet, soc) }
                        .onFailure { Log.w(RemoteConfig.TAG, "mdns packet error", it) }
                }
            } catch (t: Throwable) {
                if (running) {
                    lastError = "mDNS 异常：${t.message ?: t.javaClass.simpleName}"
                    Log.w(RemoteConfig.TAG, "mdns loop error", t)
                }
                runCatching { soc?.close() }
                socket = null
                boundIp = ""
                announcedIp = ""
                sinceAnnounceMs = -1L
                if (running) Thread.sleep(3000)
            }
        }
    }

    /**
     * 宣告线程：**只管喊，不管收**。
     *
     * 2.1 把它从收包循环里拆出来，原因是之前「发送失败 → 抛异常 → 重建 socket」，
     * 把回包路径和保活路径绑死了：一次发送抖动会连带把正常工作的收包 socket 拆掉。
     * 现在两条路各自独立，宣告失败只记数，连续失败才重建。
     *
     * 每 [ANNOUNCE_TICK_MS] 醒一次，距上次宣告满 [ANNOUNCE_INTERVAL_MS] 才真的发。
     */
    private fun announceLoop(gen: Int) {
        while (running && gen == generation.get()) {
            try {
                Thread.sleep(ANNOUNCE_TICK_MS)
            } catch (e: InterruptedException) {
                break
            }
            if (!running || gen != generation.get()) break
            val soc = socket ?: continue
            val ip = boundIp
            if (ip.isEmpty()) continue

            val now = System.currentTimeMillis()
            sinceAnnounceMs = if (lastAnnounceAt == 0L) -1L else now - lastAnnounceAt
            if (lastAnnounceAt != 0L && now - lastAnnounceAt < ANNOUNCE_INTERVAL_MS) continue

            if (sendAnnounce(soc, ip, 1)) {
                announceFailures = 0
            } else {
                announceFailures++
                if (announceFailures >= 3) {
                    // 连着三次发不出去，基本可以判定 socket 已经废了：关掉让 loop 重建
                    RemoteHealth.error("mDNS 连续 ${announceFailures} 次宣告失败，重建 socket")
                    announceFailures = 0
                    runCatching { soc.close() }
                }
            }
        }
    }

    private fun onPacket(packet: DatagramPacket, soc: MulticastSocket) {
        val questions = parseQuestions(packet.data, packet.length) ?: return
        val mine = questions.filter { it.name == HOST_NAME || it.name == RemoteConfig.MDNS_HOST }
        if (mine.isEmpty()) return
        queryCount++

        val ip = ipv4Of(currentInterface()) ?: hostIp.takeIf(String::isNotEmpty) ?: return
        hostIp = ip

        // 「最近查询」先按原样记下来。设置页上这行是排查的主力：
        // 看到 A 却「忽略」，说明判断逻辑有问题；看到只有 AAAA，就是对方在问 IPv6。
        lastQuery = mine.joinToString("+") { typeName(it.type) } + " " + RemoteConfig.MDNS_HOST +
            if (mine.any { it.unicast }) " (QU)" else ""

        // 一个查询包里可能同时带 A 和 AAAA（iOS 的 mDNSResponder 就常这么发，
        // 而且不一定把 A 放第一条）。所以要把所有 question 都看一遍，
        // 只要里面有 A 就按正向应答回 —— 上一版只看第一条，遇上 AAAA 打头就直接
        // return 了，于是设置页一直是「有收无回」，手机那边却永远解析不出名字。
        val forward = mine.filter { it.type == TYPE_A || it.type == TYPE_ANY }
        // 只有包里压根没有 A、纯问 AAAA 时，才回一条 NSEC 明确告诉对方「这个名字没有 IPv6」，
        // 免得它一直等 IPv6 超时。有 A 的包里绝不掺 NSEC，避免一条怪记录把 A 一起带坏。
        val negative = forward.isEmpty() && mine.any { it.type == TYPE_AAAA }
        val answering = if (forward.isNotEmpty()) forward else mine.filter { it.type == TYPE_AAAA }
        if (answering.isEmpty()) {
            lastQuery += " · 忽略"
            return
        }
        lastQuery += if (negative) " · 回 NSEC" else " · 已答"

        val reply = buildAnswer(packet.data, answering, ip, negative, TTL_ANSWER) ?: return
        // 一律组播回 5353。
        // 单播回复看起来更"省事"，但 MulticastSocket 只能设 IP_MULTICAST_TTL，
        // 单播包仍是默认的 64，而 RFC 6762 要求 mDNS 报文 TTL=255，
        // iOS 的 mDNSResponder 对 TTL≠255 的包直接静默丢弃。
        runCatching { soc.send(DatagramPacket(reply, reply.size, GROUP, 5353)) }
            .onFailure {
                lastError = "回包发送失败：${it.message}"
                Log.w(RemoteConfig.TAG, "mdns reply send failed", it)
            }
        // 再单播一份给提问者：热点如果挡了组播，单播是唯一可能漏过去的路径。
        // iOS 多半会丢弃（TTL 不是 255），多一个包换一个机会，不影响正常路径。
        runCatching {
            val port = if (packet.port > 0) packet.port else 5353
            soc.send(DatagramPacket(reply, reply.size, packet.address, port))
        }
        answerCount++
    }

    /**
     * 主动宣告：QDCOUNT=0, ANCOUNT=1，对端不用查也能缓存。
     * 启动时发一次、之后每 45 秒发一次，靠它把对端缓存续住。
     *
     * @return 至少成功发出一份返回 true。失败由调用方决定要不要重建 socket。
     */
    private fun sendAnnounce(soc: MulticastSocket, ip: String, times: Int): Boolean {
        val pkt = buildAnnounce(ip) ?: return false
        var sent = 0
        repeat(times) {
            runCatching {
                soc.send(DatagramPacket(pkt, pkt.size, GROUP, 5353))
                sent++
            }.onFailure {
                lastError = "宣告发送失败：${it.message}"
                Log.w(RemoteConfig.TAG, "mdns announce failed", it)
            }
        }
        if (sent == 0) return false
        val changed = ip != announcedIp
        announcedIp = ip
        lastAnnounceAt = System.currentTimeMillis()
        announceCount += sent
        if (changed) Log.i(RemoteConfig.TAG, "mdns announced $HOST_NAME.local -> $ip")
        return true
    }

    /**
     * 解析包里**所有** question。不是查询（而是响应）就返回 null。
     *
     * iOS 常把一个域名的 A 和 AAAA 放在同一个查询包里（QDCOUNT=2），
     * 所以不能只读第一条。
     */
    private fun parseQuestions(data: ByteArray, length: Int): List<Question>? {
        if (length < 12) return null
        val flags = u16(data, 2)
        if (flags and 0x8000 != 0) return null // QR=1：这是别人发的响应，别跟着回
        val count = u16(data, 4)
        if (count < 1) return null

        var pos = 12
        val out = ArrayList<Question>(minOf(count, 8))
        repeat(count) {
            val start = pos
            val name = StringBuilder()
            while (pos < length) {
                val len = data[pos].toInt() and 0xFF
                if (len == 0) {
                    pos++
                    break
                }
                if (len and 0xC0 != 0) return null // 查询里不该出现压缩指针
                pos++
                if (pos + len > length) return null
                if (name.isNotEmpty()) name.append('.')
                name.append(String(data, pos, len, Charsets.UTF_8))
                pos += len
            }
            if (pos + 4 > length) return null
            val type = u16(data, pos)
            val qclass = u16(data, pos + 2)
            pos += 4
            out.add(
                Question(
                    name = name.toString().lowercase().removeSuffix("."),
                    type = type,
                    start = start,
                    end = pos,
                    unicast = qclass and 0x8000 != 0,
                ),
            )
        }
        return out
    }

    private fun u16(data: ByteArray, at: Int): Int =
        ((data[at].toInt() and 0xFF) shl 8) or (data[at + 1].toInt() and 0xFF)

    /**
     * 回包 = 原样带回要答的 question + 每个 question 一条应答。
     * question 的 class 强制写回 IN（把 QU 位清掉），响应里不该带 QU。
     *
     * [negative] 为 true 时答的是 NSEC（「这个名字没有 AAAA，但有 A」），
     * 否则答 A 记录。
     */
    private fun buildAnswer(
        data: ByteArray,
        questions: List<Question>,
        ip: String,
        negative: Boolean,
        ttl: Int,
    ): ByteArray? {
        val addr = runCatching { InetAddress.getByName(ip).address }.getOrNull() ?: return null
        if (addr.size != 4) return null
        if (questions.isEmpty()) return null

        val recordBytes = if (negative) NSEC_RECORD_BYTES else A_RECORD_BYTES
        val questionBytes = questions.sumOf { it.end - it.start }
        val out = ByteArray(12 + questionBytes + questions.size * recordBytes)

        out[2] = 0x84.toByte() // QR=1, AA=1
        val n = questions.size
        out[4] = ((n shr 8) and 0xFF).toByte()
        out[5] = (n and 0xFF).toByte()
        out[6] = ((n shr 8) and 0xFF).toByte()
        out[7] = (n and 0xFF).toByte()
        // NSCOUNT / ARCOUNT 保持 0

        // 逐条复制要答的 question。注意：原包里可能还带着不回的问题（比如 AAAA），
        // 所以复制后偏移会变，压缩指针必须用**回包里的新偏移**，不能用原包的 start。
        val offsets = IntArray(questions.size)
        var p = 12
        for ((i, q) in questions.withIndex()) {
            val len = q.end - q.start
            offsets[i] = p
            System.arraycopy(data, q.start, out, p, len)
            out[p + len - 2] = 0x00
            out[p + len - 1] = 0x01 // class = IN
            p += len
        }
        for ((i, q) in questions.withIndex()) p = writeRecord(out, p, addr, ttl, offsets[i], negative)
        return out
    }

    /** 主动宣告：没有 question，只有一条 A 记录。 */
    private fun buildAnnounce(ip: String): ByteArray? {
        val addr = runCatching { InetAddress.getByName(ip).address }.getOrNull() ?: return null
        if (addr.size != 4) return null
        val nameAt = 12
        val labels = listOf(HOST_NAME, "local")
        val nameLen = labels.sumOf { it.length + 1 } + 1 // 每段一个长度字节，末尾一个根 0
        val name = ByteArray(nameLen)
        var p = 0
        for (label in labels) {
            name[p++] = label.length.toByte()
            for (c in label.toByteArray(Charsets.UTF_8)) name[p++] = c
        }
        name[p] = 0

        // 名字直接写全（17 字节），记录体比用指针时少 2 字节，别多分配出两个 0
        val out = ByteArray(nameAt + nameLen + A_RECORD_BYTES - 2)
        out[2] = 0x84.toByte() // QR=1, AA=1
        out[7] = 1 // ANCOUNT
        System.arraycopy(name, 0, out, nameAt, nameLen)
        // 宣告包里 QDCOUNT=0，名字直接写在应答里（不用压缩指针，Apple 的包也是这么写的）
        writeRecord(out, nameAt + nameLen, addr, TTL_ANNOUNCE, nameAt, negative = false, pointer = false)
        return out
    }

    /** 写一条资源记录，[nameAt] 是名字在原包里的偏移，用压缩指针指回去。 */
    private fun writeRecord(
        out: ByteArray,
        at: Int,
        addr: ByteArray,
        ttl: Int,
        nameAt: Int,
        negative: Boolean,
        pointer: Boolean = true,
    ): Int {
        var p = at
        if (pointer) {
            out[p++] = (0xC0 or ((nameAt shr 8) and 0x3F)).toByte()
            out[p++] = (nameAt and 0xFF).toByte()
        }
        val type = if (negative) TYPE_NSEC else TYPE_A
        out[p++] = ((type shr 8) and 0xFF).toByte()
        out[p++] = (type and 0xFF).toByte()
        out[p++] = 0x80.toByte() // IN + cache-flush
        out[p++] = 0x01
        out[p++] = 0
        out[p++] = 0
        out[p++] = ((ttl shr 8) and 0xFF).toByte()
        out[p++] = (ttl and 0xFF).toByte()

        if (!negative) {
            out[p++] = 0
            out[p++] = 4
            out[p++] = addr[0]
            out[p++] = addr[1]
            out[p++] = addr[2]
            out[p++] = addr[3]
            return p
        }

        // NSEC：rdata = 名字指针 + 类型位图。位图只列「确实存在的类型」。
        // window 0 的第 1 位（0x40）表示 A 存在；AAAA(28) 不存在就不写 window 3，
        // 写着 0x08 反而是「存在」，那是反的。
        out[p++] = 0
        out[p++] = 5
        out[p++] = (0xC0 or ((nameAt shr 8) and 0x3F)).toByte()
        out[p++] = (nameAt and 0xFF).toByte()
        out[p++] = 0x00 // window 0：类型 0-7
        out[p++] = 0x01 // 位图长度 1 字节
        out[p++] = 0x40 // A(type 1) 存在
        return p
    }

    /**
     * 优先热点网卡：ap0 / swlan0 这类；没有才退到 wlan。
     * 车机常常同时连着 WiFi（上行）又开着热点，两块网卡都是 site-local，
     * 名字前缀是唯一能稳定区分它们的信息。
     */
    /**
     * 选网卡。不能简单取「第一个有 IPv4 的」：车机常见同时有
     * 热点网卡（ap0 / swlan0 / softap0）、STA 网卡（wlan0）、4G（rmnet）、USB（rndis）。
     * 手机连的是热点，答案必须是热点网卡的地址，否则解析出来的 IP 根本连不上。
     *
     * 打分而不是硬编码顺序，是因为各家 ROM 的网卡命名不统一，硬编码遇到没见过的名字
     * 就会静默选错。分数相同就按枚举顺序取第一个（稳定、可复现）。
     */
    private fun pickInterface(): NetworkInterface? {
        val candidates = candidateInterfaces()
        if (candidates.isEmpty()) return null
        var best: NetworkInterface? = null
        var bestScore = Int.MIN_VALUE
        candidates.forEach { ni ->
            val ip = ipv4Of(ni) ?: return@forEach
            val score = scoreOf(ni.name, ip)
            if (score > bestScore) {
                bestScore = score
                best = ni
            }
        }
        return best ?: candidates.first()
    }

    private fun candidateInterfaces(): List<NetworkInterface> =
        NetworkInterface.getNetworkInterfaces()?.toList().orEmpty()
            .filter { it.isUp && !it.isLoopback && !it.isVirtual }
            .filter { ni -> ni.inetAddresses.toList().any { it is Inet4Address && it.isSiteLocalAddress } }

    private fun scoreOf(name: String, ip: String): Int = Companion.scoreOf(name, ip)

    /** 形如 `ap0=192.168.43.1, wlan0=192.168.1.7`，设置页直接显示。 */
    private fun describeCandidates(): String = runCatching {
        candidateInterfaces().joinToString(", ") { ni ->
            "${ni.name}=${ipv4Of(ni) ?: "?"}"
        }
    }.getOrDefault("")

    /** 收包时用的网卡：优先沿用正在监听的那块，避免二次枚举选到另一块。 */
    private fun currentInterface(): NetworkInterface? =
        NetworkInterface.getByName(interfaceName) ?: pickInterface()

    private fun ipv4Of(ni: NetworkInterface?): String? = ni?.inetAddresses?.toList()
        ?.filterIsInstance<Inet4Address>()
        ?.firstOrNull { it.isSiteLocalAddress }
        ?.hostAddress

    private fun typeName(type: Int): String = when (type) {
        TYPE_A -> "A"
        TYPE_AAAA -> "AAAA"
        TYPE_ANY -> "ANY"
        12 -> "PTR"
        33 -> "SRV"
        16 -> "TXT"
        TYPE_NSEC -> "NSEC"
        else -> "type$type"
    }

    // 由 RemoteServer 也要用（本机地址显示），所以是 internal 而不是 private
    internal companion object {
        const val HOST_NAME = "carremote"
        val GROUP: InetAddress = InetAddress.getByName("224.0.0.251")

        /**
         * 网卡打分。mDNS 用它挑「该把哪个地址发给手机」，设置页也要用它算「本机地址」——
         * 两处必须是同一套规则，否则会出现「页面显示 wlan0、域名解析到 ap0」这种自相矛盾，
         * 用户照着页面上的 IP 手输反而连不上。所以放在伴生对象里给两边共用。
         */
        fun scoreOf(name: String, ip: String): Int {
            var score = 0
            when {
                name.startsWith("ap") || name.contains("softap") -> score += 100
                name.startsWith("swlan") -> score += 95
                name.startsWith("wlan") -> score += 60
                name.startsWith("eth") -> score += 40
                name.startsWith("rndis") || name.startsWith("usb") -> score += 20
                else -> score += 10
            }
            // 热点网关几乎总是 x.x.x.1
            if (ip.endsWith(".1")) score += 25
            if (ip.startsWith("192.168.")) score += 10
            // iPhone 个人热点的固定网段
            if (ip.startsWith("172.20.10.")) score += 15
            return score
        }

        const val TYPE_A = 1
        const val TYPE_AAAA = 28
        const val TYPE_NSEC = 47
        const val TYPE_ANY = 255
        const val TTL_ANSWER = 120
        const val TTL_ANNOUNCE = 120

        /**
         * 重播间隔。必须明显小于 TTL（120 秒），这样对端缓存永远被提前刷新，
         * 不会掉到「必须重新查询」的境地 —— 而查询路径在热点下并不可靠。
         * 45 秒意味着每个 TTL 窗口内至少刷新两次，丢一个包也还有一次。
         */
        const val ANNOUNCE_INTERVAL_MS = 45_000L

        /** 宣告线程的检查周期。比间隔小一个数量级，保证 45 秒到点后几秒内就发出去。 */
        const val ANNOUNCE_TICK_MS = 5_000L

        /** receive 超时：既能当保活心跳，又能让 [stop] 之外的异常及时发现。 */
        const val RECV_TIMEOUT_MS = 1_000

        /** A 记录固定 16 字节：2 名字 + 2 类型 + 2 class + 4 TTL + 2 RDLEN + 4 地址。 */
        const val A_RECORD_BYTES = 16

        /** NSEC 固定 17 字节：12 头 + 2 名字指针 + 3 位图。 */
        const val NSEC_RECORD_BYTES = 17
    }
}
