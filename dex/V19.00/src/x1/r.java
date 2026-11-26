package x1;

import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r extends w1.n {
    public static final w1.n THE_ONE = new r();

    @Override // w1.n
    public final int d() {
        return 3;
    }

    @Override // w1.n
    public final BitSet f(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        int length = nVar.f6354b.length;
        BitSet bitSet = new BitSet(length);
        boolean p6 = w1.n.p(((a2.m) nVar.f(0)).f237a);
        if (length == 1) {
            bitSet.set(0, p6);
        } else {
            if (((a2.m) nVar.f(0)).f237a == ((a2.m) nVar.f(1)).f237a) {
                bitSet.set(0, p6);
                bitSet.set(1, p6);
            }
        }
        return bitSet;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        return ((a2.m) hVar.f9429d.f(0)).h() + ", " + hVar.d();
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return hVar.c();
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        a2.m mVar;
        if (!(hVar instanceof w1.f)) {
            return false;
        }
        a2.n nVar = hVar.f9429d;
        int length = nVar.f6354b.length;
        if (length == 1) {
            mVar = (a2.m) nVar.f(0);
        } else {
            if (length != 2) {
                return false;
            }
            mVar = (a2.m) nVar.f(0);
            if (mVar.f237a != ((a2.m) nVar.f(1)).f237a) {
                return false;
            }
        }
        if (!w1.n.p(mVar.f237a)) {
            return false;
        }
        b2.a aVar = ((w1.f) hVar).f9418e;
        return (aVar instanceof b2.v) || (aVar instanceof b2.i) || (aVar instanceof b2.u);
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        int n6 = ((w1.f) hVar).n();
        short m6 = w1.n.m(((a2.m) nVar.f(0)).f237a, hVar);
        cVar.k(m6);
        cVar.k((short) n6);
        cVar.k((short) (n6 >> 16));
    }
}
