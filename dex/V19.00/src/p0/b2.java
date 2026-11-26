package p0;

import android.view.WindowInsets;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class b2 extends d2 {

    /* renamed from: c, reason: collision with root package name */
    public final WindowInsets.Builder f8101c;

    public b2() {
        this.f8101c = n1.d0.b();
    }

    public b2(l2 l2Var) {
        super(l2Var);
        WindowInsets g7 = l2Var.g();
        this.f8101c = g7 != null ? n1.d0.c(g7) : n1.d0.b();
    }

    @Override // p0.d2
    public l2 b() {
        WindowInsets build;
        a();
        build = this.f8101c.build();
        l2 h7 = l2.h(null, build);
        h7.f8161a.p(this.f8117b);
        return h7;
    }

    @Override // p0.d2
    public void d(i0.c cVar) {
        this.f8101c.setMandatorySystemGestureInsets(cVar.d());
    }

    @Override // p0.d2
    public void e(i0.c cVar) {
        this.f8101c.setStableInsets(cVar.d());
    }

    @Override // p0.d2
    public void f(i0.c cVar) {
        this.f8101c.setSystemGestureInsets(cVar.d());
    }

    @Override // p0.d2
    public void g(i0.c cVar) {
        this.f8101c.setSystemWindowInsets(cVar.d());
    }

    @Override // p0.d2
    public void h(i0.c cVar) {
        this.f8101c.setTappableElementInsets(cVar.d());
    }
}
