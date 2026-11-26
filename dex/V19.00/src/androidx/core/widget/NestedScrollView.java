package androidx.core.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.fragment.app.h1;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import p0.a0;
import p0.d1;
import p0.l0;
import p0.r0;
import p0.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class NestedScrollView extends FrameLayout implements z, p0.w {
    public static final float B = (float) (Math.log(0.78d) / Math.log(0.9d));
    public static final k C = new k(0);
    public static final int[] D = {R.attr.fillViewport};
    public m A;

    /* renamed from: a, reason: collision with root package name */
    public final float f1766a;

    /* renamed from: b, reason: collision with root package name */
    public long f1767b;

    /* renamed from: c, reason: collision with root package name */
    public final Rect f1768c;

    /* renamed from: d, reason: collision with root package name */
    public final OverScroller f1769d;

    /* renamed from: e, reason: collision with root package name */
    public final EdgeEffect f1770e;

    /* renamed from: f, reason: collision with root package name */
    public final EdgeEffect f1771f;

    /* renamed from: g, reason: collision with root package name */
    public int f1772g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f1773h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f1774i;

    /* renamed from: j, reason: collision with root package name */
    public View f1775j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f1776k;

    /* renamed from: l, reason: collision with root package name */
    public VelocityTracker f1777l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1778m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f1779n;

    /* renamed from: o, reason: collision with root package name */
    public final int f1780o;

    /* renamed from: p, reason: collision with root package name */
    public final int f1781p;

    /* renamed from: q, reason: collision with root package name */
    public final int f1782q;

    /* renamed from: r, reason: collision with root package name */
    public int f1783r;

    /* renamed from: s, reason: collision with root package name */
    public final int[] f1784s;

    /* renamed from: t, reason: collision with root package name */
    public final int[] f1785t;

    /* renamed from: u, reason: collision with root package name */
    public int f1786u;

    /* renamed from: v, reason: collision with root package name */
    public int f1787v;

    /* renamed from: w, reason: collision with root package name */
    public SavedState f1788w;

    /* renamed from: x, reason: collision with root package name */
    public final a0 f1789x;

    /* renamed from: y, reason: collision with root package name */
    public final p0.x f1790y;

    /* renamed from: z, reason: collision with root package name */
    public float f1791z;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new n();

        /* renamed from: a, reason: collision with root package name */
        public int f1792a;

        public final String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f1792a + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f1792a);
        }
    }

    public NestedScrollView(Context context) {
        this(context, null);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d0.a.nestedScrollViewStyle);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1768c = new Rect();
        this.f1773h = true;
        this.f1774i = false;
        this.f1775j = null;
        this.f1776k = false;
        this.f1779n = true;
        this.f1783r = -1;
        this.f1784s = new int[2];
        this.f1785t = new int[2];
        int i8 = Build.VERSION.SDK_INT;
        this.f1770e = i8 >= 31 ? g.a(context, attributeSet) : new EdgeEffect(context);
        this.f1771f = i8 >= 31 ? g.a(context, attributeSet) : new EdgeEffect(context);
        this.f1766a = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        this.f1769d = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f1780o = viewConfiguration.getScaledTouchSlop();
        this.f1781p = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1782q = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, D, i7, 0);
        setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.f1789x = new a0();
        this.f1790y = new p0.x(this);
        setNestedScrollingEnabled(true);
        d1.o(this, C);
    }

    private float getVerticalScrollFactorCompat() {
        if (this.f1791z == CropImageView.DEFAULT_ASPECT_RATIO) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.f1791z = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.f1791z;
    }

    public static boolean m(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && m((View) parent, view2);
    }

    public final boolean a(int i7) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i7);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus == null || !n(findNextFocus, maxScrollAmount, getHeight())) {
            if (i7 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i7 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i7 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            g(maxScrollAmount);
        } else {
            Rect rect = this.f1768c;
            findNextFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(findNextFocus, rect);
            g(c(rect));
            findNextFocus.requestFocus(i7);
        }
        if (findFocus != null && findFocus.isFocused() && (!n(findFocus, 0, getHeight()))) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i7);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i7, layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, layoutParams);
    }

    @Override // p0.y
    public final void b(View view, View view2, int i7, int i8) {
        a0 a0Var = this.f1789x;
        if (i8 == 1) {
            a0Var.f8093b = i7;
        } else {
            a0Var.f8092a = i7;
        }
        this.f1790y.h(2, i8);
    }

    public final int c(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i7 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i8 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i7 - verticalFadingEdgeLength : i7;
        int i9 = rect.bottom;
        if (i9 > i8 && rect.top > scrollY) {
            return Math.min(rect.height() > height ? rect.top - scrollY : rect.bottom - i8, (childAt.getBottom() + layoutParams.bottomMargin) - i7);
        }
        if (rect.top >= scrollY || i9 >= i8) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i8 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e8  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void computeScroll() {
        /*
            Method dump skipped, instructions count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.computeScroll():void");
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int max = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > max ? bottom + (scrollY - max) : bottom;
    }

    @Override // p0.y
    public final void d(View view, int i7, int i8, int[] iArr, int i9) {
        this.f1790y.c(i7, i8, iArr, null, i9);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || h(keyEvent);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f7, float f8, boolean z6) {
        return this.f1790y.a(f7, f8, z6);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f7, float f8) {
        return this.f1790y.b(f7, f8);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i7, int i8, int[] iArr, int[] iArr2) {
        return this.f1790y.c(i7, i8, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, int[] iArr) {
        return this.f1790y.e(i7, i8, i9, i10, iArr, 0, null);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int i7;
        super.draw(canvas);
        int scrollY = getScrollY();
        EdgeEffect edgeEffect = this.f1770e;
        int i8 = 0;
        if (!edgeEffect.isFinished()) {
            int save = canvas.save();
            int width = getWidth();
            int height = getHeight();
            int min = Math.min(0, scrollY);
            if (l.a(this)) {
                width -= getPaddingRight() + getPaddingLeft();
                i7 = getPaddingLeft();
            } else {
                i7 = 0;
            }
            if (l.a(this)) {
                height -= getPaddingBottom() + getPaddingTop();
                min += getPaddingTop();
            }
            canvas.translate(i7, min);
            edgeEffect.setSize(width, height);
            if (edgeEffect.draw(canvas)) {
                int i9 = d1.OVER_SCROLL_ALWAYS;
                l0.k(this);
            }
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect2 = this.f1771f;
        if (edgeEffect2.isFinished()) {
            return;
        }
        int save2 = canvas.save();
        int width2 = getWidth();
        int height2 = getHeight();
        int max = Math.max(getScrollRange(), scrollY) + height2;
        if (l.a(this)) {
            width2 -= getPaddingRight() + getPaddingLeft();
            i8 = getPaddingLeft();
        }
        if (l.a(this)) {
            height2 -= getPaddingBottom() + getPaddingTop();
            max -= getPaddingBottom();
        }
        canvas.translate(i8 - width2, max);
        canvas.rotate(180.0f, width2, CropImageView.DEFAULT_ASPECT_RATIO);
        edgeEffect2.setSize(width2, height2);
        if (edgeEffect2.draw(canvas)) {
            int i10 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
        canvas.restoreToCount(save2);
    }

    @Override // p0.y
    public final void e(int i7, View view) {
        a0 a0Var = this.f1789x;
        if (i7 == 1) {
            a0Var.f8093b = 0;
        } else {
            a0Var.f8092a = 0;
        }
        v(i7);
    }

    @Override // p0.z
    public final void f(View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        o(i10, i11, iArr);
    }

    public final void g(int i7) {
        if (i7 != 0) {
            if (this.f1779n) {
                t(0, i7, false);
            } else {
                scrollBy(0, i7);
            }
        }
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + layoutParams.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        a0 a0Var = this.f1789x;
        return a0Var.f8093b | a0Var.f8092a;
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public final boolean h(KeyEvent keyEvent) {
        Rect rect = this.f1768c;
        rect.setEmpty();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            if (childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom()) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                int keyCode = keyEvent.getKeyCode();
                if (keyCode == 19) {
                    return !keyEvent.isAltPressed() ? a(33) : l(33);
                }
                if (keyCode == 20) {
                    return !keyEvent.isAltPressed() ? a(z1.c.INT_TO_FLOAT) : l(z1.c.INT_TO_FLOAT);
                }
                if (keyCode != 62) {
                    return false;
                }
                int i7 = keyEvent.isShiftPressed() ? 33 : 130;
                boolean z6 = i7 == 130;
                int height = getHeight();
                if (z6) {
                    rect.top = getScrollY() + height;
                    int childCount = getChildCount();
                    if (childCount > 0) {
                        View childAt2 = getChildAt(childCount - 1);
                        int paddingBottom = getPaddingBottom() + childAt2.getBottom() + ((FrameLayout.LayoutParams) childAt2.getLayoutParams()).bottomMargin;
                        if (rect.top + height > paddingBottom) {
                            rect.top = paddingBottom - height;
                        }
                    }
                } else {
                    int scrollY = getScrollY() - height;
                    rect.top = scrollY;
                    if (scrollY < 0) {
                        rect.top = 0;
                    }
                }
                int i8 = rect.top;
                int i9 = height + i8;
                rect.bottom = i9;
                r(i7, i8, i9);
                return false;
            }
        }
        if (!isFocused() || keyEvent.getKeyCode() == 4) {
            return false;
        }
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, z1.c.INT_TO_FLOAT);
        return (findNextFocus == null || findNextFocus == this || !findNextFocus.requestFocus(z1.c.INT_TO_FLOAT)) ? false : true;
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.f1790y.g(0);
    }

    @Override // p0.y
    public final void i(View view, int i7, int i8, int i9, int i10, int i11) {
        o(i10, i11, null);
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.f1790y.f8201d;
    }

    @Override // p0.y
    public final boolean j(View view, View view2, int i7, int i8) {
        return (i7 & 2) != 0;
    }

    public final void k(int i7) {
        if (getChildCount() > 0) {
            this.f1769d.fling(getScrollX(), getScrollY(), 0, i7, 0, 0, Integer.MIN_VALUE, androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED, 0, 0);
            this.f1790y.h(2, 1);
            this.f1787v = getScrollY();
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
    }

    public final boolean l(int i7) {
        int childCount;
        boolean z6 = i7 == 130;
        int height = getHeight();
        Rect rect = this.f1768c;
        rect.top = 0;
        rect.bottom = height;
        if (z6 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            rect.bottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            rect.top = rect.bottom - height;
        }
        return r(i7, rect.top, rect.bottom);
    }

    @Override // android.view.ViewGroup
    public final void measureChild(View view, int i7, int i8) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public final void measureChildWithMargins(View view, int i7, int i8, int i9, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i8, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final boolean n(View view, int i7, int i8) {
        Rect rect = this.f1768c;
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        return rect.bottom + i7 >= getScrollY() && rect.top - i7 <= getScrollY() + i8;
    }

    public final void o(int i7, int i8, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i7);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.f1790y.e(0, scrollY2, 0, i7 - scrollY2, null, i8, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1774i = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        boolean z6;
        int i7 = 0;
        if (motionEvent.getAction() == 8 && !this.f1776k) {
            float axisValue = p0.v.a(motionEvent, 2) ? motionEvent.getAxisValue(9) : p0.v.a(motionEvent, 4194304) ? motionEvent.getAxisValue(26) : 0.0f;
            if (axisValue != CropImageView.DEFAULT_ASPECT_RATIO) {
                int verticalScrollFactorCompat = (int) (axisValue * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i8 = scrollY - verticalScrollFactorCompat;
                if (i8 < 0) {
                    int overScrollMode = getOverScrollMode();
                    if ((overScrollMode == 0 || (overScrollMode == 1 && getScrollRange() > 0)) && !p0.v.a(motionEvent, h1.TRANSIT_FRAGMENT_CLOSE)) {
                        float height = (-i8) / getHeight();
                        EdgeEffect edgeEffect = this.f1770e;
                        n1.z.P0(edgeEffect, height, 0.5f);
                        edgeEffect.onRelease();
                        invalidate();
                        z6 = 1;
                    } else {
                        z6 = 0;
                    }
                } else if (i8 > scrollRange) {
                    int overScrollMode2 = getOverScrollMode();
                    if ((overScrollMode2 == 0 || (overScrollMode2 == 1 && getScrollRange() > 0)) && !p0.v.a(motionEvent, h1.TRANSIT_FRAGMENT_CLOSE)) {
                        float height2 = (i8 - scrollRange) / getHeight();
                        EdgeEffect edgeEffect2 = this.f1771f;
                        n1.z.P0(edgeEffect2, height2, 0.5f);
                        edgeEffect2.onRelease();
                        invalidate();
                        i7 = 1;
                    }
                    z6 = i7;
                    i7 = scrollRange;
                } else {
                    z6 = 0;
                    i7 = i8;
                }
                if (i7 == scrollY) {
                    return z6;
                }
                super.scrollTo(getScrollX(), i7);
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int findPointerIndex;
        int action = motionEvent.getAction();
        boolean z6 = true;
        if (action == 2 && this.f1776k) {
            return true;
        }
        int i7 = action & 255;
        if (i7 != 0) {
            if (i7 != 1) {
                if (i7 == 2) {
                    int i8 = this.f1783r;
                    if (i8 != -1 && (findPointerIndex = motionEvent.findPointerIndex(i8)) != -1) {
                        int y6 = (int) motionEvent.getY(findPointerIndex);
                        if (Math.abs(y6 - this.f1772g) > this.f1780o && (2 & getNestedScrollAxes()) == 0) {
                            this.f1776k = true;
                            this.f1772g = y6;
                            if (this.f1777l == null) {
                                this.f1777l = VelocityTracker.obtain();
                            }
                            this.f1777l.addMovement(motionEvent);
                            this.f1786u = 0;
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                } else if (i7 != 3) {
                    if (i7 == 6) {
                        p(motionEvent);
                    }
                }
            }
            this.f1776k = false;
            this.f1783r = -1;
            VelocityTracker velocityTracker = this.f1777l;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f1777l = null;
            }
            if (this.f1769d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                int i9 = d1.OVER_SCROLL_ALWAYS;
                l0.k(this);
            }
            v(0);
        } else {
            int y7 = (int) motionEvent.getY();
            int x6 = (int) motionEvent.getX();
            if (getChildCount() > 0) {
                int scrollY = getScrollY();
                View childAt = getChildAt(0);
                if (y7 >= childAt.getTop() - scrollY && y7 < childAt.getBottom() - scrollY && x6 >= childAt.getLeft() && x6 < childAt.getRight()) {
                    this.f1772g = y7;
                    this.f1783r = motionEvent.getPointerId(0);
                    VelocityTracker velocityTracker2 = this.f1777l;
                    if (velocityTracker2 == null) {
                        this.f1777l = VelocityTracker.obtain();
                    } else {
                        velocityTracker2.clear();
                    }
                    this.f1777l.addMovement(motionEvent);
                    this.f1769d.computeScrollOffset();
                    if (!u(motionEvent) && this.f1769d.isFinished()) {
                        z6 = false;
                    }
                    this.f1776k = z6;
                    this.f1790y.h(2, 0);
                }
            }
            if (!u(motionEvent) && this.f1769d.isFinished()) {
                z6 = false;
            }
            this.f1776k = z6;
            VelocityTracker velocityTracker3 = this.f1777l;
            if (velocityTracker3 != null) {
                velocityTracker3.recycle();
                this.f1777l = null;
            }
        }
        return this.f1776k;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int i11;
        super.onLayout(z6, i7, i8, i9, i10);
        int i12 = 0;
        this.f1773h = false;
        View view = this.f1775j;
        if (view != null && m(view, this)) {
            View view2 = this.f1775j;
            Rect rect = this.f1768c;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int c7 = c(rect);
            if (c7 != 0) {
                scrollBy(0, c7);
            }
        }
        this.f1775j = null;
        if (!this.f1774i) {
            if (this.f1788w != null) {
                scrollTo(getScrollX(), this.f1788w.f1792a);
                this.f1788w = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i11 = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            } else {
                i11 = 0;
            }
            int paddingTop = ((i10 - i8) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            if (paddingTop < i11 && scrollY >= 0) {
                i12 = paddingTop + scrollY > i11 ? i11 - paddingTop : scrollY;
            }
            if (i12 != scrollY) {
                scrollTo(getScrollX(), i12);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f1774i = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (this.f1778m && View.MeasureSpec.getMode(i8) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, v.c.EXACTLY));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        if (z6) {
            return false;
        }
        dispatchNestedFling(CropImageView.DEFAULT_ASPECT_RATIO, f8, true);
        k((int) f8);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f7, float f8) {
        return dispatchNestedPreFling(f7, f8);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i7, int i8, int[] iArr) {
        this.f1790y.c(i7, i8, iArr, null, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i7, int i8, int i9, int i10) {
        o(i10, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i7) {
        b(view, view2, i7, 0);
    }

    @Override // android.view.View
    public final void onOverScrolled(int i7, int i8, boolean z6, boolean z7) {
        super.scrollTo(i7, i8);
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i7, Rect rect) {
        if (i7 == 2) {
            i7 = z1.c.INT_TO_FLOAT;
        } else if (i7 == 1) {
            i7 = 33;
        }
        View findNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i7) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i7);
        if (findNextFocus == null || (true ^ n(findNextFocus, 0, getHeight()))) {
            return false;
        }
        return findNextFocus.requestFocus(i7, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f1788w = savedState;
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1792a = getScrollY();
        return savedState;
    }

    @Override // android.view.View
    public final void onScrollChanged(int i7, int i8, int i9, int i10) {
        super.onScrollChanged(i7, i8, i9, i10);
        m mVar = this.A;
        if (mVar != null) {
            mVar.x(this);
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        View findFocus = findFocus();
        if (findFocus == null || this == findFocus || !n(findFocus, 0, i10)) {
            return;
        }
        Rect rect = this.f1768c;
        findFocus.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(findFocus, rect);
        g(c(rect));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i7) {
        return j(view, view2, i7, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        e(0, view);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0135  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r25) {
        /*
            Method dump skipped, instructions count: 743
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f1783r) {
            int i7 = actionIndex == 0 ? 1 : 0;
            this.f1772g = (int) motionEvent.getY(i7);
            this.f1783r = motionEvent.getPointerId(i7);
            VelocityTracker velocityTracker = this.f1777l;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public final boolean q(int i7, int i8, int i9, int i10) {
        boolean z6;
        boolean z7;
        getOverScrollMode();
        computeHorizontalScrollRange();
        computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        computeVerticalScrollExtent();
        int i11 = i9 + i7;
        if (i8 <= 0 && i8 >= 0) {
            z6 = false;
        } else {
            i8 = 0;
            z6 = true;
        }
        if (i11 <= i10) {
            if (i11 >= 0) {
                i10 = i11;
                z7 = false;
                if (z7 && !this.f1790y.g(1)) {
                    this.f1769d.springBack(i8, i10, 0, 0, 0, getScrollRange());
                }
                onOverScrolled(i8, i10, z6, z7);
                return z6 || z7;
            }
            i10 = 0;
        }
        z7 = true;
        if (z7) {
            this.f1769d.springBack(i8, i10, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(i8, i10, z6, z7);
        if (z6) {
            return true;
        }
    }

    public final boolean r(int i7, int i8, int i9) {
        boolean z6;
        int height = getHeight();
        int scrollY = getScrollY();
        int i10 = height + scrollY;
        boolean z7 = i7 == 33;
        ArrayList<View> focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z8 = false;
        for (int i11 = 0; i11 < size; i11++) {
            View view2 = focusables.get(i11);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i8 < bottom && top < i9) {
                boolean z9 = i8 < top && bottom < i9;
                if (view == null) {
                    view = view2;
                    z8 = z9;
                } else {
                    boolean z10 = (z7 && top < view.getTop()) || (!z7 && bottom > view.getBottom());
                    if (z8) {
                        if (z9) {
                            if (!z10) {
                            }
                            view = view2;
                        }
                    } else if (z9) {
                        view = view2;
                        z8 = true;
                    } else {
                        if (!z10) {
                        }
                        view = view2;
                    }
                }
            }
        }
        if (view == null) {
            view = this;
        }
        if (i8 < scrollY || i9 > i10) {
            g(z7 ? i8 - scrollY : i9 - i10);
            z6 = true;
        } else {
            z6 = false;
        }
        if (view != findFocus()) {
            view.requestFocus(i7);
        }
        return z6;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (this.f1773h) {
            this.f1775j = view2;
        } else {
            Rect rect = this.f1768c;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int c7 = c(rect);
            if (c7 != 0) {
                scrollBy(0, c7);
            }
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int c7 = c(rect);
        boolean z7 = c7 != 0;
        if (z7) {
            if (z6) {
                scrollBy(0, c7);
            } else {
                t(0, c7, false);
            }
        }
        return z7;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z6) {
        VelocityTracker velocityTracker;
        if (z6 && (velocityTracker = this.f1777l) != null) {
            velocityTracker.recycle();
            this.f1777l = null;
        }
        super.requestDisallowInterceptTouchEvent(z6);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.f1773h = true;
        super.requestLayout();
    }

    public final boolean s(EdgeEffect edgeEffect, int i7) {
        if (i7 > 0) {
            return true;
        }
        float j02 = n1.z.j0(edgeEffect) * getHeight();
        float abs = Math.abs(-i7) * 0.35f;
        float f7 = this.f1766a * 0.015f;
        double log = Math.log(abs / f7);
        double d7 = B;
        return ((float) (Math.exp((d7 / (d7 - 1.0d)) * log) * ((double) f7))) < j02;
    }

    @Override // android.view.View
    public final void scrollTo(int i7, int i8) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (width >= width2 || i7 < 0) {
                i7 = 0;
            } else if (width + i7 > width2) {
                i7 = width2 - width;
            }
            if (height >= height2 || i8 < 0) {
                i8 = 0;
            } else if (height + i8 > height2) {
                i8 = height2 - height;
            }
            if (i7 == getScrollX() && i8 == getScrollY()) {
                return;
            }
            super.scrollTo(i7, i8);
        }
    }

    public void setFillViewport(boolean z6) {
        if (z6 != this.f1778m) {
            this.f1778m = z6;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z6) {
        p0.x xVar = this.f1790y;
        if (xVar.f8201d) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            r0.z(xVar.f8200c);
        }
        xVar.f8201d = z6;
    }

    public void setOnScrollChangeListener(m mVar) {
        this.A = mVar;
    }

    public void setSmoothScrollingEnabled(boolean z6) {
        this.f1779n = z6;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i7) {
        return this.f1790y.h(i7, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        v(0);
    }

    public final void t(int i7, int i8, boolean z6) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f1767b > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.f1769d.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i8 + scrollY, Math.max(0, height - height2))) - scrollY, 250);
            if (z6) {
                this.f1790y.h(2, 1);
            } else {
                v(1);
            }
            this.f1787v = getScrollY();
            int i9 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        } else {
            if (!this.f1769d.isFinished()) {
                this.f1769d.abortAnimation();
                v(1);
            }
            scrollBy(i7, i8);
        }
        this.f1767b = AnimationUtils.currentAnimationTimeMillis();
    }

    public final boolean u(MotionEvent motionEvent) {
        boolean z6;
        EdgeEffect edgeEffect = this.f1770e;
        if (n1.z.j0(edgeEffect) != CropImageView.DEFAULT_ASPECT_RATIO) {
            n1.z.P0(edgeEffect, CropImageView.DEFAULT_ASPECT_RATIO, motionEvent.getX() / getWidth());
            z6 = true;
        } else {
            z6 = false;
        }
        EdgeEffect edgeEffect2 = this.f1771f;
        if (n1.z.j0(edgeEffect2) == CropImageView.DEFAULT_ASPECT_RATIO) {
            return z6;
        }
        n1.z.P0(edgeEffect2, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f - (motionEvent.getX() / getWidth()));
        return true;
    }

    public final void v(int i7) {
        this.f1790y.i(i7);
    }
}
