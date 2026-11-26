package com.navi.tool.service;

import android.R;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.Display;
import com.navi.tool.MainActivity;
import com.navi.tool.NaviApplication;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.broadcast.BrightnessChangeReceiver;
import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.protocol.AmapBroadcastReceiver;
import com.navi.tool.protocol.AutoProtocolManager;
import com.navi.tool.protocol.DestinationWeatherBroadcastReceiver;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;
import com.navi.tool.receiver.ThemeChangeReceiver;
import com.navi.tool.ui.theme.ThemeViewModel;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.MegaImeHelper;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.UidSyncManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ThemeMonitorService extends Service {
    private static final String CHANNEL_ID = "theme_monitor_service";
    private static final String KEY_STR_WAKE_ENABLED = "str_wake_auto_start_enabled";
    private static final int NOTIFICATION_ID = 1007;
    private static final long STR_WAKE_COOLDOWN_MS = 120000;
    private static final long STR_WAKE_GAP_MS = 90000;
    private static final long STR_WAKE_HEARTBEAT_MS = 30000;
    private static final long STR_WAKE_LOG_FRESH_MS = 60000;
    private static final long STR_WAKE_LOOKBACK_MS = 30000;
    private static final long STR_WAKE_PENDING_POLL_MS = 2000;
    private static final long STR_WAKE_PENDING_TIMEOUT_MS = 60000;
    private static final String TAG = "ThemeMonitorService";
    private Auto3DBroadcastReceiver auto3DBroadcastReceiver;
    private BrightnessChangeReceiver brightnessChangeReceiver;
    private BroadcastReceiver screenOnReceiver;
    private Thread strWakeLogThread;
    private Process strWakeLogcatProcess;
    private SharedPreferences.OnSharedPreferenceChangeListener strWakePrefListener;
    private ThemeChangeReceiver themeChangeReceiver;
    private ThemeViewModel themeViewModel;
    private UidSyncManager uidSyncManager;
    private boolean isThemeReceiverRegistered = false;
    private boolean isAuto3DReceiverRegistered = false;
    private boolean isAmapReceiverRegistered = false;
    private boolean isDestinationWeatherReceiverRegistered = false;
    private boolean isBrightnessReceiverRegistered = false;
    private boolean isScreenOnReceiverRegistered = false;
    private final Handler strWakeHandler = new Handler(Looper.getMainLooper());
    private boolean strWakeHeartbeatRunning = false;
    private boolean strWakePending = false;
    private long strWakeLastElapsed = 0;
    private long strWakePendingSince = 0;
    private long strWakePendingGap = 0;
    private long strWakeLastTriggerAt = 0;
    private long lastScreenOnEventAt = 0;
    private final Runnable strWakeHeartbeat = new v0(this, 5);
    private volatile boolean isStrWakeLogMonitoring = false;
    private long lastStrWakeLogTriggerAt = 0;

    private Notification createNotification() {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592);
        e0.v vVar = new e0.v(this, "theme_monitor_service");
        vVar.f6327e = e0.v.c("主题监听服务");
        vVar.f6328f = e0.v.c("正在监听系统主题变化");
        vVar.f6341s.icon = R.drawable.ic_menu_info_details;
        vVar.f6329g = activity;
        vVar.f6331i = -1;
        return vVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStrWakeHeartbeat() {
        if (!isStrWakeAutoStartReady()) {
            stopStrWakeHeartbeat();
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j7 = this.strWakeLastElapsed;
        long j8 = j7 == 0 ? 0L : elapsedRealtime - j7;
        this.strWakeLastElapsed = elapsedRealtime;
        if (!this.strWakePending && j8 >= STR_WAKE_GAP_MS) {
            this.strWakePending = true;
            this.strWakePendingSince = elapsedRealtime;
            this.strWakePendingGap = j8;
        }
        if (!this.strWakePending) {
            this.strWakeHandler.postDelayed(this.strWakeHeartbeat, 30000L);
            return;
        }
        boolean isInteractiveForStrWake = isInteractiveForStrWake();
        boolean isDisplayOnForStrWake = isDisplayOnForStrWake();
        if (!isInteractiveForStrWake && !isDisplayOnForStrWake) {
            if (elapsedRealtime - this.strWakePendingSince < t1.f.T_MS_MINUTE) {
                this.strWakeHandler.postDelayed(this.strWakeHeartbeat, STR_WAKE_PENDING_POLL_MS);
                return;
            }
            recordStrWakeProbe(this.strWakePendingGap, false, false, false);
            this.strWakePending = false;
            this.strWakePendingSince = 0L;
            this.strWakeHandler.postDelayed(this.strWakeHeartbeat, 30000L);
            return;
        }
        this.strWakePending = false;
        this.strWakePendingSince = 0L;
        if (elapsedRealtime - this.lastScreenOnEventAt < 10000) {
            this.strWakeHandler.postDelayed(this.strWakeHeartbeat, 30000L);
            return;
        }
        if (elapsedRealtime - this.strWakeLastTriggerAt >= STR_WAKE_COOLDOWN_MS) {
            this.strWakeLastTriggerAt = elapsedRealtime;
            SharedPreferencesUtils.getAppPrefs(this).edit().putString("str_wake_probe_source", "heartbeat").apply();
            recordStrWakeProbe(this.strWakePendingGap, isInteractiveForStrWake, isDisplayOnForStrWake, true);
            startScreenOnAutoStart();
            MegaImeHelper.scheduleRestore(this);
        }
        this.strWakeHandler.postDelayed(this.strWakeHeartbeat, 30000L);
    }

    private void initAmapBroadcast() {
        if (SharedPreferencesUtils.isCustomAmapBroadcastEnabled(this)) {
            AmapBroadcastConstants.setAmapBroadcast(SharedPreferencesUtils.getCustomAmapBroadcastValue(this));
        } else {
            AmapBroadcastConstants.resetAmapBroadcast();
        }
    }

    private void initializeAutoProtocolManager() {
        new Thread(new v0(this, 1)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeDashboardIfNeeded() {
        DisplayManager displayManager;
        int i7;
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        if (!appPrefs.getBoolean("dashboard_enabled", false) || (displayManager = (DisplayManager) getSystemService("display")) == null) {
            return;
        }
        Display[] displays = displayManager.getDisplays();
        int length = displays.length;
        int i8 = 0;
        while (true) {
            if (i8 >= length) {
                i7 = -1;
                break;
            }
            Display display = displays[i8];
            if (display.getDisplayId() != 0) {
                i7 = display.getDisplayId();
                break;
            }
            i8++;
        }
        if (i7 != -1) {
            boolean z6 = appPrefs.getBoolean("lane_lines_enabled", false);
            boolean z7 = appPrefs.getBoolean("navigation_eta_enabled", false);
            if (Settings.canDrawOverlays(this)) {
                int i9 = appPrefs.getInt("lane_lines_x", 0);
                int i10 = appPrefs.getInt("lane_lines_y", 0);
                int i11 = appPrefs.getInt("navigation_eta_x", 0);
                int i12 = appPrefs.getInt("navigation_eta_y", 0);
                boolean z8 = appPrefs.getBoolean("navigation_eta_top_info_enabled", false);
                if (z6) {
                    try {
                        Intent intent = new Intent(this, (Class<?>) FloatingWindowService.class);
                        intent.putExtra("action", "create_lane_lines");
                        intent.putExtra("display_id", i7);
                        intent.putExtra("x", i9);
                        intent.putExtra("y", i10);
                        startService(intent);
                    } catch (Exception unused) {
                    }
                }
                if (z7) {
                    try {
                        Intent intent2 = new Intent(this, (Class<?>) FloatingWindowService.class);
                        intent2.putExtra("action", "create_navigation_eta");
                        intent2.putExtra("display_id", i7);
                        intent2.putExtra("x", i11);
                        intent2.putExtra("y", i12);
                        intent2.putExtra("show_top_info", z8);
                        startService(intent2);
                    } catch (Exception unused2) {
                    }
                }
            }
        }
    }

    private boolean isDisplayOnForStrWake() {
        try {
            DisplayManager displayManager = (DisplayManager) getSystemService("display");
            Display display = displayManager == null ? null : displayManager.getDisplay(0);
            if (display != null) {
                return display.getState() == 2;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean isInteractiveForStrWake() {
        try {
            PowerManager powerManager = (PowerManager) getSystemService("power");
            if (powerManager != null) {
                return powerManager.isInteractive();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean isStrWakeAutoStartReady() {
        return SharedPreferencesUtils.isStrWakeAutoStartEnabled(this) && SharedPreferencesUtils.isAutoStartEnabled(this) && SharedPreferencesUtils.isScreenOnAutoStartEnabled(this);
    }

    private boolean isStrWakeLogLineFresh(String str) {
        if (str == null) {
            return false;
        }
        try {
            if (str.length() >= 18 && str.charAt(2) == '-' && str.charAt(5) == ' ') {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);
                simpleDateFormat.setLenient(false);
                Date parse = simpleDateFormat.parse(str.substring(0, 18));
                if (parse == null) {
                    return false;
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(parse);
                calendar.set(1, Calendar.getInstance().get(1));
                return Math.abs(System.currentTimeMillis() - calendar.getTimeInMillis()) <= t1.f.T_MS_MINUTE;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeAutoProtocolManager$1() {
        List<String> autoNaviPackages = AppDetectionUtils.getAutoNaviPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : autoNaviPackages) {
            if (SharedPreferencesUtils.isTtsAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        AutoProtocolManager.getInstance().initializeMultiple(this, arrayList);
        arrayList.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupStrWakeLogMonitoring$2(SharedPreferences sharedPreferences, String str) {
        if (KEY_STR_WAKE_ENABLED.equals(str)) {
            if (SharedPreferencesUtils.isStrWakeAutoStartEnabled(this)) {
                startStrWakeLogMonitoring();
            } else {
                stopStrWakeLogMonitoring();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startAmapBroadcastService$0(Intent intent) {
        try {
            try {
                startService(intent);
            } catch (Throwable unused) {
                startForegroundService(intent);
            }
        } catch (Throwable unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startStrWakeLogMonitoring$3() {
        Process process;
        String readLine;
        try {
            this.strWakeLogcatProcess = Runtime.getRuntime().exec(new String[]{"logcat", "-T", new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US).format(new Date(System.currentTimeMillis() - 30000)), "-v", "time", "*:S", "CAR.POWER:V"});
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.strWakeLogcatProcess.getInputStream()));
            while (this.isStrWakeLogMonitoring && (readLine = bufferedReader.readLine()) != null) {
                if (isStrWakeLogLineFresh(readLine) && (readLine.contains("SUSPEND_EXIT") || readLine.contains("Resuming after suspending"))) {
                    triggerStrWakeLogAutoStart();
                }
            }
            this.isStrWakeLogMonitoring = false;
            process = this.strWakeLogcatProcess;
            if (process == null) {
                return;
            }
        } catch (Throwable unused) {
            this.isStrWakeLogMonitoring = false;
            process = this.strWakeLogcatProcess;
            if (process == null) {
                return;
            }
        }
        try {
            process.destroy();
        } catch (Throwable unused2) {
        }
        this.strWakeLogcatProcess = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$triggerStrWakeLogAutoStart$4() {
        try {
            if (isStrWakeAutoStartReady()) {
                SharedPreferencesUtils.getAppPrefs(this).edit().putString("str_wake_probe_source", "logcat").putBoolean("str_wake_probe_triggered", true).putLong("str_wake_probe_at", System.currentTimeMillis()).apply();
                startScreenOnAutoStart();
                MegaImeHelper.scheduleRestore(this);
                AtomicBoolean atomicBoolean = TirePollingService.f4725g;
                if (o4.z.e(this)) {
                    TirePollingService.d(this);
                }
            }
        } catch (Throwable unused) {
        }
    }

    private void recordStrWakeProbe(long j7, boolean z6, boolean z7, boolean z8) {
        try {
            SharedPreferencesUtils.getAppPrefs(this).edit().putLong("str_wake_probe_gap_ms", j7).putBoolean("str_wake_probe_interactive", z6).putBoolean("str_wake_probe_display_on", z7).putBoolean("str_wake_probe_triggered", z8).putLong("str_wake_probe_at", System.currentTimeMillis()).apply();
        } catch (Throwable unused) {
        }
    }

    private void registerAmapReceiver() {
        if (this.isAmapReceiverRegistered) {
            return;
        }
        AmapBroadcastReceiver.getInstance().unregisterBroadcast();
        AmapBroadcastReceiver.getInstance().initBroadcastForService(this);
        this.isAmapReceiverRegistered = true;
        initializeAutoProtocolManager();
    }

    private void registerAuto3DReceiver() {
        if (this.isAuto3DReceiverRegistered || this.auto3DBroadcastReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter(AmapBroadcastConstants.getAmapBroadcast());
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.auto3DBroadcastReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.auto3DBroadcastReceiver, intentFilter);
        }
        this.isAuto3DReceiverRegistered = true;
    }

    private void registerBrightnessReceiver() {
        if (this.isBrightnessReceiverRegistered || this.brightnessChangeReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.brightnessChangeReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.brightnessChangeReceiver, intentFilter);
        }
        this.isBrightnessReceiverRegistered = true;
    }

    private void registerDestinationWeatherReceiver() {
        if (this.isDestinationWeatherReceiverRegistered) {
            return;
        }
        DestinationWeatherBroadcastReceiver.getInstance().initBroadcastForService(this);
        this.isDestinationWeatherReceiverRegistered = true;
    }

    private void registerScreenOnReceiver() {
        if (this.isScreenOnReceiverRegistered) {
            return;
        }
        this.screenOnReceiver = new BroadcastReceiver() { // from class: com.navi.tool.service.ThemeMonitorService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                boolean equals = "android.intent.action.USER_PRESENT".equals(action);
                if ("android.intent.action.SCREEN_ON".equals(action) || equals) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (!equals || elapsedRealtime - ThemeMonitorService.this.lastScreenOnEventAt >= 10000) {
                        if (!equals) {
                            ThemeMonitorService.this.lastScreenOnEventAt = elapsedRealtime;
                        }
                        MegaImeHelper.scheduleRestore(context);
                        AtomicBoolean atomicBoolean = TirePollingService.f4725g;
                        if (context != null && o4.z.e(context)) {
                            TirePollingService.d(context);
                        }
                        if (SharedPreferencesUtils.isScreenOnAutoStartEnabled(context)) {
                            ThemeMonitorService.this.startScreenOnAutoStart();
                        }
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        registerReceiver(this.screenOnReceiver, intentFilter);
        this.isScreenOnReceiverRegistered = true;
    }

    private void registerThemeReceiver() {
        if (this.isThemeReceiverRegistered || this.themeChangeReceiver == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        intentFilter.addAction("android.app.action.NIGHT_MODE_CHANGED");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.themeChangeReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.themeChangeReceiver, intentFilter);
        }
        this.isThemeReceiverRegistered = true;
    }

    public static void setThemeChangeListener(ThemeChangeReceiver.OnThemeChangeListener onThemeChangeListener) {
        ThemeChangeReceiver.setOnThemeChangeListener(onThemeChangeListener);
    }

    private void setupStrWakeLogMonitoring() {
        try {
            this.strWakePrefListener = new p0(this, 1);
            SharedPreferencesUtils.getAppPrefs(this).registerOnSharedPreferenceChangeListener(this.strWakePrefListener);
            startStrWakeLogMonitoring();
        } catch (Throwable unused) {
        }
    }

    private void startAmapBroadcastService() {
        new Handler(Looper.getMainLooper()).postDelayed(new l0(13, this, new Intent(this, (Class<?>) AmapBroadcastService.class)), 1500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScreenOnAutoStart() {
        try {
            Intent intent = new Intent(this, (Class<?>) AutoStartService.class);
            intent.setAction(AutoStartService.ACTION_SCREEN_ON_AUTO_START);
            startService(intent);
        } catch (Exception unused) {
        }
    }

    private void startStrWakeHeartbeat() {
        if (this.strWakeHeartbeatRunning) {
            return;
        }
        this.strWakeHeartbeatRunning = true;
        this.strWakePending = false;
        this.strWakeLastElapsed = SystemClock.elapsedRealtime();
        this.strWakeHandler.removeCallbacks(this.strWakeHeartbeat);
        this.strWakeHandler.postDelayed(this.strWakeHeartbeat, 30000L);
    }

    private void startStrWakeLogMonitoring() {
        if (SharedPreferencesUtils.isStrWakeAutoStartEnabled(this) && !this.isStrWakeLogMonitoring) {
            this.isStrWakeLogMonitoring = true;
            Thread thread = new Thread(new v0(this, 0), "StrWakeLogcat");
            this.strWakeLogThread = thread;
            thread.start();
        }
    }

    private void stopStrWakeHeartbeat() {
        this.strWakeHeartbeatRunning = false;
        this.strWakePending = false;
        this.strWakeLastElapsed = 0L;
        this.strWakePendingSince = 0L;
        this.strWakeHandler.removeCallbacks(this.strWakeHeartbeat);
    }

    private void stopStrWakeLogMonitoring() {
        this.isStrWakeLogMonitoring = false;
        Process process = this.strWakeLogcatProcess;
        if (process != null) {
            try {
                process.destroy();
            } catch (Throwable unused) {
            }
            this.strWakeLogcatProcess = null;
        }
        this.strWakeLogThread = null;
    }

    private void triggerStrWakeLogAutoStart() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.lastStrWakeLogTriggerAt < 10000) {
            return;
        }
        this.lastStrWakeLogTriggerAt = elapsedRealtime;
        this.strWakeHandler.postDelayed(new v0(this, 4), PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS);
    }

    private void unregisterAmapReceiver() {
        if (this.isAmapReceiverRegistered) {
            AmapBroadcastReceiver.getInstance().unregisterBroadcast();
            this.isAmapReceiverRegistered = false;
        }
    }

    private void unregisterAuto3DReceiver() {
        Auto3DBroadcastReceiver auto3DBroadcastReceiver;
        if (!this.isAuto3DReceiverRegistered || (auto3DBroadcastReceiver = this.auto3DBroadcastReceiver) == null) {
            return;
        }
        unregisterReceiver(auto3DBroadcastReceiver);
        this.isAuto3DReceiverRegistered = false;
    }

    private void unregisterBrightnessReceiver() {
        BrightnessChangeReceiver brightnessChangeReceiver;
        if (!this.isBrightnessReceiverRegistered || (brightnessChangeReceiver = this.brightnessChangeReceiver) == null) {
            return;
        }
        unregisterReceiver(brightnessChangeReceiver);
        this.isBrightnessReceiverRegistered = false;
    }

    private void unregisterDestinationWeatherReceiver() {
        if (this.isDestinationWeatherReceiverRegistered) {
            DestinationWeatherBroadcastReceiver.getInstance().unregisterBroadcast();
            this.isDestinationWeatherReceiverRegistered = false;
        }
    }

    private void unregisterScreenOnReceiver() {
        BroadcastReceiver broadcastReceiver;
        if (!this.isScreenOnReceiverRegistered || (broadcastReceiver = this.screenOnReceiver) == null) {
            return;
        }
        unregisterReceiver(broadcastReceiver);
        this.isScreenOnReceiverRegistered = false;
        this.screenOnReceiver = null;
    }

    private void unregisterThemeReceiver() {
        ThemeChangeReceiver themeChangeReceiver;
        if (!this.isThemeReceiverRegistered || (themeChangeReceiver = this.themeChangeReceiver) == null) {
            return;
        }
        unregisterReceiver(themeChangeReceiver);
        this.isThemeReceiverRegistered = false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        initAmapBroadcast();
        this.themeChangeReceiver = new ThemeChangeReceiver();
        this.auto3DBroadcastReceiver = new Auto3DBroadcastReceiver();
        BrightnessChangeReceiver brightnessChangeReceiver = new BrightnessChangeReceiver();
        brightnessChangeReceiver.f4684d = -1;
        this.brightnessChangeReceiver = brightnessChangeReceiver;
        ThemeViewModel themeViewModel = ((NaviApplication) getApplication()).getThemeViewModel();
        this.themeViewModel = themeViewModel;
        this.auto3DBroadcastReceiver.setThemeViewModel(themeViewModel);
        this.brightnessChangeReceiver.f4685e = this.themeViewModel;
        new Thread(new v0(this, 3)).start();
        MegaImeHelper.registerObserver(this);
        setupStrWakeLogMonitoring();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterThemeReceiver();
        unregisterAuto3DReceiver();
        unregisterAmapReceiver();
        unregisterDestinationWeatherReceiver();
        unregisterBrightnessReceiver();
        unregisterScreenOnReceiver();
        stopStrWakeHeartbeat();
        stopStrWakeLogMonitoring();
        if (this.strWakePrefListener != null) {
            try {
                SharedPreferencesUtils.getAppPrefs(this).unregisterOnSharedPreferenceChangeListener(this.strWakePrefListener);
            } catch (Throwable unused) {
            }
            this.strWakePrefListener = null;
        }
        MegaImeHelper.unregisterObserver(this);
        UidSyncManager uidSyncManager = this.uidSyncManager;
        if (uidSyncManager != null) {
            uidSyncManager.stop();
            this.uidSyncManager = null;
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        startForeground(NOTIFICATION_ID, createNotification());
        startAmapBroadcastService();
        registerThemeReceiver();
        ThemeChangeReceiver themeChangeReceiver = this.themeChangeReceiver;
        if (themeChangeReceiver != null) {
            themeChangeReceiver.checkThemeChange(this);
        }
        registerAuto3DReceiver();
        registerAmapReceiver();
        registerDestinationWeatherReceiver();
        registerBrightnessReceiver();
        registerScreenOnReceiver();
        if (this.brightnessChangeReceiver != null && SharedPreferencesUtils.isAutoBrightnessEnabled(this)) {
            this.brightnessChangeReceiver.c(this);
        }
        BrightnessChangeReceiver brightnessChangeReceiver = this.brightnessChangeReceiver;
        if (brightnessChangeReceiver != null) {
            brightnessChangeReceiver.c(this);
        }
        new Thread(new v0(this, 2)).start();
        if (this.uidSyncManager == null) {
            this.uidSyncManager = new UidSyncManager(this);
        }
        this.uidSyncManager.start();
        if (isStrWakeAutoStartReady()) {
            startStrWakeHeartbeat();
            return 1;
        }
        stopStrWakeHeartbeat();
        return 1;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        Intent intent2 = new Intent(getApplicationContext(), getClass());
        intent2.setPackage(getPackageName());
        getApplicationContext().startForegroundService(intent2);
    }
}
