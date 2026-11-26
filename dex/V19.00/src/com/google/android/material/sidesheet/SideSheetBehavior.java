package com.google.android.material.sidesheet;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.c;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import c0.b;
import com.google.android.material.sidesheet.SideSheetBehavior;
import h0.n;
import j3.i;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import l6.f;
import p0.d1;
import p0.o0;
import p3.j;
import p3.p;
import q0.g;
import q0.w;
import q2.k;
import q2.l;
import q2.m;
import q3.d;
import u0.e;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SideSheetBehavior<V extends View> extends b implements j3.b {

    /* renamed from: x, reason: collision with root package name */
    public static final int f4116x = k.side_sheet_accessibility_pane_title;

    /* renamed from: y, reason: collision with root package name */
    public static final int f4117y = l.Widget_Material3_SideSheet;

    /* renamed from: a, reason: collision with root package name */
    public q3.a f4118a;

    /* renamed from: b, reason: collision with root package name */
    public final j f4119b;

    /* renamed from: c, reason: collision with root package name */
    public final ColorStateList f4120c;

    /* renamed from: d, reason: collision with root package name */
    public final p f4121d;

    /* renamed from: e, reason: collision with root package name */
    public final w2.b f4122e;

    /* renamed from: f, reason: collision with root package name */
    public final float f4123f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f4124g;

    /* renamed from: h, reason: collision with root package name */
    public int f4125h;

    /* renamed from: i, reason: collision with root package name */
    public e f4126i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f4127j;

    /* renamed from: k, reason: collision with root package name */
    public final float f4128k;

    /* renamed from: l, reason: collision with root package name */
    public int f4129l;

    /* renamed from: m, reason: collision with root package name */
    public int f4130m;

    /* renamed from: n, reason: collision with root package name */
    public int f4131n;

    /* renamed from: o, reason: collision with root package name */
    public int f4132o;

    /* renamed from: p, reason: collision with root package name */
    public WeakReference f4133p;

    /* renamed from: q, reason: collision with root package name */
    public WeakReference f4134q;

    /* renamed from: r, reason: collision with root package name */
    public final int f4135r;

    /* renamed from: s, reason: collision with root package name */
    public VelocityTracker f4136s;

    /* renamed from: t, reason: collision with root package name */
    public i f4137t;

    /* renamed from: u, reason: collision with root package name */
    public int f4138u;

    /* renamed from: v, reason: collision with root package name */
    public final LinkedHashSet f4139v;

    /* renamed from: w, reason: collision with root package name */
    public final d f4140w;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        public final int f4141b;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4141b = parcel.readInt();
        }

        public SavedState(android.view.AbsSavedState absSavedState, SideSheetBehavior sideSheetBehavior) {
            super(absSavedState);
            this.f4141b = sideSheetBehavior.f4125h;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeInt(this.f4141b);
        }
    }

    public SideSheetBehavior() {
        this.f4122e = new w2.b(this);
        this.f4124g = true;
        this.f4125h = 5;
        this.f4128k = 0.1f;
        this.f4135r = -1;
        this.f4139v = new LinkedHashSet();
        this.f4140w = new d(this);
    }

    public SideSheetBehavior(Context context, AttributeSet attributeSet) {
        this.f4122e = new w2.b(this);
        this.f4124g = true;
        this.f4125h = 5;
        this.f4128k = 0.1f;
        this.f4135r = -1;
        this.f4139v = new LinkedHashSet();
        this.f4140w = new d(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.SideSheetBehavior_Layout);
        if (obtainStyledAttributes.hasValue(m.SideSheetBehavior_Layout_backgroundTint)) {
            this.f4120c = f.w(context, obtainStyledAttributes, m.SideSheetBehavior_Layout_backgroundTint);
        }
        if (obtainStyledAttributes.hasValue(m.SideSheetBehavior_Layout_shapeAppearance)) {
            this.f4121d = p.c(context, attributeSet, 0, f4117y).a();
        }
        if (obtainStyledAttributes.hasValue(m.SideSheetBehavior_Layout_coplanarSiblingViewId)) {
            int resourceId = obtainStyledAttributes.getResourceId(m.SideSheetBehavior_Layout_coplanarSiblingViewId, -1);
            this.f4135r = resourceId;
            WeakReference weakReference = this.f4134q;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.f4134q = null;
            WeakReference weakReference2 = this.f4133p;
            if (weakReference2 != null) {
                View view = (View) weakReference2.get();
                if (resourceId != -1) {
                    int i7 = d1.OVER_SCROLL_ALWAYS;
                    if (o0.c(view)) {
                        view.requestLayout();
                    }
                }
            }
        }
        p pVar = this.f4121d;
        if (pVar != null) {
            j jVar = new j(pVar);
            this.f4119b = jVar;
            jVar.l(context);
            ColorStateList colorStateList = this.f4120c;
            if (colorStateList != null) {
                this.f4119b.o(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
                this.f4119b.setTint(typedValue.data);
            }
        }
        this.f4123f = obtainStyledAttributes.getDimension(m.SideSheetBehavior_Layout_android_elevation, -1.0f);
        this.f4124g = obtainStyledAttributes.getBoolean(m.SideSheetBehavior_Layout_behavior_draggable, true);
        obtainStyledAttributes.recycle();
        ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public final void A() {
        View view;
        WeakReference weakReference = this.f4133p;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        d1.l(262144, view);
        d1.i(0, view);
        d1.l(q0.i.ACTION_DISMISS, view);
        d1.i(0, view);
        final int i7 = 5;
        if (this.f4125h != 5) {
            d1.m(view, g.ACTION_DISMISS, null, new w() { // from class: q3.b
                @Override // q0.w
                public final boolean g(View view2) {
                    int i8 = SideSheetBehavior.f4116x;
                    SideSheetBehavior.this.w(i7);
                    return true;
                }
            });
        }
        final int i8 = 3;
        if (this.f4125h != 3) {
            d1.m(view, g.ACTION_EXPAND, null, new w() { // from class: q3.b
                @Override // q0.w
                public final boolean g(View view2) {
                    int i82 = SideSheetBehavior.f4116x;
                    SideSheetBehavior.this.w(i8);
                    return true;
                }
            });
        }
    }

    @Override // j3.b
    public final void a() {
        final ViewGroup.MarginLayoutParams marginLayoutParams;
        final int i7;
        i iVar = this.f4137t;
        if (iVar == null) {
            return;
        }
        c cVar = iVar.f6958f;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
        iVar.f6958f = null;
        int i8 = 5;
        if (cVar == null || Build.VERSION.SDK_INT < 34) {
            w(5);
            return;
        }
        q3.a aVar = this.f4118a;
        if (aVar != null) {
            switch (aVar.f8441d) {
                case 0:
                    i8 = 3;
                    break;
            }
        }
        androidx.appcompat.widget.d dVar = new androidx.appcompat.widget.d(9, this);
        WeakReference weakReference = this.f4134q;
        final View view = weakReference != null ? (View) weakReference.get() : null;
        if (view != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams()) != null) {
            switch (this.f4118a.f8441d) {
                case 0:
                    i7 = marginLayoutParams.leftMargin;
                    break;
                default:
                    i7 = marginLayoutParams.rightMargin;
                    break;
            }
            animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: q3.c
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    a aVar2 = SideSheetBehavior.this.f4118a;
                    int c7 = r2.a.c(i7, valueAnimator.getAnimatedFraction(), 0);
                    int i9 = aVar2.f8441d;
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = marginLayoutParams;
                    switch (i9) {
                        case 0:
                            marginLayoutParams2.leftMargin = c7;
                            break;
                        default:
                            marginLayoutParams2.rightMargin = c7;
                            break;
                    }
                    view.requestLayout();
                }
            };
        }
        iVar.b(cVar, i8, dVar, animatorUpdateListener);
    }

    @Override // j3.b
    public final void b(c cVar) {
        i iVar = this.f4137t;
        if (iVar == null) {
            return;
        }
        iVar.f6958f = cVar;
    }

    @Override // j3.b
    public final void c(c cVar) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        i iVar = this.f4137t;
        if (iVar == null) {
            return;
        }
        q3.a aVar = this.f4118a;
        int i7 = 5;
        if (aVar != null) {
            switch (aVar.f8441d) {
                case 0:
                    i7 = 3;
                    break;
            }
        }
        c cVar2 = iVar.f6958f;
        iVar.f6958f = cVar;
        if (cVar2 != null) {
            iVar.c(i7, cVar.f610c, cVar.f611d == 0);
        }
        WeakReference weakReference = this.f4133p;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        View view = (View) this.f4133p.get();
        WeakReference weakReference2 = this.f4134q;
        View view2 = weakReference2 != null ? (View) weakReference2.get() : null;
        if (view2 == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) == null) {
            return;
        }
        int scaleX = (int) ((view.getScaleX() * this.f4129l) + this.f4132o);
        switch (this.f4118a.f8441d) {
            case 0:
                marginLayoutParams.leftMargin = scaleX;
                break;
            default:
                marginLayoutParams.rightMargin = scaleX;
                break;
        }
        view2.requestLayout();
    }

    @Override // j3.b
    public final void d() {
        i iVar = this.f4137t;
        if (iVar == null) {
            return;
        }
        iVar.a();
    }

    @Override // c0.b
    public final void g(c0.e eVar) {
        this.f4133p = null;
        this.f4126i = null;
        this.f4137t = null;
    }

    @Override // c0.b
    public final void j() {
        this.f4133p = null;
        this.f4126i = null;
        this.f4137t = null;
    }

    @Override // c0.b
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        e eVar;
        VelocityTracker velocityTracker;
        if ((!view.isShown() && d1.e(view) == null) || !this.f4124g) {
            this.f4127j = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 && (velocityTracker = this.f4136s) != null) {
            velocityTracker.recycle();
            this.f4136s = null;
        }
        if (this.f4136s == null) {
            this.f4136s = VelocityTracker.obtain();
        }
        this.f4136s.addMovement(motionEvent);
        if (actionMasked == 0) {
            this.f4138u = (int) motionEvent.getX();
        } else if ((actionMasked == 1 || actionMasked == 3) && this.f4127j) {
            this.f4127j = false;
            return false;
        }
        return (this.f4127j || (eVar = this.f4126i) == null || !eVar.q(motionEvent)) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0093, code lost:
    
        if (r5 != r0) goto L42;
     */
    @Override // c0.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean l(androidx.coordinatorlayout.widget.CoordinatorLayout r10, android.view.View r11, int r12) {
        /*
            Method dump skipped, instructions count: 556
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.sidesheet.SideSheetBehavior.l(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int):boolean");
    }

    @Override // c0.b
    public final boolean m(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i7, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i8, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i9, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height));
        return true;
    }

    @Override // c0.b
    public final void r(View view, Parcelable parcelable) {
        int i7 = ((SavedState) parcelable).f4141b;
        if (i7 == 1 || i7 == 2) {
            i7 = 5;
        }
        this.f4125h = i7;
    }

    @Override // c0.b
    public final Parcelable s(View view) {
        return new SavedState(View.BaseSavedState.EMPTY_STATE, this);
    }

    @Override // c0.b
    public final boolean v(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.f4125h == 1 && actionMasked == 0) {
            return true;
        }
        if (y()) {
            this.f4126i.j(motionEvent);
        }
        if (actionMasked == 0 && (velocityTracker = this.f4136s) != null) {
            velocityTracker.recycle();
            this.f4136s = null;
        }
        if (this.f4136s == null) {
            this.f4136s = VelocityTracker.obtain();
        }
        this.f4136s.addMovement(motionEvent);
        if (y() && actionMasked == 2 && !this.f4127j && y()) {
            float abs = Math.abs(this.f4138u - motionEvent.getX());
            e eVar = this.f4126i;
            if (abs > eVar.f9238b) {
                eVar.b(motionEvent.getPointerId(motionEvent.getActionIndex()), view);
            }
        }
        return !this.f4127j;
    }

    public final void w(int i7) {
        if (i7 == 1 || i7 == 2) {
            throw new IllegalArgumentException(a.a.i(new StringBuilder("STATE_"), i7 == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        WeakReference weakReference = this.f4133p;
        if (weakReference == null || weakReference.get() == null) {
            x(i7);
            return;
        }
        View view = (View) this.f4133p.get();
        n nVar = new n(i7, 1, this);
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            if (o0.b(view)) {
                view.post(nVar);
                return;
            }
        }
        nVar.run();
    }

    public final void x(int i7) {
        View view;
        if (this.f4125h == i7) {
            return;
        }
        this.f4125h = i7;
        WeakReference weakReference = this.f4133p;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        int i8 = this.f4125h == 5 ? 4 : 0;
        if (view.getVisibility() != i8) {
            view.setVisibility(i8);
        }
        Iterator it = this.f4139v.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
        A();
    }

    public final boolean y() {
        return this.f4126i != null && (this.f4124g || this.f4125h == 1);
    }

    public final void z(View view, int i7, boolean z6) {
        int H0;
        if (i7 == 3) {
            H0 = this.f4118a.H0();
        } else {
            if (i7 != 5) {
                throw new IllegalArgumentException(a.a.d("Invalid state to get outer edge offset: ", i7));
            }
            H0 = this.f4118a.I0();
        }
        e eVar = this.f4126i;
        if (eVar == null || (!z6 ? eVar.r(view, H0, view.getTop()) : eVar.p(H0, view.getTop()))) {
            x(i7);
        } else {
            x(2);
            this.f4122e.a(i7);
        }
    }
}
