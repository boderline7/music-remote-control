package com.navi.tool.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import com.navi.tool.model.StoreConfig;
import com.navi.tool.utils.StoreDownloadManager;
import com.navi.tool.utils.ZipHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import s5.a1;
import s5.h0;
import s5.o0;
import s5.r0;
import s5.x0;
import s5.y0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class StoreDownloadManager {
    public static final int ACTION_DOWNLOAD_AND_EXTRACT = 3;
    public static final int ACTION_DOWNLOAD_ONLY = 1;
    public static final int ACTION_EXTRACT_ONLY = 2;
    private static final String DOWNLOAD_DIR = "navitool/filemanager/";
    private static o0 client = new o0();

    /* renamed from: com.navi.tool.utils.StoreDownloadManager$1, reason: invalid class name */
    public class AnonymousClass1 implements s5.g {
        final /* synthetic */ StoreConfig val$config;
        final /* synthetic */ Context val$context;
        final /* synthetic */ File val$destFile;
        final /* synthetic */ boolean val$extractAfter;
        final /* synthetic */ Handler val$mainHandler;
        final /* synthetic */ Runnable val$onSuccess;

        public AnonymousClass1(Handler handler, Context context, File file, StoreConfig storeConfig, Runnable runnable, boolean z6) {
            this.val$mainHandler = handler;
            this.val$context = context;
            this.val$destFile = file;
            this.val$config = storeConfig;
            this.val$onSuccess = runnable;
            this.val$extractAfter = z6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onFailure$0(Context context, IOException iOException) {
            ToastHelper.showLong(context, "下载失败: " + iOException.getMessage());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void lambda$onResponse$1(Context context, x0 x0Var) {
            ToastHelper.showLong(context, "下载失败，状态码: " + x0Var.f8916d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$2(Context context, Runnable runnable, boolean z6, File file) {
            ToastHelper.show(context, "下载完成");
            if (runnable != null) {
                runnable.run();
            }
            if (z6) {
                StoreDownloadManager.extractZipAsync(context, file);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onResponse$3(Context context, Exception exc) {
            ToastHelper.showLong(context, "保存文件失败: " + exc.getMessage());
        }

        @Override // s5.g
        public void onFailure(s5.f fVar, IOException iOException) {
            this.val$mainHandler.post(new l(2, this.val$context, iOException));
        }

        @Override // s5.g
        public void onResponse(s5.f fVar, x0 x0Var) {
            int i7 = 0;
            if (!x0Var.B()) {
                this.val$mainHandler.post(new l(i7, this.val$context, x0Var));
                return;
            }
            try {
                a1 a1Var = x0Var.f8919g;
                a1Var.getClass();
                g6.g x6 = ((y0) a1Var).f8928c.x();
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(this.val$destFile);
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = x6.read(bArr);
                            if (read == -1) {
                                fileOutputStream.flush();
                                this.val$context.getSharedPreferences("StoreServicePrefs", 0).edit().putString("downloaded_" + this.val$config.getName(), this.val$config.getTime()).apply();
                                Handler handler = this.val$mainHandler;
                                final Context context = this.val$context;
                                final Runnable runnable = this.val$onSuccess;
                                final boolean z6 = this.val$extractAfter;
                                final File file = this.val$destFile;
                                handler.post(new Runnable() { // from class: com.navi.tool.utils.m
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        StoreDownloadManager.AnonymousClass1.lambda$onResponse$2(context, runnable, z6, file);
                                    }
                                });
                                fileOutputStream.close();
                                x6.close();
                                return;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (Exception e7) {
                this.val$mainHandler.post(new l(1, this.val$context, e7));
            }
        }
    }

    /* renamed from: com.navi.tool.utils.StoreDownloadManager$2, reason: invalid class name */
    public class AnonymousClass2 implements ZipHelper.ZipCallback {
        final /* synthetic */ Context val$context;

        public AnonymousClass2(Context context) {
            this.val$context = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onError$1(Context context, String str) {
            ToastHelper.showLong(context, str.replace("解压", "应用"));
        }

        @Override // com.navi.tool.utils.ZipHelper.ZipCallback
        public void onError(String str) {
            new Handler(Looper.getMainLooper()).post(new n(this.val$context, 0, str));
        }

        @Override // com.navi.tool.utils.ZipHelper.ZipCallback
        public void onStart() {
        }

        @Override // com.navi.tool.utils.ZipHelper.ZipCallback
        public void onSuccess() {
            new Handler(Looper.getMainLooper()).post(new o(this.val$context, 0));
        }
    }

    private static void downloadFileAsync(Context context, StoreConfig storeConfig, File file, boolean z6, Runnable runnable) {
        String downloadUrl = storeConfig.getDownloadUrl();
        ToastHelper.show(context, "开始下载...");
        Handler handler = new Handler(Looper.getMainLooper());
        r0 r0Var = new r0();
        r0Var.f(downloadUrl);
        client.a(r0Var.a()).e(new AnonymousClass1(handler, context, file, storeConfig, runnable, z6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void extractZipAsync(Context context, File file) {
        ToastHelper.show(context, "开始应用...");
        ZipHelper.unzip(context, file, Environment.getExternalStorageDirectory(), new AnonymousClass2(context));
    }

    public static void handleAction(Context context, StoreConfig storeConfig, int i7, Runnable runnable) {
        if (!hasStoragePermission(context)) {
            ToastHelper.showLong(context, "缺少读写存储权限，操作取消。请先赋予车机助手存储读写权限");
            return;
        }
        String downloadUrl = storeConfig.getDownloadUrl();
        if (downloadUrl == null || downloadUrl.isEmpty()) {
            ToastHelper.show(context, "无效的下载链接");
            return;
        }
        String substring = downloadUrl.substring(downloadUrl.lastIndexOf(47) + 1);
        File file = new File(Environment.getExternalStorageDirectory(), DOWNLOAD_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, substring);
        if (i7 != 2) {
            downloadFileAsync(context, storeConfig, file2, i7 == 3, runnable);
        } else if (file2.exists()) {
            extractZipAsync(context, file2);
        } else {
            ToastHelper.showLong(context, "未找到下载的文件，请先执行下载操作");
        }
    }

    private static boolean hasStoragePermission(Context context) {
        boolean isExternalStorageManager;
        if (Build.VERSION.SDK_INT < 30) {
            return g0.g.a(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        }
        isExternalStorageManager = Environment.isExternalStorageManager();
        return isExternalStorageManager;
    }

    public static boolean isConfigDownloaded(Context context, StoreConfig storeConfig) {
        String downloadUrl = storeConfig.getDownloadUrl();
        if (downloadUrl == null || downloadUrl.isEmpty()) {
            return false;
        }
        if (!new File(new File(Environment.getExternalStorageDirectory(), DOWNLOAD_DIR), downloadUrl.substring(downloadUrl.lastIndexOf(47) + 1)).exists()) {
            return false;
        }
        return storeConfig.getTime().equals(context.getSharedPreferences("StoreServicePrefs", 0).getString("downloaded_" + storeConfig.getName(), h0.FRAGMENT_ENCODE_SET));
    }
}
