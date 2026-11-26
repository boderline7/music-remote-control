package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ItemScreenCastingAppBinding {
    public static ItemScreenCastingAppBinding bind(View view) {
        int i7 = p.img_icon;
        if (((ImageView) z.c0(i7, view)) != null) {
            i7 = p.switch_enable;
            if (((Switch) z.c0(i7, view)) != null) {
                i7 = p.tv_label;
                if (((TextView) z.c0(i7, view)) != null) {
                    return new ItemScreenCastingAppBinding();
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ItemScreenCastingAppBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemScreenCastingAppBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.item_screen_casting_app, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
