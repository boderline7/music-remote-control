package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2546a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ o f2547b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f2548c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ View f2549d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ q f2550e;

    public /* synthetic */ n(q qVar, o oVar, ViewPropertyAnimator viewPropertyAnimator, View view, int i7) {
        this.f2546a = i7;
        this.f2550e = qVar;
        this.f2547b = oVar;
        this.f2548c = viewPropertyAnimator;
        this.f2549d = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i7 = this.f2546a;
        q qVar = this.f2550e;
        o oVar = this.f2547b;
        View view = this.f2549d;
        ViewPropertyAnimator viewPropertyAnimator = this.f2548c;
        switch (i7) {
            case 0:
                viewPropertyAnimator.setListener(null);
                view.setAlpha(1.0f);
                view.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
                view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                qVar.c(oVar.f2559a);
                qVar.f2611r.remove(oVar.f2559a);
                qVar.i();
                break;
            default:
                viewPropertyAnimator.setListener(null);
                view.setAlpha(1.0f);
                view.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
                view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                qVar.c(oVar.f2560b);
                qVar.f2611r.remove(oVar.f2560b);
                qVar.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i7 = this.f2546a;
        q qVar = this.f2550e;
        o oVar = this.f2547b;
        switch (i7) {
            case 0:
                j2 j2Var = oVar.f2559a;
                qVar.getClass();
                break;
            default:
                j2 j2Var2 = oVar.f2560b;
                qVar.getClass();
                break;
        }
    }
}
