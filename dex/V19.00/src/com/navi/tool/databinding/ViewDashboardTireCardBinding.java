package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ViewDashboardTireCardBinding {
    public static ViewDashboardTireCardBinding bind(View view) {
        int i7 = p.img_tire_card;
        if (((ImageView) z.c0(i7, view)) != null) {
            i7 = p.tire_fl_box;
            if (((LinearLayout) z.c0(i7, view)) != null) {
                i7 = p.tire_fr_box;
                if (((LinearLayout) z.c0(i7, view)) != null) {
                    i7 = p.tire_rl_box;
                    if (((LinearLayout) z.c0(i7, view)) != null) {
                        i7 = p.tire_rr_box;
                        if (((LinearLayout) z.c0(i7, view)) != null) {
                            i7 = p.tv_tire_fl_temp;
                            if (((TextView) z.c0(i7, view)) != null) {
                                i7 = p.tv_tire_fl_value;
                                if (((TextView) z.c0(i7, view)) != null) {
                                    i7 = p.tv_tire_fr_temp;
                                    if (((TextView) z.c0(i7, view)) != null) {
                                        i7 = p.tv_tire_fr_value;
                                        if (((TextView) z.c0(i7, view)) != null) {
                                            i7 = p.tv_tire_meta;
                                            if (((TextView) z.c0(i7, view)) != null) {
                                                i7 = p.tv_tire_rl_temp;
                                                if (((TextView) z.c0(i7, view)) != null) {
                                                    i7 = p.tv_tire_rl_value;
                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                        i7 = p.tv_tire_rr_temp;
                                                        if (((TextView) z.c0(i7, view)) != null) {
                                                            i7 = p.tv_tire_rr_value;
                                                            if (((TextView) z.c0(i7, view)) != null) {
                                                                return new ViewDashboardTireCardBinding();
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

    public static ViewDashboardTireCardBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewDashboardTireCardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.view_dashboard_tire_card, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
