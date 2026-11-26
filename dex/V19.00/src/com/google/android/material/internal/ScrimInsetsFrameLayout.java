package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import p0.d1;
import p0.l2;
import p0.r0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    public Drawable f3782a;

    /* renamed from: b, reason: collision with root package name */
    public Rect f3783b;

    /* renamed from: c, reason: collision with root package name */
    public final Rect f3784c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f3785d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f3786e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f3787f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f3788g;

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f3784c = new Rect();
        this.f3785d = true;
        this.f3786e = true;
        this.f3787f = true;
        this.f3788g = true;
        int[] iArr = q2.m.ScrimInsetsFrameLayout;
        int i8 = q2.l.Widget_Design_ScrimInsetsFrameLayout;
        e0.a(context, attributeSet, i7, i8);
        e0.b(context, attributeSet, iArr, i7, i8, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i7, i8);
        this.f3782a = obtainStyledAttributes.getDrawable(q2.m.ScrimInsetsFrameLayout_insetForeground);
        obtainStyledAttributes.recycle();
        setWillNotDraw(true);
        v2.a aVar = new v2.a(6, this);
        int i9 = d1.OVER_SCROLL_ALWAYS;
        r0.u(this, aVar);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f3783b == null || this.f3782a == null) {
            return;
        }
        int save = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        boolean z6 = this.f3785d;
        Rect rect = this.f3784c;
        if (z6) {
            rect.set(0, 0, width, this.f3783b.top);
            this.f3782a.setBounds(rect);
            this.f3782a.draw(canvas);
        }
        if (this.f3786e) {
            rect.set(0, height - this.f3783b.bottom, width, height);
            this.f3782a.setBounds(rect);
            this.f3782a.draw(canvas);
        }
        if (this.f3787f) {
            Rect rect2 = this.f3783b;
            rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
            this.f3782a.setBounds(rect);
            this.f3782a.draw(canvas);
        }
        if (this.f3788g) {
            Rect rect3 = this.f3783b;
            rect.set(width - rect3.right, rect3.top, width, height - rect3.bottom);
            this.f3782a.setBounds(rect);
            this.f3782a.draw(canvas);
        }
        canvas.restoreToCount(save);
    }

    public void e(l2 l2Var) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.f3782a;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.f3782a;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z6) {
        this.f3786e = z6;
    }

    public void setDrawLeftInsetForeground(boolean z6) {
        this.f3787f = z6;
    }

    public void setDrawRightInsetForeground(boolean z6) {
        this.f3788g = z6;
    }

    public void setDrawTopInsetForeground(boolean z6) {
        this.f3785d = z6;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.f3782a = drawable;
    }
}
