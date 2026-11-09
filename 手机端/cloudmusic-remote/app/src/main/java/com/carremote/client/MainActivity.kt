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
 *   1. Bonjour 服务发现 + /24 网段扫描（弱网/热点下比域名可靠）；
 *   2. 上次成功地址记忆与失效自愈；
 *   3. 硬件音量键拦截，转给网页的 carRemoteStepVolume；
 *   4. 网络恢复时刷新或重新发现。
 */
class MainActivity : Activity() {

    private data class Endpoint(val name: String, val host: String, val port: Int)

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
            packageManager.getPackageInfo(packageName, 0).versionName ?: "2.0.0"
        }.getOrDefault("2.0.0")

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
            hint = "车机 IP，如 192.168.43.1[:18765]"
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
            } else {
                loadHost(addr.first, addr.second)
            }
        }
        box.addView(go, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        ).apply { topMargin = dp(10) })

        box.addView(TextView(this).apply {
            text = "发现依赖 18765 端口；域名发现默认关闭也能正常找到车机。"
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

    private fun parseAddress(raw: String): Pair<String, Int>? {
        var s = raw.removePrefix("http://").removePrefix("https://").substringBefore('/')
        var p = Protocol.PORT
        val colon = s.lastIndexOf(':')
        if (colon > 0) {
            val n = s.substring(colon + 1).toIntOrNull()
            if (n != null && n in 1..65535) {
                p = n
                s = s.substring(0, colon)
            }
        }
        return if (s.isBlank()) null else s to p
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
            val scanJob = async { runCatching { Scanner.scan() }.getOrDefault(emptyList()) }
            val collectJob = launch {
                combine(Discovery.found, Scanner.results) { nsd, ips -> nsd to ips }
                    .collect { (nsd, ips) ->
                        nsd.forEach {
                            found[it.host] = Endpoint(
                                it.name.ifEmpty { "车机" },
                                it.host,
                                it.port.takeIf { p -> p > 0 } ?: Protocol.PORT,
                            )
                        }
                        ips.forEach {
                            if (!found.containsKey(it)) found[it] = Endpoint("车机", it, Protocol.PORT)
                        }
                        renderCandidates()
                    }
            }
            val hits = scanJob.await()
            delay(1200)      // 给 NSD 的 resolve 留一点时间
            collectJob.cancel()
            Discovery.stop()
            hits.forEach {
                if (!found.containsKey(it)) found[it] = Endpoint("车机", it, Protocol.PORT)
            }
            renderCandidates()
            searching = false

            if (found.isEmpty()) {
                setStatus("没找到车机 · 确认在同一 WiFi / 热点后重试，或手动填 IP")
            } else if (autoConnect) {
                // 优先上次连过的那台；只有一台时直接进；多台并列就交给用户点，避免连错车
                val target = found[Prefs.lastHost] ?: found.values.singleOrNull()
                if (target != null) loadHost(target.host, target.port)
                else setStatus("找到 ${found.size} 台车机，点一下连接")
            } else {
                setStatus("找到 ${found.size} 台车机，点一下连接")
            }
        }
    }

    private fun renderCandidates() {
        candidateBox.removeAllViews()
        found.values.sortedBy { it.host }.forEach { ep ->
            val label = if (ep.name == "车机") "${ep.host}:${ep.port}" else "${ep.name}  ·  ${ep.host}:${ep.port}"
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
            showConnect("已返回连接页 · 点「重新搜索」可再连")
            return
        }
        super.onBackPressed()
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
