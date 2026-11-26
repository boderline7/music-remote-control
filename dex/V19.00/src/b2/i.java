package b2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends q {
    @Override // c2.d
    public final c2.c b() {
        return c2.c.g(this.f2937b.f2939b.f2940a);
    }

    @Override // b2.a
    public final int e(a aVar) {
        q qVar = (q) aVar;
        int compareTo = this.f2936a.compareTo(qVar.f2936a);
        s sVar = this.f2937b;
        if (compareTo == 0) {
            compareTo = sVar.f2938a.compareTo(qVar.f2937b.f2938a);
        }
        return compareTo != 0 ? compareTo : sVar.f2939b.compareTo(((i) aVar).f2937b.f2939b);
    }

    @Override // b2.a
    public final String f() {
        return "field";
    }
}
