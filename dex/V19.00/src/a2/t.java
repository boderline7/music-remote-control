package a2;

import b2.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class t extends d {

    /* renamed from: f, reason: collision with root package name */
    public final c2.e f252f;

    public t(p pVar, s sVar, n nVar, c2.b bVar, w wVar) {
        super(pVar, sVar, null, nVar, wVar);
        int i7 = pVar.f245e;
        if (i7 != 6) {
            throw new IllegalArgumentException(a.a.d("opcode with invalid branchingness: ", i7));
        }
        if (bVar == null) {
            throw new NullPointerException("catches == null");
        }
        this.f252f = bVar;
    }

    @Override // a2.g
    public final void a(f fVar) {
        fVar.f(this);
    }

    @Override // a2.g
    public final c2.e e() {
        return this.f252f;
    }

    @Override // a2.d, a2.g
    public final String f() {
        b2.a aVar = this.f227e;
        String d7 = aVar.d();
        if (aVar instanceof b2.u) {
            d7 = ((b2.u) aVar).g();
        }
        return d7 + " " + u.g(this.f252f);
    }
}
