package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q2 extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1380a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1381b;

    /* renamed from: c, reason: collision with root package name */
    public final int f1382c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f1383d;

    public q2(ScrollingTabContainerView scrollingTabContainerView) {
        this.f1380a = 0;
        this.f1383d = scrollingTabContainerView;
        this.f1381b = false;
    }

    public q2(j3.i iVar, boolean z6, int i7) {
        this.f1380a = 1;
        this.f1383d = iVar;
        this.f1381b = z6;
        this.f1382c = i7;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        switch (this.f1380a) {
            case 0:
                this.f1381b = true;
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i7 = this.f1380a;
        Object obj = this.f1383d;
        switch (i7) {
            case 0:
                if (!this.f1381b) {
                    ScrollingTabContainerView scrollingTabContainerView = (ScrollingTabContainerView) obj;
                    scrollingTabContainerView.getClass();
                    scrollingTabContainerView.setVisibility(this.f1382c);
                    break;
                }
                break;
            default:
                j3.i iVar = (j3.i) obj;
                iVar.f6954b.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
                iVar.c(this.f1382c, CropImageView.DEFAULT_ASPECT_RATIO, this.f1381b);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f1380a) {
            case 0:
                ((ScrollingTabContainerView) this.f1383d).setVisibility(0);
                this.f1381b = false;
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
