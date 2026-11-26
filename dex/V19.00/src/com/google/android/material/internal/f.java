package com.google.android.material.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    public final Drawable f3852a;

    /* renamed from: b, reason: collision with root package name */
    public final Drawable f3853b;

    /* renamed from: c, reason: collision with root package name */
    public final float[] f3854c;

    /* renamed from: d, reason: collision with root package name */
    public float f3855d;

    public f(Drawable drawable, Drawable drawable2) {
        this.f3852a = drawable.getConstantState().newDrawable().mutate();
        Drawable mutate = drawable2.getConstantState().newDrawable().mutate();
        this.f3853b = mutate;
        mutate.setAlpha(0);
        this.f3854c = new float[2];
    }

    public final void a(float f7) {
        if (this.f3855d != f7) {
            this.f3855d = f7;
            float[] fArr = this.f3854c;
            l6.f.g(f7, fArr);
            this.f3852a.setAlpha((int) (fArr[0] * 255.0f));
            this.f3853b.setAlpha((int) (fArr[1] * 255.0f));
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        this.f3852a.draw(canvas);
        this.f3853b.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.f3852a.getIntrinsicHeight(), this.f3853b.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.f3852a.getIntrinsicWidth(), this.f3853b.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumHeight() {
        return Math.max(this.f3852a.getMinimumHeight(), this.f3853b.getMinimumHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumWidth() {
        return Math.max(this.f3852a.getMinimumWidth(), this.f3853b.getMinimumWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return this.f3852a.isStateful() || this.f3853b.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        float f7 = this.f3855d;
        Drawable drawable = this.f3853b;
        Drawable drawable2 = this.f3852a;
        if (f7 <= 0.5f) {
            drawable2.setAlpha(i7);
            drawable.setAlpha(0);
        } else {
            drawable2.setAlpha(0);
            drawable.setAlpha(i7);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setBounds(int i7, int i8, int i9, int i10) {
        super.setBounds(i7, i8, i9, i10);
        this.f3852a.setBounds(i7, i8, i9, i10);
        this.f3853b.setBounds(i7, i8, i9, i10);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f3852a.setColorFilter(colorFilter);
        this.f3853b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setState(int[] iArr) {
        return this.f3852a.setState(iArr) || this.f3853b.setState(iArr);
    }
}
