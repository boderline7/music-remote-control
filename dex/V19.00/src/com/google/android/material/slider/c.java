package com.google.android.material.slider;

import android.R;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.u;
import androidx.viewpager2.widget.q;
import com.google.android.material.internal.b0;
import com.google.android.material.internal.e0;
import com.google.android.material.internal.k0;
import com.yalantis.ucrop.view.CropImageView;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import l6.f;
import n1.a0;
import n1.z;
import p0.d1;
import p0.m0;
import p0.o0;
import p3.j;
import p3.n;
import p3.p;
import q2.l;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class c extends View {

    /* renamed from: i0, reason: collision with root package name */
    public static final int f4153i0 = l.Widget_MaterialComponents_Slider;

    /* renamed from: j0, reason: collision with root package name */
    public static final int f4154j0 = q2.c.motionDurationMedium4;

    /* renamed from: k0, reason: collision with root package name */
    public static final int f4155k0 = q2.c.motionDurationShort3;

    /* renamed from: l0, reason: collision with root package name */
    public static final int f4156l0 = q2.c.motionEasingEmphasizedInterpolator;

    /* renamed from: m0, reason: collision with root package name */
    public static final int f4157m0 = q2.c.motionEasingEmphasizedAccelerateInterpolator;
    public int A;
    public int B;
    public int C;
    public int D;
    public final int E;
    public float F;
    public MotionEvent G;
    public boolean H;
    public float I;
    public float J;
    public ArrayList K;
    public int L;
    public int M;
    public float N;
    public float[] O;
    public boolean P;
    public int Q;
    public int R;
    public int S;
    public boolean T;
    public boolean U;
    public ColorStateList V;
    public ColorStateList W;

    /* renamed from: a, reason: collision with root package name */
    public final Paint f4158a;

    /* renamed from: a0, reason: collision with root package name */
    public ColorStateList f4159a0;

    /* renamed from: b, reason: collision with root package name */
    public final Paint f4160b;

    /* renamed from: b0, reason: collision with root package name */
    public ColorStateList f4161b0;

    /* renamed from: c, reason: collision with root package name */
    public final Paint f4162c;

    /* renamed from: c0, reason: collision with root package name */
    public ColorStateList f4163c0;

    /* renamed from: d, reason: collision with root package name */
    public final Paint f4164d;

    /* renamed from: d0, reason: collision with root package name */
    public final j f4165d0;

    /* renamed from: e, reason: collision with root package name */
    public final Paint f4166e;

    /* renamed from: e0, reason: collision with root package name */
    public Drawable f4167e0;

    /* renamed from: f, reason: collision with root package name */
    public final Paint f4168f;

    /* renamed from: f0, reason: collision with root package name */
    public List f4169f0;

    /* renamed from: g, reason: collision with root package name */
    public final a f4170g;

    /* renamed from: g0, reason: collision with root package name */
    public float f4171g0;

    /* renamed from: h, reason: collision with root package name */
    public final AccessibilityManager f4172h;

    /* renamed from: h0, reason: collision with root package name */
    public int f4173h0;

    /* renamed from: i, reason: collision with root package name */
    public q f4174i;

    /* renamed from: j, reason: collision with root package name */
    public final int f4175j;

    /* renamed from: k, reason: collision with root package name */
    public final ArrayList f4176k;

    /* renamed from: l, reason: collision with root package name */
    public final ArrayList f4177l;

    /* renamed from: m, reason: collision with root package name */
    public final ArrayList f4178m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f4179n;

    /* renamed from: o, reason: collision with root package name */
    public ValueAnimator f4180o;

    /* renamed from: p, reason: collision with root package name */
    public ValueAnimator f4181p;

    /* renamed from: q, reason: collision with root package name */
    public final int f4182q;

    /* renamed from: r, reason: collision with root package name */
    public final int f4183r;

    /* renamed from: s, reason: collision with root package name */
    public final int f4184s;

    /* renamed from: t, reason: collision with root package name */
    public final int f4185t;

    /* renamed from: u, reason: collision with root package name */
    public final int f4186u;

    /* renamed from: v, reason: collision with root package name */
    public final int f4187v;

    /* renamed from: w, reason: collision with root package name */
    public final int f4188w;

    /* renamed from: x, reason: collision with root package name */
    public final int f4189x;

    /* renamed from: y, reason: collision with root package name */
    public int f4190y;

    /* renamed from: z, reason: collision with root package name */
    public int f4191z;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public c(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            Method dump skipped, instructions count: 609
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.c.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public final void a(Drawable drawable) {
        int i7 = this.C * 2;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, i7, i7);
        } else {
            float max = i7 / Math.max(intrinsicWidth, intrinsicHeight);
            drawable.setBounds(0, 0, (int) (intrinsicWidth * max), (int) (intrinsicHeight * max));
        }
    }

    public final int b() {
        int i7 = this.f4190y / 2;
        int i8 = this.f4191z;
        return i7 + ((i8 == 1 || i8 == 3) ? ((u3.a) this.f4176k.get(0)).getIntrinsicHeight() : 0);
    }

    public final ValueAnimator c(boolean z6) {
        int u02;
        TimeInterpolator v02;
        float f7 = z6 ? 0.0f : 1.0f;
        ValueAnimator valueAnimator = z6 ? this.f4181p : this.f4180o;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            f7 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            valueAnimator.cancel();
        }
        int i7 = 2;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f7, z6 ? 1.0f : 0.0f);
        if (z6) {
            u02 = f.u0(getContext(), f4154j0, 83);
            v02 = f.v0(getContext(), f4156l0, r2.a.DECELERATE_INTERPOLATOR);
        } else {
            u02 = f.u0(getContext(), f4155k0, z1.c.INVOKE_SUPER_RANGE);
            v02 = f.v0(getContext(), f4157m0, r2.a.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        }
        ofFloat.setDuration(u02);
        ofFloat.setInterpolator(v02);
        ofFloat.addUpdateListener(new s2.d(i7, this));
        return ofFloat;
    }

    public final void d(Canvas canvas, int i7, int i8, float f7, Drawable drawable) {
        canvas.save();
        canvas.translate((this.B + ((int) (m(f7) * i7))) - (drawable.getBounds().width() / 2.0f), i8 - (drawable.getBounds().height() / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.f4170g.m(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        this.f4158a.setColor(f(this.f4163c0));
        this.f4160b.setColor(f(this.f4161b0));
        this.f4166e.setColor(f(this.f4159a0));
        this.f4168f.setColor(f(this.W));
        Iterator it = this.f4176k.iterator();
        while (it.hasNext()) {
            u3.a aVar = (u3.a) it.next();
            if (aVar.isStateful()) {
                aVar.setState(getDrawableState());
            }
        }
        j jVar = this.f4165d0;
        if (jVar.isStateful()) {
            jVar.setState(getDrawableState());
        }
        Paint paint = this.f4164d;
        paint.setColor(f(this.V));
        paint.setAlpha(63);
    }

    public final float[] e() {
        float floatValue = ((Float) Collections.max(getValues())).floatValue();
        float floatValue2 = ((Float) Collections.min(getValues())).floatValue();
        if (this.K.size() == 1) {
            floatValue2 = this.I;
        }
        float m6 = m(floatValue2);
        float m7 = m(floatValue);
        return i() ? new float[]{m7, m6} : new float[]{m6, m7};
    }

    public final int f(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public final boolean g(float f7) {
        double doubleValue = new BigDecimal(Float.toString(f7)).divide(new BigDecimal(Float.toString(this.N)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(doubleValue)) - doubleValue) < 1.0E-4d;
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.f4170g.f9228k;
    }

    public float getMinSeparation() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public abstract float getValueFrom();

    public abstract float getValueTo();

    public List<Float> getValues() {
        return new ArrayList(this.K);
    }

    public final boolean h(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) == 3) {
            return false;
        }
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    public final boolean i() {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        return m0.d(this) == 1;
    }

    public final void j() {
        if (this.N <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        w();
        int min = Math.min((int) (((this.J - this.I) / this.N) + 1.0f), (this.S / (this.A * 2)) + 1);
        float[] fArr = this.O;
        if (fArr == null || fArr.length != min * 2) {
            this.O = new float[min * 2];
        }
        float f7 = this.S / (min - 1);
        for (int i7 = 0; i7 < min * 2; i7 += 2) {
            float[] fArr2 = this.O;
            fArr2[i7] = ((i7 / 2.0f) * f7) + this.B;
            fArr2[i7 + 1] = b();
        }
    }

    public final boolean k(int i7) {
        int i8 = this.M;
        long j7 = i8 + i7;
        long size = this.K.size() - 1;
        if (j7 < 0) {
            j7 = 0;
        } else if (j7 > size) {
            j7 = size;
        }
        int i9 = (int) j7;
        this.M = i9;
        if (i9 == i8) {
            return false;
        }
        if (this.L != -1) {
            this.L = i9;
        }
        u();
        postInvalidate();
        return true;
    }

    public final void l(int i7) {
        if (i()) {
            i7 = i7 == Integer.MIN_VALUE ? u.MAX_ACTIVITY_COUNT_UNLIMITED : -i7;
        }
        k(i7);
    }

    public final float m(float f7) {
        float f8 = this.I;
        float f9 = (f7 - f8) / (this.J - f8);
        return i() ? 1.0f - f9 : f9;
    }

    public final void n() {
        Iterator it = this.f4178m.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
    }

    public boolean o() {
        if (this.L != -1) {
            return true;
        }
        float f7 = this.f4171g0;
        if (i()) {
            f7 = 1.0f - f7;
        }
        float f8 = this.J;
        float f9 = this.I;
        float a7 = a.a.a(f8, f9, f7, f9);
        float m6 = (m(a7) * this.S) + this.B;
        this.L = 0;
        float abs = Math.abs(((Float) this.K.get(0)).floatValue() - a7);
        for (int i7 = 1; i7 < this.K.size(); i7++) {
            float abs2 = Math.abs(((Float) this.K.get(i7)).floatValue() - a7);
            float m7 = (m(((Float) this.K.get(i7)).floatValue()) * this.S) + this.B;
            if (Float.compare(abs2, abs) > 1) {
                break;
            }
            boolean z6 = !i() ? m7 - m6 >= CropImageView.DEFAULT_ASPECT_RATIO : m7 - m6 <= CropImageView.DEFAULT_ASPECT_RATIO;
            if (Float.compare(abs2, abs) < 0) {
                this.L = i7;
            } else {
                if (Float.compare(abs2, abs) != 0) {
                    continue;
                } else {
                    if (Math.abs(m7 - m6) < this.f4182q) {
                        this.L = -1;
                        return false;
                    }
                    if (z6) {
                        this.L = i7;
                    }
                }
            }
            abs = abs2;
        }
        return this.L != -1;
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Iterator it = this.f4176k.iterator();
        while (it.hasNext()) {
            u3.a aVar = (u3.a) it.next();
            ViewGroup d7 = k0.d(this);
            if (d7 == null) {
                aVar.getClass();
            } else {
                aVar.getClass();
                int[] iArr = new int[2];
                d7.getLocationOnScreen(iArr);
                aVar.J = iArr[0];
                d7.getWindowVisibleDisplayFrame(aVar.D);
                d7.addOnLayoutChangeListener(aVar.C);
            }
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        q qVar = this.f4174i;
        if (qVar != null) {
            removeCallbacks(qVar);
        }
        this.f4179n = false;
        Iterator it = this.f4176k.iterator();
        while (it.hasNext()) {
            u3.a aVar = (u3.a) it.next();
            a0 e7 = k0.e(this);
            if (e7 != null) {
                int i7 = e7.f7586a;
                ViewOverlay viewOverlay = e7.f7587b;
                switch (i7) {
                    case 0:
                        viewOverlay.remove(aVar);
                        break;
                    default:
                        viewOverlay.remove(aVar);
                        break;
                }
                ViewGroup d7 = k0.d(this);
                if (d7 == null) {
                    aVar.getClass();
                } else {
                    d7.removeOnLayoutChangeListener(aVar.C);
                }
            }
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        if (this.U) {
            w();
            j();
        }
        super.onDraw(canvas);
        int b7 = b();
        int i7 = this.S;
        float[] e7 = e();
        int i8 = this.B;
        float f7 = i7;
        float f8 = i8 + (e7[1] * f7);
        float f9 = i8 + i7;
        Paint paint = this.f4158a;
        if (f8 < f9) {
            float f10 = b7;
            canvas.drawLine(f8, f10, f9, f10, paint);
        }
        float f11 = this.B;
        float f12 = (e7[0] * f7) + f11;
        if (f12 > f11) {
            float f13 = b7;
            canvas.drawLine(f11, f13, f12, f13, paint);
        }
        if (((Float) Collections.max(getValues())).floatValue() > this.I) {
            int i9 = this.S;
            float[] e8 = e();
            float f14 = this.B;
            float f15 = i9;
            float f16 = b7;
            canvas.drawLine((e8[0] * f15) + f14, f16, (e8[1] * f15) + f14, f16, this.f4160b);
        }
        if (this.P && this.N > CropImageView.DEFAULT_ASPECT_RATIO) {
            float[] e9 = e();
            int round = Math.round(e9[0] * ((this.O.length / 2) - 1));
            int round2 = Math.round(e9[1] * ((this.O.length / 2) - 1));
            float[] fArr = this.O;
            int i10 = round * 2;
            Paint paint2 = this.f4166e;
            canvas.drawPoints(fArr, 0, i10, paint2);
            int i11 = round2 * 2;
            canvas.drawPoints(this.O, i10, i11 - i10, this.f4168f);
            float[] fArr2 = this.O;
            canvas.drawPoints(fArr2, i11, fArr2.length - i11, paint2);
        }
        if ((this.H || isFocused()) && isEnabled()) {
            int i12 = this.S;
            if (!(getBackground() instanceof RippleDrawable)) {
                int m6 = (int) ((m(((Float) this.K.get(this.M)).floatValue()) * i12) + this.B);
                if (Build.VERSION.SDK_INT < 28) {
                    int i13 = this.D;
                    canvas.clipRect(m6 - i13, b7 - i13, m6 + i13, i13 + b7, Region.Op.UNION);
                }
                canvas.drawCircle(m6, b7, this.D, this.f4164d);
            }
        }
        if ((this.L != -1 || this.f4191z == 3) && isEnabled()) {
            if (this.f4191z != 2) {
                if (!this.f4179n) {
                    this.f4179n = true;
                    ValueAnimator c7 = c(true);
                    this.f4180o = c7;
                    this.f4181p = null;
                    c7.start();
                }
                ArrayList arrayList = this.f4176k;
                Iterator it = arrayList.iterator();
                for (int i14 = 0; i14 < this.K.size() && it.hasNext(); i14++) {
                    if (i14 != this.M) {
                        p((u3.a) it.next(), ((Float) this.K.get(i14)).floatValue());
                    }
                }
                if (!it.hasNext()) {
                    throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(arrayList.size()), Integer.valueOf(this.K.size())));
                }
                p((u3.a) it.next(), ((Float) this.K.get(this.M)).floatValue());
            }
        } else if (this.f4179n) {
            this.f4179n = false;
            ValueAnimator c8 = c(false);
            this.f4181p = c8;
            this.f4180o = null;
            c8.addListener(new androidx.appcompat.widget.d(10, this));
            this.f4181p.start();
        }
        int i15 = this.S;
        for (int i16 = 0; i16 < this.K.size(); i16++) {
            float floatValue = ((Float) this.K.get(i16)).floatValue();
            Drawable drawable = this.f4167e0;
            if (drawable != null) {
                d(canvas, i15, b7, floatValue, drawable);
            } else if (i16 < this.f4169f0.size()) {
                d(canvas, i15, b7, floatValue, (Drawable) this.f4169f0.get(i16));
            } else {
                if (!isEnabled()) {
                    canvas.drawCircle((m(floatValue) * i15) + this.B, b7, this.C, this.f4162c);
                }
                d(canvas, i15, b7, floatValue, this.f4165d0);
            }
        }
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z6, int i7, Rect rect) {
        super.onFocusChanged(z6, i7, rect);
        a aVar = this.f4170g;
        if (!z6) {
            this.L = -1;
            aVar.j(this.M);
            return;
        }
        if (i7 == 1) {
            k(u.MAX_ACTIVITY_COUNT_UNLIMITED);
        } else if (i7 == 2) {
            k(Integer.MIN_VALUE);
        } else if (i7 == 17) {
            l(u.MAX_ACTIVITY_COUNT_UNLIMITED);
        } else if (i7 == 66) {
            l(Integer.MIN_VALUE);
        }
        aVar.w(this.M);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i7, KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i7, keyEvent);
        }
        if (this.K.size() == 1) {
            this.L = 0;
        }
        Float f7 = null;
        Boolean valueOf = null;
        if (this.L == -1) {
            if (i7 != 61) {
                if (i7 != 66) {
                    if (i7 != 81) {
                        if (i7 == 69) {
                            k(-1);
                            valueOf = Boolean.TRUE;
                        } else if (i7 != 70) {
                            switch (i7) {
                                case 21:
                                    l(-1);
                                    valueOf = Boolean.TRUE;
                                    break;
                                case 22:
                                    l(1);
                                    valueOf = Boolean.TRUE;
                                    break;
                            }
                        }
                    }
                    k(1);
                    valueOf = Boolean.TRUE;
                }
                this.L = this.M;
                postInvalidate();
                valueOf = Boolean.TRUE;
            } else {
                valueOf = keyEvent.hasNoModifiers() ? Boolean.valueOf(k(1)) : keyEvent.isShiftPressed() ? Boolean.valueOf(k(-1)) : Boolean.FALSE;
            }
            return valueOf != null ? valueOf.booleanValue() : super.onKeyDown(i7, keyEvent);
        }
        boolean isLongPress = this.T | keyEvent.isLongPress();
        this.T = isLongPress;
        if (isLongPress) {
            float f8 = this.N;
            r10 = f8 != CropImageView.DEFAULT_ASPECT_RATIO ? f8 : 1.0f;
            if ((this.J - this.I) / r10 > 20) {
                r10 *= Math.round(r0 / r11);
            }
        } else {
            float f9 = this.N;
            if (f9 != CropImageView.DEFAULT_ASPECT_RATIO) {
                r10 = f9;
            }
        }
        if (i7 == 21) {
            if (!i()) {
                r10 = -r10;
            }
            f7 = Float.valueOf(r10);
        } else if (i7 == 22) {
            if (i()) {
                r10 = -r10;
            }
            f7 = Float.valueOf(r10);
        } else if (i7 == 69) {
            f7 = Float.valueOf(-r10);
        } else if (i7 == 70 || i7 == 81) {
            f7 = Float.valueOf(r10);
        }
        if (f7 != null) {
            if (r(f7.floatValue() + ((Float) this.K.get(this.L)).floatValue(), this.L)) {
                u();
                postInvalidate();
            }
            return true;
        }
        if (i7 != 23) {
            if (i7 == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return k(1);
                }
                if (keyEvent.isShiftPressed()) {
                    return k(-1);
                }
                return false;
            }
            if (i7 != 66) {
                return super.onKeyDown(i7, keyEvent);
            }
        }
        this.L = -1;
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i7, KeyEvent keyEvent) {
        this.T = false;
        return super.onKeyUp(i7, keyEvent);
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        int i9 = this.f4190y;
        int i10 = this.f4191z;
        super.onMeasure(i7, View.MeasureSpec.makeMeasureSpec(i9 + ((i10 == 1 || i10 == 3) ? ((u3.a) this.f4176k.get(0)).getIntrinsicHeight() : 0), v.c.EXACTLY));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        BaseSlider$SliderState baseSlider$SliderState = (BaseSlider$SliderState) parcelable;
        super.onRestoreInstanceState(baseSlider$SliderState.getSuperState());
        this.I = baseSlider$SliderState.f4142a;
        this.J = baseSlider$SliderState.f4143b;
        q(baseSlider$SliderState.f4144c);
        this.N = baseSlider$SliderState.f4145d;
        if (baseSlider$SliderState.f4146e) {
            requestFocus();
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        BaseSlider$SliderState baseSlider$SliderState = new BaseSlider$SliderState(super.onSaveInstanceState());
        baseSlider$SliderState.f4142a = this.I;
        baseSlider$SliderState.f4143b = this.J;
        baseSlider$SliderState.f4144c = new ArrayList(this.K);
        baseSlider$SliderState.f4145d = this.N;
        baseSlider$SliderState.f4146e = hasFocus();
        return baseSlider$SliderState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        this.S = Math.max(i7 - (this.B * 2), 0);
        j();
        u();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0035, code lost:
    
        if (r2 != 3) goto L52;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.c.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i7) {
        a0 e7;
        super.onVisibilityChanged(view, i7);
        if (i7 == 0 || (e7 = k0.e(this)) == null) {
            return;
        }
        Iterator it = this.f4176k.iterator();
        while (it.hasNext()) {
            u3.a aVar = (u3.a) it.next();
            int i8 = e7.f7586a;
            ViewOverlay viewOverlay = e7.f7587b;
            switch (i8) {
                case 0:
                    viewOverlay.remove(aVar);
                    break;
                default:
                    viewOverlay.remove(aVar);
                    break;
            }
        }
    }

    public final void p(u3.a aVar, float f7) {
        String format = String.format(((float) ((int) f7)) == f7 ? "%.0f" : "%.2f", Float.valueOf(f7));
        if (!TextUtils.equals(aVar.f9312y, format)) {
            aVar.f9312y = format;
            aVar.B.f3800e = true;
            aVar.invalidateSelf();
        }
        int m6 = (this.B + ((int) (m(f7) * this.S))) - (aVar.getIntrinsicWidth() / 2);
        int b7 = b() - (this.E + this.C);
        aVar.setBounds(m6, b7 - aVar.getIntrinsicHeight(), aVar.getIntrinsicWidth() + m6, b7);
        Rect rect = new Rect(aVar.getBounds());
        com.google.android.material.internal.e.c(k0.d(this), this, rect);
        aVar.setBounds(rect);
        a0 e7 = k0.e(this);
        int i7 = e7.f7586a;
        ViewOverlay viewOverlay = e7.f7587b;
        switch (i7) {
            case 0:
                viewOverlay.add(aVar);
                break;
            default:
                viewOverlay.add(aVar);
                break;
        }
    }

    public final void q(ArrayList arrayList) {
        ViewGroup d7;
        int resourceId;
        a0 e7;
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be set");
        }
        Collections.sort(arrayList);
        if (this.K.size() == arrayList.size() && this.K.equals(arrayList)) {
            return;
        }
        this.K = arrayList;
        this.U = true;
        this.M = 0;
        u();
        ArrayList arrayList2 = this.f4176k;
        if (arrayList2.size() > this.K.size()) {
            List<u3.a> subList = arrayList2.subList(this.K.size(), arrayList2.size());
            for (u3.a aVar : subList) {
                int i7 = d1.OVER_SCROLL_ALWAYS;
                if (o0.b(this) && (e7 = k0.e(this)) != null) {
                    int i8 = e7.f7586a;
                    ViewOverlay viewOverlay = e7.f7587b;
                    switch (i8) {
                        case 0:
                            viewOverlay.remove(aVar);
                            break;
                        default:
                            viewOverlay.remove(aVar);
                            break;
                    }
                    ViewGroup d8 = k0.d(this);
                    if (d8 == null) {
                        aVar.getClass();
                    } else {
                        d8.removeOnLayoutChangeListener(aVar.C);
                    }
                }
            }
            subList.clear();
        }
        while (true) {
            m3.e eVar = null;
            if (arrayList2.size() >= this.K.size()) {
                int i9 = arrayList2.size() == 1 ? 0 : 1;
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((u3.a) it.next()).u(i9);
                }
                Iterator it2 = this.f4177l.iterator();
                while (it2.hasNext()) {
                    a.a.u(it2.next());
                    Iterator it3 = this.K.iterator();
                    if (it3.hasNext()) {
                        ((Float) it3.next()).floatValue();
                        throw null;
                    }
                }
                postInvalidate();
                return;
            }
            Context context = getContext();
            int i10 = this.f4175j;
            u3.a aVar2 = new u3.a(context, i10);
            TypedArray d9 = e0.d(aVar2.f9313z, null, m.Tooltip, 0, i10, new int[0]);
            Context context2 = aVar2.f9313z;
            aVar2.I = context2.getResources().getDimensionPixelSize(q2.e.mtrl_tooltip_arrowSize);
            n g7 = aVar2.f8263a.f8241a.g();
            g7.f8299k = aVar2.z();
            aVar2.setShapeAppearanceModel(g7.a());
            CharSequence text = d9.getText(m.Tooltip_android_text);
            boolean equals = TextUtils.equals(aVar2.f9312y, text);
            b0 b0Var = aVar2.B;
            if (!equals) {
                aVar2.f9312y = text;
                b0Var.f3800e = true;
                aVar2.invalidateSelf();
            }
            int i11 = m.Tooltip_android_textAppearance;
            if (d9.hasValue(i11) && (resourceId = d9.getResourceId(i11, 0)) != 0) {
                eVar = new m3.e(context2, resourceId);
            }
            if (eVar != null && d9.hasValue(m.Tooltip_android_textColor)) {
                eVar.f7546j = f.w(context2, d9, m.Tooltip_android_textColor);
            }
            b0Var.c(eVar, context2);
            aVar2.o(ColorStateList.valueOf(d9.getColor(m.Tooltip_backgroundTint, i0.a.c(i0.a.e(b3.a.d(context2, q2.c.colorOnBackground, u3.a.class.getCanonicalName()), z1.c.SHR_INT), i0.a.e(b3.a.d(context2, R.attr.colorBackground, u3.a.class.getCanonicalName()), 229)))));
            aVar2.t(ColorStateList.valueOf(b3.a.d(context2, q2.c.colorSurface, u3.a.class.getCanonicalName())));
            aVar2.E = d9.getDimensionPixelSize(m.Tooltip_android_padding, 0);
            aVar2.F = d9.getDimensionPixelSize(m.Tooltip_android_minWidth, 0);
            aVar2.G = d9.getDimensionPixelSize(m.Tooltip_android_minHeight, 0);
            aVar2.H = d9.getDimensionPixelSize(m.Tooltip_android_layout_margin, 0);
            d9.recycle();
            arrayList2.add(aVar2);
            int i12 = d1.OVER_SCROLL_ALWAYS;
            if (o0.b(this) && (d7 = k0.d(this)) != null) {
                int[] iArr = new int[2];
                d7.getLocationOnScreen(iArr);
                aVar2.J = iArr[0];
                d7.getWindowVisibleDisplayFrame(aVar2.D);
                d7.addOnLayoutChangeListener(aVar2.C);
            }
        }
    }

    public final boolean r(float f7, int i7) {
        this.M = i7;
        int i8 = 0;
        if (Math.abs(f7 - ((Float) this.K.get(i7)).floatValue()) < 1.0E-4d) {
            return false;
        }
        float minSeparation = getMinSeparation();
        if (this.f4173h0 == 0) {
            if (minSeparation == CropImageView.DEFAULT_ASPECT_RATIO) {
                minSeparation = 0.0f;
            } else {
                float f8 = this.I;
                minSeparation = a.a.a(f8, this.J, (minSeparation - this.B) / this.S, f8);
            }
        }
        if (i()) {
            minSeparation = -minSeparation;
        }
        int i9 = i7 + 1;
        int i10 = i7 - 1;
        this.K.set(i7, Float.valueOf(z.x(f7, i10 < 0 ? this.I : minSeparation + ((Float) this.K.get(i10)).floatValue(), i9 >= this.K.size() ? this.J : ((Float) this.K.get(i9)).floatValue() - minSeparation)));
        Iterator it = this.f4177l.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            ((Float) this.K.get(i7)).floatValue();
            throw null;
        }
        AccessibilityManager accessibilityManager = this.f4172h;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return true;
        }
        Runnable runnable = this.f4174i;
        if (runnable == null) {
            this.f4174i = new q(this, i8);
        } else {
            removeCallbacks(runnable);
        }
        q qVar = this.f4174i;
        qVar.f2921b = i7;
        postDelayed(qVar, 200L);
        return true;
    }

    public final void s() {
        double d7;
        float f7 = this.f4171g0;
        float f8 = this.N;
        if (f8 > CropImageView.DEFAULT_ASPECT_RATIO) {
            d7 = Math.round(f7 * r1) / ((int) ((this.J - this.I) / f8));
        } else {
            d7 = f7;
        }
        if (i()) {
            d7 = 1.0d - d7;
        }
        float f9 = this.J;
        r((float) ((d7 * (f9 - r1)) + this.I), this.L);
    }

    public void setActiveThumbIndex(int i7) {
        this.L = i7;
    }

    public void setCustomThumbDrawablesForValues(int... iArr) {
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i7 = 0; i7 < iArr.length; i7++) {
            drawableArr[i7] = getResources().getDrawable(iArr[i7]);
        }
        setCustomThumbDrawablesForValues(drawableArr);
    }

    public void setCustomThumbDrawablesForValues(Drawable... drawableArr) {
        this.f4167e0 = null;
        this.f4169f0 = new ArrayList();
        for (Drawable drawable : drawableArr) {
            List list = this.f4169f0;
            Drawable newDrawable = drawable.mutate().getConstantState().newDrawable();
            a(newDrawable);
            list.add(newDrawable);
        }
        postInvalidate();
    }

    @Override // android.view.View
    public void setEnabled(boolean z6) {
        super.setEnabled(z6);
        setLayerType(z6 ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i7) {
        if (i7 < 0 || i7 >= this.K.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.M = i7;
        this.f4170g.w(i7);
        postInvalidate();
    }

    public void setHaloRadius(int i7) {
        if (i7 == this.D) {
            return;
        }
        this.D = i7;
        Drawable background = getBackground();
        if ((!(getBackground() instanceof RippleDrawable)) || !(background instanceof RippleDrawable)) {
            postInvalidate();
        } else {
            ((RippleDrawable) background).setRadius(this.D);
        }
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.V)) {
            return;
        }
        this.V = colorStateList;
        Drawable background = getBackground();
        if (!(!(getBackground() instanceof RippleDrawable)) && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        Paint paint = this.f4164d;
        paint.setColor(f(colorStateList));
        paint.setAlpha(63);
        invalidate();
    }

    public abstract void setLabelBehavior(int i7);

    public void setSeparationUnit(int i7) {
        this.f4173h0 = i7;
        this.U = true;
        postInvalidate();
    }

    public void setStepSize(float f7) {
        if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
            throw new IllegalArgumentException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.valueOf(f7), Float.valueOf(this.I), Float.valueOf(this.J)));
        }
        if (this.N != f7) {
            this.N = f7;
            this.U = true;
            postInvalidate();
        }
    }

    public abstract void setThumbElevation(float f7);

    public void setThumbRadius(int i7) {
        if (i7 == this.C) {
            return;
        }
        this.C = i7;
        j jVar = this.f4165d0;
        p3.f s6 = f.s();
        p3.f s7 = f.s();
        p3.f s8 = f.s();
        p3.f s9 = f.s();
        float f7 = this.C;
        f r6 = f.r(0);
        n.b(r6);
        n.b(r6);
        n.b(r6);
        n.b(r6);
        p3.a aVar = new p3.a(f7);
        p3.a aVar2 = new p3.a(f7);
        p3.a aVar3 = new p3.a(f7);
        p3.a aVar4 = new p3.a(f7);
        p pVar = new p();
        pVar.f8301a = r6;
        pVar.f8302b = r6;
        pVar.f8303c = r6;
        pVar.f8304d = r6;
        pVar.f8305e = aVar;
        pVar.f8306f = aVar2;
        pVar.f8307g = aVar3;
        pVar.f8308h = aVar4;
        pVar.f8309i = s6;
        pVar.f8310j = s7;
        pVar.f8311k = s8;
        pVar.f8312l = s9;
        jVar.setShapeAppearanceModel(pVar);
        int i8 = this.C * 2;
        jVar.setBounds(0, 0, i8, i8);
        Drawable drawable = this.f4167e0;
        if (drawable != null) {
            a(drawable);
        }
        Iterator it = this.f4169f0.iterator();
        while (it.hasNext()) {
            a((Drawable) it.next());
        }
        v();
    }

    public abstract void setThumbStrokeColor(ColorStateList colorStateList);

    public abstract void setThumbStrokeWidth(float f7);

    public abstract void setTickActiveRadius(int i7);

    public abstract void setTickActiveTintList(ColorStateList colorStateList);

    public abstract void setTickInactiveRadius(int i7);

    public abstract void setTickInactiveTintList(ColorStateList colorStateList);

    public abstract void setTrackActiveTintList(ColorStateList colorStateList);

    public abstract void setTrackHeight(int i7);

    public abstract void setTrackInactiveTintList(ColorStateList colorStateList);

    public void setValues(List<Float> list) {
        q(new ArrayList(list));
    }

    public void setValues(Float... fArr) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fArr);
        q(arrayList);
    }

    public final void t(int i7, Rect rect) {
        int m6 = this.B + ((int) (m(getValues().get(i7).floatValue()) * this.S));
        int b7 = b();
        int i8 = this.C;
        int i9 = this.f4188w;
        if (i8 <= i9) {
            i8 = i9;
        }
        int i10 = i8 / 2;
        rect.set(m6 - i10, b7 - i10, m6 + i10, b7 + i10);
    }

    public final void u() {
        if ((!(getBackground() instanceof RippleDrawable)) || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int m6 = (int) ((m(((Float) this.K.get(this.M)).floatValue()) * this.S) + this.B);
            int b7 = b();
            int i7 = this.D;
            j0.b.f(background, m6 - i7, b7 - i7, m6 + i7, b7 + i7);
        }
    }

    public final void v() {
        boolean z6;
        int max = Math.max(this.f4189x, Math.max(this.A + getPaddingBottom() + getPaddingTop(), getPaddingBottom() + getPaddingTop() + (this.C * 2)));
        boolean z7 = false;
        if (max == this.f4190y) {
            z6 = false;
        } else {
            this.f4190y = max;
            z6 = true;
        }
        int max2 = Math.max(Math.max(Math.max(this.C - this.f4184s, 0), Math.max((this.A - this.f4185t) / 2, 0)), Math.max(Math.max(this.Q - this.f4186u, 0), Math.max(this.R - this.f4187v, 0))) + this.f4183r;
        if (this.B != max2) {
            this.B = max2;
            int i7 = d1.OVER_SCROLL_ALWAYS;
            if (o0.c(this)) {
                this.S = Math.max(getWidth() - (this.B * 2), 0);
                j();
            }
            z7 = true;
        }
        if (z6) {
            requestLayout();
        } else if (z7) {
            postInvalidate();
        }
    }

    public final void w() {
        if (this.U) {
            float f7 = this.I;
            float f8 = this.J;
            if (f7 >= f8) {
                throw new IllegalStateException(String.format("valueFrom(%s) must be smaller than valueTo(%s)", Float.valueOf(this.I), Float.valueOf(this.J)));
            }
            if (f8 <= f7) {
                throw new IllegalStateException(String.format("valueTo(%s) must be greater than valueFrom(%s)", Float.valueOf(this.J), Float.valueOf(this.I)));
            }
            if (this.N > CropImageView.DEFAULT_ASPECT_RATIO && !g(f8 - f7)) {
                throw new IllegalStateException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.valueOf(this.N), Float.valueOf(this.I), Float.valueOf(this.J)));
            }
            Iterator it = this.K.iterator();
            while (it.hasNext()) {
                Float f9 = (Float) it.next();
                if (f9.floatValue() < this.I || f9.floatValue() > this.J) {
                    throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", f9, Float.valueOf(this.I), Float.valueOf(this.J)));
                }
                if (this.N > CropImageView.DEFAULT_ASPECT_RATIO && !g(f9.floatValue() - this.I)) {
                    throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", f9, Float.valueOf(this.I), Float.valueOf(this.N), Float.valueOf(this.N)));
                }
            }
            float minSeparation = getMinSeparation();
            if (minSeparation < CropImageView.DEFAULT_ASPECT_RATIO) {
                throw new IllegalStateException(String.format("minSeparation(%s) must be greater or equal to 0", Float.valueOf(minSeparation)));
            }
            float f10 = this.N;
            if (f10 > CropImageView.DEFAULT_ASPECT_RATIO && minSeparation > CropImageView.DEFAULT_ASPECT_RATIO) {
                if (this.f4173h0 != 1) {
                    throw new IllegalStateException(String.format("minSeparation(%s) cannot be set as a dimension when using stepSize(%s)", Float.valueOf(minSeparation), Float.valueOf(this.N)));
                }
                if (minSeparation < f10 || !g(minSeparation)) {
                    throw new IllegalStateException(String.format("minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)", Float.valueOf(minSeparation), Float.valueOf(this.N), Float.valueOf(this.N)));
                }
            }
            float f11 = this.N;
            if (f11 != CropImageView.DEFAULT_ASPECT_RATIO) {
                if (((int) f11) != f11) {
                    String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "stepSize", Float.valueOf(f11));
                }
                float f12 = this.I;
                if (((int) f12) != f12) {
                    String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueFrom", Float.valueOf(f12));
                }
                float f13 = this.J;
                if (((int) f13) != f13) {
                    String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueTo", Float.valueOf(f13));
                }
            }
            this.U = false;
        }
    }
}
