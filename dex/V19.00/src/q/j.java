package q;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j implements Collection {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ k.d f8405a;

    public j(k.d dVar) {
        this.f8405a = dVar;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final void clear() {
        this.f8405a.d();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.f8405a.i(obj) >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.f8405a.g() == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new g(this.f8405a, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        k.d dVar = this.f8405a;
        int i7 = dVar.i(obj);
        if (i7 < 0) {
            return false;
        }
        dVar.k(i7);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        k.d dVar = this.f8405a;
        int g7 = dVar.g();
        int i7 = 0;
        boolean z6 = false;
        while (i7 < g7) {
            if (collection.contains(dVar.e(i7, 1))) {
                dVar.k(i7);
                i7--;
                g7--;
                z6 = true;
            }
            i7++;
        }
        return z6;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        k.d dVar = this.f8405a;
        int g7 = dVar.g();
        int i7 = 0;
        boolean z6 = false;
        while (i7 < g7) {
            if (!collection.contains(dVar.e(i7, 1))) {
                dVar.k(i7);
                i7--;
                g7--;
                z6 = true;
            }
            i7++;
        }
        return z6;
    }

    @Override // java.util.Collection
    public final int size() {
        return this.f8405a.g();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        k.d dVar = this.f8405a;
        int g7 = dVar.g();
        Object[] objArr = new Object[g7];
        for (int i7 = 0; i7 < g7; i7++) {
            objArr[i7] = dVar.e(i7, 1);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.f8405a.t(1, objArr);
    }
}
