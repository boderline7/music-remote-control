package r2;

import android.animation.TypeEvaluator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements TypeEvaluator {

    /* renamed from: a, reason: collision with root package name */
    public static final b f8508a = new b();

    public static Integer a(float f7, Integer num, Integer num2) {
        int intValue = num.intValue();
        float f8 = ((intValue >> 24) & 255) / 255.0f;
        int intValue2 = num2.intValue();
        float f9 = ((intValue2 >> 24) & 255) / 255.0f;
        float pow = (float) Math.pow(((intValue >> 16) & 255) / 255.0f, 2.2d);
        float pow2 = (float) Math.pow(((intValue >> 8) & 255) / 255.0f, 2.2d);
        float pow3 = (float) Math.pow((intValue & 255) / 255.0f, 2.2d);
        float pow4 = (float) Math.pow(((intValue2 >> 16) & 255) / 255.0f, 2.2d);
        float pow5 = (float) Math.pow(((intValue2 >> 8) & 255) / 255.0f, 2.2d);
        float pow6 = (float) Math.pow((intValue2 & 255) / 255.0f, 2.2d);
        float a7 = a.a.a(f9, f8, f7, f8);
        float a8 = a.a.a(pow4, pow, f7, pow);
        float a9 = a.a.a(pow5, pow2, f7, pow2);
        float a10 = a.a.a(pow6, pow3, f7, pow3);
        float pow7 = ((float) Math.pow(a8, 0.45454545454545453d)) * 255.0f;
        float pow8 = ((float) Math.pow(a9, 0.45454545454545453d)) * 255.0f;
        return Integer.valueOf(Math.round(((float) Math.pow(a10, 0.45454545454545453d)) * 255.0f) | (Math.round(pow7) << 16) | (Math.round(a7 * 255.0f) << 24) | (Math.round(pow8) << 8));
    }

    @Override // android.animation.TypeEvaluator
    public final /* bridge */ /* synthetic */ Object evaluate(float f7, Object obj, Object obj2) {
        return a(f7, (Integer) obj, (Integer) obj2);
    }
}
