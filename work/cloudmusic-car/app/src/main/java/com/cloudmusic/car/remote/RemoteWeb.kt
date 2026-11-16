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

    private var pageCache: ByteArray? = null
    private var iconCache: ByteArray? = null

    /** @param head 已经读到的完整请求头（含结尾空行）。 */
    fun handle(context: Context?, head: String, socket: Socket) {
        val path = head.lineSequence().firstOrNull()
            ?.split(' ')
            ?.getOrNull(1)
            ?.substringBefore('?')
            ?: "/"

        val response: ByteArray = when {
            path == "/" || path == "/index.html" -> page(context)
            path == "/icon.png" -> icon(context)
            path == "/favicon.ico" -> icon(context)
            else -> text("404 Not Found", "404")
        }

        runCatching {
            socket.getOutputStream().apply {
                write(response)
                flush()
            }
        }.onFailure { Log.d(TAG, "http write failed: ${it.message}") }
        runCatching { socket.close() }
    }

    private fun page(context: Context?): ByteArray {
        pageCache?.let { return it }
        val bytes = runCatching {
            context?.assets?.open(ASSET_PAGE)?.use { it.readBytes() }
        }.getOrNull()
        if (bytes == null || bytes.isEmpty()) {
            Log.w(TAG, "assets/$ASSET_PAGE 读取失败")
            return text("遥控页面缺失", "500")
        }
        pageCache = response("text/html; charset=utf-8", bytes)
        return pageCache!!
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

    private fun response(
        contentType: String,
        body: ByteArray,
        status: String = "200 OK",
        cache: String = "no-store",
    ): ByteArray {
        val head = buildString {
            append("HTTP/1.1 ").append(status).append("\r\n")
            append("Content-Type: ").append(contentType).append("\r\n")
            append("Content-Length: ").append(body.size).append("\r\n")
            append("Cache-Control: ").append(cache).append("\r\n")
            append("Connection: close\r\n\r\n")
        }
        return head.toByteArray(Charsets.US_ASCII) + body
    }
}
