package androidx.activity;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i implements androidx.lifecycle.v {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f631a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ o f632b;

    public /* synthetic */ i(o oVar, int i7) {
        this.f631a = i7;
        this.f632b = oVar;
    }

    @Override // androidx.lifecycle.v
    public final void a(androidx.lifecycle.x xVar, androidx.lifecycle.q qVar) {
        g0 g0Var;
        switch (this.f631a) {
            case 0:
                if (qVar == androidx.lifecycle.q.ON_DESTROY) {
                    this.f632b.mContextAwareHelper.f3005b = null;
                    if (!this.f632b.isChangingConfigurations()) {
                        this.f632b.getViewModelStore().a();
                    }
                    ((n) this.f632b.mReportFullyDrawnExecutor).b();
                    break;
                }
                break;
            case 1:
                if (qVar == androidx.lifecycle.q.ON_STOP) {
                    Window window = this.f632b.getWindow();
                    View peekDecorView = window != null ? window.peekDecorView() : null;
                    if (peekDecorView != null) {
                        peekDecorView.cancelPendingInputEvents();
                        break;
                    }
                }
                break;
            case 2:
                o oVar = this.f632b;
                oVar.ensureViewModelStore();
                oVar.getLifecycle().b(this);
                break;
            default:
                if (qVar == androidx.lifecycle.q.ON_CREATE && Build.VERSION.SDK_INT >= 33) {
                    g0Var = this.f632b.mOnBackPressedDispatcher;
                    OnBackInvokedDispatcher a7 = k.a((o) xVar);
                    g0Var.getClass();
                    i5.a.g("invoker", a7);
                    g0Var.f627e = a7;
                    g0Var.b(g0Var.f629g);
                    break;
                }
                break;
        }
    }
}
