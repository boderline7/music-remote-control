package com.navi.tool.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import com.navi.tool.MainActivity;
import com.navi.tool.amapflying.util.kugou.KugouMusicManager;
import com.navi.tool.amapflying.util.kuwo.KuwoMusicManager;
import com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager;
import com.navi.tool.amapflying.util.netease.NeteaseMusicManager;
import com.navi.tool.amapflying.util.qq.QQMusicManager;
import com.navi.tool.protocol.AmapBroadcastReceiver;
import com.navi.tool.service.MobileHudBluetoothServer;
import com.navi.tool.utils.BroadcastUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class MobileHudService extends Service implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String ACTION_AMAP_CROSS_MAP_IMAGE = "com.navi.tool.action.AMAP_CROSS_MAP_IMAGE";
    private static final String ACTION_CACHE_HUD_PHONE_NOTIFICATION = "com.navi.tool.action.CACHE_HUD_PHONE_NOTIFICATION";
    private static final String ACTION_EXTERNAL_TRAFFIC_LIGHT = "NAVITOOL_STANDARD_BROADCAST_SEND";
    private static final String CHANNEL_ID = "mobile_hud_service_channel";
    public static final String CONNECTION_MODE_BLUETOOTH = "bluetooth";
    public static final String CONNECTION_MODE_WIFI = "wifi";
    private static final long EXTERNAL_TRAFFIC_LIGHT_TTL_MS = 3000;
    private static final String EXTRA_CROSS_MAP = "EXTRA_CROSS_MAP";
    private static final String EXTRA_CROSS_MAP_IMAGE_BASE64 = "image_base64";
    private static final String EXTRA_CROSS_MAP_IMAGE_HEIGHT = "image_height";
    private static final String EXTRA_CROSS_MAP_IMAGE_MIME = "image_mime";
    private static final String EXTRA_CROSS_MAP_IMAGE_WIDTH = "image_width";
    private static final String EXTRA_CROSS_MAP_REVISION = "revision";
    private static final String EXTRA_EXTERNAL_TRAFFIC_LIGHT = "traffic_light";
    private static final String EXTRA_HUD_PHONE_NOTIFICATION_PAYLOAD = "extra_hud_phone_notification_payload";
    private static final long GPS_HEADING_VALID_MS = 5000;
    private static final long NAVI_TRAFFIC_LIGHT_TTL_MS = 3000;
    private static final Pattern NOTIFICATION_COUNT_PATTERN = Pattern.compile("(\\d+)");
    private static final int NOTIFICATION_ID = 10120;
    private static final String PHONE_NOTIFICATION_CHANNEL_ID = "phone_relay_channel";
    public static final String PREF_CONNECTION_MODE = "mobile_hud_connection_mode";
    public static final String PREF_ENABLED = "mobile_hud_enabled";
    public static final String PREF_NIGHT_BRIGHTNESS = "mobile_hud_night_brightness";
    public static final String PREF_PHONE_BRIGHTNESS = "mobile_hud_phone_brightness";
    public static final String PREF_PHONE_NOTIFICATION_ENABLED = "mobile_hud_phone_notification_enabled";
    public static final String PREF_SHOW_ETA = "mobile_hud_show_eta";
    public static final String PREF_SHOW_FUEL = "mobile_hud_show_fuel";
    public static final String PREF_SHOW_GEAR = "mobile_hud_show_gear";
    public static final String PREF_SHOW_LANE = "mobile_hud_show_lane";
    public static final String PREF_SHOW_LANE_ANIMATION = "mobile_hud_show_lane_animation";
    public static final String PREF_SHOW_LYRIC = "mobile_hud_show_lyric";
    public static final String PREF_SHOW_PHONE_NOTIFICATION = "mobile_hud_show_phone_notification";
    public static final String PREF_SHOW_RANGE = "mobile_hud_show_range";
    public static final String PREF_SHOW_SOC = "mobile_hud_show_soc";
    public static final String PREF_SHOW_SPEED = "mobile_hud_show_speed";
    public static final String PREF_SHOW_TMC = "mobile_hud_show_tmc";
    public static final String PREF_SHOW_TRAFFIC_LIGHT = "mobile_hud_show_traffic_light";
    public static final String PREF_TEXT_COLOR = "mobile_hud_text_color";
    public static final String PREF_USE_BAIDU_SOURCE = "mobile_hud_use_baidu_source";
    public static final String PREF_USE_EXTERNAL_TRAFFIC_LIGHT = "mobile_hud_use_external_traffic_light";
    private static final String TAG = "MobileHudService";
    public static final String TEXT_COLOR_BLUE = "blue";
    public static final String TEXT_COLOR_WHITE = "white";
    private static final String TYPE_MOBILE_HUD_CONFIG = "mobile_hud_config";
    private static final String TYPE_MOBILE_HUD_CROSS_MAP_ASSET = "mobile_hud_cross_map_asset";
    private static final String TYPE_PHONE_BATTERY = "battery";
    private static final String TYPE_PHONE_NOTIFICATION = "phone_notification";
    private static final String TYPE_PHONE_NOTIFICATION_CONFIG = "phone_notification_config";
    private static final String TYPE_PHONE_TIRE = "tire";
    private MobileHudBluetoothServer bluetoothServer;
    private boolean gpsHeadingRunning;
    private LocationListener gpsLocationListener;
    private LocationManager gpsLocationManager;
    private boolean isNightMode;
    private SharedPreferences prefs;
    private o4.c repo;
    private Handler sendHandler;
    private q4.h transferManager;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final HandlerThread mSendThread = new HandlerThread("hud-send");
    private final androidx.lifecycle.h0 simpleStringObserver = new e0(this);
    private final Runnable sendRunnable = new d0(this, 1);
    private final Runnable transitionSyncRunnable = new d0(this, 2);
    private final Runnable phoneNotificationConfigSyncRunnable = new d0(this, 3);
    private final Runnable hudPhoneNotificationExpireRunnable = new d0(this, 4);
    private final q4.f wifiPhoneNotificationListener = new q4.f() { // from class: com.navi.tool.service.MobileHudService.1
        @Override // q4.f
        public /* bridge */ /* synthetic */ void onDisconnected(String str) {
        }

        @Override // q4.f
        public void onMessageReceived(String str) {
            MobileHudService.this.handleIncomingTransferMessage(str, MobileHudService.CONNECTION_MODE_WIFI);
        }
    };
    private final BroadcastReceiver crossMapReceiver = new BroadcastReceiver() { // from class: com.navi.tool.service.MobileHudService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MobileHudService.this.handleCrossMapBroadcast(intent);
        }
    };
    private final BroadcastReceiver externalLightReceiver = new BroadcastReceiver() { // from class: com.navi.tool.service.MobileHudService.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                intent.getAction();
            }
            if (intent != null) {
                intent.getStringExtra(MobileHudService.EXTRA_EXTERNAL_TRAFFIC_LIGHT);
            }
            MobileHudService.this.handleExternalTrafficLightBroadcast(intent);
        }
    };
    private String lastGuideNextRoadName = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastGuideCurRoadName = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastGuideSegmentRemainDistance = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastGuideRouteRemainDistance = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastGuideEtaText = s5.h0.FRAGMENT_ENCODE_SET;
    private int lastGuideTurnIcon = -1;
    private String lastGuideSapaName = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastGuideSapaDistance = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastGuideNextSapaName = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastGuideNextSapaDistance = s5.h0.FRAGMENT_ENCODE_SET;
    private JSONArray lastExternalTrafficLightArray = new JSONArray();
    private int lastExternalTrafficLightDist = -1;
    private long lastExternalTrafficLightUpdatedAt = 0;
    private boolean externalLightReceiverRegistered = false;
    private String lastGuideLimitedSpeed = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastLaneDriveWay = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastLyric = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastTrafficLightSource = s5.h0.FRAGMENT_ENCODE_SET;
    private JSONArray lastTrafficLightArray = new JSONArray();
    private JSONArray lastTmcSegments = new JSONArray();
    private int lastTmcDrivenPercent = 0;
    private boolean lastTmcEnabled = false;
    private long lastNaviTrafficLightUpdatedAt = 0;
    private String lastHudPhoneNotificationText = s5.h0.FRAGMENT_ENCODE_SET;
    private long lastHudPhoneNotificationExpireAt = 0;
    private boolean lastCrossMapVisible = false;
    private long lastCrossMapRevision = 0;
    private String lastCrossMapImageBase64 = s5.h0.FRAGMENT_ENCODE_SET;
    private boolean baiduCrossMapActive = false;
    private boolean lastBaiduInNavi = false;
    private float lastAmapHeadingDegrees = Float.NaN;
    private long lastAmapHeadingUpdatedAt = 0;
    private float lastGpsHeadingDegrees = Float.NaN;
    private long lastGpsHeadingUpdatedAt = 0;
    private String currentTransportMode = CONNECTION_MODE_BLUETOOTH;
    private int managedMusicSource = -1;

    /* renamed from: com.navi.tool.service.MobileHudService$4, reason: invalid class name */
    public class AnonymousClass4 implements MobileHudBluetoothServer.ConnectionListener {
        public AnonymousClass4() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onClientConnected$0() {
            MobileHudService.this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
            MobileHudService.this.sendCurrentCrossMapAsset();
            MobileHudService.this.scheduleSend();
            MobileHudService.this.schedulePhoneNotificationConfigSync();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void lambda$onClientDisconnected$1() {
            MobileHudService.this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
            MobileHudService.this.repo.f7947o.i("--");
        }

        @Override // com.navi.tool.service.MobileHudBluetoothServer.ConnectionListener
        public void onClientConnected() {
            MobileHudService.this.mainHandler.post(new f0(this, 0));
        }

        @Override // com.navi.tool.service.MobileHudBluetoothServer.ConnectionListener
        public void onClientDisconnected() {
            MobileHudService.this.mainHandler.post(new f0(this, 1));
        }
    }

    private String buildForegroundSummary() {
        boolean z6 = this.prefs.getBoolean(PREF_ENABLED, false);
        boolean z7 = this.prefs.getBoolean(PREF_PHONE_NOTIFICATION_ENABLED, false);
        return (z6 && z7) ? "后台同步HUD数据并接收手机通知" : z6 ? "后台同步HUD数据到手机助手" : z7 ? "后台接收手机通知并显示到车机" : "后台连接待命中";
    }

    private String buildHudPhoneNotificationSummary(String str, String str2, String str3) {
        String safe = safe(str2);
        if (isBlank(safe)) {
            safe = str;
        }
        return str + "：收到" + safe + "的" + extractNotificationCount(str3) + "条信息";
    }

    public static void cacheHudPhoneNotification(Context context, String str) {
        if (context == null || str == null || str.trim().isEmpty()) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) MobileHudService.class);
        intent.setAction(ACTION_CACHE_HUD_PHONE_NOTIFICATION);
        intent.putExtra(EXTRA_HUD_PHONE_NOTIFICATION_PAYLOAD, str);
        context.startForegroundService(intent);
    }

    private void cacheHudPhoneNotification(JSONObject jSONObject) {
        String buildHudPhoneNotificationSummary = buildHudPhoneNotificationSummary(safe(jSONObject.optString("appName")), safe(jSONObject.optString("title")), safe(jSONObject.optString("text")));
        if (isBlank(buildHudPhoneNotificationSummary)) {
            return;
        }
        this.lastHudPhoneNotificationText = buildHudPhoneNotificationSummary;
        this.lastHudPhoneNotificationExpireAt = System.currentTimeMillis() + getPhoneNotificationDismissDurationMs();
        this.mainHandler.removeCallbacks(this.hudPhoneNotificationExpireRunnable);
        this.mainHandler.postDelayed(this.hudPhoneNotificationExpireRunnable, Math.max(0L, this.lastHudPhoneNotificationExpireAt - System.currentTimeMillis()));
        scheduleSend();
    }

    private int clampBrightness(int i7) {
        return Math.max(0, Math.min(100, i7));
    }

    private void clearCrossMapAsset() {
        this.lastCrossMapVisible = false;
        this.lastCrossMapRevision = 0L;
        this.lastCrossMapImageBase64 = s5.h0.FRAGMENT_ENCODE_SET;
    }

    private void clearExternalTrafficLightState() {
        this.lastExternalTrafficLightArray = new JSONArray();
        this.lastExternalTrafficLightDist = -1;
        this.lastExternalTrafficLightUpdatedAt = 0L;
    }

    private void clearGuideState() {
        this.lastGuideNextRoadName = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastGuideCurRoadName = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastGuideSegmentRemainDistance = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastGuideRouteRemainDistance = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastGuideEtaText = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastGuideTurnIcon = -1;
        this.lastGuideSapaName = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastGuideSapaDistance = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastGuideNextSapaName = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastGuideNextSapaDistance = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastGuideLimitedSpeed = s5.h0.FRAGMENT_ENCODE_SET;
    }

    private void clearHeadingState() {
        this.lastAmapHeadingDegrees = Float.NaN;
        this.lastAmapHeadingUpdatedAt = 0L;
    }

    private void clearHudPhoneNotification() {
        this.mainHandler.removeCallbacks(this.hudPhoneNotificationExpireRunnable);
        this.lastHudPhoneNotificationText = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastHudPhoneNotificationExpireAt = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHudPhoneNotificationAndSend() {
        if (this.lastHudPhoneNotificationExpireAt <= 0) {
            return;
        }
        clearHudPhoneNotification();
        scheduleSend();
    }

    private void clearTmcState() {
        this.lastTmcSegments = new JSONArray();
        this.lastTmcDrivenPercent = 0;
        this.lastTmcEnabled = false;
    }

    private void clearTrafficLightState() {
        this.lastTrafficLightSource = s5.h0.FRAGMENT_ENCODE_SET;
        this.lastTrafficLightArray = new JSONArray();
        this.lastNaviTrafficLightUpdatedAt = 0L;
    }

    private void createChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "车萝卜HUD服务", 2);
        notificationChannel.setDescription("用于后台同步车机HUD数据到手机助手");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
            NotificationChannel notificationChannel2 = new NotificationChannel(PHONE_NOTIFICATION_CHANNEL_ID, "手机通知转发", 4);
            notificationChannel2.setDescription("用于显示来自手机助手转发的通知");
            notificationManager.createNotificationChannel(notificationChannel2);
        }
    }

    private Notification createNotification() {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592);
        e0.v vVar = new e0.v(this, CHANNEL_ID);
        vVar.f6327e = e0.v.c("手机连接服务");
        vVar.f6328f = e0.v.c(buildForegroundSummary());
        vVar.f6341s.icon = g4.o.ic_launcher_foreground;
        vVar.f6329g = activity;
        vVar.d(2, true);
        vVar.f6331i = -1;
        return vVar.a();
    }

    private void expireExternalTrafficLightIfNeeded() {
        if (this.lastExternalTrafficLightUpdatedAt > 0 && SystemClock.elapsedRealtime() - this.lastExternalTrafficLightUpdatedAt > PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS) {
            clearExternalTrafficLightState();
        }
    }

    private void expireNaviTrafficLightIfNeeded() {
        if (h4.i.SOURCE_NAVI.equals(this.lastTrafficLightSource) && this.lastNaviTrafficLightUpdatedAt > 0 && SystemClock.elapsedRealtime() - this.lastNaviTrafficLightUpdatedAt > PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS) {
            clearTrafficLightState();
            this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
        }
    }

    private int extractNotificationCount(String str) {
        String safe = safe(str);
        if (!isBlank(safe)) {
            Matcher matcher = NOTIFICATION_COUNT_PATTERN.matcher(safe);
            if (matcher.find()) {
                try {
                    int parseInt = Integer.parseInt(matcher.group(1));
                    if (parseInt > 0) {
                        return parseInt;
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        return 1;
    }

    private String formatArriveTime(int i7) {
        if (i7 <= 0) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date((i7 * 1000) + System.currentTimeMillis()));
    }

    private String formatBaiduEta(int i7) {
        if (i7 <= 0) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        return ((i7 + 59) / 60) + "分钟";
    }

    private String formatKm(String str) {
        try {
            float parseFloat = Float.parseFloat(str);
            if (parseFloat >= 1000.0f) {
                return String.format(Locale.US, "%.1f公里", Float.valueOf(parseFloat / 1000.0f));
            }
            return Math.round(parseFloat) + "米";
        } catch (Exception unused) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
    }

    private String formatSapaDistance(int i7) {
        if (i7 < 0) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        if (i7 >= 1000) {
            return String.format(Locale.US, "%.1f公里", Double.valueOf(i7 / 1000.0d));
        }
        return i7 + "米";
    }

    private String getConnectionMode() {
        return CONNECTION_MODE_WIFI.equals(this.prefs.getString(PREF_CONNECTION_MODE, CONNECTION_MODE_BLUETOOTH)) ? CONNECTION_MODE_WIFI : CONNECTION_MODE_BLUETOOTH;
    }

    private String getPhoneNotificationConnectionMode() {
        return CONNECTION_MODE_WIFI.equals(this.prefs.getString(PhoneNotificationRelayService.PREF_CONNECTION_MODE, CONNECTION_MODE_BLUETOOTH)) ? CONNECTION_MODE_WIFI : CONNECTION_MODE_BLUETOOTH;
    }

    private long getPhoneNotificationDismissDurationMs() {
        SharedPreferences sharedPreferences = this.prefs;
        int i7 = sharedPreferences != null ? sharedPreferences.getInt(PhoneNotificationRelayService.PREF_OVERLAY_DISMISS_SECONDS, 5) : 5;
        if (i7 < 3) {
            i7 = 3;
        }
        if (i7 > 30) {
            i7 = 30;
        }
        return i7 * 1000;
    }

    private String getTextColorMode() {
        return TEXT_COLOR_BLUE.equals(this.prefs.getString(PREF_TEXT_COLOR, TEXT_COLOR_WHITE)) ? TEXT_COLOR_BLUE : TEXT_COLOR_WHITE;
    }

    private void handleConnectionModeChanged() {
        String connectionMode = getConnectionMode();
        if (connectionMode.equals(this.currentTransportMode)) {
            return;
        }
        sendTransportState(this.currentTransportMode, false);
        this.currentTransportMode = connectionMode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCrossMapBroadcast(Intent intent) {
        if (intent == null) {
            return;
        }
        if (intent.getIntExtra(EXTRA_CROSS_MAP, 0) != 1) {
            clearCrossMapAsset();
            this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
            scheduleSend();
            return;
        }
        String safe = safe(intent.getStringExtra(EXTRA_CROSS_MAP_IMAGE_BASE64));
        if (isBlank(safe)) {
            return;
        }
        long longExtra = intent.getLongExtra(EXTRA_CROSS_MAP_REVISION, System.currentTimeMillis());
        this.lastCrossMapVisible = true;
        this.lastCrossMapRevision = longExtra;
        this.lastCrossMapImageBase64 = safe;
        sendCurrentCrossMapAsset();
        this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
        scheduleSend();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleExternalTrafficLightBroadcast(Intent intent) {
        int i7;
        if (intent == null) {
            return;
        }
        try {
            String stringExtra = intent.getStringExtra(EXTRA_EXTERNAL_TRAFFIC_LIGHT);
            if (isBlank(stringExtra)) {
                clearExternalTrafficLightState();
                scheduleSend();
                return;
            }
            JSONObject jSONObject = new JSONObject(stringExtra);
            boolean optBoolean = jSONObject.optBoolean("hasLight", false);
            JSONArray optJSONArray = jSONObject.optJSONArray("crossTlis");
            if (optBoolean && optJSONArray != null && optJSONArray.length() != 0) {
                JSONArray jSONArray = new JSONArray();
                HashSet hashSet = new HashSet();
                int i8 = -1;
                for (int i9 = 0; i9 < optJSONArray.length(); i9++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i9);
                    if (optJSONObject != null) {
                        if (i8 < 0) {
                            i8 = optJSONObject.optInt("dist", -1);
                        }
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("phases");
                        if (optJSONArray2 != null) {
                            for (int i10 = 0; i10 < optJSONArray2.length(); i10++) {
                                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i10);
                                if (optJSONObject2 != null) {
                                    String optString = optJSONObject2.optString("direction", "unknown");
                                    int i11 = 1;
                                    if (PhoneNotificationRelayService.OVERLAY_POSITION_LEFT.equals(optString)) {
                                        i7 = 1;
                                    } else if ("straight".equals(optString)) {
                                        i7 = 2;
                                    }
                                    if (hashSet.add(Integer.valueOf(i7))) {
                                        String optString2 = optJSONObject2.optString("curColor", "red");
                                        int max = Math.max(0, optJSONObject2.optInt("curCountDown", 0));
                                        if (max > 0) {
                                            if (!"green".equals(optString2) && !"yellow".equals(optString2)) {
                                                i11 = 0;
                                            }
                                            JSONObject jSONObject2 = new JSONObject();
                                            jSONObject2.put("direction", i7);
                                            jSONObject2.put("status", i11);
                                            jSONObject2.put("countdown", max);
                                            jSONArray.put(jSONObject2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (jSONArray.length() == 0) {
                    clearExternalTrafficLightState();
                    scheduleSend();
                    return;
                } else {
                    this.lastExternalTrafficLightArray = jSONArray;
                    this.lastExternalTrafficLightDist = i8;
                    this.lastExternalTrafficLightUpdatedAt = SystemClock.elapsedRealtime();
                    scheduleSend();
                    return;
                }
            }
            clearExternalTrafficLightState();
            scheduleSend();
        } catch (Throwable unused) {
        }
    }

    private void handleHudPhoneNotificationIntent(String str) {
        if (this.prefs.getBoolean(PREF_ENABLED, false) && this.prefs.getBoolean(PREF_PHONE_NOTIFICATION_ENABLED, false) && this.prefs.getBoolean(PREF_SHOW_PHONE_NOTIFICATION, true) && str != null && !str.trim().isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (TYPE_PHONE_NOTIFICATION.equals(jSONObject.optString("type"))) {
                    cacheHudPhoneNotification(jSONObject);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005f, code lost:
    
        if (r6.trim().isEmpty() != false) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0054 A[Catch: Exception -> 0x0069, TryCatch #0 {Exception -> 0x0069, blocks: (B:3:0x0003, B:7:0x0017, B:11:0x0025, B:13:0x002b, B:17:0x0035, B:20:0x004b, B:22:0x0054, B:24:0x0063), top: B:2:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean handleIncomingPhoneBattery(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "charging"
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Exception -> L69
            r2.<init>(r6)     // Catch: java.lang.Exception -> L69
            java.lang.String r6 = "battery"
            java.lang.String r3 = "type"
            java.lang.String r3 = r2.optString(r3)     // Catch: java.lang.Exception -> L69
            boolean r6 = r6.equals(r3)     // Catch: java.lang.Exception -> L69
            if (r6 != 0) goto L17
            return r1
        L17:
            java.lang.String r6 = "level"
            r3 = -1
            int r6 = r2.optInt(r6, r3)     // Catch: java.lang.Exception -> L69
            r3 = 1
            if (r6 < 0) goto L68
            r4 = 100
            if (r6 > r4) goto L68
            int r4 = r2.optInt(r0, r1)     // Catch: java.lang.Exception -> L69
            if (r4 == r3) goto L34
            boolean r0 = r2.optBoolean(r0, r1)     // Catch: java.lang.Exception -> L69
            if (r0 == 0) goto L32
            goto L34
        L32:
            r0 = r1
            goto L35
        L34:
            r0 = r3
        L35:
            o4.c r2 = r5.repo     // Catch: java.lang.Exception -> L69
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L69
            r4.<init>()     // Catch: java.lang.Exception -> L69
            r4.append(r6)     // Catch: java.lang.Exception -> L69
            java.lang.String r6 = " %"
            r4.append(r6)     // Catch: java.lang.Exception -> L69
            if (r0 == 0) goto L49
            java.lang.String r6 = "（充电）"
            goto L4b
        L49:
            java.lang.String r6 = ""
        L4b:
            r4.append(r6)     // Catch: java.lang.Exception -> L69
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Exception -> L69
            if (r6 == 0) goto L61
            r2.getClass()     // Catch: java.lang.Exception -> L69
            java.lang.String r0 = r6.trim()     // Catch: java.lang.Exception -> L69
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Exception -> L69
            if (r0 == 0) goto L63
        L61:
            java.lang.String r6 = "--"
        L63:
            androidx.lifecycle.g0 r0 = r2.f7947o     // Catch: java.lang.Exception -> L69
            r0.i(r6)     // Catch: java.lang.Exception -> L69
        L68:
            return r3
        L69:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.MobileHudService.handleIncomingPhoneBattery(java.lang.String):boolean");
    }

    private boolean handleIncomingPhoneClipboard(String str) {
        ClipboardManager clipboardManager;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!"clipboard".equals(jSONObject.optString("type"))) {
                return false;
            }
            String optString = jSONObject.optString("text");
            if (optString == null || optString.isEmpty() || (clipboardManager = (ClipboardManager) getSystemService("clipboard")) == null) {
                return true;
            }
            clipboardManager.setPrimaryClip(ClipData.newPlainText("phone", optString));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean handleIncomingPhoneTire(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!TYPE_PHONE_TIRE.equals(jSONObject.optString("type"))) {
                return false;
            }
            if (o4.z.e(this)) {
                return true;
            }
            this.repo.t(this, jSONObject);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIncomingTransferMessage(String str, String str2) {
        if (str == null || str.trim().isEmpty() || handleIncomingPhoneBattery(str) || handleIncomingPhoneTire(str) || handleIncomingPhoneClipboard(str) || !this.prefs.getBoolean(PREF_PHONE_NOTIFICATION_ENABLED, false)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (TYPE_PHONE_NOTIFICATION.equals(jSONObject.optString("type")) && shouldAcceptPhoneNotificationFrom(str2)) {
                if (this.prefs.getBoolean(PREF_SHOW_PHONE_NOTIFICATION, true)) {
                    cacheHudPhoneNotification(jSONObject);
                }
                PhoneNotificationRelayService.showForwardedPayload(this, jSONObject.toString());
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTransitionWindowExpired() {
        syncTransportMode();
        if (shouldRun(this.prefs)) {
            return;
        }
        stopSelf();
    }

    private boolean hasAmapHeading() {
        return !Float.isNaN(this.lastAmapHeadingDegrees) && this.lastAmapHeadingUpdatedAt > 0;
    }

    private boolean hasGpsHeading() {
        return !Float.isNaN(this.lastGpsHeadingDegrees) && this.lastGpsHeadingUpdatedAt > 0 && System.currentTimeMillis() - this.lastGpsHeadingUpdatedAt <= GPS_HEADING_VALID_MS;
    }

    private boolean hasLocationPermission() {
        return g0.g.a(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || g0.g.a(this, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean isGuideInfoCleared(h4.b bVar) {
        if (bVar == null) {
            return true;
        }
        return isBlank(bVar.f6726a) && isBlank(bVar.f6737l) && isBlank(bVar.f6727b) && isBlank(bVar.f6728c) && isBlank(bVar.f6730e) && isBlank(bVar.f6732g) && isBlank(bVar.f6733h) && isBlank(bVar.f6734i) && isBlank(bVar.f6735j) && bVar.f6731f < 1;
    }

    private boolean isPhoneNotificationTransitionActive() {
        return System.currentTimeMillis() < this.prefs.getLong(PhoneNotificationRelayService.PREF_CONNECTION_MODE_TRANSITION_UNTIL_MS, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(String str) {
        scheduleSend();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(String str) {
        handleIncomingTransferMessage(str, CONNECTION_MODE_BLUETOOTH);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lambda$onTmcInfo$2(h4.f fVar, h4.f fVar2) {
        return Integer.compare(fVar.f6742a, fVar2.f6742a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$refreshManagedMusicState$3(int i7) {
        switch (i7) {
            case 0:
                QQMusicManager.getInstance(this).queryCurrentState();
                break;
            case 1:
                KuwoMusicManager.getInstance(this).refreshState();
                break;
            case 2:
                KugouMusicManager.getInstance(this).refreshState();
                break;
            case 3:
                NeteaseMusicManager.getInstance(this).refreshState();
                break;
            case 4:
                NewKuwoMusicManager.getInstance(this).refreshState();
                break;
            case 5:
                k4.a.c(this).f();
                break;
            case 6:
                l4.e.b(this).c();
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float normalizeDegrees(float f7) {
        float f8 = f7 % 360.0f;
        return f8 < CropImageView.DEFAULT_ASPECT_RATIO ? f8 + 360.0f : f8;
    }

    private void refreshManagedMusicState(int i7) {
        this.mainHandler.postDelayed(new h0.n(i7, 5, this), 500L);
    }

    private void registerCrossMapReceiver() {
        try {
            registerReceiver(this.crossMapReceiver, new IntentFilter(ACTION_AMAP_CROSS_MAP_IMAGE));
        } catch (Exception unused) {
        }
    }

    private void registerObservers() {
        this.repo.f7938f.f(this.simpleStringObserver);
        this.repo.f7939g.f(this.simpleStringObserver);
        this.repo.f7941i.f(this.simpleStringObserver);
        this.repo.f7942j.f(this.simpleStringObserver);
        this.repo.I.f(this.simpleStringObserver);
    }

    private String safe(String str) {
        if (str == null) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        String trim = str.trim();
        return "null".equalsIgnoreCase(trim) ? s5.h0.FRAGMENT_ENCODE_SET : trim;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void schedulePhoneNotificationConfigSync() {
        this.mainHandler.removeCallbacks(this.phoneNotificationConfigSyncRunnable);
        this.mainHandler.post(this.phoneNotificationConfigSyncRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleSend() {
        Handler handler = this.sendHandler;
        if (handler == null) {
            return;
        }
        handler.removeCallbacks(this.sendRunnable);
        if (this.prefs.getBoolean(PREF_ENABLED, false)) {
            this.sendHandler.post(this.sendRunnable);
        }
    }

    private void scheduleTransitionSyncIfNeeded() {
        this.mainHandler.removeCallbacks(this.transitionSyncRunnable);
        long j7 = this.prefs.getLong(PhoneNotificationRelayService.PREF_CONNECTION_MODE_TRANSITION_UNTIL_MS, 0L) - System.currentTimeMillis();
        if (j7 > 0) {
            this.mainHandler.postDelayed(this.transitionSyncRunnable, j7 + 50);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCurrentCrossMapAsset() {
        if (!this.lastCrossMapVisible || isBlank(this.lastCrossMapImageBase64) || this.lastCrossMapRevision <= 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", TYPE_MOBILE_HUD_CROSS_MAP_ASSET);
            jSONObject.put("timestamp", System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(EXTRA_CROSS_MAP_REVISION, this.lastCrossMapRevision);
            jSONObject2.put("imageBase64", this.lastCrossMapImageBase64);
            jSONObject.put("data", jSONObject2);
            String jSONObject3 = jSONObject.toString();
            sendPayload(jSONObject3, CONNECTION_MODE_WIFI);
            sendPayload(jSONObject3, CONNECTION_MODE_BLUETOOTH);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x040d, code lost:
    
        if (r22.lastTmcEnabled != false) goto L248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x03ee, code lost:
    
        if (r22.lastTmcEnabled != false) goto L236;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02df A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02ee A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0300 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03d9 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x041d A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x043e A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0447 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x049d A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x04aa A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x04c4 A[ADDED_TO_REGION, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x04c5 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0420 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x03dc A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0396 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x03a2 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x03af A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03a5 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0247 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01f0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0213 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0233 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0239 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x023d A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0241 A[Catch: Exception -> 0x04cd, TryCatch #0 {Exception -> 0x04cd, blocks: (B:11:0x001f, B:13:0x007a, B:16:0x0127, B:17:0x0137, B:19:0x013e, B:20:0x014e, B:22:0x0155, B:23:0x0165, B:25:0x016c, B:26:0x017c, B:28:0x0183, B:29:0x0193, B:31:0x0198, B:37:0x01a9, B:39:0x01ad, B:40:0x01bd, B:43:0x01c6, B:44:0x01d3, B:47:0x01dc, B:48:0x01e9, B:51:0x01f2, B:52:0x01fa, B:55:0x0203, B:56:0x020c, B:58:0x0213, B:59:0x021c, B:62:0x0225, B:63:0x022e, B:66:0x0235, B:68:0x0239, B:70:0x023d, B:72:0x0241, B:73:0x0243, B:74:0x024a, B:77:0x026b, B:78:0x0272, B:81:0x027b, B:82:0x0286, B:85:0x028f, B:86:0x0296, B:89:0x029f, B:90:0x02aa, B:93:0x02b6, B:96:0x02bf, B:97:0x02c8, B:100:0x02d1, B:101:0x02d8, B:103:0x02df, B:104:0x02e3, B:106:0x02ee, B:112:0x0305, B:116:0x031a, B:117:0x0328, B:122:0x033d, B:126:0x034c, B:129:0x0353, B:130:0x0357, B:133:0x03d9, B:134:0x03de, B:137:0x03e7, B:141:0x03f2, B:142:0x03fd, B:145:0x0406, B:149:0x0411, B:150:0x0418, B:152:0x041d, B:153:0x0422, B:156:0x042d, B:157:0x0434, B:159:0x043e, B:160:0x0440, B:162:0x0447, B:163:0x044b, B:166:0x0473, B:168:0x049d, B:169:0x04a3, B:171:0x04aa, B:172:0x04ae, B:175:0x04c5, B:177:0x04cb, B:184:0x0430, B:186:0x0420, B:187:0x0414, B:188:0x040b, B:191:0x03f5, B:192:0x03ec, B:194:0x03f8, B:195:0x03dc, B:197:0x0347, B:202:0x0332, B:206:0x0362, B:208:0x0366, B:209:0x0373, B:211:0x0378, B:213:0x0380, B:216:0x038f, B:218:0x0396, B:221:0x039d, B:223:0x03a2, B:224:0x03aa, B:226:0x03af, B:227:0x03b3, B:229:0x03a5, B:236:0x03bd, B:237:0x03c1, B:239:0x03c6, B:240:0x03ce, B:241:0x03c9, B:246:0x02d4, B:249:0x02c6, B:252:0x02a6, B:254:0x0292, B:256:0x0282, B:258:0x026e, B:260:0x0247, B:262:0x022c, B:266:0x021a, B:268:0x020a, B:272:0x01e7, B:275:0x01d1, B:277:0x01b4, B:279:0x01bb), top: B:10:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void sendHudState() {
        /*
            Method dump skipped, instructions count: 1230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.MobileHudService.sendHudState():void");
    }

    private boolean sendPayload(String str) {
        return sendPayload(str, this.currentTransportMode);
    }

    private boolean sendPayload(String str, String str2) {
        if (!CONNECTION_MODE_WIFI.equals(str2)) {
            return this.bluetoothServer.sendText(str);
        }
        q4.h hVar = this.transferManager;
        String str3 = "hud";
        String str4 = hVar.f8485u;
        if (str4 == null) {
            return true;
        }
        hVar.f8467c.execute(new o4.w(hVar, str3, str4, str, 4));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPhoneNotificationConfig() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", TYPE_PHONE_NOTIFICATION_CONFIG);
            jSONObject.put("timestamp", System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("enabled", this.prefs.getBoolean(PREF_PHONE_NOTIFICATION_ENABLED, false));
            jSONObject2.put("connectionMode", getPhoneNotificationConnectionMode());
            jSONObject.put("data", jSONObject2);
            String jSONObject3 = jSONObject.toString();
            sendPayload(jSONObject3, CONNECTION_MODE_BLUETOOTH);
            q4.h hVar = this.transferManager;
            String str = "general";
            String str2 = hVar.f8482r;
            if (str2 != null) {
                hVar.f8467c.execute(new o4.w(hVar, str, str2, jSONObject3, 4));
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("type", TYPE_MOBILE_HUD_CONFIG);
            jSONObject4.put("timestamp", System.currentTimeMillis());
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("enabled", this.prefs.getBoolean(PREF_ENABLED, false));
            jSONObject5.put("connectionMode", getConnectionMode());
            jSONObject4.put("data", jSONObject5);
            String jSONObject6 = jSONObject4.toString();
            sendPayload(jSONObject6, CONNECTION_MODE_BLUETOOTH);
            q4.h hVar2 = this.transferManager;
            String str3 = "hud";
            String str4 = hVar2.f8485u;
            if (str4 == null) {
                return;
            }
            hVar2.f8467c.execute(new o4.w(hVar2, str3, str4, jSONObject6, 4));
        } catch (Exception unused) {
        }
    }

    private void sendTransportState(String str, boolean z6) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "mobile_hud_transport_state");
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("transport", str);
            jSONObject.put("active", z6);
            sendPayload(jSONObject.toString(), str);
        } catch (Exception unused) {
        }
    }

    private boolean shouldAcceptPhoneNotificationFrom(String str) {
        if (isPhoneNotificationTransitionActive()) {
            return true;
        }
        return str != null && str.equals(getPhoneNotificationConnectionMode());
    }

    private static boolean shouldRun(Context context) {
        return shouldRun(SharedPreferencesUtils.getAppPrefs(context));
    }

    private static boolean shouldRun(SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean(PREF_ENABLED, false) || sharedPreferences.getBoolean(PREF_PHONE_NOTIFICATION_ENABLED, false);
    }

    public static void start(Context context) {
        context.startForegroundService(new Intent(context, (Class<?>) MobileHudService.class));
    }

    private void startGpsHeading() {
        if (this.gpsHeadingRunning) {
            return;
        }
        try {
            if (this.gpsLocationManager == null) {
                this.gpsLocationManager = (LocationManager) getSystemService("location");
            }
            LocationManager locationManager = this.gpsLocationManager;
            if (locationManager != null && locationManager.isProviderEnabled("gps")) {
                if (this.gpsLocationListener == null) {
                    this.gpsLocationListener = new LocationListener() { // from class: com.navi.tool.service.MobileHudService.5
                        @Override // android.location.LocationListener
                        public void onLocationChanged(Location location) {
                            if (location == null || !location.hasBearing()) {
                                return;
                            }
                            MobileHudService mobileHudService = MobileHudService.this;
                            mobileHudService.lastGpsHeadingDegrees = mobileHudService.normalizeDegrees(location.getBearing());
                            MobileHudService.this.lastGpsHeadingUpdatedAt = System.currentTimeMillis();
                            MobileHudService.this.scheduleSend();
                        }

                        @Override // android.location.LocationListener
                        public void onProviderDisabled(String str) {
                            if ("gps".equals(str)) {
                                MobileHudService.this.stopGpsHeading();
                            }
                        }

                        @Override // android.location.LocationListener
                        public void onProviderEnabled(String str) {
                        }

                        @Override // android.location.LocationListener
                        public void onStatusChanged(String str, int i7, Bundle bundle) {
                        }
                    };
                }
                this.gpsLocationManager.requestLocationUpdates("gps", 1000L, CropImageView.DEFAULT_ASPECT_RATIO, this.gpsLocationListener, Looper.getMainLooper());
                this.gpsHeadingRunning = true;
            }
        } catch (Throwable unused) {
            this.gpsHeadingRunning = false;
        }
    }

    private void startManagedMusicManager(int i7) {
        switch (i7) {
            case 0:
                QQMusicManager.getInstance(this).start();
                break;
            case 1:
                KuwoMusicManager.getInstance(this).start();
                break;
            case 2:
                KugouMusicManager.getInstance(this).start();
                break;
            case 3:
                NeteaseMusicManager.getInstance(this).start();
                break;
            case 4:
                NewKuwoMusicManager.getInstance(this).start();
                break;
            case 5:
                k4.a.c(this).g();
                break;
            case 6:
                l4.e.b(this).d();
                break;
            default:
                this.managedMusicSource = -1;
                break;
        }
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, (Class<?>) MobileHudService.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopGpsHeading() {
        LocationListener locationListener;
        this.gpsHeadingRunning = false;
        LocationManager locationManager = this.gpsLocationManager;
        if (locationManager == null || (locationListener = this.gpsLocationListener) == null) {
            return;
        }
        try {
            locationManager.removeUpdates(locationListener);
        } catch (Throwable unused) {
        }
    }

    private void stopManagedMusicManager() {
        int i7 = this.managedMusicSource;
        this.managedMusicSource = -1;
        switch (i7) {
            case 0:
                QQMusicManager.getInstance(this).stop();
                break;
            case 1:
                KuwoMusicManager.getInstance(this).stop();
                break;
            case 2:
                KugouMusicManager.getInstance(this).stop();
                break;
            case 3:
                NeteaseMusicManager.getInstance(this).stop();
                break;
            case 4:
                NewKuwoMusicManager.getInstance(this).stop();
                break;
            case 5:
                k4.a.c(this).h();
                break;
            case 6:
                l4.e.b(this).e();
                break;
        }
    }

    private void syncBaiduCrossMap() {
        boolean z6 = this.prefs.getBoolean(PREF_USE_BAIDU_SOURCE, false);
        com.navi.tool.baidu.h hVar = com.navi.tool.baidu.h.B;
        if (z6) {
            hVar.getClass();
        }
        if (this.baiduCrossMapActive) {
            this.baiduCrossMapActive = false;
            clearCrossMapAsset();
            this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
        }
    }

    private void syncBaiduSource() {
        BroadcastReceiver broadcastReceiver;
        if (!this.prefs.getBoolean(PREF_USE_BAIDU_SOURCE, false)) {
            com.navi.tool.baidu.e.l(this).w();
            if (!com.navi.tool.baidu.i.f4679b || (broadcastReceiver = com.navi.tool.baidu.i.f4678a) == null) {
                return;
            }
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (Exception unused) {
            }
            com.navi.tool.baidu.i.f4678a = null;
            com.navi.tool.baidu.i.f4679b = false;
            return;
        }
        com.navi.tool.baidu.e l7 = com.navi.tool.baidu.e.l(this);
        if (!l7.f4639f) {
            l7.f4639f = true;
            HandlerThread handlerThread = l7.f4635b;
            if (handlerThread == null || !handlerThread.isAlive()) {
                HandlerThread handlerThread2 = new HandlerThread("baidu-navi-poll");
                l7.f4635b = handlerThread2;
                handlerThread2.start();
                l7.f4636c = new Handler(l7.f4635b.getLooper());
            }
            if (!l7.n() && !l7.f4638e) {
                l7.h();
            }
        }
        com.navi.tool.baidu.i.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncExternalLightSource() {
        boolean z6 = this.prefs.getBoolean(PREF_USE_EXTERNAL_TRAFFIC_LIGHT, false);
        if (this.externalLightReceiverRegistered) {
            try {
                unregisterReceiver(this.externalLightReceiver);
            } catch (Exception unused) {
            }
            this.externalLightReceiverRegistered = false;
        }
        if (!z6) {
            unregisterExternalLightReceiver();
            return;
        }
        try {
            IntentFilter intentFilter = new IntentFilter(ACTION_EXTERNAL_TRAFFIC_LIGHT);
            if (Build.VERSION.SDK_INT >= 33) {
                registerReceiver(this.externalLightReceiver, intentFilter, 2);
            } else {
                registerReceiver(this.externalLightReceiver, intentFilter);
            }
            this.externalLightReceiverRegistered = true;
        } catch (Exception unused2) {
        }
    }

    private void syncGpsHeadingListener() {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences != null && sharedPreferences.getBoolean(PREF_ENABLED, false) && hasLocationPermission()) {
            startGpsHeading();
        } else {
            stopGpsHeading();
        }
    }

    private void syncMusicManager() {
        if (!this.prefs.getBoolean(PREF_ENABLED, false) || !this.prefs.getBoolean(PREF_SHOW_LYRIC, true)) {
            this.lastLyric = s5.h0.FRAGMENT_ENCODE_SET;
            stopManagedMusicManager();
            return;
        }
        int i7 = this.prefs.getInt("desktop_music_source", 0);
        if (this.managedMusicSource != i7) {
            stopManagedMusicManager();
            startManagedMusicManager(i7);
            this.managedMusicSource = i7;
        }
        refreshManagedMusicState(i7);
    }

    public static void syncService(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (shouldRun(applicationContext)) {
            start(applicationContext);
        } else {
            stop(applicationContext);
        }
    }

    private void syncTransportMode() {
        boolean z6 = false;
        boolean z7 = this.prefs.getBoolean(PREF_ENABLED, false);
        boolean z8 = this.prefs.getBoolean(PREF_PHONE_NOTIFICATION_ENABLED, false);
        boolean equals = CONNECTION_MODE_BLUETOOTH.equals(getPhoneNotificationConnectionMode());
        if (z8 && isPhoneNotificationTransitionActive()) {
            z6 = true;
        }
        if ((z7 && CONNECTION_MODE_BLUETOOTH.equals(this.currentTransportMode)) || ((z8 && equals) || z6)) {
            getPhoneNotificationConnectionMode();
            this.bluetoothServer.start();
        } else {
            getPhoneNotificationConnectionMode();
            this.bluetoothServer.stop();
        }
    }

    private void unregisterCrossMapReceiver() {
        try {
            unregisterReceiver(this.crossMapReceiver);
        } catch (Exception unused) {
        }
    }

    private void unregisterExternalLightReceiver() {
        if (this.externalLightReceiverRegistered) {
            try {
                unregisterReceiver(this.externalLightReceiver);
            } catch (Exception unused) {
            }
            this.externalLightReceiverRegistered = false;
            clearExternalTrafficLightState();
            this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
        }
    }

    private void unregisterObservers() {
        this.repo.f7938f.g(this.simpleStringObserver);
        this.repo.f7939g.g(this.simpleStringObserver);
        this.repo.f7941i.g(this.simpleStringObserver);
        this.repo.f7942j.g(this.simpleStringObserver);
        this.repo.I.g(this.simpleStringObserver);
    }

    private void updateHudPhoneNotificationExpiryIfNeeded() {
        if (this.lastHudPhoneNotificationExpireAt <= 0 || System.currentTimeMillis() < this.lastHudPhoneNotificationExpireAt) {
            return;
        }
        clearHudPhoneNotification();
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onBaiduNaviDataChanged(com.navi.tool.baidu.f fVar) {
        boolean z6 = false;
        if (this.prefs.getBoolean(PREF_USE_BAIDU_SOURCE, false) && com.navi.tool.baidu.h.B.f4652a) {
            z6 = true;
        }
        if (this.lastBaiduInNavi && !z6) {
            this.lastGuideEtaText = s5.h0.FRAGMENT_ENCODE_SET;
            this.lastGuideSegmentRemainDistance = s5.h0.FRAGMENT_ENCODE_SET;
            this.lastGuideRouteRemainDistance = s5.h0.FRAGMENT_ENCODE_SET;
            this.lastGuideTurnIcon = -1;
            this.lastGuideNextRoadName = s5.h0.FRAGMENT_ENCODE_SET;
            this.lastGuideCurRoadName = s5.h0.FRAGMENT_ENCODE_SET;
            this.lastGuideSapaName = s5.h0.FRAGMENT_ENCODE_SET;
            this.lastGuideSapaDistance = s5.h0.FRAGMENT_ENCODE_SET;
            this.lastGuideNextSapaName = s5.h0.FRAGMENT_ENCODE_SET;
            this.lastGuideNextSapaDistance = s5.h0.FRAGMENT_ENCODE_SET;
            this.lastGuideLimitedSpeed = s5.h0.FRAGMENT_ENCODE_SET;
        }
        this.lastBaiduInNavi = z6;
        syncBaiduCrossMap();
        scheduleSend();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        boolean z6 = (configuration.uiMode & 48) == 32;
        if (z6 != this.isNightMode) {
            this.isNightMode = z6;
            this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
            scheduleSend();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mSendThread.start();
        this.sendHandler = new Handler(this.mSendThread.getLooper());
        this.prefs = SharedPreferencesUtils.getAppPrefs(this);
        this.currentTransportMode = getConnectionMode();
        this.isNightMode = BroadcastUtils.isCurrentNightMode(this);
        this.repo = o4.c.c();
        this.transferManager = q4.h.g(this);
        MobileHudBluetoothServer mobileHudBluetoothServer = new MobileHudBluetoothServer(this);
        this.bluetoothServer = mobileHudBluetoothServer;
        mobileHudBluetoothServer.setConnectionListener(new AnonymousClass4());
        this.bluetoothServer.setMessageListener(new e0(this));
        createChannel();
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(10120, createNotification(), 9);
        } else {
            startForeground(NOTIFICATION_ID, createNotification());
        }
        q4.h hVar = this.transferManager;
        q4.f fVar = this.wifiPhoneNotificationListener;
        if (fVar != null) {
            hVar.f8469e.add(fVar);
        } else {
            hVar.getClass();
        }
        this.transferManager.a("mobile_hud");
        this.prefs.registerOnSharedPreferenceChangeListener(this);
        registerObservers();
        if (!i6.e.b().e(this)) {
            i6.e.b().j(this);
        }
        registerCrossMapReceiver();
        syncBaiduSource();
        syncExternalLightSource();
        this.mainHandler.postDelayed(new d0(this, 5), 2000L);
        this.mainHandler.postDelayed(new d0(this, 6), 6000L);
        AmapBroadcastReceiver.getInstance().initBroadcastForService(this);
        this.transferManager.a("mobile_hud");
        syncTransportMode();
        scheduleTransitionSyncIfNeeded();
        syncMusicManager();
        syncGpsHeadingListener();
        scheduleSend();
        schedulePhoneNotificationConfigSync();
    }

    @Override // android.app.Service
    public void onDestroy() {
        BroadcastReceiver broadcastReceiver;
        super.onDestroy();
        this.mainHandler.removeCallbacksAndMessages(null);
        this.prefs.unregisterOnSharedPreferenceChangeListener(this);
        q4.h hVar = this.transferManager;
        q4.f fVar = this.wifiPhoneNotificationListener;
        if (fVar != null) {
            hVar.f8469e.remove(fVar);
        } else {
            hVar.getClass();
        }
        if (i6.e.b().e(this)) {
            i6.e.b().l(this);
        }
        unregisterCrossMapReceiver();
        stopManagedMusicManager();
        com.navi.tool.baidu.e.l(this).w();
        if (com.navi.tool.baidu.i.f4679b && (broadcastReceiver = com.navi.tool.baidu.i.f4678a) != null) {
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (Exception unused) {
            }
            com.navi.tool.baidu.i.f4678a = null;
            com.navi.tool.baidu.i.f4679b = false;
        }
        unregisterExternalLightReceiver();
        unregisterObservers();
        this.bluetoothServer.setMessageListener(null);
        this.bluetoothServer.stop();
        this.transferManager.m("mobile_hud");
        stopGpsHeading();
        Handler handler = this.sendHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        try {
            this.mSendThread.quitSafely();
        } catch (Exception unused2) {
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onGuideInfo(h4.b bVar) {
        if (bVar == null) {
            return;
        }
        if (isGuideInfoCleared(bVar)) {
            clearGuideState();
            scheduleSend();
            return;
        }
        this.lastGuideNextRoadName = safe(bVar.f6726a);
        this.lastGuideCurRoadName = safe(bVar.f6737l);
        this.lastGuideSegmentRemainDistance = safe(bVar.f6727b);
        this.lastGuideRouteRemainDistance = safe(bVar.f6728c);
        this.lastGuideEtaText = safe(bVar.f6730e);
        this.lastGuideTurnIcon = bVar.f6731f;
        this.lastGuideSapaName = safe(bVar.f6732g);
        this.lastGuideSapaDistance = safe(bVar.f6733h);
        this.lastGuideNextSapaName = safe(bVar.f6734i);
        this.lastGuideNextSapaDistance = safe(bVar.f6735j);
        this.lastGuideLimitedSpeed = safe(bVar.f6736k);
        scheduleSend();
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onHeadingInfo(h4.c cVar) {
        if (cVar == null || !cVar.f6738a) {
            clearHeadingState();
        } else {
            this.lastAmapHeadingDegrees = normalizeDegrees(cVar.f6739b);
            long j7 = cVar.f6740c;
            if (j7 <= 0) {
                j7 = System.currentTimeMillis();
            }
            this.lastAmapHeadingUpdatedAt = j7;
        }
        scheduleSend();
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onLaneInfo(h4.d dVar) {
        this.lastLaneDriveWay = dVar == null ? s5.h0.FRAGMENT_ENCODE_SET : safe(dVar.f6741a);
        scheduleSend();
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onLyricUpdate(h4.k kVar) {
        int i7 = this.prefs.getInt("desktop_music_source", 0);
        if (kVar == null || kVar.f6754b != i7) {
            return;
        }
        this.lastLyric = safe(kVar.f6753a);
        scheduleSend();
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onNavigationEnd(h4.e eVar) {
        clearGuideState();
        clearHeadingState();
        clearTrafficLightState();
        clearTmcState();
        clearCrossMapAsset();
        this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
        scheduleSend();
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str == null) {
            return;
        }
        if ((PREF_PHONE_NOTIFICATION_ENABLED.equals(str) && !sharedPreferences.getBoolean(PREF_PHONE_NOTIFICATION_ENABLED, false)) || (PREF_SHOW_PHONE_NOTIFICATION.equals(str) && !sharedPreferences.getBoolean(PREF_SHOW_PHONE_NOTIFICATION, true))) {
            clearHudPhoneNotification();
        }
        if (PREF_ENABLED.equals(str) || str.startsWith("mobile_hud_") || PhoneNotificationRelayService.PREF_CONNECTION_MODE.equals(str) || PhoneNotificationRelayService.PREF_CONNECTION_MODE_TRANSITION_UNTIL_MS.equals(str) || "car_link_service_enabled".equals(str) || "lion_sync_service_enabled".equals(str) || "desktop_music_source".equals(str)) {
            if (!shouldRun(sharedPreferences)) {
                sendPhoneNotificationConfig();
                stopSelf();
                return;
            }
            if (PREF_CONNECTION_MODE.equals(str)) {
                handleConnectionModeChanged();
                this.lastSentPayload = s5.h0.FRAGMENT_ENCODE_SET;
            }
            if (PREF_USE_EXTERNAL_TRAFFIC_LIGHT.equals(str)) {
                syncExternalLightSource();
            }
            if (PREF_USE_BAIDU_SOURCE.equals(str) && sharedPreferences.getBoolean(PREF_USE_BAIDU_SOURCE, false)) {
                a.a.m(sharedPreferences, PREF_USE_EXTERNAL_TRAFFIC_LIGHT, false);
                unregisterExternalLightReceiver();
            }
            syncTransportMode();
            scheduleTransitionSyncIfNeeded();
            syncMusicManager();
            syncGpsHeadingListener();
            scheduleSend();
            if (PREF_ENABLED.equals(str) || PREF_CONNECTION_MODE.equals(str) || PREF_PHONE_NOTIFICATION_ENABLED.equals(str) || PhoneNotificationRelayService.PREF_CONNECTION_MODE.equals(str)) {
                schedulePhoneNotificationConfigSync();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        boolean z6 = intent != null && ACTION_CACHE_HUD_PHONE_NOTIFICATION.equals(intent.getAction());
        if (!shouldRun(this.prefs)) {
            stopSelf();
            return 2;
        }
        this.isNightMode = BroadcastUtils.isCurrentNightMode(this);
        this.transferManager.a("mobile_hud");
        syncBaiduSource();
        syncTransportMode();
        scheduleTransitionSyncIfNeeded();
        syncMusicManager();
        syncGpsHeadingListener();
        scheduleSend();
        schedulePhoneNotificationConfigSync();
        if (z6) {
            handleHudPhoneNotificationIntent(intent.getStringExtra(EXTRA_HUD_PHONE_NOTIFICATION_PAYLOAD));
        }
        return 1;
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onTmcInfo(h4.g gVar) {
        List list;
        int i7;
        int i8;
        int i9;
        if (gVar == null || (list = gVar.f6745a) == null || list.isEmpty()) {
            clearTmcState();
            scheduleSend();
            return;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            ArrayList arrayList = new ArrayList(gVar.f6745a);
            Collections.sort(arrayList, new m0.a(4));
            Iterator it = arrayList.iterator();
            i7 = 0;
            i8 = 0;
            while (it.hasNext()) {
                try {
                    h4.f fVar = (h4.f) it.next();
                    if (fVar != null && (i9 = fVar.f6744c) > 0) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("weight", i9);
                        jSONObject.put("status", fVar.f6743b);
                        jSONArray.put(jSONObject);
                        i7 += i9;
                        if (fVar.f6743b == 10) {
                            i8 += i9;
                        }
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            i7 = 0;
            i8 = 0;
        }
        this.lastTmcSegments = jSONArray;
        this.lastTmcDrivenPercent = i7 > 0 ? Math.round((i8 * 100.0f) / i7) : 0;
        this.lastTmcEnabled = jSONArray.length() > 0;
        scheduleSend();
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onTrafficLight(h4.i iVar) {
        if (iVar == null) {
            clearTrafficLightState();
            scheduleSend();
            return;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            for (h4.h hVar : iVar.f6750b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("direction", hVar.f6746a);
                jSONObject.put("status", hVar.f6747b);
                jSONObject.put("countdown", hVar.f6748c);
                jSONArray.put(jSONObject);
            }
        } catch (Exception unused) {
        }
        String str = iVar.f6749a;
        this.lastTrafficLightSource = str;
        this.lastTrafficLightArray = jSONArray;
        if (!h4.i.SOURCE_NAVI.equals(str) || jSONArray.length() <= 0) {
            this.lastNaviTrafficLightUpdatedAt = 0L;
        } else {
            this.lastNaviTrafficLightUpdatedAt = SystemClock.elapsedRealtime();
        }
        scheduleSend();
    }
}
