package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ItemPanelAppSettingBinding {
    public static ItemPanelAppSettingBinding bind(View view) {
        int i7 = p.button_change_icon;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.button_change_name;
            if (((Button) z.c0(i7, view)) != null) {
                i7 = p.button_restore_icon;
                if (((Button) z.c0(i7, view)) != null) {
                    i7 = p.image_app_icon;
                    if (((ImageView) z.c0(i7, view)) != null) {
                        i7 = p.switch_dock_enable;
                        if (((Switch) z.c0(i7, view)) != null) {
                            i7 = p.text_app_name;
                            if (((TextView) z.c0(i7, view)) != null) {
                                i7 = p.text_app_package;
                                if (((TextView) z.c0(i7, view)) != null) {
                                    return new ItemPanelAppSettingBinding();
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ItemPanelAppSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemPanelAppSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.item_panel_app_setting, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
