package m3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.TypedValue;
import com.yalantis.ucrop.view.CropImageView;
import h0.q;
import l6.f;
import p0.d1;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public final ColorStateList f7537a;

    /* renamed from: b, reason: collision with root package name */
    public final String f7538b;

    /* renamed from: c, reason: collision with root package name */
    public final int f7539c;

    /* renamed from: d, reason: collision with root package name */
    public final int f7540d;

    /* renamed from: e, reason: collision with root package name */
    public final float f7541e;

    /* renamed from: f, reason: collision with root package name */
    public final float f7542f;

    /* renamed from: g, reason: collision with root package name */
    public final float f7543g;

    /* renamed from: h, reason: collision with root package name */
    public final boolean f7544h;

    /* renamed from: i, reason: collision with root package name */
    public final float f7545i;

    /* renamed from: j, reason: collision with root package name */
    public ColorStateList f7546j;

    /* renamed from: k, reason: collision with root package name */
    public float f7547k;

    /* renamed from: l, reason: collision with root package name */
    public final int f7548l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f7549m = false;

    /* renamed from: n, reason: collision with root package name */
    public Typeface f7550n;

    public e(Context context, int i7) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i7, m.TextAppearance);
        this.f7547k = obtainStyledAttributes.getDimension(m.TextAppearance_android_textSize, CropImageView.DEFAULT_ASPECT_RATIO);
        this.f7546j = f.w(context, obtainStyledAttributes, m.TextAppearance_android_textColor);
        f.w(context, obtainStyledAttributes, m.TextAppearance_android_textColorHint);
        f.w(context, obtainStyledAttributes, m.TextAppearance_android_textColorLink);
        this.f7539c = obtainStyledAttributes.getInt(m.TextAppearance_android_textStyle, 0);
        this.f7540d = obtainStyledAttributes.getInt(m.TextAppearance_android_typeface, 1);
        int i8 = m.TextAppearance_fontFamily;
        i8 = obtainStyledAttributes.hasValue(i8) ? i8 : m.TextAppearance_android_fontFamily;
        this.f7548l = obtainStyledAttributes.getResourceId(i8, 0);
        this.f7538b = obtainStyledAttributes.getString(i8);
        obtainStyledAttributes.getBoolean(m.TextAppearance_textAllCaps, false);
        this.f7537a = f.w(context, obtainStyledAttributes, m.TextAppearance_android_shadowColor);
        this.f7541e = obtainStyledAttributes.getFloat(m.TextAppearance_android_shadowDx, CropImageView.DEFAULT_ASPECT_RATIO);
        this.f7542f = obtainStyledAttributes.getFloat(m.TextAppearance_android_shadowDy, CropImageView.DEFAULT_ASPECT_RATIO);
        this.f7543g = obtainStyledAttributes.getFloat(m.TextAppearance_android_shadowRadius, CropImageView.DEFAULT_ASPECT_RATIO);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(i7, m.MaterialTextAppearance);
        this.f7544h = obtainStyledAttributes2.hasValue(m.MaterialTextAppearance_android_letterSpacing);
        this.f7545i = obtainStyledAttributes2.getFloat(m.MaterialTextAppearance_android_letterSpacing, CropImageView.DEFAULT_ASPECT_RATIO);
        obtainStyledAttributes2.recycle();
    }

    public final void a() {
        String str;
        Typeface typeface = this.f7550n;
        int i7 = this.f7539c;
        if (typeface == null && (str = this.f7538b) != null) {
            this.f7550n = Typeface.create(str, i7);
        }
        if (this.f7550n == null) {
            int i8 = this.f7540d;
            if (i8 == 1) {
                this.f7550n = Typeface.SANS_SERIF;
            } else if (i8 == 2) {
                this.f7550n = Typeface.SERIF;
            } else if (i8 != 3) {
                this.f7550n = Typeface.DEFAULT;
            } else {
                this.f7550n = Typeface.MONOSPACE;
            }
            this.f7550n = Typeface.create(this.f7550n, i7);
        }
    }

    public final Typeface b(Context context) {
        if (this.f7549m) {
            return this.f7550n;
        }
        if (!context.isRestricted()) {
            try {
                Typeface a7 = q.a(context, this.f7548l);
                this.f7550n = a7;
                if (a7 != null) {
                    this.f7550n = Typeface.create(a7, this.f7539c);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException | Exception unused) {
            }
        }
        a();
        this.f7549m = true;
        return this.f7550n;
    }

    public final void c(Context context, f fVar) {
        if (d(context)) {
            b(context);
        } else {
            a();
        }
        int i7 = this.f7548l;
        if (i7 == 0) {
            this.f7549m = true;
        }
        if (this.f7549m) {
            fVar.c0(this.f7550n, true);
            return;
        }
        try {
            c cVar = new c(this, fVar);
            ThreadLocal threadLocal = q.f6642a;
            if (context.isRestricted()) {
                cVar.h(-4);
            } else {
                q.b(context, i7, new TypedValue(), 0, cVar, false, false);
            }
        } catch (Resources.NotFoundException unused) {
            this.f7549m = true;
            fVar.a0(1);
        } catch (Exception unused2) {
            this.f7549m = true;
            fVar.a0(-3);
        }
    }

    public final boolean d(Context context) {
        int i7 = this.f7548l;
        Typeface typeface = null;
        if (i7 != 0) {
            ThreadLocal threadLocal = q.f6642a;
            if (!context.isRestricted()) {
                typeface = q.b(context, i7, new TypedValue(), 0, null, false, true);
            }
        }
        return typeface != null;
    }

    public final void e(Context context, TextPaint textPaint, f fVar) {
        f(context, textPaint, fVar);
        ColorStateList colorStateList = this.f7546j;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : d1.MEASURED_STATE_MASK);
        ColorStateList colorStateList2 = this.f7537a;
        textPaint.setShadowLayer(this.f7543g, this.f7541e, this.f7542f, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public final void f(Context context, TextPaint textPaint, f fVar) {
        if (d(context)) {
            g(context, textPaint, b(context));
            return;
        }
        a();
        g(context, textPaint, this.f7550n);
        c(context, new d(this, context, textPaint, fVar));
    }

    public final void g(Context context, TextPaint textPaint, Typeface typeface) {
        Typeface U = f.U(context.getResources().getConfiguration(), typeface);
        if (U != null) {
            typeface = U;
        }
        textPaint.setTypeface(typeface);
        int i7 = (~typeface.getStyle()) & this.f7539c;
        textPaint.setFakeBoldText((i7 & 1) != 0);
        textPaint.setTextSkewX((i7 & 2) != 0 ? -0.25f : CropImageView.DEFAULT_ASPECT_RATIO);
        textPaint.setTextSize(this.f7547k);
        if (this.f7544h) {
            textPaint.setLetterSpacing(this.f7545i);
        }
    }
}
