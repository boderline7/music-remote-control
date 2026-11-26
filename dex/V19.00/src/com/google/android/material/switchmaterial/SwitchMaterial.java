package com.google.android.material.switchmaterial;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.appcompat.widget.SwitchCompat;
import com.yalantis.ucrop.view.CropImageView;
import e3.a;
import p0.d1;
import p0.r0;
import q2.c;
import q2.e;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SwitchMaterial extends SwitchCompat {

    /* renamed from: a0, reason: collision with root package name */
    public static final int f4206a0 = l.Widget_MaterialComponents_CompoundButton_Switch;

    /* renamed from: b0, reason: collision with root package name */
    public static final int[][] f4207b0 = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public final a T;
    public ColorStateList U;
    public ColorStateList V;
    public boolean W;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    public SwitchMaterial(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.switchStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SwitchMaterial(android.content.Context r10, android.util.AttributeSet r11, int r12) {
        /*
            r9 = this;
            int r6 = com.google.android.material.switchmaterial.SwitchMaterial.f4206a0
            android.content.Context r10 = t3.a.a(r10, r11, r12, r6)
            r9.<init>(r10, r11, r12)
            android.content.Context r10 = r9.getContext()
            e3.a r0 = new e3.a
            r0.<init>(r10)
            r9.T = r0
            int[] r7 = q2.m.SwitchMaterial
            r8 = 0
            int[] r5 = new int[r8]
            com.google.android.material.internal.e0.a(r10, r11, r12, r6)
            r0 = r10
            r1 = r11
            r2 = r7
            r3 = r12
            r4 = r6
            com.google.android.material.internal.e0.b(r0, r1, r2, r3, r4, r5)
            android.content.res.TypedArray r10 = r10.obtainStyledAttributes(r11, r7, r12, r6)
            int r11 = q2.m.SwitchMaterial_useMaterialThemeColors
            boolean r11 = r10.getBoolean(r11, r8)
            r9.W = r11
            r10.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.switchmaterial.SwitchMaterial.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.U == null) {
            int b7 = b3.a.b(c.colorSurface, this);
            int b8 = b3.a.b(c.colorControlActivated, this);
            float dimension = getResources().getDimension(e.mtrl_switch_thumb_elevation);
            a aVar = this.T;
            if (aVar.f6366a) {
                float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
                for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
                    int i7 = d1.OVER_SCROLL_ALWAYS;
                    f7 += r0.i((View) parent);
                }
                dimension += f7;
            }
            int a7 = aVar.a(dimension, b7);
            this.U = new ColorStateList(f4207b0, new int[]{b3.a.f(b7, 1.0f, b8), a7, b3.a.f(b7, 0.38f, b8), a7});
        }
        return this.U;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.V == null) {
            int b7 = b3.a.b(c.colorSurface, this);
            int b8 = b3.a.b(c.colorControlActivated, this);
            int b9 = b3.a.b(c.colorOnSurface, this);
            this.V = new ColorStateList(f4207b0, new int[]{b3.a.f(b7, 0.54f, b8), b3.a.f(b7, 0.32f, b9), b3.a.f(b7, 0.12f, b8), b3.a.f(b7, 0.12f, b9)});
        }
        return this.V;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.W && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.W && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z6) {
        this.W = z6;
        if (z6) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        } else {
            setThumbTintList(null);
            setTrackTintList(null);
        }
    }
}
