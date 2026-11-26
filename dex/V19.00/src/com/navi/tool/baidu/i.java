package com.navi.tool.baidu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class i {

    /* renamed from: a, reason: collision with root package name */
    public static BroadcastReceiver f4678a;

    /* renamed from: b, reason: collision with root package name */
    public static boolean f4679b;

    public static void a(Context context) {
        if (f4679b) {
            return;
        }
        f4678a = new BaiduTrafficLightReceiver$1();
        IntentFilter intentFilter = new IntentFilter("NAVITOOL_STANDARD_BROADCAST_SEND");
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                context.registerReceiver(f4678a, intentFilter, 2);
            } else {
                context.registerReceiver(f4678a, intentFilter);
            }
            f4679b = true;
        } catch (Exception unused) {
        }
    }
}
