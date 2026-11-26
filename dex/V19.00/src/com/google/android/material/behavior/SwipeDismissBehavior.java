package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c0.b;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;
import p0.l0;
import q0.g;
import q0.i;
import u0.e;
import u2.a;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SwipeDismissBehavior<V extends View> extends b {
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;

    /* renamed from: a, reason: collision with root package name */
    public e f3319a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f3320b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f3321c;

    /* renamed from: d, reason: collision with root package name */
    public int f3322d = 2;

    /* renamed from: e, reason: collision with root package name */
    public final float f3323e = 0.5f;

    /* renamed from: f, reason: collision with root package name */
    public float f3324f = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: g, reason: collision with root package name */
    public float f3325g = 0.5f;

    /* renamed from: h, reason: collision with root package name */
    public final a f3326h = new a(this);

    @Override // c0.b
    public boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z6 = this.f3320b;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z6 = coordinatorLayout.s(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f3320b = z6;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f3320b = false;
        }
        if (!z6) {
            return false;
        }
        if (this.f3319a == null) {
            this.f3319a = new e(coordinatorLayout.getContext(), coordinatorLayout, this.f3326h);
        }
        return !this.f3321c && this.f3319a.q(motionEvent);
    }

    @Override // c0.b
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
        int i8 = d1.OVER_SCROLL_ALWAYS;
        if (l0.c(view) == 0) {
            l0.s(view, 1);
            d1.l(i.ACTION_DISMISS, view);
            d1.i(0, view);
            if (w(view)) {
                d1.m(view, g.ACTION_DISMISS, null, new e.a(29, this));
            }
        }
        return false;
    }

    @Override // c0.b
    public final boolean v(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (this.f3319a == null) {
            return false;
        }
        if (this.f3321c && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.f3319a.j(motionEvent);
        return true;
    }

    public boolean w(View view) {
        return true;
    }
}
