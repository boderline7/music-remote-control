package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class FragmentSceneModeBinding {
    public static FragmentSceneModeBinding bind(View view) {
        int i7 = p.button_add_scene_rule;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.button_delete_selected_scene_rule;
            if (((Button) z.c0(i7, view)) != null) {
                i7 = p.button_multi_select_scene_rule;
                if (((Button) z.c0(i7, view)) != null) {
                    i7 = p.button_reload_scene_rule;
                    if (((Button) z.c0(i7, view)) != null) {
                        i7 = p.button_select_all_scene_rule;
                        if (((Button) z.c0(i7, view)) != null) {
                            i7 = p.layout_scene_content;
                            if (((LinearLayout) z.c0(i7, view)) != null) {
                                i7 = p.recycler_scene_rules;
                                if (((RecyclerView) z.c0(i7, view)) != null) {
                                    i7 = p.switch_scene_mode_master;
                                    if (((SwitchMaterial) z.c0(i7, view)) != null) {
                                        i7 = p.text_scene_rule_empty;
                                        if (((TextView) z.c0(i7, view)) != null) {
                                            return new FragmentSceneModeBinding();
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

    public static FragmentSceneModeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentSceneModeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.fragment_scene_mode, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
