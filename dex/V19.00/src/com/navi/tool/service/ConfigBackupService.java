package com.navi.tool.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import com.navi.tool.model.CloudDiskCredential;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.utils.Pan123Api;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ConfigBackupService extends Service {
    public static final String ACTION_CANCEL = "com.navi.tool.action.CONFIG_BACKUP_CANCEL";
    public static final String ACTION_PROGRESS = "com.navi.tool.action.CONFIG_BACKUP_PROGRESS";
    public static final String ACTION_START = "com.navi.tool.action.CONFIG_BACKUP_START";
    public static final String BACKUP_FOLDER_NAME = "车机助手配置备份";
    private static final String CHANNEL_ID = "config_backup_channel";
    private static final String CHANNEL_NAME = "车机配置备份";
    public static final String EXTRA_CREDENTIAL_INDEX = "credential_index";
    public static final String EXTRA_FILE_NAME = "file_name";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_PERCENT = "percent";
    public static final String EXTRA_STAGE = "stage";
    private static final int NOTIFICATION_ID = 2101;
    public static final String STAGE_CANCELLED = "cancelled";
    public static final String STAGE_DONE = "done";
    public static final String STAGE_ERROR = "error";
    public static final String STAGE_SCANNING = "scanning";
    public static final String STAGE_UPLOADING = "uploading";
    public static final String STAGE_ZIPPING = "zipping";
    private static final String TAG = "ConfigBackupService";
    private volatile boolean cancelled = false;
    private String currentStage = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastMessage = s5.h0.FRAGMENT_ENCODE_SET;
    private int lastPercent = -1;
    private NotificationManager notificationManager;
    private File tempZip;
    private UploadControlImpl uploadControl;
    private ExecutorService worker;
    private static final String[] INCLUDE_DIRS = {"carlogo", "icon", "logs", "mp3", "oilrecord", "profile", "scenefile", "wallpaper", "wallpaper_video"};
    public static volatile boolean running = false;

    public class UploadControlImpl implements Pan123Api.UploadControl {
        private volatile s5.f call;
        private volatile boolean cancelRequested;

        private UploadControlImpl() {
            this.cancelRequested = false;
        }

        public void cancel() {
            this.cancelRequested = true;
            s5.f fVar = this.call;
            if (fVar != null) {
                ((x5.j) fVar).d();
            }
        }

        @Override // com.navi.tool.utils.Pan123Api.UploadControl
        public boolean isCancelled() {
            return this.cancelRequested || ConfigBackupService.this.cancelled;
        }

        @Override // com.navi.tool.utils.Pan123Api.UploadControl
        public void onCall(s5.f fVar) {
            this.call = fVar;
            if ((this.cancelRequested || ConfigBackupService.this.cancelled) && fVar != null) {
                ((x5.j) fVar).d();
            }
        }
    }

    public static class ZipPlan {
        final List<File> dirs;
        final List<File> files;

        private ZipPlan() {
            this.dirs = new ArrayList();
            this.files = new ArrayList();
        }
    }

    private Notification buildFinalNotification(String str, String str2) {
        e0.v vVar = new e0.v(this, CHANNEL_ID);
        vVar.f6341s.icon = g4.o.ic_file_zip;
        vVar.f6327e = e0.v.c(str);
        vVar.f6328f = e0.v.c(str2);
        vVar.f6329g = contentIntent();
        vVar.d(16, true);
        vVar.d(2, false);
        vVar.f6331i = -1;
        e0.u uVar = new e0.u();
        uVar.f6322b = e0.v.c(str2);
        vVar.e(uVar);
        return vVar.a();
    }

    private Notification buildProgressNotification(String str, int i7, boolean z6) {
        e0.v vVar = new e0.v(this, CHANNEL_ID);
        vVar.f6341s.icon = g4.o.ic_file_zip;
        vVar.f6327e = e0.v.c(CHANNEL_NAME);
        vVar.f6328f = e0.v.c(str);
        vVar.f6329g = contentIntent();
        vVar.d(8, true);
        vVar.d(2, true);
        vVar.f6331i = -1;
        vVar.f6334l = 100;
        vVar.f6335m = i7;
        vVar.f6336n = z6;
        vVar.f6324b.add(new e0.t(cancelIntent()));
        return vVar.a();
    }

    private void cancelBackup() {
        this.cancelled = true;
        UploadControlImpl uploadControlImpl = this.uploadControl;
        if (uploadControlImpl != null) {
            uploadControlImpl.cancel();
        }
        if (!running) {
            finishWithNotification("备份已取消", "未上传任何文件");
            stopSelf();
        } else {
            NotificationManager notificationManager = this.notificationManager;
            if (notificationManager != null) {
                notificationManager.notify(NOTIFICATION_ID, buildProgressNotification("正在取消...", 0, true));
            }
        }
    }

    private PendingIntent cancelIntent() {
        Intent intent = new Intent(this, (Class<?>) ConfigBackupService.class);
        intent.setAction(ACTION_CANCEL);
        return PendingIntent.getService(this, 1, intent, 201326592);
    }

    private PendingIntent contentIntent() {
        Intent intent = new Intent(this, (Class<?>) FileManagerActivity.class);
        intent.addFlags(805306368);
        return PendingIntent.getActivity(this, 0, intent, 201326592);
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 2);
        notificationChannel.setDescription("车机助手配置备份进度");
        notificationChannel.setShowBadge(false);
        this.notificationManager.createNotificationChannel(notificationChannel);
    }

    private void createZip(File file, ZipPlan zipPlan, String str) {
        int i7 = 1;
        int size = zipPlan.files.size() + zipPlan.dirs.size() + 1;
        byte[] bArr = new byte[8192];
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(this.tempZip)));
        try {
            zipOutputStream.putNextEntry(new ZipEntry("navitool/"));
            zipOutputStream.closeEntry();
            reportZipProgress(1, size, str);
            for (File file2 : zipPlan.dirs) {
                ensureNotCancelled();
                zipOutputStream.putNextEntry(new ZipEntry(entryName(file, file2) + "/"));
                zipOutputStream.closeEntry();
                i7++;
                reportZipProgress(i7, size, str);
            }
            for (File file3 : zipPlan.files) {
                ensureNotCancelled();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file3);
                    zipOutputStream.putNextEntry(new ZipEntry(entryName(file, file3)));
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                    while (true) {
                        try {
                            int read = bufferedInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            ensureNotCancelled();
                            zipOutputStream.write(bArr, 0, read);
                        } finally {
                        }
                    }
                    bufferedInputStream.close();
                    zipOutputStream.closeEntry();
                    i7++;
                    reportZipProgress(i7, size, str);
                } catch (Exception unused) {
                    file3.getAbsolutePath();
                    i7++;
                    reportZipProgress(i7, size, str);
                }
            }
            zipOutputStream.close();
        } catch (Throwable th) {
            try {
                zipOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void deleteTempZip() {
        try {
            File file = this.tempZip;
            if (file != null && file.exists()) {
                this.tempZip.delete();
            }
        } catch (Exception unused) {
        }
        this.tempZip = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doBackup, reason: merged with bridge method [inline-methods] */
    public void lambda$onStartCommand$1(int i7) {
        List<CloudDiskCredential> loadAll;
        int i8 = 0;
        try {
            try {
                notifyStage(STAGE_SCANNING, 0, "正在检查网盘登录状态...", s5.h0.FRAGMENT_ENCODE_SET);
                loadAll = CloudDiskCredential.loadAll(this);
            } catch (Exception e7) {
                if (this.cancelled) {
                    notifyStage(STAGE_CANCELLED, 0, "已取消备份", s5.h0.FRAGMENT_ENCODE_SET);
                    finishWithNotification("备份已取消", "未上传任何文件");
                } else {
                    String message = e7.getMessage() != null ? e7.getMessage() : "备份失败";
                    notifyStage(STAGE_ERROR, 0, message, s5.h0.FRAGMENT_ENCODE_SET);
                    finishWithNotification("备份失败", message);
                }
                deleteTempZip();
                this.uploadControl = null;
                running = false;
            }
            if (i7 < 0 || i7 >= loadAll.size()) {
                throw new Exception("网盘账号不存在，请重新登录123网盘");
            }
            CloudDiskCredential cloudDiskCredential = loadAll.get(i7);
            String str = cloudDiskCredential.token;
            if (str == null || str.trim().isEmpty()) {
                throw new Exception("123网盘登录已失效，请重新登录");
            }
            Pan123Api.listFiles(cloudDiskCredential, "0", 1);
            ensureNotCancelled();
            File file = new File(Environment.getExternalStorageDirectory(), "navitool");
            if (!file.isDirectory()) {
                throw new Exception("未找到 navitool 文件夹");
            }
            notifyStage(STAGE_SCANNING, 0, "正在扫描文件...", s5.h0.FRAGMENT_ENCODE_SET);
            ZipPlan scan = scan(file);
            if (scan.dirs.isEmpty() && scan.files.isEmpty()) {
                throw new Exception("列表中的目录都不存在，没有可备份的内容");
            }
            ensureNotCancelled();
            String str2 = "navitool_" + new SimpleDateFormat("yyyyMMddHHmm", Locale.US).format(new Date()) + ".zip";
            File externalCacheDir = getExternalCacheDir();
            if (externalCacheDir == null) {
                externalCacheDir = getCacheDir();
            }
            File file2 = new File(externalCacheDir, "backup");
            if (!file2.exists() && !file2.mkdirs()) {
                throw new Exception("无法创建临时备份目录");
            }
            File[] listFiles = file2.listFiles(new f(i8));
            if (listFiles != null) {
                for (File file3 : listFiles) {
                    file3.delete();
                }
            }
            this.tempZip = new File(file2, str2);
            createZip(file, scan, str2);
            ensureNotCancelled();
            notifyStage(STAGE_UPLOADING, 0, "正在查找备份文件夹...", str2);
            String findOrCreateBackupFolder = findOrCreateBackupFolder(cloudDiskCredential);
            ensureNotCancelled();
            notifyStage(STAGE_UPLOADING, 0, "正在计算文件指纹...", str2);
            Pan123Api.UploadInfo uploadUrl = Pan123Api.getUploadUrl(cloudDiskCredential, findOrCreateBackupFolder, str2, this.tempZip.length(), Pan123Api.getFileMd5(this.tempZip));
            ensureNotCancelled();
            if (uploadUrl.reuse) {
                notifyStage(STAGE_UPLOADING, 100, "网盘已存在相同文件", str2);
            } else {
                UploadControlImpl uploadControlImpl = new UploadControlImpl();
                this.uploadControl = uploadControlImpl;
                Pan123Api.uploadFileData(cloudDiskCredential, uploadUrl, this.tempZip, new g(this, new int[]{-1}, str2), uploadControlImpl);
            }
            ensureNotCancelled();
            notifyStage(STAGE_DONE, 100, "备份完成", str2);
            finishWithNotification("备份完成", str2 + " 已上传到“" + BACKUP_FOLDER_NAME + "”");
            deleteTempZip();
            this.uploadControl = null;
            running = false;
            stopForeground(2);
            stopSelf();
        } catch (Throwable th) {
            deleteTempZip();
            this.uploadControl = null;
            running = false;
            stopForeground(2);
            stopSelf();
            throw th;
        }
    }

    private void ensureNotCancelled() {
        if (this.cancelled) {
            throw new IOException(STAGE_CANCELLED);
        }
    }

    private String entryName(File file, File file2) {
        String substring = file2.getAbsolutePath().substring(file.getAbsolutePath().length());
        if (substring.startsWith(File.separator)) {
            substring = substring.substring(1);
        }
        return "navitool/" + substring.replace(File.separatorChar, '/');
    }

    private String findOrCreateBackupFolder(CloudDiskCredential cloudDiskCredential) {
        for (int i7 = 1; i7 <= 5; i7++) {
            List<Pan123Api.CloudFileInfo> listFiles = Pan123Api.listFiles(cloudDiskCredential, "0", i7);
            for (Pan123Api.CloudFileInfo cloudFileInfo : listFiles) {
                if (cloudFileInfo.isDir && BACKUP_FOLDER_NAME.equals(cloudFileInfo.name)) {
                    return cloudFileInfo.fileId;
                }
            }
            if (listFiles.size() < 100) {
                break;
            }
        }
        return Pan123Api.createFolder(cloudDiskCredential, "0", BACKUP_FOLDER_NAME).fileId;
    }

    private void finishWithNotification(String str, String str2) {
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, buildFinalNotification(str, str2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$doBackup$2(File file, String str) {
        return str.startsWith("navitool_") && str.endsWith(".zip");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$doBackup$3(int[] iArr, String str, int i7) {
        int max = Math.max(0, Math.min(100, i7));
        if (max == iArr[0]) {
            return;
        }
        iArr[0] = max;
        notifyStage(STAGE_UPLOADING, max, "正在上传配置备份", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$onCreate$0(Runnable runnable) {
        return new Thread(runnable, "config-backup");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$walk$4(File file, File file2) {
        return file.getName().compareToIgnoreCase(file2.getName());
    }

    private void notifyStage(String str, int i7, String str2, String str3) {
        if (!(!str.equals(this.currentStage)) && i7 == this.lastPercent) {
            if (str2.equals(this.lastMessage)) {
                return;
            }
            if (!STAGE_SCANNING.equals(str) && (!STAGE_UPLOADING.equals(str) || i7 != 0)) {
                return;
            }
        }
        this.currentStage = str;
        this.lastPercent = i7;
        this.lastMessage = str2;
        if (this.notificationManager != null) {
            this.notificationManager.notify(NOTIFICATION_ID, buildProgressNotification(STAGE_DONE.equals(str) ? "备份完成" : str2, i7, STAGE_SCANNING.equals(str)));
        }
        Intent intent = new Intent(ACTION_PROGRESS);
        intent.setPackage(getPackageName());
        intent.putExtra(EXTRA_STAGE, str);
        intent.putExtra(EXTRA_PERCENT, i7);
        intent.putExtra(EXTRA_MESSAGE, str2);
        intent.putExtra(EXTRA_FILE_NAME, str3);
        sendBroadcast(intent);
    }

    private void reportZipProgress(int i7, int i8, String str) {
        notifyStage(STAGE_ZIPPING, i8 <= 0 ? 0 : (int) ((i7 * 100) / i8), "正在打包 " + i7 + "/" + i8, str);
    }

    private ZipPlan scan(File file) {
        ZipPlan zipPlan = new ZipPlan();
        for (String str : INCLUDE_DIRS) {
            File file2 = new File(file, str);
            if (file2.isDirectory()) {
                walk(file, file2, zipPlan);
            }
        }
        return zipPlan;
    }

    private void walk(File file, File file2, ZipPlan zipPlan) {
        ensureNotCancelled();
        if (!file2.isDirectory()) {
            if (file2.isFile()) {
                zipPlan.files.add(file2);
                return;
            }
            return;
        }
        zipPlan.dirs.add(file2);
        File[] listFiles = file2.listFiles();
        if (listFiles != null) {
            Arrays.sort(listFiles, new m0.a(3));
            for (File file3 : listFiles) {
                walk(file, file3, zipPlan);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.worker = Executors.newSingleThreadExecutor(new p4.d(2));
        this.notificationManager = (NotificationManager) getSystemService("notification");
        createNotificationChannel();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.cancelled = true;
        UploadControlImpl uploadControlImpl = this.uploadControl;
        if (uploadControlImpl != null) {
            uploadControlImpl.cancel();
        }
        ExecutorService executorService = this.worker;
        if (executorService != null) {
            executorService.shutdown();
        }
        deleteTempZip();
        running = false;
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        if (intent == null) {
            stopSelf();
            return 2;
        }
        String action = intent.getAction();
        if (ACTION_CANCEL.equals(action)) {
            cancelBackup();
            return 2;
        }
        if (!ACTION_START.equals(action)) {
            stopSelf();
            return 2;
        }
        if (running) {
            return 2;
        }
        startForeground(NOTIFICATION_ID, buildProgressNotification("正在准备备份...", 0, true));
        running = true;
        this.cancelled = false;
        this.currentStage = STAGE_SCANNING;
        this.lastMessage = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastPercent = -1;
        this.worker.execute(new h0.n(intent.getIntExtra(EXTRA_CREDENTIAL_INDEX, -1), 4, this));
        return 2;
    }
}
