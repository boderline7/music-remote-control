package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogConflictBinding {
    public static DialogConflictBinding bind(View view) {
        int i7 = p.btn_cancel_conflict;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.btn_overwrite_conflict;
            if (((Button) z.c0(i7, view)) != null) {
                i7 = p.btn_rename_conflict;
                if (((Button) z.c0(i7, view)) != null) {
                    i7 = p.btn_skip_conflict;
                    if (((Button) z.c0(i7, view)) != null) {
                        i7 = p.checkbox_apply_all;
                        if (((CheckBox) z.c0(i7, view)) != null) {
                            i7 = p.conflict_file_name;
                            if (((TextView) z.c0(i7, view)) != null) {
                                i7 = p.conflict_message;
                                if (((TextView) z.c0(i7, view)) != null) {
                                    return new DialogConflictBinding();
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static DialogConflictBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogConflictBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.dialog_conflict, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
