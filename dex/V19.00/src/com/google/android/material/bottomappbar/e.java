package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ActionMenuView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public boolean f3363a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ActionMenuView f3364b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f3365c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ boolean f3366d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar f3367e;

    public e(BottomAppBar bottomAppBar, ActionMenuView actionMenuView, int i7, boolean z6) {
        this.f3367e = bottomAppBar;
        this.f3364b = actionMenuView;
        this.f3365c = i7;
        this.f3366d = z6;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f3363a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.f3363a) {
            return;
        }
        BottomAppBar bottomAppBar = this.f3367e;
        int i7 = bottomAppBar.f3342m0;
        boolean z6 = i7 != 0;
        if (i7 != 0) {
            bottomAppBar.f3342m0 = 0;
            bottomAppBar.getMenu().clear();
            bottomAppBar.n(i7);
        }
        bottomAppBar.M(this.f3364b, this.f3365c, this.f3366d, z6);
    }
}
