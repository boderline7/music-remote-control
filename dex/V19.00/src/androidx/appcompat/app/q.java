package androidx.appcompat.app;

import android.content.Context;
import android.view.LayoutInflater;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q implements c.b {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ r f846a;

    public q(r rVar) {
        this.f846a = rVar;
    }

    @Override // c.b
    public final void a(Context context) {
        r rVar = this.f846a;
        w delegate = rVar.getDelegate();
        j0 j0Var = (j0) delegate;
        LayoutInflater from = LayoutInflater.from(j0Var.f778k);
        if (from.getFactory() == null) {
            from.setFactory2(j0Var);
        } else {
            boolean z6 = from.getFactory2() instanceof j0;
        }
        rVar.getSavedStateRegistry().a("androidx:appcompat");
        delegate.c();
    }
}
