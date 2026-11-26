package com.navi.tool.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.navi.tool.service.MobileHudService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SharedPreferencesUtils {
    public static final int CAP_ADDITIONAL = 64;
    public static final int CAP_BASE = 1;
    public static final int CAP_CARLINK = 512;
    public static final int CAP_CAST = 8;
    public static final int CAP_DASHBOARD = 2;
    public static final int CAP_DESKTOP = 256;
    public static final int CAP_FEATURES = 3950;
    public static final int CAP_LOG_LINK = 32;
    public static final int CAP_SCENE = 1024;
    public static final int CAP_STORE = 2048;
    public static final int CAP_TIRE = 4096;
    public static final int CAP_VOICE_NAV = 4;
    private static final String KEY_3D_APP_PREFIX = "3d_app_enabled_";
    private static final String KEY_AMAP_AUTO_BG_DELAY_SEC = "amap_auto_background_delay_sec";
    private static final String KEY_AMAP_AUTO_BG_ENABLED = "amap_auto_background_enabled";
    private static final String KEY_APP_PREFIX = "app_enabled_";
    private static final String KEY_AUTO_3D_ENABLED = "auto_3d_enabled";
    private static final String KEY_AUTO_BRIGHTNESS_ENABLED = "auto_brightness_enabled";
    private static final String KEY_AUTO_START_APP_DELAY_PREFIX = "auto_start_app_delay_";
    private static final String KEY_AUTO_START_APP_PREFIX = "auto_start_app_enabled_";
    private static final String KEY_AUTO_START_ENABLED = "auto_start_enabled";
    private static final String KEY_AUTO_THEME_ENABLED = "auto_theme_enabled";
    private static final String KEY_BRIGHTNESS_APP_PREFIX = "brightness_app_enabled_";
    private static final String KEY_BRIGHTNESS_THRESHOLD = "brightness_threshold";
    private static final String KEY_CAR_SETTING_AUTO_START_PREFIX = "car_setting_auto_start_enabled_";
    private static final String KEY_CLUSTER_CROSS_MASK_ENABLED = "cluster_cross_mask_enabled";
    private static final String KEY_CLUSTER_ENLARGE_CASTING_ENABLED = "cluster_enlarge_casting_enabled";
    private static final String KEY_CLUSTER_ENLARGE_CRUISE_AUTO_BG_ENABLED = "cluster_enlarge_cruise_auto_bg_enabled";
    private static final String KEY_CLUSTER_ENLARGE_CRUISE_ENABLED = "cluster_enlarge_cruise_enabled";
    private static final String KEY_CLUSTER_ENLARGE_NAV_ENABLED = "cluster_enlarge_nav_enabled";
    private static final String KEY_CONFIG_SYNC_DONE = "config_sync_done";
    private static final String KEY_CRUISE_3D_ENABLED = "cruise_3d_enabled";
    private static final String KEY_CRUISE_FIXED_SCALE_ENABLED = "cruise_fixed_scale_enabled";
    private static final String KEY_CRUISE_SCALE = "cruise_scale";
    private static final String KEY_CRUISE_TRAFFIC_LIGHT_VOICE_ENABLED = "cruise_traffic_light_voice_enabled";
    private static final String KEY_CUSTOM_AMAP_BROADCAST_ENABLED = "custom_amap_broadcast_enabled";
    private static final String KEY_CUSTOM_AMAP_BROADCAST_VALUE = "custom_amap_broadcast_value";
    private static final String KEY_DELAYED_NAV_DELAY_SEC = "delayed_nav_delay_sec";
    private static final String KEY_DELAYED_NAV_ENABLED = "delayed_nav_enabled";
    private static final String KEY_DESKTOP_ASSISTANT_AUTO_START_ENABLED = "desktop_assistant_auto_start_enabled";
    private static final String KEY_DESTINATION_WEATHER_ENABLED = "destination_weather_enabled";
    private static final String KEY_DESTINATION_WEATHER_THRESHOLD = "destination_weather_threshold";
    private static final String KEY_FLOATING_MAP_PKG = "desktop_map_package";
    private static final String KEY_FLOATING_MAP_PKG_2 = "desktop_map_package_2";
    private static final String KEY_FLOATING_MUSIC_PKG = "desktop_music_package";
    private static final String KEY_GESTURE_BACK_ENABLED = "gesture_back_enabled";
    private static final String KEY_INITIAL_RESTART_DONE = "initial_restart_done";
    private static final String KEY_LAST_AUTO_CRASH_UPLOAD = "last_auto_crash_upload_at";
    private static final String KEY_LICENSE_TOKEN = "license_token";
    private static final String KEY_LICENSE_UID = "license_uid";
    private static final String KEY_LICENSE_UPLOADED = "license_uid_uploaded";
    private static final String KEY_LOG_ASSISTANT_LINK_ENABLED = "log_link_assistant_switch_state";
    private static final String KEY_LOG_MEDIA_CHANNEL = "log_media_channel";
    private static final String KEY_LOG_MEDIA_VOLUME = "log_media_volume";
    private static final String KEY_MANUAL_ROUTE_SELECT_ENABLED = "manual_route_select_enabled";
    private static final String KEY_NATIVE_AMAP_SELECTED_NAME = "native_amap_selected_name";
    private static final String KEY_NATIVE_AMAP_SELECTED_PKG = "native_amap_selected_pkg";
    private static final String KEY_NAVIGATION_3D_ENABLED = "navigation_3d_enabled";
    private static final String KEY_NAVIGATION_FIXED_SCALE_ENABLED = "navigation_fixed_scale_enabled";
    private static final String KEY_NAVIGATION_PERSONALIZED_ENABLED = "navigation_personalized_enabled";
    private static final String KEY_NAVIGATION_SCALE = "navigation_scale";
    private static final String KEY_OTP_VERIFIED = "otp_verified";
    private static final String KEY_RETURN_APP_DELAY_SEC = "return_app_delay_sec";
    private static final String KEY_RETURN_APP_ENABLED = "return_app_enabled";
    private static final String KEY_RETURN_APP_PREFIX = "return_app_enabled_";
    private static final String KEY_ROUTE_TRANSFER_ENABLED = "route_transfer_enabled";
    private static final String KEY_ROUTE_TRANSFER_POST_ACTION_ENABLED = "route_transfer_post_action_enabled";
    private static final String KEY_ROUTE_TRANSFER_POST_ACTION_TYPE = "route_transfer_post_action_type";
    private static final String KEY_SCREEN_CASTING_APP_STATE_PREFIX = "screen_casting_app_state_";
    private static final String KEY_SCREEN_CASTING_CUSTOM_PKGS = "screen_casting_custom_pkgs";
    private static final String KEY_SCREEN_CASTING_ENABLED = "screen_casting_enabled";
    private static final String KEY_SCREEN_CASTING_ICON_ALPHA = "screen_casting_icon_alpha";
    private static final String KEY_SCREEN_CASTING_ICON_SIZE = "screen_casting_icon_size";
    private static final String KEY_SCREEN_CASTING_ICON_X = "screen_casting_icon_x";
    private static final String KEY_SCREEN_CASTING_ICON_Y = "screen_casting_icon_y";
    private static final String KEY_SCREEN_CASTING_SELECTED_PKG = "screen_casting_selected_pkg";
    private static final String KEY_SCREEN_ON_AUTO_START_APP_PREFIX = "screen_on_auto_start_app_enabled_";
    private static final String KEY_SCREEN_ON_AUTO_START_ENABLED = "screen_on_auto_start_enabled";
    private static final String KEY_STEERING_WHEEL_BG_MODE = "steering_wheel_bg_mode";
    private static final String KEY_STEERING_WHEEL_EXT_SCREEN_ENABLED = "steering_wheel_ext_screen_enabled";
    private static final String KEY_STR_WAKE_AUTO_START_ENABLED = "str_wake_auto_start_enabled";
    private static final String KEY_THEME_APP_PREFIX = "theme_app_enabled_";
    private static final String KEY_TOMBSTONE_AUTO_START_PREFIX = "tombstone_auto_start_";
    private static final String KEY_TOMBSTONE_DOCK_INDEX_PREFIX = "tombstone_dock_index_";
    private static final String KEY_TOMBSTONE_DOCK_PREFIX = "tombstone_dock_";
    private static final String KEY_TRADITIONAL_CLUSTER_ENABLED = "traditional_cluster_enabled";
    private static final String KEY_TRAFFIC_LIGHT_SPEED_LIMIT = "traffic_light_speed_limit";
    private static final String KEY_TTS_APP_PREFIX = "tts_app_enabled_";
    private static final String KEY_TTS_ENABLED = "tts_enabled";
    private static final String KEY_TTS_ROAD_NAME_ENABLED = "tts_road_name_enabled";
    private static final String KEY_VOICE_NAV_APP_PREFIX = "voice_nav_app_enabled_";
    private static final String KEY_ZONE_SPEED_VOICE_ENABLED = "zone_speed_voice_enabled";
    private static final String KEY_ZONE_SPEED_VOICE_INTERVAL_MINUTES = "zone_speed_voice_interval_minutes";
    private static final String KEY_ZONE_SPEED_VOICE_MAX_COUNT = "zone_speed_voice_max_count";
    private static final String KEY_ZONE_SPEED_VOICE_THRESHOLD = "zone_speed_voice_threshold";
    private static final String PREF_NAME = "navi_tool_prefs";

    public static void clearOtpVerified(Context context) {
        getSharedPreferences(context).edit().remove(KEY_OTP_VERIFIED).apply();
    }

    public static int getAmapAutoBackgroundDelaySeconds(Context context) {
        return getSharedPreferences(context).getInt(KEY_AMAP_AUTO_BG_DELAY_SEC, 5);
    }

    public static SharedPreferences getAppPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, 0);
    }

    public static int getAutoStartAppCount(Context context) {
        Map<String, ?> all = getSharedPreferences(context).getAll();
        int i7 = 0;
        for (String str : all.keySet()) {
            if (str.startsWith(KEY_AUTO_START_APP_PREFIX)) {
                Object obj = all.get(str);
                if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                    i7++;
                }
            }
        }
        return i7;
    }

    public static int getAutoStartAppDelay(Context context, String str) {
        return getSharedPreferences(context).getInt(KEY_AUTO_START_APP_DELAY_PREFIX + str, 2);
    }

    public static int getBrightnessThreshold(Context context) {
        return getSharedPreferences(context).getInt(KEY_BRIGHTNESS_THRESHOLD, 50);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
    public static int getCarSettingAutoStartCount(Context context) {
        ?? isCarSettingAutoStartEnabled = isCarSettingAutoStartEnabled(context, MobileHudService.CONNECTION_MODE_WIFI);
        int i7 = isCarSettingAutoStartEnabled;
        if (isCarSettingAutoStartEnabled(context, "hotspot")) {
            i7 = isCarSettingAutoStartEnabled + 1;
        }
        return isCarSettingAutoStartEnabled(context, MobileHudService.CONNECTION_MODE_BLUETOOTH) ? i7 + 1 : i7;
    }

    public static int getCruiseScale(Context context) {
        return getSharedPreferences(context).getInt(KEY_CRUISE_SCALE, 10);
    }

    public static String getCustomAmapBroadcastValue(Context context) {
        return getSharedPreferences(context).getString(KEY_CUSTOM_AMAP_BROADCAST_VALUE, AmapBroadcastConstants.DEFAULT_CUSTOM_AMAP_BROADCAST);
    }

    public static int getDelayedNavDelaySeconds(Context context) {
        return getSharedPreferences(context).getInt(KEY_DELAYED_NAV_DELAY_SEC, 5);
    }

    public static int getDestinationWeatherThreshold(Context context) {
        return getSharedPreferences(context).getInt(KEY_DESTINATION_WEATHER_THRESHOLD, 20);
    }

    public static long getLastAutoCrashUploadAt(Context context) {
        return getSharedPreferences(context).getLong(KEY_LAST_AUTO_CRASH_UPLOAD, 0L);
    }

    public static String getLicenseToken(Context context) {
        return getSharedPreferences(context).getString(KEY_LICENSE_TOKEN, null);
    }

    public static String getLicenseUid(Context context) {
        return getSharedPreferences(context).getString(KEY_LICENSE_UID, null);
    }

    public static String getLogMediaChannel(Context context) {
        return getSharedPreferences(context).getString(KEY_LOG_MEDIA_CHANNEL, "notification");
    }

    public static int getLogMediaVolume(Context context) {
        return getSharedPreferences(context).getInt(KEY_LOG_MEDIA_VOLUME, 40);
    }

    public static String getNativeAmapSelectedName(Context context) {
        return getSharedPreferences(context).getString(KEY_NATIVE_AMAP_SELECTED_NAME, null);
    }

    public static String getNativeAmapSelectedPackage(Context context) {
        return getSharedPreferences(context).getString(KEY_NATIVE_AMAP_SELECTED_PKG, null);
    }

    public static int getNavigationScale(Context context) {
        return getSharedPreferences(context).getInt(KEY_NAVIGATION_SCALE, 10);
    }

    public static int getNextAutoStartDelay(Context context) {
        return getNextAutoStartDelay(context, null);
    }

    public static int getNextAutoStartDelay(Context context, String str) {
        int autoStartAppDelay;
        Map<String, ?> all = getSharedPreferences(context).getAll();
        boolean z6 = false;
        int i7 = 0;
        for (String str2 : all.keySet()) {
            if (str2.startsWith(KEY_AUTO_START_APP_PREFIX)) {
                Object obj = all.get(str2);
                if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                    String substring = str2.substring(23);
                    if (str == null || !str.equals(substring)) {
                        if (!"com.navi.tool.desktopassistant".equals(substring) && (autoStartAppDelay = getAutoStartAppDelay(context, substring)) > i7) {
                            z6 = true;
                            i7 = autoStartAppDelay;
                        }
                    }
                }
            }
        }
        if (z6) {
            return i7 + 2;
        }
        return 2;
    }

    public static int getReturnAppDelaySeconds(Context context) {
        return getSharedPreferences(context).getInt(KEY_RETURN_APP_DELAY_SEC, 5);
    }

    public static int getRouteTransferPostActionType(Context context) {
        return getSharedPreferences(context).getInt(KEY_ROUTE_TRANSFER_POST_ACTION_TYPE, 10021);
    }

    public static String getScreenCastingAppState(Context context, String str) {
        return getSharedPreferences(context).getString(KEY_SCREEN_CASTING_APP_STATE_PREFIX + str, "main");
    }

    public static String getScreenCastingCustomPackages(Context context) {
        return getSharedPreferences(context).getString(KEY_SCREEN_CASTING_CUSTOM_PKGS, h0.FRAGMENT_ENCODE_SET);
    }

    public static int getScreenCastingIconAlpha(Context context) {
        return getSharedPreferences(context).getInt(KEY_SCREEN_CASTING_ICON_ALPHA, 100);
    }

    public static int getScreenCastingIconSize(Context context) {
        return getSharedPreferences(context).getInt(KEY_SCREEN_CASTING_ICON_SIZE, 70);
    }

    public static int getScreenCastingIconX(Context context) {
        return getSharedPreferences(context).getInt(KEY_SCREEN_CASTING_ICON_X, 0);
    }

    public static int getScreenCastingIconY(Context context) {
        return getSharedPreferences(context).getInt(KEY_SCREEN_CASTING_ICON_Y, 0);
    }

    public static String getScreenCastingSelectedPkg(Context context) {
        return getSharedPreferences(context).getString(KEY_SCREEN_CASTING_SELECTED_PKG, null);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, 0);
    }

    public static int getTrafficLightSpeedLimit(Context context) {
        return getSharedPreferences(context).getInt(KEY_TRAFFIC_LIGHT_SPEED_LIMIT, 40);
    }

    public static int getZoneSpeedVoiceIntervalMinutes(Context context) {
        return getSharedPreferences(context).getInt(KEY_ZONE_SPEED_VOICE_INTERVAL_MINUTES, 2);
    }

    public static int getZoneSpeedVoiceMaxCount(Context context) {
        return getSharedPreferences(context).getInt(KEY_ZONE_SPEED_VOICE_MAX_COUNT, 5);
    }

    public static int getZoneSpeedVoiceThreshold(Context context) {
        return getSharedPreferences(context).getInt(KEY_ZONE_SPEED_VOICE_THRESHOLD, 10);
    }

    private static int indexInOrder(String str, String str2) {
        if (str != null && !str.isEmpty()) {
            String[] split = str.split(",");
            for (int i7 = 0; i7 < split.length; i7++) {
                if (str2.equals(split[i7].trim())) {
                    return i7;
                }
            }
        }
        return -1;
    }

    private static String insertIntoOrder(String str, String str2, int i7) {
        if (str == null || str.isEmpty()) {
            return str2;
        }
        String[] split = str.split(",");
        ArrayList arrayList = new ArrayList();
        for (String str3 : split) {
            String trim = str3.trim();
            if (!trim.isEmpty() && !str2.equals(trim)) {
                arrayList.add(trim);
            }
        }
        if (i7 < 0 || i7 > arrayList.size()) {
            arrayList.add(str2);
        } else {
            arrayList.add(i7, str2);
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str4 = (String) it.next();
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(str4);
        }
        return sb.toString();
    }

    public static boolean is3DAppEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_3D_APP_PREFIX + str, false);
    }

    public static boolean isAmapAutoBackgroundEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_AMAP_AUTO_BG_ENABLED, false);
    }

    public static boolean isAppEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_APP_PREFIX + str, false);
    }

    public static boolean isAuto3DEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_AUTO_3D_ENABLED, false);
    }

    public static boolean isAutoBrightnessEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_AUTO_BRIGHTNESS_ENABLED, false);
    }

    public static boolean isAutoStartAppEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_AUTO_START_APP_PREFIX + str, false);
    }

    public static boolean isAutoStartEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_AUTO_START_ENABLED, false);
    }

    public static boolean isAutoThemeEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_AUTO_THEME_ENABLED, false);
    }

    public static boolean isBrightnessAppEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_BRIGHTNESS_APP_PREFIX + str, false);
    }

    public static boolean isCarSettingAutoStartEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_CAR_SETTING_AUTO_START_PREFIX + str, false);
    }

    public static boolean isClusterCrossMaskEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CLUSTER_CROSS_MASK_ENABLED, false);
    }

    public static boolean isClusterEnlargeCruiseAutoBgEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CLUSTER_ENLARGE_CRUISE_AUTO_BG_ENABLED, false);
    }

    public static boolean isClusterEnlargeOnCasting(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CLUSTER_ENLARGE_CASTING_ENABLED, false);
    }

    public static boolean isClusterEnlargeOnCruise(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CLUSTER_ENLARGE_CRUISE_ENABLED, false);
    }

    public static boolean isClusterEnlargeOnNavigation(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CLUSTER_ENLARGE_NAV_ENABLED, false);
    }

    public static boolean isConfigSyncDone(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CONFIG_SYNC_DONE, false);
    }

    public static boolean isCruise3DEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CRUISE_3D_ENABLED, false);
    }

    public static boolean isCruiseFixedScaleEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CRUISE_FIXED_SCALE_ENABLED, false);
    }

    public static boolean isCruiseTrafficLightVoiceEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CRUISE_TRAFFIC_LIGHT_VOICE_ENABLED, false);
    }

    public static boolean isCustomAmapBroadcastEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_CUSTOM_AMAP_BROADCAST_ENABLED, false);
    }

    public static boolean isDelayedNavEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_DELAYED_NAV_ENABLED, false);
    }

    public static boolean isDesktopAssistantAutoStartEnabled(Context context) {
        return isAutoStartAppEnabled(context, "com.navi.tool.desktopassistant");
    }

    public static boolean isDestinationWeatherEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_DESTINATION_WEATHER_ENABLED, false);
    }

    public static boolean isGestureBackEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_GESTURE_BACK_ENABLED, false);
    }

    public static boolean isInitialRestartDone(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_INITIAL_RESTART_DONE, false);
    }

    public static boolean isLicenseUploaded(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_LICENSE_UPLOADED, false);
    }

    public static boolean isLogAssistantLinkEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_LOG_ASSISTANT_LINK_ENABLED, false);
    }

    public static boolean isManualRouteSelectEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_MANUAL_ROUTE_SELECT_ENABLED, false);
    }

    public static boolean isNavigation3DEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_NAVIGATION_3D_ENABLED, false);
    }

    public static boolean isNavigationFixedScaleEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_NAVIGATION_FIXED_SCALE_ENABLED, false);
    }

    public static boolean isNavigationPersonalizedEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_NAVIGATION_PERSONALIZED_ENABLED, false);
    }

    public static boolean isOtpVerified(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_OTP_VERIFIED, false);
    }

    public static boolean isReturnAppEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_RETURN_APP_ENABLED, false);
    }

    public static boolean isReturnAppEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_RETURN_APP_PREFIX + str, false);
    }

    public static boolean isRouteTransferEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_ROUTE_TRANSFER_ENABLED, false);
    }

    public static boolean isRouteTransferPostActionEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_ROUTE_TRANSFER_POST_ACTION_ENABLED, false);
    }

    public static boolean isScreenCastingEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_SCREEN_CASTING_ENABLED, false);
    }

    public static boolean isScreenOnAutoStartAppEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_SCREEN_ON_AUTO_START_APP_PREFIX + str, true);
    }

    public static boolean isScreenOnAutoStartEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_SCREEN_ON_AUTO_START_ENABLED, false);
    }

    public static boolean isSteeringWheelBackgroundMode(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_STEERING_WHEEL_BG_MODE, true);
    }

    public static boolean isSteeringWheelExtScreenEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_STEERING_WHEEL_EXT_SCREEN_ENABLED, false);
    }

    public static boolean isStrWakeAutoStartEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_STR_WAKE_AUTO_START_ENABLED, false);
    }

    public static boolean isThemeAppEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_THEME_APP_PREFIX + str, false);
    }

    public static boolean isTraditionalClusterEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_TRADITIONAL_CLUSTER_ENABLED, false);
    }

    public static boolean isTtsAppEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_TTS_APP_PREFIX + str, false);
    }

    public static boolean isTtsEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_TTS_ENABLED, false);
    }

    public static boolean isTtsRoadNameEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_TTS_ROAD_NAME_ENABLED, false);
    }

    public static boolean isVoiceNavAppEnabled(Context context, String str) {
        return getSharedPreferences(context).getBoolean(KEY_VOICE_NAV_APP_PREFIX + str, false);
    }

    public static boolean isZoneSpeedVoiceEnabled(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_ZONE_SPEED_VOICE_ENABLED, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00d3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void onAppUninstalled(android.content.Context r17, java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.utils.SharedPreferencesUtils.onAppUninstalled(android.content.Context, java.lang.String):void");
    }

    private static void recordFloatingTombstone(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(KEY_FLOATING_MAP_PKG, null);
        String string2 = sharedPreferences.getString(KEY_FLOATING_MAP_PKG_2, null);
        String string3 = sharedPreferences.getString(KEY_FLOATING_MUSIC_PKG, null);
        if (str.equals(string) || str.equals(string2) || str.equals(string3)) {
            boolean z6 = sharedPreferences.getBoolean(KEY_AUTO_START_APP_PREFIX.concat(str), false);
            boolean z7 = sharedPreferences.getBoolean("dock_app_enabled_".concat(str), false);
            if (z6 || z7) {
                sharedPreferences.edit().putBoolean(KEY_TOMBSTONE_AUTO_START_PREFIX.concat(str), z6).putBoolean(KEY_TOMBSTONE_DOCK_PREFIX.concat(str), z7).putInt(KEY_TOMBSTONE_DOCK_INDEX_PREFIX.concat(str), indexInOrder(sharedPreferences.getString("dock_app_order", h0.FRAGMENT_ENCODE_SET), str)).apply();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x005c A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void removeAutoStartApp(android.content.Context r12, java.lang.String r13) {
        /*
            android.content.SharedPreferences r0 = getSharedPreferences(r12)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "auto_start_app_delay_"
            r1.<init>(r2)
            r1.append(r13)
            java.lang.String r1 = r1.toString()
            r3 = -1
            int r1 = r0.getInt(r1, r3)
            r4 = 1
            r5 = 0
            java.lang.String r6 = "auto_start_app_enabled_"
            if (r1 != r3) goto L32
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            r7.append(r13)
            java.lang.String r7 = r7.toString()
            boolean r7 = r0.contains(r7)
            if (r7 == 0) goto L30
            goto L32
        L30:
            r7 = r5
            goto L33
        L32:
            r7 = r4
        L33:
            android.content.SharedPreferences$Editor r8 = r0.edit()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r6)
            r9.append(r13)
            java.lang.String r9 = r9.toString()
            android.content.SharedPreferences$Editor r8 = r8.remove(r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r2)
            r9.append(r13)
            java.lang.String r9 = r9.toString()
            android.content.SharedPreferences$Editor r8 = r8.remove(r9)
            r8.apply()
            if (r7 != 0) goto L5d
            return
        L5d:
            r7 = 2
            if (r1 != r3) goto L61
            r1 = r7
        L61:
            if (r1 < r7) goto Ldd
            java.util.Map r3 = r0.getAll()
            android.content.SharedPreferences$Editor r0 = r0.edit()
            java.util.Set r8 = r3.keySet()
            java.util.Iterator r8 = r8.iterator()
        L73:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto Lb7
            java.lang.Object r9 = r8.next()
            java.lang.String r9 = (java.lang.String) r9
            boolean r10 = r9.startsWith(r6)
            if (r10 == 0) goto L73
            java.lang.Object r10 = r3.get(r9)
            boolean r11 = r10 instanceof java.lang.Boolean
            if (r11 == 0) goto L73
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L73
            r10 = 23
            java.lang.String r9 = r9.substring(r10)
            boolean r10 = r9.equals(r13)
            if (r10 == 0) goto La2
            goto L73
        La2:
            int r10 = getAutoStartAppDelay(r12, r9)
            if (r10 <= r1) goto L73
            int r10 = r10 + (-2)
            int r5 = java.lang.Math.max(r7, r10)
            java.lang.String r9 = r2.concat(r9)
            r0.putInt(r9, r5)
            r5 = r4
            goto L73
        Lb7:
            java.lang.String r13 = "auto_start_app_enabled_com.navi.tool.desktopassistant"
            boolean r13 = r3.containsKey(r13)
            if (r13 != 0) goto Ld8
            boolean r13 = isDesktopAssistantAutoStartEnabled(r12)
            if (r13 == 0) goto Ld8
            java.lang.String r13 = "com.navi.tool.desktopassistant"
            int r12 = getAutoStartAppDelay(r12, r13)
            if (r12 <= r1) goto Ld8
            int r12 = r12 - r7
            int r12 = java.lang.Math.max(r7, r12)
            java.lang.String r13 = "auto_start_app_delay_com.navi.tool.desktopassistant"
            r0.putInt(r13, r12)
            goto Lda
        Ld8:
            if (r5 == 0) goto Ldd
        Lda:
            r0.apply()
        Ldd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.utils.SharedPreferencesUtils.removeAutoStartApp(android.content.Context, java.lang.String):void");
    }

    private static String removePackageFromOrder(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        StringBuilder sb = new StringBuilder();
        for (String str3 : str.split(",")) {
            String trim = str3.trim();
            if (!trim.isEmpty() && !str2.equals(trim)) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(trim);
            }
        }
        return sb.toString();
    }

    public static boolean restoreUninstalledFloatingConfig(Context context, String str) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String B = a.a.B(KEY_TOMBSTONE_AUTO_START_PREFIX, str);
        String B2 = a.a.B(KEY_TOMBSTONE_DOCK_PREFIX, str);
        String B3 = a.a.B(KEY_TOMBSTONE_DOCK_INDEX_PREFIX, str);
        if (!sharedPreferences.contains(B) && !sharedPreferences.contains(B2)) {
            return false;
        }
        String string = sharedPreferences.getString(KEY_FLOATING_MAP_PKG, null);
        String string2 = sharedPreferences.getString(KEY_FLOATING_MAP_PKG_2, null);
        String string3 = sharedPreferences.getString(KEY_FLOATING_MUSIC_PKG, null);
        if (!str.equals(string) && !str.equals(string2) && !str.equals(string3)) {
            sharedPreferences.edit().remove(B).remove(B2).remove(B3).apply();
            return false;
        }
        boolean z6 = sharedPreferences.getBoolean(B, false);
        boolean z7 = sharedPreferences.getBoolean(B2, false);
        int i7 = sharedPreferences.getInt(B3, -1);
        SharedPreferences.Editor remove = sharedPreferences.edit().remove(B).remove(B2).remove(B3);
        if (z6) {
            setAutoStartAppEnabled(context, str, true);
            setAutoStartAppDelay(context, str, getNextAutoStartDelay(context, str));
        }
        if (z7) {
            remove.putBoolean("dock_app_enabled_".concat(str), true);
            String string4 = sharedPreferences.getString("dock_app_order", h0.FRAGMENT_ENCODE_SET);
            String insertIntoOrder = insertIntoOrder(string4, str, i7);
            if (!insertIntoOrder.equals(string4)) {
                remove.putString("dock_app_order", insertIntoOrder);
            }
        }
        remove.apply();
        return true;
    }

    public static void set3DAppEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_3D_APP_PREFIX + str, z6).apply();
    }

    public static void setAmapAutoBackgroundDelaySeconds(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_AMAP_AUTO_BG_DELAY_SEC, i7);
    }

    public static void setAmapAutoBackgroundEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_AMAP_AUTO_BG_ENABLED, z6);
    }

    public static void setAppEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_APP_PREFIX + str, z6).apply();
    }

    public static void setAuto3DEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_AUTO_3D_ENABLED, z6);
    }

    public static void setAutoBrightnessEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_AUTO_BRIGHTNESS_ENABLED, z6);
    }

    public static void setAutoStartAppDelay(Context context, String str, int i7) {
        getSharedPreferences(context).edit().putInt(KEY_AUTO_START_APP_DELAY_PREFIX + str, i7).apply();
        if (str.equals("com.navi.tool.desktopassistant") || !isDesktopAssistantAutoStartEnabled(context) || i7 < getAutoStartAppDelay(context, "com.navi.tool.desktopassistant")) {
            return;
        }
        setAutoStartAppDelay(context, "com.navi.tool.desktopassistant", i7 + 2);
    }

    public static void setAutoStartAppEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_AUTO_START_APP_PREFIX + str, z6).apply();
    }

    public static void setAutoStartEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_AUTO_START_ENABLED, z6);
    }

    public static void setAutoThemeEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_AUTO_THEME_ENABLED, z6);
    }

    public static void setBrightnessAppEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_BRIGHTNESS_APP_PREFIX + str, z6).apply();
    }

    public static void setBrightnessThreshold(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_BRIGHTNESS_THRESHOLD, i7);
    }

    public static void setCarSettingAutoStartEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_CAR_SETTING_AUTO_START_PREFIX + str, z6).apply();
    }

    public static void setClusterCrossMaskEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CLUSTER_CROSS_MASK_ENABLED, z6);
    }

    public static void setClusterEnlargeCruiseAutoBgEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CLUSTER_ENLARGE_CRUISE_AUTO_BG_ENABLED, z6);
    }

    public static void setClusterEnlargeOnCasting(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CLUSTER_ENLARGE_CASTING_ENABLED, z6);
    }

    public static void setClusterEnlargeOnCruise(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CLUSTER_ENLARGE_CRUISE_ENABLED, z6);
    }

    public static void setClusterEnlargeOnNavigation(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CLUSTER_ENLARGE_NAV_ENABLED, z6);
    }

    public static void setConfigSyncDone(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CONFIG_SYNC_DONE, z6);
    }

    public static void setCruise3DEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CRUISE_3D_ENABLED, z6);
    }

    public static void setCruiseFixedScaleEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CRUISE_FIXED_SCALE_ENABLED, z6);
    }

    public static void setCruiseScale(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_CRUISE_SCALE, i7);
    }

    public static void setCruiseTrafficLightVoiceEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CRUISE_TRAFFIC_LIGHT_VOICE_ENABLED, z6);
    }

    public static void setCustomAmapBroadcastEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_CUSTOM_AMAP_BROADCAST_ENABLED, z6);
    }

    public static void setCustomAmapBroadcastValue(Context context, String str) {
        getSharedPreferences(context).edit().putString(KEY_CUSTOM_AMAP_BROADCAST_VALUE, str).apply();
    }

    public static void setDelayedNavDelaySeconds(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_DELAYED_NAV_DELAY_SEC, i7);
    }

    public static void setDelayedNavEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_DELAYED_NAV_ENABLED, z6);
    }

    public static void setDesktopAssistantAutoStartEnabled(Context context, boolean z6) {
        setAutoStartAppEnabled(context, "com.navi.tool.desktopassistant", z6);
        if (z6) {
            setAutoStartAppDelay(context, "com.navi.tool.desktopassistant", getNextAutoStartDelay(context, "com.navi.tool.desktopassistant"));
        }
    }

    public static void setDestinationWeatherEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_DESTINATION_WEATHER_ENABLED, z6);
    }

    public static void setDestinationWeatherThreshold(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_DESTINATION_WEATHER_THRESHOLD, i7);
    }

    public static void setGestureBackEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_GESTURE_BACK_ENABLED, z6);
    }

    public static void setInitialRestartDone(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_INITIAL_RESTART_DONE, z6);
    }

    public static void setLastAutoCrashUploadAt(Context context, long j7) {
        getSharedPreferences(context).edit().putLong(KEY_LAST_AUTO_CRASH_UPLOAD, j7).apply();
    }

    public static boolean setLicenseState(Context context, String str, String str2) {
        String licenseUid = getLicenseUid(context);
        if (licenseUid != null && !licenseUid.isEmpty()) {
            if (str == null) {
                str = h0.FRAGMENT_ENCODE_SET;
            }
            if (str2 == null) {
                str2 = h0.FRAGMENT_ENCODE_SET;
            }
            try {
                String persistLicenseToken = NativeBridge.persistLicenseToken(str, licenseUid, str2);
                if (persistLicenseToken != null && !persistLicenseToken.isEmpty()) {
                    getSharedPreferences(context).edit().putString(KEY_LICENSE_TOKEN, persistLicenseToken).apply();
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public static void setLicenseUid(Context context, String str) {
        getSharedPreferences(context).edit().putString(KEY_LICENSE_UID, str).apply();
    }

    public static void setLicenseUploaded(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_LICENSE_UPLOADED, z6);
    }

    public static void setLogAssistantLinkEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_LOG_ASSISTANT_LINK_ENABLED, z6);
    }

    public static void setLogMediaChannel(Context context, String str) {
        getSharedPreferences(context).edit().putString(KEY_LOG_MEDIA_CHANNEL, (str == null || str.trim().isEmpty()) ? "notification" : str.trim().toLowerCase()).apply();
    }

    public static void setLogMediaVolume(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_LOG_MEDIA_VOLUME, i7);
    }

    public static void setManualRouteSelectEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_MANUAL_ROUTE_SELECT_ENABLED, z6);
    }

    public static void setNativeAmapSelectedName(Context context, String str) {
        getSharedPreferences(context).edit().putString(KEY_NATIVE_AMAP_SELECTED_NAME, str).apply();
    }

    public static void setNativeAmapSelectedPackage(Context context, String str) {
        getSharedPreferences(context).edit().putString(KEY_NATIVE_AMAP_SELECTED_PKG, str).apply();
    }

    public static void setNavigation3DEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_NAVIGATION_3D_ENABLED, z6);
    }

    public static void setNavigationFixedScaleEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_NAVIGATION_FIXED_SCALE_ENABLED, z6);
    }

    public static void setNavigationPersonalizedEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_NAVIGATION_PERSONALIZED_ENABLED, z6);
    }

    public static void setNavigationScale(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_NAVIGATION_SCALE, i7);
    }

    public static void setOtpVerified(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_OTP_VERIFIED, z6);
    }

    public static void setReturnAppDelaySeconds(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_RETURN_APP_DELAY_SEC, i7);
    }

    public static void setReturnAppEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_RETURN_APP_PREFIX + str, z6).apply();
    }

    public static void setReturnAppEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_RETURN_APP_ENABLED, z6);
    }

    public static void setRouteTransferEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_ROUTE_TRANSFER_ENABLED, z6);
    }

    public static void setRouteTransferPostActionEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_ROUTE_TRANSFER_POST_ACTION_ENABLED, z6);
    }

    public static void setRouteTransferPostActionType(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_ROUTE_TRANSFER_POST_ACTION_TYPE, i7);
    }

    public static void setScreenCastingAppState(Context context, String str, String str2) {
        getSharedPreferences(context).edit().putString(KEY_SCREEN_CASTING_APP_STATE_PREFIX + str, str2).apply();
    }

    public static void setScreenCastingCustomPackages(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreferences(context).edit();
        if (str == null) {
            str = h0.FRAGMENT_ENCODE_SET;
        }
        edit.putString(KEY_SCREEN_CASTING_CUSTOM_PKGS, str).apply();
    }

    public static void setScreenCastingEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_SCREEN_CASTING_ENABLED, z6);
    }

    public static void setScreenCastingIconAlpha(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_SCREEN_CASTING_ICON_ALPHA, i7);
    }

    public static void setScreenCastingIconPosition(Context context, int i7, int i8) {
        getSharedPreferences(context).edit().putInt(KEY_SCREEN_CASTING_ICON_X, i7).putInt(KEY_SCREEN_CASTING_ICON_Y, i8).apply();
    }

    public static void setScreenCastingIconSize(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_SCREEN_CASTING_ICON_SIZE, i7);
    }

    public static void setScreenCastingSelectedPkg(Context context, String str) {
        getSharedPreferences(context).edit().putString(KEY_SCREEN_CASTING_SELECTED_PKG, str).apply();
    }

    public static void setScreenOnAutoStartAppEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_SCREEN_ON_AUTO_START_APP_PREFIX + str, z6).apply();
    }

    public static void setScreenOnAutoStartEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_SCREEN_ON_AUTO_START_ENABLED, z6);
    }

    public static void setSteeringWheelBackgroundMode(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_STEERING_WHEEL_BG_MODE, z6);
    }

    public static void setSteeringWheelExtScreenEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_STEERING_WHEEL_EXT_SCREEN_ENABLED, z6);
    }

    public static void setStrWakeAutoStartEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_STR_WAKE_AUTO_START_ENABLED, z6);
    }

    public static void setThemeAppEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_THEME_APP_PREFIX + str, z6).apply();
    }

    public static void setTraditionalClusterEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_TRADITIONAL_CLUSTER_ENABLED, z6);
    }

    public static void setTrafficLightSpeedLimit(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_TRAFFIC_LIGHT_SPEED_LIMIT, i7);
    }

    public static void setTtsAppEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_TTS_APP_PREFIX + str, z6).apply();
    }

    public static void setTtsEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_TTS_ENABLED, z6);
    }

    public static void setTtsRoadNameEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_TTS_ROAD_NAME_ENABLED, z6);
    }

    public static void setVoiceNavAppEnabled(Context context, String str, boolean z6) {
        getSharedPreferences(context).edit().putBoolean(KEY_VOICE_NAV_APP_PREFIX + str, z6).apply();
    }

    public static void setZoneSpeedVoiceEnabled(Context context, boolean z6) {
        a.a.m(getSharedPreferences(context), KEY_ZONE_SPEED_VOICE_ENABLED, z6);
    }

    public static void setZoneSpeedVoiceIntervalMinutes(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_ZONE_SPEED_VOICE_INTERVAL_MINUTES, i7);
    }

    public static void setZoneSpeedVoiceMaxCount(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_ZONE_SPEED_VOICE_MAX_COUNT, i7);
    }

    public static void setZoneSpeedVoiceThreshold(Context context, int i7) {
        a.a.l(getSharedPreferences(context), KEY_ZONE_SPEED_VOICE_THRESHOLD, i7);
    }
}
