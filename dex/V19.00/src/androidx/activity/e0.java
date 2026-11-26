package androidx.activity;

import androidx.fragment.app.q0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e0 implements d {

    /* renamed from: a, reason: collision with root package name */
    public final q0 f618a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g0 f619b;

    public e0(g0 g0Var, q0 q0Var) {
        this.f619b = g0Var;
        this.f618a = q0Var;
    }

    @Override // androidx.activity.d
    public final void cancel() {
        g0 g0Var = this.f619b;
        c5.k kVar = g0Var.f624b;
        q0 q0Var = this.f618a;
        kVar.remove(q0Var);
        if (i5.a.c(g0Var.f625c, q0Var)) {
            q0Var.getClass();
            g0Var.f625c = null;
        }
        q0Var.getClass();
        q0Var.f2040b.remove(this);
        h5.a aVar = q0Var.f2041c;
        if (aVar != null) {
            aVar.a();
        }
        q0Var.f2041c = null;
    }
}
