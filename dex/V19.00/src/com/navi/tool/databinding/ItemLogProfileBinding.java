package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ItemLogProfileBinding {

    /* renamed from: a, reason: collision with root package name */
    public final LinearLayout f4836a;

    /* renamed from: b, reason: collision with root package name */
    public final CheckBox f4837b;

    /* renamed from: c, reason: collision with root package name */
    public final SwitchMaterial f4838c;

    /* renamed from: d, reason: collision with root package name */
    public final TextView f4839d;

    /* renamed from: e, reason: collision with root package name */
    public final TextView f4840e;

    public ItemLogProfileBinding(LinearLayout linearLayout, CheckBox checkBox, SwitchMaterial switchMaterial, TextView textView, TextView textView2) {
        this.f4836a = linearLayout;
        this.f4837b = checkBox;
        this.f4838c = switchMaterial;
        this.f4839d = textView;
        this.f4840e = textView2;
    }

    public static ItemLogProfileBinding bind(View view) {
        int i7 = p.checkbox_select;
        CheckBox checkBox = (CheckBox) z.c0(i7, view);
        if (checkBox != null) {
            i7 = p.switch_enabled;
            SwitchMaterial switchMaterial = (SwitchMaterial) z.c0(i7, view);
            if (switchMaterial != null) {
                i7 = p.text_name;
                TextView textView = (TextView) z.c0(i7, view);
                if (textView != null) {
                    i7 = p.text_summary;
                    TextView textView2 = (TextView) z.c0(i7, view);
                    if (textView2 != null) {
                        return new ItemLogProfileBinding((LinearLayout) view, checkBox, switchMaterial, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ItemLogProfileBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemLogProfileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.item_log_profile, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
