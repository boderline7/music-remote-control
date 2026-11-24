package com.cloudmusic.car.player

import android.content.ComponentName
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
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

    private val main = Handler(Looper.getMainLooper())
    private val tickStarted = AtomicBoolean(false)
    private val retryScheduled = AtomicBoolean(false)

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
            main.post { warmUp(controller) }
        }, MoreExecutors.directExecutor())
    }

    /**
     * 空队列预热。
     *
     * 车机助手的方控是 `AudioManager.dispatchMediaKeyEvent`，它只投给系统记录的
     * 「当前媒体会话」，而那个记录要靠会话自己产生过一次播放状态变化才会更新。
     * 队列非空时 `PlayerHub.restorePlayback` 已经 prepare 过；队列为空（新装、清过数据、
     * 或上次没留下快照）时会话一直是 IDLE，系统不认，方向盘按了也没人接——但手机遥控
     * 走 HTTP 直连，所以能用。这里对空队列调一次 prepare：立刻落到 ENDED，状态变化有了，
     * 不出声、不联网。
     */
    private fun warmUp(controller: MediaController) {
        if (!controller.isConnected) return
        if (controller.mediaItemCount > 0) return
        if (controller.playbackState == Player.STATE_ENDED) return
        runCatching { controller.prepare() }
        RemoteHealth.log("空队列预热：让系统把这个会话记为当前媒体会话")
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
