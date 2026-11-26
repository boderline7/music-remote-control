package androidx.fragment.app;

import android.view.View;
import android.view.Window;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c0 extends l0 implements androidx.lifecycle.p1, androidx.activity.h0, d.h, b1 {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ d0 f1924e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(androidx.appcompat.app.r rVar) {
        super(rVar);
        this.f1924e = rVar;
    }

    @Override // androidx.fragment.app.b1
    public final void a(z zVar) {
        this.f1924e.onAttachFragment(zVar);
    }

    @Override // androidx.fragment.app.j0
    public final View b(int i7) {
        return this.f1924e.findViewById(i7);
    }

    @Override // androidx.fragment.app.j0
    public final boolean c() {
        Window window = this.f1924e.getWindow();
        return (window == null || window.peekDecorView() == null) ? false : true;
    }

    @Override // d.h
    public final d.g getActivityResultRegistry() {
        return this.f1924e.getActivityResultRegistry();
    }

    @Override // androidx.lifecycle.x
    public final androidx.lifecycle.s getLifecycle() {
        return this.f1924e.mFragmentLifecycleRegistry;
    }

    @Override // androidx.activity.h0
    public final androidx.activity.g0 getOnBackPressedDispatcher() {
        return this.f1924e.getOnBackPressedDispatcher();
    }

    @Override // androidx.lifecycle.p1
    public final androidx.lifecycle.o1 getViewModelStore() {
        return this.f1924e.getViewModelStore();
    }
}
