package l3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7377a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f7378b;

    public /* synthetic */ k(l lVar, int i7) {
        this.f7377a = i7;
        this.f7378b = lVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f7377a) {
            case 1:
                super.onAnimationEnd(animator);
                l lVar = this.f7378b;
                super/*android.graphics.drawable.Drawable*/.setVisible(false, false);
                ArrayList arrayList = lVar.f7385f;
                if (arrayList != null && !lVar.f7386g) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((o1.c) it.next()).a(lVar);
                    }
                    break;
                }
                break;
            default:
                super.onAnimationEnd(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f7377a) {
            case 0:
                super.onAnimationStart(animator);
                l lVar = this.f7378b;
                ArrayList arrayList = lVar.f7385f;
                if (arrayList != null && !lVar.f7386g) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((o1.c) it.next()).b(lVar);
                    }
                    break;
                }
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
