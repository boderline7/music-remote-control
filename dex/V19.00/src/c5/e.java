package c5;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends d implements ListIterator {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g f3069d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(g gVar, int i7) {
        super(gVar);
        this.f3069d = gVar;
        c cVar = g.Companion;
        int a7 = gVar.a();
        cVar.getClass();
        c.b(i7, a7);
        this.f3067b = i7;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f3067b > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f3067b;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i7 = this.f3067b - 1;
        this.f3067b = i7;
        return this.f3069d.get(i7);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f3067b - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
