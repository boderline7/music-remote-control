package com.navi.tool.ui.extra;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.p0;
import com.navi.tool.NaviApplication;
import com.navi.tool.service.MobileHudService;
import com.navi.tool.service.WeatherAlertOverlayService;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DesktopAssistantClockWeatherController {
    private static final long ALERT_MIN_REQUEST_INTERVAL_MS = 1200000;
    private static final String ALERT_RECORD_DIR = ".qw";
    private static final String ALERT_RECORD_FILE = "qw.json";
    private static final long ALERT_REFRESH_INTERVAL_MS = 1200000;
    private static final long CACHE_TTL_MS = 600000;
    private static final long CONTINUOUS_LOCATION_MIN_TIME_MS = 60000;
    private static final String DEFAULT_LOCATION_TEXT = "正在定位";
    private static final long FRESH_LOCATION_MAX_AGE_MS = 120000;
    private static final float GEO_LOOKUP_DISTANCE_THRESHOLD_METERS = 1000.0f;
    public static final int LOCATION_MODE_COUNTY = 0;
    public static final int LOCATION_MODE_COUNTY_TOWN = 1;
    public static final int LOCATION_MODE_TOWN = 2;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 4017;
    private static final long LOCATION_RECOVERY_THRESHOLD_MS = 300000;
    private static final long LOCATION_TIMEOUT_MS = 18000;
    private static final float MAX_ACCEPTABLE_ACCURACY_METERS = 300.0f;
    public static final String PREF_CLOCK_WEATHER_ALERT_ENABLED = "desktop_music_weather_alert_enabled";
    public static final String PREF_CLOCK_WEATHER_LOCATION_MODE = "desktop_music_clock_weather_location_mode";
    private static final long REFRESH_INTERVAL_MS = 1200000;
    private static final String TAG = "ClockWeatherController";
    private static volatile double lastAlertLatitude = Double.NaN;
    private static volatile double lastAlertLongitude = Double.NaN;
    private static volatile long lastAlertUpdateMs;
    private static WeatherMemoryCache memoryCache;
    private final Activity activity;
    private boolean enabled;
    private Location fallbackLocation;
    private boolean forceAlertRefreshOnce;
    private boolean forceGeoLookupOnce;
    private final ImageView imageIcon;
    private String lastCountyCode;
    private String lastCountyName;
    private String lastLocationLabel;
    private String lastTownName;
    private long lastValidLocationTimeMs;
    private long lastWeatherUpdateMs;
    private LocationManager locationManager;
    private boolean locationUpdatesActive;
    private boolean pendingDeferredAlertRefresh;
    private boolean pendingRecoveryWeatherRefresh;
    private boolean refreshing;
    private final TextView textLocation;
    private final TextView textSummary;
    private WeatherObserver weatherObserver;
    private static final Object CACHE_LOCK = new Object();
    private static final Object ALERT_FILE_LOCK = new Object();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService worker = Executors.newSingleThreadExecutor();
    private double lastGeoLookupLatitude = Double.NaN;
    private double lastGeoLookupLongitude = Double.NaN;
    private final Runnable periodicRefresh = new Runnable() { // from class: com.navi.tool.ui.extra.DesktopAssistantClockWeatherController.1
        @Override // java.lang.Runnable
        public void run() {
            if (DesktopAssistantClockWeatherController.this.enabled) {
                DesktopAssistantClockWeatherController.this.refresh(false);
                DesktopAssistantClockWeatherController.this.mainHandler.postDelayed(this, 1200000L);
            }
        }
    };
    private final Runnable periodicAlertRefresh = new Runnable() { // from class: com.navi.tool.ui.extra.DesktopAssistantClockWeatherController.2
        @Override // java.lang.Runnable
        public void run() {
            if (DesktopAssistantClockWeatherController.this.enabled) {
                DesktopAssistantClockWeatherController.this.triggerAlertRefresh();
                DesktopAssistantClockWeatherController.this.mainHandler.postDelayed(this, 1200000L);
            }
        }
    };
    private final Runnable locationTimeout = new u1(this, 1);
    private final LocationListener locationListener = new LocationListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantClockWeatherController.3
        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location != null) {
                if (!DesktopAssistantClockWeatherController.this.refreshing) {
                    if (DesktopAssistantClockWeatherController.this.isLocationAccurateEnough(location)) {
                        DesktopAssistantClockWeatherController.this.markRecoveryWeatherRefreshIfNeeded();
                        DesktopAssistantClockWeatherController.this.handleLocation(location);
                        return;
                    } else {
                        DesktopAssistantClockWeatherController desktopAssistantClockWeatherController = DesktopAssistantClockWeatherController.this;
                        desktopAssistantClockWeatherController.fallbackLocation = desktopAssistantClockWeatherController.chooseBetterLocation(desktopAssistantClockWeatherController.fallbackLocation, location);
                        return;
                    }
                }
                DesktopAssistantClockWeatherController desktopAssistantClockWeatherController2 = DesktopAssistantClockWeatherController.this;
                desktopAssistantClockWeatherController2.fallbackLocation = desktopAssistantClockWeatherController2.chooseBetterLocation(desktopAssistantClockWeatherController2.fallbackLocation, location);
                DesktopAssistantClockWeatherController desktopAssistantClockWeatherController3 = DesktopAssistantClockWeatherController.this;
                if (desktopAssistantClockWeatherController3.isLocationAccurateEnough(desktopAssistantClockWeatherController3.fallbackLocation)) {
                    DesktopAssistantClockWeatherController.this.markRecoveryWeatherRefreshIfNeeded();
                    DesktopAssistantClockWeatherController desktopAssistantClockWeatherController4 = DesktopAssistantClockWeatherController.this;
                    desktopAssistantClockWeatherController4.handleLocation(desktopAssistantClockWeatherController4.fallbackLocation);
                }
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i7, Bundle bundle) {
        }
    };

    public static final class AlertRecord {
        private String colorCode;
        private String expireTime;
        private String id;
        private String messageTypeCode;
        private String severity;

        private AlertRecord() {
        }

        public static AlertRecord fromAlert(s4.a aVar) {
            AlertRecord alertRecord = new AlertRecord();
            alertRecord.id = aVar.f8712a;
            alertRecord.severity = aVar.f8717f;
            alertRecord.colorCode = aVar.f8719h;
            alertRecord.expireTime = aVar.f8720i;
            alertRecord.messageTypeCode = aVar.f8714c;
            return alertRecord;
        }

        public static AlertRecord fromJson(JSONObject jSONObject) {
            AlertRecord alertRecord = new AlertRecord();
            alertRecord.id = jSONObject.optString("id", s5.h0.FRAGMENT_ENCODE_SET);
            alertRecord.severity = jSONObject.optString("severity", s5.h0.FRAGMENT_ENCODE_SET);
            alertRecord.colorCode = jSONObject.optString("colorCode", s5.h0.FRAGMENT_ENCODE_SET);
            alertRecord.expireTime = jSONObject.optString("expireTime", s5.h0.FRAGMENT_ENCODE_SET);
            alertRecord.messageTypeCode = jSONObject.optString("messageTypeCode", s5.h0.FRAGMENT_ENCODE_SET);
            return alertRecord;
        }

        private static String nullSafe(String str) {
            return str == null ? s5.h0.FRAGMENT_ENCODE_SET : str;
        }

        public boolean isSameAlert(AlertRecord alertRecord) {
            return alertRecord != null && nullSafe(this.id).equals(nullSafe(alertRecord.id)) && nullSafe(this.severity).equals(nullSafe(alertRecord.severity)) && nullSafe(this.colorCode).equals(nullSafe(alertRecord.colorCode)) && nullSafe(this.expireTime).equals(nullSafe(alertRecord.expireTime)) && nullSafe(this.messageTypeCode).equals(nullSafe(alertRecord.messageTypeCode));
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", nullSafe(this.id));
                jSONObject.put("severity", nullSafe(this.severity));
                jSONObject.put("colorCode", nullSafe(this.colorCode));
                jSONObject.put("expireTime", nullSafe(this.expireTime));
                jSONObject.put("messageTypeCode", nullSafe(this.messageTypeCode));
            } catch (Exception unused) {
            }
            return jSONObject;
        }
    }

    public static final class GeoAreaInfo {
        private final String countyCode;
        private final String countyName;
        private final String displayName;
        private final String townName;

        private GeoAreaInfo(String str, String str2, String str3, String str4) {
            this.countyCode = str;
            this.countyName = str2;
            this.townName = str3;
            this.displayName = TextUtils.isEmpty(str4) ? DesktopAssistantClockWeatherController.DEFAULT_LOCATION_TEXT : str4;
        }
    }

    public static final class WeatherMemoryCache {
        private String countyCode;
        private String countyName;
        private String iconCode;
        private double lastGeoLookupLatitude;
        private double lastGeoLookupLongitude;
        private double latitude;
        private String locationName;
        private double longitude;
        private String temp;
        private String townName;
        private long updateTimeMs;
        private String weatherText;

        private WeatherMemoryCache() {
            this.locationName = DesktopAssistantClockWeatherController.DEFAULT_LOCATION_TEXT;
            this.weatherText = "--";
            this.temp = "--";
            this.iconCode = "999";
            this.lastGeoLookupLatitude = Double.NaN;
            this.lastGeoLookupLongitude = Double.NaN;
        }

        private WeatherMemoryCache(WeatherMemoryCache weatherMemoryCache) {
            this.locationName = DesktopAssistantClockWeatherController.DEFAULT_LOCATION_TEXT;
            this.weatherText = "--";
            this.temp = "--";
            this.iconCode = "999";
            this.lastGeoLookupLatitude = Double.NaN;
            this.lastGeoLookupLongitude = Double.NaN;
            this.locationName = weatherMemoryCache.locationName;
            this.countyCode = weatherMemoryCache.countyCode;
            this.countyName = weatherMemoryCache.countyName;
            this.townName = weatherMemoryCache.townName;
            this.latitude = weatherMemoryCache.latitude;
            this.longitude = weatherMemoryCache.longitude;
            this.weatherText = weatherMemoryCache.weatherText;
            this.temp = weatherMemoryCache.temp;
            this.iconCode = weatherMemoryCache.iconCode;
            this.updateTimeMs = weatherMemoryCache.updateTimeMs;
            this.lastGeoLookupLatitude = weatherMemoryCache.lastGeoLookupLatitude;
            this.lastGeoLookupLongitude = weatherMemoryCache.lastGeoLookupLongitude;
        }
    }

    public interface WeatherObserver {
        void onWeather(String str, String str2);
    }

    public DesktopAssistantClockWeatherController(Activity activity, TextView textView, TextView textView2, ImageView imageView) {
        this.activity = activity;
        this.textLocation = textView;
        this.textSummary = textView2;
        this.imageIcon = imageView;
        this.locationManager = (LocationManager) activity.getSystemService("location");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyStatus(String str, String str2) {
        TextView textView = this.textLocation;
        if (TextUtils.isEmpty(str)) {
            str = DEFAULT_LOCATION_TEXT;
        }
        textView.setText(str);
        this.textSummary.setText(str2);
        int k02 = n1.z.k0(this.activity, "999");
        if (k02 != 0) {
            this.imageIcon.setImageResource(k02);
        }
    }

    private String buildAlertOverlayDescription(s4.a aVar) {
        String sanitizeAlertText = sanitizeAlertText(aVar.f8722k);
        if (TextUtils.isEmpty(sanitizeAlertText)) {
            sanitizeAlertText = sanitizeAlertText(aVar.f8723l);
        }
        return TextUtils.isEmpty(sanitizeAlertText) ? "请注意防范" : sanitizeAlertText;
    }

    private String buildAlertTtsMessage(s4.a aVar, String str) {
        String sanitizeAlertText = sanitizeAlertText(aVar.f8721j);
        String sanitizeAlertText2 = sanitizeAlertText(aVar.f8716e);
        StringBuilder sb = new StringBuilder();
        if ("update".equalsIgnoreCase(aVar.f8714c)) {
            sb.append("天气预警更新，");
        } else if (getSeverityRank(aVar.f8717f) >= 3 || getColorRank(aVar.f8719h) >= 3) {
            sb.append("重要天气预警，");
        } else {
            sb.append("天气预警提醒，");
        }
        if (!TextUtils.isEmpty(sanitizeAlertText)) {
            sb.append(sanitizeAlertText);
        } else if (TextUtils.isEmpty(sanitizeAlertText2)) {
            sb.append("请注意天气变化");
        } else {
            sb.append(sanitizeAlertText2);
            sb.append("预警");
        }
        if ("update".equalsIgnoreCase(aVar.f8714c)) {
            sb.append("，请及时关注最新预警信息。");
        } else {
            sb.append("，请注意防范。");
        }
        return sb.toString();
    }

    private String buildLocationDisplayName(String str, String str2, String str3) {
        return buildLocationDisplayName(str, str2, str3, null);
    }

    private String buildLocationDisplayName(String str, String str2, String str3, String str4) {
        String sanitizeGeoValue = sanitizeGeoValue(str);
        String sanitizeGeoValue2 = sanitizeGeoValue(str2);
        String sanitizeGeoValue3 = sanitizeGeoValue(str4);
        int locationDisplayMode = getLocationDisplayMode();
        if (locationDisplayMode == 0) {
            if (!TextUtils.isEmpty(sanitizeGeoValue)) {
                return sanitizeGeoValue;
            }
            if (!TextUtils.isEmpty(sanitizeGeoValue2)) {
                return sanitizeGeoValue2;
            }
        } else if (locationDisplayMode == 2) {
            if (!TextUtils.isEmpty(sanitizeGeoValue2)) {
                return sanitizeGeoValue2;
            }
            if (!TextUtils.isEmpty(sanitizeGeoValue)) {
                return sanitizeGeoValue;
            }
        } else {
            if (!TextUtils.isEmpty(sanitizeGeoValue) && !TextUtils.isEmpty(sanitizeGeoValue2)) {
                return sanitizeGeoValue.equals(sanitizeGeoValue2) ? sanitizeGeoValue : a.a.g(sanitizeGeoValue, sanitizeGeoValue2);
            }
            if (!TextUtils.isEmpty(sanitizeGeoValue)) {
                return sanitizeGeoValue;
            }
            if (!TextUtils.isEmpty(sanitizeGeoValue2)) {
                return sanitizeGeoValue2;
            }
        }
        return !TextUtils.isEmpty(sanitizeGeoValue3) ? sanitizeGeoValue3 : !isPlaceholderLocationName(str3) ? str3 : DEFAULT_LOCATION_TEXT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Location chooseBetterLocation(Location location, Location location2) {
        return location2 == null ? location : location == null ? location2 : (location2.hasAccuracy() && location.hasAccuracy()) ? location2.getAccuracy() <= location.getAccuracy() ? location2 : location : location2.getTime() >= location.getTime() ? location2 : location;
    }

    private void clearAlertRecord() {
        synchronized (ALERT_FILE_LOCK) {
            try {
                File alertRecordFile = getAlertRecordFile();
                if (alertRecordFile != null && alertRecordFile.exists()) {
                    alertRecordFile.delete();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private int compareAlertPriority(s4.a aVar, s4.a aVar2) {
        int compare = Integer.compare(getSeverityRank(aVar.f8717f), getSeverityRank(aVar2.f8717f));
        if (compare != 0) {
            return compare;
        }
        int compare2 = Integer.compare(getColorRank(aVar.f8719h), getColorRank(aVar2.f8719h));
        return compare2 != 0 ? compare2 : nullSafe(aVar.f8713b).compareTo(nullSafe(aVar2.f8713b));
    }

    private WeatherMemoryCache copyMemoryCache() {
        synchronized (CACHE_LOCK) {
            try {
                WeatherMemoryCache weatherMemoryCache = memoryCache;
                if (weatherMemoryCache == null) {
                    return null;
                }
                return new WeatherMemoryCache(weatherMemoryCache);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void dismissWeatherAlertOverlay() {
        Intent intent = new Intent(this.activity, (Class<?>) WeatherAlertOverlayService.class);
        intent.setAction(WeatherAlertOverlayService.ACTION_DISMISS_ALERT);
        this.activity.startService(intent);
    }

    private void dispatchDeferredAlertRefreshIfNeeded() {
        if (this.enabled && !this.refreshing && this.pendingDeferredAlertRefresh) {
            this.pendingDeferredAlertRefresh = false;
            this.forceAlertRefreshOnce = true;
            this.mainHandler.post(new u1(this, 0));
        }
    }

    private float distanceBetweenMeters(double d7, double d8, double d9, double d10) {
        float[] fArr = new float[1];
        Location.distanceBetween(d7, d8, d9, d10, fArr);
        return fArr[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishRefresh() {
        this.refreshing = false;
        dispatchDeferredAlertRefreshIfNeeded();
    }

    private String firstNonEmpty(String... strArr) {
        if (strArr == null) {
            return null;
        }
        for (String str : strArr) {
            String sanitizeGeoValue = sanitizeGeoValue(str);
            if (!TextUtils.isEmpty(sanitizeGeoValue)) {
                return sanitizeGeoValue;
            }
        }
        return null;
    }

    private static File getAlertRecordFile() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "navitool/.qw");
            if (!file.exists()) {
                file.mkdirs();
            }
            return new File(file, ALERT_RECORD_FILE);
        } catch (Exception unused) {
            return null;
        }
    }

    private Location getBestLastKnownLocation() {
        LocationManager locationManager;
        Location location = null;
        try {
            locationManager = this.locationManager;
        } catch (Exception unused) {
        }
        if (locationManager == null) {
            return null;
        }
        for (String str : locationManager.getProviders(true)) {
            if (g0.g.a(this.activity, "android.permission.ACCESS_FINE_LOCATION") == 0 || g0.g.a(this.activity, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                Location lastKnownLocation = this.locationManager.getLastKnownLocation(str);
                if (lastKnownLocation != null && (location == null || lastKnownLocation.getTime() > location.getTime())) {
                    location = lastKnownLocation;
                }
            }
        }
        return location;
    }

    private GeoAreaInfo getCachedGeoAreaInfo() {
        String str = this.lastCountyCode;
        String str2 = this.lastCountyName;
        String str3 = this.lastTownName;
        return new GeoAreaInfo(str, str2, str3, buildLocationDisplayName(str2, str3, this.lastLocationLabel));
    }

    private int getColorRank(String str) {
        if ("red".equalsIgnoreCase(str)) {
            return 4;
        }
        if ("orange".equalsIgnoreCase(str)) {
            return 3;
        }
        if ("yellow".equalsIgnoreCase(str)) {
            return 2;
        }
        return MobileHudService.TEXT_COLOR_BLUE.equalsIgnoreCase(str) ? 1 : 0;
    }

    private int getLocationDisplayMode() {
        try {
            return SharedPreferencesUtils.getAppPrefs(this.activity).getInt(PREF_CLOCK_WEATHER_LOCATION_MODE, 1);
        } catch (Exception unused) {
            return 1;
        }
    }

    private int getSeverityRank(String str) {
        if ("extreme".equalsIgnoreCase(str)) {
            return 4;
        }
        if ("severe".equalsIgnoreCase(str)) {
            return 3;
        }
        if ("moderate".equalsIgnoreCase(str)) {
            return 2;
        }
        return "minor".equalsIgnoreCase(str) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLocation(Location location) {
        this.mainHandler.removeCallbacks(this.locationTimeout);
        final double latitude = location.getLatitude();
        final double longitude = location.getLongitude();
        final boolean z6 = this.pendingRecoveryWeatherRefresh;
        this.pendingRecoveryWeatherRefresh = false;
        this.lastValidLocationTimeMs = System.currentTimeMillis();
        this.fallbackLocation = null;
        this.worker.execute(new Runnable() { // from class: com.navi.tool.ui.extra.v1
            @Override // java.lang.Runnable
            public final void run() {
                DesktopAssistantClockWeatherController.this.lambda$handleLocation$2(latitude, longitude, z6);
            }
        });
    }

    private boolean hasCountyCodeChanged(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(this.lastCountyCode)) {
            return true;
        }
        return !str.equals(this.lastCountyCode);
    }

    private boolean hasLocationPermission() {
        return g0.g.a(this.activity, "android.permission.ACCESS_FINE_LOCATION") == 0 || g0.g.a(this.activity, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLocationAccurateEnough(Location location) {
        if (location == null) {
            return false;
        }
        if ("gps".equals(location.getProvider())) {
            return true;
        }
        return !location.hasAccuracy() || location.getAccuracy() <= MAX_ACCEPTABLE_ACCURACY_METERS;
    }

    private boolean isLocationFreshAndAccurate(Location location) {
        return location != null && System.currentTimeMillis() - location.getTime() <= FRESH_LOCATION_MAX_AGE_MS && isLocationAccurateEnough(location);
    }

    private boolean isPlaceholderLocationName(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String trim = str.trim();
        return DEFAULT_LOCATION_TEXT.equals(trim) || "当前位置".equals(trim) || "中国".equals(trim);
    }

    private boolean isWeatherAlertEnabled() {
        try {
            return SharedPreferencesUtils.getAppPrefs(this.activity).getBoolean(PREF_CLOCK_WEATHER_ALERT_ENABLED, true);
        } catch (Exception unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchDeferredAlertRefreshIfNeeded$1() {
        if (!this.enabled || this.refreshing) {
            return;
        }
        refresh(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$handleLocation$2(final double d7, final double d8, boolean z6) {
        boolean shouldTimeRefresh = shouldTimeRefresh();
        boolean z7 = this.forceAlertRefreshOnce;
        this.forceAlertRefreshOnce = false;
        final GeoAreaInfo resolveGeoAreaInfo = resolveGeoAreaInfo(d7, d8);
        final String str = resolveGeoAreaInfo.displayName;
        boolean hasCountyCodeChanged = hasCountyCodeChanged(resolveGeoAreaInfo.countyCode);
        rememberGeoAreaInfo(resolveGeoAreaInfo);
        maybeRefreshWeatherAlert(d7, d8, resolveGeoAreaInfo, hasCountyCodeChanged, z7 || z6);
        if (hasCountyCodeChanged || shouldTimeRefresh || z6) {
            final s4.e eVar = new s4.e() { // from class: com.navi.tool.ui.extra.DesktopAssistantClockWeatherController.4
                @Override // s4.e
                public void onError(Exception exc) {
                    DesktopAssistantClockWeatherController.this.finishRefresh();
                    if (DesktopAssistantClockWeatherController.this.renderCachedWeather(true)) {
                        return;
                    }
                    DesktopAssistantClockWeatherController desktopAssistantClockWeatherController = DesktopAssistantClockWeatherController.this;
                    desktopAssistantClockWeatherController.applyStatus(desktopAssistantClockWeatherController.resolveUsableLocationName(str), "天气不可用");
                }

                @Override // s4.e
                public void onSuccess(s4.f fVar) {
                    DesktopAssistantClockWeatherController.this.finishRefresh();
                    String resolveUsableLocationName = DesktopAssistantClockWeatherController.this.resolveUsableLocationName(str);
                    DesktopAssistantClockWeatherController.this.lastWeatherUpdateMs = System.currentTimeMillis();
                    DesktopAssistantClockWeatherController.this.rememberGeoAreaInfo(resolveGeoAreaInfo);
                    DesktopAssistantClockWeatherController.this.saveCache(resolveUsableLocationName, resolveGeoAreaInfo.countyCode, resolveGeoAreaInfo.countyName, resolveGeoAreaInfo.townName, d7, d8, fVar);
                    DesktopAssistantClockWeatherController.this.renderWeather(resolveUsableLocationName, fVar);
                }
            };
            s4.g.f8737b.execute(new Runnable() { // from class: s4.d
                @Override // java.lang.Runnable
                public final void run() {
                    double d9 = d7;
                    double d10 = d8;
                    e eVar2 = eVar;
                    Handler handler = g.f8736a;
                    try {
                        handler.post(new p0(25, eVar2, g.a(d9, d10)));
                    } catch (Exception e7) {
                        handler.post(new p0(26, eVar2, e7));
                    }
                }
            });
        } else {
            finishRefresh();
            updateLocationTextOnly(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.refreshing) {
            Location location = this.fallbackLocation;
            if (location != null) {
                handleLocation(location);
                return;
            }
            finishRefresh();
            if (renderCachedWeather(true)) {
                return;
            }
            applyStatus(DEFAULT_LOCATION_TEXT, "定位超时");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLocationTextOnly$3(String str) {
        this.textLocation.setText(str);
    }

    private AlertRecord loadAlertRecord() {
        synchronized (ALERT_FILE_LOCK) {
            File alertRecordFile = getAlertRecordFile();
            if (alertRecordFile == null || !alertRecordFile.exists()) {
                return null;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(alertRecordFile));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            AlertRecord fromJson = AlertRecord.fromJson(new JSONObject(sb.toString()));
                            bufferedReader.close();
                            return fromJson;
                        }
                        sb.append(readLine);
                    }
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Exception unused) {
                return null;
            }
        }
    }

    private s4.b loadAlertResult(double d7, double d8) {
        String str;
        String str2 = s5.h0.FRAGMENT_ENCODE_SET;
        s5.o0 o0Var = s4.c.f8728a;
        Locale locale = Locale.US;
        String format = String.format(locale, "%.2f", Double.valueOf(d7));
        String format2 = String.format(locale, "%.2f", Double.valueOf(d8));
        StringBuilder sb = new StringBuilder();
        sb.append(n1.z.h());
        sb.append("/wxproxy.php?uid=");
        try {
            str = SharedPreferencesUtils.getLicenseUid(NaviApplication.getInstance());
        } catch (Throwable unused) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        sb.append(n1.z.Z(str));
        sb.append("&tk=");
        try {
            str2 = SharedPreferencesUtils.getLicenseToken(NaviApplication.getInstance());
        } catch (Throwable unused2) {
        }
        sb.append(n1.z.Z(str2));
        sb.append("&p=");
        sb.append(n1.z.Z("/weatheralert/v1/current/" + format + "/" + format2));
        sb.append("&lang=zh");
        String sb2 = sb.toString();
        s5.r0 r0Var = new s5.r0();
        r0Var.f(sb2);
        r0Var.b();
        s5.x0 f7 = s4.c.f8728a.a(r0Var.a()).f();
        try {
            if (!f7.B()) {
                throw new IOException("天气预警API请求失败: " + f7.f8916d);
            }
            s5.a1 a1Var = f7.f8919g;
            if (a1Var == null) {
                throw new IOException("天气预警API响应体为空");
            }
            s4.b a7 = s4.c.a(a1Var.A());
            f7.close();
            return a7;
        } catch (Throwable th) {
            try {
                f7.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markRecoveryWeatherRefreshIfNeeded() {
        if (this.lastValidLocationTimeMs <= 0 || this.pendingRecoveryWeatherRefresh || System.currentTimeMillis() - this.lastValidLocationTimeMs < LOCATION_RECOVERY_THRESHOLD_MS) {
            return;
        }
        this.pendingRecoveryWeatherRefresh = true;
    }

    private void maybeRefreshWeatherAlert(double d7, double d8, GeoAreaInfo geoAreaInfo, boolean z6, boolean z7) {
        if (isWeatherAlertEnabled() && shouldRequestAlert(z7, z6, d7, d8)) {
            try {
                s4.b loadAlertResult = loadAlertResult(d7, d8);
                if (isWeatherAlertEnabled()) {
                    lastAlertUpdateMs = System.currentTimeMillis();
                    lastAlertLatitude = d7;
                    lastAlertLongitude = d8;
                    if (!loadAlertResult.f8725a && !loadAlertResult.f8727c.isEmpty()) {
                        s4.a selectHighestPriorityAlert = selectHighestPriorityAlert(loadAlertResult.f8727c);
                        if (selectHighestPriorityAlert == null) {
                            return;
                        }
                        if ("cancel".equalsIgnoreCase(selectHighestPriorityAlert.f8714c)) {
                            clearAlertRecord();
                            return;
                        }
                        AlertRecord fromAlert = AlertRecord.fromAlert(selectHighestPriorityAlert);
                        if (shouldAnnounce(fromAlert) && isWeatherAlertEnabled()) {
                            saveAlertRecord(fromAlert);
                            startWeatherAlertOverlay(selectHighestPriorityAlert, buildAlertOverlayDescription(selectHighestPriorityAlert), buildAlertTtsMessage(selectHighestPriorityAlert, geoAreaInfo.displayName));
                            return;
                        }
                        return;
                    }
                    clearAlertRecord();
                }
            } catch (Exception unused) {
            }
        }
    }

    private String nullSafe(String str) {
        return str == null ? s5.h0.FRAGMENT_ENCODE_SET : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rememberGeoAreaInfo(GeoAreaInfo geoAreaInfo) {
        if (!TextUtils.isEmpty(geoAreaInfo.countyCode)) {
            this.lastCountyCode = geoAreaInfo.countyCode;
        }
        if (!TextUtils.isEmpty(geoAreaInfo.countyName)) {
            this.lastCountyName = geoAreaInfo.countyName;
        }
        if (!TextUtils.isEmpty(geoAreaInfo.townName)) {
            this.lastTownName = geoAreaInfo.townName;
        }
        if (isPlaceholderLocationName(geoAreaInfo.displayName)) {
            return;
        }
        this.lastLocationLabel = geoAreaInfo.displayName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean renderCachedWeather(boolean z6) {
        WeatherMemoryCache copyMemoryCache = copyMemoryCache();
        if (copyMemoryCache == null) {
            return false;
        }
        long j7 = copyMemoryCache.updateTimeMs;
        if (!z6 && System.currentTimeMillis() - j7 > CACHE_TTL_MS) {
            return false;
        }
        s4.f fVar = new s4.f();
        fVar.f8732a = copyMemoryCache.iconCode;
        fVar.f8733b = copyMemoryCache.weatherText;
        fVar.f8734c = copyMemoryCache.temp;
        fVar.f8735d = j7;
        this.lastWeatherUpdateMs = j7;
        this.lastGeoLookupLatitude = copyMemoryCache.lastGeoLookupLatitude;
        this.lastGeoLookupLongitude = copyMemoryCache.lastGeoLookupLongitude;
        if (!TextUtils.isEmpty(copyMemoryCache.countyCode)) {
            this.lastCountyCode = copyMemoryCache.countyCode;
        }
        if (!TextUtils.isEmpty(copyMemoryCache.countyName)) {
            this.lastCountyName = copyMemoryCache.countyName;
        }
        if (!TextUtils.isEmpty(copyMemoryCache.townName)) {
            this.lastTownName = copyMemoryCache.townName;
        }
        if (!isPlaceholderLocationName(copyMemoryCache.locationName)) {
            this.lastLocationLabel = copyMemoryCache.locationName;
        }
        renderWeather(buildLocationDisplayName(copyMemoryCache.countyName, copyMemoryCache.townName, copyMemoryCache.locationName), fVar);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renderWeather(String str, s4.f fVar) {
        TextView textView = this.textLocation;
        if (TextUtils.isEmpty(str)) {
            str = DEFAULT_LOCATION_TEXT;
        }
        textView.setText(str);
        this.textSummary.setText(fVar.f8733b + " / " + fVar.f8734c + "°C");
        int k02 = n1.z.k0(this.activity, fVar.f8732a);
        if (k02 != 0) {
            this.imageIcon.setImageResource(k02);
        }
        WeatherObserver weatherObserver = this.weatherObserver;
        if (weatherObserver != null) {
            weatherObserver.onWeather(fVar.f8732a, fVar.f8733b);
        }
    }

    private void requestCurrentLocation() {
        if (this.locationManager == null) {
            applyStatus(DEFAULT_LOCATION_TEXT, "定位服务不可用");
            return;
        }
        this.refreshing = true;
        this.fallbackLocation = null;
        try {
            Location bestLastKnownLocation = getBestLastKnownLocation();
            if (isLocationFreshAndAccurate(bestLastKnownLocation)) {
                handleLocation(bestLastKnownLocation);
                return;
            }
            this.fallbackLocation = chooseBetterLocation(this.fallbackLocation, bestLastKnownLocation);
            startContinuousLocationUpdates();
            if (this.locationUpdatesActive) {
                this.mainHandler.removeCallbacks(this.locationTimeout);
                this.mainHandler.postDelayed(this.locationTimeout, LOCATION_TIMEOUT_MS);
            } else {
                finishRefresh();
                if (renderCachedWeather(true)) {
                    return;
                }
                applyStatus(DEFAULT_LOCATION_TEXT, "定位服务未开启");
            }
        } catch (Exception unused) {
            finishRefresh();
            if (renderCachedWeather(true)) {
                return;
            }
            applyStatus(DEFAULT_LOCATION_TEXT, "定位失败");
        }
    }

    private GeoAreaInfo resolveGeoAreaInfo(double d7, double d8) {
        boolean z6 = this.forceGeoLookupOnce;
        this.forceGeoLookupOnce = false;
        if (!z6 && !shouldLookupGeoApi(d7, d8)) {
            return getCachedGeoAreaInfo();
        }
        try {
            s4.h a7 = s4.i.a(d7, d8);
            this.lastGeoLookupLatitude = d7;
            this.lastGeoLookupLongitude = d8;
            String sanitizeGeoValue = sanitizeGeoValue(a7.f8740b);
            String firstNonEmpty = firstNonEmpty(a7.f8741c, a7.f8744f, a7.f8745g);
            String firstNonEmpty2 = firstNonEmpty(a7.f8742d, a7.f8743e);
            String sanitizeGeoValue2 = sanitizeGeoValue(a7.f8739a);
            String buildLocationDisplayName = buildLocationDisplayName(firstNonEmpty, firstNonEmpty2, this.lastLocationLabel, sanitizeGeoValue2);
            return new GeoAreaInfo(sanitizeGeoValue, firstNonEmpty, firstNonEmpty2, (!DEFAULT_LOCATION_TEXT.equals(buildLocationDisplayName) || TextUtils.isEmpty(sanitizeGeoValue2)) ? buildLocationDisplayName : sanitizeGeoValue2);
        } catch (Exception unused) {
            return getCachedGeoAreaInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String resolveUsableLocationName(String str) {
        if (!isPlaceholderLocationName(str)) {
            return str;
        }
        if (!isPlaceholderLocationName(this.lastLocationLabel)) {
            return this.lastLocationLabel;
        }
        WeatherMemoryCache copyMemoryCache = copyMemoryCache();
        return (copyMemoryCache == null || isPlaceholderLocationName(copyMemoryCache.locationName)) ? DEFAULT_LOCATION_TEXT : copyMemoryCache.locationName;
    }

    private String sanitizeAlertText(String str) {
        return TextUtils.isEmpty(str) ? s5.h0.FRAGMENT_ENCODE_SET : str.replace('\n', ' ').trim();
    }

    private String sanitizeGeoValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (trim.isEmpty() || "null".equalsIgnoreCase(trim)) {
            return null;
        }
        return trim;
    }

    private void saveAlertRecord(AlertRecord alertRecord) {
        synchronized (ALERT_FILE_LOCK) {
            File alertRecordFile = getAlertRecordFile();
            if (alertRecordFile == null) {
                return;
            }
            try {
                FileWriter fileWriter = new FileWriter(alertRecordFile);
                try {
                    fileWriter.write(alertRecord.toJson().toString());
                    fileWriter.close();
                } catch (Throwable th) {
                    try {
                        fileWriter.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveCache(String str, String str2, String str3, String str4, double d7, double d8, s4.f fVar) {
        synchronized (CACHE_LOCK) {
            try {
                WeatherMemoryCache weatherMemoryCache = memoryCache;
                if (weatherMemoryCache == null) {
                    weatherMemoryCache = new WeatherMemoryCache();
                    memoryCache = weatherMemoryCache;
                }
                weatherMemoryCache.locationName = resolveUsableLocationName(str);
                weatherMemoryCache.countyCode = sanitizeGeoValue(str2);
                weatherMemoryCache.countyName = sanitizeGeoValue(str3);
                weatherMemoryCache.townName = sanitizeGeoValue(str4);
                weatherMemoryCache.latitude = d7;
                weatherMemoryCache.longitude = d8;
                weatherMemoryCache.weatherText = TextUtils.isEmpty(fVar.f8733b) ? "--" : fVar.f8733b;
                weatherMemoryCache.temp = TextUtils.isEmpty(fVar.f8734c) ? "--" : fVar.f8734c;
                weatherMemoryCache.iconCode = TextUtils.isEmpty(fVar.f8732a) ? "999" : fVar.f8732a;
                weatherMemoryCache.updateTimeMs = fVar.f8735d;
                weatherMemoryCache.lastGeoLookupLatitude = this.lastGeoLookupLatitude;
                weatherMemoryCache.lastGeoLookupLongitude = this.lastGeoLookupLongitude;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void schedulePeriodicAlertRefresh() {
        this.mainHandler.removeCallbacks(this.periodicAlertRefresh);
        this.mainHandler.postDelayed(this.periodicAlertRefresh, 1200000L);
    }

    private void schedulePeriodicRefresh() {
        this.mainHandler.removeCallbacks(this.periodicRefresh);
        this.mainHandler.postDelayed(this.periodicRefresh, 1200000L);
    }

    private s4.a selectHighestPriorityAlert(List<s4.a> list) {
        s4.a aVar = null;
        for (s4.a aVar2 : list) {
            if (aVar2 != null && (!TextUtils.isEmpty(aVar2.f8721j) || !TextUtils.isEmpty(aVar2.f8716e))) {
                if (aVar == null || compareAlertPriority(aVar2, aVar) > 0) {
                    aVar = aVar2;
                }
            }
        }
        return aVar;
    }

    private boolean shouldAlertTimeRefresh() {
        return lastAlertUpdateMs <= 0 || System.currentTimeMillis() - lastAlertUpdateMs >= 1200000;
    }

    private boolean shouldAnnounce(AlertRecord alertRecord) {
        if (loadAlertRecord() == null) {
            return true;
        }
        return !alertRecord.isSameAlert(r0);
    }

    private boolean shouldLookupGeoApi(double d7, double d8) {
        return TextUtils.isEmpty(this.lastCountyCode) || Double.isNaN(this.lastGeoLookupLatitude) || Double.isNaN(this.lastGeoLookupLongitude) || distanceBetweenMeters(this.lastGeoLookupLatitude, this.lastGeoLookupLongitude, d7, d8) >= GEO_LOOKUP_DISTANCE_THRESHOLD_METERS;
    }

    private boolean shouldRequestAlert(boolean z6, boolean z7, double d7, double d8) {
        if (z6 || z7 || lastAlertUpdateMs <= 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastAlertUpdateMs >= 1200000) {
            return true;
        }
        return (Double.isNaN(lastAlertLatitude) || Double.isNaN(lastAlertLongitude)) ? currentTimeMillis - lastAlertUpdateMs >= 1200000 : distanceBetweenMeters(lastAlertLatitude, lastAlertLongitude, d7, d8) >= GEO_LOOKUP_DISTANCE_THRESHOLD_METERS && currentTimeMillis - lastAlertUpdateMs >= 1200000;
    }

    private boolean shouldTimeRefresh() {
        return this.lastWeatherUpdateMs <= 0 || System.currentTimeMillis() - this.lastWeatherUpdateMs >= 1200000;
    }

    private void startContinuousLocationUpdates() {
        if (this.locationManager == null || this.locationUpdatesActive || !hasLocationPermission()) {
            return;
        }
        try {
            boolean z6 = true;
            boolean z7 = false;
            if (g0.g.a(this.activity, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                if (g0.g.a(this.activity, "android.permission.ACCESS_COARSE_LOCATION") == 0 && this.locationManager.isProviderEnabled("network")) {
                    this.locationManager.requestLocationUpdates("network", 60000L, CropImageView.DEFAULT_ASPECT_RATIO, this.locationListener, Looper.getMainLooper());
                    this.locationUpdatesActive = z6;
                }
                z6 = z7;
                this.locationUpdatesActive = z6;
            }
            if (this.locationManager.isProviderEnabled("gps")) {
                this.locationManager.requestLocationUpdates("gps", 60000L, CropImageView.DEFAULT_ASPECT_RATIO, this.locationListener, Looper.getMainLooper());
                z7 = true;
            }
            if (this.locationManager.isProviderEnabled("network")) {
                this.locationManager.requestLocationUpdates("network", 60000L, CropImageView.DEFAULT_ASPECT_RATIO, this.locationListener, Looper.getMainLooper());
                this.locationUpdatesActive = z6;
            }
            z6 = z7;
            this.locationUpdatesActive = z6;
        } catch (Exception unused) {
        }
    }

    private void startWeatherAlertOverlay(s4.a aVar, String str, String str2) {
        if (isWeatherAlertEnabled()) {
            Intent intent = new Intent(this.activity, (Class<?>) WeatherAlertOverlayService.class);
            intent.setAction(WeatherAlertOverlayService.ACTION_SHOW_ALERT);
            intent.putExtra(WeatherAlertOverlayService.EXTRA_ICON_CODE, sanitizeAlertText(aVar.f8718g));
            intent.putExtra(WeatherAlertOverlayService.EXTRA_HEADLINE, sanitizeAlertText(aVar.f8721j));
            intent.putExtra(WeatherAlertOverlayService.EXTRA_DESCRIPTION, str);
            intent.putExtra(WeatherAlertOverlayService.EXTRA_TTS_TEXT, str2);
            this.activity.startService(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerAlertRefresh() {
        if (!isWeatherAlertEnabled()) {
            this.forceAlertRefreshOnce = false;
            this.pendingDeferredAlertRefresh = false;
            this.mainHandler.removeCallbacks(this.periodicAlertRefresh);
        } else {
            this.forceAlertRefreshOnce = true;
            if (this.refreshing) {
                this.pendingDeferredAlertRefresh = true;
            } else {
                refresh(false);
            }
        }
    }

    private void unregisterLocationUpdates() {
        LocationManager locationManager = this.locationManager;
        if (locationManager == null) {
            return;
        }
        try {
            locationManager.removeUpdates(this.locationListener);
        } catch (Exception unused) {
        }
    }

    private void updateLocationTextOnly(String str) {
        this.mainHandler.post(new z0(9, (Object) this, (Object) resolveUsableLocationName(str)));
    }

    public void onDestroy() {
        onPause();
        this.worker.shutdownNow();
    }

    public void onLocationDisplayModeChanged() {
        if (this.enabled) {
            renderCachedWeather(true);
            if (this.refreshing || !hasLocationPermission()) {
                return;
            }
            this.forceGeoLookupOnce = true;
            this.forceAlertRefreshOnce = isWeatherAlertEnabled();
            requestCurrentLocation();
        }
    }

    public void onPause() {
        unregisterLocationUpdates();
        this.mainHandler.removeCallbacks(this.locationTimeout);
        this.mainHandler.removeCallbacks(this.periodicRefresh);
        this.mainHandler.removeCallbacks(this.periodicAlertRefresh);
        this.fallbackLocation = null;
        this.locationUpdatesActive = false;
        this.pendingDeferredAlertRefresh = false;
    }

    public boolean onRequestPermissionsResult(int i7, int[] iArr) {
        int i8 = 0;
        if (i7 != 4017) {
            return false;
        }
        int length = iArr.length;
        while (true) {
            if (i8 >= length) {
                break;
            }
            if (iArr[i8] != 0) {
                i8++;
            } else if (this.enabled) {
                refresh(true);
            }
        }
        applyStatus(DEFAULT_LOCATION_TEXT, "未授予定位权限");
        return true;
    }

    public void onResume() {
        if (this.enabled) {
            renderCachedWeather(true);
            schedulePeriodicRefresh();
            if (isWeatherAlertEnabled()) {
                schedulePeriodicAlertRefresh();
            }
            startContinuousLocationUpdates();
        }
    }

    public void onWeatherAlertPreferenceChanged(boolean z6) {
        this.forceAlertRefreshOnce = z6;
        this.pendingDeferredAlertRefresh = false;
        this.mainHandler.removeCallbacks(this.periodicAlertRefresh);
        if (!z6) {
            dismissWeatherAlertOverlay();
        } else if (this.enabled) {
            schedulePeriodicAlertRefresh();
            if (this.refreshing) {
                return;
            }
            refresh(false);
        }
    }

    public void refresh(boolean z6) {
        if (!this.enabled || this.refreshing) {
            return;
        }
        if (!z6) {
            renderCachedWeather(false);
        }
        if (!hasLocationPermission()) {
            e0.g.d(this.activity, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, LOCATION_PERMISSION_REQUEST_CODE);
            applyStatus(DEFAULT_LOCATION_TEXT, "等待定位权限");
            return;
        }
        boolean z7 = true;
        boolean z8 = z6 || shouldTimeRefresh();
        boolean isWeatherAlertEnabled = isWeatherAlertEnabled();
        if (!isWeatherAlertEnabled || (!shouldAlertTimeRefresh() && !this.forceAlertRefreshOnce)) {
            z7 = false;
        }
        if (!isWeatherAlertEnabled) {
            this.forceAlertRefreshOnce = false;
        }
        if (z8 || z7) {
            if (z8) {
                applyStatus(this.textLocation.getText().toString(), "天气更新中");
            }
            requestCurrentLocation();
        }
    }

    public void setEnabled(boolean z6) {
        this.enabled = z6;
        if (z6) {
            this.forceAlertRefreshOnce = true;
            renderCachedWeather(true);
            refresh(false);
            schedulePeriodicRefresh();
            if (isWeatherAlertEnabled()) {
                schedulePeriodicAlertRefresh();
            }
            startContinuousLocationUpdates();
            return;
        }
        this.mainHandler.removeCallbacks(this.periodicRefresh);
        this.mainHandler.removeCallbacks(this.periodicAlertRefresh);
        this.mainHandler.removeCallbacks(this.locationTimeout);
        unregisterLocationUpdates();
        this.refreshing = false;
        this.fallbackLocation = null;
        this.locationUpdatesActive = false;
        this.forceAlertRefreshOnce = false;
        this.pendingDeferredAlertRefresh = false;
    }

    public void setWeatherObserver(WeatherObserver weatherObserver) {
        this.weatherObserver = weatherObserver;
    }
}
