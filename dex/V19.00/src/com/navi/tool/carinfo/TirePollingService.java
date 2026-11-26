package com.navi.tool.carinfo;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.activity.e;
import androidx.appcompat.app.s0;
import com.navi.tool.tire.api.TireAccount;
import com.navi.tool.tire.api.TireEngine;
import com.navi.tool.tire.api.TireSnapshot;
import com.navi.tool.ui.extra.t1;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import o4.c;
import o4.u;
import o4.v;
import o4.y;
import o4.z;
import org.json.JSONObject;
import t1.f;
import y0.n;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TirePollingService extends Service {

    /* renamed from: g, reason: collision with root package name */
    public static final AtomicBoolean f4725g = new AtomicBoolean(false);

    /* renamed from: h, reason: collision with root package name */
    public static final AtomicBoolean f4726h = new AtomicBoolean(false);

    /* renamed from: i, reason: collision with root package name */
    public static final ExecutorService f4727i = Executors.newSingleThreadExecutor();

    /* renamed from: j, reason: collision with root package name */
    public static volatile long f4728j = 0;

    /* renamed from: k, reason: collision with root package name */
    public static volatile TirePollingService f4729k;

    /* renamed from: d, reason: collision with root package name */
    public volatile TireEngine f4733d;

    /* renamed from: e, reason: collision with root package name */
    public volatile boolean f4734e;

    /* renamed from: a, reason: collision with root package name */
    public final Handler f4730a = new Handler(Looper.getMainLooper());

    /* renamed from: b, reason: collision with root package name */
    public final ExecutorService f4731b = Executors.newSingleThreadExecutor();

    /* renamed from: c, reason: collision with root package name */
    public final s0 f4732c = new s0(17, this);

    /* renamed from: f, reason: collision with root package name */
    public volatile long f4735f = 0;

    public static void a(TirePollingService tirePollingService) {
        tirePollingService.getClass();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < tirePollingService.f4735f) {
            return;
        }
        TireEngine tireEngine = tirePollingService.f4733d;
        if (tireEngine == null) {
            if (tirePollingService.f4734e) {
                return;
            }
            tirePollingService.f4734e = true;
            u.f8031e.c(tirePollingService, new y(tirePollingService));
            return;
        }
        long d7 = z.d(tirePollingService) * f.T_MS_MINUTE;
        long j7 = tirePollingService.getApplicationContext().getSharedPreferences("tire_account", 0).getLong("tire_last_fetch_at", 0L);
        if (j7 <= 0 || (30000 + currentTimeMillis) - j7 >= d7) {
            tirePollingService.f4735f = currentTimeMillis + f.T_MS_MINUTE;
            TireAccount f7 = z.f(tirePollingService);
            if (f7 == null || !f7.isUsable()) {
                tirePollingService.stopSelf();
            } else {
                tirePollingService.f4731b.execute(new n(tirePollingService, tireEngine, f7, 5));
            }
        }
    }

    public static void b(t1 t1Var, boolean z6, boolean z7, String str) {
        if (t1Var == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new v(t1Var, z6, z7, str, 0));
    }

    public static void c(Context context, TireSnapshot tireSnapshot) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "tire");
            jSONObject.put("fl", tireSnapshot.flKpa);
            jSONObject.put("fr", tireSnapshot.frKpa);
            jSONObject.put("rl", tireSnapshot.rlKpa);
            jSONObject.put("rr", tireSnapshot.rrKpa);
            int i7 = tireSnapshot.flTemp;
            int i8 = 255;
            if (i7 == Integer.MIN_VALUE) {
                i7 = 255;
            }
            jSONObject.put("flT", i7);
            int i9 = tireSnapshot.frTemp;
            if (i9 == Integer.MIN_VALUE) {
                i9 = 255;
            }
            jSONObject.put("frT", i9);
            int i10 = tireSnapshot.rlTemp;
            if (i10 == Integer.MIN_VALUE) {
                i10 = 255;
            }
            jSONObject.put("rlT", i10);
            int i11 = tireSnapshot.rrTemp;
            if (i11 != Integer.MIN_VALUE) {
                i8 = i11;
            }
            jSONObject.put("rrT", i8);
            jSONObject.put("online", tireSnapshot.online ? 1 : 0);
            jSONObject.put("src", tireSnapshot.updateTime);
            long j7 = tireSnapshot.updateTimestamp;
            if (j7 <= 0) {
                j7 = tireSnapshot.fetchedAt;
            }
            jSONObject.put("ts", j7);
            String str = tireSnapshot.serverTime;
            if (str != null && !str.isEmpty()) {
                jSONObject.put("serverTime", tireSnapshot.serverTime);
            }
            jSONObject.put("fetchedAt", tireSnapshot.fetchedAt);
            jSONObject.put("fromCar", 1);
            String jSONObject2 = jSONObject.toString();
            if (jSONObject2 != null && !jSONObject2.isEmpty()) {
                context.getApplicationContext().getSharedPreferences("tire_account", 0).edit().putString("tire_last_snapshot", jSONObject2).apply();
            }
            c.c().t(context.getApplicationContext(), jSONObject);
        } catch (Exception e7) {
            e7.getMessage();
        }
    }

    public static void d(Context context) {
        if (context == null) {
            return;
        }
        try {
            context.startForegroundService(new Intent(context, (Class<?>) TirePollingService.class));
        } catch (Exception e7) {
            e7.getMessage();
        }
    }

    public static void e(Context context) {
        if (context == null) {
            return;
        }
        try {
            context.stopService(new Intent(context, (Class<?>) TirePollingService.class));
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            NotificationChannel notificationChannel = new NotificationChannel("navi_tire_polling", "胎压同步", 1);
            notificationChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        startForeground(31249, new Notification.Builder(this, "navi_tire_polling").setContentTitle("胎压同步").setContentText("正在后台同步车机胎压").setSmallIcon(R.drawable.ic_menu_mylocation).setOngoing(true).build());
        f4725g.set(true);
        f4729k = this;
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (f4729k == this) {
            f4729k = null;
        }
        f4725g.set(false);
        this.f4730a.removeCallbacksAndMessages(null);
        this.f4731b.shutdownNow();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        new Thread(new e(17, this), "tire-feature-check").start();
        return 1;
    }
}
