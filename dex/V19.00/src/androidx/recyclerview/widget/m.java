package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ j2 f2530a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f2531b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f2532c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2533d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f2534e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ q f2535f;

    public m(q qVar, j2 j2Var, int i7, View view, int i8, ViewPropertyAnimator viewPropertyAnimator) {
        this.f2535f = qVar;
        this.f2530a = j2Var;
        this.f2531b = i7;
        this.f2532c = view;
        this.f2533d = i8;
        this.f2534e = viewPropertyAnimator;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i7 = this.f2531b;
        View view = this.f2532c;
        if (i7 != 0) {
            view.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
        }
        if (this.f2533d != 0) {
            view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f2534e.setListener(null);
        q qVar = this.f2535f;
        j2 j2Var = this.f2530a;
        qVar.c(j2Var);
        qVar.f2609p.remove(j2Var);
        qVar.i();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f2535f.getClass();
    }
}
