package n1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j0 extends AnimatorListenerAdapter implements q {

    /* renamed from: a, reason: collision with root package name */
    public final View f7618a;

    /* renamed from: b, reason: collision with root package name */
    public final int f7619b;

    /* renamed from: c, reason: collision with root package name */
    public final ViewGroup f7620c;

    /* renamed from: e, reason: collision with root package name */
    public boolean f7622e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f7623f = false;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f7621d = true;

    public j0(View view, int i7) {
        this.f7618a = view;
        this.f7619b = i7;
        this.f7620c = (ViewGroup) view.getParent();
        f(true);
    }

    @Override // n1.q
    public final void a() {
    }

    @Override // n1.q
    public final void b() {
    }

    @Override // n1.q
    public final void c() {
        f(false);
    }

    @Override // n1.q
    public final void d(r rVar) {
        if (!this.f7623f) {
            b0.f7590a.I(this.f7619b, this.f7618a);
            ViewGroup viewGroup = this.f7620c;
            if (viewGroup != null) {
                viewGroup.invalidate();
            }
        }
        f(false);
        rVar.v(this);
    }

    @Override // n1.q
    public final void e() {
        f(true);
    }

    public final void f(boolean z6) {
        ViewGroup viewGroup;
        if (!this.f7621d || this.f7622e == z6 || (viewGroup = this.f7620c) == null) {
            return;
        }
        this.f7622e = z6;
        l6.f.D0(viewGroup, z6);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f7623f = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (!this.f7623f) {
            b0.f7590a.I(this.f7619b, this.f7618a);
            ViewGroup viewGroup = this.f7620c;
            if (viewGroup != null) {
                viewGroup.invalidate();
            }
        }
        f(false);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        if (this.f7623f) {
            return;
        }
        b0.f7590a.I(this.f7619b, this.f7618a);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        if (this.f7623f) {
            return;
        }
        b0.f7590a.I(0, this.f7618a);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
    }
}
