package t;

import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int CUSTOM = 7;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static final int TRIANGLE_WAVE = 2;

    /* renamed from: a, reason: collision with root package name */
    public float[] f8977a;

    /* renamed from: b, reason: collision with root package name */
    public double[] f8978b;

    /* renamed from: c, reason: collision with root package name */
    public double[] f8979c;

    /* renamed from: d, reason: collision with root package name */
    public int f8980d;

    public final void a(double d7, float f7) {
        int length = this.f8977a.length + 1;
        int binarySearch = Arrays.binarySearch(this.f8978b, d7);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        this.f8978b = Arrays.copyOf(this.f8978b, length);
        this.f8977a = Arrays.copyOf(this.f8977a, length);
        this.f8979c = new double[length];
        double[] dArr = this.f8978b;
        System.arraycopy(dArr, binarySearch, dArr, binarySearch + 1, (length - binarySearch) - 1);
        this.f8978b[binarySearch] = d7;
        this.f8977a[binarySearch] = f7;
    }

    public final double b(double d7) {
        if (d7 < 0.0d) {
            d7 = 0.0d;
        } else if (d7 > 1.0d) {
            d7 = 1.0d;
        }
        int binarySearch = Arrays.binarySearch(this.f8978b, d7);
        if (binarySearch > 0) {
            return 1.0d;
        }
        if (binarySearch == 0) {
            return 0.0d;
        }
        int i7 = -binarySearch;
        int i8 = i7 - 1;
        float[] fArr = this.f8977a;
        float f7 = fArr[i8];
        int i9 = i7 - 2;
        float f8 = fArr[i9];
        double[] dArr = this.f8978b;
        double d8 = dArr[i8];
        double d9 = dArr[i9];
        double d10 = (f7 - f8) / (d8 - d9);
        return ((((d7 * d7) - (d9 * d9)) * d10) / 2.0d) + ((d7 - d9) * (f8 - (d10 * d9))) + this.f8979c[i9];
    }

    public final double c(double d7, double d8) {
        double abs;
        double b7 = b(d7) + d8;
        switch (this.f8980d) {
            case 1:
                return Math.signum(0.5d - (b7 % 1.0d));
            case 2:
                abs = Math.abs((((b7 * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((b7 * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                abs = ((b7 * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos((d8 + b7) * 6.283185307179586d);
            case 6:
                double abs2 = 1.0d - Math.abs(((b7 * 4.0d) % 4.0d) - 2.0d);
                abs = abs2 * abs2;
                break;
            case 7:
                throw null;
            default:
                return Math.sin(6.283185307179586d * b7);
        }
        return 1.0d - abs;
    }

    public final String toString() {
        return "pos =" + Arrays.toString(this.f8978b) + " period=" + Arrays.toString(this.f8977a);
    }
}
