package z2;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.material.chip.Chip;
import com.google.android.material.internal.a0;
import com.google.android.material.internal.b0;
import com.google.android.material.internal.k0;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import n1.z;
import p3.n;
import p3.r;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends p3.j implements Drawable.Callback, a0 {
    public static final int[] H0 = {R.attr.state_enabled};
    public static final ShapeDrawable I0 = new ShapeDrawable(new OvalShape());
    public float A;
    public boolean A0;
    public float B;
    public ColorStateList B0;
    public ColorStateList C;
    public WeakReference C0;
    public float D;
    public TextUtils.TruncateAt D0;
    public ColorStateList E;
    public boolean E0;
    public CharSequence F;
    public int F0;
    public boolean G;
    public boolean G0;
    public Drawable H;
    public ColorStateList I;
    public float J;
    public boolean K;
    public boolean L;
    public Drawable M;
    public RippleDrawable N;
    public ColorStateList O;
    public float P;
    public SpannableStringBuilder Q;
    public boolean R;
    public boolean S;
    public Drawable T;
    public ColorStateList U;
    public r2.e V;
    public r2.e W;
    public float X;
    public float Y;
    public float Z;

    /* renamed from: a0, reason: collision with root package name */
    public float f10258a0;

    /* renamed from: b0, reason: collision with root package name */
    public float f10259b0;

    /* renamed from: c0, reason: collision with root package name */
    public float f10260c0;

    /* renamed from: d0, reason: collision with root package name */
    public float f10261d0;

    /* renamed from: e0, reason: collision with root package name */
    public float f10262e0;

    /* renamed from: f0, reason: collision with root package name */
    public final Context f10263f0;

    /* renamed from: g0, reason: collision with root package name */
    public final Paint f10264g0;

    /* renamed from: h0, reason: collision with root package name */
    public final Paint.FontMetrics f10265h0;

    /* renamed from: i0, reason: collision with root package name */
    public final RectF f10266i0;

    /* renamed from: j0, reason: collision with root package name */
    public final PointF f10267j0;

    /* renamed from: k0, reason: collision with root package name */
    public final Path f10268k0;

    /* renamed from: l0, reason: collision with root package name */
    public final b0 f10269l0;

    /* renamed from: m0, reason: collision with root package name */
    public int f10270m0;

    /* renamed from: n0, reason: collision with root package name */
    public int f10271n0;

    /* renamed from: o0, reason: collision with root package name */
    public int f10272o0;

    /* renamed from: p0, reason: collision with root package name */
    public int f10273p0;

    /* renamed from: q0, reason: collision with root package name */
    public int f10274q0;

    /* renamed from: r0, reason: collision with root package name */
    public int f10275r0;

    /* renamed from: s0, reason: collision with root package name */
    public boolean f10276s0;

    /* renamed from: t0, reason: collision with root package name */
    public int f10277t0;

    /* renamed from: u0, reason: collision with root package name */
    public int f10278u0;

    /* renamed from: v0, reason: collision with root package name */
    public ColorFilter f10279v0;

    /* renamed from: w0, reason: collision with root package name */
    public PorterDuffColorFilter f10280w0;

    /* renamed from: x0, reason: collision with root package name */
    public ColorStateList f10281x0;

    /* renamed from: y, reason: collision with root package name */
    public ColorStateList f10282y;

    /* renamed from: y0, reason: collision with root package name */
    public PorterDuff.Mode f10283y0;

    /* renamed from: z, reason: collision with root package name */
    public ColorStateList f10284z;

    /* renamed from: z0, reason: collision with root package name */
    public int[] f10285z0;

    public f(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7, Chip.f3529x);
        this.B = -1.0f;
        this.f10264g0 = new Paint(1);
        this.f10265h0 = new Paint.FontMetrics();
        this.f10266i0 = new RectF();
        this.f10267j0 = new PointF();
        this.f10268k0 = new Path();
        this.f10278u0 = 255;
        this.f10283y0 = PorterDuff.Mode.SRC_IN;
        this.C0 = new WeakReference(null);
        l(context);
        this.f10263f0 = context;
        b0 b0Var = new b0(this);
        this.f10269l0 = b0Var;
        this.F = h0.FRAGMENT_ENCODE_SET;
        b0Var.f3796a.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = H0;
        setState(iArr);
        if (!Arrays.equals(this.f10285z0, iArr)) {
            this.f10285z0 = iArr;
            if (d0()) {
                G(getState(), iArr);
            }
        }
        this.E0 = true;
        if (n3.a.USE_FRAMEWORK_RIPPLE) {
            I0.setTint(-1);
        }
    }

    public static boolean D(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean E(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public static void e0(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public final float A() {
        if (!c0() && !b0()) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float f7 = this.Y;
        Drawable drawable = this.f10276s0 ? this.T : this.H;
        float f8 = this.J;
        if (f8 <= CropImageView.DEFAULT_ASPECT_RATIO && drawable != null) {
            f8 = drawable.getIntrinsicWidth();
        }
        return f8 + f7 + this.Z;
    }

    public final float B() {
        return d0() ? this.f10260c0 + this.P + this.f10261d0 : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final float C() {
        return this.G0 ? j() : this.B;
    }

    public final void F() {
        e eVar = (e) this.C0.get();
        if (eVar != null) {
            Chip chip = (Chip) eVar;
            chip.c(chip.f3544q);
            chip.requestLayout();
            chip.invalidateOutline();
        }
    }

    public final boolean G(int[] iArr, int[] iArr2) {
        boolean z6;
        boolean z7;
        ColorStateList colorStateList;
        boolean onStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList2 = this.f10282y;
        int d7 = d(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.f10270m0) : 0);
        boolean z8 = true;
        if (this.f10270m0 != d7) {
            this.f10270m0 = d7;
            onStateChange = true;
        }
        ColorStateList colorStateList3 = this.f10284z;
        int d8 = d(colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.f10271n0) : 0);
        if (this.f10271n0 != d8) {
            this.f10271n0 = d8;
            onStateChange = true;
        }
        int c7 = i0.a.c(d8, d7);
        if ((this.f10272o0 != c7) | (this.f8263a.f8243c == null)) {
            this.f10272o0 = c7;
            o(ColorStateList.valueOf(c7));
            onStateChange = true;
        }
        ColorStateList colorStateList4 = this.C;
        int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.f10273p0) : 0;
        if (this.f10273p0 != colorForState) {
            this.f10273p0 = colorForState;
            onStateChange = true;
        }
        int colorForState2 = (this.B0 == null || !n3.a.d(iArr)) ? 0 : this.B0.getColorForState(iArr, this.f10274q0);
        if (this.f10274q0 != colorForState2) {
            this.f10274q0 = colorForState2;
            if (this.A0) {
                onStateChange = true;
            }
        }
        m3.e eVar = this.f10269l0.f3802g;
        int colorForState3 = (eVar == null || (colorStateList = eVar.f7546j) == null) ? 0 : colorStateList.getColorForState(iArr, this.f10275r0);
        if (this.f10275r0 != colorForState3) {
            this.f10275r0 = colorForState3;
            onStateChange = true;
        }
        int[] state = getState();
        if (state != null) {
            int length = state.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    break;
                }
                if (state[i7] != 16842912) {
                    i7++;
                } else if (this.R) {
                    z6 = true;
                }
            }
        }
        z6 = false;
        if (this.f10276s0 == z6 || this.T == null) {
            z7 = false;
        } else {
            float A = A();
            this.f10276s0 = z6;
            if (A != A()) {
                onStateChange = true;
                z7 = true;
            } else {
                z7 = false;
                onStateChange = true;
            }
        }
        ColorStateList colorStateList5 = this.f10281x0;
        int colorForState4 = colorStateList5 != null ? colorStateList5.getColorForState(iArr, this.f10277t0) : 0;
        if (this.f10277t0 != colorForState4) {
            this.f10277t0 = colorForState4;
            ColorStateList colorStateList6 = this.f10281x0;
            PorterDuff.Mode mode = this.f10283y0;
            this.f10280w0 = (colorStateList6 == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList6.getColorForState(getState(), 0), mode);
        } else {
            z8 = onStateChange;
        }
        if (E(this.H)) {
            z8 |= this.H.setState(iArr);
        }
        if (E(this.T)) {
            z8 |= this.T.setState(iArr);
        }
        if (E(this.M)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            z8 |= this.M.setState(iArr3);
        }
        if (n3.a.USE_FRAMEWORK_RIPPLE && E(this.N)) {
            z8 |= this.N.setState(iArr2);
        }
        if (z8) {
            invalidateSelf();
        }
        if (z7) {
            F();
        }
        return z8;
    }

    public final void H(boolean z6) {
        if (this.R != z6) {
            this.R = z6;
            float A = A();
            if (!z6 && this.f10276s0) {
                this.f10276s0 = false;
            }
            float A2 = A();
            invalidateSelf();
            if (A != A2) {
                F();
            }
        }
    }

    public final void I(Drawable drawable) {
        if (this.T != drawable) {
            float A = A();
            this.T = drawable;
            float A2 = A();
            e0(this.T);
            y(this.T);
            invalidateSelf();
            if (A != A2) {
                F();
            }
        }
    }

    public final void J(ColorStateList colorStateList) {
        Drawable drawable;
        if (this.U != colorStateList) {
            this.U = colorStateList;
            if (this.S && (drawable = this.T) != null && this.R) {
                j0.b.h(drawable, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void K(boolean z6) {
        if (this.S != z6) {
            boolean b02 = b0();
            this.S = z6;
            boolean b03 = b0();
            if (b02 != b03) {
                if (b03) {
                    y(this.T);
                } else {
                    e0(this.T);
                }
                invalidateSelf();
                F();
            }
        }
    }

    public final void L(float f7) {
        if (this.B != f7) {
            this.B = f7;
            n g7 = this.f8263a.f8241a.g();
            g7.c(f7);
            setShapeAppearanceModel(g7.a());
        }
    }

    public final void M(Drawable drawable) {
        Drawable drawable2 = this.H;
        Drawable p12 = drawable2 != null ? z.p1(drawable2) : null;
        if (p12 != drawable) {
            float A = A();
            this.H = drawable != null ? drawable.mutate() : null;
            float A2 = A();
            e0(p12);
            if (c0()) {
                y(this.H);
            }
            invalidateSelf();
            if (A != A2) {
                F();
            }
        }
    }

    public final void N(float f7) {
        if (this.J != f7) {
            float A = A();
            this.J = f7;
            float A2 = A();
            invalidateSelf();
            if (A != A2) {
                F();
            }
        }
    }

    public final void O(ColorStateList colorStateList) {
        this.K = true;
        if (this.I != colorStateList) {
            this.I = colorStateList;
            if (c0()) {
                j0.b.h(this.H, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void P(boolean z6) {
        if (this.G != z6) {
            boolean c02 = c0();
            this.G = z6;
            boolean c03 = c0();
            if (c02 != c03) {
                if (c03) {
                    y(this.H);
                } else {
                    e0(this.H);
                }
                invalidateSelf();
                F();
            }
        }
    }

    public final void Q(ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            if (this.G0) {
                t(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void R(float f7) {
        if (this.D != f7) {
            this.D = f7;
            this.f10264g0.setStrokeWidth(f7);
            if (this.G0) {
                u(f7);
            }
            invalidateSelf();
        }
    }

    public final void S(Drawable drawable) {
        Drawable drawable2 = this.M;
        Drawable p12 = drawable2 != null ? z.p1(drawable2) : null;
        if (p12 != drawable) {
            float B = B();
            this.M = drawable != null ? drawable.mutate() : null;
            if (n3.a.USE_FRAMEWORK_RIPPLE) {
                this.N = new RippleDrawable(n3.a.c(this.E), this.M, I0);
            }
            float B2 = B();
            e0(p12);
            if (d0()) {
                y(this.M);
            }
            invalidateSelf();
            if (B != B2) {
                F();
            }
        }
    }

    public final void T(float f7) {
        if (this.f10261d0 != f7) {
            this.f10261d0 = f7;
            invalidateSelf();
            if (d0()) {
                F();
            }
        }
    }

    public final void U(float f7) {
        if (this.P != f7) {
            this.P = f7;
            invalidateSelf();
            if (d0()) {
                F();
            }
        }
    }

    public final void V(float f7) {
        if (this.f10260c0 != f7) {
            this.f10260c0 = f7;
            invalidateSelf();
            if (d0()) {
                F();
            }
        }
    }

    public final void W(ColorStateList colorStateList) {
        if (this.O != colorStateList) {
            this.O = colorStateList;
            if (d0()) {
                j0.b.h(this.M, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void X(boolean z6) {
        if (this.L != z6) {
            boolean d02 = d0();
            this.L = z6;
            boolean d03 = d0();
            if (d02 != d03) {
                if (d03) {
                    y(this.M);
                } else {
                    e0(this.M);
                }
                invalidateSelf();
                F();
            }
        }
    }

    public final void Y(float f7) {
        if (this.Z != f7) {
            float A = A();
            this.Z = f7;
            float A2 = A();
            invalidateSelf();
            if (A != A2) {
                F();
            }
        }
    }

    public final void Z(float f7) {
        if (this.Y != f7) {
            float A = A();
            this.Y = f7;
            float A2 = A();
            invalidateSelf();
            if (A != A2) {
                F();
            }
        }
    }

    @Override // p3.j, com.google.android.material.internal.a0
    public final void a() {
        F();
        invalidateSelf();
    }

    public final void a0(ColorStateList colorStateList) {
        if (this.E != colorStateList) {
            this.E = colorStateList;
            this.B0 = this.A0 ? n3.a.c(colorStateList) : null;
            onStateChange(getState());
        }
    }

    public final boolean b0() {
        return this.S && this.T != null && this.f10276s0;
    }

    public final boolean c0() {
        return this.G && this.H != null;
    }

    public final boolean d0() {
        return this.L && this.M != null;
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i7;
        RectF rectF;
        int i8;
        int i9;
        int i10;
        RectF rectF2;
        int i11;
        Rect bounds = getBounds();
        if (bounds.isEmpty() || (i7 = this.f10278u0) == 0) {
            return;
        }
        int saveLayerAlpha = i7 < 255 ? canvas.saveLayerAlpha(bounds.left, bounds.top, bounds.right, bounds.bottom, i7) : 0;
        boolean z6 = this.G0;
        Paint paint = this.f10264g0;
        RectF rectF3 = this.f10266i0;
        if (!z6) {
            paint.setColor(this.f10270m0);
            paint.setStyle(Paint.Style.FILL);
            rectF3.set(bounds);
            canvas.drawRoundRect(rectF3, C(), C(), paint);
        }
        if (!this.G0) {
            paint.setColor(this.f10271n0);
            paint.setStyle(Paint.Style.FILL);
            ColorFilter colorFilter = this.f10279v0;
            if (colorFilter == null) {
                colorFilter = this.f10280w0;
            }
            paint.setColorFilter(colorFilter);
            rectF3.set(bounds);
            canvas.drawRoundRect(rectF3, C(), C(), paint);
        }
        if (this.G0) {
            super.draw(canvas);
        }
        if (this.D > CropImageView.DEFAULT_ASPECT_RATIO && !this.G0) {
            paint.setColor(this.f10273p0);
            paint.setStyle(Paint.Style.STROKE);
            if (!this.G0) {
                ColorFilter colorFilter2 = this.f10279v0;
                if (colorFilter2 == null) {
                    colorFilter2 = this.f10280w0;
                }
                paint.setColorFilter(colorFilter2);
            }
            float f7 = bounds.left;
            float f8 = this.D / 2.0f;
            rectF3.set(f7 + f8, bounds.top + f8, bounds.right - f8, bounds.bottom - f8);
            float f9 = this.B - (this.D / 2.0f);
            canvas.drawRoundRect(rectF3, f9, f9, paint);
        }
        paint.setColor(this.f10274q0);
        paint.setStyle(Paint.Style.FILL);
        rectF3.set(bounds);
        if (this.G0) {
            RectF rectF4 = new RectF(bounds);
            Path path = this.f10268k0;
            r rVar = this.f8280r;
            p3.i iVar = this.f8263a;
            rVar.a(iVar.f8241a, iVar.f8250j, rectF4, this.f8279q, path);
            f(canvas, paint, path, this.f8263a.f8241a, h());
        } else {
            canvas.drawRoundRect(rectF3, C(), C(), paint);
        }
        if (c0()) {
            z(bounds, rectF3);
            float f10 = rectF3.left;
            float f11 = rectF3.top;
            canvas.translate(f10, f11);
            this.H.setBounds(0, 0, (int) rectF3.width(), (int) rectF3.height());
            this.H.draw(canvas);
            canvas.translate(-f10, -f11);
        }
        if (b0()) {
            z(bounds, rectF3);
            float f12 = rectF3.left;
            float f13 = rectF3.top;
            canvas.translate(f12, f13);
            this.T.setBounds(0, 0, (int) rectF3.width(), (int) rectF3.height());
            this.T.draw(canvas);
            canvas.translate(-f12, -f13);
        }
        if (!this.E0 || this.F == null) {
            rectF = rectF3;
            i8 = saveLayerAlpha;
            i9 = 0;
            i10 = 255;
        } else {
            PointF pointF = this.f10267j0;
            pointF.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
            Paint.Align align = Paint.Align.LEFT;
            CharSequence charSequence = this.F;
            b0 b0Var = this.f10269l0;
            if (charSequence != null) {
                float A = A() + this.X + this.f10258a0;
                if (j0.c.a(this) == 0) {
                    pointF.x = bounds.left + A;
                } else {
                    pointF.x = bounds.right - A;
                    align = Paint.Align.RIGHT;
                }
                float centerY = bounds.centerY();
                TextPaint textPaint = b0Var.f3796a;
                Paint.FontMetrics fontMetrics = this.f10265h0;
                textPaint.getFontMetrics(fontMetrics);
                pointF.y = centerY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
            }
            rectF3.setEmpty();
            if (this.F != null) {
                float A2 = A() + this.X + this.f10258a0;
                float B = B() + this.f10262e0 + this.f10259b0;
                if (j0.c.a(this) == 0) {
                    rectF3.left = bounds.left + A2;
                    rectF3.right = bounds.right - B;
                } else {
                    rectF3.left = bounds.left + B;
                    rectF3.right = bounds.right - A2;
                }
                rectF3.top = bounds.top;
                rectF3.bottom = bounds.bottom;
            }
            m3.e eVar = b0Var.f3802g;
            TextPaint textPaint2 = b0Var.f3796a;
            if (eVar != null) {
                textPaint2.drawableState = getState();
                b0Var.f3802g.e(this.f10263f0, textPaint2, b0Var.f3797b);
            }
            textPaint2.setTextAlign(align);
            boolean z7 = Math.round(b0Var.a(this.F.toString())) > Math.round(rectF3.width());
            if (z7) {
                i11 = canvas.save();
                canvas.clipRect(rectF3);
            } else {
                i11 = 0;
            }
            CharSequence charSequence2 = this.F;
            if (z7 && this.D0 != null) {
                charSequence2 = TextUtils.ellipsize(charSequence2, textPaint2, rectF3.width(), this.D0);
            }
            CharSequence charSequence3 = charSequence2;
            int length = charSequence3.length();
            float f14 = pointF.x;
            float f15 = pointF.y;
            rectF = rectF3;
            i8 = saveLayerAlpha;
            i9 = 0;
            i10 = 255;
            canvas.drawText(charSequence3, 0, length, f14, f15, textPaint2);
            if (z7) {
                canvas.restoreToCount(i11);
            }
        }
        if (d0()) {
            rectF.setEmpty();
            if (d0()) {
                float f16 = this.f10262e0 + this.f10261d0;
                if (j0.c.a(this) == 0) {
                    float f17 = bounds.right - f16;
                    rectF2 = rectF;
                    rectF2.right = f17;
                    rectF2.left = f17 - this.P;
                } else {
                    rectF2 = rectF;
                    float f18 = bounds.left + f16;
                    rectF2.left = f18;
                    rectF2.right = f18 + this.P;
                }
                float exactCenterY = bounds.exactCenterY();
                float f19 = this.P;
                float f20 = exactCenterY - (f19 / 2.0f);
                rectF2.top = f20;
                rectF2.bottom = f20 + f19;
            } else {
                rectF2 = rectF;
            }
            float f21 = rectF2.left;
            float f22 = rectF2.top;
            canvas.translate(f21, f22);
            this.M.setBounds(i9, i9, (int) rectF2.width(), (int) rectF2.height());
            if (n3.a.USE_FRAMEWORK_RIPPLE) {
                this.N.setBounds(this.M.getBounds());
                this.N.jumpToCurrentState();
                this.N.draw(canvas);
            } else {
                this.M.draw(canvas);
            }
            canvas.translate(-f21, -f22);
        }
        if (this.f10278u0 < i10) {
            canvas.restoreToCount(i8);
        }
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f10278u0;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        return this.f10279v0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) this.A;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.min(Math.round(B() + this.f10269l0.a(this.F.toString()) + A() + this.X + this.f10258a0 + this.f10259b0 + this.f10262e0), this.F0);
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        if (this.G0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.A, this.B);
        } else {
            outline.setRoundRect(bounds, this.B);
        }
        outline.setAlpha(this.f10278u0 / 255.0f);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        m3.e eVar;
        ColorStateList colorStateList;
        return D(this.f10282y) || D(this.f10284z) || D(this.C) || (this.A0 && D(this.B0)) || (!((eVar = this.f10269l0.f3802g) == null || (colorStateList = eVar.f7546j) == null || !colorStateList.isStateful()) || ((this.S && this.T != null && this.R) || E(this.H) || E(this.T) || D(this.f10281x0)));
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i7) {
        boolean onLayoutDirectionChanged = super.onLayoutDirectionChanged(i7);
        if (c0()) {
            onLayoutDirectionChanged |= j0.c.b(this.H, i7);
        }
        if (b0()) {
            onLayoutDirectionChanged |= j0.c.b(this.T, i7);
        }
        if (d0()) {
            onLayoutDirectionChanged |= j0.c.b(this.M, i7);
        }
        if (!onLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i7) {
        boolean onLevelChange = super.onLevelChange(i7);
        if (c0()) {
            onLevelChange |= this.H.setLevel(i7);
        }
        if (b0()) {
            onLevelChange |= this.T.setLevel(i7);
        }
        if (d0()) {
            onLevelChange |= this.M.setLevel(i7);
        }
        if (onLevelChange) {
            invalidateSelf();
        }
        return onLevelChange;
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        if (this.G0) {
            super.onStateChange(iArr);
        }
        return G(iArr, this.f10285z0);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j7) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j7);
        }
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        if (this.f10278u0 != i7) {
            this.f10278u0 = i7;
            invalidateSelf();
        }
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.f10279v0 != colorFilter) {
            this.f10279v0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        if (this.f10281x0 != colorStateList) {
            this.f10281x0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        if (this.f10283y0 != mode) {
            this.f10283y0 = mode;
            ColorStateList colorStateList = this.f10281x0;
            this.f10280w0 = (colorStateList == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z6, boolean z7) {
        boolean visible = super.setVisible(z6, z7);
        if (c0()) {
            visible |= this.H.setVisible(z6, z7);
        }
        if (b0()) {
            visible |= this.T.setVisible(z6, z7);
        }
        if (d0()) {
            visible |= this.M.setVisible(z6, z7);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final void y(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        j0.c.b(drawable, j0.c.a(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.M) {
            if (drawable.isStateful()) {
                drawable.setState(this.f10285z0);
            }
            j0.b.h(drawable, this.O);
            return;
        }
        Drawable drawable2 = this.H;
        if (drawable == drawable2 && this.K) {
            j0.b.h(drawable2, this.I);
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
    }

    public final void z(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (c0() || b0()) {
            float f7 = this.X + this.Y;
            Drawable drawable = this.f10276s0 ? this.T : this.H;
            float f8 = this.J;
            if (f8 <= CropImageView.DEFAULT_ASPECT_RATIO && drawable != null) {
                f8 = drawable.getIntrinsicWidth();
            }
            if (j0.c.a(this) == 0) {
                float f9 = rect.left + f7;
                rectF.left = f9;
                rectF.right = f9 + f8;
            } else {
                float f10 = rect.right - f7;
                rectF.right = f10;
                rectF.left = f10 - f8;
            }
            Drawable drawable2 = this.f10276s0 ? this.T : this.H;
            float f11 = this.J;
            if (f11 <= CropImageView.DEFAULT_ASPECT_RATIO && drawable2 != null) {
                f11 = (float) Math.ceil(k0.c(this.f10263f0, 24));
                if (drawable2.getIntrinsicHeight() <= f11) {
                    f11 = drawable2.getIntrinsicHeight();
                }
            }
            float exactCenterY = rect.exactCenterY() - (f11 / 2.0f);
            rectF.top = exactCenterY;
            rectF.bottom = exactCenterY + f11;
        }
    }
}
