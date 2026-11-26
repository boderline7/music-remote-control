package x1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w extends w1.n {
    public static final w1.n THE_ONE = new w();

    @Override // w1.n
    public final int d() {
        return 3;
    }

    @Override // w1.n
    public final String g(w1.h hVar) {
        return w1.n.o(hVar.f9429d) + ", " + hVar.d();
    }

    @Override // w1.n
    public final String h(w1.h hVar) {
        return hVar.c();
    }

    @Override // w1.n
    public final boolean i(w1.h hVar) {
        boolean z6;
        if (!(hVar instanceof w1.f)) {
            return false;
        }
        w1.f fVar = (w1.f) hVar;
        if (!w1.n.r(fVar.n())) {
            return false;
        }
        b2.a aVar = fVar.f9418e;
        if (!(aVar instanceof b2.r) && !(aVar instanceof b2.v)) {
            return false;
        }
        a2.n nVar = fVar.f9429d;
        Object[] objArr = nVar.f6354b;
        int length = objArr.length;
        if (objArr.length != 0) {
            int length2 = objArr.length;
            if (length2 >= 2) {
                int i7 = ((a2.m) nVar.f(0)).f237a;
                for (int i8 = 0; i8 < length2; i8++) {
                    a2.m mVar = (a2.m) nVar.f(i8);
                    if (mVar.f237a != i7) {
                        z6 = false;
                        break;
                    }
                    i7 += mVar.e();
                }
            }
            z6 = true;
            if (!z6 || !w1.n.r(((a2.m) nVar.f(0)).f237a)) {
                return false;
            }
            int length3 = nVar.f6354b.length;
            int i9 = 0;
            for (int i10 = 0; i10 < length3; i10++) {
                i9 += nVar.a(i10).e();
            }
            if (!w1.n.p(i9)) {
                return false;
            }
        }
        return true;
    }

    @Override // w1.n
    public final void t(e2.c cVar, w1.h hVar) {
        a2.n nVar = hVar.f9429d;
        int n6 = ((w1.f) hVar).n();
        int i7 = nVar.f6354b.length == 0 ? 0 : ((a2.m) nVar.f(0)).f237a;
        int length = nVar.f6354b.length;
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            i8 += nVar.a(i9).e();
        }
        cVar.k(w1.n.m(i8, hVar));
        cVar.k((short) n6);
        cVar.k((short) i7);
    }
}
