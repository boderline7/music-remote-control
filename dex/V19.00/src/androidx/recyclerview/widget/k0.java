package androidx.recyclerview.widget;

import android.view.animation.Interpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k0 implements Interpolator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2520a;

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f7) {
        switch (this.f2520a) {
            case 0:
                return f7 * f7 * f7 * f7 * f7;
            case 1:
                float f8 = f7 - 1.0f;
                return (f8 * f8 * f8 * f8 * f8) + 1.0f;
            default:
                float f9 = f7 - 1.0f;
                return (f9 * f9 * f9 * f9 * f9) + 1.0f;
        }
    }
}
