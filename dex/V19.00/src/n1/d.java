package n1;

import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends s {

    /* renamed from: a, reason: collision with root package name */
    public boolean f7593a = false;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f7594b;

    public d(ViewGroup viewGroup) {
        this.f7594b = viewGroup;
    }

    @Override // n1.s, n1.q
    public final void a() {
        l6.f.D0(this.f7594b, false);
        this.f7593a = true;
    }

    @Override // n1.s, n1.q
    public final void c() {
        l6.f.D0(this.f7594b, false);
    }

    @Override // n1.q
    public final void d(r rVar) {
        if (!this.f7593a) {
            l6.f.D0(this.f7594b, false);
        }
        rVar.v(this);
    }

    @Override // n1.s, n1.q
    public final void e() {
        l6.f.D0(this.f7594b, true);
    }
}
