package x1;

import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v extends w1.n {
    public static final w1.n THE_ONE = new v();

    public static a2.n u(a2.n nVar) {
        int v6 = v(nVar);
        int length = nVar.f6354b.length;
        if (v6 == length) {
            return nVar;
        }
        a2.n nVar2 = new a2.n(v6);
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            a2.m mVar = (a2.m) nVar.f(i8);
            nVar2.g(i7, mVar);
            if (mVar.e() == 2) {
                nVar2.g(i7 + 1, a2.m.f(mVar.f237a + 1, c2.c.VOID));
                i7 += 2;
            } else {
                i7++;
            }
        }
        nVar2.f6364a = false;
        return nVar2;
    }

    public static int v(a2.n nVar) {
        int length = nVar.f6354b.length;
        if (length > 5) {
            return -1;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            i7 += ((a2.m) nVar.f(i8)).e();
            if (!w1.n.q((r5.e() + r5.f237a) - 1)) {
                return -1;
            }
        }
        if (i7 <= 5) {
            return i7;
        }
        return -1;
    }

    @Override // w1.n
    public final int d() {
        return 3;
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
        return w1.n.n(u(hVar.f9429d)) + ", " + hVar.d();
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return hVar.c();
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        if (!(hVar instanceof w1.f)) {
            return false;
        }
        w1.f fVar = (w1.f) hVar;
        if (!w1.n.r(fVar.n())) {
            return false;
        }
        b2.a aVar = fVar.f9418e;
        return ((aVar instanceof b2.r) || (aVar instanceof b2.v)) && v(fVar.f9429d) >= 0;
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        int n6 = ((w1.f) hVar).n();
        a2.n u6 = u(hVar.f9429d);
        int length = u6.f6354b.length;
        int i7 = length > 0 ? ((a2.m) u6.f(0)).f237a : 0;
        int i8 = length > 1 ? ((a2.m) u6.f(1)).f237a : 0;
        int i9 = length > 2 ? ((a2.m) u6.f(2)).f237a : 0;
        int i10 = length > 3 ? ((a2.m) u6.f(3)).f237a : 0;
        short m6 = w1.n.m(w1.n.l(length > 4 ? ((a2.m) u6.f(4)).f237a : 0, length), hVar);
        short s6 = (short) n6;
        if ((i7 & 15) != i7) {
            throw new IllegalArgumentException("n0 out of range 0..15");
        }
        if ((i8 & 15) != i8) {
            throw new IllegalArgumentException("n1 out of range 0..15");
        }
        if ((i9 & 15) != i9) {
            throw new IllegalArgumentException("n2 out of range 0..15");
        }
        if ((i10 & 15) != i10) {
            throw new IllegalArgumentException("n3 out of range 0..15");
        }
        cVar.k(m6);
        cVar.k(s6);
        cVar.k((short) ((i8 << 4) | i7 | (i9 << 8) | (i10 << 12)));
    }
}
