package p0;

import android.view.WindowInsets;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class f2 extends e2 {

    /* renamed from: m, reason: collision with root package name */
    public i0.c f8132m;

    public f2(l2 l2Var, WindowInsets windowInsets) {
        super(l2Var, windowInsets);
        this.f8132m = null;
    }

    @Override // p0.j2
    public l2 b() {
        return l2.h(null, this.f8127c.consumeStableInsets());
    }

    @Override // p0.j2
    public l2 c() {
        return l2.h(null, this.f8127c.consumeSystemWindowInsets());
    }

    @Override // p0.j2
    public final i0.c h() {
        if (this.f8132m == null) {
            WindowInsets windowInsets = this.f8127c;
            this.f8132m = i0.c.b(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.f8132m;
    }

    @Override // p0.j2
    public boolean m() {
        return this.f8127c.isConsumed();
    }

    @Override // p0.j2
    public void r(i0.c cVar) {
        this.f8132m = cVar;
    }
}
