package com.navitool.bridge;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * 常驻服务：18766 的容器。
 *
 * 为什么需要它：
 *   ContentProvider 只在进程启动时跑一次。进程被系统回收后没人再拉起，
 *   18766 就跟着没了（手机端就显示"车控离线"）。所以再挂一个前台服务，
 *   START_STICKY + 开机广播，进程被杀也能自己回来。
 *
 * 原工程一行代码不改，全靠清单里多声明一个服务。
 */
public final class BridgeService extends Service {

    static final String CHANNEL = "navitool_bridge";
    static final int NID = 18766;

    /** 从任何地方拉起服务（provider / 开机广播 / 外部） */
    static void kick(Context ctx) {
        if (ctx == null) {
            return;
        }
        try {
            Intent i = new Intent(ctx, BridgeService.class);
            ctx.startForegroundService(i);
            Bridge.note("service kick ok");
        } catch (Throwable t) {
            Bridge.note("service kick: " + Bridge.root(t));
            try {
                ctx.startService(new Intent(ctx, BridgeService.class));
            } catch (Throwable t2) {
                Bridge.note("service kick2: " + Bridge.root(t2));
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            foreground();
        } catch (Throwable t) {
            Bridge.note("foreground: " + Bridge.root(t));
        }
        try {
            Bridge.install(getApplicationContext());
        } catch (Throwable t) {
            Bridge.note("service install: " + Bridge.root(t));
        }
        HttpServer.start(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HttpServer.start(getApplicationContext());
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Bridge.note("service destroyed");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void foreground() {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel ch = new NotificationChannel(CHANNEL, "车控桥接",
                NotificationManager.IMPORTANCE_MIN);
        ch.setShowBadge(false);
        ch.setSound(null, null);
        nm.createNotificationChannel(ch);

        int icon = getApplicationInfo().icon;
        if (icon == 0) {
            icon = android.R.drawable.stat_sys_download_done;
        }
        Notification n = new Notification.Builder(this, CHANNEL)
                .setContentTitle("车控桥接")
                .setContentText("18766 常驻中")
                .setSmallIcon(icon)
                .setOngoing(true)
                .setShowWhen(false)
                .build();
        startForeground(NID, n);
    }
}
