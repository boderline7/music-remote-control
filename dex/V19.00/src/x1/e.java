package x1;

import java.util.BitSet;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends w1.n {
    public static final w1.n THE_ONE = new e();

    @Override // w1.n
    public final int d() {
        return 1;
    }

    @Override // w1.n
    public final BitSet f(w1.h hVar) {
        BitSet bitSet = new BitSet(2);
        a2.n nVar = hVar.f9429d;
        a2.m mVar = (a2.m) nVar.f(0);
        a2.m mVar2 = (a2.m) nVar.f(1);
        int length = nVar.f6354b.length;
        int i7 = mVar.f237a;
        int i8 = mVar2.f237a;
        if (length == 2) {
            bitSet.set(0, w1.n.q(i7));
            bitSet.set(1, w1.n.q(i8));
        } else {
            if (length != 3) {
                throw new AssertionError();
            }
            if (i7 != i8) {
                bitSet.set(0, false);
                bitSet.set(1, false);
            } else {
                boolean q6 = w1.n.q(i8);
                bitSet.set(0, q6);
                bitSet.set(1, q6);
            }
            bitSet.set(2, w1.n.q(((a2.m) nVar.f(2)).f237a));
        }
        return bitSet;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        int length = nVar.f6354b.length;
        return ((a2.m) nVar.f(length - 2)).h() + ", " + ((a2.m) nVar.f(length - 1)).h();
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return h0.FRAGMENT_ENCODE_SET;
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        a2.m mVar;
        a2.m mVar2;
        if (!(hVar instanceof w1.x)) {
            return false;
        }
        a2.n nVar = hVar.f9429d;
        int length = nVar.f6354b.length;
        if (length == 2) {
            mVar = (a2.m) nVar.f(0);
            mVar2 = (a2.m) nVar.f(1);
        } else {
            if (length != 3) {
                return false;
            }
            mVar = (a2.m) nVar.f(1);
            mVar2 = (a2.m) nVar.f(2);
            if (mVar.f237a != ((a2.m) nVar.f(0)).f237a) {
                return false;
            }
        }
        return w1.n.q(mVar.f237a) && w1.n.q(mVar2.f237a);
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        int length = nVar.f6354b.length;
        cVar.k(w1.n.m(w1.n.l(((a2.m) nVar.f(length - 2)).f237a, ((a2.m) nVar.f(length - 1)).f237a), hVar));
    }
}
