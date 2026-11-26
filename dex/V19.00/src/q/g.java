package q;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public final int f8394a;

    /* renamed from: b, reason: collision with root package name */
    public int f8395b;

    /* renamed from: c, reason: collision with root package name */
    public int f8396c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f8397d = false;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ k.d f8398e;

    public g(k.d dVar, int i7) {
        this.f8398e = dVar;
        this.f8394a = i7;
        this.f8395b = dVar.g();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f8396c < this.f8395b;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object e7 = this.f8398e.e(this.f8396c, this.f8394a);
        this.f8396c++;
        this.f8397d = true;
        return e7;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f8397d) {
            throw new IllegalStateException();
        }
        int i7 = this.f8396c - 1;
        this.f8396c = i7;
        this.f8395b--;
        this.f8397d = false;
        this.f8398e.k(i7);
    }
}
