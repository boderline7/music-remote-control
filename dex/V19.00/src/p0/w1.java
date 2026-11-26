package p0;

import android.view.WindowInsetsAnimation;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w1 extends x1 {

    /* renamed from: e, reason: collision with root package name */
    public final WindowInsetsAnimation f8197e;

    public w1(WindowInsetsAnimation windowInsetsAnimation) {
        super(0, null, 0L);
        this.f8197e = windowInsetsAnimation;
    }

    @Override // p0.x1
    public final long a() {
        long durationMillis;
        durationMillis = this.f8197e.getDurationMillis();
        return durationMillis;
    }

    @Override // p0.x1
    public final float b() {
        float interpolatedFraction;
        interpolatedFraction = this.f8197e.getInterpolatedFraction();
        return interpolatedFraction;
    }

    @Override // p0.x1
    public final int c() {
        int typeMask;
        typeMask = this.f8197e.getTypeMask();
        return typeMask;
    }

    @Override // p0.x1
    public final void d(float f7) {
        this.f8197e.setFraction(f7);
    }
}
