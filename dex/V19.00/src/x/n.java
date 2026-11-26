package x;

import android.util.SparseArray;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.Array;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n extends q {

    /* renamed from: g, reason: collision with root package name */
    public String f9540g;

    /* renamed from: h, reason: collision with root package name */
    public SparseArray f9541h;

    /* renamed from: i, reason: collision with root package name */
    public SparseArray f9542i;

    /* renamed from: j, reason: collision with root package name */
    public float[] f9543j;

    /* renamed from: k, reason: collision with root package name */
    public float[] f9544k;

    @Override // t.q
    public final void a(int i7) {
        SparseArray sparseArray = this.f9541h;
        int size = sparseArray.size();
        int c7 = ((a0.c) sparseArray.valueAt(0)).c();
        double[] dArr = new double[size];
        int i8 = c7 + 2;
        this.f9543j = new float[i8];
        this.f9544k = new float[c7];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, i8);
        for (int i9 = 0; i9 < size; i9++) {
            int keyAt = sparseArray.keyAt(i9);
            a0.c cVar = (a0.c) sparseArray.valueAt(i9);
            float[] fArr = (float[]) this.f9542i.valueAt(i9);
            dArr[i9] = keyAt * 0.01d;
            cVar.b(this.f9543j);
            int i10 = 0;
            while (true) {
                if (i10 < this.f9543j.length) {
                    dArr2[i9][i10] = r8[i10];
                    i10++;
                }
            }
            double[] dArr3 = dArr2[i9];
            dArr3[c7] = fArr[0];
            dArr3[c7 + 1] = fArr[1];
        }
        this.f9006a = t.d.a(i7, dArr, dArr2);
    }

    @Override // x.q
    public final boolean c(float f7, long j7, View view, t.g gVar) {
        this.f9006a.d(f7, this.f9543j);
        float[] fArr = this.f9543j;
        float f8 = fArr[fArr.length - 2];
        float f9 = fArr[fArr.length - 1];
        long j8 = j7 - this.f9010e;
        if (Float.isNaN(this.f9011f)) {
            float a7 = gVar.a(view, this.f9540g);
            this.f9011f = a7;
            if (Float.isNaN(a7)) {
                this.f9011f = CropImageView.DEFAULT_ASPECT_RATIO;
            }
        }
        this.f9011f = (float) ((((j8 * 1.0E-9d) * f8) + this.f9011f) % 1.0d);
        this.f9010e = j7;
        float sin = (float) Math.sin(r15 * 6.2831855f);
        this.f9009d = false;
        int i7 = 0;
        while (true) {
            float[] fArr2 = this.f9544k;
            if (i7 >= fArr2.length) {
                break;
            }
            boolean z6 = this.f9009d;
            float f10 = this.f9543j[i7];
            this.f9009d = z6 | (((double) f10) != 0.0d);
            fArr2[i7] = (f10 * sin) + f9;
            i7++;
        }
        l6.f.z0((a0.c) this.f9541h.valueAt(0), view, this.f9544k);
        if (f8 != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.f9009d = true;
        }
        return this.f9009d;
    }
}
