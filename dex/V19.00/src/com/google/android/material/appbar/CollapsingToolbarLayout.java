package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import p0.d1;
import p0.l0;
import p0.l2;
import p0.m0;
import p0.o0;
import p0.p0;
import q2.g;
import q2.l;
import q2.m;
import s2.f;
import s2.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    public static final int F = l.Widget_Design_CollapsingToolbar;
    public static final int TITLE_COLLAPSE_MODE_FADE = 1;
    public static final int TITLE_COLLAPSE_MODE_SCALE = 0;
    public l2 A;
    public int B;
    public boolean C;
    public int D;
    public boolean E;

    /* renamed from: a, reason: collision with root package name */
    public boolean f3240a;

    /* renamed from: b, reason: collision with root package name */
    public final int f3241b;

    /* renamed from: c, reason: collision with root package name */
    public ViewGroup f3242c;

    /* renamed from: d, reason: collision with root package name */
    public View f3243d;

    /* renamed from: e, reason: collision with root package name */
    public View f3244e;

    /* renamed from: f, reason: collision with root package name */
    public int f3245f;

    /* renamed from: g, reason: collision with root package name */
    public int f3246g;

    /* renamed from: h, reason: collision with root package name */
    public int f3247h;

    /* renamed from: i, reason: collision with root package name */
    public int f3248i;

    /* renamed from: j, reason: collision with root package name */
    public final Rect f3249j;

    /* renamed from: k, reason: collision with root package name */
    public final com.google.android.material.internal.d f3250k;

    /* renamed from: l, reason: collision with root package name */
    public final e3.a f3251l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f3252m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f3253n;

    /* renamed from: o, reason: collision with root package name */
    public Drawable f3254o;

    /* renamed from: p, reason: collision with root package name */
    public Drawable f3255p;

    /* renamed from: q, reason: collision with root package name */
    public int f3256q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f3257r;

    /* renamed from: s, reason: collision with root package name */
    public ValueAnimator f3258s;

    /* renamed from: t, reason: collision with root package name */
    public long f3259t;

    /* renamed from: u, reason: collision with root package name */
    public final TimeInterpolator f3260u;

    /* renamed from: v, reason: collision with root package name */
    public final TimeInterpolator f3261v;

    /* renamed from: w, reason: collision with root package name */
    public int f3262w;

    /* renamed from: x, reason: collision with root package name */
    public f f3263x;

    /* renamed from: y, reason: collision with root package name */
    public int f3264y;

    /* renamed from: z, reason: collision with root package name */
    public int f3265z;

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.collapsingToolbarLayoutStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CollapsingToolbarLayout(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            Method dump skipped, instructions count: 488
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public static k b(View view) {
        k kVar = (k) view.getTag(g.view_offset_helper);
        if (kVar != null) {
            return kVar;
        }
        k kVar2 = new k(view);
        view.setTag(g.view_offset_helper, kVar2);
        return kVar2;
    }

    private int getDefaultContentScrimColorForTitleCollapseFadeMode() {
        Context context = getContext();
        TypedValue s02 = l6.f.s0(context, q2.c.colorSurfaceContainer);
        ColorStateList colorStateList = null;
        if (s02 != null) {
            int i7 = s02.resourceId;
            if (i7 != 0) {
                colorStateList = g0.g.b(context, i7);
            } else {
                int i8 = s02.data;
                if (i8 != 0) {
                    colorStateList = ColorStateList.valueOf(i8);
                }
            }
        }
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        float dimension = getResources().getDimension(q2.e.design_appbar_elevation);
        e3.a aVar = this.f3251l;
        return aVar.a(dimension, aVar.f6369d);
    }

    public final void a() {
        if (this.f3240a) {
            ViewGroup viewGroup = null;
            this.f3242c = null;
            this.f3243d = null;
            int i7 = this.f3241b;
            if (i7 != -1) {
                ViewGroup viewGroup2 = (ViewGroup) findViewById(i7);
                this.f3242c = viewGroup2;
                if (viewGroup2 != null) {
                    ViewParent parent = viewGroup2.getParent();
                    View view = viewGroup2;
                    while (parent != this && parent != null) {
                        if (parent instanceof View) {
                            view = (View) parent;
                        }
                        parent = parent.getParent();
                        view = view;
                    }
                    this.f3243d = view;
                }
            }
            if (this.f3242c == null) {
                int childCount = getChildCount();
                for (int i8 = 0; i8 < childCount; i8++) {
                    View childAt = getChildAt(i8);
                    if ((childAt instanceof Toolbar) || (childAt instanceof android.widget.Toolbar)) {
                        viewGroup = (ViewGroup) childAt;
                        break;
                    }
                }
                this.f3242c = viewGroup;
            }
            c();
            this.f3240a = false;
        }
    }

    public final void c() {
        View view;
        if (!this.f3252m && (view = this.f3244e) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f3244e);
            }
        }
        if (!this.f3252m || this.f3242c == null) {
            return;
        }
        if (this.f3244e == null) {
            this.f3244e = new View(getContext());
        }
        if (this.f3244e.getParent() == null) {
            this.f3242c.addView(this.f3244e, -1, -1);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof s2.e;
    }

    public final void d() {
        if (this.f3254o == null && this.f3255p == null) {
            return;
        }
        setScrimsShown(getHeight() + this.f3264y < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        a();
        if (this.f3242c == null && (drawable = this.f3254o) != null && this.f3256q > 0) {
            drawable.mutate().setAlpha(this.f3256q);
            this.f3254o.draw(canvas);
        }
        if (this.f3252m && this.f3253n) {
            ViewGroup viewGroup = this.f3242c;
            com.google.android.material.internal.d dVar = this.f3250k;
            if (viewGroup == null || this.f3254o == null || this.f3256q <= 0 || this.f3265z != 1 || dVar.f3807b >= dVar.f3813e) {
                dVar.d(canvas);
            } else {
                int save = canvas.save();
                canvas.clipRect(this.f3254o.getBounds(), Region.Op.DIFFERENCE);
                dVar.d(canvas);
                canvas.restoreToCount(save);
            }
        }
        if (this.f3255p == null || this.f3256q <= 0) {
            return;
        }
        l2 l2Var = this.A;
        int d7 = l2Var != null ? l2Var.d() : 0;
        if (d7 > 0) {
            this.f3255p.setBounds(0, -this.f3264y, getWidth(), d7 - this.f3264y);
            this.f3255p.mutate().setAlpha(this.f3256q);
            this.f3255p.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j7) {
        boolean z6;
        View view2;
        Drawable drawable = this.f3254o;
        if (drawable == null || this.f3256q <= 0 || ((view2 = this.f3243d) == null || view2 == this ? view != this.f3242c : view != view2)) {
            z6 = false;
        } else {
            int width = getWidth();
            int height = getHeight();
            if (this.f3265z == 1 && view != null && this.f3252m) {
                height = view.getBottom();
            }
            drawable.setBounds(0, 0, width, height);
            this.f3254o.mutate().setAlpha(this.f3256q);
            this.f3254o.draw(canvas);
            z6 = true;
        }
        return super.drawChild(canvas, view, j7) || z6;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        ColorStateList colorStateList;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f3255p;
        boolean z6 = false;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.f3254o;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        com.google.android.material.internal.d dVar = this.f3250k;
        if (dVar != null) {
            dVar.R = drawableState;
            ColorStateList colorStateList2 = dVar.f3833o;
            if ((colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = dVar.f3831n) != null && colorStateList.isStateful())) {
                dVar.i(false);
                z6 = true;
            }
            state |= z6;
        }
        if (state) {
            invalidate();
        }
    }

    public final void e(int i7, int i8, int i9, int i10, boolean z6) {
        View view;
        int i11;
        int i12;
        int i13;
        if (!this.f3252m || (view = this.f3244e) == null) {
            return;
        }
        int i14 = d1.OVER_SCROLL_ALWAYS;
        int i15 = 0;
        boolean z7 = o0.b(view) && this.f3244e.getVisibility() == 0;
        this.f3253n = z7;
        if (z7 || z6) {
            boolean z8 = m0.d(this) == 1;
            View view2 = this.f3243d;
            if (view2 == null) {
                view2 = this.f3242c;
            }
            int height = ((getHeight() - b(view2).f8689b) - view2.getHeight()) - ((FrameLayout.LayoutParams) ((s2.e) view2.getLayoutParams())).bottomMargin;
            View view3 = this.f3244e;
            Rect rect = this.f3249j;
            com.google.android.material.internal.e.a(this, view3, rect);
            ViewGroup viewGroup = this.f3242c;
            if (viewGroup instanceof Toolbar) {
                Toolbar toolbar = (Toolbar) viewGroup;
                i15 = toolbar.getTitleMarginStart();
                i12 = toolbar.getTitleMarginEnd();
                i13 = toolbar.getTitleMarginTop();
                i11 = toolbar.getTitleMarginBottom();
            } else if (viewGroup instanceof android.widget.Toolbar) {
                android.widget.Toolbar toolbar2 = (android.widget.Toolbar) viewGroup;
                i15 = toolbar2.getTitleMarginStart();
                i12 = toolbar2.getTitleMarginEnd();
                i13 = toolbar2.getTitleMarginTop();
                i11 = toolbar2.getTitleMarginBottom();
            } else {
                i11 = 0;
                i12 = 0;
                i13 = 0;
            }
            int i16 = rect.left + (z8 ? i12 : i15);
            int i17 = rect.top + height + i13;
            int i18 = rect.right;
            if (!z8) {
                i15 = i12;
            }
            int i19 = i18 - i15;
            int i20 = (rect.bottom + height) - i11;
            com.google.android.material.internal.d dVar = this.f3250k;
            Rect rect2 = dVar.f3819h;
            if (rect2.left != i16 || rect2.top != i17 || rect2.right != i19 || rect2.bottom != i20) {
                rect2.set(i16, i17, i19, i20);
                dVar.S = true;
            }
            int i21 = z8 ? this.f3247h : this.f3245f;
            int i22 = rect.top + this.f3246g;
            int i23 = (i9 - i7) - (z8 ? this.f3245f : this.f3247h);
            int i24 = (i10 - i8) - this.f3248i;
            Rect rect3 = dVar.f3817g;
            if (rect3.left != i21 || rect3.top != i22 || rect3.right != i23 || rect3.bottom != i24) {
                rect3.set(i21, i22, i23, i24);
                dVar.S = true;
            }
            dVar.i(z6);
        }
    }

    public final void f() {
        if (this.f3242c != null && this.f3252m && TextUtils.isEmpty(this.f3250k.G)) {
            ViewGroup viewGroup = this.f3242c;
            setTitle(viewGroup instanceof Toolbar ? ((Toolbar) viewGroup).getTitle() : viewGroup instanceof android.widget.Toolbar ? ((android.widget.Toolbar) viewGroup).getTitle() : null);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        s2.e eVar = new s2.e(-1, -1);
        eVar.f8672a = 0;
        eVar.f8673b = 0.5f;
        return eVar;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateDefaultLayoutParams() {
        s2.e eVar = new s2.e(-1, -1);
        eVar.f8672a = 0;
        eVar.f8673b = 0.5f;
        return eVar;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        s2.e eVar = new s2.e(layoutParams);
        eVar.f8672a = 0;
        eVar.f8673b = 0.5f;
        return eVar;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        s2.e eVar = new s2.e(context, attributeSet);
        eVar.f8672a = 0;
        eVar.f8673b = 0.5f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.CollapsingToolbarLayout_Layout);
        eVar.f8672a = obtainStyledAttributes.getInt(m.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
        eVar.f8673b = obtainStyledAttributes.getFloat(m.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f);
        obtainStyledAttributes.recycle();
        return eVar;
    }

    public int getCollapsedTitleGravity() {
        return this.f3250k.f3825k;
    }

    public float getCollapsedTitleTextSize() {
        return this.f3250k.f3829m;
    }

    public Typeface getCollapsedTitleTypeface() {
        Typeface typeface = this.f3250k.f3844w;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public Drawable getContentScrim() {
        return this.f3254o;
    }

    public int getExpandedTitleGravity() {
        return this.f3250k.f3823j;
    }

    public int getExpandedTitleMarginBottom() {
        return this.f3248i;
    }

    public int getExpandedTitleMarginEnd() {
        return this.f3247h;
    }

    public int getExpandedTitleMarginStart() {
        return this.f3245f;
    }

    public int getExpandedTitleMarginTop() {
        return this.f3246g;
    }

    public float getExpandedTitleTextSize() {
        return this.f3250k.f3827l;
    }

    public Typeface getExpandedTitleTypeface() {
        Typeface typeface = this.f3250k.f3847z;
        return typeface != null ? typeface : Typeface.DEFAULT;
    }

    public int getHyphenationFrequency() {
        return this.f3250k.f3838q0;
    }

    public int getLineCount() {
        StaticLayout staticLayout = this.f3250k.f3822i0;
        if (staticLayout != null) {
            return staticLayout.getLineCount();
        }
        return 0;
    }

    public float getLineSpacingAdd() {
        return this.f3250k.f3822i0.getSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.f3250k.f3822i0.getSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.f3250k.f3832n0;
    }

    public int getScrimAlpha() {
        return this.f3256q;
    }

    public long getScrimAnimationDuration() {
        return this.f3259t;
    }

    public int getScrimVisibleHeightTrigger() {
        int i7 = this.f3262w;
        if (i7 >= 0) {
            return i7 + this.B + this.D;
        }
        l2 l2Var = this.A;
        int d7 = l2Var != null ? l2Var.d() : 0;
        int i8 = d1.OVER_SCROLL_ALWAYS;
        int d8 = l0.d(this);
        return d8 > 0 ? Math.min((d8 * 2) + d7, getHeight()) : getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.f3255p;
    }

    public CharSequence getTitle() {
        if (this.f3252m) {
            return this.f3250k.G;
        }
        return null;
    }

    public int getTitleCollapseMode() {
        return this.f3265z;
    }

    public TimeInterpolator getTitlePositionInterpolator() {
        return this.f3250k.V;
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.f3250k.F;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.f3265z == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
            int i7 = d1.OVER_SCROLL_ALWAYS;
            setFitsSystemWindows(l0.b(appBarLayout));
            if (this.f3263x == null) {
                this.f3263x = new f(this);
            }
            f fVar = this.f3263x;
            if (appBarLayout.f3211h == null) {
                appBarLayout.f3211h = new ArrayList();
            }
            if (fVar != null && !appBarLayout.f3211h.contains(fVar)) {
                appBarLayout.f3211h.add(fVar);
            }
            p0.c(this);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f3250k.h(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ArrayList arrayList;
        ViewParent parent = getParent();
        f fVar = this.f3263x;
        if (fVar != null && (parent instanceof AppBarLayout) && (arrayList = ((AppBarLayout) parent).f3211h) != null) {
            arrayList.remove(fVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        l2 l2Var = this.A;
        if (l2Var != null) {
            int d7 = l2Var.d();
            int childCount = getChildCount();
            for (int i11 = 0; i11 < childCount; i11++) {
                View childAt = getChildAt(i11);
                int i12 = d1.OVER_SCROLL_ALWAYS;
                if (!l0.b(childAt) && childAt.getTop() < d7) {
                    childAt.offsetTopAndBottom(d7);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            k b7 = b(getChildAt(i13));
            View view = b7.f8688a;
            b7.f8689b = view.getTop();
            b7.f8690c = view.getLeft();
        }
        e(i7, i8, i9, i10, false);
        f();
        d();
        int childCount3 = getChildCount();
        for (int i14 = 0; i14 < childCount3; i14++) {
            b(getChildAt(i14)).a();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        int measuredHeight;
        int measuredHeight2;
        a();
        super.onMeasure(i7, i8);
        int mode = View.MeasureSpec.getMode(i8);
        l2 l2Var = this.A;
        int d7 = l2Var != null ? l2Var.d() : 0;
        if ((mode == 0 || this.C) && d7 > 0) {
            this.B = d7;
            super.onMeasure(i7, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + d7, v.c.EXACTLY));
        }
        if (this.E) {
            com.google.android.material.internal.d dVar = this.f3250k;
            if (dVar.f3832n0 > 1) {
                f();
                e(0, 0, getMeasuredWidth(), getMeasuredHeight(), true);
                int i9 = dVar.f3835p;
                if (i9 > 1) {
                    TextPaint textPaint = dVar.U;
                    textPaint.setTextSize(dVar.f3827l);
                    textPaint.setTypeface(dVar.f3847z);
                    textPaint.setLetterSpacing(dVar.f3818g0);
                    this.D = (i9 - 1) * Math.round(textPaint.descent() + (-textPaint.ascent()));
                    super.onMeasure(i7, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + this.D, v.c.EXACTLY));
                }
            }
        }
        ViewGroup viewGroup = this.f3242c;
        if (viewGroup != null) {
            View view = this.f3243d;
            if (view == null || view == this) {
                ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    measuredHeight = viewGroup.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                } else {
                    measuredHeight = viewGroup.getMeasuredHeight();
                }
                setMinimumHeight(measuredHeight);
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
                measuredHeight2 = view.getMeasuredHeight() + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
            } else {
                measuredHeight2 = view.getMeasuredHeight();
            }
            setMinimumHeight(measuredHeight2);
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        Drawable drawable = this.f3254o;
        if (drawable != null) {
            ViewGroup viewGroup = this.f3242c;
            if (this.f3265z == 1 && viewGroup != null && this.f3252m) {
                i8 = viewGroup.getBottom();
            }
            drawable.setBounds(0, 0, i7, i8);
        }
    }

    public void setCollapsedTitleGravity(int i7) {
        this.f3250k.l(i7);
    }

    public void setCollapsedTitleTextAppearance(int i7) {
        this.f3250k.k(i7);
    }

    public void setCollapsedTitleTextColor(int i7) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i7));
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (dVar.f3833o != colorStateList) {
            dVar.f3833o = colorStateList;
            dVar.i(false);
        }
    }

    public void setCollapsedTitleTextSize(float f7) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (dVar.f3829m != f7) {
            dVar.f3829m = f7;
            dVar.i(false);
        }
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (dVar.m(typeface)) {
            dVar.i(false);
        }
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.f3254o;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.f3254o = mutate;
            if (mutate != null) {
                int width = getWidth();
                int height = getHeight();
                ViewGroup viewGroup = this.f3242c;
                if (this.f3265z == 1 && viewGroup != null && this.f3252m) {
                    height = viewGroup.getBottom();
                }
                mutate.setBounds(0, 0, width, height);
                this.f3254o.setCallback(this);
                this.f3254o.setAlpha(this.f3256q);
            }
            int i7 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
    }

    public void setContentScrimColor(int i7) {
        setContentScrim(new ColorDrawable(i7));
    }

    public void setContentScrimResource(int i7) {
        Context context = getContext();
        Object obj = g0.g.f6423a;
        setContentScrim(g0.c.b(context, i7));
    }

    public void setExpandedTitleColor(int i7) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i7));
    }

    public void setExpandedTitleGravity(int i7) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (dVar.f3823j != i7) {
            dVar.f3823j = i7;
            dVar.i(false);
        }
    }

    public void setExpandedTitleMargin(int i7, int i8, int i9, int i10) {
        this.f3245f = i7;
        this.f3246g = i8;
        this.f3247h = i9;
        this.f3248i = i10;
        requestLayout();
    }

    public void setExpandedTitleMarginBottom(int i7) {
        this.f3248i = i7;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i7) {
        this.f3247h = i7;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i7) {
        this.f3245f = i7;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i7) {
        this.f3246g = i7;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i7) {
        this.f3250k.n(i7);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (dVar.f3831n != colorStateList) {
            dVar.f3831n = colorStateList;
            dVar.i(false);
        }
    }

    public void setExpandedTitleTextSize(float f7) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (dVar.f3827l != f7) {
            dVar.f3827l = f7;
            dVar.i(false);
        }
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (dVar.o(typeface)) {
            dVar.i(false);
        }
    }

    public void setExtraMultilineHeightEnabled(boolean z6) {
        this.E = z6;
    }

    public void setForceApplySystemWindowInsetTop(boolean z6) {
        this.C = z6;
    }

    public void setHyphenationFrequency(int i7) {
        this.f3250k.f3838q0 = i7;
    }

    public void setLineSpacingAdd(float f7) {
        this.f3250k.f3834o0 = f7;
    }

    public void setLineSpacingMultiplier(float f7) {
        this.f3250k.f3836p0 = f7;
    }

    public void setMaxLines(int i7) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (i7 != dVar.f3832n0) {
            dVar.f3832n0 = i7;
            Bitmap bitmap = dVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                dVar.K = null;
            }
            dVar.i(false);
        }
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z6) {
        this.f3250k.J = z6;
    }

    public void setScrimAlpha(int i7) {
        ViewGroup viewGroup;
        if (i7 != this.f3256q) {
            if (this.f3254o != null && (viewGroup = this.f3242c) != null) {
                int i8 = d1.OVER_SCROLL_ALWAYS;
                l0.k(viewGroup);
            }
            this.f3256q = i7;
            int i9 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
    }

    public void setScrimAnimationDuration(long j7) {
        this.f3259t = j7;
    }

    public void setScrimVisibleHeightTrigger(int i7) {
        if (this.f3262w != i7) {
            this.f3262w = i7;
            d();
        }
    }

    public void setScrimsShown(boolean z6) {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        setScrimsShown(z6, o0.c(this) && !isInEditMode());
    }

    public void setScrimsShown(boolean z6, boolean z7) {
        if (this.f3257r != z6) {
            int i7 = 0;
            if (z7) {
                int i8 = z6 ? 255 : 0;
                a();
                ValueAnimator valueAnimator = this.f3258s;
                if (valueAnimator == null) {
                    ValueAnimator valueAnimator2 = new ValueAnimator();
                    this.f3258s = valueAnimator2;
                    valueAnimator2.setInterpolator(i8 > this.f3256q ? this.f3260u : this.f3261v);
                    this.f3258s.addUpdateListener(new s2.d(i7, this));
                } else if (valueAnimator.isRunning()) {
                    this.f3258s.cancel();
                }
                this.f3258s.setDuration(this.f3259t);
                this.f3258s.setIntValues(this.f3256q, i8);
                this.f3258s.start();
            } else {
                setScrimAlpha(z6 ? 255 : 0);
            }
            this.f3257r = z6;
        }
    }

    public void setStaticLayoutBuilderConfigurer(s2.g gVar) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (gVar != null) {
            dVar.i(true);
        } else {
            dVar.getClass();
        }
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = this.f3255p;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable mutate = drawable != null ? drawable.mutate() : null;
            this.f3255p = mutate;
            if (mutate != null) {
                if (mutate.isStateful()) {
                    this.f3255p.setState(getDrawableState());
                }
                Drawable drawable3 = this.f3255p;
                int i7 = d1.OVER_SCROLL_ALWAYS;
                j0.c.b(drawable3, m0.d(this));
                this.f3255p.setVisible(getVisibility() == 0, false);
                this.f3255p.setCallback(this);
                this.f3255p.setAlpha(this.f3256q);
            }
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.k(this);
        }
    }

    public void setStatusBarScrimColor(int i7) {
        setStatusBarScrim(new ColorDrawable(i7));
    }

    public void setStatusBarScrimResource(int i7) {
        Context context = getContext();
        Object obj = g0.g.f6423a;
        setStatusBarScrim(g0.c.b(context, i7));
    }

    public void setTitle(CharSequence charSequence) {
        com.google.android.material.internal.d dVar = this.f3250k;
        if (charSequence == null || !TextUtils.equals(dVar.G, charSequence)) {
            dVar.G = charSequence;
            dVar.H = null;
            Bitmap bitmap = dVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                dVar.K = null;
            }
            dVar.i(false);
        }
        setContentDescription(getTitle());
    }

    public void setTitleCollapseMode(int i7) {
        this.f3265z = i7;
        boolean z6 = i7 == 1;
        this.f3250k.f3809c = z6;
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            if (this.f3265z == 1) {
                appBarLayout.setLiftOnScroll(false);
            }
        }
        if (z6 && this.f3254o == null) {
            setContentScrimColor(getDefaultContentScrimColorForTitleCollapseFadeMode());
        }
    }

    public void setTitleEllipsize(TextUtils.TruncateAt truncateAt) {
        com.google.android.material.internal.d dVar = this.f3250k;
        dVar.F = truncateAt;
        dVar.i(false);
    }

    public void setTitleEnabled(boolean z6) {
        if (z6 != this.f3252m) {
            this.f3252m = z6;
            setContentDescription(getTitle());
            c();
            requestLayout();
        }
    }

    public void setTitlePositionInterpolator(TimeInterpolator timeInterpolator) {
        com.google.android.material.internal.d dVar = this.f3250k;
        dVar.V = timeInterpolator;
        dVar.i(false);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        boolean z6 = i7 == 0;
        Drawable drawable = this.f3255p;
        if (drawable != null && drawable.isVisible() != z6) {
            this.f3255p.setVisible(z6, false);
        }
        Drawable drawable2 = this.f3254o;
        if (drawable2 == null || drawable2.isVisible() == z6) {
            return;
        }
        this.f3254o.setVisible(z6, false);
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f3254o || drawable == this.f3255p;
    }
}
