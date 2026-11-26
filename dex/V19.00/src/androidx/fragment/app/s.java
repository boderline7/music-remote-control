package androidx.fragment.app;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s extends j0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ z f2052a;

    public s(z zVar) {
        this.f2052a = zVar;
    }

    @Override // androidx.fragment.app.j0
    public final View b(int i7) {
        z zVar = this.f2052a;
        View view = zVar.mView;
        if (view != null) {
            return view.findViewById(i7);
        }
        throw new IllegalStateException("Fragment " + zVar + " does not have a view");
    }

    @Override // androidx.fragment.app.j0
    public final boolean c() {
        return this.f2052a.mView != null;
    }
}
