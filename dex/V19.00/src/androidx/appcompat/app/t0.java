package androidx.appcompat.app;

import android.view.MenuItem;
import android.view.Window;
import androidx.appcompat.widget.u3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t0 implements u3, k.m {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ v0 f855a;

    public /* synthetic */ t0(v0 v0Var) {
        this.f855a = v0Var;
    }

    @Override // k.m
    public final boolean l(k.o oVar, MenuItem menuItem) {
        return false;
    }

    @Override // k.m
    public final void s(k.o oVar) {
        v0 v0Var = this.f855a;
        boolean q6 = v0Var.f858a.f1442a.q();
        Window.Callback callback = v0Var.f859b;
        if (q6) {
            callback.onPanelClosed(108, oVar);
        } else if (callback.onPreparePanel(0, null, oVar)) {
            callback.onMenuOpened(108, oVar);
        }
    }
}
