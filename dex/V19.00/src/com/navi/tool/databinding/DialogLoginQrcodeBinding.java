package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogLoginQrcodeBinding {
    public static DialogLoginQrcodeBinding bind(View view) {
        int i7 = p.btn_close;
        if (((Button) z.c0(i7, view)) != null) {
            i7 = p.btn_refresh_qr;
            if (((Button) z.c0(i7, view)) != null) {
                i7 = p.iv_qr_code;
                if (((ImageView) z.c0(i7, view)) != null) {
                    i7 = p.progress_loading;
                    if (((ProgressBar) z.c0(i7, view)) != null) {
                        i7 = p.tv_qr_status;
                        if (((TextView) z.c0(i7, view)) != null) {
                            return new DialogLoginQrcodeBinding();
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static DialogLoginQrcodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogLoginQrcodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.dialog_login_qrcode, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
