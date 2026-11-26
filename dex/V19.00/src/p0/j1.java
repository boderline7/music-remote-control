package p0;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j1 extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8153a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f8154b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f8155c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f8156d;

    public /* synthetic */ j1(Object obj, Object obj2, Object obj3, int i7) {
        this.f8153a = i7;
        this.f8156d = obj;
        this.f8155c = obj2;
        this.f8154b = obj3;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        switch (this.f8153a) {
            case 0:
                ((m1) this.f8155c).b((View) this.f8154b);
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i7 = this.f8153a;
        Object obj = this.f8155c;
        switch (i7) {
            case 0:
                ((m1) obj).a();
                break;
            case 1:
                y1 y1Var = (y1) obj;
                y1Var.f8207a.d(1.0f);
                u1.e((View) this.f8154b, y1Var);
                break;
            default:
                ((a3.g) obj).setCircularRevealOverlayDrawable(null);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i7 = this.f8153a;
        Object obj = this.f8155c;
        switch (i7) {
            case 0:
                ((m1) obj).c();
                break;
            case 1:
            default:
                super.onAnimationStart(animator);
                break;
            case 2:
                ((a3.g) obj).setCircularRevealOverlayDrawable((Drawable) this.f8154b);
                break;
        }
    }
}
