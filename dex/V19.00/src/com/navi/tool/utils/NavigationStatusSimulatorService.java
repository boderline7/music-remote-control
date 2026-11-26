package com.navi.tool.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.navi.tool.MainActivity;
import e0.v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NavigationStatusSimulatorService extends Service {
    private static final String ACTION_STANDARD_SEND = "AUTONAVI_STANDARD_BROADCAST_SEND";
    private static final long BROADCAST_INTERVAL = 1000;
    private static final String CHANNEL_ID = "NavigationStatusSimulatorChannel";
    private static final String EXTRA_KEY_TYPE = "KEY_TYPE";
    private static final String EXTRA_STATE = "EXTRA_STATE";
    private static final int KEY_NAVIGATION_STATUS = 10019;
    private static final int MAX_BROADCAST_COUNT = 5;
    private static final int NAVIGATION_STATE_START = 0;
    private static final int NOTIFICATION_ID = 1008;
    private static final String TAG = "NavigationStatusSimulator";
    private int broadcastCount = 0;
    private Runnable broadcastRunnable;
    private Handler handler;

    public static /* synthetic */ int access$008(NavigationStatusSimulatorService navigationStatusSimulatorService) {
        int i7 = navigationStatusSimulatorService.broadcastCount;
        navigationStatusSimulatorService.broadcastCount = i7 + 1;
        return i7;
    }

    private Notification createNotification() {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 67108864);
        v vVar = new v(this, CHANNEL_ID);
        vVar.f6327e = v.c("导航状态模拟服务");
        vVar.f6328f = v.c("正在发送导航状态广播");
        vVar.f6341s.icon = g4.o.ic_launcher_foreground;
        vVar.f6329g = activity;
        return vVar.a();
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "导航状态模拟服务", 2);
        notificationChannel.setDescription("用于模拟导航状态广播");
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNavigationStatusBroadcast() {
        try {
            Intent intent = new Intent("AUTONAVI_STANDARD_BROADCAST_SEND");
            intent.putExtra(AmapBroadcastConstants.EXTRA_SELF_BROADCAST, true);
            intent.putExtra("KEY_TYPE", 10019);
            intent.putExtra("EXTRA_STATE", 0);
            sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    public static void startService(Context context) {
        context.startForegroundService(new Intent(context, (Class<?>) NavigationStatusSimulatorService.class));
    }

    public static void stopService(Context context) {
        context.stopService(new Intent(context, (Class<?>) NavigationStatusSimulatorService.class));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification());
        this.handler = new Handler(Looper.getMainLooper());
        this.broadcastRunnable = new Runnable() { // from class: com.navi.tool.utils.NavigationStatusSimulatorService.1
            @Override // java.lang.Runnable
            public void run() {
                if (NavigationStatusSimulatorService.this.broadcastCount >= 5) {
                    NavigationStatusSimulatorService.this.stopSelf();
                    return;
                }
                NavigationStatusSimulatorService.this.sendNavigationStatusBroadcast();
                NavigationStatusSimulatorService.access$008(NavigationStatusSimulatorService.this);
                int unused = NavigationStatusSimulatorService.this.broadcastCount;
                NavigationStatusSimulatorService.this.handler.postDelayed(this, 1000L);
            }
        };
    }

    @Override // android.app.Service
    public void onDestroy() {
        Runnable runnable;
        super.onDestroy();
        Handler handler = this.handler;
        if (handler == null || (runnable = this.broadcastRunnable) == null) {
            return;
        }
        handler.removeCallbacks(runnable);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        this.broadcastCount = 0;
        this.handler.post(this.broadcastRunnable);
        return 1;
    }
}
