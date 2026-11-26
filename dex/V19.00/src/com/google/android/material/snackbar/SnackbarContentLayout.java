package com.google.android.material.snackbar;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import l6.f;
import p0.d1;
import p0.m0;
import q2.c;
import q2.e;
import q2.g;
import r2.a;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SnackbarContentLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    public TextView f4202a;

    /* renamed from: b, reason: collision with root package name */
    public Button f4203b;

    /* renamed from: c, reason: collision with root package name */
    public int f4204c;

    public SnackbarContentLayout(Context context) {
        this(context, null);
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        f.v0(context, c.motionEasingEmphasizedInterpolator, a.FAST_OUT_SLOW_IN_INTERPOLATOR);
    }

    public final boolean a(int i7, int i8, int i9) {
        boolean z6;
        if (i7 != getOrientation()) {
            setOrientation(i7);
            z6 = true;
        } else {
            z6 = false;
        }
        if (this.f4202a.getPaddingTop() == i8 && this.f4202a.getPaddingBottom() == i9) {
            return z6;
        }
        TextView textView = this.f4202a;
        int i10 = d1.OVER_SCROLL_ALWAYS;
        if (m0.g(textView)) {
            m0.k(textView, m0.f(textView), i8, m0.e(textView), i9);
            return true;
        }
        textView.setPadding(textView.getPaddingLeft(), i8, textView.getPaddingRight(), i9);
        return true;
    }

    public Button getActionView() {
        return this.f4203b;
    }

    public TextView getMessageView() {
        return this.f4202a;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f4202a = (TextView) findViewById(g.snackbar_text);
        this.f4203b = (Button) findViewById(g.snackbar_action);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (getOrientation() == 1) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(e.design_snackbar_padding_vertical_2lines);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(e.design_snackbar_padding_vertical);
        Layout layout = this.f4202a.getLayout();
        boolean z6 = layout != null && layout.getLineCount() > 1;
        if (!z6 || this.f4204c <= 0 || this.f4203b.getMeasuredWidth() <= this.f4204c) {
            if (!z6) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (!a(0, dimensionPixelSize, dimensionPixelSize)) {
                return;
            }
        } else if (!a(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
            return;
        }
        super.onMeasure(i7, i8);
    }

    public void setMaxInlineActionWidth(int i7) {
        this.f4204c = i7;
    }
}
