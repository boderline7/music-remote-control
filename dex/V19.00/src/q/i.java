package q;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i implements Iterator, Map.Entry {

    /* renamed from: a, reason: collision with root package name */
    public int f8401a;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ k.d f8404d;

    /* renamed from: c, reason: collision with root package name */
    public boolean f8403c = false;

    /* renamed from: b, reason: collision with root package name */
    public int f8402b = -1;

    public i(k.d dVar) {
        this.f8404d = dVar;
        this.f8401a = dVar.g() - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.f8403c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i7 = this.f8402b;
        k.d dVar = this.f8404d;
        Object e7 = dVar.e(i7, 0);
        if (key != e7 && (key == null || !key.equals(e7))) {
            return false;
        }
        Object value = entry.getValue();
        Object e8 = dVar.e(this.f8402b, 1);
        return value == e8 || (value != null && value.equals(e8));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (!this.f8403c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.f8404d.e(this.f8402b, 0);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (!this.f8403c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.f8404d.e(this.f8402b, 1);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f8402b < this.f8401a;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.f8403c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i7 = this.f8402b;
        k.d dVar = this.f8404d;
        Object e7 = dVar.e(i7, 0);
        Object e8 = dVar.e(this.f8402b, 1);
        return (e7 == null ? 0 : e7.hashCode()) ^ (e8 != null ? e8.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f8402b++;
        this.f8403c = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f8403c) {
            throw new IllegalStateException();
        }
        this.f8404d.k(this.f8402b);
        this.f8402b--;
        this.f8401a--;
        this.f8403c = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (this.f8403c) {
            return this.f8404d.l(this.f8402b, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
