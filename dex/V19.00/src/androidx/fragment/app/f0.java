package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f0 extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f1948a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f1949b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ z f1950c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ p0 f1951d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ l0.d f1952e;

    public f0(ViewGroup viewGroup, View view, z zVar, p0 p0Var, l0.d dVar) {
        this.f1948a = viewGroup;
        this.f1949b = view;
        this.f1950c = zVar;
        this.f1951d = p0Var;
        this.f1952e = dVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ViewGroup viewGroup = this.f1948a;
        View view = this.f1949b;
        viewGroup.endViewTransition(view);
        z zVar = this.f1950c;
        Animator animator2 = zVar.getAnimator();
        zVar.setAnimator(null);
        if (animator2 == null || viewGroup.indexOfChild(view) >= 0) {
            return;
        }
        this.f1951d.c(zVar, this.f1952e);
    }
}
