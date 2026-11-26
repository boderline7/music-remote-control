package w1;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends l {

    /* renamed from: e, reason: collision with root package name */
    public final b2.a f9418e;

    /* renamed from: f, reason: collision with root package name */
    public int f9419f;

    /* renamed from: g, reason: collision with root package name */
    public int f9420g;

    public f(j jVar, a2.s sVar, a2.n nVar, b2.a aVar) {
        super(jVar, sVar, nVar);
        if (aVar == null) {
            throw new NullPointerException("constant == null");
        }
        this.f9418e = aVar;
        this.f9419f = -1;
        this.f9420g = -1;
    }

    @Override // w1.h
    public final String a() {
        return this.f9418e.d();
    }

    @Override // w1.h
    public final String c() {
        if (this.f9419f < 0) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        StringBuilder sb = new StringBuilder(20);
        sb.append(this.f9418e.f());
        sb.append('@');
        int i7 = this.f9419f;
        if (i7 < 65536) {
            sb.append(n1.z.l1(i7));
        } else {
            sb.append(n1.z.m1(i7));
        }
        return sb.toString();
    }

    @Override // w1.h
    public final String d() {
        b2.a aVar = this.f9418e;
        return aVar instanceof b2.u ? ((b2.u) aVar).g() : aVar.d();
    }

    @Override // w1.h
    public final h j(j jVar) {
        f fVar = new f(jVar, this.f9428c, this.f9429d, this.f9418e);
        int i7 = this.f9419f;
        if (i7 >= 0) {
            fVar.p(i7);
        }
        int i8 = this.f9420g;
        if (i8 >= 0) {
            fVar.o(i8);
        }
        return fVar;
    }

    @Override // w1.h
    public final h l(a2.n nVar) {
        f fVar = new f(this.f9427b, this.f9428c, nVar, this.f9418e);
        int i7 = this.f9419f;
        if (i7 >= 0) {
            fVar.p(i7);
        }
        int i8 = this.f9420g;
        if (i8 >= 0) {
            fVar.o(i8);
        }
        return fVar;
    }

    public final int n() {
        int i7 = this.f9419f;
        if (i7 >= 0) {
            return i7;
        }
        throw new IllegalStateException("index not yet set for " + this.f9418e);
    }

    public final void o(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("index < 0");
        }
        if (this.f9420g >= 0) {
            throw new IllegalStateException("class index already set");
        }
        this.f9420g = i7;
    }

    public final void p(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("index < 0");
        }
        if (this.f9419f >= 0) {
            throw new IllegalStateException("index already set");
        }
        this.f9419f = i7;
    }
}
