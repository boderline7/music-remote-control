package g3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class p extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public boolean f6490a;

    /* renamed from: b, reason: collision with root package name */
    public float f6491b;

    /* renamed from: c, reason: collision with root package name */
    public float f6492c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ q f6493d;

    public p(s sVar) {
        this.f6493d = sVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        float f7 = (int) this.f6492c;
        p3.j jVar = this.f6493d.f6495b;
        if (jVar != null) {
            jVar.n(f7);
        }
        this.f6490a = false;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f7;
        float f8;
        boolean z6 = this.f6490a;
        q qVar = this.f6493d;
        if (!z6) {
            p3.j jVar = qVar.f6495b;
            float f9 = CropImageView.DEFAULT_ASPECT_RATIO;
            this.f6491b = jVar == null ? 0.0f : jVar.f8263a.f8254n;
            o oVar = (o) this;
            int i7 = oVar.f6488e;
            q qVar2 = oVar.f6489f;
            switch (i7) {
                case 0:
                    break;
                case 1:
                    f7 = qVar2.f6501h;
                    f8 = qVar2.f6502i;
                    f9 = f7 + f8;
                    break;
                case 2:
                    f7 = qVar2.f6501h;
                    f8 = qVar2.f6503j;
                    f9 = f7 + f8;
                    break;
                default:
                    f9 = qVar2.f6501h;
                    break;
            }
            this.f6492c = f9;
            this.f6490a = true;
        }
        float f10 = this.f6491b;
        float animatedFraction = (int) ((valueAnimator.getAnimatedFraction() * (this.f6492c - f10)) + f10);
        p3.j jVar2 = qVar.f6495b;
        if (jVar2 != null) {
            jVar2.n(animatedFraction);
        }
    }
}
