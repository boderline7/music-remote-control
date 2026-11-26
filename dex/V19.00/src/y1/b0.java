package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b0 extends o {

    /* renamed from: b, reason: collision with root package name */
    public final c2.a f10040b;

    /* renamed from: c, reason: collision with root package name */
    public final b2.u f10041c;

    /* renamed from: d, reason: collision with root package name */
    public g0 f10042d;

    public b0(c2.a aVar) {
        if (aVar == null) {
            throw new NullPointerException("prototype == null");
        }
        this.f10040b = aVar;
        c2.b bVar = aVar.f3037c;
        int length = bVar.f6354b.length;
        StringBuilder sb = new StringBuilder(length + 1);
        char charAt = aVar.f3036b.f3040a.charAt(0);
        sb.append(charAt == '[' ? 'L' : charAt);
        for (int i7 = 0; i7 < length; i7++) {
            char charAt2 = ((c2.c) bVar.f(i7)).f3040a.charAt(0);
            if (charAt2 == '[') {
                charAt2 = 'L';
            }
            sb.append(charAt2);
        }
        this.f10041c = new b2.u(sb.toString());
        this.f10042d = bVar.f6354b.length == 0 ? null : new g0(bVar);
    }

    @Override // y1.p
    public final void a(f fVar) {
        u uVar = fVar.f10077f;
        fVar.f10078g.r(this.f10040b.f3036b);
        uVar.p(this.f10041c);
        g0 g0Var = this.f10042d;
        if (g0Var != null) {
            this.f10042d = (g0) fVar.f10074c.l(g0Var);
        }
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_PROTO_ID_ITEM;
    }

    @Override // y1.p
    public final int c() {
        return 12;
    }

    @Override // y1.p
    public final void d(f fVar, e2.c cVar) {
        u uVar = fVar.f10077f;
        b2.u uVar2 = this.f10041c;
        int m6 = uVar.m(uVar2);
        c2.a aVar = this.f10040b;
        int o6 = fVar.f10078g.o(aVar.f3036b);
        g0 g0Var = this.f10042d;
        int f7 = g0Var == null ? 0 : g0Var.f();
        if (cVar.d()) {
            StringBuilder sb = new StringBuilder();
            c2.c cVar2 = aVar.f3036b;
            sb.append(cVar2.d());
            sb.append(" proto(");
            c2.b bVar = aVar.f3037c;
            int length = bVar.f6354b.length;
            for (int i7 = 0; i7 < length; i7++) {
                if (i7 != 0) {
                    sb.append(", ");
                }
                sb.append(((c2.c) bVar.f(i7)).d());
            }
            sb.append(")");
            cVar.b(0, f() + ' ' + sb.toString());
            cVar.b(4, "  shorty_idx:      " + n1.z.m1(m6) + " // " + uVar2.g());
            cVar.b(4, "  return_type_idx: " + n1.z.m1(o6) + " // " + cVar2.d());
            cVar.b(4, "  parameters_off:  ".concat(n1.z.m1(f7)));
        }
        cVar.j(m6);
        cVar.j(o6);
        cVar.j(f7);
    }
}
