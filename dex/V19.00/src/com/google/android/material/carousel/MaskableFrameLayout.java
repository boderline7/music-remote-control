package com.google.android.material.carousel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.yalantis.ucrop.view.CropImageView;
import n1.z;
import p3.a0;
import p3.b0;
import p3.c0;
import p3.d0;
import p3.p;
import r2.a;
import y2.k;
import y2.l;
import y2.n;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaskableFrameLayout extends FrameLayout implements k, a0 {

    /* renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ int f3500f = 0;

    /* renamed from: a, reason: collision with root package name */
    public float f3501a;

    /* renamed from: b, reason: collision with root package name */
    public final RectF f3502b;

    /* renamed from: c, reason: collision with root package name */
    public p f3503c;

    /* renamed from: d, reason: collision with root package name */
    public final b0 f3504d;

    /* renamed from: e, reason: collision with root package name */
    public Boolean f3505e;

    public MaskableFrameLayout(Context context) {
        this(context, null);
    }

    public MaskableFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaskableFrameLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f3501a = -1.0f;
        this.f3502b = new RectF();
        this.f3504d = Build.VERSION.SDK_INT >= 33 ? new d0(this) : new c0(this);
        this.f3505e = null;
        setShapeAppearanceModel(p.c(context, attributeSet, i7, 0).a());
    }

    public final void b() {
        if (this.f3501a != -1.0f) {
            float b7 = a.b(CropImageView.DEFAULT_ASPECT_RATIO, getWidth() / 2.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, this.f3501a);
            setMaskRectF(new RectF(b7, CropImageView.DEFAULT_ASPECT_RATIO, getWidth() - b7, getHeight()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        b0 b0Var = this.f3504d;
        if (b0Var.b()) {
            Path path = b0Var.f8231e;
            if (!path.isEmpty()) {
                canvas.save();
                canvas.clipPath(path);
                super.dispatchDraw(canvas);
                canvas.restore();
                return;
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.View
    public final void getFocusedRect(Rect rect) {
        RectF rectF = this.f3502b;
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    public RectF getMaskRectF() {
        return this.f3502b;
    }

    @Deprecated
    public float getMaskXPercentage() {
        return this.f3501a;
    }

    public p getShapeAppearanceModel() {
        return this.f3503c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Boolean bool = this.f3505e;
        if (bool != null) {
            boolean booleanValue = bool.booleanValue();
            b0 b0Var = this.f3504d;
            if (booleanValue != b0Var.f8227a) {
                b0Var.f8227a = booleanValue;
                b0Var.a(this);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        b0 b0Var = this.f3504d;
        this.f3505e = Boolean.valueOf(b0Var.f8227a);
        if (true != b0Var.f8227a) {
            b0Var.f8227a = true;
            b0Var.a(this);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        if (this.f3501a != -1.0f) {
            b();
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        RectF rectF = this.f3502b;
        if (rectF.isEmpty() || motionEvent.getAction() != 0 || rectF.contains(motionEvent.getX(), motionEvent.getY())) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setForceCompatClipping(boolean z6) {
        b0 b0Var = this.f3504d;
        if (z6 != b0Var.f8227a) {
            b0Var.f8227a = z6;
            b0Var.a(this);
        }
    }

    @Override // y2.k
    public void setMaskRectF(RectF rectF) {
        RectF rectF2 = this.f3502b;
        rectF2.set(rectF);
        b0 b0Var = this.f3504d;
        b0Var.f8230d = rectF2;
        b0Var.c();
        b0Var.a(this);
    }

    @Deprecated
    public void setMaskXPercentage(float f7) {
        float x6 = z.x(f7, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        if (this.f3501a != x6) {
            this.f3501a = x6;
            b();
        }
    }

    public void setOnMaskChangedListener(n nVar) {
    }

    @Override // p3.a0
    public void setShapeAppearanceModel(p pVar) {
        p h7 = pVar.h(new l(0));
        this.f3503c = h7;
        b0 b0Var = this.f3504d;
        b0Var.f8229c = h7;
        b0Var.c();
        b0Var.a(this);
    }
}
