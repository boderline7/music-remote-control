package com.google.android.material.circularreveal.coordinatorlayout;

import a3.b;
import a3.f;
import a3.g;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CircularRevealCoordinatorLayout extends CoordinatorLayout implements g {

    /* renamed from: y, reason: collision with root package name */
    public final b f3563y;

    public CircularRevealCoordinatorLayout(Context context) {
        this(context, null);
    }

    public CircularRevealCoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3563y = new b(this);
    }

    @Override // a3.a
    public final void a(Canvas canvas) {
        super.draw(canvas);
    }

    @Override // a3.g
    public final void c() {
        this.f3563y.a();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        b bVar = this.f3563y;
        if (bVar != null) {
            bVar.c(canvas);
        } else {
            super.draw(canvas);
        }
    }

    @Override // a3.g
    public final void g() {
        this.f3563y.b();
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.f3563y.f260g;
    }

    @Override // a3.g
    public int getCircularRevealScrimColor() {
        return this.f3563y.f258e.getColor();
    }

    @Override // a3.g
    public f getRevealInfo() {
        return this.f3563y.d();
    }

    @Override // a3.a
    public final boolean h() {
        return super.isOpaque();
    }

    @Override // android.view.View
    public final boolean isOpaque() {
        b bVar = this.f3563y;
        return bVar != null ? bVar.e() : super.isOpaque();
    }

    @Override // a3.g
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        this.f3563y.f(drawable);
    }

    @Override // a3.g
    public void setCircularRevealScrimColor(int i7) {
        this.f3563y.g(i7);
    }

    @Override // a3.g
    public void setRevealInfo(f fVar) {
        this.f3563y.h(fVar);
    }
}
