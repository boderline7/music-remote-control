package com.navi.tool.utils;

import android.content.Context;
import android.content.Intent;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class BroadcastUtils {
    private static final String AUTONAVI_3D_BROADCAST_ACTION = "AUTONAVI_STANDARD_BROADCAST_RECV";
    private static final int AUTONAVI_3D_KEY_TYPE = 10027;
    private static final String AUTONAVI_3D_RECEIVER = "com.autonavi.amapauto.adapter.internal.AmapAutoBroadcastReceiver";
    private static final String AUTONAVI_BROADCAST_ACTION = "AUTONAVI_STANDARD_BROADCAST_RECV";
    private static final int AUTONAVI_DAY_MODE = 0;
    private static final int AUTONAVI_KEY_TYPE = 10048;
    private static final int AUTONAVI_NIGHT_MODE = 2;
    private static final String AUTONAVI_RECEIVER = "com.autonavi.amapauto.adapter.internal.AmapAutoBroadcastReceiver";
    private static final String BAIDU_BROADCAST_ACTION = "BAIDU_STANDARD_BROADCAST_RECV";
    private static final int BAIDU_DAY_MODE = 1;
    private static final int BAIDU_KEY_TYPE = 10048;
    private static final int BAIDU_NIGHT_MODE = 2;
    private static final String TAG = "BroadcastUtils";
    private static final String TENCENT_BROADCAST_ACTION = "WECARNAVI_STANDARD_BROADCAST_RECV";
    private static final int TENCENT_DAY_OPERA = 0;
    private static final int TENCENT_EXTRA_TYPE = 3;
    private static final int TENCENT_KEY_TYPE = 1001;
    private static final int TENCENT_NIGHT_OPERA = 1;

    public static boolean isCurrentNightMode(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static void send3DBroadcast(Context context, String str, int i7, int i8) {
        try {
            Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
            intent.setPackage(str);
            intent.setClassName(str, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
            intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, AUTONAVI_3D_KEY_TYPE);
            intent.putExtra(Auto3DBroadcastReceiver.EXTRA_TYPE, i7);
            intent.putExtra(Auto3DBroadcastReceiver.EXTRA_OPERA, i8);
            context.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    public static void sendAutoNaviThemeBroadcast(Context context, String str, boolean z6) {
        try {
            Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
            intent.setPackage(str);
            intent.setClassName(str, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
            intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10048);
            intent.putExtra("EXTRA_DAY_NIGHT_MODE", z6 ? 2 : 0);
            context.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    public static void sendBaiduThemeBroadcast(Context context, boolean z6) {
        try {
            Intent intent = new Intent(BAIDU_BROADCAST_ACTION);
            intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10048);
            intent.putExtra("EXTRA_DAY_NIGHT_MODE", z6 ? 2 : 1);
            context.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    public static void sendScaleBroadcast(Context context, String str, int i7, int i8) {
        try {
            Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
            intent.setPackage(str);
            intent.setClassName(str, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
            intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, AUTONAVI_3D_KEY_TYPE);
            intent.putExtra(Auto3DBroadcastReceiver.EXTRA_TYPE, i7);
            intent.putExtra(Auto3DBroadcastReceiver.EXTRA_OPERA, i8);
            context.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    public static void sendTencentThemeBroadcast(Context context, boolean z6) {
        try {
            Intent intent = new Intent(TENCENT_BROADCAST_ACTION);
            intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, TENCENT_KEY_TYPE);
            intent.putExtra(Auto3DBroadcastReceiver.EXTRA_TYPE, 3);
            intent.putExtra(Auto3DBroadcastReceiver.EXTRA_OPERA, z6 ? 1 : 0);
            context.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    public static void sendThemeBroadcast(Context context, String str, boolean z6) {
        if (AppDetectionUtils.isAmapPackage(str)) {
            sendAutoNaviThemeBroadcast(context, str, z6);
        } else if (str.startsWith(AppDetectionUtils.BAIDU_PREFIX)) {
            sendBaiduThemeBroadcast(context, z6);
        } else if (str.startsWith(AppDetectionUtils.TENCENT_PREFIX)) {
            sendTencentThemeBroadcast(context, z6);
        }
    }

    public static void sendTtsBroadcast(Context context, String str, String str2) {
        try {
            Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
            intent.setPackage(str);
            intent.setClassName(str, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
            intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 60073);
            intent.putExtra("EXTRA_TTS_TEXT", str2);
            context.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }
}
