package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogSceneRuleBinding {
    public static DialogSceneRuleBinding bind(View view) {
        int i7 = p.button_add_scene_action;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.button_add_scene_condition;
            if (((Button) z.c0(i7, view)) != null) {
                i7 = p.checkbox_scene_reset_enabled;
                if (((CheckBox) z.c0(i7, view)) != null) {
                    i7 = p.edit_scene_rule_name;
                    if (((EditText) z.c0(i7, view)) != null) {
                        i7 = p.layout_scene_actions;
                        if (((LinearLayout) z.c0(i7, view)) != null) {
                            i7 = p.layout_scene_conditions;
                            if (((LinearLayout) z.c0(i7, view)) != null) {
                                i7 = p.radio_scene_logic_and;
                                if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                    i7 = p.radio_scene_logic_group;
                                    if (((RadioGroup) z.c0(i7, view)) != null) {
                                        i7 = p.radio_scene_logic_or;
                                        if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                            i7 = p.radio_scene_reset_engine;
                                            if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                                i7 = p.radio_scene_reset_group;
                                                if (((RadioGroup) z.c0(i7, view)) != null) {
                                                    i7 = p.radio_scene_reset_power;
                                                    if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                                        i7 = p.radio_scene_reset_reboot;
                                                        if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                                            i7 = p.seek_scene_delay;
                                                            if (((SeekBar) z.c0(i7, view)) != null) {
                                                                i7 = p.seek_scene_delay_seconds;
                                                                if (((SeekBar) z.c0(i7, view)) != null) {
                                                                    i7 = p.text_scene_delay_value;
                                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                                        return new DialogSceneRuleBinding();
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

    public static DialogSceneRuleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogSceneRuleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.dialog_scene_rule, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
