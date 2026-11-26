package a2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends e2.d {

    /* renamed from: c, reason: collision with root package name */
    public final e2.f f225c;

    /* renamed from: d, reason: collision with root package name */
    public int f226d;

    public c(int i7) {
        super(i7);
        this.f225c = new e2.f(i7);
        this.f226d = -1;
    }

    public final int i() {
        e2.f fVar = this.f225c;
        int i7 = fVar.f6362c - 1;
        while (i7 >= 0 && fVar.g(i7) < 0) {
            i7--;
        }
        int i8 = i7 + 1;
        if (i8 < 0) {
            throw new IllegalArgumentException("newSize < 0");
        }
        if (i8 > fVar.f6362c) {
            throw new IllegalArgumentException("newSize > size");
        }
        fVar.e();
        fVar.f6362c = i8;
        return i8;
    }

    public final b j(int i7) {
        e2.f fVar = this.f225c;
        int g7 = i7 >= fVar.f6362c ? -1 : fVar.g(i7);
        if (g7 >= 0) {
            return (b) f(g7);
        }
        throw new IllegalArgumentException("no such label: ".concat(z.l1(i7)));
    }

    public final void k(int i7, b bVar) {
        e2.g gVar = (e2.g) this.f6354b[i7];
        g(i7, bVar);
        e2.f fVar = this.f225c;
        if (gVar != null) {
            fVar.h(((b) gVar).f221a, -1);
        }
        int i8 = fVar.f6362c;
        int i9 = 0;
        while (true) {
            int i10 = bVar.f221a;
            if (i9 > i10 - i8) {
                fVar.h(i10, i7);
                return;
            } else {
                fVar.f(-1);
                i9++;
            }
        }
    }
}
