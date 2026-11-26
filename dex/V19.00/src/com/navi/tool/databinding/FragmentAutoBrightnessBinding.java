package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class FragmentAutoBrightnessBinding {

    /* renamed from: a, reason: collision with root package name */
    public final CoordinatorLayout f4763a;

    /* renamed from: b, reason: collision with root package name */
    public final MaterialCardView f4764b;

    /* renamed from: c, reason: collision with root package name */
    public final RecyclerView f4765c;

    /* renamed from: d, reason: collision with root package name */
    public final SeekBar f4766d;

    /* renamed from: e, reason: collision with root package name */
    public final SwitchMaterial f4767e;

    /* renamed from: f, reason: collision with root package name */
    public final TextView f4768f;

    /* renamed from: g, reason: collision with root package name */
    public final TextView f4769g;

    /* renamed from: h, reason: collision with root package name */
    public final TextView f4770h;

    public FragmentAutoBrightnessBinding(CoordinatorLayout coordinatorLayout, MaterialCardView materialCardView, RecyclerView recyclerView, SeekBar seekBar, SwitchMaterial switchMaterial, TextView textView, TextView textView2, TextView textView3) {
        this.f4763a = coordinatorLayout;
        this.f4764b = materialCardView;
        this.f4765c = recyclerView;
        this.f4766d = seekBar;
        this.f4767e = switchMaterial;
        this.f4768f = textView;
        this.f4769g = textView2;
        this.f4770h = textView3;
    }

    public static FragmentAutoBrightnessBinding bind(View view) {
        int i7 = p.card_brightness_threshold;
        MaterialCardView materialCardView = (MaterialCardView) z.c0(i7, view);
        if (materialCardView != null) {
            i7 = p.recycler_view_apps;
            RecyclerView recyclerView = (RecyclerView) z.c0(i7, view);
            if (recyclerView != null) {
                i7 = p.seekbar_brightness;
                SeekBar seekBar = (SeekBar) z.c0(i7, view);
                if (seekBar != null) {
                    i7 = p.switch_auto_brightness;
                    SwitchMaterial switchMaterial = (SwitchMaterial) z.c0(i7, view);
                    if (switchMaterial != null) {
                        i7 = p.text_brightness_value;
                        TextView textView = (TextView) z.c0(i7, view);
                        if (textView != null) {
                            i7 = p.text_current_brightness;
                            TextView textView2 = (TextView) z.c0(i7, view);
                            if (textView2 != null) {
                                i7 = p.text_no_apps;
                                TextView textView3 = (TextView) z.c0(i7, view);
                                if (textView3 != null) {
                                    return new FragmentAutoBrightnessBinding((CoordinatorLayout) view, materialCardView, recyclerView, seekBar, switchMaterial, textView, textView2, textView3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static FragmentAutoBrightnessBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAutoBrightnessBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.fragment_auto_brightness, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
