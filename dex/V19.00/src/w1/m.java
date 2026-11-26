package w1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends h {

    /* renamed from: e, reason: collision with root package name */
    public x[] f9437e;

    public m(a2.s sVar, a2.n nVar) {
        super(k.SPECIAL_FORMAT, sVar, nVar);
        if (nVar.f6354b.length == 0) {
            throw new IllegalArgumentException("registers.size() == 0");
        }
        this.f9437e = null;
    }

    @Override // w1.h
    public final String a() {
        return null;
    }

    @Override // w1.h
    public final int b() {
        n();
        int i7 = 0;
        for (x xVar : this.f9437e) {
            i7 += xVar.b();
        }
        return i7;
    }

    @Override // w1.h
    public final String g() {
        a2.n nVar = this.f9429d;
        int length = nVar.f6354b.length;
        StringBuilder sb = new StringBuilder(100);
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            a2.m mVar = (a2.m) nVar.f(i8);
            x h7 = h.h(a2.s.NO_INFO, a2.m.f(i7, mVar.f238b.b()), mVar);
            if (i8 != 0) {
                sb.append('\n');
            }
            sb.append(h7.g());
            i7 += mVar.e();
        }
        return sb.toString();
    }

    @Override // w1.h
    public final h j(j jVar) {
        throw new RuntimeException("unsupported");
    }

    @Override // w1.h
    public final h k(int i7) {
        return l(this.f9429d.l(i7));
    }

    @Override // w1.h
    public final h l(a2.n nVar) {
        return new m(this.f9428c, nVar);
    }

    @Override // w1.h
    public final void m(e2.c cVar) {
        n();
        for (x xVar : this.f9437e) {
            xVar.m(cVar);
        }
    }

    public final void n() {
        if (this.f9437e != null) {
            return;
        }
        a2.n nVar = this.f9429d;
        int length = nVar.f6354b.length;
        this.f9437e = new x[length];
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            a2.m mVar = (a2.m) nVar.f(i8);
            this.f9437e[i8] = h.h(a2.s.NO_INFO, a2.m.f(i7, mVar.f238b.b()), mVar);
            i7 += mVar.e();
        }
    }
}
