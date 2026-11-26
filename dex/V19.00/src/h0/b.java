package h0;

import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    public static final float[][] f6615a = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};

    /* renamed from: b, reason: collision with root package name */
    public static final float[][] f6616b = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};

    /* renamed from: c, reason: collision with root package name */
    public static final float[] f6617c = {95.047f, 100.0f, 108.883f};

    /* renamed from: d, reason: collision with root package name */
    public static final float[][] f6618d = {new float[]{0.41233894f, 0.35762063f, 0.18051042f}, new float[]{0.2126f, 0.7152f, 0.0722f}, new float[]{0.01932141f, 0.11916382f, 0.9503448f}};

    public static int a(float f7) {
        if (f7 < 1.0f) {
            return d1.MEASURED_STATE_MASK;
        }
        if (f7 > 99.0f) {
            return -1;
        }
        float f8 = (f7 + 16.0f) / 116.0f;
        float f9 = f7 > 8.0f ? f8 * f8 * f8 : f7 / 903.2963f;
        float f10 = f8 * f8 * f8;
        boolean z6 = f10 > 0.008856452f;
        float f11 = z6 ? f10 : ((f8 * 116.0f) - 16.0f) / 903.2963f;
        if (!z6) {
            f10 = ((f8 * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = f6617c;
        return i0.a.a(f11 * fArr[0], f9 * fArr[1], f10 * fArr[2]);
    }

    public static float b(int i7) {
        float f7 = i7 / 255.0f;
        return (f7 <= 0.04045f ? f7 / 12.92f : (float) Math.pow((f7 + 0.055f) / 1.055f, 2.4000000953674316d)) * 100.0f;
    }

    public static float c() {
        return ((float) Math.pow((50.0f + 16.0d) / 116.0d, 3.0d)) * 100.0f;
    }
}
