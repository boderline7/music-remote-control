package b3;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import g0.d;
import g0.g;
import l6.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;

    public static int a(int i7, int i8) {
        return i0.a.e(i7, (Color.alpha(i7) * i8) / 255);
    }

    public static int b(int i7, View view) {
        Context context = view.getContext();
        TypedValue w02 = f.w0(view.getContext(), i7, view.getClass().getCanonicalName());
        int i8 = w02.resourceId;
        if (i8 == 0) {
            return w02.data;
        }
        Object obj = g.f6423a;
        return d.a(context, i8);
    }

    public static int c(Context context, int i7, int i8) {
        Integer num;
        int i9;
        TypedValue s02 = f.s0(context, i7);
        if (s02 != null) {
            int i10 = s02.resourceId;
            if (i10 != 0) {
                Object obj = g.f6423a;
                i9 = d.a(context, i10);
            } else {
                i9 = s02.data;
            }
            num = Integer.valueOf(i9);
        } else {
            num = null;
        }
        return num != null ? num.intValue() : i8;
    }

    public static int d(Context context, int i7, String str) {
        TypedValue w02 = f.w0(context, i7, str);
        int i8 = w02.resourceId;
        if (i8 == 0) {
            return w02.data;
        }
        Object obj = g.f6423a;
        return d.a(context, i8);
    }

    public static boolean e(int i7) {
        boolean z6;
        if (i7 != 0) {
            ThreadLocal threadLocal = i0.a.f6772a;
            double[] dArr = (double[]) threadLocal.get();
            if (dArr == null) {
                dArr = new double[3];
                threadLocal.set(dArr);
            }
            int red = Color.red(i7);
            int green = Color.green(i7);
            int blue = Color.blue(i7);
            if (dArr.length != 3) {
                throw new IllegalArgumentException("outXyz must have a length of 3.");
            }
            double d7 = red / 255.0d;
            double pow = d7 < 0.04045d ? d7 / 12.92d : Math.pow((d7 + 0.055d) / 1.055d, 2.4d);
            double d8 = green / 255.0d;
            double pow2 = d8 < 0.04045d ? d8 / 12.92d : Math.pow((d8 + 0.055d) / 1.055d, 2.4d);
            double d9 = blue / 255.0d;
            double pow3 = d9 < 0.04045d ? d9 / 12.92d : Math.pow((d9 + 0.055d) / 1.055d, 2.4d);
            z6 = false;
            dArr[0] = ((0.1805d * pow3) + (0.3576d * pow2) + (0.4124d * pow)) * 100.0d;
            double d10 = ((0.0722d * pow3) + (0.7152d * pow2) + (0.2126d * pow)) * 100.0d;
            dArr[1] = d10;
            dArr[2] = ((pow3 * 0.9505d) + (pow2 * 0.1192d) + (pow * 0.0193d)) * 100.0d;
            if (d10 / 100.0d > 0.5d) {
                return true;
            }
        } else {
            z6 = false;
        }
        return z6;
    }

    public static int f(int i7, float f7, int i8) {
        return i0.a.c(i0.a.e(i8, Math.round(Color.alpha(i8) * f7)), i7);
    }
}
