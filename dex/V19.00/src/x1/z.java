package x1;

import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class z extends w1.n {
    public static final w1.n THE_ONE = new z();

    @Override // w1.n
    public final int d() {
        return 5;
    }

    @Override // w1.n
    public final BitSet f(w1.h hVar) {
        BitSet bitSet = new BitSet(1);
        bitSet.set(0, w1.n.p(((a2.m) hVar.f9429d.f(0)).f237a));
        return bitSet;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        return ((a2.m) hVar.f9429d.f(0)).h() + ", " + w1.n.k((b2.o) ((w1.f) hVar).f9418e);
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return w1.n.j((b2.o) ((w1.f) hVar).f9418e, 64);
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        if ((hVar instanceof w1.f) && nVar.f6354b.length == 1 && w1.n.p(((a2.m) nVar.f(0)).f237a)) {
            return ((w1.f) hVar).f9418e instanceof b2.n;
        }
        return false;
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        long j7 = ((b2.n) ((w1.f) hVar).f9418e).f2935a;
        cVar.k(w1.n.m(((a2.m) nVar.f(0)).f237a, hVar));
        cVar.k((short) j7);
        cVar.k((short) (j7 >> 16));
        cVar.k((short) (j7 >> 32));
        cVar.k((short) (j7 >> 48));
    }
}
