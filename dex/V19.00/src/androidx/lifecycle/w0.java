package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class w0 implements v {

    /* renamed from: a, reason: collision with root package name */
    public final String f2212a;

    /* renamed from: b, reason: collision with root package name */
    public final v0 f2213b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2214c;

    public w0(String str, v0 v0Var) {
        this.f2212a = str;
        this.f2213b = v0Var;
    }

    @Override // androidx.lifecycle.v
    public final void a(x xVar, q qVar) {
        if (qVar == q.ON_DESTROY) {
            this.f2214c = false;
            xVar.getLifecycle().b(this);
        }
    }

    public final void b(s sVar, j1.g gVar) {
        i5.a.g("registry", gVar);
        i5.a.g("lifecycle", sVar);
        if (!(!this.f2214c)) {
            throw new IllegalStateException("Already attached to lifecycleOwner".toString());
        }
        this.f2214c = true;
        sVar.a(this);
        gVar.c(this.f2212a, this.f2213b.f2211e);
    }
}
