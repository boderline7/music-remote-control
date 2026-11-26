package androidx.recyclerview.widget;

import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public int f2490a;

    /* renamed from: b, reason: collision with root package name */
    public int f2491b;

    /* renamed from: c, reason: collision with root package name */
    public OverScroller f2492c;

    /* renamed from: d, reason: collision with root package name */
    public Interpolator f2493d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2494e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2495f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f2496g;

    public i2(RecyclerView recyclerView) {
        this.f2496g = recyclerView;
        k0 k0Var = RecyclerView.f2296y0;
        this.f2493d = k0Var;
        this.f2494e = false;
        this.f2495f = false;
        this.f2492c = new OverScroller(recyclerView.getContext(), k0Var);
    }

    public final void a() {
        if (this.f2494e) {
            this.f2495f = true;
            return;
        }
        RecyclerView recyclerView = this.f2496g;
        recyclerView.removeCallbacks(this);
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        p0.l0.m(recyclerView, this);
    }

    public final void b(int i7, int i8, int i9, Interpolator interpolator) {
        int i10;
        RecyclerView recyclerView = this.f2496g;
        if (i9 == Integer.MIN_VALUE) {
            int abs = Math.abs(i7);
            int abs2 = Math.abs(i8);
            boolean z6 = abs > abs2;
            int sqrt = (int) Math.sqrt(0);
            int sqrt2 = (int) Math.sqrt((i8 * i8) + (i7 * i7));
            int width = z6 ? recyclerView.getWidth() : recyclerView.getHeight();
            int i11 = width / 2;
            float f7 = width;
            float f8 = i11;
            float sin = (((float) Math.sin((Math.min(1.0f, (sqrt2 * 1.0f) / f7) - 0.5f) * 0.47123894f)) * f8) + f8;
            if (sqrt > 0) {
                i10 = Math.round(Math.abs(sin / sqrt) * 1000.0f) * 4;
            } else {
                if (!z6) {
                    abs = abs2;
                }
                i10 = (int) (((abs / f7) + 1.0f) * 300.0f);
            }
            i9 = Math.min(i10, 2000);
        }
        int i12 = i9;
        if (interpolator == null) {
            interpolator = RecyclerView.f2296y0;
        }
        if (this.f2493d != interpolator) {
            this.f2493d = interpolator;
            this.f2492c = new OverScroller(recyclerView.getContext(), interpolator);
        }
        this.f2491b = 0;
        this.f2490a = 0;
        recyclerView.setScrollState(2);
        this.f2492c.startScroll(0, 0, i7, i8, i12);
        a();
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7;
        int i8;
        int i9;
        int i10;
        boolean awakenScrollBars;
        RecyclerView recyclerView = this.f2496g;
        if (recyclerView.f2321m == null) {
            recyclerView.removeCallbacks(this);
            this.f2492c.abortAnimation();
            return;
        }
        this.f2495f = false;
        this.f2494e = true;
        recyclerView.m();
        OverScroller overScroller = this.f2492c;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i11 = currX - this.f2490a;
            int i12 = currY - this.f2491b;
            this.f2490a = currX;
            this.f2491b = currY;
            int[] iArr = recyclerView.f2334s0;
            iArr[0] = 0;
            iArr[1] = 0;
            boolean s6 = recyclerView.s(i11, i12, iArr, null, 1);
            int[] iArr2 = recyclerView.f2334s0;
            if (s6) {
                i11 -= iArr2[0];
                i12 -= iArr2[1];
            }
            if (recyclerView.getOverScrollMode() != 2) {
                recyclerView.l(i11, i12);
            }
            if (recyclerView.f2319l != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
                recyclerView.d0(i11, i12, iArr2);
                int i13 = iArr2[0];
                int i14 = iArr2[1];
                int i15 = i11 - i13;
                int i16 = i12 - i14;
                f2 f2Var = recyclerView.f2321m.f2625e;
                if (f2Var != null && !f2Var.isPendingInitialRun() && f2Var.isRunning()) {
                    int b7 = recyclerView.f2308f0.b();
                    if (b7 == 0) {
                        f2Var.stop();
                    } else if (f2Var.getTargetPosition() >= b7) {
                        f2Var.setTargetPosition(b7 - 1);
                        f2Var.onAnimation(i13, i14);
                    } else {
                        f2Var.onAnimation(i13, i14);
                    }
                }
                i10 = i13;
                i7 = i15;
                i8 = i16;
                i9 = i14;
            } else {
                i7 = i11;
                i8 = i12;
                i9 = 0;
                i10 = 0;
            }
            if (!recyclerView.f2323n.isEmpty()) {
                recyclerView.invalidate();
            }
            int[] iArr3 = recyclerView.f2334s0;
            iArr3[0] = 0;
            iArr3[1] = 0;
            int i17 = i9;
            recyclerView.t(i10, i9, i7, i8, null, 1, iArr3);
            int i18 = i7 - iArr2[0];
            int i19 = i8 - iArr2[1];
            if (i10 != 0 || i17 != 0) {
                recyclerView.u(i10, i17);
            }
            awakenScrollBars = recyclerView.awakenScrollBars();
            if (!awakenScrollBars) {
                recyclerView.invalidate();
            }
            boolean z6 = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i18 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i19 != 0));
            f2 f2Var2 = recyclerView.f2321m.f2625e;
            if ((f2Var2 == null || !f2Var2.isPendingInitialRun()) && z6) {
                if (recyclerView.getOverScrollMode() != 2) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    int i20 = i18 < 0 ? -currVelocity : i18 > 0 ? currVelocity : 0;
                    if (i19 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i19 <= 0) {
                        currVelocity = 0;
                    }
                    if (i20 < 0) {
                        recyclerView.w();
                        if (recyclerView.G.isFinished()) {
                            recyclerView.G.onAbsorb(-i20);
                        }
                    } else if (i20 > 0) {
                        recyclerView.x();
                        if (recyclerView.I.isFinished()) {
                            recyclerView.I.onAbsorb(i20);
                        }
                    }
                    if (currVelocity < 0) {
                        recyclerView.y();
                        if (recyclerView.H.isFinished()) {
                            recyclerView.H.onAbsorb(-currVelocity);
                        }
                    } else if (currVelocity > 0) {
                        recyclerView.v();
                        if (recyclerView.J.isFinished()) {
                            recyclerView.J.onAbsorb(currVelocity);
                        }
                    }
                    if (i20 != 0 || currVelocity != 0) {
                        int i21 = p0.d1.OVER_SCROLL_ALWAYS;
                        p0.l0.k(recyclerView);
                    }
                }
                e0 e0Var = recyclerView.f2306e0;
                int[] iArr4 = (int[]) e0Var.f2437d;
                if (iArr4 != null) {
                    Arrays.fill(iArr4, -1);
                }
                e0Var.f2436c = 0;
            } else {
                a();
                g0 g0Var = recyclerView.f2304d0;
                if (g0Var != null) {
                    g0Var.a(recyclerView, i10, i17);
                }
            }
        }
        f2 f2Var3 = recyclerView.f2321m.f2625e;
        if (f2Var3 != null && f2Var3.isPendingInitialRun()) {
            f2Var3.onAnimation(0, 0);
        }
        this.f2494e = false;
        if (!this.f2495f) {
            recyclerView.setScrollState(0);
            recyclerView.i0(1);
        } else {
            recyclerView.removeCallbacks(this);
            int i22 = p0.d1.OVER_SCROLL_ALWAYS;
            p0.l0.m(recyclerView, this);
        }
    }
}
