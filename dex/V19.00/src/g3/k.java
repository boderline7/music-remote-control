package g3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f6471a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ i6.i f6472b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ q f6473c;

    public k(q qVar, boolean z6, i6.i iVar) {
        this.f6473c = qVar;
        this.f6471a = z6;
        this.f6472b = iVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        q qVar = this.f6473c;
        qVar.f6511r = 0;
        qVar.f6505l = null;
        i6.i iVar = this.f6472b;
        if (iVar != null) {
            ((l6.f) iVar.f6848b).g0();
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        q qVar = this.f6473c;
        qVar.f6515v.a(0, this.f6471a);
        qVar.f6511r = 2;
        qVar.f6505l = animator;
    }
}
