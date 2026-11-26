package com.navi.tool.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.navi.tool.MainActivity;
import com.navi.tool.navi.api.NaviAction;
import com.navi.tool.navi.api.NaviConfig;
import com.navi.tool.navi.api.NaviEngine;
import com.navi.tool.navi.api.PoiPoint;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;
import com.navi.tool.service.NaviModuleLoader;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogMonitorService extends Service {
    public static final String ACTION_CHECK_STATUS = "com.navi.tool.CHECK_STATUS";
    public static final String ACTION_START_LOG_MONITORING = "com.navi.tool.START_LOG_MONITORING";
    public static final String ACTION_START_ROUTE_TRANSFER = "com.navi.tool.START_ROUTE_TRANSFER";
    public static final String ACTION_STOP_LOG_MONITORING = "com.navi.tool.STOP_LOG_MONITORING";
    public static final String ACTION_STOP_ROUTE_TRANSFER = "com.navi.tool.STOP_ROUTE_TRANSFER";
    public static final String ACTION_UPDATE_STEERING_FLYING = "com.navi.tool.UPDATE_STEERING_FLYING";
    private static final String CHANNEL_ID = "LogMonitorServiceChannel";
    private static final String CHANNEL_NAME = "语音导航服务";
    public static final String EXTRA_STATUS = "extra_status";
    private static final long LOG_TIME_THRESHOLD = 1000;
    private static final int NOTIFICATION_ID = 1001;
    private static final String TAG = "LogMonitorService";
    private static boolean isServiceRunning;
    private Thread logThread;
    private Process logcatProcess;
    private Handler mainHandler;
    private BroadcastReceiver navStatusReceiver;
    private volatile NaviEngine naviEngine;
    private Thread poiLogThread;
    private Process poiLogcatProcess;
    private BroadcastReceiver routeTransferReceiver;
    private long serviceStartTime;
    private Thread steeringLogThread;
    private Process steeringLogcatProcess;
    private String lastProcessedLogLine = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastPoiKey = s5.h0.FRAGMENT_ENCODE_SET;
    private long lastPoiSentAt = 0;
    private String routeTransferLastKey = s5.h0.FRAGMENT_ENCODE_SET;
    private boolean isMonitoring = false;
    private boolean isStrictFiltering = false;
    private boolean isRouteTransferListening = false;
    private String lastProcessedPoiLogLine = s5.h0.FRAGMENT_ENCODE_SET;
    private boolean isSteeringMonitoring = false;
    private String lastProcessedSteeringLogLine = s5.h0.FRAGMENT_ENCODE_SET;
    private long lastSteeringKeyAction4Time = 0;
    private boolean isForegroundStarted = false;
    private int engineRetryAttempt = 0;
    private final Runnable engineRetryRunnable = new v(this, 10);

    /* renamed from: com.navi.tool.service.LogMonitorService$2, reason: invalid class name */
    public class AnonymousClass2 extends BroadcastReceiver {
        public AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$0() {
            LogMonitorService.this.sendAmapCommandBroadcast(10031, null, null);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            try {
                if (AmapBroadcastConstants.getAmapBroadcast().equals(intent.getAction()) && intent.getIntExtra(Auto3DBroadcastReceiver.KEY_TYPE, -1) == 10019 && intent.getIntExtra(Auto3DBroadcastReceiver.EXTRA_STATE, -1) == 8) {
                    int i7 = 0;
                    if (SharedPreferencesUtils.isAmapAutoBackgroundEnabled(LogMonitorService.this)) {
                        LogMonitorService.this.mainHandler.postDelayed(new b0(i7, this), SharedPreferencesUtils.getAmapAutoBackgroundDelaySeconds(LogMonitorService.this) * 1000);
                    }
                    if (SharedPreferencesUtils.isReturnAppEnabled(LogMonitorService.this)) {
                        Intent intent2 = new Intent(LogMonitorService.this, (Class<?>) ReturnAppService.class);
                        intent2.putExtra(ReturnAppService.EXTRA_DELAY_MS, Math.max(0, SharedPreferencesUtils.getReturnAppDelaySeconds(LogMonitorService.this)) * 1000);
                        LogMonitorService.this.startForegroundService(intent2);
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static class PoiData {
        public String area;
        public String city;
        public int id;
        public String latitude;
        public String longitude;
        public String name;
        public String province;
    }

    private NaviConfig buildNaviConfig() {
        return new NaviConfig(SharedPreferencesUtils.isManualRouteSelectEnabled(this), SharedPreferencesUtils.getDelayedNavDelaySeconds(this), countEnabledNaviApps() > 1, hasEnabledBaiduVoiceNavApp(), hasEnabledTencentVoiceNavApp());
    }

    private void clearPoiData() {
    }

    private int countEnabledNaviApps() {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        int i7 = 0;
        if (installedNavigationPackages == null) {
            return 0;
        }
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str) || str.startsWith(AppDetectionUtils.BAIDU_PREFIX) || str.startsWith(AppDetectionUtils.TENCENT_PREFIX)) {
                if (SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                    i7++;
                }
            }
        }
        return i7;
    }

    private Notification createNotification() {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592);
        e0.v vVar = new e0.v(this, CHANNEL_ID);
        vVar.f6327e = e0.v.c(CHANNEL_NAME);
        vVar.f6328f = e0.v.c("正在监听导航日志...");
        vVar.f6341s.icon = g4.o.ic_launcher_foreground;
        vVar.f6329g = activity;
        vVar.f6331i = -1;
        return vVar.a();
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 2);
        notificationChannel.setDescription("用于语音导航日志监控服务");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void ensureNaviEngineLoaded() {
        if (this.isMonitoring) {
            if (this.naviEngine != null) {
                startLogcatThreads();
            } else {
                NaviModuleLoader.getInstance().ensureEngine(this, new NaviModuleLoader.Callback() { // from class: com.navi.tool.service.LogMonitorService.3
                    @Override // com.navi.tool.service.NaviModuleLoader.Callback
                    public void onError(String str) {
                        LogMonitorService.this.scheduleEngineRetry();
                    }

                    @Override // com.navi.tool.service.NaviModuleLoader.Callback
                    public void onReady(NaviEngine naviEngine) {
                        if (LogMonitorService.this.isMonitoring) {
                            LogMonitorService.this.naviEngine = naviEngine;
                            LogMonitorService.this.engineRetryAttempt = 0;
                            LogMonitorService.this.mainHandler.removeCallbacks(LogMonitorService.this.engineRetryRunnable);
                            LogMonitorService.this.startLogcatThreads();
                        }
                    }
                });
            }
        }
    }

    private void executeNaviAction(NaviAction naviAction) {
        if (naviAction == null) {
            return;
        }
        l0 l0Var = new l0(5, this, naviAction);
        long j7 = naviAction.delayMs;
        if (j7 > 0) {
            this.mainHandler.postDelayed(l0Var, j7);
        } else {
            l0Var.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executePostActionForNativeAmap() {
        String nativeAmapSelectedPackage;
        if (!SharedPreferencesUtils.isRouteTransferPostActionEnabled(this) || (nativeAmapSelectedPackage = SharedPreferencesUtils.getNativeAmapSelectedPackage(this)) == null || nativeAmapSelectedPackage.isEmpty()) {
            return;
        }
        int routeTransferPostActionType = SharedPreferencesUtils.getRouteTransferPostActionType(this);
        try {
            Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
            intent.setClassName(nativeAmapSelectedPackage, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
            intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, routeTransferPostActionType);
            sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    private static double[] gaoDeToBaidu(double d7, double d8) {
        double sin = (Math.sin(d8 * 52.35987755982988d) * 2.0E-5d) + Math.sqrt((d8 * d8) + (d7 * d7));
        double cos = (Math.cos(d7 * 52.35987755982988d) * 3.0E-6d) + Math.atan2(d8, d7);
        return new double[]{(Math.cos(cos) * sin) + 0.0065d, (Math.sin(cos) * sin) + 0.006d};
    }

    private void handleSteeringKeyAction4() {
        if (SharedPreferencesUtils.isSteeringWheelExtScreenEnabled(this)) {
            int i7 = 3;
            int i8 = 5;
            try {
                if (SharedPreferencesUtils.isSteeringWheelBackgroundMode(this)) {
                    Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
                    intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10031);
                    sendBroadcast(intent);
                    this.mainHandler.post(new g4.k(i8));
                }
            } catch (Throwable th) {
                this.mainHandler.post(new b0(i7, th));
            }
            Handler handler = new Handler(Looper.getMainLooper());
            if (!SharedPreferencesUtils.isTraditionalClusterEnabled(this)) {
                handler.postDelayed(new v(this, 6), 100L);
                handler.postDelayed(new v(this, 7), 200L);
                handler.postDelayed(new v(this, 8), 300L);
            } else {
                handler.postDelayed(new v(this, 1), 100L);
                handler.postDelayed(new v(this, 2), 150L);
                handler.postDelayed(new v(this, i7), 200L);
                handler.postDelayed(new v(this, 4), 300L);
                handler.postDelayed(new v(this, i8), 350L);
            }
        }
    }

    private boolean hasEnabledBaiduVoiceNavApp() {
        for (String str : AppDetectionUtils.getInstalledNavigationPackages(this)) {
            if (str.startsWith(AppDetectionUtils.BAIDU_PREFIX) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasEnabledTencentVoiceNavApp() {
        for (String str : AppDetectionUtils.getInstalledNavigationPackages(this)) {
            if (str.startsWith(AppDetectionUtils.TENCENT_PREFIX) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyAmapEnabledForVoiceNav() {
        for (String str : AppDetectionUtils.getInstalledNavigationPackages(this)) {
            if (AppDetectionUtils.isAmapPackage(str) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLogWithinTimeThreshold(String str) {
        if (str != null) {
            try {
                if (str.length() >= 18) {
                    Matcher matcher = Pattern.compile("^(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{3})").matcher(str);
                    if (!matcher.find()) {
                        return false;
                    }
                    int parseInt = Integer.parseInt(matcher.group(1));
                    int parseInt2 = Integer.parseInt(matcher.group(2));
                    int parseInt3 = Integer.parseInt(matcher.group(3));
                    int parseInt4 = Integer.parseInt(matcher.group(4));
                    int parseInt5 = Integer.parseInt(matcher.group(5));
                    int parseInt6 = Integer.parseInt(matcher.group(6));
                    Calendar calendar = Calendar.getInstance();
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.set(2, parseInt - 1);
                    calendar2.set(5, parseInt2);
                    calendar2.set(11, parseInt3);
                    calendar2.set(12, parseInt4);
                    calendar2.set(13, parseInt5);
                    calendar2.set(14, parseInt6);
                    calendar2.set(1, calendar.get(1));
                    if (calendar2.after(calendar)) {
                        calendar2.set(1, calendar.get(1) - 1);
                    }
                    return Math.abs(calendar.getTimeInMillis() - calendar2.getTimeInMillis()) <= 1000;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean isServiceRunning() {
        return isServiceRunning;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeNaviAction$15(NaviAction naviAction) {
        switch (naviAction.type) {
            case 1:
                sendAmapCommandBroadcast(10010, null, null);
                logAsync("匹配到退出导航指令");
                break;
            case 2:
                sendBaiduExitBroadcast();
                break;
            case 3:
                launchEnabledAmapApps();
                break;
            case 4:
                sendAmapCommandBroadcast(10040, 0, 0);
                logAsync("匹配到回家指令");
                break;
            case 5:
                sendAmapCommandBroadcast(10040, 1, 0);
                logAsync("匹配到公司指令");
                break;
            case 6:
                sendAmapHomeCompanyManualBroadcast(0);
                logAsync("匹配到回家指令");
                break;
            case 7:
                sendAmapHomeCompanyManualBroadcast(1);
                logAsync("匹配到公司指令");
                break;
            case 8:
                startBaiduHomeNavigation();
                break;
            case 9:
                startBaiduCompanyNavigation();
                break;
            case 10:
                startTencentHomeNavigation();
                break;
            case 11:
                startTencentCompanyNavigation();
                break;
            case 12:
                sendAmapMuteBroadcast(1);
                logAsync("匹配到静音指令");
                break;
            case 13:
                sendAmapMuteBroadcast(0);
                logAsync("匹配到取消静音指令");
                break;
            case 14:
                sendAmapZoomBroadcast(0);
                logAsync("匹配到放大地图指令");
                break;
            case 15:
                sendAmapZoomBroadcast(1);
                logAsync("匹配到缩小地图指令");
                break;
            case 16:
                sendAmapSwitchRoadBroadcast(0);
                logAsync("匹配到切换主路指令");
                break;
            case 17:
                sendAmapSwitchRoadBroadcast(1);
                logAsync("匹配到切换辅路指令");
                break;
            case 18:
                sendAmapContinueNavigationBroadcast();
                logAsync("匹配到继续导航指令");
                break;
            case 19:
                sendAmapCommandBroadcast(12012, null, null);
                logAsync("匹配到刷新路线指令");
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleSteeringKeyAction4$22() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSteeringKeyAction4$24() {
        sendSteeringExtScreenBroadcast(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSteeringKeyAction4$25() {
        sendSteeringExtScreenBroadcast(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSteeringKeyAction4$26() {
        sendSteeringExtScreenBroadcast(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSteeringKeyAction4$27() {
        sendSteeringExtScreenBroadcast(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSteeringKeyAction4$28() {
        sendSteeringExtScreenBroadcast(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSteeringKeyAction4$29() {
        sendSteeringExtScreenBroadcast(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSteeringKeyAction4$30() {
        sendSteeringExtScreenBroadcast(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleSteeringKeyAction4$31() {
        sendSteeringExtScreenBroadcast(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$launchEnabledAmapApps$53() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$logAsync$16(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.isMonitoring) {
            ensureNaviEngineLoaded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(boolean z6) {
        if (z6) {
            onLicensed();
        } else {
            stopSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2() {
        boolean z6;
        try {
            z6 = NativeBridge.isFeatureEnabled(getApplicationContext(), 4);
        } catch (Throwable unused) {
            z6 = false;
        }
        this.mainHandler.post(new com.google.android.material.internal.g0(this, z6, 3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapCommandBroadcast$38() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapCommandBroadcast$39(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapCommandBroadcast$40(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapContinueNavigationBroadcast$63() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapContinueNavigationBroadcast$64(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapContinueNavigationBroadcast$65(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapHomeCompanyManualBroadcast$50() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapHomeCompanyManualBroadcast$51(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapHomeCompanyManualBroadcast$52(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapMuteBroadcast$54() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapMuteBroadcast$55(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapMuteBroadcast$56(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapSwitchRoadBroadcast$60() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapSwitchRoadBroadcast$61(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapSwitchRoadBroadcast$62(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapZoomBroadcast$57() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapZoomBroadcast$58(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendAmapZoomBroadcast$59(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendBaiduExitBroadcast$41() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendBaiduExitBroadcast$42(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcast$32(Intent intent, String str) {
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcast$33(Intent intent, String str) {
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcast$34(String str, PoiData poiData) {
        ResolveInfo resolveInfo;
        try {
            PackageManager packageManager = getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    resolveInfo = null;
                    break;
                } else {
                    resolveInfo = it.next();
                    if (resolveInfo.activityInfo.packageName.equals(str)) {
                        break;
                    }
                }
            }
            if (resolveInfo != null) {
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setClassName(str, resolveInfo.activityInfo.name);
                intent2.addFlags(872546304);
                startActivity(intent2);
                String str2 = resolveInfo.activityInfo.name;
            }
        } catch (Exception e7) {
            e7.getMessage();
        }
        if (SharedPreferencesUtils.isManualRouteSelectEnabled(this)) {
            Intent intent3 = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
            intent3.setClassName(str, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
            intent3.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10007);
            intent3.putExtra("EXTRA_DNAME", poiData.name);
            try {
                double parseDouble = Double.parseDouble(poiData.latitude);
                double parseDouble2 = Double.parseDouble(poiData.longitude);
                intent3.putExtra("ENTRY_LAT", parseDouble);
                intent3.putExtra("ENTRY_LON", parseDouble2);
                intent3.putExtra("EXTRA_DEV", 0);
                intent3.putExtra("EXTRA_M", -1);
                this.mainHandler.postDelayed(new t(this, intent3, str, 6), 800L);
                return;
            } catch (NumberFormatException e8) {
                e8.getMessage();
                return;
            }
        }
        Intent intent4 = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
        intent4.setClassName(str, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
        intent4.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10038);
        intent4.putExtra("SOURCE_APP", "车机助手");
        intent4.putExtra("POINAME", poiData.name);
        try {
            double parseDouble3 = Double.parseDouble(poiData.latitude);
            double parseDouble4 = Double.parseDouble(poiData.longitude);
            intent4.putExtra("LAT", parseDouble3);
            intent4.putExtra("LON", parseDouble4);
            intent4.putExtra("DEV", 0);
            intent4.putExtra("STYLE", -1);
            this.mainHandler.postDelayed(new t(this, intent4, str, 7), 800L);
        } catch (NumberFormatException e9) {
            e9.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcast$35(Intent intent, String str) {
        sendBroadcast(intent);
        triggerReturnAppFromPoiDelivery();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcast$36(String str, PoiData poiData) {
        ResolveInfo resolveInfo;
        try {
            PackageManager packageManager = getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    resolveInfo = null;
                    break;
                } else {
                    resolveInfo = it.next();
                    if (resolveInfo.activityInfo.packageName.equals(str)) {
                        break;
                    }
                }
            }
            if (resolveInfo != null) {
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setClassName(str, resolveInfo.activityInfo.name);
                intent2.addFlags(872546304);
                startActivity(intent2);
                String str2 = resolveInfo.activityInfo.name;
            }
        } catch (Exception e7) {
            e7.getMessage();
        }
        Intent intent3 = new Intent("WECARNAVI_STANDARD_BROADCAST_RECV");
        intent3.setPackage(str);
        intent3.putExtra("POINAME", poiData.name);
        try {
            float parseFloat = Float.parseFloat(poiData.latitude);
            float parseFloat2 = Float.parseFloat(poiData.longitude);
            intent3.putExtra("LAT", parseFloat);
            intent3.putExtra("LON", parseFloat2);
            intent3.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 1008);
            intent3.putExtra("COORD", 1);
            this.mainHandler.postDelayed(new t(this, intent3, str, 3), 800L);
        } catch (NumberFormatException e8) {
            e8.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcast$37(PoiData poiData) {
        double d7;
        double d8;
        try {
            d7 = Double.parseDouble(poiData.latitude);
            d8 = Double.parseDouble(poiData.longitude);
        } catch (NumberFormatException e7) {
            e7.getMessage();
            d7 = 0.0d;
            d8 = 0.0d;
        }
        double[] gaoDeToBaidu = gaoDeToBaidu(d8, d7);
        double d9 = gaoDeToBaidu[0];
        double d10 = gaoDeToBaidu[1];
        String str = poiData.name;
        if (str == null) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        String encode = Uri.encode(str);
        String str2 = "baidumap://map/navi?location=" + String.format(Locale.US, "%.6f,%.6f", Double.valueOf(d10), Double.valueOf(d9)) + "&coord_type=bd09ll&query=" + encode + "&src=com.navi.tool&ts=" + System.currentTimeMillis();
        sendBaiduExitBroadcast();
        startBaiduNaviDelayed(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcastForRouteTransfer$3(Intent intent, String str) {
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcastForRouteTransfer$4(Intent intent, String str) {
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcastForRouteTransfer$5(String str, PoiData poiData) {
        ResolveInfo resolveInfo;
        try {
            PackageManager packageManager = getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    resolveInfo = null;
                    break;
                } else {
                    resolveInfo = it.next();
                    if (resolveInfo.activityInfo.packageName.equals(str)) {
                        break;
                    }
                }
            }
            if (resolveInfo != null) {
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setClassName(str, resolveInfo.activityInfo.name);
                intent2.addFlags(872546304);
                startActivity(intent2);
                String str2 = resolveInfo.activityInfo.name;
            }
        } catch (Exception e7) {
            e7.getMessage();
        }
        if (SharedPreferencesUtils.isManualRouteSelectEnabled(this)) {
            Intent intent3 = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
            intent3.setClassName(str, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
            intent3.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10007);
            intent3.putExtra("EXTRA_DNAME", poiData.name);
            try {
                double parseDouble = Double.parseDouble(poiData.latitude);
                double parseDouble2 = Double.parseDouble(poiData.longitude);
                intent3.putExtra("ENTRY_LAT", parseDouble);
                intent3.putExtra("ENTRY_LON", parseDouble2);
                intent3.putExtra("EXTRA_DEV", 0);
                intent3.putExtra("EXTRA_M", -1);
                this.mainHandler.postDelayed(new t(this, intent3, str, 1), 800L);
                return;
            } catch (NumberFormatException e8) {
                e8.getMessage();
                return;
            }
        }
        Intent intent4 = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
        intent4.setClassName(str, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
        intent4.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10038);
        intent4.putExtra("SOURCE_APP", "车机助手");
        intent4.putExtra("POINAME", poiData.name);
        try {
            double parseDouble3 = Double.parseDouble(poiData.latitude);
            double parseDouble4 = Double.parseDouble(poiData.longitude);
            intent4.putExtra("LAT", parseDouble3);
            intent4.putExtra("LON", parseDouble4);
            intent4.putExtra("DEV", 0);
            intent4.putExtra("STYLE", -1);
            this.mainHandler.postDelayed(new t(this, intent4, str, 2), 800L);
        } catch (NumberFormatException e9) {
            e9.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcastForRouteTransfer$6(Intent intent, String str) {
        sendBroadcast(intent);
        triggerReturnAppFromPoiDelivery();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcastForRouteTransfer$7(String str, PoiData poiData) {
        ResolveInfo resolveInfo;
        try {
            PackageManager packageManager = getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    resolveInfo = null;
                    break;
                } else {
                    resolveInfo = it.next();
                    if (resolveInfo.activityInfo.packageName.equals(str)) {
                        break;
                    }
                }
            }
            if (resolveInfo != null) {
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setClassName(str, resolveInfo.activityInfo.name);
                intent2.addFlags(872546304);
                startActivity(intent2);
                String str2 = resolveInfo.activityInfo.name;
            }
        } catch (Exception e7) {
            e7.getMessage();
        }
        Intent intent3 = new Intent("WECARNAVI_STANDARD_BROADCAST_RECV");
        intent3.setPackage(str);
        intent3.putExtra("POINAME", poiData.name);
        try {
            float parseFloat = Float.parseFloat(poiData.latitude);
            float parseFloat2 = Float.parseFloat(poiData.longitude);
            intent3.putExtra("LAT", parseFloat);
            intent3.putExtra("LON", parseFloat2);
            intent3.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 1008);
            intent3.putExtra("COORD", 1);
            this.mainHandler.postDelayed(new t(this, intent3, str, 5), 800L);
        } catch (NumberFormatException e8) {
            e8.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendNavigationBroadcastForRouteTransfer$8(PoiData poiData) {
        double d7;
        double d8;
        try {
            d7 = Double.parseDouble(poiData.latitude);
            d8 = Double.parseDouble(poiData.longitude);
        } catch (NumberFormatException e7) {
            e7.getMessage();
            d7 = 0.0d;
            d8 = 0.0d;
        }
        double[] gaoDeToBaidu = gaoDeToBaidu(d8, d7);
        double d9 = gaoDeToBaidu[0];
        double d10 = gaoDeToBaidu[1];
        String str = poiData.name;
        if (str == null) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        String encode = Uri.encode(str);
        String str2 = "baidumap://map/navi?location=" + String.format(Locale.US, "%.6f,%.6f", Double.valueOf(d10), Double.valueOf(d9)) + "&coord_type=bd09ll&query=" + encode + "&src=com.navi.tool&ts=" + System.currentTimeMillis();
        sendBaiduExitBroadcast();
        startBaiduNaviDelayed(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startBaiduNaviDelayed$43(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(268435456);
        try {
            startActivity(intent);
            triggerReturnAppFromPoiDelivery();
        } catch (Exception e7) {
            e7.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startLogcatThreads$11() {
        String readLine;
        try {
            this.poiLogcatProcess = Runtime.getRuntime().exec("logcat -v time *:S SCENE-BuriedDataUploader:V");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.poiLogcatProcess.getInputStream()));
            while (this.isMonitoring && (readLine = bufferedReader.readLine()) != null) {
                NaviEngine naviEngine = this.naviEngine;
                if (naviEngine != null && naviEngine.isNaviSelectLine(readLine) && !readLine.equals(this.lastProcessedPoiLogLine)) {
                    this.lastProcessedPoiLogLine = readLine;
                    if (isLogWithinTimeThreshold(readLine)) {
                        this.mainHandler.post(new y0.n(this, readLine, naviEngine, 12));
                    }
                }
            }
        } catch (IOException e7) {
            this.mainHandler.post(new w(e7, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startLogcatThreads$12(int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0060 A[Catch: IOException -> 0x006a, LOOP:1: B:25:0x005a->B:27:0x0060, LOOP_END, TryCatch #0 {IOException -> 0x006a, blocks: (B:3:0x0001, B:4:0x001e, B:6:0x0022, B:8:0x0028, B:11:0x002f, B:14:0x0038, B:16:0x003e, B:18:0x0044, B:24:0x0052, B:25:0x005a, B:27:0x0060, B:29:0x004e, B:30:0x006c, B:33:0x0070), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$startLogcatThreads$14() {
        /*
            r7 = this;
            r0 = 2
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.io.IOException -> L6a
            java.lang.String r2 = "logcat -v time *:S SrSolutiontucao11:V"
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.io.IOException -> L6a
            r7.logcatProcess = r1     // Catch: java.io.IOException -> L6a
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.io.IOException -> L6a
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L6a
            java.lang.Process r3 = r7.logcatProcess     // Catch: java.io.IOException -> L6a
            java.io.InputStream r3 = r3.getInputStream()     // Catch: java.io.IOException -> L6a
            r2.<init>(r3)     // Catch: java.io.IOException -> L6a
            r1.<init>(r2)     // Catch: java.io.IOException -> L6a
            r2 = 0
        L1e:
            boolean r3 = r7.isMonitoring     // Catch: java.io.IOException -> L6a
            if (r3 == 0) goto L85
            java.lang.String r3 = r1.readLine()     // Catch: java.io.IOException -> L6a
            if (r3 == 0) goto L85
            int r2 = r2 + 1
            com.navi.tool.navi.api.NaviEngine r4 = r7.naviEngine     // Catch: java.io.IOException -> L6a
            if (r4 != 0) goto L2f
            goto L1e
        L2f:
            java.lang.String r5 = r7.lastProcessedLogLine     // Catch: java.io.IOException -> L6a
            boolean r5 = r3.equals(r5)     // Catch: java.io.IOException -> L6a
            if (r5 == 0) goto L38
            goto L1e
        L38:
            int r5 = r4.detect(r3)     // Catch: java.io.IOException -> L6a
            if (r5 == 0) goto L6c
            boolean r6 = r7.isLogWithinTimeThreshold(r3)     // Catch: java.io.IOException -> L6a
            if (r6 == 0) goto L6c
            r7.lastProcessedLogLine = r3     // Catch: java.io.IOException -> L6a
            if (r5 == r0) goto L4e
            r3 = 3
            if (r5 != r3) goto L4c
            goto L4e
        L4c:
            r3 = 0
            goto L52
        L4e:
            com.navi.tool.navi.api.NaviConfig r3 = r7.buildNaviConfig()     // Catch: java.io.IOException -> L6a
        L52:
            java.util.List r3 = r4.plan(r5, r3)     // Catch: java.io.IOException -> L6a
            java.util.Iterator r3 = r3.iterator()     // Catch: java.io.IOException -> L6a
        L5a:
            boolean r4 = r3.hasNext()     // Catch: java.io.IOException -> L6a
            if (r4 == 0) goto L6c
            java.lang.Object r4 = r3.next()     // Catch: java.io.IOException -> L6a
            com.navi.tool.navi.api.NaviAction r4 = (com.navi.tool.navi.api.NaviAction) r4     // Catch: java.io.IOException -> L6a
            r7.executeNaviAction(r4)     // Catch: java.io.IOException -> L6a
            goto L5a
        L6a:
            r1 = move-exception
            goto L7b
        L6c:
            int r3 = r2 % 10000
            if (r3 != 0) goto L1e
            android.os.Handler r3 = r7.mainHandler     // Catch: java.io.IOException -> L6a
            com.navi.tool.service.a0 r4 = new com.navi.tool.service.a0     // Catch: java.io.IOException -> L6a
            r4.<init>()     // Catch: java.io.IOException -> L6a
            r3.post(r4)     // Catch: java.io.IOException -> L6a
            goto L1e
        L7b:
            android.os.Handler r2 = r7.mainHandler
            com.navi.tool.service.w r3 = new com.navi.tool.service.w
            r3.<init>(r1, r0)
            r2.post(r3)
        L85:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.LogMonitorService.lambda$startLogcatThreads$14():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startLogcatThreads$9(String str, NaviEngine naviEngine) {
        PoiPoint parseNaviSelect = naviEngine.parseNaviSelect(str);
        if (parseNaviSelect != null) {
            PoiData poiData = new PoiData();
            poiData.id = 1;
            poiData.name = parseNaviSelect.name;
            poiData.latitude = parseNaviSelect.latitude;
            poiData.longitude = parseNaviSelect.longitude;
            sendNavigationBroadcast(poiData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startSteeringLogMonitoring$17() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startSteeringLogMonitoring$18() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startSteeringLogMonitoring$19(String str) {
        handleSteeringKeyAction4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startSteeringLogMonitoring$21() {
        String readLine;
        try {
            this.steeringLogcatProcess = Runtime.getRuntime().exec("logcat -v time *:S TingT26_SOP_241231_09:V HardKeyAdapterManager:V");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.steeringLogcatProcess.getInputStream()));
            while (this.isSteeringMonitoring && (readLine = bufferedReader.readLine()) != null) {
                if (!readLine.equals(this.lastProcessedSteeringLogLine) && (readLine.contains("keyAction = 4") || readLine.contains("keyAction:4"))) {
                    if (isLogWithinTimeThreshold(readLine)) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.lastSteeringKeyAction4Time >= 800) {
                            this.lastSteeringKeyAction4Time = currentTimeMillis;
                            this.lastProcessedSteeringLogLine = readLine;
                            this.mainHandler.post(new s(this, readLine, 1));
                        }
                    }
                }
            }
        } catch (IOException e7) {
            this.mainHandler.post(new w(e7, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startTencentCompanyNavigation$47() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startTencentCompanyNavigation$48(Intent intent, String str) {
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startTencentHomeNavigation$44() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startTencentHomeNavigation$45(Intent intent, String str) {
        sendBroadcast(intent);
    }

    private void launchEnabledAmapApps() {
        ResolveInfo resolveInfo;
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(14));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            try {
                PackageManager packageManager = getPackageManager();
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                Iterator<ResolveInfo> it2 = packageManager.queryIntentActivities(intent, 0).iterator();
                while (true) {
                    if (it2.hasNext()) {
                        resolveInfo = it2.next();
                        if (resolveInfo.activityInfo.packageName.equals(str2)) {
                            break;
                        }
                    } else {
                        resolveInfo = null;
                        break;
                    }
                }
                if (resolveInfo != null) {
                    Intent intent2 = new Intent("android.intent.action.MAIN");
                    intent2.addCategory("android.intent.category.LAUNCHER");
                    intent2.setClassName(str2, resolveInfo.activityInfo.name);
                    intent2.addFlags(872546304);
                    startActivity(intent2);
                    String str3 = resolveInfo.activityInfo.name;
                }
            } catch (Exception e7) {
                e7.getMessage();
            }
        }
    }

    private void logAsync(String str) {
        this.mainHandler.post(new x(str, 2));
    }

    private void onLicensed() {
        isServiceRunning = true;
        if (SharedPreferencesUtils.getAppPrefs(this).getBoolean("voice_navigation_switch_state", false)) {
            startLogMonitoring();
        } else {
            boolean isRouteTransferEnabled = SharedPreferencesUtils.isRouteTransferEnabled(this);
            String nativeAmapSelectedPackage = SharedPreferencesUtils.getNativeAmapSelectedPackage(this);
            if (isRouteTransferEnabled && nativeAmapSelectedPackage != null && !nativeAmapSelectedPackage.isEmpty()) {
                startRouteTransferListening();
            }
        }
        startNavStatusListening();
    }

    private void processSelectionLine(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleEngineRetry() {
        if (!this.isMonitoring || this.mainHandler == null) {
            return;
        }
        int i7 = this.engineRetryAttempt + 1;
        this.engineRetryAttempt = i7;
        long min = Math.min(i7 * 30000, 300000L);
        this.mainHandler.removeCallbacks(this.engineRetryRunnable);
        this.mainHandler.postDelayed(this.engineRetryRunnable, min);
        long j7 = min / 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmapCommandBroadcast(int i7, Integer num, Integer num2) {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(3));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            try {
                Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
                intent.setClassName(str2, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
                intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, i7);
                if (num != null) {
                    intent.putExtra("DEST", num.intValue());
                }
                if (num2 != null) {
                    intent.putExtra("IS_START_NAVI", num2.intValue());
                }
                sendBroadcast(intent);
                StringBuilder sb = new StringBuilder();
                sb.append("已向高德发送指令广播 KEY_TYPE=");
                sb.append(i7);
                String str3 = s5.h0.FRAGMENT_ENCODE_SET;
                sb.append(num != null ? " DEST=" + num : s5.h0.FRAGMENT_ENCODE_SET);
                if (num2 != null) {
                    str3 = " IS_START_NAVI=" + num2;
                }
                sb.append(str3);
                sb.append(": ");
                sb.append(str2);
                this.mainHandler.post(new x(sb.toString(), 0));
            } catch (Exception e7) {
                this.mainHandler.post(new x("发送高德指令广播失败: " + e7.getMessage(), 1));
            }
        }
    }

    private void sendAmapContinueNavigationBroadcast() {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(8));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            try {
                Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
                intent.setClassName(str2, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
                intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10049);
                sendBroadcast(intent);
                this.mainHandler.post(new x("已向高德发送继续导航指令: " + str2, 3));
            } catch (Exception e7) {
                this.mainHandler.post(new x("发送高德继续导航指令失败: " + e7.getMessage(), 4));
            }
        }
    }

    private void sendAmapHomeCompanyManualBroadcast(int i7) {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(11));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            try {
                Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
                intent.setClassName(str2, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
                intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10040);
                intent.putExtra("DEST", i7);
                intent.putExtra("IS_START_NAVI", 1);
                intent.putExtra("SOURCE_APP", "车机助手");
                sendBroadcast(intent);
                this.mainHandler.post(new x("已向高德发送手动路线指令 KEY_TYPE=10040 DEST=" + i7 + " IS_START_NAVI=1 SOURCE_APP=车机助手: " + str2, 8));
            } catch (Exception e7) {
                this.mainHandler.post(new x("发送手动路线指令失败: " + e7.getMessage(), 9));
            }
        }
    }

    private void sendAmapMuteBroadcast(int i7) {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(12));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            try {
                Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
                intent.setClassName(str2, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
                intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10047);
                intent.putExtra("EXTRA_MUTE", i7);
                sendBroadcast(intent);
                this.mainHandler.post(new x("已向高德发送静音指令 EXTRA_MUTE=" + i7 + ": " + str2, 10));
            } catch (Exception e7) {
                this.mainHandler.post(new x("发送高德静音指令失败: " + e7.getMessage(), 11));
            }
        }
    }

    private void sendAmapSwitchRoadBroadcast(int i7) {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        int i8 = 13;
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(i8));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            try {
                Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
                intent.setClassName(str2, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
                intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 12013);
                intent.putExtra(Auto3DBroadcastReceiver.EXTRA_TYPE, i7);
                sendBroadcast(intent);
                this.mainHandler.post(new x("已向高德发送切路指令 EXTRA_TYPE=" + i7 + ": " + str2, 12));
            } catch (Exception e7) {
                this.mainHandler.post(new x("发送高德切路指令失败: " + e7.getMessage(), i8));
            }
        }
    }

    private void sendAmapZoomBroadcast(int i7) {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(9));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            try {
                Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
                intent.setClassName(str2, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
                intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10027);
                intent.putExtra(Auto3DBroadcastReceiver.EXTRA_TYPE, 1);
                intent.putExtra(Auto3DBroadcastReceiver.EXTRA_OPERA, i7);
                sendBroadcast(intent);
                this.mainHandler.post(new x("已向高德发送放缩指令 EXTRA_OPERA=" + i7 + ": " + str2, 5));
            } catch (Exception e7) {
                this.mainHandler.post(new x("发送高德放缩指令失败: " + e7.getMessage(), 6));
            }
        }
    }

    private void sendBaiduExitBroadcast() {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (str.startsWith(AppDetectionUtils.BAIDU_PREFIX) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(10));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            try {
                Intent intent = new Intent("BAIDU_STANDARD_BROADCAST_RECV");
                intent.setPackage(str2);
                intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10049);
                intent.putExtra("EXTRA_NAVI_EXIT", 1);
                sendBroadcast(intent);
                this.mainHandler.post(new x("已向百度发送退出导航广播: " + str2, 7));
            } catch (Exception unused) {
            }
        }
    }

    private void sendNavigationBroadcast(PoiData poiData) {
        StringBuilder sb = new StringBuilder();
        String str = poiData.name;
        String str2 = s5.h0.FRAGMENT_ENCODE_SET;
        if (str == null) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        sb.append(str);
        sb.append("|");
        String str3 = poiData.latitude;
        if (str3 == null) {
            str3 = s5.h0.FRAGMENT_ENCODE_SET;
        }
        sb.append(str3);
        sb.append("|");
        String str4 = poiData.longitude;
        if (str4 != null) {
            str2 = str4;
        }
        sb.append(str2);
        String sb2 = sb.toString();
        long currentTimeMillis = System.currentTimeMillis();
        if (!sb2.equals(this.lastPoiKey) || currentTimeMillis - this.lastPoiSentAt >= PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS) {
            this.lastPoiKey = sb2;
            this.lastPoiSentAt = currentTimeMillis;
            List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (String str5 : installedNavigationPackages) {
                if (AppDetectionUtils.isAmapPackage(str5)) {
                    if (SharedPreferencesUtils.isVoiceNavAppEnabled(this, str5)) {
                        arrayList.add(str5);
                    }
                } else if (str5.startsWith(AppDetectionUtils.BAIDU_PREFIX)) {
                    if (SharedPreferencesUtils.isVoiceNavAppEnabled(this, str5)) {
                        arrayList2.add(str5);
                    }
                } else if (str5.startsWith(AppDetectionUtils.TENCENT_PREFIX) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str5)) {
                    arrayList3.add(str5);
                }
            }
            if (arrayList.isEmpty() && arrayList2.isEmpty() && arrayList3.isEmpty()) {
                return;
            }
            boolean isDelayedNavEnabled = SharedPreferencesUtils.isDelayedNavEnabled(this);
            int delayedNavDelaySeconds = SharedPreferencesUtils.getDelayedNavDelaySeconds(this);
            SharedPreferencesUtils.isAmapAutoBackgroundEnabled(this);
            SharedPreferencesUtils.getAmapAutoBackgroundDelaySeconds(this);
            ArrayList arrayList4 = new ArrayList();
            int i7 = 0;
            if (!arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList4.add(new y(this, (String) it.next(), poiData, i7));
                }
            }
            int i8 = 1;
            if (!arrayList3.isEmpty()) {
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    arrayList4.add(new y(this, (String) it2.next(), poiData, i8));
                }
            }
            if (!arrayList2.isEmpty()) {
                arrayList4.add(new z(this, poiData, i7));
            }
            if (arrayList4.isEmpty()) {
                return;
            }
            if (!isDelayedNavEnabled || arrayList4.size() <= 1) {
                Iterator it3 = arrayList4.iterator();
                while (it3.hasNext()) {
                    this.mainHandler.post((Runnable) it3.next());
                }
                return;
            }
            long j7 = delayedNavDelaySeconds * 1000;
            Iterator it4 = arrayList4.iterator();
            long j8 = 0;
            while (it4.hasNext()) {
                this.mainHandler.postDelayed((Runnable) it4.next(), j8);
                j8 += j7;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNavigationBroadcastForRouteTransfer(PoiData poiData) {
        String nativeAmapSelectedPackage = SharedPreferencesUtils.getNativeAmapSelectedPackage(this);
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str)) {
                if (!str.equals(nativeAmapSelectedPackage) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                    arrayList.add(str);
                }
            } else if (str.startsWith(AppDetectionUtils.BAIDU_PREFIX)) {
                if (SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                    arrayList2.add(str);
                }
            } else if (str.startsWith(AppDetectionUtils.TENCENT_PREFIX) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList3.add(str);
            }
        }
        if (arrayList.isEmpty() && arrayList2.isEmpty() && arrayList3.isEmpty()) {
            return;
        }
        boolean isDelayedNavEnabled = SharedPreferencesUtils.isDelayedNavEnabled(this);
        int delayedNavDelaySeconds = SharedPreferencesUtils.getDelayedNavDelaySeconds(this);
        SharedPreferencesUtils.isAmapAutoBackgroundEnabled(this);
        SharedPreferencesUtils.getAmapAutoBackgroundDelaySeconds(this);
        ArrayList arrayList4 = new ArrayList();
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList4.add(new y(this, (String) it.next(), poiData, 2));
            }
        }
        if (!arrayList3.isEmpty()) {
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add(new y(this, (String) it2.next(), poiData, 3));
            }
        }
        boolean isEmpty = arrayList2.isEmpty();
        int i7 = 1;
        if (!isEmpty) {
            arrayList4.add(new z(this, poiData, i7));
        }
        if (arrayList4.isEmpty()) {
            return;
        }
        if (!isDelayedNavEnabled || arrayList4.size() <= 1) {
            Iterator it3 = arrayList4.iterator();
            while (it3.hasNext()) {
                this.mainHandler.post((Runnable) it3.next());
            }
            return;
        }
        long j7 = delayedNavDelaySeconds * 1000;
        Iterator it4 = arrayList4.iterator();
        long j8 = 0;
        while (it4.hasNext()) {
            this.mainHandler.postDelayed((Runnable) it4.next(), j8);
            j8 += j7;
        }
    }

    private void sendSteeringExtScreenBroadcast(int i7) {
        try {
            Intent intent = new Intent(AmapBroadcastConstants.DEFAULT_AMAP_BROADCAST);
            intent.putExtra(AmapBroadcastConstants.EXTRA_SELF_BROADCAST, true);
            intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10117);
            intent.putExtra("EXTSCREEN_STATUS_INFO", 0);
            intent.putExtra("EXTSCREEN_WINDOW_INFO", 1002);
            intent.putExtra("EXTSCREEN_MODE_INFO", i7);
            sendBroadcast(intent);
        } catch (Throwable unused) {
        }
    }

    private void startBaiduCompanyNavigation() {
        sendBaiduExitBroadcast();
        startBaiduNaviDelayed("baidumap://map/navi/common?addr=company&coord_type=bd09ll&src=com.navi.tool");
    }

    private void startBaiduHomeNavigation() {
        sendBaiduExitBroadcast();
        startBaiduNaviDelayed("baidumap://map/navi/common?addr=home&coord_type=bd09ll&src=com.navi.tool");
    }

    private void startBaiduNaviDelayed(String str) {
        this.mainHandler.postDelayed(new s(this, str, 0), 2000L);
    }

    private void startLogMonitoring() {
        if (this.isMonitoring) {
            return;
        }
        this.serviceStartTime = System.currentTimeMillis();
        this.isMonitoring = true;
        if (this.naviEngine == null) {
            ensureNaviEngineLoaded();
        } else {
            startLogcatThreads();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLogcatThreads() {
        if (!this.isMonitoring || this.naviEngine == null) {
            return;
        }
        Thread thread = this.logThread;
        if (thread == null || !thread.isAlive()) {
            Thread thread2 = new Thread(new v(this, 11));
            this.poiLogThread = thread2;
            thread2.start();
            Thread thread3 = new Thread(new v(this, 12));
            this.logThread = thread3;
            thread3.start();
            if (SharedPreferencesUtils.isSteeringWheelExtScreenEnabled(this)) {
                startSteeringLogMonitoring();
            }
        }
    }

    private void startNavStatusListening() {
        if (this.navStatusReceiver != null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter(AmapBroadcastConstants.getAmapBroadcast());
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        this.navStatusReceiver = anonymousClass2;
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(anonymousClass2, intentFilter, 2);
        } else {
            registerReceiver(anonymousClass2, intentFilter);
        }
    }

    private void startRouteTransferListening() {
        if (this.isRouteTransferListening) {
            return;
        }
        SharedPreferencesUtils.getNativeAmapSelectedPackage(this);
        this.isRouteTransferListening = true;
        IntentFilter intentFilter = new IntentFilter(AmapBroadcastConstants.getAmapBroadcast());
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.navi.tool.service.LogMonitorService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    if (intent.getAction().equals(AmapBroadcastConstants.getAmapBroadcast()) && intent.getIntExtra(Auto3DBroadcastReceiver.KEY_TYPE, -1) == 10001) {
                        String stringExtra = intent.getStringExtra("endPOIName");
                        double doubleExtra = intent.getDoubleExtra("endPOILatitude", 0.0d);
                        double doubleExtra2 = intent.getDoubleExtra("endPOILongitude", 0.0d);
                        if (stringExtra == null || stringExtra.isEmpty()) {
                            stringExtra = "流转目的地";
                        }
                        if (doubleExtra != 0.0d && doubleExtra2 != 0.0d) {
                            PoiData poiData = new PoiData();
                            poiData.name = stringExtra;
                            Locale locale = Locale.US;
                            poiData.latitude = String.format(locale, "%.6f", Double.valueOf(doubleExtra));
                            poiData.longitude = String.format(locale, "%.6f", Double.valueOf(doubleExtra2));
                            StringBuilder sb = new StringBuilder();
                            String str = poiData.name;
                            if (str == null) {
                                str = s5.h0.FRAGMENT_ENCODE_SET;
                            }
                            sb.append(str);
                            sb.append("|");
                            sb.append(poiData.latitude);
                            sb.append("|");
                            sb.append(poiData.longitude);
                            String sb2 = sb.toString();
                            if (sb2.equals(LogMonitorService.this.routeTransferLastKey)) {
                                return;
                            }
                            LogMonitorService.this.routeTransferLastKey = sb2;
                            LogMonitorService.this.executePostActionForNativeAmap();
                            LogMonitorService.this.sendNavigationBroadcastForRouteTransfer(poiData);
                        }
                    }
                } catch (Exception unused) {
                }
            }
        };
        this.routeTransferReceiver = broadcastReceiver;
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(broadcastReceiver, intentFilter, 2);
        } else {
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void startSteeringLogMonitoring() {
        if (this.isSteeringMonitoring) {
            this.mainHandler.post(new g4.k(6));
            return;
        }
        this.isSteeringMonitoring = true;
        this.mainHandler.post(new g4.k(7));
        Thread thread = new Thread(new v(this, 9));
        this.steeringLogThread = thread;
        thread.start();
    }

    private void startTencentCompanyNavigation() {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (str.startsWith(AppDetectionUtils.TENCENT_PREFIX) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        int i7 = 4;
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(i7));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            int i8 = 1;
            try {
                PackageManager packageManager = getPackageManager();
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setPackage(str2);
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
                if (!queryIntentActivities.isEmpty()) {
                    ResolveInfo resolveInfo = queryIntentActivities.get(0);
                    Intent intent2 = new Intent("android.intent.action.MAIN");
                    intent2.addCategory("android.intent.category.LAUNCHER");
                    intent2.setClassName(str2, resolveInfo.activityInfo.name);
                    intent2.addFlags(872546304);
                    startActivity(intent2);
                }
                Intent intent3 = new Intent("WECARNAVI_STANDARD_BROADCAST_RECV");
                intent3.setPackage(str2);
                intent3.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 1004);
                intent3.putExtra(Auto3DBroadcastReceiver.EXTRA_OPERA, 1);
                this.mainHandler.postDelayed(new t(this, intent3, str2, i7), 800L);
            } catch (Exception e7) {
                this.mainHandler.post(new u(e7, i8));
            }
        }
    }

    private void startTencentHomeNavigation() {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(this);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (str.startsWith(AppDetectionUtils.TENCENT_PREFIX) && SharedPreferencesUtils.isVoiceNavAppEnabled(this, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            this.mainHandler.post(new g4.k(2));
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            int i7 = 0;
            try {
                PackageManager packageManager = getPackageManager();
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setPackage(str2);
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
                if (!queryIntentActivities.isEmpty()) {
                    ResolveInfo resolveInfo = queryIntentActivities.get(0);
                    Intent intent2 = new Intent("android.intent.action.MAIN");
                    intent2.addCategory("android.intent.category.LAUNCHER");
                    intent2.setClassName(str2, resolveInfo.activityInfo.name);
                    intent2.addFlags(872546304);
                    startActivity(intent2);
                }
                Intent intent3 = new Intent("WECARNAVI_STANDARD_BROADCAST_RECV");
                intent3.setPackage(str2);
                intent3.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 1004);
                intent3.putExtra(Auto3DBroadcastReceiver.EXTRA_OPERA, 0);
                this.mainHandler.postDelayed(new t(this, intent3, str2, i7), 800L);
            } catch (Exception e7) {
                this.mainHandler.post(new u(e7, i7));
            }
        }
    }

    private void stopLogMonitoring() {
        if (this.isMonitoring) {
            this.isMonitoring = false;
            Handler handler = this.mainHandler;
            if (handler != null) {
                handler.removeCallbacks(this.engineRetryRunnable);
            }
            this.engineRetryAttempt = 0;
            Process process = this.logcatProcess;
            if (process != null) {
                process.destroy();
                this.logcatProcess = null;
            }
            Process process2 = this.poiLogcatProcess;
            if (process2 != null) {
                process2.destroy();
                this.poiLogcatProcess = null;
            }
            stopSteeringLogMonitoring();
            Thread thread = this.logThread;
            if (thread != null) {
                try {
                    thread.join(1000L);
                } catch (InterruptedException unused) {
                }
                this.logThread = null;
            }
            Thread thread2 = this.poiLogThread;
            if (thread2 != null) {
                try {
                    thread2.join(1000L);
                } catch (InterruptedException unused2) {
                }
                this.poiLogThread = null;
            }
        }
    }

    private void stopNavStatusListening() {
        try {
            BroadcastReceiver broadcastReceiver = this.navStatusReceiver;
            if (broadcastReceiver != null) {
                unregisterReceiver(broadcastReceiver);
                this.navStatusReceiver = null;
            }
        } catch (Exception unused) {
        }
    }

    private void stopRouteTransferListening() {
        if (this.isRouteTransferListening) {
            try {
                BroadcastReceiver broadcastReceiver = this.routeTransferReceiver;
                if (broadcastReceiver != null) {
                    unregisterReceiver(broadcastReceiver);
                    this.routeTransferReceiver = null;
                }
            } catch (Exception unused) {
            }
            this.isRouteTransferListening = false;
        }
    }

    private void stopSteeringLogMonitoring() {
        if (this.isSteeringMonitoring) {
            this.isSteeringMonitoring = false;
            Process process = this.steeringLogcatProcess;
            if (process != null) {
                process.destroy();
                this.steeringLogcatProcess = null;
            }
            Thread thread = this.steeringLogThread;
            if (thread != null) {
                try {
                    thread.join(1000L);
                } catch (InterruptedException unused) {
                }
                this.steeringLogThread = null;
            }
        }
    }

    private void triggerReturnAppFromPoiDelivery() {
        if (SharedPreferencesUtils.isReturnAppEnabled(this) && !isAnyAmapEnabledForVoiceNav()) {
            Intent intent = new Intent(this, (Class<?>) ReturnAppService.class);
            intent.putExtra(ReturnAppService.EXTRA_DELAY_MS, Math.max(0, SharedPreferencesUtils.getReturnAppDelaySeconds(this)) * 1000);
            startForegroundService(intent);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mainHandler = new Handler(Looper.getMainLooper());
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification());
        this.isForegroundStarted = true;
        new Thread(new v(this, 0), "feature-check").start();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        isServiceRunning = false;
        stopLogMonitoring();
        stopRouteTransferListening();
        stopNavStatusListening();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        if (!this.isForegroundStarted) {
            createNotificationChannel();
            startForeground(NOTIFICATION_ID, createNotification());
            this.isForegroundStarted = true;
        }
        if (intent != null) {
            String action = intent.getAction();
            if (ACTION_START_LOG_MONITORING.equals(action)) {
                if (!this.isMonitoring) {
                    startLogMonitoring();
                }
            } else if (ACTION_STOP_LOG_MONITORING.equals(action)) {
                if (this.isMonitoring) {
                    stopLogMonitoring();
                }
            } else if (ACTION_START_ROUTE_TRANSFER.equals(action)) {
                if (!this.isRouteTransferListening) {
                    startRouteTransferListening();
                }
            } else if (ACTION_STOP_ROUTE_TRANSFER.equals(action)) {
                if (this.isRouteTransferListening) {
                    stopRouteTransferListening();
                }
            } else if (ACTION_UPDATE_STEERING_FLYING.equals(action)) {
                if (!SharedPreferencesUtils.isSteeringWheelExtScreenEnabled(this)) {
                    stopSteeringLogMonitoring();
                } else if (this.isMonitoring && !this.isSteeringMonitoring) {
                    startSteeringLogMonitoring();
                }
            }
        }
        return 1;
    }
}
