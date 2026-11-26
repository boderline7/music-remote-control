package c5;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i implements Collection {

    /* renamed from: a, reason: collision with root package name */
    public final Object[] f3073a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f3074b;

    public i(Object[] objArr, boolean z6) {
        this.f3073a = objArr;
        this.f3074b = z6;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        int i7;
        Object[] objArr = this.f3073a;
        i5.a.g("<this>", objArr);
        if (obj == null) {
            int length = objArr.length;
            i7 = 0;
            while (i7 < length) {
                if (objArr[i7] != null) {
                    i7++;
                }
            }
            return false;
        }
        int length2 = objArr.length;
        for (int i8 = 0; i8 < length2; i8++) {
            if (i5.a.c(obj, objArr[i8])) {
                i7 = i8;
            }
        }
        return false;
        return i7 >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        i5.a.g("elements", collection);
        if (collection.isEmpty()) {
            return true;
        }
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
        return this.f3073a.length == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        Object[] objArr = this.f3073a;
        i5.a.g("array", objArr);
        return new d(objArr);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final int size() {
        return this.f3073a.length;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        Object[] objArr = this.f3073a;
        i5.a.g("<this>", objArr);
        if (this.f3074b && i5.a.c(objArr.getClass(), Object[].class)) {
            return objArr;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
        i5.a.f("copyOf(this, this.size, Array<Any?>::class.java)", copyOf);
        return copyOf;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        i5.a.g("array", objArr);
        return i5.a.v(this, objArr);
    }
}
