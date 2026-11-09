package com.cloudmusic.car.remote

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject
import java.net.Inet4Address
import java.net.NetworkInterface

import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

data class OnlineDevice(
    val deviceId: String,
    val name: String,
    val remote: String,
)

data class RemoteUiState(
    val running: Boolean = false,
    val ip: String = "",
    val port: Int = RemoteConfig.PORT,
    /** v2.0 去掉了 PIN 和配对：连上就能控，所以界面上不再有 PIN 这一块。 */
    val connected: Int = 0,
    /** 当前在线的设备（临时列表，不落盘，断开即消失）。 */
    val online: List<OnlineDevice> = emptyList(),
    /** 同时在线设备上限，1~8，设置页可调。 */
    val maxClients: Int = 4,
    /** 资料库缓存上限（份），1~30，设置页可调。 */
    val cacheLimit: Int = 12,
    /** 当前实际缓存了几份、占多少字节。调上限时用来看「生效了没有」。 */
    val cacheEntries: Int = 0,
    val cacheBytes: Long = 0L,
    val error: String? = null,
    // mDNS 自检：设置页据此显示「网页遥控能不能用」
    val mdnsRunning: Boolean = false,
    val mdnsInterface: String = "",
    val mdnsIp: String = "",
    val mdnsQueries: Long = 0L,
    val mdnsAnswers: Long = 0L,
    /** 累计宣告次数。这个数不涨 = 域名保活断了，过一会必然解析失败。 */
    val mdnsAnnounces: Long = 0L,
    /** 距上次宣告的秒数。正常应该在 45 上下，> 60 就是有问题。 */
    val mdnsSinceAnnounceSec: Long = -1L,
    val mdnsLastQuery: String = "",
    val mdnsLocked: Boolean = false,
    val mdnsError: String = "",
    /** mDNS 网卡候选（多网卡时用来判断选错了没有）。 */
    val mdnsCandidates: String = "",
    /** 诊断快照，2.1 起加到设置页底部；为 null 表示还没采到。 */
    val health: RemoteHealth.Snapshot? = null,
) {
    /** 手机浏览器要打开的地址。 */
    val webUrl: String get() = "http://${RemoteConfig.MDNS_HOST}:$port"
}

/**
 * 遥控服务：WebSocket 服务端 + 协议分发 + 配对/认证 + 状态推送。
 *
 * 设计约束（任务书 §3.1）：遥控链路任何异常都不能影响原有播放。
 * 所以这里所有入口都包了 runCatching，服务起不来只在 UI 上提示，绝不抛出到调用方（Application / UI 线程）。
 */
object RemoteServer : WsServer.Listener {

    private class Client(
        val conn: WsServer.Conn,
        val connectedAt: Long = System.currentTimeMillis(),
    ) {
        var deviceId: String? = null
        var deviceName: String = ""
        var authed: Boolean = false
        var pairAttempts: Int = 0
        var lastSeenAt: Long = System.currentTimeMillis()
    }

    private val main = Handler(Looper.getMainLooper())
    private val seq = AtomicLong(0)
    private val clients = CopyOnWriteArrayList<Client>()

    private var appContext: Context? = null
    private var ws: WsServer? = null
    private var nsd: NsdRegistrar? = null
    private var mdns: MdnsResponder? = null
    private var scheduler: ScheduledExecutorService? = null

    private var lastStateKey = ""
    private var lastHealth: RemoteHealth.Snapshot? = null
    private var lastHealthAt = 0L

    /** 上次尝试重启 WS 监听的时间，防止端口被真占用时每 5 秒刷一次错误。 */
    private var lastRestartAt = 0L

    /** 写线程卡在一次 write 超过这个时间，判定慢客户端并断开。 */
    private const val WRITE_STALL_MS = 12_000L

    /** 诊断快照复用窗口：pushUi 调得很频繁，线程枚举不必每次都做。 */
    private const val HEALTH_INTERVAL_MS = 2_000L

    private val _ui = MutableStateFlow(RemoteUiState())
    val ui: StateFlow<RemoteUiState> = _ui.asStateFlow()

    // ---------- 生命周期 ----------

    @Synchronized
    fun start(context: Context, ensureForeground: Boolean = true): Boolean {
        val ctx = context.applicationContext
        appContext = ctx
        PlayerBridge.init(ctx)
        RemotePrefs.init(ctx)
        if (ws != null) return true
        return runCatching {
            // 同一个端口顺带服务网页遥控页面
            val server = WsServer(RemoteConfig.PORT, this) { head, socket ->
                RemoteWeb.handle(ctx, head, socket)
            }
            if (!server.start()) {
                _ui.value = _ui.value.copy(running = false, error = "端口 ${RemoteConfig.PORT} 被占用")
                return false
            }
            ws = server
            nsd = NsdRegistrar(ctx).also { it.register(RemoteConfig.PORT) }
            // 固定域名 carremote.local → 当前热点 IP，网页端不依赖会变的 IP
            mdns = MdnsResponder(ctx).also { it.start() }
            // 网络变化只作为「立刻刷新」的信号，真正的正确性仍靠 5 秒一次的轮询兜底
            NetworkMonitor.start(ctx) { onNetworkChanged() }
            // 熄屏时热点常被系统回收、亮屏再起：亮屏是「马上要来连」的最强信号，主动校验一次
            registerScreenReceiver(ctx)
            startScheduler()
            pushUi()
            // 挂前台壳，否则进程被系统冻结后连接会「连上但没人应答」
            if (ensureForeground) RemoteService.start(ctx)
            Log.i(RemoteConfig.TAG, "remote server started on ${localIpv4()}:${RemoteConfig.PORT}")
            true
        }.getOrElse {
            Log.w(RemoteConfig.TAG, "start failed", it)
            _ui.value = _ui.value.copy(running = false, error = "遥控服务启动失败：${it.message}")
            false
        }
    }

    @Synchronized
    fun stop() {
        unregisterScreenReceiver()
        runCatching { NetworkMonitor.stop() }
        runCatching { scheduler?.shutdownNow() }
        scheduler = null
        runCatching { ws?.stop() }
        ws = null
        runCatching { nsd?.unregister() }
        nsd = null
        runCatching { mdns?.stop() }
        mdns = null
        clients.forEach { runCatching { it.conn.close() } }
        clients.clear()
        pushUi()
        appContext?.let { RemoteService.stop(it) }
    }

    fun isRunning(): Boolean = ws != null

    /**
     * 同时在线设备上限。调小之后不会立刻踢掉多余的人，
     * 只对**新连接**生效 —— 手一滑把 4 调成 1 就把正在放的手机踢掉，太粗暴。
     */
    fun setMaxClients(value: Int) {
        RemotePrefs.setMaxClients(value)
        pushUi()
    }

    /**
     * 资料库缓存上限（份）。调小立即回收最久没用的那几份，
     * 不影响正在播放的队列 —— 播放队列在 PlayerHub 里，和这份缓存无关。
     */
    fun setCacheLimit(value: Int) {
        RemotePrefs.setCacheLimit(value)
        RemoteLibrary.trim()
        Log.i(RemoteConfig.TAG, "cache limit -> ${RemotePrefs.currentCacheLimit}")
        pushUi()
    }

    /** 清空资料库缓存。下次打开歌单会重新拉一遍，歌本身不受影响。 */
    fun clearLibraryCache() {
        RemoteLibrary.clearCache()
        Log.i(RemoteConfig.TAG, "library cache cleared")
        pushUi()
    }

    // ---------- WebSocket 回调（读线程） ----------

    override fun onOpen(c: WsServer.Conn) {
        // v2.0：连上即认证，不再有 PIN / Token / 配对表。
        // 唯一限制是同时在线台数，超了就关掉这一条（不踢已在线的）。
        val limit = RemotePrefs.currentMaxClients
        if (clients.size >= limit) {
            Log.i(RemoteConfig.TAG, "reject ${c.remote}: $limit clients already online")
            c.close()
            return
        }
        val client = Client(c)
        // 连上即认证：不做 PIN、不发 Token、不记设备表。
        // 手机端仍会发一条 pair/auth（老版本也在用），handlePair/handleAuth 只回确认，
        // 这样新旧手机端都能用，而车机这边没有任何握手门槛。
        client.authed = true
        clients.add(client)
        RemoteHealth.peakClients.accumulateAndGet(clients.size.toLong()) { a, b -> maxOf(a, b) }
        Log.i(RemoteConfig.TAG, "client ${c.remote} connected, total=${clients.size}")
        pushUi()
        // 一连上就推一份当前状态，手机端不用等自己发的那条 GET_STATE 回来才显示画面
        pushState("open")
    }

    override fun onClose(c: WsServer.Conn) {
        clients.removeAll { it.conn === c }
        Log.i(RemoteConfig.TAG, "client left, total=${clients.size}")
        pushUi()
    }

    override fun onText(c: WsServer.Conn, text: String) {
        val client = clients.firstOrNull { it.conn === c } ?: return
        client.lastSeenAt = System.currentTimeMillis()
        val json = runCatching { JSONObject(text) }.getOrNull()
        if (json == null) {
            c.sendText(errorMessage(null, RemoteConfig.ERR_INVALID_MESSAGE, "not a json object"))
            return
        }
        val version = json.optInt("v", 0)
        if (version != RemoteConfig.PROTOCOL_VERSION) {
            c.sendText(errorMessage(json.optString("id").ifEmpty { null }, RemoteConfig.ERR_UNSUPPORTED_VERSION, "protocol v$version unsupported"))
            return
        }
        val type = json.optString("type")
        val id = json.optString("id").ifEmpty { null }
        val data = json.optJSONObject("data") ?: JSONObject()
        if (type !in RemoteConfig.PRE_AUTH_TYPES && !client.authed) {
            c.sendText(errorMessage(id, RemoteConfig.ERR_NOT_AUTHENTICATED, "auth required"))
            return
        }
        when (type) {
            "ping" -> c.sendText(
                envelope(
                    "pong", null,
                    JSONObject().put("t", data.optLong("t")).put("serverTs", System.currentTimeMillis()),
                ),
            )
            "pair" -> handlePair(client, id, data)
            "auth" -> handleAuth(client, id, data)
            "command" -> handleCommand(client, id, data)
            else -> c.sendText(errorMessage(id, RemoteConfig.ERR_UNKNOWN_TYPE, "unknown type: $type"))
        }
    }

    // ---------- 握手（兼容层，不再校验任何东西） ----------

    /**
     * 老手机端连上会先发 pair（PIN 可能是空字符串），新手机端也可能还带着 Token 发 auth。
     * 车机这边一律直接回 ok —— 认证已经前移到「连上就是认证」。
     * 保留这两个分支是为了让车机端可以单独升级，不用等手机端一起换。
     */
    private fun handlePair(client: Client, id: String?, data: JSONObject) {
        client.deviceId = data.optString("deviceId").take(64)
        client.deviceName = data.optString("deviceName", "手机").take(40)
        client.authed = true
        client.conn.sendText(
            envelope(
                "pair_result", id,
                JSONObject()
                    .put("ok", true)
                    .put("autoApproved", true) // 老客户端看到这个就不会再要求输 PIN
                    .put("token", "open")      // 占位：老客户端会把它存起来，下次当 auth 发回来
                    .put("carId", carId())
                    .put("carName", carName())
                    .put("protocolVersion", RemoteConfig.PROTOCOL_VERSION),
            ),
        )
        pushUi()
        pushState("reply")
    }

    private fun handleAuth(client: Client, id: String?, data: JSONObject) {
        client.deviceId = data.optString("deviceId").take(64)
        client.deviceName = client.deviceName.ifEmpty { "手机" }
        client.authed = true
        client.conn.sendText(
            envelope(
                "auth_result", id,
                JSONObject()
                    .put("ok", true)
                    .put("carId", carId())
                    .put("carName", carName())
                    .put("protocolVersion", RemoteConfig.PROTOCOL_VERSION),
            ),
        )
        pushUi()
        pushState("reply")
    }

    // ---------- 指令 ----------

    private fun handleCommand(client: Client, id: String?, data: JSONObject) {
        val action = data.optString("action")
        val args = data.optJSONObject("args") ?: JSONObject()
        // 资料库相关要发网络请求，走异步分支，不能在这里同步等
        if (action in LIBRARY_ACTIONS) {
            handleLibrary(client, id, action, args)
            return
        }
        // 播放器只能在主线程访问，统一 post 过去；ack 在同一个 Runnable 里发，保证「执行 → ack」顺序
        main.post {
            val code = runCatching { execute(action, args) }.getOrElse {
                Log.w(RemoteConfig.TAG, "command $action failed", it)
                RemoteConfig.ERR_ACTION_FAILED
            }
            client.conn.sendText(
                envelope(
                    "ack", id,
                    JSONObject()
                        .put("ok", code == 0)
                        .put("action", action)
                        .apply {
                            if (code != 0) {
                                put("error", code)
                                put("message", errorName(code))
                            }
                        },
                ),
            )
            if (code == 0) pushState("event")
        }
    }

    /** @return 0 表示成功，否则为错误码。必须主线程调用。 */
    private fun execute(action: String, args: JSONObject): Int = when (action) {
        "play" -> { PlayerBridge.play(); 0 }
        "pause" -> { PlayerBridge.pause(); 0 }
        "next" -> { PlayerBridge.next(); 0 }
        "prev" -> { PlayerBridge.previous(); 0 }
        "seek" -> {
            if (!PlayerBridge.hasMedia()) {
                RemoteConfig.ERR_NOT_AVAILABLE
            } else {
                val position = args.optLong("positionMs", -1L)
                when {
                    position < 0 -> RemoteConfig.ERR_VALUE_OUT_OF_RANGE
                    PlayerBridge.seekTo(position) -> 0
                    else -> RemoteConfig.ERR_NOT_AVAILABLE
                }
            }
        }
        "setVolume" -> {
            if (PlayerBridge.volumeMax() <= 0) {
                RemoteConfig.ERR_NOT_AVAILABLE
            } else {
                val volume = args.optInt("volume", -1)
                when {
                    volume < 0 -> RemoteConfig.ERR_VALUE_OUT_OF_RANGE
                    PlayerBridge.setVolume(volume) -> 0
                    else -> RemoteConfig.ERR_VALUE_OUT_OF_RANGE
                }
            }
        }
        "getState" -> { pushState("reply"); 0 }
        // 立即广播一次当前歌词；同步执行，ack 由 handleCommand 统一回
        "getLyrics" -> {
            // 记录当前指纹，避免紧接着的 tick 又重复广播同一份歌词
            lastLyricsKey = lyricsFingerprint()
            broadcastLyrics()
            0
        }
        "like" -> if (PlayerBridge.toggleLike()) 0 else RemoteConfig.ERR_NOT_AVAILABLE
        else -> RemoteConfig.ERR_UNSUPPORTED_ACTION
    }

    // ---------- 资料库（我喜欢的音乐 / 云盘 / 我创建的歌单） ----------

    private fun handleLibrary(client: Client, id: String?, action: String, args: JSONObject) {
        val listId = args.optString("id")
        val offset = args.optInt("offset", 0)
        val index = args.optInt("index", 0)
        when (action) {
            "library" -> RemoteLibrary.library { result ->
                client.conn.sendText(envelope("library", id, result), OutQueue.Kind.BULK)
            }

            // 排行榜 + 推荐歌单，给手机页面最下面那两块用
            "catalog" -> RemoteLibrary.catalog { result ->
                client.conn.sendText(envelope("catalog", id, result), OutQueue.Kind.BULK)
            }

            "search" -> {
                val keyword = args.optString("q").trim()
                if (keyword.isEmpty()) {
                    client.conn.sendText(errorMessage(id, RemoteConfig.ERR_INVALID_MESSAGE, "q required"))
                    return
                }
                RemoteLibrary.search(keyword) { result ->
                    client.conn.sendText(envelope("tracks", id, result), OutQueue.Kind.BULK)
                }
            }

            "getTracks" -> {
                if (listId.isEmpty()) {
                    client.conn.sendText(errorMessage(id, RemoteConfig.ERR_INVALID_MESSAGE, "id required"))
                    return
                }
                RemoteLibrary.tracks(listId, offset) { result ->
                    client.conn.sendText(envelope("tracks", id, result), OutQueue.Kind.BULK)
                }
            }

            "playTracks" -> {
                if (listId.isEmpty()) {
                    client.conn.sendText(errorMessage(id, RemoteConfig.ERR_INVALID_MESSAGE, "id required"))
                    return
                }
                RemoteLibrary.play(listId, index) { ok, message ->
                    client.conn.sendText(
                        envelope(
                            "ack", id,
                            JSONObject()
                                .put("ok", ok)
                                .put("action", action)
                                .apply {
                                    if (!ok) {
                                        put("error", RemoteConfig.ERR_ACTION_FAILED)
                                        put("message", message)
                                    }
                                },
                        ),
                    )
                    if (ok) main.post { pushState("event") }
                }
            }

            "addToPlaylist" -> {
                val playlistId = args.optString("playlistId")
                if (playlistId.isEmpty()) {
                    client.conn.sendText(errorMessage(id, RemoteConfig.ERR_INVALID_MESSAGE, "playlistId required"))
                    return
                }
                // trackId 可选：空则交给 RemoteLibrary 取当前播放歌曲 id
                val trackId = args.optString("trackId").takeIf { it.isNotEmpty() }?.toLongOrNull()
                RemoteLibrary.addTrackToPlaylist(playlistId, trackId) { ok, message ->
                    client.conn.sendText(
                        envelope(
                            "ack", id,
                            JSONObject()
                                .put("ok", ok)
                                .put("action", action)
                                .apply {
                                    if (!ok) {
                                        put("error", RemoteConfig.ERR_ACTION_FAILED)
                                        put("message", message)
                                    }
                                },
                        ),
                    )
                }
            }
        }
    }

    // ---------- 状态推送 ----------

    /** 主线程构建快照并推送。定时 1s 一次；指令生效时立即推。 */
    private fun pushState(reason: String) {
        if (clients.none { it.authed }) return
        val run = Runnable {
            val snapshot = runCatching { PlayerBridge.snapshot() }.getOrNull() ?: return@Runnable
            // 歌词变化在 1Hz tick 里兜底检测一次：换歌 / 异步加载完成 / 清空都会触发广播
            maybePushLyrics()
            val volume = PlayerBridge.volume()
            val volumeMax = PlayerBridge.volumeMax()
            // 暂停且内容没变时，tick 不必重复推同样的东西
            val key = "${snapshot.playing}|${snapshot.title}|${snapshot.artist}|${snapshot.durationMs}|$volume|${snapshot.liked}"
            if (reason == "tick" && !snapshot.playing && key == lastStateKey) return@Runnable
            lastStateKey = key
            val data = JSONObject()
                .put("seq", seq.incrementAndGet())
                .put("playing", snapshot.playing)
                .put("title", snapshot.title)
                .put("artist", snapshot.artist)
                .put("positionMs", snapshot.positionMs)
                .put("durationMs", snapshot.durationMs)
                .put("liked", snapshot.liked)
                .put("reason", reason)
            if (snapshot.album.isNotEmpty()) data.put("album", snapshot.album)
            if (volume >= 0 && volumeMax > 0) {
                data.put("volume", volume)
                data.put("volumeMax", volumeMax)
            }
            val message = envelope("state", null, data)
            // 状态是「可合并」的：慢客户端积压时，只保留最新一条进度就够
            clients.filter { it.authed }.forEach { it.conn.sendText(message, OutQueue.Kind.STATE) }
        }
        if (Looper.myLooper() == Looper.getMainLooper()) run.run() else main.post(run)
    }

    // ---------- 歌词推送 ----------

    /** 上次广播的歌词指纹，用来在 1Hz tick 里避免每秒重复发同一份歌词。 */
    private var lastLyricsKey = ""

    /**
     * 歌词内容指纹：当前歌曲 id + 行数 + 首行文字。
     * 换歌、异步加载完成、清空都会改变它，足以触发一次广播，又足够轻量。
     */
    private fun lyricsFingerprint(): String {
        val lines = PlayerBridge.lyrics()
        val trackId = PlayerBridge.currentTrackId()?.toString().orEmpty()
        return "$trackId|${lines.size}|${lines.firstOrNull()?.text.orEmpty()}"
    }

    /** 主线程构造 lyrics.data：无歌时 trackId 空串；无歌词时 lines=[]；ok 恒为 true。 */
    private fun lyricsMessageData(): JSONObject {
        val lines = PlayerBridge.lyrics()
        val array = JSONArray()
        lines.forEach { line ->
            array.put(
                JSONObject()
                    .put("t", line.timeMs.toInt())
                    .put("text", line.text)
                    .put("trans", line.translation.orEmpty()),
            )
        }
        return JSONObject()
            .put("ok", true)
            .put("trackId", PlayerBridge.currentTrackId()?.toString().orEmpty())
            .put("lines", array)
    }

    /** 主线程：立即向所有 authed 客户端广播一条 lyrics 消息（歌词较大，走 BULK）。 */
    private fun broadcastLyrics() {
        if (clients.none { it.authed }) return
        val message = envelope("lyrics", null, lyricsMessageData())
        clients.filter { it.authed }.forEach { it.conn.sendText(message, OutQueue.Kind.BULK) }
    }

    /** 主线程：歌词内容变化时才广播一次，避免 1Hz tick 每秒重复广播。 */
    private fun maybePushLyrics() {
        if (clients.none { it.authed }) return
        val key = lyricsFingerprint()
        if (key == lastLyricsKey) return
        lastLyricsKey = key
        broadcastLyrics()
    }

    // ---------- 内部 ----------

    private fun startScheduler() {
        val executor = Executors.newScheduledThreadPool(2) { r ->
            Thread(r, "remote-timer").apply { isDaemon = true }
        }
        executor.scheduleWithFixedDelay({ runCatching { pushState("tick") } }, 1, 1, TimeUnit.SECONDS)
        executor.scheduleWithFixedDelay({ runCatching { watchdog() } }, 5, 5, TimeUnit.SECONDS)
        scheduler = executor
    }

    private fun watchdog() {
        val now = System.currentTimeMillis()
        // 兜底：accept 线程万一因为 IOException 退出（端口被抢、网卡瞬间消失），
        // 监听 socket 还在、running 却没人推进，表现为「能连上端口但握不上手」。
        // 30 秒内只试一次，避免端口真被别的进程占着时每 5 秒刷一条错误。
        val server = ws
        if (server != null && !server.isRunning() && now - lastRestartAt > 30_000L) {
            lastRestartAt = now
            RemoteHealth.error("WS 监听意外停止，自动重启", null)
            runCatching { server.start() }
        }
        // 热点重连后 IP 会变，界面上的地址和 mDNS 状态要跟着刷新（顺带刷新查询计数）
        if (ws != null) pushUi()
        // 换了 IP 就重新宣告一次，名字不变、答案跟着变
        mdns?.refresh()
        clients.forEach { client ->
            // 慢客户端：写线程卡在一次 write 里超过 12 秒，说明对端不读了。
            // 不主动断开的话，这条连接的写线程和队列会一直占着。
            val stalled = client.conn.writeStalledMs()
            if (stalled > WRITE_STALL_MS) {
                RemoteHealth.writeStalls.incrementAndGet()
                RemoteHealth.slowClientCloses.incrementAndGet()
                Log.w(RemoteConfig.TAG, "slow client ${client.conn.remote} write stalled ${stalled}ms, closing")
                client.conn.close()
                return@forEach
            }
            // authed 现在连上就是 true，authTimedOut 只在极端情况下短暂为真，留着当保险
            val authTimedOut = !client.authed && now - client.connectedAt > RemoteConfig.AUTH_TIMEOUT_MS
            val idleTimedOut = now - client.lastSeenAt > 90_000L
            if (authTimedOut || idleTimedOut) {
                Log.i(RemoteConfig.TAG, "closing client (auth=$authTimedOut idle=$idleTimedOut)")
                client.conn.close()
            }
        }
    }

    /** 网络变化（热点重建 / Wi-Fi 切换）：立刻重建 mDNS 监听并刷新界面。 */
    fun onNetworkChanged() {
        RemoteHealth.networkChanges.incrementAndGet()
        RemoteHealth.log("网络变化，重建 mDNS")
        runCatching { mdns?.forceRebuild() }
        ws?.let { runCatching { pushUi() } }
        // IP 变了就顺手把新地址推给手机，省得它们等下一轮 state
        runCatching { pushState("network") }
    }

    // ---------- 熄屏 / 亮屏 ----------

    private var screenReceiver: BroadcastReceiver? = null

    /**
     * 为什么需要它：车机熄屏后热点网卡经常被系统回收（省电策略直接把 AP 关掉），
     * 亮屏再重新起来时，网卡可能换了名字、换了网段，甚至内核把之前的组播加入关系清了，
     * 而我们的 mDNS 线程还「活着」—— socket 没报错、但已经收不到也发不出东西。
     * 5 秒轮询的宣告停滞检测能兜住（3 个间隔 = 135 秒），但用户亮屏就想连，等不了两分钟。
     *
     * 亮屏时主动验一次：WS 监听没了就重启，mDNS 太久没成功宣告就重建。
     */
    private fun registerScreenReceiver(ctx: Context) {
        if (screenReceiver != null) return
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    Intent.ACTION_SCREEN_ON -> onScreenOn()
                    Intent.ACTION_SCREEN_OFF -> RemoteHealth.log("熄屏")
                }
            }
        }
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        runCatching {
            // Android 13 起运行时注册接收器要显式声明导出行为。亮屏/熄屏是系统广播，
            // 用 NOT_EXPORTED 就够了 —— 我们不需要任何第三方应用往这里发东西。
            if (android.os.Build.VERSION.SDK_INT >= 33) {
                ctx.registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
            } else {
                @Suppress("UnspecifiedRegisterReceiverFlag")
                ctx.registerReceiver(receiver, filter)
            }
        }
            .onSuccess { screenReceiver = receiver }
            .onFailure { RemoteHealth.error("注册亮屏广播失败", it) }
    }

    private fun unregisterScreenReceiver() {
        val receiver = screenReceiver ?: return
        screenReceiver = null
        appContext?.let { runCatching { it.unregisterReceiver(receiver) } }
    }

    private fun onScreenOn() {
        if (ws == null) return
        RemoteHealth.log("亮屏，校验服务与 mDNS")
        val server = ws
        if (server != null && !server.isRunning()) {
            // accept 线程如果因为 IOException 退出过，socket 还在但没人 accept，
            // 表现为「端口能连上但永远握不上手」。这里重开一次。
            RemoteHealth.error("亮屏发现 WS 监听已停，重启监听", null)
            runCatching { server.start() }
        }
        val responder = mdns
        if (responder != null) {
            if (responder.needsRebuild()) responder.forceRebuild() else responder.refresh()
        }
        runCatching { pushUi() }
        runCatching { pushState("screen") }
    }

    private fun pushUi() {
        val responder = mdns
        val online = clients.map { OnlineDevice(it.deviceId.orEmpty(), it.deviceName, it.conn.remote) }
        val cache = RemoteLibrary.stats()
        _ui.value = RemoteUiState(
            running = ws != null,
            ip = localIpv4(),
            port = RemoteConfig.PORT,
            connected = clients.size,
            online = online,
            maxClients = RemotePrefs.currentMaxClients,
            cacheLimit = cache.limit,
            cacheEntries = cache.entries,
            cacheBytes = cache.bytes.toLong(),
            error = null,
            mdnsRunning = responder?.isRunning() == true,
            mdnsInterface = responder?.interfaceName.orEmpty(),
            mdnsIp = responder?.hostIp.orEmpty(),
            mdnsQueries = responder?.queryCount ?: 0L,
            mdnsAnswers = responder?.answerCount ?: 0L,
            mdnsAnnounces = responder?.announceCount ?: 0L,
            mdnsSinceAnnounceSec = responder?.sinceAnnounceMs?.takeIf { it >= 0 }?.div(1000) ?: -1L,
            mdnsLastQuery = responder?.lastQuery.orEmpty(),
            mdnsLocked = responder?.locked == true,
            mdnsError = responder?.lastError.orEmpty(),
            mdnsCandidates = responder?.candidates.orEmpty(),
            health = healthSnapshot(),
        )
    }

    /**
     * 诊断快照 2 秒内复用。`Thread.getAllStackTraces()` 不便宜，
     * 而 pushUi 在连接/断开/网络变化时都会调，没必要每次都重新枚举线程。
     */
    private fun healthSnapshot(): RemoteHealth.Snapshot {
        val now = System.currentTimeMillis()
        val cached = lastHealth
        if (cached != null && now - lastHealthAt < HEALTH_INTERVAL_MS) return cached
        val snapshot = RemoteHealth.snapshot(
            clients = clients.size,
            pendingFrames = ws?.pendingFrames() ?: 0,
            pendingBytes = ws?.pendingBytes() ?: 0L,
        )
        lastHealth = snapshot
        lastHealthAt = now
        return snapshot
    }

    /**
     * 取本机局域网 IPv4。开热点时地址经常变，优先 wlan/ap 网卡，
     * 避免双网卡（4G + Wi-Fi）时取到手机流量的那张网卡。
     */
    /**
     * 页面上显示的「本机地址」，也是用户手输 IP 的唯一来源，所以必须和 mDNS 答案**一致**。
     *
     * v2.0 这里写的是「取第一个名字以 wlan 或 ap 开头的」，但枚举顺序不保证 ——
     * 车机常常一边连着家里/手机 WiFi、一边开热点，两块网卡都命中这个条件，
     * 于是页面经常显示 wlan0 的地址，手机照着输进去连不上（手机在热点网段，不在 wlan0 网段）。
     *
     * 现在优先用 mDNS 已经选定的那块热点网卡的地址，其次才按同一套打分规则自己算。
     */
    private fun localIpv4(): String {
        mdns?.hostIp?.takeIf { it.isNotEmpty() }?.let { return it }
        return runCatching {
            NetworkInterface.getNetworkInterfaces()?.asSequence()
                ?.filter { it.isUp && !it.isLoopback && !it.isVirtual }
                ?.flatMap { ni -> ni.inetAddresses.asSequence().map { ni.name to it } }
                ?.filter { (_, addr) -> addr is Inet4Address && addr.isSiteLocalAddress }
                ?.map { (name, addr) -> name to addr.hostAddress.orEmpty() }
                ?.filter { (_, ip) -> ip.isNotEmpty() }
                ?.maxByOrNull { (name, ip) -> MdnsResponder.scoreOf(name, ip) }
                ?.second
                .orEmpty()
        }.getOrDefault("")
    }

    private fun carId(): String = "cloudmusic-car"

    private fun carName(): String = android.os.Build.MODEL?.takeIf(String::isNotBlank) ?: "车机"

    private fun envelope(type: String, id: String?, data: JSONObject): String {
        val json = JSONObject()
            .put("v", RemoteConfig.PROTOCOL_VERSION)
            .put("type", type)
            .put("ts", System.currentTimeMillis())
            .put("data", data)
        if (id != null) json.put("id", id)
        return json.toString()
    }

    private fun pairResult(id: String?, ok: Boolean, error: Int, message: String): String =
        envelope(
            "pair_result", id,
            JSONObject()
                .put("ok", ok)
                .put("protocolVersion", RemoteConfig.PROTOCOL_VERSION)
                .put("error", error)
                .put("message", message),
        )

    private fun errorMessage(id: String?, code: Int, message: String): String =
        envelope(
            "error", id,
            JSONObject().put("error", code).put("message", message),
        )

    private fun errorName(code: Int): String = when (code) {
        RemoteConfig.ERR_INVALID_MESSAGE -> "INVALID_MESSAGE"
        RemoteConfig.ERR_UNSUPPORTED_VERSION -> "UNSUPPORTED_VERSION"
        RemoteConfig.ERR_UNKNOWN_TYPE -> "UNKNOWN_TYPE"
        RemoteConfig.ERR_NOT_AUTHENTICATED -> "NOT_AUTHENTICATED"
        RemoteConfig.ERR_PIN_INVALID -> "PIN_INVALID"
        RemoteConfig.ERR_PIN_EXPIRED -> "PIN_EXPIRED"
        RemoteConfig.ERR_PIN_ATTEMPTS_EXCEEDED -> "PIN_ATTEMPTS_EXCEEDED"
        RemoteConfig.ERR_TOKEN_INVALID -> "TOKEN_INVALID"
        RemoteConfig.ERR_DEVICE_REVOKED -> "DEVICE_REVOKED"
        RemoteConfig.ERR_DEVICE_LIMIT_REACHED -> "DEVICE_LIMIT_REACHED"
        RemoteConfig.ERR_UNSUPPORTED_ACTION -> "UNSUPPORTED_ACTION"
        RemoteConfig.ERR_ACTION_FAILED -> "ACTION_FAILED"
        RemoteConfig.ERR_VALUE_OUT_OF_RANGE -> "VALUE_OUT_OF_RANGE"
        RemoteConfig.ERR_NOT_AVAILABLE -> "NOT_AVAILABLE"
        RemoteConfig.ERR_RATE_LIMITED -> "RATE_LIMITED"
        else -> "INTERNAL"
    }

    /** 需要发网络请求、走异步 ack 的指令。 */
    private val LIBRARY_ACTIONS = setOf("library", "catalog", "search", "getTracks", "playTracks", "addToPlaylist")
}
