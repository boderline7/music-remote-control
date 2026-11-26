package com.navi.tool.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class UidSyncManager {
    private static final long INTERVAL = 300000;
    private static final String TAG = "UidSyncManager";
    private final Context context;
    private Runnable runnable;
    private volatile boolean running;
    private final AtomicBoolean syncInProgress = new AtomicBoolean(false);
    private final Handler handler = new Handler(Looper.getMainLooper());

    public UidSyncManager(Context context) {
        this.context = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$triggerSync$0() {
        try {
            perform();
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.syncInProgress.set(false);
            throw th;
        }
        this.syncInProgress.set(false);
    }

    private void perform() {
        String licenseUid = SharedPreferencesUtils.getLicenseUid(this.context);
        if (licenseUid == null || licenseUid.trim().isEmpty()) {
            return;
        }
        try {
            NativeBridge.uidSyncOnce(licenseUid);
        } catch (Exception unused) {
        }
        try {
            AutoCrashUploader.maybeUpload(this.context, licenseUid);
        } catch (Throwable unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerSync() {
        int i7 = 1;
        if (this.syncInProgress.compareAndSet(false, true)) {
            new Thread(new k(i7, this), "uid-sync-worker").start();
        }
    }

    public void start() {
        this.running = true;
        Runnable runnable = this.runnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: com.navi.tool.utils.UidSyncManager.1
            @Override // java.lang.Runnable
            public void run() {
                if (UidSyncManager.this.running) {
                    UidSyncManager.this.triggerSync();
                    if (UidSyncManager.this.running) {
                        UidSyncManager.this.handler.postDelayed(this, UidSyncManager.INTERVAL);
                    }
                }
            }
        };
        this.runnable = runnable2;
        this.handler.postDelayed(runnable2, 10000L);
    }

    public void stop() {
        this.running = false;
        Runnable runnable = this.runnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
    }
}
