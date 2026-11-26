package com.google.android.material.timepicker;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.internal.k0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class ClockHandView extends View {

    /* renamed from: a, reason: collision with root package name */
    public final ValueAnimator f4439a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f4440b;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f4441c;

    /* renamed from: d, reason: collision with root package name */
    public final int f4442d;

    /* renamed from: e, reason: collision with root package name */
    public final float f4443e;

    /* renamed from: f, reason: collision with root package name */
    public final Paint f4444f;

    /* renamed from: g, reason: collision with root package name */
    public final RectF f4445g;

    /* renamed from: h, reason: collision with root package name */
    public final int f4446h;

    /* renamed from: i, reason: collision with root package name */
    public float f4447i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f4448j;

    /* renamed from: k, reason: collision with root package name */
    public double f4449k;

    /* renamed from: l, reason: collision with root package name */
    public int f4450l;

    /* renamed from: m, reason: collision with root package name */
    public int f4451m;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ClockHandView(android.content.Context r6, android.util.AttributeSet r7) {
        /*
            r5 = this;
            int r0 = q2.c.materialClockStyle
            r5.<init>(r6, r7, r0)
            android.animation.ValueAnimator r1 = new android.animation.ValueAnimator
            r1.<init>()
            r5.f4439a = r1
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.f4441c = r1
            android.graphics.Paint r1 = new android.graphics.Paint
            r1.<init>()
            r5.f4444f = r1
            android.graphics.RectF r2 = new android.graphics.RectF
            r2.<init>()
            r5.f4445g = r2
            r2 = 1
            r5.f4451m = r2
            int[] r3 = q2.m.ClockHandView
            int r4 = q2.l.Widget_MaterialComponents_TimePicker_Clock
            android.content.res.TypedArray r7 = r6.obtainStyledAttributes(r7, r3, r0, r4)
            int r0 = q2.c.motionDurationLong2
            r3 = 200(0xc8, float:2.8E-43)
            l6.f.u0(r6, r0, r3)
            int r0 = q2.c.motionEasingEmphasizedInterpolator
            android.animation.TimeInterpolator r3 = r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR
            l6.f.v0(r6, r0, r3)
            int r0 = q2.m.ClockHandView_materialCircleRadius
            r3 = 0
            int r0 = r7.getDimensionPixelSize(r0, r3)
            r5.f4450l = r0
            int r0 = q2.m.ClockHandView_selectorSize
            int r0 = r7.getDimensionPixelSize(r0, r3)
            r5.f4442d = r0
            android.content.res.Resources r0 = r5.getResources()
            int r4 = q2.e.material_clock_hand_stroke_width
            int r4 = r0.getDimensionPixelSize(r4)
            r5.f4446h = r4
            int r4 = q2.e.material_clock_hand_center_dot_radius
            int r0 = r0.getDimensionPixelSize(r4)
            float r0 = (float) r0
            r5.f4443e = r0
            int r0 = q2.m.ClockHandView_clockHandColor
            int r0 = r7.getColor(r0, r3)
            r1.setAntiAlias(r2)
            r1.setColor(r0)
            r0 = 0
            r5.b(r0)
            android.view.ViewConfiguration r6 = android.view.ViewConfiguration.get(r6)
            r6.getScaledTouchSlop()
            int r6 = p0.d1.OVER_SCROLL_ALWAYS
            r6 = 2
            p0.l0.s(r5, r6)
            r7.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.timepicker.ClockHandView.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final int a(int i7) {
        return i7 == 2 ? Math.round(this.f4450l * 0.66f) : this.f4450l;
    }

    public final void b(float f7) {
        ValueAnimator valueAnimator = this.f4439a;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        c(f7);
    }

    public final void c(float f7) {
        float f8 = f7 % 360.0f;
        this.f4447i = f8;
        this.f4449k = Math.toRadians(f8 - 90.0f);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float a7 = a(this.f4451m);
        float cos = (((float) Math.cos(this.f4449k)) * a7) + width;
        float sin = (a7 * ((float) Math.sin(this.f4449k))) + height;
        float f9 = this.f4442d;
        this.f4445g.set(cos - f9, sin - f9, cos + f9, sin + f9);
        Iterator it = this.f4441c.iterator();
        while (it.hasNext()) {
            ClockFaceView clockFaceView = (ClockFaceView) ((d) it.next());
            if (Math.abs(clockFaceView.I - f8) > 0.001f) {
                clockFaceView.I = f8;
                clockFaceView.q();
            }
        }
        invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float f7 = width;
        float a7 = a(this.f4451m);
        float cos = (((float) Math.cos(this.f4449k)) * a7) + f7;
        float f8 = height;
        float sin = (a7 * ((float) Math.sin(this.f4449k))) + f8;
        Paint paint = this.f4444f;
        paint.setStrokeWidth(CropImageView.DEFAULT_ASPECT_RATIO);
        canvas.drawCircle(cos, sin, this.f4442d, paint);
        double sin2 = Math.sin(this.f4449k);
        paint.setStrokeWidth(this.f4446h);
        canvas.drawLine(f7, f8, width + ((int) (Math.cos(this.f4449k) * r12)), height + ((int) (r12 * sin2)), paint);
        canvas.drawCircle(f7, f8, this.f4443e, paint);
    }

    @Override // android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        if (this.f4439a.isRunning()) {
            return;
        }
        b(this.f4447i);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z6;
        boolean z7;
        int actionMasked = motionEvent.getActionMasked();
        float x6 = motionEvent.getX();
        float y6 = motionEvent.getY();
        boolean z8 = false;
        if (actionMasked == 0) {
            this.f4448j = false;
            z6 = true;
            z7 = false;
        } else if (actionMasked == 1 || actionMasked == 2) {
            z7 = this.f4448j;
            if (this.f4440b) {
                this.f4451m = i3.a.a((float) (getWidth() / 2), (float) (getHeight() / 2), x6, y6) <= ((float) a(2)) + k0.c(getContext(), 12) ? 2 : 1;
            }
            z6 = false;
        } else {
            z7 = false;
            z6 = false;
        }
        boolean z9 = this.f4448j;
        int degrees = (int) Math.toDegrees(Math.atan2(y6 - (getHeight() / 2), x6 - (getWidth() / 2)));
        int i7 = degrees + 90;
        if (i7 < 0) {
            i7 = degrees + 450;
        }
        float f7 = i7;
        boolean z10 = this.f4447i != f7;
        if (!z6 || !z10) {
            if (z10 || z7) {
                b(f7);
            }
            this.f4448j = z9 | z8;
            return true;
        }
        z8 = true;
        this.f4448j = z9 | z8;
        return true;
    }
}
