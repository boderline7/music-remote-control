package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2523a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j2 f2524b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f2525c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f2526d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ q f2527e;

    public l(q qVar, j2 j2Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.f2527e = qVar;
        this.f2524b = j2Var;
        this.f2525c = view;
        this.f2526d = viewPropertyAnimator;
    }

    public l(q qVar, j2 j2Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f2527e = qVar;
        this.f2524b = j2Var;
        this.f2526d = viewPropertyAnimator;
        this.f2525c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        switch (this.f2523a) {
            case 1:
                this.f2525c.setAlpha(1.0f);
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i7 = this.f2523a;
        j2 j2Var = this.f2524b;
        q qVar = this.f2527e;
        ViewPropertyAnimator viewPropertyAnimator = this.f2526d;
        switch (i7) {
            case 0:
                viewPropertyAnimator.setListener(null);
                this.f2525c.setAlpha(1.0f);
                qVar.c(j2Var);
                qVar.f2610q.remove(j2Var);
                qVar.i();
                break;
            default:
                viewPropertyAnimator.setListener(null);
                qVar.c(j2Var);
                qVar.f2608o.remove(j2Var);
                qVar.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i7 = this.f2523a;
        q qVar = this.f2527e;
        switch (i7) {
            case 0:
                qVar.getClass();
                break;
            default:
                qVar.getClass();
                break;
        }
    }
}
