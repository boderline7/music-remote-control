package androidx.fragment.app;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t implements androidx.lifecycle.v {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ z f2055a;

    public t(z zVar) {
        this.f2055a = zVar;
    }

    @Override // androidx.lifecycle.v
    public final void a(androidx.lifecycle.x xVar, androidx.lifecycle.q qVar) {
        View view;
        if (qVar != androidx.lifecycle.q.ON_STOP || (view = this.f2055a.mView) == null) {
            return;
        }
        view.cancelPendingInputEvents();
    }
}
