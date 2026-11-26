package u2;

import android.view.View;
import com.google.android.material.behavior.SwipeDismissBehavior;
import p0.d1;
import p0.l0;
import u0.e;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final View f9310a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SwipeDismissBehavior f9311b;

    public b(SwipeDismissBehavior swipeDismissBehavior, View view, boolean z6) {
        this.f9311b = swipeDismissBehavior;
        this.f9310a = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        e eVar = this.f9311b.f3319a;
        if (eVar == null || !eVar.g()) {
            return;
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.m(this.f9310a, this);
    }
}
