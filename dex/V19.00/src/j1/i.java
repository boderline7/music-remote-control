package j1;

import android.os.Bundle;
import androidx.lifecycle.a0;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import androidx.lifecycle.s;
import androidx.lifecycle.v;
import androidx.lifecycle.x;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i {
    public static final h Companion = new h();

    /* renamed from: a, reason: collision with root package name */
    public final j f6949a;

    /* renamed from: b, reason: collision with root package name */
    public final g f6950b = new g();

    /* renamed from: c, reason: collision with root package name */
    public boolean f6951c;

    public i(j jVar) {
        this.f6949a = jVar;
    }

    public final void a() {
        j jVar = this.f6949a;
        s lifecycle = jVar.getLifecycle();
        if (((a0) lifecycle).f2136c != r.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage".toString());
        }
        lifecycle.a(new c(jVar));
        final g gVar = this.f6950b;
        gVar.getClass();
        if (!(!gVar.f6944b)) {
            throw new IllegalStateException("SavedStateRegistry was already attached.".toString());
        }
        lifecycle.a(new v() { // from class: j1.d
            @Override // androidx.lifecycle.v
            public final void a(x xVar, q qVar) {
                g gVar2 = g.this;
                i5.a.g("this$0", gVar2);
                if (qVar == q.ON_START) {
                    gVar2.f6948f = true;
                } else if (qVar == q.ON_STOP) {
                    gVar2.f6948f = false;
                }
            }
        });
        gVar.f6944b = true;
        this.f6951c = true;
    }

    public final void b(Bundle bundle) {
        if (!this.f6951c) {
            a();
        }
        a0 a0Var = (a0) this.f6949a.getLifecycle();
        if (!(!a0Var.f2136c.a(r.STARTED))) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + a0Var.f2136c).toString());
        }
        g gVar = this.f6950b;
        if (!gVar.f6944b) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).".toString());
        }
        if (!(!gVar.f6946d)) {
            throw new IllegalStateException("SavedStateRegistry was already restored.".toString());
        }
        gVar.f6945c = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
        gVar.f6946d = true;
    }

    public final void c(Bundle bundle) {
        i5.a.g("outBundle", bundle);
        g gVar = this.f6950b;
        gVar.getClass();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = gVar.f6945c;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        m.g gVar2 = gVar.f6943a;
        gVar2.getClass();
        m.d dVar = new m.d(gVar2);
        gVar2.f7484c.put(dVar, Boolean.FALSE);
        while (dVar.hasNext()) {
            Map.Entry entry = (Map.Entry) dVar.next();
            bundle2.putBundle((String) entry.getKey(), ((f) entry.getValue()).a());
        }
        if (bundle2.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
