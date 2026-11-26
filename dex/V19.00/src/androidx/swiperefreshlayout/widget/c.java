package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c implements Animator.AnimatorListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f2758a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e f2759b;

    public c(e eVar, d dVar) {
        this.f2759b = eVar;
        this.f2758a = dVar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        e eVar = this.f2759b;
        d dVar = this.f2758a;
        eVar.a(1.0f, dVar, true);
        dVar.f2770k = dVar.f2764e;
        dVar.f2771l = dVar.f2765f;
        dVar.f2772m = dVar.f2766g;
        dVar.a((dVar.f2769j + 1) % dVar.f2768i.length);
        if (!eVar.f2789f) {
            eVar.f2788e += 1.0f;
            return;
        }
        eVar.f2789f = false;
        animator.cancel();
        animator.setDuration(1332L);
        animator.start();
        if (dVar.f2773n) {
            dVar.f2773n = false;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f2759b.f2788e = CropImageView.DEFAULT_ASPECT_RATIO;
    }
}
