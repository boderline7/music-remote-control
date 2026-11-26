package androidx.fragment.app;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class v0 implements u0 {

    /* renamed from: a, reason: collision with root package name */
    public final int f2067a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2068b = 1;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ x0 f2069c;

    public v0(x0 x0Var, int i7) {
        this.f2069c = x0Var;
        this.f2067a = i7;
    }

    @Override // androidx.fragment.app.u0
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        x0 x0Var = this.f2069c;
        z zVar = x0Var.f2120s;
        int i7 = this.f2067a;
        if (zVar == null || i7 >= 0 || !zVar.getChildFragmentManager().H()) {
            return x0Var.I(arrayList, arrayList2, i7, this.f2068b);
        }
        return false;
    }
}
