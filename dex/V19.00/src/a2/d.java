package a2;

import b2.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d extends g {

    /* renamed from: e, reason: collision with root package name */
    public final b2.a f227e;

    public d(p pVar, s sVar, m mVar, n nVar, w wVar) {
        super(pVar, sVar, mVar, nVar);
        if (wVar == null) {
            throw new NullPointerException("cst == null");
        }
        this.f227e = wVar;
    }

    @Override // a2.g
    public String f() {
        return this.f227e.d();
    }
}
