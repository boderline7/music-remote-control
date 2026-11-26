package com.google.android.material.internal;

import android.graphics.Insets;
import android.view.WindowInsetsAnimation;
import android.view.animation.DecelerateInterpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract /* synthetic */ class l0 {
    public static /* synthetic */ WindowInsetsAnimation.Bounds j(Insets insets, Insets insets2) {
        return new WindowInsetsAnimation.Bounds(insets, insets2);
    }

    public static /* synthetic */ WindowInsetsAnimation k(int i7, DecelerateInterpolator decelerateInterpolator, long j7) {
        return new WindowInsetsAnimation(i7, decelerateInterpolator, j7);
    }

    public static /* bridge */ /* synthetic */ WindowInsetsAnimation l(Object obj) {
        return (WindowInsetsAnimation) obj;
    }

    public static /* synthetic */ void o() {
    }
}
