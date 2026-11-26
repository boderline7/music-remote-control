package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b0 extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2382a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2383b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f2384c;

    public b0(View view) {
        this.f2382a = 1;
        this.f2383b = false;
        this.f2384c = view;
    }

    public b0(d0 d0Var) {
        this.f2382a = 0;
        this.f2384c = d0Var;
        this.f2383b = false;
    }

    public b0(com.google.android.material.search.n nVar, boolean z6) {
        this.f2382a = 2;
        this.f2384c = nVar;
        this.f2383b = z6;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        switch (this.f2382a) {
            case 0:
                this.f2383b = true;
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i7 = this.f2382a;
        Object obj = this.f2384c;
        switch (i7) {
            case 0:
                if (!this.f2383b) {
                    d0 d0Var = (d0) obj;
                    if (((Float) d0Var.f2424z.getAnimatedValue()).floatValue() != CropImageView.DEFAULT_ASPECT_RATIO) {
                        d0Var.A = 2;
                        d0Var.f2417s.invalidate();
                        break;
                    } else {
                        d0Var.A = 0;
                        d0Var.i(0);
                        break;
                    }
                } else {
                    this.f2383b = false;
                    break;
                }
            case 1:
                View view = (View) obj;
                n1.b0.f7590a.O(view, 1.0f);
                if (this.f2383b) {
                    view.setLayerType(0, null);
                    break;
                }
                break;
            default:
                com.google.android.material.search.n nVar = (com.google.android.material.search.n) obj;
                com.google.android.material.search.n.a(nVar, this.f2383b ? 1.0f : 0.0f);
                ClippableRoundedCornerLayout clippableRoundedCornerLayout = nVar.f4103c;
                clippableRoundedCornerLayout.f3765a = null;
                clippableRoundedCornerLayout.f3766b = CropImageView.DEFAULT_ASPECT_RATIO;
                clippableRoundedCornerLayout.invalidate();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i7 = this.f2382a;
        Object obj = this.f2384c;
        switch (i7) {
            case 1:
                View view = (View) obj;
                int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                if (p0.l0.h(view) && view.getLayerType() == 0) {
                    this.f2383b = true;
                    view.setLayerType(2, null);
                    break;
                }
                break;
            case 2:
                com.google.android.material.search.n.a((com.google.android.material.search.n) obj, this.f2383b ? CropImageView.DEFAULT_ASPECT_RATIO : 1.0f);
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
