package com.navi.tool.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class CrashLogManager {
    private static final String LOG_DIR_NAME = "logs";
    private static final String ROOT_DIR_NAME = "navitool";
    private static final String TAG = "CrashLogManager";
    private static Context sAppContext = null;
    private static volatile boolean sInstalled = false;
    private static volatile String sLastForegroundPage = "unknown";
    private static Thread.UncaughtExceptionHandler sPreviousHandler;

    private CrashLogManager() {
    }

    private static String buildCrashContent(Thread thread, Throwable th) {
        StringBuilder sb = new StringBuilder(2048);
        sb.append("time=");
        sb.append(formatNow());
        sb.append("\npackage=");
        sb.append(getPackageName());
        sb.append("\nversionName=");
        sb.append(getVersionName());
        sb.append("\nversionCode=");
        sb.append(getVersionCode());
        sb.append("\nthread=");
        sb.append(thread != null ? thread.getName() : "unknown");
        sb.append("\nthreadId=");
        sb.append(thread != null ? thread.getId() : -1L);
        sb.append("\nprocessId=");
        sb.append(Process.myPid());
        sb.append("\nforegroundPage=");
        sb.append(sLastForegroundPage);
        sb.append("\ndevice=");
        sb.append(Build.MANUFACTURER);
        sb.append(' ');
        sb.append(Build.MODEL);
        sb.append("\nandroid=");
        sb.append(Build.VERSION.RELEASE);
        sb.append("\nsdkInt=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("\nbrand=");
        sb.append(Build.BRAND);
        sb.append("\nfingerprint=");
        sb.append(Build.FINGERPRINT);
        sb.append("\n\nexception=");
        sb.append(th.getClass().getName());
        sb.append("\nmessage=");
        sb.append(String.valueOf(th.getMessage()));
        sb.append("\n\nstacktrace:\n");
        sb.append(getStackTraceString(th));
        sb.append("\nall_threads:\n");
        sb.append(getAllThreadStacks());
        return sb.toString();
    }

    private static String formatNow() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).format(new Date());
    }

    private static String getAllThreadStacks() {
        StringBuilder sb = new StringBuilder(4096);
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            Thread key = entry.getKey();
            StackTraceElement[] value = entry.getValue();
            sb.append("--- thread: ");
            sb.append(key != null ? key.getName() : "unknown");
            sb.append(" (id=");
            sb.append(key != null ? key.getId() : -1L);
            sb.append(") ---\n");
            if (value != null) {
                for (StackTraceElement stackTraceElement : value) {
                    sb.append("    at ");
                    sb.append(stackTraceElement);
                    sb.append('\n');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static File getCrashDir(Context context) {
        File file = new File(new File(Environment.getExternalStorageDirectory(), ROOT_DIR_NAME), LOG_DIR_NAME);
        if (!file.exists() && !file.mkdirs()) {
            file.getAbsolutePath();
        }
        return file;
    }

    public static String getLastForegroundPage() {
        return sLastForegroundPage;
    }

    private static PackageInfo getPackageInfo() {
        Context context = sAppContext;
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(sAppContext.getPackageName(), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    private static String getPackageName() {
        Context context = sAppContext;
        return context != null ? context.getPackageName() : "unknown";
    }

    private static String getStackTraceString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    private static long getVersionCode() {
        long longVersionCode;
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo == null) {
            return -1L;
        }
        if (Build.VERSION.SDK_INT < 28) {
            return packageInfo.versionCode;
        }
        longVersionCode = packageInfo.getLongVersionCode();
        return longVersionCode;
    }

    private static String getVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        return packageInfo != null ? packageInfo.versionName : "unknown";
    }

    public static synchronized void install(Context context) {
        synchronized (CrashLogManager.class) {
            if (!sInstalled && context != null) {
                Context applicationContext = context.getApplicationContext();
                sAppContext = applicationContext;
                registerActivityTracker(applicationContext);
                sPreviousHandler = Thread.getDefaultUncaughtExceptionHandler();
                Thread.setDefaultUncaughtExceptionHandler(new h());
                sInstalled = true;
                AnrWatchdog.start(sAppContext);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$install$0(Thread thread, Throwable th) {
        try {
            writeCrashLog(thread, th);
        } catch (Throwable unused) {
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = sPreviousHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
    }

    private static void registerActivityTracker(Context context) {
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.navi.tool.utils.CrashLogManager.1
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    if (activity != null) {
                        String unused = CrashLogManager.sLastForegroundPage = activity.getClass().getName();
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                }
            });
        }
    }

    private static void writeCrashLog(Thread thread, Throwable th) {
        Context context = sAppContext;
        if (context == null || th == null) {
            return;
        }
        File file = new File(getCrashDir(context), a.a.h("crash_", new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US).format(new Date()), ".txt"));
        writeString(file, buildCrashContent(thread, th));
        file.getAbsolutePath();
    }

    private static void writeString(File file, String str) {
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                try {
                    bufferedWriter.write(str);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStreamWriter.close();
                    fileOutputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
