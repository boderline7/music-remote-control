package androidx.lifecycle;

import android.os.Handler;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class o0 implements x {
    public static final long TIMEOUT_MS = 700;

    /* renamed from: a, reason: collision with root package name */
    public int f2192a;

    /* renamed from: b, reason: collision with root package name */
    public int f2193b;

    /* renamed from: e, reason: collision with root package name */
    public Handler f2196e;
    public static final k0 Companion = new k0();

    /* renamed from: i, reason: collision with root package name */
    public static final o0 f2191i = new o0();

    /* renamed from: c, reason: collision with root package name */
    public boolean f2194c = true;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2195d = true;

    /* renamed from: f, reason: collision with root package name */
    public final a0 f2197f = new a0(this);

    /* renamed from: g, reason: collision with root package name */
    public final androidx.activity.e f2198g = new androidx.activity.e(6, this);

    /* renamed from: h, reason: collision with root package name */
    public final n0 f2199h = new n0(this);

    public final void a() {
        int i7 = this.f2193b + 1;
        this.f2193b = i7;
        if (i7 == 1) {
            if (this.f2194c) {
                this.f2197f.e(q.ON_RESUME);
                this.f2194c = false;
            } else {
                Handler handler = this.f2196e;
                i5.a.d(handler);
                handler.removeCallbacks(this.f2198g);
            }
        }
    }

    @Override // androidx.lifecycle.x
    public final s getLifecycle() {
        return this.f2197f;
    }
}
