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

    /** 确认起播之后再播这么久，然后暂停保活。 */
    private const val PRIME_HOLD_MS = 600L

    /** 这么久还没确认起播，就当这次没成，暂停保留后重来。 */
    private const val PRIME_CONFIRM_MS = 5_000L

    /** 上一轮失败后隔多久再试。 */
    private const val PRIME_RETRY_MS = 6_000L

    private const val PRIME_MAX_ATTEMPT = 4

    private val main = Handler(Looper.getMainLooper())
    private val tickStarted = AtomicBoolean(false)
    private val retryScheduled = AtomicBoolean(false)

    /** 每个进程预热一次就够：登记在系统侧（按 UID）。 */
    private var primed = false
    private var primeAttempt = 0

    /**
     * 保活的预热播放器：播完静音后**只暂停、不释放**。
     *
     * 这是 2026-11-24 16:52 那份诊断给出的关键差别：上一版播完 700ms 直接 `release()`，
     * 系统侧这个 UID 的播放配置随之消失，等于白播；真实播放（用户点一次播放）之所以有效，
     * 是因为主播放器一直活着、配置一直在。所以这里改成暂停保活。
     */
    private var primePlayer: ExoPlayer? = null

    /** 诊断用：预热走到哪一步（未开始 / 尝试中 / 已保活 / 跳过 / 放弃）。 */
    @Volatile
    var primeState: String = "未开始"
        private set

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
     * 现场事实（2026-11-24）：`媒体会话已连接` 有了，但方向盘要按到「点一次播放」之后才有反应。
     * 16:52 那份诊断把上一版的毛病暴露了：16:51:22 预热、同秒释放，之后到 16:52:43 之间
     * **一条媒体键都没到会话**。也就是说「播 700ms 再 release」等于没播。
     *
     * AOSP 依据（android13-release `MediaSessionStack.updateMediaButtonSessionIfNeeded`）：
     * 媒体键只投给 `AudioPlayerStateMonitor.getSortedAudioPlaybackClientUids()` 里
     * **最近真的播过音频**的 UID 所对应的会话。光建会话、prepare 到 PAUSED 都不算播放；
     * 而 UID 的播放配置一旦变成 RELEASED，就会被 UID 清理逻辑摘掉。
     *
     * 所以改成两步，且第二步是关键：
     * 1. 独立临时播放器播内置静音（音量 0、不抢音频焦点），**确认真的起播**（收到 isPlaying）；
     * 2. 起播后只 `pause()`，**绝不 `release()`** —— 实例留在 [primePlayer] 里保活，
     *    播放配置一直在，UID 就不会被清掉。
     *
     * 这正好复刻「用户点一次播放之后就一直好」的状态：主播放器播过之后一直活着。
     * 代价是进程里多一个空转的 ExoPlayer（几百 KB），没有音频输出、不持 WakeLock、不碰真实队列。
     *
     * 5 秒内没确认起播（车机音频 HAL 刚上电时常见）就先暂停保留，隔 6 秒重来，最多 4 次；
     * 本进程已经真播过（`playedSinceBoot`）则整段跳过 —— 那时主播放器已经把 UID 顶上去了。
     */
    private fun warmUp() {
        if (primed) return
        val delayMs = if (PlayerHub.playedSinceBoot) 300L else PRIME_DELAY_MS
        main.postDelayed({ startPrime() }, delayMs)
    }

    @OptIn(UnstableApi::class)
    private fun startPrime() {
        if (primed) return
        val ctx = appContext ?: return
        if (PlayerHub.playedSinceBoot) {
            primed = true
            primeState = "跳过(已真播)"
            RemoteHealth.log("静音预热跳过：本进程已经播过，系统已登记")
            return
        }
        if (primeAttempt >= PRIME_MAX_ATTEMPT) {
            primeState = "放弃(试了$primeAttempt 次)"
            return
        }
        primeAttempt++
        primeState = "尝试中#$primeAttempt"
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
            primeState = "创建失败"
            RemoteHealth.warn("静音预热：临时播放器创建失败")
            schedulePrimeRetry()
            return
        }
        temp.volume = 0f
        val confirmed = AtomicBoolean(false)
        val held = AtomicBoolean(false)
        temp.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (!isPlaying || held.get()) return
                confirmed.set(true)
                RemoteHealth.log("静音预热：确认起播 state=${temp.playbackState}，UID 已登记")
                main.postDelayed({
                    if (held.compareAndSet(false, true)) {
                        runCatching { temp.pause() }
                        primePlayer = temp
                        primed = true
                        primeState = "已保活(第$primeAttempt 次)"
                        RemoteHealth.log("静音预热：暂停保活，不释放")
                    }
                }, PRIME_HOLD_MS)
            }
        })
        val started = runCatching {
            temp.setMediaItem(
                MediaItem.fromUri(RawResourceDataSource.buildRawResourceUri(R.raw.prime_silent))
            )
            temp.prepare()
            temp.play()
        }
        if (started.isFailure) {
            primeState = "起播异常"
            RemoteHealth.warn("静音预热：起播失败（${started.exceptionOrNull()?.javaClass?.simpleName}）")
            runCatching { temp.release() }
            schedulePrimeRetry()
            return
        }
        main.postDelayed({
            if (confirmed.get() || held.get()) return@postDelayed
            // 没收到 isPlaying 不代表没出声，先暂停留着，别急着 release 把 UID 清掉
            runCatching { temp.pause() }
            if (primePlayer == null) primePlayer = temp else runCatching { temp.release() }
            primeState = "未确认起播(第$primeAttempt 次)"
            RemoteHealth.warn(
                "静音预热：${PRIME_CONFIRM_MS / 1000}s 内没确认起播（state=${temp.playbackState}），暂停保留后重试"
            )
            schedulePrimeRetry()
        }, PRIME_CONFIRM_MS)
    }

    private fun schedulePrimeRetry() {
        main.postDelayed({ if (!primed) startPrime() }, PRIME_RETRY_MS)
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
