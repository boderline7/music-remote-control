package q;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h implements Set {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8399a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ k.d f8400b;

    public /* synthetic */ h(k.d dVar, int i7) {
        this.f8399a = i7;
        this.f8400b = dVar;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        switch (this.f8399a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        switch (this.f8399a) {
            case 0:
                k.d dVar = this.f8400b;
                int g7 = dVar.g();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    dVar.j(entry.getKey(), entry.getValue());
                }
                return g7 != dVar.g();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        int i7 = this.f8399a;
        k.d dVar = this.f8400b;
        switch (i7) {
            case 0:
                dVar.d();
                break;
            default:
                dVar.d();
                break;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        int i7 = this.f8399a;
        k.d dVar = this.f8400b;
        switch (i7) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    int h7 = dVar.h(entry.getKey());
                    if (h7 >= 0) {
                        Object e7 = dVar.e(h7, 1);
                        Object value = entry.getValue();
                        if (e7 == value || (e7 != null && e7.equals(value))) {
                            break;
                        }
                    }
                }
                break;
            default:
                if (dVar.h(obj) >= 0) {
                    break;
                }
                break;
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        switch (this.f8399a) {
            case 0:
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        break;
                    }
                }
                break;
            default:
                b f7 = this.f8400b.f();
                Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!f7.containsKey(it2.next())) {
                        break;
                    }
                }
                break;
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        switch (this.f8399a) {
        }
        return k.d.m(this, obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        int i7 = this.f8399a;
        k.d dVar = this.f8400b;
        switch (i7) {
            case 0:
                int i8 = 0;
                for (int g7 = dVar.g() - 1; g7 >= 0; g7--) {
                    Object e7 = dVar.e(g7, 0);
                    Object e8 = dVar.e(g7, 1);
                    i8 += (e7 == null ? 0 : e7.hashCode()) ^ (e8 == null ? 0 : e8.hashCode());
                }
                return i8;
            default:
                int i9 = 0;
                for (int g8 = dVar.g() - 1; g8 >= 0; g8--) {
                    Object e9 = dVar.e(g8, 0);
                    i9 += e9 == null ? 0 : e9.hashCode();
                }
                return i9;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        int i7 = this.f8399a;
        k.d dVar = this.f8400b;
        switch (i7) {
            case 0:
                if (dVar.g() == 0) {
                    break;
                }
                break;
            default:
                if (dVar.g() == 0) {
                    break;
                }
                break;
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        int i7 = this.f8399a;
        k.d dVar = this.f8400b;
        switch (i7) {
            case 0:
                return new i(dVar);
            default:
                return new g(dVar, 0);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        switch (this.f8399a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                k.d dVar = this.f8400b;
                int h7 = dVar.h(obj);
                if (h7 < 0) {
                    return false;
                }
                dVar.k(h7);
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        switch (this.f8399a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                b f7 = this.f8400b.f();
                int i7 = f7.f8412c;
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    f7.remove(it.next());
                }
                return i7 != f7.f8412c;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        switch (this.f8399a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                return k.d.r(this.f8400b.f(), collection);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        int i7 = this.f8399a;
        k.d dVar = this.f8400b;
        switch (i7) {
        }
        return dVar.g();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        switch (this.f8399a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                k.d dVar = this.f8400b;
                int g7 = dVar.g();
                Object[] objArr = new Object[g7];
                for (int i7 = 0; i7 < g7; i7++) {
                    objArr[i7] = dVar.e(i7, 0);
                }
                return objArr;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        switch (this.f8399a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                return this.f8400b.t(0, objArr);
        }
    }
}
