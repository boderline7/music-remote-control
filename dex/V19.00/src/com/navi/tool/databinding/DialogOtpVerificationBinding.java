package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogOtpVerificationBinding {
    public static DialogOtpVerificationBinding bind(View view) {
        int i7 = p.edit_code;
        if (((TextInputEditText) z.c0(i7, view)) != null) {
            i7 = p.input_layout;
            if (((TextInputLayout) z.c0(i7, view)) != null) {
                i7 = p.message;
                if (((TextView) z.c0(i7, view)) != null) {
                    i7 = p.title;
                    if (((TextView) z.c0(i7, view)) != null) {
                        return new DialogOtpVerificationBinding();
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static DialogOtpVerificationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogOtpVerificationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.dialog_otp_verification, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
