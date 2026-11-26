package com.google.android.material.materialswitch;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.SwitchCompat;
import i0.a;
import j0.b;
import l6.f;
import q2.c;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialSwitch extends SwitchCompat {

    /* renamed from: j0, reason: collision with root package name */
    public static final int f3920j0 = l.Widget_Material3_CompoundButton_MaterialSwitch;

    /* renamed from: k0, reason: collision with root package name */
    public static final int[] f3921k0 = {c.state_with_icon};
    public Drawable T;
    public Drawable U;
    public int V;
    public Drawable W;

    /* renamed from: a0, reason: collision with root package name */
    public Drawable f3922a0;

    /* renamed from: b0, reason: collision with root package name */
    public ColorStateList f3923b0;

    /* renamed from: c0, reason: collision with root package name */
    public ColorStateList f3924c0;

    /* renamed from: d0, reason: collision with root package name */
    public PorterDuff.Mode f3925d0;

    /* renamed from: e0, reason: collision with root package name */
    public ColorStateList f3926e0;

    /* renamed from: f0, reason: collision with root package name */
    public ColorStateList f3927f0;

    /* renamed from: g0, reason: collision with root package name */
    public PorterDuff.Mode f3928g0;

    /* renamed from: h0, reason: collision with root package name */
    public int[] f3929h0;

    /* renamed from: i0, reason: collision with root package name */
    public int[] f3930i0;

    public MaterialSwitch(Context context) {
        this(context, null);
    }

    public MaterialSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.materialSwitchStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialSwitch(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r4 = com.google.android.material.materialswitch.MaterialSwitch.f3920j0
            android.content.Context r8 = t3.a.a(r8, r9, r10, r4)
            r7.<init>(r8, r9, r10)
            r8 = -1
            r7.V = r8
            android.content.Context r0 = r7.getContext()
            android.graphics.drawable.Drawable r1 = super.getThumbDrawable()
            r7.T = r1
            android.content.res.ColorStateList r1 = super.getThumbTintList()
            r7.f3923b0 = r1
            r1 = 0
            super.setThumbTintList(r1)
            android.graphics.drawable.Drawable r2 = super.getTrackDrawable()
            r7.W = r2
            android.content.res.ColorStateList r2 = super.getTrackTintList()
            r7.f3926e0 = r2
            super.setTrackTintList(r1)
            int[] r2 = q2.m.MaterialSwitch
            r6 = 0
            int[] r5 = new int[r6]
            r1 = r9
            r3 = r10
            androidx.appcompat.app.g r9 = com.google.android.material.internal.e0.e(r0, r1, r2, r3, r4, r5)
            int r10 = q2.m.MaterialSwitch_thumbIcon
            android.graphics.drawable.Drawable r10 = r9.I(r10)
            r7.U = r10
            int r10 = q2.m.MaterialSwitch_thumbIconSize
            int r10 = r9.H(r10, r8)
            r7.V = r10
            int r10 = q2.m.MaterialSwitch_thumbIconTint
            android.content.res.ColorStateList r10 = r9.F(r10)
            r7.f3924c0 = r10
            int r10 = q2.m.MaterialSwitch_thumbIconTintMode
            int r10 = r9.L(r10, r8)
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r10 = com.google.android.material.internal.k0.g(r10, r0)
            r7.f3925d0 = r10
            int r10 = q2.m.MaterialSwitch_trackDecoration
            android.graphics.drawable.Drawable r10 = r9.I(r10)
            r7.f3922a0 = r10
            int r10 = q2.m.MaterialSwitch_trackDecorationTint
            android.content.res.ColorStateList r10 = r9.F(r10)
            r7.f3927f0 = r10
            int r10 = q2.m.MaterialSwitch_trackDecorationTintMode
            int r8 = r9.L(r10, r8)
            android.graphics.PorterDuff$Mode r8 = com.google.android.material.internal.k0.g(r8, r0)
            r7.f3928g0 = r8
            r9.V()
            r7.setEnforceSwitchWidth(r6)
            r7.e()
            r7.f()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.materialswitch.MaterialSwitch.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public static void g(Drawable drawable, ColorStateList colorStateList, int[] iArr, int[] iArr2, float f7) {
        if (drawable == null || colorStateList == null) {
            return;
        }
        b.g(drawable, a.b(colorStateList.getColorForState(iArr, 0), f7, colorStateList.getColorForState(iArr2, 0)));
    }

    public final void e() {
        this.T = d3.a.b(this.T, this.f3923b0, getThumbTintMode());
        this.U = d3.a.b(this.U, this.f3924c0, this.f3925d0);
        h();
        Drawable drawable = this.T;
        Drawable drawable2 = this.U;
        int i7 = this.V;
        super.setThumbDrawable(d3.a.a(drawable, drawable2, i7, i7));
        refreshDrawableState();
    }

    public final void f() {
        this.W = d3.a.b(this.W, this.f3926e0, getTrackTintMode());
        this.f3922a0 = d3.a.b(this.f3922a0, this.f3927f0, this.f3928g0);
        h();
        Drawable drawable = this.W;
        if (drawable != null && this.f3922a0 != null) {
            drawable = new LayerDrawable(new Drawable[]{this.W, this.f3922a0});
        } else if (drawable == null) {
            drawable = this.f3922a0;
        }
        if (drawable != null) {
            setSwitchMinWidth(drawable.getIntrinsicWidth());
        }
        super.setTrackDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getThumbDrawable() {
        return this.T;
    }

    public Drawable getThumbIconDrawable() {
        return this.U;
    }

    public int getThumbIconSize() {
        return this.V;
    }

    public ColorStateList getThumbIconTintList() {
        return this.f3924c0;
    }

    public PorterDuff.Mode getThumbIconTintMode() {
        return this.f3925d0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getThumbTintList() {
        return this.f3923b0;
    }

    public Drawable getTrackDecorationDrawable() {
        return this.f3922a0;
    }

    public ColorStateList getTrackDecorationTintList() {
        return this.f3927f0;
    }

    public PorterDuff.Mode getTrackDecorationTintMode() {
        return this.f3928g0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getTrackDrawable() {
        return this.W;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getTrackTintList() {
        return this.f3926e0;
    }

    public final void h() {
        if (this.f3923b0 == null && this.f3924c0 == null && this.f3926e0 == null && this.f3927f0 == null) {
            return;
        }
        float thumbPosition = getThumbPosition();
        ColorStateList colorStateList = this.f3923b0;
        if (colorStateList != null) {
            g(this.T, colorStateList, this.f3929h0, this.f3930i0, thumbPosition);
        }
        ColorStateList colorStateList2 = this.f3924c0;
        if (colorStateList2 != null) {
            g(this.U, colorStateList2, this.f3929h0, this.f3930i0, thumbPosition);
        }
        ColorStateList colorStateList3 = this.f3926e0;
        if (colorStateList3 != null) {
            g(this.W, colorStateList3, this.f3929h0, this.f3930i0, thumbPosition);
        }
        ColorStateList colorStateList4 = this.f3927f0;
        if (colorStateList4 != null) {
            g(this.f3922a0, colorStateList4, this.f3929h0, this.f3930i0, thumbPosition);
        }
    }

    @Override // android.view.View
    public final void invalidate() {
        h();
        super.invalidate();
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i7 + 1);
        if (this.U != null) {
            View.mergeDrawableStates(onCreateDrawableState, f3921k0);
        }
        int[] iArr = new int[onCreateDrawableState.length];
        int i8 = 0;
        for (int i9 : onCreateDrawableState) {
            if (i9 != 16842912) {
                iArr[i8] = i9;
                i8++;
            }
        }
        this.f3929h0 = iArr;
        this.f3930i0 = d3.a.c(onCreateDrawableState);
        return onCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbDrawable(Drawable drawable) {
        this.T = drawable;
        e();
    }

    public void setThumbIconDrawable(Drawable drawable) {
        this.U = drawable;
        e();
    }

    public void setThumbIconResource(int i7) {
        setThumbIconDrawable(f.A(getContext(), i7));
    }

    public void setThumbIconSize(int i7) {
        if (this.V != i7) {
            this.V = i7;
            e();
        }
    }

    public void setThumbIconTintList(ColorStateList colorStateList) {
        this.f3924c0 = colorStateList;
        e();
    }

    public void setThumbIconTintMode(PorterDuff.Mode mode) {
        this.f3925d0 = mode;
        e();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintList(ColorStateList colorStateList) {
        this.f3923b0 = colorStateList;
        e();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintMode(PorterDuff.Mode mode) {
        super.setThumbTintMode(mode);
        e();
    }

    public void setTrackDecorationDrawable(Drawable drawable) {
        this.f3922a0 = drawable;
        f();
    }

    public void setTrackDecorationResource(int i7) {
        setTrackDecorationDrawable(f.A(getContext(), i7));
    }

    public void setTrackDecorationTintList(ColorStateList colorStateList) {
        this.f3927f0 = colorStateList;
        f();
    }

    public void setTrackDecorationTintMode(PorterDuff.Mode mode) {
        this.f3928g0 = mode;
        f();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackDrawable(Drawable drawable) {
        this.W = drawable;
        f();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintList(ColorStateList colorStateList) {
        this.f3926e0 = colorStateList;
        f();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintMode(PorterDuff.Mode mode) {
        super.setTrackTintMode(mode);
        f();
    }
}
