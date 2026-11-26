package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends i implements Comparable {

    /* renamed from: b, reason: collision with root package name */
    public final b2.r f10097b;

    /* renamed from: c, reason: collision with root package name */
    public final e f10098c;

    public j(b2.r rVar, int i7, w1.g gVar, c2.b bVar) {
        super(i7);
        if (rVar == null) {
            throw new NullPointerException("method == null");
        }
        this.f10097b = rVar;
        if (gVar == null) {
            this.f10098c = null;
        } else {
            this.f10098c = new e(rVar, gVar, (i7 & 8) != 0, bVar);
        }
    }

    @Override // y1.i
    public final int a(f fVar, e2.c cVar, int i7, int i8) {
        w wVar = fVar.f10081j;
        b2.r rVar = this.f10097b;
        int l7 = wVar.l(rVar);
        int i9 = l7 - i7;
        e eVar = this.f10098c;
        int f7 = eVar == null ? 0 : eVar.f();
        boolean z6 = f7 != 0;
        int i10 = this.f10094a;
        if (z6 != ((i10 & 1280) == 0)) {
            throw new UnsupportedOperationException("code vs. access_flags mismatch");
        }
        if (cVar.d()) {
            cVar.b(0, String.format("  [%x] %s", Integer.valueOf(i8), rVar.d()));
            cVar.b(n1.z.o1(i9), "    method_idx:   ".concat(n1.z.m1(l7)));
            cVar.b(n1.z.o1(i10), "    access_flags: " + a2.a.a(i10, a2.a.METHOD_FLAGS, 3));
            cVar.b(n1.z.o1(f7), "    code_off:     ".concat(n1.z.m1(f7)));
        }
        cVar.m(i9);
        cVar.m(i10);
        cVar.m(f7);
        return l7;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f10097b.compareTo(((j) obj).f10097b);
    }

    @Override // e2.i
    public final String d() {
        return this.f10097b.d();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof j) && this.f10097b.compareTo(((j) obj).f10097b) == 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(j.class.getName());
        sb.append('{');
        sb.append(n1.z.l1(this.f10094a));
        sb.append(' ');
        sb.append(this.f10097b);
        e eVar = this.f10098c;
        if (eVar != null) {
            sb.append(' ');
            sb.append(eVar);
        }
        sb.append('}');
        return sb.toString();
    }
}
