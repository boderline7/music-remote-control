package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogProgressBinding {
    public static DialogProgressBinding bind(View view) {
        int i7 = p.btn_cancel;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.progress_bar;
            if (((ProgressBar) z.c0(i7, view)) != null) {
                i7 = p.progress_count;
                if (((TextView) z.c0(i7, view)) != null) {
                    i7 = p.progress_percent;
                    if (((TextView) z.c0(i7, view)) != null) {
                        i7 = p.progress_text;
                        if (((TextView) z.c0(i7, view)) != null) {
                            return new DialogProgressBinding();
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static DialogProgressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogProgressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.dialog_progress, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
