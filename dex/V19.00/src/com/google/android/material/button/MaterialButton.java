package com.google.android.material.button;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.r;
import androidx.customview.view.AbsSavedState;
import com.yalantis.ucrop.view.CropImageView;
import g0.g;
import java.util.Iterator;
import java.util.LinkedHashSet;
import l6.f;
import p0.d1;
import p0.m0;
import p3.a0;
import p3.n;
import p3.p;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialButton extends AppCompatButton implements Checkable, a0 {
    public static final int ICON_GRAVITY_END = 3;
    public static final int ICON_GRAVITY_START = 1;
    public static final int ICON_GRAVITY_TEXT_END = 4;
    public static final int ICON_GRAVITY_TEXT_START = 2;
    public static final int ICON_GRAVITY_TEXT_TOP = 32;
    public static final int ICON_GRAVITY_TOP = 16;

    /* renamed from: r, reason: collision with root package name */
    public static final int[] f3427r = {R.attr.state_checkable};

    /* renamed from: s, reason: collision with root package name */
    public static final int[] f3428s = {R.attr.state_checked};

    /* renamed from: t, reason: collision with root package name */
    public static final int f3429t = l.Widget_MaterialComponents_Button;

    /* renamed from: d, reason: collision with root package name */
    public final c f3430d;

    /* renamed from: e, reason: collision with root package name */
    public final LinkedHashSet f3431e;

    /* renamed from: f, reason: collision with root package name */
    public a f3432f;

    /* renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f3433g;

    /* renamed from: h, reason: collision with root package name */
    public ColorStateList f3434h;

    /* renamed from: i, reason: collision with root package name */
    public Drawable f3435i;

    /* renamed from: j, reason: collision with root package name */
    public String f3436j;

    /* renamed from: k, reason: collision with root package name */
    public int f3437k;

    /* renamed from: l, reason: collision with root package name */
    public int f3438l;

    /* renamed from: m, reason: collision with root package name */
    public int f3439m;

    /* renamed from: n, reason: collision with root package name */
    public int f3440n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f3441o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f3442p;

    /* renamed from: q, reason: collision with root package name */
    public int f3443q;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new b();

        /* renamed from: b, reason: collision with root package name */
        public boolean f3444b;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            this.f3444b = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeInt(this.f3444b ? 1 : 0);
        }
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.materialButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialButton(android.content.Context r9, android.util.AttributeSet r10, int r11) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.button.MaterialButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private Layout.Alignment getActualTextAlignment() {
        int textAlignment = getTextAlignment();
        return textAlignment != 1 ? (textAlignment == 6 || textAlignment == 3) ? Layout.Alignment.ALIGN_OPPOSITE : textAlignment != 4 ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER : getGravityTextAlignment();
    }

    private Layout.Alignment getGravityTextAlignment() {
        int gravity = getGravity() & 8388615;
        return gravity != 1 ? (gravity == 5 || gravity == 8388613) ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER;
    }

    private int getTextHeight() {
        if (getLineCount() > 1) {
            return getLayout().getHeight();
        }
        TextPaint paint = getPaint();
        String charSequence = getText().toString();
        if (getTransformationMethod() != null) {
            charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
        }
        Rect rect = new Rect();
        paint.getTextBounds(charSequence, 0, charSequence.length(), rect);
        return Math.min(rect.height(), getLayout().getHeight());
    }

    private int getTextLayoutWidth() {
        int lineCount = getLineCount();
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        for (int i7 = 0; i7 < lineCount; i7++) {
            f7 = Math.max(f7, getLayout().getLineWidth(i7));
        }
        return (int) Math.ceil(f7);
    }

    public final boolean a() {
        c cVar = this.f3430d;
        return cVar != null && cVar.f3472q;
    }

    public final boolean b() {
        c cVar = this.f3430d;
        return (cVar == null || cVar.f3470o) ? false : true;
    }

    public final void c() {
        int i7 = this.f3443q;
        boolean z6 = true;
        if (i7 != 1 && i7 != 2) {
            z6 = false;
        }
        if (z6) {
            r.e(this, this.f3435i, null, null, null);
            return;
        }
        if (i7 == 3 || i7 == 4) {
            r.e(this, null, null, this.f3435i, null);
        } else if (i7 == 16 || i7 == 32) {
            r.e(this, null, this.f3435i, null, null);
        }
    }

    public final void d(boolean z6) {
        Drawable drawable = this.f3435i;
        if (drawable != null) {
            Drawable mutate = drawable.mutate();
            this.f3435i = mutate;
            j0.b.h(mutate, this.f3434h);
            PorterDuff.Mode mode = this.f3433g;
            if (mode != null) {
                j0.b.i(this.f3435i, mode);
            }
            int i7 = this.f3437k;
            if (i7 == 0) {
                i7 = this.f3435i.getIntrinsicWidth();
            }
            int i8 = this.f3437k;
            if (i8 == 0) {
                i8 = this.f3435i.getIntrinsicHeight();
            }
            Drawable drawable2 = this.f3435i;
            int i9 = this.f3438l;
            int i10 = this.f3439m;
            drawable2.setBounds(i9, i10, i7 + i9, i8 + i10);
            this.f3435i.setVisible(true, z6);
        }
        if (z6) {
            c();
            return;
        }
        Drawable[] a7 = r.a(this);
        Drawable drawable3 = a7[0];
        Drawable drawable4 = a7[1];
        Drawable drawable5 = a7[2];
        int i11 = this.f3443q;
        if (((i11 == 1 || i11 == 2) && drawable3 != this.f3435i) || (((i11 == 3 || i11 == 4) && drawable5 != this.f3435i) || ((i11 == 16 || i11 == 32) && drawable4 != this.f3435i))) {
            c();
        }
    }

    public final void e(int i7, int i8) {
        if (this.f3435i == null || getLayout() == null) {
            return;
        }
        int i9 = this.f3443q;
        if (!(i9 == 1 || i9 == 2) && i9 != 3 && i9 != 4) {
            if (i9 == 16 || i9 == 32) {
                this.f3438l = 0;
                if (i9 == 16) {
                    this.f3439m = 0;
                    d(false);
                    return;
                }
                int i10 = this.f3437k;
                if (i10 == 0) {
                    i10 = this.f3435i.getIntrinsicHeight();
                }
                int max = Math.max(0, (((((i8 - getTextHeight()) - getPaddingTop()) - i10) - this.f3440n) - getPaddingBottom()) / 2);
                if (this.f3439m != max) {
                    this.f3439m = max;
                    d(false);
                    return;
                }
                return;
            }
            return;
        }
        this.f3439m = 0;
        Layout.Alignment actualTextAlignment = getActualTextAlignment();
        int i11 = this.f3443q;
        if (i11 == 1 || i11 == 3 || ((i11 == 2 && actualTextAlignment == Layout.Alignment.ALIGN_NORMAL) || (i11 == 4 && actualTextAlignment == Layout.Alignment.ALIGN_OPPOSITE))) {
            this.f3438l = 0;
            d(false);
            return;
        }
        int i12 = this.f3437k;
        if (i12 == 0) {
            i12 = this.f3435i.getIntrinsicWidth();
        }
        int textLayoutWidth = i7 - getTextLayoutWidth();
        int i13 = d1.OVER_SCROLL_ALWAYS;
        int e7 = (((textLayoutWidth - m0.e(this)) - i12) - this.f3440n) - m0.f(this);
        if (actualTextAlignment == Layout.Alignment.ALIGN_CENTER) {
            e7 /= 2;
        }
        if ((m0.d(this) == 1) != (this.f3443q == 4)) {
            e7 = -e7;
        }
        if (this.f3438l != e7) {
            this.f3438l = e7;
            d(false);
        }
    }

    public String getA11yClassName() {
        if (TextUtils.isEmpty(this.f3436j)) {
            return (a() ? CompoundButton.class : Button.class).getName();
        }
        return this.f3436j;
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public int getCornerRadius() {
        if (b()) {
            return this.f3430d.f3462g;
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.f3435i;
    }

    public int getIconGravity() {
        return this.f3443q;
    }

    public int getIconPadding() {
        return this.f3440n;
    }

    public int getIconSize() {
        return this.f3437k;
    }

    public ColorStateList getIconTint() {
        return this.f3434h;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f3433g;
    }

    public int getInsetBottom() {
        return this.f3430d.f3461f;
    }

    public int getInsetTop() {
        return this.f3430d.f3460e;
    }

    public ColorStateList getRippleColor() {
        if (b()) {
            return this.f3430d.f3467l;
        }
        return null;
    }

    public p getShapeAppearanceModel() {
        if (b()) {
            return this.f3430d.f3457b;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (b()) {
            return this.f3430d.f3466k;
        }
        return null;
    }

    public int getStrokeWidth() {
        if (b()) {
            return this.f3430d.f3463h;
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public ColorStateList getSupportBackgroundTintList() {
        return b() ? this.f3430d.f3465j : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return b() ? this.f3430d.f3464i : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.f3441o;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (b()) {
            f.B0(this, this.f3430d.b(false));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i7 + 2);
        if (a()) {
            View.mergeDrawableStates(onCreateDrawableState, f3427r);
        }
        if (isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, f3428s);
        }
        return onCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(a());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        e(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        setChecked(savedState.f3444b);
    }

    @Override // android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3444b = this.f3441o;
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        super.onTextChanged(charSequence, i7, i8, i9);
        e(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (this.f3430d.f3473r) {
            toggle();
        }
        return super.performClick();
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.f3435i != null) {
            if (this.f3435i.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    public void setA11yClassName(String str) {
        this.f3436j = str;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i7) {
        if (!b()) {
            super.setBackgroundColor(i7);
            return;
        }
        c cVar = this.f3430d;
        if (cVar.b(false) != null) {
            cVar.b(false).setTint(i7);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!b()) {
            super.setBackgroundDrawable(drawable);
            return;
        }
        if (drawable == getBackground()) {
            getBackground().setState(drawable.getState());
            return;
        }
        c cVar = this.f3430d;
        cVar.f3470o = true;
        ColorStateList colorStateList = cVar.f3465j;
        MaterialButton materialButton = cVar.f3456a;
        materialButton.setSupportBackgroundTintList(colorStateList);
        materialButton.setSupportBackgroundTintMode(cVar.f3464i);
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i7) {
        setBackgroundDrawable(i7 != 0 ? f.A(getContext(), i7) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z6) {
        if (b()) {
            this.f3430d.f3472q = z6;
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z6) {
        if (a() && isEnabled() && this.f3441o != z6) {
            this.f3441o = z6;
            refreshDrawableState();
            if (getParent() instanceof MaterialButtonToggleGroup) {
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) getParent();
                boolean z7 = this.f3441o;
                if (!materialButtonToggleGroup.f3451f) {
                    materialButtonToggleGroup.b(getId(), z7);
                }
            }
            if (this.f3442p) {
                return;
            }
            this.f3442p = true;
            Iterator it = this.f3431e.iterator();
            if (it.hasNext()) {
                a.a.u(it.next());
                throw null;
            }
            this.f3442p = false;
        }
    }

    public void setCornerRadius(int i7) {
        if (b()) {
            c cVar = this.f3430d;
            if (cVar.f3471p && cVar.f3462g == i7) {
                return;
            }
            cVar.f3462g = i7;
            cVar.f3471p = true;
            n g7 = cVar.f3457b.g();
            g7.c(i7);
            cVar.c(g7.a());
        }
    }

    public void setCornerRadiusResource(int i7) {
        if (b()) {
            setCornerRadius(getResources().getDimensionPixelSize(i7));
        }
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        if (b()) {
            this.f3430d.b(false).n(f7);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.f3435i != drawable) {
            this.f3435i = drawable;
            d(true);
            e(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i7) {
        if (this.f3443q != i7) {
            this.f3443q = i7;
            e(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i7) {
        if (this.f3440n != i7) {
            this.f3440n = i7;
            setCompoundDrawablePadding(i7);
        }
    }

    public void setIconResource(int i7) {
        setIcon(i7 != 0 ? f.A(getContext(), i7) : null);
    }

    public void setIconSize(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.f3437k != i7) {
            this.f3437k = i7;
            d(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.f3434h != colorStateList) {
            this.f3434h = colorStateList;
            d(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.f3433g != mode) {
            this.f3433g = mode;
            d(false);
        }
    }

    public void setIconTintResource(int i7) {
        setIconTint(g.b(getContext(), i7));
    }

    public void setInsetBottom(int i7) {
        c cVar = this.f3430d;
        cVar.d(cVar.f3460e, i7);
    }

    public void setInsetTop(int i7) {
        c cVar = this.f3430d;
        cVar.d(i7, cVar.f3461f);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(a aVar) {
        this.f3432f = aVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z6) {
        a aVar = this.f3432f;
        if (aVar != null) {
            ((MaterialButtonToggleGroup) ((v2.a) aVar).f9388b).invalidate();
        }
        super.setPressed(z6);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (b()) {
            c cVar = this.f3430d;
            if (cVar.f3467l != colorStateList) {
                cVar.f3467l = colorStateList;
                MaterialButton materialButton = cVar.f3456a;
                if (materialButton.getBackground() instanceof RippleDrawable) {
                    ((RippleDrawable) materialButton.getBackground()).setColor(n3.a.c(colorStateList));
                }
            }
        }
    }

    public void setRippleColorResource(int i7) {
        if (b()) {
            setRippleColor(g.b(getContext(), i7));
        }
    }

    @Override // p3.a0
    public void setShapeAppearanceModel(p pVar) {
        if (!b()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.f3430d.c(pVar);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z6) {
        if (b()) {
            c cVar = this.f3430d;
            cVar.f3469n = z6;
            cVar.f();
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (b()) {
            c cVar = this.f3430d;
            if (cVar.f3466k != colorStateList) {
                cVar.f3466k = colorStateList;
                cVar.f();
            }
        }
    }

    public void setStrokeColorResource(int i7) {
        if (b()) {
            setStrokeColor(g.b(getContext(), i7));
        }
    }

    public void setStrokeWidth(int i7) {
        if (b()) {
            c cVar = this.f3430d;
            if (cVar.f3463h != i7) {
                cVar.f3463h = i7;
                cVar.f();
            }
        }
    }

    public void setStrokeWidthResource(int i7) {
        if (b()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i7));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (!b()) {
            super.setSupportBackgroundTintList(colorStateList);
            return;
        }
        c cVar = this.f3430d;
        if (cVar.f3465j != colorStateList) {
            cVar.f3465j = colorStateList;
            if (cVar.b(false) != null) {
                j0.b.h(cVar.b(false), cVar.f3465j);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (!b()) {
            super.setSupportBackgroundTintMode(mode);
            return;
        }
        c cVar = this.f3430d;
        if (cVar.f3464i != mode) {
            cVar.f3464i = mode;
            if (cVar.b(false) == null || cVar.f3464i == null) {
                return;
            }
            j0.b.i(cVar.b(false), cVar.f3464i);
        }
    }

    @Override // android.view.View
    public void setTextAlignment(int i7) {
        super.setTextAlignment(i7);
        e(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setToggleCheckedStateOnClick(boolean z6) {
        this.f3430d.f3473r = z6;
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.f3441o);
    }
}
