package i0;

import android.graphics.Color;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f6772a = new ThreadLocal();

    public static int a(double d7, double d8, double d9) {
        double d10 = (((-0.4986d) * d9) + (((-1.5372d) * d8) + (3.2406d * d7))) / 100.0d;
        double d11 = ((0.0415d * d9) + ((1.8758d * d8) + ((-0.9689d) * d7))) / 100.0d;
        double d12 = ((1.057d * d9) + (((-0.204d) * d8) + (0.0557d * d7))) / 100.0d;
        double pow = d10 > 0.0031308d ? (Math.pow(d10, 0.4166666666666667d) * 1.055d) - 0.055d : d10 * 12.92d;
        double pow2 = d11 > 0.0031308d ? (Math.pow(d11, 0.4166666666666667d) * 1.055d) - 0.055d : d11 * 12.92d;
        double pow3 = d12 > 0.0031308d ? (Math.pow(d12, 0.4166666666666667d) * 1.055d) - 0.055d : d12 * 12.92d;
        int round = (int) Math.round(pow * 255.0d);
        int min = round < 0 ? 0 : Math.min(round, 255);
        int round2 = (int) Math.round(pow2 * 255.0d);
        int min2 = round2 < 0 ? 0 : Math.min(round2, 255);
        int round3 = (int) Math.round(pow3 * 255.0d);
        return Color.rgb(min, min2, round3 >= 0 ? Math.min(round3, 255) : 0);
    }

    public static int b(int i7, float f7, int i8) {
        float f8 = 1.0f - f7;
        return Color.argb((int) ((Color.alpha(i8) * f7) + (Color.alpha(i7) * f8)), (int) ((Color.red(i8) * f7) + (Color.red(i7) * f8)), (int) ((Color.green(i8) * f7) + (Color.green(i7) * f8)), (int) ((Color.blue(i8) * f7) + (Color.blue(i7) * f8)));
    }

    public static int c(int i7, int i8) {
        int alpha = Color.alpha(i8);
        int alpha2 = Color.alpha(i7);
        int i9 = 255 - (((255 - alpha2) * (255 - alpha)) / 255);
        return Color.argb(i9, d(Color.red(i7), alpha2, Color.red(i8), alpha, i9), d(Color.green(i7), alpha2, Color.green(i8), alpha, i9), d(Color.blue(i7), alpha2, Color.blue(i8), alpha, i9));
    }

    public static int d(int i7, int i8, int i9, int i10, int i11) {
        if (i11 == 0) {
            return 0;
        }
        return (((255 - i8) * (i9 * i10)) + ((i7 * 255) * i8)) / (i11 * 255);
    }

    public static int e(int i7, int i8) {
        if (i8 < 0 || i8 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i7 & d1.MEASURED_SIZE_MASK) | (i8 << 24);
    }
}
