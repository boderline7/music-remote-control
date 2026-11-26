package androidx.fragment.app;

import java.util.HashSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class j {

    /* renamed from: a, reason: collision with root package name */
    public final v1 f1997a;

    /* renamed from: b, reason: collision with root package name */
    public final l0.d f1998b;

    public j(v1 v1Var, l0.d dVar) {
        this.f1997a = v1Var;
        this.f1998b = dVar;
    }

    public final void a() {
        v1 v1Var = this.f1997a;
        HashSet hashSet = v1Var.f2074e;
        if (hashSet.remove(this.f1998b) && hashSet.isEmpty()) {
            v1Var.b();
        }
    }

    public final boolean b() {
        x1 x1Var;
        v1 v1Var = this.f1997a;
        x1 c7 = x1.c(v1Var.f2072c.mView);
        x1 x1Var2 = v1Var.f2070a;
        return c7 == x1Var2 || !(c7 == (x1Var = x1.VISIBLE) || x1Var2 == x1Var);
    }
}
