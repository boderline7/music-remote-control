package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g0 extends a0 {

    /* renamed from: e, reason: collision with root package name */
    public final c2.e f10092e;

    public g0(c2.b bVar) {
        super(4, (bVar.f6354b.length * 2) + 4);
        this.f10092e = bVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // y1.p
    public final void a(f fVar) {
        u uVar = fVar.f10078g;
        c2.e eVar = this.f10092e;
        int length = ((e2.d) eVar).f6354b.length;
        for (int i7 = 0; i7 < length; i7++) {
            uVar.r(eVar.a(i7));
        }
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_TYPE_LIST;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // y1.a0
    public final int e(a0 a0Var) {
        c2.e eVar = ((g0) a0Var).f10092e;
        c2.b bVar = c2.b.EMPTY;
        c2.e eVar2 = this.f10092e;
        int length = ((e2.d) eVar2).f6354b.length;
        int length2 = ((e2.d) eVar).f6354b.length;
        int min = Math.min(length, length2);
        for (int i7 = 0; i7 < min; i7++) {
            int compareTo = eVar2.a(i7).f3040a.compareTo(eVar.a(i7).f3040a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        if (length == length2) {
            return 0;
        }
        return length < length2 ? -1 : 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int hashCode() {
        c2.b bVar = c2.b.EMPTY;
        c2.e eVar = this.f10092e;
        int length = ((e2.d) eVar).f6354b.length;
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            i7 = (i7 * 31) + eVar.a(i8).f3040a.hashCode();
        }
        return i7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // y1.a0
    public final void k(f fVar, e2.c cVar) {
        u uVar = fVar.f10078g;
        c2.e eVar = this.f10092e;
        int length = ((e2.d) eVar).f6354b.length;
        if (cVar.d()) {
            cVar.b(0, g() + " type_list");
            cVar.b(4, "  size: ".concat(n1.z.m1(length)));
            for (int i7 = 0; i7 < length; i7++) {
                c2.c a7 = eVar.a(i7);
                cVar.b(2, "  " + n1.z.l1(uVar.o(a7)) + " // " + a7.d());
            }
        }
        cVar.j(length);
        for (int i8 = 0; i8 < length; i8++) {
            cVar.k(uVar.o(eVar.a(i8)));
        }
    }
}
