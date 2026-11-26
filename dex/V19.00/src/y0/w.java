package y0;

import android.util.SparseArray;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w {

    /* renamed from: a, reason: collision with root package name */
    public final SparseArray f10024a;

    /* renamed from: b, reason: collision with root package name */
    public q f10025b;

    public w(int i7) {
        this.f10024a = new SparseArray(i7);
    }

    public final void a(q qVar, int i7, int i8) {
        int a7 = qVar.a(i7);
        SparseArray sparseArray = this.f10024a;
        w wVar = sparseArray == null ? null : (w) sparseArray.get(a7);
        if (wVar == null) {
            wVar = new w(1);
            sparseArray.put(qVar.a(i7), wVar);
        }
        if (i8 > i7) {
            wVar.a(qVar, i7 + 1, i8);
        } else {
            wVar.f10025b = qVar;
        }
    }
}
