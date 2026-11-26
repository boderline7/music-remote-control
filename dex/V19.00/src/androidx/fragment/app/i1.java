package androidx.fragment.app;

import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1993a = 2;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1994b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1995c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f1996d;

    public i1(l lVar, ArrayList arrayList, v1 v1Var) {
        this.f1996d = lVar;
        this.f1994b = arrayList;
        this.f1995c = v1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f1993a;
        Object obj = this.f1996d;
        Object obj2 = this.f1995c;
        Object obj3 = this.f1994b;
        switch (i7) {
            case 0:
                ((p0) obj3).c((z) obj2, (l0.d) obj);
                break;
            case 1:
                ((p0) obj3).c((z) obj2, (l0.d) obj);
                break;
            default:
                List list = (List) obj3;
                v1 v1Var = (v1) obj2;
                if (list.contains(v1Var)) {
                    list.remove(v1Var);
                    ((l) obj).getClass();
                    v1Var.f2070a.a(v1Var.f2072c.mView);
                    break;
                }
                break;
        }
    }
}
