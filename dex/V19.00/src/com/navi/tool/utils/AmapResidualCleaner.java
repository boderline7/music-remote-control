package com.navi.tool.utils;

import android.os.Build;
import android.os.Environment;
import java.io.File;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class AmapResidualCleaner {
    public static final String AMAP_DATA_DIR_NAME = "amapauto9";
    private static final String TAG = "AmapResidualCleaner";

    private AmapResidualCleaner() {
    }

    public static boolean canWritePublicStorage() {
        boolean isExternalStorageManager;
        if (Build.VERSION.SDK_INT < 30) {
            return true;
        }
        try {
            isExternalStorageManager = Environment.isExternalStorageManager();
            return isExternalStorageManager;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static File dataDir() {
        return new File(Environment.getExternalStorageDirectory(), AMAP_DATA_DIR_NAME);
    }

    public static boolean deleteTree(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        HashSet hashSet = new HashSet();
        arrayDeque.push(file);
        boolean z6 = true;
        while (true) {
            if (arrayDeque.isEmpty()) {
                break;
            }
            File file2 = (File) arrayDeque.pop();
            File[] listFiles = file2.listFiles();
            String absolutePath = file2.getAbsolutePath();
            if (listFiles != null && listFiles.length > 0 && hashSet.add(absolutePath)) {
                arrayDeque.push(file2);
                for (File file3 : listFiles) {
                    arrayDeque.push(file3);
                }
            } else if (file2.exists() && !file2.delete()) {
                z6 = false;
            }
        }
        return z6 && !file.exists();
    }

    public static String formatSize(long j7) {
        if (j7 <= 0) {
            return "0 B";
        }
        String[] strArr = {"B", "KB", "MB", "GB", "TB"};
        double d7 = j7;
        int i7 = 0;
        while (d7 >= 1024.0d && i7 < 4) {
            d7 /= 1024.0d;
            i7++;
        }
        return String.format(Locale.US, "%.1f %s", Double.valueOf(d7), strArr[i7]);
    }

    public static boolean isAmapPackage(String str) {
        if (str == null || str.equals(AppDetectionUtils.AUTONAVI_EXCLUDE)) {
            return false;
        }
        return str.startsWith(AppDetectionUtils.AUTONAVI_PREFIX);
    }

    public static long sizeOf(File file) {
        long j7 = 0;
        if (file != null && file.exists()) {
            ArrayDeque arrayDeque = new ArrayDeque();
            arrayDeque.push(file);
            while (!arrayDeque.isEmpty()) {
                File file2 = (File) arrayDeque.pop();
                j7 += file2.length();
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File file3 : listFiles) {
                        arrayDeque.push(file3);
                    }
                }
            }
        }
        return j7;
    }
}
