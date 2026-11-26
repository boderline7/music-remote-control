package x;

import android.view.View;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g {

    /* renamed from: a, reason: collision with root package name */
    public t.h f9527a;

    /* renamed from: b, reason: collision with root package name */
    public String f9528b;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f9529c = new ArrayList();

    public static g c(String str) {
        if (str.startsWith(y.c.CUSTOM)) {
            d dVar = new d();
            dVar.f9525d = new float[1];
            return dVar;
        }
        switch (str) {
            case "rotationX":
                return new c(3);
            case "rotationY":
                return new c(4);
            case "translationX":
                return new c(7);
            case "translationY":
                return new c(8);
            case "translationZ":
                return new c(9);
            case "progress":
                f fVar = new f();
                fVar.f9526d = false;
                return fVar;
            case "scaleX":
                return new c(5);
            case "scaleY":
                return new c(6);
            case "waveVariesBy":
                return new c(0);
            case "rotation":
                return new c(2);
            case "elevation":
                return new c(1);
            case "transitionPathRotate":
                return new e();
            case "alpha":
                return new c(0);
            case "waveOffset":
                return new c(0);
            default:
                return null;
        }
    }

    public final float a(float f7) {
        t.h hVar = this.f9527a;
        t.d dVar = hVar.f8967g;
        if (dVar != null) {
            dVar.c(f7, hVar.f8968h);
        } else {
            double[] dArr = hVar.f8968h;
            dArr[0] = hVar.f8965e[0];
            dArr[1] = hVar.f8966f[0];
            dArr[2] = hVar.f8962b[0];
        }
        double[] dArr2 = hVar.f8968h;
        return (float) ((hVar.f8961a.c(f7, dArr2[1]) * hVar.f8968h[2]) + dArr2[0]);
    }

    public final float b(float f7) {
        double d7;
        double d8;
        double signum;
        double d9;
        t.h hVar = this.f9527a;
        t.d dVar = hVar.f8967g;
        double d10 = 0.0d;
        if (dVar != null) {
            double d11 = f7;
            dVar.f(d11, hVar.f8969i);
            hVar.f8967g.c(d11, hVar.f8968h);
        } else {
            double[] dArr = hVar.f8969i;
            dArr[0] = 0.0d;
            dArr[1] = 0.0d;
            dArr[2] = 0.0d;
        }
        double d12 = f7;
        double d13 = hVar.f8968h[1];
        t.k kVar = hVar.f8961a;
        double c7 = kVar.c(d12, d13);
        double d14 = hVar.f8968h[1];
        double d15 = hVar.f8969i[1];
        double b7 = kVar.b(d12) + d14;
        if (d12 <= 0.0d) {
            d12 = 1.0E-5d;
        } else if (d12 >= 1.0d) {
            d12 = 0.999999d;
        }
        int binarySearch = Arrays.binarySearch(kVar.f8978b, d12);
        if (binarySearch <= 0) {
            if (binarySearch != 0) {
                int i7 = -binarySearch;
                int i8 = i7 - 1;
                float[] fArr = kVar.f8977a;
                float f8 = fArr[i8];
                int i9 = i7 - 2;
                float f9 = fArr[i9];
                double d16 = f8 - f9;
                double[] dArr2 = kVar.f8978b;
                double d17 = dArr2[i8];
                double d18 = dArr2[i9];
                double d19 = d16 / (d17 - d18);
                d10 = (f9 - (d19 * d18)) + (d12 * d19);
            } else {
                d10 = 0.0d;
            }
        }
        double d20 = d10 + d15;
        double d21 = 2.0d;
        switch (kVar.f8980d) {
            case 1:
                d7 = 0.0d;
                double[] dArr3 = hVar.f8969i;
                return (float) ((d7 * hVar.f8968h[2]) + (c7 * dArr3[2]) + dArr3[0]);
            case 2:
                d8 = d20 * 4.0d;
                signum = Math.signum((((b7 * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                d7 = d8 * signum;
                double[] dArr32 = hVar.f8969i;
                return (float) ((d7 * hVar.f8968h[2]) + (c7 * dArr32[2]) + dArr32[0]);
            case 3:
                d7 = d20 * 2.0d;
                double[] dArr322 = hVar.f8969i;
                return (float) ((d7 * hVar.f8968h[2]) + (c7 * dArr322[2]) + dArr322[0]);
            case 4:
                d9 = -d20;
                d7 = d9 * d21;
                double[] dArr3222 = hVar.f8969i;
                return (float) ((d7 * hVar.f8968h[2]) + (c7 * dArr3222[2]) + dArr3222[0]);
            case 5:
                d21 = (-6.283185307179586d) * d20;
                d9 = Math.sin(6.283185307179586d * b7);
                d7 = d9 * d21;
                double[] dArr32222 = hVar.f8969i;
                return (float) ((d7 * hVar.f8968h[2]) + (c7 * dArr32222[2]) + dArr32222[0]);
            case 6:
                d8 = d20 * 4.0d;
                signum = (((b7 * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                d7 = d8 * signum;
                double[] dArr322222 = hVar.f8969i;
                return (float) ((d7 * hVar.f8968h[2]) + (c7 * dArr322222[2]) + dArr322222[0]);
            case 7:
                throw null;
            default:
                d8 = d20 * 6.283185307179586d;
                signum = Math.cos(6.283185307179586d * b7);
                d7 = d8 * signum;
                double[] dArr3222222 = hVar.f8969i;
                return (float) ((d7 * hVar.f8968h[2]) + (c7 * dArr3222222[2]) + dArr3222222[0]);
        }
    }

    public abstract void d(View view, float f7);

    public final void e() {
        int i7;
        int size = this.f9529c.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f9529c, new s.f(1, this));
        double[] dArr = new double[size];
        Class cls = Double.TYPE;
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) cls, size, 3);
        t.h hVar = new t.h();
        t.k kVar = new t.k();
        kVar.f8977a = new float[0];
        kVar.f8978b = new double[0];
        hVar.f8961a = kVar;
        kVar.f8980d = 0;
        hVar.f8962b = new float[size];
        hVar.f8963c = new double[size];
        hVar.f8964d = new float[size];
        hVar.f8965e = new float[size];
        hVar.f8966f = new float[size];
        float[] fArr = new float[size];
        this.f9527a = hVar;
        Iterator it = this.f9529c.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
        t.h hVar2 = this.f9527a;
        double[] dArr3 = hVar2.f8963c;
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) cls, dArr3.length, 3);
        float[] fArr2 = hVar2.f8962b;
        hVar2.f8968h = new double[fArr2.length + 2];
        hVar2.f8969i = new double[fArr2.length + 2];
        double d7 = dArr3[0];
        float[] fArr3 = hVar2.f8964d;
        t.k kVar2 = hVar2.f8961a;
        if (d7 > 0.0d) {
            kVar2.a(0.0d, fArr3[0]);
        }
        int length = dArr3.length - 1;
        if (dArr3[length] < 1.0d) {
            kVar2.a(1.0d, fArr3[length]);
        }
        int i8 = 0;
        while (i8 < dArr4.length) {
            double[] dArr5 = dArr4[i8];
            float[] fArr4 = fArr3;
            dArr5[0] = hVar2.f8965e[i8];
            dArr5[1] = hVar2.f8966f[i8];
            dArr5[2] = fArr2[i8];
            kVar2.a(dArr3[i8], fArr4[i8]);
            i8++;
            fArr3 = fArr4;
        }
        int i9 = 0;
        double d8 = 0.0d;
        while (true) {
            if (i9 >= kVar2.f8977a.length) {
                break;
            }
            d8 += r12[i9];
            i9++;
        }
        int i10 = 1;
        double d9 = 0.0d;
        while (true) {
            float[] fArr5 = kVar2.f8977a;
            if (i10 >= fArr5.length) {
                break;
            }
            int i11 = i10 - 1;
            float f7 = (fArr5[i11] + fArr5[i10]) / 2.0f;
            double[] dArr6 = kVar2.f8978b;
            d9 = ((dArr6[i10] - dArr6[i11]) * f7) + d9;
            i10++;
            d8 = d8;
        }
        double d10 = d8;
        int i12 = 0;
        while (true) {
            float[] fArr6 = kVar2.f8977a;
            if (i12 >= fArr6.length) {
                break;
            }
            fArr6[i12] = (float) ((d10 / d9) * fArr6[i12]);
            i12++;
        }
        kVar2.f8979c[0] = 0.0d;
        int i13 = 1;
        while (true) {
            float[] fArr7 = kVar2.f8977a;
            if (i13 >= fArr7.length) {
                break;
            }
            int i14 = i13 - 1;
            float f8 = (fArr7[i14] + fArr7[i13]) / 2.0f;
            double[] dArr7 = kVar2.f8978b;
            double d11 = dArr7[i13] - dArr7[i14];
            double[] dArr8 = kVar2.f8979c;
            dArr8[i13] = (d11 * f8) + dArr8[i14];
            i13++;
        }
        if (dArr3.length > 1) {
            i7 = 0;
            hVar2.f8967g = t.d.a(0, dArr3, dArr4);
        } else {
            i7 = 0;
            hVar2.f8967g = null;
        }
        t.d.a(i7, dArr, dArr2);
    }

    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        String str = this.f9528b;
        new DecimalFormat("##.##");
        Iterator it = this.f9529c.iterator();
        if (!it.hasNext()) {
            return str;
        }
        a.a.u(it.next());
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("[0 , ");
        throw null;
    }
}
