package a2;

import b2.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends d {
    public i(p pVar, s sVar, m mVar, n nVar, w wVar) {
        super(pVar, sVar, mVar, nVar, wVar);
        int i7 = pVar.f245e;
        if (i7 != 1) {
            throw new IllegalArgumentException(a.a.d("opcode with invalid branchingness: ", i7));
        }
    }

    @Override // a2.g
    public final void a(f fVar) {
        fVar.e(this);
    }

    @Override // a2.g
    public final c2.e e() {
        return c2.b.EMPTY;
    }
}
