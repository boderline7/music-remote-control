package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class LinearLayoutManager extends r1 implements o0, e2 {
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    public static final int VERTICAL = 1;
    public final t0 A;
    public final u0 B;
    public final int C;
    public final int[] D;

    /* renamed from: p, reason: collision with root package name */
    public int f2280p;

    /* renamed from: q, reason: collision with root package name */
    public v0 f2281q;

    /* renamed from: r, reason: collision with root package name */
    public c1 f2282r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f2283s;

    /* renamed from: t, reason: collision with root package name */
    public final boolean f2284t;

    /* renamed from: u, reason: collision with root package name */
    public boolean f2285u;

    /* renamed from: v, reason: collision with root package name */
    public boolean f2286v;

    /* renamed from: w, reason: collision with root package name */
    public final boolean f2287w;

    /* renamed from: x, reason: collision with root package name */
    public int f2288x;

    /* renamed from: y, reason: collision with root package name */
    public int f2289y;

    /* renamed from: z, reason: collision with root package name */
    public SavedState f2290z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new w0();

        /* renamed from: a, reason: collision with root package name */
        public int f2291a;

        /* renamed from: b, reason: collision with root package name */
        public int f2292b;

        /* renamed from: c, reason: collision with root package name */
        public boolean f2293c;

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeInt(this.f2291a);
            parcel.writeInt(this.f2292b);
            parcel.writeInt(this.f2293c ? 1 : 0);
        }
    }

    public LinearLayoutManager(int i7) {
        this.f2280p = 1;
        this.f2284t = false;
        this.f2285u = false;
        this.f2286v = false;
        this.f2287w = true;
        this.f2288x = -1;
        this.f2289y = Integer.MIN_VALUE;
        this.f2290z = null;
        this.A = new t0();
        this.B = new u0();
        this.C = 2;
        this.D = new int[2];
        g1(i7);
        c(null);
        if (this.f2284t) {
            this.f2284t = false;
            q0();
        }
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i7, int i8) {
        this.f2280p = 1;
        this.f2284t = false;
        this.f2285u = false;
        this.f2286v = false;
        this.f2287w = true;
        this.f2288x = -1;
        this.f2289y = Integer.MIN_VALUE;
        this.f2290z = null;
        this.A = new t0();
        this.B = new u0();
        this.C = 2;
        this.D = new int[2];
        q1 L = r1.L(context, attributeSet, i7, i8);
        g1(L.f2612a);
        boolean z6 = L.f2614c;
        c(null);
        if (z6 != this.f2284t) {
            this.f2284t = z6;
            q0();
        }
        h1(L.f2615d);
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean A0() {
        if (this.f2633m == 1073741824 || this.f2632l == 1073741824) {
            return false;
        }
        int w6 = w();
        for (int i7 = 0; i7 < w6; i7++) {
            ViewGroup.LayoutParams layoutParams = v(i7).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.r1
    public void C0(RecyclerView recyclerView, int i7) {
        x0 x0Var = new x0(recyclerView.getContext());
        x0Var.setTargetPosition(i7);
        D0(x0Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public boolean E0() {
        return this.f2290z == null && this.f2283s == this.f2286v;
    }

    public void F0(g2 g2Var, int[] iArr) {
        int i7;
        int g7 = g2Var.f2465a != -1 ? this.f2282r.g() : 0;
        if (this.f2281q.f2688f == -1) {
            i7 = 0;
        } else {
            i7 = g7;
            g7 = 0;
        }
        iArr[0] = g7;
        iArr[1] = i7;
    }

    public void G0(g2 g2Var, v0 v0Var, e0 e0Var) {
        int i7 = v0Var.f2686d;
        if (i7 < 0 || i7 >= g2Var.b()) {
            return;
        }
        e0Var.a(i7, Math.max(0, v0Var.f2689g));
    }

    public final int H0(g2 g2Var) {
        if (w() == 0) {
            return 0;
        }
        L0();
        c1 c1Var = this.f2282r;
        boolean z6 = !this.f2287w;
        return n1.z.E(g2Var, c1Var, O0(z6), N0(z6), this, this.f2287w);
    }

    public final int I0(g2 g2Var) {
        if (w() == 0) {
            return 0;
        }
        L0();
        c1 c1Var = this.f2282r;
        boolean z6 = !this.f2287w;
        return n1.z.F(g2Var, c1Var, O0(z6), N0(z6), this, this.f2287w, this.f2285u);
    }

    public final int J0(g2 g2Var) {
        if (w() == 0) {
            return 0;
        }
        L0();
        c1 c1Var = this.f2282r;
        boolean z6 = !this.f2287w;
        return n1.z.G(g2Var, c1Var, O0(z6), N0(z6), this, this.f2287w);
    }

    public final int K0(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 17 ? i7 != 33 ? i7 != 66 ? (i7 == 130 && this.f2280p == 1) ? 1 : Integer.MIN_VALUE : this.f2280p == 0 ? 1 : Integer.MIN_VALUE : this.f2280p == 1 ? -1 : Integer.MIN_VALUE : this.f2280p == 0 ? -1 : Integer.MIN_VALUE : (this.f2280p != 1 && Y0()) ? -1 : 1 : (this.f2280p != 1 && Y0()) ? 1 : -1;
    }

    public final void L0() {
        if (this.f2281q == null) {
            v0 v0Var = new v0();
            v0Var.f2683a = true;
            v0Var.f2690h = 0;
            v0Var.f2691i = 0;
            v0Var.f2693k = null;
            this.f2281q = v0Var;
        }
    }

    public final int M0(z1 z1Var, v0 v0Var, g2 g2Var, boolean z6) {
        int i7;
        int i8 = v0Var.f2685c;
        int i9 = v0Var.f2689g;
        if (i9 != Integer.MIN_VALUE) {
            if (i8 < 0) {
                v0Var.f2689g = i9 + i8;
            }
            b1(z1Var, v0Var);
        }
        int i10 = v0Var.f2685c + v0Var.f2690h;
        while (true) {
            if ((!v0Var.f2694l && i10 <= 0) || (i7 = v0Var.f2686d) < 0 || i7 >= g2Var.b()) {
                break;
            }
            u0 u0Var = this.B;
            u0Var.f2675a = 0;
            u0Var.f2676b = false;
            u0Var.f2677c = false;
            u0Var.f2678d = false;
            Z0(z1Var, g2Var, v0Var, u0Var);
            if (!u0Var.f2676b) {
                int i11 = v0Var.f2684b;
                int i12 = u0Var.f2675a;
                v0Var.f2684b = (v0Var.f2688f * i12) + i11;
                if (!u0Var.f2677c || v0Var.f2693k != null || !g2Var.f2471g) {
                    v0Var.f2685c -= i12;
                    i10 -= i12;
                }
                int i13 = v0Var.f2689g;
                if (i13 != Integer.MIN_VALUE) {
                    int i14 = i13 + i12;
                    v0Var.f2689g = i14;
                    int i15 = v0Var.f2685c;
                    if (i15 < 0) {
                        v0Var.f2689g = i14 + i15;
                    }
                    b1(z1Var, v0Var);
                }
                if (z6 && u0Var.f2678d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i8 - v0Var.f2685c;
    }

    public final View N0(boolean z6) {
        return this.f2285u ? S0(0, w(), z6) : S0(w() - 1, -1, z6);
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean O() {
        return true;
    }

    public final View O0(boolean z6) {
        return this.f2285u ? S0(w() - 1, -1, z6) : S0(0, w(), z6);
    }

    public final int P0() {
        View S0 = S0(0, w(), false);
        if (S0 == null) {
            return -1;
        }
        return r1.K(S0);
    }

    public final int Q0() {
        View S0 = S0(w() - 1, -1, false);
        if (S0 == null) {
            return -1;
        }
        return r1.K(S0);
    }

    public final View R0(int i7, int i8) {
        int i9;
        int i10;
        L0();
        if (i8 <= i7 && i8 >= i7) {
            return v(i7);
        }
        if (this.f2282r.d(v(i7)) < this.f2282r.f()) {
            i9 = 16644;
            i10 = 16388;
        } else {
            i9 = 4161;
            i10 = androidx.fragment.app.h1.TRANSIT_FRAGMENT_OPEN;
        }
        return this.f2280p == 0 ? this.f2623c.f(i7, i8, i9, i10) : this.f2624d.f(i7, i8, i9, i10);
    }

    public final View S0(int i7, int i8, boolean z6) {
        L0();
        int i9 = z6 ? 24579 : 320;
        return this.f2280p == 0 ? this.f2623c.f(i7, i8, i9, 320) : this.f2624d.f(i7, i8, i9, 320);
    }

    public View T0(z1 z1Var, g2 g2Var, int i7, int i8, int i9) {
        L0();
        int f7 = this.f2282r.f();
        int e7 = this.f2282r.e();
        int i10 = i8 > i7 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i7 != i8) {
            View v6 = v(i7);
            int K = r1.K(v6);
            if (K >= 0 && K < i9) {
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

    @Override // androidx.recyclerview.widget.r1
    public final void U(RecyclerView recyclerView) {
    }

    public final int U0(int i7, z1 z1Var, g2 g2Var, boolean z6) {
        int e7;
        int e8 = this.f2282r.e() - i7;
        if (e8 <= 0) {
            return 0;
        }
        int i8 = -e1(-e8, z1Var, g2Var);
        int i9 = i7 + i8;
        if (!z6 || (e7 = this.f2282r.e() - i9) <= 0) {
            return i8;
        }
        this.f2282r.k(e7);
        return e7 + i8;
    }

    @Override // androidx.recyclerview.widget.r1
    public View V(View view, int i7, z1 z1Var, g2 g2Var) {
        int K0;
        d1();
        if (w() == 0 || (K0 = K0(i7)) == Integer.MIN_VALUE) {
            return null;
        }
        L0();
        i1(K0, (int) (this.f2282r.g() * 0.33333334f), false, g2Var);
        v0 v0Var = this.f2281q;
        v0Var.f2689g = Integer.MIN_VALUE;
        v0Var.f2683a = false;
        M0(z1Var, v0Var, g2Var, true);
        View R0 = K0 == -1 ? this.f2285u ? R0(w() - 1, -1) : R0(0, w()) : this.f2285u ? R0(0, w()) : R0(w() - 1, -1);
        View X0 = K0 == -1 ? X0() : W0();
        if (!X0.hasFocusable()) {
            return R0;
        }
        if (R0 == null) {
            return null;
        }
        return X0;
    }

    public final int V0(int i7, z1 z1Var, g2 g2Var, boolean z6) {
        int f7;
        int f8 = i7 - this.f2282r.f();
        if (f8 <= 0) {
            return 0;
        }
        int i8 = -e1(f8, z1Var, g2Var);
        int i9 = i7 + i8;
        if (!z6 || (f7 = i9 - this.f2282r.f()) <= 0) {
            return i8;
        }
        this.f2282r.k(-f7);
        return i8 - f7;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void W(AccessibilityEvent accessibilityEvent) {
        super.W(accessibilityEvent);
        if (w() > 0) {
            accessibilityEvent.setFromIndex(P0());
            accessibilityEvent.setToIndex(Q0());
        }
    }

    public final View W0() {
        return v(this.f2285u ? 0 : w() - 1);
    }

    public final View X0() {
        return v(this.f2285u ? w() - 1 : 0);
    }

    public final boolean Y0() {
        return F() == 1;
    }

    public void Z0(z1 z1Var, g2 g2Var, v0 v0Var, u0 u0Var) {
        int i7;
        int i8;
        int i9;
        int i10;
        View b7 = v0Var.b(z1Var);
        if (b7 == null) {
            u0Var.f2676b = true;
            return;
        }
        s1 s1Var = (s1) b7.getLayoutParams();
        if (v0Var.f2693k == null) {
            if (this.f2285u == (v0Var.f2688f == -1)) {
                b(b7, -1, false);
            } else {
                b(b7, 0, false);
            }
        } else {
            if (this.f2285u == (v0Var.f2688f == -1)) {
                b(b7, -1, true);
            } else {
                b(b7, 0, true);
            }
        }
        s1 s1Var2 = (s1) b7.getLayoutParams();
        Rect M = this.f2622b.M(b7);
        int i11 = M.left + M.right;
        int i12 = M.top + M.bottom;
        int x6 = r1.x(this.f2634n, this.f2632l, I() + H() + ((ViewGroup.MarginLayoutParams) s1Var2).leftMargin + ((ViewGroup.MarginLayoutParams) s1Var2).rightMargin + i11, ((ViewGroup.MarginLayoutParams) s1Var2).width, e());
        int x7 = r1.x(this.f2635o, this.f2633m, G() + J() + ((ViewGroup.MarginLayoutParams) s1Var2).topMargin + ((ViewGroup.MarginLayoutParams) s1Var2).bottomMargin + i12, ((ViewGroup.MarginLayoutParams) s1Var2).height, f());
        if (z0(b7, x6, x7, s1Var2)) {
            b7.measure(x6, x7);
        }
        u0Var.f2675a = this.f2282r.c(b7);
        if (this.f2280p == 1) {
            if (Y0()) {
                i10 = this.f2634n - I();
                i7 = i10 - this.f2282r.l(b7);
            } else {
                i7 = H();
                i10 = this.f2282r.l(b7) + i7;
            }
            if (v0Var.f2688f == -1) {
                i8 = v0Var.f2684b;
                i9 = i8 - u0Var.f2675a;
            } else {
                i9 = v0Var.f2684b;
                i8 = u0Var.f2675a + i9;
            }
        } else {
            int J = J();
            int l7 = this.f2282r.l(b7) + J;
            if (v0Var.f2688f == -1) {
                int i13 = v0Var.f2684b;
                int i14 = i13 - u0Var.f2675a;
                i10 = i13;
                i8 = l7;
                i7 = i14;
                i9 = J;
            } else {
                int i15 = v0Var.f2684b;
                int i16 = u0Var.f2675a + i15;
                i7 = i15;
                i8 = l7;
                i9 = J;
                i10 = i16;
            }
        }
        r1.Q(b7, i7, i9, i10, i8);
        if (s1Var.f2652a.isRemoved() || s1Var.f2652a.isUpdated()) {
            u0Var.f2677c = true;
        }
        u0Var.f2678d = b7.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.e2
    public final PointF a(int i7) {
        if (w() == 0) {
            return null;
        }
        int i8 = (i7 < r1.K(v(0))) != this.f2285u ? -1 : 1;
        return this.f2280p == 0 ? new PointF(i8, CropImageView.DEFAULT_ASPECT_RATIO) : new PointF(CropImageView.DEFAULT_ASPECT_RATIO, i8);
    }

    public void a1(z1 z1Var, g2 g2Var, t0 t0Var, int i7) {
    }

    public final void b1(z1 z1Var, v0 v0Var) {
        int i7;
        if (!v0Var.f2683a || v0Var.f2694l) {
            return;
        }
        int i8 = v0Var.f2689g;
        int i9 = v0Var.f2691i;
        if (v0Var.f2688f != -1) {
            if (i8 < 0) {
                return;
            }
            int i10 = i8 - i9;
            int w6 = w();
            if (!this.f2285u) {
                for (int i11 = 0; i11 < w6; i11++) {
                    View v6 = v(i11);
                    if (this.f2282r.b(v6) > i10 || this.f2282r.i(v6) > i10) {
                        c1(z1Var, 0, i11);
                        return;
                    }
                }
                return;
            }
            int i12 = w6 - 1;
            for (int i13 = i12; i13 >= 0; i13--) {
                View v7 = v(i13);
                if (this.f2282r.b(v7) > i10 || this.f2282r.i(v7) > i10) {
                    c1(z1Var, i12, i13);
                    return;
                }
            }
            return;
        }
        int w7 = w();
        if (i8 < 0) {
            return;
        }
        c1 c1Var = this.f2282r;
        int i14 = c1Var.f2393d;
        r1 r1Var = c1Var.f2425a;
        switch (i14) {
            case 0:
                i7 = r1Var.f2634n;
                break;
            default:
                i7 = r1Var.f2635o;
                break;
        }
        int i15 = (i7 - i8) + i9;
        if (this.f2285u) {
            for (int i16 = 0; i16 < w7; i16++) {
                View v8 = v(i16);
                if (this.f2282r.d(v8) < i15 || this.f2282r.j(v8) < i15) {
                    c1(z1Var, 0, i16);
                    return;
                }
            }
            return;
        }
        int i17 = w7 - 1;
        for (int i18 = i17; i18 >= 0; i18--) {
            View v9 = v(i18);
            if (this.f2282r.d(v9) < i15 || this.f2282r.j(v9) < i15) {
                c1(z1Var, i17, i18);
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final void c(String str) {
        if (this.f2290z == null) {
            super.c(str);
        }
    }

    public final void c1(z1 z1Var, int i7, int i8) {
        if (i7 == i8) {
            return;
        }
        if (i8 <= i7) {
            while (i7 > i8) {
                View v6 = v(i7);
                o0(i7);
                z1Var.f(v6);
                i7--;
            }
            return;
        }
        for (int i9 = i8 - 1; i9 >= i7; i9--) {
            View v7 = v(i9);
            o0(i9);
            z1Var.f(v7);
        }
    }

    public final void d1() {
        if (this.f2280p == 1 || !Y0()) {
            this.f2285u = this.f2284t;
        } else {
            this.f2285u = !this.f2284t;
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean e() {
        return this.f2280p == 0;
    }

    public final int e1(int i7, z1 z1Var, g2 g2Var) {
        if (w() == 0 || i7 == 0) {
            return 0;
        }
        L0();
        this.f2281q.f2683a = true;
        int i8 = i7 > 0 ? 1 : -1;
        int abs = Math.abs(i7);
        i1(i8, abs, true, g2Var);
        v0 v0Var = this.f2281q;
        int M0 = M0(z1Var, v0Var, g2Var, false) + v0Var.f2689g;
        if (M0 < 0) {
            return 0;
        }
        if (abs > M0) {
            i7 = i8 * M0;
        }
        this.f2282r.k(-i7);
        this.f2281q.f2692j = i7;
        return i7;
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean f() {
        return this.f2280p == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0413  */
    @Override // androidx.recyclerview.widget.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f0(androidx.recyclerview.widget.z1 r18, androidx.recyclerview.widget.g2 r19) {
        /*
            Method dump skipped, instructions count: 1198
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.f0(androidx.recyclerview.widget.z1, androidx.recyclerview.widget.g2):void");
    }

    public final void f1(int i7, int i8) {
        this.f2288x = i7;
        this.f2289y = i8;
        SavedState savedState = this.f2290z;
        if (savedState != null) {
            savedState.f2291a = -1;
        }
        q0();
    }

    @Override // androidx.recyclerview.widget.r1
    public void g0(g2 g2Var) {
        this.f2290z = null;
        this.f2288x = -1;
        this.f2289y = Integer.MIN_VALUE;
        this.A.d();
    }

    public final void g1(int i7) {
        if (i7 != 0 && i7 != 1) {
            throw new IllegalArgumentException(a.a.d("invalid orientation:", i7));
        }
        c(null);
        if (i7 != this.f2280p || this.f2282r == null) {
            c1 a7 = d1.a(this, i7);
            this.f2282r = a7;
            this.A.f2662a = a7;
            this.f2280p = i7;
            q0();
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final void h0(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.f2290z = (SavedState) parcelable;
            q0();
        }
    }

    public void h1(boolean z6) {
        c(null);
        if (this.f2286v == z6) {
            return;
        }
        this.f2286v = z6;
        q0();
    }

    @Override // androidx.recyclerview.widget.r1
    public final void i(int i7, int i8, g2 g2Var, e0 e0Var) {
        if (this.f2280p != 0) {
            i7 = i8;
        }
        if (w() == 0 || i7 == 0) {
            return;
        }
        L0();
        i1(i7 > 0 ? 1 : -1, Math.abs(i7), true, g2Var);
        G0(g2Var, this.f2281q, e0Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final Parcelable i0() {
        SavedState savedState = this.f2290z;
        if (savedState != null) {
            SavedState savedState2 = new SavedState();
            savedState2.f2291a = savedState.f2291a;
            savedState2.f2292b = savedState.f2292b;
            savedState2.f2293c = savedState.f2293c;
            return savedState2;
        }
        SavedState savedState3 = new SavedState();
        if (w() > 0) {
            L0();
            boolean z6 = this.f2283s ^ this.f2285u;
            savedState3.f2293c = z6;
            if (z6) {
                View W0 = W0();
                savedState3.f2292b = this.f2282r.e() - this.f2282r.b(W0);
                savedState3.f2291a = r1.K(W0);
            } else {
                View X0 = X0();
                savedState3.f2291a = r1.K(X0);
                savedState3.f2292b = this.f2282r.d(X0) - this.f2282r.f();
            }
        } else {
            savedState3.f2291a = -1;
        }
        return savedState3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void i1(int r7, int r8, boolean r9, androidx.recyclerview.widget.g2 r10) {
        /*
            Method dump skipped, instructions count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.i1(int, int, boolean, androidx.recyclerview.widget.g2):void");
    }

    @Override // androidx.recyclerview.widget.r1
    public final void j(int i7, e0 e0Var) {
        boolean z6;
        int i8;
        SavedState savedState = this.f2290z;
        if (savedState == null || (i8 = savedState.f2291a) < 0) {
            d1();
            z6 = this.f2285u;
            i8 = this.f2288x;
            if (i8 == -1) {
                i8 = z6 ? i7 - 1 : 0;
            }
        } else {
            z6 = savedState.f2293c;
        }
        int i9 = z6 ? -1 : 1;
        for (int i10 = 0; i10 < this.C && i8 >= 0 && i8 < i7; i10++) {
            e0Var.a(i8, 0);
            i8 += i9;
        }
    }

    public final void j1(int i7, int i8) {
        this.f2281q.f2685c = this.f2282r.e() - i8;
        v0 v0Var = this.f2281q;
        v0Var.f2687e = this.f2285u ? -1 : 1;
        v0Var.f2686d = i7;
        v0Var.f2688f = 1;
        v0Var.f2684b = i8;
        v0Var.f2689g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.r1
    public final int k(g2 g2Var) {
        return H0(g2Var);
    }

    public final void k1(int i7, int i8) {
        this.f2281q.f2685c = i8 - this.f2282r.f();
        v0 v0Var = this.f2281q;
        v0Var.f2686d = i7;
        v0Var.f2687e = this.f2285u ? 1 : -1;
        v0Var.f2688f = -1;
        v0Var.f2684b = i8;
        v0Var.f2689g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.r1
    public int l(g2 g2Var) {
        return I0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public int m(g2 g2Var) {
        return J0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int n(g2 g2Var) {
        return H0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public int o(g2 g2Var) {
        return I0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public int p(g2 g2Var) {
        return J0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final View r(int i7) {
        int w6 = w();
        if (w6 == 0) {
            return null;
        }
        int K = i7 - r1.K(v(0));
        if (K >= 0 && K < w6) {
            View v6 = v(K);
            if (r1.K(v6) == i7) {
                return v6;
            }
        }
        return super.r(i7);
    }

    @Override // androidx.recyclerview.widget.r1
    public int r0(int i7, z1 z1Var, g2 g2Var) {
        if (this.f2280p == 1) {
            return 0;
        }
        return e1(i7, z1Var, g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public s1 s() {
        return new s1(-2, -2);
    }

    @Override // androidx.recyclerview.widget.r1
    public final void s0(int i7) {
        this.f2288x = i7;
        this.f2289y = Integer.MIN_VALUE;
        SavedState savedState = this.f2290z;
        if (savedState != null) {
            savedState.f2291a = -1;
        }
        q0();
    }

    @Override // androidx.recyclerview.widget.r1
    public int t0(int i7, z1 z1Var, g2 g2Var) {
        if (this.f2280p == 0) {
            return 0;
        }
        return e1(i7, z1Var, g2Var);
    }
}
