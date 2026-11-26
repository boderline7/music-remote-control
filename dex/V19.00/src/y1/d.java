package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends o {

    /* renamed from: b, reason: collision with root package name */
    public final b2.v f10056b;

    /* renamed from: c, reason: collision with root package name */
    public final int f10057c;

    /* renamed from: d, reason: collision with root package name */
    public final b2.v f10058d;

    /* renamed from: e, reason: collision with root package name */
    public g0 f10059e;

    /* renamed from: f, reason: collision with root package name */
    public final b2.u f10060f;

    /* renamed from: g, reason: collision with root package name */
    public final c f10061g;

    /* renamed from: h, reason: collision with root package name */
    public g f10062h;

    /* renamed from: i, reason: collision with root package name */
    public final a f10063i;

    public d(b2.v vVar, int i7, b2.v vVar2, c2.b bVar, b2.u uVar) {
        if (vVar == null) {
            throw new NullPointerException("thisClass == null");
        }
        if (bVar == null) {
            throw new NullPointerException("interfaces == null");
        }
        this.f10056b = vVar;
        this.f10057c = i7;
        this.f10058d = vVar2;
        this.f10059e = bVar.f6354b.length == 0 ? null : new g0(bVar);
        this.f10060f = uVar;
        this.f10061g = new c(vVar);
        this.f10062h = null;
        this.f10063i = new a(4, -1);
    }

    @Override // y1.p
    public final void a(f fVar) {
        u uVar = fVar.f10078g;
        uVar.q(this.f10056b);
        c cVar = this.f10061g;
        if (!cVar.p()) {
            fVar.f10083l.k(cVar);
            b2.c o6 = cVar.o();
            if (o6 != null) {
                this.f10062h = (g) fVar.f10086o.l(new g(o6));
            }
        }
        b2.v vVar = this.f10058d;
        if (vVar != null) {
            uVar.q(vVar);
        }
        g0 g0Var = this.f10059e;
        if (g0Var != null) {
            this.f10059e = (g0) fVar.f10074c.l(g0Var);
        }
        b2.u uVar2 = this.f10060f;
        if (uVar2 != null) {
            fVar.f10077f.p(uVar2);
        }
        this.f10063i.getClass();
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_CLASS_DEF_ITEM;
    }

    @Override // y1.p
    public final int c() {
        return 32;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // y1.p
    public final void d(f fVar, e2.c cVar) {
        boolean d7 = cVar.d();
        u uVar = fVar.f10078g;
        b2.v vVar = this.f10056b;
        int n6 = uVar.n(vVar);
        b2.v vVar2 = this.f10058d;
        int n7 = vVar2 == null ? -1 : uVar.n(vVar2);
        g0 g0Var = this.f10059e;
        int f7 = g0Var == null ? 0 : g0Var.f();
        this.f10063i.getClass();
        b2.u uVar2 = this.f10060f;
        int m6 = uVar2 != null ? fVar.f10077f.m(uVar2) : -1;
        c cVar2 = this.f10061g;
        int f8 = cVar2.p() ? 0 : cVar2.f();
        g gVar = this.f10062h;
        int f9 = gVar == null ? 0 : gVar.f();
        int i7 = this.f10057c;
        if (d7) {
            cVar.b(0, f() + ' ' + vVar.f2943a.d());
            cVar.b(4, "  class_idx:           ".concat(n1.z.m1(n6)));
            StringBuilder sb = new StringBuilder("  access_flags:        ");
            sb.append(a2.a.a(i7, a2.a.CLASS_FLAGS, 1));
            cVar.b(4, sb.toString());
            StringBuilder sb2 = new StringBuilder("  superclass_idx:      ");
            sb2.append(n1.z.m1(n7));
            sb2.append(" // ");
            sb2.append(vVar2 == null ? "<none>" : vVar2.f2943a.d());
            cVar.b(4, sb2.toString());
            cVar.b(4, "  interfaces_off:      ".concat(n1.z.m1(f7)));
            if (f7 != 0) {
                c2.e eVar = this.f10059e.f10092e;
                int length = ((e2.d) eVar).f6354b.length;
                for (int i8 = 0; i8 < length; i8++) {
                    cVar.b(0, "    " + eVar.a(i8).d());
                }
            }
            StringBuilder sb3 = new StringBuilder("  source_file_idx:     ");
            sb3.append(n1.z.m1(m6));
            sb3.append(" // ");
            sb3.append(uVar2 != null ? uVar2.d() : "<none>");
            cVar.b(4, sb3.toString());
            cVar.b(4, "  annotations_off:     ".concat(n1.z.m1(0)));
            cVar.b(4, "  class_data_off:      ".concat(n1.z.m1(f8)));
            cVar.b(4, "  static_values_off:   ".concat(n1.z.m1(f9)));
        }
        cVar.j(n6);
        cVar.j(i7);
        cVar.j(n7);
        cVar.j(f7);
        cVar.j(m6);
        cVar.j(0);
        cVar.j(f8);
        cVar.j(f9);
    }
}
