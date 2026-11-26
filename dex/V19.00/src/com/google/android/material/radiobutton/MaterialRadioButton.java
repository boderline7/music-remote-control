package com.google.android.material.radiobutton;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import b3.a;
import q2.c;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialRadioButton extends AppCompatRadioButton {

    /* renamed from: g, reason: collision with root package name */
    public static final int f4035g = l.Widget_MaterialComponents_CompoundButton_RadioButton;

    /* renamed from: h, reason: collision with root package name */
    public static final int[][] f4036h = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* renamed from: e, reason: collision with root package name */
    public ColorStateList f4037e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f4038f;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.radioButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialRadioButton(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r4 = com.google.android.material.radiobutton.MaterialRadioButton.f4035g
            android.content.Context r8 = t3.a.a(r8, r9, r10, r4)
            r7.<init>(r8, r9, r10)
            android.content.Context r8 = r7.getContext()
            int[] r2 = q2.m.MaterialRadioButton
            r6 = 0
            int[] r5 = new int[r6]
            r0 = r8
            r1 = r9
            r3 = r10
            android.content.res.TypedArray r9 = com.google.android.material.internal.e0.d(r0, r1, r2, r3, r4, r5)
            int r10 = q2.m.MaterialRadioButton_buttonTint
            boolean r10 = r9.hasValue(r10)
            if (r10 == 0) goto L2a
            int r10 = q2.m.MaterialRadioButton_buttonTint
            android.content.res.ColorStateList r8 = l6.f.w(r8, r9, r10)
            androidx.core.widget.c.c(r7, r8)
        L2a:
            int r8 = q2.m.MaterialRadioButton_useMaterialThemeColors
            boolean r8 = r9.getBoolean(r8, r6)
            r7.f4038f = r8
            r9.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.radiobutton.MaterialRadioButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f4037e == null) {
            int b7 = a.b(c.colorControlActivated, this);
            int b8 = a.b(c.colorOnSurface, this);
            int b9 = a.b(c.colorSurface, this);
            this.f4037e = new ColorStateList(f4036h, new int[]{a.f(b9, 1.0f, b7), a.f(b9, 0.54f, b8), a.f(b9, 0.38f, b8), a.f(b9, 0.38f, b8)});
        }
        return this.f4037e;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f4038f && androidx.core.widget.c.a(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z6) {
        this.f4038f = z6;
        if (z6) {
            androidx.core.widget.c.c(this, getMaterialThemeColorsTintList());
        } else {
            androidx.core.widget.c.c(this, null);
        }
    }
}
