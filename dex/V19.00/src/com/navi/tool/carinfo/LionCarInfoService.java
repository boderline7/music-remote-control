package com.navi.tool.carinfo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.media.j;
import androidx.appcompat.app.s0;
import androidx.lifecycle.g0;
import com.lion.api.bus.sync.SyncBusEvent;
import com.navi.tool.service.CarClimateManager;
import com.navi.tool.service.CarSoundManager;
import com.navi.tool.utils.SharedPreferencesUtils;
import f4.a;
import f4.c;
import g4.o;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import o4.b;
import o4.h;
import o4.k;
import o4.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LionCarInfoService extends Service {
    public static final int CABIN_CAR_SPEED = 2099;
    public static final int CABIN_DOORS_STATUS = 2041;
    public static final int CABIN_ENGINE_HOOD_STATUS = 2048;
    public static final int CABIN_ENGINE_STATE = 2201;
    public static final int CABIN_FL_DOOR_STATUS = 2042;
    public static final int CABIN_FL_WINDOW_STATE = 2027;
    public static final int CABIN_FR_DOOR_STATUS = 2043;
    public static final int CABIN_FR_WINDOW_STATE = 2028;
    public static final int CABIN_GEAR_DISPLAY_INFO = 2098;
    public static final int CABIN_RL_DOOR_STATUS = 2046;
    public static final int CABIN_RL_WINDOW_STATE = 2031;
    public static final int CABIN_RR_DOOR_STATUS = 2047;
    public static final int CABIN_RR_WINDOW_STATE = 2032;
    public static final int CABIN_SEAT_HEAT_STATE = 2199;
    public static final int CABIN_SEAT_WIND_STATE = 2200;
    public static final int CABIN_SKY_LIGHT_CONTROL = 2187;
    public static final int CABIN_TRUNK_STATUS = 2049;
    public static final int DOOR_AREA_ENGINE_HOOD = 4;
    public static final int DOOR_AREA_FL = 0;
    public static final int DOOR_AREA_FR = 1;
    public static final int DOOR_AREA_RL = 2;
    public static final int DOOR_AREA_RR = 3;
    public static final int DOOR_AREA_TRUNK = 5;
    public static final int HVAC_AC = 4;
    public static final int HVAC_AREA_LEFT = 1;
    public static final int HVAC_AREA_RIGHT = 2;
    public static final int HVAC_AUTO = 7046;
    public static final int HVAC_FRONT_LEFT_TEMP = 2;
    public static final int HVAC_INSIDE_TEMP = 26;
    public static final int HVAC_OUTSIDE_TEMP = 25;
    public static final int HVAC_POWER = 1;
    public static final int HVAC_WIND_DIRECTION = 11;
    public static final int HVAC_WIND_LEVEL = 7053;
    public static final int VEHICLE_WINDOW_STATUS = 30002;
    public static final int WINDOW_AREA_FL = 1;
    public static final int WINDOW_AREA_FR = 2;
    public static final int WINDOW_AREA_RL = 3;
    public static final int WINDOW_AREA_RR = 4;

    /* renamed from: m, reason: collision with root package name */
    public static LionCarInfoService f4712m;

    /* renamed from: a, reason: collision with root package name */
    public c f4713a;

    /* renamed from: c, reason: collision with root package name */
    public HandlerThread f4715c;

    /* renamed from: d, reason: collision with root package name */
    public Handler f4716d;

    /* renamed from: f, reason: collision with root package name */
    public CarSoundManager f4718f;

    /* renamed from: g, reason: collision with root package name */
    public CarClimateManager f4719g;

    /* renamed from: b, reason: collision with root package name */
    public final Handler f4714b = new Handler(Looper.getMainLooper());

    /* renamed from: e, reason: collision with root package name */
    public boolean f4717e = false;

    /* renamed from: h, reason: collision with root package name */
    public final h f4720h = new h(this, 1);

    /* renamed from: i, reason: collision with root package name */
    public final k f4721i = new k(this, 1);

    /* renamed from: j, reason: collision with root package name */
    public final s0 f4722j = new s0(16, this);

    /* renamed from: k, reason: collision with root package name */
    public final l f4723k = new l(this);

    /* renamed from: l, reason: collision with root package name */
    public final j f4724l = new j(3, this);

    public static void a(LionCarInfoService lionCarInfoService) {
        HandlerThread handlerThread = lionCarInfoService.f4715c;
        if (handlerThread == null || !handlerThread.isAlive()) {
            HandlerThread handlerThread2 = new HandlerThread("LionRealtimeRefresh", 10);
            lionCarInfoService.f4715c = handlerThread2;
            handlerThread2.start();
            lionCarInfoService.f4716d = new Handler(lionCarInfoService.f4715c.getLooper());
        }
        Handler handler = lionCarInfoService.f4716d;
        s0 s0Var = lionCarInfoService.f4722j;
        handler.removeCallbacks(s0Var);
        lionCarInfoService.f4716d.postDelayed(s0Var, 1000L);
    }

    public static void b(int i7, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        Object f7 = f(bundle);
        if (f7 instanceof Integer) {
            int intValue = ((Integer) f7).intValue();
            if (intValue == 0 || intValue == 1) {
                boolean z6 = intValue == 1;
                o4.c c7 = o4.c.c();
                if (i7 == 0) {
                    h(c7, "fl", z6);
                    return;
                }
                if (i7 == 1) {
                    h(c7, "fr", z6);
                    return;
                }
                if (i7 == 2) {
                    h(c7, "rl", z6);
                    return;
                }
                if (i7 == 3) {
                    h(c7, "rr", z6);
                } else if (i7 == 4) {
                    h(c7, "hood", z6);
                } else if (i7 == 5) {
                    h(c7, "trunk", z6);
                }
            }
        }
    }

    public static void c(int i7, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        Object f7 = f(bundle);
        if (f7 instanceof Integer) {
            int intValue = ((Integer) f7).intValue();
            String str = intValue == 1 ? "关" : intValue == 0 ? "开" : null;
            if (str == null) {
                return;
            }
            o4.c c7 = o4.c.c();
            if (i7 == 1) {
                k(c7, "fl", str);
                return;
            }
            if (i7 == 2) {
                k(c7, "fr", str);
            } else if (i7 == 3) {
                k(c7, "rl", str);
            } else if (i7 == 4) {
                k(c7, "rr", str);
            }
        }
    }

    public static Object f(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Object obj = bundle.get("data");
        if (obj == null) {
            obj = bundle.get("DATA");
        }
        return obj == null ? bundle.get("status") : obj;
    }

    public static void g(int i7, Bundle bundle) {
        Object f7;
        String str;
        if (bundle == null || (f7 = f(bundle)) == null) {
            return;
        }
        o4.c c7 = o4.c.c();
        if (!(f7 instanceof Integer)) {
            if (f7 instanceof Float) {
                float floatValue = ((Float) f7).floatValue();
                if (i7 == 2099) {
                    g0 g0Var = c7.f7938f;
                    if (g0Var instanceof g0) {
                        g0Var.i(String.valueOf(Math.round(floatValue)));
                        return;
                    }
                    return;
                }
                if (i7 == 2) {
                    i(c7, "temp", String.format("%.1f°C", Float.valueOf(floatValue)));
                    return;
                } else {
                    if (i7 == 25) {
                        g0 g0Var2 = c7.f7957y;
                        if (g0Var2 instanceof g0) {
                            g0Var2.i(String.format("%.1f°C", Float.valueOf(floatValue)));
                        }
                        i(c7, "outside_temp", String.format("%.1f°C", Float.valueOf(floatValue)));
                        return;
                    }
                    return;
                }
            }
            return;
        }
        int intValue = ((Integer) f7).intValue();
        str = "开";
        String str2 = "关";
        if (i7 == 1) {
            i(c7, "power", intValue != 1 ? "关" : "开");
            return;
        }
        if (i7 == 2) {
            i(c7, "temp", String.format("%.1f°C", Float.valueOf(intValue)));
            return;
        }
        if (i7 == 4) {
            i(c7, "ac", intValue != 1 ? "关" : "开");
            return;
        }
        String str3 = "未知";
        if (i7 == 11) {
            if (bundle.getInt("status", 1) == 1) {
                if (intValue == 0) {
                    str3 = "关闭";
                } else if (intValue == 1) {
                    str3 = "吹脸";
                } else if (intValue == 2) {
                    str3 = "吹脚";
                } else if (intValue == 3) {
                    str3 = "吹脸+吹脚";
                } else if (intValue == 4) {
                    str3 = "除霜+吹脚";
                } else if (intValue == 5) {
                    str3 = "除霜";
                }
                i(c7, "mode", str3);
                return;
            }
            return;
        }
        if (i7 == 25) {
            g0 g0Var3 = c7.f7957y;
            if (g0Var3 instanceof g0) {
                g0Var3.i(String.format("%.1f°C", Float.valueOf(intValue)));
            }
            i(c7, "outside_temp", String.format("%.1f°C", Float.valueOf(intValue)));
            return;
        }
        Boolean bool = null;
        if (i7 == 2041) {
            int i8 = bundle.getInt("data", -1);
            int i9 = bundle.getInt("status", -1);
            if (i9 == 1) {
                bool = Boolean.TRUE;
            } else if (i9 == 0) {
                bool = Boolean.FALSE;
            }
            if (bool == null) {
                return;
            }
            if (i8 == 0) {
                h(c7, "fl", bool.booleanValue());
                return;
            }
            if (i8 == 1) {
                h(c7, "fr", bool.booleanValue());
                return;
            }
            if (i8 == 2) {
                h(c7, "rl", bool.booleanValue());
                return;
            }
            if (i8 == 3) {
                h(c7, "rr", bool.booleanValue());
                return;
            } else if (i8 == 4) {
                h(c7, "hood", bool.booleanValue());
                return;
            } else {
                if (i8 == 5) {
                    h(c7, "trunk", bool.booleanValue());
                    return;
                }
                return;
            }
        }
        if (i7 == 2187) {
            if (intValue == 1) {
                k(c7, "sunroof", "开");
                return;
            } else {
                if (intValue == 0) {
                    k(c7, "sunroof", "关");
                    return;
                }
                return;
            }
        }
        if (i7 == 7046) {
            i(c7, "auto", intValue != 1 ? "关" : "开");
            return;
        }
        if (i7 == 7053) {
            if (bundle.getInt("status", 1) == 1) {
                i(c7, "fan", String.valueOf(intValue));
                return;
            }
            return;
        }
        if (i7 == 30002) {
            int i10 = bundle.getInt("data", -1);
            int i11 = bundle.getInt("status", -1);
            if (i11 == 1) {
                str = "关";
            } else if (i11 != 0) {
                str = null;
            }
            if (str == null) {
                return;
            }
            if (i10 == 1) {
                k(c7, "fl", str);
                return;
            }
            if (i10 == 2) {
                k(c7, "fr", str);
                return;
            } else if (i10 == 3) {
                k(c7, "rl", str);
                return;
            } else {
                if (i10 == 4) {
                    k(c7, "rr", str);
                    return;
                }
                return;
            }
        }
        if (i7 == 2098) {
            if (intValue == 1) {
                str3 = "D";
            } else if (intValue == 2) {
                str3 = "N";
            } else if (intValue == 3) {
                str3 = "R";
            } else if (intValue == 4) {
                str3 = "P";
            }
            g0 g0Var4 = c7.f7939g;
            if (g0Var4 instanceof g0) {
                g0Var4.i(str3);
                return;
            }
            return;
        }
        if (i7 == 2099) {
            g0 g0Var5 = c7.f7938f;
            if (g0Var5 instanceof g0) {
                g0Var5.i(String.valueOf(intValue));
                return;
            }
            return;
        }
        switch (i7) {
            case CABIN_SEAT_HEAT_STATE /* 2199 */:
                int i12 = bundle.getInt("status", 1);
                if (intValue != 0) {
                    str2 = intValue + " 档";
                }
                if (i12 != 2) {
                    j(c7, "heat_fl", str2);
                    break;
                } else {
                    j(c7, "heat_fr", str2);
                    break;
                }
            case CABIN_SEAT_WIND_STATE /* 2200 */:
                int i13 = bundle.getInt("status", 1);
                if (intValue != 0) {
                    str2 = intValue + " 档";
                }
                if (i13 != 2) {
                    j(c7, "vent_fl", str2);
                    break;
                } else {
                    j(c7, "vent_fr", str2);
                    break;
                }
            case CABIN_ENGINE_STATE /* 2201 */:
                if (intValue != 0) {
                    if (intValue == 1) {
                        g0 g0Var6 = c7.f7949q;
                        if (g0Var6 instanceof g0) {
                            g0Var6.i("运行");
                            break;
                        }
                    }
                } else {
                    g0 g0Var7 = c7.f7949q;
                    if (g0Var7 instanceof g0) {
                        g0Var7.i("熄火");
                        break;
                    }
                }
                break;
        }
    }

    public static void h(o4.c cVar, String str, boolean z6) {
        Map map = (Map) cVar.f7956x.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, Boolean.valueOf(z6));
        g0 g0Var = cVar.f7956x;
        if (g0Var instanceof g0) {
            g0Var.i(map);
        }
    }

    public static void i(o4.c cVar, String str, String str2) {
        Map map = (Map) cVar.F.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, str2);
        g0 g0Var = cVar.F;
        if (g0Var instanceof g0) {
            g0Var.i(map);
        }
    }

    public static void j(o4.c cVar, String str, String str2) {
        Map map = (Map) cVar.G.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, str2);
        g0 g0Var = cVar.G;
        if (g0Var instanceof g0) {
            g0Var.i(map);
        }
    }

    public static void k(o4.c cVar, String str, String str2) {
        Map map = (Map) cVar.C.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, str2);
        g0 g0Var = cVar.C;
        if (g0Var instanceof g0) {
            g0Var.i(map);
        }
    }

    public final void d() {
        k kVar = this.f4721i;
        Handler handler = this.f4714b;
        if (this.f4713a == null && !this.f4717e) {
            Intent intent = new Intent("com.lion.os.service.ACTION.LTCarInfoService");
            intent.setPackage("com.lion.os.service");
            try {
                if (bindService(intent, this.f4724l, 1)) {
                    this.f4717e = true;
                } else {
                    handler.postDelayed(kVar, 10000L);
                }
            } catch (Exception unused) {
                handler.postDelayed(kVar, 10000L);
            }
        }
    }

    public final Bundle e(int i7, Bundle bundle) {
        if (this.f4713a == null) {
            return null;
        }
        SyncBusEvent syncBusEvent = new SyncBusEvent(i7);
        if (bundle == null) {
            bundle = new Bundle();
        }
        syncBusEvent.f4485c = bundle;
        try {
            SyncBusEvent h7 = ((a) this.f4713a).h(syncBusEvent);
            if (h7 != null) {
                return h7.f4485c;
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        f4712m = this;
        CarSoundManager carSoundManager = new CarSoundManager(this);
        this.f4718f = carSoundManager;
        carSoundManager.start();
        CarClimateManager carClimateManager = new CarClimateManager(this);
        this.f4719g = carClimateManager;
        carClimateManager.start();
        d();
        if (SharedPreferencesUtils.getAppPrefs(this).getBoolean("amap_speed_sync_enabled", false)) {
            b.b(this).d();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        f4712m = null;
        CarSoundManager carSoundManager = this.f4718f;
        if (carSoundManager != null) {
            carSoundManager.stop();
            this.f4718f = null;
        }
        CarClimateManager carClimateManager = this.f4719g;
        if (carClimateManager != null) {
            carClimateManager.stop();
            this.f4719g = null;
        }
        this.f4714b.removeCallbacks(this.f4721i);
        b.b(this).e();
        Handler handler = this.f4716d;
        if (handler != null) {
            handler.removeCallbacks(this.f4722j);
        }
        HandlerThread handlerThread = this.f4715c;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.f4715c = null;
            this.f4716d = null;
        }
        c cVar = this.f4713a;
        if (cVar != null) {
            try {
                ((a) cVar).k(Process.myPid());
                ((a) this.f4713a).f6402a.unlinkToDeath(this.f4720h, 0);
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            unbindService(this.f4724l);
            this.f4713a = null;
        }
        o4.c.c().n(false);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        NotificationChannel notificationChannel = new NotificationChannel("lion_sync_channel", "雄狮同步服务通道", 2);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        startForeground(10087, new Notification.Builder(this, "lion_sync_channel").setContentTitle("雄狮同步服务运行中").setContentText("正在连接车机总线...").setSmallIcon(o.ic_launcher_foreground).build());
        return 1;
    }
}
