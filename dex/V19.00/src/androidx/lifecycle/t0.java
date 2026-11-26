package androidx.lifecycle;

import android.os.Bundle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class t0 implements j1.f {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2203a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2204b;

    public /* synthetic */ t0(int i7, Object obj) {
        this.f2203a = i7;
        this.f2204b = obj;
    }

    @Override // j1.f
    public final Bundle a() {
        int i7 = this.f2203a;
        Object obj = this.f2204b;
        switch (i7) {
            case 0:
                return v0.a((v0) obj);
            case 1:
                return v0.a((v0) obj);
            default:
                return androidx.activity.o.a((androidx.activity.o) obj);
        }
    }
}
