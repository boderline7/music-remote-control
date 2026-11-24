package com.cloudmusic.car.remote

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.cloudmusic.car.data.Settings
import com.cloudmusic.car.player.MediaSessionKeeper

/**
 * 开机自启：车机通电启动后直接把遥控服务拉起来，不用先点开应用。
 *
 * Application.onCreate 里已经有一条「按上次开关状态启动」的逻辑，
 * 但进程不启动就不会执行，所以需要这个广播把进程叫醒。
 * 部分 ROM 限制后台起前台服务，RemoteService.start 内部已做降级，
 * 真起不来也只是没有常驻通知，局域网监听照旧。
 */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action ?: return
        val boot = action == Intent.ACTION_BOOT_COMPLETED ||
                action == "android.intent.action.QUICKBOOT_POWERON" ||
                action == Intent.ACTION_MY_PACKAGE_REPLACED
        if (!boot) return
        val app = context.applicationContext
        /*
         * 遥控开关只管「要不要常驻前台」，媒体会话必须无条件建立：
         * 方控是车机助手 dispatchMediaKeyEvent 注入的系统媒体键，系统按当前活跃会话路由，
         * 没有会话时按键直接被丢弃（重启 / 静置后必现，手机遥控却正常）。
         */
        MediaSessionKeeper.ensure(app)
        if (!Settings.remoteEnabled.value) return
        // 交给前台壳：由它按遥控开关决定是否常驻，端口常开不依赖网络状态
        RemoteService.start(app)
        Log.i(RemoteConfig.TAG, "remote autostart on boot")
    }
}
