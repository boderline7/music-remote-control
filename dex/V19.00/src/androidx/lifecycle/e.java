package androidx.lifecycle;

import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e implements v {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2166a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final Object f2167b;

    public e(a1 a1Var) {
        this.f2167b = a1Var;
    }

    public e(j[] jVarArr) {
        this.f2167b = jVarArr;
    }

    @Override // androidx.lifecycle.v
    public final void a(x xVar, q qVar) {
        int i7 = this.f2166a;
        Object obj = this.f2167b;
        switch (i7) {
            case 0:
                new HashMap();
                j[] jVarArr = (j[]) obj;
                if (jVarArr.length > 0) {
                    j jVar = jVarArr[0];
                    throw null;
                }
                if (jVarArr.length <= 0) {
                    return;
                }
                j jVar2 = jVarArr[0];
                throw null;
            case 1:
                if (qVar == q.ON_CREATE) {
                    xVar.getLifecycle().b(this);
                    ((a1) obj).b();
                    return;
                } else {
                    throw new IllegalStateException(("Next event must be ON_CREATE, it was " + qVar).toString());
                }
            default:
                a.a.u(obj);
                throw null;
        }
    }
}
