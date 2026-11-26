package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d0 extends e0 implements v {

    /* renamed from: e, reason: collision with root package name */
    public final x f2162e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f0 f2163f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d0(f0 f0Var, x xVar, h0 h0Var) {
        super(f0Var, h0Var);
        this.f2163f = f0Var;
        this.f2162e = xVar;
    }

    @Override // androidx.lifecycle.v
    public final void a(x xVar, q qVar) {
        x xVar2 = this.f2162e;
        r rVar = ((a0) xVar2.getLifecycle()).f2136c;
        if (rVar == r.DESTROYED) {
            this.f2163f.g(this.f2168a);
            return;
        }
        r rVar2 = null;
        while (rVar2 != rVar) {
            b(e());
            rVar2 = rVar;
            rVar = ((a0) xVar2.getLifecycle()).f2136c;
        }
    }

    @Override // androidx.lifecycle.e0
    public final void c() {
        this.f2162e.getLifecycle().b(this);
    }

    @Override // androidx.lifecycle.e0
    public final boolean d(x xVar) {
        return this.f2162e == xVar;
    }

    @Override // androidx.lifecycle.e0
    public final boolean e() {
        return ((a0) this.f2162e.getLifecycle()).f2136c.a(r.STARTED);
    }
}
