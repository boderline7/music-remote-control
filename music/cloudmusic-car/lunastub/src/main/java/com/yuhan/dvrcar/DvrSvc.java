package com.yuhan.dvrcar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

/**
 * 行车记录仪常驻前台服务 —— 无条件启动，跟音乐音源（Svc / Mode）完全无关。
 *
 * 进程一起来（App.onCreate）就拉起，开机广播、被回收、划掉任务都会重来；
 * 音乐切官方/第三方、用户停不停音乐服务，都不影响这个端口。
 * 独立通知渠道，跟「网易云遥控」那条通知分开。
 */
public class DvrSvc extends Service {
    private static final String CHANNEL = "dvrbridge";
    private static final int NOTIF_ID = 18767;

    /** 无条件拉起：不看 Mode、不看 Svc 的停服标记。 */
    static void start(Context c) {
        Intent i = new Intent(c, DvrSvc.class);
        try {
            if (Build.VERSION.SDK_INT >= 26) c.startForegroundService(i);
            else c.startService(i);
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "dvr svc start failed", t);
            LogCapture.logAlways("DVR SVC start 失败：" + t);
        }
    }

    @Override public void onCreate() {
        super.onCreate();
        try {
            // 先满足 5 秒前台契约，再绑端口，绑定失败也不至于被 ANR 杀进程
            foreground();
            DvrServer.I.start(this);
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "dvr svc onCreate failed", t);
            LogCapture.logAlways("DVR SVC onCreate 异常：" + t);
            try { foreground(); } catch (Throwable ignored) {}
        }
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            if (!DvrServer.I.isRunning()) DvrServer.I.start(this);
            foreground();
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "dvr svc onStartCommand failed", t);
            try { foreground(); } catch (Throwable ignored) {}
        }
        return START_STICKY;
    }

    @Override public void onDestroy() {
        DvrServer.I.stop();
        super.onDestroy();
    }

    @Override public void onTaskRemoved(Intent rootIntent) {
        start(this);
        super.onTaskRemoved(rootIntent);
    }

    @Override public IBinder onBind(Intent intent) {
        return null;
    }

    private void foreground() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm != null) {
                NotificationChannel ch = new NotificationChannel(CHANNEL, "行车记录仪",
                        NotificationManager.IMPORTANCE_LOW);
                nm.createNotificationChannel(ch);
            }
        }
        int flags = PendingIntent.FLAG_UPDATE_CURRENT
                | (Build.VERSION.SDK_INT >= 23 ? PendingIntent.FLAG_IMMUTABLE : 0);
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, Main.class), flags);
        Notification.Builder b = Build.VERSION.SDK_INT >= 26
                ? new Notification.Builder(this, CHANNEL) : new Notification.Builder(this);
        Notification n = b.setSmallIcon(android.R.drawable.ic_media_play)
                .setContentTitle("行车记录仪")
                .setContentText("http://" + Ip.local() + ":" + DvrServer.PORT)
                .setContentIntent(pi)
                .setOngoing(true)
                .build();
        startForeground(NOTIF_ID, n);
    }
}
