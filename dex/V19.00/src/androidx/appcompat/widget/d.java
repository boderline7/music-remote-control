package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewOverlay;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.google.android.material.transformation.ExpandableTransformationBehavior;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1204a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1205b;

    public /* synthetic */ d(int i7, Object obj) {
        this.f1204a = i7;
        this.f1205b = obj;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        switch (this.f1204a) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.f1205b;
                actionBarOverlayLayout.f976w = null;
                actionBarOverlayLayout.f964k = false;
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i7 = this.f1204a;
        Object obj = this.f1205b;
        switch (i7) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) obj;
                actionBarOverlayLayout.f976w = null;
                actionBarOverlayLayout.f964k = false;
                break;
            case 1:
                ((n1.r) obj).m();
                animator.removeListener(this);
                break;
            case 2:
                o1.f fVar = (o1.f) obj;
                ArrayList arrayList = new ArrayList(fVar.f7808e);
                int size = arrayList.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((o1.c) arrayList.get(i8)).a(fVar);
                }
                break;
            case 3:
                ((HideBottomViewOnScrollBehavior) obj).f3318i = null;
                break;
            case 4:
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) obj;
                bottomSheetBehavior.J(5);
                WeakReference weakReference = bottomSheetBehavior.U;
                if (weakReference != null && weakReference.get() != null) {
                    ((View) bottomSheetBehavior.U.get()).requestLayout();
                    break;
                }
                break;
            case 5:
                ((a3.g) obj).g();
                break;
            case 6:
                j.h hVar = (j.h) obj;
                if (((ValueAnimator) hVar.f6891b) == animator) {
                    hVar.f6891b = null;
                    break;
                }
                break;
            case 7:
                j3.g gVar = (j3.g) obj;
                gVar.f6954b.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                gVar.b(CropImageView.DEFAULT_ASPECT_RATIO);
                break;
            case 8:
            default:
                super.onAnimationEnd(animator);
                break;
            case 9:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) obj;
                sideSheetBehavior.x(5);
                WeakReference weakReference2 = sideSheetBehavior.f4133p;
                if (weakReference2 != null && weakReference2.get() != null) {
                    ((View) sideSheetBehavior.f4133p.get()).requestLayout();
                    break;
                }
                break;
            case 10:
                super.onAnimationEnd(animator);
                com.google.android.material.slider.c cVar = (com.google.android.material.slider.c) obj;
                n1.a0 e7 = com.google.android.material.internal.k0.e(cVar);
                Iterator it = cVar.f4176k.iterator();
                while (it.hasNext()) {
                    u3.a aVar = (u3.a) it.next();
                    int i9 = e7.f7586a;
                    ViewOverlay viewOverlay = e7.f7587b;
                    switch (i9) {
                        case 0:
                            viewOverlay.remove(aVar);
                            break;
                        default:
                            viewOverlay.remove(aVar);
                            break;
                    }
                }
                break;
            case 11:
                com.google.android.material.textfield.i iVar = (com.google.android.material.textfield.i) obj;
                iVar.q();
                iVar.f4354r.start();
                break;
            case 12:
                ((ExpandableTransformationBehavior) obj).f4470b = null;
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        switch (this.f1204a) {
            case 8:
                super.onAnimationRepeat(animator);
                l3.p pVar = (l3.p) this.f1205b;
                pVar.f7402g = (pVar.f7402g + 1) % pVar.f7401f.f7345c.length;
                pVar.f7403h = true;
                break;
            default:
                super.onAnimationRepeat(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i7 = this.f1204a;
        Object obj = this.f1205b;
        switch (i7) {
            case 2:
                o1.f fVar = (o1.f) obj;
                ArrayList arrayList = new ArrayList(fVar.f7808e);
                int size = arrayList.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((o1.c) arrayList.get(i8)).b(fVar);
                }
                break;
            case 5:
                ((a3.g) obj).c();
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
