package com.google.android.material.navigation;

import android.animation.ValueAnimator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ float f4009a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ NavigationBarItemView f4010b;

    public b(NavigationBarItemView navigationBarItemView, float f7) {
        this.f4010b = navigationBarItemView;
        this.f4009a = f7;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int[] iArr = NavigationBarItemView.G;
        this.f4010b.e(floatValue, this.f4009a);
    }
}
