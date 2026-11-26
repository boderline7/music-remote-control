package com.google.android.material.behavior;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.appcompat.widget.d;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c0.b;
import java.util.Iterator;
import java.util.LinkedHashSet;
import l6.f;
import q2.c;
import r2.a;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends b {
    public static final int STATE_SCROLLED_DOWN = 1;
    public static final int STATE_SCROLLED_UP = 2;

    /* renamed from: j, reason: collision with root package name */
    public static final int f3307j = c.motionDurationLong2;

    /* renamed from: k, reason: collision with root package name */
    public static final int f3308k = c.motionDurationMedium4;

    /* renamed from: l, reason: collision with root package name */
    public static final int f3309l = c.motionEasingEmphasizedInterpolator;

    /* renamed from: b, reason: collision with root package name */
    public int f3311b;

    /* renamed from: c, reason: collision with root package name */
    public int f3312c;

    /* renamed from: d, reason: collision with root package name */
    public TimeInterpolator f3313d;

    /* renamed from: e, reason: collision with root package name */
    public TimeInterpolator f3314e;

    /* renamed from: i, reason: collision with root package name */
    public ViewPropertyAnimator f3318i;

    /* renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f3310a = new LinkedHashSet();

    /* renamed from: f, reason: collision with root package name */
    public int f3315f = 0;

    /* renamed from: g, reason: collision with root package name */
    public int f3316g = 2;

    /* renamed from: h, reason: collision with root package name */
    public int f3317h = 0;

    public HideBottomViewOnScrollBehavior() {
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
    }

    @Override // c0.b
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
        this.f3315f = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        this.f3311b = f.u0(view.getContext(), f3307j, z1.c.SHR_INT_LIT8);
        this.f3312c = f.u0(view.getContext(), f3308k, z1.c.REM_DOUBLE);
        Context context = view.getContext();
        TimeInterpolator timeInterpolator = a.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
        int i8 = f3309l;
        this.f3313d = f.v0(context, i8, timeInterpolator);
        this.f3314e = f.v0(view.getContext(), i8, a.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        return false;
    }

    @Override // c0.b
    public final void p(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9, int[] iArr) {
        LinkedHashSet linkedHashSet = this.f3310a;
        if (i7 > 0) {
            if (this.f3316g == 1) {
                return;
            }
            ViewPropertyAnimator viewPropertyAnimator = this.f3318i;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                view.clearAnimation();
            }
            this.f3316g = 1;
            Iterator it = linkedHashSet.iterator();
            if (it.hasNext()) {
                a.a.u(it.next());
                throw null;
            }
            this.f3318i = view.animate().translationY(this.f3315f + this.f3317h).setInterpolator(this.f3314e).setDuration(this.f3312c).setListener(new d(3, this));
            return;
        }
        if (i7 >= 0 || this.f3316g == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.f3318i;
        if (viewPropertyAnimator2 != null) {
            viewPropertyAnimator2.cancel();
            view.clearAnimation();
        }
        this.f3316g = 2;
        Iterator it2 = linkedHashSet.iterator();
        if (it2.hasNext()) {
            a.a.u(it2.next());
            throw null;
        }
        this.f3318i = view.animate().translationY(0).setInterpolator(this.f3313d).setDuration(this.f3311b).setListener(new d(3, this));
    }

    @Override // c0.b
    public boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i7, int i8) {
        return i7 == 2;
    }
}
