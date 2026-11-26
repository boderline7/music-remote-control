package com.navi.tool.utils;

import android.content.Context;
import com.navi.tool.utils.CrashReportCollector;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class AutoCrashUploader {
    private static final long MAX_AGE_MS = 7200000;
    private static final int MAX_CRASH_BYTES = 2097152;
    private static final int MAX_LOG_BYTES = 3145728;
    private static final long MIN_INTERVAL_MS = 86400000;
    private static final String TAG = "AutoCrashUpload";
    private static final AtomicBoolean sChecked = new AtomicBoolean(false);

    private AutoCrashUploader() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$maybeUpload$0(Context context, String str) {
        String licenseToken;
        CrashReportCollector.Report collect;
        try {
            File latestCrashFile = CrashReportCollector.latestCrashFile(context);
            if (latestCrashFile != null && (licenseToken = SharedPreferencesUtils.getLicenseToken(context)) != null && !licenseToken.isEmpty()) {
                long lastAutoCrashUploadAt = SharedPreferencesUtils.getLastAutoCrashUploadAt(context);
                if (latestCrashFile.lastModified() <= lastAutoCrashUploadAt) {
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - latestCrashFile.lastModified() > MAX_AGE_MS) {
                    return;
                }
                if ((lastAutoCrashUploadAt <= 0 || currentTimeMillis - lastAutoCrashUploadAt >= 86400000) && (collect = CrashReportCollector.collect(context, MAX_LOG_BYTES, 2097152)) != null && !collect.isEmpty()) {
                    if (CrashReportUploader.upload(context, str, "auto_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date(currentTimeMillis)), collect.summary, collect.logcat, collect.crash).ok) {
                        SharedPreferencesUtils.setLastAutoCrashUploadAt(context, currentTimeMillis);
                    }
                }
            }
        } catch (Throwable th) {
            th.toString();
        }
    }

    public static void maybeUpload(Context context, String str) {
        if (context == null || str == null || str.trim().isEmpty()) {
            return;
        }
        int i7 = 1;
        if (sChecked.compareAndSet(false, true)) {
            new Thread(new n(context.getApplicationContext(), i7, str), "auto-crash-upload").start();
        }
    }
}
