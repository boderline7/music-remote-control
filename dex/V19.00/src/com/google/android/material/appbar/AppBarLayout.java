package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.yalantis.ucrop.view.CropImageView;
import i6.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import n1.z;
import p0.d1;
import p0.l0;
import p0.l2;
import p0.m0;
import p0.n0;
import p0.o0;
import p0.w;
import p3.j;
import q.k;
import q0.g;
import q2.l;
import q2.m;
import s2.f;
import s2.h;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppBarLayout extends LinearLayout implements c0.a {

    /* renamed from: z, reason: collision with root package name */
    public static final int f3203z = l.Widget_Design_AppBarLayout;

    /* renamed from: a, reason: collision with root package name */
    public int f3204a;

    /* renamed from: b, reason: collision with root package name */
    public int f3205b;

    /* renamed from: c, reason: collision with root package name */
    public int f3206c;

    /* renamed from: d, reason: collision with root package name */
    public int f3207d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f3208e;

    /* renamed from: f, reason: collision with root package name */
    public int f3209f;

    /* renamed from: g, reason: collision with root package name */
    public l2 f3210g;

    /* renamed from: h, reason: collision with root package name */
    public ArrayList f3211h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f3212i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f3213j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f3214k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f3215l;

    /* renamed from: m, reason: collision with root package name */
    public int f3216m;

    /* renamed from: n, reason: collision with root package name */
    public WeakReference f3217n;

    /* renamed from: o, reason: collision with root package name */
    public final boolean f3218o;

    /* renamed from: p, reason: collision with root package name */
    public ValueAnimator f3219p;

    /* renamed from: q, reason: collision with root package name */
    public final ValueAnimator.AnimatorUpdateListener f3220q;

    /* renamed from: r, reason: collision with root package name */
    public final ArrayList f3221r;

    /* renamed from: s, reason: collision with root package name */
    public final long f3222s;

    /* renamed from: t, reason: collision with root package name */
    public final TimeInterpolator f3223t;

    /* renamed from: u, reason: collision with root package name */
    public int[] f3224u;

    /* renamed from: v, reason: collision with root package name */
    public Drawable f3225v;

    /* renamed from: w, reason: collision with root package name */
    public Integer f3226w;

    /* renamed from: x, reason: collision with root package name */
    public final float f3227x;

    /* renamed from: y, reason: collision with root package name */
    public Behavior f3228y;

    public static class BaseBehavior<T extends AppBarLayout> extends h {

        /* renamed from: j, reason: collision with root package name */
        public int f3229j;

        /* renamed from: k, reason: collision with root package name */
        public int f3230k;

        /* renamed from: l, reason: collision with root package name */
        public ValueAnimator f3231l;

        /* renamed from: m, reason: collision with root package name */
        public SavedState f3232m;

        /* renamed from: n, reason: collision with root package name */
        public WeakReference f3233n;

        /* renamed from: o, reason: collision with root package name */
        public boolean f3234o;

        public static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new e();

            /* renamed from: b, reason: collision with root package name */
            public boolean f3235b;

            /* renamed from: c, reason: collision with root package name */
            public boolean f3236c;

            /* renamed from: d, reason: collision with root package name */
            public int f3237d;

            /* renamed from: e, reason: collision with root package name */
            public float f3238e;

            /* renamed from: f, reason: collision with root package name */
            public boolean f3239f;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.f3235b = parcel.readByte() != 0;
                this.f3236c = parcel.readByte() != 0;
                this.f3237d = parcel.readInt();
                this.f3238e = parcel.readFloat();
                this.f3239f = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i7) {
                parcel.writeParcelable(this.f1829a, i7);
                parcel.writeByte(this.f3235b ? (byte) 1 : (byte) 0);
                parcel.writeByte(this.f3236c ? (byte) 1 : (byte) 0);
                parcel.writeInt(this.f3237d);
                parcel.writeFloat(this.f3238e);
                parcel.writeByte(this.f3239f ? (byte) 1 : (byte) 0);
            }
        }

        public BaseBehavior() {
            this.f8678f = -1;
            this.f8680h = -1;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(0);
            this.f8678f = -1;
            this.f8680h = -1;
        }

        public static View C(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = coordinatorLayout.getChildAt(i7);
                if ((childAt instanceof w) || (childAt instanceof AbsListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        public static void H(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i7, int i8, boolean z6) {
            View view;
            boolean z7;
            int abs = Math.abs(i7);
            int childCount = appBarLayout.getChildCount();
            int i9 = 0;
            while (true) {
                if (i9 >= childCount) {
                    view = null;
                    break;
                }
                view = appBarLayout.getChildAt(i9);
                if (abs >= view.getTop() && abs <= view.getBottom()) {
                    break;
                } else {
                    i9++;
                }
            }
            if (view != null) {
                int i10 = ((s2.c) view.getLayoutParams()).f8667a;
                if ((i10 & 1) != 0) {
                    int i11 = d1.OVER_SCROLL_ALWAYS;
                    int d7 = l0.d(view);
                    z7 = true;
                    if (i8 > 0) {
                    }
                }
            }
            z7 = false;
            if (appBarLayout.f3215l) {
                z7 = appBarLayout.f(C(coordinatorLayout));
            }
            boolean e7 = appBarLayout.e(z7);
            if (!z6) {
                if (e7) {
                    List list = (List) ((k) coordinatorLayout.f1720b.f6893d).getOrDefault(appBarLayout, null);
                    ArrayList arrayList = coordinatorLayout.f1722d;
                    arrayList.clear();
                    if (list != null) {
                        arrayList.addAll(list);
                    }
                    int size = arrayList.size();
                    for (int i12 = 0; i12 < size; i12++) {
                        c0.b bVar = ((c0.e) ((View) arrayList.get(i12)).getLayoutParams()).f3007a;
                        if (bVar instanceof ScrollingViewBehavior) {
                            if (((ScrollingViewBehavior) bVar).f8685f == 0) {
                                return;
                            }
                        }
                    }
                    return;
                }
                return;
            }
            if (appBarLayout.getBackground() != null) {
                appBarLayout.getBackground().jumpToCurrentState();
            }
            if (appBarLayout.getForeground() != null) {
                appBarLayout.getForeground().jumpToCurrentState();
            }
            if (appBarLayout.getStateListAnimator() != null) {
                appBarLayout.getStateListAnimator().jumpToCurrentState();
            }
        }

        public final void B(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i7) {
            int abs = Math.abs(y() - i7);
            float abs2 = Math.abs(CropImageView.DEFAULT_ASPECT_RATIO);
            int round = abs2 > CropImageView.DEFAULT_ASPECT_RATIO ? Math.round((abs / abs2) * 1000.0f) * 3 : (int) (((abs / appBarLayout.getHeight()) + 1.0f) * 150.0f);
            int y6 = y();
            if (y6 == i7) {
                ValueAnimator valueAnimator = this.f3231l;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.f3231l.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.f3231l;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.f3231l = valueAnimator3;
                valueAnimator3.setInterpolator(r2.a.DECELERATE_INTERPOLATOR);
                this.f3231l.addUpdateListener(new a(this, coordinatorLayout, appBarLayout));
            } else {
                valueAnimator2.cancel();
            }
            this.f3231l.setDuration(Math.min(round, 600));
            this.f3231l.setIntValues(y6, i7);
            this.f3231l.start();
        }

        public final void D(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i7, int[] iArr) {
            int i8;
            int i9;
            if (i7 != 0) {
                if (i7 < 0) {
                    i8 = -appBarLayout.getTotalScrollRange();
                    i9 = appBarLayout.getDownNestedPreScrollRange() + i8;
                } else {
                    i8 = -appBarLayout.getUpNestedPreScrollRange();
                    i9 = 0;
                }
                int i10 = i8;
                int i11 = i9;
                if (i10 != i11) {
                    iArr[1] = z(coordinatorLayout, appBarLayout, y() - i7, i10, i11);
                }
            }
            if (appBarLayout.f3215l) {
                appBarLayout.e(appBarLayout.f(view));
            }
        }

        public final SavedState E(Parcelable parcelable, AppBarLayout appBarLayout) {
            int w6 = w();
            int childCount = appBarLayout.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = appBarLayout.getChildAt(i7);
                int bottom = childAt.getBottom() + w6;
                if (childAt.getTop() + w6 <= 0 && bottom >= 0) {
                    if (parcelable == null) {
                        parcelable = AbsSavedState.EMPTY_STATE;
                    }
                    SavedState savedState = new SavedState(parcelable);
                    boolean z6 = w6 == 0;
                    savedState.f3236c = z6;
                    savedState.f3235b = !z6 && (-w6) >= appBarLayout.getTotalScrollRange();
                    savedState.f3237d = i7;
                    int i8 = d1.OVER_SCROLL_ALWAYS;
                    savedState.f3239f = bottom == appBarLayout.getTopInset() + l0.d(childAt);
                    savedState.f3238e = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return null;
        }

        public final void F(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            int paddingTop = appBarLayout.getPaddingTop() + appBarLayout.getTopInset();
            int y6 = y() - paddingTop;
            int childCount = appBarLayout.getChildCount();
            int i7 = 0;
            while (true) {
                if (i7 >= childCount) {
                    i7 = -1;
                    break;
                }
                View childAt = appBarLayout.getChildAt(i7);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                s2.c cVar = (s2.c) childAt.getLayoutParams();
                if ((cVar.f8667a & 32) == 32) {
                    top -= ((LinearLayout.LayoutParams) cVar).topMargin;
                    bottom += ((LinearLayout.LayoutParams) cVar).bottomMargin;
                }
                int i8 = -y6;
                if (top <= i8 && bottom >= i8) {
                    break;
                } else {
                    i7++;
                }
            }
            if (i7 >= 0) {
                View childAt2 = appBarLayout.getChildAt(i7);
                s2.c cVar2 = (s2.c) childAt2.getLayoutParams();
                int i9 = cVar2.f8667a;
                if ((i9 & 17) == 17) {
                    int i10 = -childAt2.getTop();
                    int i11 = -childAt2.getBottom();
                    if (i7 == 0) {
                        int i12 = d1.OVER_SCROLL_ALWAYS;
                        if (l0.b(appBarLayout) && l0.b(childAt2)) {
                            i10 -= appBarLayout.getTopInset();
                        }
                    }
                    if ((i9 & 2) == 2) {
                        int i13 = d1.OVER_SCROLL_ALWAYS;
                        i11 += l0.d(childAt2);
                    } else if ((i9 & 5) == 5) {
                        int i14 = d1.OVER_SCROLL_ALWAYS;
                        int d7 = l0.d(childAt2) + i11;
                        if (y6 < d7) {
                            i10 = d7;
                        } else {
                            i11 = d7;
                        }
                    }
                    if ((i9 & 32) == 32) {
                        i10 += ((LinearLayout.LayoutParams) cVar2).topMargin;
                        i11 -= ((LinearLayout.LayoutParams) cVar2).bottomMargin;
                    }
                    if (y6 < (i11 + i10) / 2) {
                        i10 = i11;
                    }
                    B(coordinatorLayout, appBarLayout, z.y(i10 + paddingTop, -appBarLayout.getTotalScrollRange(), 0));
                }
            }
        }

        public final void G(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            View view;
            d1.l(g.ACTION_SCROLL_FORWARD.a(), coordinatorLayout);
            boolean z6 = false;
            d1.i(0, coordinatorLayout);
            d1.l(g.ACTION_SCROLL_BACKWARD.a(), coordinatorLayout);
            d1.i(0, coordinatorLayout);
            if (appBarLayout.getTotalScrollRange() == 0) {
                return;
            }
            int childCount = coordinatorLayout.getChildCount();
            int i7 = 0;
            while (true) {
                if (i7 >= childCount) {
                    view = null;
                    break;
                }
                View childAt = coordinatorLayout.getChildAt(i7);
                if (((c0.e) childAt.getLayoutParams()).f3007a instanceof ScrollingViewBehavior) {
                    view = childAt;
                    break;
                }
                i7++;
            }
            if (view == null) {
                return;
            }
            int childCount2 = appBarLayout.getChildCount();
            for (int i8 = 0; i8 < childCount2; i8++) {
                if (((s2.c) appBarLayout.getChildAt(i8).getLayoutParams()).f8667a != 0) {
                    if (d1.d(coordinatorLayout) == null) {
                        d1.o(coordinatorLayout, new b(this));
                    }
                    boolean z7 = true;
                    if (y() != (-appBarLayout.getTotalScrollRange())) {
                        d1.m(coordinatorLayout, g.ACTION_SCROLL_FORWARD, null, new d(appBarLayout, false));
                        z6 = true;
                    }
                    if (y() != 0) {
                        if (view.canScrollVertically(-1)) {
                            int i9 = -appBarLayout.getDownNestedPreScrollRange();
                            if (i9 != 0) {
                                d1.m(coordinatorLayout, g.ACTION_SCROLL_BACKWARD, null, new c(this, coordinatorLayout, appBarLayout, view, i9));
                            }
                        } else {
                            d1.m(coordinatorLayout, g.ACTION_SCROLL_BACKWARD, null, new d(appBarLayout, true));
                        }
                        this.f3234o = z7;
                        return;
                    }
                    z7 = z6;
                    this.f3234o = z7;
                    return;
                }
            }
        }

        @Override // s2.j, c0.b
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
            int round;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            super.l(coordinatorLayout, appBarLayout, i7);
            int pendingAction = appBarLayout.getPendingAction();
            SavedState savedState = this.f3232m;
            if (savedState == null || (pendingAction & 8) != 0) {
                if (pendingAction != 0) {
                    boolean z6 = (pendingAction & 4) != 0;
                    if ((pendingAction & 2) != 0) {
                        int i8 = -appBarLayout.getUpNestedPreScrollRange();
                        if (z6) {
                            B(coordinatorLayout, appBarLayout, i8);
                        } else {
                            A(coordinatorLayout, appBarLayout, i8);
                        }
                    } else if ((pendingAction & 1) != 0) {
                        if (z6) {
                            B(coordinatorLayout, appBarLayout, 0);
                        } else {
                            A(coordinatorLayout, appBarLayout, 0);
                        }
                    }
                }
            } else if (savedState.f3235b) {
                A(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange());
            } else if (savedState.f3236c) {
                A(coordinatorLayout, appBarLayout, 0);
            } else {
                View childAt = appBarLayout.getChildAt(savedState.f3237d);
                int i9 = -childAt.getBottom();
                if (this.f3232m.f3239f) {
                    int i10 = d1.OVER_SCROLL_ALWAYS;
                    round = appBarLayout.getTopInset() + l0.d(childAt) + i9;
                } else {
                    round = Math.round(childAt.getHeight() * this.f3232m.f3238e) + i9;
                }
                A(coordinatorLayout, appBarLayout, round);
            }
            appBarLayout.f3209f = 0;
            this.f3232m = null;
            int y6 = z.y(w(), -appBarLayout.getTotalScrollRange(), 0);
            s2.k kVar = this.f8686a;
            if (kVar != null) {
                kVar.b(y6);
            } else {
                this.f8687b = y6;
            }
            H(coordinatorLayout, appBarLayout, w(), 0, true);
            appBarLayout.d(w());
            G(coordinatorLayout, appBarLayout);
            return true;
        }

        @Override // c0.b
        public final boolean m(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (((ViewGroup.MarginLayoutParams) ((c0.e) appBarLayout.getLayoutParams())).height != -2) {
                return false;
            }
            coordinatorLayout.v(appBarLayout, i7, i8, View.MeasureSpec.makeMeasureSpec(0, 0));
            return true;
        }

        @Override // c0.b
        public final /* bridge */ /* synthetic */ void o(CoordinatorLayout coordinatorLayout, View view, View view2, int i7, int i8, int[] iArr, int i9) {
            D(coordinatorLayout, (AppBarLayout) view, view2, i8, iArr);
        }

        @Override // c0.b
        public final void p(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9, int[] iArr) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i9 < 0) {
                iArr[1] = z(coordinatorLayout, appBarLayout, y() - i9, -appBarLayout.getDownNestedScrollRange(), 0);
            }
            if (i9 == 0) {
                G(coordinatorLayout, appBarLayout);
            }
        }

        @Override // c0.b
        public final void r(View view, Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                this.f3232m = (SavedState) parcelable;
            } else {
                this.f3232m = null;
            }
        }

        @Override // c0.b
        public final Parcelable s(View view) {
            android.view.AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            SavedState E = E(absSavedState, (AppBarLayout) view);
            return E == null ? absSavedState : E;
        }

        @Override // c0.b
        public final boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i7, int i8) {
            ValueAnimator valueAnimator;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean z6 = (i7 & 2) != 0 && (appBarLayout.f3215l || (appBarLayout.getTotalScrollRange() != 0 && coordinatorLayout.getHeight() - view2.getHeight() <= appBarLayout.getHeight()));
            if (z6 && (valueAnimator = this.f3231l) != null) {
                valueAnimator.cancel();
            }
            this.f3233n = null;
            this.f3230k = i8;
            return z6;
        }

        @Override // c0.b
        public final void u(CoordinatorLayout coordinatorLayout, View view, View view2, int i7) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.f3230k == 0 || i7 == 1) {
                F(coordinatorLayout, appBarLayout);
                if (appBarLayout.f3215l) {
                    appBarLayout.e(appBarLayout.f(view2));
                }
            }
            this.f3233n = new WeakReference(view2);
        }

        @Override // s2.h
        public final int y() {
            return w() + this.f3229j;
        }

        @Override // s2.h
        public final int z(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9) {
            int i10;
            boolean z6;
            int i11;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int y6 = y();
            int i12 = 0;
            if (i8 == 0 || y6 < i8 || y6 > i9) {
                this.f3229j = 0;
            } else {
                int y7 = z.y(i7, i8, i9);
                if (y6 != y7) {
                    if (appBarLayout.f3208e) {
                        int abs = Math.abs(y7);
                        int childCount = appBarLayout.getChildCount();
                        int i13 = 0;
                        while (true) {
                            if (i13 >= childCount) {
                                break;
                            }
                            View childAt = appBarLayout.getChildAt(i13);
                            s2.c cVar = (s2.c) childAt.getLayoutParams();
                            Interpolator interpolator = cVar.f8669c;
                            if (abs < childAt.getTop() || abs > childAt.getBottom()) {
                                i13++;
                            } else if (interpolator != null) {
                                int i14 = cVar.f8667a;
                                if ((i14 & 1) != 0) {
                                    i11 = childAt.getHeight() + ((LinearLayout.LayoutParams) cVar).topMargin + ((LinearLayout.LayoutParams) cVar).bottomMargin;
                                    if ((i14 & 2) != 0) {
                                        int i15 = d1.OVER_SCROLL_ALWAYS;
                                        i11 -= l0.d(childAt);
                                    }
                                } else {
                                    i11 = 0;
                                }
                                int i16 = d1.OVER_SCROLL_ALWAYS;
                                if (l0.b(childAt)) {
                                    i11 -= appBarLayout.getTopInset();
                                }
                                if (i11 > 0) {
                                    float f7 = i11;
                                    i10 = (childAt.getTop() + Math.round(interpolator.getInterpolation((abs - childAt.getTop()) / f7) * f7)) * Integer.signum(y7);
                                }
                            }
                        }
                    }
                    i10 = y7;
                    s2.k kVar = this.f8686a;
                    if (kVar != null) {
                        z6 = kVar.b(i10);
                    } else {
                        this.f8687b = i10;
                        z6 = false;
                    }
                    int i17 = y6 - y7;
                    this.f3229j = y7 - i10;
                    if (z6) {
                        for (int i18 = 0; i18 < appBarLayout.getChildCount(); i18++) {
                            s2.c cVar2 = (s2.c) appBarLayout.getChildAt(i18).getLayoutParams();
                            i iVar = cVar2.f8668b;
                            if (iVar != null && (cVar2.f8667a & 1) != 0) {
                                View childAt2 = appBarLayout.getChildAt(i18);
                                float w6 = w();
                                Rect rect = (Rect) iVar.f6848b;
                                childAt2.getDrawingRect(rect);
                                appBarLayout.offsetDescendantRectToMyCoords(childAt2, rect);
                                rect.offset(0, -appBarLayout.getTopInset());
                                float abs2 = ((Rect) iVar.f6848b).top - Math.abs(w6);
                                if (abs2 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                                    float x6 = 1.0f - z.x(Math.abs(abs2 / ((Rect) iVar.f6848b).height()), CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
                                    float height = (-abs2) - ((((Rect) iVar.f6848b).height() * 0.3f) * (1.0f - (x6 * x6)));
                                    childAt2.setTranslationY(height);
                                    childAt2.getDrawingRect((Rect) iVar.f6849c);
                                    ((Rect) iVar.f6849c).offset(0, (int) (-height));
                                    Rect rect2 = (Rect) iVar.f6849c;
                                    int i19 = d1.OVER_SCROLL_ALWAYS;
                                    n0.c(childAt2, rect2);
                                } else {
                                    int i20 = d1.OVER_SCROLL_ALWAYS;
                                    n0.c(childAt2, null);
                                    childAt2.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                                }
                            }
                        }
                    }
                    if (!z6 && appBarLayout.f3208e) {
                        coordinatorLayout.m(appBarLayout);
                    }
                    appBarLayout.d(w());
                    H(coordinatorLayout, appBarLayout, y7, y7 < y6 ? -1 : 1, false);
                    i12 = i17;
                }
            }
            G(coordinatorLayout, appBarLayout);
            return i12;
        }
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class ScrollingViewBehavior extends s2.i {
        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(0);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.ScrollingViewBehavior_Layout);
            this.f8685f = obtainStyledAttributes.getDimensionPixelSize(m.ScrollingViewBehavior_Layout_behavior_overlapTop, 0);
            obtainStyledAttributes.recycle();
        }

        public static AppBarLayout z(ArrayList arrayList) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                View view = (View) arrayList.get(i7);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        @Override // c0.b
        public final boolean f(View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // c0.b
        public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            c0.b bVar = ((c0.e) view2.getLayoutParams()).f3007a;
            if (bVar instanceof BaseBehavior) {
                int bottom = (((view2.getBottom() - view.getTop()) + ((BaseBehavior) bVar).f3229j) + this.f8684e) - y(view2);
                int i7 = d1.OVER_SCROLL_ALWAYS;
                view.offsetTopAndBottom(bottom);
            }
            if (!(view2 instanceof AppBarLayout)) {
                return false;
            }
            AppBarLayout appBarLayout = (AppBarLayout) view2;
            if (!appBarLayout.f3215l) {
                return false;
            }
            appBarLayout.e(appBarLayout.f(view));
            return false;
        }

        @Override // c0.b
        public final void i(CoordinatorLayout coordinatorLayout, View view) {
            if (view instanceof AppBarLayout) {
                d1.l(g.ACTION_SCROLL_FORWARD.a(), coordinatorLayout);
                d1.i(0, coordinatorLayout);
                d1.l(g.ACTION_SCROLL_BACKWARD.a(), coordinatorLayout);
                d1.i(0, coordinatorLayout);
                d1.o(coordinatorLayout, null);
            }
        }

        @Override // c0.b
        public final boolean q(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z6) {
            AppBarLayout z7 = z(coordinatorLayout.o(view));
            if (z7 != null) {
                Rect rect2 = new Rect(rect);
                rect2.offset(view.getLeft(), view.getTop());
                int width = coordinatorLayout.getWidth();
                int height = coordinatorLayout.getHeight();
                Rect rect3 = this.f8682c;
                rect3.set(0, 0, width, height);
                if (!rect3.contains(rect2)) {
                    z7.setExpanded(false, !z6);
                    return true;
                }
            }
            return false;
        }
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.appBarLayoutStyle);
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AppBarLayout(android.content.Context r12, android.util.AttributeSet r13, int r14) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public static s2.c b(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            s2.c cVar = new s2.c((LinearLayout.LayoutParams) layoutParams);
            cVar.f8667a = 1;
            return cVar;
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            s2.c cVar2 = new s2.c((ViewGroup.MarginLayoutParams) layoutParams);
            cVar2.f8667a = 1;
            return cVar2;
        }
        s2.c cVar3 = new s2.c(layoutParams);
        cVar3.f8667a = 1;
        return cVar3;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final s2.c generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        s2.c cVar = new s2.c(context, attributeSet);
        cVar.f8667a = 1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.AppBarLayout_Layout);
        cVar.f8667a = obtainStyledAttributes.getInt(m.AppBarLayout_Layout_layout_scrollFlags, 0);
        cVar.f8668b = obtainStyledAttributes.getInt(m.AppBarLayout_Layout_layout_scrollEffect, 0) != 1 ? null : new i();
        if (obtainStyledAttributes.hasValue(m.AppBarLayout_Layout_layout_scrollInterpolator)) {
            cVar.f8669c = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(m.AppBarLayout_Layout_layout_scrollInterpolator, 0));
        }
        obtainStyledAttributes.recycle();
        return cVar;
    }

    public final void c() {
        Behavior behavior = this.f3228y;
        BaseBehavior.SavedState E = (behavior == null || this.f3205b == -1 || this.f3209f != 0) ? null : behavior.E(AbsSavedState.EMPTY_STATE, this);
        this.f3205b = -1;
        this.f3206c = -1;
        this.f3207d = -1;
        if (E != null) {
            Behavior behavior2 = this.f3228y;
            if (behavior2.f3232m != null) {
                return;
            }
            behavior2.f3232m = E;
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof s2.c;
    }

    public final void d(int i7) {
        this.f3204a = i7;
        if (!willNotDraw()) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
        ArrayList arrayList = this.f3211h;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i9 = 0; i9 < size; i9++) {
                s2.b bVar = (s2.b) this.f3211h.get(i9);
                if (bVar != null) {
                    CollapsingToolbarLayout collapsingToolbarLayout = ((f) bVar).f8674a;
                    collapsingToolbarLayout.f3264y = i7;
                    l2 l2Var = collapsingToolbarLayout.A;
                    int d7 = l2Var != null ? l2Var.d() : 0;
                    int childCount = collapsingToolbarLayout.getChildCount();
                    for (int i10 = 0; i10 < childCount; i10++) {
                        View childAt = collapsingToolbarLayout.getChildAt(i10);
                        s2.e eVar = (s2.e) childAt.getLayoutParams();
                        s2.k b7 = CollapsingToolbarLayout.b(childAt);
                        int i11 = eVar.f8672a;
                        if (i11 == 1) {
                            b7.b(z.y(-i7, 0, ((collapsingToolbarLayout.getHeight() - CollapsingToolbarLayout.b(childAt).f8689b) - childAt.getHeight()) - ((FrameLayout.LayoutParams) ((s2.e) childAt.getLayoutParams())).bottomMargin));
                        } else if (i11 == 2) {
                            b7.b(Math.round((-i7) * eVar.f8673b));
                        }
                    }
                    collapsingToolbarLayout.d();
                    if (collapsingToolbarLayout.f3255p != null && d7 > 0) {
                        int i12 = d1.OVER_SCROLL_ALWAYS;
                        l0.k(collapsingToolbarLayout);
                    }
                    int height = collapsingToolbarLayout.getHeight();
                    int i13 = d1.OVER_SCROLL_ALWAYS;
                    int d8 = (height - l0.d(collapsingToolbarLayout)) - d7;
                    float scrimVisibleHeightTrigger = height - collapsingToolbarLayout.getScrimVisibleHeightTrigger();
                    float f7 = d8;
                    float min = Math.min(1.0f, scrimVisibleHeightTrigger / f7);
                    com.google.android.material.internal.d dVar = collapsingToolbarLayout.f3250k;
                    dVar.f3811d = min;
                    dVar.f3813e = a.a.a(1.0f, min, 0.5f, min);
                    dVar.f3815f = collapsingToolbarLayout.f3264y + d8;
                    dVar.p(Math.abs(i7) / f7);
                }
            }
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f3225v == null || getTopInset() <= 0) {
            return;
        }
        int save = canvas.save();
        canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, -this.f3204a);
        this.f3225v.draw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f3225v;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    public final boolean e(boolean z6) {
        if (!(!this.f3212i) || this.f3214k == z6) {
            return false;
        }
        this.f3214k = z6;
        refreshDrawableState();
        if (!(getBackground() instanceof j)) {
            return true;
        }
        boolean z7 = this.f3218o;
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (z7) {
            float f8 = z6 ? 0.0f : 1.0f;
            if (z6) {
                f7 = 1.0f;
            }
            h(f8, f7);
            return true;
        }
        if (!this.f3215l) {
            return true;
        }
        float f9 = this.f3227x;
        float f10 = z6 ? 0.0f : f9;
        if (z6) {
            f7 = f9;
        }
        h(f10, f7);
        return true;
    }

    public final boolean f(View view) {
        int i7;
        if (this.f3217n == null && (i7 = this.f3216m) != -1) {
            View findViewById = view != null ? view.findViewById(i7) : null;
            if (findViewById == null && (getParent() instanceof ViewGroup)) {
                findViewById = ((ViewGroup) getParent()).findViewById(this.f3216m);
            }
            if (findViewById != null) {
                this.f3217n = new WeakReference(findViewById);
            }
        }
        WeakReference weakReference = this.f3217n;
        View view2 = weakReference != null ? (View) weakReference.get() : null;
        if (view2 != null) {
            view = view2;
        }
        return view != null && (view.canScrollVertically(-1) || view.getScrollY() > 0);
    }

    public final boolean g() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        if (childAt.getVisibility() == 8) {
            return false;
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        return !l0.b(childAt);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        s2.c cVar = new s2.c(-1, -2);
        cVar.f8667a = 1;
        return cVar;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final LinearLayout.LayoutParams generateDefaultLayoutParams() {
        s2.c cVar = new s2.c(-1, -2);
        cVar.f8667a = 1;
        return cVar;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return b(layoutParams);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return b(layoutParams);
    }

    @Override // c0.a
    public c0.b getBehavior() {
        Behavior behavior = new Behavior();
        this.f3228y = behavior;
        return behavior;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getDownNestedPreScrollRange() {
        /*
            r9 = this;
            int r0 = r9.f3206c
            r1 = -1
            if (r0 == r1) goto L6
            return r0
        L6:
            int r0 = r9.getChildCount()
            int r0 = r0 + (-1)
            r1 = 0
            r2 = r1
        Le:
            if (r0 < 0) goto L69
            android.view.View r3 = r9.getChildAt(r0)
            int r4 = r3.getVisibility()
            r5 = 8
            if (r4 != r5) goto L1d
            goto L66
        L1d:
            android.view.ViewGroup$LayoutParams r4 = r3.getLayoutParams()
            s2.c r4 = (s2.c) r4
            int r5 = r3.getMeasuredHeight()
            int r6 = r4.f8667a
            r7 = r6 & 5
            r8 = 5
            if (r7 != r8) goto L63
            int r7 = r4.topMargin
            int r4 = r4.bottomMargin
            int r7 = r7 + r4
            r4 = r6 & 8
            if (r4 == 0) goto L3f
            int r4 = p0.d1.OVER_SCROLL_ALWAYS
            int r4 = p0.l0.d(r3)
        L3d:
            int r4 = r4 + r7
            goto L4e
        L3f:
            r4 = r6 & 2
            if (r4 == 0) goto L4c
            int r4 = p0.d1.OVER_SCROLL_ALWAYS
            int r4 = p0.l0.d(r3)
            int r4 = r5 - r4
            goto L3d
        L4c:
            int r4 = r7 + r5
        L4e:
            if (r0 != 0) goto L61
            int r6 = p0.d1.OVER_SCROLL_ALWAYS
            boolean r3 = p0.l0.b(r3)
            if (r3 == 0) goto L61
            int r3 = r9.getTopInset()
            int r5 = r5 - r3
            int r4 = java.lang.Math.min(r4, r5)
        L61:
            int r2 = r2 + r4
            goto L66
        L63:
            if (r2 <= 0) goto L66
            goto L69
        L66:
            int r0 = r0 + (-1)
            goto Le
        L69:
            int r0 = java.lang.Math.max(r1, r2)
            r9.f3206c = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.getDownNestedPreScrollRange():int");
    }

    public int getDownNestedScrollRange() {
        int i7 = this.f3207d;
        if (i7 != -1) {
            return i7;
        }
        int childCount = getChildCount();
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i8 >= childCount) {
                break;
            }
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                s2.c cVar = (s2.c) childAt.getLayoutParams();
                int measuredHeight = ((LinearLayout.LayoutParams) cVar).topMargin + ((LinearLayout.LayoutParams) cVar).bottomMargin + childAt.getMeasuredHeight();
                int i10 = cVar.f8667a;
                if ((i10 & 1) == 0) {
                    break;
                }
                i9 += measuredHeight;
                if ((i10 & 2) != 0) {
                    int i11 = d1.OVER_SCROLL_ALWAYS;
                    i9 -= l0.d(childAt);
                    break;
                }
            }
            i8++;
        }
        int max = Math.max(0, i9);
        this.f3207d = max;
        return max;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.f3216m;
    }

    public j getMaterialShapeBackground() {
        Drawable background = getBackground();
        if (background instanceof j) {
            return (j) background;
        }
        return null;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int i7 = d1.OVER_SCROLL_ALWAYS;
        int d7 = l0.d(this);
        if (d7 == 0) {
            int childCount = getChildCount();
            d7 = childCount >= 1 ? l0.d(getChildAt(childCount - 1)) : 0;
            if (d7 == 0) {
                return getHeight() / 3;
            }
        }
        return (d7 * 2) + topInset;
    }

    public int getPendingAction() {
        return this.f3209f;
    }

    public Drawable getStatusBarForeground() {
        return this.f3225v;
    }

    @Deprecated
    public float getTargetElevation() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final int getTopInset() {
        l2 l2Var = this.f3210g;
        if (l2Var != null) {
            return l2Var.d();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i7 = this.f3205b;
        if (i7 != -1) {
            return i7;
        }
        int childCount = getChildCount();
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i8 >= childCount) {
                break;
            }
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                s2.c cVar = (s2.c) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i10 = cVar.f8667a;
                if ((i10 & 1) == 0) {
                    break;
                }
                int i11 = measuredHeight + ((LinearLayout.LayoutParams) cVar).topMargin + ((LinearLayout.LayoutParams) cVar).bottomMargin + i9;
                if (i8 == 0) {
                    int i12 = d1.OVER_SCROLL_ALWAYS;
                    if (l0.b(childAt)) {
                        i11 -= getTopInset();
                    }
                }
                i9 = i11;
                if ((i10 & 2) != 0) {
                    int i13 = d1.OVER_SCROLL_ALWAYS;
                    i9 -= l0.d(childAt);
                    break;
                }
            }
            i8++;
        }
        int max = Math.max(0, i9);
        this.f3205b = max;
        return max;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    public final void h(float f7, float f8) {
        ValueAnimator valueAnimator = this.f3219p;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f7, f8);
        this.f3219p = ofFloat;
        ofFloat.setDuration(this.f3222s);
        this.f3219p.setInterpolator(this.f3223t);
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.f3220q;
        if (animatorUpdateListener != null) {
            this.f3219p.addUpdateListener(animatorUpdateListener);
        }
        this.f3219p.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        l6.f.A0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        if (this.f3224u == null) {
            this.f3224u = new int[4];
        }
        int[] iArr = this.f3224u;
        int[] onCreateDrawableState = super.onCreateDrawableState(i7 + iArr.length);
        boolean z6 = this.f3213j;
        int i8 = q2.c.state_liftable;
        if (!z6) {
            i8 = -i8;
        }
        iArr[0] = i8;
        iArr[1] = (z6 && this.f3214k) ? q2.c.state_lifted : -q2.c.state_lifted;
        int i9 = q2.c.state_collapsible;
        if (!z6) {
            i9 = -i9;
        }
        iArr[2] = i9;
        iArr[3] = (z6 && this.f3214k) ? q2.c.state_collapsed : -q2.c.state_collapsed;
        return View.mergeDrawableStates(onCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference weakReference = this.f3217n;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f3217n = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        int i11 = d1.OVER_SCROLL_ALWAYS;
        boolean z7 = true;
        if (l0.b(this) && g()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                getChildAt(childCount).offsetTopAndBottom(topInset);
            }
        }
        c();
        this.f3208e = false;
        int childCount2 = getChildCount();
        int i12 = 0;
        while (true) {
            if (i12 >= childCount2) {
                break;
            }
            if (((s2.c) getChildAt(i12).getLayoutParams()).f8669c != null) {
                this.f3208e = true;
                break;
            }
            i12++;
        }
        Drawable drawable = this.f3225v;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.f3212i) {
            return;
        }
        if (!this.f3215l) {
            int childCount3 = getChildCount();
            int i13 = 0;
            while (true) {
                if (i13 >= childCount3) {
                    z7 = false;
                    break;
                }
                int i14 = ((s2.c) getChildAt(i13).getLayoutParams()).f8667a;
                if ((i14 & 1) == 1 && (i14 & 10) != 0) {
                    break;
                } else {
                    i13++;
                }
            }
        }
        if (this.f3213j != z7) {
            this.f3213j = z7;
            refreshDrawableState();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        int mode = View.MeasureSpec.getMode(i8);
        if (mode != 1073741824) {
            int i9 = d1.OVER_SCROLL_ALWAYS;
            if (l0.b(this) && g()) {
                int measuredHeight = getMeasuredHeight();
                if (mode == Integer.MIN_VALUE) {
                    measuredHeight = z.y(getTopInset() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i8));
                } else if (mode == 0) {
                    measuredHeight += getTopInset();
                }
                setMeasuredDimension(getMeasuredWidth(), measuredHeight);
            }
        }
        c();
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        l6.f.x0(this, f7);
    }

    public void setExpanded(boolean z6) {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        setExpanded(z6, o0.c(this));
    }

    public void setExpanded(boolean z6, boolean z7) {
        this.f3209f = (z6 ? 1 : 2) | (z7 ? 4 : 0) | 8;
        requestLayout();
    }

    public void setLiftOnScroll(boolean z6) {
        this.f3215l = z6;
    }

    public void setLiftOnScrollTargetView(View view) {
        this.f3216m = -1;
        if (view != null) {
            this.f3217n = new WeakReference(view);
            return;
        }
        WeakReference weakReference = this.f3217n;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f3217n = null;
    }

    public void setLiftOnScrollTargetViewId(int i7) {
        this.f3216m = i7;
        WeakReference weakReference = this.f3217n;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.f3217n = null;
    }

    public void setLiftableOverrideEnabled(boolean z6) {
        this.f3212i = z6;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i7) {
        if (i7 != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i7);
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.f3225v;
        if (drawable2 != drawable) {
            Integer num = null;
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.f3225v = mutate;
            if (mutate instanceof j) {
                num = Integer.valueOf(((j) mutate).f8283u);
            } else {
                ColorStateList d7 = d3.a.d(mutate);
                if (d7 != null) {
                    num = Integer.valueOf(d7.getDefaultColor());
                }
            }
            this.f3226w = num;
            Drawable drawable3 = this.f3225v;
            boolean z6 = false;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.f3225v.setState(getDrawableState());
                }
                Drawable drawable4 = this.f3225v;
                int i7 = d1.OVER_SCROLL_ALWAYS;
                j0.c.b(drawable4, m0.d(this));
                this.f3225v.setVisible(getVisibility() == 0, false);
                this.f3225v.setCallback(this);
            }
            if (this.f3225v != null && getTopInset() > 0) {
                z6 = true;
            }
            setWillNotDraw(!z6);
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
    }

    public void setStatusBarForegroundColor(int i7) {
        setStatusBarForeground(new ColorDrawable(i7));
    }

    public void setStatusBarForegroundResource(int i7) {
        setStatusBarForeground(l6.f.A(getContext(), i7));
    }

    @Deprecated
    public void setTargetElevation(float f7) {
        s2.l.a(this, f7);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        boolean z6 = i7 == 0;
        Drawable drawable = this.f3225v;
        if (drawable != null) {
            drawable.setVisible(z6, false);
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f3225v;
    }
}
