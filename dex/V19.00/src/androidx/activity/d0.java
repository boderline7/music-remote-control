package androidx.activity;

import androidx.fragment.app.q0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d0 implements androidx.lifecycle.v, d {

    /* renamed from: a, reason: collision with root package name */
    public final androidx.lifecycle.s f612a;

    /* renamed from: b, reason: collision with root package name */
    public final q0 f613b;

    /* renamed from: c, reason: collision with root package name */
    public e0 f614c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g0 f615d;

    public d0(g0 g0Var, androidx.lifecycle.s sVar, q0 q0Var) {
        i5.a.g("lifecycle", sVar);
        this.f615d = g0Var;
        this.f612a = sVar;
        this.f613b = q0Var;
        sVar.a(this);
    }

    @Override // androidx.lifecycle.v
    public final void a(androidx.lifecycle.x xVar, androidx.lifecycle.q qVar) {
        if (qVar != androidx.lifecycle.q.ON_START) {
            if (qVar != androidx.lifecycle.q.ON_STOP) {
                if (qVar == androidx.lifecycle.q.ON_DESTROY) {
                    cancel();
                    return;
                }
                return;
            } else {
                e0 e0Var = this.f614c;
                if (e0Var != null) {
                    e0Var.cancel();
                    return;
                }
                return;
            }
        }
        g0 g0Var = this.f615d;
        g0Var.getClass();
        q0 q0Var = this.f613b;
        i5.a.g("onBackPressedCallback", q0Var);
        g0Var.f624b.a(q0Var);
        e0 e0Var2 = new e0(g0Var, q0Var);
        q0Var.f2040b.add(e0Var2);
        g0Var.c();
        q0Var.f2041c = new f0(1, g0Var);
        this.f614c = e0Var2;
    }

    @Override // androidx.activity.d
    public final void cancel() {
        this.f612a.b(this);
        q0 q0Var = this.f613b;
        q0Var.getClass();
        q0Var.f2040b.remove(this);
        e0 e0Var = this.f614c;
        if (e0Var != null) {
            e0Var.cancel();
        }
        this.f614c = null;
    }
}
