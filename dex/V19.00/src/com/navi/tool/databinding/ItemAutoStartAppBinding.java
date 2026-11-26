package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ItemAutoStartAppBinding {
    public static ItemAutoStartAppBinding bind(View view) {
        int i7 = p.app_icon;
        if (((ImageView) z.c0(i7, view)) != null) {
            i7 = p.app_name;
            if (((TextView) z.c0(i7, view)) != null) {
                i7 = p.app_package;
                if (((TextView) z.c0(i7, view)) != null) {
                    i7 = p.btn_delay;
                    if (((Button) z.c0(i7, view)) != null) {
                        i7 = p.switch_app_auto_start;
                        if (((SwitchMaterial) z.c0(i7, view)) != null) {
                            return new ItemAutoStartAppBinding();
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ItemAutoStartAppBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAutoStartAppBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.item_auto_start_app, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
