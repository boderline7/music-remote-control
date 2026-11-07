package com.cloudmusic.car

import android.app.Application
import android.content.ComponentName
import android.util.Log
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.cloudmusic.car.api.NeteaseClient
import com.cloudmusic.car.data.AccountStore
import com.cloudmusic.car.data.MusicCache
import com.cloudmusic.car.data.Settings
import com.cloudmusic.car.player.PlaybackService
import com.cloudmusic.car.player.PlayerHub
import com.cloudmusic.car.remote.RemoteConfig
import com.cloudmusic.car.remote.RemoteServer
import com.google.common.util.concurrent.ListenableFuture

class CloudMusicApp : Application(), ImageLoaderFactory {

    /**
     * 常驻媒体会话控制器，不随 Activity 生命周期释放。
     *
     * `MainActivity` 只在 `onStart`~`onStop` 之间持有 MediaController，退到后台就 release。
     * `PlaybackService` 是 MediaSessionService：最后一个 controller 断开后服务会被销毁、会话释放，
     * 系统媒体按键（方向盘上一首 / 下一首）就找不到活跃会话。
     *
     * 以前用通用音源（source 6）时，车机助手的 `l4/e` 会通过 `getActiveSessions()` 连一个
     * MediaController 来读状态，顺带把会话一直拉活；切成汽水音源（source 5）后助手只走广播、
     * 不再连接 MediaSession，于是只要 App 退到后台，方向盘按键就失效。
     * 这里由应用自己持有一个 controller，保证会话始终活跃，与选哪个音源无关。
     */
    private var sessionKeepAlive: ListenableFuture<MediaController>? = null

    override fun onCreate() {
        super.onCreate()
        // 独立 APK：不再校验跑跑桌面宿主，装到任何车机 / 手机上都能直接跑。
        NeteaseClient.init(this)
        Settings.init(this)
        MusicCache.init(this)
        AccountStore.init()
        PlayerHub.init(this)
        keepMediaSessionAlive()
        // 遥控服务必须在播放器初始化之后：适配层直接读 PlayerHub。
        // 启动失败（端口占用等）只影响遥控，播放照常，所以这里吞掉异常、只记日志。
        if (Settings.remoteEnabled.value) {
            runCatching { RemoteServer.start(this) }
                .onFailure { Log.w(RemoteConfig.TAG, "remote autostart failed", it) }
        }
    }

    override fun newImageLoader(): ImageLoader = MusicCache.imageLoader(this)

    private fun keepMediaSessionAlive() {
        runCatching {
            val token = SessionToken(this, ComponentName(this, PlaybackService::class.java))
            sessionKeepAlive = MediaController.Builder(this, token).buildAsync()
        }.onFailure { Log.w(TAG, "media session keep-alive failed", it) }
    }

    private companion object {
        const val TAG = "CloudMusicApp"
    }
}
