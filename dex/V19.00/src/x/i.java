package x;

import android.util.SparseArray;
import android.view.View;
import java.lang.reflect.Array;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends l {

    /* renamed from: f, reason: collision with root package name */
    public SparseArray f9531f;

    /* renamed from: g, reason: collision with root package name */
    public float[] f9532g;

    @Override // x.l
    public final void b(float f7, int i7) {
        throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
    }

    @Override // x.l
    public final void c(View view, float f7) {
        this.f9534a.d(f7, this.f9532g);
        l6.f.z0((a0.c) this.f9531f.valueAt(0), view, this.f9532g);
    }

    @Override // x.l
    public final void d(int i7) {
        SparseArray sparseArray = this.f9531f;
        int size = sparseArray.size();
        int c7 = ((a0.c) sparseArray.valueAt(0)).c();
        double[] dArr = new double[size];
        this.f9532g = new float[c7];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, c7);
        for (int i8 = 0; i8 < size; i8++) {
            int keyAt = sparseArray.keyAt(i8);
            a0.c cVar = (a0.c) sparseArray.valueAt(i8);
            dArr[i8] = keyAt * 0.01d;
            cVar.b(this.f9532g);
            int i9 = 0;
            while (true) {
                if (i9 < this.f9532g.length) {
                    dArr2[i8][i9] = r7[i9];
                    i9++;
                }
            }
        }
        this.f9534a = t.d.a(i7, dArr, dArr2);
    }
}
