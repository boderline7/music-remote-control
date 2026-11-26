package androidx.recyclerview.widget;

import android.view.animation.Interpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d2 {
    public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;

    /* renamed from: a, reason: collision with root package name */
    public int f2428a;

    /* renamed from: b, reason: collision with root package name */
    public int f2429b;

    /* renamed from: c, reason: collision with root package name */
    public int f2430c;

    /* renamed from: d, reason: collision with root package name */
    public int f2431d;

    /* renamed from: e, reason: collision with root package name */
    public Interpolator f2432e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2433f;

    public final void a(RecyclerView recyclerView) {
        int i7 = this.f2431d;
        if (i7 >= 0) {
            this.f2431d = -1;
            recyclerView.P(i7);
            this.f2433f = false;
        } else if (this.f2433f) {
            Interpolator interpolator = this.f2432e;
            if (interpolator != null && this.f2430c < 1) {
                throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
            }
            int i8 = this.f2430c;
            if (i8 < 1) {
                throw new IllegalStateException("Scroll duration must be a positive number");
            }
            recyclerView.f2302c0.b(this.f2428a, this.f2429b, i8, interpolator);
            this.f2433f = false;
        }
    }
}
