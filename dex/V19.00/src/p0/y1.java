package p0;

import android.os.Build;
import android.view.animation.DecelerateInterpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class y1 {

    /* renamed from: a, reason: collision with root package name */
    public x1 f8207a;

    public y1(int i7, DecelerateInterpolator decelerateInterpolator, long j7) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.f8207a = new w1(com.google.android.material.internal.l0.k(i7, decelerateInterpolator, j7));
        } else {
            this.f8207a = new u1(i7, decelerateInterpolator, j7);
        }
    }
}
