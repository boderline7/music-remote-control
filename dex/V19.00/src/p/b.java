package p;

import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    public static final double f8090a = Math.cos(Math.toRadians(45.0d));

    public static float a(float f7, float f8, boolean z6) {
        if (!z6) {
            return f7;
        }
        return (float) (((1.0d - f8090a) * f8) + f7);
    }

    public static float b(float f7, float f8, boolean z6) {
        if (!z6) {
            return f7 * 1.5f;
        }
        return (float) (((1.0d - f8090a) * f8) + (f7 * 1.5f));
    }
}
