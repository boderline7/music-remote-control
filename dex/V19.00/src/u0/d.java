package u0;

import android.view.animation.Interpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements Interpolator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9235a;

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f7) {
        switch (this.f9235a) {
            case 0:
                float f8 = f7 - 1.0f;
                return (f8 * f8 * f8 * f8 * f8) + 1.0f;
            default:
                float f9 = f7 - 1.0f;
                return (f9 * f9 * f9 * f9 * f9) + 1.0f;
        }
    }
}
