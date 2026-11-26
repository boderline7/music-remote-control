package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ItemFileBinding {
    public static ItemFileBinding bind(View view) {
        int i7 = p.checkbox_select;
        if (((CheckBox) z.c0(i7, view)) != null) {
            i7 = p.file_apk_info;
            if (((TextView) z.c0(i7, view)) != null) {
                i7 = p.file_date;
                if (((TextView) z.c0(i7, view)) != null) {
                    i7 = p.file_icon;
                    if (((ImageView) z.c0(i7, view)) != null) {
                        i7 = p.file_name;
                        if (((TextView) z.c0(i7, view)) != null) {
                            i7 = p.file_size;
                            if (((TextView) z.c0(i7, view)) != null) {
                                return new ItemFileBinding();
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ItemFileBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemFileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.item_file, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
