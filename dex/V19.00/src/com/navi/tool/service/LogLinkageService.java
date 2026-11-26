package com.navi.tool.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.FileObserver;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.navi.tool.MainActivity;
import com.navi.tool.utils.LogAppProfileStorage;
import com.navi.tool.utils.LogAssistantProfileStorage;
import com.navi.tool.utils.LogMediaProfileStorage;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogLinkageService extends Service {
    public static final String ACTION_RELOAD_LOG_LINK_PROFILES = "com.navi.tool.RELOAD_LOG_LINK_PROFILES";
    public static final String ACTION_START_LOG_LINKAGE = "com.navi.tool.START_LOG_LINKAGE";
    public static final String ACTION_STOP_LOG_LINKAGE = "com.navi.tool.STOP_LOG_LINKAGE";
    private static final String CHANNEL_ID = "LogLinkageServiceChannel";
    private static final String CHANNEL_NAME = "日志联动服务";
    private static final int NOTIFICATION_ID = 1002;
    private static final String TAG = "LogLinkageService";
    private static boolean isServiceRunning;
    private FileObserver appProfileObserver;
    private FileObserver assistantProfileObserver;
    private LogLinkageManager logLinkageManager;
    private FileObserver mediaProfileObserver;
    private Handler reloadHandler;
    private final Object reloadLock = new Object();
    private Runnable reloadRunnable;
    private volatile boolean reloading;

    private Notification createNotification() {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592);
        e0.v vVar = new e0.v(this, CHANNEL_ID);
        vVar.f6327e = e0.v.c(CHANNEL_NAME);
        vVar.f6328f = e0.v.c("正在监听联动应用日志...");
        vVar.f6341s.icon = g4.o.ic_launcher_foreground;
        vVar.f6329g = activity;
        vVar.f6331i = -1;
        return vVar.a();
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 2);
        notificationChannel.setDescription("用于日志联动前台服务");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private FileObserver createObserver(File file, final String str) {
        if (file != null && file.exists() && file.isDirectory()) {
            return new FileObserver(file.getAbsolutePath(), 970) { // from class: com.navi.tool.service.LogLinkageService.1
                @Override // android.os.FileObserver
                public void onEvent(int i7, String str2) {
                    if (str2 == null || !str2.toLowerCase().endsWith(".xml") || LogLinkageService.this.reloadHandler == null || LogLinkageService.this.reloadRunnable == null || !SharedPreferencesUtils.getAppPrefs(LogLinkageService.this).getBoolean("log_link_switch_state", false)) {
                        return;
                    }
                    LogLinkageService.this.reloadHandler.removeCallbacks(LogLinkageService.this.reloadRunnable);
                    LogLinkageService.this.reloadHandler.postDelayed(LogLinkageService.this.reloadRunnable, 800L);
                }
            };
        }
        return null;
    }

    private void ensureFileObservers() {
        try {
            stopFileObservers();
            File appProfileDir = LogAppProfileStorage.getAppProfileDir(this);
            File mediaProfileDir = LogMediaProfileStorage.getMediaProfileDir(this);
            File assistantProfileDir = LogAssistantProfileStorage.getAssistantProfileDir(this);
            this.appProfileObserver = createObserver(appProfileDir, "app");
            this.mediaProfileObserver = createObserver(mediaProfileDir, "media");
            this.assistantProfileObserver = createObserver(assistantProfileDir, "assistant");
            tryStart(this.appProfileObserver);
            tryStart(this.mediaProfileObserver);
            tryStart(this.assistantProfileObserver);
        } catch (Throwable th) {
            th.getMessage();
        }
    }

    public static boolean isServiceRunning() {
        return isServiceRunning;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0() {
        synchronized (this.reloadLock) {
            try {
                if (this.reloading) {
                    return;
                }
                this.reloading = true;
                try {
                    performReloadByConfig();
                    synchronized (this.reloadLock) {
                        this.reloading = false;
                    }
                } catch (Throwable th) {
                    synchronized (this.reloadLock) {
                        this.reloading = false;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    private void performReloadByConfig() {
        boolean z6 = SharedPreferencesUtils.getAppPrefs(this).getBoolean("log_link_switch_state", false);
        if (this.logLinkageManager == null) {
            this.logLinkageManager = new LogLinkageManager(this);
        }
        if (!z6) {
            this.logLinkageManager.stop();
        } else {
            this.logLinkageManager.stop();
            this.logLinkageManager.start();
        }
    }

    private void stopFileObservers() {
        tryStop(this.appProfileObserver);
        tryStop(this.mediaProfileObserver);
        tryStop(this.assistantProfileObserver);
        this.appProfileObserver = null;
        this.mediaProfileObserver = null;
        this.assistantProfileObserver = null;
    }

    private void tryStart(FileObserver fileObserver) {
        if (fileObserver == null) {
            return;
        }
        try {
            fileObserver.startWatching();
        } catch (Throwable unused) {
        }
    }

    private void tryStop(FileObserver fileObserver) {
        if (fileObserver == null) {
            return;
        }
        try {
            fileObserver.stopWatching();
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        isServiceRunning = true;
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification());
        this.reloadHandler = new Handler(Looper.getMainLooper());
        this.reloadRunnable = new b0(2, this);
        ensureFileObservers();
        boolean z6 = SharedPreferencesUtils.getAppPrefs(this).getBoolean("log_link_switch_state", false);
        LogLinkageManager logLinkageManager = new LogLinkageManager(this);
        this.logLinkageManager = logLinkageManager;
        if (z6) {
            logLinkageManager.start();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        Runnable runnable;
        super.onDestroy();
        isServiceRunning = false;
        stopFileObservers();
        Handler handler = this.reloadHandler;
        if (handler != null && (runnable = this.reloadRunnable) != null) {
            handler.removeCallbacks(runnable);
        }
        LogLinkageManager logLinkageManager = this.logLinkageManager;
        if (logLinkageManager != null) {
            logLinkageManager.stop();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        if (intent == null) {
            return 1;
        }
        String action = intent.getAction();
        if (ACTION_START_LOG_LINKAGE.equals(action)) {
            if (this.logLinkageManager == null) {
                this.logLinkageManager = new LogLinkageManager(this);
            }
            this.logLinkageManager.start();
            ensureFileObservers();
            return 1;
        }
        if (ACTION_STOP_LOG_LINKAGE.equals(action)) {
            LogLinkageManager logLinkageManager = this.logLinkageManager;
            if (logLinkageManager == null) {
                return 1;
            }
            logLinkageManager.stop();
            return 1;
        }
        if (!ACTION_RELOAD_LOG_LINK_PROFILES.equals(action)) {
            return 1;
        }
        if (this.logLinkageManager == null) {
            this.logLinkageManager = new LogLinkageManager(this);
        }
        performReloadByConfig();
        ensureFileObservers();
        return 1;
    }
}
