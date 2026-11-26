package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4325a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ c f4326b;

    public /* synthetic */ b(c cVar, int i7) {
        this.f4325a = i7;
        this.f4326b = cVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f4325a) {
            case 1:
                this.f4326b.f4381b.h(false);
                break;
            default:
                super.onAnimationEnd(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f4325a) {
            case 0:
                this.f4326b.f4381b.h(true);
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
