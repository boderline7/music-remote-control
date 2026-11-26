package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a0 implements j1.f {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d0 f1913a;

    public a0(androidx.appcompat.app.r rVar) {
        this.f1913a = rVar;
    }

    @Override // j1.f
    public final Bundle a() {
        Bundle bundle = new Bundle();
        d0 d0Var = this.f1913a;
        d0Var.markFragmentsCreated();
        d0Var.mFragmentLifecycleRegistry.e(androidx.lifecycle.q.ON_STOP);
        Parcelable N = d0Var.mFragments.f2004a.f2010d.N();
        if (N != null) {
            bundle.putParcelable("android:support:fragments", N);
        }
        return bundle;
    }
}
