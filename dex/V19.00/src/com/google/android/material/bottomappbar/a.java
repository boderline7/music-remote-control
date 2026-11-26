package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3357a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar f3358b;

    public /* synthetic */ a(BottomAppBar bottomAppBar, int i7) {
        this.f3357a = i7;
        this.f3358b = bottomAppBar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i7 = this.f3357a;
        BottomAppBar bottomAppBar = this.f3358b;
        switch (i7) {
            case 1:
                int i8 = BottomAppBar.f3327v0;
                bottomAppBar.getClass();
                bottomAppBar.W = null;
                break;
            case 2:
                int i9 = BottomAppBar.f3327v0;
                bottomAppBar.getClass();
                bottomAppBar.f3343n0 = false;
                bottomAppBar.f3330a0 = null;
                break;
            default:
                super.onAnimationEnd(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        float fabTranslationX;
        int i7 = this.f3357a;
        BottomAppBar bottomAppBar = this.f3358b;
        switch (i7) {
            case 0:
                if (!bottomAppBar.f3343n0) {
                    bottomAppBar.I(bottomAppBar.f3331b0, bottomAppBar.f3344o0);
                    break;
                }
                break;
            case 1:
                int i8 = BottomAppBar.f3327v0;
                bottomAppBar.getClass();
                break;
            case 2:
                int i9 = BottomAppBar.f3327v0;
                bottomAppBar.getClass();
                break;
            default:
                bottomAppBar.f3349t0.onAnimationStart(animator);
                FloatingActionButton D = bottomAppBar.D();
                if (D != null) {
                    fabTranslationX = bottomAppBar.getFabTranslationX();
                    D.setTranslationX(fabTranslationX);
                    break;
                }
                break;
        }
    }
}
