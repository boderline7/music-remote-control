package x1;

import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class x extends w1.n {
    public static final w1.n THE_ONE = new x();

    @Override // w1.n
    public final int d() {
        return 4;
    }

    @Override // w1.n
    public final BitSet f(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        int length = nVar.f6354b.length;
        BitSet bitSet = new BitSet(length);
        for (int i7 = 0; i7 < length; i7++) {
            a2.m mVar = (a2.m) nVar.f(i7);
            bitSet.set(i7, w1.n.q((mVar.e() + mVar.f237a) - 1));
        }
        return bitSet;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        int length = nVar.f6354b.length;
        int i7 = -1;
        if (length <= 5) {
            int i8 = 0;
            int i9 = 0;
            while (true) {
                if (i8 < length) {
                    i9 += ((a2.m) nVar.f(i8)).e();
                    if (!w1.n.q((r7.e() + r7.f237a) - 1)) {
                        break;
                    }
                    i8++;
                } else if (i9 <= 5) {
                    i7 = i9;
                }
            }
        }
        int length2 = nVar.f6354b.length;
        if (i7 != length2) {
            a2.n nVar2 = new a2.n(i7);
            int i10 = 0;
            for (int i11 = 0; i11 < length2; i11++) {
                a2.m mVar = (a2.m) nVar.f(i11);
                nVar2.g(i10, mVar);
                if (mVar.e() == 2) {
                    nVar2.g(i10 + 1, a2.m.f(mVar.f237a + 1, c2.c.VOID));
                    i10 += 2;
                } else {
                    i10++;
                }
            }
            nVar2.f6364a = false;
            nVar = nVar2;
        }
        return w1.n.n(nVar) + ", " + hVar.d();
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return hVar.c();
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        return false;
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        a.a.u(hVar);
        throw null;
    }
}
