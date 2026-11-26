package h0;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r {

    /* renamed from: k, reason: collision with root package name */
    public static final r f6645k;

    /* renamed from: a, reason: collision with root package name */
    public final float f6646a;

    /* renamed from: b, reason: collision with root package name */
    public final float f6647b;

    /* renamed from: c, reason: collision with root package name */
    public final float f6648c;

    /* renamed from: d, reason: collision with root package name */
    public final float f6649d;

    /* renamed from: e, reason: collision with root package name */
    public final float f6650e;

    /* renamed from: f, reason: collision with root package name */
    public final float f6651f;

    /* renamed from: g, reason: collision with root package name */
    public final float[] f6652g;

    /* renamed from: h, reason: collision with root package name */
    public final float f6653h;

    /* renamed from: i, reason: collision with root package name */
    public final float f6654i;

    /* renamed from: j, reason: collision with root package name */
    public final float f6655j;

    static {
        float[] fArr = b.f6617c;
        float c7 = (float) ((b.c() * 63.66197723675813d) / 100.0d);
        float[][] fArr2 = b.f6615a;
        float f7 = fArr[0];
        float[] fArr3 = fArr2[0];
        float f8 = fArr3[0] * f7;
        float f9 = fArr[1];
        float f10 = (fArr3[1] * f9) + f8;
        float f11 = fArr[2];
        float f12 = (fArr3[2] * f11) + f10;
        float[] fArr4 = fArr2[1];
        float f13 = (fArr4[2] * f11) + (fArr4[1] * f9) + (fArr4[0] * f7);
        float[] fArr5 = fArr2[2];
        float f14 = (f11 * fArr5[2]) + (f9 * fArr5[1]) + (f7 * fArr5[0]);
        float f15 = ((double) 1.0f) >= 0.9d ? 0.69f : 0.655f;
        float exp = (1.0f - (((float) Math.exp(((-c7) - 42.0f) / 92.0f)) * 0.2777778f)) * 1.0f;
        double d7 = exp;
        if (d7 > 1.0d) {
            exp = 1.0f;
        } else if (d7 < 0.0d) {
            exp = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float[] fArr6 = {(((100.0f / f12) * exp) + 1.0f) - exp, (((100.0f / f13) * exp) + 1.0f) - exp, (((100.0f / f14) * exp) + 1.0f) - exp};
        float f16 = 1.0f / ((5.0f * c7) + 1.0f);
        float f17 = f16 * f16 * f16 * f16;
        float f18 = 1.0f - f17;
        float cbrt = (0.1f * f18 * f18 * ((float) Math.cbrt(c7 * 5.0d))) + (f17 * c7);
        float c8 = b.c() / fArr[1];
        double d8 = c8;
        float sqrt = ((float) Math.sqrt(d8)) + 1.48f;
        float pow = 0.725f / ((float) Math.pow(d8, 0.2d));
        float pow2 = (float) Math.pow(((fArr6[2] * cbrt) * f14) / 100.0d, 0.42d);
        float[] fArr7 = {(float) Math.pow(((fArr6[0] * cbrt) * f12) / 100.0d, 0.42d), (float) Math.pow(((fArr6[1] * cbrt) * f13) / 100.0d, 0.42d), pow2};
        float f19 = fArr7[0];
        float f20 = fArr7[1];
        f6645k = new r(c8, ((((400.0f * pow2) / (pow2 + 27.13f)) * 0.05f) + (((f19 * 400.0f) / (f19 + 27.13f)) * 2.0f) + ((f20 * 400.0f) / (f20 + 27.13f))) * pow, pow, pow, f15, 1.0f, fArr6, cbrt, (float) Math.pow(cbrt, 0.25d), sqrt);
    }

    public r(float f7, float f8, float f9, float f10, float f11, float f12, float[] fArr, float f13, float f14, float f15) {
        this.f6651f = f7;
        this.f6646a = f8;
        this.f6647b = f9;
        this.f6648c = f10;
        this.f6649d = f11;
        this.f6650e = f12;
        this.f6652g = fArr;
        this.f6653h = f13;
        this.f6654i = f14;
        this.f6655j = f15;
    }
}
