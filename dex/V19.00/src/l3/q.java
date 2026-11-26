package l3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7405a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ r f7406b;

    public /* synthetic */ q(r rVar, int i7) {
        this.f7405a = i7;
        this.f7406b = rVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f7405a) {
            case 1:
                super.onAnimationEnd(animator);
                r rVar = this.f7406b;
                rVar.c();
                o1.c cVar = rVar.f7417k;
                if (cVar != null) {
                    cVar.a((n) rVar.f7060a);
                    break;
                }
                break;
            default:
                super.onAnimationEnd(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        switch (this.f7405a) {
            case 0:
                super.onAnimationRepeat(animator);
                r rVar = this.f7406b;
                rVar.f7414h = (rVar.f7414h + 1) % rVar.f7413g.f7345c.length;
                rVar.f7415i = true;
                break;
            default:
                super.onAnimationRepeat(animator);
                break;
        }
    }
}
