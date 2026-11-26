package com.navi.tool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import androidx.appcompat.app.t;
import com.google.android.material.datepicker.g;
import com.navi.tool.NaviApplication;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.carinfo.LionCarInfoService;
import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.service.AutoStartService;
import com.navi.tool.service.CarLanDashboardService;
import com.navi.tool.service.GestureAccessibilityService;
import com.navi.tool.service.GestureActionService;
import com.navi.tool.service.LionMediaBridgeService;
import com.navi.tool.service.LogLinkageService;
import com.navi.tool.service.LogMonitorService;
import com.navi.tool.service.MobileHudService;
import com.navi.tool.service.MusicIslandService;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.ui.cast.ScreenCastingFloatingService;
import com.navi.tool.utils.AppFreezeManager;
import com.navi.tool.utils.AppSuspendManager;
import com.navi.tool.utils.MegaImeHelper;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.NavigationStatusSimulatorService;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import o4.z;
import p4.d;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiver";

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$onReceive$0(Context context) {
        boolean z6 = FloatingWindowService.f4529p;
        FloatingWindowService.n(context, new Intent(context, (Class<?>) FloatingWindowService.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onReceive$1(Context context) {
        try {
            if (NativeBridge.verifySignature(context)) {
                SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(context);
                AppFreezeManager.reapplyFrozenPackages(context);
                AppSuspendManager.reapplySuspendedPackages(context);
                boolean z6 = appPrefs.getBoolean("voice_navigation_switch_state", false);
                boolean z7 = appPrefs.getBoolean("log_link_switch_state", false);
                if (context.checkSelfPermission("android.permission.READ_LOGS") == 0) {
                    if (z7) {
                        context.startForegroundService(new Intent(context, (Class<?>) LogLinkageService.class));
                    }
                    if (z6) {
                        context.startForegroundService(new Intent(context, (Class<?>) LogMonitorService.class));
                    }
                }
                NaviApplication.getInstance().startThemeMonitorService();
                if (SharedPreferencesUtils.isCustomAmapBroadcastEnabled(context)) {
                    context.startForegroundService(new Intent(context, (Class<?>) NavigationStatusSimulatorService.class));
                }
                if (SharedPreferencesUtils.isScreenCastingEnabled(context)) {
                    context.startForegroundService(new Intent(context, (Class<?>) ScreenCastingFloatingService.class));
                }
                boolean z8 = appPrefs.getBoolean("lyric_enabled", false);
                boolean z9 = appPrefs.getBoolean("lane_lines_enabled", false);
                boolean z10 = appPrefs.getBoolean("navigation_eta_enabled", false);
                if (z8 || z9 || z10) {
                    new Handler(Looper.getMainLooper()).post(new t(context, 4));
                }
                if (SharedPreferencesUtils.isAutoStartEnabled(context)) {
                    context.startForegroundService(new Intent(context, (Class<?>) AutoStartService.class));
                }
                boolean z11 = appPrefs.getBoolean("car_link_service_enabled", false);
                boolean z12 = appPrefs.getBoolean("lion_sync_service_enabled", false);
                if (z11) {
                    context.startForegroundService(new Intent(context, (Class<?>) CarInfoService.class));
                }
                if (z12) {
                    context.startForegroundService(new Intent(context, (Class<?>) LionCarInfoService.class));
                }
                if (appPrefs.getBoolean("lion_media_bridge_enabled", false)) {
                    Intent intent = new Intent(context, (Class<?>) LionMediaBridgeService.class);
                    intent.putExtra("boot_start", true);
                    context.startForegroundService(intent);
                }
                if (appPrefs.getBoolean("carlan_dashboard_enabled", false)) {
                    Intent intent2 = new Intent(context, (Class<?>) CarLanDashboardService.class);
                    intent2.putExtra("boot_start", true);
                    context.startForegroundService(intent2);
                }
                boolean z13 = appPrefs.getBoolean("desktop_music_enabled", false);
                boolean z14 = appPrefs.getBoolean("desktop_music_island_enabled", false);
                if (z13 && z14) {
                    MusicIslandService.startOrRefresh(context);
                }
                MobileHudService.syncService(context);
                PhoneNotificationRelayService.syncService(context);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$scheduleGestureRetries$2(Runnable runnable) {
        return new Thread(runnable, "gesture-boot-retry");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$scheduleGestureRetries$3(Context context, long j7) {
        tryStartGesture(context, j7 + "s");
    }

    private static void scheduleGestureRetries(Context context) {
        int i7 = 0;
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new d(i7));
        long[] jArr = {5, 10, 15};
        while (i7 < 3) {
            long j7 = jArr[i7];
            newSingleThreadScheduledExecutor.schedule(new g(context, j7, 1), j7, TimeUnit.SECONDS);
            i7++;
        }
        newSingleThreadScheduledExecutor.shutdown();
    }

    private void scheduleMegaImeRetries(Context context) {
        MegaImeHelper.scheduleRestore(context);
        AtomicBoolean atomicBoolean = TirePollingService.f4725g;
        if (context == null || !z.e(context)) {
            return;
        }
        TirePollingService.d(context);
    }

    private static void tryStartGesture(Context context, String str) {
        try {
            if (GestureActionService.isServiceRunning()) {
                return;
            }
            boolean z6 = SharedPreferencesUtils.getAppPrefs(context).getBoolean("gesture_actions_enabled", false);
            boolean canDrawOverlays = Settings.canDrawOverlays(context);
            GestureAccessibilityService.isEnabled(context);
            if (z6 && canDrawOverlays) {
                GestureActionService.start(context);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent != null ? intent.getAction() : null;
        if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.intent.action.USER_UNLOCKED".equals(action)) {
            Context applicationContext = context.getApplicationContext();
            scheduleGestureRetries(applicationContext);
            scheduleMegaImeRetries(applicationContext);
            new Thread(new t(applicationContext, 5)).start();
        }
    }
}
