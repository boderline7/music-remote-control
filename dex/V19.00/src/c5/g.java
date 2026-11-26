package c5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g extends b implements List {
    public static final c Companion = new c();

    @Override // java.util.List
    public final void add(int i7, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        Collection collection = (Collection) obj;
        Companion.getClass();
        i5.a.g("other", collection);
        if (size() == collection.size()) {
            Iterator it = collection.iterator();
            Iterator<E> it2 = iterator();
            while (it2.hasNext()) {
                if (!i5.a.c(it2.next(), it.next())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        Companion.getClass();
        Iterator<E> it = iterator();
        int i7 = 1;
        while (it.hasNext()) {
            Object next = it.next();
            i7 = (i7 * 31) + (next != null ? next.hashCode() : 0);
        }
        return i7;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Iterator it = iterator();
        int i7 = 0;
        while (it.hasNext()) {
            if (i5.a.c(it.next(), obj)) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new d(this);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (i5.a.c(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new e(this, 0);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i7) {
        return new e(this, i7);
    }

    @Override // java.util.List
    public final Object remove(int i7) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final Object set(int i7, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final List subList(int i7, int i8) {
        return new f(this, i7, i8);
    }
}
