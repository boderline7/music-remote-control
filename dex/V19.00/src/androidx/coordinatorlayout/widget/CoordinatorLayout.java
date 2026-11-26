package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.customview.view.AbsSavedState;
import b0.b;
import b0.c;
import c0.e;
import c0.f;
import c0.g;
import com.yalantis.ucrop.view.CropImageView;
import j.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import o0.d;
import p0.a0;
import p0.d1;
import p0.l0;
import p0.l2;
import p0.m0;
import p0.o0;
import p0.p0;
import p0.r0;
import p0.y;
import p0.z;
import q.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CoordinatorLayout extends ViewGroup implements y, z {

    /* renamed from: t, reason: collision with root package name */
    public static final String f1714t;

    /* renamed from: u, reason: collision with root package name */
    public static final Class[] f1715u;

    /* renamed from: v, reason: collision with root package name */
    public static final ThreadLocal f1716v;

    /* renamed from: w, reason: collision with root package name */
    public static final g f1717w;

    /* renamed from: x, reason: collision with root package name */
    public static final d f1718x;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f1719a;

    /* renamed from: b, reason: collision with root package name */
    public final h f1720b;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f1721c;

    /* renamed from: d, reason: collision with root package name */
    public final ArrayList f1722d;

    /* renamed from: e, reason: collision with root package name */
    public final int[] f1723e;

    /* renamed from: f, reason: collision with root package name */
    public final int[] f1724f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1725g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f1726h;

    /* renamed from: i, reason: collision with root package name */
    public final int[] f1727i;

    /* renamed from: j, reason: collision with root package name */
    public View f1728j;

    /* renamed from: k, reason: collision with root package name */
    public View f1729k;

    /* renamed from: l, reason: collision with root package name */
    public f f1730l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1731m;

    /* renamed from: n, reason: collision with root package name */
    public l2 f1732n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f1733o;

    /* renamed from: p, reason: collision with root package name */
    public Drawable f1734p;

    /* renamed from: q, reason: collision with root package name */
    public ViewGroup.OnHierarchyChangeListener f1735q;

    /* renamed from: r, reason: collision with root package name */
    public e.a f1736r;

    /* renamed from: s, reason: collision with root package name */
    public final a0 f1737s;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        public SparseArray f1738b;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.f1738b = new SparseArray(readInt);
            for (int i7 = 0; i7 < readInt; i7++) {
                this.f1738b.append(iArr[i7], readParcelableArray[i7]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            SparseArray sparseArray = this.f1738b;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i8 = 0; i8 < size; i8++) {
                iArr[i8] = this.f1738b.keyAt(i8);
                parcelableArr[i8] = (Parcelable) this.f1738b.valueAt(i8);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i7);
        }
    }

    static {
        Package r02 = CoordinatorLayout.class.getPackage();
        f1714t = r02 != null ? r02.getName() : null;
        f1717w = new g(0);
        f1715u = new Class[]{Context.class, AttributeSet.class};
        f1716v = new ThreadLocal();
        f1718x = new d(12);
    }

    public CoordinatorLayout(Context context) {
        this(context, null);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b0.a.coordinatorLayoutStyle);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1719a = new ArrayList();
        this.f1720b = new h(2);
        this.f1721c = new ArrayList();
        this.f1722d = new ArrayList();
        this.f1723e = new int[2];
        this.f1724f = new int[2];
        this.f1737s = new a0();
        TypedArray obtainStyledAttributes = i7 == 0 ? context.obtainStyledAttributes(attributeSet, c.CoordinatorLayout, 0, b.Widget_Support_CoordinatorLayout) : context.obtainStyledAttributes(attributeSet, c.CoordinatorLayout, i7, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            if (i7 == 0) {
                saveAttributeDataForStyleable(context, c.CoordinatorLayout, attributeSet, obtainStyledAttributes, 0, b.Widget_Support_CoordinatorLayout);
            } else {
                saveAttributeDataForStyleable(context, c.CoordinatorLayout, attributeSet, obtainStyledAttributes, i7, 0);
            }
        }
        int resourceId = obtainStyledAttributes.getResourceId(c.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            int[] intArray = resources.getIntArray(resourceId);
            this.f1727i = intArray;
            float f7 = resources.getDisplayMetrics().density;
            int length = intArray.length;
            for (int i8 = 0; i8 < length; i8++) {
                this.f1727i[i8] = (int) (r11[i8] * f7);
            }
        }
        this.f1734p = obtainStyledAttributes.getDrawable(c.CoordinatorLayout_statusBarBackground);
        obtainStyledAttributes.recycle();
        B();
        super.setOnHierarchyChangeListener(new c0.d(this));
        int i9 = d1.OVER_SCROLL_ALWAYS;
        if (l0.c(this) == 0) {
            l0.s(this, 1);
        }
    }

    public static void A(int i7, View view) {
        e eVar = (e) view.getLayoutParams();
        int i8 = eVar.f3016j;
        if (i8 != i7) {
            int i9 = d1.OVER_SCROLL_ALWAYS;
            view.offsetTopAndBottom(i7 - i8);
            eVar.f3016j = i7;
        }
    }

    public static Rect k() {
        Rect rect = (Rect) f1718x.a();
        return rect == null ? new Rect() : rect;
    }

    public static void q(int i7, Rect rect, Rect rect2, e eVar, int i8, int i9) {
        int i10 = eVar.f3009c;
        if (i10 == 0) {
            i10 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i10, i7);
        int i11 = eVar.f3010d;
        if ((i11 & 7) == 0) {
            i11 |= 8388611;
        }
        if ((i11 & z1.c.INVOKE_DIRECT) == 0) {
            i11 |= 48;
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(i11, i7);
        int i12 = absoluteGravity & 7;
        int i13 = absoluteGravity & z1.c.INVOKE_DIRECT;
        int i14 = absoluteGravity2 & 7;
        int i15 = absoluteGravity2 & z1.c.INVOKE_DIRECT;
        int width = i14 != 1 ? i14 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int height = i15 != 16 ? i15 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i12 == 1) {
            width -= i8 / 2;
        } else if (i12 != 5) {
            width -= i8;
        }
        if (i13 == 16) {
            height -= i9 / 2;
        } else if (i13 != 80) {
            height -= i9;
        }
        rect2.set(width, height, i8 + width, i9 + height);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static e r(View view) {
        e eVar = (e) view.getLayoutParams();
        if (!eVar.f3008b) {
            if (view instanceof c0.a) {
                c0.b behavior = ((c0.a) view).getBehavior();
                c0.b bVar = eVar.f3007a;
                if (bVar != behavior) {
                    if (bVar != null) {
                        bVar.j();
                    }
                    eVar.f3007a = behavior;
                    eVar.f3008b = true;
                    if (behavior != null) {
                        behavior.g(eVar);
                    }
                }
                eVar.f3008b = true;
            } else {
                c0.c cVar = null;
                for (Class<?> cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                    cVar = (c0.c) cls.getAnnotation(c0.c.class);
                    if (cVar != null) {
                        break;
                    }
                }
                if (cVar != null) {
                    try {
                        c0.b bVar2 = (c0.b) cVar.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                        c0.b bVar3 = eVar.f3007a;
                        if (bVar3 != bVar2) {
                            if (bVar3 != null) {
                                bVar3.j();
                            }
                            eVar.f3007a = bVar2;
                            eVar.f3008b = true;
                            if (bVar2 != null) {
                                bVar2.g(eVar);
                            }
                        }
                    } catch (Exception unused) {
                        cVar.value().getClass();
                    }
                }
                eVar.f3008b = true;
            }
        }
        return eVar;
    }

    public static void z(int i7, View view) {
        e eVar = (e) view.getLayoutParams();
        int i8 = eVar.f3015i;
        if (i8 != i7) {
            int i9 = d1.OVER_SCROLL_ALWAYS;
            view.offsetLeftAndRight(i7 - i8);
            eVar.f3015i = i7;
        }
    }

    public final void B() {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        if (!l0.b(this)) {
            r0.u(this, null);
            return;
        }
        if (this.f1736r == null) {
            this.f1736r = new e.a(8, this);
        }
        r0.u(this, this.f1736r);
        setSystemUiVisibility(1280);
    }

    @Override // p0.y
    public final void b(View view, View view2, int i7, int i8) {
        a0 a0Var = this.f1737s;
        if (i8 == 1) {
            a0Var.f8093b = i7;
        } else {
            a0Var.f8092a = i7;
        }
        this.f1729k = view2;
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            ((e) getChildAt(i9).getLayoutParams()).getClass();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof e) && super.checkLayoutParams(layoutParams);
    }

    @Override // p0.y
    public final void d(View view, int i7, int i8, int[] iArr, int i9) {
        c0.b bVar;
        int childCount = getChildCount();
        boolean z6 = false;
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(i9) && (bVar = eVar.f3007a) != null) {
                    int[] iArr2 = this.f1723e;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    bVar.o(this, childAt, view, i7, i8, iArr2, i9);
                    int[] iArr3 = this.f1723e;
                    i10 = i7 > 0 ? Math.max(i10, iArr3[0]) : Math.min(i10, iArr3[0]);
                    i11 = i8 > 0 ? Math.max(i11, iArr3[1]) : Math.min(i11, iArr3[1]);
                    z6 = true;
                }
            }
        }
        iArr[0] = i10;
        iArr[1] = i11;
        if (z6) {
            t(1);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j7) {
        c0.b bVar = ((e) view.getLayoutParams()).f3007a;
        if (bVar != null) {
            bVar.getClass();
        }
        return super.drawChild(canvas, view, j7);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1734p;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidate();
        }
    }

    @Override // p0.y
    public final void e(int i7, View view) {
        a0 a0Var = this.f1737s;
        if (i7 == 1) {
            a0Var.f8093b = 0;
        } else {
            a0Var.f8092a = 0;
        }
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            e eVar = (e) childAt.getLayoutParams();
            if (eVar.a(i7)) {
                c0.b bVar = eVar.f3007a;
                if (bVar != null) {
                    bVar.u(this, childAt, view, i7);
                }
                if (i7 == 0) {
                    eVar.f3020n = false;
                } else if (i7 == 1) {
                    eVar.f3021o = false;
                }
                eVar.f3022p = false;
            }
        }
        this.f1729k = null;
    }

    @Override // p0.z
    public final void f(View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        c0.b bVar;
        int childCount = getChildCount();
        boolean z6 = false;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(i11) && (bVar = eVar.f3007a) != null) {
                    int[] iArr2 = this.f1723e;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    bVar.p(this, childAt, i8, i9, i10, iArr2);
                    i12 = i9 > 0 ? Math.max(i12, iArr2[0]) : Math.min(i12, iArr2[0]);
                    i13 = i10 > 0 ? Math.max(i13, iArr2[1]) : Math.min(i13, iArr2[1]);
                    z6 = true;
                }
            }
        }
        iArr[0] = iArr[0] + i12;
        iArr[1] = iArr[1] + i13;
        if (z6) {
            t(1);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new e();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e ? new e((e) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new e((ViewGroup.MarginLayoutParams) layoutParams) : new e(layoutParams);
    }

    public final List<View> getDependencySortedChildren() {
        x();
        return Collections.unmodifiableList(this.f1719a);
    }

    public final l2 getLastWindowInsets() {
        return this.f1732n;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        a0 a0Var = this.f1737s;
        return a0Var.f8093b | a0Var.f8092a;
    }

    public Drawable getStatusBarBackground() {
        return this.f1734p;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    @Override // p0.y
    public final void i(View view, int i7, int i8, int i9, int i10, int i11) {
        f(view, i7, i8, i9, i10, 0, this.f1724f);
    }

    @Override // p0.y
    public final boolean j(View view, View view2, int i7, int i8) {
        int childCount = getChildCount();
        boolean z6 = false;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                c0.b bVar = eVar.f3007a;
                if (bVar != null) {
                    boolean t6 = bVar.t(this, childAt, view, view2, i7, i8);
                    z6 |= t6;
                    if (i8 == 0) {
                        eVar.f3020n = t6;
                    } else if (i8 == 1) {
                        eVar.f3021o = t6;
                    }
                } else if (i8 == 0) {
                    eVar.f3020n = false;
                } else if (i8 == 1) {
                    eVar.f3021o = false;
                }
            }
        }
        return z6;
    }

    public final void l(e eVar, Rect rect, int i7, int i8) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i7) - ((ViewGroup.MarginLayoutParams) eVar).rightMargin));
        int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) eVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i8) - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin));
        rect.set(max, max2, i7 + max, i8 + max2);
    }

    public final void m(View view) {
        List list = (List) ((k) this.f1720b.f6893d).getOrDefault(view, null);
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i7 = 0; i7 < list.size(); i7++) {
            View view2 = (View) list.get(i7);
            c0.b bVar = ((e) view2.getLayoutParams()).f3007a;
            if (bVar != null) {
                bVar.h(this, view2, view);
            }
        }
    }

    public final void n(View view, Rect rect, boolean z6) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z6) {
            p(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public final ArrayList o(View view) {
        h hVar = this.f1720b;
        int i7 = ((k) hVar.f6893d).f8412c;
        ArrayList arrayList = null;
        for (int i8 = 0; i8 < i7; i8++) {
            ArrayList arrayList2 = (ArrayList) ((k) hVar.f6893d).j(i8);
            if (arrayList2 != null && arrayList2.contains(view)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(((k) hVar.f6893d).h(i8));
            }
        }
        ArrayList arrayList3 = this.f1722d;
        arrayList3.clear();
        if (arrayList != null) {
            arrayList3.addAll(arrayList);
        }
        return arrayList3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i7 = 0;
        y(false);
        if (this.f1731m) {
            if (this.f1730l == null) {
                this.f1730l = new f(i7, this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.f1730l);
        }
        if (this.f1732n == null) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            if (l0.b(this)) {
                p0.c(this);
            }
        }
        this.f1726h = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        y(false);
        if (this.f1731m && this.f1730l != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.f1730l);
        }
        View view = this.f1729k;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.f1726h = false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f1733o || this.f1734p == null) {
            return;
        }
        l2 l2Var = this.f1732n;
        int d7 = l2Var != null ? l2Var.d() : 0;
        if (d7 > 0) {
            this.f1734p.setBounds(0, 0, getWidth(), d7);
            this.f1734p.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            y(true);
        }
        boolean w6 = w(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            y(true);
        }
        return w6;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        c0.b bVar;
        int i11 = d1.OVER_SCROLL_ALWAYS;
        int d7 = m0.d(this);
        ArrayList arrayList = this.f1719a;
        int size = arrayList.size();
        for (int i12 = 0; i12 < size; i12++) {
            View view = (View) arrayList.get(i12);
            if (view.getVisibility() != 8 && ((bVar = ((e) view.getLayoutParams()).f3007a) == null || !bVar.l(this, view, d7))) {
                u(d7, view);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x01a4, code lost:
    
        if (r0.m(r30, r19, r25, r20, r26) == false) goto L90;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a7  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instructions count: 539
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(0)) {
                    c0.b bVar = eVar.f3007a;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f7, float f8) {
        c0.b bVar;
        int childCount = getChildCount();
        boolean z6 = false;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                if (eVar.a(0) && (bVar = eVar.f3007a) != null) {
                    z6 |= bVar.n(view);
                }
            }
        }
        return z6;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i7, int i8, int[] iArr) {
        d(view, i7, i8, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i7, int i8, int i9, int i10) {
        i(view, i7, i8, i9, i10, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i7) {
        b(view, view2, i7, 0);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        SparseArray sparseArray = savedState.f1738b;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int id = childAt.getId();
            c0.b bVar = r(childAt).f3007a;
            if (id != -1 && bVar != null && (parcelable2 = (Parcelable) sparseArray.get(id)) != null) {
                bVar.r(childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable s6;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int id = childAt.getId();
            c0.b bVar = ((e) childAt.getLayoutParams()).f3007a;
            if (id != -1 && bVar != null && (s6 = bVar.s(childAt)) != null) {
                sparseArray.append(id, s6);
            }
        }
        savedState.f1738b = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i7) {
        return j(view, view2, i7, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        e(0, view);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0012, code lost:
    
        if (r3 != false) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r18.getActionMasked()
            android.view.View r3 = r0.f1728j
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L17
            boolean r3 = r0.w(r1, r4)
            if (r3 == 0) goto L15
            goto L18
        L15:
            r6 = r5
            goto L2a
        L17:
            r3 = r5
        L18:
            android.view.View r6 = r0.f1728j
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            c0.e r6 = (c0.e) r6
            c0.b r6 = r6.f3007a
            if (r6 == 0) goto L15
            android.view.View r7 = r0.f1728j
            boolean r6 = r6.v(r0, r7, r1)
        L2a:
            android.view.View r7 = r0.f1728j
            r8 = 0
            if (r7 != 0) goto L35
            boolean r1 = super.onTouchEvent(r18)
            r6 = r6 | r1
            goto L48
        L35:
            if (r3 == 0) goto L48
            long r11 = android.os.SystemClock.uptimeMillis()
            r13 = 3
            r14 = 0
            r15 = 0
            r16 = 0
            r9 = r11
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16)
            super.onTouchEvent(r8)
        L48:
            if (r8 == 0) goto L4d
            r8.recycle()
        L4d:
            if (r2 == r4) goto L52
            r1 = 3
            if (r2 != r1) goto L55
        L52:
            r0.y(r5)
        L55:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p(View view, Rect rect) {
        ThreadLocal threadLocal = c0.h.f3027a;
        rect.set(0, 0, view.getWidth(), view.getHeight());
        ThreadLocal threadLocal2 = c0.h.f3027a;
        Matrix matrix = (Matrix) threadLocal2.get();
        if (matrix == null) {
            matrix = new Matrix();
            threadLocal2.set(matrix);
        } else {
            matrix.reset();
        }
        c0.h.a(this, view, matrix);
        ThreadLocal threadLocal3 = c0.h.f3028b;
        RectF rectF = (RectF) threadLocal3.get();
        if (rectF == null) {
            rectF = new RectF();
            threadLocal3.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        c0.b bVar = ((e) view.getLayoutParams()).f3007a;
        if (bVar == null || !bVar.q(this, view, rect, z6)) {
            return super.requestChildRectangleOnScreen(view, rect, z6);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z6) {
        super.requestDisallowInterceptTouchEvent(z6);
        if (!z6 || this.f1725g) {
            return;
        }
        y(false);
        this.f1725g = true;
    }

    public final boolean s(View view, int i7, int i8) {
        d dVar = f1718x;
        Rect k7 = k();
        p(view, k7);
        try {
            return k7.contains(i7, i8);
        } finally {
            k7.setEmpty();
            dVar.b(k7);
        }
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z6) {
        super.setFitsSystemWindows(z6);
        B();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f1735q = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.f1734p;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.f1734p = mutate;
            if (mutate != null) {
                if (mutate.isStateful()) {
                    this.f1734p.setState(getDrawableState());
                }
                Drawable drawable3 = this.f1734p;
                int i7 = d1.OVER_SCROLL_ALWAYS;
                j0.c.b(drawable3, m0.d(this));
                this.f1734p.setVisible(getVisibility() == 0, false);
                this.f1734p.setCallback(this);
            }
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
    }

    public void setStatusBarBackgroundColor(int i7) {
        setStatusBarBackground(new ColorDrawable(i7));
    }

    public void setStatusBarBackgroundResource(int i7) {
        Drawable drawable;
        if (i7 != 0) {
            Context context = getContext();
            Object obj = g0.g.f6423a;
            drawable = g0.c.b(context, i7);
        } else {
            drawable = null;
        }
        setStatusBarBackground(drawable);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        boolean z6 = i7 == 0;
        Drawable drawable = this.f1734p;
        if (drawable == null || drawable.isVisible() == z6) {
            return;
        }
        this.f1734p.setVisible(z6, false);
    }

    public final void t(int i7) {
        int i8;
        Rect rect;
        int i9;
        ArrayList arrayList;
        boolean z6;
        boolean z7;
        boolean z8;
        int width;
        int i10;
        int i11;
        int i12;
        int height;
        int i13;
        int i14;
        int i15;
        int i16;
        e eVar;
        ArrayList arrayList2;
        int i17;
        Rect rect2;
        int i18;
        View view;
        d dVar;
        e eVar2;
        int i19;
        boolean z9;
        c0.b bVar;
        int i20 = d1.OVER_SCROLL_ALWAYS;
        int d7 = m0.d(this);
        ArrayList arrayList3 = this.f1719a;
        int size = arrayList3.size();
        Rect k7 = k();
        Rect k8 = k();
        Rect k9 = k();
        int i21 = 0;
        while (true) {
            d dVar2 = f1718x;
            if (i21 >= size) {
                Rect rect3 = k9;
                k7.setEmpty();
                dVar2.b(k7);
                k8.setEmpty();
                dVar2.b(k8);
                rect3.setEmpty();
                dVar2.b(rect3);
                return;
            }
            View view2 = (View) arrayList3.get(i21);
            e eVar3 = (e) view2.getLayoutParams();
            if (i7 != 0 || view2.getVisibility() != 8) {
                int i22 = 0;
                while (i22 < i21) {
                    if (eVar3.f3018l == ((View) arrayList3.get(i22))) {
                        e eVar4 = (e) view2.getLayoutParams();
                        if (eVar4.f3017k != null) {
                            Rect k10 = k();
                            Rect k11 = k();
                            arrayList2 = arrayList3;
                            Rect k12 = k();
                            i16 = i22;
                            p(eVar4.f3017k, k10);
                            n(view2, k11, false);
                            int measuredWidth = view2.getMeasuredWidth();
                            i17 = size;
                            int measuredHeight = view2.getMeasuredHeight();
                            i18 = i21;
                            eVar = eVar3;
                            view = view2;
                            rect2 = k9;
                            dVar = dVar2;
                            q(d7, k10, k12, eVar4, measuredWidth, measuredHeight);
                            if (k12.left == k11.left && k12.top == k11.top) {
                                eVar2 = eVar4;
                                i19 = measuredWidth;
                                z9 = false;
                            } else {
                                eVar2 = eVar4;
                                i19 = measuredWidth;
                                z9 = true;
                            }
                            l(eVar2, k12, i19, measuredHeight);
                            int i23 = k12.left - k11.left;
                            int i24 = k12.top - k11.top;
                            if (i23 != 0) {
                                int i25 = d1.OVER_SCROLL_ALWAYS;
                                view.offsetLeftAndRight(i23);
                            }
                            if (i24 != 0) {
                                int i26 = d1.OVER_SCROLL_ALWAYS;
                                view.offsetTopAndBottom(i24);
                            }
                            if (z9 && (bVar = eVar2.f3007a) != null) {
                                bVar.h(this, view, eVar2.f3017k);
                            }
                            k10.setEmpty();
                            dVar.b(k10);
                            k11.setEmpty();
                            dVar.b(k11);
                            k12.setEmpty();
                            dVar.b(k12);
                            i22 = i16 + 1;
                            dVar2 = dVar;
                            view2 = view;
                            arrayList3 = arrayList2;
                            size = i17;
                            i21 = i18;
                            eVar3 = eVar;
                            k9 = rect2;
                        }
                    }
                    i16 = i22;
                    eVar = eVar3;
                    arrayList2 = arrayList3;
                    i17 = size;
                    rect2 = k9;
                    i18 = i21;
                    view = view2;
                    dVar = dVar2;
                    i22 = i16 + 1;
                    dVar2 = dVar;
                    view2 = view;
                    arrayList3 = arrayList2;
                    size = i17;
                    i21 = i18;
                    eVar3 = eVar;
                    k9 = rect2;
                }
                e eVar5 = eVar3;
                ArrayList arrayList4 = arrayList3;
                int i27 = size;
                Rect rect4 = k9;
                i8 = i21;
                View view3 = view2;
                s.e eVar6 = dVar2;
                n(view3, k8, true);
                if (eVar5.f3013g != 0 && !k8.isEmpty()) {
                    int absoluteGravity = Gravity.getAbsoluteGravity(eVar5.f3013g, d7);
                    int i28 = absoluteGravity & z1.c.INVOKE_DIRECT;
                    if (i28 == 48) {
                        k7.top = Math.max(k7.top, k8.bottom);
                    } else if (i28 == 80) {
                        k7.bottom = Math.max(k7.bottom, getHeight() - k8.top);
                    }
                    int i29 = absoluteGravity & 7;
                    if (i29 == 3) {
                        k7.left = Math.max(k7.left, k8.right);
                    } else if (i29 == 5) {
                        k7.right = Math.max(k7.right, getWidth() - k8.left);
                    }
                }
                if (eVar5.f3014h != 0 && view3.getVisibility() == 0) {
                    int i30 = d1.OVER_SCROLL_ALWAYS;
                    if (o0.c(view3) && view3.getWidth() > 0 && view3.getHeight() > 0) {
                        e eVar7 = (e) view3.getLayoutParams();
                        c0.b bVar2 = eVar7.f3007a;
                        Rect k13 = k();
                        Rect k14 = k();
                        k14.set(view3.getLeft(), view3.getTop(), view3.getRight(), view3.getBottom());
                        if (bVar2 == null || !bVar2.e(view3, k13)) {
                            k13.set(k14);
                        } else if (!k14.contains(k13)) {
                            throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + k13.toShortString() + " | Bounds:" + k14.toShortString());
                        }
                        k14.setEmpty();
                        eVar6.b(k14);
                        if (k13.isEmpty()) {
                            k13.setEmpty();
                            eVar6.b(k13);
                        } else {
                            int absoluteGravity2 = Gravity.getAbsoluteGravity(eVar7.f3014h, d7);
                            if ((absoluteGravity2 & 48) != 48 || (i14 = (k13.top - ((ViewGroup.MarginLayoutParams) eVar7).topMargin) - eVar7.f3016j) >= (i15 = k7.top)) {
                                z7 = false;
                            } else {
                                A(i15 - i14, view3);
                                z7 = true;
                            }
                            if ((absoluteGravity2 & 80) == 80 && (height = ((getHeight() - k13.bottom) - ((ViewGroup.MarginLayoutParams) eVar7).bottomMargin) + eVar7.f3016j) < (i13 = k7.bottom)) {
                                A(height - i13, view3);
                            } else if (!z7) {
                                A(0, view3);
                            }
                            if ((absoluteGravity2 & 3) != 3 || (i11 = (k13.left - ((ViewGroup.MarginLayoutParams) eVar7).leftMargin) - eVar7.f3015i) >= (i12 = k7.left)) {
                                z8 = false;
                            } else {
                                z(i12 - i11, view3);
                                z8 = true;
                            }
                            if ((absoluteGravity2 & 5) == 5 && (width = ((getWidth() - k13.right) - ((ViewGroup.MarginLayoutParams) eVar7).rightMargin) + eVar7.f3015i) < (i10 = k7.right)) {
                                z(width - i10, view3);
                            } else if (!z8) {
                                z(0, view3);
                            }
                            k13.setEmpty();
                            eVar6.b(k13);
                        }
                    }
                }
                if (i7 != 2) {
                    rect = rect4;
                    rect.set(((e) view3.getLayoutParams()).f3023q);
                    if (rect.equals(k8)) {
                        arrayList = arrayList4;
                        i9 = i27;
                    } else {
                        ((e) view3.getLayoutParams()).f3023q.set(k8);
                    }
                } else {
                    rect = rect4;
                }
                int i31 = i8 + 1;
                i9 = i27;
                while (true) {
                    arrayList = arrayList4;
                    if (i31 >= i9) {
                        break;
                    }
                    View view4 = (View) arrayList.get(i31);
                    e eVar8 = (e) view4.getLayoutParams();
                    c0.b bVar3 = eVar8.f3007a;
                    if (bVar3 != null && bVar3.f(view4, view3)) {
                        if (i7 == 0 && eVar8.f3022p) {
                            eVar8.f3022p = false;
                        } else {
                            if (i7 != 2) {
                                z6 = bVar3.h(this, view4, view3);
                            } else {
                                bVar3.i(this, view3);
                                z6 = true;
                            }
                            if (i7 == 1) {
                                eVar8.f3022p = z6;
                            }
                        }
                    }
                    i31++;
                    arrayList4 = arrayList;
                }
            } else {
                arrayList = arrayList3;
                i9 = size;
                rect = k9;
                i8 = i21;
            }
            i21 = i8 + 1;
            k9 = rect;
            size = i9;
            arrayList3 = arrayList;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u(int r13, android.view.View r14) {
        /*
            Method dump skipped, instructions count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.u(int, android.view.View):void");
    }

    public final void v(View view, int i7, int i8, int i9) {
        measureChildWithMargins(view, i7, i8, i9, 0);
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1734p;
    }

    public final boolean w(MotionEvent motionEvent, int i7) {
        boolean z6;
        int actionMasked = motionEvent.getActionMasked();
        ArrayList arrayList = this.f1721c;
        arrayList.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i8 = childCount - 1; i8 >= 0; i8--) {
            arrayList.add(getChildAt(isChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i8) : i8));
        }
        g gVar = f1717w;
        if (gVar != null) {
            Collections.sort(arrayList, gVar);
        }
        int size = arrayList.size();
        MotionEvent motionEvent2 = null;
        boolean z7 = false;
        boolean z8 = false;
        for (int i9 = 0; i9 < size; i9++) {
            View view = (View) arrayList.get(i9);
            e eVar = (e) view.getLayoutParams();
            c0.b bVar = eVar.f3007a;
            if (!(z7 || z8) || actionMasked == 0) {
                if (!z7 && bVar != null) {
                    if (i7 == 0) {
                        z7 = bVar.k(this, view, motionEvent);
                    } else if (i7 == 1) {
                        z7 = bVar.v(this, view, motionEvent);
                    }
                    if (z7) {
                        this.f1728j = view;
                    }
                }
                if (eVar.f3007a == null) {
                    eVar.f3019m = false;
                }
                boolean z9 = eVar.f3019m;
                if (z9) {
                    z6 = true;
                } else {
                    eVar.f3019m = z9;
                    z6 = z9;
                }
                z8 = z6 && !z9;
                if (z6 && !z8) {
                    break;
                }
            } else if (bVar != null) {
                if (motionEvent2 == null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    motionEvent2 = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                }
                if (i7 == 0) {
                    bVar.k(this, view, motionEvent2);
                } else if (i7 == 1) {
                    bVar.v(this, view, motionEvent2);
                }
            }
        }
        arrayList.clear();
        return z7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0106, code lost:
    
        if ((android.view.Gravity.getAbsoluteGravity(r6.f3014h, r10) & r11) == r11) goto L72;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void x() {
        /*
            Method dump skipped, instructions count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.x():void");
    }

    public final void y(boolean z6) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            c0.b bVar = ((e) childAt.getLayoutParams()).f3007a;
            if (bVar != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                if (z6) {
                    bVar.k(this, childAt, obtain);
                } else {
                    bVar.v(this, childAt, obtain);
                }
                obtain.recycle();
            }
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            ((e) getChildAt(i8).getLayoutParams()).f3019m = false;
        }
        this.f1728j = null;
        this.f1725g = false;
    }
}
