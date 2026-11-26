package com.navi.tool.navi.api;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class NaviAction {
    public static final int AMAP_AUX_ROAD = 17;
    public static final int AMAP_COMPANY = 5;
    public static final int AMAP_COMPANY_MANUAL = 7;
    public static final int AMAP_CONTINUE = 18;
    public static final int AMAP_EXIT = 1;
    public static final int AMAP_HOME = 4;
    public static final int AMAP_HOME_MANUAL = 6;
    public static final int AMAP_LAUNCH = 3;
    public static final int AMAP_MAIN_ROAD = 16;
    public static final int AMAP_MUTE = 12;
    public static final int AMAP_REFRESH_ROUTE = 19;
    public static final int AMAP_UNMUTE = 13;
    public static final int AMAP_ZOOM_IN = 14;
    public static final int AMAP_ZOOM_OUT = 15;
    public static final int BAIDU_COMPANY = 9;
    public static final int BAIDU_EXIT = 2;
    public static final int BAIDU_HOME = 8;
    public static final int TENCENT_COMPANY = 11;
    public static final int TENCENT_HOME = 10;
    public final long delayMs;
    public final int type;

    public NaviAction(int i7, long j7) {
        this.type = i7;
        this.delayMs = j7;
    }
}
