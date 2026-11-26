package androidx.recyclerview.widget;

import android.util.SparseArray;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class y1 {

    /* renamed from: a, reason: collision with root package name */
    public SparseArray f2714a;

    /* renamed from: b, reason: collision with root package name */
    public int f2715b;

    public final x1 a(int i7) {
        SparseArray sparseArray = this.f2714a;
        x1 x1Var = (x1) sparseArray.get(i7);
        if (x1Var != null) {
            return x1Var;
        }
        x1 x1Var2 = new x1();
        sparseArray.put(i7, x1Var2);
        return x1Var2;
    }
}
