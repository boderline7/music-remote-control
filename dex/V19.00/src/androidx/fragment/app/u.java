package androidx.fragment.app;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class u implements n.a, l0.c {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ z f2059a;

    public /* synthetic */ u(z zVar) {
        this.f2059a = zVar;
    }

    @Override // n.a
    public final d.g a() {
        z zVar = this.f2059a;
        Object obj = zVar.mHost;
        return obj instanceof d.h ? ((d.h) obj).getActivityResultRegistry() : zVar.requireActivity().getActivityResultRegistry();
    }

    @Override // l0.c
    public final void h() {
        z zVar = this.f2059a;
        if (zVar.getAnimatingAway() != null) {
            View animatingAway = zVar.getAnimatingAway();
            zVar.setAnimatingAway(null);
            animatingAway.clearAnimation();
        }
        zVar.setAnimator(null);
    }
}
