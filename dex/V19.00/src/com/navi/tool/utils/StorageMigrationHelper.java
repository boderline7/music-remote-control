package com.navi.tool.utils;

import android.content.Context;
import android.os.Environment;
import com.navi.tool.model.LogMediaProfile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class StorageMigrationHelper {
    private static final String TAG = "StorageMigration";

    private static void copyFile(File file, File file2) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        fileOutputStream.close();
                        fileInputStream.close();
                        return;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static boolean deleteDirIfEmpty(File file) {
        if (!file.isDirectory()) {
            return false;
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            return file.delete();
        }
        return false;
    }

    private static void fixProfilePaths(Context context) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "navitool");
            String str = (hasMediaFiles(new File(file, "mp3")) || !hasMediaFiles(new File(file, ".mp3"))) ? "mp3" : ".mp3";
            for (LogMediaProfile logMediaProfile : LogMediaProfileStorage.loadProfiles(context)) {
                String mediaPath = logMediaProfile.getMediaPath();
                if (mediaPath != null && !mediaPath.isEmpty()) {
                    String absolutePath = new File(new File(file, str), new File(mediaPath).getName()).getAbsolutePath();
                    if (!mediaPath.equals(absolutePath)) {
                        logMediaProfile.setMediaPath(absolutePath);
                        LogMediaProfileStorage.saveProfile(context, logMediaProfile);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    private static boolean hasMediaFiles(File file) {
        File[] listFiles;
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void migratePhysicalFiles() {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "navitool");
            File file2 = new File(Environment.getExternalStorageDirectory(), "navitool");
            if (file.exists()) {
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                moveDirectory(file, file2);
                deleteDirIfEmpty(file);
            }
        } catch (Exception unused) {
        }
    }

    private static void moveDirectory(File file, File file2) {
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file3 : listFiles) {
            File file4 = new File(file2, file3.getName());
            if (file3.isDirectory()) {
                moveDirectory(file3, file4);
                file3.delete();
            } else {
                copyFile(file3, file4);
                file3.delete();
            }
        }
    }

    public static void performMigration(Context context) {
        migratePhysicalFiles();
        fixProfilePaths(context);
    }
}
