package h0;

import android.graphics.Color;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public float f6609a;

    /* renamed from: b, reason: collision with root package name */
    public float f6610b;

    /* renamed from: c, reason: collision with root package name */
    public float f6611c;

    /* renamed from: d, reason: collision with root package name */
    public float f6612d;

    /* renamed from: e, reason: collision with root package name */
    public float f6613e;

    /* renamed from: f, reason: collision with root package name */
    public float f6614f;

    public a(float f7, float f8, float f9, float f10, float f11, float f12) {
        this.f6609a = f7;
        this.f6610b = f8;
        this.f6611c = f9;
        this.f6612d = f10;
        this.f6613e = f11;
        this.f6614f = f12;
    }

    public static a b(int i7) {
        r rVar = r.f6645k;
        float b7 = b.b(Color.red(i7));
        float b8 = b.b(Color.green(i7));
        float b9 = b.b(Color.blue(i7));
        float[][] fArr = b.f6618d;
        float[] fArr2 = fArr[0];
        float f7 = (fArr2[2] * b9) + (fArr2[1] * b8) + (fArr2[0] * b7);
        float[] fArr3 = fArr[1];
        float f8 = (fArr3[2] * b9) + (fArr3[1] * b8) + (fArr3[0] * b7);
        float[] fArr4 = fArr[2];
        float f9 = (b9 * fArr4[2]) + (b8 * fArr4[1]) + (b7 * fArr4[0]);
        float[][] fArr5 = b.f6615a;
        float[] fArr6 = fArr5[0];
        float f10 = (fArr6[2] * f9) + (fArr6[1] * f8) + (fArr6[0] * f7);
        float[] fArr7 = fArr5[1];
        float f11 = (fArr7[2] * f9) + (fArr7[1] * f8) + (fArr7[0] * f7);
        float[] fArr8 = fArr5[2];
        float f12 = (f9 * fArr8[2]) + (f8 * fArr8[1]) + (f7 * fArr8[0]);
        float[] fArr9 = rVar.f6652g;
        float f13 = fArr9[0] * f10;
        float f14 = fArr9[1] * f11;
        float f15 = fArr9[2] * f12;
        float abs = Math.abs(f13);
        float f16 = rVar.f6653h;
        float pow = (float) Math.pow((abs * f16) / 100.0d, 0.42d);
        float pow2 = (float) Math.pow((Math.abs(f14) * f16) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow((Math.abs(f15) * f16) / 100.0d, 0.42d);
        float signum = ((Math.signum(f13) * 400.0f) * pow) / (pow + 27.13f);
        float signum2 = ((Math.signum(f14) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum3 = ((Math.signum(f15) * 400.0f) * pow3) / (pow3 + 27.13f);
        double d7 = signum3;
        float f17 = ((float) (((signum2 * (-12.0d)) + (signum * 11.0d)) + d7)) / 11.0f;
        float f18 = ((float) ((signum + signum2) - (d7 * 2.0d))) / 9.0f;
        float f19 = signum2 * 20.0f;
        float f20 = ((21.0f * signum3) + ((signum * 20.0f) + f19)) / 20.0f;
        float f21 = (((signum * 40.0f) + f19) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2(f18, f17)) * 180.0f) / 3.1415927f;
        if (atan2 < CropImageView.DEFAULT_ASPECT_RATIO) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f22 = atan2;
        float f23 = (3.1415927f * f22) / 180.0f;
        float f24 = f21 * rVar.f6647b;
        float f25 = rVar.f6646a;
        float f26 = rVar.f6649d;
        float pow4 = ((float) Math.pow(f24 / f25, rVar.f6655j * f26)) * 100.0f;
        Math.sqrt(pow4 / 100.0f);
        float f27 = f25 + 4.0f;
        float pow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, rVar.f6651f), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos((((((double) f22) < 20.14d ? 360.0f + f22 : f22) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * rVar.f6650e) * rVar.f6648c) * ((float) Math.sqrt((f18 * f18) + (f17 * f17)))) / (f20 + 0.305f), 0.9d)) * ((float) Math.sqrt(pow4 / 100.0d));
        float f28 = rVar.f6654i * pow5;
        Math.sqrt((r3 * f26) / f27);
        float f29 = (1.7f * pow4) / ((0.007f * pow4) + 1.0f);
        float log = ((float) Math.log((f28 * 0.0228f) + 1.0f)) * 43.85965f;
        double d8 = f23;
        return new a(f22, pow5, pow4, f29, log * ((float) Math.cos(d8)), log * ((float) Math.sin(d8)));
    }

    public static a c(float f7, float f8, float f9) {
        r rVar = r.f6645k;
        float f10 = rVar.f6649d;
        Math.sqrt(f7 / 100.0d);
        float f11 = rVar.f6646a + 4.0f;
        float f12 = rVar.f6654i * f8;
        Math.sqrt(((f8 / ((float) Math.sqrt(r1))) * rVar.f6649d) / f11);
        float f13 = (1.7f * f7) / ((0.007f * f7) + 1.0f);
        float log = ((float) Math.log((f12 * 0.0228d) + 1.0d)) * 43.85965f;
        double d7 = (3.1415927f * f9) / 180.0f;
        return new a(f9, f8, f7, f13, log * ((float) Math.cos(d7)), log * ((float) Math.sin(d7)));
    }

    public void a(float f7, float f8, int i7, int i8, float[] fArr) {
        float f9 = fArr[0];
        float f10 = fArr[1];
        float f11 = (f8 - 0.5f) * 2.0f;
        float f12 = f9 + this.f6611c;
        float f13 = f10 + this.f6612d;
        float f14 = (this.f6609a * (f7 - 0.5f) * 2.0f) + f12;
        float f15 = (this.f6610b * f11) + f13;
        float radians = (float) Math.toRadians(this.f6614f);
        float radians2 = (float) Math.toRadians(this.f6613e);
        double d7 = radians;
        double d8 = i8 * f11;
        float sin = (((float) ((Math.sin(d7) * ((-i7) * r7)) - (Math.cos(d7) * d8))) * radians2) + f14;
        float cos = (radians2 * ((float) ((Math.cos(d7) * (i7 * r7)) - (Math.sin(d7) * d8)))) + f15;
        fArr[0] = sin;
        fArr[1] = cos;
    }

    public int d(r rVar) {
        float f7;
        float f8 = this.f6610b;
        double d7 = f8;
        float f9 = this.f6611c;
        if (d7 != 0.0d) {
            double d8 = f9;
            if (d8 != 0.0d) {
                f7 = f8 / ((float) Math.sqrt(d8 / 100.0d));
                float pow = (float) Math.pow(f7 / Math.pow(1.64d - Math.pow(0.29d, rVar.f6651f), 0.73d), 1.1111111111111112d);
                double d9 = (this.f6609a * 3.1415927f) / 180.0f;
                float cos = ((float) (Math.cos(2.0d + d9) + 3.8d)) * 0.25f;
                float pow2 = rVar.f6646a * ((float) Math.pow(f9 / 100.0d, (1.0d / rVar.f6649d) / rVar.f6655j));
                float f10 = cos * 3846.1538f * rVar.f6650e * rVar.f6648c;
                float f11 = pow2 / rVar.f6647b;
                float sin = (float) Math.sin(d9);
                float cos2 = (float) Math.cos(d9);
                float A = (((0.305f + f11) * 23.0f) * pow) / (((pow * 108.0f) * sin) + a.a.A(pow, 11.0f, cos2, f10 * 23.0f));
                float f12 = cos2 * A;
                float f13 = A * sin;
                float f14 = f11 * 460.0f;
                float f15 = ((288.0f * f13) + ((451.0f * f12) + f14)) / 1403.0f;
                float f16 = ((f14 - (891.0f * f12)) - (261.0f * f13)) / 1403.0f;
                float f17 = ((f14 - (f12 * 220.0f)) - (f13 * 6300.0f)) / 1403.0f;
                float max = (float) Math.max(0.0d, (Math.abs(f15) * 27.13d) / (400.0d - Math.abs(f15)));
                float signum = Math.signum(f15);
                float f18 = 100.0f / rVar.f6653h;
                float pow3 = signum * f18 * ((float) Math.pow(max, 2.380952380952381d));
                float signum2 = Math.signum(f16) * f18 * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f16) * 27.13d) / (400.0d - Math.abs(f16))), 2.380952380952381d));
                float signum3 = Math.signum(f17) * f18 * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f17) * 27.13d) / (400.0d - Math.abs(f17))), 2.380952380952381d));
                float[] fArr = rVar.f6652g;
                float f19 = pow3 / fArr[0];
                float f20 = signum2 / fArr[1];
                float f21 = signum3 / fArr[2];
                float[][] fArr2 = b.f6616b;
                float[] fArr3 = fArr2[0];
                float f22 = (fArr3[2] * f21) + (fArr3[1] * f20) + (fArr3[0] * f19);
                float[] fArr4 = fArr2[1];
                float f23 = (fArr4[2] * f21) + (fArr4[1] * f20) + (fArr4[0] * f19);
                float[] fArr5 = fArr2[2];
                return i0.a.a(f22, f23, (f21 * fArr5[2]) + (f20 * fArr5[1]) + (f19 * fArr5[0]));
            }
        }
        f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        float pow4 = (float) Math.pow(f7 / Math.pow(1.64d - Math.pow(0.29d, rVar.f6651f), 0.73d), 1.1111111111111112d);
        double d92 = (this.f6609a * 3.1415927f) / 180.0f;
        float cos3 = ((float) (Math.cos(2.0d + d92) + 3.8d)) * 0.25f;
        float pow22 = rVar.f6646a * ((float) Math.pow(f9 / 100.0d, (1.0d / rVar.f6649d) / rVar.f6655j));
        float f102 = cos3 * 3846.1538f * rVar.f6650e * rVar.f6648c;
        float f112 = pow22 / rVar.f6647b;
        float sin2 = (float) Math.sin(d92);
        float cos22 = (float) Math.cos(d92);
        float A2 = (((0.305f + f112) * 23.0f) * pow4) / (((pow4 * 108.0f) * sin2) + a.a.A(pow4, 11.0f, cos22, f102 * 23.0f));
        float f122 = cos22 * A2;
        float f132 = A2 * sin2;
        float f142 = f112 * 460.0f;
        float f152 = ((288.0f * f132) + ((451.0f * f122) + f142)) / 1403.0f;
        float f162 = ((f142 - (891.0f * f122)) - (261.0f * f132)) / 1403.0f;
        float f172 = ((f142 - (f122 * 220.0f)) - (f132 * 6300.0f)) / 1403.0f;
        float max2 = (float) Math.max(0.0d, (Math.abs(f152) * 27.13d) / (400.0d - Math.abs(f152)));
        float signum4 = Math.signum(f152);
        float f182 = 100.0f / rVar.f6653h;
        float pow32 = signum4 * f182 * ((float) Math.pow(max2, 2.380952380952381d));
        float signum22 = Math.signum(f162) * f182 * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f162) * 27.13d) / (400.0d - Math.abs(f162))), 2.380952380952381d));
        float signum32 = Math.signum(f172) * f182 * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f172) * 27.13d) / (400.0d - Math.abs(f172))), 2.380952380952381d));
        float[] fArr6 = rVar.f6652g;
        float f192 = pow32 / fArr6[0];
        float f202 = signum22 / fArr6[1];
        float f212 = signum32 / fArr6[2];
        float[][] fArr22 = b.f6616b;
        float[] fArr32 = fArr22[0];
        float f222 = (fArr32[2] * f212) + (fArr32[1] * f202) + (fArr32[0] * f192);
        float[] fArr42 = fArr22[1];
        float f232 = (fArr42[2] * f212) + (fArr42[1] * f202) + (fArr42[0] * f192);
        float[] fArr52 = fArr22[2];
        return i0.a.a(f222, f232, (f212 * fArr52[2]) + (f202 * fArr52[1]) + (f192 * fArr52[0]));
    }
}
