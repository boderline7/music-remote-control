package androidx.fragment.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r1 implements androidx.lifecycle.k, j1.j, androidx.lifecycle.p1 {

    /* renamed from: a, reason: collision with root package name */
    public final z f2047a;

    /* renamed from: b, reason: collision with root package name */
    public final androidx.lifecycle.o1 f2048b;

    /* renamed from: c, reason: collision with root package name */
    public androidx.lifecycle.j1 f2049c;

    /* renamed from: d, reason: collision with root package name */
    public androidx.lifecycle.a0 f2050d = null;

    /* renamed from: e, reason: collision with root package name */
    public j1.i f2051e = null;

    public r1(z zVar, androidx.lifecycle.o1 o1Var) {
        this.f2047a = zVar;
        this.f2048b = o1Var;
    }

    public final void a(androidx.lifecycle.q qVar) {
        this.f2050d.e(qVar);
    }

    public final void b() {
        if (this.f2050d == null) {
            this.f2050d = new androidx.lifecycle.a0(this);
            j1.i.Companion.getClass();
            this.f2051e = j1.h.a(this);
        }
    }

    @Override // androidx.lifecycle.k
    public final androidx.lifecycle.j1 getDefaultViewModelProviderFactory() {
        Application application;
        z zVar = this.f2047a;
        androidx.lifecycle.j1 defaultViewModelProviderFactory = zVar.getDefaultViewModelProviderFactory();
        if (!defaultViewModelProviderFactory.equals(zVar.mDefaultFactory)) {
            this.f2049c = defaultViewModelProviderFactory;
            return defaultViewModelProviderFactory;
        }
        if (this.f2049c == null) {
            Context applicationContext = zVar.requireContext().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    application = null;
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            this.f2049c = new androidx.lifecycle.c1(application, this, zVar.getArguments());
        }
        return this.f2049c;
    }

    @Override // androidx.lifecycle.x
    public final androidx.lifecycle.s getLifecycle() {
        b();
        return this.f2050d;
    }

    @Override // j1.j
    public final j1.g getSavedStateRegistry() {
        b();
        return this.f2051e.f6950b;
    }

    @Override // androidx.lifecycle.p1
    public final androidx.lifecycle.o1 getViewModelStore() {
        b();
        return this.f2048b;
    }
}
