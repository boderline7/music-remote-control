package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.n3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class GridLayoutManager extends LinearLayoutManager {
    public static final int DEFAULT_SPAN_COUNT = -1;
    public boolean E;
    public int F;
    public int[] G;
    public View[] H;
    public final SparseIntArray I;
    public final SparseIntArray J;
    public final n3 K;
    public final Rect L;

    public GridLayoutManager(int i7) {
        super(1);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.J = new SparseIntArray();
        this.K = new n3(1);
        this.L = new Rect();
        s1(i7);
    }

    public GridLayoutManager(int i7, int i8) {
        super(1);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.J = new SparseIntArray();
        this.K = new n3(1);
        this.L = new Rect();
        s1(i7);
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i7, int i8) {
        super(context, attributeSet, i7, i8);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.J = new SparseIntArray();
        this.K = new n3(1);
        this.L = new Rect();
        s1(r1.L(context, attributeSet, i7, i8).f2613b);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final boolean E0() {
        return this.f2290z == null && !this.E;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void G0(g2 g2Var, v0 v0Var, e0 e0Var) {
        int i7;
        int i8 = this.F;
        for (int i9 = 0; i9 < this.F && (i7 = v0Var.f2686d) >= 0 && i7 < g2Var.b() && i8 > 0; i9++) {
            e0Var.a(v0Var.f2686d, Math.max(0, v0Var.f2689g));
            this.K.getClass();
            i8--;
            v0Var.f2686d += v0Var.f2687e;
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final int M(z1 z1Var, g2 g2Var) {
        if (this.f2280p == 0) {
            return this.F;
        }
        if (g2Var.b() < 1) {
            return 0;
        }
        return o1(g2Var.b() - 1, z1Var, g2Var) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final View T0(z1 z1Var, g2 g2Var, int i7, int i8, int i9) {
        L0();
        int f7 = this.f2282r.f();
        int e7 = this.f2282r.e();
        int i10 = i8 > i7 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i7 != i8) {
            View v6 = v(i7);
            int K = r1.K(v6);
            if (K >= 0 && K < i9 && p1(K, z1Var, g2Var) == 0) {
                if (((s1) v6.getLayoutParams()).f2652a.isRemoved()) {
                    if (view2 == null) {
                        view2 = v6;
                    }
                } else {
                    if (this.f2282r.d(v6) < e7 && this.f2282r.b(v6) >= f7) {
                        return v6;
                    }
                    if (view == null) {
                        view = v6;
                    }
                }
            }
            i7 += i10;
        }
        return view != null ? view : view2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x00df, code lost:
    
        if (r13 == (r2 > r15)) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x010b, code lost:
    
        if (r13 == (r2 > r8 ? r9 : false)) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x001e, code lost:
    
        if (r22.f2621a.k(r3) != false) goto L5;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0126  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View V(android.view.View r23, int r24, androidx.recyclerview.widget.z1 r25, androidx.recyclerview.widget.g2 r26) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.V(android.view.View, int, androidx.recyclerview.widget.z1, androidx.recyclerview.widget.g2):android.view.View");
    }

    @Override // androidx.recyclerview.widget.r1
    public final void Z(z1 z1Var, g2 g2Var, View view, q0.i iVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof h0)) {
            Y(view, iVar);
            return;
        }
        h0 h0Var = (h0) layoutParams;
        int o12 = o1(h0Var.f2652a.getLayoutPosition(), z1Var, g2Var);
        if (this.f2280p == 0) {
            iVar.g(androidx.fragment.app.o.b(h0Var.f2484e, h0Var.f2485f, o12, 1, false, false));
        } else {
            iVar.g(androidx.fragment.app.o.b(o12, 1, h0Var.f2484e, h0Var.f2485f, false, false));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a7, code lost:
    
        r22.f2676b = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a9, code lost:
    
        return;
     */
    @Override // androidx.recyclerview.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Z0(androidx.recyclerview.widget.z1 r19, androidx.recyclerview.widget.g2 r20, androidx.recyclerview.widget.v0 r21, androidx.recyclerview.widget.u0 r22) {
        /*
            Method dump skipped, instructions count: 648
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.Z0(androidx.recyclerview.widget.z1, androidx.recyclerview.widget.g2, androidx.recyclerview.widget.v0, androidx.recyclerview.widget.u0):void");
    }

    @Override // androidx.recyclerview.widget.r1
    public final void a0(int i7, int i8) {
        n3 n3Var = this.K;
        n3Var.d();
        ((SparseIntArray) n3Var.f1364d).clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void a1(z1 z1Var, g2 g2Var, t0 t0Var, int i7) {
        t1();
        if (g2Var.b() > 0 && !g2Var.f2471g) {
            boolean z6 = i7 == 1;
            int p12 = p1(t0Var.f2663b, z1Var, g2Var);
            if (z6) {
                while (p12 > 0) {
                    int i8 = t0Var.f2663b;
                    if (i8 <= 0) {
                        break;
                    }
                    int i9 = i8 - 1;
                    t0Var.f2663b = i9;
                    p12 = p1(i9, z1Var, g2Var);
                }
            } else {
                int b7 = g2Var.b() - 1;
                int i10 = t0Var.f2663b;
                while (i10 < b7) {
                    int i11 = i10 + 1;
                    int p13 = p1(i11, z1Var, g2Var);
                    if (p13 <= p12) {
                        break;
                    }
                    i10 = i11;
                    p12 = p13;
                }
                t0Var.f2663b = i10;
            }
        }
        m1();
    }

    @Override // androidx.recyclerview.widget.r1
    public final void b0() {
        n3 n3Var = this.K;
        n3Var.d();
        ((SparseIntArray) n3Var.f1364d).clear();
    }

    @Override // androidx.recyclerview.widget.r1
    public final void c0(int i7, int i8) {
        n3 n3Var = this.K;
        n3Var.d();
        ((SparseIntArray) n3Var.f1364d).clear();
    }

    @Override // androidx.recyclerview.widget.r1
    public final void d0(int i7, int i8) {
        n3 n3Var = this.K;
        n3Var.d();
        ((SparseIntArray) n3Var.f1364d).clear();
    }

    @Override // androidx.recyclerview.widget.r1
    public final void e0(int i7, int i8) {
        n3 n3Var = this.K;
        n3Var.d();
        ((SparseIntArray) n3Var.f1364d).clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final void f0(z1 z1Var, g2 g2Var) {
        boolean z6 = g2Var.f2471g;
        SparseIntArray sparseIntArray = this.J;
        SparseIntArray sparseIntArray2 = this.I;
        if (z6) {
            int w6 = w();
            for (int i7 = 0; i7 < w6; i7++) {
                h0 h0Var = (h0) v(i7).getLayoutParams();
                int layoutPosition = h0Var.f2652a.getLayoutPosition();
                sparseIntArray2.put(layoutPosition, h0Var.f2485f);
                sparseIntArray.put(layoutPosition, h0Var.f2484e);
            }
        }
        super.f0(z1Var, g2Var);
        sparseIntArray2.clear();
        sparseIntArray.clear();
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean g(s1 s1Var) {
        return s1Var instanceof h0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final void g0(g2 g2Var) {
        super.g0(g2Var);
        this.E = false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void h1(boolean z6) {
        if (z6) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.h1(false);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final int l(g2 g2Var) {
        return I0(g2Var);
    }

    public final void l1(int i7) {
        int i8;
        int[] iArr = this.G;
        int i9 = this.F;
        if (iArr == null || iArr.length != i9 + 1 || iArr[iArr.length - 1] != i7) {
            iArr = new int[i9 + 1];
        }
        int i10 = 0;
        iArr[0] = 0;
        int i11 = i7 / i9;
        int i12 = i7 % i9;
        int i13 = 0;
        for (int i14 = 1; i14 <= i9; i14++) {
            i10 += i12;
            if (i10 <= 0 || i9 - i10 >= i12) {
                i8 = i11;
            } else {
                i8 = i11 + 1;
                i10 -= i9;
            }
            i13 += i8;
            iArr[i14] = i13;
        }
        this.G = iArr;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final int m(g2 g2Var) {
        return J0(g2Var);
    }

    public final void m1() {
        View[] viewArr = this.H;
        if (viewArr == null || viewArr.length != this.F) {
            this.H = new View[this.F];
        }
    }

    public final int n1(int i7, int i8) {
        if (this.f2280p != 1 || !Y0()) {
            int[] iArr = this.G;
            return iArr[i8 + i7] - iArr[i7];
        }
        int[] iArr2 = this.G;
        int i9 = this.F;
        return iArr2[i9 - i7] - iArr2[(i9 - i7) - i8];
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final int o(g2 g2Var) {
        return I0(g2Var);
    }

    public final int o1(int i7, z1 z1Var, g2 g2Var) {
        boolean z6 = g2Var.f2471g;
        n3 n3Var = this.K;
        if (!z6) {
            return n3Var.a(i7, this.F);
        }
        int b7 = z1Var.b(i7);
        if (b7 == -1) {
            return 0;
        }
        return n3Var.a(b7, this.F);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final int p(g2 g2Var) {
        return J0(g2Var);
    }

    public final int p1(int i7, z1 z1Var, g2 g2Var) {
        boolean z6 = g2Var.f2471g;
        n3 n3Var = this.K;
        if (!z6) {
            return n3Var.b(i7, this.F);
        }
        int i8 = this.J.get(i7, -1);
        if (i8 != -1) {
            return i8;
        }
        int b7 = z1Var.b(i7);
        if (b7 == -1) {
            return 0;
        }
        return n3Var.b(b7, this.F);
    }

    public final int q1(int i7, z1 z1Var, g2 g2Var) {
        boolean z6 = g2Var.f2471g;
        n3 n3Var = this.K;
        if (!z6) {
            n3Var.getClass();
            return 1;
        }
        int i8 = this.I.get(i7, -1);
        if (i8 != -1) {
            return i8;
        }
        if (z1Var.b(i7) == -1) {
            return 1;
        }
        n3Var.getClass();
        return 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final int r0(int i7, z1 z1Var, g2 g2Var) {
        t1();
        m1();
        return super.r0(i7, z1Var, g2Var);
    }

    public final void r1(View view, int i7, boolean z6) {
        int i8;
        int i9;
        h0 h0Var = (h0) view.getLayoutParams();
        Rect rect = h0Var.f2653b;
        int i10 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) h0Var).topMargin + ((ViewGroup.MarginLayoutParams) h0Var).bottomMargin;
        int i11 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) h0Var).leftMargin + ((ViewGroup.MarginLayoutParams) h0Var).rightMargin;
        int n12 = n1(h0Var.f2484e, h0Var.f2485f);
        if (this.f2280p == 1) {
            i9 = r1.x(n12, i7, i11, ((ViewGroup.MarginLayoutParams) h0Var).width, false);
            i8 = r1.x(this.f2282r.g(), this.f2633m, i10, ((ViewGroup.MarginLayoutParams) h0Var).height, true);
        } else {
            int x6 = r1.x(n12, i7, i10, ((ViewGroup.MarginLayoutParams) h0Var).height, false);
            int x7 = r1.x(this.f2282r.g(), this.f2632l, i11, ((ViewGroup.MarginLayoutParams) h0Var).width, true);
            i8 = x6;
            i9 = x7;
        }
        s1 s1Var = (s1) view.getLayoutParams();
        if (z6 ? B0(view, i9, i8, s1Var) : z0(view, i9, i8, s1Var)) {
            view.measure(i9, i8);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final s1 s() {
        return this.f2280p == 0 ? new h0(-2, -1) : new h0(-1, -2);
    }

    public final void s1(int i7) {
        if (i7 == this.F) {
            return;
        }
        this.E = true;
        if (i7 < 1) {
            throw new IllegalArgumentException(a.a.d("Span count should be at least 1. Provided ", i7));
        }
        this.F = i7;
        this.K.d();
        q0();
    }

    @Override // androidx.recyclerview.widget.r1
    public final s1 t(Context context, AttributeSet attributeSet) {
        h0 h0Var = new h0(context, attributeSet);
        h0Var.f2484e = -1;
        h0Var.f2485f = 0;
        return h0Var;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final int t0(int i7, z1 z1Var, g2 g2Var) {
        t1();
        m1();
        return super.t0(i7, z1Var, g2Var);
    }

    public final void t1() {
        int G;
        int J;
        if (this.f2280p == 1) {
            G = this.f2634n - I();
            J = H();
        } else {
            G = this.f2635o - G();
            J = J();
        }
        l1(G - J);
    }

    @Override // androidx.recyclerview.widget.r1
    public final s1 u(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            h0 h0Var = new h0((ViewGroup.MarginLayoutParams) layoutParams);
            h0Var.f2484e = -1;
            h0Var.f2485f = 0;
            return h0Var;
        }
        h0 h0Var2 = new h0(layoutParams);
        h0Var2.f2484e = -1;
        h0Var2.f2485f = 0;
        return h0Var2;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void w0(Rect rect, int i7, int i8) {
        int h7;
        int h8;
        if (this.G == null) {
            super.w0(rect, i7, i8);
        }
        int I = I() + H();
        int G = G() + J();
        if (this.f2280p == 1) {
            int height = rect.height() + G;
            RecyclerView recyclerView = this.f2622b;
            int i9 = p0.d1.OVER_SCROLL_ALWAYS;
            h8 = r1.h(i8, height, p0.l0.d(recyclerView));
            int[] iArr = this.G;
            h7 = r1.h(i7, iArr[iArr.length - 1] + I, p0.l0.e(this.f2622b));
        } else {
            int width = rect.width() + I;
            RecyclerView recyclerView2 = this.f2622b;
            int i10 = p0.d1.OVER_SCROLL_ALWAYS;
            h7 = r1.h(i7, width, p0.l0.e(recyclerView2));
            int[] iArr2 = this.G;
            h8 = r1.h(i8, iArr2[iArr2.length - 1] + G, p0.l0.d(this.f2622b));
        }
        this.f2622b.setMeasuredDimension(h7, h8);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int y(z1 z1Var, g2 g2Var) {
        if (this.f2280p == 1) {
            return this.F;
        }
        if (g2Var.b() < 1) {
            return 0;
        }
        return o1(g2Var.b() - 1, z1Var, g2Var) + 1;
    }
}
