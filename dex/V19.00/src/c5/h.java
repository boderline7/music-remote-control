package c5;

import java.util.AbstractList;
import java.util.List;
import java.util.NoSuchElementException;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h extends AbstractList implements List {
    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i7) {
        k kVar = (k) this;
        c cVar = g.Companion;
        int i8 = kVar.f3078c;
        cVar.getClass();
        c.a(i7, i8);
        if (i7 == z.m0(kVar)) {
            if (kVar.isEmpty()) {
                throw new NoSuchElementException("ArrayDeque is empty.");
            }
            int e7 = kVar.e(z.m0(kVar) + kVar.f3076a);
            Object[] objArr = kVar.f3077b;
            Object obj = objArr[e7];
            objArr[e7] = null;
            kVar.f3078c--;
            return obj;
        }
        if (i7 == 0) {
            if (kVar.isEmpty()) {
                throw new NoSuchElementException("ArrayDeque is empty.");
            }
            Object[] objArr2 = kVar.f3077b;
            int i9 = kVar.f3076a;
            Object obj2 = objArr2[i9];
            objArr2[i9] = null;
            kVar.f3076a = kVar.d(i9);
            kVar.f3078c--;
            return obj2;
        }
        int e8 = kVar.e(kVar.f3076a + i7);
        Object[] objArr3 = kVar.f3077b;
        Object obj3 = objArr3[e8];
        if (i7 < (kVar.f3078c >> 1)) {
            int i10 = kVar.f3076a;
            if (e8 >= i10) {
                l.z1(objArr3, objArr3, i10 + 1, i10, e8);
            } else {
                l.z1(objArr3, objArr3, 1, 0, e8);
                Object[] objArr4 = kVar.f3077b;
                objArr4[0] = objArr4[objArr4.length - 1];
                int i11 = kVar.f3076a;
                l.z1(objArr4, objArr4, i11 + 1, i11, objArr4.length - 1);
            }
            Object[] objArr5 = kVar.f3077b;
            int i12 = kVar.f3076a;
            objArr5[i12] = null;
            kVar.f3076a = kVar.d(i12);
        } else {
            int e9 = kVar.e(z.m0(kVar) + kVar.f3076a);
            if (e8 <= e9) {
                Object[] objArr6 = kVar.f3077b;
                l.z1(objArr6, objArr6, e8, e8 + 1, e9 + 1);
            } else {
                Object[] objArr7 = kVar.f3077b;
                l.z1(objArr7, objArr7, e8, e8 + 1, objArr7.length);
                Object[] objArr8 = kVar.f3077b;
                objArr8[objArr8.length - 1] = objArr8[0];
                l.z1(objArr8, objArr8, 0, 1, e9 + 1);
            }
            kVar.f3077b[e9] = null;
        }
        kVar.f3078c--;
        return obj3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return ((k) this).f3078c;
    }
}
