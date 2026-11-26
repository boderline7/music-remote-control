package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.OverScroller;

@SuppressLint({"UnknownNullness"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ActionBarOverlayLayout extends ViewGroup implements i1, p0.y, p0.z {
    public static final int[] B = {f.a.actionBarSize, R.attr.windowContentOverlay};
    public final p0.a0 A;

    /* renamed from: a, reason: collision with root package name */
    public int f954a;

    /* renamed from: b, reason: collision with root package name */
    public int f955b;

    /* renamed from: c, reason: collision with root package name */
    public ContentFrameLayout f956c;

    /* renamed from: d, reason: collision with root package name */
    public ActionBarContainer f957d;

    /* renamed from: e, reason: collision with root package name */
    public j1 f958e;

    /* renamed from: f, reason: collision with root package name */
    public Drawable f959f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f960g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f961h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f962i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f963j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f964k;

    /* renamed from: l, reason: collision with root package name */
    public int f965l;

    /* renamed from: m, reason: collision with root package name */
    public int f966m;

    /* renamed from: n, reason: collision with root package name */
    public final Rect f967n;

    /* renamed from: o, reason: collision with root package name */
    public final Rect f968o;

    /* renamed from: p, reason: collision with root package name */
    public final Rect f969p;

    /* renamed from: q, reason: collision with root package name */
    public p0.l2 f970q;

    /* renamed from: r, reason: collision with root package name */
    public p0.l2 f971r;

    /* renamed from: s, reason: collision with root package name */
    public p0.l2 f972s;

    /* renamed from: t, reason: collision with root package name */
    public p0.l2 f973t;

    /* renamed from: u, reason: collision with root package name */
    public f f974u;

    /* renamed from: v, reason: collision with root package name */
    public OverScroller f975v;

    /* renamed from: w, reason: collision with root package name */
    public ViewPropertyAnimator f976w;

    /* renamed from: x, reason: collision with root package name */
    public final d f977x;

    /* renamed from: y, reason: collision with root package name */
    public final e f978y;

    /* renamed from: z, reason: collision with root package name */
    public final e f979z;

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f955b = 0;
        this.f967n = new Rect();
        this.f968o = new Rect();
        this.f969p = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        p0.l2 l2Var = p0.l2.CONSUMED;
        this.f970q = l2Var;
        this.f971r = l2Var;
        this.f972s = l2Var;
        this.f973t = l2Var;
        this.f977x = new d(0, this);
        this.f978y = new e(this, 0);
        this.f979z = new e(this, 1);
        g(context);
        this.A = new p0.a0();
    }

    public static boolean a(FrameLayout frameLayout, Rect rect, boolean z6) {
        boolean z7;
        g gVar = (g) frameLayout.getLayoutParams();
        int i7 = ((ViewGroup.MarginLayoutParams) gVar).leftMargin;
        int i8 = rect.left;
        if (i7 != i8) {
            ((ViewGroup.MarginLayoutParams) gVar).leftMargin = i8;
            z7 = true;
        } else {
            z7 = false;
        }
        int i9 = ((ViewGroup.MarginLayoutParams) gVar).topMargin;
        int i10 = rect.top;
        if (i9 != i10) {
            ((ViewGroup.MarginLayoutParams) gVar).topMargin = i10;
            z7 = true;
        }
        int i11 = ((ViewGroup.MarginLayoutParams) gVar).rightMargin;
        int i12 = rect.right;
        if (i11 != i12) {
            ((ViewGroup.MarginLayoutParams) gVar).rightMargin = i12;
            z7 = true;
        }
        if (z6) {
            int i13 = ((ViewGroup.MarginLayoutParams) gVar).bottomMargin;
            int i14 = rect.bottom;
            if (i13 != i14) {
                ((ViewGroup.MarginLayoutParams) gVar).bottomMargin = i14;
                return true;
            }
        }
        return z7;
    }

    @Override // p0.y
    public final void b(View view, View view2, int i7, int i8) {
        if (i8 == 0) {
            onNestedScrollAccepted(view, view2, i7);
        }
    }

    public final void c() {
        removeCallbacks(this.f978y);
        removeCallbacks(this.f979z);
        ViewPropertyAnimator viewPropertyAnimator = this.f976w;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof g;
    }

    @Override // p0.y
    public final void d(View view, int i7, int i8, int[] iArr, int i9) {
        if (i9 == 0) {
            onNestedPreScroll(view, i7, i8, iArr);
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int i7;
        super.draw(canvas);
        if (this.f959f == null || this.f960g) {
            return;
        }
        if (this.f957d.getVisibility() == 0) {
            i7 = (int) (this.f957d.getTranslationY() + this.f957d.getBottom() + 0.5f);
        } else {
            i7 = 0;
        }
        this.f959f.setBounds(0, i7, getWidth(), this.f959f.getIntrinsicHeight() + i7);
        this.f959f.draw(canvas);
    }

    @Override // p0.y
    public final void e(int i7, View view) {
        if (i7 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // p0.z
    public final void f(View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        i(view, i7, i8, i9, i10, i11);
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    public final void g(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(B);
        this.f954a = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        this.f959f = drawable;
        setWillNotDraw(drawable == null);
        obtainStyledAttributes.recycle();
        this.f960g = context.getApplicationInfo().targetSdkVersion < 19;
        this.f975v = new OverScroller(context);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new g(layoutParams);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f957d;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        p0.a0 a0Var = this.A;
        return a0Var.f8093b | a0Var.f8092a;
    }

    public CharSequence getTitle() {
        k();
        return ((w3) this.f958e).f1442a.getTitle();
    }

    public final void h(int i7) {
        k();
        if (i7 == 2) {
            this.f958e.getClass();
        } else if (i7 == 5) {
            this.f958e.getClass();
        } else {
            if (i7 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // p0.y
    public final void i(View view, int i7, int i8, int i9, int i10, int i11) {
        if (i11 == 0) {
            onNestedScroll(view, i7, i8, i9, i10);
        }
    }

    @Override // p0.y
    public final boolean j(View view, View view2, int i7, int i8) {
        return i8 == 0 && onStartNestedScroll(view, view2, i7);
    }

    public final void k() {
        j1 wrapper;
        if (this.f956c == null) {
            this.f956c = (ContentFrameLayout) findViewById(f.f.action_bar_activity_content);
            this.f957d = (ActionBarContainer) findViewById(f.f.action_bar_container);
            KeyEvent.Callback findViewById = findViewById(f.f.action_bar);
            if (findViewById instanceof j1) {
                wrapper = (j1) findViewById;
            } else {
                if (!(findViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(findViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) findViewById).getWrapper();
            }
            this.f958e = wrapper;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0053, code lost:
    
        if (r0 != false) goto L9;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.WindowInsets onApplyWindowInsets(android.view.WindowInsets r7) {
        /*
            r6 = this;
            r6.k()
            p0.l2 r7 = p0.l2.h(r6, r7)
            android.graphics.Rect r0 = new android.graphics.Rect
            int r1 = r7.b()
            int r2 = r7.d()
            int r3 = r7.c()
            int r4 = r7.a()
            r0.<init>(r1, r2, r3, r4)
            androidx.appcompat.widget.ActionBarContainer r1 = r6.f957d
            r2 = 0
            boolean r0 = a(r1, r0, r2)
            int r1 = p0.d1.OVER_SCROLL_ALWAYS
            android.graphics.Rect r1 = r6.f967n
            p0.r0.b(r6, r7, r1)
            int r2 = r1.left
            int r3 = r1.top
            int r4 = r1.right
            int r5 = r1.bottom
            p0.j2 r7 = r7.f8161a
            p0.l2 r2 = r7.l(r2, r3, r4, r5)
            r6.f970q = r2
            p0.l2 r3 = r6.f971r
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L47
            p0.l2 r0 = r6.f970q
            r6.f971r = r0
            r0 = 1
        L47:
            android.graphics.Rect r2 = r6.f968o
            boolean r3 = r2.equals(r1)
            if (r3 != 0) goto L53
            r2.set(r1)
            goto L55
        L53:
            if (r0 == 0) goto L58
        L55:
            r6.requestLayout()
        L58:
            p0.l2 r7 = r7.a()
            p0.j2 r7 = r7.f8161a
            p0.l2 r7 = r7.c()
            p0.j2 r7 = r7.f8161a
            p0.l2 r7 = r7.b()
            android.view.WindowInsets r7 = r7.g()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.onApplyWindowInsets(android.view.WindowInsets):android.view.WindowInsets");
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        g(getContext());
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        p0.p0.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                g gVar = (g) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i12 = ((ViewGroup.MarginLayoutParams) gVar).leftMargin + paddingLeft;
                int i13 = ((ViewGroup.MarginLayoutParams) gVar).topMargin + paddingTop;
                childAt.layout(i12, i13, measuredWidth + i12, measuredHeight + i13);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        int measuredHeight;
        k();
        measureChildWithMargins(this.f957d, i7, 0, i8, 0);
        g gVar = (g) this.f957d.getLayoutParams();
        int max = Math.max(0, this.f957d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) gVar).leftMargin + ((ViewGroup.MarginLayoutParams) gVar).rightMargin);
        int max2 = Math.max(0, this.f957d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) gVar).topMargin + ((ViewGroup.MarginLayoutParams) gVar).bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.f957d.getMeasuredState());
        int i9 = p0.d1.OVER_SCROLL_ALWAYS;
        boolean z6 = (p0.l0.g(this) & 256) != 0;
        if (z6) {
            measuredHeight = this.f954a;
            if (this.f962i && this.f957d.getTabContainer() != null) {
                measuredHeight += this.f954a;
            }
        } else {
            measuredHeight = this.f957d.getVisibility() != 8 ? this.f957d.getMeasuredHeight() : 0;
        }
        Rect rect = this.f967n;
        Rect rect2 = this.f969p;
        rect2.set(rect);
        p0.l2 l2Var = this.f970q;
        this.f972s = l2Var;
        if (this.f961h || z6) {
            i0.c b7 = i0.c.b(l2Var.b(), this.f972s.d() + measuredHeight, this.f972s.c(), this.f972s.a());
            p0.l2 l2Var2 = this.f972s;
            int i10 = Build.VERSION.SDK_INT;
            p0.d2 c2Var = i10 >= 30 ? new p0.c2(l2Var2) : i10 >= 29 ? new p0.b2(l2Var2) : new p0.a2(l2Var2);
            c2Var.g(b7);
            this.f972s = c2Var.b();
        } else {
            rect2.top += measuredHeight;
            rect2.bottom = rect2.bottom;
            this.f972s = l2Var.f8161a.l(0, measuredHeight, 0, 0);
        }
        a(this.f956c, rect2, true);
        if (!this.f973t.equals(this.f972s)) {
            p0.l2 l2Var3 = this.f972s;
            this.f973t = l2Var3;
            p0.d1.b(this.f956c, l2Var3);
        }
        measureChildWithMargins(this.f956c, i7, 0, i8, 0);
        g gVar2 = (g) this.f956c.getLayoutParams();
        int max3 = Math.max(max, this.f956c.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) gVar2).leftMargin + ((ViewGroup.MarginLayoutParams) gVar2).rightMargin);
        int max4 = Math.max(max2, this.f956c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) gVar2).topMargin + ((ViewGroup.MarginLayoutParams) gVar2).bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.f956c.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + max3, getSuggestedMinimumWidth()), i7, combineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + max4, getSuggestedMinimumHeight()), i8, combineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        if (!this.f963j || !z6) {
            return false;
        }
        this.f975v.fling(0, 0, 0, (int) f8, 0, 0, Integer.MIN_VALUE, u.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (this.f975v.getFinalY() > this.f957d.getHeight()) {
            c();
            this.f979z.run();
        } else {
            c();
            this.f978y.run();
        }
        this.f964k = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f7, float f8) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i7, int i8, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i7, int i8, int i9, int i10) {
        int i11 = this.f965l + i8;
        this.f965l = i11;
        setActionBarHideOffset(i11);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i7) {
        androidx.appcompat.app.a1 a1Var;
        j.n nVar;
        this.A.f8092a = i7;
        this.f965l = getActionBarHideOffset();
        c();
        f fVar = this.f974u;
        if (fVar == null || (nVar = (a1Var = (androidx.appcompat.app.a1) fVar).f693t) == null) {
            return;
        }
        nVar.a();
        a1Var.f693t = null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i7) {
        if ((i7 & 2) == 0 || this.f957d.getVisibility() != 0) {
            return false;
        }
        return this.f963j;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.f963j || this.f964k) {
            return;
        }
        if (this.f965l <= this.f957d.getHeight()) {
            c();
            postDelayed(this.f978y, 600L);
        } else {
            c();
            postDelayed(this.f979z, 600L);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i7) {
        super.onWindowSystemUiVisibilityChanged(i7);
        k();
        int i8 = this.f966m ^ i7;
        this.f966m = i7;
        boolean z6 = (i7 & 4) == 0;
        boolean z7 = (i7 & 256) != 0;
        f fVar = this.f974u;
        if (fVar != null) {
            ((androidx.appcompat.app.a1) fVar).f688o = !z7;
            if (z6 || !z7) {
                androidx.appcompat.app.a1 a1Var = (androidx.appcompat.app.a1) fVar;
                if (a1Var.f690q) {
                    a1Var.f690q = false;
                    a1Var.A(true);
                }
            } else {
                androidx.appcompat.app.a1 a1Var2 = (androidx.appcompat.app.a1) fVar;
                if (!a1Var2.f690q) {
                    a1Var2.f690q = true;
                    a1Var2.A(true);
                }
            }
        }
        if ((i8 & 256) == 0 || this.f974u == null) {
            return;
        }
        int i9 = p0.d1.OVER_SCROLL_ALWAYS;
        p0.p0.c(this);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i7) {
        super.onWindowVisibilityChanged(i7);
        this.f955b = i7;
        f fVar = this.f974u;
        if (fVar != null) {
            ((androidx.appcompat.app.a1) fVar).f687n = i7;
        }
    }

    public void setActionBarHideOffset(int i7) {
        c();
        this.f957d.setTranslationY(-Math.max(0, Math.min(i7, this.f957d.getHeight())));
    }

    public void setActionBarVisibilityCallback(f fVar) {
        this.f974u = fVar;
        if (getWindowToken() != null) {
            ((androidx.appcompat.app.a1) this.f974u).f687n = this.f955b;
            int i7 = this.f966m;
            if (i7 != 0) {
                onWindowSystemUiVisibilityChanged(i7);
                int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                p0.p0.c(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z6) {
        this.f962i = z6;
    }

    public void setHideOnContentScrollEnabled(boolean z6) {
        if (z6 != this.f963j) {
            this.f963j = z6;
            if (z6) {
                return;
            }
            c();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i7) {
        k();
        w3 w3Var = (w3) this.f958e;
        w3Var.f1446e = i7 != 0 ? l6.f.A(w3Var.f1442a.getContext(), i7) : null;
        w3Var.d();
    }

    public void setIcon(Drawable drawable) {
        k();
        w3 w3Var = (w3) this.f958e;
        w3Var.f1446e = drawable;
        w3Var.d();
    }

    public void setLogo(int i7) {
        k();
        w3 w3Var = (w3) this.f958e;
        w3Var.f1447f = i7 != 0 ? l6.f.A(w3Var.f1442a.getContext(), i7) : null;
        w3Var.d();
    }

    @Override // androidx.appcompat.widget.i1
    public void setMenu(Menu menu, k.b0 b0Var) {
        k();
        w3 w3Var = (w3) this.f958e;
        m mVar = w3Var.f1455n;
        Toolbar toolbar = w3Var.f1442a;
        if (mVar == null) {
            m mVar2 = new m(toolbar.getContext());
            w3Var.f1455n = mVar2;
            mVar2.f1331i = f.f.action_menu_presenter;
        }
        m mVar3 = w3Var.f1455n;
        mVar3.f1327e = b0Var;
        toolbar.setMenu((k.o) menu, mVar3);
    }

    @Override // androidx.appcompat.widget.i1
    public void setMenuPrepared() {
        k();
        ((w3) this.f958e).f1454m = true;
    }

    public void setOverlayMode(boolean z6) {
        this.f961h = z6;
        this.f960g = z6 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z6) {
    }

    public void setUiOptions(int i7) {
    }

    @Override // androidx.appcompat.widget.i1
    public void setWindowCallback(Window.Callback callback) {
        k();
        ((w3) this.f958e).f1453l = callback;
    }

    @Override // androidx.appcompat.widget.i1
    public void setWindowTitle(CharSequence charSequence) {
        k();
        w3 w3Var = (w3) this.f958e;
        if (w3Var.f1449h) {
            return;
        }
        w3Var.f1450i = charSequence;
        if ((w3Var.f1443b & 8) != 0) {
            Toolbar toolbar = w3Var.f1442a;
            toolbar.setTitle(charSequence);
            if (w3Var.f1449h) {
                p0.d1.p(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
