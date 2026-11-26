package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogCustomAmapBroadcastBinding {
    public static DialogCustomAmapBroadcastBinding bind(View view) {
        int i7 = p.btn_save;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.edit_broadcast_value;
            if (((EditText) z.c0(i7, view)) != null) {
                i7 = p.layout_cluster_cross_mask;
                if (((LinearLayout) z.c0(i7, view)) != null) {
                    i7 = p.switch_cluster_cross_mask;
                    if (((Switch) z.c0(i7, view)) != null) {
                        i7 = p.switch_custom_broadcast;
                        if (((Switch) z.c0(i7, view)) != null) {
                            return new DialogCustomAmapBroadcastBinding();
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static DialogCustomAmapBroadcastBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCustomAmapBroadcastBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.dialog_custom_amap_broadcast, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
