package com.carremote.client

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket

/**
 * 车机遥控 3.2：一个 App、一个 WebView。
 *
 *   ┌──────────────────────────────┐
 *   │ 车机控制 │ 音乐控制          │  ← 页面自己的切换，App 不再画一层
 *   ├──────────────────────────────┤
 *   │ 车机下发的页面（18766 / 18765 / 18764）│
 *   └──────────────────────────────┘
 *
 * 3.1 是原生 tab + 两个 WebView，页面上还有自己的一层套壳，结果就是
 * 「两层切换、两条状态栏」。现在切换只留页面这一份：不管从 18766、18765
 * 还是 18764 进，看到的顶栏和按钮都是同一个，App 只负责把页面装进来。
 *
 * 进哪个端口：18766 优先（车机助手常驻，页面里再去探音乐），
 * 不通再退 18765 / 18764。
 */
class MainActivity : Activity() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var root: FrameLayout
    private lateinit var content: FrameLayout
    private lateinit var webArea: FrameLayout
    private lateinit var connectPanel: ScrollView
    private lateinit var statusText: TextView
    private lateinit var candidateBox: LinearLayout
    private lateinit var manualInput: EditText
    private lateinit var placeholder: TextView
    private lateinit var web: WebView

    private var host = ""
    private var port = 0

    private var webShown = false
    private var loaded = false
    private var ready = false
    private var searching = false
    private var searchJob: Job? = null

    /** 找到的车机：host -> 显示名 */
    private val found = LinkedHashMap<String, String>()

    private val clientVersion: String
        get() = runCatching {
            @Suppress("DEPRECATION")
            packageManager.getPackageInfo(packageName, 0).versionName ?: "3.2.0"
        }.getOrDefault("3.2.0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Prefs.init(this)
        buildViews()
        setContentView(root)

        NetWatcher.onAvailable = { runOnUiThread { onNetworkAvailable() } }
        NetWatcher.start(this)

        val last = Prefs.lastHost
        if (last.isNotEmpty()) {
            connectTo(last)
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

        content = FrameLayout(this).apply { visibility = View.GONE }

        webArea = FrameLayout(this)
        web = createWeb()
        web.visibility = View.GONE
        webArea.addView(web, fill)

        placeholder = TextView(this).apply {
            visibility = View.GONE
            textSize = 14f
            gravity = Gravity.CENTER
            setTextColor(Color.parseColor("#9A9AA2"))
            setPadding(dp(28), dp(28), dp(28), dp(28))
            setBackgroundColor(bg)
            setOnClickListener { recheck() }
        }
        webArea.addView(placeholder, fill)

        content.addView(webArea, fill)
        root.addView(content, fill)

        connectPanel = buildConnectPanel()
        root.addView(connectPanel, fill)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun createWeb(): WebView = WebView(this).apply {
        setBackgroundColor(Color.parseColor("#0B0B0D"))
        settings.apply {
            javaScriptEnabled = true
            // 网页用 localStorage 存 deviceId / 配对 token / 上次看的页签，必须开
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
                if (url != null && !url.startsWith("about:")) ready = true
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
            text = "与车机连同一 WiFi / 热点即可自动发现 · 连上后页面顶部直接切车控和音乐"
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
            hint = "车机 IP，如 192.168.124.102"
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
            val h = parseHost(manualInput.text.toString())
            if (h == null) {
                setStatus("地址格式不对，填 IP 就行（不用带端口）")
                return@setOnClickListener
            }
            manualInput.setText("")
            connectTo(h)
        }
        box.addView(go, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        ).apply { topMargin = dp(10) })

        box.addView(TextView(this).apply {
            text = "只用填 IP：App 依次试 18766（车机助手）、18765（第三方）、18764（桥接包），" +
                "哪个在跑就进哪个，页面里再切。手动加的地址会一直留着，长按可删。"
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

    // ---------- 页面 ----------

    /** 页面自己带切换，App 不再往 URL 上塞控件开关，只留客户端标识给车机侧看 */
    private fun pageUrl(): String = "http://$host:$port/?client=android&cv=$clientVersion"

    private fun openPage() {
        placeholder.visibility = View.GONE
        web.visibility = View.VISIBLE
        if (!loaded) {
            loaded = true
            web.loadUrl(pageUrl())
        }
    }

    private fun reloadPage() {
        loaded = false
        ready = false
        openPage()
    }

    /** 依次试三个端口；18766 优先，因为它本身就会去探音乐 */
    private suspend fun pickPort(): Int {
        for (p in Protocol.PORTS) {
            if (reachable(host, p)) return p
        }
        return 0
    }

    private fun connectTo(h: String) {
        host = h
        Prefs.lastHost = h
        Prefs.addManual(h)
        webShown = true
        port = 0
        loaded = false
        ready = false
        web.visibility = View.GONE
        content.visibility = View.VISIBLE
        connectPanel.visibility = View.GONE
        placeholder.visibility = View.VISIBLE
        placeholder.text = "正在连接 $h …"

        scope.launch {
            val p = pickPort()
            if (p <= 0) {
                showConnect("$h 能连上，但 18766 / 18765 / 18764 都没响应 · 车机上确认页面在跑")
                return@launch
            }
            port = p
            placeholder.visibility = View.GONE
            web.visibility = View.VISIBLE
            reloadPage()
        }
    }

    /** 端口都重探一遍；页面挂了点提示就能重试 */
    private fun recheck() {
        if (host.isEmpty()) return
        placeholder.text = "正在重试 $host …"
        scope.launch {
            val p = pickPort()
            if (p <= 0) {
                placeholder.text = "还是不通\n\n车机上确认 18766（车机助手）/ 18764（桥接包）/ 18765（第三方）在跑，再点这里重试"
                return@launch
            }
            port = p
            reloadPage()
        }
    }

    private fun showConnect(message: String) {
        webShown = false
        content.visibility = View.GONE
        connectPanel.visibility = View.VISIBLE
        // 直接连上次地址进来时没走过 discover，手动地址列表要在这里补一次
        renderCandidates()
        setStatus(message)
    }

    private fun failPage(desc: String) {
        loaded = false
        ready = false
        if (webShown) showConnect("打不开车机页面 · ${desc.ifEmpty { "连接失败" }}")
    }

    private fun setStatus(text: String) {
        statusText.text = text
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    // ---------- 发现与连接 ----------

    /** 只取主机名：`http://1.2.3.4:18766/x`、`1.2.3.4:18766`、`1.2.3.4` 都认 */
    private fun parseHost(raw: String): String? {
        val s = raw.trim()
            .removePrefix("http://")
            .removePrefix("https://")
            .substringBefore('/')
            .substringBefore('?')
            .substringBefore(':')
            .trim()
        return if (s.isBlank()) null else s
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
                        // 一台车机只留一条：端口对用户没意义，App 三个都探
                        nsd.forEach { found[it.host] = it.name.ifEmpty { "车机" } }
                        hits.forEach { hp ->
                            if (!found.containsKey(hp.first)) found[hp.first] = "车机"
                        }
                        renderCandidates()
                    }
            }
            scanJob.await()
            delay(1200)      // 给 NSD 的 resolve 留一点时间
            collectJob.cancel()
            Discovery.stop()
            renderCandidates()
            searching = false

            val target = found.keys.firstOrNull { it == Prefs.lastHost }
                ?: found.keys.singleOrNull()
            if (found.isEmpty()) {
                setStatus("没找到车机 · 确认在同一 WiFi / 热点后重试，或手动填 IP")
            } else if (autoConnect && target != null) {
                connectTo(target)
            } else {
                setStatus("找到 ${found.size} 台车机，点一下连接")
            }
        }
    }

    private fun renderCandidates() {
        candidateBox.removeAllViews()
        val seen = mutableSetOf<String>()

        // 手动加过的排最前：换过网络也能一键回去；长按删掉
        Prefs.manualHosts.mapNotNull { parseHost(it) }.forEach { h ->
            if (!seen.add(h)) return@forEach
            val b = actionButton(if (h == Prefs.lastHost) "$h · 上次" else h)
            b.setOnClickListener { connectTo(h) }
            b.setOnLongClickListener {
                Prefs.removeManual(h)
                renderCandidates()
                true
            }
            candidateBox.addView(b, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            ).apply { topMargin = dp(8) })
        }

        found.forEach { (h, name) ->
            if (!seen.add(h)) return@forEach
            val label = if (name == "车机" || name.isBlank()) h else "$name · $h"
            val b = actionButton(label)
            b.setOnClickListener { connectTo(h) }
            candidateBox.addView(b, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            ).apply { topMargin = dp(8) })
        }
    }

    private suspend fun reachable(h: String, p: Int): Boolean = withContext(Dispatchers.IO) {
        if (h.isEmpty()) return@withContext false
        runCatching {
            Socket().use {
                it.connect(InetSocketAddress(h, p), 700)
                true
            }
        }.getOrDefault(false)
    }

    // ---------- 网页 <-> 原生 ----------

    /** 网页 onConnection 上报，原生据此记住可用地址。 */
    private inner class NativeBridge {
        @JavascriptInterface
        fun onConnection(ok: Boolean, @Suppress("UNUSED_PARAMETER") text: String) {
            runOnUiThread { if (ok && host.isNotEmpty()) Prefs.lastHost = host }
        }
    }

    private fun onNetworkAvailable() {
        if (!webShown) {
            if (!searching) discover(autoConnect = true)
            return
        }
        scope.launch {
            val p = pickPort()
            if (p <= 0) {
                showConnect("车机地址变了，正在重新搜索…")
                discover(autoConnect = true)
                return@launch
            }
            if (p != port) {
                port = p
                reloadPage()
            } else if (ready) {
                web.evaluateJavascript(
                    "window.carRemoteRefresh && window.carRemoteRefresh()", null
                )
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val volumeKey = keyCode == KeyEvent.KEYCODE_VOLUME_UP ||
            keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
        if (volumeKey && webShown && ready) {
            val dir = if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) 1 else -1
            web.evaluateJavascript(
                "window.carRemoteStepVolume && window.carRemoteStepVolume($dir)", null
            )
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    @Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
    override fun onBackPressed() {
        if (webShown) {
            // 切换在页面里，返回键直接退到连接页，不用「先切页签再退」
            showConnect("已返回连接页 · 点「重新搜索」可再连")
            return
        }
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        if (webShown && ready) {
            web.evaluateJavascript("window.carRemoteRefresh && window.carRemoteRefresh()", null)
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
        web.destroy()
    }
}
