package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.appcompat.app.s0;
import androidx.customview.view.AbsSavedState;
import c0.g;
import com.yalantis.ucrop.view.CropImageView;
import i6.i;
import java.util.ArrayList;
import java.util.Collections;
import p0.d1;
import p0.l0;
import p0.r0;
import q1.e;
import q1.f;
import q1.h;
import q1.j;
import q1.k;
import u0.d;
import v.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ViewPager extends ViewGroup {
    public static final int[] L = {R.attr.layout_gravity};
    public static final d M = new d(1);
    public static final g N = new g(2);
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    public boolean A;
    public boolean B;
    public int C;
    public ArrayList D;
    public j E;
    public j F;
    public ArrayList G;
    public int H;
    public ArrayList I;
    public final s0 J;
    public int K;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f2824a;

    /* renamed from: b, reason: collision with root package name */
    public final f f2825b;

    /* renamed from: c, reason: collision with root package name */
    public final Rect f2826c;

    /* renamed from: d, reason: collision with root package name */
    public Parcelable f2827d;

    /* renamed from: e, reason: collision with root package name */
    public Scroller f2828e;

    /* renamed from: f, reason: collision with root package name */
    public int f2829f;

    /* renamed from: g, reason: collision with root package name */
    public Drawable f2830g;

    /* renamed from: h, reason: collision with root package name */
    public final float f2831h;

    /* renamed from: i, reason: collision with root package name */
    public final float f2832i;

    /* renamed from: j, reason: collision with root package name */
    public int f2833j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f2834k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f2835l;

    /* renamed from: m, reason: collision with root package name */
    public int f2836m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f2837n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f2838o;

    /* renamed from: p, reason: collision with root package name */
    public int f2839p;

    /* renamed from: q, reason: collision with root package name */
    public int f2840q;

    /* renamed from: r, reason: collision with root package name */
    public int f2841r;

    /* renamed from: s, reason: collision with root package name */
    public float f2842s;

    /* renamed from: t, reason: collision with root package name */
    public float f2843t;

    /* renamed from: u, reason: collision with root package name */
    public float f2844u;

    /* renamed from: v, reason: collision with root package name */
    public int f2845v;

    /* renamed from: w, reason: collision with root package name */
    public VelocityTracker f2846w;

    /* renamed from: x, reason: collision with root package name */
    public int f2847x;

    /* renamed from: y, reason: collision with root package name */
    public EdgeEffect f2848y;

    /* renamed from: z, reason: collision with root package name */
    public EdgeEffect f2849z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        public int f2850b;

        /* renamed from: c, reason: collision with root package name */
        public final Parcelable f2851c;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f2850b = parcel.readInt();
            this.f2851c = parcel.readParcelable(classLoader);
        }

        public final String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f2850b + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeInt(this.f2850b);
            parcel.writeParcelable(this.f2851c, i7);
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.f2824a = new ArrayList();
        this.f2825b = new f();
        this.f2826c = new Rect();
        this.f2831h = -3.4028235E38f;
        this.f2832i = Float.MAX_VALUE;
        this.f2836m = 1;
        this.f2845v = -1;
        this.A = true;
        this.J = new s0(12, this);
        this.K = 0;
        g();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2824a = new ArrayList();
        this.f2825b = new f();
        this.f2826c = new Rect();
        this.f2831h = -3.4028235E38f;
        this.f2832i = Float.MAX_VALUE;
        this.f2836m = 1;
        this.f2845v = -1;
        this.A = true;
        this.J = new s0(12, this);
        this.K = 0;
        g();
    }

    public static boolean b(int i7, int i8, int i9, View view, boolean z6) {
        int i10;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i11 = i8 + scrollX;
                if (i11 >= childAt.getLeft() && i11 < childAt.getRight() && (i10 = i9 + scrollY) >= childAt.getTop() && i10 < childAt.getBottom() && b(i7, i11 - childAt.getLeft(), i10 - childAt.getTop(), childAt, true)) {
                    return true;
                }
            }
        }
        return z6 && view.canScrollHorizontally(-i7);
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void setScrollingCacheEnabled(boolean z6) {
        if (this.f2835l != z6) {
            this.f2835l = z6;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(int r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.findFocus()
            r1 = 0
            if (r0 != r5) goto L9
        L7:
            r0 = r1
            goto L2b
        L9:
            if (r0 == 0) goto L2b
            android.view.ViewParent r2 = r0.getParent()
        Lf:
            boolean r3 = r2 instanceof android.view.ViewGroup
            if (r3 == 0) goto L1b
            if (r2 != r5) goto L16
            goto L2b
        L16:
            android.view.ViewParent r2 = r2.getParent()
            goto Lf
        L1b:
            android.view.ViewParent r0 = r0.getParent()
        L1f:
            boolean r2 = r0 instanceof android.view.ViewGroup
            if (r2 == 0) goto L7
            r0.getClass()
            android.view.ViewParent r0 = r0.getParent()
            goto L1f
        L2b:
            android.view.FocusFinder r1 = android.view.FocusFinder.getInstance()
            android.view.View r1 = r1.findNextFocus(r5, r0, r6)
            r2 = 1
            r3 = 0
            r4 = 17
            if (r1 == 0) goto L6e
            if (r1 == r0) goto L6e
            android.graphics.Rect r2 = r5.f2826c
            if (r6 != r4) goto L55
            android.graphics.Rect r4 = r5.d(r1, r2)
            int r4 = r4.left
            android.graphics.Rect r2 = r5.d(r0, r2)
            int r2 = r2.left
            if (r0 == 0) goto L50
            if (r4 < r2) goto L50
            goto L6e
        L50:
            boolean r3 = r1.requestFocus()
            goto L6e
        L55:
            r4 = 66
            if (r6 != r4) goto L6e
            android.graphics.Rect r4 = r5.d(r1, r2)
            int r4 = r4.left
            android.graphics.Rect r2 = r5.d(r0, r2)
            int r2 = r2.left
            if (r0 == 0) goto L6a
            if (r4 > r2) goto L6a
            goto L6e
        L6a:
            boolean r3 = r1.requestFocus()
        L6e:
            if (r3 == 0) goto L77
            int r6 = android.view.SoundEffectConstants.getContantForFocusDirection(r6)
            r5.playSoundEffect(r6)
        L77:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.a(int):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i7, int i8) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i9 = 0; i9 < getChildCount(); i9++) {
                if (getChildAt(i9).getVisibility() == 0) {
                    e();
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i8 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addTouchables(ArrayList arrayList) {
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            if (getChildAt(i7).getVisibility() == 0) {
                e();
            }
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        q1.g gVar = (q1.g) layoutParams;
        boolean z6 = gVar.f8437a | (view.getClass().getAnnotation(e.class) != null);
        gVar.f8437a = z6;
        if (!this.f2834k) {
            super.addView(view, i7, layoutParams);
        } else {
            if (z6) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            addViewInLayout(view, i7, layoutParams);
        }
    }

    public final void c(boolean z6) {
        int i7 = 0;
        boolean z7 = this.K == 2;
        if (z7) {
            setScrollingCacheEnabled(false);
            if (!this.f2828e.isFinished()) {
                this.f2828e.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.f2828e.getCurrX();
                int currY = this.f2828e.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        i(currX);
                    }
                }
            }
        }
        while (true) {
            ArrayList arrayList = this.f2824a;
            if (i7 >= arrayList.size()) {
                break;
            }
            ((f) arrayList.get(i7)).getClass();
            i7++;
        }
        if (z7) {
            s0 s0Var = this.J;
            if (!z6) {
                s0Var.run();
            } else {
                int i8 = d1.OVER_SCROLL_ALWAYS;
                l0.m(this, s0Var);
            }
        }
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i7) {
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof q1.g) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public final void computeScroll() {
        if (this.f2828e.isFinished() || !this.f2828e.computeScrollOffset()) {
            c(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f2828e.getCurrX();
        int currY = this.f2828e.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!i(currX)) {
                this.f2828e.abortAnimation();
                scrollTo(0, currY);
            }
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.k(this);
    }

    public final Rect d(View view, Rect rect) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left = viewGroup.getLeft() + rect.left;
            rect.right = viewGroup.getRight() + rect.right;
            rect.top = viewGroup.getTop() + rect.top;
            rect.bottom = viewGroup.getBottom() + rect.bottom;
            parent = viewGroup.getParent();
        }
        return rect;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r5) {
        /*
            r4 = this;
            boolean r0 = super.dispatchKeyEvent(r5)
            r1 = 1
            if (r0 != 0) goto L54
            int r0 = r5.getAction()
            if (r0 != 0) goto L53
            int r0 = r5.getKeyCode()
            r2 = 21
            r3 = 2
            if (r0 == r2) goto L43
            r2 = 22
            if (r0 == r2) goto L35
            r2 = 61
            if (r0 == r2) goto L1f
            goto L53
        L1f:
            boolean r0 = r5.hasNoModifiers()
            if (r0 == 0) goto L2a
            boolean r5 = r4.a(r3)
            goto L50
        L2a:
            boolean r5 = r5.hasModifiers(r1)
            if (r5 == 0) goto L53
            boolean r5 = r4.a(r1)
            goto L50
        L35:
            boolean r5 = r5.hasModifiers(r3)
            if (r5 == 0) goto L3c
            goto L53
        L3c:
            r5 = 66
            boolean r5 = r4.a(r5)
            goto L50
        L43:
            boolean r5 = r5.hasModifiers(r3)
            if (r5 == 0) goto L4a
            goto L53
        L4a:
            r5 = 17
            boolean r5 = r4.a(r5)
        L50:
            if (r5 == 0) goto L53
            goto L54
        L53:
            r1 = 0
        L54:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            if (getChildAt(i7).getVisibility() == 0) {
                e();
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        boolean z6;
        super.draw(canvas);
        if (getOverScrollMode() != 0) {
            this.f2848y.finish();
            this.f2849z.finish();
            return;
        }
        if (this.f2848y.isFinished()) {
            z6 = false;
        } else {
            int save = canvas.save();
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int width = getWidth();
            canvas.rotate(270.0f);
            canvas.translate(getPaddingTop() + (-height), this.f2831h * width);
            this.f2848y.setSize(height, width);
            z6 = this.f2848y.draw(canvas);
            canvas.restoreToCount(save);
        }
        if (!this.f2849z.isFinished()) {
            int save2 = canvas.save();
            int width2 = getWidth();
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            canvas.rotate(90.0f);
            canvas.translate(-getPaddingTop(), (-(this.f2832i + 1.0f)) * width2);
            this.f2849z.setSize(height2, width2);
            z6 |= this.f2849z.draw(canvas);
            canvas.restoreToCount(save2);
        }
        if (z6) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f2830g;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public final void e() {
        ArrayList arrayList = this.f2824a;
        if (arrayList.size() <= 0) {
            return;
        }
        ((f) arrayList.get(0)).getClass();
        throw null;
    }

    public final f f(int i7) {
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f2824a;
            if (i8 >= arrayList.size()) {
                return null;
            }
            f fVar = (f) arrayList.get(i8);
            if (fVar.f8435a == i7) {
                return fVar;
            }
            i8++;
        }
    }

    public final void g() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.f2828e = new Scroller(context, M);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f7 = context.getResources().getDisplayMetrics().density;
        this.f2841r = viewConfiguration.getScaledPagingTouchSlop();
        viewConfiguration.getScaledMaximumFlingVelocity();
        this.f2848y = new EdgeEffect(context);
        this.f2849z = new EdgeEffect(context);
        this.f2847x = (int) (2.0f * f7);
        this.f2839p = (int) (f7 * 16.0f);
        d1.o(this, new h(0, this));
        if (l0.c(this) == 0) {
            l0.s(this, 1);
        }
        r0.u(this, new i(this));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new q1.g(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        q1.g gVar = new q1.g(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L);
        gVar.f8438b = obtainStyledAttributes.getInteger(0, 48);
        obtainStyledAttributes.recycle();
        return gVar;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public q1.a getAdapter() {
        return null;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i7, int i8) {
        if (this.H == 2) {
            i8 = (i7 - 1) - i8;
        }
        ((q1.g) ((View) this.I.get(i8)).getLayoutParams()).getClass();
        return 0;
    }

    public int getCurrentItem() {
        return 0;
    }

    public int getOffscreenPageLimit() {
        return this.f2836m;
    }

    public int getPageMargin() {
        return this.f2829f;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(int r12, float r13, int r14) {
        /*
            r11 = this;
            int r14 = r11.C
            r0 = 0
            r1 = 1
            if (r14 <= 0) goto L6c
            int r14 = r11.getScrollX()
            int r2 = r11.getPaddingLeft()
            int r3 = r11.getPaddingRight()
            int r4 = r11.getWidth()
            int r5 = r11.getChildCount()
            r6 = r0
        L1b:
            if (r6 >= r5) goto L6c
            android.view.View r7 = r11.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            q1.g r8 = (q1.g) r8
            boolean r9 = r8.f8437a
            if (r9 != 0) goto L2c
            goto L69
        L2c:
            int r8 = r8.f8438b
            r8 = r8 & 7
            if (r8 == r1) goto L50
            r9 = 3
            if (r8 == r9) goto L4a
            r9 = 5
            if (r8 == r9) goto L3a
            r8 = r2
            goto L5d
        L3a:
            int r8 = r4 - r3
            int r9 = r7.getMeasuredWidth()
            int r8 = r8 - r9
            int r9 = r7.getMeasuredWidth()
            int r3 = r3 + r9
        L46:
            r10 = r8
            r8 = r2
            r2 = r10
            goto L5d
        L4a:
            int r8 = r7.getWidth()
            int r8 = r8 + r2
            goto L5d
        L50:
            int r8 = r7.getMeasuredWidth()
            int r8 = r4 - r8
            int r8 = r8 / 2
            int r8 = java.lang.Math.max(r8, r2)
            goto L46
        L5d:
            int r2 = r2 + r14
            int r9 = r7.getLeft()
            int r2 = r2 - r9
            if (r2 == 0) goto L68
            r7.offsetLeftAndRight(r2)
        L68:
            r2 = r8
        L69:
            int r6 = r6 + 1
            goto L1b
        L6c:
            q1.j r14 = r11.E
            if (r14 == 0) goto L73
            r14.a(r12, r13)
        L73:
            java.util.ArrayList r14 = r11.D
            if (r14 == 0) goto L8d
            int r14 = r14.size()
        L7b:
            if (r0 >= r14) goto L8d
            java.util.ArrayList r2 = r11.D
            java.lang.Object r2 = r2.get(r0)
            q1.j r2 = (q1.j) r2
            if (r2 == 0) goto L8a
            r2.a(r12, r13)
        L8a:
            int r0 = r0 + 1
            goto L7b
        L8d:
            q1.j r14 = r11.F
            if (r14 == 0) goto L94
            r14.a(r12, r13)
        L94:
            r11.B = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.h(int, float, int):void");
    }

    public final boolean i(int i7) {
        int i8;
        ArrayList arrayList = this.f2824a;
        if (arrayList.size() == 0) {
            if (this.A) {
                return false;
            }
            this.B = false;
            h(0, CropImageView.DEFAULT_ASPECT_RATIO, 0);
            if (this.B) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        int clientWidth = getClientWidth();
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f7 = clientWidth > 0 ? this.f2829f / clientWidth : 0.0f;
        int i9 = -1;
        boolean z6 = true;
        float f8 = 0.0f;
        int i10 = 0;
        f fVar = null;
        while (i10 < arrayList.size()) {
            f fVar2 = (f) arrayList.get(i10);
            if (!z6 && fVar2.f8435a != (i8 = i9 + 1)) {
                float f9 = f8 + CropImageView.DEFAULT_ASPECT_RATIO + f7;
                f fVar3 = this.f2825b;
                fVar3.f8436b = f9;
                fVar3.f8435a = i8;
                throw null;
            }
            f8 = fVar2.f8436b;
            float f10 = f8 + CropImageView.DEFAULT_ASPECT_RATIO + f7;
            if (!z6 && scrollX < f8) {
                break;
            }
            if (scrollX < f10 || i10 == arrayList.size() - 1) {
                fVar = fVar2;
                break;
            }
            i9 = fVar2.f8435a;
            i10++;
            z6 = false;
            fVar = fVar2;
        }
        int clientWidth2 = getClientWidth();
        int i11 = this.f2829f;
        int i12 = clientWidth2 + i11;
        float f11 = clientWidth2;
        int i13 = fVar.f8435a;
        float f12 = ((i7 / f11) - fVar.f8436b) / (CropImageView.DEFAULT_ASPECT_RATIO + (i11 / f11));
        this.B = false;
        h(i13, f12, (int) (i12 * f12));
        if (this.B) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    public final void j() {
        if (this.H != 0) {
            ArrayList arrayList = this.I;
            if (arrayList == null) {
                this.I = new ArrayList();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                this.I.add(getChildAt(i7));
            }
            Collections.sort(this.I, N);
        }
    }

    public final void k(int i7, int i8, int i9, int i10) {
        if (i8 > 0 && !this.f2824a.isEmpty()) {
            if (!this.f2828e.isFinished()) {
                this.f2828e.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i8 - getPaddingLeft()) - getPaddingRight()) + i10)) * (((i7 - getPaddingLeft()) - getPaddingRight()) + i9)), getScrollY());
                return;
            }
        }
        f f7 = f(0);
        int min = (int) ((f7 != null ? Math.min(f7.f8436b, this.f2832i) : CropImageView.DEFAULT_ASPECT_RATIO) * ((i7 - getPaddingLeft()) - getPaddingRight()));
        if (min != getScrollX()) {
            c(false);
            scrollTo(min, getScrollY());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.A = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.J);
        Scroller scroller = this.f2828e;
        if (scroller != null && !scroller.isFinished()) {
            this.f2828e.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f2829f <= 0 || this.f2830g == null) {
            return;
        }
        this.f2824a.size();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.f2845v = -1;
            this.f2837n = false;
            this.f2838o = false;
            VelocityTracker velocityTracker = this.f2846w;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f2846w = null;
            }
            this.f2848y.onRelease();
            this.f2849z.onRelease();
            if (!this.f2848y.isFinished()) {
                this.f2849z.isFinished();
            }
            return false;
        }
        if (action != 0) {
            if (this.f2837n) {
                return true;
            }
            if (this.f2838o) {
                return false;
            }
        }
        if (action == 0) {
            float x6 = motionEvent.getX();
            this.f2843t = x6;
            this.f2842s = x6;
            this.f2844u = motionEvent.getY();
            this.f2845v = motionEvent.getPointerId(0);
            this.f2838o = false;
            this.f2828e.computeScrollOffset();
            if (this.K != 2 || Math.abs(this.f2828e.getFinalX() - this.f2828e.getCurrX()) <= this.f2847x) {
                c(false);
                this.f2837n = false;
            } else {
                this.f2828e.abortAnimation();
                j();
                this.f2837n = true;
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                setScrollState(1);
            }
        } else if (action == 2) {
            int i7 = this.f2845v;
            if (i7 != -1) {
                int findPointerIndex = motionEvent.findPointerIndex(i7);
                float x7 = motionEvent.getX(findPointerIndex);
                float f7 = x7 - this.f2842s;
                float abs = Math.abs(f7);
                float y6 = motionEvent.getY(findPointerIndex);
                float abs2 = Math.abs(y6 - this.f2844u);
                if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
                    float f8 = this.f2842s;
                    if ((f8 >= this.f2840q || f7 <= CropImageView.DEFAULT_ASPECT_RATIO) && ((f8 <= getWidth() - this.f2840q || f7 >= CropImageView.DEFAULT_ASPECT_RATIO) && b((int) f7, (int) x7, (int) y6, this, false))) {
                        this.f2842s = x7;
                        this.f2838o = true;
                        return false;
                    }
                }
                float f9 = this.f2841r;
                if (abs > f9 && abs * 0.5f > abs2) {
                    this.f2837n = true;
                    ViewParent parent2 = getParent();
                    if (parent2 != null) {
                        parent2.requestDisallowInterceptTouchEvent(true);
                    }
                    setScrollState(1);
                    float f10 = this.f2843t;
                    float f11 = this.f2841r;
                    this.f2842s = f7 > CropImageView.DEFAULT_ASPECT_RATIO ? f10 + f11 : f10 - f11;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > f9) {
                    this.f2838o = true;
                }
                if (this.f2837n) {
                    this.f2842s = x7;
                    getScrollX();
                    getClientWidth();
                    ArrayList arrayList = this.f2824a;
                    f fVar = (f) arrayList.get(0);
                    f fVar2 = (f) arrayList.get(arrayList.size() - 1);
                    int i8 = fVar.f8435a;
                    int i9 = fVar2.f8435a;
                    throw null;
                }
            }
        } else if (action == 6) {
            int actionIndex = motionEvent.getActionIndex();
            if (motionEvent.getPointerId(actionIndex) == this.f2845v) {
                int i10 = actionIndex == 0 ? 1 : 0;
                this.f2842s = motionEvent.getX(i10);
                this.f2845v = motionEvent.getPointerId(i10);
                VelocityTracker velocityTracker2 = this.f2846w;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
        }
        if (this.f2846w == null) {
            this.f2846w = VelocityTracker.obtain();
        }
        this.f2846w.addMovement(motionEvent);
        return this.f2837n;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0094  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        q1.g gVar;
        q1.g gVar2;
        int i9;
        setMeasuredDimension(View.getDefaultSize(0, i7), View.getDefaultSize(0, i8));
        int measuredWidth = getMeasuredWidth();
        this.f2840q = Math.min(measuredWidth / 10, this.f2839p);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i10 = 0;
        while (true) {
            boolean z6 = true;
            int i11 = c.EXACTLY;
            if (i10 >= childCount) {
                break;
            }
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8 && (gVar2 = (q1.g) childAt.getLayoutParams()) != null && gVar2.f8437a) {
                int i12 = gVar2.f8438b;
                int i13 = i12 & 7;
                int i14 = i12 & z1.c.INVOKE_DIRECT;
                boolean z7 = i14 == 48 || i14 == 80;
                if (i13 != 3 && i13 != 5) {
                    z6 = false;
                }
                int i15 = Integer.MIN_VALUE;
                if (z7) {
                    i9 = Integer.MIN_VALUE;
                    i15 = 1073741824;
                } else {
                    i9 = z6 ? 1073741824 : Integer.MIN_VALUE;
                }
                int i16 = ((ViewGroup.LayoutParams) gVar2).width;
                if (i16 != -2) {
                    if (i16 == -1) {
                        i16 = paddingLeft;
                    }
                    i15 = 1073741824;
                } else {
                    i16 = paddingLeft;
                }
                int i17 = ((ViewGroup.LayoutParams) gVar2).height;
                if (i17 == -2) {
                    i17 = measuredHeight;
                    i11 = i9;
                } else if (i17 == -1) {
                    i17 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i16, i15), View.MeasureSpec.makeMeasureSpec(i17, i11));
                if (z7) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z6) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i10++;
        }
        View.MeasureSpec.makeMeasureSpec(paddingLeft, c.EXACTLY);
        this.f2833j = View.MeasureSpec.makeMeasureSpec(measuredHeight, c.EXACTLY);
        this.f2834k = true;
        j();
        this.f2834k = false;
        int childCount2 = getChildCount();
        for (int i18 = 0; i18 < childCount2; i18++) {
            View childAt2 = getChildAt(i18);
            if (childAt2.getVisibility() != 8 && ((gVar = (q1.g) childAt2.getLayoutParams()) == null || !gVar.f8437a)) {
                gVar.getClass();
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * CropImageView.DEFAULT_ASPECT_RATIO), c.EXACTLY), this.f2833j);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i7, Rect rect) {
        int i8;
        int i9;
        int i10;
        int childCount = getChildCount();
        if ((i7 & 2) != 0) {
            i9 = 1;
            i10 = childCount;
            i8 = 0;
        } else {
            i8 = childCount - 1;
            i9 = -1;
            i10 = -1;
        }
        while (i8 != i10) {
            if (getChildAt(i8).getVisibility() == 0) {
                e();
            }
            i8 += i9;
        }
        return false;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        this.f2827d = savedState.f2851c;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2850b = 0;
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        if (i7 != i9) {
            int i11 = this.f2829f;
            k(i7, i9, i11, i11);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            motionEvent.getEdgeFlags();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        if (this.f2834k) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(q1.a aVar) {
        ArrayList arrayList = this.G;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int size = this.G.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((q1.i) this.G.get(i7)).c(this);
        }
    }

    public void setCurrentItem(int i7) {
        setScrollingCacheEnabled(false);
    }

    public void setCurrentItem(int i7, boolean z6) {
        setScrollingCacheEnabled(false);
    }

    public void setOffscreenPageLimit(int i7) {
        if (i7 < 1) {
            i7 = 1;
        }
        if (i7 != this.f2836m) {
            this.f2836m = i7;
            j();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.E = jVar;
    }

    public void setPageMargin(int i7) {
        int i8 = this.f2829f;
        this.f2829f = i7;
        int width = getWidth();
        k(width, width, i7, i8);
        requestLayout();
    }

    public void setPageMarginDrawable(int i7) {
        Context context = getContext();
        Object obj = g0.g.f6423a;
        setPageMarginDrawable(g0.c.b(context, i7));
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f2830g = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageTransformer(boolean z6, k kVar) {
        setPageTransformer(z6, kVar, 2);
    }

    public void setPageTransformer(boolean z6, k kVar, int i7) {
        boolean z7 = kVar != null;
        setChildrenDrawingOrderEnabled(z7);
        if (z7) {
            this.H = z6 ? 2 : 1;
        } else {
            this.H = 0;
        }
        if (z7) {
            j();
        }
    }

    public void setScrollState(int i7) {
        if (this.K == i7) {
            return;
        }
        this.K = i7;
        j jVar = this.E;
        if (jVar != null) {
            jVar.b(i7);
        }
        ArrayList arrayList = this.D;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                j jVar2 = (j) this.D.get(i8);
                if (jVar2 != null) {
                    jVar2.b(i7);
                }
            }
        }
        j jVar3 = this.F;
        if (jVar3 != null) {
            jVar3.b(i7);
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f2830g;
    }
}
