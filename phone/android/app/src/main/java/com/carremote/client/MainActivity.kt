package com.carremote.client

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
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
import java.io.File
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
        // 网页里 a[download] / 「下载」按钮点了没反应，就是因为 WebView 没有下载器。
        // 交给系统 DownloadManager：通知栏有进度，文件落到「下载」目录。
        setDownloadListener { url, userAgent, contentDisposition, mimeType, _ ->
            enqueueDownload(url, userAgent, contentDisposition, mimeType)
        }
    }

    /**
     * 下载交给系统：WebView 自己不会存盘。文件名优先取 Content-Disposition，
     * 其次取 `?p=/SD0/NOR/M_video/xxx.mp4` 的最后一段，免得存成一个没后缀的 `file`。
     */
    private fun enqueueDownload(url: String, userAgent: String?, cd: String?, mime: String?) {
        try {
            val name = fileNameOf(url, cd)
            val req = DownloadManager.Request(Uri.parse(url))
                .setTitle(name)
                .setDescription("车机下载")
                .setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
                )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name)
            } else {
                req.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, name)
            }
            if (!mime.isNullOrBlank()) req.setMimeType(mime)
            if (!userAgent.isNullOrBlank()) req.addRequestHeader("User-Agent", userAgent)
            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(req)
            Toast.makeText(this, "已开始下载 $name", Toast.LENGTH_SHORT).show()
        } catch (t: Throwable) {
            Toast.makeText(this, "下载失败：${t.message ?: t}", Toast.LENGTH_LONG).show()
        }
    }

    private fun fileNameOf(url: String, cd: String?): String {
        var name = ""
        if (!cd.isNullOrBlank()) {
            val m = Regex("""filename\*?=(?:UTF-8'')?"?([^";]+)"?""", RegexOption.IGNORE_CASE)
                .find(cd)
            name = m?.groupValues?.get(1)?.trim()?.trim('"') ?: ""
        }
        if (name.isEmpty()) {
            val p = runCatching { Uri.parse(url).getQueryParameter("p") }.getOrNull()
            if (!p.isNullOrBlank()) name = p.substringAfterLast('/')
        }
        if (name.isEmpty()) {
            name = "car-" + System.currentTimeMillis()
            val ext = runCatching { Uri.parse(url).lastPathSegment }.getOrNull()
                ?.substringAfterLast('.', "")
            if (!ext.isNullOrBlank() && ext.length <= 5) name += "." + ext
        }
        return name.replace(Regex("""[\\/:*?"<>|]"""), "_").take(120)
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
            text = "连同一 WiFi / 热点，自动连接"
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
            text = "填 IP 即可，端口自动探测"
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

    /**
     * 页面自己带切换，App 不再往 URL 上塞控件开关，只留客户端标识给车机侧看。
     *
     * 末尾的 t= 是去缓存用的：车机换包之后，WebView 里可能还留着上一次的旧文档
     * （cacheMode = LOAD_NO_CACHE 在新版 WebView 上并不可靠），表现就是「装了新包
     * 还是旧界面 / 连不上」。每次进页面换个时间戳，URL 一变缓存必然失配。
     */
    private fun pageUrl(): String =
        "http://$host:$port/?client=android&cv=$clientVersion&t=${System.currentTimeMillis()}"

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

    /** 三个端口并行探，保持 18766 优先；总耗时从「3×超时」降到「1×超时」。 */
    private suspend fun pickPort(): Int = coroutineScope {
        val probes = Protocol.PORTS.map { p ->
            async(Dispatchers.IO) { reachable(host, p) }
        }
        probes.forEachIndexed { i, probe ->
            if (probe.await()) return@coroutineScope Protocol.PORTS[i]
        }
        0
    }

    private fun connectTo(h: String) {
        host = h
        Prefs.lastHost = h
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
                // 上次记的地址失效（换热点 / IP 变了）→ 直接自动重搜，不再把用户卡在错误页
                showConnect("连不上 $h，正在重新搜索…")
                discover(autoConnect = true)
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
            val hits = scanJob.await()
            // 扫描命中就直接走，不再干等 NSD；只有两边都没结果时才给 mDNS 一个短窗口
            if (hits.isEmpty() && found.isEmpty()) delay(500)
            collectJob.cancel()
            Discovery.stop()
            renderCandidates()
            searching = false

            val target = found.keys.firstOrNull { it == Prefs.lastHost }
                ?: found.keys.singleOrNull()
                ?: hits.firstOrNull()?.first
            if (found.isEmpty() && hits.isEmpty()) {
                setStatus("没找到车机 · 连同一 WiFi 后再试，或直接填 IP")
            } else if (autoConnect && target != null) {
                connectTo(target)
            } else {
                setStatus("找到车机，点下方 IP 连接")
            }
        }
    }

    /** 连接页只留一条记录：上次连过的 IP。发现/扫描只用于自动直连，不再铺成一长串候选。 */
    private fun renderCandidates() {
        candidateBox.removeAllViews()
        val last = Prefs.lastHost
        if (last.isEmpty()) return
        val b = actionButton("$last · 上次连接", accent = true)
        b.setOnClickListener { connectTo(last) }
        candidateBox.addView(b, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        ).apply { topMargin = dp(8) })
    }

    private suspend fun reachable(h: String, p: Int): Boolean = withContext(Dispatchers.IO) {
        if (h.isEmpty()) return@withContext false
        runCatching {
            Socket().use {
                it.connect(InetSocketAddress(h, p), 300)
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

        /**
         * 网页里的「下载」转成真正的存盘。WebView 自己没有下载器，页面里的 Blob
         * 下载常被存成没有后缀的文件，所以这里接管：Android 10 以上直接写进
         * 系统的「下载」目录，不用任何权限。返回 false 就让网页退回服务器下载。
         */
        @JavascriptInterface
        fun saveText(name: String, text: String): Boolean {
            val safe = name.replace(Regex("""[\\/:*?"<>|]"""), "_")
                .ifBlank { "car-log.txt" }
            return try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val values = ContentValues().apply {
                        put(MediaStore.Downloads.DISPLAY_NAME, safe)
                        put(MediaStore.Downloads.MIME_TYPE, "text/plain")
                        put(MediaStore.Downloads.IS_PENDING, 1)
                    }
                    val resolver = contentResolver
                    val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values)
                        ?: return false
                    resolver.openOutputStream(uri)?.use { out ->
                        out.write(text.toByteArray(Charsets.UTF_8))
                    } ?: return false
                    values.clear()
                    values.put(MediaStore.Downloads.IS_PENDING, 0)
                    resolver.update(uri, values, null, null)
                    true
                } else {
                    val dir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS
                    )
                    if (!dir.exists()) dir.mkdirs()
                    File(dir, safe).writeText(text, Charsets.UTF_8)
                    true
                }
            } catch (e: Throwable) {
                false
            }
        }

        /**
         * 原生剪贴板。http 页面没有 navigator.clipboard，execCommand 在 WebView 里
         * 又常常静默失败，日志「复制」按钮就点不动。这里直接写系统剪贴板。
         */
        @JavascriptInterface
        fun copy(text: String): Boolean {
            return try {
                val cm = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                cm.setPrimaryClip(ClipData.newPlainText("车机遥控", text))
                true
            } catch (t: Throwable) {
                false
            }
        }

        /** 网页拿不到可用的下载（视频 / 日志），统一从这里转系统下载器。 */
        @JavascriptInterface
        fun download(url: String, name: String) {
            runOnUiThread {
                val cd = if (name.isBlank()) null else "filename=\"$name\""
                enqueueDownload(url, null, cd, null)
            }
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
