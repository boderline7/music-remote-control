package c5;

import java.util.RandomAccess;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends g implements RandomAccess {

    /* renamed from: a, reason: collision with root package name */
    public final g f3070a;

    /* renamed from: b, reason: collision with root package name */
    public final int f3071b;

    /* renamed from: c, reason: collision with root package name */
    public final int f3072c;

    public f(g gVar, int i7, int i8) {
        i5.a.g("list", gVar);
        this.f3070a = gVar;
        this.f3071b = i7;
        c cVar = g.Companion;
        int a7 = gVar.a();
        cVar.getClass();
        if (i7 < 0 || i8 > a7) {
            throw new IndexOutOfBoundsException("fromIndex: " + i7 + ", toIndex: " + i8 + ", size: " + a7);
        }
        if (i7 <= i8) {
            this.f3072c = i8 - i7;
            return;
        }
        throw new IllegalArgumentException("fromIndex: " + i7 + " > toIndex: " + i8);
    }

    @Override // c5.b
    public final int a() {
        return this.f3072c;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        c cVar = g.Companion;
        int i8 = this.f3072c;
        cVar.getClass();
        c.a(i7, i8);
        return this.f3070a.get(this.f3071b + i7);
    }
}
