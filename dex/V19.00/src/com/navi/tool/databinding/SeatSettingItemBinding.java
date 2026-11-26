package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class SeatSettingItemBinding {
    public static SeatSettingItemBinding bind(View view) {
        int i7 = p.layout_seat_level;
        if (((LinearLayout) z.c0(i7, view)) != null) {
            i7 = p.rb_level_1;
            if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                i7 = p.rb_level_2;
                if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                    i7 = p.rb_level_3;
                    if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                        i7 = p.rg_seat_level;
                        if (((RadioGroup) z.c0(i7, view)) != null) {
                            i7 = p.switch_seat_enabled;
                            if (((SwitchMaterial) z.c0(i7, view)) != null) {
                                i7 = p.text_seat_name;
                                if (((TextView) z.c0(i7, view)) != null) {
                                    return new SeatSettingItemBinding();
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static SeatSettingItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SeatSettingItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.seat_setting_item, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
