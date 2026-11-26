package com.navi.tool.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class MegaImeHelper {
    private static final long BOOT_PROTECT_MS = 90000;
    private static final long[] IME_RESTORE_DELAYS_MS;
    private static final String KEY_MEGA_IME_ID = "mega_ime_id";
    private static final String TAG = "MegaImeHelper";
    private static ContentObserver sObserver;
    private static final long sProcessStartAt = SystemClock.elapsedRealtime();
    private static final Handler sRestoreHandler;
    private static final Runnable[] sRestoreRunnables;
    private static volatile long sSelfWriteAt;

    static {
        long[] jArr = {30000, t1.f.T_MS_MINUTE, BOOT_PROTECT_MS, 120000};
        IME_RESTORE_DELAYS_MS = jArr;
        sRestoreHandler = new Handler(Looper.getMainLooper());
        sRestoreRunnables = new Runnable[jArr.length];
        sSelfWriteAt = 0L;
    }

    public static boolean apply(Context context, String str) {
        if (str == null || str.isEmpty() || !hasWriteSecureSettings(context)) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            ContentResolver contentResolver = applicationContext.getContentResolver();
            String string = Settings.Secure.getString(contentResolver, "enabled_input_methods");
            if (string != null && !string.isEmpty()) {
                if (!string.contains(str)) {
                    string = string + ":" + str;
                }
                sSelfWriteAt = System.currentTimeMillis();
                Settings.Secure.putString(contentResolver, "enabled_input_methods", string);
                Settings.Secure.putString(contentResolver, "default_input_method", str);
                saveImeId(applicationContext, str);
                return true;
            }
            string = str;
            sSelfWriteAt = System.currentTimeMillis();
            Settings.Secure.putString(contentResolver, "enabled_input_methods", string);
            Settings.Secure.putString(contentResolver, "default_input_method", str);
            saveImeId(applicationContext, str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void cancelRestore() {
        int i7 = 0;
        while (true) {
            Runnable[] runnableArr = sRestoreRunnables;
            if (i7 >= runnableArr.length) {
                return;
            }
            Runnable runnable = runnableArr[i7];
            if (runnable != null) {
                sRestoreHandler.removeCallbacks(runnable);
                runnableArr[i7] = null;
            }
            i7++;
        }
    }

    public static void clearSavedImeId(Context context) {
        try {
            SharedPreferencesUtils.getAppPrefs(context).edit().remove(KEY_MEGA_IME_ID).apply();
        } catch (Throwable unused) {
        }
    }

    public static String getSavedImeId(Context context) {
        try {
            return SharedPreferencesUtils.getAppPrefs(context).getString(KEY_MEGA_IME_ID, h0.FRAGMENT_ENCODE_SET);
        } catch (Throwable unused) {
            return h0.FRAGMENT_ENCODE_SET;
        }
    }

    public static boolean hasWriteSecureSettings(Context context) {
        return context.checkSelfPermission("android.permission.WRITE_SECURE_SETTINGS") == 0;
    }

    private static boolean isMegaModeEnabled(Context context) {
        try {
            return SharedPreferencesUtils.getAppPrefs(context).getBoolean("mega_permission_mode", false);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$scheduleRestore$0(Context context, String str) {
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "default_input_method");
            if (string == null || !string.equals(str)) {
                apply(context, str);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onDefaultImeChanged(Context context) {
        String string;
        try {
            String savedImeId = getSavedImeId(context);
            if (savedImeId != null && !savedImeId.isEmpty() && (string = Settings.Secure.getString(context.getContentResolver(), "default_input_method")) != null && !string.isEmpty() && !string.equals(savedImeId)) {
                System.currentTimeMillis();
            }
        } catch (Throwable unused) {
        }
    }

    public static synchronized void registerObserver(Context context) {
        synchronized (MegaImeHelper.class) {
            if (sObserver != null) {
                return;
            }
            final Context applicationContext = context.getApplicationContext();
            sObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.navi.tool.utils.MegaImeHelper.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z6) {
                    MegaImeHelper.onDefaultImeChanged(applicationContext);
                }
            };
            try {
                applicationContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("default_input_method"), false, sObserver);
            } catch (Throwable unused) {
                sObserver = null;
            }
        }
    }

    public static void saveImeId(Context context, String str) {
        try {
            SharedPreferences.Editor edit = SharedPreferencesUtils.getAppPrefs(context).edit();
            if (str == null) {
                str = h0.FRAGMENT_ENCODE_SET;
            }
            edit.putString(KEY_MEGA_IME_ID, str).apply();
        } catch (Throwable unused) {
        }
    }

    public static void scheduleRestore(Context context) {
        String savedImeId;
        Context applicationContext = context.getApplicationContext();
        if (!isMegaModeEnabled(applicationContext) || (savedImeId = getSavedImeId(applicationContext)) == null || savedImeId.isEmpty()) {
            return;
        }
        cancelRestore();
        int i7 = 0;
        while (true) {
            long[] jArr = IME_RESTORE_DELAYS_MS;
            if (i7 >= jArr.length) {
                return;
            }
            n nVar = new n(applicationContext, 2, savedImeId);
            sRestoreRunnables[i7] = nVar;
            sRestoreHandler.postDelayed(nVar, jArr[i7]);
            i7++;
        }
    }

    public static synchronized void unregisterObserver(Context context) {
        synchronized (MegaImeHelper.class) {
            if (sObserver == null) {
                return;
            }
            try {
                context.getApplicationContext().getContentResolver().unregisterContentObserver(sObserver);
            } catch (Throwable unused) {
            }
            sObserver = null;
        }
    }
}
