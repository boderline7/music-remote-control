package com.navi.tool.amapflying;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.material.internal.g0;
import com.navi.tool.MainActivity;
import com.navi.tool.amapflying.receiver.AmapStandardBroadcastReceiver;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;
import com.navi.tool.receiver.PersonalizedNavigationReceiver;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.SharedPreferencesUtils;
import e0.v;
import g4.f;
import g4.o;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AmapBroadcastService extends Service {
    public static final String ACTION_TRIGGER_EXTSCREEN_ACTIVATION = "com.navi.tool.amapflying.ACTION_TRIGGER_EXTSCREEN_ACTIVATION";

    /* renamed from: a, reason: collision with root package name */
    public AmapStandardBroadcastReceiver f4486a;

    /* renamed from: b, reason: collision with root package name */
    public SharedPreferences f4487b;

    /* renamed from: c, reason: collision with root package name */
    public f f4488c;

    /* renamed from: d, reason: collision with root package name */
    public PersonalizedNavigationReceiver f4489d;

    /* renamed from: e, reason: collision with root package name */
    public BroadcastReceiver f4490e;

    /* renamed from: f, reason: collision with root package name */
    public BroadcastReceiver f4491f;

    /* renamed from: g, reason: collision with root package name */
    public BroadcastReceiver f4492g;

    /* renamed from: h, reason: collision with root package name */
    public HandlerThread f4493h;

    /* renamed from: i, reason: collision with root package name */
    public Handler f4494i;

    /* renamed from: j, reason: collision with root package name */
    public int f4495j = 0;

    /* renamed from: k, reason: collision with root package name */
    public int f4496k = 0;

    /* renamed from: l, reason: collision with root package name */
    public boolean f4497l = false;

    public final Notification a() {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 67108864);
        v vVar = new v(this, "AmapBroadcastServiceChannel");
        vVar.f6327e = v.c("高德导航服务");
        vVar.f6328f = v.c("正在监听高德导航数据");
        vVar.f6341s.icon = o.ic_launcher_foreground;
        vVar.f6329g = activity;
        return vVar.a();
    }

    public final void b() {
        SharedPreferences sharedPreferences = this.f4487b;
        if (sharedPreferences == null || !sharedPreferences.getBoolean("extscreen_activation_enabled", false)) {
            return;
        }
        Intent intent = new Intent(AmapBroadcastConstants.DEFAULT_AMAP_BROADCAST);
        intent.putExtra(AmapBroadcastConstants.EXTRA_SELF_BROADCAST, true);
        intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10117);
        intent.putExtra("EXTSCREEN_STATUS_INFO", 0);
        intent.putExtra("EXTSCREEN_WINDOW_INFO", 1002);
        intent.putExtra("EXTSCREEN_MODE_INFO", 2);
        try {
            sendBroadcast(intent);
            intent.getAction();
        } catch (SecurityException unused) {
            intent.getAction();
            new Handler(Looper.getMainLooper()).post(new a(this, 2));
        } catch (Throwable unused2) {
            intent.getAction();
            new Handler(Looper.getMainLooper()).post(new a(this, 3));
        }
    }

    public final void c() {
        Handler handler = new Handler(Looper.getMainLooper());
        for (int i7 = 0; i7 < 10; i7++) {
            handler.postDelayed(new a(this, 9), i7 * 2000);
        }
    }

    public final void d(int i7) {
        SharedPreferences sharedPreferences = this.f4487b;
        if (sharedPreferences == null || !sharedPreferences.getBoolean("extscreen_activation_enabled", false)) {
            return;
        }
        Intent intent = new Intent(AmapBroadcastConstants.DEFAULT_AMAP_BROADCAST);
        intent.putExtra(AmapBroadcastConstants.EXTRA_SELF_BROADCAST, true);
        intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10117);
        intent.putExtra("EXTSCREEN_STATUS_INFO", 0);
        intent.putExtra("EXTSCREEN_WINDOW_INFO", 1002);
        intent.putExtra("EXTSCREEN_MODE_INFO", i7);
        try {
            sendBroadcast(intent);
            intent.getAction();
        } catch (SecurityException unused) {
            intent.getAction();
            new Handler(Looper.getMainLooper()).post(new a(this, 4));
        } catch (Throwable unused2) {
            intent.getAction();
            new Handler(Looper.getMainLooper()).post(new a(this, 5));
        }
    }

    public final void e() {
        Handler handler = new Handler(Looper.getMainLooper());
        for (int i7 = 0; i7 < 10; i7++) {
            handler.postDelayed(new a(this, 10), i7 * 1000);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        NotificationChannel notificationChannel = new NotificationChannel("AmapBroadcastServiceChannel", "高德导航服务", 2);
        notificationChannel.setDescription("用于接收高德导航数据");
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        try {
            startForeground(1003, a());
            if (SharedPreferencesUtils.isCustomAmapBroadcastEnabled(this)) {
                AmapBroadcastConstants.setAmapBroadcast(SharedPreferencesUtils.getCustomAmapBroadcastValue(this));
            } else {
                AmapBroadcastConstants.resetAmapBroadcast();
            }
            SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
            this.f4487b = appPrefs;
            f fVar = new f(1, this);
            this.f4488c = fVar;
            appPrefs.registerOnSharedPreferenceChangeListener(fVar);
            if (this.f4487b.getBoolean("extscreen_activation_enabled", false)) {
                c();
                new Handler(Looper.getMainLooper()).postDelayed(new a(this, 0), 2100L);
                if (SharedPreferencesUtils.isTraditionalClusterEnabled(this)) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    for (int i7 = 0; i7 < 10; i7++) {
                        handler.postDelayed(new a(this, 1), (i7 * 2000) + 2100);
                    }
                }
            }
            this.f4486a = new AmapStandardBroadcastReceiver();
            this.f4489d = new PersonalizedNavigationReceiver();
            this.f4490e = new BroadcastReceiver() { // from class: com.navi.tool.amapflying.AmapBroadcastService.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent == null) {
                        return;
                    }
                    if (AmapBroadcastConstants.getAmapBroadcast().equals(intent.getAction())) {
                        int intExtra = intent.getIntExtra(Auto3DBroadcastReceiver.KEY_TYPE, -1);
                        int intExtra2 = intent.getIntExtra(Auto3DBroadcastReceiver.EXTRA_STATE, -1);
                        if (intExtra == 10019) {
                            if (intExtra2 == 8 || intExtra2 == 24) {
                                String str = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                                AmapBroadcastService.this.b();
                            }
                        }
                    }
                }
            };
            this.f4491f = new BroadcastReceiver() { // from class: com.navi.tool.amapflying.AmapBroadcastService.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent != null && Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV.equals(intent.getAction())) {
                        int intExtra = intent.getIntExtra(Auto3DBroadcastReceiver.KEY_TYPE, -1);
                        int intExtra2 = intent.getIntExtra("EXTSCREEN_OPERATE_TYPE", -1);
                        int intExtra3 = intent.getIntExtra("EXTSCREEN_WINDOW_INFO", -1);
                        int intExtra4 = intent.getIntExtra("EXTSCREEN_MODE_INFO", -1);
                        if (intExtra == 10116 && intExtra2 == 0 && intExtra3 == 1002) {
                            if (intExtra4 == 1 || intExtra4 == 2) {
                                String str = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                                AmapBroadcastService.this.d(intExtra4);
                            }
                        }
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(AmapBroadcastConstants.getAmapBroadcast());
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
            int i8 = Build.VERSION.SDK_INT;
            if (i8 >= 33) {
                registerReceiver(this.f4486a, intentFilter, 2);
                registerReceiver(this.f4489d, intentFilter, 2);
                registerReceiver(this.f4490e, intentFilter, 2);
                registerReceiver(this.f4491f, intentFilter2, 2);
            } else {
                registerReceiver(this.f4486a, intentFilter);
                registerReceiver(this.f4489d, intentFilter);
                registerReceiver(this.f4490e, intentFilter);
                registerReceiver(this.f4491f, intentFilter2);
            }
            this.f4497l = SharedPreferencesUtils.isCustomAmapBroadcastEnabled(this);
            this.f4495j = 0;
            this.f4496k = 0;
            HandlerThread handlerThread = new HandlerThread("AmapAutoSwitch");
            this.f4493h = handlerThread;
            handlerThread.start();
            this.f4494i = new Handler(this.f4493h.getLooper());
            this.f4492g = new BroadcastReceiver() { // from class: com.navi.tool.amapflying.AmapBroadcastService.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent == null) {
                        return;
                    }
                    boolean z6 = false;
                    if (intent.getBooleanExtra(AmapBroadcastConstants.EXTRA_SELF_BROADCAST, false)) {
                        return;
                    }
                    String action = intent.getAction();
                    boolean equals = AmapBroadcastConstants.DEFAULT_AMAP_BROADCAST.equals(action);
                    int i9 = 1;
                    char c7 = 1;
                    char c8 = 1;
                    AmapBroadcastService amapBroadcastService = AmapBroadcastService.this;
                    if (equals) {
                        if (amapBroadcastService.f4497l) {
                            int i10 = amapBroadcastService.f4496k + 1;
                            amapBroadcastService.f4496k = i10;
                            if (i10 >= 20) {
                                amapBroadcastService.f4495j = 0;
                                amapBroadcastService.f4496k = 0;
                                amapBroadcastService.f4497l = false;
                                new Handler(Looper.getMainLooper()).post(new g0(amapBroadcastService, z6, i9));
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (AmapBroadcastConstants.DEFAULT_CUSTOM_AMAP_BROADCAST.equals(action)) {
                        amapBroadcastService.f4496k = 0;
                        if (amapBroadcastService.f4497l) {
                            return;
                        }
                        int i11 = amapBroadcastService.f4495j + 1;
                        amapBroadcastService.f4495j = i11;
                        if (i11 >= 20) {
                            amapBroadcastService.f4495j = 0;
                            amapBroadcastService.f4496k = 0;
                            amapBroadcastService.f4497l = true;
                            new Handler(Looper.getMainLooper()).post(new g0(amapBroadcastService, c8 == true ? 1 : 0, c7 == true ? 1 : 0));
                        }
                    }
                }
            };
            IntentFilter intentFilter3 = new IntentFilter();
            intentFilter3.addAction(AmapBroadcastConstants.DEFAULT_AMAP_BROADCAST);
            intentFilter3.addAction(AmapBroadcastConstants.DEFAULT_CUSTOM_AMAP_BROADCAST);
            if (i8 >= 33) {
                registerReceiver(this.f4492g, intentFilter3, null, this.f4494i, 2);
            } else {
                registerReceiver(this.f4492g, intentFilter3, null, this.f4494i);
            }
        } catch (Throwable unused) {
            stopSelf();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        f fVar;
        super.onDestroy();
        AmapStandardBroadcastReceiver amapStandardBroadcastReceiver = this.f4486a;
        if (amapStandardBroadcastReceiver != null) {
            unregisterReceiver(amapStandardBroadcastReceiver);
            this.f4486a = null;
        }
        PersonalizedNavigationReceiver personalizedNavigationReceiver = this.f4489d;
        if (personalizedNavigationReceiver != null) {
            unregisterReceiver(personalizedNavigationReceiver);
            this.f4489d = null;
        }
        BroadcastReceiver broadcastReceiver = this.f4490e;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            this.f4490e = null;
        }
        BroadcastReceiver broadcastReceiver2 = this.f4491f;
        if (broadcastReceiver2 != null) {
            unregisterReceiver(broadcastReceiver2);
            this.f4491f = null;
        }
        BroadcastReceiver broadcastReceiver3 = this.f4492g;
        if (broadcastReceiver3 != null) {
            try {
                unregisterReceiver(broadcastReceiver3);
            } catch (Exception unused) {
            }
            this.f4492g = null;
        }
        HandlerThread handlerThread = this.f4493h;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.f4493h = null;
        }
        this.f4494i = null;
        this.f4495j = 0;
        this.f4496k = 0;
        SharedPreferences sharedPreferences = this.f4487b;
        if (sharedPreferences == null || (fVar = this.f4488c) == null) {
            return;
        }
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(fVar);
        this.f4488c = null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        SharedPreferences sharedPreferences;
        try {
            startForeground(1003, a());
            if (intent == null || !ACTION_TRIGGER_EXTSCREEN_ACTIVATION.equals(intent.getAction()) || (sharedPreferences = this.f4487b) == null) {
                return 1;
            }
            if (!sharedPreferences.getBoolean("extscreen_activation_enabled", false)) {
                return 1;
            }
            c();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new a(this, 6), 2100L);
            if (!SharedPreferencesUtils.isTraditionalClusterEnabled(this)) {
                return 1;
            }
            for (int i9 = 0; i9 < 2; i9++) {
                handler.postDelayed(new a(this, 7), (i9 * 2000) + 2100);
            }
            return 1;
        } catch (Throwable unused) {
            stopSelf();
            return 2;
        }
    }
}
