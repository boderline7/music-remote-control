package r2;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static final TimeInterpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final TimeInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new c1.b();
    public static final TimeInterpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new c1.a();
    public static final TimeInterpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new c1.c();
    public static final TimeInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();

    public static float a(float f7, float f8, float f9) {
        return a.a.a(f8, f7, f9, f7);
    }

    public static float b(float f7, float f8, float f9, float f10, float f11) {
        return f11 <= f9 ? f7 : f11 >= f10 ? f8 : a(f7, f8, (f11 - f9) / (f10 - f9));
    }

    public static int c(int i7, float f7, int i8) {
        return Math.round(f7 * (i8 - i7)) + i7;
    }
}
