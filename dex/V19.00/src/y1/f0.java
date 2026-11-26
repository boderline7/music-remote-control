package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f0 extends n {
    @Override // y1.p
    public final void a(f fVar) {
        fVar.f10077f.p(this.f10100b.g());
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_TYPE_ID_ITEM;
    }

    @Override // y1.p
    public final int c() {
        return 4;
    }

    @Override // y1.p
    public final void d(f fVar, e2.c cVar) {
        b2.u g7 = this.f10100b.g();
        int m6 = fVar.f10077f.m(g7);
        if (cVar.d()) {
            cVar.b(0, f() + ' ' + g7.d());
            cVar.b(4, "  descriptor_idx: ".concat(n1.z.m1(m6)));
        }
        cVar.j(m6);
    }
}
