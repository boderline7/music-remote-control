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
 * 车机遥控 3.0：一个 App、两个 tab。
 *
 *   ┌──────────────────────────────┐
 *   │ 车机控制 │ 音乐控制          │  ← 原生 tab，永远在，跟网页内容无关
 *   ├──────────────────────────────┤
 *   │ carWeb(18766) / musicWeb(18765) │
 *   └──────────────────────────────┘
 *
 * 为什么不再只当"网页壳"：
 *   18765 有可能是第三方网易云自己发的页面 —— 里面没有返回车控的入口，也可能不允许被 iframe 嵌入。
 *   只要切换由原生 tab 承担，加载谁都不影响切换：页面再怎么跳、怎么刷，都只在自己那个 WebView 里。
 *
 * 网页侧配合：带 `client=android` 打开时，两页都会收掉自己的网页切换条，避免两层切换打架。
 */
class MainActivity : Activity() {

    private companion object {
        const val TAB_CAR = 0
        const val TAB_MUSIC = 1
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var root: FrameLayout
    private lateinit var content: LinearLayout
    private lateinit var webArea: FrameLayout
    private lateinit var connectPanel: ScrollView
    private lateinit var statusText: TextView
    private lateinit var candidateBox: LinearLayout
    private lateinit var manualInput: EditText
    private lateinit var carTabBtn: TextView
    private lateinit var musicTabBtn: TextView
    private lateinit var placeholder: TextView
    private lateinit var carWeb: WebView
    private lateinit var musicWeb: WebView

    private var host = ""
    private var tab = TAB_CAR
    private var carOk = false
    private var musicOk = false
    private var webShown = false
    private var searching = false
    private var searchJob: Job? = null

    /** 每个 tab 是否已经发出过请求 / 页面是否加载完成，都按 tab 记 */
    private val loaded = booleanArrayOf(false, false)
    private val ready = booleanArrayOf(false, false)

    /** 找到的车机：host -> 显示名 */
    private val found = LinkedHashMap<String, String>()

    private val tabLabels = arrayOf("车机控制", "音乐控制")

    private val clientVersion: String
        get() = runCatching {
            @Suppress("DEPRECATION")
            packageManager.getPackageInfo(packageName, 0).versionName ?: "3.0.0"
        }.getOrDefault("3.0.0")

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

        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            visibility = View.GONE
        }

        // ---- 原生 tab ----
        val bar = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setBackgroundColor(bg)
            setPadding(dp(8), dp(8), dp(8), dp(6))
        }
        carTabBtn = tabView(TAB_CAR)
        musicTabBtn = tabView(TAB_MUSIC)
        carTabBtn.setOnClickListener { selectTab(TAB_CAR) }
        musicTabBtn.setOnClickListener { selectTab(TAB_MUSIC) }
        bar.addView(carTabBtn, tabLp())
        bar.addView(musicTabBtn, tabLp())

        val divider = View(this).apply { setBackgroundColor(Color.parseColor("#22222A")) }

        // ---- 内容区：两个 WebView 各管一边，切 tab 只是换显示 ----
        webArea = FrameLayout(this)
        carWeb = createWeb(TAB_CAR)
        musicWeb = createWeb(TAB_MUSIC)
        carWeb.visibility = View.GONE
        musicWeb.visibility = View.GONE
        webArea.addView(carWeb, fill)
        webArea.addView(musicWeb, fill)

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

        content.addView(bar, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        ))
        content.addView(divider, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(1),
        ))
        content.addView(webArea, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0,
            1f,
        ))
        root.addView(content, fill)

        connectPanel = buildConnectPanel()
        root.addView(connectPanel, fill)
    }

    private fun tabLp() =
        LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).apply {
            marginStart = dp(4)
            marginEnd = dp(4)
        }

    private fun tabView(index: Int): TextView = TextView(this).apply {
        text = tabLabels[index]
        textSize = 14.5f
        gravity = Gravity.CENTER
        setPadding(dp(8), dp(10), dp(8), dp(10))
    }

    private fun paintTabs() {
        styleTab(carTabBtn, tab == TAB_CAR, carOk)
        styleTab(musicTabBtn, tab == TAB_MUSIC, musicOk)
    }

    private fun styleTab(v: TextView, active: Boolean, ok: Boolean) {
        val label = if (v === carTabBtn) tabLabels[TAB_CAR] else tabLabels[TAB_MUSIC]
        v.text = label
        v.setTextColor(
            Color.parseColor(
                when {
                    active -> "#FFFFFF"
                    ok -> "#9A9AA2"
                    else -> "#5E5E66"     // 端口没起来：灰掉，但仍可点，点了给提示
                }
            )
        )
        v.typeface = if (active) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        v.background = if (active) rounded("#22222A", "#3A3A44") else null
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun createWeb(index: Int): WebView = WebView(this).apply {
        setBackgroundColor(Color.parseColor("#0B0B0D"))
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
                if (url != null && !url.startsWith("about:")) ready[index] = true
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?,
            ) {
                if (request?.isForMainFrame == true) {
                    failPage(index, error?.description?.toString().orEmpty())
                }
            }
        }
        addJavascriptInterface(NativeBridge(), "CarRemoteNative")
    }

    private fun web(index: Int): WebView = if (index == TAB_CAR) carWeb else musicWeb

    private fun portOf(index: Int): Int =
        if (index == TAB_CAR) Protocol.PORT_CAR else Protocol.PORT

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
            text = "与车机连同一 WiFi / 热点即可自动发现 · 连上后顶部两个 tab 直接切"
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
            text = "只用填 IP：车控页在 18766、音乐页在 18765，App 自己探，哪个通了就能切到哪个。" +
                "手动加的地址会一直留着，长按可删。"
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

    // ---------- tab 与页面 ----------

    private fun selectTab(index: Int) {
        val ok = if (index == TAB_CAR) carOk else musicOk
        tab = index
        paintTabs()

        if (!ok) {
            carWeb.visibility = View.GONE
            musicWeb.visibility = View.GONE
            placeholder.visibility = View.VISIBLE
            placeholder.text = if (index == TAB_CAR)
                "18766 车控页没响应\n\n到车机上打开一次「桌面助手」，再点这里重试"
            else
                "18765 音乐页没响应\n\n到车机上打开一次汽水音乐（或桥接包），再点这里重试"
            return
        }

        placeholder.visibility = View.GONE
        carWeb.visibility = if (index == TAB_CAR) View.VISIBLE else View.GONE
        musicWeb.visibility = if (index == TAB_MUSIC) View.VISIBLE else View.GONE
        if (!loaded[index]) {
            loaded[index] = true
            web(index).loadUrl(pageUrl(portOf(index)))
        }
    }

    private fun pageUrl(port: Int): String = "http://$host:$port/?client=android&cv=$clientVersion"

    /** 两个端口都重探一遍；页面挂了点提示就能重试 */
    private fun recheck() {
        if (host.isEmpty()) return
        placeholder.text = "正在重试 $host …"
        val h = host
        scope.launch {
            val c = async { reachable(h, Protocol.PORT_CAR) }
            val m = async { reachable(h, Protocol.PORT) }
            carOk = c.await()
            musicOk = m.await()
            paintTabs()
            if (!carOk && !musicOk) {
                placeholder.text = "还是不通\n\n车机上确认 18766（车机助手）/ 18765（汽水音乐）在跑，再点这里重试"
                return@launch
            }
            // 重新拉一次，别停在旧的白板页上
            loaded[0] = false
            loaded[1] = false
            ready[0] = false
            ready[1] = false
            val target = when {
                tab == TAB_CAR && carOk -> TAB_CAR
                tab == TAB_MUSIC && musicOk -> TAB_MUSIC
                carOk -> TAB_CAR
                else -> TAB_MUSIC
            }
            selectTab(target)
        }
    }

    private fun connectTo(h: String) {
        host = h
        Prefs.lastHost = h
        Prefs.addManual(h)
        webShown = true
        carOk = false
        musicOk = false
        loaded[0] = false
        loaded[1] = false
        ready[0] = false
        ready[1] = false
        carWeb.visibility = View.GONE
        musicWeb.visibility = View.GONE
        content.visibility = View.VISIBLE
        connectPanel.visibility = View.GONE
        placeholder.visibility = View.VISIBLE
        placeholder.text = "正在连接 $h …"
        tab = TAB_CAR
        paintTabs()

        scope.launch {
            val c = async { reachable(h, Protocol.PORT_CAR) }
            val m = async { reachable(h, Protocol.PORT) }
            carOk = c.await()
            musicOk = m.await()
            paintTabs()
            if (!carOk && !musicOk) {
                showConnect("$h 能连上，但 18766 / 18765 都没响应 · 车机上确认页面在跑")
                return@launch
            }
            selectTab(if (carOk) TAB_CAR else TAB_MUSIC)
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

    private fun failPage(index: Int, desc: String) {
        if (index == TAB_CAR) carOk = false else musicOk = false
        loaded[index] = false
        ready[index] = false
        paintTabs()
        if (webShown) showConnect("打不开车机页面 · ${desc.ifEmpty { "连接失败" }}")
    }

    private fun setStatus(text: String) {
        statusText.text = text
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
                        // 一台车机只留一条：端口对用户没意义，App 两个都探
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
        val h = host
        scope.launch {
            val c = async { reachable(h, Protocol.PORT_CAR) }
            val m = async { reachable(h, Protocol.PORT) }
            val carNow = c.await()
            val musicNow = m.await()
            if (!carNow && !musicNow) {
                showConnect("车机地址变了，正在重新搜索…")
                discover(autoConnect = true)
                return@launch
            }
            carOk = carNow
            musicOk = musicNow
            paintTabs()
            when {
                tab == TAB_CAR && !carNow -> selectTab(TAB_MUSIC)
                tab == TAB_MUSIC && !musicNow -> selectTab(TAB_CAR)
                ready[tab] -> web(tab).evaluateJavascript(
                    "window.carRemoteRefresh && window.carRemoteRefresh()", null
                )
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val volumeKey = keyCode == KeyEvent.KEYCODE_VOLUME_UP ||
            keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
        if (volumeKey && webShown && ready[tab]) {
            val dir = if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) 1 else -1
            web(tab).evaluateJavascript(
                "window.carRemoteStepVolume && window.carRemoteStepVolume($dir)", null
            )
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    @Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
    override fun onBackPressed() {
        if (webShown) {
            // tab 一直在，返回键直接退到连接页，不再需要「先切 tab 再退」那一套
            showConnect("已返回连接页 · 点「重新搜索」可再连")
            return
        }
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        if (webShown && ready[tab]) {
            web(tab).evaluateJavascript("window.carRemoteRefresh && window.carRemoteRefresh()", null)
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
        carWeb.destroy()
        musicWeb.destroy()
    }
}
