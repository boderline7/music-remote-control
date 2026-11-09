package com.carremote.client

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

/**
 * 连接状态机：连接 → 配对 / 认证 → 就绪 → 断线重连。
 *
 * 有 Token 就直接 auth，只有收到 2004 / 2005 才回到需要 PIN 的状态，
 * 避免每次重连都要求用户去车上看 PIN。
 */
object RemoteClient {

    sealed interface Conn {
        data object Idle : Conn
        data class Connecting(val host: String, val port: Int) : Conn
        /** 需要用户输入车机上显示的 PIN。 */
        data class NeedPin(val message: String? = null) : Conn
        data class Ready(val carName: String) : Conn
        data class Failed(val message: String) : Conn
    }

    data class Now(
        val playing: Boolean = false,
        val title: String = "",
        val artist: String = "",
        val album: String = "",
        val positionMs: Long = 0L,
        val durationMs: Long = 0L,
        val volume: Int = -1,
        val volumeMax: Int = 0,
        val liked: Boolean = false,
    )

    private const val TAG = "CarRemote"

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val http = OkHttpClient.Builder()
        .pingInterval(20, TimeUnit.SECONDS)
        .connectTimeout(6, TimeUnit.SECONDS)
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val _state = MutableStateFlow<Conn>(Conn.Idle)
    val state: StateFlow<Conn> = _state.asStateFlow()

    private val _now = MutableStateFlow(Now())
    val now: StateFlow<Now> = _now.asStateFlow()

    /** 车机下发的歌词行；t 为毫秒，trans 是翻译（可空）。 */
    data class LyricLine(val t: Long, val text: String, val trans: String)

    private val _lyrics = MutableStateFlow<List<LyricLine>>(emptyList())
    val lyrics: StateFlow<List<LyricLine>> = _lyrics.asStateFlow()

    private val _hint = MutableStateFlow("")
    val hint: StateFlow<String> = _hint.asStateFlow()

    // ---------- 资料库 ----------

    /** 资料库入口：我喜欢的音乐 / 音乐云盘 / 我创建的歌单。 */
    data class LibEntry(val id: String, val name: String, val count: Int)

    data class LibData(
        val loading: Boolean = false,
        val liked: LibEntry? = null,
        val cloud: LibEntry? = null,
        val playlists: List<LibEntry> = emptyList(),
        val error: String? = null,
    )

    data class TrackItem(
        val index: Int,
        val name: String,
        val artist: String,
        val durationMs: Long,
        val playable: Boolean,
    )

    data class TrackList(
        val id: String = "",
        val keyword: String = "",
        val items: List<TrackItem> = emptyList(),
        val total: Int = 0,
        val loading: Boolean = false,
        val error: String? = null,
    )

    /** 排行榜 + 推荐歌单。id 已带 `top:` / `rec:` 前缀，直接当列表 id 用。 */
    data class Catalog(
        val loading: Boolean = false,
        val top: List<LibEntry> = emptyList(),
        val recommend: List<LibEntry> = emptyList(),
        val error: String? = null,
    )

    private val _library = MutableStateFlow(LibData())
    val library: StateFlow<LibData> = _library.asStateFlow()

    private val _catalog = MutableStateFlow(Catalog())
    val catalog: StateFlow<Catalog> = _catalog.asStateFlow()

    private val _trackList = MutableStateFlow(TrackList())
    val trackList: StateFlow<TrackList> = _trackList.asStateFlow()

    /** 搜索结果单独一份，免得和资料库列表互相冲掉。 */
    private val _search = MutableStateFlow(TrackList())
    val search: StateFlow<TrackList> = _search.asStateFlow()

    fun requestLibrary() {
        _library.value = _library.value.copy(loading = true, error = null)
        send("command", JSONObject().put("action", Protocol.ACTION_LIBRARY))
    }

    fun requestCatalog() {
        _catalog.value = Catalog(loading = true)
        send("command", JSONObject().put("action", Protocol.ACTION_CATALOG))
    }

    fun searchTracks(keyword: String) {
        val q = keyword.trim()
        if (q.isEmpty()) return
        val id = "search:$q"
        _search.value = TrackList(id = id, keyword = q, loading = true)
        send(
            "command",
            JSONObject().put("action", Protocol.ACTION_SEARCH).put("args", JSONObject().put("q", q)),
        )
        timeoutGuard(15_000, { _search.value.id == id && _search.value.loading }) {
            _search.value = _search.value.copy(loading = false, error = it)
        }
    }

    /** 车机迟迟不回包时收掉 loading，否则界面会一直转圈，看起来像卡死。 */
    private fun timeoutGuard(timeoutMs: Long, pending: () -> Boolean, fail: (String) -> Unit) {
        scope.launch {
            delay(timeoutMs)
            if (pending()) fail("车机没有响应，请重试")
        }
    }

    fun requestTracks(id: String, offset: Int = 0) {
        // 搜索结果的下一页也走同一条指令，但要落在 search 这条流上，不能覆盖资料库列表
        val isSearch = id.startsWith("search:")
        if (isSearch) {
            _search.value = if (offset == 0) {
                TrackList(id = id, keyword = _search.value.keyword, loading = true)
            } else {
                _search.value.copy(loading = true, error = null)
            }
        } else {
            _trackList.value = if (offset == 0) {
                TrackList(id = id, loading = true)
            } else {
                _trackList.value.copy(loading = true, error = null)
            }
        }
        send(
            "command",
            JSONObject().put("action", Protocol.ACTION_GET_TRACKS)
                .put("args", JSONObject().put("id", id).put("offset", offset)),
        )
        timeoutGuard(20_000, { if (isSearch) _search.value.loading else _trackList.value.loading }) { msg ->
            if (isSearch) _search.value = _search.value.copy(loading = false, error = msg)
            else _trackList.value = _trackList.value.copy(loading = false, error = msg)
        }
    }

    fun playFromLibrary(id: String, index: Int) {
        send(
            "command",
            JSONObject().put("action", Protocol.ACTION_PLAY_TRACKS)
                .put("args", JSONObject().put("id", id).put("index", index)),
        )
    }

    fun like() {
        command(Protocol.ACTION_LIKE)
    }

    private var socket: WebSocket? = null
    private var host: String = ""
    private var port: Int = Protocol.PORT
    private var carName: String = ""
    private var wantConnection = false
    private var retryDelayMs = 1_000L
    private var retryJob: Job? = null
    private val requestIds = AtomicInteger(0)
    private var lastSeq = -1L

    /**
     * 连接代次。每次真正开新 socket 都 +1，旧 socket 的回调（onOpen/onFailure/onClosed）
     * 拿着过期代次直接丢弃。
     *
     * 这是「刚启动时状态反复跳」的根治点：启动会先连上次的地址，紧接着网段扫描
     * 又跳出一个地址再连一次，上一版两条 socket 同时活着、还共用一个 socket 字段，
     * 谁先抖一下状态就被改成 Failed，界面在「连上 / 断开」之间弹来弹去。
     */
    private val gen = AtomicInteger(0)
    private var settleJob: Job? = null
    private var beatJob: Job? = null
    private var dropCount = 0

    /** 连续失败次数。到阈值就重新找一遍车机（IP 可能变了）。连上即清零。 */
    private var failStreak = 0
    private var recoverJob: Job? = null

    /**
     * 应用层心跳。
     *
     * 车机端有个 90 秒的「一直没说话就断开」兜底，而暂停播放时状态推送会被节流成一条不发，
     * 于是光靠 OkHttp 的协议级 ping（对端 WS 层自己回 pong，不会进到业务计时）撑不住 ——
     * 暂停放着不动一分半就会被车机踢掉，然后重连。这里每 25 秒发一条业务 ping 顶住。
     */
    private fun startHeartbeat(g: Int) {
        beatJob?.cancel()
        beatJob = scope.launch {
            while (true) {
                delay(HEARTBEAT_MS)
                if (g != gen.get()) return@launch
                val ws = socket ?: return@launch
                val ok = runCatching {
                    ws.send(
                        JSONObject()
                            .put("v", Protocol.VERSION)
                            .put("type", "ping")
                            .put("id", "hb-${requestIds.incrementAndGet()}")
                            .put("ts", System.currentTimeMillis())
                            .toString(),
                    )
                }.getOrDefault(false)
                if (!ok) return@launch
            }
        }
    }

    fun connect(host: String, port: Int = Protocol.PORT) {
        val target = host.trim()
        if (target.isEmpty()) return
        // 幂等：同一个目标已经在连 / 已经连上，就不要再开一条。
        // 启动时「先连上次地址 → 扫描完再连一次」会在这里被拦住。
        val same = wantConnection && socket != null && target == this.host && port == this.port
        if (same && _state.value !is Conn.Failed) return
        // 换了目标（点了另一台车机）：明确回到「连接中」，别把上一台的面板挂在那儿
        val switching = target != this.host || port != this.port
        this.host = target
        this.port = port
        if (switching) _state.value = Conn.Connecting(target, port)
        wantConnection = true
        retryDelayMs = 1_000L
        dropCount = 0
        retryJob?.cancel()
        retryJob = null
        openSocket()
    }

    fun disconnect() {
        wantConnection = false
        retryJob?.cancel()
        retryJob = null
        settleJob?.cancel()
        settleJob = null
        beatJob?.cancel()
        beatJob = null
        gen.incrementAndGet() // 让所有在途回调失效
        runCatching { socket?.close(1000, "bye") }
        socket = null
        lastSeq = -1
        dropCount = 0
        _state.value = Conn.Idle
        _hint.value = ""
    }

    /**
     * 系统回调：网回来了（切 Wi-Fi、热点重连、飞行模式关掉）。
     *
     * 这时退避可能已经涨到 15 秒，干等着就是「重连中」卡十几秒。
     * 把退避打回 1 秒并立刻试一次；如果手上这条 socket 还活着且已经在用，就别碰它。
     */
    fun onNetworkAvailable() {
        if (!wantConnection) return
        if (socket != null && _state.value is Conn.Ready) return
        retryJob?.cancel()
        retryJob = null
        retryDelayMs = 1_000L
        Log.i(TAG, "network available, reconnect now")
        openSocket()
    }

    /**
     * 系统回调：网断了。
     *
     * 主动断掉，不等 OkHttp 自己超时（它发现链路死掉可能要几十秒）。
     * 状态仍走 [fail] 的「已连接就只变提示」那条路，不把遥控面板收走。
     */
    fun onNetworkLost() {
        if (!wantConnection) return
        runCatching { socket?.cancel() }
        socket = null
        fail("网络已断开，正在重连…")
    }

    fun pair(pin: String) {
        val data = JSONObject()
            .put("pin", pin.trim())
            .put("deviceId", Prefs.deviceId)
            .put("deviceName", deviceName())
            .put("clientVersion", "1.0.0")
        send("pair", data)
    }

    /**
     * 免 PIN 自动配对：车机开着「免 PIN 自动通过」时，这次请求直接成功。
     * 车机要求 PIN 时会回 ERR_PIN_INVALID，UI 再让用户手输。
     */
    fun autoPair() {
        pair("")
    }

    fun command(action: String, args: JSONObject? = null) {
        val data = JSONObject().put("action", action)
        if (args != null) data.put("args", args)
        send("command", data)
    }

    fun seek(positionMs: Long) {
        command(Protocol.ACTION_SEEK, JSONObject().put("positionMs", positionMs))
    }

    fun setVolume(volume: Int) {
        command(Protocol.ACTION_SET_VOLUME, JSONObject().put("volume", volume))
    }

    fun requestLyrics() {
        command(Protocol.ACTION_GET_LYRICS)
    }

    /** 把当前播放的歌（或指定 trackId）加入某个自建歌单。 */
    fun addToPlaylist(playlistId: String, trackId: String? = null) {
        val args = JSONObject().put("playlistId", playlistId)
        if (!trackId.isNullOrEmpty()) args.put("trackId", trackId)
        command(Protocol.ACTION_ADD_TO_PLAYLIST, args)
    }

    // ---------- 内部 ----------

    private fun deviceName(): String = runCatching { android.os.Build.MODEL }.getOrDefault("手机")

    private fun openSocket() {
        val g = gen.incrementAndGet()
        // 旧连接必须先关掉，否则两条 socket 同时在跑，谁断一下都会把状态打成 Failed
        val old = socket
        socket = null
        settleJob?.cancel()
        settleJob = null
        beatJob?.cancel()
        beatJob = null
        if (old != null) runCatching { old.cancel() }

        // 已经在遥控面板上（断线重连）时不要把状态打回 Connecting —— 那会让整个面板
        // 闪回连接页，看起来就是「一下连开一下断开」。重连期间保持在 Ready，只换提示文字。
        if (_state.value !is Conn.Ready) {
            _state.value = Conn.Connecting(host, port)
            _hint.value = "正在连接 $host…"
        }
        val request = Request.Builder().url("ws://$host:$port/").build()
        runCatching { http.newWebSocket(request, listener(g)) }
            .onFailure { if (g == gen.get()) fail("无法建立连接：${it.message}") }
    }

    private fun send(type: String, data: JSONObject) {
        val id = "r${requestIds.incrementAndGet()}"
        val message = JSONObject()
            .put("v", Protocol.VERSION)
            .put("type", type)
            .put("id", id)
            .put("ts", System.currentTimeMillis())
            .put("data", data)
            .toString()
        val ws = socket
        if (ws == null || !ws.send(message)) {
            _hint.value = "未连接，指令未发出"
        }
    }

    /**
     * 每条连接一个 listener，闭包里带自己的代次 [g]。
     * 代次过期的回调直接丢弃 —— 这是「旧 socket 的 onFailure 把新连接状态打掉」的解药。
     */
    private fun listener(g: Int) = object : WebSocketListener() {

        private fun alive(): Boolean = g == gen.get()

        override fun onOpen(webSocket: WebSocket, response: Response) {
            if (!alive()) {
                runCatching { webSocket.close(1000, "stale") }
                return
            }
            socket = webSocket
            retryDelayMs = 1_000L
            failStreak = 0
            recoverJob?.cancel()
            recoverJob = null
            startHeartbeat(g)
            val token = Prefs.token
            if (token.isNullOrEmpty()) {
                // 不切到 NeedPin：配对过程还在 Connecting 里，等真正确认了再一次性跳过去，
                // 否则「Connecting → NeedPin → Ready」三段状态会连着闪。
                _hint.value = "正在自动配对…"
                autoPair()
            } else {
                _hint.value = "正在验证授权…"
                webSocket.send(
                    JSONObject()
                        .put("v", Protocol.VERSION)
                        .put("type", "auth")
                        .put("id", "auth-${requestIds.incrementAndGet()}")
                        .put("ts", System.currentTimeMillis())
                        .put(
                            "data",
                            JSONObject()
                                .put("token", token)
                                .put("deviceId", Prefs.deviceId)
                                .put("clientVersion", "1.0.0"),
                        )
                        .toString(),
                )
            }
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            if (!alive()) return
            runCatching { handle(text) }
                .onFailure { Log.w(TAG, "bad message: $text", it) }
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            if (!alive()) return
            socket = null
            fail("连接断开：${t.message ?: t.javaClass.simpleName}")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            if (!alive()) return
            socket = null
            fail("连接已关闭（$code）")
        }
    }

    private fun handle(text: String) {
        val msg = JSONObject(text)
        val type = msg.optString("type")
        val data = msg.optJSONObject("data") ?: JSONObject()
        when (type) {
            "pair_result" -> {
                if (data.optBoolean("ok")) {
                    Prefs.token = data.optString("token")
                    carName = data.optString("carName", "车机")
                    Prefs.lastName = carName
                    onReady()
                } else {
                    val code = data.optInt("error")
                    _state.value = Conn.NeedPin(pairError(code, data.optString("message")))
                    _hint.value = pairError(code, data.optString("message"))
                }
            }

            "auth_result" -> {
                if (data.optBoolean("ok")) {
                    carName = data.optString("carName", "车机")
                    Prefs.lastName = carName
                    onReady()
                } else {
                    // Token 失效或被撤销：清掉，先试一次免 PIN 自动配对
                    Prefs.clearToken()
                    _state.value = Conn.NeedPin("授权已失效，正在重新配对…")
                    autoPair()
                }
            }

            "state" -> {
                val seq = data.optLong("seq", 0L)
                if (seq <= lastSeq) return
                lastSeq = seq
                _now.value = Now(
                    playing = data.optBoolean("playing"),
                    title = data.optString("title"),
                    artist = data.optString("artist"),
                    album = data.optString("album"),
                    positionMs = data.optLong("positionMs"),
                    durationMs = data.optLong("durationMs"),
                    volume = if (data.has("volume")) data.optInt("volume") else -1,
                    volumeMax = data.optInt("volumeMax"),
                    liked = data.optBoolean("liked"),
                )
                // 能收到车机的状态推送，说明双向通道确实通了 —— 这时才真正进遥控面板，
                // 不用再等去抖定时器。
                commitReady()
            }

            "ack" -> {
                val ok = data.optBoolean("ok", true)
                _hint.value = if (ok) {
                    if (data.optString("action") == Protocol.ACTION_ADD_TO_PLAYLIST) "已添加到歌单" else ""
                } else {
                    val code = data.optInt("error")
                    "指令失败：${data.optString("message", "错误 $code")}"
                }
            }

            "error" -> {
                val code = data.optInt("error")
                val msg = data.optString("message", "错误 $code")
                _hint.value = msg
                // 有列表还在转圈就一起收掉，别让界面停在「加载中」
                if (_search.value.loading) _search.value = _search.value.copy(loading = false, error = msg)
                if (_trackList.value.loading) _trackList.value = _trackList.value.copy(loading = false, error = msg)
                if (_library.value.loading) _library.value = _library.value.copy(loading = false, error = msg)
            }

            "revoked" -> {
                Prefs.clearToken()
                _state.value = Conn.NeedPin("车机已撤销授权，正在重新配对…")
                autoPair()
            }

            "pong" -> Unit

            "lyrics" -> {
                val arr = data.optJSONArray("lines") ?: org.json.JSONArray()
                val lines = ArrayList<LyricLine>(arr.length())
                for (i in 0 until arr.length()) {
                    val o = arr.optJSONObject(i) ?: continue
                    lines.add(LyricLine(o.optLong("t"), o.optString("text"), o.optString("trans")))
                }
                _lyrics.value = lines
            }

            "library" -> {
                if (data.optBoolean("ok")) {
                    fun entry(key: String): LibEntry? = data.optJSONObject(key)?.let {
                        LibEntry(it.optString("id"), it.optString("name"), it.optInt("count"))
                    }
                    _library.value = LibData(
                        loading = false,
                        liked = entry("liked"),
                        cloud = entry("cloud"),
                        playlists = entries(data, "playlists"),
                    )
                } else {
                    _library.value = _library.value.copy(
                        loading = false,
                        error = data.optString("message", "资料库读取失败"),
                    )
                }
            }

            "catalog" -> {
                if (data.optBoolean("ok")) {
                    _catalog.value = Catalog(
                        loading = false,
                        top = entries(data, "top"),
                        recommend = entries(data, "recommend"),
                    )
                } else {
                    _catalog.value = _catalog.value.copy(
                        loading = false,
                        error = data.optString("message", "榜单读取失败"),
                    )
                }
            }

            "tracks" -> {
                val id = data.optString("id")
                // 搜索结果的 id 带 search: 前缀，和资料库列表各走各的
                val isSearch = id.startsWith("search:")
                val current = if (isSearch) _search.value else _trackList.value
                // 迟到的上一页回包直接丢掉
                if (current.id.isNotEmpty() && id != current.id) return
                val next = if (data.optBoolean("ok")) {
                    val offset = data.optInt("offset", 0)
                    val page = mutableListOf<TrackItem>()
                    data.optJSONArray("tracks")?.let { arr ->
                        for (i in 0 until arr.length()) {
                            val o = arr.optJSONObject(i) ?: continue
                            page.add(
                                TrackItem(
                                    index = o.optInt("index", i),
                                    name = o.optString("name"),
                                    artist = o.optString("artist"),
                                    durationMs = o.optLong("durationMs"),
                                    playable = o.optBoolean("playable", true),
                                ),
                            )
                        }
                    }
                    val items = if (offset == 0) page else current.items + page
                    current.copy(
                        items = items,
                        total = data.optInt("total", items.size),
                        loading = false,
                        error = null,
                    )
                } else {
                    current.copy(loading = false, error = data.optString("message", "读取失败"))
                }
                if (isSearch) _search.value = next else _trackList.value = next
            }
        }
    }

    private fun entries(data: JSONObject, key: String): List<LibEntry> {
        val arr = data.optJSONArray(key) ?: return emptyList()
        return (0 until arr.length()).mapNotNull { i ->
            arr.optJSONObject(i)?.let { LibEntry(it.optString("id"), it.optString("name"), it.optInt("count")) }
        }
    }

    private fun pairError(code: Int, message: String): String = when (code) {
        Protocol.ERR_PIN_INVALID -> "PIN 不对，再试一次"
        Protocol.ERR_PIN_EXPIRED -> "PIN 已过期，去车机上点「换一个 PIN」"
        Protocol.ERR_PIN_ATTEMPTS_EXCEEDED -> "错太多次，PIN 已作废，去车机重新生成"
        else -> message.ifEmpty { "配对失败（$code）" }
    }

    /**
     * 授权/配对成功。**不立刻**把状态切成 Ready，而是先等一小会儿：
     * 车机端在这之后可能马上就断（比如端口被抢、进程刚醒），立刻显示「已连接」
     * 再退回连接页，看起来就是状态在跳。
     *
     * 收到的第一条 state 推送会提前结束等待（见 handle 里的 commitReady）。
     */
    private fun onReady() {
        Prefs.lastHost = host
        Prefs.lastPort = port
        dropCount = 0
        retryDelayMs = 1_000L
        command(Protocol.ACTION_GET_STATE)
        requestLyrics()
        if (_state.value is Conn.Ready) {
            // 重连成功：已经在面板上了，直接把提示恢复成干净状态
            _hint.value = ""
            return
        }
        _hint.value = "已连上车机，正在同步…"
        armSettle()
    }

    /** 去抖：等 [SETTLE_MS]，期间 socket 还在、代次没变，才算真连上。 */
    private fun armSettle() {
        settleJob?.cancel()
        val g = gen.get()
        settleJob = scope.launch {
            delay(SETTLE_MS)
            if (g != gen.get() || socket == null) return@launch
            if (_state.value is Conn.Ready) return@launch
            _state.value = Conn.Ready(displayName())
            _hint.value = ""
        }
    }

    /** 收到服务端消息后的确认路径：比定时器更早、也更可信。 */
    private fun commitReady() {
        if (socket == null) return
        if (_state.value is Conn.Ready) return
        settleJob?.cancel()
        settleJob = null
        _state.value = Conn.Ready(displayName())
        _hint.value = ""
    }

    /**
     * 车机 v2.0 在 WS 一建立就推 state，可能比 pair_result 先到，
     * 这时 carName 还是空的 —— 用上次记下的名字兜底，别让标题栏空着。
     */
    private fun displayName(): String =
        carName.ifEmpty { Prefs.lastName.ifEmpty { "车机" } }

    /**
     * 断线处理。核心是**别把已经连上的遥控面板踢回连接页**：
     * 短暂抖动会立刻重连回来，来回切面板比断线本身更烦人。
     * 所以先保持 Ready，只换提示；连续失败 3 次才真正退回连接页。
     */
    private fun fail(message: String) {
        settleJob?.cancel()
        settleJob = null
        if (!wantConnection) {
            _state.value = Conn.Failed(message)
            return
        }
        val wasReady = _state.value is Conn.Ready
        if (wasReady && dropCount < MAX_SILENT_DROPS) {
            dropCount++
            _hint.value = "连接断了，正在重连…"
        } else {
            _state.value = Conn.Failed(message)
            _hint.value = message
        }
        failStreak++
        if (failStreak >= REDISCOVER_AFTER) scheduleRediscover()
        scheduleRetry()
    }

    /**
     * 连着几次都连不上，说明**地址本身**可能已经不对了 ——
     * 车机热点重启后 IP 会变（甚至换网段），继续对着旧 IP 退避重试永远连不上。
     *
     * 这里做兜底发现，顺序是「名字优先、扫描兜底」：
     * 1. `carremote.local`：Android 12+ 的解析器能走 mDNS 解析 .local，命中就不必扫网段；
     *    老系统会直接 UnknownHostException，直接跳过。
     * 2. 本机所在 /24 扫一遍 18765 端口（约 1.4 秒），谁能连上谁是车机。
     *
     * 只有找到**不同的**地址才会切过去；找到同一个说明车机确实不在线，交给原来的退避继续等。
     */
    private fun scheduleRediscover() {
        if (recoverJob?.isActive == true) return
        recoverJob = scope.launch {
            Log.i(TAG, "连续 $failStreak 次失败，重新寻找车机")
            _hint.value = "正在重新寻找车机…"
            val byName = withContext(Dispatchers.IO) {
                runCatching {
                    withTimeout(1_500L) { InetAddress.getByName(MDNS_HOST)?.hostAddress }
                }.getOrNull()
            }
            if (!wantConnection) return@launch
            var next = byName?.takeIf { it.isNotEmpty() && it != host }
            // 域名解析出来还是老地址（缓存没刷新，或者车机压根没在线），就退到扫网段。
            // 扫一遍 /24 大约 1.4 秒，只在连续失败时才做，平时不会跑。
            if (next == null) {
                next = runCatching { Scanner.scan() }.getOrDefault(emptyList()).firstOrNull { it != host }
            }
            if (next != null) {
                Log.i(TAG, "重新找到车机 $next（原 $host）")
                failStreak = 0
                connect(next, Protocol.PORT)
            }
        }
    }

    private fun scheduleRetry() {
        retryJob?.cancel()
        retryJob = scope.launch {
            delay(retryDelayMs)
            retryDelayMs = (retryDelayMs * 2).coerceAtMost(15_000L)
            if (wantConnection) {
                Log.i(TAG, "reconnect to $host:$port")
                openSocket()
            }
        }
    }

    /** 应用层心跳间隔。车机端的空闲判定是 90 秒，这里留足三次重发余量。 */
    private const val HEARTBEAT_MS = 25_000L

    /** 车机固定域名。手机端不依赖它也能连（有 IP + 扫描兜底），但 IP 变了时它比扫描快。 */
    private const val MDNS_HOST = "carremote.local"

    /** 连续失败几次就开始重新找车机。3 次大约 7 秒（1+2+4），再久用户就以为坏了。 */
    private const val REDISCOVER_AFTER = 3

    /** 授权成功后等多久再显示「已连接」。够长到能挡住一次瞬时断开，又不至于让人等。 */
    private const val SETTLE_MS = 400L

    /** 已经在遥控面板时，允许连续静默重连几次才退回连接页。 */
    private const val MAX_SILENT_DROPS = 3
}
