package com.navi.tool.utils;

import android.content.Context;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DensityUtil {
    public static int dip2px(Context context, float f7) {
        return (int) ((f7 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f7) {
        return (int) ((f7 / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
