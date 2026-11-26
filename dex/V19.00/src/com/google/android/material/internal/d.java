package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d {
    public Typeface A;
    public Typeface B;
    public Typeface C;
    public m3.b D;
    public m3.b E;
    public CharSequence G;
    public CharSequence H;
    public boolean I;
    public Bitmap K;
    public float L;
    public float M;
    public float N;
    public float O;
    public float P;
    public int Q;
    public int[] R;
    public boolean S;
    public final TextPaint T;
    public final TextPaint U;
    public TimeInterpolator V;
    public TimeInterpolator W;
    public float X;
    public float Y;
    public float Z;

    /* renamed from: a, reason: collision with root package name */
    public final View f3805a;

    /* renamed from: a0, reason: collision with root package name */
    public ColorStateList f3806a0;

    /* renamed from: b, reason: collision with root package name */
    public float f3807b;

    /* renamed from: b0, reason: collision with root package name */
    public float f3808b0;

    /* renamed from: c, reason: collision with root package name */
    public boolean f3809c;

    /* renamed from: c0, reason: collision with root package name */
    public float f3810c0;

    /* renamed from: d, reason: collision with root package name */
    public float f3811d;

    /* renamed from: d0, reason: collision with root package name */
    public float f3812d0;

    /* renamed from: e, reason: collision with root package name */
    public float f3813e;

    /* renamed from: e0, reason: collision with root package name */
    public ColorStateList f3814e0;

    /* renamed from: f, reason: collision with root package name */
    public int f3815f;

    /* renamed from: f0, reason: collision with root package name */
    public float f3816f0;

    /* renamed from: g, reason: collision with root package name */
    public final Rect f3817g;

    /* renamed from: g0, reason: collision with root package name */
    public float f3818g0;

    /* renamed from: h, reason: collision with root package name */
    public final Rect f3819h;

    /* renamed from: h0, reason: collision with root package name */
    public float f3820h0;

    /* renamed from: i, reason: collision with root package name */
    public final RectF f3821i;

    /* renamed from: i0, reason: collision with root package name */
    public StaticLayout f3822i0;

    /* renamed from: j0, reason: collision with root package name */
    public float f3824j0;

    /* renamed from: k0, reason: collision with root package name */
    public float f3826k0;

    /* renamed from: l0, reason: collision with root package name */
    public float f3828l0;

    /* renamed from: m0, reason: collision with root package name */
    public CharSequence f3830m0;

    /* renamed from: n, reason: collision with root package name */
    public ColorStateList f3831n;

    /* renamed from: o, reason: collision with root package name */
    public ColorStateList f3833o;

    /* renamed from: p, reason: collision with root package name */
    public int f3835p;

    /* renamed from: q, reason: collision with root package name */
    public float f3837q;

    /* renamed from: r, reason: collision with root package name */
    public float f3839r;

    /* renamed from: s, reason: collision with root package name */
    public float f3840s;

    /* renamed from: t, reason: collision with root package name */
    public float f3841t;

    /* renamed from: u, reason: collision with root package name */
    public float f3842u;

    /* renamed from: v, reason: collision with root package name */
    public float f3843v;

    /* renamed from: w, reason: collision with root package name */
    public Typeface f3844w;

    /* renamed from: x, reason: collision with root package name */
    public Typeface f3845x;

    /* renamed from: y, reason: collision with root package name */
    public Typeface f3846y;

    /* renamed from: z, reason: collision with root package name */
    public Typeface f3847z;

    /* renamed from: j, reason: collision with root package name */
    public int f3823j = 16;

    /* renamed from: k, reason: collision with root package name */
    public int f3825k = 16;

    /* renamed from: l, reason: collision with root package name */
    public float f3827l = 15.0f;

    /* renamed from: m, reason: collision with root package name */
    public float f3829m = 15.0f;
    public TextUtils.TruncateAt F = TextUtils.TruncateAt.END;
    public boolean J = true;

    /* renamed from: n0, reason: collision with root package name */
    public int f3832n0 = 1;

    /* renamed from: o0, reason: collision with root package name */
    public float f3834o0 = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: p0, reason: collision with root package name */
    public float f3836p0 = 1.0f;

    /* renamed from: q0, reason: collision with root package name */
    public int f3838q0 = 1;

    public d(View view) {
        this.f3805a = view;
        TextPaint textPaint = new TextPaint(z1.c.INT_TO_LONG);
        this.T = textPaint;
        this.U = new TextPaint(textPaint);
        this.f3819h = new Rect();
        this.f3817g = new Rect();
        this.f3821i = new RectF();
        float f7 = this.f3811d;
        this.f3813e = a.a.a(1.0f, f7, 0.5f, f7);
        h(view.getContext().getResources().getConfiguration());
    }

    public static int a(int i7, float f7, int i8) {
        float f8 = 1.0f - f7;
        return Color.argb(Math.round((Color.alpha(i8) * f7) + (Color.alpha(i7) * f8)), Math.round((Color.red(i8) * f7) + (Color.red(i7) * f8)), Math.round((Color.green(i8) * f7) + (Color.green(i7) * f8)), Math.round((Color.blue(i8) * f7) + (Color.blue(i7) * f8)));
    }

    public static float g(float f7, float f8, float f9, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f9 = timeInterpolator.getInterpolation(f9);
        }
        return r2.a.a(f7, f8, f9);
    }

    public final boolean b(CharSequence charSequence) {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        boolean z6 = m0.d(this.f3805a) == 1;
        if (this.J) {
            return ((n0.k) (z6 ? n0.n.FIRSTSTRONG_RTL : n0.n.FIRSTSTRONG_LTR)).c(charSequence, charSequence.length());
        }
        return z6;
    }

    public final void c(float f7, boolean z6) {
        float f8;
        float f9;
        Typeface typeface;
        boolean z7;
        Layout.Alignment alignment;
        if (this.G == null) {
            return;
        }
        float width = this.f3819h.width();
        float width2 = this.f3817g.width();
        if (Math.abs(f7 - 1.0f) < 1.0E-5f) {
            f8 = this.f3829m;
            f9 = this.f3816f0;
            this.L = 1.0f;
            typeface = this.f3844w;
        } else {
            float f10 = this.f3827l;
            float f11 = this.f3818g0;
            Typeface typeface2 = this.f3847z;
            if (Math.abs(f7 - CropImageView.DEFAULT_ASPECT_RATIO) < 1.0E-5f) {
                this.L = 1.0f;
            } else {
                this.L = g(this.f3827l, this.f3829m, f7, this.W) / this.f3827l;
            }
            float f12 = this.f3829m / this.f3827l;
            width = (z6 || this.f3809c || width2 * f12 <= width) ? width2 : Math.min(width / f12, width2);
            f8 = f10;
            f9 = f11;
            typeface = typeface2;
        }
        TextPaint textPaint = this.T;
        if (width > CropImageView.DEFAULT_ASPECT_RATIO) {
            boolean z8 = this.M != f8;
            boolean z9 = this.f3820h0 != f9;
            boolean z10 = this.C != typeface;
            StaticLayout staticLayout = this.f3822i0;
            boolean z11 = z8 || z9 || (staticLayout != null && (width > ((float) staticLayout.getWidth()) ? 1 : (width == ((float) staticLayout.getWidth()) ? 0 : -1)) != 0) || z10 || this.S;
            this.M = f8;
            this.f3820h0 = f9;
            this.C = typeface;
            this.S = false;
            textPaint.setLinearText(this.L != 1.0f);
            z7 = z11;
        } else {
            z7 = false;
        }
        if (this.H == null || z7) {
            textPaint.setTextSize(this.M);
            textPaint.setTypeface(this.C);
            textPaint.setLetterSpacing(this.f3820h0);
            boolean b7 = b(this.G);
            this.I = b7;
            int i7 = this.f3832n0;
            if (i7 <= 1 || (b7 && !this.f3809c)) {
                i7 = 1;
            }
            if (i7 == 1) {
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else {
                int absoluteGravity = Gravity.getAbsoluteGravity(this.f3823j, b7 ? 1 : 0) & 7;
                alignment = absoluteGravity != 1 ? absoluteGravity != 5 ? this.I ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL : this.I ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_CENTER;
            }
            z zVar = new z(this.G, textPaint, (int) width);
            zVar.f3919l = this.F;
            zVar.f3918k = b7;
            zVar.f3912e = alignment;
            zVar.f3917j = false;
            zVar.f3913f = i7;
            float f13 = this.f3834o0;
            float f14 = this.f3836p0;
            zVar.f3914g = f13;
            zVar.f3915h = f14;
            zVar.f3916i = this.f3838q0;
            StaticLayout a7 = zVar.a();
            a7.getClass();
            this.f3822i0 = a7;
            this.H = a7.getText();
        }
    }

    public final void d(Canvas canvas) {
        int save = canvas.save();
        if (this.H != null) {
            RectF rectF = this.f3821i;
            if (rectF.width() <= CropImageView.DEFAULT_ASPECT_RATIO || rectF.height() <= CropImageView.DEFAULT_ASPECT_RATIO) {
                return;
            }
            TextPaint textPaint = this.T;
            textPaint.setTextSize(this.M);
            float f7 = this.f3842u;
            float f8 = this.f3843v;
            float f9 = this.L;
            if (f9 != 1.0f && !this.f3809c) {
                canvas.scale(f9, f9, f7, f8);
            }
            if (this.f3832n0 <= 1 || ((this.I && !this.f3809c) || (this.f3809c && this.f3807b <= this.f3813e))) {
                canvas.translate(f7, f8);
                this.f3822i0.draw(canvas);
            } else {
                float lineStart = this.f3842u - this.f3822i0.getLineStart(0);
                int alpha = textPaint.getAlpha();
                canvas.translate(lineStart, f8);
                if (!this.f3809c) {
                    textPaint.setAlpha((int) (this.f3828l0 * alpha));
                    if (Build.VERSION.SDK_INT >= 31) {
                        textPaint.setShadowLayer(this.N, this.O, this.P, b3.a.a(this.Q, textPaint.getAlpha()));
                    }
                    this.f3822i0.draw(canvas);
                }
                if (!this.f3809c) {
                    textPaint.setAlpha((int) (this.f3826k0 * alpha));
                }
                int i7 = Build.VERSION.SDK_INT;
                if (i7 >= 31) {
                    textPaint.setShadowLayer(this.N, this.O, this.P, b3.a.a(this.Q, textPaint.getAlpha()));
                }
                int lineBaseline = this.f3822i0.getLineBaseline(0);
                CharSequence charSequence = this.f3830m0;
                float f10 = lineBaseline;
                canvas.drawText(charSequence, 0, charSequence.length(), CropImageView.DEFAULT_ASPECT_RATIO, f10, textPaint);
                if (i7 >= 31) {
                    textPaint.setShadowLayer(this.N, this.O, this.P, this.Q);
                }
                if (!this.f3809c) {
                    String trim = this.f3830m0.toString().trim();
                    if (trim.endsWith("…")) {
                        trim = trim.substring(0, trim.length() - 1);
                    }
                    String str = trim;
                    textPaint.setAlpha(alpha);
                    canvas.drawText(str, 0, Math.min(this.f3822i0.getLineEnd(0), str.length()), CropImageView.DEFAULT_ASPECT_RATIO, f10, (Paint) textPaint);
                }
            }
            canvas.restoreToCount(save);
        }
    }

    public final float e() {
        TextPaint textPaint = this.U;
        textPaint.setTextSize(this.f3829m);
        textPaint.setTypeface(this.f3844w);
        textPaint.setLetterSpacing(this.f3816f0);
        return -textPaint.ascent();
    }

    public final int f(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.R;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    public final void h(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 31) {
            Typeface typeface = this.f3846y;
            if (typeface != null) {
                this.f3845x = l6.f.U(configuration, typeface);
            }
            Typeface typeface2 = this.B;
            if (typeface2 != null) {
                this.A = l6.f.U(configuration, typeface2);
            }
            Typeface typeface3 = this.f3845x;
            if (typeface3 == null) {
                typeface3 = this.f3846y;
            }
            this.f3844w = typeface3;
            Typeface typeface4 = this.A;
            if (typeface4 == null) {
                typeface4 = this.B;
            }
            this.f3847z = typeface4;
            i(true);
        }
    }

    public final void i(boolean z6) {
        float measureText;
        float f7;
        StaticLayout staticLayout;
        View view = this.f3805a;
        if ((view.getHeight() <= 0 || view.getWidth() <= 0) && !z6) {
            return;
        }
        c(1.0f, z6);
        CharSequence charSequence = this.H;
        TextPaint textPaint = this.T;
        if (charSequence != null && (staticLayout = this.f3822i0) != null) {
            this.f3830m0 = TextUtils.ellipsize(charSequence, textPaint, staticLayout.getWidth(), this.F);
        }
        CharSequence charSequence2 = this.f3830m0;
        if (charSequence2 != null) {
            this.f3824j0 = textPaint.measureText(charSequence2, 0, charSequence2.length());
        } else {
            this.f3824j0 = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(this.f3825k, this.I ? 1 : 0);
        int i7 = absoluteGravity & z1.c.INVOKE_DIRECT;
        Rect rect = this.f3819h;
        if (i7 == 48) {
            this.f3839r = rect.top;
        } else if (i7 != 80) {
            this.f3839r = rect.centerY() - ((textPaint.descent() - textPaint.ascent()) / 2.0f);
        } else {
            this.f3839r = textPaint.ascent() + rect.bottom;
        }
        int i8 = absoluteGravity & 8388615;
        if (i8 == 1) {
            this.f3841t = rect.centerX() - (this.f3824j0 / 2.0f);
        } else if (i8 != 5) {
            this.f3841t = rect.left;
        } else {
            this.f3841t = rect.right - this.f3824j0;
        }
        c(CropImageView.DEFAULT_ASPECT_RATIO, z6);
        float height = this.f3822i0 != null ? r1.getHeight() : 0.0f;
        StaticLayout staticLayout2 = this.f3822i0;
        if (staticLayout2 == null || this.f3832n0 <= 1) {
            CharSequence charSequence3 = this.H;
            measureText = charSequence3 != null ? textPaint.measureText(charSequence3, 0, charSequence3.length()) : 0.0f;
        } else {
            measureText = staticLayout2.getWidth();
        }
        StaticLayout staticLayout3 = this.f3822i0;
        this.f3835p = staticLayout3 != null ? staticLayout3.getLineCount() : 0;
        int absoluteGravity2 = Gravity.getAbsoluteGravity(this.f3823j, this.I ? 1 : 0);
        int i9 = absoluteGravity2 & z1.c.INVOKE_DIRECT;
        Rect rect2 = this.f3817g;
        if (i9 == 48) {
            this.f3837q = rect2.top;
        } else if (i9 != 80) {
            this.f3837q = rect2.centerY() - (height / 2.0f);
        } else {
            this.f3837q = textPaint.descent() + (rect2.bottom - height);
        }
        int i10 = absoluteGravity2 & 8388615;
        if (i10 == 1) {
            this.f3840s = rect2.centerX() - (measureText / 2.0f);
        } else if (i10 != 5) {
            this.f3840s = rect2.left;
        } else {
            this.f3840s = rect2.right - measureText;
        }
        Bitmap bitmap = this.K;
        if (bitmap != null) {
            bitmap.recycle();
            this.K = null;
        }
        q(this.f3807b);
        float f8 = this.f3807b;
        boolean z7 = this.f3809c;
        RectF rectF = this.f3821i;
        if (z7) {
            if (f8 < this.f3813e) {
                rect = rect2;
            }
            rectF.set(rect);
        } else {
            rectF.left = g(rect2.left, rect.left, f8, this.V);
            rectF.top = g(this.f3837q, this.f3839r, f8, this.V);
            rectF.right = g(rect2.right, rect.right, f8, this.V);
            rectF.bottom = g(rect2.bottom, rect.bottom, f8, this.V);
        }
        if (!this.f3809c) {
            this.f3842u = g(this.f3840s, this.f3841t, f8, this.V);
            this.f3843v = g(this.f3837q, this.f3839r, f8, this.V);
            q(f8);
            f7 = f8;
        } else if (f8 < this.f3813e) {
            this.f3842u = this.f3840s;
            this.f3843v = this.f3837q;
            q(CropImageView.DEFAULT_ASPECT_RATIO);
            f7 = 0.0f;
        } else {
            this.f3842u = this.f3841t;
            this.f3843v = this.f3839r - Math.max(0, this.f3815f);
            q(1.0f);
            f7 = 1.0f;
        }
        TimeInterpolator timeInterpolator = r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR;
        this.f3826k0 = 1.0f - g(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f - f8, timeInterpolator);
        int i11 = d1.OVER_SCROLL_ALWAYS;
        p0.l0.k(view);
        this.f3828l0 = g(1.0f, CropImageView.DEFAULT_ASPECT_RATIO, f8, timeInterpolator);
        p0.l0.k(view);
        ColorStateList colorStateList = this.f3833o;
        ColorStateList colorStateList2 = this.f3831n;
        if (colorStateList != colorStateList2) {
            textPaint.setColor(a(f(colorStateList2), f7, f(this.f3833o)));
        } else {
            textPaint.setColor(f(colorStateList));
        }
        float f9 = this.f3816f0;
        float f10 = this.f3818g0;
        if (f9 != f10) {
            textPaint.setLetterSpacing(g(f10, f9, f8, timeInterpolator));
        } else {
            textPaint.setLetterSpacing(f9);
        }
        this.N = g(this.f3808b0, this.X, f8, null);
        this.O = g(this.f3810c0, this.Y, f8, null);
        this.P = g(this.f3812d0, this.Z, f8, null);
        int a7 = a(f(this.f3814e0), f8, f(this.f3806a0));
        this.Q = a7;
        textPaint.setShadowLayer(this.N, this.O, this.P, a7);
        if (this.f3809c) {
            int alpha = textPaint.getAlpha();
            float f11 = this.f3813e;
            textPaint.setAlpha((int) ((f8 <= f11 ? r2.a.b(1.0f, CropImageView.DEFAULT_ASPECT_RATIO, this.f3811d, f11, f8) : r2.a.b(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, f11, 1.0f, f8)) * alpha));
        }
        p0.l0.k(view);
    }

    public final void j(ColorStateList colorStateList) {
        if (this.f3833o == colorStateList && this.f3831n == colorStateList) {
            return;
        }
        this.f3833o = colorStateList;
        this.f3831n = colorStateList;
        i(false);
    }

    public final void k(int i7) {
        View view = this.f3805a;
        m3.e eVar = new m3.e(view.getContext(), i7);
        ColorStateList colorStateList = eVar.f7546j;
        if (colorStateList != null) {
            this.f3833o = colorStateList;
        }
        float f7 = eVar.f7547k;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.f3829m = f7;
        }
        ColorStateList colorStateList2 = eVar.f7537a;
        if (colorStateList2 != null) {
            this.f3806a0 = colorStateList2;
        }
        this.Y = eVar.f7541e;
        this.Z = eVar.f7542f;
        this.X = eVar.f7543g;
        this.f3816f0 = eVar.f7545i;
        m3.b bVar = this.E;
        if (bVar != null) {
            bVar.f7530f = true;
        }
        c cVar = new c(this, 0);
        eVar.a();
        this.E = new m3.b(cVar, eVar.f7550n);
        eVar.c(view.getContext(), this.E);
        i(false);
    }

    public final void l(int i7) {
        if (this.f3825k != i7) {
            this.f3825k = i7;
            i(false);
        }
    }

    public final boolean m(Typeface typeface) {
        m3.b bVar = this.E;
        if (bVar != null) {
            bVar.f7530f = true;
        }
        if (this.f3846y == typeface) {
            return false;
        }
        this.f3846y = typeface;
        Typeface U = l6.f.U(this.f3805a.getContext().getResources().getConfiguration(), typeface);
        this.f3845x = U;
        if (U == null) {
            U = this.f3846y;
        }
        this.f3844w = U;
        return true;
    }

    public final void n(int i7) {
        View view = this.f3805a;
        m3.e eVar = new m3.e(view.getContext(), i7);
        ColorStateList colorStateList = eVar.f7546j;
        if (colorStateList != null) {
            this.f3831n = colorStateList;
        }
        float f7 = eVar.f7547k;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.f3827l = f7;
        }
        ColorStateList colorStateList2 = eVar.f7537a;
        if (colorStateList2 != null) {
            this.f3814e0 = colorStateList2;
        }
        this.f3810c0 = eVar.f7541e;
        this.f3812d0 = eVar.f7542f;
        this.f3808b0 = eVar.f7543g;
        this.f3818g0 = eVar.f7545i;
        m3.b bVar = this.D;
        if (bVar != null) {
            bVar.f7530f = true;
        }
        c cVar = new c(this, 1);
        eVar.a();
        this.D = new m3.b(cVar, eVar.f7550n);
        eVar.c(view.getContext(), this.D);
        i(false);
    }

    public final boolean o(Typeface typeface) {
        m3.b bVar = this.D;
        if (bVar != null) {
            bVar.f7530f = true;
        }
        if (this.B == typeface) {
            return false;
        }
        this.B = typeface;
        Typeface U = l6.f.U(this.f3805a.getContext().getResources().getConfiguration(), typeface);
        this.A = U;
        if (U == null) {
            U = this.B;
        }
        this.f3847z = U;
        return true;
    }

    public final void p(float f7) {
        float f8;
        float x6 = n1.z.x(f7, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        if (x6 != this.f3807b) {
            this.f3807b = x6;
            boolean z6 = this.f3809c;
            RectF rectF = this.f3821i;
            Rect rect = this.f3819h;
            Rect rect2 = this.f3817g;
            if (z6) {
                if (x6 < this.f3813e) {
                    rect = rect2;
                }
                rectF.set(rect);
            } else {
                rectF.left = g(rect2.left, rect.left, x6, this.V);
                rectF.top = g(this.f3837q, this.f3839r, x6, this.V);
                rectF.right = g(rect2.right, rect.right, x6, this.V);
                rectF.bottom = g(rect2.bottom, rect.bottom, x6, this.V);
            }
            if (!this.f3809c) {
                this.f3842u = g(this.f3840s, this.f3841t, x6, this.V);
                this.f3843v = g(this.f3837q, this.f3839r, x6, this.V);
                q(x6);
                f8 = x6;
            } else if (x6 < this.f3813e) {
                this.f3842u = this.f3840s;
                this.f3843v = this.f3837q;
                q(CropImageView.DEFAULT_ASPECT_RATIO);
                f8 = 0.0f;
            } else {
                this.f3842u = this.f3841t;
                this.f3843v = this.f3839r - Math.max(0, this.f3815f);
                q(1.0f);
                f8 = 1.0f;
            }
            TimeInterpolator timeInterpolator = r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR;
            this.f3826k0 = 1.0f - g(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f - x6, timeInterpolator);
            int i7 = d1.OVER_SCROLL_ALWAYS;
            View view = this.f3805a;
            p0.l0.k(view);
            this.f3828l0 = g(1.0f, CropImageView.DEFAULT_ASPECT_RATIO, x6, timeInterpolator);
            p0.l0.k(view);
            ColorStateList colorStateList = this.f3833o;
            ColorStateList colorStateList2 = this.f3831n;
            TextPaint textPaint = this.T;
            if (colorStateList != colorStateList2) {
                textPaint.setColor(a(f(colorStateList2), f8, f(this.f3833o)));
            } else {
                textPaint.setColor(f(colorStateList));
            }
            float f9 = this.f3816f0;
            float f10 = this.f3818g0;
            if (f9 != f10) {
                textPaint.setLetterSpacing(g(f10, f9, x6, timeInterpolator));
            } else {
                textPaint.setLetterSpacing(f9);
            }
            this.N = g(this.f3808b0, this.X, x6, null);
            this.O = g(this.f3810c0, this.Y, x6, null);
            this.P = g(this.f3812d0, this.Z, x6, null);
            int a7 = a(f(this.f3814e0), x6, f(this.f3806a0));
            this.Q = a7;
            textPaint.setShadowLayer(this.N, this.O, this.P, a7);
            if (this.f3809c) {
                int alpha = textPaint.getAlpha();
                float f11 = this.f3813e;
                textPaint.setAlpha((int) ((x6 <= f11 ? r2.a.b(1.0f, CropImageView.DEFAULT_ASPECT_RATIO, this.f3811d, f11, x6) : r2.a.b(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, f11, 1.0f, x6)) * alpha));
            }
            p0.l0.k(view);
        }
    }

    public final void q(float f7) {
        c(f7, false);
        int i7 = d1.OVER_SCROLL_ALWAYS;
        p0.l0.k(this.f3805a);
    }
}
