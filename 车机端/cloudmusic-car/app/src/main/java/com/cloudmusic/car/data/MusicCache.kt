package com.cloudmusic.car.data

import com.paopao.music.nowplaying.LyricWord
import android.content.Context
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.disk.DiskCache
import com.cloudmusic.car.api.LyricLine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

data class MusicCacheStats(
    val audioBytes: Long = 0,
    val imageBytes: Long = 0,
    val lyricBytes: Long = 0,
) {
    val totalBytes: Long get() = audioBytes + imageBytes + lyricBytes
}

/**
 * 缓存实际采用的额度。设置页要显示「音乐 X · 歌词 Y」，所以必须能读到真实值，
 * 不能在 UI 里再算一遍 9:1 —— 两处算法各自演化，迟早对不上。
 */
data class MusicCacheLimits(
    val audioBytes: Long = 0,
    val lyricBytes: Long = 0,
) {
    val budgetGb: Int get() = (audioBytes / (1024L * 1024 * 1024)).toInt()
}

/** Persistent, bounded cache shared by playback, artwork and lyrics. */
@androidx.annotation.OptIn(markerClass = [UnstableApi::class])
@kotlin.OptIn(ExperimentalCoilApi::class)
object MusicCache {
    /** 封面单独一档，不占歌曲预算：一张图几十 KB，跟无损音频不是一个量级。 */
    private const val IMAGE_MAX_BYTES = 256L * 1024 * 1024
    private const val LOW_SPACE_BYTES = 1L * 1024 * 1024 * 1024
    private const val TARGET_FREE_BYTES = 2L * 1024 * 1024 * 1024

    /** 歌词在总预算里的占比：10%，但不少于 128 MB、不多于 1 GB。 */
    private const val LYRIC_SHARE = 10
    private const val LYRIC_MIN_BYTES = 128L * 1024 * 1024
    private const val LYRIC_MAX_BYTES = 1024L * 1024 * 1024

    private lateinit var root: File
    private lateinit var lyricDir: File
    private lateinit var artworkLoader: ImageLoader
    private var initialized = false

    private var audioLimitBytes = 5L * 1024 * 1024 * 1024
    private var lyricLimitBytes = 512L * 1024 * 1024

    private val _limits = MutableStateFlow(MusicCacheLimits())
    val limits: StateFlow<MusicCacheLimits> = _limits.asStateFlow()

    lateinit var audio: SimpleCache
        private set

    private val _stats = MutableStateFlow(MusicCacheStats())
    val stats: StateFlow<MusicCacheStats> = _stats.asStateFlow()

    @Synchronized
    fun init(context: Context) {
        if (initialized) return
        val appContext = context.applicationContext
        root = File(appContext.cacheDir, "cloudmusic").apply { mkdirs() }
        lyricDir = File(root, "lyrics").apply { mkdirs() }

        // 预算从设置读，9:1 分给音频和歌词。只在启动时算一次 —— SimpleCache 的上限
        // 是构造参数，播放中重建会把正在放的歌打断，所以改设置要下次启动才生效。
        val budget = Settings.cacheBudgetGb.value.coerceIn(Settings.MIN_CACHE_GB, Settings.MAX_CACHE_GB) *
            1024L * 1024 * 1024
        audioLimitBytes = budget
        lyricLimitBytes = (budget / LYRIC_SHARE).coerceIn(LYRIC_MIN_BYTES, LYRIC_MAX_BYTES)

        audio = SimpleCache(
            File(root, "audio").apply { mkdirs() },
            LeastRecentlyUsedCacheEvictor(audioLimitBytes),
            StandaloneDatabaseProvider(appContext),
        )
        artworkLoader = ImageLoader.Builder(appContext)
            .okHttpClient(com.cloudmusic.car.api.NeteaseClient.http)
            .diskCache {
                DiskCache.Builder()
                    .directory(File(root, "images"))
                    .maxSizeBytes(IMAGE_MAX_BYTES)
                    .build()
            }
            .crossfade(true)
            .build()
        initialized = true
        _limits.value = MusicCacheLimits(audioLimitBytes, lyricLimitBytes)
        trimLyrics() // 上次可能把预算调小了，启动时先收一遍
        _stats.value = snapshot()
    }

    /**
     * 车机存储快满时的保护，每首歌开始加载前在加载线程调用：剩余空间不到 1GB 就从最久没听的整首删，
     * 删到剩 2GB 为止（正要放的这首不删）。
     */
    @Synchronized
    fun ensureDiskRoom(keepKey: String?) {
        if (!initialized) return
        val dir = File(root, "audio")
        if (dir.usableSpace >= LOW_SPACE_BYTES) return
        val oldestFirst = runCatching {
            audio.keys.toList()
                .map { key -> key to (audio.getCachedSpans(key).maxOfOrNull { it.lastTouchTimestamp } ?: 0L) }
                .sortedBy { it.second }
        }.getOrDefault(emptyList())
        for ((key, _) in oldestFirst) {
            if (dir.usableSpace >= TARGET_FREE_BYTES) break
            if (key != keepKey) runCatching { audio.removeResource(key) }
        }
        _stats.value = snapshot()
    }

    /**
     * 功能包卸载/升级时由运行时调用。SimpleCache 的目录锁记在桌面进程共享的类上，
     * 不释放的话同一进程里新装的包再开这个目录会直接失败。
     */
    @Synchronized
    fun close() {
        if (!initialized) return
        runCatching { audio.release() }
        initialized = false
    }

    fun imageLoader(context: Context): ImageLoader {
        init(context)
        return artworkLoader
    }

    fun readLyrics(trackId: Long): List<LyricLine>? = runCatching {
        val file = File(lyricDir, "$trackId.v3.json")
        val array = JSONArray(file.readText())
        // 命中就把时间戳往后推，这样淘汰时按「真正最久没听过」而不是「最久没写过」排。
        // 一首老歌反复听，它的歌词不该因为写于半年前就先被删掉。
        file.setLastModified(System.currentTimeMillis())
        buildList {
            for (index in 0 until array.length()) {
                val item = array.getJSONObject(index)
                add(
                    LyricLine(
                        timeMs = item.getLong("time"),
                        text = item.getString("text"),
                        translation = item.optString("translation").takeIf { it.isNotEmpty() },
                        words = item.optJSONArray("words").toLyricWords(),
                    ),
                )
            }
        }
    }.getOrNull()

    fun writeLyrics(trackId: Long, lines: List<LyricLine>) {
        if (lines.isEmpty()) return
        val target = File(lyricDir, "$trackId.v3.json")
        val temp = File(lyricDir, "$trackId.tmp")
        val array = JSONArray().apply {
            lines.forEach { line ->
                put(
                    JSONObject()
                        .put("time", line.timeMs)
                        .put("text", line.text)
                        .put("translation", line.translation.orEmpty())
                        .apply { if (line.words.isNotEmpty()) put("words", line.words.toJson()) },
                )
            }
        }
        runCatching {
            temp.writeText(array.toString())
            check(temp.renameTo(target)) { "Unable to publish lyric cache" }
        }.onFailure { temp.delete() }
        trimLyrics(keep = target.name)
    }

    /**
     * 歌词淘汰。这份缓存以前只有「清理缓存」按钮能删，长期不点就无限长 ——
     * 单份只有几 KB，但几千首累积起来也能到几十兆，而且没有任何上限约束。
     *
     * 按「最久没被读写」排序删（lastModified 会在读命中时刷新，见 [readLyrics]），
     * 删到预算的 90% 就停，留一点余量避免每次写都触发扫描。
     */
    @Synchronized
    fun trimLyrics(keep: String? = null) {
        if (!::lyricDir.isInitialized) return
        val files = lyricDir.listFiles()?.filter { it.isFile } ?: return
        var total = files.sumOf { it.length() }
        if (total <= lyricLimitBytes) return
        val target = (lyricLimitBytes * 9) / 10
        files.sortedBy { it.lastModified() }.forEach { file ->
            if (total <= target) return@forEach
            if (file.name == keep) return@forEach
            val size = file.length()
            if (runCatching { file.delete() }.getOrDefault(false)) total -= size
        }
    }

    suspend fun refreshStats() = withContext(Dispatchers.IO) {
        if (!initialized) return@withContext
        _stats.value = snapshot()
    }

    suspend fun clear() = withContext(Dispatchers.IO) {
        if (!initialized) return@withContext
        audio.keys.toList().forEach { key -> runCatching { audio.removeResource(key) } }
        artworkLoader.memoryCache?.clear()
        artworkLoader.diskCache?.clear()
        lyricDir.listFiles().orEmpty().forEach(File::delete)
        _stats.value = MusicCacheStats()
    }

    private fun snapshot() = MusicCacheStats(
        audioBytes = audio.cacheSpace,
        imageBytes = File(root, "images").sizeOnDisk(),
        lyricBytes = lyricDir.sizeOnDisk(),
    )
}

fun formatCacheSize(bytes: Long): String = when {
    bytes >= 1024L * 1024 * 1024 -> "%.1f GB".format(bytes / (1024.0 * 1024 * 1024))
    bytes >= 1024L * 1024 -> "%.1f MB".format(bytes / (1024.0 * 1024))
    bytes >= 1024L -> "%.1f KB".format(bytes / 1024.0)
    else -> "$bytes B"
}

private fun File.sizeOnDisk(): Long = when {
    isFile -> length()
    isDirectory -> listFiles().orEmpty().sumOf(File::sizeOnDisk)
    else -> 0L
}

/** 逐字片段存成 [文本, 起点, 终点] 三元组，省空间。 */
private fun List<LyricWord>.toJson(): JSONArray = JSONArray().apply {
    forEach { put(JSONArray().put(it.text).put(it.startMs).put(it.endMs)) }
}

private fun JSONArray?.toLyricWords(): List<LyricWord> {
    if (this == null) return emptyList()
    return (0 until length()).mapNotNull { i ->
        val w = optJSONArray(i) ?: return@mapNotNull null
        LyricWord(w.optString(0), w.optLong(1), w.optLong(2)).takeIf { it.text.isNotEmpty() }
    }
}
