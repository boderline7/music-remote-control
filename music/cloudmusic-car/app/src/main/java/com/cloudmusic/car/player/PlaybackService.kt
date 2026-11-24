package com.cloudmusic.car.player

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.cloudmusic.car.MainActivity
import com.cloudmusic.car.api.LyricsParser
import com.cloudmusic.car.remote.RemoteHealth
import com.paopao.music.nowplaying.KrcSource
import com.paopao.music.nowplaying.METADATA_KEY_KRC_LYRIC
import com.paopao.music.nowplaying.WordLyricsParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/** 媒体会话服务：提供系统通知栏 / 锁屏 / 车机媒体中心的播放控制。 */
class PlaybackService : MediaSessionService() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var session: MediaSession? = null

    override fun onCreate() {
        super.onCreate()
        val openApp = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        session = MediaSession.Builder(this, PlayerHub.sessionPlayer)
            .setId("cloudmusic.car")
            .setSessionActivity(openApp)
            .setCallback(object : MediaSession.Callback {
                /**
                 * 留痕用：方向盘 / 方控键只要真的落到本会话，这里就会记一条。
                 * 导出「音乐控制诊断」时，「按键有没有到达会话」一眼可辨——
                 * 没有这条 = 系统没把键派过来（登记问题）；有这条但没动作 = 我们的处理问题。
                 */
                override fun onMediaButtonEvent(
                    session: MediaSession,
                    controllerInfo: MediaSession.ControllerInfo,
                    mediaButtonIntent: Intent,
                ): Boolean {
                    @Suppress("DEPRECATION")
                    val event = mediaButtonIntent.getParcelableExtra(Intent.EXTRA_KEY_EVENT) as? KeyEvent
                    RemoteHealth.log("收到媒体键 keyCode=${event?.keyCode} action=${event?.action}")
                    return super.onMediaButtonEvent(session, controllerInfo, mediaButtonIntent)
                }
            })
            .build()
        scope.launch {
            PlayerHub.lyrics.collectLatest { lines ->
                val mediaSession = session ?: return@collectLatest
                val extras = Bundle(mediaSession.sessionExtras)
                extras.remove(METADATA_KEY_LYRIC)
                LyricsParser.toLrc(lines).takeIf(String::isNotEmpty)?.let {
                    extras.putString(METADATA_KEY_LYRIC, it)
                }
                extras.remove(METADATA_KEY_KRC_LYRIC)
                WordLyricsParser.toKrc(lines.map { KrcSource(it.timeMs, it.text, it.words) })
                    .takeIf(String::isNotEmpty)?.let { extras.putString(METADATA_KEY_KRC_LYRIC, it) }
                mediaSession.setSessionExtras(extras)
            }
        }
        scope.launch {
            PlayerHub.status.collectLatest { notice ->
                val mediaSession = session ?: return@collectLatest
                val extras = Bundle(mediaSession.sessionExtras)
                extras.remove(STATUS_TEXT)
                extras.remove(STATUS_KIND)
                notice?.let {
                    extras.putString(STATUS_TEXT, it.text)
                    extras.putString(STATUS_KIND, it.kind)
                }
                mediaSession.setSessionExtras(extras)
            }
        }
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? = session

    override fun onTaskRemoved(rootIntent: Intent?) {
        /*
         * 故意不调 super、也不 stopSelf。
         *
         * 父类默认会在「没在播放 / 队列为空」时 stopSelf，会话随即释放；
         * 车机静置很久或用户划掉任务后，方控媒体键就没有接收者了，
         * 而手机遥控走进程内 PlayerHub，看起来会像「只有方控坏了」。
         * 方控要随时可用，这个服务就常驻：空闲时没有前台通知，也不持 WakeLock。
         * 连接断了由 MediaSessionKeeper 负责重建，不靠这里自杀。
         */
    }

    override fun onDestroy() {
        // 播放器是进程级单例，这里只释放会话。
        scope.cancel()
        session?.release()
        session = null
        super.onDestroy()
    }

    private companion object {
        const val METADATA_KEY_LYRIC = "android.media.metadata.LYRIC"
        const val STATUS_TEXT = "com.paopao.music.STATUS_TEXT"
        const val STATUS_KIND = "com.paopao.music.STATUS_KIND"
    }
}
