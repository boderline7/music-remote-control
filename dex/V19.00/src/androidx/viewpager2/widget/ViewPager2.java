package androidx.viewpager2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.m1;
import androidx.recyclerview.widget.r1;
import java.util.ArrayList;
import java.util.List;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class ViewPager2 extends ViewGroup {
    public static final int OFFSCREEN_PAGE_LIMIT_DEFAULT = -1;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;

    /* renamed from: a, reason: collision with root package name */
    public final Rect f2868a;

    /* renamed from: b, reason: collision with root package name */
    public final Rect f2869b;

    /* renamed from: c, reason: collision with root package name */
    public final androidx.viewpager2.adapter.d f2870c;

    /* renamed from: d, reason: collision with root package name */
    public int f2871d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2872e;

    /* renamed from: f, reason: collision with root package name */
    public final e f2873f;

    /* renamed from: g, reason: collision with root package name */
    public i f2874g;

    /* renamed from: h, reason: collision with root package name */
    public int f2875h;

    /* renamed from: i, reason: collision with root package name */
    public Parcelable f2876i;

    /* renamed from: j, reason: collision with root package name */
    public o f2877j;

    /* renamed from: k, reason: collision with root package name */
    public n f2878k;

    /* renamed from: l, reason: collision with root package name */
    public d f2879l;

    /* renamed from: m, reason: collision with root package name */
    public androidx.viewpager2.adapter.d f2880m;

    /* renamed from: n, reason: collision with root package name */
    public androidx.appcompat.app.g f2881n;

    /* renamed from: o, reason: collision with root package name */
    public b f2882o;

    /* renamed from: p, reason: collision with root package name */
    public m1 f2883p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f2884q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f2885r;

    /* renamed from: s, reason: collision with root package name */
    public int f2886s;

    /* renamed from: t, reason: collision with root package name */
    public l f2887t;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new p();

        /* renamed from: a, reason: collision with root package name */
        public int f2888a;

        /* renamed from: b, reason: collision with root package name */
        public int f2889b;

        /* renamed from: c, reason: collision with root package name */
        public Parcelable f2890c;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f2888a);
            parcel.writeInt(this.f2889b);
            parcel.writeParcelable(this.f2890c, i7);
        }
    }

    public ViewPager2(Context context) {
        super(context);
        this.f2868a = new Rect();
        this.f2869b = new Rect();
        this.f2870c = new androidx.viewpager2.adapter.d();
        this.f2872e = false;
        this.f2873f = new e(0, this);
        this.f2875h = -1;
        this.f2883p = null;
        this.f2884q = false;
        this.f2885r = true;
        this.f2886s = -1;
        a(context, null);
    }

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2868a = new Rect();
        this.f2869b = new Rect();
        this.f2870c = new androidx.viewpager2.adapter.d();
        this.f2872e = false;
        this.f2873f = new e(0, this);
        this.f2875h = -1;
        this.f2883p = null;
        this.f2884q = false;
        this.f2885r = true;
        this.f2886s = -1;
        a(context, attributeSet);
    }

    public ViewPager2(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f2868a = new Rect();
        this.f2869b = new Rect();
        this.f2870c = new androidx.viewpager2.adapter.d();
        this.f2872e = false;
        this.f2873f = new e(0, this);
        this.f2875h = -1;
        this.f2883p = null;
        this.f2884q = false;
        this.f2885r = true;
        this.f2886s = -1;
        a(context, attributeSet);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        this.f2887t = new l(this);
        o oVar = new o(this, context);
        this.f2877j = oVar;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        oVar.setId(m0.a());
        this.f2877j.setDescendantFocusability(131072);
        i iVar = new i(this);
        this.f2874g = iVar;
        this.f2877j.setLayoutManager(iVar);
        int i8 = 1;
        this.f2877j.setScrollingTouchSlop(1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, r1.a.ViewPager2);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, r1.a.ViewPager2, attributeSet, obtainStyledAttributes, 0, 0);
        }
        try {
            int i9 = 0;
            setOrientation(obtainStyledAttributes.getInt(r1.a.ViewPager2_android_orientation, 0));
            obtainStyledAttributes.recycle();
            this.f2877j.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            o oVar2 = this.f2877j;
            g gVar = new g();
            if (oVar2.A == null) {
                oVar2.A = new ArrayList();
            }
            oVar2.A.add(gVar);
            d dVar = new d(this);
            this.f2879l = dVar;
            this.f2881n = new androidx.appcompat.app.g(this, dVar, this.f2877j);
            n nVar = new n(this);
            this.f2878k = nVar;
            nVar.a(this.f2877j);
            this.f2877j.h(this.f2879l);
            androidx.viewpager2.adapter.d dVar2 = new androidx.viewpager2.adapter.d();
            this.f2880m = dVar2;
            this.f2879l.f2895a = dVar2;
            f fVar = new f(this, i9);
            f fVar2 = new f(this, i8);
            ((List) dVar2.f2859b).add(fVar);
            ((List) this.f2880m.f2859b).add(fVar2);
            this.f2887t.g(this.f2877j);
            androidx.viewpager2.adapter.d dVar3 = this.f2880m;
            ((List) dVar3.f2859b).add(this.f2870c);
            b bVar = new b();
            this.f2882o = bVar;
            ((List) this.f2880m.f2859b).add(bVar);
            o oVar3 = this.f2877j;
            attachViewToParent(oVar3, 0, oVar3.getLayoutParams());
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void b() {
        h1 adapter;
        if (this.f2875h == -1 || (adapter = getAdapter()) == null) {
            return;
        }
        Parcelable parcelable = this.f2876i;
        if (parcelable != null) {
            if (adapter instanceof androidx.viewpager2.adapter.g) {
                ((androidx.viewpager2.adapter.g) adapter).restoreState(parcelable);
            }
            this.f2876i = null;
        }
        int max = Math.max(0, Math.min(this.f2875h, adapter.getItemCount() - 1));
        this.f2871d = max;
        this.f2875h = -1;
        this.f2877j.e0(max);
        this.f2887t.k();
    }

    public final void c(int i7, boolean z6) {
        r1 r1Var;
        j jVar;
        h1 adapter = getAdapter();
        if (adapter == null) {
            if (this.f2875h != -1) {
                this.f2875h = Math.max(i7, 0);
                return;
            }
            return;
        }
        if (adapter.getItemCount() <= 0) {
            return;
        }
        int min = Math.min(Math.max(i7, 0), adapter.getItemCount() - 1);
        int i8 = this.f2871d;
        if (min == i8 && this.f2879l.f2900f == 0) {
            return;
        }
        if (min == i8 && z6) {
            return;
        }
        double d7 = i8;
        this.f2871d = min;
        this.f2887t.k();
        d dVar = this.f2879l;
        if (dVar.f2900f != 0) {
            dVar.e();
            c cVar = dVar.f2901g;
            d7 = cVar.f2892a + cVar.f2893b;
        }
        d dVar2 = this.f2879l;
        dVar2.getClass();
        dVar2.f2899e = z6 ? 2 : 3;
        dVar2.f2907m = false;
        boolean z7 = dVar2.f2903i != min;
        dVar2.f2903i = min;
        dVar2.c(2);
        if (z7 && (jVar = dVar2.f2895a) != null) {
            jVar.onPageSelected(min);
        }
        if (!z6) {
            this.f2877j.e0(min);
            return;
        }
        double d8 = min;
        if (Math.abs(d8 - d7) > 3.0d) {
            this.f2877j.e0(d8 > d7 ? min - 3 : min + 3);
            o oVar = this.f2877j;
            oVar.post(new q(min, oVar));
        } else {
            o oVar2 = this.f2877j;
            if (oVar2.f2339v || (r1Var = oVar2.f2321m) == null) {
                return;
            }
            r1Var.C0(oVar2, min);
        }
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i7) {
        return this.f2877j.canScrollHorizontally(i7);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i7) {
        return this.f2877j.canScrollVertically(i7);
    }

    public final void d() {
        n nVar = this.f2878k;
        if (nVar == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View e7 = nVar.e(this.f2874g);
        if (e7 == null) {
            return;
        }
        this.f2874g.getClass();
        int K = r1.K(e7);
        if (K != this.f2871d && getScrollState() == 0) {
            this.f2880m.onPageSelected(K);
        }
        this.f2872e = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        Parcelable parcelable = (Parcelable) sparseArray.get(getId());
        if (parcelable instanceof SavedState) {
            int i7 = ((SavedState) parcelable).f2888a;
            sparseArray.put(this.f2877j.getId(), sparseArray.get(i7));
            sparseArray.remove(i7);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        this.f2887t.getClass();
        this.f2887t.getClass();
        return "androidx.viewpager.widget.ViewPager";
    }

    public h1 getAdapter() {
        return this.f2877j.getAdapter();
    }

    public int getCurrentItem() {
        return this.f2871d;
    }

    public int getItemDecorationCount() {
        return this.f2877j.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.f2886s;
    }

    public int getOrientation() {
        return this.f2874g.f2280p;
    }

    public int getPageSize() {
        int height;
        int paddingBottom;
        o oVar = this.f2877j;
        if (getOrientation() == 0) {
            height = oVar.getWidth() - oVar.getPaddingLeft();
            paddingBottom = oVar.getPaddingRight();
        } else {
            height = oVar.getHeight() - oVar.getPaddingTop();
            paddingBottom = oVar.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.f2879l.f2900f;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.f2887t.h(accessibilityNodeInfo);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int measuredWidth = this.f2877j.getMeasuredWidth();
        int measuredHeight = this.f2877j.getMeasuredHeight();
        int paddingLeft = getPaddingLeft();
        Rect rect = this.f2868a;
        rect.left = paddingLeft;
        rect.right = (i9 - i7) - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = (i10 - i8) - getPaddingBottom();
        Rect rect2 = this.f2869b;
        Gravity.apply(8388659, measuredWidth, measuredHeight, rect, rect2);
        this.f2877j.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
        if (this.f2872e) {
            d();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        measureChild(this.f2877j, i7, i8);
        int measuredWidth = this.f2877j.getMeasuredWidth();
        int measuredHeight = this.f2877j.getMeasuredHeight();
        int measuredState = this.f2877j.getMeasuredState();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + measuredHeight;
        setMeasuredDimension(View.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i7, measuredState), View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i8, measuredState << 16));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f2875h = savedState.f2889b;
        this.f2876i = savedState.f2890c;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f2888a = this.f2877j.getId();
        int i7 = this.f2875h;
        if (i7 == -1) {
            i7 = this.f2871d;
        }
        savedState.f2889b = i7;
        Parcelable parcelable = this.f2876i;
        if (parcelable != null) {
            savedState.f2890c = parcelable;
        } else {
            h1 adapter = this.f2877j.getAdapter();
            if (adapter instanceof androidx.viewpager2.adapter.g) {
                savedState.f2890c = ((androidx.viewpager2.adapter.g) adapter).saveState();
            }
        }
        return savedState;
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        throw new IllegalStateException("ViewPager2 does not support direct child views");
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i7, Bundle bundle) {
        this.f2887t.getClass();
        if (i7 != 8192 && i7 != 4096) {
            return super.performAccessibilityAction(i7, bundle);
        }
        this.f2887t.i(i7, bundle);
        return true;
    }

    public void setAdapter(h1 h1Var) {
        h1 adapter = this.f2877j.getAdapter();
        this.f2887t.f(adapter);
        e eVar = this.f2873f;
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver(eVar);
        }
        this.f2877j.setAdapter(h1Var);
        this.f2871d = 0;
        b();
        this.f2887t.e(h1Var);
        if (h1Var != null) {
            h1Var.registerAdapterDataObserver(eVar);
        }
    }

    public void setCurrentItem(int i7) {
        setCurrentItem(i7, true);
    }

    public void setCurrentItem(int i7, boolean z6) {
        if (((d) this.f2881n.f715c).f2907m) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        c(i7, z6);
    }

    @Override // android.view.View
    public void setLayoutDirection(int i7) {
        super.setLayoutDirection(i7);
        this.f2887t.k();
    }

    public void setOffscreenPageLimit(int i7) {
        if (i7 < 1 && i7 != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.f2886s = i7;
        this.f2877j.requestLayout();
    }

    public void setOrientation(int i7) {
        this.f2874g.g1(i7);
        this.f2887t.k();
    }

    public void setPageTransformer(m mVar) {
        if (mVar != null) {
            if (!this.f2884q) {
                this.f2883p = this.f2877j.getItemAnimator();
                this.f2884q = true;
            }
            this.f2877j.setItemAnimator(null);
        } else if (this.f2884q) {
            this.f2877j.setItemAnimator(this.f2883p);
            this.f2883p = null;
            this.f2884q = false;
        }
        this.f2882o.getClass();
        if (mVar == null) {
            return;
        }
        this.f2882o.getClass();
        this.f2882o.getClass();
    }

    public void setUserInputEnabled(boolean z6) {
        this.f2885r = z6;
        this.f2887t.k();
    }
}
