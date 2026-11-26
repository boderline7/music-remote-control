package com.google.android.material.bottomsheet;

import android.R;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.app.n;
import androidx.appcompat.app.u0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import c0.b;
import com.google.android.material.internal.k0;
import com.yalantis.ucrop.view.CropImageView;
import j3.g;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import l6.f;
import p0.d1;
import p0.l0;
import p0.o0;
import p0.r0;
import p0.t1;
import p0.v1;
import p3.j;
import p3.p;
import q0.i;
import q2.c;
import q2.k;
import q2.l;
import q2.m;
import s2.d;
import u0.e;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class BottomSheetBehavior<V extends View> extends b implements j3.b {
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int SAVE_ALL = -1;
    public static final int SAVE_FIT_TO_CONTENTS = 2;
    public static final int SAVE_HIDEABLE = 4;
    public static final int SAVE_NONE = 0;
    public static final int SAVE_PEEK_HEIGHT = 1;
    public static final int SAVE_SKIP_COLLAPSED = 8;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;

    /* renamed from: g0, reason: collision with root package name */
    public static final int f3379g0 = l.Widget_Design_BottomSheet_Modal;
    public final w2.b A;
    public final ValueAnimator B;
    public final int C;
    public int D;
    public int E;
    public final float F;
    public int G;
    public final float H;
    public boolean I;
    public boolean J;
    public final boolean K;
    public int L;
    public e M;
    public boolean N;
    public int O;
    public boolean P;
    public final float Q;
    public int R;
    public int S;
    public int T;
    public WeakReference U;
    public WeakReference V;
    public WeakReference W;
    public final ArrayList X;
    public VelocityTracker Y;
    public g Z;

    /* renamed from: a, reason: collision with root package name */
    public final int f3380a;

    /* renamed from: a0, reason: collision with root package name */
    public int f3381a0;

    /* renamed from: b, reason: collision with root package name */
    public boolean f3382b;

    /* renamed from: b0, reason: collision with root package name */
    public int f3383b0;

    /* renamed from: c, reason: collision with root package name */
    public final float f3384c;

    /* renamed from: c0, reason: collision with root package name */
    public boolean f3385c0;

    /* renamed from: d, reason: collision with root package name */
    public final int f3386d;

    /* renamed from: d0, reason: collision with root package name */
    public HashMap f3387d0;

    /* renamed from: e, reason: collision with root package name */
    public int f3388e;

    /* renamed from: e0, reason: collision with root package name */
    public final SparseIntArray f3389e0;

    /* renamed from: f, reason: collision with root package name */
    public boolean f3390f;

    /* renamed from: f0, reason: collision with root package name */
    public final w2.a f3391f0;

    /* renamed from: g, reason: collision with root package name */
    public int f3392g;

    /* renamed from: h, reason: collision with root package name */
    public final int f3393h;

    /* renamed from: i, reason: collision with root package name */
    public final j f3394i;

    /* renamed from: j, reason: collision with root package name */
    public final ColorStateList f3395j;

    /* renamed from: k, reason: collision with root package name */
    public final int f3396k;

    /* renamed from: l, reason: collision with root package name */
    public final int f3397l;

    /* renamed from: m, reason: collision with root package name */
    public int f3398m;

    /* renamed from: n, reason: collision with root package name */
    public final boolean f3399n;

    /* renamed from: o, reason: collision with root package name */
    public final boolean f3400o;

    /* renamed from: p, reason: collision with root package name */
    public final boolean f3401p;

    /* renamed from: q, reason: collision with root package name */
    public final boolean f3402q;

    /* renamed from: r, reason: collision with root package name */
    public final boolean f3403r;

    /* renamed from: s, reason: collision with root package name */
    public final boolean f3404s;

    /* renamed from: t, reason: collision with root package name */
    public final boolean f3405t;

    /* renamed from: u, reason: collision with root package name */
    public final boolean f3406u;

    /* renamed from: v, reason: collision with root package name */
    public int f3407v;

    /* renamed from: w, reason: collision with root package name */
    public int f3408w;

    /* renamed from: x, reason: collision with root package name */
    public final boolean f3409x;

    /* renamed from: y, reason: collision with root package name */
    public final p f3410y;

    /* renamed from: z, reason: collision with root package name */
    public boolean f3411z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        public final int f3412b;

        /* renamed from: c, reason: collision with root package name */
        public final int f3413c;

        /* renamed from: d, reason: collision with root package name */
        public final boolean f3414d;

        /* renamed from: e, reason: collision with root package name */
        public final boolean f3415e;

        /* renamed from: f, reason: collision with root package name */
        public final boolean f3416f;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3412b = parcel.readInt();
            this.f3413c = parcel.readInt();
            this.f3414d = parcel.readInt() == 1;
            this.f3415e = parcel.readInt() == 1;
            this.f3416f = parcel.readInt() == 1;
        }

        public SavedState(android.view.AbsSavedState absSavedState, BottomSheetBehavior bottomSheetBehavior) {
            super(absSavedState);
            this.f3412b = bottomSheetBehavior.L;
            this.f3413c = bottomSheetBehavior.f3388e;
            this.f3414d = bottomSheetBehavior.f3382b;
            this.f3415e = bottomSheetBehavior.I;
            this.f3416f = bottomSheetBehavior.J;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeInt(this.f3412b);
            parcel.writeInt(this.f3413c);
            parcel.writeInt(this.f3414d ? 1 : 0);
            parcel.writeInt(this.f3415e ? 1 : 0);
            parcel.writeInt(this.f3416f ? 1 : 0);
        }
    }

    public BottomSheetBehavior() {
        this.f3380a = 0;
        this.f3382b = true;
        this.f3396k = -1;
        this.f3397l = -1;
        this.A = new w2.b(this, 0);
        this.F = 0.5f;
        this.H = -1.0f;
        this.K = true;
        this.L = 4;
        this.Q = 0.1f;
        this.X = new ArrayList();
        this.f3383b0 = -1;
        this.f3389e0 = new SparseIntArray();
        this.f3391f0 = new w2.a(this);
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i7;
        this.f3380a = 0;
        int i8 = 1;
        this.f3382b = true;
        this.f3396k = -1;
        this.f3397l = -1;
        this.A = new w2.b(this, 0);
        this.F = 0.5f;
        this.H = -1.0f;
        this.K = true;
        this.L = 4;
        this.Q = 0.1f;
        this.X = new ArrayList();
        this.f3383b0 = -1;
        this.f3389e0 = new SparseIntArray();
        this.f3391f0 = new w2.a(this);
        this.f3393h = context.getResources().getDimensionPixelSize(q2.e.mtrl_min_touch_target_size);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.BottomSheetBehavior_Layout);
        if (obtainStyledAttributes.hasValue(m.BottomSheetBehavior_Layout_backgroundTint)) {
            this.f3395j = f.w(context, obtainStyledAttributes, m.BottomSheetBehavior_Layout_backgroundTint);
        }
        if (obtainStyledAttributes.hasValue(m.BottomSheetBehavior_Layout_shapeAppearance)) {
            this.f3410y = p.c(context, attributeSet, c.bottomSheetStyle, f3379g0).a();
        }
        p pVar = this.f3410y;
        if (pVar != null) {
            j jVar = new j(pVar);
            this.f3394i = jVar;
            jVar.l(context);
            ColorStateList colorStateList = this.f3395j;
            if (colorStateList != null) {
                this.f3394i.o(colorStateList);
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
                this.f3394i.setTint(typedValue.data);
            }
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(x(), 1.0f);
        this.B = ofFloat;
        ofFloat.setDuration(500L);
        this.B.addUpdateListener(new d(i8, this));
        this.H = obtainStyledAttributes.getDimension(m.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        if (obtainStyledAttributes.hasValue(m.BottomSheetBehavior_Layout_android_maxWidth)) {
            this.f3396k = obtainStyledAttributes.getDimensionPixelSize(m.BottomSheetBehavior_Layout_android_maxWidth, -1);
        }
        if (obtainStyledAttributes.hasValue(m.BottomSheetBehavior_Layout_android_maxHeight)) {
            this.f3397l = obtainStyledAttributes.getDimensionPixelSize(m.BottomSheetBehavior_Layout_android_maxHeight, -1);
        }
        TypedValue peekValue = obtainStyledAttributes.peekValue(m.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (peekValue == null || (i7 = peekValue.data) != -1) {
            H(obtainStyledAttributes.getDimensionPixelSize(m.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        } else {
            H(i7);
        }
        boolean z6 = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_behavior_hideable, false);
        if (this.I != z6) {
            this.I = z6;
            if (!z6 && this.L == 5) {
                I(4);
            }
            N();
        }
        this.f3399n = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false);
        boolean z7 = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_behavior_fitToContents, true);
        if (this.f3382b != z7) {
            this.f3382b = z7;
            if (this.U != null) {
                w();
            }
            J((this.f3382b && this.L == 6) ? 3 : this.L);
            O(this.L, true);
            N();
        }
        this.J = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_behavior_skipCollapsed, false);
        this.K = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_behavior_draggable, true);
        this.f3380a = obtainStyledAttributes.getInt(m.BottomSheetBehavior_Layout_behavior_saveFlags, 0);
        float f7 = obtainStyledAttributes.getFloat(m.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f);
        if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO || f7 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.F = f7;
        if (this.U != null) {
            this.E = (int) ((1.0f - f7) * this.T);
        }
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(m.BottomSheetBehavior_Layout_behavior_expandedOffset);
        if (peekValue2 == null || peekValue2.type != 16) {
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(m.BottomSheetBehavior_Layout_behavior_expandedOffset, 0);
            if (dimensionPixelOffset < 0) {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
            this.C = dimensionPixelOffset;
            O(this.L, true);
        } else {
            int i9 = peekValue2.data;
            if (i9 < 0) {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
            this.C = i9;
            O(this.L, true);
        }
        this.f3386d = obtainStyledAttributes.getInt(m.BottomSheetBehavior_Layout_behavior_significantVelocityThreshold, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
        this.f3400o = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.f3401p = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.f3402q = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.f3403r = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        this.f3404s = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_marginLeftSystemWindowInsets, false);
        this.f3405t = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_marginRightSystemWindowInsets, false);
        this.f3406u = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_marginTopSystemWindowInsets, false);
        this.f3409x = obtainStyledAttributes.getBoolean(m.BottomSheetBehavior_Layout_shouldRemoveExpandedCorners, true);
        obtainStyledAttributes.recycle();
        this.f3384c = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public static View B(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        if (r0.p(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View B = B(viewGroup.getChildAt(i8));
                if (B != null) {
                    return B;
                }
            }
        }
        return null;
    }

    public static int C(int i7, int i8, int i9, int i10) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i7, i8, i10);
        if (i9 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i9), v.c.EXACTLY);
        }
        if (size != 0) {
            i9 = Math.min(size, i9);
        }
        return View.MeasureSpec.makeMeasureSpec(i9, Integer.MIN_VALUE);
    }

    public final void A(int i7) {
        if (((View) this.U.get()) != null) {
            ArrayList arrayList = this.X;
            if (arrayList.isEmpty()) {
                return;
            }
            int i8 = this.G;
            if (i7 <= i8 && i8 != D()) {
                D();
            }
            for (int i9 = 0; i9 < arrayList.size(); i9++) {
                ((w2.c) arrayList.get(i9)).getClass();
            }
        }
    }

    public final int D() {
        if (this.f3382b) {
            return this.D;
        }
        return Math.max(this.C, this.f3403r ? 0 : this.f3408w);
    }

    public final int E(int i7) {
        if (i7 == 3) {
            return D();
        }
        if (i7 == 4) {
            return this.G;
        }
        if (i7 == 5) {
            return this.T;
        }
        if (i7 == 6) {
            return this.E;
        }
        throw new IllegalArgumentException(a.a.d("Invalid state to get top offset: ", i7));
    }

    public final boolean F() {
        WeakReference weakReference = this.U;
        if (weakReference == null || weakReference.get() == null) {
            return false;
        }
        int[] iArr = new int[2];
        ((View) this.U.get()).getLocationOnScreen(iArr);
        return iArr[1] == 0;
    }

    public final void G(View view) {
        WeakReference weakReference;
        if (view != null || (weakReference = this.V) == null) {
            this.V = new WeakReference(view);
            M(1, view);
        } else {
            z(1, (View) weakReference.get());
            this.V = null;
        }
    }

    public final void H(int i7) {
        if (i7 == -1) {
            if (this.f3390f) {
                return;
            } else {
                this.f3390f = true;
            }
        } else {
            if (!this.f3390f && this.f3388e == i7) {
                return;
            }
            this.f3390f = false;
            this.f3388e = Math.max(0, i7);
        }
        Q();
    }

    public final void I(int i7) {
        if (i7 == 1 || i7 == 2) {
            throw new IllegalArgumentException(a.a.i(new StringBuilder("STATE_"), i7 == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        if (this.I || i7 != 5) {
            int i8 = (i7 == 6 && this.f3382b && E(i7) <= this.D) ? 3 : i7;
            WeakReference weakReference = this.U;
            if (weakReference == null || weakReference.get() == null) {
                J(i7);
                return;
            }
            View view = (View) this.U.get();
            b.d dVar = new b.d(this, view, i8, 8);
            ViewParent parent = view.getParent();
            if (parent != null && parent.isLayoutRequested()) {
                int i9 = d1.OVER_SCROLL_ALWAYS;
                if (o0.b(view)) {
                    view.post(dVar);
                    return;
                }
            }
            dVar.run();
        }
    }

    public final void J(int i7) {
        if (this.L == i7) {
            return;
        }
        this.L = i7;
        if (i7 != 4 && i7 != 3 && i7 != 6) {
            boolean z6 = this.I;
        }
        WeakReference weakReference = this.U;
        if (weakReference == null || ((View) weakReference.get()) == null) {
            return;
        }
        int i8 = 0;
        if (i7 == 3) {
            P(true);
        } else if (i7 == 6 || i7 == 5 || i7 == 4) {
            P(false);
        }
        O(i7, true);
        while (true) {
            ArrayList arrayList = this.X;
            if (i8 >= arrayList.size()) {
                N();
                return;
            }
            w2.c cVar = (w2.c) arrayList.get(i8);
            cVar.getClass();
            int i9 = BottomSheetDragHandleView.f3417m;
            cVar.f9462a.d(i7);
            i8++;
        }
    }

    public final boolean K(View view, float f7) {
        if (this.J) {
            return true;
        }
        if (view.getTop() < this.G) {
            return false;
        }
        return Math.abs(((f7 * this.Q) + ((float) view.getTop())) - ((float) this.G)) / ((float) y()) > 0.5f;
    }

    public final void L(View view, int i7, boolean z6) {
        int E = E(i7);
        e eVar = this.M;
        if (eVar == null || (!z6 ? eVar.r(view, view.getLeft(), E) : eVar.p(view.getLeft(), E))) {
            J(i7);
            return;
        }
        J(2);
        O(i7, true);
        this.A.a(i7);
    }

    public final void M(int i7, View view) {
        int i8;
        if (view == null) {
            return;
        }
        z(i7, view);
        if (!this.f3382b && this.L != 6) {
            String string = view.getResources().getString(k.bottomsheet_action_expand_halfway);
            n nVar = new n(this, 6);
            ArrayList f7 = d1.f(view);
            int i9 = 0;
            while (true) {
                if (i9 >= f7.size()) {
                    int i10 = 0;
                    int i11 = -1;
                    while (true) {
                        int[] iArr = d1.f8113d;
                        if (i10 >= iArr.length || i11 != -1) {
                            break;
                        }
                        int i12 = iArr[i10];
                        boolean z6 = true;
                        for (int i13 = 0; i13 < f7.size(); i13++) {
                            z6 &= ((q0.g) f7.get(i13)).a() != i12;
                        }
                        if (z6) {
                            i11 = i12;
                        }
                        i10++;
                    }
                    i8 = i11;
                } else {
                    if (TextUtils.equals(string, ((AccessibilityNodeInfo.AccessibilityAction) ((q0.g) f7.get(i9)).f8421a).getLabel())) {
                        i8 = ((q0.g) f7.get(i9)).a();
                        break;
                    }
                    i9++;
                }
            }
            if (i8 != -1) {
                q0.g gVar = new q0.g(null, i8, string, nVar, null);
                View.AccessibilityDelegate d7 = d1.d(view);
                p0.c cVar = d7 == null ? null : d7 instanceof p0.a ? ((p0.a) d7).f8091a : new p0.c(d7);
                if (cVar == null) {
                    cVar = new p0.c();
                }
                d1.o(view, cVar);
                d1.l(gVar.a(), view);
                d1.f(view).add(gVar);
                d1.i(0, view);
            }
            this.f3389e0.put(i7, i8);
        }
        if (this.I && this.L != 5) {
            d1.m(view, q0.g.ACTION_DISMISS, null, new n(this, 5));
        }
        int i14 = this.L;
        if (i14 == 3) {
            d1.m(view, q0.g.ACTION_COLLAPSE, null, new n(this, this.f3382b ? 4 : 6));
            return;
        }
        if (i14 == 4) {
            d1.m(view, q0.g.ACTION_EXPAND, null, new n(this, this.f3382b ? 3 : 6));
        } else {
            if (i14 != 6) {
                return;
            }
            d1.m(view, q0.g.ACTION_COLLAPSE, null, new n(this, 4));
            d1.m(view, q0.g.ACTION_EXPAND, null, new n(this, 3));
        }
    }

    public final void N() {
        WeakReference weakReference = this.U;
        if (weakReference != null) {
            M(0, (View) weakReference.get());
        }
        WeakReference weakReference2 = this.V;
        if (weakReference2 != null) {
            M(1, (View) weakReference2.get());
        }
    }

    public final void O(int i7, boolean z6) {
        ValueAnimator valueAnimator;
        if (i7 == 2) {
            return;
        }
        boolean z7 = this.L == 3 && (this.f3409x || F());
        if (this.f3411z == z7 || this.f3394i == null) {
            return;
        }
        this.f3411z = z7;
        if (!z6 || (valueAnimator = this.B) == null) {
            ValueAnimator valueAnimator2 = this.B;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.B.cancel();
            }
            this.f3394i.p(this.f3411z ? x() : 1.0f);
            return;
        }
        if (valueAnimator.isRunning()) {
            this.B.reverse();
        } else {
            this.B.setFloatValues(this.f3394i.f8263a.f8250j, z7 ? x() : 1.0f);
            this.B.start();
        }
    }

    public final void P(boolean z6) {
        WeakReference weakReference = this.U;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = ((View) weakReference.get()).getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z6) {
                if (this.f3387d0 != null) {
                    return;
                } else {
                    this.f3387d0 = new HashMap(childCount);
                }
            }
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = coordinatorLayout.getChildAt(i7);
                if (childAt != this.U.get() && z6) {
                    this.f3387d0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                }
            }
            if (z6) {
                return;
            }
            this.f3387d0 = null;
        }
    }

    public final void Q() {
        View view;
        if (this.U != null) {
            w();
            if (this.L != 4 || (view = (View) this.U.get()) == null) {
                return;
            }
            view.requestLayout();
        }
    }

    @Override // j3.b
    public final void a() {
        g gVar = this.Z;
        if (gVar == null) {
            return;
        }
        androidx.activity.c cVar = gVar.f6958f;
        gVar.f6958f = null;
        if (cVar == null || Build.VERSION.SDK_INT < 34) {
            I(this.I ? 5 : 4);
            return;
        }
        boolean z6 = this.I;
        int i7 = gVar.f6956d;
        int i8 = gVar.f6955c;
        float f7 = cVar.f610c;
        if (!z6) {
            AnimatorSet a7 = gVar.a();
            a7.setDuration(r2.a.c(i8, f7, i7));
            a7.start();
            I(4);
            return;
        }
        androidx.appcompat.widget.d dVar = new androidx.appcompat.widget.d(r2, this);
        View view = gVar.f6954b;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, view.getScaleY() * view.getHeight());
        ofFloat.setInterpolator(new c1.b());
        ofFloat.setDuration(r2.a.c(i8, f7, i7));
        ofFloat.addListener(new androidx.appcompat.widget.d(7, gVar));
        ofFloat.addListener(dVar);
        ofFloat.start();
    }

    @Override // j3.b
    public final void b(androidx.activity.c cVar) {
        g gVar = this.Z;
        if (gVar == null) {
            return;
        }
        gVar.f6958f = cVar;
    }

    @Override // j3.b
    public final void c(androidx.activity.c cVar) {
        g gVar = this.Z;
        if (gVar == null) {
            return;
        }
        androidx.activity.c cVar2 = gVar.f6958f;
        gVar.f6958f = cVar;
        if (cVar2 == null) {
            return;
        }
        gVar.b(cVar.f610c);
    }

    @Override // j3.b
    public final void d() {
        g gVar = this.Z;
        if (gVar == null) {
            return;
        }
        androidx.activity.c cVar = gVar.f6958f;
        gVar.f6958f = null;
        if (cVar == null) {
            return;
        }
        AnimatorSet a7 = gVar.a();
        a7.setDuration(gVar.f6957e);
        a7.start();
    }

    @Override // c0.b
    public final void g(c0.e eVar) {
        this.U = null;
        this.M = null;
        this.Z = null;
    }

    @Override // c0.b
    public final void j() {
        this.U = null;
        this.M = null;
        this.Z = null;
    }

    @Override // c0.b
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        int i7;
        e eVar;
        if (!view.isShown() || !this.K) {
            this.N = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f3381a0 = -1;
            this.f3383b0 = -1;
            VelocityTracker velocityTracker = this.Y;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.Y = null;
            }
        }
        if (this.Y == null) {
            this.Y = VelocityTracker.obtain();
        }
        this.Y.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x6 = (int) motionEvent.getX();
            this.f3383b0 = (int) motionEvent.getY();
            if (this.L != 2) {
                WeakReference weakReference = this.W;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && coordinatorLayout.s(view2, x6, this.f3383b0)) {
                    this.f3381a0 = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.f3385c0 = true;
                }
            }
            this.N = this.f3381a0 == -1 && !coordinatorLayout.s(view, x6, this.f3383b0);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f3385c0 = false;
            this.f3381a0 = -1;
            if (this.N) {
                this.N = false;
                return false;
            }
        }
        if (!this.N && (eVar = this.M) != null && eVar.q(motionEvent)) {
            return true;
        }
        WeakReference weakReference2 = this.W;
        View view3 = weakReference2 != null ? (View) weakReference2.get() : null;
        return (actionMasked != 2 || view3 == null || this.N || this.L == 1 || coordinatorLayout.s(view3, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.M == null || (i7 = this.f3383b0) == -1 || Math.abs(((float) i7) - motionEvent.getY()) <= ((float) this.M.f9238b)) ? false : true;
    }

    @Override // c0.b
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
        int i8 = d1.OVER_SCROLL_ALWAYS;
        if (l0.b(coordinatorLayout) && !l0.b(view)) {
            view.setFitsSystemWindows(true);
        }
        int i9 = 0;
        if (this.U == null) {
            this.f3392g = coordinatorLayout.getResources().getDimensionPixelSize(q2.e.design_bottom_sheet_peek_height_min);
            int i10 = Build.VERSION.SDK_INT;
            boolean z6 = (i10 < 29 || this.f3399n || this.f3390f) ? false : true;
            if (this.f3400o || this.f3401p || this.f3402q || this.f3404s || this.f3405t || this.f3406u || z6) {
                k0.b(view, new u0(this, z6));
            }
            w2.d dVar = new w2.d(view);
            if (i10 >= 30) {
                view.setWindowInsetsAnimationCallback(new v1(dVar));
            } else {
                Object tag = view.getTag(d0.c.tag_on_apply_window_listener);
                View.OnApplyWindowInsetsListener t1Var = new t1(view, dVar);
                view.setTag(d0.c.tag_window_insets_animation_callback, t1Var);
                if (tag == null) {
                    view.setOnApplyWindowInsetsListener(t1Var);
                }
            }
            this.U = new WeakReference(view);
            this.Z = new g(view);
            j jVar = this.f3394i;
            if (jVar != null) {
                l0.q(view, jVar);
                j jVar2 = this.f3394i;
                float f7 = this.H;
                if (f7 == -1.0f) {
                    f7 = r0.i(view);
                }
                jVar2.n(f7);
            } else {
                ColorStateList colorStateList = this.f3395j;
                if (colorStateList != null) {
                    r0.q(view, colorStateList);
                }
            }
            N();
            if (l0.c(view) == 0) {
                l0.s(view, 1);
            }
        }
        if (this.M == null) {
            this.M = new e(coordinatorLayout.getContext(), coordinatorLayout, this.f3391f0);
        }
        int top = view.getTop();
        coordinatorLayout.u(i7, view);
        this.S = coordinatorLayout.getWidth();
        this.T = coordinatorLayout.getHeight();
        int height = view.getHeight();
        this.R = height;
        int i11 = this.T;
        int i12 = i11 - height;
        int i13 = this.f3408w;
        if (i12 < i13) {
            if (this.f3403r) {
                int i14 = this.f3397l;
                if (i14 != -1) {
                    i11 = Math.min(i11, i14);
                }
                this.R = i11;
            } else {
                int i15 = i11 - i13;
                int i16 = this.f3397l;
                if (i16 != -1) {
                    i15 = Math.min(i15, i16);
                }
                this.R = i15;
            }
        }
        this.D = Math.max(0, this.T - this.R);
        this.E = (int) ((1.0f - this.F) * this.T);
        w();
        int i17 = this.L;
        if (i17 == 3) {
            view.offsetTopAndBottom(D());
        } else if (i17 == 6) {
            view.offsetTopAndBottom(this.E);
        } else if (this.I && i17 == 5) {
            view.offsetTopAndBottom(this.T);
        } else if (i17 == 4) {
            view.offsetTopAndBottom(this.G);
        } else if (i17 == 1 || i17 == 2) {
            view.offsetTopAndBottom(top - view.getTop());
        }
        O(this.L, false);
        this.W = new WeakReference(B(view));
        while (true) {
            ArrayList arrayList = this.X;
            if (i9 >= arrayList.size()) {
                return true;
            }
            ((w2.c) arrayList.get(i9)).getClass();
            i9++;
        }
    }

    @Override // c0.b
    public final boolean m(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(C(i7, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i8, this.f3396k, marginLayoutParams.width), C(i9, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, this.f3397l, marginLayoutParams.height));
        return true;
    }

    @Override // c0.b
    public final boolean n(View view) {
        WeakReference weakReference = this.W;
        return (weakReference == null || view != weakReference.get() || this.L == 3) ? false : true;
    }

    @Override // c0.b
    public final void o(CoordinatorLayout coordinatorLayout, View view, View view2, int i7, int i8, int[] iArr, int i9) {
        if (i9 == 1) {
            return;
        }
        WeakReference weakReference = this.W;
        if (view2 != (weakReference != null ? (View) weakReference.get() : null)) {
            return;
        }
        int top = view.getTop();
        int i10 = top - i8;
        if (i8 > 0) {
            if (i10 < D()) {
                int D = top - D();
                iArr[1] = D;
                int i11 = -D;
                int i12 = d1.OVER_SCROLL_ALWAYS;
                view.offsetTopAndBottom(i11);
                J(3);
            } else {
                if (!this.K) {
                    return;
                }
                iArr[1] = i8;
                int i13 = d1.OVER_SCROLL_ALWAYS;
                view.offsetTopAndBottom(-i8);
                J(1);
            }
        } else if (i8 < 0 && !view2.canScrollVertically(-1)) {
            int i14 = this.G;
            if (i10 > i14 && !this.I) {
                int i15 = top - i14;
                iArr[1] = i15;
                int i16 = -i15;
                int i17 = d1.OVER_SCROLL_ALWAYS;
                view.offsetTopAndBottom(i16);
                J(4);
            } else {
                if (!this.K) {
                    return;
                }
                iArr[1] = i8;
                int i18 = d1.OVER_SCROLL_ALWAYS;
                view.offsetTopAndBottom(-i8);
                J(1);
            }
        }
        A(view.getTop());
        this.O = i8;
        this.P = true;
    }

    @Override // c0.b
    public final void p(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9, int[] iArr) {
    }

    @Override // c0.b
    public final void r(View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        int i7 = this.f3380a;
        if (i7 != 0) {
            if (i7 == -1 || (i7 & 1) == 1) {
                this.f3388e = savedState.f3413c;
            }
            if (i7 == -1 || (i7 & 2) == 2) {
                this.f3382b = savedState.f3414d;
            }
            if (i7 == -1 || (i7 & 4) == 4) {
                this.I = savedState.f3415e;
            }
            if (i7 == -1 || (i7 & 8) == 8) {
                this.J = savedState.f3416f;
            }
        }
        int i8 = savedState.f3412b;
        if (i8 == 1 || i8 == 2) {
            this.L = 4;
        } else {
            this.L = i8;
        }
    }

    @Override // c0.b
    public final Parcelable s(View view) {
        return new SavedState(View.BaseSavedState.EMPTY_STATE, this);
    }

    @Override // c0.b
    public final boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i7, int i8) {
        this.O = 0;
        this.P = false;
        return (i7 & 2) != 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
    
        if (r4.getTop() <= r2.E) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0071, code lost:
    
        if (java.lang.Math.abs(r3 - r2.D) < java.lang.Math.abs(r3 - r2.G)) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0080, code lost:
    
        if (r3 < java.lang.Math.abs(r3 - r2.G)) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0090, code lost:
    
        if (java.lang.Math.abs(r3 - r1) < java.lang.Math.abs(r3 - r2.G)) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ac, code lost:
    
        if (java.lang.Math.abs(r3 - r2.E) < java.lang.Math.abs(r3 - r2.G)) goto L50;
     */
    @Override // c0.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u(androidx.coordinatorlayout.widget.CoordinatorLayout r3, android.view.View r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.D()
            r0 = 3
            if (r3 != r6) goto Lf
            r2.J(r0)
            return
        Lf:
            java.lang.ref.WeakReference r3 = r2.W
            if (r3 == 0) goto Lb5
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto Lb5
            boolean r3 = r2.P
            if (r3 != 0) goto L1f
            goto Lb5
        L1f:
            int r3 = r2.O
            r5 = 6
            if (r3 <= 0) goto L34
            boolean r3 = r2.f3382b
            if (r3 == 0) goto L2a
            goto Laf
        L2a:
            int r3 = r4.getTop()
            int r6 = r2.E
            if (r3 <= r6) goto Laf
            goto Lae
        L34:
            boolean r3 = r2.I
            if (r3 == 0) goto L55
            android.view.VelocityTracker r3 = r2.Y
            if (r3 != 0) goto L3e
            r3 = 0
            goto L4d
        L3e:
            r6 = 1000(0x3e8, float:1.401E-42)
            float r1 = r2.f3384c
            r3.computeCurrentVelocity(r6, r1)
            android.view.VelocityTracker r3 = r2.Y
            int r6 = r2.f3381a0
            float r3 = r3.getYVelocity(r6)
        L4d:
            boolean r3 = r2.K(r4, r3)
            if (r3 == 0) goto L55
            r0 = 5
            goto Laf
        L55:
            int r3 = r2.O
            r6 = 4
            if (r3 != 0) goto L93
            int r3 = r4.getTop()
            boolean r1 = r2.f3382b
            if (r1 == 0) goto L74
            int r5 = r2.D
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.G
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L97
            goto Laf
        L74:
            int r1 = r2.E
            if (r3 >= r1) goto L83
            int r6 = r2.G
            int r6 = r3 - r6
            int r6 = java.lang.Math.abs(r6)
            if (r3 >= r6) goto Lae
            goto Laf
        L83:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.G
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L97
            goto Lae
        L93:
            boolean r3 = r2.f3382b
            if (r3 == 0) goto L99
        L97:
            r0 = r6
            goto Laf
        L99:
            int r3 = r4.getTop()
            int r0 = r2.E
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.G
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L97
        Lae:
            r0 = r5
        Laf:
            r3 = 0
            r2.L(r4, r0, r3)
            r2.P = r3
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.u(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    @Override // c0.b
    public final boolean v(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        int i7 = this.L;
        if (i7 == 1 && actionMasked == 0) {
            return true;
        }
        e eVar = this.M;
        if (eVar != null && (this.K || i7 == 1)) {
            eVar.j(motionEvent);
        }
        if (actionMasked == 0) {
            this.f3381a0 = -1;
            this.f3383b0 = -1;
            VelocityTracker velocityTracker = this.Y;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.Y = null;
            }
        }
        if (this.Y == null) {
            this.Y = VelocityTracker.obtain();
        }
        this.Y.addMovement(motionEvent);
        if (this.M != null && ((this.K || this.L == 1) && actionMasked == 2 && !this.N)) {
            float abs = Math.abs(this.f3383b0 - motionEvent.getY());
            e eVar2 = this.M;
            if (abs > eVar2.f9238b) {
                eVar2.b(motionEvent.getPointerId(motionEvent.getActionIndex()), view);
            }
        }
        return !this.N;
    }

    public final void w() {
        int y6 = y();
        if (this.f3382b) {
            this.G = Math.max(this.T - y6, this.D);
        } else {
            this.G = this.T - y6;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float x() {
        /*
            r5 = this;
            p3.j r0 = r5.f3394i
            r1 = 0
            if (r0 == 0) goto L6f
            java.lang.ref.WeakReference r0 = r5.U
            if (r0 == 0) goto L6f
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L6f
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L6f
            java.lang.ref.WeakReference r0 = r5.U
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            boolean r2 = r5.F()
            if (r2 == 0) goto L6f
            android.view.WindowInsets r0 = r0.getRootWindowInsets()
            if (r0 == 0) goto L6f
            p3.j r2 = r5.f3394i
            float r2 = r2.j()
            android.view.RoundedCorner r3 = e0.w.i(r0)
            if (r3 == 0) goto L44
            int r3 = e0.w.c(r3)
            float r3 = (float) r3
            int r4 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r4 <= 0) goto L44
            int r4 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r4 <= 0) goto L44
            float r3 = r3 / r2
            goto L45
        L44:
            r3 = r1
        L45:
            p3.j r2 = r5.f3394i
            p3.i r4 = r2.f8263a
            p3.p r4 = r4.f8241a
            p3.d r4 = r4.f8306f
            android.graphics.RectF r2 = r2.h()
            float r2 = r4.a(r2)
            android.view.RoundedCorner r0 = e0.w.r(r0)
            if (r0 == 0) goto L6a
            int r0 = e0.w.c(r0)
            float r0 = (float) r0
            int r4 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r4 <= 0) goto L6a
            int r4 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r4 <= 0) goto L6a
            float r1 = r0 / r2
        L6a:
            float r0 = java.lang.Math.max(r3, r1)
            return r0
        L6f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.x():float");
    }

    public final int y() {
        int i7;
        return this.f3390f ? Math.min(Math.max(this.f3392g, this.T - ((this.S * 9) / 16)), this.R) + this.f3407v : (this.f3399n || this.f3400o || (i7 = this.f3398m) <= 0) ? this.f3388e + this.f3407v : Math.max(this.f3388e, i7 + this.f3393h);
    }

    public final void z(int i7, View view) {
        if (view == null) {
            return;
        }
        d1.l(i.ACTION_COLLAPSE, view);
        d1.i(0, view);
        d1.l(262144, view);
        d1.i(0, view);
        d1.l(i.ACTION_DISMISS, view);
        d1.i(0, view);
        SparseIntArray sparseIntArray = this.f3389e0;
        int i8 = sparseIntArray.get(i7, -1);
        if (i8 != -1) {
            d1.l(i8, view);
            d1.i(0, view);
            sparseIntArray.delete(i7);
        }
    }
}
