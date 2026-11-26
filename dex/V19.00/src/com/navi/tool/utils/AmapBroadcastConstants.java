package com.navi.tool.utils;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AmapBroadcastConstants {
    private static String AMAP_BROADCAST = "AUTONAVI_STANDARD_BROADCAST_SEND";
    public static final String DEFAULT_AMAP_BROADCAST = "AUTONAVI_STANDARD_BROADCAST_SEND";
    public static final String DEFAULT_CUSTOM_AMAP_BROADCAST = "AUTONAVI_STANDARD_BROADCAST_DIY";
    public static final String EXTRA_SELF_BROADCAST = "NAVITOOL_SELF_BROADCAST";

    public static String getAmapBroadcast() {
        return AMAP_BROADCAST;
    }

    public static void resetAmapBroadcast() {
        AMAP_BROADCAST = DEFAULT_AMAP_BROADCAST;
    }

    public static void setAmapBroadcast(String str) {
        AMAP_BROADCAST = str;
    }
}
