package com.google.android.material.internal;

import android.animation.TimeInterpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class x implements TimeInterpolator {

    /* renamed from: a, reason: collision with root package name */
    public final TimeInterpolator f3907a;

    public x(TimeInterpolator timeInterpolator) {
        this.f3907a = timeInterpolator;
    }

    public static TimeInterpolator a(boolean z6, TimeInterpolator timeInterpolator) {
        return z6 ? timeInterpolator : new x(timeInterpolator);
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f7) {
        return 1.0f - this.f3907a.getInterpolation(f7);
    }
}
