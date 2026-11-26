package com.cloudmusic.car.player

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.annotation.OptIn
import com.cloudmusic.car.remote.RemoteHealth
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.ForwardingPlayer
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.ResolvingDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.cloudmusic.car.compat.LunaCompat
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import com.cloudmusic.car.api.LyricLine
import com.cloudmusic.car.api.LyricsParser
import com.cloudmusic.car.api.NeteaseApi
import com.cloudmusic.car.api.NeteaseClient
import com.cloudmusic.car.api.Track
import com.cloudmusic.car.api.sized
import com.cloudmusic.car.data.AccountStore
import com.cloudmusic.car.data.AudioQuality
import com.cloudmusic.car.data.MusicCache
import com.cloudmusic.car.data.Settings
import com.paopao.music.nowplaying.KrcSource
import com.paopao.music.nowplaying.METADATA_KEY_KRC_LYRIC
import com.paopao.music.nowplaying.NowPlayingNeighbors
import com.paopao.music.nowplaying.PlayModes
import com.paopao.music.nowplaying.WordLyricsParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

enum class PlayMode { NORMAL, FM, HEARTBEAT }

data class PlaybackNotice(val kind: String, val text: String)

/**
 * 全局播放器。队列直接交给 ExoPlayer（通知栏、方向盘按键的上一首/下一首因此天然可用），
 * 每首歌以 `cloudmusic://song/<id>` 占位，真正加载时才通过 [ResolvingDataSource] 换成云音乐的播放地址。
 */
@OptIn(UnstableApi::class)
object PlayerHub {
    private const val TAG = "CloudMusic"

    private class UnplayableTrackException(id: Long) : IOException("no url for $id")

    private const val SCHEME = "cloudmusic"
    private const val URL_TTL_MS = 15 * 60 * 1000L
    private const val PREFETCH_MIN_BUFFER_MS = 30_000L
    private const val PREFETCH_RETRY_MS = 2 * 60 * 1000L
    private const val METADATA_KEY_LYRIC = "android.media.metadata.LYRIC"

    private var _sessionPlayer: ForwardingPlayer? = null

    /**
     * 交给 MediaSession 的包装。方向盘 / 通知栏 / 桌面底栏的媒体键走 Player.next()，
     * 直接落到裸 ExoPlayer 上，不经过 [next] / [previous]；这里把两条切歌入口导回本类，
     * 才能和遥控器共用「暂停时自动起播」。其它能力全部透传，事件监听照旧转发。
     */
    val sessionPlayer: Player
        get() = _sessionPlayer ?: error("PlayerHub.init 尚未调用")

    private var scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    lateinit var player: ExoPlayer
        private set

    // ---- 诊断快照：只在主线程写，导出线程随便读 ----
    // ExoPlayer 的 Player 接口要求在应用线程调用，网页导出线程不能直接摸它，
    // 所以状态在主线程抄一份到 volatile 字段里。
    @Volatile private var diagState: Int = Player.STATE_IDLE
    @Volatile private var diagPlayWhenReady: Boolean = false
    @Volatile private var diagItems: Int = 0
    @Volatile private var diagIsPlaying: Boolean = false
    /** 本进程是否真的进过 PLAYING：系统登记「媒体键目标会话」用的就是这个信号。 */
    @Volatile var playedSinceBoot: Boolean = false
        private set

    private fun refreshDiagState() {
        if (!initialized) return
        runCatching {
            diagState = player.playbackState
            diagPlayWhenReady = player.playWhenReady
            diagItems = player.mediaItemCount
            diagIsPlaying = player.isPlaying
            if (diagIsPlaying) playedSinceBoot = true
        }
    }

    /** 诊断导出一行：会话侧最能说明「方控为什么没反应」的几项。 */
    fun diagLine(): String {
        val stateName = when (diagState) {
            Player.STATE_IDLE -> "IDLE"
            Player.STATE_BUFFERING -> "BUFFERING"
            Player.STATE_READY -> "READY"
            Player.STATE_ENDED -> "ENDED"
            else -> "?"
        }
        return "state=$stateName playWhenReady=$diagPlayWhenReady playing=$diagIsPlaying " +
            "队列=$diagItems 本进程进过播放=$playedSinceBoot"
    }

    // 手动切歌的补播代次：每次切歌 +1，延迟任务只认自己那一代，
    // 避免连续快切时旧任务落到新歌上。用户在这期间主动暂停也会 +1 让补播作废。
    private var cutGeneration = 0
    private var cutResumeJob: Job? = null
    private const val CUT_RESUME_DELAY_MS = 220L

    private val tracks = ConcurrentHashMap<Long, Track>()
    private data class ResolvedUrl(val url: String, val resolvedAt: Long, val cacheKey: String)

    private val urlCache = ConcurrentHashMap<String, ResolvedUrl>()
    private lateinit var cacheDataSourceFactory: DataSource.Factory
    private var prefetchJob: Job? = null
    private var prefetchIds: List<Long> = emptyList()
    @Volatile private var prefetchGeneration = 0L
    private val prefetched = ConcurrentHashMap.newKeySet<String>()
    private val prefetchFailures = ConcurrentHashMap<String, Long>()
    private var prefetchSupported = false

    private val _current = MutableStateFlow<Track?>(null)
    val current: StateFlow<Track?> = _current.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()
    private val _isBuffering = MutableStateFlow(false)
    val isBuffering: StateFlow<Boolean> = _isBuffering.asStateFlow()

    /** 按歌单原顺序排列的队列（随机模式下也不打乱），以及当前歌曲在其中的位置。 */
    private val _queue = MutableStateFlow<List<Pair<Int, Track>>>(emptyList())
    val queue: StateFlow<List<Pair<Int, Track>>> = _queue.asStateFlow()

    private val _mode = MutableStateFlow(PlayMode.NORMAL)
    val mode: StateFlow<PlayMode> = _mode.asStateFlow()

    private val _sourceName = MutableStateFlow<String?>(null)
    val sourceName: StateFlow<String?> = _sourceName.asStateFlow()

    private val _playMode = MutableStateFlow(PlayModes.SEQUENTIAL)
    val playMode: StateFlow<Int> = _playMode.asStateFlow()

    private val _neighbors = MutableStateFlow(NowPlayingNeighbors(null, null))
    val neighbors: StateFlow<NowPlayingNeighbors> = _neighbors.asStateFlow()

    private val _lyrics = MutableStateFlow<List<LyricLine>>(emptyList())
    val lyrics: StateFlow<List<LyricLine>> = _lyrics.asStateFlow()

    private val _status = MutableStateFlow<PlaybackNotice?>(null)
    val status: StateFlow<PlaybackNotice?> = _status.asStateFlow()

    private val _messages = MutableSharedFlow<String>(extraBufferCapacity = 8)
    val messages: SharedFlow<String> = _messages

    private var sourceId = 0L
    private var lyricsJob: Job? = null
    private var fmLoading = false
    private var consecutiveFailures = 0
    private var connectivity: ConnectivityManager? = null
    private var networkCallback: ConnectivityManager.NetworkCallback? = null
    private var waitingMediaId: String? = null
    private var waitingPosition = 0L
    private var waitingForRecovery = false
    private var progressMediaId: String? = null
    private var progressPosition = 0L
    private var continuousPlaybackMs = 0L
    private var lastTrack: Track? = null
    private var initialized = false

    @Synchronized
    fun init(context: Context) {
        if (initialized) return
        initialized = true
        connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        MusicCache.init(context)
        val http = OkHttpDataSource.Factory(NeteaseClient.http)
        val upstream = DefaultDataSource.Factory(context, http)
        val cached = CacheDataSource.Factory()
            .setCache(MusicCache.audio)
            .setUpstreamDataSourceFactory(upstream)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
        cacheDataSourceFactory = cached
        // Older hosts kept the factory names but obfuscated the return types in R8.
        prefetchSupported = runCatching {
            val dataSource = Class.forName("androidx.media3.datasource.DataSource")
            val cachedSource = Class.forName("androidx.media3.datasource.cache.CacheDataSource")
            DataSource.Factory::class.java.getMethod("createDataSource").returnType == dataSource &&
                CacheDataSource.Factory::class.java.methods.any {
                    it.name == "createDataSource" && it.returnType == cachedSource
                }
        }.getOrDefault(false)
        val resolving = ResolvingDataSource.Factory(cached) { spec -> resolve(spec) }
        player = ExoPlayer.Builder(context)
            .setMediaSourceFactory(DefaultMediaSourceFactory(resolving))
            .setAudioAttributes(
                AudioAttributes.Builder().setUsage(C.USAGE_MEDIA).setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).build(),
                /* handleAudioFocus = */ true,
            )
            .setHandleAudioBecomingNoisy(true)
            .setWakeMode(C.WAKE_MODE_NETWORK)
            // 「上一首」永远切到上一首，不按默认规则放过 3 秒就改成从头重放；桌面底栏、方向盘走的也是它。
            .setMaxSeekToPreviousPositionMs(Long.MAX_VALUE)
            .build()
        player.addListener(listener)
        _sessionPlayer = object : ForwardingPlayer(player) {
            override fun next() {
                RemoteHealth.log("媒体键 next → PlayerHub.next")
                this@PlayerHub.next()
            }
            override fun previous() {
                RemoteHealth.log("媒体键 previous → PlayerHub.previous")
                this@PlayerHub.previous()
            }
            override fun seekToNextMediaItem() {
                RemoteHealth.log("媒体键 seekToNext → PlayerHub.next")
                this@PlayerHub.next()
            }
            override fun seekToPreviousMediaItem() {
                RemoteHealth.log("媒体键 seekToPrev → PlayerHub.previous")
                this@PlayerHub.previous()
            }
        }
        PlaybackSnapshotStore.init(context)
        _playMode.value = PlaybackSnapshotStore.restorePlayMode()
        applyPlayMode()
        PlaybackSnapshotStore.restore()?.let(::restorePlayback)
        scope.launch {
            while (true) {
                delay(5_000)
                updatePlaybackProgress()
                persistPosition()
                updatePrefetch()
                refreshDiagState()
            }
        }
        // 车机助手（com.navi.tool）的「汽水」通道适配，见 LunaCompat 注释：
        // 助手那边的酷我槽位（source 1/4）直连本包，歌词由本应用自己提供，不联网搜词。
        LunaCompat.start(context)
    }

    @Synchronized
    fun release() {
        if (!initialized) return
        LunaCompat.stop()
        cancelNetworkWait()
        stopPrefetch()
        _status.value = null
        _isBuffering.value = false
        connectivity = null
        runCatching(::persistPosition)
        lyricsJob?.cancel()
        lyricsJob = null
        scope.cancel()
        runCatching { player.removeListener(listener) }
        runCatching { player.release() }
        _sessionPlayer = null
        scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        fmLoading = false
        _isPlaying.value = false
        initialized = false
    }

    private fun restorePlayback(snapshot: PlaybackSnapshot) {
        sourceId = snapshot.sourceId
        _mode.value = snapshot.mode
        _sourceName.value = snapshot.sourceName
        applyPlayMode()
        player.setMediaItems(snapshot.tracks.map(::mediaItem), snapshot.currentIndex, snapshot.positionMs)
        player.prepare()
        player.pause()
    }

    fun toast(message: String) {
        _messages.tryEmit(message)
    }

    // ---------- 地址解析（运行在加载线程） ----------

    private fun resolve(spec: DataSpec): DataSpec {
        if (spec.uri.scheme != SCHEME) return spec
        val id = spec.uri.lastPathSegment?.toLongOrNull() ?: throw IOException("bad uri")
        val requestedQuality = Settings.quality.value
        val requestKey = "$id:${requestedQuality.level}"
        urlCache[requestKey]?.let { cached ->
            if (System.currentTimeMillis() - cached.resolvedAt < URL_TTL_MS) {
                return spec.buildUpon()
                    .setUri(Uri.parse(cached.url))
                    .setKey(cached.cacheKey)
                    .build()
            }
        }
        // 过期的 MUSIC_U 取地址不报 301，只会悄悄降成试听/空地址，所以取地址前补一次到期续签
        NeteaseClient.refreshIfDueBlocking()
        var quality = requestedQuality
        var result = NeteaseApi.songUrlBlocking(id, quality.level)
        if (result.url == null && quality != AudioQuality.STANDARD) {
            quality = AudioQuality.STANDARD
            result = NeteaseApi.songUrlBlocking(id, AudioQuality.STANDARD.level)
        }
        val url = result.url ?: throw UnplayableTrackException(id)
        if (result.isTrial) toast("《${tracks[id]?.name.orEmpty()}》为 VIP 歌曲，当前为试听片段")
        val cacheKey = "song-$id-${quality.level}"
        // 存储快满先删最久没听的；真写不进去时缓存层会自动改走网络，不会跳歌
        MusicCache.ensureDiskRoom(cacheKey)
        urlCache[requestKey] = ResolvedUrl(url, System.currentTimeMillis(), cacheKey)
        return spec.buildUpon()
            .setUri(Uri.parse(url))
            .setKey(cacheKey)
            .build()
    }

    /** Only read ahead when playback already has enough audio to stay ahead of the download. */
    private fun updatePrefetch() {
        if (!prefetchSupported) return
        val bufferedAhead = player.bufferedPosition - player.currentPosition
        val ready = player.playWhenReady && player.playbackState == Player.STATE_READY &&
            (bufferedAhead >= PREFETCH_MIN_BUFFER_MS ||
                (player.duration > 0 && player.bufferedPosition >= player.duration))
        if (!ready) {
            stopPrefetch()
            return
        }
        val ids = upcomingTrackIds()
        if (ids.isEmpty()) {
            stopPrefetch()
            return
        }
        // Quality and play order are part of the work identity. A new selection cancels the old download.
        val workKey = ids.map { "$it:${Settings.quality.value.level}" }
        if (prefetchJob?.isActive == true && prefetchIds == ids && prefetchQuality == Settings.quality.value.level) return
        stopPrefetch()
        prefetchIds = ids
        prefetchQuality = Settings.quality.value.level
        val generation = prefetchGeneration
        prefetchJob = scope.launch(Dispatchers.IO) {
            for ((index, id) in ids.withIndex()) {
                if (generation != prefetchGeneration) break
                val key = workKey[index]
                if (key in prefetched) continue
                val failedAt = prefetchFailures[key]
                if (failedAt != null && System.currentTimeMillis() - failedAt < PREFETCH_RETRY_MS) continue
                Log.i("CloudPlayer", "prefetch start id=$id quality=${Settings.quality.value.level}")
                val success = runCatching { cacheWholeTrack(id, generation) }
                    .onFailure { Log.w("CloudPlayer", "prefetch failed id=$id", it) }
                    .getOrDefault(false)
                if (generation == prefetchGeneration) {
                    if (success) {
                        Log.i("CloudPlayer", "prefetch complete id=$id")
                        prefetchFailures.remove(key)
                        prefetched.add(key)
                    } else {
                        prefetchFailures[key] = System.currentTimeMillis()
                    }
                }
            }
        }
    }

    private var prefetchQuality: String? = null

    private fun stopPrefetch() {
        prefetchGeneration++
        prefetchJob?.cancel()
        prefetchJob = null
        prefetchIds = emptyList()
        prefetchQuality = null
    }

    private fun upcomingTrackIds(): List<Long> {
        val timeline = player.currentTimeline
        if (timeline.isEmpty) return emptyList()
        val repeatMode = if (player.repeatMode == Player.REPEAT_MODE_ONE) Player.REPEAT_MODE_ALL else player.repeatMode
        var index = player.currentMediaItemIndex
        val currentId = player.currentMediaItem?.mediaId
        val found = LinkedHashSet<Long>()
        repeat(timeline.windowCount.coerceAtMost(4)) {
            index = timeline.getNextWindowIndex(index, repeatMode, player.shuffleModeEnabled)
            if (index == C.INDEX_UNSET) return found.toList()
            val id = player.getMediaItemAt(index).mediaId.toLongOrNull() ?: return@repeat
            if (id.toString() != currentId) found.add(id)
            if (found.size == 3) return found.toList()
        }
        return found.toList()
    }

    private fun cacheWholeTrack(id: Long, generation: Long): Boolean {
        if (tracks[id] == null) return false
        val spec = resolve(DataSpec(Uri.parse("$SCHEME://song/$id")))
        if (spec.key == null) return false
        val source = cacheDataSourceFactory.createDataSource()
        return try {
            source.open(spec)
            val bytes = ByteArray(64 * 1024)
            while (generation == prefetchGeneration) {
                if (source.read(bytes, 0, bytes.size) == C.RESULT_END_OF_INPUT) return true
            }
            false
        } finally {
            source.close()
        }
    }

    private fun mediaItem(track: Track): MediaItem {
        tracks[track.id] = track
        return MediaItem.Builder()
            .setMediaId(track.id.toString())
            .setUri("$SCHEME://song/${track.id}")
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(track.name)
                    .setArtist(track.artistNames)
                    .setAlbumTitle(track.album.name)
                    .setArtworkUri(track.album.picUrl.sized(512)?.let(Uri::parse))
                    .build(),
            )
            .build()
    }

    // ---------- 播放入口 ----------

    /**
     * 播放一组歌曲。不可播放（VIP/无版权等）的歌曲会被跳过。
     * @param start 从哪首开始；为 null 且 [shuffle] 时随机开始。
     */
    fun play(
        list: List<Track>,
        start: Track? = null,
        shuffle: Boolean = false,
        source: String? = null,
        sourceId: Long = 0,
        mode: PlayMode = PlayMode.NORMAL,
    ) {
        if (start != null) {
            AccountStore.unplayableReason(start)?.let {
                toast("《${start.name}》无法播放：$it")
                return
            }
        }
        val playable = list.filter { AccountStore.unplayableReason(it) == null }
        if (playable.isEmpty()) {
            toast("没有可播放的歌曲")
            return
        }
        val startIndex = when {
            start != null -> playable.indexOfFirst { it.id == start.id }.coerceAtLeast(0)
            shuffle -> playable.indices.random()
            else -> 0
        }
        this.sourceId = sourceId
        cancelNetworkWait()
        _status.value = null
        _mode.value = mode
        _sourceName.value = source
        consecutiveFailures = 0
        if (shuffle) setPlayMode(PlayModes.SHUFFLE) else applyPlayMode()
        player.setMediaItems(playable.map(::mediaItem), startIndex, 0)
        persistQueue(playable, startIndex)
        player.prepare()
        player.play()
    }

    fun startFm() {
        scope.launch {
            runCatching { NeteaseApi.personalFm() }
                .onSuccess { play(it, source = "私人 FM", mode = PlayMode.FM) }
                .onFailure { toast(it.message ?: "私人 FM 加载失败") }
        }
    }

    fun startHeartbeat() {
        val liked = AccountStore.likedPlaylist
        val seed = AccountStore.likedIds.value.randomOrNull()
        if (liked == null || seed == null) {
            toast("先去红心几首歌，才能开启心动模式")
            return
        }
        scope.launch {
            runCatching { NeteaseApi.intelligenceList(seed, liked.id) }
                .onSuccess { play(it, source = "心动模式", sourceId = liked.id, mode = PlayMode.HEARTBEAT) }
                .onFailure { toast(it.message ?: "心动模式加载失败") }
        }
    }

    // ---------- 控制 ----------

    fun togglePlay() {
        if (waitingMediaId != null) cancelNetworkWait()
        // 用 playWhenReady 判定意图，不用 isPlaying：后者在 buffering 时会自己翻转，
        // 切歌补播那条 play 指令撞上翻转窗口时，外层判"要播"、这里却判"在播"→ 误按暂停。
        val wasWant = player.playWhenReady
        RemoteHealth.log("togglePlay want=$wasWant isPlaying=${player.isPlaying} state=${player.playbackState}")
        // 用户主动按了播放/暂停就作废进行中的切歌补播，否则补播会把刚按下的暂停冲掉。
        if (wasWant) {
            cutGeneration++
            cutResumeJob?.cancel()
            cutResumeJob = null
            player.pause()
        } else {
            consecutiveFailures = 0
            _status.value = null
            if (player.playbackState == Player.STATE_IDLE) player.prepare()
            player.play()
        }
    }

    /**
     * 手动切歌后起播。ExoPlayer 的 seekTo 只换 item，不动 playWhenReady：
     * 暂停状态下点「下一首」会跳过去但继续停着。这里按「用户想听」补一次 play，
     * 已在播放（含 buffering）时是空操作，不会打断缓冲。
     */
    /** 把 Media3 的 playWhenReady 变更原因翻成可读文字，写进诊断环形日志。 */
    private fun playReason(reason: Int): String = when (reason) {
        Player.PLAY_WHEN_READY_CHANGE_REASON_USER_REQUEST -> "用户指令"
        Player.PLAY_WHEN_READY_CHANGE_REASON_AUDIO_FOCUS_LOSS -> "焦点丢失"
        Player.PLAY_WHEN_READY_CHANGE_REASON_AUDIO_BECOMING_NOISY -> "音频设备拔出"
        Player.PLAY_WHEN_READY_CHANGE_REASON_REMOTE -> "远端媒体键"
        Player.PLAY_WHEN_READY_CHANGE_REASON_END_OF_MEDIA_ITEM -> "播完"
        else -> "原因$reason"
    }

    /**
     * 手动切歌后起播。只记下「这次切歌发生在暂停态」这个意图，真正补播放到延迟任务里做。
     *
     * 不能紧跟 seekTo 立即 play：方向盘那条链（MediaSession 媒体键）切歌时，新 item 还在
     * 切换/加载，紧跟着的 play 会落进这个窗口被冲掉，约一秒后仍是暂停 —— 这正是遥控器
     * （网页端本来就在 150ms 后补一条 play）正常、方向盘不正常的原因。延迟一小段再确认，
     * 两条链就一致了。
     */
    /**
     * 切歌落地的兜底补播：这是所有切歌路径（遥控器/方向盘媒体键/助手广播/网页）
     * 唯一的汇聚点 onMediaItemTransition 调用的，不依赖任何具体入口。
     * 日志实测：方向盘切歌不走 next()/媒体键/助手广播，只在 onMediaItemTransition 留痕，
     * 且落地时 playWhenReady=false、之后再无 play —— 补播必须挂在这里才不会漏。
     */
    private fun armCutResume() {
        if (player.playWhenReady) {
            cutGeneration++
            cutResumeJob?.cancel()
            cutResumeJob = null
            return
        }
        consecutiveFailures = 0
        _status.value = null
        if (player.playbackState == Player.STATE_IDLE) player.prepare()
        // 立即起播；新 item 还在加载时这一下可能被冲掉，靠下面的延迟兜底再确认一次。
        player.play()
        val gen = ++cutGeneration
        cutResumeJob?.cancel()
        cutResumeJob = scope.launch {
            delay(CUT_RESUME_DELAY_MS)
            if (gen != cutGeneration) return@launch
            if (!player.playWhenReady && player.mediaItemCount > 0) {
                RemoteHealth.log("切歌落地兜底：仍暂停，补一条 PLAY")
                player.play()
            }
        }
    }

    private fun resumeIfPaused() {
        if (player.playWhenReady) {
            cutGeneration++
            cutResumeJob?.cancel()
            cutResumeJob = null
            return
        }
        consecutiveFailures = 0
        _status.value = null
        if (player.playbackState == Player.STATE_IDLE) player.prepare()
        // 立即起播，保证绝大多数情况无延迟
        player.play()
        // 延迟兜底：只认这一代、且期间用户没主动暂停（generation 没变）、且仍是暂停态才补。
        val gen = ++cutGeneration
        cutResumeJob?.cancel()
        cutResumeJob = scope.launch {
            delay(CUT_RESUME_DELAY_MS)
            if (gen != cutGeneration) return@launch
            if (!player.playWhenReady && player.mediaItemCount > 0) {
                RemoteHealth.log("切歌补播：延迟确认仍暂停，补一条 PLAY")
                player.play()
            }
        }
    }

    fun next() {
        RemoteHealth.log("PlayerHub.next 被调用 playWhenReady=${player.playWhenReady}")
        cancelNetworkWait()
        _status.value = null
        val index = manualNextIndex()
        if (index != C.INDEX_UNSET) {
            player.seekTo(index, 0)
            resumeIfPaused()
        } else if (_mode.value == PlayMode.FM) {
            extendFm(true)
            resumeIfPaused()
        }
    }

    fun previous() {
        RemoteHealth.log("PlayerHub.previous 被调用 playWhenReady=${player.playWhenReady}")
        cancelNetworkWait()
        _status.value = null
        val index = manualPreviousIndex()
        if (index != C.INDEX_UNSET) player.seekTo(index, 0) else player.seekTo(0)
        resumeIfPaused()
    }

    fun seekTo(ms: Long) {
        cancelNetworkWait()
        player.seekTo(ms)
    }

    fun jumpTo(index: Int) {
        cancelNetworkWait()
        _status.value = null
        consecutiveFailures = 0
        player.seekTo(index, 0)
        player.play()
    }

    /** 顺序播放 → 单曲循环 → 随机播放 → 顺序播放，持久化后重启仍保持。 */
    fun cyclePlayMode() {
        cancelNetworkWait()
        setPlayMode(
            when (_playMode.value) {
                PlayModes.SEQUENTIAL -> PlayModes.REPEAT_ONE
                PlayModes.REPEAT_ONE -> PlayModes.SHUFFLE
                else -> PlayModes.SEQUENTIAL
            },
        )
    }

    /** 遥控端直接指定模式（0 顺序 / 1 单曲循环 / 2 随机）；与 cyclePlayMode 共用持久化和 ExoPlayer 落地。 */
    fun setPlayMode(mode: Int) {
        _playMode.value = mode
        PlaybackSnapshotStore.savePlayMode(mode)
        applyPlayMode()
    }

    /**
     * 播放模式直接落到 ExoPlayer 上，自动切歌、播放失败跳过、通知栏、方向盘、宿主底栏因此都按同一套顺序走。
     * 私人 FM 是边放边续的电台，始终顺序、不循环，播放模式只对普通队列生效。
     */
    private fun applyPlayMode() {
        val radio = _mode.value == PlayMode.FM
        player.shuffleModeEnabled = !radio && _playMode.value == PlayModes.SHUFFLE
        player.repeatMode = when {
            radio -> Player.REPEAT_MODE_OFF
            _playMode.value == PlayModes.REPEAT_ONE -> Player.REPEAT_MODE_ONE
            else -> Player.REPEAT_MODE_ALL
        }
    }

    /** 私人 FM：不喜欢当前歌曲并跳到下一首。 */
    fun fmTrash() {
        val track = _current.value ?: return
        scope.launch { runCatching { NeteaseApi.fmTrash(track.id) } }
        next()
    }

    private fun extendFm(skipAfter: Boolean) {
        if (fmLoading) return
        fmLoading = true
        scope.launch {
            val more = runCatching { NeteaseApi.personalFm() }.getOrDefault(emptyList())
                .filter { AccountStore.unplayableReason(it) == null && it.id !in currentIds() }
            if (more.isNotEmpty() && _mode.value == PlayMode.FM) {
                player.addMediaItems(more.map(::mediaItem))
                persistQueue()
                if (skipAfter) player.seekToNextMediaItem()
            }
            fmLoading = false
        }
    }

    private fun currentIds(): Set<Long> =
        (0 until player.mediaItemCount).mapNotNull { player.getMediaItemAt(it).mediaId.toLongOrNull() }.toSet()

    private fun persistQueue(
        queueTracks: List<Track> = (0 until player.mediaItemCount).mapNotNull {
            player.getMediaItemAt(it).mediaId.toLongOrNull()?.let(tracks::get)
        },
        currentIndex: Int = player.currentMediaItemIndex,
    ) {
        PlaybackSnapshotStore.saveQueue(
            queueTracks,
            currentIndex,
            _sourceName.value,
            sourceId,
            _mode.value,
        )
        persistPosition()
    }

    private fun persistPosition() {
        PlaybackSnapshotStore.savePosition(player.currentMediaItem?.mediaId, player.currentPosition)
    }

    // ---------- 状态同步 ----------

    private fun rebuildQueue() {
        val timeline = player.currentTimeline
        if (timeline.isEmpty) {
            _queue.value = emptyList()
            return
        }
        _queue.value = (0 until timeline.windowCount).mapNotNull { index ->
            tracks[player.getMediaItemAt(index).mediaId.toLongOrNull()]?.let { index to it }
        }
    }

    /**
     * 手动切歌（按键、滑动、播放失败跳过）的目标。单曲循环只管自动续播，手动切歌仍按列表循环走，
     * 否则 ExoPlayer 在单曲循环下按「不循环」导航，列表最后一首点下一首没反应。
     * 通知栏 / 方向盘 / 桌面底栏经 MediaSession → [sessionPlayer] 导回这里，和遥控器共用同一套切歌。
     */
    private fun manualNextIndex(): Int = manualNeighbor(forward = true)
    private fun manualPreviousIndex(): Int = manualNeighbor(forward = false)
    private fun manualNeighbor(forward: Boolean): Int {
        val timeline = player.currentTimeline
        if (timeline.isEmpty) return C.INDEX_UNSET
        val repeat = if (player.repeatMode == Player.REPEAT_MODE_ONE) Player.REPEAT_MODE_ALL else player.repeatMode
        val current = player.currentMediaItemIndex
        return if (forward) timeline.getNextWindowIndex(current, repeat, player.shuffleModeEnabled)
        else timeline.getPreviousWindowIndex(current, repeat, player.shuffleModeEnabled)
    }

    /** 上一首 / 下一首按播放器当前的随机顺序和循环规则算，与按键实际切到的一致；只有一首歌时没有邻居。 */
    private fun updateNeighbors() {
        val current = player.currentMediaItemIndex
        fun neighbor(index: Int) = index.takeIf { it != C.INDEX_UNSET && it != current }
        _neighbors.value = NowPlayingNeighbors(
            previousIndex = neighbor(manualPreviousIndex()),
            nextIndex = neighbor(manualNextIndex()),
        )
    }

    private fun onTrackChanged(finishedPrevious: Boolean = false) {
        val id = player.currentMediaItem?.mediaId?.toLongOrNull()
        val track = id?.let { tracks[it] }
        val prev = lastTrack
        if (finishedPrevious && prev != null) {
            val secs = (prev.durationMs / 1000).coerceAtLeast(1)
            scope.launch { NeteaseApi.scrobble("play", prev.id, sourceId, secs) }
        }
        lastTrack = track
        _current.value = track
        if (track != null) persistQueue()
        lyricsJob?.cancel()
        _lyrics.value = emptyList()
        updateSessionLyrics(id, emptyList())
        if (track != null) {
            scope.launch { NeteaseApi.scrobble("startplay", track.id, sourceId) }
            lyricsJob = scope.launch {
                val cached = withContext(Dispatchers.IO) { MusicCache.readLyrics(track.id) }
                val lines = cached ?: runCatching { NeteaseApi.lyric(track.id) }
                    .onSuccess { fetched ->
                        withContext(Dispatchers.IO) { MusicCache.writeLyrics(track.id, fetched) }
                    }
                    .getOrDefault(emptyList())
                if (player.currentMediaItem?.mediaId == track.id.toString()) {
                    _lyrics.value = lines
                    updateSessionLyrics(track.id, lines)
                }
            }
        }
        if (_mode.value == PlayMode.FM && player.mediaItemCount - player.currentMediaItemIndex <= 2) {
            extendFm(false)
        }
    }

    private fun updateSessionLyrics(trackId: Long?, lines: List<LyricLine>) {
        if (trackId == null || player.currentMediaItem?.mediaId != trackId.toString()) return
        val index = player.currentMediaItemIndex
        if (index == C.INDEX_UNSET) return

        val item = player.currentMediaItem ?: return
        val extras = Bundle(item.mediaMetadata.extras ?: Bundle()).apply {
            remove(METADATA_KEY_LYRIC)
            LyricsParser.toLrc(lines).takeIf(String::isNotEmpty)?.let {
                putString(METADATA_KEY_LYRIC, it)
            }
            remove(METADATA_KEY_KRC_LYRIC)
            WordLyricsParser.toKrc(lines.map { KrcSource(it.timeMs, it.text, it.words) })
                .takeIf(String::isNotEmpty)?.let { putString(METADATA_KEY_KRC_LYRIC, it) }
        }
        val metadata = item.mediaMetadata.buildUpon().setExtras(extras).build()
        player.replaceMediaItem(index, item.buildUpon().setMediaMetadata(metadata).build())
    }

    private fun hasValidatedNetwork(): Boolean {
        val manager = connectivity ?: return false
        val network = manager.activeNetwork ?: return false
        val capabilities = manager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    private fun cancelNetworkWait() {
        waitingMediaId = null
        if (_status.value?.kind == "network_wait") _status.value = null
        networkCallback?.let { callback -> connectivity?.unregisterNetworkCallback(callback) }
        networkCallback = null
    }

    private fun waitForNetwork() {
        if (waitingMediaId != null) return
        waitingMediaId = player.currentMediaItem?.mediaId ?: return
        _status.value = PlaybackNotice("network_wait", "网络断开，恢复后自动继续")
        waitingPosition = player.currentPosition.coerceAtLeast(0L)
        waitingForRecovery = !hasValidatedNetwork()
        player.pause()
        val manager = connectivity ?: return
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onCapabilitiesChanged(network: Network, capabilities: NetworkCapabilities) {
                val validated = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                val currentCallback = this
                scope.launch {
                    if (networkCallback !== currentCallback || waitingMediaId == null) return@launch
                    if (!validated) waitingForRecovery = true
                    else if (waitingForRecovery && hasValidatedNetwork()) {
                        val mediaId = waitingMediaId
                        val position = waitingPosition
                        cancelNetworkWait()
                        if (player.currentMediaItem?.mediaId == mediaId) {
                            player.seekTo(position)
                            player.prepare()
                            player.play()
                        }
                    }
                }
            }

            override fun onLost(network: Network) {
                val currentCallback = this
                scope.launch {
                    if (networkCallback === currentCallback) waitingForRecovery = true
                }
            }
        }
        networkCallback = callback
        manager.registerDefaultNetworkCallback(callback)
        if (waitingForRecovery && hasValidatedNetwork()) callback.onCapabilitiesChanged(
            manager.activeNetwork ?: return,
            manager.getNetworkCapabilities(manager.activeNetwork) ?: return,
        )
    }

    private fun resetPlaybackProgress() {
        progressMediaId = player.currentMediaItem?.mediaId
        progressPosition = player.currentPosition
        continuousPlaybackMs = 0L
    }

    private fun updatePlaybackProgress(includeStopped: Boolean = false) {
        if (!includeStopped && !player.isPlaying) return
        val mediaId = player.currentMediaItem?.mediaId
        val position = player.currentPosition
        if (mediaId != progressMediaId) {
            resetPlaybackProgress()
            return
        }
        val advanced = position - progressPosition
        continuousPlaybackMs = if (advanced in 1L..6_000L) continuousPlaybackMs + advanced else 0L
        progressPosition = position
        if (continuousPlaybackMs >= 10_000) {
            consecutiveFailures = 0
        }
    }

    private val listener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            if (!isPlaying && _isPlaying.value) updatePlaybackProgress(includeStopped = true)
            resetPlaybackProgress()
            _isPlaying.value = isPlaying
            persistPosition()
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            // 任何路径的切歌落地都在这里留痕：reason + 落地那一刻的播放意图。
            // 方向盘切歌后若保持暂停，这一行能区分：是切歌时就没想播（want=false），
            // 还是想过又随后被按停（want=true 之后紧跟一条 playWhenReady=false）。
            RemoteHealth.log("切歌落地 reason=$reason playWhenReady=${player.playWhenReady}")
            stopPrefetch()
            cancelNetworkWait()
            if (reason == Player.MEDIA_ITEM_TRANSITION_REASON_SEEK) {
                _status.value = null
                // 所有手动切歌（遥控器/方向盘/媒体键/助手广播）都汇聚到这里。
                // 落地时若仍是暂停态，就地补播 —— 方向盘那条链不经过 next()，只有这里兜得住。
                armCutResume()
            }
            resetPlaybackProgress()
            onTrackChanged(finishedPrevious = reason == Player.MEDIA_ITEM_TRANSITION_REASON_AUTO)
            updateNeighbors()
        }


        override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
            // 每次播放意图翻转都留痕，导出「音乐控制诊断」能直接看到是谁按的：
            // 1 用户指令 / 2 焦点丢失 / 3 音频设备拔出 / 4 远端媒体键 / 5 播完 / 7 播放错误。
            RemoteHealth.log("playWhenReady=$playWhenReady (${playReason(reason)}, state=${player.playbackState})")
            _isBuffering.value = playWhenReady && player.playbackState == Player.STATE_BUFFERING
            updatePrefetch()
            if (playWhenReady && waitingMediaId != null) cancelNetworkWait()
            if (playWhenReady && _status.value?.kind == "failures_paused") _status.value = null
        }

        override fun onTimelineChanged(timeline: Timeline, reason: Int) {
            stopPrefetch()
            rebuildQueue()
            if (_current.value?.id?.toString() != player.currentMediaItem?.mediaId) onTrackChanged()
            updateNeighbors()
        }

        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
            stopPrefetch()
            updateNeighbors()
        }

        override fun onRepeatModeChanged(repeatMode: Int) {
            stopPrefetch()
            updateNeighbors()
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            _isBuffering.value = player.playWhenReady && playbackState == Player.STATE_BUFFERING
            refreshDiagState()
            updatePrefetch()
        }

        override fun onPlayerError(error: PlaybackException) {
            stopPrefetch()
            _isBuffering.value = false
            val track = _current.value
            val id = player.currentMediaItem?.mediaId?.toLongOrNull()
            if (id != null) urlCache.keys.removeAll { it.startsWith("$id:") }
            // 只有真没网才停下等：网络是通的却报超时/连接失败，等不到"恢复"回调会永远卡住，按这首放不了处理。
            if (!hasValidatedNetwork()) {
                waitForNetwork()
                return
            }
            consecutiveFailures++
            if (consecutiveFailures >= 5) {
                player.pause()
                _status.value = PlaybackNotice("failures_paused", "连续几首都放不了，已暂停")
                return
            }
            toast("《${track?.name.orEmpty()}》无法播放，已跳过")
            when {
                manualNextIndex() != C.INDEX_UNSET -> {
                    player.seekTo(manualNextIndex(), 0)
                    player.prepare()
                    player.play()
                }
                _mode.value == PlayMode.FM -> {
                    player.prepare()
                    extendFm(true)
                }
            }
        }
    }
}
