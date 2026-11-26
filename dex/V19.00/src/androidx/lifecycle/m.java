package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m {
    public static final m INSTANCE = new m();
    public static final String TAG_SAVED_STATE_HANDLE_CONTROLLER = "androidx.lifecycle.savedstate.vm.tag";

    public static final void a(e1 e1Var, j1.g gVar, s sVar) {
        i5.a.g("registry", gVar);
        i5.a.g("lifecycle", sVar);
        w0 w0Var = (w0) e1Var.getTag(TAG_SAVED_STATE_HANDLE_CONTROLLER);
        if (w0Var == null || w0Var.f2214c) {
            return;
        }
        w0Var.b(sVar, gVar);
        INSTANCE.getClass();
        b(sVar, gVar);
    }

    public static void b(s sVar, j1.g gVar) {
        r rVar = ((a0) sVar).f2136c;
        if (rVar == r.INITIALIZED || rVar.a(r.STARTED)) {
            gVar.d();
        } else {
            sVar.a(new h(sVar, gVar));
        }
    }
}
