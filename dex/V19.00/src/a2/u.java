package a2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u extends g {

    /* renamed from: e, reason: collision with root package name */
    public final c2.e f253e;

    public u(p pVar, s sVar, n nVar, c2.b bVar) {
        super(pVar, sVar, null, nVar);
        int i7 = pVar.f245e;
        if (i7 != 6) {
            throw new IllegalArgumentException(a.a.d("opcode with invalid branchingness: ", i7));
        }
        if (bVar == null) {
            throw new NullPointerException("catches == null");
        }
        this.f253e = bVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String g(c2.e eVar) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("catch");
        int length = ((e2.d) eVar).f6354b.length;
        for (int i7 = 0; i7 < length; i7++) {
            sb.append(" ");
            sb.append(eVar.a(i7).d());
        }
        return sb.toString();
    }

    @Override // a2.g
    public final void a(f fVar) {
        fVar.g(this);
    }

    @Override // a2.g
    public final c2.e e() {
        return this.f253e;
    }

    @Override // a2.g
    public final String f() {
        return g(this.f253e);
    }
}
