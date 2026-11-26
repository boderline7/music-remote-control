package com.navi.tool.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ToastHelper {
    public static void show(Context context, int i7) {
        if (context == null) {
            return;
        }
        showToast(context, context.getString(i7), 0);
    }

    public static void show(Context context, String str) {
        if (context == null) {
            return;
        }
        showToast(context, str, 0);
    }

    public static void showLong(Context context, int i7) {
        if (context == null) {
            return;
        }
        showToast(context, context.getString(i7), 1);
    }

    public static void showLong(Context context, String str) {
        if (context == null) {
            return;
        }
        showToast(context, str, 1);
    }

    private static void showToast(Context context, String str, int i7) {
        try {
            View inflate = LayoutInflater.from(context).inflate(g4.q.layout_custom_toast, (ViewGroup) null);
            ((TextView) inflate.findViewById(g4.p.toast_text)).setText(str);
            Toast toast = new Toast(context);
            toast.setDuration(i7);
            toast.setView(inflate);
            toast.show();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }
}
