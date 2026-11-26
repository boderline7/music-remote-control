package androidx.fragment.app;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p extends j0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ j0 f2030a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ q f2031b;

    public p(q qVar, j0 j0Var) {
        this.f2031b = qVar;
        this.f2030a = j0Var;
    }

    @Override // androidx.fragment.app.j0
    public final View b(int i7) {
        j0 j0Var = this.f2030a;
        return j0Var.c() ? j0Var.b(i7) : this.f2031b.onFindViewById(i7);
    }

    @Override // androidx.fragment.app.j0
    public final boolean c() {
        return this.f2030a.c() || this.f2031b.onHasView();
    }
}
