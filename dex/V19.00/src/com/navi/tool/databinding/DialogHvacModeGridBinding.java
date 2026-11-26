package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogHvacModeGridBinding {
    public static DialogHvacModeGridBinding bind(View view) {
        int i7 = p.grid_hvac_modes;
        if (((GridLayout) z.c0(i7, view)) == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
        }
        return new DialogHvacModeGridBinding();
    }

    public static DialogHvacModeGridBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogHvacModeGridBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.dialog_hvac_mode_grid, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
