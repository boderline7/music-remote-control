package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.radiobutton.MaterialRadioButton;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ActivityPermissionManagerBinding {
    public static ActivityPermissionManagerBinding bind(View view) {
        int i7 = p.btn_ime_choose;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.et_search;
            if (((EditText) z.c0(i7, view)) != null) {
                i7 = p.ime_panel;
                if (((LinearLayout) z.c0(i7, view)) != null) {
                    i7 = p.rb_freeze_mode;
                    if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                        i7 = p.rb_freeze_only;
                        if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                            i7 = p.rb_ime_mode;
                            if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                i7 = p.rb_permission_mode;
                                if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                    i7 = p.rb_suspend_mode;
                                    if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                        i7 = p.rb_suspended_only;
                                        if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                            i7 = p.rb_unfrozen_only;
                                            if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                                i7 = p.rb_unsuspended_only;
                                                if (((MaterialRadioButton) z.c0(i7, view)) != null) {
                                                    i7 = p.recycler_view_apps;
                                                    if (((RecyclerView) z.c0(i7, view)) != null) {
                                                        i7 = p.rg_freeze_filter;
                                                        if (((RadioGroup) z.c0(i7, view)) != null) {
                                                            i7 = p.rg_mode;
                                                            if (((RadioGroup) z.c0(i7, view)) != null) {
                                                                i7 = p.rg_suspend_filter;
                                                                if (((RadioGroup) z.c0(i7, view)) != null) {
                                                                    i7 = p.toolbar;
                                                                    if (((MaterialToolbar) z.c0(i7, view)) != null) {
                                                                        i7 = p.tv_empty;
                                                                        if (((TextView) z.c0(i7, view)) != null) {
                                                                            i7 = p.tv_ime_current;
                                                                            if (((TextView) z.c0(i7, view)) != null) {
                                                                                i7 = p.view_freeze_divider;
                                                                                if (z.c0(i7, view) != null) {
                                                                                    return new ActivityPermissionManagerBinding();
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

    public static ActivityPermissionManagerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityPermissionManagerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.activity_permission_manager, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
