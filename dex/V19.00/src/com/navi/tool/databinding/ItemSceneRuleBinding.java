package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ItemSceneRuleBinding {
    public static ItemSceneRuleBinding bind(View view) {
        int i7 = p.checkbox_scene_select;
        if (((CheckBox) z.c0(i7, view)) != null) {
            i7 = p.switch_scene_enabled;
            if (((SwitchMaterial) z.c0(i7, view)) != null) {
                i7 = p.text_scene_meta;
                if (((TextView) z.c0(i7, view)) != null) {
                    i7 = p.text_scene_name;
                    if (((TextView) z.c0(i7, view)) != null) {
                        return new ItemSceneRuleBinding();
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ItemSceneRuleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSceneRuleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.item_scene_rule, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
