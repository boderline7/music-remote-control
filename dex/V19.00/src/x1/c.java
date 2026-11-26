package x1;

import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends w1.n {
    public static final w1.n THE_ONE = new c();

    @Override // w1.n
    public final int d() {
        return 1;
    }

    @Override // w1.n
    public final BitSet f(w1.h hVar) {
        BitSet bitSet = new BitSet(1);
        bitSet.set(0, w1.n.q(((a2.m) hVar.f9429d.f(0)).f237a));
        return bitSet;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        return ((a2.m) hVar.f9429d.f(0)).h() + ", " + w1.n.k((b2.o) ((w1.f) hVar).f9418e);
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return w1.n.j((b2.o) ((w1.f) hVar).f9418e, 4);
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        int h7;
        a2.n nVar = hVar.f9429d;
        if (!(hVar instanceof w1.f) || nVar.f6354b.length != 1 || !w1.n.q(((a2.m) nVar.f(0)).f237a)) {
            return false;
        }
        b2.a aVar = ((w1.f) hVar).f9418e;
        if (!(aVar instanceof b2.o)) {
            return false;
        }
        b2.o oVar = (b2.o) aVar;
        return oVar.g() && (h7 = oVar.h()) >= -8 && h7 <= 7;
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        int h7 = ((b2.o) ((w1.f) hVar).f9418e).h();
        cVar.k(w1.n.m(w1.n.l(((a2.m) nVar.f(0)).f237a, h7 & 15), hVar));
    }
}
