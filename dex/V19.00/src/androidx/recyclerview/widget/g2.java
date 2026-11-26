package androidx.recyclerview.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g2 {

    /* renamed from: a, reason: collision with root package name */
    public int f2465a;

    /* renamed from: b, reason: collision with root package name */
    public int f2466b;

    /* renamed from: c, reason: collision with root package name */
    public int f2467c;

    /* renamed from: d, reason: collision with root package name */
    public int f2468d;

    /* renamed from: e, reason: collision with root package name */
    public int f2469e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2470f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f2471g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f2472h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f2473i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f2474j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f2475k;

    /* renamed from: l, reason: collision with root package name */
    public int f2476l;

    /* renamed from: m, reason: collision with root package name */
    public long f2477m;

    /* renamed from: n, reason: collision with root package name */
    public int f2478n;

    public final void a(int i7) {
        if ((this.f2468d & i7) != 0) {
            return;
        }
        throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i7) + " but it is " + Integer.toBinaryString(this.f2468d));
    }

    public final int b() {
        return this.f2471g ? this.f2466b - this.f2467c : this.f2469e;
    }

    public final String toString() {
        return "State{mTargetPosition=" + this.f2465a + ", mData=null, mItemCount=" + this.f2469e + ", mIsMeasuring=" + this.f2473i + ", mPreviousLayoutItemCount=" + this.f2466b + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f2467c + ", mStructureChanged=" + this.f2470f + ", mInPreLayout=" + this.f2471g + ", mRunSimpleAnimations=" + this.f2474j + ", mRunPredictiveAnimations=" + this.f2475k + '}';
    }
}
