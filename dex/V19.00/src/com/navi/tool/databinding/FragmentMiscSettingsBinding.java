package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class FragmentMiscSettingsBinding {
    public static FragmentMiscSettingsBinding bind(View view) {
        int i7 = p.button_apply_gaode_dpi;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.button_upload_crash_log;
            if (((Button) z.c0(i7, view)) != null) {
                i7 = p.checkbox_enable_dpi_edit;
                if (((CheckBox) z.c0(i7, view)) != null) {
                    i7 = p.edit_gaode_dpi_value;
                    if (((EditText) z.c0(i7, view)) != null) {
                        i7 = p.radio_group_display_mode;
                        if (((RadioGroup) z.c0(i7, view)) != null) {
                            i7 = p.radio_mode_default;
                            if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                i7 = p.radio_mode_immersive;
                                if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                    i7 = p.radio_mode_transparent;
                                    if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                        i7 = p.text_crash_upload_status;
                                        if (((TextView) z.c0(i7, view)) != null) {
                                            i7 = p.text_current_mode;
                                            if (((TextView) z.c0(i7, view)) != null) {
                                                i7 = p.text_gaode_dpi_configured;
                                                if (((TextView) z.c0(i7, view)) != null) {
                                                    i7 = p.text_gaode_dpi_desc;
                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                        i7 = p.text_gaode_dpi_input_label;
                                                        if (((TextView) z.c0(i7, view)) != null) {
                                                            i7 = p.text_gaode_dpi_title;
                                                            if (((TextView) z.c0(i7, view)) != null) {
                                                                return new FragmentMiscSettingsBinding();
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

    public static FragmentMiscSettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentMiscSettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.fragment_misc_settings, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
