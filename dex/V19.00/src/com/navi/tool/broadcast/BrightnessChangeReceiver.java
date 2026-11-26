package com.navi.tool.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.navi.tool.MainActivity;
import com.navi.tool.NaviApplication;
import com.navi.tool.broadcast.BrightnessChangeReceiver;
import com.navi.tool.ui.theme.ThemeViewModel;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.BroadcastUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import t0.a;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class BrightnessChangeReceiver extends BroadcastReceiver {

    /* renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ int f4680f = 0;

    /* renamed from: a, reason: collision with root package name */
    public Context f4681a;

    /* renamed from: b, reason: collision with root package name */
    public a f4682b;

    /* renamed from: c, reason: collision with root package name */
    public Handler f4683c;

    /* renamed from: d, reason: collision with root package name */
    public int f4684d;

    /* renamed from: e, reason: collision with root package name */
    public ThemeViewModel f4685e;

    public static int b(Context context) {
        try {
            return Math.round((Settings.System.getInt(context.getContentResolver(), "screen_brightness") * 100.0f) / 255.0f);
        } catch (Settings.SettingNotFoundException unused) {
            return 50;
        }
    }

    public final void a(Context context) {
        int b7 = b(context);
        int brightnessThreshold = SharedPreferencesUtils.getBrightnessThreshold(context);
        ThemeViewModel themeViewModel = this.f4685e;
        if (themeViewModel != null) {
            themeViewModel.updateCurrentBrightness();
        }
        if (SharedPreferencesUtils.isAutoBrightnessEnabled(context)) {
            int i7 = this.f4684d;
            if (b7 != i7 || i7 == -1) {
                this.f4684d = b7;
                final boolean z6 = b7 < brightnessThreshold;
                for (String str : AppDetectionUtils.getInstalledNavigationPackages(context)) {
                    if (SharedPreferencesUtils.isBrightnessAppEnabled(context, str)) {
                        BroadcastUtils.sendThemeBroadcast(context, str, z6);
                        try {
                            context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(str, 0)).toString();
                        } catch (Exception unused) {
                        }
                    }
                }
                try {
                    final int b8 = b(context);
                    new Handler(Looper.getMainLooper()).post(new Runnable(b8, z6) { // from class: n4.a

                        /* renamed from: a, reason: collision with root package name */
                        public final /* synthetic */ int f7784a;

                        @Override // java.lang.Runnable
                        public final void run() {
                            int i8 = this.f7784a;
                            int i9 = BrightnessChangeReceiver.f4680f;
                            try {
                                if (MainActivity.getInstance() != null) {
                                    MainActivity.getInstance().updateThemeByBrightness(i8);
                                }
                            } catch (Exception unused2) {
                            }
                        }
                    });
                } catch (Exception unused2) {
                }
            }
        }
    }

    public final void c(Context context) {
        this.f4681a = context;
        if (this.f4683c == null) {
            this.f4683c = new Handler(Looper.getMainLooper());
        }
        if (this.f4685e == null) {
            this.f4685e = ((NaviApplication) context.getApplicationContext()).getThemeViewModel();
        }
        if (this.f4682b == null) {
            this.f4682b = new a(this);
            context.getContentResolver().registerContentObserver(Settings.System.getUriFor("screen_brightness"), false, this.f4682b);
        }
        a(context);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Context context2;
        if (intent == null || intent.getAction() == null) {
            return;
        }
        this.f4681a = context;
        this.f4683c = new Handler(Looper.getMainLooper());
        if (this.f4685e == null) {
            this.f4685e = ((NaviApplication) context.getApplicationContext()).getThemeViewModel();
        }
        if (intent.getAction().equals("android.intent.action.SCREEN_ON") || intent.getAction().equals("android.intent.action.USER_PRESENT")) {
            if (this.f4682b == null) {
                this.f4682b = new a(this);
                context.getContentResolver().registerContentObserver(Settings.System.getUriFor("screen_brightness"), false, this.f4682b);
            }
            a(context);
            return;
        }
        if (!intent.getAction().equals("android.intent.action.SCREEN_OFF") || this.f4682b == null || (context2 = this.f4681a) == null) {
            return;
        }
        context2.getContentResolver().unregisterContentObserver(this.f4682b);
        this.f4682b = null;
    }
}
