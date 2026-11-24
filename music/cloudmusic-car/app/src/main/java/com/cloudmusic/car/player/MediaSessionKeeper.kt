package com.cloudmusic.car.player

import android.content.ComponentName
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.cloudmusic.car.R
import com.cloudmusic.car.remote.RemoteHealth
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 媒体会话守护。
 *
 * 为什么需要它：方向盘 / 方控走系统媒体键 → MediaSession，不走遥控的 HTTP。
 * `PlaybackService` 是 MediaSessionService，没有 controller 时会话会被释放；
 * 以前只在 `Application.onCreate` 连一次 controller，连失败或服务被回收后不再重试，
 * 表现就是「车机刚启动 / 静置很久后，方控完全没反应，但手机遥控正常」。
 *
 * 这里做三件事：
 * 1. 幂等连接：已有可用 controller 就直接返回；
 * 2. 断开重连：controller 掉线或 future 失败时按退避重试；
 * 3. 周期巡检：每 15 秒确认一次，覆盖服务被系统静默回收、没有回调的情况。
 *
 * 只在主线程操作 future / controller，调用方随便哪个线程都能调 [ensure]。
 */
object MediaSessionKeeper {

    private const val CHECK_MS = 15_000L

    /** 会话就绪后等这么久再预热，避开服务刚起来的抖动。 */
    private const val PRIME_DELAY_MS = 1_200L

    /** 静音播多久：够系统把它记成一次真实播放即可。 */
    private const val PRIME_HOLD_MS = 700L

    private val main = Handler(Looper.getMainLooper())
    private val tickStarted = AtomicBoolean(false)
    private val retryScheduled = AtomicBoolean(false)

    /** 每个进程预热一次就够：登记在系统侧（按 UID），进程重启也不会丢。 */
    private var primed = false

    @Volatile
    private var appContext: Context? = null
    private var future: ListenableFuture<MediaController>? = null
    private var connectAt = 0L
    private var attempt = 0

    private val tick = object : Runnable {
        override fun run() {
            ensureOnMain()
            main.postDelayed(this, CHECK_MS)
        }
    }

    fun ensure(context: Context) {
        appContext = context.applicationContext
        if (tickStarted.compareAndSet(false, true)) {
            main.postDelayed(tick, CHECK_MS)
        }
        main.post { ensureOnMain() }
    }

    private fun ensureOnMain() {
        val ctx = appContext ?: return
        val f = future
        if (f != null && !f.isDone) {
            // 连接卡住超过 10 秒就丢掉重来，别让巡检一直空转
            if (System.currentTimeMillis() - connectAt < 10_000L) return
            future = null
            runCatching { MediaController.releaseFuture(f) }
        } else if (f != null) {
            val controller = runCatching { f.get() }.getOrNull()
            if (controller != null && controller.isConnected) return
            future = null
            runCatching { MediaController.releaseFuture(f) }
        }
        connect(ctx)
    }

    private fun connect(ctx: Context) {
        val token = SessionToken(ctx, ComponentName(ctx, PlaybackService::class.java))
        // Builder 的 listener 负责「断开」，Guava future 的 listener 负责「连上 / 失败」。
        val holder = arrayOfNulls<ListenableFuture<MediaController>>(1)
        val f = MediaController.Builder(ctx, token)
            .setListener(object : MediaController.Listener {
                override fun onDisconnected(controller: MediaController) {
                    val current = holder[0] ?: return
                    if (future === current) {
                        future = null
                        RemoteHealth.warn("媒体会话断开，重建")
                        scheduleRetry()
                    }
                }
            })
            .buildAsync()
        holder[0] = f
        future = f
        connectAt = System.currentTimeMillis()
        f.addListener({
            if (f.isCancelled) return@addListener
            val controller = runCatching { f.get() }.getOrNull()
            if (controller == null) {
                if (future === f) {
                    future = null
                    RemoteHealth.warn("媒体会话连接失败，稍后重试")
                    scheduleRetry()
                }
                return@addListener
            }
            if (future !== f) return@addListener
            attempt = 0
            RemoteHealth.log("媒体会话已连接")
            main.post { warmUp() }
        }, MoreExecutors.directExecutor())
    }

    /**
     * 静音预热 —— 让系统把「本应用」记成最近播过音频的那一方。
     *
     * 现场事实（2026-11-24）：开机日志里已有 `媒体会话已连接`，但方向盘要按到「点一次播放」
     * 之后才恢复。
     *
     * AOSP 依据（android13-release `MediaSessionStack.updateMediaButtonSessionIfNeeded`）：
     * 媒体键只投给 `AudioPlayerStateMonitor.getSortedAudioPlaybackClientUids()` 里
     * **最近真的播过音频**的 UID 所对应的会话。光建会话、或者 prepare 到 PAUSED 都不算播放，
     * UID 进不了这个货架，按键就没有落点——而手机遥控走 HTTP，所以一直正常。
     *
     * 一旦真的播过一次，这个 UID 会一直留在货架上：`cleanUpAudioPlaybackUids` 以媒体键会话为界，
     * 只清理它后面的、且已不活跃的 UID，所以暂停也不会被摘掉。这也解释了为什么
     * 「点一次播放之后就一直好了」。
     *
     * 做法：用一条**独立的临时播放器**播 0.5 秒内置静音（音量 0、不抢音频焦点），
     * 播完立即释放。不碰真实队列、不改播放状态、不联网、听不到声音，
     * 但系统侧本 UID 已经进了「最近播放」。
     */
    @OptIn(UnstableApi::class)
    private fun warmUp() {
        if (primed) return
        primed = true
        val ctx = appContext ?: return
        val delayMs = if (PlayerHub.playedSinceBoot) 300L else PRIME_DELAY_MS
        main.postDelayed({
            if (PlayerHub.playedSinceBoot) {
                RemoteHealth.log("静音预热跳过：本进程已经播过，系统已登记")
                return@postDelayed
            }
            val temp = runCatching {
                ExoPlayer.Builder(ctx)
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(C.USAGE_MEDIA)
                            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                            .build(),
                        /* handleAudioFocus = */ false,
                    )
                    .setHandleAudioBecomingNoisy(false)
                    .build()
            }.getOrNull()
            if (temp == null) {
                RemoteHealth.warn("静音预热：临时播放器创建失败")
                return@postDelayed
            }
            temp.volume = 0f
            val started = runCatching {
                temp.setMediaItem(
                    MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.prime_silent))
                )
                temp.prepare()
                temp.play()
            }
            if (started.isFailure) {
                RemoteHealth.warn("静音预热：起播失败（${started.exceptionOrNull()?.javaClass?.simpleName}）")
                runCatching { temp.release() }
                return@postDelayed
            }
            RemoteHealth.log("静音预热：播 0.5s 静音，把本 UID 记成最近播放")
            main.postDelayed({
                runCatching { temp.release() }
                RemoteHealth.log("静音预热：释放临时播放器")
            }, PRIME_HOLD_MS)
        }, delayMs)
    }

    private fun scheduleRetry() {
        if (!retryScheduled.compareAndSet(false, true)) return
        attempt++
        val delayMs = when {
            attempt <= 1 -> 1_000L
            attempt == 2 -> 3_000L
            attempt == 3 -> 8_000L
            else -> 20_000L
        }
        main.postDelayed({
            retryScheduled.set(false)
            ensureOnMain()
        }, delayMs)
    }
}
