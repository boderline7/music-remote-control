package x1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q extends w1.n {
    public static final w1.n THE_ONE = new q();

    @Override // w1.n
    public final int d() {
        return 3;
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
        return (hVar instanceof w1.y) && hVar.f9429d.f6354b.length == 0;
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        int n6 = ((w1.y) hVar).n();
        cVar.k(w1.n.m(0, hVar));
        cVar.k((short) n6);
        cVar.k((short) (n6 >> 16));
    }
}
