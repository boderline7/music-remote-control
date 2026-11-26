package x;

import android.view.View;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l {

    /* renamed from: a, reason: collision with root package name */
    public t.d f9534a;

    /* renamed from: b, reason: collision with root package name */
    public int[] f9535b = new int[10];

    /* renamed from: c, reason: collision with root package name */
    public float[] f9536c = new float[10];

    /* renamed from: d, reason: collision with root package name */
    public int f9537d;

    /* renamed from: e, reason: collision with root package name */
    public String f9538e;

    public final float a(float f7) {
        return (float) this.f9534a.b(f7);
    }

    public void b(float f7, int i7) {
        int[] iArr = this.f9535b;
        if (iArr.length < this.f9537d + 1) {
            this.f9535b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f9536c;
            this.f9536c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f9535b;
        int i8 = this.f9537d;
        iArr2[i8] = i7;
        this.f9536c[i8] = f7;
        this.f9537d = i8 + 1;
    }

    public abstract void c(View view, float f7);

    public void d(int i7) {
        int i8;
        int i9 = this.f9537d;
        if (i9 == 0) {
            return;
        }
        int[] iArr = this.f9535b;
        float[] fArr = this.f9536c;
        int[] iArr2 = new int[iArr.length + 10];
        iArr2[0] = i9 - 1;
        iArr2[1] = 0;
        int i10 = 2;
        while (i10 > 0) {
            int i11 = i10 - 1;
            int i12 = iArr2[i11];
            int i13 = i10 - 2;
            int i14 = iArr2[i13];
            if (i12 < i14) {
                int i15 = iArr[i14];
                int i16 = i12;
                int i17 = i16;
                while (i16 < i14) {
                    int i18 = iArr[i16];
                    if (i18 <= i15) {
                        int i19 = iArr[i17];
                        iArr[i17] = i18;
                        iArr[i16] = i19;
                        float f7 = fArr[i17];
                        fArr[i17] = fArr[i16];
                        fArr[i16] = f7;
                        i17++;
                    }
                    i16++;
                }
                int i20 = iArr[i17];
                iArr[i17] = iArr[i14];
                iArr[i14] = i20;
                float f8 = fArr[i17];
                fArr[i17] = fArr[i14];
                fArr[i14] = f8;
                iArr2[i13] = i17 - 1;
                iArr2[i11] = i12;
                int i21 = i10 + 1;
                iArr2[i10] = i14;
                i10 += 2;
                iArr2[i21] = i17 + 1;
            } else {
                i10 = i13;
            }
        }
        int i22 = 1;
        for (int i23 = 1; i23 < this.f9537d; i23++) {
            int[] iArr3 = this.f9535b;
            if (iArr3[i23 - 1] != iArr3[i23]) {
                i22++;
            }
        }
        double[] dArr = new double[i22];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i22, 1);
        int i24 = 0;
        for (0; i8 < this.f9537d; i8 + 1) {
            if (i8 > 0) {
                int[] iArr4 = this.f9535b;
                i8 = iArr4[i8] == iArr4[i8 - 1] ? i8 + 1 : 0;
            }
            dArr[i24] = this.f9535b[i8] * 0.01d;
            dArr2[i24][0] = this.f9536c[i8];
            i24++;
        }
        this.f9534a = t.d.a(i7, dArr, dArr2);
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        String str = this.f9538e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i7 = 0; i7 < this.f9537d; i7++) {
            str = str + "[" + this.f9535b[i7] + " , " + decimalFormat.format(this.f9536c[i7]) + "] ";
        }
        return str;
    }
}
