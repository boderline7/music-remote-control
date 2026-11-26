package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b0 implements c.b {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d0 f1922a;

    public b0(androidx.appcompat.app.r rVar) {
        this.f1922a = rVar;
    }

    @Override // c.b
    public final void a(Context context) {
        d0 d0Var = this.f1922a;
        l0 l0Var = d0Var.mFragments.f2004a;
        l0Var.f2010d.b(l0Var, l0Var, null);
        Bundle a7 = d0Var.getSavedStateRegistry().a("android:support:fragments");
        if (a7 != null) {
            Parcelable parcelable = a7.getParcelable("android:support:fragments");
            l0 l0Var2 = d0Var.mFragments.f2004a;
            if (!(l0Var2 instanceof androidx.lifecycle.p1)) {
                throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
            }
            l0Var2.f2010d.M(parcelable);
        }
    }
}
