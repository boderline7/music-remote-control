package a2;

import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n extends e2.d implements c2.e {
    public static final n EMPTY = new n(0);

    public static n i(m mVar) {
        n nVar = new n(1);
        nVar.g(0, mVar);
        return nVar;
    }

    public static n j(m mVar, m mVar2) {
        n nVar = new n(2);
        nVar.g(0, mVar);
        nVar.g(1, mVar2);
        return nVar;
    }

    @Override // e2.d, c2.e
    public final c2.c a(int i7) {
        c2.c b7 = ((m) f(i7)).f238b.b();
        b7.getClass();
        return b7;
    }

    public final n k(boolean z6, BitSet bitSet) {
        Object[] objArr = this.f6354b;
        int length = objArr.length;
        if (length == 0) {
            return this;
        }
        n nVar = new n(objArr.length);
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            m mVar = (m) f(i7);
            if (bitSet == null || !bitSet.get(i7)) {
                if (mVar.f237a != i8) {
                    mVar = m.f(i8, mVar.f238b);
                }
                if (!z6) {
                    i8 += mVar.e();
                }
            }
            nVar.g(i7, mVar);
            i7++;
            z6 = false;
        }
        if (!this.f6364a) {
            nVar.f6364a = false;
        }
        return nVar;
    }

    public final n l(int i7) {
        int i8;
        int i9;
        int length = this.f6354b.length;
        if (length == 0) {
            return this;
        }
        n nVar = new n(length);
        for (int i10 = 0; i10 < length; i10++) {
            m mVar = (m) f(i10);
            if (i7 != 0 && i8 != (i9 = (i8 = mVar.f237a) + i7)) {
                mVar = m.f(i9, mVar.f238b);
            }
            nVar.g(i10, mVar);
        }
        if (!this.f6364a) {
            nVar.f6364a = false;
        }
        return nVar;
    }
}
