package x1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends w1.n {
    public static final w1.n THE_ONE = new a();

    @Override // w1.n
    public final boolean b(w1.y yVar) {
        int n6 = yVar.n();
        return n6 != 0 && ((byte) n6) == n6;
    }

    @Override // w1.n
    public final int d() {
        return 1;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        return w1.n.c(hVar);
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return w1.n.a(hVar);
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        if (!(hVar instanceof w1.y) || hVar.f9429d.f6354b.length != 0) {
            return false;
        }
        w1.y yVar = (w1.y) hVar;
        if (yVar.o()) {
            return b(yVar);
        }
        return true;
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        cVar.k(w1.n.m(((w1.y) hVar).n() & 255, hVar));
    }
}
