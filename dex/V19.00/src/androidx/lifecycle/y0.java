package androidx.lifecycle;

import android.os.Bundle;
import java.util.LinkedHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class y0 {
    public static final e1.b SAVED_STATE_REGISTRY_OWNER_KEY = new a6.k0();
    public static final e1.b VIEW_MODEL_STORE_OWNER_KEY = new a6.k0();
    public static final e1.b DEFAULT_ARGS_KEY = new a6.k0();

    public static final v0 a(e1.d dVar) {
        j1.j jVar = (j1.j) dVar.a(SAVED_STATE_REGISTRY_OWNER_KEY);
        if (jVar == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
        }
        p1 p1Var = (p1) dVar.a(VIEW_MODEL_STORE_OWNER_KEY);
        if (p1Var == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        Bundle bundle = (Bundle) dVar.a(DEFAULT_ARGS_KEY);
        String str = (String) dVar.a(m1.VIEW_MODEL_KEY);
        if (str == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
        }
        j1.f b7 = jVar.getSavedStateRegistry().b();
        a1 a1Var = b7 instanceof a1 ? (a1) b7 : null;
        if (a1Var == null) {
            throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
        }
        LinkedHashMap linkedHashMap = b(p1Var).f2151a;
        v0 v0Var = (v0) linkedHashMap.get(str);
        if (v0Var != null) {
            return v0Var;
        }
        u0 u0Var = v0.Companion;
        a1Var.b();
        Bundle bundle2 = a1Var.f2145c;
        Bundle bundle3 = bundle2 != null ? bundle2.getBundle(str) : null;
        Bundle bundle4 = a1Var.f2145c;
        if (bundle4 != null) {
            bundle4.remove(str);
        }
        Bundle bundle5 = a1Var.f2145c;
        if (bundle5 != null && bundle5.isEmpty()) {
            a1Var.f2145c = null;
        }
        u0Var.getClass();
        v0 a7 = u0.a(bundle3, bundle);
        linkedHashMap.put(str, a7);
        return a7;
    }

    public static final b1 b(p1 p1Var) {
        i5.a.g("<this>", p1Var);
        return (b1) new androidx.appcompat.app.g(p1Var, new x0()).D("androidx.lifecycle.internal.SavedStateHandlesVM", b1.class);
    }
}
