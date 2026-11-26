package com.google.android.material.card;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import androidx.cardview.widget.CardView;
import g0.g;
import j0.b;
import l6.f;
import p3.a0;
import p3.j;
import p3.n;
import p3.p;
import q2.c;
import q2.l;
import x2.a;
import x2.d;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialCardView extends CardView implements Checkable, a0 {
    public static final int CHECKED_ICON_GRAVITY_BOTTOM_END = 8388693;
    public static final int CHECKED_ICON_GRAVITY_BOTTOM_START = 8388691;
    public static final int CHECKED_ICON_GRAVITY_TOP_END = 8388661;
    public static final int CHECKED_ICON_GRAVITY_TOP_START = 8388659;

    /* renamed from: l, reason: collision with root package name */
    public static final int[] f3481l = {R.attr.state_checkable};

    /* renamed from: m, reason: collision with root package name */
    public static final int[] f3482m = {R.attr.state_checked};

    /* renamed from: n, reason: collision with root package name */
    public static final int[] f3483n = {c.state_dragged};

    /* renamed from: o, reason: collision with root package name */
    public static final int f3484o = l.Widget_MaterialComponents_CardView;

    /* renamed from: h, reason: collision with root package name */
    public final d f3485h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f3486i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f3487j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f3488k;

    public MaterialCardView(Context context) {
        this(context, null);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.materialCardViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialCardView(android.content.Context r7, android.util.AttributeSet r8, int r9) {
        /*
            Method dump skipped, instructions count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.card.MaterialCardView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private RectF getBoundsAsRectF() {
        RectF rectF = new RectF();
        rectF.set(this.f3485h.f9588c.getBounds());
        return rectF;
    }

    public final void d() {
        d dVar;
        Drawable drawable;
        if (Build.VERSION.SDK_INT <= 26 || (drawable = (dVar = this.f3485h).f9600o) == null) {
            return;
        }
        Rect bounds = drawable.getBounds();
        int i7 = bounds.bottom;
        dVar.f9600o.setBounds(bounds.left, bounds.top, bounds.right, i7 - 1);
        dVar.f9600o.setBounds(bounds.left, bounds.top, bounds.right, i7);
    }

    public final void e(int i7, int i8, int i9, int i10) {
        super.setContentPadding(i7, i8, i9, i10);
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.f3485h.f9588c.f8263a.f8243c;
    }

    public ColorStateList getCardForegroundColor() {
        return this.f3485h.f9589d.f8263a.f8243c;
    }

    public float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        return this.f3485h.f9595j;
    }

    public int getCheckedIconGravity() {
        return this.f3485h.f9592g;
    }

    public int getCheckedIconMargin() {
        return this.f3485h.f9590e;
    }

    public int getCheckedIconSize() {
        return this.f3485h.f9591f;
    }

    public ColorStateList getCheckedIconTint() {
        return this.f3485h.f9597l;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.f3485h.f9587b.bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.f3485h.f9587b.left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.f3485h.f9587b.right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.f3485h.f9587b.top;
    }

    public float getProgress() {
        return this.f3485h.f9588c.f8263a.f8250j;
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.f3485h.f9588c.j();
    }

    public ColorStateList getRippleColor() {
        return this.f3485h.f9596k;
    }

    public p getShapeAppearanceModel() {
        return this.f3485h.f9598m;
    }

    @Deprecated
    public int getStrokeColor() {
        ColorStateList colorStateList = this.f3485h.f9599n;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.f3485h.f9599n;
    }

    public int getStrokeWidth() {
        return this.f3485h.f9593h;
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.f3487j;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        d dVar = this.f3485h;
        dVar.k();
        f.B0(this, dVar.f9588c);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i7 + 3);
        d dVar = this.f3485h;
        if (dVar != null && dVar.f9605t) {
            View.mergeDrawableStates(onCreateDrawableState, f3481l);
        }
        if (isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, f3482m);
        }
        if (this.f3488k) {
            View.mergeDrawableStates(onCreateDrawableState, f3483n);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        d dVar = this.f3485h;
        accessibilityNodeInfo.setCheckable(dVar != null && dVar.f9605t);
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        this.f3485h.e(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.f3486i) {
            d dVar = this.f3485h;
            if (!dVar.f9604s) {
                dVar.f9604s = true;
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int i7) {
        this.f3485h.f9588c.o(ColorStateList.valueOf(i7));
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.f3485h.f9588c.o(colorStateList);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f7) {
        super.setCardElevation(f7);
        d dVar = this.f3485h;
        dVar.f9588c.n(dVar.f9586a.getCardElevation());
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        j jVar = this.f3485h.f9589d;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        jVar.o(colorStateList);
    }

    public void setCheckable(boolean z6) {
        this.f3485h.f9605t = z6;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z6) {
        if (this.f3487j != z6) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        this.f3485h.g(drawable);
    }

    public void setCheckedIconGravity(int i7) {
        d dVar = this.f3485h;
        if (dVar.f9592g != i7) {
            dVar.f9592g = i7;
            MaterialCardView materialCardView = dVar.f9586a;
            dVar.e(materialCardView.getMeasuredWidth(), materialCardView.getMeasuredHeight());
        }
    }

    public void setCheckedIconMargin(int i7) {
        this.f3485h.f9590e = i7;
    }

    public void setCheckedIconMarginResource(int i7) {
        if (i7 != -1) {
            this.f3485h.f9590e = getResources().getDimensionPixelSize(i7);
        }
    }

    public void setCheckedIconResource(int i7) {
        this.f3485h.g(f.A(getContext(), i7));
    }

    public void setCheckedIconSize(int i7) {
        this.f3485h.f9591f = i7;
    }

    public void setCheckedIconSizeResource(int i7) {
        if (i7 != 0) {
            this.f3485h.f9591f = getResources().getDimensionPixelSize(i7);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        d dVar = this.f3485h;
        dVar.f9597l = colorStateList;
        Drawable drawable = dVar.f9595j;
        if (drawable != null) {
            b.h(drawable, colorStateList);
        }
    }

    @Override // android.view.View
    public void setClickable(boolean z6) {
        super.setClickable(z6);
        d dVar = this.f3485h;
        if (dVar != null) {
            dVar.k();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setContentPadding(int i7, int i8, int i9, int i10) {
        d dVar = this.f3485h;
        dVar.f9587b.set(i7, i8, i9, i10);
        dVar.l();
    }

    public void setDragged(boolean z6) {
        if (this.f3488k != z6) {
            this.f3488k = z6;
            refreshDrawableState();
            d();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f7) {
        super.setMaxCardElevation(f7);
        this.f3485h.m();
    }

    public void setOnCheckedChangeListener(a aVar) {
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z6) {
        super.setPreventCornerOverlap(z6);
        d dVar = this.f3485h;
        dVar.m();
        dVar.l();
    }

    public void setProgress(float f7) {
        d dVar = this.f3485h;
        dVar.f9588c.p(f7);
        j jVar = dVar.f9589d;
        if (jVar != null) {
            jVar.p(f7);
        }
        j jVar2 = dVar.f9603r;
        if (jVar2 != null) {
            jVar2.p(f7);
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float f7) {
        super.setRadius(f7);
        d dVar = this.f3485h;
        n g7 = dVar.f9598m.g();
        g7.c(f7);
        dVar.h(g7.a());
        dVar.f9594i.invalidateSelf();
        if (dVar.i() || (dVar.f9586a.getPreventCornerOverlap() && !dVar.f9588c.m())) {
            dVar.l();
        }
        if (dVar.i()) {
            dVar.m();
        }
    }

    public void setRippleColor(ColorStateList colorStateList) {
        Drawable drawable;
        d dVar = this.f3485h;
        dVar.f9596k = colorStateList;
        if (n3.a.USE_FRAMEWORK_RIPPLE && (drawable = dVar.f9600o) != null) {
            ((RippleDrawable) drawable).setColor(colorStateList);
            return;
        }
        j jVar = dVar.f9602q;
        if (jVar != null) {
            jVar.o(colorStateList);
        }
    }

    public void setRippleColorResource(int i7) {
        Drawable drawable;
        ColorStateList b7 = g.b(getContext(), i7);
        d dVar = this.f3485h;
        dVar.f9596k = b7;
        if (n3.a.USE_FRAMEWORK_RIPPLE && (drawable = dVar.f9600o) != null) {
            ((RippleDrawable) drawable).setColor(b7);
            return;
        }
        j jVar = dVar.f9602q;
        if (jVar != null) {
            jVar.o(b7);
        }
    }

    @Override // p3.a0
    public void setShapeAppearanceModel(p pVar) {
        setClipToOutline(pVar.f(getBoundsAsRectF()));
        this.f3485h.h(pVar);
    }

    public void setStrokeColor(int i7) {
        setStrokeColor(ColorStateList.valueOf(i7));
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        d dVar = this.f3485h;
        if (dVar.f9599n != colorStateList) {
            dVar.f9599n = colorStateList;
            j jVar = dVar.f9589d;
            jVar.u(dVar.f9593h);
            jVar.t(colorStateList);
        }
        invalidate();
    }

    public void setStrokeWidth(int i7) {
        d dVar = this.f3485h;
        if (i7 != dVar.f9593h) {
            dVar.f9593h = i7;
            j jVar = dVar.f9589d;
            ColorStateList colorStateList = dVar.f9599n;
            jVar.u(i7);
            jVar.t(colorStateList);
        }
        invalidate();
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z6) {
        super.setUseCompatPadding(z6);
        d dVar = this.f3485h;
        dVar.m();
        dVar.l();
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        d dVar = this.f3485h;
        if (dVar != null && dVar.f9605t && isEnabled()) {
            this.f3487j = !this.f3487j;
            refreshDrawableState();
            d();
            dVar.f(this.f3487j, true);
        }
    }
}
