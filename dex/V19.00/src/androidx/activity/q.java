package androidx.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.window.OnBackInvokedDispatcher;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class q extends Dialog implements androidx.lifecycle.x, h0, j1.j {

    /* renamed from: a, reason: collision with root package name */
    public androidx.lifecycle.a0 f644a;

    /* renamed from: b, reason: collision with root package name */
    public final j1.i f645b;

    /* renamed from: c, reason: collision with root package name */
    public final g0 f646c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(Context context, int i7) {
        super(context, i7);
        i5.a.g("context", context);
        j1.i.Companion.getClass();
        this.f645b = j1.h.a(this);
        this.f646c = new g0(new e(2, this));
    }

    public static void a(q qVar) {
        i5.a.g("this$0", qVar);
        super.onBackPressed();
    }

    public final androidx.lifecycle.a0 b() {
        androidx.lifecycle.a0 a0Var = this.f644a;
        if (a0Var != null) {
            return a0Var;
        }
        androidx.lifecycle.a0 a0Var2 = new androidx.lifecycle.a0(this);
        this.f644a = a0Var2;
        return a0Var2;
    }

    @Override // androidx.lifecycle.x
    public final androidx.lifecycle.s getLifecycle() {
        return b();
    }

    @Override // androidx.activity.h0
    public final g0 getOnBackPressedDispatcher() {
        return this.f646c;
    }

    @Override // j1.j
    public final j1.g getSavedStateRegistry() {
        return this.f645b.f6950b;
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        this.f646c.a();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            i5.a.f("onBackInvokedDispatcher", onBackInvokedDispatcher);
            g0 g0Var = this.f646c;
            g0Var.getClass();
            g0Var.f627e = onBackInvokedDispatcher;
            g0Var.b(g0Var.f629g);
        }
        this.f645b.b(bundle);
        b().e(androidx.lifecycle.q.ON_CREATE);
    }

    @Override // android.app.Dialog
    public final Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        i5.a.f("super.onSaveInstanceState()", onSaveInstanceState);
        this.f645b.c(onSaveInstanceState);
        return onSaveInstanceState;
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        b().e(androidx.lifecycle.q.ON_RESUME);
    }

    @Override // android.app.Dialog
    public void onStop() {
        b().e(androidx.lifecycle.q.ON_DESTROY);
        this.f644a = null;
        super.onStop();
    }
}
