package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.t3;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.k0;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p0.d1;
import p0.m2;
import p0.o0;
import p0.r0;
import p3.j;
import q.k;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class BottomAppBar extends Toolbar implements c0.a {
    public static final int FAB_ALIGNMENT_MODE_CENTER = 0;
    public static final int FAB_ALIGNMENT_MODE_END = 1;
    public static final int FAB_ANCHOR_MODE_CRADLE = 1;
    public static final int FAB_ANCHOR_MODE_EMBED = 0;
    public static final int FAB_ANIMATION_MODE_SCALE = 0;
    public static final int FAB_ANIMATION_MODE_SLIDE = 1;
    public static final int MENU_ALIGNMENT_MODE_AUTO = 0;
    public static final int MENU_ALIGNMENT_MODE_START = 1;

    /* renamed from: v0, reason: collision with root package name */
    public static final int f3327v0 = l.Widget_MaterialComponents_BottomAppBar;

    /* renamed from: w0, reason: collision with root package name */
    public static final int f3328w0 = q2.c.motionDurationLong2;

    /* renamed from: x0, reason: collision with root package name */
    public static final int f3329x0 = q2.c.motionEasingEmphasizedInterpolator;
    public Integer U;
    public final j V;
    public Animator W;

    /* renamed from: a0, reason: collision with root package name */
    public Animator f3330a0;

    /* renamed from: b0, reason: collision with root package name */
    public int f3331b0;

    /* renamed from: c0, reason: collision with root package name */
    public int f3332c0;

    /* renamed from: d0, reason: collision with root package name */
    public int f3333d0;

    /* renamed from: e0, reason: collision with root package name */
    public final int f3334e0;

    /* renamed from: f0, reason: collision with root package name */
    public int f3335f0;

    /* renamed from: g0, reason: collision with root package name */
    public int f3336g0;

    /* renamed from: h0, reason: collision with root package name */
    public final boolean f3337h0;

    /* renamed from: i0, reason: collision with root package name */
    public boolean f3338i0;

    /* renamed from: j0, reason: collision with root package name */
    public final boolean f3339j0;

    /* renamed from: k0, reason: collision with root package name */
    public final boolean f3340k0;

    /* renamed from: l0, reason: collision with root package name */
    public final boolean f3341l0;

    /* renamed from: m0, reason: collision with root package name */
    public int f3342m0;

    /* renamed from: n0, reason: collision with root package name */
    public boolean f3343n0;

    /* renamed from: o0, reason: collision with root package name */
    public boolean f3344o0;

    /* renamed from: p0, reason: collision with root package name */
    public Behavior f3345p0;

    /* renamed from: q0, reason: collision with root package name */
    public int f3346q0;

    /* renamed from: r0, reason: collision with root package name */
    public int f3347r0;

    /* renamed from: s0, reason: collision with root package name */
    public int f3348s0;

    /* renamed from: t0, reason: collision with root package name */
    public final a f3349t0;

    /* renamed from: u0, reason: collision with root package name */
    public final b f3350u0;

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {

        /* renamed from: m, reason: collision with root package name */
        public final Rect f3351m;

        /* renamed from: n, reason: collision with root package name */
        public WeakReference f3352n;

        /* renamed from: o, reason: collision with root package name */
        public int f3353o;

        /* renamed from: p, reason: collision with root package name */
        public final g f3354p;

        public Behavior() {
            this.f3354p = new g(this);
            this.f3351m = new Rect();
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f3354p = new g(this);
            this.f3351m = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, c0.b
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            this.f3352n = new WeakReference(bottomAppBar);
            int i8 = BottomAppBar.f3327v0;
            View E = bottomAppBar.E();
            if (E != null) {
                int i9 = d1.OVER_SCROLL_ALWAYS;
                if (!o0.c(E)) {
                    BottomAppBar.N(bottomAppBar, E);
                    this.f3353o = ((ViewGroup.MarginLayoutParams) ((c0.e) E.getLayoutParams())).bottomMargin;
                    if (E instanceof FloatingActionButton) {
                        FloatingActionButton floatingActionButton = (FloatingActionButton) E;
                        if (bottomAppBar.f3333d0 == 0 && bottomAppBar.f3337h0) {
                            r0.s(floatingActionButton, CropImageView.DEFAULT_ASPECT_RATIO);
                            floatingActionButton.setCompatElevation(CropImageView.DEFAULT_ASPECT_RATIO);
                        }
                        if (floatingActionButton.getShowMotionSpec() == null) {
                            floatingActionButton.setShowMotionSpecResource(q2.b.mtrl_fab_show_motion_spec);
                        }
                        if (floatingActionButton.getHideMotionSpec() == null) {
                            floatingActionButton.setHideMotionSpecResource(q2.b.mtrl_fab_hide_motion_spec);
                        }
                        floatingActionButton.c(bottomAppBar.f3349t0);
                        floatingActionButton.d(new a(bottomAppBar, 3));
                        floatingActionButton.e(bottomAppBar.f3350u0);
                    }
                    E.addOnLayoutChangeListener(this.f3354p);
                    bottomAppBar.K();
                }
            }
            coordinatorLayout.u(i7, bottomAppBar);
            super.l(coordinatorLayout, bottomAppBar, i7);
            return false;
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, c0.b
        public final boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i7, int i8) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            return bottomAppBar.getHideOnScroll() && super.t(coordinatorLayout, bottomAppBar, view2, view3, i7, i8);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new h();

        /* renamed from: b, reason: collision with root package name */
        public int f3355b;

        /* renamed from: c, reason: collision with root package name */
        public boolean f3356c;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3355b = parcel.readInt();
            this.f3356c = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeInt(this.f3355b);
            parcel.writeInt(this.f3356c ? 1 : 0);
        }
    }

    public BottomAppBar(Context context) {
        this(context, null);
    }

    public BottomAppBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.bottomAppBarStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BottomAppBar(android.content.Context r20, android.util.AttributeSet r21, int r22) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomappbar.BottomAppBar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public static void N(BottomAppBar bottomAppBar, View view) {
        c0.e eVar = (c0.e) view.getLayoutParams();
        eVar.f3010d = 17;
        int i7 = bottomAppBar.f3333d0;
        if (i7 == 1) {
            eVar.f3010d = 49;
        }
        if (i7 == 0) {
            eVar.f3010d |= 80;
        }
    }

    private ActionMenuView getActionMenuView() {
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.f3346q0;
    }

    private int getFabAlignmentAnimationDuration() {
        return l6.f.u0(getContext(), f3328w0, x2.d.DEFAULT_FADE_ANIM_DURATION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return G(this.f3331b0);
    }

    private float getFabTranslationY() {
        if (this.f3333d0 == 1) {
            return -getTopEdgeTreatment().f3376h;
        }
        return E() != null ? (-((getMeasuredHeight() + getBottomInset()) - r0.getMeasuredHeight())) / 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return this.f3348s0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.f3347r0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public i getTopEdgeTreatment() {
        return (i) this.V.f8263a.f8241a.f8309i;
    }

    public final FloatingActionButton D() {
        View E = E();
        if (E instanceof FloatingActionButton) {
            return (FloatingActionButton) E;
        }
        return null;
    }

    public final View E() {
        if (!(getParent() instanceof CoordinatorLayout)) {
            return null;
        }
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) getParent();
        List list = (List) ((k) coordinatorLayout.f1720b.f6893d).getOrDefault(this, null);
        ArrayList arrayList = coordinatorLayout.f1722d;
        arrayList.clear();
        if (list != null) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            if ((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton)) {
                return view;
            }
        }
        return null;
    }

    public final int F(ActionMenuView actionMenuView, int i7, boolean z6) {
        int i8 = 0;
        if (this.f3336g0 != 1 && (i7 != 1 || !z6)) {
            return 0;
        }
        boolean f7 = k0.f(this);
        int measuredWidth = f7 ? getMeasuredWidth() : 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if ((childAt.getLayoutParams() instanceof t3) && (((t3) childAt.getLayoutParams()).f670a & 8388615) == 8388611) {
                measuredWidth = f7 ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        int right = f7 ? actionMenuView.getRight() : actionMenuView.getLeft();
        int i10 = f7 ? this.f3347r0 : -this.f3348s0;
        if (getNavigationIcon() == null) {
            i8 = getResources().getDimensionPixelOffset(q2.e.m3_bottomappbar_horizontal_padding);
            if (!f7) {
                i8 = -i8;
            }
        }
        return measuredWidth - ((right + i10) + i8);
    }

    public final float G(int i7) {
        boolean f7 = k0.f(this);
        if (i7 != 1) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        View E = E();
        int i8 = f7 ? this.f3348s0 : this.f3347r0;
        return ((getMeasuredWidth() / 2) - ((this.f3335f0 == -1 || E == null) ? this.f3334e0 + i8 : ((E.getMeasuredWidth() / 2) + this.f3335f0) + i8)) * (f7 ? -1 : 1);
    }

    public final boolean H() {
        FloatingActionButton D = D();
        return D != null && D.i();
    }

    public final void I(int i7, boolean z6) {
        int i8 = d1.OVER_SCROLL_ALWAYS;
        if (!o0.c(this)) {
            this.f3343n0 = false;
            int i9 = this.f3342m0;
            if (i9 != 0) {
                this.f3342m0 = 0;
                getMenu().clear();
                n(i9);
                return;
            }
            return;
        }
        Animator animator = this.f3330a0;
        if (animator != null) {
            animator.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (!H()) {
            i7 = 0;
            z6 = false;
        }
        ActionMenuView actionMenuView = getActionMenuView();
        int i10 = 2;
        if (actionMenuView != null) {
            float fabAlignmentAnimationDuration = getFabAlignmentAnimationDuration();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(actionMenuView, y.c.ALPHA, 1.0f);
            ofFloat.setDuration((long) (0.8f * fabAlignmentAnimationDuration));
            if (Math.abs(actionMenuView.getTranslationX() - F(actionMenuView, i7, z6)) > 1.0f) {
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(actionMenuView, y.c.ALPHA, CropImageView.DEFAULT_ASPECT_RATIO);
                ofFloat2.setDuration((long) (fabAlignmentAnimationDuration * 0.2f));
                ofFloat2.addListener(new e(this, actionMenuView, i7, z6));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(ofFloat2, ofFloat);
                arrayList.add(animatorSet);
            } else if (actionMenuView.getAlpha() < 1.0f) {
                arrayList.add(ofFloat);
            }
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(arrayList);
        this.f3330a0 = animatorSet2;
        animatorSet2.addListener(new a(this, i10));
        this.f3330a0.start();
    }

    public final void J() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null || this.f3330a0 != null) {
            return;
        }
        actionMenuView.setAlpha(1.0f);
        if (H()) {
            M(actionMenuView, this.f3331b0, this.f3344o0, false);
        } else {
            M(actionMenuView, 0, false, false);
        }
    }

    public final void K() {
        getTopEdgeTreatment().f3377i = getFabTranslationX();
        this.V.p((this.f3344o0 && H() && this.f3333d0 == 1) ? 1.0f : CropImageView.DEFAULT_ASPECT_RATIO);
        View E = E();
        if (E != null) {
            E.setTranslationY(getFabTranslationY());
            E.setTranslationX(getFabTranslationX());
        }
    }

    public final void L(int i7) {
        float f7 = i7;
        if (f7 != getTopEdgeTreatment().f3375g) {
            getTopEdgeTreatment().f3375g = f7;
            this.V.invalidateSelf();
        }
    }

    public final void M(ActionMenuView actionMenuView, int i7, boolean z6, boolean z7) {
        f fVar = new f(this, actionMenuView, i7, z6);
        if (z7) {
            actionMenuView.post(fVar);
        } else {
            fVar.run();
        }
    }

    public ColorStateList getBackgroundTint() {
        return this.V.f8263a.f8246f;
    }

    @Override // c0.a
    public Behavior getBehavior() {
        if (this.f3345p0 == null) {
            this.f3345p0 = new Behavior();
        }
        return this.f3345p0;
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().f3376h;
    }

    public int getFabAlignmentMode() {
        return this.f3331b0;
    }

    public int getFabAlignmentModeEndMargin() {
        return this.f3335f0;
    }

    public int getFabAnchorMode() {
        return this.f3333d0;
    }

    public int getFabAnimationMode() {
        return this.f3332c0;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().f3374f;
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().f3373e;
    }

    public boolean getHideOnScroll() {
        return this.f3338i0;
    }

    public int getMenuAlignmentMode() {
        return this.f3336g0;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        l6.f.B0(this, this.V);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        if (z6) {
            Animator animator = this.f3330a0;
            if (animator != null) {
                animator.cancel();
            }
            Animator animator2 = this.W;
            if (animator2 != null) {
                animator2.cancel();
            }
            K();
            View E = E();
            if (E != null) {
                int i11 = d1.OVER_SCROLL_ALWAYS;
                if (o0.c(E)) {
                    E.post(new m2(1, E));
                }
            }
        }
        J();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        this.f3331b0 = savedState.f3355b;
        this.f3344o0 = savedState.f3356c;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3355b = this.f3331b0;
        savedState.f3356c = this.f3344o0;
        return savedState;
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        j0.b.h(this.V, colorStateList);
    }

    public void setCradleVerticalOffset(float f7) {
        if (f7 != getCradleVerticalOffset()) {
            getTopEdgeTreatment().N(f7);
            this.V.invalidateSelf();
            K();
        }
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        j jVar = this.V;
        jVar.n(f7);
        int i7 = jVar.f8263a.f8257q - jVar.i();
        Behavior behavior = getBehavior();
        behavior.f3317h = i7;
        if (behavior.f3316g == 1) {
            setTranslationY(behavior.f3315f + i7);
        }
    }

    public void setFabAlignmentMode(int i7) {
        setFabAlignmentModeAndReplaceMenu(i7, 0);
    }

    public void setFabAlignmentModeAndReplaceMenu(int i7, int i8) {
        this.f3342m0 = i8;
        int i9 = 1;
        this.f3343n0 = true;
        I(i7, this.f3344o0);
        if (this.f3331b0 != i7) {
            int i10 = d1.OVER_SCROLL_ALWAYS;
            if (o0.c(this)) {
                Animator animator = this.W;
                if (animator != null) {
                    animator.cancel();
                }
                ArrayList arrayList = new ArrayList();
                if (this.f3332c0 == 1) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(D(), y.c.TRANSLATION_X, G(i7));
                    ofFloat.setDuration(getFabAlignmentAnimationDuration());
                    arrayList.add(ofFloat);
                } else {
                    FloatingActionButton D = D();
                    if (D != null && !D.h()) {
                        D.g(new d(this, i7), true);
                    }
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(arrayList);
                animatorSet.setInterpolator(l6.f.v0(getContext(), f3329x0, r2.a.LINEAR_INTERPOLATOR));
                this.W = animatorSet;
                animatorSet.addListener(new a(this, i9));
                this.W.start();
            }
        }
        this.f3331b0 = i7;
    }

    public void setFabAlignmentModeEndMargin(int i7) {
        if (this.f3335f0 != i7) {
            this.f3335f0 = i7;
            K();
        }
    }

    public void setFabAnchorMode(int i7) {
        this.f3333d0 = i7;
        K();
        View E = E();
        if (E != null) {
            N(this, E);
            E.requestLayout();
            this.V.invalidateSelf();
        }
    }

    public void setFabAnimationMode(int i7) {
        this.f3332c0 = i7;
    }

    public void setFabCornerSize(float f7) {
        if (f7 != getTopEdgeTreatment().f3378j) {
            getTopEdgeTreatment().f3378j = f7;
            this.V.invalidateSelf();
        }
    }

    public void setFabCradleMargin(float f7) {
        if (f7 != getFabCradleMargin()) {
            getTopEdgeTreatment().f3374f = f7;
            this.V.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f7) {
        if (f7 != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().f3373e = f7;
            this.V.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z6) {
        this.f3338i0 = z6;
    }

    public void setMenuAlignmentMode(int i7) {
        if (this.f3336g0 != i7) {
            this.f3336g0 = i7;
            ActionMenuView actionMenuView = getActionMenuView();
            if (actionMenuView != null) {
                M(actionMenuView, this.f3331b0, H(), false);
            }
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null && this.U != null) {
            drawable = drawable.mutate();
            j0.b.g(drawable, this.U.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i7) {
        this.U = Integer.valueOf(i7);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }
}
