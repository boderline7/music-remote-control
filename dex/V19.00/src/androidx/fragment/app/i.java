package androidx.fragment.app;

import android.content.Context;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i extends j {

    /* renamed from: c, reason: collision with root package name */
    public boolean f1990c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1991d;

    /* renamed from: e, reason: collision with root package name */
    public g0 f1992e;

    public final g0 c(Context context) {
        if (this.f1991d) {
            return this.f1992e;
        }
        v1 v1Var = this.f1997a;
        g0 a7 = i0.a(context, v1Var.f2072c, v1Var.f2070a == x1.VISIBLE, this.f1990c);
        this.f1992e = a7;
        this.f1991d = true;
        return a7;
    }
}
