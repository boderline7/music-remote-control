package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends a0 {

    /* renamed from: e, reason: collision with root package name */
    public final b2.c f10090e;

    /* renamed from: f, reason: collision with root package name */
    public byte[] f10091f;

    public g(b2.c cVar) {
        super(1, -1);
        this.f10090e = cVar;
        this.f10091f = null;
    }

    @Override // y1.p
    public final void a(f fVar) {
        i6.i.a(fVar, this.f10090e);
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_ENCODED_ARRAY_ITEM;
    }

    @Override // y1.a0
    public final int e(a0 a0Var) {
        return this.f10090e.compareTo(((g) a0Var).f10090e);
    }

    public final int hashCode() {
        return this.f10090e.hashCode();
    }

    @Override // y1.a0
    public final void i(c0 c0Var, int i7) {
        e2.c cVar = new e2.c();
        new i6.i(c0Var.f10052b, cVar).w(this.f10090e, false);
        int i8 = cVar.f6349c;
        byte[] bArr = new byte[i8];
        System.arraycopy(cVar.f6348b, 0, bArr, 0, i8);
        this.f10091f = bArr;
        j(i8);
    }

    @Override // y1.a0
    public final void k(f fVar, e2.c cVar) {
        if (!cVar.d()) {
            cVar.h(this.f10091f);
            return;
        }
        cVar.b(0, g() + " encoded array");
        new i6.i(fVar, cVar).w(this.f10090e, true);
    }
}
