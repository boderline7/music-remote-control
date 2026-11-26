package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import java.lang.reflect.Constructor;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c1 extends n1 implements j1 {

    /* renamed from: a, reason: collision with root package name */
    public final Application f2154a;

    /* renamed from: b, reason: collision with root package name */
    public final h1 f2155b;

    /* renamed from: c, reason: collision with root package name */
    public final Bundle f2156c;

    /* renamed from: d, reason: collision with root package name */
    public final s f2157d;

    /* renamed from: e, reason: collision with root package name */
    public final j1.g f2158e;

    public c1(Application application, j1.j jVar, Bundle bundle) {
        h1 h1Var;
        i5.a.g("owner", jVar);
        this.f2158e = jVar.getSavedStateRegistry();
        this.f2157d = jVar.getLifecycle();
        this.f2156c = bundle;
        this.f2154a = application;
        if (application != null) {
            h1.Companion.getClass();
            if (h1.f2186c == null) {
                h1.f2186c = new h1(application);
            }
            h1Var = h1.f2186c;
            i5.a.d(h1Var);
        } else {
            h1Var = new h1(null, 0);
        }
        this.f2155b = h1Var;
    }

    @Override // androidx.lifecycle.j1
    public final e1 a(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return c(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.j1
    public final e1 b(Class cls, e1.d dVar) {
        String str = (String) dVar.a(m1.VIEW_MODEL_KEY);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        if (dVar.a(y0.SAVED_STATE_REGISTRY_OWNER_KEY) == null || dVar.a(y0.VIEW_MODEL_STORE_OWNER_KEY) == null) {
            if (this.f2157d != null) {
                return c(str, cls);
            }
            throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        }
        Application application = (Application) dVar.a(h1.APPLICATION_KEY);
        boolean isAssignableFrom = a.class.isAssignableFrom(cls);
        Constructor a7 = (!isAssignableFrom || application == null) ? d1.a(d1.f2165b, cls) : d1.a(d1.f2164a, cls);
        return a7 == null ? this.f2155b.b(cls, dVar) : (!isAssignableFrom || application == null) ? d1.b(cls, a7, y0.a(dVar)) : d1.b(cls, a7, application, y0.a(dVar));
    }

    public final e1 c(String str, Class cls) {
        s sVar = this.f2157d;
        if (sVar == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean isAssignableFrom = a.class.isAssignableFrom(cls);
        Application application = this.f2154a;
        Constructor a7 = (!isAssignableFrom || application == null) ? d1.a(d1.f2165b, cls) : d1.a(d1.f2164a, cls);
        if (a7 == null) {
            if (application != null) {
                return this.f2155b.a(cls);
            }
            m1.Companion.getClass();
            return l1.a().a(cls);
        }
        j1.g gVar = this.f2158e;
        i5.a.d(gVar);
        Bundle a8 = gVar.a(str);
        u0 u0Var = v0.Companion;
        Bundle bundle = this.f2156c;
        u0Var.getClass();
        v0 a9 = u0.a(a8, bundle);
        w0 w0Var = new w0(str, a9);
        w0Var.b(sVar, gVar);
        m.INSTANCE.getClass();
        m.b(sVar, gVar);
        e1 b7 = (!isAssignableFrom || application == null) ? d1.b(cls, a7, a9) : d1.b(cls, a7, application, a9);
        b7.setTagIfAbsent(m.TAG_SAVED_STATE_HANDLE_CONTROLLER, w0Var);
        return b7;
    }
}
