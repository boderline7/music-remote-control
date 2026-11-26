package androidx.recyclerview.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a0 extends w1 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d0 f2375a;

    public a0(d0 d0Var) {
        this.f2375a = d0Var;
    }

    @Override // androidx.recyclerview.widget.w1
    public final void b(RecyclerView recyclerView, int i7, int i8) {
        int computeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
        int computeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        d0 d0Var = this.f2375a;
        int computeVerticalScrollRange = d0Var.f2417s.computeVerticalScrollRange();
        int i9 = d0Var.f2416r;
        int i10 = computeVerticalScrollRange - i9;
        int i11 = d0Var.f2399a;
        d0Var.f2418t = i10 > 0 && i9 >= i11;
        int computeHorizontalScrollRange = d0Var.f2417s.computeHorizontalScrollRange();
        int i12 = d0Var.f2415q;
        boolean z6 = computeHorizontalScrollRange - i12 > 0 && i12 >= i11;
        d0Var.f2419u = z6;
        boolean z7 = d0Var.f2418t;
        if (!z7 && !z6) {
            if (d0Var.f2420v != 0) {
                d0Var.i(0);
                return;
            }
            return;
        }
        if (z7) {
            float f7 = i9;
            d0Var.f2410l = (int) ((((f7 / 2.0f) + computeVerticalScrollOffset) * f7) / computeVerticalScrollRange);
            d0Var.f2409k = Math.min(i9, (i9 * i9) / computeVerticalScrollRange);
        }
        if (d0Var.f2419u) {
            float f8 = computeHorizontalScrollOffset;
            float f9 = i12;
            d0Var.f2413o = (int) ((((f9 / 2.0f) + f8) * f9) / computeHorizontalScrollRange);
            d0Var.f2412n = Math.min(i12, (i12 * i12) / computeHorizontalScrollRange);
        }
        int i13 = d0Var.f2420v;
        if (i13 == 0 || i13 == 1) {
            d0Var.i(1);
        }
    }
}
