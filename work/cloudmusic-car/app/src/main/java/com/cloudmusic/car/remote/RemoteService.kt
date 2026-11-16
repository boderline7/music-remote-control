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
import com.cloudmusic.car.data.Settings

/**
 * 遥控服务的前台壳，只做两件事：
 * 1. 把进程钉住，别被系统冻结（实测 vivo 直接把进程状态打成 T/stopped，
 *    冻结后内核照样回 SYN，但应用层不读数据，手机端表现是「一直正在连接」）；
 * 2. 按用户开关启停 WebSocket 服务，开关开着就一直提供服务，不按网络状态起停。
 *
 * 不持有 WakeLock，也不跑定时任务；端口常开本身不耗电。
 * 网络变化由 RemoteServer 里的 NetworkMonitor 处理，只用来刷新 mDNS / 地址。
 */
class RemoteService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createChannel()
        startForeground(NOTIF_ID, buildNotification())
        sync()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            RemoteServer.stop()
            stopSelf()
            return START_NOT_STICKY
        }
        sync()
        return START_STICKY
    }

    override fun onDestroy() {
        RemoteServer.stop()
        super.onDestroy()
    }

    /** 只认用户的遥控开关，不再看热点/Wi-Fi。 */
    private fun sync() {
        if (Settings.remoteEnabled.value) {
            if (!RemoteServer.isRunning()) {
                RemoteHealth.log("遥控开关已开，启动服务")
                if (!RemoteServer.start(this, ensureForeground = false)) {
                    RemoteHealth.warn("遥控服务启动失败")
                }
            }
        } else if (RemoteServer.isRunning()) {
            RemoteServer.stop(stopService = false)
        }
        updateNotification()
    }

    private fun updateNotification() {
        runCatching {
            getSystemService(NotificationManager::class.java)?.notify(NOTIF_ID, buildNotification())
        }
    }

    private fun notificationText(): String =
        if (RemoteServer.isRunning()) {
            "端口 ${RemoteConfig.PORT}，手机连同一 Wi-Fi 即可控制"
        } else {
            "遥控已关闭"
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
            .setContentTitle(if (RemoteServer.isRunning()) "手机遥控已开启" else "手机遥控待机")
            .setContentText(notificationText())
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pending)
            .setOngoing(true)
            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "关闭遥控", stop)
            .build()
    }

    companion object {
        private const val CHANNEL_ID = "remote_service"
        private const val NOTIF_ID = 18765
        const val ACTION_STOP = "com.cloudmusic.car.remote.STOP"

        /** Android 12+ 后台起前台服务会被拒，这里吞掉异常；服务仍会以普通 Service 起来。 */
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
