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
import com.navi.tool.MainActivity;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ReturnAppService extends Service {
    private static final String CHANNEL_ID = "ReturnAppServiceChannel";
    public static final String EXTRA_DELAY_MS = "delay_ms";
    private static final int NOTIF_ID = 1005;
    private static final String TAG = "ReturnAppService";
    private Handler handler;
    private Runnable task;

    private void createChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "返回应用服务", 2);
        notificationChannel.setDescription("导航后返回用户选择的应用");
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    private Notification createNotification() {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592);
        e0.v vVar = new e0.v(this, CHANNEL_ID);
        vVar.f6327e = e0.v.c("返回应用服务");
        vVar.f6328f = e0.v.c("等待返回指定应用");
        vVar.f6341s.icon = g4.o.ic_launcher_foreground;
        vVar.f6329g = activity;
        vVar.f6331i = -1;
        return vVar.a();
    }

    private String findEnabledReturnAppPackage() {
        if (SharedPreferencesUtils.isReturnAppEnabled(this, "com.navi.tool.desktopassistant") && SharedPreferencesUtils.getAppPrefs(this).getBoolean("desktop_assistant_enabled", false)) {
            return "com.navi.tool.desktopassistant";
        }
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (it.hasNext()) {
            String str = it.next().activityInfo.packageName;
            if (!str.equals(getPackageName())) {
                try {
                    if (SharedPreferencesUtils.isReturnAppEnabled(this, str)) {
                        arrayList.add(str);
                    }
                } catch (Exception unused) {
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        arrayList.sort(new m0.a(5));
        arrayList.toString();
        return (String) arrayList.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleLaunch$0() {
        ResolveInfo resolveInfo;
        String findEnabledReturnAppPackage = findEnabledReturnAppPackage();
        if (findEnabledReturnAppPackage == null) {
            stopSelf();
            return;
        }
        if ("com.navi.tool.desktopassistant".equals(findEnabledReturnAppPackage)) {
            try {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), "com.navi.tool.ui.extra.DesktopAssistantActivity");
                intent.addFlags(872546304);
                startActivity(intent);
            } catch (Exception unused) {
            } catch (Throwable th) {
                stopSelf();
                throw th;
            }
            stopSelf();
            return;
        }
        try {
            PackageManager packageManager = getPackageManager();
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent2, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    resolveInfo = null;
                    break;
                } else {
                    resolveInfo = it.next();
                    if (resolveInfo.activityInfo.packageName.equals(findEnabledReturnAppPackage)) {
                        break;
                    }
                }
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            stopSelf();
            throw th2;
        }
        if (resolveInfo == null) {
            stopSelf();
            stopSelf();
            return;
        }
        Intent intent3 = new Intent("android.intent.action.MAIN");
        intent3.addCategory("android.intent.category.LAUNCHER");
        intent3.setClassName(findEnabledReturnAppPackage, resolveInfo.activityInfo.name);
        intent3.addFlags(872546304);
        String str = resolveInfo.activityInfo.name;
        startActivity(intent3);
        stopSelf();
    }

    private void scheduleLaunch(long j7) {
        if (!SharedPreferencesUtils.isReturnAppEnabled(this)) {
            stopSelf();
            return;
        }
        b0 b0Var = new b0(5, this);
        this.task = b0Var;
        this.handler.postDelayed(b0Var, j7);
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
        Runnable runnable;
        super.onDestroy();
        Handler handler = this.handler;
        if (handler == null || (runnable = this.task) == null) {
            return;
        }
        handler.removeCallbacks(runnable);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        Runnable runnable;
        long returnAppDelaySeconds = SharedPreferencesUtils.getReturnAppDelaySeconds(this) * 1000;
        if (intent != null && intent.hasExtra(EXTRA_DELAY_MS)) {
            returnAppDelaySeconds = Math.max(0L, intent.getLongExtra(EXTRA_DELAY_MS, 5000L));
        }
        Handler handler = this.handler;
        if (handler != null && (runnable = this.task) != null) {
            handler.removeCallbacks(runnable);
            this.task = null;
        }
        scheduleLaunch(returnAppDelaySeconds);
        return 1;
    }
}
