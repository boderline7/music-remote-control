package com.cloudmusic.car.remote

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.cloudmusic.car.R
import java.io.ByteArrayOutputStream
import java.net.Socket

/**
 * 同一个端口上的静态网页：手机浏览器访问 `http://carremote.local:18765/` 直接拿到的遥控器页面。
 *
 * 和 WebSocket 共用 18765，靠请求头里有没有 `Sec-WebSocket-Key` 分流（见 WsServer）。
 * 页面本身在 assets/remote.html，全部内联，没有外链资源，弱网也一次加载完。
 */
object RemoteWeb {

    private const val TAG = RemoteConfig.TAG
    private const val ASSET_PAGE = "remote.html"

    /** 页面里的占位符：下发时换成请求 Host，手机端 JS 全挂也点得动。 */
    private const val HOST_TAG = "{{HOST}}"

    private var pageCache: ByteArray? = null
    private var pageKey: String? = null
    private var rawPage: String? = null
    private var iconCache: ByteArray? = null

    /** @param head 已经读到的完整请求头（含结尾空行）。 */
    fun handle(context: Context?, head: String, socket: Socket) {
        var path = "/"
        var host = ""
        for (line in head.lineSequence()) {
            when {
                line.startsWith("GET ") || line.startsWith("POST ") ->
                    path = line.split(' ').getOrNull(1)?.substringBefore('?') ?: "/"
                line.startsWith("Host:", true) -> host = line.substringAfter(':').trim()
            }
        }

        val response: ByteArray = when {
            path == "/" || path == "/index.html" -> page(context, host)
            // 车控页拿它探活：18765 在不在，一个空响应就够了
            path == "/ping" -> text("ok", "200 OK")
            // Web 设置面板的音乐那一路：状态 / 诊断 / 采样日志。
            // 18766 网关把 /api/* 原样转过来，所以从统一入口进来也是这三个接口。
            path == "/api/status" -> response(
                "application/json; charset=utf-8",
                RemoteServer.statusJson().toByteArray(Charsets.UTF_8),
            )
            path == "/api/diag" -> download("music-diag.txt", RemoteServer.diagText())
            path == "/api/logs" -> download("music-log.txt", RemoteHealth.logsText())
            path == "/icon.png" -> icon(context)
            path == "/favicon.ico" -> icon(context)
            else -> text("404 Not Found", "404 Not Found")
        }

        runCatching {
            socket.getOutputStream().apply {
                write(response)
                flush()
            }
        }.onFailure { Log.d(TAG, "http write failed: ${it.message}") }
        runCatching { socket.close() }
    }

    private fun page(context: Context?, host: String): ByteArray {
        // 同一个 host 直接复用整包响应；换 host（127.0.0.1 / 局域网 IP）才重拼一次
        if (pageCache != null && pageKey == host) return pageCache!!

        var raw = rawPage
        if (raw == null) {
            raw = runCatching {
                context?.assets?.open(ASSET_PAGE)?.use { it.readBytes() }
            }.getOrNull()?.toString(Charsets.UTF_8)
            if (raw.isNullOrEmpty()) {
                Log.w(TAG, "assets/$ASSET_PAGE 读取失败")
                return text("遥控页面缺失", "500 Internal Server Error")
            }
            rawPage = raw
        }

        val body = fill(raw, host).toByteArray(Charsets.UTF_8)
        val bytes = response("text/html; charset=utf-8", body)
        pageCache = bytes
        pageKey = host
        return bytes
    }

    /**
     * 把 {{HOST}} 换成请求里的主机名。Host 是客户端给的，只放行 IP / 域名会用到的字符，
     * 顺手去掉端口；换不到就退回 127.0.0.1 —— 车机自己访问永远成立。
     */
    private fun fill(page: String, host: String): String {
        if (!page.contains(HOST_TAG)) return page
        val clean = host.trim().substringBefore(':')
            .filter { it.isLetterOrDigit() || it == '.' || it == '-' }
        return page.replace(HOST_TAG, clean.ifEmpty { "127.0.0.1" })
    }

    /** iOS「添加到主屏幕」用的图标，取自官方那张网易云图标。 */
    private fun icon(context: Context?): ByteArray {
        iconCache?.let { return it }
        val ctx = context ?: return text("no icon", "404")
        val bytes = runCatching {
            // 原图 1254×1254，直接整张解码要 6 MB；先按 1/4 采样，缩到 180 足够清晰
            val options = BitmapFactory.Options().apply { inSampleSize = 4 }
            val src = BitmapFactory.decodeResource(ctx.resources, R.drawable.ic_cloudmusic_app, options)
                ?: return@runCatching null
            val size = 180
            val scaled = Bitmap.createScaledBitmap(src, size, size, true)
            val out = ByteArrayOutputStream()
            scaled.compress(Bitmap.CompressFormat.PNG, 100, out)
            if (scaled !== src) scaled.recycle()
            src.recycle()
            out.toByteArray()
        }.getOrNull()
        if (bytes == null) return text("no icon", "404")
        iconCache = response("image/png", bytes, cache = "public, max-age=86400")
        return iconCache!!
    }

    private fun text(body: String, status: String): ByteArray =
        response("text/plain; charset=utf-8", body.toByteArray(Charsets.UTF_8), status)

    /** 网页「下载」的直出兜底：带 attachment 头，浏览器存下来的就是正常 txt。 */
    private fun download(name: String, body: String): ByteArray =
        response("text/plain; charset=utf-8", body.toByteArray(Charsets.UTF_8), attachment = name)

    private fun response(
        contentType: String,
        body: ByteArray,
        status: String = "200 OK",
        cache: String = "no-store",
        attachment: String? = null,
    ): ByteArray {
        val head = buildString {
            append("HTTP/1.1 ").append(status).append("\r\n")
            append("Content-Type: ").append(contentType).append("\r\n")
            append("Content-Length: ").append(body.size).append("\r\n")
            if (attachment != null) {
                append("Content-Disposition: attachment; filename=\"").append(attachment).append("\"\r\n")
            }
            append("Access-Control-Allow-Origin: *\r\n")
            append("Cache-Control: ").append(cache).append("\r\n")
            append("Connection: close\r\n\r\n")
        }
        return head.toByteArray(Charsets.US_ASCII) + body
    }
}
