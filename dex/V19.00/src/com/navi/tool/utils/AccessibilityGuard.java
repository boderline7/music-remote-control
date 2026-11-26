package com.navi.tool.utils;

import android.content.ComponentName;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import com.navi.tool.service.GestureAccessibilityService;
import com.navi.tool.ui.extra.PermissionManagerActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class AccessibilityGuard {
    private static final String PERM_WRITE_SECURE_SETTINGS = "android.permission.WRITE_SECURE_SETTINGS";
    private static final long REBIND_COOLDOWN_MS = 60000;
    private static final int REBIND_MAX_FAILURES = 3;
    private static final long REBIND_RESTORE_DELAY_MS = 300;
    private static final long REBIND_VERIFY_DELAY_MS = 3000;
    private static final long REPAIR_DEBOUNCE_MS = 1500;
    private static final String SETTING_ENABLED_SERVICES = "enabled_accessibility_services";
    private static final String TAG = "AccessibilityGuard";
    private static Context sAppContext;
    private static long sLastRebindMs;
    private static long sLastRepairMs;
    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());
    private static ContentObserver sObserver;
    private static int sRebindFailures;
    private static volatile boolean sRebinding;

    private AccessibilityGuard() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$rebindIfNeeded$0() {
        if (GestureAccessibilityService.isConnected()) {
            sRebindFailures = 0;
        } else {
            sRebindFailures++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$rebindIfNeeded$1(String str, String str2, Context context) {
        try {
            if (!str.isEmpty()) {
                str2 = str + ":" + str2;
            }
            Settings.Secure.putString(context.getContentResolver(), SETTING_ENABLED_SERVICES, str2);
        } catch (Throwable unused) {
        }
        sRebinding = false;
        sMainHandler.postDelayed(new g4.k(15), 3000L);
    }

    public static boolean rebindIfNeeded() {
        String string;
        Context context = sAppContext;
        if (context == null || !SharedPreferencesUtils.isStrWakeAutoStartEnabled(context) || GestureAccessibilityService.isConnected() || !GestureAccessibilityService.isEnabled(context)) {
            return false;
        }
        int i7 = 3;
        if (sRebindFailures >= 3 || sRebinding) {
            return false;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - sLastRebindMs >= 60000 && context.checkSelfPermission(PERM_WRITE_SECURE_SETTINGS) == 0 && (string = Settings.Secure.getString(context.getContentResolver(), SETTING_ENABLED_SERVICES)) != null && !string.isEmpty()) {
            String packageName = context.getPackageName();
            StringBuilder sb = new StringBuilder();
            boolean z6 = false;
            for (String str : string.split(":")) {
                if (!str.isEmpty()) {
                    if ((str.contains("/") ? str.substring(0, str.indexOf(47)) : str).equals(packageName)) {
                        z6 = true;
                    } else {
                        if (sb.length() > 0) {
                            sb.append(':');
                        }
                        sb.append(str);
                    }
                }
            }
            if (!z6) {
                return false;
            }
            sRebinding = true;
            sLastRebindMs = uptimeMillis;
            String sb2 = sb.toString();
            String flattenToString = new ComponentName(packageName, GestureAccessibilityService.class.getName()).flattenToString();
            try {
                Settings.Secure.putString(context.getContentResolver(), SETTING_ENABLED_SERVICES, sb2);
                sMainHandler.postDelayed(new q(sb2, flattenToString, context, i7), REBIND_RESTORE_DELAY_MS);
                return true;
            } catch (Throwable unused) {
                sRebinding = false;
            }
        }
        return false;
    }

    public static void repairIfNeeded(Context context) {
        if (context == null || sRebinding || GestureAccessibilityService.isEnabled(context)) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - sLastRepairMs < REPAIR_DEBOUNCE_MS) {
            return;
        }
        sLastRepairMs = uptimeMillis;
        PermissionManagerActivity.grantAccessibility(context, context.getPackageName());
    }

    public static void start(Context context) {
        if (context == null || sObserver != null) {
            return;
        }
        final Context applicationContext = context.getApplicationContext();
        sAppContext = applicationContext;
        sObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.navi.tool.utils.AccessibilityGuard.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z6) {
                AccessibilityGuard.repairIfNeeded(applicationContext);
            }
        };
        try {
            applicationContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(SETTING_ENABLED_SERVICES), false, sObserver);
        } catch (Throwable unused) {
            sObserver = null;
        }
    }
}
