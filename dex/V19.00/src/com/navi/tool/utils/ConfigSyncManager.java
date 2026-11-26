package com.navi.tool.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.navi.tool.MainActivity;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.service.LogMonitorService;
import com.navi.tool.service.ThemeMonitorService;
import com.navi.tool.ui.cast.ScreenCastingFloatingService;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.json.JSONObject;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ConfigSyncManager {
    private static final String KEY_DIRTY = "config_dirty";
    private static final String KEY_FORCE_UPLOAD = "config_force_upload";
    private static final String KEY_LAST_UPLOAD_TS = "config_last_upload_ts";
    private static final String KEY_LICENSE_SEMANTIC_HASH = "license_semantic_hash";
    private static final String PREF_NAME = "navi_tool_prefs";
    private static final String TAG = "ConfigSync";
    private static final long UPLOAD_DEBOUNCE_MS = 5000;
    private static final long UPLOAD_INTERVAL_MS = 60000;
    private static final Object UPLOAD_LOCK = new Object();
    private static volatile ConfigSyncManager instance;
    private volatile boolean applying;
    private final Context context;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private final SharedPreferences prefs;
    private int refCount;
    private Runnable uploadRunnable;

    private ConfigSyncManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREF_NAME, 0);
    }

    private void applyPreferencesFromXml(byte[] bArr) {
        NativeBridge.applyPreferencesFromXml(this.context, bArr, PREF_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPostDownloadPermissions() {
        int computePermissionState = NativeBridge.computePermissionState(this.context);
        if (computePermissionState == 3) {
            androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this.context);
            nVar.t("权限提示");
            nVar.m("云端配置文件已同步到本地，需同时赋予READ_LOGS权限与悬浮窗权限，否则APP会闪退。");
            nVar.r("确定", new e(0, this));
            nVar.k();
            nVar.v();
            return;
        }
        int i7 = 1;
        if (computePermissionState == 2) {
            androidx.appcompat.app.n nVar2 = new androidx.appcompat.app.n(this.context);
            nVar2.t("权限提示");
            nVar2.m("云端配置文件已恢复并应用，请赋予悬浮窗权限，否则APP闪退。");
            nVar2.r("确定", new e(i7, this));
            nVar2.k();
            nVar2.v();
            return;
        }
        if (computePermissionState != 1) {
            restartApp();
            return;
        }
        androidx.appcompat.app.n nVar3 = new androidx.appcompat.app.n(this.context);
        nVar3.t("权限提示");
        nVar3.m("云端配置文件已同步到本地，请赋予READ_LOGS权限，否则APP闪退");
        nVar3.r("确定", null);
        nVar3.k();
        nVar3.v();
    }

    private long computeDelay() {
        long j7 = this.prefs.getLong(KEY_LAST_UPLOAD_TS, 0L);
        if (j7 <= 0) {
            return UPLOAD_DEBOUNCE_MS;
        }
        long currentTimeMillis = System.currentTimeMillis() - j7;
        return (currentTimeMillis >= 0 && currentTimeMillis < 60000) ? 65000 - currentTimeMillis : UPLOAD_DEBOUNCE_MS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadAndApply, reason: merged with bridge method [inline-methods] */
    public void lambda$maybeDownloadAndApply$1(String str) {
        Context context = this.context;
        if (str == null) {
            str = "unknown";
        }
        byte[] downloadPrefsData = NativeBridge.downloadPrefsData(context, str, PREF_NAME);
        int i7 = 0;
        int i8 = 1;
        if (downloadPrefsData == null || downloadPrefsData.length < 10) {
            if (SharedPreferencesUtils.isInitialRestartDone(this.context)) {
                return;
            }
            SharedPreferencesUtils.setInitialRestartDone(this.context, true);
            this.handler.postDelayed(new g(this, i7), UPLOAD_DEBOUNCE_MS);
            return;
        }
        NativeBridge.savePrefsXml(this.context, PREF_NAME, downloadPrefsData);
        this.applying = true;
        try {
            applyPreferencesFromXml(downloadPrefsData);
            SharedPreferencesUtils.setConfigSyncDone(this.context, true);
            this.applying = false;
            Context context2 = this.context;
            if (context2 instanceof Activity) {
                ((Activity) context2).runOnUiThread(new g(this, i8));
            }
        } catch (Throwable th) {
            this.applying = false;
            throw th;
        }
    }

    private void exitApp() {
        Context context = this.context;
        if (context instanceof Activity) {
            try {
                ((Activity) context).finishAffinity();
            } catch (Exception unused) {
            }
        }
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static ConfigSyncManager getInstance(Context context) {
        if (instance == null) {
            synchronized (ConfigSyncManager.class) {
                try {
                    if (instance == null) {
                        instance = new ConfigSyncManager(context.getApplicationContext());
                    }
                } finally {
                }
            }
        }
        return instance;
    }

    private void handleLicenseTokenChange() {
        String semanticFingerprint = semanticFingerprint(this.prefs.getString("license_token", h0.FRAGMENT_ENCODE_SET));
        if (semanticFingerprint.equals(this.prefs.getString(KEY_LICENSE_SEMANTIC_HASH, h0.FRAGMENT_ENCODE_SET))) {
            return;
        }
        this.prefs.edit().putString(KEY_LICENSE_SEMANTIC_HASH, semanticFingerprint).apply();
        if (semanticFingerprint.isEmpty()) {
            return;
        }
        markDirty();
        scheduleUpload(true);
    }

    private boolean hasPermissionIssues() {
        return NativeBridge.computePermissionState(this.context) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkPostDownloadPermissions$5(DialogInterface dialogInterface, int i7) {
        exitApp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkPostDownloadPermissions$6(DialogInterface dialogInterface, int i7) {
        exitApp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$register$0(SharedPreferences sharedPreferences, String str) {
        if (this.applying) {
            return;
        }
        if (str != null && str.equals("license_token")) {
            handleLicenseTokenChange();
            return;
        }
        if (str != null && str.equals("license_uid")) {
            markDirty();
            scheduleUpload(true);
        } else if (shouldUploadKey(str)) {
            markDirty();
            scheduleUpload(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleUpload$3() {
        new Thread(new g(this, 3)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$upload$4() {
        ToastHelper.show(this.context, "配置文件已同步到云端！");
    }

    private void markDirty() {
        a.a.m(this.prefs, KEY_DIRTY, true);
    }

    private static byte[] readAll(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartApp() {
        AlarmManager alarmManager = (AlarmManager) this.context.getSystemService("alarm");
        Intent intent = new Intent(this.context, (Class<?>) MainActivity.class);
        intent.addFlags(268468224);
        PendingIntent activity = PendingIntent.getActivity(this.context, 0, intent, 335544320);
        long currentTimeMillis = System.currentTimeMillis() + 500;
        if (alarmManager != null) {
            alarmManager.setExactAndAllowWhileIdle(1, currentTimeMillis, activity);
        }
        this.context.stopService(new Intent(this.context, (Class<?>) LogMonitorService.class));
        this.context.stopService(new Intent(this.context, (Class<?>) AmapBroadcastService.class));
        this.context.stopService(new Intent(this.context, (Class<?>) FloatingWindowService.class));
        this.context.stopService(new Intent(this.context, (Class<?>) NavigationStatusSimulatorService.class));
        this.context.stopService(new Intent(this.context, (Class<?>) ThemeMonitorService.class));
        this.context.stopService(new Intent(this.context, (Class<?>) ScreenCastingFloatingService.class));
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    private void scheduleIfDirty() {
        if (this.prefs.getBoolean(KEY_DIRTY, false)) {
            scheduleUpload(this.prefs.getBoolean(KEY_FORCE_UPLOAD, false));
        }
    }

    private void scheduleUpload(boolean z6) {
        if (z6) {
            a.a.m(this.prefs, KEY_FORCE_UPLOAD, true);
        }
        if ((NativeBridge.getLicenseMask(this.context) & SharedPreferencesUtils.CAP_FEATURES) != 3950) {
            return;
        }
        if (this.uploadRunnable == null) {
            this.uploadRunnable = new g(this, 4);
        }
        long computeDelay = z6 ? 1000L : computeDelay();
        this.handler.removeCallbacks(this.uploadRunnable);
        this.handler.postDelayed(this.uploadRunnable, computeDelay);
    }

    private static String semanticFingerprint(String str) {
        if (str == null || str.isEmpty()) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        int lastIndexOf = str.lastIndexOf(124);
        if (lastIndexOf > 0) {
            str = str.substring(0, lastIndexOf);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.optString(a2.m.PREFIX) + "|" + jSONObject.optString("uid") + "|" + jSONObject.optString("full") + "|" + jSONObject.optString("feats") + "|" + jSONObject.optString("exp") + "|" + jSONObject.optString("pkh") + "|" + jSONObject.optString("block");
        } catch (Exception unused) {
            return str;
        }
    }

    private boolean shouldUploadKey(String str) {
        return (str == null || str.startsWith("license_") || str.equals("otp_verified") || str.equals("config_sync_done") || str.equals("initial_restart_done") || str.equals(KEY_LAST_UPLOAD_TS) || str.equals(KEY_DIRTY) || str.equals(KEY_FORCE_UPLOAD) || str.startsWith("screen_casting_app_state_")) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: upload, reason: merged with bridge method [inline-methods] */
    public void lambda$scheduleUpload$2() {
        synchronized (UPLOAD_LOCK) {
            try {
                if (!this.prefs.getBoolean(KEY_DIRTY, false)) {
                    this.prefs.edit().putBoolean(KEY_FORCE_UPLOAD, false).apply();
                    return;
                }
                boolean z6 = this.prefs.getBoolean(KEY_FORCE_UPLOAD, false);
                long j7 = this.prefs.getLong(KEY_LAST_UPLOAD_TS, 0L);
                long currentTimeMillis = System.currentTimeMillis();
                if (!z6 && j7 > 0 && currentTimeMillis >= j7 && currentTimeMillis - j7 < 60000) {
                    scheduleUpload(false);
                    return;
                }
                String licenseUid = SharedPreferencesUtils.getLicenseUid(this.context);
                Context context = this.context;
                if (licenseUid == null) {
                    licenseUid = "unknown";
                }
                if (NativeBridge.uploadPrefsData(context, licenseUid, PREF_NAME)) {
                    this.prefs.edit().putLong(KEY_LAST_UPLOAD_TS, System.currentTimeMillis()).putBoolean(KEY_DIRTY, false).putBoolean(KEY_FORCE_UPLOAD, false).apply();
                    this.handler.post(new g(this, 2));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void maybeDownloadAndApply(String str, boolean z6) {
        if (z6 && !SharedPreferencesUtils.isConfigSyncDone(this.context)) {
            new Thread(new l(4, this, str)).start();
        }
    }

    public synchronized void register() {
        this.refCount++;
        if (this.listener != null) {
            scheduleIfDirty();
            return;
        }
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.navi.tool.utils.f
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                ConfigSyncManager.this.lambda$register$0(sharedPreferences, str);
            }
        };
        this.listener = onSharedPreferenceChangeListener;
        this.prefs.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        handleLicenseTokenChange();
        scheduleIfDirty();
    }

    public void schedulePendingUpload() {
        scheduleIfDirty();
    }

    public synchronized void unregister() {
        try {
            int i7 = this.refCount;
            if (i7 > 0) {
                this.refCount = i7 - 1;
            }
            if (this.refCount > 0) {
                return;
            }
            try {
                SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = this.listener;
                if (onSharedPreferenceChangeListener != null) {
                    this.prefs.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
                }
            } catch (Exception unused) {
            }
            this.listener = null;
            Runnable runnable = this.uploadRunnable;
            if (runnable != null) {
                this.handler.removeCallbacks(runnable);
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
