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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class StaggeredGridLayoutManager extends r1 implements e2 {

    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public final q2 B;
    public final int C;
    public boolean D;
    public boolean E;
    public SavedState F;
    public final Rect G;
    public final n2 H;
    public final boolean I;
    public int[] J;
    public final z K;

    /* renamed from: p, reason: collision with root package name */
    public final int f2346p;

    /* renamed from: q, reason: collision with root package name */
    public final s2[] f2347q;

    /* renamed from: r, reason: collision with root package name */
    public final d1 f2348r;

    /* renamed from: s, reason: collision with root package name */
    public final d1 f2349s;

    /* renamed from: t, reason: collision with root package name */
    public final int f2350t;

    /* renamed from: u, reason: collision with root package name */
    public int f2351u;

    /* renamed from: v, reason: collision with root package name */
    public final s0 f2352v;

    /* renamed from: w, reason: collision with root package name */
    public boolean f2353w;

    /* renamed from: y, reason: collision with root package name */
    public final BitSet f2355y;

    /* renamed from: x, reason: collision with root package name */
    public boolean f2354x = false;

    /* renamed from: z, reason: collision with root package name */
    public int f2356z = -1;
    public int A = Integer.MIN_VALUE;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new r2();

        /* renamed from: a, reason: collision with root package name */
        public int f2361a;

        /* renamed from: b, reason: collision with root package name */
        public int f2362b;

        /* renamed from: c, reason: collision with root package name */
        public int f2363c;

        /* renamed from: d, reason: collision with root package name */
        public int[] f2364d;

        /* renamed from: e, reason: collision with root package name */
        public int f2365e;

        /* renamed from: f, reason: collision with root package name */
        public int[] f2366f;

        /* renamed from: g, reason: collision with root package name */
        public List f2367g;

        /* renamed from: h, reason: collision with root package name */
        public boolean f2368h;

        /* renamed from: i, reason: collision with root package name */
        public boolean f2369i;

        /* renamed from: j, reason: collision with root package name */
        public boolean f2370j;

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeInt(this.f2361a);
            parcel.writeInt(this.f2362b);
            parcel.writeInt(this.f2363c);
            if (this.f2363c > 0) {
                parcel.writeIntArray(this.f2364d);
            }
            parcel.writeInt(this.f2365e);
            if (this.f2365e > 0) {
                parcel.writeIntArray(this.f2366f);
            }
            parcel.writeInt(this.f2368h ? 1 : 0);
            parcel.writeInt(this.f2369i ? 1 : 0);
            parcel.writeInt(this.f2370j ? 1 : 0);
            parcel.writeList(this.f2367g);
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i7, int i8) {
        this.f2346p = -1;
        this.f2353w = false;
        q2 q2Var = new q2(0);
        this.B = q2Var;
        this.C = 2;
        this.G = new Rect();
        this.H = new n2(this);
        this.I = true;
        this.K = new z(2, this);
        q1 L = r1.L(context, attributeSet, i7, i8);
        int i9 = L.f2612a;
        if (i9 != 0 && i9 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        c(null);
        if (i9 != this.f2350t) {
            this.f2350t = i9;
            d1 d1Var = this.f2348r;
            this.f2348r = this.f2349s;
            this.f2349s = d1Var;
            q0();
        }
        int i10 = L.f2613b;
        c(null);
        if (i10 != this.f2346p) {
            q2Var.d();
            q0();
            this.f2346p = i10;
            this.f2355y = new BitSet(this.f2346p);
            this.f2347q = new s2[this.f2346p];
            for (int i11 = 0; i11 < this.f2346p; i11++) {
                this.f2347q[i11] = new s2(this, i11);
            }
            q0();
        }
        boolean z6 = L.f2614c;
        c(null);
        SavedState savedState = this.F;
        if (savedState != null && savedState.f2368h != z6) {
            savedState.f2368h = z6;
        }
        this.f2353w = z6;
        q0();
        s0 s0Var = new s0();
        s0Var.f2643a = true;
        s0Var.f2648f = 0;
        s0Var.f2649g = 0;
        this.f2352v = s0Var;
        this.f2348r = d1.a(this, this.f2350t);
        this.f2349s = d1.a(this, 1 - this.f2350t);
    }

    public static int i1(int i7, int i8, int i9) {
        if (i8 == 0 && i9 == 0) {
            return i7;
        }
        int mode = View.MeasureSpec.getMode(i7);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i7) - i8) - i9), mode) : i7;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void C0(RecyclerView recyclerView, int i7) {
        x0 x0Var = new x0(recyclerView.getContext());
        x0Var.setTargetPosition(i7);
        D0(x0Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean E0() {
        return this.F == null;
    }

    public final int F0(int i7) {
        if (w() == 0) {
            return this.f2354x ? 1 : -1;
        }
        return (i7 < P0()) != this.f2354x ? -1 : 1;
    }

    public final boolean G0() {
        int P0;
        if (w() != 0 && this.C != 0 && this.f2627g) {
            if (this.f2354x) {
                P0 = Q0();
                P0();
            } else {
                P0 = P0();
                Q0();
            }
            q2 q2Var = this.B;
            if (P0 == 0 && U0() != null) {
                q2Var.d();
                this.f2626f = true;
                q0();
                return true;
            }
        }
        return false;
    }

    public final int H0(g2 g2Var) {
        if (w() == 0) {
            return 0;
        }
        d1 d1Var = this.f2348r;
        boolean z6 = this.I;
        return n1.z.E(g2Var, d1Var, M0(!z6), L0(!z6), this, this.I);
    }

    public final int I0(g2 g2Var) {
        if (w() == 0) {
            return 0;
        }
        d1 d1Var = this.f2348r;
        boolean z6 = this.I;
        return n1.z.F(g2Var, d1Var, M0(!z6), L0(!z6), this, this.I, this.f2354x);
    }

    public final int J0(g2 g2Var) {
        if (w() == 0) {
            return 0;
        }
        d1 d1Var = this.f2348r;
        boolean z6 = this.I;
        return n1.z.G(g2Var, d1Var, M0(!z6), L0(!z6), this, this.I);
    }

    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean, int] */
    public final int K0(z1 z1Var, s0 s0Var, g2 g2Var) {
        s2 s2Var;
        ?? r6;
        int i7;
        int h7;
        int c7;
        int f7;
        int c8;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12 = 0;
        int i13 = 1;
        this.f2355y.set(0, this.f2346p, true);
        s0 s0Var2 = this.f2352v;
        int i14 = s0Var2.f2651i ? s0Var.f2647e == 1 ? androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED : Integer.MIN_VALUE : s0Var.f2647e == 1 ? s0Var.f2649g + s0Var.f2644b : s0Var.f2648f - s0Var.f2644b;
        int i15 = s0Var.f2647e;
        for (int i16 = 0; i16 < this.f2346p; i16++) {
            if (!this.f2347q[i16].f2656a.isEmpty()) {
                h1(this.f2347q[i16], i15, i14);
            }
        }
        int e7 = this.f2354x ? this.f2348r.e() : this.f2348r.f();
        boolean z6 = false;
        while (true) {
            int i17 = s0Var.f2645c;
            if (((i17 < 0 || i17 >= g2Var.b()) ? i12 : i13) == 0 || (!s0Var2.f2651i && this.f2355y.isEmpty())) {
                break;
            }
            View view = z1Var.i(s0Var.f2645c, Long.MAX_VALUE).itemView;
            s0Var.f2645c += s0Var.f2646d;
            o2 o2Var = (o2) view.getLayoutParams();
            int layoutPosition = o2Var.f2652a.getLayoutPosition();
            q2 q2Var = this.B;
            int[] iArr = (int[]) q2Var.f2617b;
            int i18 = (iArr == null || layoutPosition >= iArr.length) ? -1 : iArr[layoutPosition];
            if (i18 == -1) {
                if (Y0(s0Var.f2647e)) {
                    i11 = this.f2346p - i13;
                    i10 = -1;
                    i9 = -1;
                } else {
                    i9 = i13;
                    i10 = this.f2346p;
                    i11 = i12;
                }
                s2 s2Var2 = null;
                if (s0Var.f2647e == i13) {
                    int f8 = this.f2348r.f();
                    int i19 = androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
                    while (i11 != i10) {
                        s2 s2Var3 = this.f2347q[i11];
                        int f9 = s2Var3.f(f8);
                        if (f9 < i19) {
                            i19 = f9;
                            s2Var2 = s2Var3;
                        }
                        i11 += i9;
                    }
                } else {
                    int e8 = this.f2348r.e();
                    int i20 = Integer.MIN_VALUE;
                    while (i11 != i10) {
                        s2 s2Var4 = this.f2347q[i11];
                        int h8 = s2Var4.h(e8);
                        if (h8 > i20) {
                            s2Var2 = s2Var4;
                            i20 = h8;
                        }
                        i11 += i9;
                    }
                }
                s2Var = s2Var2;
                q2Var.e(layoutPosition);
                ((int[]) q2Var.f2617b)[layoutPosition] = s2Var.f2660e;
            } else {
                s2Var = this.f2347q[i18];
            }
            o2Var.f2565e = s2Var;
            if (s0Var.f2647e == 1) {
                r6 = 0;
                b(view, -1, false);
            } else {
                r6 = 0;
                b(view, 0, false);
            }
            if (this.f2350t == 1) {
                i7 = 1;
                W0(view, r1.x(this.f2351u, this.f2632l, r6, ((ViewGroup.MarginLayoutParams) o2Var).width, r6), r1.x(this.f2635o, this.f2633m, G() + J(), ((ViewGroup.MarginLayoutParams) o2Var).height, true));
            } else {
                i7 = 1;
                W0(view, r1.x(this.f2634n, this.f2632l, I() + H(), ((ViewGroup.MarginLayoutParams) o2Var).width, true), r1.x(this.f2351u, this.f2633m, 0, ((ViewGroup.MarginLayoutParams) o2Var).height, false));
            }
            if (s0Var.f2647e == i7) {
                c7 = s2Var.f(e7);
                h7 = this.f2348r.c(view) + c7;
            } else {
                h7 = s2Var.h(e7);
                c7 = h7 - this.f2348r.c(view);
            }
            if (s0Var.f2647e == 1) {
                s2 s2Var5 = o2Var.f2565e;
                s2Var5.getClass();
                o2 o2Var2 = (o2) view.getLayoutParams();
                o2Var2.f2565e = s2Var5;
                ArrayList arrayList = s2Var5.f2656a;
                arrayList.add(view);
                s2Var5.f2658c = Integer.MIN_VALUE;
                if (arrayList.size() == 1) {
                    s2Var5.f2657b = Integer.MIN_VALUE;
                }
                if (o2Var2.f2652a.isRemoved() || o2Var2.f2652a.isUpdated()) {
                    s2Var5.f2659d = s2Var5.f2661f.f2348r.c(view) + s2Var5.f2659d;
                }
            } else {
                s2 s2Var6 = o2Var.f2565e;
                s2Var6.getClass();
                o2 o2Var3 = (o2) view.getLayoutParams();
                o2Var3.f2565e = s2Var6;
                ArrayList arrayList2 = s2Var6.f2656a;
                arrayList2.add(0, view);
                s2Var6.f2657b = Integer.MIN_VALUE;
                if (arrayList2.size() == 1) {
                    s2Var6.f2658c = Integer.MIN_VALUE;
                }
                if (o2Var3.f2652a.isRemoved() || o2Var3.f2652a.isUpdated()) {
                    s2Var6.f2659d = s2Var6.f2661f.f2348r.c(view) + s2Var6.f2659d;
                }
            }
            if (V0() && this.f2350t == 1) {
                c8 = this.f2349s.e() - (((this.f2346p - 1) - s2Var.f2660e) * this.f2351u);
                f7 = c8 - this.f2349s.c(view);
            } else {
                f7 = this.f2349s.f() + (s2Var.f2660e * this.f2351u);
                c8 = this.f2349s.c(view) + f7;
            }
            if (this.f2350t == 1) {
                r1.Q(view, f7, c7, c8, h7);
            } else {
                r1.Q(view, c7, f7, h7, c8);
            }
            h1(s2Var, s0Var2.f2647e, i14);
            a1(z1Var, s0Var2);
            if (s0Var2.f2650h && view.hasFocusable()) {
                i8 = 0;
                this.f2355y.set(s2Var.f2660e, false);
            } else {
                i8 = 0;
            }
            i12 = i8;
            i13 = 1;
            z6 = true;
        }
        int i21 = i12;
        if (!z6) {
            a1(z1Var, s0Var2);
        }
        int f10 = s0Var2.f2647e == -1 ? this.f2348r.f() - S0(this.f2348r.f()) : R0(this.f2348r.e()) - this.f2348r.e();
        return f10 > 0 ? Math.min(s0Var.f2644b, f10) : i21;
    }

    public final View L0(boolean z6) {
        int f7 = this.f2348r.f();
        int e7 = this.f2348r.e();
        View view = null;
        for (int w6 = w() - 1; w6 >= 0; w6--) {
            View v6 = v(w6);
            int d7 = this.f2348r.d(v6);
            int b7 = this.f2348r.b(v6);
            if (b7 > f7 && d7 < e7) {
                if (b7 <= e7 || !z6) {
                    return v6;
                }
                if (view == null) {
                    view = v6;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.r1
    public final int M(z1 z1Var, g2 g2Var) {
        return this.f2350t == 0 ? this.f2346p : super.M(z1Var, g2Var);
    }

    public final View M0(boolean z6) {
        int f7 = this.f2348r.f();
        int e7 = this.f2348r.e();
        int w6 = w();
        View view = null;
        for (int i7 = 0; i7 < w6; i7++) {
            View v6 = v(i7);
            int d7 = this.f2348r.d(v6);
            if (this.f2348r.b(v6) > f7 && d7 < e7) {
                if (d7 >= f7 || !z6) {
                    return v6;
                }
                if (view == null) {
                    view = v6;
                }
            }
        }
        return view;
    }

    public final void N0(z1 z1Var, g2 g2Var, boolean z6) {
        int e7;
        int R0 = R0(Integer.MIN_VALUE);
        if (R0 != Integer.MIN_VALUE && (e7 = this.f2348r.e() - R0) > 0) {
            int i7 = e7 - (-e1(-e7, z1Var, g2Var));
            if (!z6 || i7 <= 0) {
                return;
            }
            this.f2348r.k(i7);
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean O() {
        return this.C != 0;
    }

    public final void O0(z1 z1Var, g2 g2Var, boolean z6) {
        int f7;
        int S0 = S0(androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (S0 != Integer.MAX_VALUE && (f7 = S0 - this.f2348r.f()) > 0) {
            int e12 = f7 - e1(f7, z1Var, g2Var);
            if (!z6 || e12 <= 0) {
                return;
            }
            this.f2348r.k(-e12);
        }
    }

    public final int P0() {
        if (w() == 0) {
            return 0;
        }
        return r1.K(v(0));
    }

    public final int Q0() {
        int w6 = w();
        if (w6 == 0) {
            return 0;
        }
        return r1.K(v(w6 - 1));
    }

    @Override // androidx.recyclerview.widget.r1
    public final void R(int i7) {
        super.R(i7);
        for (int i8 = 0; i8 < this.f2346p; i8++) {
            s2 s2Var = this.f2347q[i8];
            int i9 = s2Var.f2657b;
            if (i9 != Integer.MIN_VALUE) {
                s2Var.f2657b = i9 + i7;
            }
            int i10 = s2Var.f2658c;
            if (i10 != Integer.MIN_VALUE) {
                s2Var.f2658c = i10 + i7;
            }
        }
    }

    public final int R0(int i7) {
        int f7 = this.f2347q[0].f(i7);
        for (int i8 = 1; i8 < this.f2346p; i8++) {
            int f8 = this.f2347q[i8].f(i7);
            if (f8 > f7) {
                f7 = f8;
            }
        }
        return f7;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void S(int i7) {
        super.S(i7);
        for (int i8 = 0; i8 < this.f2346p; i8++) {
            s2 s2Var = this.f2347q[i8];
            int i9 = s2Var.f2657b;
            if (i9 != Integer.MIN_VALUE) {
                s2Var.f2657b = i9 + i7;
            }
            int i10 = s2Var.f2658c;
            if (i10 != Integer.MIN_VALUE) {
                s2Var.f2658c = i10 + i7;
            }
        }
    }

    public final int S0(int i7) {
        int h7 = this.f2347q[0].h(i7);
        for (int i8 = 1; i8 < this.f2346p; i8++) {
            int h8 = this.f2347q[i8].h(i7);
            if (h8 < h7) {
                h7 = h8;
            }
        }
        return h7;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void T0(int r8, int r9, int r10) {
        /*
            r7 = this;
            boolean r0 = r7.f2354x
            if (r0 == 0) goto L9
            int r0 = r7.Q0()
            goto Ld
        L9:
            int r0 = r7.P0()
        Ld:
            r1 = 8
            if (r10 != r1) goto L1b
            if (r8 >= r9) goto L17
            int r2 = r9 + 1
        L15:
            r3 = r8
            goto L1e
        L17:
            int r2 = r8 + 1
            r3 = r9
            goto L1e
        L1b:
            int r2 = r8 + r9
            goto L15
        L1e:
            androidx.recyclerview.widget.q2 r4 = r7.B
            r4.g(r3)
            r5 = 1
            if (r10 == r5) goto L37
            r6 = 2
            if (r10 == r6) goto L33
            if (r10 == r1) goto L2c
            goto L3a
        L2c:
            r4.j(r8, r5)
            r4.i(r9, r5)
            goto L3a
        L33:
            r4.j(r8, r9)
            goto L3a
        L37:
            r4.i(r8, r9)
        L3a:
            if (r2 > r0) goto L3d
            return
        L3d:
            boolean r8 = r7.f2354x
            if (r8 == 0) goto L46
            int r8 = r7.P0()
            goto L4a
        L46:
            int r8 = r7.Q0()
        L4a:
            if (r3 > r8) goto L4f
            r7.q0()
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.T0(int, int, int):void");
    }

    @Override // androidx.recyclerview.widget.r1
    public final void U(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f2622b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(this.K);
        }
        for (int i7 = 0; i7 < this.f2346p; i7++) {
            this.f2347q[i7].b();
        }
        recyclerView.requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x002c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View U0() {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.U0():android.view.View");
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x004b, code lost:
    
        if (r8.f2350t == 1) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0050, code lost:
    
        if (r8.f2350t == 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x005d, code lost:
    
        if (V0() == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x006a, code lost:
    
        if (V0() == false) goto L46;
     */
    @Override // androidx.recyclerview.widget.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View V(android.view.View r9, int r10, androidx.recyclerview.widget.z1 r11, androidx.recyclerview.widget.g2 r12) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.V(android.view.View, int, androidx.recyclerview.widget.z1, androidx.recyclerview.widget.g2):android.view.View");
    }

    public final boolean V0() {
        return F() == 1;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void W(AccessibilityEvent accessibilityEvent) {
        super.W(accessibilityEvent);
        if (w() > 0) {
            View M0 = M0(false);
            View L0 = L0(false);
            if (M0 == null || L0 == null) {
                return;
            }
            int K = r1.K(M0);
            int K2 = r1.K(L0);
            if (K < K2) {
                accessibilityEvent.setFromIndex(K);
                accessibilityEvent.setToIndex(K2);
            } else {
                accessibilityEvent.setFromIndex(K2);
                accessibilityEvent.setToIndex(K);
            }
        }
    }

    public final void W0(View view, int i7, int i8) {
        Rect rect = this.G;
        d(view, rect);
        o2 o2Var = (o2) view.getLayoutParams();
        int i12 = i1(i7, ((ViewGroup.MarginLayoutParams) o2Var).leftMargin + rect.left, ((ViewGroup.MarginLayoutParams) o2Var).rightMargin + rect.right);
        int i13 = i1(i8, ((ViewGroup.MarginLayoutParams) o2Var).topMargin + rect.top, ((ViewGroup.MarginLayoutParams) o2Var).bottomMargin + rect.bottom);
        if (z0(view, i12, i13, o2Var)) {
            view.measure(i12, i13);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:267:0x0429, code lost:
    
        if (G0() != false) goto L262;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void X0(androidx.recyclerview.widget.z1 r17, androidx.recyclerview.widget.g2 r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 1120
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.X0(androidx.recyclerview.widget.z1, androidx.recyclerview.widget.g2, boolean):void");
    }

    public final boolean Y0(int i7) {
        if (this.f2350t == 0) {
            return (i7 == -1) != this.f2354x;
        }
        return ((i7 == -1) == this.f2354x) == V0();
    }

    @Override // androidx.recyclerview.widget.r1
    public final void Z(z1 z1Var, g2 g2Var, View view, q0.i iVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof o2)) {
            Y(view, iVar);
            return;
        }
        o2 o2Var = (o2) layoutParams;
        if (this.f2350t == 0) {
            s2 s2Var = o2Var.f2565e;
            iVar.g(androidx.fragment.app.o.b(s2Var != null ? s2Var.f2660e : -1, 1, -1, -1, false, false));
        } else {
            s2 s2Var2 = o2Var.f2565e;
            iVar.g(androidx.fragment.app.o.b(-1, -1, s2Var2 != null ? s2Var2.f2660e : -1, 1, false, false));
        }
    }

    public final void Z0(int i7, g2 g2Var) {
        int P0;
        int i8;
        if (i7 > 0) {
            P0 = Q0();
            i8 = 1;
        } else {
            P0 = P0();
            i8 = -1;
        }
        s0 s0Var = this.f2352v;
        s0Var.f2643a = true;
        g1(P0, g2Var);
        f1(i8);
        s0Var.f2645c = P0 + s0Var.f2646d;
        s0Var.f2644b = Math.abs(i7);
    }

    @Override // androidx.recyclerview.widget.e2
    public final PointF a(int i7) {
        int F0 = F0(i7);
        PointF pointF = new PointF();
        if (F0 == 0) {
            return null;
        }
        if (this.f2350t == 0) {
            pointF.x = F0;
            pointF.y = CropImageView.DEFAULT_ASPECT_RATIO;
        } else {
            pointF.x = CropImageView.DEFAULT_ASPECT_RATIO;
            pointF.y = F0;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void a0(int i7, int i8) {
        T0(i7, i8, 1);
    }

    public final void a1(z1 z1Var, s0 s0Var) {
        if (!s0Var.f2643a || s0Var.f2651i) {
            return;
        }
        if (s0Var.f2644b == 0) {
            if (s0Var.f2647e == -1) {
                b1(s0Var.f2649g, z1Var);
                return;
            } else {
                c1(s0Var.f2648f, z1Var);
                return;
            }
        }
        int i7 = 1;
        if (s0Var.f2647e == -1) {
            int i8 = s0Var.f2648f;
            int h7 = this.f2347q[0].h(i8);
            while (i7 < this.f2346p) {
                int h8 = this.f2347q[i7].h(i8);
                if (h8 > h7) {
                    h7 = h8;
                }
                i7++;
            }
            int i9 = i8 - h7;
            b1(i9 < 0 ? s0Var.f2649g : s0Var.f2649g - Math.min(i9, s0Var.f2644b), z1Var);
            return;
        }
        int i10 = s0Var.f2649g;
        int f7 = this.f2347q[0].f(i10);
        while (i7 < this.f2346p) {
            int f8 = this.f2347q[i7].f(i10);
            if (f8 < f7) {
                f7 = f8;
            }
            i7++;
        }
        int i11 = f7 - s0Var.f2649g;
        c1(i11 < 0 ? s0Var.f2648f : Math.min(i11, s0Var.f2644b) + s0Var.f2648f, z1Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final void b0() {
        this.B.d();
        q0();
    }

    public final void b1(int i7, z1 z1Var) {
        for (int w6 = w() - 1; w6 >= 0; w6--) {
            View v6 = v(w6);
            if (this.f2348r.d(v6) < i7 || this.f2348r.j(v6) < i7) {
                return;
            }
            o2 o2Var = (o2) v6.getLayoutParams();
            o2Var.getClass();
            if (o2Var.f2565e.f2656a.size() == 1) {
                return;
            }
            s2 s2Var = o2Var.f2565e;
            ArrayList arrayList = s2Var.f2656a;
            int size = arrayList.size();
            View view = (View) arrayList.remove(size - 1);
            o2 o2Var2 = (o2) view.getLayoutParams();
            o2Var2.f2565e = null;
            if (o2Var2.f2652a.isRemoved() || o2Var2.f2652a.isUpdated()) {
                s2Var.f2659d -= s2Var.f2661f.f2348r.c(view);
            }
            if (size == 1) {
                s2Var.f2657b = Integer.MIN_VALUE;
            }
            s2Var.f2658c = Integer.MIN_VALUE;
            n0(v6, z1Var);
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final void c(String str) {
        if (this.F == null) {
            super.c(str);
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final void c0(int i7, int i8) {
        T0(i7, i8, 8);
    }

    public final void c1(int i7, z1 z1Var) {
        while (w() > 0) {
            View v6 = v(0);
            if (this.f2348r.b(v6) > i7 || this.f2348r.i(v6) > i7) {
                return;
            }
            o2 o2Var = (o2) v6.getLayoutParams();
            o2Var.getClass();
            if (o2Var.f2565e.f2656a.size() == 1) {
                return;
            }
            s2 s2Var = o2Var.f2565e;
            ArrayList arrayList = s2Var.f2656a;
            View view = (View) arrayList.remove(0);
            o2 o2Var2 = (o2) view.getLayoutParams();
            o2Var2.f2565e = null;
            if (arrayList.size() == 0) {
                s2Var.f2658c = Integer.MIN_VALUE;
            }
            if (o2Var2.f2652a.isRemoved() || o2Var2.f2652a.isUpdated()) {
                s2Var.f2659d -= s2Var.f2661f.f2348r.c(view);
            }
            s2Var.f2657b = Integer.MIN_VALUE;
            n0(v6, z1Var);
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final void d0(int i7, int i8) {
        T0(i7, i8, 2);
    }

    public final void d1() {
        if (this.f2350t == 1 || !V0()) {
            this.f2354x = this.f2353w;
        } else {
            this.f2354x = !this.f2353w;
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean e() {
        return this.f2350t == 0;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void e0(int i7, int i8) {
        T0(i7, i8, 4);
    }

    public final int e1(int i7, z1 z1Var, g2 g2Var) {
        if (w() == 0 || i7 == 0) {
            return 0;
        }
        Z0(i7, g2Var);
        s0 s0Var = this.f2352v;
        int K0 = K0(z1Var, s0Var, g2Var);
        if (s0Var.f2644b >= K0) {
            i7 = i7 < 0 ? -K0 : K0;
        }
        this.f2348r.k(-i7);
        this.D = this.f2354x;
        s0Var.f2644b = 0;
        a1(z1Var, s0Var);
        return i7;
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean f() {
        return this.f2350t == 1;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void f0(z1 z1Var, g2 g2Var) {
        X0(z1Var, g2Var, true);
    }

    public final void f1(int i7) {
        s0 s0Var = this.f2352v;
        s0Var.f2647e = i7;
        s0Var.f2646d = this.f2354x != (i7 == -1) ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean g(s1 s1Var) {
        return s1Var instanceof o2;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void g0(g2 g2Var) {
        this.f2356z = -1;
        this.A = Integer.MIN_VALUE;
        this.F = null;
        this.H.a();
    }

    public final void g1(int i7, g2 g2Var) {
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        s0 s0Var = this.f2352v;
        boolean z6 = false;
        s0Var.f2644b = 0;
        s0Var.f2645c = i7;
        f2 f2Var = this.f2625e;
        if (!(f2Var != null && f2Var.isRunning()) || (i13 = g2Var.f2465a) == -1) {
            i8 = 0;
            i9 = 0;
        } else {
            if (this.f2354x == (i13 < i7)) {
                i8 = this.f2348r.g();
                i9 = 0;
            } else {
                i9 = this.f2348r.g();
                i8 = 0;
            }
        }
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView == null || !recyclerView.f2309g) {
            c1 c1Var = (c1) this.f2348r;
            int i14 = c1Var.f2393d;
            r1 r1Var = c1Var.f2425a;
            switch (i14) {
                case 0:
                    i10 = r1Var.f2634n;
                    break;
                default:
                    i10 = r1Var.f2635o;
                    break;
            }
            s0Var.f2649g = i10 + i8;
            s0Var.f2648f = -i9;
        } else {
            s0Var.f2648f = this.f2348r.f() - i9;
            s0Var.f2649g = this.f2348r.e() + i8;
        }
        s0Var.f2650h = false;
        s0Var.f2643a = true;
        d1 d1Var = this.f2348r;
        c1 c1Var2 = (c1) d1Var;
        int i15 = c1Var2.f2393d;
        r1 r1Var2 = c1Var2.f2425a;
        switch (i15) {
            case 0:
                i11 = r1Var2.f2632l;
                break;
            default:
                i11 = r1Var2.f2633m;
                break;
        }
        if (i11 == 0) {
            c1 c1Var3 = (c1) d1Var;
            int i16 = c1Var3.f2393d;
            r1 r1Var3 = c1Var3.f2425a;
            switch (i16) {
                case 0:
                    i12 = r1Var3.f2634n;
                    break;
                default:
                    i12 = r1Var3.f2635o;
                    break;
            }
            if (i12 == 0) {
                z6 = true;
            }
        }
        s0Var.f2651i = z6;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void h0(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.F = (SavedState) parcelable;
            q0();
        }
    }

    public final void h1(s2 s2Var, int i7, int i8) {
        int i9 = s2Var.f2659d;
        int i10 = s2Var.f2660e;
        if (i7 != -1) {
            int i11 = s2Var.f2658c;
            if (i11 == Integer.MIN_VALUE) {
                s2Var.a();
                i11 = s2Var.f2658c;
            }
            if (i11 - i9 >= i8) {
                this.f2355y.set(i10, false);
                return;
            }
            return;
        }
        int i12 = s2Var.f2657b;
        if (i12 == Integer.MIN_VALUE) {
            View view = (View) s2Var.f2656a.get(0);
            o2 o2Var = (o2) view.getLayoutParams();
            s2Var.f2657b = s2Var.f2661f.f2348r.d(view);
            o2Var.getClass();
            i12 = s2Var.f2657b;
        }
        if (i12 + i9 <= i8) {
            this.f2355y.set(i10, false);
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final void i(int i7, int i8, g2 g2Var, e0 e0Var) {
        s0 s0Var;
        int f7;
        int i9;
        if (this.f2350t != 0) {
            i7 = i8;
        }
        if (w() == 0 || i7 == 0) {
            return;
        }
        Z0(i7, g2Var);
        int[] iArr = this.J;
        if (iArr == null || iArr.length < this.f2346p) {
            this.J = new int[this.f2346p];
        }
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int i12 = this.f2346p;
            s0Var = this.f2352v;
            if (i10 >= i12) {
                break;
            }
            if (s0Var.f2646d == -1) {
                f7 = s0Var.f2648f;
                i9 = this.f2347q[i10].h(f7);
            } else {
                f7 = this.f2347q[i10].f(s0Var.f2649g);
                i9 = s0Var.f2649g;
            }
            int i13 = f7 - i9;
            if (i13 >= 0) {
                this.J[i11] = i13;
                i11++;
            }
            i10++;
        }
        Arrays.sort(this.J, 0, i11);
        for (int i14 = 0; i14 < i11; i14++) {
            int i15 = s0Var.f2645c;
            if (i15 < 0 || i15 >= g2Var.b()) {
                return;
            }
            e0Var.a(s0Var.f2645c, this.J[i14]);
            s0Var.f2645c += s0Var.f2646d;
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final Parcelable i0() {
        int h7;
        int f7;
        int[] iArr;
        SavedState savedState = this.F;
        if (savedState != null) {
            SavedState savedState2 = new SavedState();
            savedState2.f2363c = savedState.f2363c;
            savedState2.f2361a = savedState.f2361a;
            savedState2.f2362b = savedState.f2362b;
            savedState2.f2364d = savedState.f2364d;
            savedState2.f2365e = savedState.f2365e;
            savedState2.f2366f = savedState.f2366f;
            savedState2.f2368h = savedState.f2368h;
            savedState2.f2369i = savedState.f2369i;
            savedState2.f2370j = savedState.f2370j;
            savedState2.f2367g = savedState.f2367g;
            return savedState2;
        }
        SavedState savedState3 = new SavedState();
        savedState3.f2368h = this.f2353w;
        savedState3.f2369i = this.D;
        savedState3.f2370j = this.E;
        q2 q2Var = this.B;
        if (q2Var == null || (iArr = (int[]) q2Var.f2617b) == null) {
            savedState3.f2365e = 0;
        } else {
            savedState3.f2366f = iArr;
            savedState3.f2365e = iArr.length;
            savedState3.f2367g = (List) q2Var.f2618c;
        }
        if (w() > 0) {
            savedState3.f2361a = this.D ? Q0() : P0();
            View L0 = this.f2354x ? L0(true) : M0(true);
            savedState3.f2362b = L0 != null ? r1.K(L0) : -1;
            int i7 = this.f2346p;
            savedState3.f2363c = i7;
            savedState3.f2364d = new int[i7];
            for (int i8 = 0; i8 < this.f2346p; i8++) {
                if (this.D) {
                    h7 = this.f2347q[i8].f(Integer.MIN_VALUE);
                    if (h7 != Integer.MIN_VALUE) {
                        f7 = this.f2348r.e();
                        h7 -= f7;
                        savedState3.f2364d[i8] = h7;
                    } else {
                        savedState3.f2364d[i8] = h7;
                    }
                } else {
                    h7 = this.f2347q[i8].h(Integer.MIN_VALUE);
                    if (h7 != Integer.MIN_VALUE) {
                        f7 = this.f2348r.f();
                        h7 -= f7;
                        savedState3.f2364d[i8] = h7;
                    } else {
                        savedState3.f2364d[i8] = h7;
                    }
                }
            }
        } else {
            savedState3.f2361a = -1;
            savedState3.f2362b = -1;
            savedState3.f2363c = 0;
        }
        return savedState3;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void j0(int i7) {
        if (i7 == 0) {
            G0();
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final int k(g2 g2Var) {
        return H0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int l(g2 g2Var) {
        return I0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int m(g2 g2Var) {
        return J0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int n(g2 g2Var) {
        return H0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int o(g2 g2Var) {
        return I0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int p(g2 g2Var) {
        return J0(g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int r0(int i7, z1 z1Var, g2 g2Var) {
        return e1(i7, z1Var, g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final s1 s() {
        return this.f2350t == 0 ? new o2(-2, -1) : new o2(-1, -2);
    }

    @Override // androidx.recyclerview.widget.r1
    public final void s0(int i7) {
        SavedState savedState = this.F;
        if (savedState != null && savedState.f2361a != i7) {
            savedState.f2364d = null;
            savedState.f2363c = 0;
            savedState.f2361a = -1;
            savedState.f2362b = -1;
        }
        this.f2356z = i7;
        this.A = Integer.MIN_VALUE;
        q0();
    }

    @Override // androidx.recyclerview.widget.r1
    public final s1 t(Context context, AttributeSet attributeSet) {
        return new o2(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int t0(int i7, z1 z1Var, g2 g2Var) {
        return e1(i7, z1Var, g2Var);
    }

    @Override // androidx.recyclerview.widget.r1
    public final s1 u(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new o2((ViewGroup.MarginLayoutParams) layoutParams) : new o2(layoutParams);
    }

    @Override // androidx.recyclerview.widget.r1
    public final void w0(Rect rect, int i7, int i8) {
        int h7;
        int h8;
        int I = I() + H();
        int G = G() + J();
        if (this.f2350t == 1) {
            int height = rect.height() + G;
            RecyclerView recyclerView = this.f2622b;
            int i9 = p0.d1.OVER_SCROLL_ALWAYS;
            h8 = r1.h(i8, height, p0.l0.d(recyclerView));
            h7 = r1.h(i7, (this.f2351u * this.f2346p) + I, p0.l0.e(this.f2622b));
        } else {
            int width = rect.width() + I;
            RecyclerView recyclerView2 = this.f2622b;
            int i10 = p0.d1.OVER_SCROLL_ALWAYS;
            h7 = r1.h(i7, width, p0.l0.e(recyclerView2));
            h8 = r1.h(i8, (this.f2351u * this.f2346p) + G, p0.l0.d(this.f2622b));
        }
        this.f2622b.setMeasuredDimension(h7, h8);
    }

    @Override // androidx.recyclerview.widget.r1
    public final int y(z1 z1Var, g2 g2Var) {
        return this.f2350t == 1 ? this.f2346p : super.y(z1Var, g2Var);
    }
}
