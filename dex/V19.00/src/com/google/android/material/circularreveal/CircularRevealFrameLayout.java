package com.google.android.material.circularreveal;

import a3.b;
import a3.f;
import a3.g;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CircularRevealFrameLayout extends FrameLayout implements g {

    /* renamed from: a, reason: collision with root package name */
    public final b f3558a;

    public CircularRevealFrameLayout(Context context) {
        this(context, null);
    }

    public CircularRevealFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3558a = new b(this);
    }

    @Override // a3.a
    public final void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // a3.g
    public final void c() {
        this.f3558a.a();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        b bVar = this.f3558a;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    @Override // a3.g
    public final void g() {
        this.f3558a.b();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f3558a.f260g;
    }

    @Override // a3.g
    public int getCircularRevealScrimColor() {
        return this.f3558a.f258e.getColor();
    }

    @Override // a3.g
    public f getRevealInfo() {
        return this.f3558a.d();
    }

    @Override // a3.a
    public final boolean h() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public final boolean isOpaque() {
        b bVar = this.f3558a;
        return bVar != null ? bVar.e() : super.isOpaque();
    }

    @Override // a3.g
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f3558a.f(drawable);
    }

    @Override // a3.g
    public void setCircularRevealScrimColor(int i7) {
        this.f3558a.g(i7);
    }

    @Override // a3.g
    public void setRevealInfo(f fVar) {
        this.f3558a.h(fVar);
    }
}
