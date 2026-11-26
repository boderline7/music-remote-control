package com.navi.tool.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import com.navi.tool.MainActivity;
import com.navi.tool.receiver.ThemeChangeReceiver;
import com.navi.tool.utils.CarSettingsUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AutoStartService extends Service {
    public static final String ACTION_SCREEN_ON_AUTO_START = "com.navi.tool.action.SCREEN_ON_AUTO_START";
    private static final String CHANNEL_ID = "AutoStartServiceChannel";
    private static final int NOTIF_ID = 1006;
    private static final long SCREEN_ON_TRIGGER_COOLDOWN_MS = 30000;
    private static final String TAG = "AutoStartService";
    private static final int[] THEME_SYNC_RETRY_DELAYS_SEC = {3, 6, 9};
    private static final AtomicLong sLastScreenOnTriggerAt = new AtomicLong(0);
    private Handler handler;
    private final AtomicInteger pendingTasks = new AtomicInteger(0);

    private void checkAndSyncTheme(String str) {
        if (SharedPreferencesUtils.isThemeAppEnabled(this, str)) {
            for (int i7 : THEME_SYNC_RETRY_DELAYS_SEC) {
                this.pendingTasks.incrementAndGet();
                this.handler.postDelayed(new h1.a(this, str, i7, 2), i7 * 1000);
            }
        }
    }

    private void createChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "自启管理服务", 2);
        notificationChannel.setDescription("负责开机自动启动应用");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private Notification createNotification() {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592);
        e0.v vVar = new e0.v(this, CHANNEL_ID);
        vVar.f6327e = e0.v.c("自启管理服务");
        vVar.f6328f = e0.v.c("正在等待应用启动...");
        vVar.f6341s.icon = g4.o.ic_launcher_foreground;
        vVar.f6329g = activity;
        vVar.f6331i = -1;
        return vVar.a();
    }

    private void finishPendingTask() {
        if (this.pendingTasks.decrementAndGet() <= 0) {
            stopSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndSyncTheme$2(String str, int i7) {
        try {
            new ThemeChangeReceiver().checkThemeChange(getApplicationContext());
        } catch (Exception unused) {
        } catch (Throwable th) {
            finishPendingTask();
            throw th;
        }
        finishPendingTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleLaunches$0(String str, ResolveInfo resolveInfo) {
        launchApp(str, resolveInfo.activityInfo.name);
        finishPendingTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleLaunches$1() {
        launchDesktopAssistant();
        finishPendingTask();
    }

    private void launchApp(String str, String str2) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setClassName(str, str2);
            intent.addFlags(872546304);
            startActivity(intent);
            checkAndSyncTheme(str);
        } catch (Exception unused) {
        }
    }

    private void launchDesktopAssistant() {
        try {
            Intent intent = new Intent();
            intent.setClassName(getPackageName(), "com.navi.tool.ui.extra.DesktopAssistantActivity");
            intent.addFlags(872546304);
            startActivity(intent);
        } catch (Exception unused) {
        }
    }

    private void scheduleLaunches(boolean z6) {
        int i7;
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        int i8 = 0;
        Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 0).iterator();
        boolean z7 = false;
        while (true) {
            i7 = 1;
            if (!it.hasNext()) {
                break;
            }
            ResolveInfo next = it.next();
            String str = next.activityInfo.packageName;
            if (!str.equals(getPackageName()) && SharedPreferencesUtils.isAutoStartAppEnabled(this, str) && (!z6 || SharedPreferencesUtils.isScreenOnAutoStartAppEnabled(this, str))) {
                int autoStartAppDelay = SharedPreferencesUtils.getAutoStartAppDelay(this, str);
                if (autoStartAppDelay > i8) {
                    i8 = autoStartAppDelay;
                }
                this.pendingTasks.incrementAndGet();
                this.handler.postDelayed(new y0.n(this, str, next, 8), autoStartAppDelay * 1000);
                z7 = true;
            }
        }
        if (!SharedPreferencesUtils.isAutoStartAppEnabled(this, "com.navi.tool.desktopassistant")) {
            if (z7) {
                return;
            }
            stopSelf();
            return;
        }
        int autoStartAppDelay2 = SharedPreferencesUtils.getAutoStartAppDelay(this, "com.navi.tool.desktopassistant");
        int max = Math.max(autoStartAppDelay2, i8 + 2);
        if (max > autoStartAppDelay2) {
            SharedPreferencesUtils.setAutoStartAppDelay(this, "com.navi.tool.desktopassistant", max);
        }
        this.pendingTasks.incrementAndGet();
        this.handler.postDelayed(new b0(i7, this), max * 1000);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        createChannel();
        startForeground(NOTIF_ID, createNotification());
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        if (!SharedPreferencesUtils.isAutoStartEnabled(this)) {
            stopSelf();
            return 2;
        }
        CarSettingsUtils.applyCarSettings(this);
        boolean z6 = intent != null && ACTION_SCREEN_ON_AUTO_START.equals(intent.getAction());
        if (z6) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            AtomicLong atomicLong = sLastScreenOnTriggerAt;
            if (elapsedRealtime - atomicLong.get() < SCREEN_ON_TRIGGER_COOLDOWN_MS) {
                return 1;
            }
            atomicLong.set(elapsedRealtime);
        }
        scheduleLaunches(z6);
        return 1;
    }
}
