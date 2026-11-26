package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.appcompat.widget.ActionMenuView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3858a;

    /* renamed from: b, reason: collision with root package name */
    public final View f3859b;

    /* renamed from: c, reason: collision with root package name */
    public final View f3860c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f3861d;

    public g(ActionMenuView actionMenuView, ActionMenuView actionMenuView2) {
        this.f3858a = 0;
        this.f3859b = actionMenuView;
        this.f3860c = actionMenuView2;
        this.f3861d = new float[2];
    }

    public g(s3.e eVar, View view, View view2) {
        this.f3858a = 1;
        this.f3861d = eVar;
        this.f3859b = view;
        this.f3860c = view2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i7 = this.f3858a;
        View view = this.f3860c;
        View view2 = this.f3859b;
        Object obj = this.f3861d;
        switch (i7) {
            case 0:
                float[] fArr = (float[]) obj;
                l6.f.g(((Float) valueAnimator.getAnimatedValue()).floatValue(), fArr);
                if (view2 != null) {
                    view2.setAlpha(fArr[0]);
                }
                if (view != null) {
                    view.setAlpha(fArr[1]);
                    break;
                }
                break;
            default:
                float animatedFraction = valueAnimator.getAnimatedFraction();
                int i8 = s3.e.f8695c;
                ((s3.e) obj).c(view2, view, animatedFraction);
                break;
        }
    }
}
