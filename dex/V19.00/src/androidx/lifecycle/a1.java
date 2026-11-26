package androidx.lifecycle;

import android.os.Bundle;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a1 implements j1.f {

    /* renamed from: a, reason: collision with root package name */
    public final j1.g f2143a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2144b;

    /* renamed from: c, reason: collision with root package name */
    public Bundle f2145c;

    /* renamed from: d, reason: collision with root package name */
    public final b5.b f2146d;

    public a1(j1.g gVar, androidx.fragment.app.d0 d0Var) {
        i5.a.g("savedStateRegistry", gVar);
        this.f2143a = gVar;
        this.f2146d = new b5.b(new z0(0, d0Var));
    }

    @Override // j1.f
    public final Bundle a() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f2145c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry entry : ((b1) this.f2146d.a()).f2151a.entrySet()) {
            String str = (String) entry.getKey();
            Bundle a7 = ((v0) entry.getValue()).f2211e.a();
            if (!i5.a.c(a7, Bundle.EMPTY)) {
                bundle.putBundle(str, a7);
            }
        }
        this.f2144b = false;
        return bundle;
    }

    public final void b() {
        if (this.f2144b) {
            return;
        }
        Bundle a7 = this.f2143a.a("androidx.lifecycle.internal.SavedStateHandlesProvider");
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f2145c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if (a7 != null) {
            bundle.putAll(a7);
        }
        this.f2145c = bundle;
        this.f2144b = true;
    }
}
