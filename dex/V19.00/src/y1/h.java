package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h extends i implements Comparable {

    /* renamed from: b, reason: collision with root package name */
    public final b2.i f10093b;

    public h(b2.i iVar, int i7) {
        super(i7);
        if (iVar == null) {
            throw new NullPointerException("field == null");
        }
        this.f10093b = iVar;
    }

    @Override // y1.i
    public final int a(f fVar, e2.c cVar, int i7, int i8) {
        l lVar = fVar.f10080i;
        b2.i iVar = this.f10093b;
        int l7 = lVar.l(iVar);
        int i9 = l7 - i7;
        boolean d7 = cVar.d();
        int i10 = this.f10094a;
        if (d7) {
            cVar.b(0, String.format("  [%x] %s", Integer.valueOf(i8), iVar.d()));
            cVar.b(n1.z.o1(i9), "    field_idx:    ".concat(n1.z.m1(l7)));
            cVar.b(n1.z.o1(i10), "    access_flags: " + a2.a.a(i10, a2.a.FIELD_FLAGS, 2));
        }
        cVar.m(i9);
        cVar.m(i10);
        return l7;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f10093b.compareTo(((h) obj).f10093b);
    }

    @Override // e2.i
    public final String d() {
        return this.f10093b.d();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof h) && this.f10093b.compareTo(((h) obj).f10093b) == 0;
    }

    public final int hashCode() {
        return this.f10093b.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(h.class.getName());
        sb.append('{');
        sb.append(n1.z.l1(this.f10094a));
        sb.append(' ');
        sb.append(this.f10093b);
        sb.append('}');
        return sb.toString();
    }
}
