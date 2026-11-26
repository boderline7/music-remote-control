package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class s extends n {

    /* renamed from: c, reason: collision with root package name */
    public final b2.q f10110c;

    public s(b2.q qVar) {
        super(qVar.f2936a);
        this.f10110c = qVar;
    }

    @Override // y1.p
    public final int c() {
        return 8;
    }

    @Override // y1.p
    public final void d(f fVar, e2.c cVar) {
        u uVar = fVar.f10078g;
        b2.q qVar = this.f10110c;
        b2.s sVar = qVar.f2937b;
        int n6 = uVar.n(this.f10100b);
        int m6 = fVar.f10077f.m(sVar.f2938a);
        int h7 = h(fVar);
        if (cVar.d()) {
            cVar.b(0, f() + ' ' + qVar.d());
            cVar.b(2, "  class_idx: ".concat(n1.z.l1(n6)));
            cVar.b(2, String.format("  %-10s %s", i() + ':', n1.z.l1(h7)));
            cVar.b(4, "  name_idx:  ".concat(n1.z.m1(m6)));
        }
        cVar.k(n6);
        cVar.k(h7);
        cVar.j(m6);
    }

    public abstract int h(f fVar);

    public abstract String i();
}
