package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.internal.k0;
import l6.f;
import p0.d1;
import p0.l0;
import p0.p0;
import p0.r0;
import p3.j;
import p3.p;
import q2.c;
import q2.e;
import q2.m;
import r3.b;
import t3.a;
import y.b0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout {

    /* renamed from: i, reason: collision with root package name */
    public static final b0 f4193i = new b0();

    /* renamed from: a, reason: collision with root package name */
    public final p f4194a;

    /* renamed from: b, reason: collision with root package name */
    public int f4195b;

    /* renamed from: c, reason: collision with root package name */
    public final float f4196c;

    /* renamed from: d, reason: collision with root package name */
    public final float f4197d;

    /* renamed from: e, reason: collision with root package name */
    public final int f4198e;

    /* renamed from: f, reason: collision with root package name */
    public final int f4199f;

    /* renamed from: g, reason: collision with root package name */
    public ColorStateList f4200g;

    /* renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f4201h;

    /* JADX WARN: Multi-variable type inference failed */
    public BaseTransientBottomBar$SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
        super(a.a(context, attributeSet, 0, 0), attributeSet);
        GradientDrawable gradientDrawable;
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, m.SnackbarLayout);
        if (obtainStyledAttributes.hasValue(m.SnackbarLayout_elevation)) {
            float dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(m.SnackbarLayout_elevation, 0);
            int i7 = d1.OVER_SCROLL_ALWAYS;
            r0.s(this, dimensionPixelSize);
        }
        this.f4195b = obtainStyledAttributes.getInt(m.SnackbarLayout_animationMode, 0);
        if (obtainStyledAttributes.hasValue(m.SnackbarLayout_shapeAppearance) || obtainStyledAttributes.hasValue(m.SnackbarLayout_shapeAppearanceOverlay)) {
            this.f4194a = p.c(context2, attributeSet, 0, 0).a();
        }
        this.f4196c = obtainStyledAttributes.getFloat(m.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
        setBackgroundTintList(f.w(context2, obtainStyledAttributes, m.SnackbarLayout_backgroundTint));
        setBackgroundTintMode(k0.g(obtainStyledAttributes.getInt(m.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
        this.f4197d = obtainStyledAttributes.getFloat(m.SnackbarLayout_actionTextColorAlpha, 1.0f);
        this.f4198e = obtainStyledAttributes.getDimensionPixelSize(m.SnackbarLayout_android_maxWidth, -1);
        this.f4199f = obtainStyledAttributes.getDimensionPixelSize(m.SnackbarLayout_maxActionInlineWidth, -1);
        obtainStyledAttributes.recycle();
        setOnTouchListener(f4193i);
        setFocusable(true);
        if (getBackground() == null) {
            int f7 = b3.a.f(b3.a.b(c.colorSurface, this), getBackgroundOverlayColorAlpha(), b3.a.b(c.colorOnSurface, this));
            p pVar = this.f4194a;
            if (pVar != null) {
                int i8 = b.ANIMATION_MODE_SLIDE;
                j jVar = new j(pVar);
                jVar.o(ColorStateList.valueOf(f7));
                gradientDrawable = jVar;
            } else {
                Resources resources = getResources();
                int i9 = b.ANIMATION_MODE_SLIDE;
                float dimension = resources.getDimension(e.mtrl_snackbar_background_corner_radius);
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setShape(0);
                gradientDrawable2.setCornerRadius(dimension);
                gradientDrawable2.setColor(f7);
                gradientDrawable = gradientDrawable2;
            }
            ColorStateList colorStateList = this.f4200g;
            if (colorStateList != null) {
                j0.b.h(gradientDrawable, colorStateList);
            }
            int i10 = d1.OVER_SCROLL_ALWAYS;
            l0.q(this, gradientDrawable);
        }
    }

    private void setBaseTransientBottomBar(b bVar) {
    }

    public float getActionTextColorAlpha() {
        return this.f4197d;
    }

    public int getAnimationMode() {
        return this.f4195b;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.f4196c;
    }

    public int getMaxInlineActionWidth() {
        return this.f4199f;
    }

    public int getMaxWidth() {
        return this.f4198e;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i7 = d1.OVER_SCROLL_ALWAYS;
        p0.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        int i9 = this.f4198e;
        if (i9 <= 0 || getMeasuredWidth() <= i9) {
            return;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(i9, v.c.EXACTLY), i8);
    }

    public void setAnimationMode(int i7) {
        this.f4195b = i7;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.f4200g != null) {
            drawable = drawable.mutate();
            j0.b.h(drawable, this.f4200g);
            j0.b.i(drawable, this.f4201h);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.f4200g = colorStateList;
        if (getBackground() != null) {
            Drawable mutate = getBackground().mutate();
            j0.b.h(mutate, colorStateList);
            j0.b.i(mutate, this.f4201h);
            if (mutate != getBackground()) {
                super.setBackgroundDrawable(mutate);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.f4201h = mode;
        if (getBackground() != null) {
            Drawable mutate = getBackground().mutate();
            j0.b.i(mutate, mode);
            if (mutate != getBackground()) {
                super.setBackgroundDrawable(mutate);
            }
        }
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f4193i);
        super.setOnClickListener(onClickListener);
    }
}
