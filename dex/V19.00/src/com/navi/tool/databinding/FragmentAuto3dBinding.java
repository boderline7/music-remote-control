package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class FragmentAuto3dBinding {

    /* renamed from: a, reason: collision with root package name */
    public final CoordinatorLayout f4748a;

    /* renamed from: b, reason: collision with root package name */
    public final MaterialCardView f4749b;

    /* renamed from: c, reason: collision with root package name */
    public final LinearLayout f4750c;

    /* renamed from: d, reason: collision with root package name */
    public final LinearLayout f4751d;

    /* renamed from: e, reason: collision with root package name */
    public final LinearLayout f4752e;

    /* renamed from: f, reason: collision with root package name */
    public final LinearLayout f4753f;

    /* renamed from: g, reason: collision with root package name */
    public final RadioGroup f4754g;

    /* renamed from: h, reason: collision with root package name */
    public final RadioGroup f4755h;

    /* renamed from: i, reason: collision with root package name */
    public final RecyclerView f4756i;

    /* renamed from: j, reason: collision with root package name */
    public final SwitchMaterial f4757j;

    /* renamed from: k, reason: collision with root package name */
    public final SwitchMaterial f4758k;

    /* renamed from: l, reason: collision with root package name */
    public final SwitchMaterial f4759l;

    /* renamed from: m, reason: collision with root package name */
    public final SwitchMaterial f4760m;

    /* renamed from: n, reason: collision with root package name */
    public final SwitchMaterial f4761n;

    /* renamed from: o, reason: collision with root package name */
    public final TextView f4762o;

    public FragmentAuto3dBinding(CoordinatorLayout coordinatorLayout, MaterialCardView materialCardView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, RadioGroup radioGroup, RadioGroup radioGroup2, RecyclerView recyclerView, SwitchMaterial switchMaterial, SwitchMaterial switchMaterial2, SwitchMaterial switchMaterial3, SwitchMaterial switchMaterial4, SwitchMaterial switchMaterial5, TextView textView) {
        this.f4748a = coordinatorLayout;
        this.f4749b = materialCardView;
        this.f4750c = linearLayout;
        this.f4751d = linearLayout2;
        this.f4752e = linearLayout3;
        this.f4753f = linearLayout4;
        this.f4754g = radioGroup;
        this.f4755h = radioGroup2;
        this.f4756i = recyclerView;
        this.f4757j = switchMaterial;
        this.f4758k = switchMaterial2;
        this.f4759l = switchMaterial3;
        this.f4760m = switchMaterial4;
        this.f4761n = switchMaterial5;
        this.f4762o = textView;
    }

    public static FragmentAuto3dBinding bind(View view) {
        int i7 = p.card_3d_options;
        MaterialCardView materialCardView = (MaterialCardView) z.c0(i7, view);
        if (materialCardView != null) {
            i7 = p.layout_cruise_scale;
            LinearLayout linearLayout = (LinearLayout) z.c0(i7, view);
            if (linearLayout != null) {
                i7 = p.layout_cruise_scale_options;
                LinearLayout linearLayout2 = (LinearLayout) z.c0(i7, view);
                if (linearLayout2 != null) {
                    i7 = p.layout_navigation_scale;
                    LinearLayout linearLayout3 = (LinearLayout) z.c0(i7, view);
                    if (linearLayout3 != null) {
                        i7 = p.layout_navigation_scale_options;
                        LinearLayout linearLayout4 = (LinearLayout) z.c0(i7, view);
                        if (linearLayout4 != null) {
                            i7 = p.radio_cruise_10m;
                            if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                i7 = p.radio_cruise_25m;
                                if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                    i7 = p.radio_cruise_50m;
                                    if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                        i7 = p.radio_group_cruise_scale;
                                        RadioGroup radioGroup = (RadioGroup) z.c0(i7, view);
                                        if (radioGroup != null) {
                                            i7 = p.radio_group_navigation_scale;
                                            RadioGroup radioGroup2 = (RadioGroup) z.c0(i7, view);
                                            if (radioGroup2 != null) {
                                                i7 = p.radio_navigation_10m;
                                                if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                                    i7 = p.radio_navigation_25m;
                                                    if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                                        i7 = p.radio_navigation_50m;
                                                        if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                                            i7 = p.recycler_view_apps;
                                                            RecyclerView recyclerView = (RecyclerView) z.c0(i7, view);
                                                            if (recyclerView != null) {
                                                                i7 = p.switch_auto_3d;
                                                                SwitchMaterial switchMaterial = (SwitchMaterial) z.c0(i7, view);
                                                                if (switchMaterial != null) {
                                                                    i7 = p.switch_cruise_3d;
                                                                    SwitchMaterial switchMaterial2 = (SwitchMaterial) z.c0(i7, view);
                                                                    if (switchMaterial2 != null) {
                                                                        i7 = p.switch_cruise_fixed_scale;
                                                                        SwitchMaterial switchMaterial3 = (SwitchMaterial) z.c0(i7, view);
                                                                        if (switchMaterial3 != null) {
                                                                            i7 = p.switch_navigation_3d;
                                                                            SwitchMaterial switchMaterial4 = (SwitchMaterial) z.c0(i7, view);
                                                                            if (switchMaterial4 != null) {
                                                                                i7 = p.switch_navigation_fixed_scale;
                                                                                SwitchMaterial switchMaterial5 = (SwitchMaterial) z.c0(i7, view);
                                                                                if (switchMaterial5 != null) {
                                                                                    i7 = p.text_no_apps;
                                                                                    TextView textView = (TextView) z.c0(i7, view);
                                                                                    if (textView != null) {
                                                                                        return new FragmentAuto3dBinding((CoordinatorLayout) view, materialCardView, linearLayout, linearLayout2, linearLayout3, linearLayout4, radioGroup, radioGroup2, recyclerView, switchMaterial, switchMaterial2, switchMaterial3, switchMaterial4, switchMaterial5, textView);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static FragmentAuto3dBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAuto3dBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.fragment_auto_3d, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
