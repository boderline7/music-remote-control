package androidx.activity;

import androidx.fragment.app.q0;
import java.util.ListIterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class y extends i5.h implements h5.a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f659a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g0 f660b;

    public y(g0 g0Var, int i7) {
        this.f659a = i7;
        this.f660b = g0Var;
    }

    @Override // h5.a
    public final /* bridge */ /* synthetic */ Object a() {
        switch (this.f659a) {
            case 0:
                c();
                break;
            case 1:
                c();
                break;
            default:
                c();
                break;
        }
        return b5.d.INSTANCE;
    }

    public final void c() {
        Object obj;
        int i7 = this.f659a;
        g0 g0Var = this.f660b;
        switch (i7) {
            case 0:
                g0Var.a();
                break;
            case 1:
                c5.k kVar = g0Var.f624b;
                kVar.getClass();
                ListIterator listIterator = kVar.listIterator(kVar.f3078c);
                while (true) {
                    if (listIterator.hasPrevious()) {
                        obj = listIterator.previous();
                        if (((q0) obj).f2039a) {
                        }
                    } else {
                        obj = null;
                    }
                }
                g0Var.f625c = null;
                break;
            default:
                g0Var.a();
                break;
        }
    }
}
