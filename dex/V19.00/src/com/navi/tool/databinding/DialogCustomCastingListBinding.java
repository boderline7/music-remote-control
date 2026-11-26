package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogCustomCastingListBinding {
    public static DialogCustomCastingListBinding bind(View view) {
        int i7 = p.recycler_all_apps;
        if (((RecyclerView) z.c0(i7, view)) == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
        }
        return new DialogCustomCastingListBinding();
    }

    public static DialogCustomCastingListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogCustomCastingListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.dialog_custom_casting_list, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
