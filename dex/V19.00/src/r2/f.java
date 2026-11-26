package r2;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public long f8511a;

    /* renamed from: c, reason: collision with root package name */
    public TimeInterpolator f8513c = null;

    /* renamed from: d, reason: collision with root package name */
    public int f8514d = 0;

    /* renamed from: e, reason: collision with root package name */
    public int f8515e = 1;

    /* renamed from: b, reason: collision with root package name */
    public long f8512b = 150;

    public f(long j7) {
        this.f8511a = j7;
    }

    public final void a(Animator animator) {
        animator.setStartDelay(this.f8511a);
        animator.setDuration(this.f8512b);
        animator.setInterpolator(b());
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnimator = (ValueAnimator) animator;
            valueAnimator.setRepeatCount(this.f8514d);
            valueAnimator.setRepeatMode(this.f8515e);
        }
    }

    public final TimeInterpolator b() {
        TimeInterpolator timeInterpolator = this.f8513c;
        return timeInterpolator != null ? timeInterpolator : a.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.f8511a == fVar.f8511a && this.f8512b == fVar.f8512b && this.f8514d == fVar.f8514d && this.f8515e == fVar.f8515e) {
            return b().getClass().equals(fVar.b().getClass());
        }
        return false;
    }

    public final int hashCode() {
        long j7 = this.f8511a;
        long j8 = this.f8512b;
        return ((((b().getClass().hashCode() + (((((int) (j7 ^ (j7 >>> 32))) * 31) + ((int) ((j8 >>> 32) ^ j8))) * 31)) * 31) + this.f8514d) * 31) + this.f8515e;
    }

    public final String toString() {
        return "\n" + f.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + this.f8511a + " duration: " + this.f8512b + " interpolator: " + b().getClass() + " repeatCount: " + this.f8514d + " repeatMode: " + this.f8515e + "}\n";
    }
}
