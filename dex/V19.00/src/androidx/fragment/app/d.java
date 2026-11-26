package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f1926a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f1927b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f1928c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ v1 f1929d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ i f1930e;

    public d(ViewGroup viewGroup, View view, boolean z6, v1 v1Var, i iVar) {
        this.f1926a = viewGroup;
        this.f1927b = view;
        this.f1928c = z6;
        this.f1929d = v1Var;
        this.f1930e = iVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ViewGroup viewGroup = this.f1926a;
        View view = this.f1927b;
        viewGroup.endViewTransition(view);
        if (this.f1928c) {
            this.f1929d.f2070a.a(view);
        }
        this.f1930e.a();
    }
}
