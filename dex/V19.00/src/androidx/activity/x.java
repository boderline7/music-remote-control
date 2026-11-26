package androidx.activity;

import androidx.fragment.app.q0;
import java.util.ListIterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class x extends i5.h implements h5.l {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f657a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g0 f658b;

    public x(g0 g0Var, int i7) {
        this.f657a = i7;
        this.f658b = g0Var;
    }

    @Override // h5.l
    public final /* bridge */ /* synthetic */ Object b(Object obj) {
        switch (this.f657a) {
            case 0:
                c((c) obj);
                break;
            default:
                c((c) obj);
                break;
        }
        return b5.d.INSTANCE;
    }

    public final void c(c cVar) {
        int i7 = this.f657a;
        Object obj = null;
        g0 g0Var = this.f658b;
        switch (i7) {
            case 0:
                i5.a.g("backEvent", cVar);
                c5.k kVar = g0Var.f624b;
                kVar.getClass();
                ListIterator listIterator = kVar.listIterator(kVar.f3078c);
                while (true) {
                    if (listIterator.hasPrevious()) {
                        Object previous = listIterator.previous();
                        if (((q0) previous).f2039a) {
                            obj = previous;
                        }
                    }
                }
                g0Var.f625c = (q0) obj;
                break;
            default:
                i5.a.g("backEvent", cVar);
                c5.k kVar2 = g0Var.f624b;
                kVar2.getClass();
                ListIterator listIterator2 = kVar2.listIterator(kVar2.f3078c);
                while (true) {
                    if (listIterator2.hasPrevious()) {
                        Object previous2 = listIterator2.previous();
                        if (((q0) previous2).f2039a) {
                            obj = previous2;
                        }
                    }
                }
                break;
        }
    }
}
