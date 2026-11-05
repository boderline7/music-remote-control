package com.cloudmusic.car

import android.app.Application
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.cloudmusic.car.api.NeteaseClient
import com.cloudmusic.car.data.AccountStore
import com.cloudmusic.car.data.MusicCache
import com.cloudmusic.car.data.Settings
import com.cloudmusic.car.player.PlayerHub
import com.cloudmusic.car.remote.RemoteConfig
import com.cloudmusic.car.remote.RemoteServer

class CloudMusicApp : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        // 独立 APK：不再校验跑跑桌面宿主，装到任何车机 / 手机上都能直接跑。
        NeteaseClient.init(this)
        Settings.init(this)
        MusicCache.init(this)
        AccountStore.init()
        PlayerHub.init(this)
        // 遥控服务必须在播放器初始化之后：适配层直接读 PlayerHub。
        // 启动失败（端口占用等）只影响遥控，播放照常，所以这里吞掉异常、只记日志。
        if (Settings.remoteEnabled.value) {
            runCatching { RemoteServer.start(this) }
                .onFailure { Log.w(RemoteConfig.TAG, "remote autostart failed", it) }
        }
    }

    override fun newImageLoader(): ImageLoader = MusicCache.imageLoader(this)
}
