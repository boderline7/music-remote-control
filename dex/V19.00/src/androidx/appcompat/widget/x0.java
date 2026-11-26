package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class x0 {

    /* renamed from: a, reason: collision with root package name */
    public final TextView f1465a;

    /* renamed from: b, reason: collision with root package name */
    public n3 f1466b;

    /* renamed from: c, reason: collision with root package name */
    public n3 f1467c;

    /* renamed from: d, reason: collision with root package name */
    public n3 f1468d;

    /* renamed from: e, reason: collision with root package name */
    public n3 f1469e;

    /* renamed from: f, reason: collision with root package name */
    public n3 f1470f;

    /* renamed from: g, reason: collision with root package name */
    public n3 f1471g;

    /* renamed from: h, reason: collision with root package name */
    public n3 f1472h;

    /* renamed from: i, reason: collision with root package name */
    public final g1 f1473i;

    /* renamed from: j, reason: collision with root package name */
    public int f1474j = 0;

    /* renamed from: k, reason: collision with root package name */
    public int f1475k = -1;

    /* renamed from: l, reason: collision with root package name */
    public Typeface f1476l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1477m;

    public x0(TextView textView) {
        this.f1465a = textView;
        this.f1473i = new g1(textView);
    }

    public static n3 c(Context context, z zVar, int i7) {
        ColorStateList h7;
        synchronized (zVar) {
            h7 = zVar.f1494a.h(context, i7);
        }
        if (h7 == null) {
            return null;
        }
        n3 n3Var = new n3(0);
        n3Var.f1362b = true;
        n3Var.f1363c = h7;
        return n3Var;
    }

    public static void h(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 30 || inputConnection == null) {
            return;
        }
        CharSequence text = textView.getText();
        if (i7 >= 30) {
            s0.a.a(editorInfo, text);
            return;
        }
        text.getClass();
        if (i7 >= 30) {
            s0.a.a(editorInfo, text);
            return;
        }
        int i8 = editorInfo.initialSelStart;
        int i9 = editorInfo.initialSelEnd;
        int i10 = i8 > i9 ? i9 : i8;
        if (i8 <= i9) {
            i8 = i9;
        }
        int length = text.length();
        if (i10 < 0 || i8 > length) {
            s0.b.a(editorInfo, null, 0, 0);
            return;
        }
        int i11 = editorInfo.inputType & 4095;
        if (i11 == 129 || i11 == 225 || i11 == 18) {
            s0.b.a(editorInfo, null, 0, 0);
            return;
        }
        if (length <= 2048) {
            s0.b.a(editorInfo, text, i10, i8);
            return;
        }
        int i12 = i8 - i10;
        int i13 = i12 > 1024 ? 0 : i12;
        int i14 = 2048 - i13;
        int min = Math.min(text.length() - i8, i14 - Math.min(i10, (int) (i14 * 0.8d)));
        int min2 = Math.min(i10, i14 - min);
        int i15 = i10 - min2;
        if (Character.isLowSurrogate(text.charAt(i15))) {
            i15++;
            min2--;
        }
        if (Character.isHighSurrogate(text.charAt((i8 + min) - 1))) {
            min--;
        }
        int i16 = min2 + i13;
        s0.b.a(editorInfo, i13 != i12 ? TextUtils.concat(text.subSequence(i15, i15 + min2), text.subSequence(i8, min + i8)) : text.subSequence(i15, i16 + min + i15), min2, i16);
    }

    public final void a(Drawable drawable, n3 n3Var) {
        if (drawable == null || n3Var == null) {
            return;
        }
        z.e(drawable, n3Var, this.f1465a.getDrawableState());
    }

    public final void b() {
        n3 n3Var = this.f1466b;
        TextView textView = this.f1465a;
        if (n3Var != null || this.f1467c != null || this.f1468d != null || this.f1469e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.f1466b);
            a(compoundDrawables[1], this.f1467c);
            a(compoundDrawables[2], this.f1468d);
            a(compoundDrawables[3], this.f1469e);
        }
        if (this.f1470f == null && this.f1471g == null) {
            return;
        }
        Drawable[] a7 = t0.a(textView);
        a(a7[0], this.f1470f);
        a(a7[2], this.f1471g);
    }

    public final ColorStateList d() {
        n3 n3Var = this.f1472h;
        if (n3Var != null) {
            return (ColorStateList) n3Var.f1363c;
        }
        return null;
    }

    public final PorterDuff.Mode e() {
        n3 n3Var = this.f1472h;
        if (n3Var != null) {
            return (PorterDuff.Mode) n3Var.f1364d;
        }
        return null;
    }

    public final void f(AttributeSet attributeSet, int i7) {
        boolean z6;
        boolean z7;
        String str;
        String str2;
        int i8;
        int resourceId;
        TextView textView = this.f1465a;
        Context context = textView.getContext();
        z a7 = z.a();
        androidx.appcompat.app.g S = androidx.appcompat.app.g.S(context, attributeSet, f.j.AppCompatTextHelper, i7);
        p0.d1.n(textView, textView.getContext(), f.j.AppCompatTextHelper, attributeSet, (TypedArray) S.f715c, i7);
        int N = S.N(f.j.AppCompatTextHelper_android_textAppearance, -1);
        if (S.R(f.j.AppCompatTextHelper_android_drawableLeft)) {
            this.f1466b = c(context, a7, S.N(f.j.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (S.R(f.j.AppCompatTextHelper_android_drawableTop)) {
            this.f1467c = c(context, a7, S.N(f.j.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (S.R(f.j.AppCompatTextHelper_android_drawableRight)) {
            this.f1468d = c(context, a7, S.N(f.j.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (S.R(f.j.AppCompatTextHelper_android_drawableBottom)) {
            this.f1469e = c(context, a7, S.N(f.j.AppCompatTextHelper_android_drawableBottom, 0));
        }
        int i9 = Build.VERSION.SDK_INT;
        if (S.R(f.j.AppCompatTextHelper_android_drawableStart)) {
            this.f1470f = c(context, a7, S.N(f.j.AppCompatTextHelper_android_drawableStart, 0));
        }
        if (S.R(f.j.AppCompatTextHelper_android_drawableEnd)) {
            this.f1471g = c(context, a7, S.N(f.j.AppCompatTextHelper_android_drawableEnd, 0));
        }
        S.V();
        boolean z8 = textView.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (N != -1) {
            androidx.appcompat.app.g gVar = new androidx.appcompat.app.g(context, context.obtainStyledAttributes(N, f.j.TextAppearance));
            if (z8 || !gVar.R(f.j.TextAppearance_textAllCaps)) {
                z6 = false;
                z7 = false;
            } else {
                z6 = gVar.E(f.j.TextAppearance_textAllCaps, false);
                z7 = true;
            }
            n(context, gVar);
            str = gVar.R(f.j.TextAppearance_textLocale) ? gVar.P(f.j.TextAppearance_textLocale) : null;
            str2 = gVar.R(f.j.TextAppearance_fontVariationSettings) ? gVar.P(f.j.TextAppearance_fontVariationSettings) : null;
            gVar.V();
        } else {
            z6 = false;
            z7 = false;
            str = null;
            str2 = null;
        }
        androidx.appcompat.app.g gVar2 = new androidx.appcompat.app.g(context, context.obtainStyledAttributes(attributeSet, f.j.TextAppearance, i7, 0));
        if (!z8 && gVar2.R(f.j.TextAppearance_textAllCaps)) {
            z6 = gVar2.E(f.j.TextAppearance_textAllCaps, false);
            z7 = true;
        }
        if (gVar2.R(f.j.TextAppearance_textLocale)) {
            str = gVar2.P(f.j.TextAppearance_textLocale);
        }
        if (gVar2.R(f.j.TextAppearance_fontVariationSettings)) {
            str2 = gVar2.P(f.j.TextAppearance_fontVariationSettings);
        }
        if (i9 >= 28 && gVar2.R(f.j.TextAppearance_android_textSize) && gVar2.H(f.j.TextAppearance_android_textSize, -1) == 0) {
            textView.setTextSize(0, CropImageView.DEFAULT_ASPECT_RATIO);
        }
        n(context, gVar2);
        gVar2.V();
        if (!z8 && z7) {
            textView.setAllCaps(z6);
        }
        Typeface typeface = this.f1476l;
        if (typeface != null) {
            if (this.f1475k == -1) {
                textView.setTypeface(typeface, this.f1474j);
            } else {
                textView.setTypeface(typeface);
            }
        }
        if (str2 != null) {
            v0.d(textView, str2);
        }
        if (str != null) {
            u0.b(textView, u0.a(str));
        }
        int[] iArr = f.j.AppCompatTextView;
        g1 g1Var = this.f1473i;
        Context context2 = g1Var.f1262j;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i7, 0);
        TextView textView2 = g1Var.f1261i;
        p0.d1.n(textView2, textView2.getContext(), f.j.AppCompatTextView, attributeSet, obtainStyledAttributes, i7);
        if (obtainStyledAttributes.hasValue(f.j.AppCompatTextView_autoSizeTextType)) {
            g1Var.f1253a = obtainStyledAttributes.getInt(f.j.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = obtainStyledAttributes.hasValue(f.j.AppCompatTextView_autoSizeStepGranularity) ? obtainStyledAttributes.getDimension(f.j.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes.hasValue(f.j.AppCompatTextView_autoSizeMinTextSize) ? obtainStyledAttributes.getDimension(f.j.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = obtainStyledAttributes.hasValue(f.j.AppCompatTextView_autoSizeMaxTextSize) ? obtainStyledAttributes.getDimension(f.j.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (obtainStyledAttributes.hasValue(f.j.AppCompatTextView_autoSizePresetSizes) && (resourceId = obtainStyledAttributes.getResourceId(f.j.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            int length = obtainTypedArray.length();
            int[] iArr2 = new int[length];
            if (length > 0) {
                for (int i10 = 0; i10 < length; i10++) {
                    iArr2[i10] = obtainTypedArray.getDimensionPixelSize(i10, -1);
                }
                g1Var.f1258f = g1.b(iArr2);
                g1Var.i();
            }
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (!g1Var.j()) {
            g1Var.f1253a = 0;
        } else if (g1Var.f1253a == 1) {
            if (!g1Var.f1259g) {
                DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                g1Var.k(dimension2, dimension3, dimension);
            }
            g1Var.h();
        }
        if (a4.f1189b && g1Var.f1253a != 0) {
            int[] iArr3 = g1Var.f1258f;
            if (iArr3.length > 0) {
                if (v0.a(textView) != -1.0f) {
                    v0.b(textView, Math.round(g1Var.f1256d), Math.round(g1Var.f1257e), Math.round(g1Var.f1255c), 0);
                } else {
                    v0.c(textView, iArr3, 0);
                }
            }
        }
        androidx.appcompat.app.g gVar3 = new androidx.appcompat.app.g(context, context.obtainStyledAttributes(attributeSet, f.j.AppCompatTextView));
        int N2 = gVar3.N(f.j.AppCompatTextView_drawableLeftCompat, -1);
        Drawable b7 = N2 != -1 ? a7.b(context, N2) : null;
        int N3 = gVar3.N(f.j.AppCompatTextView_drawableTopCompat, -1);
        Drawable b8 = N3 != -1 ? a7.b(context, N3) : null;
        int N4 = gVar3.N(f.j.AppCompatTextView_drawableRightCompat, -1);
        Drawable b9 = N4 != -1 ? a7.b(context, N4) : null;
        int N5 = gVar3.N(f.j.AppCompatTextView_drawableBottomCompat, -1);
        Drawable b10 = N5 != -1 ? a7.b(context, N5) : null;
        int N6 = gVar3.N(f.j.AppCompatTextView_drawableStartCompat, -1);
        Drawable b11 = N6 != -1 ? a7.b(context, N6) : null;
        int N7 = gVar3.N(f.j.AppCompatTextView_drawableEndCompat, -1);
        Drawable b12 = N7 != -1 ? a7.b(context, N7) : null;
        if (b11 != null || b12 != null) {
            Drawable[] a8 = t0.a(textView);
            if (b11 == null) {
                b11 = a8[0];
            }
            if (b8 == null) {
                b8 = a8[1];
            }
            if (b12 == null) {
                b12 = a8[2];
            }
            if (b10 == null) {
                b10 = a8[3];
            }
            t0.b(textView, b11, b8, b12, b10);
        } else if (b7 != null || b8 != null || b9 != null || b10 != null) {
            Drawable[] a9 = t0.a(textView);
            Drawable drawable = a9[0];
            if (drawable == null && a9[2] == null) {
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
                if (b7 == null) {
                    b7 = compoundDrawables[0];
                }
                if (b8 == null) {
                    b8 = compoundDrawables[1];
                }
                if (b9 == null) {
                    b9 = compoundDrawables[2];
                }
                if (b10 == null) {
                    b10 = compoundDrawables[3];
                }
                textView.setCompoundDrawablesWithIntrinsicBounds(b7, b8, b9, b10);
            } else {
                if (b8 == null) {
                    b8 = a9[1];
                }
                Drawable drawable2 = a9[2];
                if (b10 == null) {
                    b10 = a9[3];
                }
                t0.b(textView, drawable, b8, drawable2, b10);
            }
        }
        if (gVar3.R(f.j.AppCompatTextView_drawableTint)) {
            androidx.core.widget.s.f(textView, gVar3.F(f.j.AppCompatTextView_drawableTint));
        }
        if (gVar3.R(f.j.AppCompatTextView_drawableTintMode)) {
            i8 = -1;
            androidx.core.widget.s.g(textView, m1.c(gVar3.L(f.j.AppCompatTextView_drawableTintMode, -1), null));
        } else {
            i8 = -1;
        }
        int H = gVar3.H(f.j.AppCompatTextView_firstBaselineToTopHeight, i8);
        int H2 = gVar3.H(f.j.AppCompatTextView_lastBaselineToBottomHeight, i8);
        int H3 = gVar3.H(f.j.AppCompatTextView_lineHeight, i8);
        gVar3.V();
        if (H != i8) {
            androidx.core.widget.w.b(textView, H);
        }
        if (H2 != i8) {
            androidx.core.widget.w.c(textView, H2);
        }
        if (H3 != i8) {
            n1.z.u(H3);
            if (H3 != textView.getPaint().getFontMetricsInt(null)) {
                textView.setLineSpacing(H3 - r1, 1.0f);
            }
        }
    }

    public final void g(Context context, int i7) {
        String P;
        androidx.appcompat.app.g gVar = new androidx.appcompat.app.g(context, context.obtainStyledAttributes(i7, f.j.TextAppearance));
        boolean R = gVar.R(f.j.TextAppearance_textAllCaps);
        TextView textView = this.f1465a;
        if (R) {
            textView.setAllCaps(gVar.E(f.j.TextAppearance_textAllCaps, false));
        }
        if (gVar.R(f.j.TextAppearance_android_textSize) && gVar.H(f.j.TextAppearance_android_textSize, -1) == 0) {
            textView.setTextSize(0, CropImageView.DEFAULT_ASPECT_RATIO);
        }
        n(context, gVar);
        if (gVar.R(f.j.TextAppearance_fontVariationSettings) && (P = gVar.P(f.j.TextAppearance_fontVariationSettings)) != null) {
            v0.d(textView, P);
        }
        gVar.V();
        Typeface typeface = this.f1476l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.f1474j);
        }
    }

    public final void i(int i7, int i8, int i9, int i10) {
        g1 g1Var = this.f1473i;
        if (g1Var.j()) {
            DisplayMetrics displayMetrics = g1Var.f1262j.getResources().getDisplayMetrics();
            g1Var.k(TypedValue.applyDimension(i10, i7, displayMetrics), TypedValue.applyDimension(i10, i8, displayMetrics), TypedValue.applyDimension(i10, i9, displayMetrics));
            if (g1Var.h()) {
                g1Var.a();
            }
        }
    }

    public final void j(int[] iArr, int i7) {
        g1 g1Var = this.f1473i;
        if (g1Var.j()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i7 == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = g1Var.f1262j.getResources().getDisplayMetrics();
                    for (int i8 = 0; i8 < length; i8++) {
                        iArr2[i8] = Math.round(TypedValue.applyDimension(i7, iArr[i8], displayMetrics));
                    }
                }
                g1Var.f1258f = g1.b(iArr2);
                if (!g1Var.i()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                g1Var.f1259g = false;
            }
            if (g1Var.h()) {
                g1Var.a();
            }
        }
    }

    public final void k(int i7) {
        g1 g1Var = this.f1473i;
        if (g1Var.j()) {
            if (i7 == 0) {
                g1Var.f1253a = 0;
                g1Var.f1256d = -1.0f;
                g1Var.f1257e = -1.0f;
                g1Var.f1255c = -1.0f;
                g1Var.f1258f = new int[0];
                g1Var.f1254b = false;
                return;
            }
            if (i7 != 1) {
                throw new IllegalArgumentException(a.a.d("Unknown auto-size text type: ", i7));
            }
            DisplayMetrics displayMetrics = g1Var.f1262j.getResources().getDisplayMetrics();
            g1Var.k(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (g1Var.h()) {
                g1Var.a();
            }
        }
    }

    public final void l(ColorStateList colorStateList) {
        if (this.f1472h == null) {
            this.f1472h = new n3(0);
        }
        n3 n3Var = this.f1472h;
        n3Var.f1363c = colorStateList;
        n3Var.f1362b = colorStateList != null;
        this.f1466b = n3Var;
        this.f1467c = n3Var;
        this.f1468d = n3Var;
        this.f1469e = n3Var;
        this.f1470f = n3Var;
        this.f1471g = n3Var;
    }

    public final void m(PorterDuff.Mode mode) {
        if (this.f1472h == null) {
            this.f1472h = new n3(0);
        }
        n3 n3Var = this.f1472h;
        n3Var.f1364d = mode;
        n3Var.f1361a = mode != null;
        this.f1466b = n3Var;
        this.f1467c = n3Var;
        this.f1468d = n3Var;
        this.f1469e = n3Var;
        this.f1470f = n3Var;
        this.f1471g = n3Var;
    }

    public final void n(Context context, androidx.appcompat.app.g gVar) {
        String P;
        this.f1474j = gVar.L(f.j.TextAppearance_android_textStyle, this.f1474j);
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 28) {
            int L = gVar.L(f.j.TextAppearance_android_textFontWeight, -1);
            this.f1475k = L;
            if (L != -1) {
                this.f1474j &= 2;
            }
        }
        if (!gVar.R(f.j.TextAppearance_android_fontFamily) && !gVar.R(f.j.TextAppearance_fontFamily)) {
            if (gVar.R(f.j.TextAppearance_android_typeface)) {
                this.f1477m = false;
                int L2 = gVar.L(f.j.TextAppearance_android_typeface, 1);
                if (L2 == 1) {
                    this.f1476l = Typeface.SANS_SERIF;
                    return;
                } else if (L2 == 2) {
                    this.f1476l = Typeface.SERIF;
                    return;
                } else {
                    if (L2 != 3) {
                        return;
                    }
                    this.f1476l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.f1476l = null;
        int i8 = gVar.R(f.j.TextAppearance_fontFamily) ? f.j.TextAppearance_fontFamily : f.j.TextAppearance_android_fontFamily;
        int i9 = this.f1475k;
        int i10 = this.f1474j;
        if (!context.isRestricted()) {
            try {
                Typeface K = gVar.K(i8, this.f1474j, new r0(this, i9, i10, new WeakReference(this.f1465a)));
                if (K != null) {
                    if (i7 < 28 || this.f1475k == -1) {
                        this.f1476l = K;
                    } else {
                        this.f1476l = w0.a(Typeface.create(K, 0), this.f1475k, (this.f1474j & 2) != 0);
                    }
                }
                this.f1477m = this.f1476l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f1476l != null || (P = gVar.P(i8)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.f1475k == -1) {
            this.f1476l = Typeface.create(P, this.f1474j);
        } else {
            this.f1476l = w0.a(Typeface.create(P, 0), this.f1475k, (this.f1474j & 2) != 0);
        }
    }
}
