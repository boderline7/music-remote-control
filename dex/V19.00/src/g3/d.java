package g3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public boolean f6457a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ a f6458b;

    public d(a aVar) {
        this.f6458b = aVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f6457a = true;
        this.f6458b.d();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        a aVar = this.f6458b;
        aVar.e();
        if (this.f6457a) {
            return;
        }
        aVar.g();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f6458b.f(animator);
        this.f6457a = false;
    }
}
