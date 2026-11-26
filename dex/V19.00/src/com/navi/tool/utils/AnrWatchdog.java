package com.navi.tool.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.navi.tool.utils.CrashReportUploader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class AnrWatchdog {
    private static final String FILE_PREFIX = "anr_watchdog_";
    private static final String FILE_SUFFIX = ".txt";
    private static final long HEARTBEAT_INTERVAL_MS = 2000;
    private static final int MAX_FILES = 5;
    private static final int MAX_UPLOAD_BYTES = 8388608;
    private static final long STARTUP_DELAY_MS = 10000;
    private static final long STUCK_THRESHOLD_MS = 5000;
    private static final String TAG = "AnrWatchdog";
    private static volatile boolean sStarted;

    private AnrWatchdog() {
    }

    private static String describeStuck() {
        return "ANR看门狗：主线程卡死 5000ms";
    }

    private static String dumpAllThreads() {
        StringBuilder sb = new StringBuilder(16384);
        try {
            Thread thread = Looper.getMainLooper().getThread();
            sb.append("--- thread: ");
            sb.append(thread.getName());
            sb.append(" (MAIN) ---\n");
            for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
                sb.append("    at ");
                sb.append(stackTraceElement);
                sb.append('\n');
            }
            sb.append('\n');
            for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                Thread key = entry.getKey();
                if (key != null && key != thread) {
                    sb.append("--- thread: ");
                    sb.append(key.getName());
                    sb.append(" (id=");
                    sb.append(key.getId());
                    sb.append(") ---\n");
                    StackTraceElement[] value = entry.getValue();
                    if (value != null) {
                        for (StackTraceElement stackTraceElement2 : value) {
                            sb.append("    at ");
                            sb.append(stackTraceElement2);
                            sb.append('\n');
                        }
                    }
                    sb.append('\n');
                }
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    private static void handleStuck(Context context) {
        String str;
        String dumpAllThreads = dumpAllThreads();
        if (dumpAllThreads.isEmpty()) {
            return;
        }
        try {
            str = CrashLogManager.getLastForegroundPage();
        } catch (Throwable unused) {
            str = "unknown";
        }
        Locale locale = Locale.US;
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).format(new Date());
        StringBuilder sb = new StringBuilder(dumpAllThreads.length() + 256);
        sb.append("exception=ANR_WATCHDOG\nmessage=主线程卡死 5000ms\ntime=");
        sb.append(format);
        sb.append("\nforegroundPage=");
        sb.append(str);
        String i7 = a.a.i(sb, "\n\nstacktrace:\n", dumpAllThreads);
        File crashDir = CrashLogManager.getCrashDir(context);
        File file = new File(crashDir, FILE_PREFIX + new SimpleDateFormat("yyyyMMdd_HHmmss", locale).format(new Date()) + FILE_SUFFIX);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                try {
                    outputStreamWriter.write(i7);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    fileOutputStream.close();
                    file.getAbsolutePath();
                    trimOldFiles(crashDir);
                    upload(context, file, i7, describeStuck());
                } finally {
                }
            } finally {
            }
        } catch (Throwable unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$loop$2(Object obj, boolean[] zArr) {
        synchronized (obj) {
            zArr[0] = true;
            obj.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$retryPending$4(File file, String str) {
        return str.startsWith(FILE_PREFIX) && str.endsWith(FILE_SUFFIX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$trimOldFiles$5(File file, String str) {
        return str.startsWith(FILE_PREFIX) && str.endsWith(FILE_SUFFIX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$upload$3(Context context, String str, String str2, File file) {
        try {
            String resolveUid = resolveUid(context);
            if (resolveUid.isEmpty()) {
                return;
            }
            if (CrashReportUploader.upload(context, resolveUid, "anr_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + FILE_SUFFIX, str, h0.FRAGMENT_ENCODE_SET, trimContent(str2)).ok) {
                file.delete();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loop(Context context) {
        boolean z6;
        sleep(STARTUP_DELAY_MS);
        Handler handler = new Handler(Looper.getMainLooper());
        while (true) {
            boolean z7 = false;
            while (true) {
                Object obj = new Object();
                boolean[] zArr = {false};
                handler.post(new l(3, obj, zArr));
                synchronized (obj) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long j7 = STUCK_THRESHOLD_MS;
                    long j8 = currentTimeMillis + STUCK_THRESHOLD_MS;
                    while (true) {
                        z6 = zArr[0];
                        if (z6 || j7 <= 0) {
                            break;
                        }
                        try {
                            obj.wait(j7);
                        } catch (InterruptedException unused) {
                        }
                        j7 = j8 - System.currentTimeMillis();
                    }
                }
                if (z6) {
                    break;
                }
                if (!z7) {
                    try {
                        handleStuck(context);
                    } catch (Throwable unused2) {
                    }
                    z7 = true;
                }
                sleep(HEARTBEAT_INTERVAL_MS);
            }
            sleep(HEARTBEAT_INTERVAL_MS);
        }
    }

    private static String readFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[8192];
                    do {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    } while (byteArrayOutputStream.size() < MAX_UPLOAD_BYTES);
                    String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                    return str;
                } finally {
                }
            } finally {
            }
        } catch (Throwable unused) {
            return h0.FRAGMENT_ENCODE_SET;
        }
    }

    private static String resolveUid(Context context) {
        String licenseUid = SharedPreferencesUtils.getLicenseUid(context);
        if (licenseUid == null || licenseUid.trim().isEmpty()) {
            licenseUid = NativeBridge.getSystemVin();
        }
        return licenseUid == null ? h0.FRAGMENT_ENCODE_SET : licenseUid.trim();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void retryPending(Context context) {
        try {
            File[] listFiles = CrashLogManager.getCrashDir(context).listFiles(new com.navi.tool.service.f(2));
            if (listFiles != null && listFiles.length != 0) {
                Arrays.sort(listFiles, Comparator.comparingLong(new a(1)));
                for (File file : listFiles) {
                    String readFile = readFile(file);
                    if (readFile.isEmpty()) {
                        file.delete();
                    } else {
                        String resolveUid = resolveUid(context);
                        if (resolveUid.isEmpty()) {
                            return;
                        }
                        CrashReportUploader.Result upload = CrashReportUploader.upload(context, resolveUid, "anr_" + file.getName().replace(FILE_PREFIX, h0.FRAGMENT_ENCODE_SET).replace(FILE_SUFFIX, h0.FRAGMENT_ENCODE_SET) + FILE_SUFFIX, "ANR看门狗：主线程卡死（补传）", h0.FRAGMENT_ENCODE_SET, trimContent(readFile));
                        file.getName();
                        if (!upload.ok) {
                            return;
                        } else {
                            file.delete();
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static void sleep(long j7) {
        try {
            Thread.sleep(j7);
        } catch (InterruptedException unused) {
        }
    }

    public static void start(Context context) {
        if (sStarted || context == null) {
            return;
        }
        sStarted = true;
        Context applicationContext = context.getApplicationContext();
        new Thread(new o(applicationContext, 1), "anr-watchdog-retry").start();
        Thread thread = new Thread(new o(applicationContext, 2), "anr-watchdog");
        thread.setDaemon(true);
        thread.start();
    }

    private static String trimContent(String str) {
        if (str == null) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length;
        int i7 = MAX_UPLOAD_BYTES;
        if (length <= MAX_UPLOAD_BYTES) {
            return str;
        }
        while (i7 > 0 && i7 < bytes.length && (bytes[i7] & 192) == 128) {
            i7--;
        }
        return new String(bytes, 0, i7, StandardCharsets.UTF_8).concat("\n[已截断]\n");
    }

    private static void trimOldFiles(File file) {
        File[] listFiles = file.listFiles(new com.navi.tool.service.f(1));
        if (listFiles == null || listFiles.length <= 5) {
            return;
        }
        int i7 = 0;
        Arrays.sort(listFiles, Comparator.comparingLong(new a(i7)));
        while (i7 < listFiles.length - 5) {
            listFiles[i7].delete();
            i7++;
        }
    }

    private static void upload(Context context, File file, String str, String str2) {
        new Thread(new t(context, str2, str, file), "anr-watchdog-upload").start();
    }
}
