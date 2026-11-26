package androidx.recyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class r1 {

    /* renamed from: a, reason: collision with root package name */
    public j f2621a;

    /* renamed from: b, reason: collision with root package name */
    public RecyclerView f2622b;

    /* renamed from: c, reason: collision with root package name */
    public final q2 f2623c;

    /* renamed from: d, reason: collision with root package name */
    public final q2 f2624d;

    /* renamed from: e, reason: collision with root package name */
    public f2 f2625e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2626f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f2627g;

    /* renamed from: h, reason: collision with root package name */
    public final boolean f2628h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f2629i;

    /* renamed from: j, reason: collision with root package name */
    public int f2630j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f2631k;

    /* renamed from: l, reason: collision with root package name */
    public int f2632l;

    /* renamed from: m, reason: collision with root package name */
    public int f2633m;

    /* renamed from: n, reason: collision with root package name */
    public int f2634n;

    /* renamed from: o, reason: collision with root package name */
    public int f2635o;

    public r1() {
        p1 p1Var = new p1(this, 0);
        p1 p1Var2 = new p1(this, 1);
        this.f2623c = new q2(p1Var);
        this.f2624d = new q2(p1Var2);
        this.f2626f = false;
        this.f2627g = false;
        this.f2628h = true;
        this.f2629i = true;
    }

    public static int B(View view) {
        return view.getLeft() - ((s1) view.getLayoutParams()).f2653b.left;
    }

    public static int C(View view) {
        return view.getRight() + ((s1) view.getLayoutParams()).f2653b.right;
    }

    public static int D(View view) {
        return view.getTop() - ((s1) view.getLayoutParams()).f2653b.top;
    }

    public static int K(View view) {
        return ((s1) view.getLayoutParams()).f2652a.getLayoutPosition();
    }

    public static q1 L(Context context, AttributeSet attributeSet, int i7, int i8) {
        q1 q1Var = new q1();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i1.d.RecyclerView, i7, i8);
        q1Var.f2612a = obtainStyledAttributes.getInt(i1.d.RecyclerView_android_orientation, 1);
        q1Var.f2613b = obtainStyledAttributes.getInt(i1.d.RecyclerView_spanCount, 1);
        q1Var.f2614c = obtainStyledAttributes.getBoolean(i1.d.RecyclerView_reverseLayout, false);
        q1Var.f2615d = obtainStyledAttributes.getBoolean(i1.d.RecyclerView_stackFromEnd, false);
        obtainStyledAttributes.recycle();
        return q1Var;
    }

    public static boolean P(int i7, int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        if (i9 > 0 && i7 != i9) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i7;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i7;
        }
        return true;
    }

    public static void Q(View view, int i7, int i8, int i9, int i10) {
        s1 s1Var = (s1) view.getLayoutParams();
        Rect rect = s1Var.f2653b;
        view.layout(i7 + rect.left + ((ViewGroup.MarginLayoutParams) s1Var).leftMargin, i8 + rect.top + ((ViewGroup.MarginLayoutParams) s1Var).topMargin, (i9 - rect.right) - ((ViewGroup.MarginLayoutParams) s1Var).rightMargin, (i10 - rect.bottom) - ((ViewGroup.MarginLayoutParams) s1Var).bottomMargin);
    }

    public static int h(int i7, int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i8, i9) : size : Math.min(size, Math.max(i8, i9));
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
    
        if (r5 == 1073741824) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int x(int r4, int r5, int r6, int r7, boolean r8) {
        /*
            int r4 = r4 - r6
            r6 = 0
            int r4 = java.lang.Math.max(r6, r4)
            r0 = -2
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L1d
            if (r7 < 0) goto L12
        L10:
            r5 = r3
            goto L30
        L12:
            if (r7 != r1) goto L1a
            if (r5 == r2) goto L22
            if (r5 == 0) goto L1a
            if (r5 == r3) goto L22
        L1a:
            r5 = r6
            r7 = r5
            goto L30
        L1d:
            if (r7 < 0) goto L20
            goto L10
        L20:
            if (r7 != r1) goto L24
        L22:
            r7 = r4
            goto L30
        L24:
            if (r7 != r0) goto L1a
            if (r5 == r2) goto L2e
            if (r5 != r3) goto L2b
            goto L2e
        L2b:
            r7 = r4
            r5 = r6
            goto L30
        L2e:
            r7 = r4
            r5 = r2
        L30:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.r1.x(int, int, int, int, boolean):int");
    }

    public static int z(View view) {
        return view.getBottom() + ((s1) view.getLayoutParams()).f2653b.bottom;
    }

    public void A(View view, Rect rect) {
        RecyclerView.L(view, rect);
    }

    public boolean A0() {
        return false;
    }

    public final boolean B0(View view, int i7, int i8, s1 s1Var) {
        return (this.f2628h && P(view.getMeasuredWidth(), i7, ((ViewGroup.MarginLayoutParams) s1Var).width) && P(view.getMeasuredHeight(), i8, ((ViewGroup.MarginLayoutParams) s1Var).height)) ? false : true;
    }

    public abstract void C0(RecyclerView recyclerView, int i7);

    public final void D0(f2 f2Var) {
        f2 f2Var2 = this.f2625e;
        if (f2Var2 != null && f2Var != f2Var2 && f2Var2.isRunning()) {
            this.f2625e.stop();
        }
        this.f2625e = f2Var;
        f2Var.start(this.f2622b, this);
    }

    public final int E() {
        RecyclerView recyclerView = this.f2622b;
        h1 adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        if (adapter != null) {
            return adapter.getItemCount();
        }
        return 0;
    }

    public boolean E0() {
        return false;
    }

    public final int F() {
        RecyclerView recyclerView = this.f2622b;
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        return p0.m0.d(recyclerView);
    }

    public final int G() {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }

    public final int H() {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public final int I() {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public final int J() {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public int M(z1 z1Var, g2 g2Var) {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView == null || recyclerView.f2319l == null || !f()) {
            return 1;
        }
        return this.f2622b.f2319l.getItemCount();
    }

    public final void N(View view, Rect rect) {
        Matrix matrix;
        Rect rect2 = ((s1) view.getLayoutParams()).f2653b;
        rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        if (this.f2622b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
            RectF rectF = this.f2622b.f2317k;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public boolean O() {
        return false;
    }

    public void R(int i7) {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView != null) {
            int e7 = recyclerView.f2305e.e();
            for (int i8 = 0; i8 < e7; i8++) {
                recyclerView.f2305e.d(i8).offsetLeftAndRight(i7);
            }
        }
    }

    public void S(int i7) {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView != null) {
            int e7 = recyclerView.f2305e.e();
            for (int i8 = 0; i8 < e7; i8++) {
                recyclerView.f2305e.d(i8).offsetTopAndBottom(i7);
            }
        }
    }

    public void T(RecyclerView recyclerView) {
    }

    public abstract void U(RecyclerView recyclerView);

    public abstract View V(View view, int i7, z1 z1Var, g2 g2Var);

    public void W(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.f2622b;
        z1 z1Var = recyclerView.f2299b;
        g2 g2Var = recyclerView.f2308f0;
        if (recyclerView == null || accessibilityEvent == null) {
            return;
        }
        boolean z6 = true;
        if (!recyclerView.canScrollVertically(1) && !this.f2622b.canScrollVertically(-1) && !this.f2622b.canScrollHorizontally(-1) && !this.f2622b.canScrollHorizontally(1)) {
            z6 = false;
        }
        accessibilityEvent.setScrollable(z6);
        h1 h1Var = this.f2622b.f2319l;
        if (h1Var != null) {
            accessibilityEvent.setItemCount(h1Var.getItemCount());
        }
    }

    public void X(z1 z1Var, g2 g2Var, q0.i iVar) {
        if (this.f2622b.canScrollVertically(-1) || this.f2622b.canScrollHorizontally(-1)) {
            iVar.a(8192);
            iVar.h(true);
        }
        if (this.f2622b.canScrollVertically(1) || this.f2622b.canScrollHorizontally(1)) {
            iVar.a(4096);
            iVar.h(true);
        }
        iVar.f8426a.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) q0.h.a(M(z1Var, g2Var), y(z1Var, g2Var), 0).f8425a);
    }

    public final void Y(View view, q0.i iVar) {
        j2 K = RecyclerView.K(view);
        if (K == null || K.isRemoved() || this.f2621a.k(K.itemView)) {
            return;
        }
        RecyclerView recyclerView = this.f2622b;
        Z(recyclerView.f2299b, recyclerView.f2308f0, view, iVar);
    }

    public void Z(z1 z1Var, g2 g2Var, View view, q0.i iVar) {
        iVar.g(androidx.fragment.app.o.b(f() ? K(view) : 0, 1, e() ? K(view) : 0, 1, false, false));
    }

    public void a0(int i7, int i8) {
    }

    public final void b(View view, int i7, boolean z6) {
        j2 K = RecyclerView.K(view);
        if (z6 || K.isRemoved()) {
            this.f2622b.f2307f.a(K);
        } else {
            this.f2622b.f2307f.l(K);
        }
        s1 s1Var = (s1) view.getLayoutParams();
        if (K.wasReturnedFromScrap() || K.isScrap()) {
            if (K.isScrap()) {
                K.unScrap();
            } else {
                K.clearReturnedFromScrapFlag();
            }
            this.f2621a.b(view, i7, view.getLayoutParams(), false);
        } else if (view.getParent() == this.f2622b) {
            int j7 = this.f2621a.j(view);
            if (i7 == -1) {
                i7 = this.f2621a.e();
            }
            if (j7 == -1) {
                throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f2622b.indexOfChild(view) + this.f2622b.z());
            }
            if (j7 != i7) {
                r1 r1Var = this.f2622b.f2321m;
                View v6 = r1Var.v(j7);
                if (v6 == null) {
                    throw new IllegalArgumentException("Cannot move a child from non-existing index:" + j7 + r1Var.f2622b.toString());
                }
                r1Var.v(j7);
                r1Var.f2621a.c(j7);
                s1 s1Var2 = (s1) v6.getLayoutParams();
                j2 K2 = RecyclerView.K(v6);
                if (K2.isRemoved()) {
                    r1Var.f2622b.f2307f.a(K2);
                } else {
                    r1Var.f2622b.f2307f.l(K2);
                }
                r1Var.f2621a.b(v6, i7, s1Var2, K2.isRemoved());
            }
        } else {
            this.f2621a.a(view, i7, false);
            s1Var.f2654c = true;
            f2 f2Var = this.f2625e;
            if (f2Var != null && f2Var.isRunning()) {
                this.f2625e.onChildAttachedToWindow(view);
            }
        }
        if (s1Var.f2655d) {
            K.itemView.invalidate();
            s1Var.f2655d = false;
        }
    }

    public void b0() {
    }

    public void c(String str) {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView != null) {
            recyclerView.i(str);
        }
    }

    public void c0(int i7, int i8) {
    }

    public final void d(View view, Rect rect) {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.M(view));
        }
    }

    public void d0(int i7, int i8) {
    }

    public abstract boolean e();

    public void e0(int i7, int i8) {
    }

    public abstract boolean f();

    public abstract void f0(z1 z1Var, g2 g2Var);

    public boolean g(s1 s1Var) {
        return s1Var != null;
    }

    public abstract void g0(g2 g2Var);

    public void h0(Parcelable parcelable) {
    }

    public void i(int i7, int i8, g2 g2Var, e0 e0Var) {
    }

    public Parcelable i0() {
        return null;
    }

    public void j(int i7, e0 e0Var) {
    }

    public void j0(int i7) {
    }

    public abstract int k(g2 g2Var);

    public boolean k0(z1 z1Var, g2 g2Var, int i7, Bundle bundle) {
        int J;
        int H;
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView == null) {
            return false;
        }
        if (i7 == 4096) {
            J = recyclerView.canScrollVertically(1) ? (this.f2635o - J()) - G() : 0;
            if (this.f2622b.canScrollHorizontally(1)) {
                H = (this.f2634n - H()) - I();
            }
            H = 0;
        } else if (i7 != 8192) {
            J = 0;
            H = 0;
        } else {
            J = recyclerView.canScrollVertically(-1) ? -((this.f2635o - J()) - G()) : 0;
            if (this.f2622b.canScrollHorizontally(-1)) {
                H = -((this.f2634n - H()) - I());
            }
            H = 0;
        }
        if (J == 0 && H == 0) {
            return false;
        }
        this.f2622b.f0(H, J, true);
        return true;
    }

    public abstract int l(g2 g2Var);

    public final void l0(z1 z1Var) {
        for (int w6 = w() - 1; w6 >= 0; w6--) {
            if (!RecyclerView.K(v(w6)).shouldIgnore()) {
                View v6 = v(w6);
                o0(w6);
                z1Var.f(v6);
            }
        }
    }

    public abstract int m(g2 g2Var);

    public final void m0(z1 z1Var) {
        ArrayList arrayList;
        int size = z1Var.f2718a.size();
        int i7 = size - 1;
        while (true) {
            arrayList = z1Var.f2718a;
            if (i7 < 0) {
                break;
            }
            View view = ((j2) arrayList.get(i7)).itemView;
            j2 K = RecyclerView.K(view);
            if (!K.shouldIgnore()) {
                K.setIsRecyclable(false);
                if (K.isTmpDetached()) {
                    this.f2622b.removeDetachedView(view, false);
                }
                m1 m1Var = this.f2622b.K;
                if (m1Var != null) {
                    m1Var.d(K);
                }
                K.setIsRecyclable(true);
                j2 K2 = RecyclerView.K(view);
                K2.mScrapContainer = null;
                K2.mInChangeScrap = false;
                K2.clearReturnedFromScrapFlag();
                z1Var.g(K2);
            }
            i7--;
        }
        arrayList.clear();
        ArrayList arrayList2 = z1Var.f2719b;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        if (size > 0) {
            this.f2622b.invalidate();
        }
    }

    public abstract int n(g2 g2Var);

    public final void n0(View view, z1 z1Var) {
        j jVar = this.f2621a;
        n1 n1Var = (n1) jVar.f2498b;
        int indexOfChild = n1Var.f2551a.indexOfChild(view);
        if (indexOfChild >= 0) {
            if (((i) jVar.f2499c).g(indexOfChild)) {
                jVar.l(view);
            }
            n1Var.h(indexOfChild);
        }
        z1Var.f(view);
    }

    public abstract int o(g2 g2Var);

    public final void o0(int i7) {
        if (v(i7) != null) {
            j jVar = this.f2621a;
            int f7 = jVar.f(i7);
            n1 n1Var = (n1) jVar.f2498b;
            View childAt = n1Var.f2551a.getChildAt(f7);
            if (childAt == null) {
                return;
            }
            if (((i) jVar.f2499c).g(f7)) {
                jVar.l(childAt);
            }
            n1Var.h(f7);
        }
    }

    public abstract int p(g2 g2Var);

    /* JADX WARN: Code restructure failed: missing block: B:17:0x00a3, code lost:
    
        if ((r5.bottom - r1) > r13) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean p0(androidx.recyclerview.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            int r0 = r8.H()
            int r1 = r8.J()
            int r2 = r8.f2634n
            int r3 = r8.I()
            int r2 = r2 - r3
            int r3 = r8.f2635o
            int r4 = r8.G()
            int r3 = r3 - r4
            int r4 = r10.getLeft()
            int r5 = r11.left
            int r4 = r4 + r5
            int r5 = r10.getScrollX()
            int r4 = r4 - r5
            int r5 = r10.getTop()
            int r6 = r11.top
            int r5 = r5 + r6
            int r10 = r10.getScrollY()
            int r5 = r5 - r10
            int r10 = r11.width()
            int r10 = r10 + r4
            int r11 = r11.height()
            int r11 = r11 + r5
            int r4 = r4 - r0
            r0 = 0
            int r6 = java.lang.Math.min(r0, r4)
            int r5 = r5 - r1
            int r1 = java.lang.Math.min(r0, r5)
            int r10 = r10 - r2
            int r2 = java.lang.Math.max(r0, r10)
            int r11 = r11 - r3
            int r11 = java.lang.Math.max(r0, r11)
            int r3 = r8.F()
            r7 = 1
            if (r3 != r7) goto L5c
            if (r2 == 0) goto L57
            goto L64
        L57:
            int r2 = java.lang.Math.max(r6, r10)
            goto L64
        L5c:
            if (r6 == 0) goto L5f
            goto L63
        L5f:
            int r6 = java.lang.Math.min(r4, r2)
        L63:
            r2 = r6
        L64:
            if (r1 == 0) goto L67
            goto L6b
        L67:
            int r1 = java.lang.Math.min(r5, r11)
        L6b:
            if (r13 == 0) goto La6
            android.view.View r10 = r9.getFocusedChild()
            if (r10 != 0) goto L74
            goto Lab
        L74:
            int r11 = r8.H()
            int r13 = r8.J()
            int r3 = r8.f2634n
            int r4 = r8.I()
            int r3 = r3 - r4
            int r4 = r8.f2635o
            int r5 = r8.G()
            int r4 = r4 - r5
            androidx.recyclerview.widget.RecyclerView r5 = r8.f2622b
            android.graphics.Rect r5 = r5.f2313i
            r8.A(r10, r5)
            int r10 = r5.left
            int r10 = r10 - r2
            if (r10 >= r3) goto Lab
            int r10 = r5.right
            int r10 = r10 - r2
            if (r10 <= r11) goto Lab
            int r10 = r5.top
            int r10 = r10 - r1
            if (r10 >= r4) goto Lab
            int r10 = r5.bottom
            int r10 = r10 - r1
            if (r10 > r13) goto La6
            goto Lab
        La6:
            if (r2 != 0) goto Lac
            if (r1 == 0) goto Lab
            goto Lac
        Lab:
            return r0
        Lac:
            if (r12 == 0) goto Lb2
            r9.scrollBy(r2, r1)
            goto Lb5
        Lb2:
            r9.f0(r2, r1, r0)
        Lb5:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.r1.p0(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    public final void q(z1 z1Var) {
        for (int w6 = w() - 1; w6 >= 0; w6--) {
            View v6 = v(w6);
            j2 K = RecyclerView.K(v6);
            if (!K.shouldIgnore()) {
                if (!K.isInvalid() || K.isRemoved() || this.f2622b.f2319l.hasStableIds()) {
                    v(w6);
                    this.f2621a.c(w6);
                    z1Var.h(v6);
                    this.f2622b.f2307f.l(K);
                } else {
                    o0(w6);
                    z1Var.g(K);
                }
            }
        }
    }

    public final void q0() {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public View r(int i7) {
        int w6 = w();
        for (int i8 = 0; i8 < w6; i8++) {
            View v6 = v(i8);
            j2 K = RecyclerView.K(v6);
            if (K != null && K.getLayoutPosition() == i7 && !K.shouldIgnore() && (this.f2622b.f2308f0.f2471g || !K.isRemoved())) {
                return v6;
            }
        }
        return null;
    }

    public abstract int r0(int i7, z1 z1Var, g2 g2Var);

    public abstract s1 s();

    public abstract void s0(int i7);

    public s1 t(Context context, AttributeSet attributeSet) {
        return new s1(context, attributeSet);
    }

    public abstract int t0(int i7, z1 z1Var, g2 g2Var);

    public s1 u(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof s1 ? new s1((s1) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new s1((ViewGroup.MarginLayoutParams) layoutParams) : new s1(layoutParams);
    }

    public final void u0(RecyclerView recyclerView) {
        v0(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), v.c.EXACTLY), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), v.c.EXACTLY));
    }

    public final View v(int i7) {
        j jVar = this.f2621a;
        if (jVar != null) {
            return jVar.d(i7);
        }
        return null;
    }

    public final void v0(int i7, int i8) {
        this.f2634n = View.MeasureSpec.getSize(i7);
        int mode = View.MeasureSpec.getMode(i7);
        this.f2632l = mode;
        if (mode == 0) {
            int[] iArr = RecyclerView.f2294w0;
        }
        this.f2635o = View.MeasureSpec.getSize(i8);
        int mode2 = View.MeasureSpec.getMode(i8);
        this.f2633m = mode2;
        if (mode2 == 0) {
            int[] iArr2 = RecyclerView.f2294w0;
        }
    }

    public final int w() {
        j jVar = this.f2621a;
        if (jVar != null) {
            return jVar.e();
        }
        return 0;
    }

    public void w0(Rect rect, int i7, int i8) {
        int I = I() + H() + rect.width();
        int G = G() + J() + rect.height();
        RecyclerView recyclerView = this.f2622b;
        int i9 = p0.d1.OVER_SCROLL_ALWAYS;
        this.f2622b.setMeasuredDimension(h(i7, I, p0.l0.e(recyclerView)), h(i8, G, p0.l0.d(this.f2622b)));
    }

    public final void x0(int i7, int i8) {
        int w6 = w();
        if (w6 == 0) {
            this.f2622b.n(i7, i8);
            return;
        }
        int i9 = Integer.MIN_VALUE;
        int i10 = Integer.MAX_VALUE;
        int i11 = Integer.MIN_VALUE;
        int i12 = Integer.MAX_VALUE;
        for (int i13 = 0; i13 < w6; i13++) {
            View v6 = v(i13);
            Rect rect = this.f2622b.f2313i;
            A(v6, rect);
            int i14 = rect.left;
            if (i14 < i12) {
                i12 = i14;
            }
            int i15 = rect.right;
            if (i15 > i9) {
                i9 = i15;
            }
            int i16 = rect.top;
            if (i16 < i10) {
                i10 = i16;
            }
            int i17 = rect.bottom;
            if (i17 > i11) {
                i11 = i17;
            }
        }
        this.f2622b.f2313i.set(i12, i10, i9, i11);
        w0(this.f2622b.f2313i, i7, i8);
    }

    public int y(z1 z1Var, g2 g2Var) {
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView == null || recyclerView.f2319l == null || !e()) {
            return 1;
        }
        return this.f2622b.f2319l.getItemCount();
    }

    public final void y0(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.f2622b = null;
            this.f2621a = null;
            this.f2634n = 0;
            this.f2635o = 0;
        } else {
            this.f2622b = recyclerView;
            this.f2621a = recyclerView.f2305e;
            this.f2634n = recyclerView.getWidth();
            this.f2635o = recyclerView.getHeight();
        }
        this.f2632l = v.c.EXACTLY;
        this.f2633m = v.c.EXACTLY;
    }

    public final boolean z0(View view, int i7, int i8, s1 s1Var) {
        return (!view.isLayoutRequested() && this.f2628h && P(view.getWidth(), i7, ((ViewGroup.MarginLayoutParams) s1Var).width) && P(view.getHeight(), i8, ((ViewGroup.MarginLayoutParams) s1Var).height)) ? false : true;
    }
}
