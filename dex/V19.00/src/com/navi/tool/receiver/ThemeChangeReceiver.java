package com.navi.tool.receiver;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.receiver.ThemeChangeReceiver;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.BroadcastUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p4.d;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ThemeChangeReceiver extends BroadcastReceiver {
    private static final ExecutorService BROADCAST_EXECUTOR = Executors.newSingleThreadExecutor(new d(1));
    private static final String TAG = "ThemeChangeReceiver";
    private static OnThemeChangeListener sListener;

    public interface OnThemeChangeListener {
        void onThemeChanged(boolean z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$static$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "theme-broadcast");
        thread.setDaemon(true);
        return thread;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendThemeBroadcastToAllEnabledApps, reason: merged with bridge method [inline-methods] */
    public void lambda$checkThemeChange$1(Context context, boolean z6) {
        for (String str : AppDetectionUtils.getInstalledNavigationPackages(context)) {
            if (SharedPreferencesUtils.isThemeAppEnabled(context, str)) {
                BroadcastUtils.sendThemeBroadcast(context, str, z6);
            }
        }
    }

    private void sendThemeBroadcastToEnabledAutoNaviApps(Context context, boolean z6) {
        for (String str : AppDetectionUtils.getAutoNaviPackages(context)) {
            if (SharedPreferencesUtils.isThemeAppEnabled(context, str)) {
                BroadcastUtils.sendThemeBroadcast(context, str, z6);
            }
        }
    }

    public static void setOnThemeChangeListener(OnThemeChangeListener onThemeChangeListener) {
        sListener = onThemeChangeListener;
    }

    public void checkThemeChange(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        final boolean z6 = false;
        if (uiModeManager == null ? (context.getResources().getConfiguration().uiMode & 48) == 32 : uiModeManager.getNightMode() == 2) {
            z6 = true;
        }
        if (SharedPreferencesUtils.isAutoThemeEnabled(context)) {
            final Context applicationContext = context.getApplicationContext();
            BROADCAST_EXECUTOR.execute(new Runnable() { // from class: p4.e
                @Override // java.lang.Runnable
                public final void run() {
                    ThemeChangeReceiver.this.lambda$checkThemeChange$1(applicationContext, z6);
                }
            });
        }
        OnThemeChangeListener onThemeChangeListener = sListener;
        if (onThemeChangeListener != null) {
            onThemeChangeListener.onThemeChanged(z6);
        }
        try {
            boolean z7 = FloatingWindowService.f4529p;
            Intent intent = new Intent(context, (Class<?>) FloatingWindowService.class);
            intent.setAction("refresh_lane_lines_style");
            FloatingWindowService.n(context, intent);
        } catch (Exception unused) {
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("android.intent.action.CONFIGURATION_CHANGED".equals(action) || "android.app.action.NIGHT_MODE_CHANGED".equals(action) || "android.intent.action.USER_PRESENT".equals(action) || "android.intent.action.SCREEN_ON".equals(action) || "android.intent.action.ACTION_POWER_CONNECTED".equals(action) || "android.intent.action.ACTION_POWER_DISCONNECTED".equals(action) || "android.intent.action.UI_MODE_CHANGED".equals(action)) {
            checkThemeChange(context);
        }
    }
}
