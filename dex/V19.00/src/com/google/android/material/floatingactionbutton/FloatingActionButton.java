package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.appcompat.widget.c0;
import androidx.appcompat.widget.z;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c0.b;
import c0.e;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.d;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.stateful.ExtendableSavedState;
import com.yalantis.ucrop.view.CropImageView;
import f3.a;
import g3.i;
import g3.j;
import g3.k;
import g3.q;
import g3.s;
import java.util.ArrayList;
import java.util.Iterator;
import l6.f;
import p0.d1;
import p0.o0;
import p3.a0;
import p3.p;
import q2.c;
import q2.l;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements a, a0, c0.a {
    public static final int NO_CUSTOM_SIZE = 0;
    public static final int SIZE_AUTO = -1;
    public static final int SIZE_MINI = 1;
    public static final int SIZE_NORMAL = 0;

    /* renamed from: q, reason: collision with root package name */
    public static final int f3722q = l.Widget_Design_FloatingActionButton;

    /* renamed from: b, reason: collision with root package name */
    public ColorStateList f3723b;

    /* renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f3724c;

    /* renamed from: d, reason: collision with root package name */
    public ColorStateList f3725d;

    /* renamed from: e, reason: collision with root package name */
    public PorterDuff.Mode f3726e;

    /* renamed from: f, reason: collision with root package name */
    public ColorStateList f3727f;

    /* renamed from: g, reason: collision with root package name */
    public int f3728g;

    /* renamed from: h, reason: collision with root package name */
    public int f3729h;

    /* renamed from: i, reason: collision with root package name */
    public int f3730i;

    /* renamed from: j, reason: collision with root package name */
    public int f3731j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f3732k;

    /* renamed from: l, reason: collision with root package name */
    public final Rect f3733l;

    /* renamed from: m, reason: collision with root package name */
    public final Rect f3734m;

    /* renamed from: n, reason: collision with root package name */
    public final c0 f3735n;

    /* renamed from: o, reason: collision with root package name */
    public final n0.a f3736o;

    /* renamed from: p, reason: collision with root package name */
    public s f3737p;

    public static class BaseBehavior<T extends FloatingActionButton> extends b {

        /* renamed from: a, reason: collision with root package name */
        public Rect f3738a;

        /* renamed from: b, reason: collision with root package name */
        public final boolean f3739b;

        public BaseBehavior() {
            this.f3739b = true;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.FloatingActionButton_Behavior_Layout);
            this.f3739b = obtainStyledAttributes.getBoolean(m.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            obtainStyledAttributes.recycle();
        }

        @Override // c0.b
        public final boolean e(View view, Rect rect) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            int left = floatingActionButton.getLeft();
            Rect rect2 = floatingActionButton.f3733l;
            rect.set(left + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        @Override // c0.b
        public final void g(e eVar) {
            if (eVar.f3014h == 0) {
                eVar.f3014h = 80;
            }
        }

        @Override // c0.b
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                w(coordinatorLayout, (AppBarLayout) view2, floatingActionButton);
                return false;
            }
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if (!(layoutParams instanceof e) || !(((e) layoutParams).f3007a instanceof BottomSheetBehavior)) {
                return false;
            }
            x(view2, floatingActionButton);
            return false;
        }

        @Override // c0.b
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            ArrayList o6 = coordinatorLayout.o(floatingActionButton);
            int size = o6.size();
            int i8 = 0;
            for (int i9 = 0; i9 < size; i9++) {
                View view2 = (View) o6.get(i9);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof e) && (((e) layoutParams).f3007a instanceof BottomSheetBehavior) && x(view2, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (w(coordinatorLayout, (AppBarLayout) view2, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.u(i7, floatingActionButton);
            Rect rect = floatingActionButton.f3733l;
            if (rect == null || rect.centerX() <= 0 || rect.centerY() <= 0) {
                return true;
            }
            e eVar = (e) floatingActionButton.getLayoutParams();
            int i10 = floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) eVar).rightMargin ? rect.right : floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) eVar).leftMargin ? -rect.left : 0;
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) {
                i8 = rect.bottom;
            } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) eVar).topMargin) {
                i8 = -rect.top;
            }
            if (i8 != 0) {
                int i11 = d1.OVER_SCROLL_ALWAYS;
                floatingActionButton.offsetTopAndBottom(i8);
            }
            if (i10 == 0) {
                return true;
            }
            int i12 = d1.OVER_SCROLL_ALWAYS;
            floatingActionButton.offsetLeftAndRight(i10);
            return true;
        }

        public final boolean w(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!(this.f3739b && ((e) floatingActionButton.getLayoutParams()).f3012f == appBarLayout.getId() && floatingActionButton.getUserSetVisibility() == 0)) {
                return false;
            }
            if (this.f3738a == null) {
                this.f3738a = new Rect();
            }
            Rect rect = this.f3738a;
            com.google.android.material.internal.e.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.g(null, false);
            } else {
                floatingActionButton.l(null, false);
            }
            return true;
        }

        public final boolean x(View view, FloatingActionButton floatingActionButton) {
            if (!(this.f3739b && ((e) floatingActionButton.getLayoutParams()).f3012f == view.getId() && floatingActionButton.getUserSetVisibility() == 0)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((e) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.g(null, false);
            } else {
                floatingActionButton.l(null, false);
            }
            return true;
        }
    }

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.floatingActionButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public FloatingActionButton(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.FloatingActionButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private q getImpl() {
        if (this.f3737p == null) {
            this.f3737p = new s(this, new v2.a(4, this));
        }
        return this.f3737p;
    }

    public final void c(com.google.android.material.bottomappbar.a aVar) {
        q impl = getImpl();
        if (impl.f6513t == null) {
            impl.f6513t = new ArrayList();
        }
        impl.f6513t.add(aVar);
    }

    public final void d(com.google.android.material.bottomappbar.a aVar) {
        q impl = getImpl();
        if (impl.f6512s == null) {
            impl.f6512s = new ArrayList();
        }
        impl.f6512s.add(aVar);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().j(getDrawableState());
    }

    public final void e(com.google.android.material.bottomappbar.b bVar) {
        q impl = getImpl();
        i iVar = new i(this, bVar);
        if (impl.f6514u == null) {
            impl.f6514u = new ArrayList();
        }
        impl.f6514u.add(iVar);
    }

    public final int f(int i7) {
        int i8 = this.f3729h;
        if (i8 != 0) {
            return i8;
        }
        Resources resources = getResources();
        return i7 != -1 ? i7 != 1 ? resources.getDimensionPixelSize(q2.e.design_fab_size_normal) : resources.getDimensionPixelSize(q2.e.design_fab_size_mini) : Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470 ? f(1) : f(0);
    }

    public final void g(d dVar, boolean z6) {
        q impl = getImpl();
        i6.i iVar = dVar == null ? null : new i6.i(this, dVar, 18);
        if (impl.f6515v.getVisibility() == 0) {
            if (impl.f6511r == 1) {
                return;
            }
        } else if (impl.f6511r != 2) {
            return;
        }
        Animator animator = impl.f6505l;
        if (animator != null) {
            animator.cancel();
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        FloatingActionButton floatingActionButton = impl.f6515v;
        if (!o0.c(floatingActionButton) || floatingActionButton.isInEditMode()) {
            floatingActionButton.a(z6 ? 8 : 4, z6);
            if (iVar != null) {
                ((f) iVar.f6848b).d0((FloatingActionButton) iVar.f6849c);
                return;
            }
            return;
        }
        r2.e eVar = impl.f6507n;
        AnimatorSet b7 = eVar != null ? impl.b(eVar, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO) : impl.c(CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, 0.4f, q.F, q.G);
        b7.addListener(new j(impl, z6, iVar));
        ArrayList arrayList = impl.f6513t;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b7.addListener((Animator.AnimatorListener) it.next());
            }
        }
        b7.start();
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.f3723b;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.f3724c;
    }

    @Override // c0.a
    public b getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().e();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().f6502i;
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().f6503j;
    }

    public Drawable getContentBackground() {
        return getImpl().f6498e;
    }

    public int getCustomSize() {
        return this.f3729h;
    }

    public int getExpandedComponentIdHint() {
        return this.f3736o.f7561b;
    }

    public r2.e getHideMotionSpec() {
        return getImpl().f6507n;
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.f3727f;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.f3727f;
    }

    public p getShapeAppearanceModel() {
        p pVar = getImpl().f6494a;
        pVar.getClass();
        return pVar;
    }

    public r2.e getShowMotionSpec() {
        return getImpl().f6506m;
    }

    public int getSize() {
        return this.f3728g;
    }

    public int getSizeDimension() {
        return f(this.f3728g);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.f3725d;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        return this.f3726e;
    }

    public boolean getUseCompatPadding() {
        return this.f3732k;
    }

    public final boolean h() {
        q impl = getImpl();
        if (impl.f6515v.getVisibility() == 0) {
            if (impl.f6511r != 1) {
                return false;
            }
        } else if (impl.f6511r == 2) {
            return false;
        }
        return true;
    }

    public final boolean i() {
        q impl = getImpl();
        if (impl.f6515v.getVisibility() != 0) {
            if (impl.f6511r != 2) {
                return false;
            }
        } else if (impl.f6511r == 1) {
            return false;
        }
        return true;
    }

    public final void j(Rect rect) {
        int i7 = rect.left;
        Rect rect2 = this.f3733l;
        rect.left = i7 + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom -= rect2.bottom;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().h();
    }

    public final void k() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.f3725d;
        if (colorStateList == null) {
            drawable.clearColorFilter();
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.f3726e;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(z.c(colorForState, mode));
    }

    public final void l(com.google.android.material.bottomappbar.c cVar, boolean z6) {
        q impl = getImpl();
        i6.i iVar = cVar == null ? null : new i6.i(this, cVar, 18);
        if (impl.f6515v.getVisibility() != 0) {
            if (impl.f6511r == 2) {
                return;
            }
        } else if (impl.f6511r != 1) {
            return;
        }
        Animator animator = impl.f6505l;
        if (animator != null) {
            animator.cancel();
        }
        boolean z7 = impl.f6506m == null;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        FloatingActionButton floatingActionButton = impl.f6515v;
        boolean z8 = o0.c(floatingActionButton) && !floatingActionButton.isInEditMode();
        Matrix matrix = impl.A;
        if (!z8) {
            floatingActionButton.a(0, z6);
            floatingActionButton.setAlpha(1.0f);
            floatingActionButton.setScaleY(1.0f);
            floatingActionButton.setScaleX(1.0f);
            impl.f6509p = 1.0f;
            impl.a(1.0f, matrix);
            floatingActionButton.setImageMatrix(matrix);
            if (iVar != null) {
                ((f) iVar.f6848b).g0();
                return;
            }
            return;
        }
        if (floatingActionButton.getVisibility() != 0) {
            float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
            floatingActionButton.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
            floatingActionButton.setScaleY(z7 ? 0.4f : 0.0f);
            floatingActionButton.setScaleX(z7 ? 0.4f : 0.0f);
            if (z7) {
                f7 = 0.4f;
            }
            impl.f6509p = f7;
            impl.a(f7, matrix);
            floatingActionButton.setImageMatrix(matrix);
        }
        r2.e eVar = impl.f6506m;
        AnimatorSet b7 = eVar != null ? impl.b(eVar, 1.0f, 1.0f, 1.0f) : impl.c(1.0f, 1.0f, 1.0f, q.D, q.E);
        b7.addListener(new k(impl, z6, iVar));
        ArrayList arrayList = impl.f6512s;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b7.addListener((Animator.AnimatorListener) it.next());
            }
        }
        b7.start();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        q impl = getImpl();
        p3.j jVar = impl.f6495b;
        FloatingActionButton floatingActionButton = impl.f6515v;
        if (jVar != null) {
            f.B0(floatingActionButton, jVar);
        }
        int i7 = 1;
        if (!(impl instanceof s)) {
            ViewTreeObserver viewTreeObserver = floatingActionButton.getViewTreeObserver();
            if (impl.B == null) {
                impl.B = new c0.f(i7, impl);
            }
            viewTreeObserver.addOnPreDrawListener(impl.B);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        q impl = getImpl();
        ViewTreeObserver viewTreeObserver = impl.f6515v.getViewTreeObserver();
        c0.f fVar = impl.B;
        if (fVar != null) {
            viewTreeObserver.removeOnPreDrawListener(fVar);
            impl.B = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i7, int i8) {
        int sizeDimension = getSizeDimension();
        this.f3730i = (sizeDimension - this.f3731j) / 2;
        getImpl().r();
        int min = Math.min(View.resolveSize(sizeDimension, i7), View.resolveSize(sizeDimension, i8));
        Rect rect = this.f3733l;
        setMeasuredDimension(rect.left + min + rect.right, min + rect.top + rect.bottom);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
        super.onRestoreInstanceState(extendableSavedState.f1829a);
        Bundle bundle = (Bundle) extendableSavedState.f4205b.getOrDefault("expandableWidgetHelper", null);
        bundle.getClass();
        n0.a aVar = this.f3736o;
        aVar.getClass();
        aVar.f7560a = bundle.getBoolean("expanded", false);
        aVar.f7561b = bundle.getInt("expandedComponentIdHint", 0);
        if (aVar.f7560a) {
            ViewParent parent = ((View) aVar.f7562c).getParent();
            if (parent instanceof CoordinatorLayout) {
                ((CoordinatorLayout) parent).m((View) aVar.f7562c);
            }
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (onSaveInstanceState == null) {
            onSaveInstanceState = new Bundle();
        }
        ExtendableSavedState extendableSavedState = new ExtendableSavedState(onSaveInstanceState);
        q.k kVar = extendableSavedState.f4205b;
        n0.a aVar = this.f3736o;
        aVar.getClass();
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", aVar.f7560a);
        bundle.putInt("expandedComponentIdHint", aVar.f7561b);
        kVar.put("expandableWidgetHelper", bundle);
        return extendableSavedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            Rect rect = this.f3734m;
            rect.set(0, 0, measuredWidth, measuredHeight);
            j(rect);
            s sVar = this.f3737p;
            int i7 = -(sVar.f6499f ? Math.max((sVar.f6504k - sVar.f6515v.getSizeDimension()) / 2, 0) : 0);
            rect.inset(i7, i7);
            if (!rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i7) {
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.f3723b != colorStateList) {
            this.f3723b = colorStateList;
            q impl = getImpl();
            p3.j jVar = impl.f6495b;
            if (jVar != null) {
                jVar.setTintList(colorStateList);
            }
            g3.b bVar = impl.f6497d;
            if (bVar != null) {
                if (colorStateList != null) {
                    bVar.f6451m = colorStateList.getColorForState(bVar.getState(), bVar.f6451m);
                }
                bVar.f6454p = colorStateList;
                bVar.f6452n = true;
                bVar.invalidateSelf();
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.f3724c != mode) {
            this.f3724c = mode;
            p3.j jVar = getImpl().f6495b;
            if (jVar != null) {
                jVar.setTintMode(mode);
            }
        }
    }

    public void setCompatElevation(float f7) {
        q impl = getImpl();
        if (impl.f6501h != f7) {
            impl.f6501h = f7;
            impl.k(f7, impl.f6502i, impl.f6503j);
        }
    }

    public void setCompatElevationResource(int i7) {
        setCompatElevation(getResources().getDimension(i7));
    }

    public void setCompatHoveredFocusedTranslationZ(float f7) {
        q impl = getImpl();
        if (impl.f6502i != f7) {
            impl.f6502i = f7;
            impl.k(impl.f6501h, f7, impl.f6503j);
        }
    }

    public void setCompatHoveredFocusedTranslationZResource(int i7) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i7));
    }

    public void setCompatPressedTranslationZ(float f7) {
        q impl = getImpl();
        if (impl.f6503j != f7) {
            impl.f6503j = f7;
            impl.k(impl.f6501h, impl.f6502i, f7);
        }
    }

    public void setCompatPressedTranslationZResource(int i7) {
        setCompatPressedTranslationZ(getResources().getDimension(i7));
    }

    public void setCustomSize(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        if (i7 != this.f3729h) {
            this.f3729h = i7;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        p3.j jVar = getImpl().f6495b;
        if (jVar != null) {
            jVar.n(f7);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z6) {
        if (z6 != getImpl().f6499f) {
            getImpl().f6499f = z6;
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(int i7) {
        this.f3736o.f7561b = i7;
    }

    public void setHideMotionSpec(r2.e eVar) {
        getImpl().f6507n = eVar;
    }

    public void setHideMotionSpecResource(int i7) {
        setHideMotionSpec(r2.e.b(getContext(), i7));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            q impl = getImpl();
            float f7 = impl.f6509p;
            impl.f6509p = f7;
            Matrix matrix = impl.A;
            impl.a(f7, matrix);
            impl.f6515v.setImageMatrix(matrix);
            if (this.f3725d != null) {
                k();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i7) {
        this.f3735n.d(i7);
        k();
    }

    public void setMaxImageSize(int i7) {
        this.f3731j = i7;
        q impl = getImpl();
        if (impl.f6510q != i7) {
            impl.f6510q = i7;
            float f7 = impl.f6509p;
            impl.f6509p = f7;
            Matrix matrix = impl.A;
            impl.a(f7, matrix);
            impl.f6515v.setImageMatrix(matrix);
        }
    }

    public void setRippleColor(int i7) {
        setRippleColor(ColorStateList.valueOf(i7));
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.f3727f != colorStateList) {
            this.f3727f = colorStateList;
            getImpl().n(this.f3727f);
        }
    }

    @Override // android.view.View
    public void setScaleX(float f7) {
        super.setScaleX(f7);
        getImpl().l();
    }

    @Override // android.view.View
    public void setScaleY(float f7) {
        super.setScaleY(f7);
        getImpl().l();
    }

    public void setShadowPaddingEnabled(boolean z6) {
        q impl = getImpl();
        impl.f6500g = z6;
        impl.r();
    }

    @Override // p3.a0
    public void setShapeAppearanceModel(p pVar) {
        getImpl().o(pVar);
    }

    public void setShowMotionSpec(r2.e eVar) {
        getImpl().f6506m = eVar;
    }

    public void setShowMotionSpecResource(int i7) {
        setShowMotionSpec(r2.e.b(getContext(), i7));
    }

    public void setSize(int i7) {
        this.f3729h = 0;
        if (i7 != this.f3728g) {
            this.f3728g = i7;
            requestLayout();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        setBackgroundTintMode(mode);
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.f3725d != colorStateList) {
            this.f3725d = colorStateList;
            k();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.f3726e != mode) {
            this.f3726e = mode;
            k();
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f7) {
        super.setTranslationX(f7);
        getImpl().m();
    }

    @Override // android.view.View
    public void setTranslationY(float f7) {
        super.setTranslationY(f7);
        getImpl().m();
    }

    @Override // android.view.View
    public void setTranslationZ(float f7) {
        super.setTranslationZ(f7);
        getImpl().m();
    }

    public void setUseCompatPadding(boolean z6) {
        if (this.f3732k != z6) {
            this.f3732k = z6;
            getImpl().i();
        }
    }

    @Override // com.google.android.material.internal.VisibilityAwareImageButton, android.widget.ImageView, android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
    }
}
