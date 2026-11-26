package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class FragmentAutoThemeBinding {

    /* renamed from: a, reason: collision with root package name */
    public final CoordinatorLayout f4771a;

    /* renamed from: b, reason: collision with root package name */
    public final RecyclerView f4772b;

    /* renamed from: c, reason: collision with root package name */
    public final SwitchMaterial f4773c;

    /* renamed from: d, reason: collision with root package name */
    public final TextView f4774d;

    public FragmentAutoThemeBinding(CoordinatorLayout coordinatorLayout, RecyclerView recyclerView, SwitchMaterial switchMaterial, TextView textView) {
        this.f4771a = coordinatorLayout;
        this.f4772b = recyclerView;
        this.f4773c = switchMaterial;
        this.f4774d = textView;
    }

    public static FragmentAutoThemeBinding bind(View view) {
        int i7 = p.recycler_view_apps;
        RecyclerView recyclerView = (RecyclerView) z.c0(i7, view);
        if (recyclerView != null) {
            i7 = p.switch_auto_theme;
            SwitchMaterial switchMaterial = (SwitchMaterial) z.c0(i7, view);
            if (switchMaterial != null) {
                i7 = p.text_no_apps;
                TextView textView = (TextView) z.c0(i7, view);
                if (textView != null) {
                    return new FragmentAutoThemeBinding((CoordinatorLayout) view, recyclerView, switchMaterial, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static FragmentAutoThemeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAutoThemeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.fragment_auto_theme, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
