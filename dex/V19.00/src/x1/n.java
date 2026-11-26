package x1;

import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n extends w1.n {
    public static final w1.n THE_ONE = new n();

    @Override // w1.n
    public final boolean b(w1.y yVar) {
        int n6 = yVar.n();
        return n6 != 0 && ((short) n6) == n6;
    }

    @Override // w1.n
    public final int d() {
        return 2;
    }

    @Override // w1.n
    public final BitSet f(w1.h hVar) {
        BitSet bitSet = new BitSet(2);
        a2.n nVar = hVar.f9429d;
        bitSet.set(0, w1.n.q(((a2.m) nVar.f(0)).f237a));
        bitSet.set(1, w1.n.q(((a2.m) nVar.f(1)).f237a));
        return bitSet;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        return ((a2.m) nVar.f(0)).h() + ", " + ((a2.m) nVar.f(1)).h() + ", " + w1.n.c(hVar);
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return w1.n.a(hVar);
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        if (!(hVar instanceof w1.y) || nVar.f6354b.length != 2 || !w1.n.q(((a2.m) nVar.f(0)).f237a) || !w1.n.q(((a2.m) nVar.f(1)).f237a)) {
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
        a2.n nVar = hVar.f9429d;
        int n6 = ((w1.y) hVar).n();
        w1.n.s(cVar, w1.n.m(w1.n.l(((a2.m) nVar.f(0)).f237a, ((a2.m) nVar.f(1)).f237a), hVar), (short) n6);
    }
}
