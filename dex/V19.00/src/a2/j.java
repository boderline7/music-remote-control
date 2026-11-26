package a2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends g {
    public j(p pVar, s sVar, m mVar, n nVar) {
        super(pVar, sVar, mVar, nVar);
        int i7 = pVar.f245e;
        if (i7 == 5 || i7 == 6) {
            throw new IllegalArgumentException("opcode with invalid branchingness: " + i7);
        }
        if (mVar != null && i7 != 1) {
            throw new IllegalArgumentException("can't mix branchingness with result");
        }
    }

    @Override // a2.g
    public final void a(f fVar) {
        fVar.c(this);
    }

    @Override // a2.g
    public final c2.e e() {
        return c2.b.EMPTY;
    }
}
