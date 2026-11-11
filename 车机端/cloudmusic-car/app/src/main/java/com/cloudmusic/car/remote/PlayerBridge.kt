package com.cloudmusic.car.remote

import android.content.Context
import android.media.AudioManager
import android.os.SystemClock
import com.cloudmusic.car.api.LyricLine
import com.cloudmusic.car.data.AccountStore
import com.cloudmusic.car.player.PlayerHub

/**
 * 播放器薄适配层 —— 全工程唯一直接碰 [PlayerHub] 的地方。
 *
 * 车机播放器是进程级单例 [PlayerHub]，遥控服务和它在同一进程，因此不需要 Binder / MediaController，
 * 直接调用即可。代价是所有调用**必须在主线程**（ExoPlayer 的应用线程约束），
 * 由 [RemoteServer] 统一 post 到主线程，这里不做线程切换，避免两处各切一次。
 */
object PlayerBridge {

    data class Snapshot(
        val playing: Boolean,
        val title: String,
        val artist: String,
        val album: String,
        val positionMs: Long,
        val durationMs: Long,
        val liked: Boolean = false,
    )

    private var audio: AudioManager? = null

    fun init(context: Context) {
        if (audio == null) {
            audio = context.applicationContext.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
        }
    }

    fun snapshot(): Snapshot {
        val track = PlayerHub.current.value
        val player = PlayerHub.player
        val position = runCatching { player.currentPosition }.getOrDefault(0L).coerceAtLeast(0L)
        val duration = when {
            track != null && track.durationMs > 0 -> track.durationMs
            else -> runCatching { player.duration }.getOrDefault(0L).coerceAtLeast(0L)
        }
        return Snapshot(
            playing = PlayerHub.isPlaying.value,
            title = track?.name.orEmpty(),
            artist = track?.artistNames.orEmpty(),
            album = track?.album?.name.orEmpty(),
            positionMs = position,
            durationMs = duration,
            liked = track != null && track.id in AccountStore.likedIds.value,
        )
    }

    // ---------- 当前歌曲 / 歌词（遥控协议用，只读）----------

    /** 当前播放歌曲 id；无歌时 null。PlayerHub 访问仍只在本类内。 */
    fun currentTrackId(): Long? = PlayerHub.current.value?.id

    /** 当前歌词；换歌加载中 / 无歌词时为空列表。 */
    fun lyrics(): List<LyricLine> = PlayerHub.lyrics.value

    // ---------- 红心 ----------
    // 和车机上点那颗心是同一套逻辑（AccountStore 先乐观更新、失败回滚），手机只是多一个入口。

    /** @return false 表示当前没有在播的歌。 */
    fun toggleLike(): Boolean {
        val track = PlayerHub.current.value ?: return false
        AccountStore.toggleLike(track) { message -> PlayerHub.toast(message) }
        return true
    }

    fun play() = setPlaying(true)

    fun pause() = setPlaying(false)

    /** 上一条播放/暂停指令的方向与时间，用来吃掉重复指令。 */
    private var wantedPlaying: Boolean? = null
    private var lastPlayingCommandAt = 0L

    /**
     * 显式的播放 / 暂停 —— 不是 toggle。
     *
     * 之前是「读 isPlaying，再决定要不要 toggle」。问题在于 ExoPlayer 的 `isPlaying`
     * 是异步更新的：连着来两条 `play`（手机端重发、两台手机同时按、UI 抖动）时，
     * 第二条仍看到 `isPlaying=false`，于是又 toggle 一次，结果第一下播、第二下暂停，
     * 表现为「按了没反应」或「自己跳回去」。
     *
     * 现在按「目标状态」处理，并且 [DEDUP_MS] 内的同向指令直接忽略。
     *
     * @return 永远是 true（播放器没有「拒绝播放」这种状态，失败由 UI 的 state 反映）。
     */
    fun setPlaying(want: Boolean): Boolean {
        val now = SystemClock.elapsedRealtime()
        if (wantedPlaying == want && now - lastPlayingCommandAt < DEDUP_MS) return true
        wantedPlaying = want
        lastPlayingCommandAt = now
        val actual = runCatching { PlayerHub.player.isPlaying }.getOrDefault(false)
        // 已经是目标状态就什么都不做 —— 这就是幂等
        if (actual != want) PlayerHub.togglePlay()
        return true
    }

    fun toggle() = PlayerHub.togglePlay()

    fun next() = PlayerHub.next()

    fun previous() = PlayerHub.previous()

    /** 0 顺序 / 1 单曲循环 / 2 随机，与 shared/nowplaying 的 PlayModes 一致。 */
    fun playMode(): Int = PlayerHub.playMode.value

    /** @return false 表示模式值不在 0..2。 */
    fun setPlayMode(mode: Int): Boolean {
        if (mode !in 0..2) return false
        PlayerHub.setPlayMode(mode)
        return true
    }

    /** @return false 表示当前没有可跳转的媒体。 */
    fun seekTo(positionMs: Long): Boolean {
        val player = PlayerHub.player
        val trackDuration = PlayerHub.current.value?.durationMs ?: 0L
        val duration = if (trackDuration > 0) trackDuration
        else runCatching { player.duration }.getOrDefault(0L)
        if (duration <= 0) return false
        player.seekTo(positionMs.coerceIn(0L, duration))
        return true
    }

    fun hasMedia(): Boolean = runCatching { PlayerHub.player.mediaItemCount > 0 }.getOrDefault(false)

    // ---------- 音量 ----------
    // 车机音量是系统音量（方向盘 / 车机旋钮调的都是 STREAM_MUSIC），
    // 播放器自身的 player.volume 只是 0..1 的软件增益，改它会出现「车机显示 50% 但实际很轻」，所以不用。

    fun volumeMax(): Int = runCatching { audio?.getStreamMaxVolume(AudioManager.STREAM_MUSIC) ?: 0 }
        .getOrDefault(0)

    fun volume(): Int = runCatching { audio?.getStreamVolume(AudioManager.STREAM_MUSIC) ?: -1 }
        .getOrDefault(-1)

    /** @return false 表示超出 0..max。 */
    fun setVolume(value: Int): Boolean {
        val manager = audio ?: return false
        val max = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        if (max <= 0 || value < 0 || value > max) return false
        return runCatching {
            manager.setStreamVolume(AudioManager.STREAM_MUSIC, value, 0)
            true
        }.getOrDefault(false)
    }

    /** 同向播放指令的去重窗口。重发、双端同按都落在这个窗口里。 */
    private const val DEDUP_MS = 500L
}
