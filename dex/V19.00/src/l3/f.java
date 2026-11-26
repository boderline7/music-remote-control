package l3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7353a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g f7354b;

    public /* synthetic */ f(g gVar, int i7) {
        this.f7353a = i7;
        this.f7354b = gVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f7353a) {
            case 1:
                super.onAnimationEnd(animator);
                g gVar = this.f7354b;
                gVar.c();
                o1.c cVar = gVar.f7367k;
                if (cVar != null) {
                    cVar.a((n) gVar.f7060a);
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
        switch (this.f7353a) {
            case 0:
                super.onAnimationRepeat(animator);
                g gVar = this.f7354b;
                gVar.f7364h = (gVar.f7364h + 4) % gVar.f7363g.f7345c.length;
                break;
            default:
                super.onAnimationRepeat(animator);
                break;
        }
    }
}
