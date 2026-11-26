package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class z {

    /* renamed from: a, reason: collision with root package name */
    public r f2215a;

    /* renamed from: b, reason: collision with root package name */
    public v f2216b;

    public final void a(x xVar, q qVar) {
        r a7 = qVar.a();
        y yVar = a0.Companion;
        r rVar = this.f2215a;
        yVar.getClass();
        i5.a.g("state1", rVar);
        if (a7 != null && a7.compareTo(rVar) < 0) {
            rVar = a7;
        }
        this.f2215a = rVar;
        this.f2216b.a(xVar, qVar);
        this.f2215a = a7;
    }
}
