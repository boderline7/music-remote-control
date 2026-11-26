package x1;

import java.util.BitSet;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o extends w1.n {
    public static final w1.n THE_ONE = new o();

    @Override // w1.n
    public final int d() {
        return 2;
    }

    @Override // w1.n
    public final BitSet f(w1.h hVar) {
        BitSet bitSet = new BitSet(2);
        a2.n nVar = hVar.f9429d;
        bitSet.set(0, w1.n.p(((a2.m) nVar.f(0)).f237a));
        bitSet.set(1, w1.n.r(((a2.m) nVar.f(1)).f237a));
        return bitSet;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        return ((a2.m) nVar.f(0)).h() + ", " + ((a2.m) nVar.f(1)).h();
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return h0.FRAGMENT_ENCODE_SET;
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        return (hVar instanceof w1.x) && nVar.f6354b.length == 2 && w1.n.p(((a2.m) nVar.f(0)).f237a) && w1.n.r(((a2.m) nVar.f(1)).f237a);
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        w1.n.s(cVar, w1.n.m(((a2.m) nVar.f(0)).f237a, hVar), (short) ((a2.m) nVar.f(1)).f237a);
    }
}
