package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e0 extends o implements Comparable {

    /* renamed from: b, reason: collision with root package name */
    public final b2.u f10070b;

    /* renamed from: c, reason: collision with root package name */
    public d0 f10071c;

    public e0(b2.u uVar) {
        if (uVar == null) {
            throw new NullPointerException("value == null");
        }
        this.f10070b = uVar;
        this.f10071c = null;
    }

    @Override // y1.p
    public final void a(f fVar) {
        if (this.f10071c == null) {
            z zVar = fVar.f10076e;
            d0 d0Var = new d0(this.f10070b);
            this.f10071c = d0Var;
            zVar.k(d0Var);
        }
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_STRING_ID_ITEM;
    }

    @Override // y1.p
    public final int c() {
        return 4;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f10070b.compareTo(((e0) obj).f10070b);
    }

    @Override // y1.p
    public final void d(f fVar, e2.c cVar) {
        String str;
        int f7 = this.f10071c.f();
        if (cVar.d()) {
            StringBuilder sb = new StringBuilder();
            sb.append(f());
            sb.append(' ');
            String d7 = this.f10070b.d();
            if (d7.length() <= 98) {
                str = s5.h0.FRAGMENT_ENCODE_SET;
            } else {
                d7 = d7.substring(0, 95);
                str = "...";
            }
            sb.append("\"" + d7 + str + '\"');
            cVar.b(0, sb.toString());
            cVar.b(4, "  string_data_off: ".concat(n1.z.m1(f7)));
        }
        cVar.j(f7);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof e0) {
            return this.f10070b.equals(((e0) obj).f10070b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f10070b.f2940a.hashCode();
    }
}
