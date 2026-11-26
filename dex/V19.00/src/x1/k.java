package x1;

import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends w1.n {
    public static final w1.n THE_ONE = new k();

    @Override // w1.n
    public final int d() {
        return 2;
    }

    @Override // w1.n
    public final BitSet f(w1.h hVar) {
        BitSet bitSet = new BitSet(2);
        a2.n nVar = hVar.f9429d;
        bitSet.set(0, w1.n.p(((a2.m) nVar.f(0)).f237a));
        bitSet.set(1, w1.n.p(((a2.m) nVar.f(1)).f237a));
        return bitSet;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        return ((a2.m) nVar.f(0)).h() + ", " + ((a2.m) nVar.f(1)).h() + ", " + w1.n.k((b2.o) ((w1.f) hVar).f9418e);
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return w1.n.j((b2.o) ((w1.f) hVar).f9418e, 8);
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        if (!(hVar instanceof w1.f) || nVar.f6354b.length != 2 || !w1.n.p(((a2.m) nVar.f(0)).f237a) || !w1.n.p(((a2.m) nVar.f(1)).f237a)) {
            return false;
        }
        b2.a aVar = ((w1.f) hVar).f9418e;
        if (!(aVar instanceof b2.o)) {
            return false;
        }
        b2.o oVar = (b2.o) aVar;
        if (!oVar.g()) {
            return false;
        }
        int h7 = oVar.h();
        return ((byte) h7) == h7;
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        int h7 = ((b2.o) ((w1.f) hVar).f9418e).h();
        w1.n.s(cVar, w1.n.m(((a2.m) nVar.f(0)).f237a, hVar), w1.n.e(((a2.m) nVar.f(1)).f237a, h7 & 255));
    }
}
