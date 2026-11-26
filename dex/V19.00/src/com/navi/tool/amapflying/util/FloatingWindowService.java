package com.navi.tool.amapflying.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.p0;
import com.navi.tool.amapflying.receiver.LunaMusicLyricReceiver;
import com.navi.tool.amapflying.receiver.QQMusicLyricReceiver;
import com.navi.tool.amapflying.util.kugou.KugouMusicManager;
import com.navi.tool.amapflying.util.kuwo.KuwoMusicManager;
import com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager;
import com.navi.tool.amapflying.util.netease.NeteaseMusicManager;
import com.navi.tool.amapflying.util.qq.QQMusicManager;
import com.navi.tool.utils.SharedPreferencesUtils;
import e0.v;
import g4.o;
import j4.h;
import k4.a;
import l4.e;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class FloatingWindowService extends Service {
    public static final String KEY_MUSIC_SOURCE = "music_source";
    public static final int MUSIC_SOURCE_KUGOU = 2;
    public static final int MUSIC_SOURCE_KUWO = 1;
    public static final int MUSIC_SOURCE_LUNA = 5;
    public static final int MUSIC_SOURCE_NETEASE = 3;
    public static final int MUSIC_SOURCE_NEW_KUWO = 4;
    public static final int MUSIC_SOURCE_QQ = 0;
    public static final int MUSIC_SOURCE_UNIVERSAL = 6;

    /* renamed from: p, reason: collision with root package name */
    public static volatile boolean f4529p;

    /* renamed from: q, reason: collision with root package name */
    public static final Handler f4530q = new Handler(Looper.getMainLooper());

    /* renamed from: r, reason: collision with root package name */
    public static boolean f4531r = false;

    /* renamed from: a, reason: collision with root package name */
    public DisplayManager f4532a;

    /* renamed from: b, reason: collision with root package name */
    public LaneLinesFloatingView f4533b;

    /* renamed from: c, reason: collision with root package name */
    public NavigationETAFloatingView f4534c;

    /* renamed from: d, reason: collision with root package name */
    public RoadSpeedFloatingView f4535d;

    /* renamed from: e, reason: collision with root package name */
    public TmcBarFloatingView f4536e;

    /* renamed from: f, reason: collision with root package name */
    public LyricFloatingView f4537f;

    /* renamed from: g, reason: collision with root package name */
    public TireFloatingView f4538g;

    /* renamed from: h, reason: collision with root package name */
    public WindowManager.LayoutParams f4539h;

    /* renamed from: i, reason: collision with root package name */
    public LinearLayout f4540i;

    /* renamed from: j, reason: collision with root package name */
    public Display f4541j;

    /* renamed from: k, reason: collision with root package name */
    public Context f4542k;

    /* renamed from: l, reason: collision with root package name */
    public WindowManager.LayoutParams f4543l;

    /* renamed from: m, reason: collision with root package name */
    public WindowManager.LayoutParams f4544m;

    /* renamed from: n, reason: collision with root package name */
    public int f4545n = -1;

    /* renamed from: o, reason: collision with root package name */
    public Handler f4546o;

    public static void n(Context context, Intent intent) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (f4529p) {
                try {
                    applicationContext.startService(intent);
                    return;
                } catch (Throwable unused) {
                }
            }
            f4530q.post(new p0(8, applicationContext, intent));
        }
    }

    public final void a(LinearLayout linearLayout, int i7, int i8) {
        try {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 24, -3);
            layoutParams.gravity = 51;
            layoutParams.x = i7;
            layoutParams.y = i8;
            if (linearLayout instanceof NavigationETAFloatingView) {
                this.f4543l = layoutParams;
            }
            ((WindowManager) this.f4542k.getSystemService("window")).addView(linearLayout, layoutParams);
        } catch (Exception unused) {
        }
    }

    public final void b() {
        if (this.f4542k == null) {
            return;
        }
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        boolean z6 = appPrefs.getBoolean("lane_lines_enabled", false);
        boolean z7 = appPrefs.getBoolean("navigation_eta_enabled", false);
        boolean z8 = appPrefs.getBoolean("lyric_enabled", false);
        if (z6 && this.f4533b == null) {
            c(appPrefs.getInt("lane_lines_x", 0), appPrefs.getInt("lane_lines_y", 0));
        }
        if (z7 && this.f4534c == null) {
            e(appPrefs.getInt("navigation_eta_x", 0), appPrefs.getInt("navigation_eta_y", 200), appPrefs.getBoolean("navigation_eta_top_info_enabled", false));
        }
        if (appPrefs.getBoolean("road_speed_info_enabled", false) && this.f4535d == null) {
            g();
        }
        if (appPrefs.getBoolean("tmc_bar_enabled", false) && this.f4536e == null) {
            i();
        }
        if (z8 && this.f4537f == null) {
            d();
        }
        if (appPrefs.getBoolean("dashboard_tire_card_enabled", false) && this.f4538g == null) {
            h();
        }
    }

    public final void c(int i7, int i8) {
        if (this.f4542k == null) {
            return;
        }
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        appPrefs.edit().putInt("lane_lines_x", i7).putInt("lane_lines_y", i8).apply();
        LaneLinesFloatingView laneLinesFloatingView = this.f4533b;
        if (laneLinesFloatingView != null) {
            m(laneLinesFloatingView);
        }
        this.f4533b = new LaneLinesFloatingView(this.f4542k);
        try {
            this.f4533b.j(appPrefs.getInt("lane_lines_height", 80));
        } catch (Exception unused) {
        }
        try {
            this.f4533b.c();
        } catch (Exception unused2) {
        }
        a(this.f4533b, i7, i8);
    }

    public final void d() {
        WindowManager.LayoutParams layoutParams;
        Context context = this.f4542k;
        if (context == null) {
            return;
        }
        try {
            if (this.f4537f != null) {
                return;
            }
            this.f4537f = new LyricFloatingView(this.f4542k, (WindowManager) context.getSystemService("window"));
            SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
            int i7 = appPrefs.getInt("lyric_position_x", 0);
            int i8 = appPrefs.getInt("lyric_position_y", 0);
            int i9 = appPrefs.getInt("lyric_font_size", 30);
            boolean z6 = appPrefs.getBoolean("lyric_double_line", false);
            LyricFloatingView lyricFloatingView = this.f4537f;
            lyricFloatingView.f4561f = i7;
            lyricFloatingView.f4562g = i8;
            if (lyricFloatingView.f4559d && (layoutParams = lyricFloatingView.f4558c) != null) {
                layoutParams.x = i7;
                layoutParams.y = i8;
                lyricFloatingView.f4557b.updateViewLayout(lyricFloatingView, layoutParams);
            }
            LyricFloatingView lyricFloatingView2 = this.f4537f;
            lyricFloatingView2.f4560e = i9;
            TextView textView = lyricFloatingView2.f4556a;
            if (textView != null) {
                textView.setTextSize(2, i9);
            }
            QQMusicLyricReceiver.setDoubleLine(z6);
            KuwoMusicManager.setDoubleLine(z6);
            KugouMusicManager.setDoubleLine(z6);
            NeteaseMusicManager.setDoubleLine(z6);
            NewKuwoMusicManager.setDoubleLine(z6);
            LunaMusicLyricReceiver.f4524u = z6;
            e.f7428x = z6;
            this.f4537f.a();
            int i10 = appPrefs.getInt(KEY_MUSIC_SOURCE, 0);
            this.f4545n = -1;
            o(i10);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x008c, code lost:
    
        if (r11 > 40) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e(int r26, int r27, boolean r28) {
        /*
            Method dump skipped, instructions count: 1866
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.amapflying.util.FloatingWindowService.e(int, int, boolean):void");
    }

    public final Notification f() {
        v vVar = new v(this, "FloatingWindowService");
        vVar.f6327e = v.c("导航悬浮窗");
        vVar.f6328f = v.c("正在扩展屏上显示导航信息");
        vVar.f6341s.icon = o.ic_launcher_foreground;
        vVar.f6331i = -1;
        return vVar.a();
    }

    public final void g() {
        Context context = this.f4542k;
        if (context == null) {
            return;
        }
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            RoadSpeedFloatingView roadSpeedFloatingView = this.f4535d;
            if (roadSpeedFloatingView != null) {
                m(roadSpeedFloatingView);
            }
            RoadSpeedFloatingView roadSpeedFloatingView2 = new RoadSpeedFloatingView(this.f4542k);
            this.f4535d = roadSpeedFloatingView2;
            if (!roadSpeedFloatingView2.f4595f) {
                try {
                    roadSpeedFloatingView2.f4595f = true;
                    roadSpeedFloatingView2.setVisibility(8);
                    if (!i6.e.b().e(roadSpeedFloatingView2)) {
                        i6.e.b().j(roadSpeedFloatingView2);
                    }
                } catch (Exception unused) {
                }
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 24, -3);
            layoutParams.gravity = 51;
            layoutParams.x = 0;
            layoutParams.y = 0;
            windowManager.addView(this.f4535d, layoutParams);
            this.f4535d.getViewTreeObserver().addOnGlobalLayoutListener(new h(this, windowManager));
        } catch (Exception unused2) {
        }
    }

    public final void h() {
        Context context = this.f4542k;
        if (context == null) {
            return;
        }
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            TireFloatingView tireFloatingView = this.f4538g;
            if (tireFloatingView != null) {
                m(tireFloatingView);
            }
            this.f4538g = new TireFloatingView(this.f4542k);
            SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
            int i7 = appPrefs.getInt("dashboard_tire_card_x", 0);
            int i8 = appPrefs.getInt("dashboard_tire_card_y", 0);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 24, -3);
            this.f4539h = layoutParams;
            layoutParams.gravity = 51;
            layoutParams.x = i7;
            layoutParams.y = i8;
            windowManager.addView(this.f4538g, layoutParams);
        } catch (Exception unused) {
        }
    }

    public final void i() {
        if (this.f4542k == null) {
            return;
        }
        try {
            TmcBarFloatingView tmcBarFloatingView = this.f4536e;
            if (tmcBarFloatingView != null) {
                m(tmcBarFloatingView);
            }
            WindowManager windowManager = (WindowManager) this.f4542k.getSystemService("window");
            this.f4536e = new TmcBarFloatingView(this.f4542k, windowManager);
            this.f4536e.setPositionMode(SharedPreferencesUtils.getAppPrefs(this).getInt("tmc_bar_position", 0));
            TmcBarFloatingView tmcBarFloatingView2 = this.f4536e;
            if (!tmcBarFloatingView2.f4618f) {
                try {
                    if (!i6.e.b().e(tmcBarFloatingView2)) {
                        i6.e.b().j(tmcBarFloatingView2);
                    }
                    tmcBarFloatingView2.setVisibility(8);
                    tmcBarFloatingView2.f4618f = true;
                } catch (Exception unused) {
                }
            }
            windowManager.addView(this.f4536e, this.f4536e.getWindowParams());
        } catch (Exception unused2) {
        }
    }

    public final void j() {
        LyricFloatingView lyricFloatingView = this.f4537f;
        if (lyricFloatingView != null) {
            if (lyricFloatingView.f4559d) {
                try {
                    i6.e.b().l(lyricFloatingView);
                    lyricFloatingView.f4557b.removeView(lyricFloatingView);
                    lyricFloatingView.f4559d = false;
                } catch (Exception unused) {
                }
            }
            this.f4537f = null;
        }
        p();
        this.f4545n = -1;
    }

    public final void k() {
        try {
            LaneLinesFloatingView laneLinesFloatingView = this.f4533b;
            if (laneLinesFloatingView != null) {
                m(laneLinesFloatingView);
                this.f4533b = null;
            }
            NavigationETAFloatingView navigationETAFloatingView = this.f4534c;
            if (navigationETAFloatingView != null) {
                m(navigationETAFloatingView);
                this.f4534c = null;
            }
            RoadSpeedFloatingView roadSpeedFloatingView = this.f4535d;
            if (roadSpeedFloatingView != null) {
                m(roadSpeedFloatingView);
                this.f4535d = null;
            }
            TmcBarFloatingView tmcBarFloatingView = this.f4536e;
            if (tmcBarFloatingView != null) {
                if (tmcBarFloatingView.f4618f) {
                    if (i6.e.b().e(tmcBarFloatingView)) {
                        i6.e.b().l(tmcBarFloatingView);
                    }
                    tmcBarFloatingView.f4618f = false;
                }
                m(this.f4536e);
                this.f4536e = null;
            }
            j();
            TireFloatingView tireFloatingView = this.f4538g;
            if (tireFloatingView != null) {
                tireFloatingView.a();
                m(this.f4538g);
                this.f4538g = null;
                this.f4539h = null;
            }
        } catch (Exception unused) {
        }
    }

    public final void l() {
        try {
            if (this.f4540i != null) {
                WindowManager windowManager = (WindowManager) this.f4542k.getSystemService("window");
                if (windowManager != null) {
                    windowManager.removeView(this.f4540i);
                }
                this.f4540i = null;
                this.f4544m = null;
            }
        } catch (Exception unused) {
        }
    }

    public final void m(ViewGroup viewGroup) {
        try {
            WindowManager windowManager = (WindowManager) this.f4542k.getSystemService("window");
            if (viewGroup == null || windowManager == null) {
                return;
            }
            windowManager.removeView(viewGroup);
            if (viewGroup instanceof NavigationETAFloatingView) {
                this.f4543l = null;
            }
        } catch (Exception unused) {
        }
    }

    public final void o(int i7) {
        if (i7 == this.f4545n) {
            return;
        }
        p();
        if (i7 == 0) {
            QQMusicManager.getInstance(this).start();
        } else if (i7 == 1) {
            KuwoMusicManager.getInstance(this).start();
        } else if (i7 == 2) {
            KugouMusicManager.getInstance(this).start();
        } else if (i7 == 3) {
            NeteaseMusicManager.getInstance(this).start();
        } else if (i7 == 4) {
            NewKuwoMusicManager.getInstance(this).start();
        } else if (i7 == 5) {
            a.c(this).g();
        } else if (i7 == 6) {
            e.b(this).d();
        }
        this.f4545n = i7;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        NotificationChannel notificationChannel = new NotificationChannel("FloatingWindowService", "悬浮窗口服务", 2);
        notificationChannel.setDescription("用于在扩展屏上显示导航信息");
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        startForeground(1004, f());
        f4529p = true;
        DisplayManager displayManager = (DisplayManager) getSystemService("display");
        this.f4532a = displayManager;
        Display[] displays = displayManager.getDisplays();
        int length = displays.length;
        int length2 = displays.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length2) {
                break;
            }
            Display display = displays[i7];
            if (display.getDisplayId() != 0) {
                this.f4541j = display;
                display.getDisplayId();
                display.getName();
                break;
            }
            i7++;
        }
        if (this.f4541j == null) {
            this.f4541j = this.f4532a.getDisplay(0);
        }
        this.f4542k = createDisplayContext(this.f4541j);
        this.f4546o = new Handler(Looper.getMainLooper());
    }

    @Override // android.app.Service
    public void onDestroy() {
        f4529p = false;
        super.onDestroy();
        k();
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x02bf, code lost:
    
        if (r0 != false) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0483, code lost:
    
        if (r1 != false) goto L202;
     */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int onStartCommand(android.content.Intent r13, int r14, int r15) {
        /*
            Method dump skipped, instructions count: 1339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.amapflying.util.FloatingWindowService.onStartCommand(android.content.Intent, int, int):int");
    }

    public final void p() {
        QQMusicManager.getInstance(this).stop();
        KuwoMusicManager.getInstance(this).stop();
        KugouMusicManager.getInstance(this).stop();
        NeteaseMusicManager.getInstance(this).stop();
        NewKuwoMusicManager.getInstance(this).stop();
        a.c(this).h();
        e.b(this).e();
    }
}
