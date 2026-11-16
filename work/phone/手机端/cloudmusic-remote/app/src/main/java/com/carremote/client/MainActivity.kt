package com.carremote.client

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket

/**
 * 车机遥控 2.0.0：只做「发现 + 加载网页」两件事的原生壳。
 *
 * 界面、协议、播放控制、资料库、歌词全在车机端 assets/remote.html 里，
 * 安卓 / iOS / 浏览器三端共用这一份页面，不再各自维护一套 UI。
 * 这里保留的原生能力只有：
 *   1. Bonjour 服务发现 + /24 网段扫描，两个端口（18766 车控 / 18765 音乐）一起探；
 *   2. 上次成功地址记忆与失效自愈；
 *   3. 硬件音量键拦截，转给网页的 carRemoteStepVolume；
 *   4. 网络恢复时刷新或重新发现。
 */
class MainActivity : Activity() {

    private data class Endpoint(val name: String, val host: String, val port: Int) {
        /** 同一台车机有两个端口，键必须带上端口 */
        val key: String get() = "$host:$port"
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var root: FrameLayout
    private lateinit var webView: WebView
    private lateinit var connectPanel: ScrollView
    private lateinit var statusText: TextView
    private lateinit var candidateBox: LinearLayout
    private lateinit var manualInput: EditText

    private var host = ""
    private var port = Protocol.PORT
    private var pageLoaded = false
    private var connected = false
    private var webShown = false
    private var searching = false
    private var searchJob: Job? = null
    private val found = LinkedHashMap<String, Endpoint>()

    private val clientVersion: String
        get() = runCatching {
            @Suppress("DEPRECATION")
            packageManager.getPackageInfo(packageName, 0).versionName ?: "2.0.1"
        }.getOrDefault("2.0.1")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Prefs.init(this)
        buildViews()
        setContentView(root)

        NetWatcher.onAvailable = { runOnUiThread { onNetworkAvailable() } }
        NetWatcher.start(this)

        val last = Prefs.lastHost
        if (last.isNotEmpty()) {
            val lastPort = Prefs.lastPort
            showConnect("正在连接上次的车机 $last:$lastPort…")
            scope.launch {
                if (reachable(last, lastPort)) {
                    loadHost(last, lastPort)
                } else {
                    showConnect("上次的地址连不上了，正在重新搜索…")
                    discover(autoConnect = true)
                }
            }
        } else {
            showConnect("正在搜索局域网内的车机…")
            discover(autoConnect = true)
        }
    }

    // ---------- 视图（纯代码构建，不引入任何布局资源 / Compose 依赖） ----------

    private fun dp(v: Int): Int = (v * resources.displayMetrics.density).toInt()

    private fun rounded(fill: String, stroke: String? = null): GradientDrawable =
        GradientDrawable().apply {
            cornerRadius = dp(12).toFloat()
            setColor(Color.parseColor(fill))
            if (stroke != null) setStroke(1, Color.parseColor(stroke))
        }

    private fun actionButton(label: String, accent: Boolean = false): Button = Button(this).apply {
        text = label
        textSize = 15f
        isAllCaps = false
        minHeight = dp(46)
        setTextColor(Color.parseColor(if (accent) "#FFFFFF" else "#E5E5EA"))
        background = rounded(if (accent) "#FA2D48" else "#1C1C22", if (accent) null else "#2C2C34")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun buildViews() {
        val bg = Color.parseColor("#0B0B0D")
        val fill = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        root = FrameLayout(this).apply { setBackgroundColor(bg) }

        webView = WebView(this).apply {
            setBackgroundColor(bg)
            settings.apply {
                javaScriptEnabled = true
                // 网页用 localStorage 存 deviceId / 配对 token，必须开
                domStorageEnabled = true
                // 车机端页面更新后，壳不能拿本地缓存的旧版
                cacheMode = WebSettings.LOAD_NO_CACHE
                allowFileAccess = false
                allowContentAccess = false
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                setSupportZoom(false)
                builtInZoomControls = false
                useWideViewPort = true
                loadWithOverviewMode = false
            }
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    pageLoaded = true
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?,
                ) {
                    if (request?.isForMainFrame == true) {
                        failPage(error?.description?.toString().orEmpty())
                    }
                }
            }
            addJavascriptInterface(NativeBridge(), "CarRemoteNative")
            visibility = View.GONE
        }
        root.addView(webView, fill)

        connectPanel = buildConnectPanel()
        root.addView(connectPanel, fill)
    }

    private fun buildConnectPanel(): ScrollView {
        val panel = ScrollView(this).apply { setBackgroundColor(Color.parseColor("#0B0B0D")) }
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(24), dp(52), dp(24), dp(32))
        }

        box.addView(TextView(this).apply {
            text = "车机遥控"
            textSize = 27f
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
        })
        box.addView(TextView(this).apply {
            text = "与车机连接同一 WiFi / 热点即可自动发现"
            textSize = 13f
            setTextColor(Color.parseColor("#8E8E93"))
            setPadding(0, dp(8), 0, dp(26))
        })

        statusText = TextView(this).apply {
            text = "正在搜索…"
            textSize = 14f
            setTextColor(Color.parseColor("#C7C7CC"))
            setPadding(0, 0, 0, dp(16))
        }
        box.addView(statusText)

        val scan = actionButton("重新搜索", accent = true)
        scan.setOnClickListener { discover(autoConnect = false) }
        box.addView(scan, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        ))

        candidateBox = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        box.addView(candidateBox)

        box.addView(TextView(this).apply {
            text = "手动连接"
            textSize = 13f
            setTextColor(Color.parseColor("#8E8E93"))
            setPadding(0, dp(26), 0, dp(10))
        })
        manualInput = EditText(this).apply {
            hint = "车机 IP，如 192.168.43.1[:18766]"
            textSize = 15f
            setTextColor(Color.WHITE)
            setHintTextColor(Color.parseColor("#6E6E76"))
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            setPadding(dp(14), dp(12), dp(14), dp(12))
            background = rounded("#1C1C22", "#2C2C34")
        }
        box.addView(manualInput, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        ))

        val go = actionButton("连接")
        go.setOnClickListener {
            val raw = manualInput.text.toString().trim()
            val addr = parseAddress(raw)
            if (addr == null) {
                setStatus("地址格式不对，填 IP 或 IP:端口")
                return@setOnClickListener
            }
            val h = addr.first
            val p = addr.second
            manualInput.setText("")
            if (addr.third) {
                Prefs.addManual("$h:$p")
                renderCandidates()
                loadHost(h, p)
            } else {
                // 只填了 IP：先探 18766（车控页自带两个 tab），不通再退回 18765
                scope.launch {
                    var hit = 0
                    for (p in Protocol.PORTS) {
                        setStatus("正在试 $h:$p …")
                        if (reachable(h, p)) { hit = p; break }
                    }
                    if (hit == 0) {
                        setStatus("$h 的 18766 / 18765 都不通 · 确认车机上车机助手在跑")
                        return@launch
                    }
                    Prefs.addManual("$h:$hit")
                    found["$h:$hit"] = Endpoint(Protocol.role(hit), h, hit)
                    renderCandidates()
                    loadHost(h, hit)
                }
            }
        }
        box.addView(go, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        ).apply { topMargin = dp(10) })

        box.addView(TextView(this).apply {
            text = "自动探测 18766（车控 + 音乐，页面顶部切换），不通再退回 18765。" +
                "想直连音乐页就填 IP:18765；停在 18765 时按返回键切回车控页。" +
                "手动加的地址会一直留着，长按候选可删除。"
            textSize = 12f
            setTextColor(Color.parseColor("#6E6E76"))
            setPadding(0, dp(20), 0, 0)
        })

        panel.addView(box, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        ))
        return panel
    }

    // ---------- 发现与连接 ----------

    /** 返回 地址 / 端口 / 是否显式写了端口。只填 IP 时端口只是占位，真正连哪头还要探。 */
    private fun parseAddress(raw: String): Triple<String, Int, Boolean>? {
        var s = raw.removePrefix("http://").removePrefix("https://").substringBefore('/')
        var p = Protocol.PORT
        var explicit = false
        val colon = s.lastIndexOf(':')
        if (colon > 0) {
            val n = s.substring(colon + 1).toIntOrNull()
            if (n != null && n in 1..65535) {
                p = n
                explicit = true
                s = s.substring(0, colon)
            }
        }
        return if (s.isBlank()) null else Triple(s.trim(), p, explicit)
    }

    private fun discover(autoConnect: Boolean) {
        if (searching) return
        searching = true
        found.clear()
        renderCandidates()
        setStatus("正在搜索局域网内的车机…")
        searchJob?.cancel()
        Discovery.start(this)
        searchJob = scope.launch {
            val scanJob = async {
                runCatching { Scanner.scan(Protocol.PORTS) }.getOrDefault(emptyList())
            }
            val collectJob = launch {
                combine(Discovery.found, Scanner.results) { nsd, hits -> nsd to hits }
                    .collect { (nsd, hits) ->
                        nsd.forEach {
                            // 车控页是统一入口；广播里带的那个端口也留着，想直接进音乐页时能点
                            val name = it.name.ifEmpty { "车机" }
                            val car = Endpoint(name, it.host, Protocol.PORT_CAR)
                            found[car.key] = car
                            if (it.port != Protocol.PORT_CAR && it.port > 0) {
                                val alt = Endpoint(name, it.host, it.port)
                                if (!found.containsKey(alt.key)) found[alt.key] = alt
                            }
                        }
                        hits.forEach { hp ->
                            val ep = Endpoint(Protocol.role(hp.second), hp.first, hp.second)
                            if (!found.containsKey(ep.key)) found[ep.key] = ep
                        }
                        renderCandidates()
                    }
            }
            val hits = scanJob.await()
            delay(1200)      // 给 NSD 的 resolve 留一点时间
            collectJob.cancel()
            Discovery.stop()
            hits.forEach { hp ->
                val ep = Endpoint(Protocol.role(hp.second), hp.first, hp.second)
                if (!found.containsKey(ep.key)) found[ep.key] = ep
            }
            renderCandidates()
            searching = false

            if (found.isEmpty()) {
                setStatus("没找到车机 · 确认在同一 WiFi / 热点后重试，或手动填 IP")
            } else if (autoConnect) {
                // 优先上次连过的那条地址；只有一条时直接进；多条并列交给用户点，避免连错
                val target = found["${Prefs.lastHost}:${Prefs.lastPort}"]
                    ?: found["${Prefs.lastHost}:${Protocol.PORT_CAR}"]
                    ?: found.values.filter { it.port == Protocol.PORT_CAR }.singleOrNull()
                    ?: found.values.singleOrNull()
                if (target != null) loadHost(target.host, target.port)
                else setStatus("找到 ${found.size} 个入口，点一下连接")
            } else {
                setStatus("找到 ${found.size} 个入口，点一下连接")
            }
        }
    }

    private fun renderCandidates() {
        candidateBox.removeAllViews()

        val manual = Prefs.manualHosts
        val seen = manual.toMutableSet()

        // 手动加的排最前：换过网络也能一键回去；长按删掉
        manual.forEach { raw ->
            val a = parseAddress(raw) ?: return@forEach
            val label = "${a.first}:${a.second} · ${Protocol.role(a.second)}"
            val b = actionButton(label)
            b.setOnClickListener { loadHost(a.first, a.second) }
            b.setOnLongClickListener {
                Prefs.removeManual(raw)
                renderCandidates()
                true
            }
            candidateBox.addView(b, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            ).apply { topMargin = dp(8) })
        }

        found.values.sortedWith(compareBy({ it.host }, { it.port })).forEach { ep ->
            if (!seen.add(ep.key)) return@forEach
            val label = if (ep.name == "车机" || ep.name == Protocol.role(ep.port)) {
                "${ep.host}:${ep.port} · ${Protocol.role(ep.port)}"
            } else {
                "${ep.name}  ·  ${ep.host}:${ep.port}"
            }
            val b = actionButton(label)
            b.setOnClickListener { loadHost(ep.host, ep.port) }
            candidateBox.addView(b, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            ).apply { topMargin = dp(8) })
        }
    }

    private fun loadHost(h: String, p: Int) {
        host = h
        port = p
        Prefs.lastHost = h
        Prefs.lastPort = p
        pageLoaded = false
        connected = false
        // 同一台车机的另一个端口也放进候选：从 18765 那边退回来时能一键切回车控页
        val sib = if (p == Protocol.PORT_CAR) Protocol.PORT else Protocol.PORT_CAR
        if (!found.containsKey("$h:$sib")) found["$h:$sib"] = Endpoint("车机", h, sib)
        showWeb()
        // 壳层带上客户端标识，车机侧能看到是安卓 / 哪个版本连进来的
        webView.loadUrl("http://$h:$p/?client=android&cv=$clientVersion")
    }

    private fun showWeb() {
        webShown = true
        webView.visibility = View.VISIBLE
        connectPanel.visibility = View.GONE
    }

    private fun showConnect(message: String) {
        webShown = false
        pageLoaded = false
        connected = false
        webView.visibility = View.GONE
        connectPanel.visibility = View.VISIBLE
        // 直接连上次地址进来时没走过 discover，手动地址列表要在这里补一次
        renderCandidates()
        setStatus(message)
    }

    private fun failPage(desc: String) {
        if (webShown) showConnect("打不开车机页面 · ${desc.ifEmpty { "连接失败" }}")
    }

    private fun setStatus(text: String) {
        statusText.text = text
    }

    private suspend fun reachable(h: String, p: Int): Boolean = withContext(Dispatchers.IO) {
        if (h.isEmpty()) return@withContext false
        runCatching {
            Socket().use {
                it.connect(InetSocketAddress(h, p), 400)
                true
            }
        }.getOrDefault(false)
    }

    // ---------- 网页 <-> 原生 ----------


    /** 网页 setStatus 时主动上报，原生据此决定是否吞掉硬件音量键、记住成功地址。 */
    private inner class NativeBridge {
        @JavascriptInterface
        fun onConnection(ok: Boolean, @Suppress("UNUSED_PARAMETER") text: String) {
            runOnUiThread {
                connected = ok
                if (ok) {
                    Prefs.lastHost = host
                    Prefs.lastPort = port
                }
            }
        }
    }

    private fun onNetworkAvailable() {
        if (!webShown) {
            if (!searching) discover(autoConnect = true)
            return
        }
        scope.launch {
            if (reachable(host, port)) {
                webView.evaluateJavascript("window.carRemoteRefresh && window.carRemoteRefresh()", null)
            } else {
                showConnect("车机地址变了，正在重新搜索…")
                discover(autoConnect = true)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val volumeKey = keyCode == KeyEvent.KEYCODE_VOLUME_UP ||
            keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
        if (volumeKey && webShown && pageLoaded && connected) {
            val dir = if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) 1 else -1
            webView.evaluateJavascript(
                "window.carRemoteStepVolume && window.carRemoteStepVolume($dir)", null
            )
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    @Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
    override fun onBackPressed() {
        if (webShown) {
            // 停在 18765（很可能是第三方网易云自己的页面，里面没有回车控的入口）：
            // 返回键先切回 18766 的车控页，再按一次才回连接页。
            if (port != Protocol.PORT_CAR && host.isNotEmpty()) {
                val h = host
                scope.launch {
                    if (reachable(h, Protocol.PORT_CAR)) {
                        loadHost(h, Protocol.PORT_CAR)
                        toast("已切到车控页 18766 · 再按返回回到连接页")
                    } else {
                        showConnect("18766 没响应 · 已返回连接页")
                    }
                }
                return
            }
            showConnect("已返回连接页 · 点「重新搜索」可再连")
            return
        }
        super.onBackPressed()
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        if (webShown && pageLoaded) {
            webView.evaluateJavascript("window.carRemoteRefresh && window.carRemoteRefresh()", null)
        }
    }

    override fun onPause() {
        super.onPause()
        Discovery.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        NetWatcher.onAvailable = null
        NetWatcher.stop()
        Discovery.stop()
        searchJob?.cancel()
        scope.cancel()
        webView.destroy()
    }
}
