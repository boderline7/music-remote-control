package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class l3 {

    /* renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f1316a = new ThreadLocal();

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f1317b = {-16842910};

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f1318c = {R.attr.state_focused};

    /* renamed from: d, reason: collision with root package name */
    public static final int[] f1319d = {R.attr.state_pressed};

    /* renamed from: e, reason: collision with root package name */
    public static final int[] f1320e = {R.attr.state_checked};

    /* renamed from: f, reason: collision with root package name */
    public static final int[] f1321f = new int[0];

    /* renamed from: g, reason: collision with root package name */
    public static final int[] f1322g = new int[1];

    public static void a(View view, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f.j.AppCompatTheme);
        try {
            if (!obtainStyledAttributes.hasValue(f.j.AppCompatTheme_windowActionBar)) {
                view.getClass().toString();
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static int b(Context context, int i7) {
        ColorStateList d7 = d(context, i7);
        if (d7 != null && d7.isStateful()) {
            return d7.getColorForState(f1317b, d7.getDefaultColor());
        }
        ThreadLocal threadLocal = f1316a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValue, true);
        float f7 = typedValue.getFloat();
        return i0.a.e(c(context, i7), Math.round(Color.alpha(r4) * f7));
    }

    public static int c(Context context, int i7) {
        int[] iArr = f1322g;
        iArr[0] = i7;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, iArr);
        try {
            return obtainStyledAttributes.getColor(0, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static ColorStateList d(Context context, int i7) {
        ColorStateList colorStateList;
        int resourceId;
        int[] iArr = f1322g;
        iArr[0] = i7;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, iArr);
        try {
            if (!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0 || (colorStateList = g0.g.b(context, resourceId)) == null) {
                colorStateList = obtainStyledAttributes.getColorStateList(0);
            }
            return colorStateList;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
