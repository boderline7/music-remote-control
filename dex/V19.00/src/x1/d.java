package x1;

import java.util.BitSet;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends w1.n {
    public static final w1.n THE_ONE = new d();

    @Override // w1.n
    public final int d() {
        return 1;
    }

    @Override // w1.n
    public final BitSet f(w1.h hVar) {
        BitSet bitSet = new BitSet(1);
        bitSet.set(0, w1.n.p(((a2.m) hVar.f9429d.f(0)).f237a));
        return bitSet;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        return ((a2.m) hVar.f9429d.f(0)).h();
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return h0.FRAGMENT_ENCODE_SET;
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        return (hVar instanceof w1.x) && nVar.f6354b.length == 1 && w1.n.p(((a2.m) nVar.f(0)).f237a);
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        cVar.k(w1.n.m(((a2.m) hVar.f9429d.f(0)).f237a, hVar));
    }
}
