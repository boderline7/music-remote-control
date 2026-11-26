package com.navi.tool;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.UiModeManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.w;
import androidx.lifecycle.h1;
import com.navi.tool.receiver.PackageInstallReceiver;
import com.navi.tool.receiver.ThemeChangeReceiver;
import com.navi.tool.service.GestureActionService;
import com.navi.tool.service.LogLinkageService;
import com.navi.tool.service.LogMonitorService;
import com.navi.tool.service.ThemeMonitorService;
import com.navi.tool.ui.theme.ThemeViewModel;
import com.navi.tool.utils.AccessibilityGuard;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.CrashLogManager;
import com.navi.tool.utils.SharedPreferencesUtils;
import g4.k;
import g4.l;
import g4.u;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NaviApplication extends Application {
    public static final String CHANNEL_ID = "app_detection_service";
    private static final String TAG = "NaviApplication";
    public static final String THEME_MONITOR_CHANNEL_ID = "theme_monitor_service";
    private static NaviApplication instance;
    private ThemeChangeReceiver.OnThemeChangeListener globalThemeChangeListener;
    private ThemeViewModel themeViewModel;

    private void applySavedTheme() {
        int i7 = getSharedPreferences("navi_tool_prefs", 0).getInt("theme_mode", 2);
        if (i7 == 0) {
            w.j(1);
        } else if (i7 != 1) {
            w.j(-1);
        } else {
            w.j(2);
        }
    }

    private void checkAndStartLogLinkageServiceFirst() {
        if (SharedPreferencesUtils.getAppPrefs(this).getBoolean("log_link_switch_state", false) && checkSelfPermission("android.permission.READ_LOGS") == 0) {
            try {
                startForegroundService(new Intent(this, (Class<?>) LogLinkageService.class));
            } catch (Exception unused) {
            }
        }
    }

    private void checkAndStartVoiceNavigationService() {
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        boolean isRouteTransferEnabled = SharedPreferencesUtils.isRouteTransferEnabled(this);
        String nativeAmapSelectedPackage = SharedPreferencesUtils.getNativeAmapSelectedPackage(this);
        boolean z6 = appPrefs.getBoolean("voice_navigation_switch_state", false);
        if (isRouteTransferEnabled && nativeAmapSelectedPackage != null) {
            try {
                if (!nativeAmapSelectedPackage.isEmpty()) {
                    startForegroundService(new Intent(this, (Class<?>) LogMonitorService.class));
                    Intent intent = new Intent(this, (Class<?>) LogMonitorService.class);
                    intent.setAction(LogMonitorService.ACTION_START_ROUTE_TRANSFER);
                    startForegroundService(intent);
                }
            } catch (Exception unused) {
                return;
            }
        }
        if (z6) {
            startForegroundService(new Intent(this, (Class<?>) LogMonitorService.class));
            Intent intent2 = new Intent(this, (Class<?>) LogMonitorService.class);
            intent2.setAction(LogMonitorService.ACTION_START_LOG_MONITORING);
            startForegroundService(intent2);
        }
    }

    private void createNotificationChannel(String str, String str2, String str3) {
        NotificationChannel notificationChannel = new NotificationChannel(str, str2, 2);
        notificationChannel.setDescription(str3);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotificationChannels() {
        createNotificationChannel(CHANNEL_ID, getString(u.notification_channel_name), getString(u.notification_channel_description));
        createNotificationChannel(THEME_MONITOR_CHANNEL_ID, "主题监听服务", "用于在后台监听系统主题变化");
    }

    public static NaviApplication getInstance() {
        return instance;
    }

    private void initializeThemeMonitoring() {
        ThemeChangeReceiver.setOnThemeChangeListener(new l(this));
    }

    public ThemeViewModel getThemeViewModel() {
        if (this.themeViewModel == null) {
            this.themeViewModel = (ThemeViewModel) new h1(this).a(ThemeViewModel.class);
        }
        return this.themeViewModel;
    }

    public boolean isDarkMode() {
        UiModeManager uiModeManager = (UiModeManager) getSystemService("uimode");
        return uiModeManager != null ? uiModeManager.getNightMode() == 2 : (getResources().getConfiguration().uiMode & 48) == 32;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        instance = this;
        CrashLogManager.install(this);
        AppDetectionUtils.prewarm(this);
        createNotificationChannels();
        initializeThemeMonitoring();
        PackageInstallReceiver.registerDynamic(this);
        checkAndStartLogLinkageServiceFirst();
        startThemeMonitorService();
        checkAndStartVoiceNavigationService();
        AccessibilityGuard.start(this);
        GestureActionService.ensureRunning(this);
        applySavedTheme();
        new Handler(Looper.getMainLooper()).postDelayed(new k(0), 8000L);
    }

    public void setGlobalThemeChangeListener(ThemeChangeReceiver.OnThemeChangeListener onThemeChangeListener) {
        this.globalThemeChangeListener = onThemeChangeListener;
    }

    public void startThemeMonitorService() {
        startForegroundService(new Intent(this, (Class<?>) ThemeMonitorService.class));
    }

    public void stopThemeMonitorService() {
        stopService(new Intent(this, (Class<?>) ThemeMonitorService.class));
    }
}
