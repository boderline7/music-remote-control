package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ItemAppBinding {

    /* renamed from: a, reason: collision with root package name */
    public final LinearLayout f4831a;

    /* renamed from: b, reason: collision with root package name */
    public final ImageView f4832b;

    /* renamed from: c, reason: collision with root package name */
    public final TextView f4833c;

    /* renamed from: d, reason: collision with root package name */
    public final SwitchMaterial f4834d;

    /* renamed from: e, reason: collision with root package name */
    public final TextView f4835e;

    public ItemAppBinding(LinearLayout linearLayout, ImageView imageView, TextView textView, SwitchMaterial switchMaterial, TextView textView2) {
        this.f4831a = linearLayout;
        this.f4832b = imageView;
        this.f4833c = textView;
        this.f4834d = switchMaterial;
        this.f4835e = textView2;
    }

    public static ItemAppBinding bind(View view) {
        int i7 = p.app_icon;
        ImageView imageView = (ImageView) z.c0(i7, view);
        if (imageView != null) {
            i7 = p.app_name;
            TextView textView = (TextView) z.c0(i7, view);
            if (textView != null) {
                i7 = p.app_switch;
                SwitchMaterial switchMaterial = (SwitchMaterial) z.c0(i7, view);
                if (switchMaterial != null) {
                    i7 = p.package_name;
                    TextView textView2 = (TextView) z.c0(i7, view);
                    if (textView2 != null) {
                        return new ItemAppBinding((LinearLayout) view, imageView, textView, switchMaterial, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ItemAppBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemAppBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.item_app, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
