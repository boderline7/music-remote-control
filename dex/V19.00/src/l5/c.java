package l5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public final int f7453a;

    /* renamed from: b, reason: collision with root package name */
    public final int f7454b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f7455c;

    /* renamed from: d, reason: collision with root package name */
    public int f7456d;

    public c(int i7, int i8, int i9) {
        this.f7453a = i9;
        this.f7454b = i8;
        boolean z6 = true;
        if (i9 <= 0 ? i7 < i8 : i7 > i8) {
            z6 = false;
        }
        this.f7455c = z6;
        this.f7456d = z6 ? i7 : i8;
    }

    @Override // java.util.Iterator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final /* bridge */ /* synthetic */ Object next() {
        return Integer.valueOf(b());
    }

    public final int b() {
        int i7 = this.f7456d;
        if (i7 != this.f7454b) {
            this.f7456d = this.f7453a + i7;
        } else {
            if (!this.f7455c) {
                throw new NoSuchElementException();
            }
            this.f7455c = false;
        }
        return i7;
    }

    public final void c() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f7455c;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ void remove() {
        c();
        throw null;
    }
}
