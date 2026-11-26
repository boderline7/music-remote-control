package com.navi.tool.carinfo;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.j;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.p0;
import androidx.appcompat.app.s0;
import androidx.lifecycle.g0;
import androidx.lifecycle.h0;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.service.CarClimateManager;
import com.navi.tool.service.CarSoundManager;
import com.navi.tool.service.SceneModeManager;
import com.navi.tool.utils.SharedPreferencesUtils;
import g2.a;
import g2.c;
import g4.o;
import java.util.Map;
import o4.b;
import o4.d;
import o4.e;
import o4.h;
import o4.i;
import o4.r;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CarInfoService extends Service {
    public static CarInfoService F;

    /* renamed from: a, reason: collision with root package name */
    public CarSoundManager f4687a;

    /* renamed from: b, reason: collision with root package name */
    public CarClimateManager f4688b;

    /* renamed from: c, reason: collision with root package name */
    public SceneModeManager f4689c;

    /* renamed from: d, reason: collision with root package name */
    public r f4690d;

    /* renamed from: e, reason: collision with root package name */
    public c f4691e;

    /* renamed from: f, reason: collision with root package name */
    public final Handler f4692f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f4693g;

    /* renamed from: h, reason: collision with root package name */
    public final h f4694h;

    /* renamed from: i, reason: collision with root package name */
    public final d f4695i;

    /* renamed from: j, reason: collision with root package name */
    public final s0 f4696j;

    /* renamed from: k, reason: collision with root package name */
    public final j f4697k;

    /* renamed from: l, reason: collision with root package name */
    public final i f4698l;

    /* renamed from: m, reason: collision with root package name */
    public final o4.j f4699m;

    /* renamed from: n, reason: collision with root package name */
    public final e f4700n;

    /* renamed from: o, reason: collision with root package name */
    public AlertDialog f4701o;

    /* renamed from: p, reason: collision with root package name */
    public AlertDialog f4702p;

    /* renamed from: q, reason: collision with root package name */
    public ToneGenerator f4703q;

    /* renamed from: r, reason: collision with root package name */
    public Thread f4704r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f4705s;

    /* renamed from: t, reason: collision with root package name */
    public boolean f4706t;

    /* renamed from: u, reason: collision with root package name */
    public View f4707u;

    /* renamed from: v, reason: collision with root package name */
    public int f4708v;

    /* renamed from: w, reason: collision with root package name */
    public final d f4709w;

    /* renamed from: x, reason: collision with root package name */
    public final e f4710x;

    /* renamed from: y, reason: collision with root package name */
    public final e f4711y;

    /* renamed from: z, reason: collision with root package name */
    public static final int[] f4686z = {z1.c.INVOKE_STATIC_RANGE, 68, 24, 99, 2, 62, 1035, 26, 15, 16, 17, 18, 19, 47, 71, 72, z1.c.INVOKE_INTERFACE_RANGE, 91, 1, 22, 23, 32, 33, 34, 35, 79, z1.c.INVOKE_SUPER_RANGE, 75, 80, z1.c.INVOKE_VIRTUAL, 95, 96, 97, 5, 6, 36, 37, 74, 28, 29, 13};
    public static final int[] A = {100, z1.c.SGET_CHAR, z1.c.SGET_SHORT, z1.c.SPUT, 10, 91, 5, 6, 44, 45, 52, 54, 93, 94, 95, 96, z1.c.INVOKE_INTERFACE, 115, z1.c.DOUBLE_TO_LONG, z1.c.INT_TO_BYTE, z1.c.USHR_LONG_2ADDR, z1.c.ADD_FLOAT_2ADDR, z1.c.MUL_FLOAT, z1.c.AND_LONG, z1.c.OR_LONG, z1.c.MUL_LONG_2ADDR, z1.c.DIV_LONG_2ADDR};
    public static final int[] B = {10, 1, 12, 8, 4, 18, 1035, 62, 6, 3, 24, 80, 19, 61, 64, 66, 71, 48, 58, 59, 60, 67, 68};
    public static final int[] C = {10, 23, 24, 4, 25, z1.c.SGET_SHORT, 8};
    public static final int[] D = {11};
    public static final int[] E = {96, 97, z1.c.INVOKE_VIRTUAL};

    /* JADX WARN: Type inference failed for: r0v4, types: [o4.e] */
    /* JADX WARN: Type inference failed for: r0v5, types: [o4.e] */
    /* JADX WARN: Type inference failed for: r1v7, types: [o4.e] */
    public CarInfoService() {
        o4.c.c();
        this.f4692f = new Handler(Looper.getMainLooper());
        final int i7 = 0;
        this.f4693g = false;
        this.f4694h = new h(this, i7);
        this.f4695i = new d(this, i7);
        this.f4696j = new s0(15, this);
        final int i8 = 2;
        this.f4697k = new j(i8, this);
        i iVar = new i();
        iVar.attachInterface(iVar, "com.desaysv.ivi.vdb.IVDBusCallback");
        this.f4698l = iVar;
        o4.j jVar = new o4.j();
        jVar.attachInterface(jVar, "com.desaysv.ivi.vdb.IVDBusNotify");
        this.f4699m = jVar;
        this.f4700n = new h0(this) { // from class: o4.e

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CarInfoService f7962b;

            {
                this.f7962b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i9 = i7;
                int i10 = 0;
                int i11 = 1;
                CarInfoService carInfoService = this.f7962b;
                switch (i9) {
                    case 0:
                        Map map = (Map) obj;
                        int[] iArr = CarInfoService.f4686z;
                        carInfoService.getClass();
                        if (map != null && !map.isEmpty() && SharedPreferencesUtils.getAppPrefs(carInfoService).getBoolean("tire_pressure_alarm_enabled", false)) {
                            StringBuilder sb = new StringBuilder();
                            if ("异常".equals(map.get("fl"))) {
                                sb.append("左前轮胎压异常\n");
                                i10 = 1;
                            }
                            if ("异常".equals(map.get("fr"))) {
                                sb.append("右前轮胎压异常\n");
                                i10 = 1;
                            }
                            if ("异常".equals(map.get("rl"))) {
                                sb.append("左后轮胎压异常\n");
                            } else {
                                i11 = i10;
                            }
                            if ("异常".equals(map.get("rr"))) {
                                sb.append("右后轮胎压异常\n");
                            } else if (i11 == 0) {
                            }
                            carInfoService.f4692f.post(new p0(14, carInfoService, sb.toString().trim()));
                            break;
                        }
                        break;
                    case 1:
                        Map map2 = (Map) obj;
                        int[] iArr2 = CarInfoService.f4686z;
                        carInfoService.getClass();
                        if (map2 != null && !map2.isEmpty() && SharedPreferencesUtils.getAppPrefs(carInfoService).getBoolean("auto_fragrance_shortage_alarm_enabled", true) && !carInfoService.f4706t && "1".equals((String) map2.get("fragrance_shortage_raw"))) {
                            carInfoService.f4706t = true;
                            carInfoService.f4692f.post(new d(carInfoService, 4));
                            break;
                        }
                        break;
                    default:
                        Map map3 = (Map) obj;
                        int[] iArr3 = CarInfoService.f4686z;
                        carInfoService.getClass();
                        if (map3 != null && !map3.isEmpty() && SharedPreferencesUtils.getAppPrefs(carInfoService).getBoolean("wireless_charging_capsule_enabled", true)) {
                            try {
                                i10 = Integer.parseInt((String) map3.get("wireless_charging_state"));
                            } catch (Exception unused) {
                            }
                            int i12 = carInfoService.f4708v;
                            if (i10 != i12) {
                                carInfoService.f4708v = i10;
                                Handler handler = carInfoService.f4692f;
                                if (i10 != 1 || i12 != 0) {
                                    if (i10 == 2 && i12 == 1) {
                                        handler.post(new f(carInfoService, g4.o.ic_wireless_charging_capsule_done, "手机已充满", g4.t.nfc_success, "nfc_success.ogg", 0));
                                        break;
                                    }
                                } else {
                                    handler.post(new f(carInfoService, g4.o.ic_wireless_charging_capsule_charging, "MAX 50W无线 超级快充", g4.t.wireless_charging_started, "wireless_charging_started.ogg", 0));
                                    break;
                                }
                            }
                        }
                        break;
                }
            }
        };
        this.f4705s = false;
        this.f4706t = false;
        this.f4708v = -1;
        final int i9 = 1;
        this.f4709w = new d(this, i9);
        this.f4710x = new h0(this) { // from class: o4.e

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CarInfoService f7962b;

            {
                this.f7962b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i9;
                int i10 = 0;
                int i11 = 1;
                CarInfoService carInfoService = this.f7962b;
                switch (i92) {
                    case 0:
                        Map map = (Map) obj;
                        int[] iArr = CarInfoService.f4686z;
                        carInfoService.getClass();
                        if (map != null && !map.isEmpty() && SharedPreferencesUtils.getAppPrefs(carInfoService).getBoolean("tire_pressure_alarm_enabled", false)) {
                            StringBuilder sb = new StringBuilder();
                            if ("异常".equals(map.get("fl"))) {
                                sb.append("左前轮胎压异常\n");
                                i10 = 1;
                            }
                            if ("异常".equals(map.get("fr"))) {
                                sb.append("右前轮胎压异常\n");
                                i10 = 1;
                            }
                            if ("异常".equals(map.get("rl"))) {
                                sb.append("左后轮胎压异常\n");
                            } else {
                                i11 = i10;
                            }
                            if ("异常".equals(map.get("rr"))) {
                                sb.append("右后轮胎压异常\n");
                            } else if (i11 == 0) {
                            }
                            carInfoService.f4692f.post(new p0(14, carInfoService, sb.toString().trim()));
                            break;
                        }
                        break;
                    case 1:
                        Map map2 = (Map) obj;
                        int[] iArr2 = CarInfoService.f4686z;
                        carInfoService.getClass();
                        if (map2 != null && !map2.isEmpty() && SharedPreferencesUtils.getAppPrefs(carInfoService).getBoolean("auto_fragrance_shortage_alarm_enabled", true) && !carInfoService.f4706t && "1".equals((String) map2.get("fragrance_shortage_raw"))) {
                            carInfoService.f4706t = true;
                            carInfoService.f4692f.post(new d(carInfoService, 4));
                            break;
                        }
                        break;
                    default:
                        Map map3 = (Map) obj;
                        int[] iArr3 = CarInfoService.f4686z;
                        carInfoService.getClass();
                        if (map3 != null && !map3.isEmpty() && SharedPreferencesUtils.getAppPrefs(carInfoService).getBoolean("wireless_charging_capsule_enabled", true)) {
                            try {
                                i10 = Integer.parseInt((String) map3.get("wireless_charging_state"));
                            } catch (Exception unused) {
                            }
                            int i12 = carInfoService.f4708v;
                            if (i10 != i12) {
                                carInfoService.f4708v = i10;
                                Handler handler = carInfoService.f4692f;
                                if (i10 != 1 || i12 != 0) {
                                    if (i10 == 2 && i12 == 1) {
                                        handler.post(new f(carInfoService, g4.o.ic_wireless_charging_capsule_done, "手机已充满", g4.t.nfc_success, "nfc_success.ogg", 0));
                                        break;
                                    }
                                } else {
                                    handler.post(new f(carInfoService, g4.o.ic_wireless_charging_capsule_charging, "MAX 50W无线 超级快充", g4.t.wireless_charging_started, "wireless_charging_started.ogg", 0));
                                    break;
                                }
                            }
                        }
                        break;
                }
            }
        };
        this.f4711y = new h0(this) { // from class: o4.e

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CarInfoService f7962b;

            {
                this.f7962b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i8;
                int i10 = 0;
                int i11 = 1;
                CarInfoService carInfoService = this.f7962b;
                switch (i92) {
                    case 0:
                        Map map = (Map) obj;
                        int[] iArr = CarInfoService.f4686z;
                        carInfoService.getClass();
                        if (map != null && !map.isEmpty() && SharedPreferencesUtils.getAppPrefs(carInfoService).getBoolean("tire_pressure_alarm_enabled", false)) {
                            StringBuilder sb = new StringBuilder();
                            if ("异常".equals(map.get("fl"))) {
                                sb.append("左前轮胎压异常\n");
                                i10 = 1;
                            }
                            if ("异常".equals(map.get("fr"))) {
                                sb.append("右前轮胎压异常\n");
                                i10 = 1;
                            }
                            if ("异常".equals(map.get("rl"))) {
                                sb.append("左后轮胎压异常\n");
                            } else {
                                i11 = i10;
                            }
                            if ("异常".equals(map.get("rr"))) {
                                sb.append("右后轮胎压异常\n");
                            } else if (i11 == 0) {
                            }
                            carInfoService.f4692f.post(new p0(14, carInfoService, sb.toString().trim()));
                            break;
                        }
                        break;
                    case 1:
                        Map map2 = (Map) obj;
                        int[] iArr2 = CarInfoService.f4686z;
                        carInfoService.getClass();
                        if (map2 != null && !map2.isEmpty() && SharedPreferencesUtils.getAppPrefs(carInfoService).getBoolean("auto_fragrance_shortage_alarm_enabled", true) && !carInfoService.f4706t && "1".equals((String) map2.get("fragrance_shortage_raw"))) {
                            carInfoService.f4706t = true;
                            carInfoService.f4692f.post(new d(carInfoService, 4));
                            break;
                        }
                        break;
                    default:
                        Map map3 = (Map) obj;
                        int[] iArr3 = CarInfoService.f4686z;
                        carInfoService.getClass();
                        if (map3 != null && !map3.isEmpty() && SharedPreferencesUtils.getAppPrefs(carInfoService).getBoolean("wireless_charging_capsule_enabled", true)) {
                            try {
                                i10 = Integer.parseInt((String) map3.get("wireless_charging_state"));
                            } catch (Exception unused) {
                            }
                            int i12 = carInfoService.f4708v;
                            if (i10 != i12) {
                                carInfoService.f4708v = i10;
                                Handler handler = carInfoService.f4692f;
                                if (i10 != 1 || i12 != 0) {
                                    if (i10 == 2 && i12 == 1) {
                                        handler.post(new f(carInfoService, g4.o.ic_wireless_charging_capsule_done, "手机已充满", g4.t.nfc_success, "nfc_success.ogg", 0));
                                        break;
                                    }
                                } else {
                                    handler.post(new f(carInfoService, g4.o.ic_wireless_charging_capsule_charging, "MAX 50W无线 超级快充", g4.t.wireless_charging_started, "wireless_charging_started.ogg", 0));
                                    break;
                                }
                            }
                        }
                        break;
                }
            }
        };
    }

    public static void a(CarInfoService carInfoService, int i7, int[] iArr) {
        ((a) carInfoService.f4691e).j(iArr, i7, carInfoService.getPackageName(), carInfoService.f4698l);
        Bundle bundle = new Bundle();
        bundle.putIntArray("CMD_ID_ARRAY", iArr);
        VDEvent vDEvent = new VDEvent(i7);
        vDEvent.f3190b = bundle;
        c cVar = carInfoService.f4691e;
        o4.j jVar = carInfoService.f4699m;
        a aVar = (a) cVar;
        aVar.getClass();
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(c.DESCRIPTOR);
            obtain.writeInt(1);
            vDEvent.writeToParcel(obtain, 0);
            obtain.writeStrongInterface(jVar);
            aVar.f6431a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public static c d() {
        CarInfoService carInfoService = F;
        if (carInfoService != null) {
            return carInfoService.f4691e;
        }
        return null;
    }

    public final void b() {
        d dVar = this.f4695i;
        Handler handler = this.f4692f;
        if (this.f4691e == null && !this.f4693g) {
            Intent intent = new Intent("action.desaysv.ivi.vds.carinfo.SERVICE");
            intent.setPackage("com.desaysv.ivi.vds.carinfo");
            try {
                if (bindService(intent, this.f4697k, 1)) {
                    this.f4693g = true;
                } else {
                    handler.postDelayed(dVar, 10000L);
                }
            } catch (Exception unused) {
                handler.postDelayed(dVar, 10000L);
            }
        }
    }

    public final void c(int i7, int[] iArr) {
        if (this.f4691e == null) {
            return;
        }
        for (int i8 : iArr) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("CMD_ID", i8);
                VDEvent vDEvent = new VDEvent(i7);
                vDEvent.f3190b = bundle;
                VDEvent h7 = ((a) this.f4691e).h(vDEvent);
                if (h7 != null) {
                    o4.c.c().o(h7);
                }
                Thread.sleep(20L);
            } catch (Exception unused) {
            }
        }
    }

    public final void e() {
        this.f4692f.removeCallbacks(this.f4709w);
        if (this.f4707u != null) {
            try {
                ((WindowManager) getSystemService("window")).removeView(this.f4707u);
            } catch (Exception unused) {
            }
            this.f4707u = null;
        }
    }

    public final void f(int[] iArr) {
        if (this.f4691e == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putIntArray("IntVector", iArr);
            VDEvent vDEvent = new VDEvent(29987);
            vDEvent.f3190b = bundle;
            ((a) this.f4691e).i(vDEvent);
        } catch (RemoteException unused) {
        }
    }

    public final void g() {
        this.f4705s = false;
        Thread thread = this.f4704r;
        if (thread != null) {
            thread.interrupt();
            this.f4704r = null;
        }
        ToneGenerator toneGenerator = this.f4703q;
        if (toneGenerator != null) {
            toneGenerator.release();
            this.f4703q = null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        F = this;
        o4.c.c().H.f(this.f4700n);
        o4.c.c().F.f(this.f4710x);
        o4.c.c().F.f(this.f4711y);
        CarSoundManager carSoundManager = new CarSoundManager(this);
        this.f4687a = carSoundManager;
        carSoundManager.start();
        CarClimateManager carClimateManager = new CarClimateManager(this);
        this.f4688b = carClimateManager;
        carClimateManager.start();
        SceneModeManager sceneModeManager = new SceneModeManager(this);
        this.f4689c = sceneModeManager;
        sceneModeManager.start();
        r rVar = new r(this);
        this.f4690d = rVar;
        if (!rVar.f8014h) {
            rVar.f8014h = true;
            rVar.i();
            rVar.f8008b.registerOnSharedPreferenceChangeListener(rVar.f8018l);
            o4.c cVar = rVar.f8009c;
            cVar.f7941i.f(rVar.f8015i);
            g0 g0Var = cVar.f7943k;
            g0Var.f(rVar.f8016j);
            g0 g0Var2 = cVar.f7949q;
            g0Var2.f(rVar.f8017k);
            rVar.f8011e = r.k((String) cVar.f7941i.d());
            rVar.f8012f = r.k((String) g0Var.d());
            String str = (String) g0Var2.d();
            rVar.f8013g = str == null ? null : str.trim();
            rVar.j();
        }
        if (SharedPreferencesUtils.getAppPrefs(this).getBoolean("amap_speed_sync_enabled", false)) {
            b.b(this).d();
        }
        b();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        F = null;
        o4.c.c().H.g(this.f4700n);
        o4.c.c().F.g(this.f4710x);
        o4.c.c().F.g(this.f4711y);
        e();
        g();
        AlertDialog alertDialog = this.f4701o;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f4701o.dismiss();
        }
        AlertDialog alertDialog2 = this.f4702p;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            this.f4702p.dismiss();
        }
        CarSoundManager carSoundManager = this.f4687a;
        if (carSoundManager != null) {
            carSoundManager.stop();
            this.f4687a = null;
        }
        CarClimateManager carClimateManager = this.f4688b;
        if (carClimateManager != null) {
            carClimateManager.stop();
            this.f4688b = null;
        }
        SceneModeManager sceneModeManager = this.f4689c;
        if (sceneModeManager != null) {
            sceneModeManager.stop();
            this.f4689c = null;
        }
        r rVar = this.f4690d;
        if (rVar != null) {
            if (rVar.f8014h) {
                rVar.f8014h = false;
                rVar.f8008b.unregisterOnSharedPreferenceChangeListener(rVar.f8018l);
                o4.c cVar = rVar.f8009c;
                cVar.f7941i.g(rVar.f8015i);
                cVar.f7943k.g(rVar.f8016j);
                cVar.f7949q.g(rVar.f8017k);
            }
            this.f4690d = null;
        }
        Handler handler = this.f4692f;
        handler.removeCallbacks(this.f4695i);
        handler.removeCallbacks(this.f4696j);
        b.b(this).e();
        c cVar2 = this.f4691e;
        if (cVar2 != null) {
            try {
                ((a) cVar2).f6431a.unlinkToDeath(this.f4694h, 0);
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            unbindService(this.f4697k);
            this.f4691e = null;
        }
        o4.c.c().n(false);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        NotificationChannel notificationChannel = new NotificationChannel("car_link_channel", "车联服务通道", 2);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        startForeground(10086, new Notification.Builder(this, "car_link_channel").setContentTitle("车联服务运行中").setContentText("正在连接车机总线...").setSmallIcon(o.ic_launcher_foreground).build());
        return 1;
    }
}
