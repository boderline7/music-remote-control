package com.cloudmusic.car.remote

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.cloudmusic.car.data.Settings

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
        if (action != Intent.ACTION_BOOT_COMPLETED && action != "android.intent.action.QUICKBOOT_POWERON") return
        if (!Settings.remoteEnabled.value) return
        val app = context.applicationContext
        // 交给前台壳：由它判断热点是否就绪，热点没开就待机，不提前绑端口
        RemoteService.start(app)
        Log.i(RemoteConfig.TAG, "remote autostart on boot")
    }
}
