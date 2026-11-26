package com.navi.tool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;
import com.navi.tool.ui.theme.ThemeViewModel;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.SharedPreferencesUtils;
import p4.a;
import p4.b;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class Auto3DBroadcastReceiver extends BroadcastReceiver {
    public static final String AMAP_AUTO_BROADCAST_RECEIVER = "com.autonavi.amapauto.adapter.internal.AmapAutoBroadcastReceiver";
    public static final String AUTONAVI_STANDARD_BROADCAST_RECV = "AUTONAVI_STANDARD_BROADCAST_RECV";
    private static final long DELAY_MILLIS = 3000;
    public static final String EXTRA_OPERA = "EXTRA_OPERA";
    public static final String EXTRA_STATE = "EXTRA_STATE";
    public static final int EXTRA_STATE_CRUISE_3D = 24;
    public static final int EXTRA_STATE_CRUISE_3D_END = 25;
    public static final int EXTRA_STATE_NAVIGATION_3D = 8;
    public static final int EXTRA_STATE_NAVIGATION_3D_END = 9;
    public static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final String KEY_TYPE = "KEY_TYPE";
    public static final int KEY_TYPE_VALUE = 10019;
    public static final int OPERA_3D = 2;
    public static final int OPERA_SCALE_DECREASE = 0;
    public static final int OPERA_SCALE_INCREASE = 1;
    public static final int SCALE_10M = 10;
    public static final int SCALE_25M = 25;
    public static final int SCALE_50M = 50;
    private static final String TAG = "Auto3DBroadcastReceiver";
    public static final int TYPE_3D = 2;
    public static final int TYPE_SCALE = 1;
    private Handler handler = new Handler(Looper.getMainLooper());
    private ThemeViewModel themeViewModel;

    public Auto3DBroadcastReceiver() {
    }

    public Auto3DBroadcastReceiver(ThemeViewModel themeViewModel) {
        this.themeViewModel = themeViewModel;
    }

    private boolean isAuto3DMainEnabled(Context context) {
        ThemeViewModel themeViewModel = this.themeViewModel;
        Boolean bool = themeViewModel != null ? (Boolean) themeViewModel.getAuto3DEnabled().d() : null;
        return bool != null ? bool.booleanValue() : SharedPreferencesUtils.isAuto3DEnabled(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onReceive$0(Context context) {
        if (isAuto3DMainEnabled(context)) {
            sendNavigation3DBroadcast(context);
            this.themeViewModel.setNavigation3DBroadcastSent(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onReceive$1(Context context) {
        if (isAuto3DMainEnabled(context)) {
            sendCruise3DBroadcast(context);
            this.themeViewModel.setCruise3DBroadcastSent(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendCruise3DBroadcast$3(Context context, int i7) {
        if (isAuto3DMainEnabled(context)) {
            sendCruiseScaleBroadcast(context, i7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendMultipleScaleBroadcasts$4(Context context, int i7, int i8) {
        if (isAuto3DMainEnabled(context)) {
            this.themeViewModel.sendScaleBroadcastToEnabledAutoNaviApps(i7, i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigation3DBroadcast$2(Context context, int i7) {
        if (isAuto3DMainEnabled(context)) {
            sendNavigationScaleBroadcast(context, i7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationScaleBroadcast$5(Context context) {
        if (isAuto3DMainEnabled(context)) {
            sendMultipleScaleBroadcasts(context, 1, 1, 1, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationScaleBroadcast$6(Context context) {
        if (isAuto3DMainEnabled(context)) {
            sendMultipleScaleBroadcasts(context, 1, 1, 2, 600);
        }
    }

    private void sendCruise3DBroadcast(Context context) {
        ThemeViewModel themeViewModel = this.themeViewModel;
        if (themeViewModel == null) {
            return;
        }
        themeViewModel.send3DBroadcastToEnabledAutoNaviApps(2, 2);
        Boolean bool = (Boolean) this.themeViewModel.getCruiseFixedScaleEnabled().d();
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        this.handler.postDelayed(new b(this, context, SharedPreferencesUtils.getCruiseScale(context), 1), 3000L);
    }

    private void sendCruiseScaleBroadcast(Context context, int i7) {
        if (i7 == 10) {
            sendMultipleScaleBroadcasts(context, 1, 0, 1, 0);
        } else {
            if (i7 != 50) {
                return;
            }
            sendMultipleScaleBroadcasts(context, 1, 1, 1, 0);
        }
    }

    private void sendMultipleScaleBroadcasts(final Context context, final int i7, final int i8, int i9, int i10) {
        for (int i11 = 0; i11 < i9; i11++) {
            this.handler.postDelayed(new Runnable() { // from class: p4.c
                @Override // java.lang.Runnable
                public final void run() {
                    Auto3DBroadcastReceiver.this.lambda$sendMultipleScaleBroadcasts$4(context, i7, i8);
                }
            }, i11 * i10);
        }
    }

    private void sendNavigation3DBroadcast(Context context) {
        ThemeViewModel themeViewModel = this.themeViewModel;
        if (themeViewModel == null) {
            return;
        }
        themeViewModel.send3DBroadcastToEnabledAutoNaviApps(2, 2);
        Boolean bool = (Boolean) this.themeViewModel.getNavigationFixedScaleEnabled().d();
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        this.handler.postDelayed(new b(this, context, SharedPreferencesUtils.getNavigationScale(context), 0), 3000L);
    }

    private void sendNavigationScaleBroadcast(Context context, int i7) {
        if (i7 == 10) {
            sendMultipleScaleBroadcasts(context, 1, 0, 5, 600);
            return;
        }
        if (i7 == 25) {
            sendMultipleScaleBroadcasts(context, 1, 0, 5, 600);
            this.handler.postDelayed(new a(this, context, 2), 3200L);
        } else {
            if (i7 != 50) {
                return;
            }
            sendMultipleScaleBroadcasts(context, 1, 0, 5, 600);
            this.handler.postDelayed(new a(this, context, 3), 3200L);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ThemeViewModel themeViewModel;
        ThemeViewModel themeViewModel2;
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if (AmapBroadcastConstants.getAmapBroadcast().equals(intent.getAction()) && intent.getIntExtra(KEY_TYPE, -1) == 10019 && isAuto3DMainEnabled(context)) {
            int intExtra = intent.getIntExtra(EXTRA_STATE, -1);
            int i7 = 0;
            if (intExtra == 8) {
                ThemeViewModel themeViewModel3 = this.themeViewModel;
                if (themeViewModel3 != null && !themeViewModel3.isNavigation3DBroadcastSent() && this.themeViewModel.getNavigation3DEnabled().d() != null && ((Boolean) this.themeViewModel.getNavigation3DEnabled().d()).booleanValue()) {
                    this.handler.postDelayed(new a(this, context, i7), 3000L);
                }
            } else if (intExtra == 9 && (themeViewModel = this.themeViewModel) != null) {
                themeViewModel.setNavigation3DBroadcastSent(false);
            }
            if (intExtra != 24) {
                if (intExtra != 25 || (themeViewModel2 = this.themeViewModel) == null) {
                    return;
                }
                themeViewModel2.setCruise3DBroadcastSent(false);
                return;
            }
            ThemeViewModel themeViewModel4 = this.themeViewModel;
            if (themeViewModel4 == null || themeViewModel4.isCruise3DBroadcastSent() || this.themeViewModel.getCruise3DEnabled().d() == null || !((Boolean) this.themeViewModel.getCruise3DEnabled().d()).booleanValue()) {
                return;
            }
            this.handler.postDelayed(new a(this, context, 1), 3000L);
        }
    }

    public void setThemeViewModel(ThemeViewModel themeViewModel) {
        this.themeViewModel = themeViewModel;
    }
}
