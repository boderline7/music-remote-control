package com.navi.tool.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.yalantis.ucrop.view.CropImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class CrashReportCollector {
    private static final int MAX_CRASH_BYTES = 10485760;
    private static final int MAX_LOG_BYTES = 20971520;
    public static final int MAX_TOTAL_BYTES = 31457280;

    public static final class Report {
        public boolean hasCrash;
        public boolean hasLogcat;
        public String logcat = h0.FRAGMENT_ENCODE_SET;
        public String crash = h0.FRAGMENT_ENCODE_SET;
        public String installedApps = h0.FRAGMENT_ENCODE_SET;
        public String summary = h0.FRAGMENT_ENCODE_SET;

        public boolean isEmpty() {
            return (this.hasLogcat || this.hasCrash) ? false : true;
        }
    }

    private CrashReportCollector() {
    }

    private static String buildSummary(Report report) {
        if (report.hasCrash) {
            String firstMatch = firstMatch(report.crash, "(?m)^exception=(.*)$");
            String firstMatch2 = firstMatch(report.crash, "(?m)^message=(.*)$");
            String firstMatch3 = firstMatch(report.crash, "(?m)^foregroundPage=(.*)$");
            String firstMatch4 = firstMatch(report.crash, "(?m)^time=(.*)$");
            if (!firstMatch.isEmpty() || !firstMatch2.isEmpty()) {
                String concat = "异常：".concat(firstMatch);
                if (!firstMatch2.isEmpty()) {
                    concat = concat + "\n信息：" + firstMatch2;
                }
                if (!firstMatch3.isEmpty()) {
                    concat = concat + "\n页面：" + firstMatch3;
                }
                if (!firstMatch4.isEmpty()) {
                    concat = concat + "\n时间：" + firstMatch4;
                }
                return trimText(concat, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
            }
        }
        if (!report.hasLogcat) {
            return "未提取到明确异常摘要";
        }
        String firstMatch5 = firstMatch(report.logcat, "(?i)(ANR in [^\\r\\n]+)");
        if (!firstMatch5.isEmpty()) {
            return trimText("ANR：".concat(firstMatch5), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
        }
        String firstMatch6 = firstMatch(report.logcat, "(?m)(am_anr[^\\r\\n]*)");
        if (!firstMatch6.isEmpty()) {
            return trimText("ANR：".concat(firstMatch6), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
        }
        String firstMatch7 = firstMatch(report.logcat, "(?m)^.*FATAL EXCEPTION.*$");
        if (firstMatch7.isEmpty()) {
            return "未提取到明确异常摘要";
        }
        String nextLineAfter = nextLineAfter(report.logcat, firstMatch7);
        StringBuilder sb = new StringBuilder("闪退：");
        sb.append(firstMatch7);
        sb.append(nextLineAfter.isEmpty() ? h0.FRAGMENT_ENCODE_SET : "\n".concat(nextLineAfter));
        return trimText(sb.toString(), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
    }

    private static int byteLength(String str) {
        if (str == null) {
            return 0;
        }
        return str.getBytes(StandardCharsets.UTF_8).length;
    }

    public static Report collect(Context context) {
        return collect(context, MAX_LOG_BYTES, MAX_CRASH_BYTES);
    }

    public static Report collect(Context context, int i7, int i8) {
        Report report = new Report();
        int i9 = i7 + i8;
        report.installedApps = collectInstalledApps(context);
        if (hasReadLogs(context)) {
            String runCommand = runCommand("logcat -b crash -d -v threadtime", 12000L, i7 / 2);
            String runCommand2 = runCommand("logcat -b events -d -v threadtime | grep -E 'com.navi.tool|am_anr|am_crash' | tail -n 2000", 12000L, i7 / 3);
            String runCommand3 = runCommand(a.a.d("logcat -d -b main -b system -b crash -v threadtime | grep -iE -A 200 'ANR in com.navi.tool|FATAL EXCEPTION|Fatal signal|Abort message' | tail -c ", i7), 20000L, i7);
            StringBuilder sb = new StringBuilder();
            if (!report.installedApps.isEmpty()) {
                sb.append("===== 已安装普通应用 =====\n");
                sb.append(report.installedApps);
                sb.append('\n');
            }
            if (!runCommand.isEmpty()) {
                sb.append("===== logcat -b crash =====\n");
                sb.append(runCommand);
                sb.append('\n');
            }
            if (!runCommand2.isEmpty()) {
                sb.append("===== logcat -b events =====\n");
                sb.append(runCommand2);
                sb.append('\n');
            }
            if (!runCommand3.isEmpty()) {
                sb.append("===== logcat main/system/crash (filtered) =====\n");
                sb.append(runCommand3);
                sb.append('\n');
            }
            String sb2 = sb.toString();
            if (hasCrashMarker(sb2)) {
                report.logcat = trimToBytes(sb2, i7);
                report.hasLogcat = !r10.isEmpty();
            }
        }
        File latestCrashFile = latestCrashFile(context);
        if (latestCrashFile != null) {
            String readFileCapped = readFileCapped(latestCrashFile, i8);
            if (!readFileCapped.isEmpty()) {
                report.crash = readFileCapped;
                report.hasCrash = true;
            }
        }
        int max = Math.max(0, (i9 - byteLength(report.crash)) - 4096);
        if (byteLength(report.logcat) > max) {
            report.logcat = trimToBytes(report.logcat, max);
        }
        int byteLength = byteLength(report.crash);
        int max2 = Math.max(0, (i9 - byteLength(report.logcat)) - 4096);
        if (byteLength > max2) {
            report.crash = trimToBytes(report.crash, max2);
        }
        String str = report.logcat;
        report.hasLogcat = (str == null || str.isEmpty()) ? false : true;
        String str2 = report.crash;
        report.hasCrash = (str2 == null || str2.isEmpty()) ? false : true;
        report.summary = buildSummary(report);
        return report;
    }

    public static String collectInstalledApps(Context context) {
        ApplicationInfo applicationInfo;
        String str;
        StringBuilder sb = new StringBuilder();
        try {
            PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            ArrayList arrayList = new ArrayList();
            if (installedPackages != null) {
                for (PackageInfo packageInfo : installedPackages) {
                    if (packageInfo != null && (applicationInfo = packageInfo.applicationInfo) != null && (applicationInfo.flags & 1) == 0) {
                        try {
                            str = packageManager.getApplicationLabel(applicationInfo).toString();
                        } catch (Throwable unused) {
                            str = applicationInfo.packageName;
                        }
                        long longVersionCode = Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
                        String str2 = packageInfo.versionName;
                        if (str2 == null) {
                            str2 = h0.FRAGMENT_ENCODE_SET;
                        }
                        arrayList.add(applicationInfo.packageName + " | " + str + " | " + str2 + " (" + longVersionCode + ")");
                    }
                }
            }
            Collections.sort(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append((String) it.next());
                sb.append('\n');
            }
            sb.append("共 ");
            sb.append(arrayList.size());
            sb.append(" 个\n");
        } catch (Throwable th) {
            sb.append("收集应用列表失败: ");
            sb.append(th);
            sb.append('\n');
        }
        return sb.toString();
    }

    private static String firstMatch(String str, String str2) {
        String group;
        if (str == null) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        try {
            Matcher matcher = Pattern.compile(str2).matcher(str);
            if (matcher.find() && (group = matcher.group(1)) != null) {
                return group.trim();
            }
            return h0.FRAGMENT_ENCODE_SET;
        } catch (Throwable unused) {
            return h0.FRAGMENT_ENCODE_SET;
        }
    }

    private static boolean hasCrashMarker(String str) {
        if (str != null && !str.isEmpty()) {
            String lowerCase = str.toLowerCase(Locale.US);
            if ((lowerCase.contains("com.navi.tool") && (lowerCase.contains("fatal exception") || lowerCase.contains("fatal signal") || lowerCase.contains("abort message"))) || lowerCase.contains("anr in com.navi.tool")) {
                return true;
            }
            for (String str2 : lowerCase.split("\n")) {
                if ((str2.contains("am_crash") || str2.contains("am_anr")) && str2.contains("com.navi.tool")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasReadLogs(Context context) {
        try {
            return context.checkSelfPermission("android.permission.READ_LOGS") == 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$latestCrashFile$0(File file, String str) {
        return str.startsWith("crash_") && str.endsWith(".txt");
    }

    public static File latestCrashFile(Context context) {
        try {
            File[] listFiles = CrashLogManager.getCrashDir(context).listFiles(new com.navi.tool.service.f(3));
            if (listFiles != null && listFiles.length != 0) {
                File file = null;
                for (File file2 : listFiles) {
                    if (file2.isFile() && (file == null || file2.lastModified() > file.lastModified())) {
                        file = file2;
                    }
                }
                return file;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private static String nextLineAfter(String str, String str2) {
        int indexOf;
        int indexOf2 = str.indexOf(str2);
        if (indexOf2 < 0 || (indexOf = str.indexOf(10, indexOf2)) < 0) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        int i7 = indexOf + 1;
        int indexOf3 = str.indexOf(10, i7);
        return (indexOf3 < 0 ? str.substring(i7) : str.substring(i7, indexOf3)).trim();
    }

    private static String readFileCapped(File file, int i7) {
        int size;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1 || (size = i7 - byteArrayOutputStream.size()) <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, Math.min(read, size));
                    }
                    String str = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
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

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:26:0x006e
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /* JADX WARN: Unreachable blocks removed: 4, instructions: 4 */
    private static java.lang.String runCommand(java.lang.String r9, long r10, int r12) {
        /*
            r0 = 0
            java.lang.ProcessBuilder r1 = new java.lang.ProcessBuilder     // Catch: java.lang.Throwable -> L60
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L60
            java.lang.String r3 = "sh"
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.Throwable -> L60
            java.lang.String r3 = "-c"
            r5 = 1
            r2[r5] = r3     // Catch: java.lang.Throwable -> L60
            r3 = 2
            r2[r3] = r9     // Catch: java.lang.Throwable -> L60
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L60
            java.lang.ProcessBuilder r9 = r1.redirectErrorStream(r5)     // Catch: java.lang.Throwable -> L60
            java.lang.Process r0 = r9.start()     // Catch: java.lang.Throwable -> L60
            java.io.InputStream r9 = r0.getInputStream()     // Catch: java.lang.Throwable -> L60
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L60
            r1.<init>()     // Catch: java.lang.Throwable -> L60
            r2 = 8192(0x2000, float:1.148E-41)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L60
            long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L60
            long r5 = r5 + r10
        L30:
            int r10 = r9.read(r2)     // Catch: java.lang.Throwable -> L60
            r11 = -1
            if (r10 == r11) goto L51
            long r7 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L60
            int r11 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r11 <= 0) goto L40
            goto L51
        L40:
            int r11 = r1.size()     // Catch: java.lang.Throwable -> L60
            int r11 = r12 - r11
            if (r11 > 0) goto L49
            goto L51
        L49:
            int r10 = java.lang.Math.min(r10, r11)     // Catch: java.lang.Throwable -> L60
            r1.write(r2, r4, r10)     // Catch: java.lang.Throwable -> L60
            goto L30
        L51:
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> L60
            byte[] r10 = r1.toByteArray()     // Catch: java.lang.Throwable -> L60
            java.nio.charset.Charset r11 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L60
            r9.<init>(r10, r11)     // Catch: java.lang.Throwable -> L60
            r0.destroy()     // Catch: java.lang.Throwable -> L5f
        L5f:
            return r9
        L60:
            java.lang.String r9 = ""
            if (r0 == 0) goto L67
            r0.destroy()     // Catch: java.lang.Throwable -> L67
        L67:
            return r9
        L68:
            r9 = move-exception
            if (r0 == 0) goto L6e
            r0.destroy()     // Catch: java.lang.Throwable -> L6e
        L6e:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.utils.CrashReportCollector.runCommand(java.lang.String, long, int):java.lang.String");
    }

    private static String trimText(String str, int i7) {
        if (str == null) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        if (str.length() <= i7) {
            return str;
        }
        return str.substring(0, i7) + "…";
    }

    private static String trimToBytes(String str, int i7) {
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
