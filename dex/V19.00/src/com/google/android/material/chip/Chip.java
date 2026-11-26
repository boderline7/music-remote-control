package com.google.android.material.chip;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.o;
import com.google.android.material.internal.b0;
import com.google.android.material.internal.h;
import com.google.android.material.internal.i;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import n1.z;
import n3.a;
import p0.d1;
import p0.l0;
import p0.m0;
import p3.a0;
import p3.p;
import q2.c;
import q2.g;
import q2.l;
import s5.h0;
import z2.b;
import z2.d;
import z2.e;
import z2.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Chip extends AppCompatCheckBox implements e, a0, i {

    /* renamed from: e, reason: collision with root package name */
    public f f3532e;

    /* renamed from: f, reason: collision with root package name */
    public InsetDrawable f3533f;

    /* renamed from: g, reason: collision with root package name */
    public RippleDrawable f3534g;

    /* renamed from: h, reason: collision with root package name */
    public View.OnClickListener f3535h;

    /* renamed from: i, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f3536i;

    /* renamed from: j, reason: collision with root package name */
    public h f3537j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f3538k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f3539l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f3540m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f3541n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f3542o;

    /* renamed from: p, reason: collision with root package name */
    public int f3543p;

    /* renamed from: q, reason: collision with root package name */
    public int f3544q;

    /* renamed from: r, reason: collision with root package name */
    public CharSequence f3545r;

    /* renamed from: s, reason: collision with root package name */
    public final d f3546s;

    /* renamed from: t, reason: collision with root package name */
    public boolean f3547t;

    /* renamed from: u, reason: collision with root package name */
    public final Rect f3548u;

    /* renamed from: v, reason: collision with root package name */
    public final RectF f3549v;

    /* renamed from: w, reason: collision with root package name */
    public final b f3550w;

    /* renamed from: x, reason: collision with root package name */
    public static final int f3529x = l.Widget_MaterialComponents_Chip_Action;

    /* renamed from: y, reason: collision with root package name */
    public static final Rect f3530y = new Rect();

    /* renamed from: z, reason: collision with root package name */
    public static final int[] f3531z = {R.attr.state_selected};
    public static final int[] A = {R.attr.state_checkable};

    public Chip(Context context) {
        this(context, null);
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.chipStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Chip(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            Method dump skipped, instructions count: 915
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RectF getCloseIconTouchBounds() {
        RectF rectF = this.f3549v;
        rectF.setEmpty();
        if (d() && this.f3535h != null) {
            f fVar = this.f3532e;
            Rect bounds = fVar.getBounds();
            rectF.setEmpty();
            if (fVar.d0()) {
                float f7 = fVar.f10262e0 + fVar.f10261d0 + fVar.P + fVar.f10260c0 + fVar.f10259b0;
                if (j0.c.a(fVar) == 0) {
                    float f8 = bounds.right;
                    rectF.right = f8;
                    rectF.left = f8 - f7;
                } else {
                    float f9 = bounds.left;
                    rectF.left = f9;
                    rectF.right = f9 + f7;
                }
                rectF.top = bounds.top;
                rectF.bottom = bounds.bottom;
            }
        }
        return rectF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        int i7 = (int) closeIconTouchBounds.left;
        int i8 = (int) closeIconTouchBounds.top;
        int i9 = (int) closeIconTouchBounds.right;
        int i10 = (int) closeIconTouchBounds.bottom;
        Rect rect = this.f3548u;
        rect.set(i7, i8, i9, i10);
        return rect;
    }

    private m3.e getTextAppearance() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.f10269l0.f3802g;
        }
        return null;
    }

    private void setCloseIconHovered(boolean z6) {
        if (this.f3540m != z6) {
            this.f3540m = z6;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z6) {
        if (this.f3539l != z6) {
            this.f3539l = z6;
            refreshDrawableState();
        }
    }

    public final void c(int i7) {
        this.f3544q = i7;
        if (!this.f3542o) {
            InsetDrawable insetDrawable = this.f3533f;
            if (insetDrawable == null) {
                g();
                return;
            } else {
                if (insetDrawable != null) {
                    this.f3533f = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    g();
                    return;
                }
                return;
            }
        }
        int max = Math.max(0, i7 - ((int) this.f3532e.A));
        int max2 = Math.max(0, i7 - this.f3532e.getIntrinsicWidth());
        if (max2 <= 0 && max <= 0) {
            InsetDrawable insetDrawable2 = this.f3533f;
            if (insetDrawable2 == null) {
                g();
                return;
            } else {
                if (insetDrawable2 != null) {
                    this.f3533f = null;
                    setMinWidth(0);
                    setMinHeight((int) getChipMinHeight());
                    g();
                    return;
                }
                return;
            }
        }
        int i8 = max2 > 0 ? max2 / 2 : 0;
        int i9 = max > 0 ? max / 2 : 0;
        if (this.f3533f != null) {
            Rect rect = new Rect();
            this.f3533f.getPadding(rect);
            if (rect.top == i9 && rect.bottom == i9 && rect.left == i8 && rect.right == i8) {
                g();
                return;
            }
        }
        if (getMinHeight() != i7) {
            setMinHeight(i7);
        }
        if (getMinWidth() != i7) {
            setMinWidth(i7);
        }
        this.f3533f = new InsetDrawable((Drawable) this.f3532e, i8, i9, i8, i9);
        g();
    }

    public final boolean d() {
        f fVar = this.f3532e;
        if (fVar != null) {
            Drawable drawable = fVar.M;
            if ((drawable != null ? z.p1(drawable) : null) != null) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return !this.f3547t ? super.dispatchHoverEvent(motionEvent) : this.f3546s.m(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0082, code lost:
    
        if (r7 == false) goto L47;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r10) {
        /*
            r9 = this;
            boolean r0 = r9.f3547t
            if (r0 != 0) goto L9
            boolean r10 = super.dispatchKeyEvent(r10)
            return r10
        L9:
            z2.d r0 = r9.f3546s
            r0.getClass()
            int r1 = r10.getAction()
            r2 = 1
            if (r1 == r2) goto L89
            int r1 = r10.getKeyCode()
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = 61
            r5 = 0
            if (r1 == r4) goto L6c
            r4 = 66
            if (r1 == r4) goto L55
            switch(r1) {
                case 19: goto L28;
                case 20: goto L28;
                case 21: goto L28;
                case 22: goto L28;
                case 23: goto L55;
                default: goto L27;
            }
        L27:
            goto L89
        L28:
            boolean r6 = r10.hasNoModifiers()
            if (r6 == 0) goto L89
            r6 = 19
            if (r1 == r6) goto L40
            r6 = 21
            if (r1 == r6) goto L3d
            r6 = 22
            if (r1 == r6) goto L42
            r4 = 130(0x82, float:1.82E-43)
            goto L42
        L3d:
            r4 = 17
            goto L42
        L40:
            r4 = 33
        L42:
            int r1 = r10.getRepeatCount()
            int r1 = r1 + r2
            r6 = 0
            r7 = r6
        L49:
            if (r6 >= r1) goto L82
            boolean r8 = r0.q(r4, r5)
            if (r8 == 0) goto L82
            int r6 = r6 + 1
            r7 = r2
            goto L49
        L55:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto L89
            int r1 = r10.getRepeatCount()
            if (r1 != 0) goto L89
            int r1 = r0.f9229l
            if (r1 == r3) goto L84
            r4 = 16
            boolean r1 = r0.s(r1, r4, r5)
            goto L84
        L6c:
            boolean r1 = r10.hasNoModifiers()
            if (r1 == 0) goto L78
            r1 = 2
            boolean r7 = r0.q(r1, r5)
            goto L82
        L78:
            boolean r1 = r10.hasModifiers(r2)
            if (r1 == 0) goto L89
            boolean r7 = r0.q(r2, r5)
        L82:
            if (r7 == 0) goto L89
        L84:
            int r0 = r0.f9229l
            if (r0 == r3) goto L89
            return r2
        L89:
            boolean r10 = super.dispatchKeyEvent(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [boolean, int] */
    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.f3532e;
        if (fVar == null || !f.E(fVar.M)) {
            return;
        }
        f fVar2 = this.f3532e;
        ?? isEnabled = isEnabled();
        int i7 = isEnabled;
        if (this.f3541n) {
            i7 = isEnabled + 1;
        }
        int i8 = i7;
        if (this.f3540m) {
            i8 = i7 + 1;
        }
        int i9 = i8;
        if (this.f3539l) {
            i9 = i8 + 1;
        }
        int i10 = i9;
        if (isChecked()) {
            i10 = i9 + 1;
        }
        int[] iArr = new int[i10];
        int i11 = 0;
        if (isEnabled()) {
            iArr[0] = 16842910;
            i11 = 1;
        }
        if (this.f3541n) {
            iArr[i11] = 16842908;
            i11++;
        }
        if (this.f3540m) {
            iArr[i11] = 16843623;
            i11++;
        }
        if (this.f3539l) {
            iArr[i11] = 16842919;
            i11++;
        }
        if (isChecked()) {
            iArr[i11] = 16842913;
        }
        if (Arrays.equals(fVar2.f10285z0, iArr)) {
            return;
        }
        fVar2.f10285z0 = iArr;
        if (fVar2.d0() && fVar2.G(fVar2.getState(), iArr)) {
            invalidate();
        }
    }

    public final boolean e() {
        f fVar = this.f3532e;
        return fVar != null && fVar.R;
    }

    public final void f() {
        f fVar;
        if (!d() || (fVar = this.f3532e) == null || !fVar.L || this.f3535h == null) {
            d1.o(this, null);
            this.f3547t = false;
        } else {
            d1.o(this, this.f3546s);
            this.f3547t = true;
        }
    }

    public final void g() {
        if (a.USE_FRAMEWORK_RIPPLE) {
            h();
            return;
        }
        f fVar = this.f3532e;
        if (!fVar.A0) {
            fVar.A0 = true;
            fVar.B0 = a.c(fVar.E);
            fVar.onStateChange(fVar.getState());
        }
        Drawable backgroundDrawable = getBackgroundDrawable();
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.q(this, backgroundDrawable);
        i();
        if (getBackgroundDrawable() == this.f3533f && this.f3532e.getCallback() == null) {
            this.f3532e.setCallback(this.f3533f);
        }
    }

    @Override // android.widget.CheckBox, android.widget.CompoundButton, android.widget.Button, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        if (!TextUtils.isEmpty(this.f3545r)) {
            return this.f3545r;
        }
        if (!e()) {
            return isClickable() ? "android.widget.Button" : "android.view.View";
        }
        ViewParent parent = getParent();
        return ((parent instanceof ChipGroup) && ((ChipGroup) parent).f3555h.f3794d) ? "android.widget.RadioButton" : "android.widget.Button";
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.f3533f;
        return insetDrawable == null ? this.f3532e : insetDrawable;
    }

    public Drawable getCheckedIcon() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.T;
        }
        return null;
    }

    public ColorStateList getCheckedIconTint() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.U;
        }
        return null;
    }

    public ColorStateList getChipBackgroundColor() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.f10284z;
        }
        return null;
    }

    public float getChipCornerRadius() {
        f fVar = this.f3532e;
        return fVar != null ? Math.max(CropImageView.DEFAULT_ASPECT_RATIO, fVar.C()) : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public Drawable getChipDrawable() {
        return this.f3532e;
    }

    public float getChipEndPadding() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.f10262e0 : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public Drawable getChipIcon() {
        Drawable drawable;
        f fVar = this.f3532e;
        if (fVar == null || (drawable = fVar.H) == null) {
            return null;
        }
        return z.p1(drawable);
    }

    public float getChipIconSize() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.J : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public ColorStateList getChipIconTint() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.I;
        }
        return null;
    }

    public float getChipMinHeight() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.A : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float getChipStartPadding() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.X : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public ColorStateList getChipStrokeColor() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.C;
        }
        return null;
    }

    public float getChipStrokeWidth() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.D : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        Drawable drawable;
        f fVar = this.f3532e;
        if (fVar == null || (drawable = fVar.M) == null) {
            return null;
        }
        return z.p1(drawable);
    }

    public CharSequence getCloseIconContentDescription() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.Q;
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.f10261d0 : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float getCloseIconSize() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.P : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float getCloseIconStartPadding() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.f10260c0 : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public ColorStateList getCloseIconTint() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.O;
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.D0;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public final void getFocusedRect(Rect rect) {
        if (this.f3547t) {
            d dVar = this.f3546s;
            if (dVar.f9229l == 1 || dVar.f9228k == 1) {
                rect.set(getCloseIconTouchBoundsInt());
                return;
            }
        }
        super.getFocusedRect(rect);
    }

    public r2.e getHideMotionSpec() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.W;
        }
        return null;
    }

    public float getIconEndPadding() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.Z : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float getIconStartPadding() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.Y : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public ColorStateList getRippleColor() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.E;
        }
        return null;
    }

    public p getShapeAppearanceModel() {
        return this.f3532e.f8263a.f8241a;
    }

    public r2.e getShowMotionSpec() {
        f fVar = this.f3532e;
        if (fVar != null) {
            return fVar.V;
        }
        return null;
    }

    public float getTextEndPadding() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.f10259b0 : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float getTextStartPadding() {
        f fVar = this.f3532e;
        return fVar != null ? fVar.f10258a0 : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final void h() {
        this.f3534g = new RippleDrawable(a.c(this.f3532e.E), getBackgroundDrawable(), null);
        f fVar = this.f3532e;
        if (fVar.A0) {
            fVar.A0 = false;
            fVar.B0 = null;
            fVar.onStateChange(fVar.getState());
        }
        RippleDrawable rippleDrawable = this.f3534g;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.q(this, rippleDrawable);
        i();
    }

    public final void i() {
        f fVar;
        if (TextUtils.isEmpty(getText()) || (fVar = this.f3532e) == null) {
            return;
        }
        int B = (int) (fVar.B() + fVar.f10262e0 + fVar.f10259b0);
        f fVar2 = this.f3532e;
        int A2 = (int) (fVar2.A() + fVar2.X + fVar2.f10258a0);
        if (this.f3533f != null) {
            Rect rect = new Rect();
            this.f3533f.getPadding(rect);
            A2 += rect.left;
            B += rect.right;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i7 = d1.OVER_SCROLL_ALWAYS;
        m0.k(this, A2, paddingTop, B, paddingBottom);
    }

    public final void j() {
        TextPaint paint = getPaint();
        f fVar = this.f3532e;
        if (fVar != null) {
            paint.drawableState = fVar.getState();
        }
        m3.e textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.e(getContext(), paint, this.f3550w);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        l6.f.B0(this, this.f3532e);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i7 + 2);
        if (isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, f3531z);
        }
        if (e()) {
            View.mergeDrawableStates(onCreateDrawableState, A);
        }
        return onCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z6, int i7, Rect rect) {
        super.onFocusChanged(z6, i7, rect);
        if (this.f3547t) {
            d dVar = this.f3546s;
            int i8 = dVar.f9229l;
            if (i8 != Integer.MIN_VALUE) {
                dVar.j(i8);
            }
            if (z6) {
                dVar.q(i7, rect);
            }
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i7;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getAccessibilityClassName());
        accessibilityNodeInfo.setCheckable(e());
        accessibilityNodeInfo.setClickable(isClickable());
        if (getParent() instanceof ChipGroup) {
            ChipGroup chipGroup = (ChipGroup) getParent();
            if (chipGroup.f3769c) {
                int i8 = 0;
                int i9 = 0;
                while (true) {
                    if (i8 >= chipGroup.getChildCount()) {
                        i9 = -1;
                        break;
                    }
                    View childAt = chipGroup.getChildAt(i8);
                    if ((childAt instanceof Chip) && chipGroup.getChildAt(i8).getVisibility() == 0) {
                        if (((Chip) childAt) == this) {
                            break;
                        } else {
                            i9++;
                        }
                    }
                    i8++;
                }
                i7 = i9;
            } else {
                i7 = -1;
            }
            Object tag = getTag(g.row_index_key);
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) o.b(tag instanceof Integer ? ((Integer) tag).intValue() : -1, 1, i7, 1, false, isChecked()).f2027a);
        }
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i7) {
        return (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) ? PointerIcon.getSystemIcon(getContext(), 1002) : super.onResolvePointerIcon(motionEvent, i7);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        super.onRtlPropertiesChanged(i7);
        if (this.f3543p != i7) {
            this.f3543p = i7;
            i();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0048, code lost:
    
        if (r0 != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001e, code lost:
    
        if (r0 != 3) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L4b
            if (r0 == r3) goto L2b
            r4 = 2
            if (r0 == r4) goto L21
            r1 = 3
            if (r0 == r1) goto L44
            goto L51
        L21:
            boolean r0 = r5.f3539l
            if (r0 == 0) goto L51
            if (r1 != 0) goto L57
            r5.setCloseIconPressed(r2)
            goto L57
        L2b:
            boolean r0 = r5.f3539l
            if (r0 == 0) goto L44
            r5.playSoundEffect(r2)
            android.view.View$OnClickListener r0 = r5.f3535h
            if (r0 == 0) goto L39
            r0.onClick(r5)
        L39:
            boolean r0 = r5.f3547t
            if (r0 == 0) goto L42
            z2.d r0 = r5.f3546s
            r0.x(r3, r3)
        L42:
            r0 = r3
            goto L45
        L44:
            r0 = r2
        L45:
            r5.setCloseIconPressed(r2)
            if (r0 != 0) goto L57
            goto L51
        L4b:
            if (r1 == 0) goto L51
            r5.setCloseIconPressed(r3)
            goto L57
        L51:
            boolean r6 = super.onTouchEvent(r6)
            if (r6 == 0) goto L58
        L57:
            r2 = r3
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setAccessibilityClassName(CharSequence charSequence) {
        this.f3545r = charSequence;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f3534g) {
            super.setBackground(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i7) {
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f3534g) {
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundResource(int i7) {
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
    }

    public void setCheckable(boolean z6) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.H(z6);
        }
    }

    public void setCheckableResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.H(fVar.f10263f0.getResources().getBoolean(i7));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z6) {
        f fVar = this.f3532e;
        if (fVar == null) {
            this.f3538k = z6;
        } else if (fVar.R) {
            super.setChecked(z6);
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.I(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z6) {
        setCheckedIconVisible(z6);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int i7) {
        setCheckedIconVisible(i7);
    }

    public void setCheckedIconResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.I(l6.f.A(fVar.f10263f0, i7));
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.J(colorStateList);
        }
    }

    public void setCheckedIconTintResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.J(g0.g.b(fVar.f10263f0, i7));
        }
    }

    public void setCheckedIconVisible(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.K(fVar.f10263f0.getResources().getBoolean(i7));
        }
    }

    public void setCheckedIconVisible(boolean z6) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.K(z6);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList) {
        f fVar = this.f3532e;
        if (fVar == null || fVar.f10284z == colorStateList) {
            return;
        }
        fVar.f10284z = colorStateList;
        fVar.onStateChange(fVar.getState());
    }

    public void setChipBackgroundColorResource(int i7) {
        ColorStateList b7;
        f fVar = this.f3532e;
        if (fVar == null || fVar.f10284z == (b7 = g0.g.b(fVar.f10263f0, i7))) {
            return;
        }
        fVar.f10284z = b7;
        fVar.onStateChange(fVar.getState());
    }

    @Deprecated
    public void setChipCornerRadius(float f7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.L(f7);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.L(fVar.f10263f0.getResources().getDimension(i7));
        }
    }

    public void setChipDrawable(f fVar) {
        f fVar2 = this.f3532e;
        if (fVar2 != fVar) {
            if (fVar2 != null) {
                fVar2.C0 = new WeakReference(null);
            }
            this.f3532e = fVar;
            fVar.E0 = false;
            fVar.C0 = new WeakReference(this);
            c(this.f3544q);
        }
    }

    public void setChipEndPadding(float f7) {
        f fVar = this.f3532e;
        if (fVar == null || fVar.f10262e0 == f7) {
            return;
        }
        fVar.f10262e0 = f7;
        fVar.invalidateSelf();
        fVar.F();
    }

    public void setChipEndPaddingResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            float dimension = fVar.f10263f0.getResources().getDimension(i7);
            if (fVar.f10262e0 != dimension) {
                fVar.f10262e0 = dimension;
                fVar.invalidateSelf();
                fVar.F();
            }
        }
    }

    public void setChipIcon(Drawable drawable) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.M(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z6) {
        setChipIconVisible(z6);
    }

    @Deprecated
    public void setChipIconEnabledResource(int i7) {
        setChipIconVisible(i7);
    }

    public void setChipIconResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.M(l6.f.A(fVar.f10263f0, i7));
        }
    }

    public void setChipIconSize(float f7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.N(f7);
        }
    }

    public void setChipIconSizeResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.N(fVar.f10263f0.getResources().getDimension(i7));
        }
    }

    public void setChipIconTint(ColorStateList colorStateList) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.O(colorStateList);
        }
    }

    public void setChipIconTintResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.O(g0.g.b(fVar.f10263f0, i7));
        }
    }

    public void setChipIconVisible(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.P(fVar.f10263f0.getResources().getBoolean(i7));
        }
    }

    public void setChipIconVisible(boolean z6) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.P(z6);
        }
    }

    public void setChipMinHeight(float f7) {
        f fVar = this.f3532e;
        if (fVar == null || fVar.A == f7) {
            return;
        }
        fVar.A = f7;
        fVar.invalidateSelf();
        fVar.F();
    }

    public void setChipMinHeightResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            float dimension = fVar.f10263f0.getResources().getDimension(i7);
            if (fVar.A != dimension) {
                fVar.A = dimension;
                fVar.invalidateSelf();
                fVar.F();
            }
        }
    }

    public void setChipStartPadding(float f7) {
        f fVar = this.f3532e;
        if (fVar == null || fVar.X == f7) {
            return;
        }
        fVar.X = f7;
        fVar.invalidateSelf();
        fVar.F();
    }

    public void setChipStartPaddingResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            float dimension = fVar.f10263f0.getResources().getDimension(i7);
            if (fVar.X != dimension) {
                fVar.X = dimension;
                fVar.invalidateSelf();
                fVar.F();
            }
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.Q(colorStateList);
        }
    }

    public void setChipStrokeColorResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.Q(g0.g.b(fVar.f10263f0, i7));
        }
    }

    public void setChipStrokeWidth(float f7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.R(f7);
        }
    }

    public void setChipStrokeWidthResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.R(fVar.f10263f0.getResources().getDimension(i7));
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(int i7) {
        setText(getResources().getString(i7));
    }

    public void setCloseIcon(Drawable drawable) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.S(drawable);
        }
        f();
    }

    public void setCloseIconContentDescription(CharSequence charSequence) {
        f fVar = this.f3532e;
        if (fVar == null || fVar.Q == charSequence) {
            return;
        }
        n0.c c7 = n0.c.c();
        fVar.Q = c7.d(charSequence, c7.f7575c);
        fVar.invalidateSelf();
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z6) {
        setCloseIconVisible(z6);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int i7) {
        setCloseIconVisible(i7);
    }

    public void setCloseIconEndPadding(float f7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.T(f7);
        }
    }

    public void setCloseIconEndPaddingResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.T(fVar.f10263f0.getResources().getDimension(i7));
        }
    }

    public void setCloseIconResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.S(l6.f.A(fVar.f10263f0, i7));
        }
        f();
    }

    public void setCloseIconSize(float f7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.U(f7);
        }
    }

    public void setCloseIconSizeResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.U(fVar.f10263f0.getResources().getDimension(i7));
        }
    }

    public void setCloseIconStartPadding(float f7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.V(f7);
        }
    }

    public void setCloseIconStartPaddingResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.V(fVar.f10263f0.getResources().getDimension(i7));
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.W(colorStateList);
        }
    }

    public void setCloseIconTintResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.W(g0.g.b(fVar.f10263f0, i7));
        }
    }

    public void setCloseIconVisible(int i7) {
        setCloseIconVisible(getResources().getBoolean(i7));
    }

    public void setCloseIconVisible(boolean z6) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.X(z6);
        }
        f();
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i7, int i8, int i9, int i10) {
        if (i7 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i9 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i7, i8, i9, i10);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i7, int i8, int i9, int i10) {
        if (i7 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i9 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i7, i8, i9, i10);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.n(f7);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f3532e == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.D0 = truncateAt;
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z6) {
        this.f3542o = z6;
        c(this.f3544q);
    }

    @Override // android.widget.TextView
    public void setGravity(int i7) {
        if (i7 != 8388627) {
            return;
        }
        super.setGravity(i7);
    }

    public void setHideMotionSpec(r2.e eVar) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.W = eVar;
        }
    }

    public void setHideMotionSpecResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.W = r2.e.b(fVar.f10263f0, i7);
        }
    }

    public void setIconEndPadding(float f7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.Y(f7);
        }
    }

    public void setIconEndPaddingResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.Y(fVar.f10263f0.getResources().getDimension(i7));
        }
    }

    public void setIconStartPadding(float f7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.Z(f7);
        }
    }

    public void setIconStartPaddingResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.Z(fVar.f10263f0.getResources().getDimension(i7));
        }
    }

    @Override // com.google.android.material.internal.i
    public void setInternalOnCheckedChangeListener(h hVar) {
        this.f3537j = hVar;
    }

    @Override // android.view.View
    public void setLayoutDirection(int i7) {
        if (this.f3532e == null) {
            return;
        }
        super.setLayoutDirection(i7);
    }

    @Override // android.widget.TextView
    public void setLines(int i7) {
        if (i7 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i7);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i7) {
        if (i7 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i7);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int i7) {
        super.setMaxWidth(i7);
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.F0 = i7;
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i7) {
        if (i7 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i7);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f3536i = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.f3535h = onClickListener;
        f();
    }

    public void setRippleColor(ColorStateList colorStateList) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.a0(colorStateList);
        }
        if (this.f3532e.A0) {
            return;
        }
        h();
    }

    public void setRippleColorResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.a0(g0.g.b(fVar.f10263f0, i7));
            if (this.f3532e.A0) {
                return;
            }
            h();
        }
    }

    @Override // p3.a0
    public void setShapeAppearanceModel(p pVar) {
        this.f3532e.setShapeAppearanceModel(pVar);
    }

    public void setShowMotionSpec(r2.e eVar) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.V = eVar;
        }
    }

    public void setShowMotionSpecResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.V = r2.e.b(fVar.f10263f0, i7);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z6) {
        if (!z6) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z6);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        f fVar = this.f3532e;
        if (fVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = h0.FRAGMENT_ENCODE_SET;
        }
        super.setText(fVar.E0 ? null : charSequence, bufferType);
        f fVar2 = this.f3532e;
        if (fVar2 == null || TextUtils.equals(fVar2.F, charSequence)) {
            return;
        }
        fVar2.F = charSequence;
        fVar2.f10269l0.f3800e = true;
        fVar2.invalidateSelf();
        fVar2.F();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i7) {
        super.setTextAppearance(i7);
        f fVar = this.f3532e;
        if (fVar != null) {
            Context context = fVar.f10263f0;
            fVar.f10269l0.c(new m3.e(context, i7), context);
        }
        j();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        f fVar = this.f3532e;
        if (fVar != null) {
            Context context2 = fVar.f10263f0;
            fVar.f10269l0.c(new m3.e(context2, i7), context2);
        }
        j();
    }

    public void setTextAppearance(m3.e eVar) {
        f fVar = this.f3532e;
        if (fVar != null) {
            fVar.f10269l0.c(eVar, fVar.f10263f0);
        }
        j();
    }

    public void setTextAppearanceResource(int i7) {
        setTextAppearance(getContext(), i7);
    }

    public void setTextEndPadding(float f7) {
        f fVar = this.f3532e;
        if (fVar == null || fVar.f10259b0 == f7) {
            return;
        }
        fVar.f10259b0 = f7;
        fVar.invalidateSelf();
        fVar.F();
    }

    public void setTextEndPaddingResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            float dimension = fVar.f10263f0.getResources().getDimension(i7);
            if (fVar.f10259b0 != dimension) {
                fVar.f10259b0 = dimension;
                fVar.invalidateSelf();
                fVar.F();
            }
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i7, float f7) {
        super.setTextSize(i7, f7);
        f fVar = this.f3532e;
        if (fVar != null) {
            float applyDimension = TypedValue.applyDimension(i7, f7, getResources().getDisplayMetrics());
            b0 b0Var = fVar.f10269l0;
            m3.e eVar = b0Var.f3802g;
            if (eVar != null) {
                eVar.f7547k = applyDimension;
                b0Var.f3796a.setTextSize(applyDimension);
                fVar.a();
            }
        }
        j();
    }

    public void setTextStartPadding(float f7) {
        f fVar = this.f3532e;
        if (fVar == null || fVar.f10258a0 == f7) {
            return;
        }
        fVar.f10258a0 = f7;
        fVar.invalidateSelf();
        fVar.F();
    }

    public void setTextStartPaddingResource(int i7) {
        f fVar = this.f3532e;
        if (fVar != null) {
            float dimension = fVar.f10263f0.getResources().getDimension(i7);
            if (fVar.f10258a0 != dimension) {
                fVar.f10258a0 = dimension;
                fVar.invalidateSelf();
                fVar.F();
            }
        }
    }
}
