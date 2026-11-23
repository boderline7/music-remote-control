package com.cloudmusic.car

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.cloudmusic.car.api.NeteaseClient
import com.cloudmusic.car.data.AccountStore
import com.cloudmusic.car.data.MusicCache
import com.cloudmusic.car.data.Settings
import com.cloudmusic.car.player.MediaSessionKeeper
import com.cloudmusic.car.player.PlayerHub
import com.cloudmusic.car.remote.RemoteServer
import com.cloudmusic.car.remote.RemoteService

class CloudMusicApp : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        // 独立 APK：不再校验跑跑桌面宿主，装到任何车机 / 手机上都能直接跑。
        NeteaseClient.init(this)
        Settings.init(this)
        MusicCache.init(this)
        AccountStore.init()
        PlayerHub.init(this)
        /*
         * 方向盘 / 方控走系统媒体键 → MediaSession，不走遥控的 HTTP。
         * `PlaybackService` 是 MediaSessionService：没有 controller 时服务会被回收、会话释放，
         * 系统媒体键就找不到接收者。手机遥控直连进程内 PlayerHub，所以看起来只有方控坏。
         *
         * 这里交给 MediaSessionKeeper：连接失败、会话断开、服务被静默回收都会自动重建，
         * 不再像以前那样只在启动时连一次（失败就直到下次打开 App 才恢复）。
         */
        MediaSessionKeeper.ensure(this)
        // 遥控服务必须在播放器初始化之后：适配层直接读 PlayerHub。
        // 启动失败（端口占用等）只影响遥控，播放照常，所以这里吞掉异常、只记日志。
        if (Settings.remoteEnabled.value) {
            // 只拉起前台壳，真正的监听由它按遥控开关决定
            RemoteService.start(this)
        }
    }

    override fun newImageLoader(): ImageLoader = MusicCache.imageLoader(this)
}
