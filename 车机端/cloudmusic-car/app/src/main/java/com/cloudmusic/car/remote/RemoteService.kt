package com.cloudmusic.car.remote

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log

/**
 * 遥控服务的前台壳。
 *
 * 为什么必须有：系统会冻结后台进程（实测 vivo 直接把进程状态打成 T/stopped）。
 * 冻结之后内核照样回 SYN、TCP 连接能建立，但应用层不再读数据，
 * 手机端表现就是「一直正在连接」，日志里一条都没有。
 * 挂前台通知后进程不会被冻，熄屏也能继续响应。
 */
class RemoteService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createChannel()
        startForeground(NOTIF_ID, buildNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            RemoteServer.stop()
            stopSelf()
            return START_NOT_STICKY
        }
        // 服务被系统重建时把监听一并恢复（此时已在前台，不会循环启动服务）
        if (!RemoteServer.isRunning()) RemoteServer.start(this, ensureForeground = false)
        return START_STICKY
    }

    override fun onDestroy() {
        RemoteServer.stop()
        super.onDestroy()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val manager = getSystemService(NotificationManager::class.java) ?: return
        if (manager.getNotificationChannel(CHANNEL_ID) != null) return
        val channel = NotificationChannel(
            CHANNEL_ID,
            "手机遥控",
            NotificationManager.IMPORTANCE_MIN,
        ).apply {
            description = "局域网遥控服务运行中"
            setShowBadge(false)
        }
        manager.createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        val open = Intent(this, com.cloudmusic.car.MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pending = PendingIntent.getActivity(
            this, 0, open,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val stop = PendingIntent.getService(
            this, 1,
            Intent(this, RemoteService::class.java).setAction(ACTION_STOP),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, CHANNEL_ID)
        } else {
            @Suppress("DEPRECATION")
            Notification.Builder(this)
        }
        return builder
            .setContentTitle("手机遥控已开启")
            .setContentText("端口 ${RemoteConfig.PORT}，手机连同一 Wi-Fi 即可控制")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pending)
            .setOngoing(true)
            .addAction(Notification.Action.Builder(null, "关闭遥控", stop).build())
            .build()
    }

    companion object {
        private const val CHANNEL_ID = "remote_service"
        private const val NOTIF_ID = 18765
        const val ACTION_STOP = "com.cloudmusic.car.remote.STOP"

        /** 启动前台壳。Android 12+ 后台起前台服务会被拒，这里吞掉异常走降级（不挂前台也能连，只是可能被冻结）。 */
        fun start(context: Context) {
            val intent = Intent(context, RemoteService::class.java)
            runCatching {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }
            }.onFailure {
                Log.w(RemoteConfig.TAG, "foreground service refused, running in background", it)
                runCatching { context.startService(intent) }
            }
        }

        fun stop(context: Context) {
            runCatching { context.stopService(Intent(context, RemoteService::class.java)) }
        }
    }
}
