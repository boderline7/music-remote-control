package p0;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class x1 {

    /* renamed from: a, reason: collision with root package name */
    public final int f8203a;

    /* renamed from: b, reason: collision with root package name */
    public float f8204b;

    /* renamed from: c, reason: collision with root package name */
    public final Interpolator f8205c;

    /* renamed from: d, reason: collision with root package name */
    public final long f8206d;

    public x1(int i7, DecelerateInterpolator decelerateInterpolator, long j7) {
        this.f8203a = i7;
        this.f8205c = decelerateInterpolator;
        this.f8206d = j7;
    }

    public long a() {
        return this.f8206d;
    }

    public float b() {
        Interpolator interpolator = this.f8205c;
        return interpolator != null ? interpolator.getInterpolation(this.f8204b) : this.f8204b;
    }

    public int c() {
        return this.f8203a;
    }

    public void d(float f7) {
        this.f8204b = f7;
    }
}
