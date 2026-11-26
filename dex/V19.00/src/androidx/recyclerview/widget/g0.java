package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g0 implements Runnable {

    /* renamed from: e, reason: collision with root package name */
    public static final ThreadLocal f2457e = new ThreadLocal();

    /* renamed from: f, reason: collision with root package name */
    public static final r f2458f = new r(1);

    /* renamed from: a, reason: collision with root package name */
    public ArrayList f2459a;

    /* renamed from: b, reason: collision with root package name */
    public long f2460b;

    /* renamed from: c, reason: collision with root package name */
    public long f2461c;

    /* renamed from: d, reason: collision with root package name */
    public ArrayList f2462d;

    public static j2 c(RecyclerView recyclerView, int i7, long j7) {
        int h7 = recyclerView.f2305e.h();
        for (int i8 = 0; i8 < h7; i8++) {
            j2 K = RecyclerView.K(recyclerView.f2305e.g(i8));
            if (K.mPosition == i7 && !K.isInvalid()) {
                return null;
            }
        }
        z1 z1Var = recyclerView.f2299b;
        try {
            recyclerView.S();
            j2 i9 = z1Var.i(i7, j7);
            if (i9 != null) {
                if (!i9.isBound() || i9.isInvalid()) {
                    z1Var.a(i9, false);
                } else {
                    z1Var.f(i9.itemView);
                }
            }
            recyclerView.T(false);
            return i9;
        } catch (Throwable th) {
            recyclerView.T(false);
            throw th;
        }
    }

    public final void a(RecyclerView recyclerView, int i7, int i8) {
        if (recyclerView.isAttachedToWindow() && this.f2460b == 0) {
            this.f2460b = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        e0 e0Var = recyclerView.f2306e0;
        e0Var.f2434a = i7;
        e0Var.f2435b = i8;
    }

    public final void b(long j7) {
        f0 f0Var;
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        f0 f0Var2;
        ArrayList arrayList = this.f2459a;
        int size = arrayList.size();
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            RecyclerView recyclerView3 = (RecyclerView) arrayList.get(i8);
            if (recyclerView3.getWindowVisibility() == 0) {
                e0 e0Var = recyclerView3.f2306e0;
                e0Var.b(recyclerView3, false);
                i7 += e0Var.f2436c;
            }
        }
        ArrayList arrayList2 = this.f2462d;
        arrayList2.ensureCapacity(i7);
        int i9 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            RecyclerView recyclerView4 = (RecyclerView) arrayList.get(i10);
            if (recyclerView4.getWindowVisibility() == 0) {
                e0 e0Var2 = recyclerView4.f2306e0;
                int abs = Math.abs(e0Var2.f2435b) + Math.abs(e0Var2.f2434a);
                for (int i11 = 0; i11 < e0Var2.f2436c * 2; i11 += 2) {
                    if (i9 >= arrayList2.size()) {
                        f0Var2 = new f0();
                        arrayList2.add(f0Var2);
                    } else {
                        f0Var2 = (f0) arrayList2.get(i9);
                    }
                    int[] iArr = (int[]) e0Var2.f2437d;
                    int i12 = iArr[i11 + 1];
                    f0Var2.f2440a = i12 <= abs;
                    f0Var2.f2441b = abs;
                    f0Var2.f2442c = i12;
                    f0Var2.f2443d = recyclerView4;
                    f0Var2.f2444e = iArr[i11];
                    i9++;
                }
            }
        }
        Collections.sort(arrayList2, f2458f);
        for (int i13 = 0; i13 < arrayList2.size() && (recyclerView = (f0Var = (f0) arrayList2.get(i13)).f2443d) != null; i13++) {
            j2 c7 = c(recyclerView, f0Var.f2444e, f0Var.f2440a ? Long.MAX_VALUE : j7);
            if (c7 != null && c7.mNestedRecyclerView != null && c7.isBound() && !c7.isInvalid() && (recyclerView2 = c7.mNestedRecyclerView.get()) != null) {
                if (recyclerView2.B && recyclerView2.f2305e.h() != 0) {
                    m1 m1Var = recyclerView2.K;
                    if (m1Var != null) {
                        m1Var.e();
                    }
                    r1 r1Var = recyclerView2.f2321m;
                    z1 z1Var = recyclerView2.f2299b;
                    if (r1Var != null) {
                        r1Var.l0(z1Var);
                        recyclerView2.f2321m.m0(z1Var);
                    }
                    z1Var.f2718a.clear();
                    z1Var.d();
                }
                e0 e0Var3 = recyclerView2.f2306e0;
                e0Var3.b(recyclerView2, true);
                if (e0Var3.f2436c != 0) {
                    try {
                        int i14 = l0.k.f7276a;
                        l0.j.a("RV Nested Prefetch");
                        g2 g2Var = recyclerView2.f2308f0;
                        h1 h1Var = recyclerView2.f2319l;
                        g2Var.f2468d = 1;
                        g2Var.f2469e = h1Var.getItemCount();
                        g2Var.f2471g = false;
                        g2Var.f2472h = false;
                        g2Var.f2473i = false;
                        for (int i15 = 0; i15 < e0Var3.f2436c * 2; i15 += 2) {
                            c(recyclerView2, ((int[]) e0Var3.f2437d)[i15], j7);
                        }
                        l0.j.b();
                        f0Var.f2440a = false;
                        f0Var.f2441b = 0;
                        f0Var.f2442c = 0;
                        f0Var.f2443d = null;
                        f0Var.f2444e = 0;
                    } catch (Throwable th) {
                        int i16 = l0.k.f7276a;
                        l0.j.b();
                        throw th;
                    }
                }
            }
            f0Var.f2440a = false;
            f0Var.f2441b = 0;
            f0Var.f2442c = 0;
            f0Var.f2443d = null;
            f0Var.f2444e = 0;
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            int i7 = l0.k.f7276a;
            l0.j.a("RV Prefetch");
            ArrayList arrayList = this.f2459a;
            if (arrayList.isEmpty()) {
                this.f2460b = 0L;
                l0.j.b();
                return;
            }
            int size = arrayList.size();
            long j7 = 0;
            for (int i8 = 0; i8 < size; i8++) {
                RecyclerView recyclerView = (RecyclerView) arrayList.get(i8);
                if (recyclerView.getWindowVisibility() == 0) {
                    j7 = Math.max(recyclerView.getDrawingTime(), j7);
                }
            }
            if (j7 == 0) {
                this.f2460b = 0L;
                l0.j.b();
            } else {
                b(TimeUnit.MILLISECONDS.toNanos(j7) + this.f2461c);
                this.f2460b = 0L;
                l0.j.b();
            }
        } catch (Throwable th) {
            this.f2460b = 0L;
            int i9 = l0.k.f7276a;
            l0.j.b();
            throw th;
        }
    }
}
