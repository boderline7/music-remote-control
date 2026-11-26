package com.google.android.material.checkbox;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.d;
import com.google.android.material.internal.k0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import o1.b;
import o1.f;
import q2.c;
import q2.g;
import q2.k;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialCheckBox extends AppCompatCheckBox {
    public static final int[] A;
    public static final int[][] B;
    public static final int C;
    public static final int STATE_CHECKED = 1;
    public static final int STATE_INDETERMINATE = 2;
    public static final int STATE_UNCHECKED = 0;

    /* renamed from: y, reason: collision with root package name */
    public static final int f3506y = l.Widget_MaterialComponents_CompoundButton_CheckBox;

    /* renamed from: z, reason: collision with root package name */
    public static final int[] f3507z = {c.state_indeterminate};

    /* renamed from: e, reason: collision with root package name */
    public final LinkedHashSet f3508e;

    /* renamed from: f, reason: collision with root package name */
    public final LinkedHashSet f3509f;

    /* renamed from: g, reason: collision with root package name */
    public ColorStateList f3510g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f3511h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f3512i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f3513j;

    /* renamed from: k, reason: collision with root package name */
    public CharSequence f3514k;

    /* renamed from: l, reason: collision with root package name */
    public Drawable f3515l;

    /* renamed from: m, reason: collision with root package name */
    public Drawable f3516m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f3517n;

    /* renamed from: o, reason: collision with root package name */
    public ColorStateList f3518o;

    /* renamed from: p, reason: collision with root package name */
    public ColorStateList f3519p;

    /* renamed from: q, reason: collision with root package name */
    public PorterDuff.Mode f3520q;

    /* renamed from: r, reason: collision with root package name */
    public int f3521r;

    /* renamed from: s, reason: collision with root package name */
    public int[] f3522s;

    /* renamed from: t, reason: collision with root package name */
    public boolean f3523t;

    /* renamed from: u, reason: collision with root package name */
    public CharSequence f3524u;

    /* renamed from: v, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f3525v;

    /* renamed from: w, reason: collision with root package name */
    public final f f3526w;

    /* renamed from: x, reason: collision with root package name */
    public final l3.c f3527x;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* renamed from: a, reason: collision with root package name */
        public int f3528a;

        public final String toString() {
            StringBuilder sb = new StringBuilder("MaterialCheckBox.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" CheckedState=");
            int i7 = this.f3528a;
            return a.a.i(sb, i7 != 1 ? i7 != 2 ? "unchecked" : "indeterminate" : "checked", "}");
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeValue(Integer.valueOf(this.f3528a));
        }
    }

    static {
        int i7 = c.state_error;
        A = new int[]{i7};
        B = new int[][]{new int[]{R.attr.state_enabled, i7}, new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
        C = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", "android");
    }

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.checkboxStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialCheckBox(android.content.Context r9, android.util.AttributeSet r10, int r11) {
        /*
            r8 = this;
            int r4 = com.google.android.material.checkbox.MaterialCheckBox.f3506y
            android.content.Context r9 = t3.a.a(r9, r10, r11, r4)
            r8.<init>(r9, r10, r11)
            java.util.LinkedHashSet r9 = new java.util.LinkedHashSet
            r9.<init>()
            r8.f3508e = r9
            java.util.LinkedHashSet r9 = new java.util.LinkedHashSet
            r9.<init>()
            r8.f3509f = r9
            android.content.Context r9 = r8.getContext()
            int r0 = q2.f.mtrl_checkbox_button_checked_unchecked
            o1.f r1 = new o1.f
            r1.<init>(r9)
            android.content.res.Resources r2 = r9.getResources()
            android.content.res.Resources$Theme r9 = r9.getTheme()
            java.lang.ThreadLocal r3 = h0.q.f6642a
            android.graphics.drawable.Drawable r9 = h0.j.a(r2, r0, r9)
            r1.f7810a = r9
            g.a r0 = r1.f7809f
            r9.setCallback(r0)
            o1.e r9 = new o1.e
            android.graphics.drawable.Drawable r0 = r1.f7810a
            android.graphics.drawable.Drawable$ConstantState r0 = r0.getConstantState()
            r9.<init>(r0)
            r8.f3526w = r1
            l3.c r9 = new l3.c
            r0 = 2
            r9.<init>(r0, r8)
            r8.f3527x = r9
            android.content.Context r9 = r8.getContext()
            android.graphics.drawable.Drawable r0 = androidx.core.widget.d.a(r8)
            r8.f3515l = r0
            android.content.res.ColorStateList r0 = r8.getSuperButtonTintList()
            r8.f3518o = r0
            r6 = 0
            r8.setSupportButtonTintList(r6)
            int[] r2 = q2.m.MaterialCheckBox
            r7 = 0
            int[] r5 = new int[r7]
            r0 = r9
            r1 = r10
            r3 = r11
            androidx.appcompat.app.g r10 = com.google.android.material.internal.e0.e(r0, r1, r2, r3, r4, r5)
            int r11 = q2.m.MaterialCheckBox_buttonIcon
            android.graphics.drawable.Drawable r11 = r10.I(r11)
            r8.f3516m = r11
            android.graphics.drawable.Drawable r11 = r8.f3515l
            r0 = 1
            if (r11 == 0) goto Lac
            int r11 = q2.c.isMaterial3Theme
            boolean r11 = l6.f.t0(r9, r11, r7)
            if (r11 == 0) goto Lac
            int r11 = q2.m.MaterialCheckBox_android_button
            int r11 = r10.N(r11, r7)
            int r1 = q2.m.MaterialCheckBox_buttonCompat
            int r1 = r10.N(r1, r7)
            int r2 = com.google.android.material.checkbox.MaterialCheckBox.C
            if (r11 != r2) goto Lac
            if (r1 != 0) goto Lac
            super.setButtonDrawable(r6)
            int r11 = q2.f.mtrl_checkbox_button
            android.graphics.drawable.Drawable r11 = l6.f.A(r9, r11)
            r8.f3515l = r11
            r8.f3517n = r0
            android.graphics.drawable.Drawable r11 = r8.f3516m
            if (r11 != 0) goto Lac
            int r11 = q2.f.mtrl_checkbox_button_icon
            android.graphics.drawable.Drawable r11 = l6.f.A(r9, r11)
            r8.f3516m = r11
        Lac:
            int r11 = q2.m.MaterialCheckBox_buttonIconTint
            android.content.res.ColorStateList r9 = l6.f.x(r9, r10, r11)
            r8.f3519p = r9
            int r9 = q2.m.MaterialCheckBox_buttonIconTintMode
            r11 = -1
            int r9 = r10.L(r9, r11)
            android.graphics.PorterDuff$Mode r11 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r9 = com.google.android.material.internal.k0.g(r9, r11)
            r8.f3520q = r9
            int r9 = q2.m.MaterialCheckBox_useMaterialThemeColors
            boolean r9 = r10.E(r9, r7)
            r8.f3511h = r9
            int r9 = q2.m.MaterialCheckBox_centerIfNoTextEnabled
            boolean r9 = r10.E(r9, r0)
            r8.f3512i = r9
            int r9 = q2.m.MaterialCheckBox_errorShown
            boolean r9 = r10.E(r9, r7)
            r8.f3513j = r9
            int r9 = q2.m.MaterialCheckBox_errorAccessibilityLabel
            java.lang.CharSequence r9 = r10.Q(r9)
            r8.f3514k = r9
            int r9 = q2.m.MaterialCheckBox_checkedState
            boolean r9 = r10.R(r9)
            if (r9 == 0) goto Lf4
            int r9 = q2.m.MaterialCheckBox_checkedState
            int r9 = r10.L(r9, r7)
            r8.setCheckedState(r9)
        Lf4:
            r10.V()
            r8.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.checkbox.MaterialCheckBox.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private String getButtonStateDescription() {
        int i7 = this.f3521r;
        return i7 == 1 ? getResources().getString(k.mtrl_checkbox_state_description_checked) : i7 == 0 ? getResources().getString(k.mtrl_checkbox_state_description_unchecked) : getResources().getString(k.mtrl_checkbox_state_description_indeterminate);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f3510g == null) {
            int b7 = b3.a.b(c.colorControlActivated, this);
            int b8 = b3.a.b(c.colorError, this);
            int b9 = b3.a.b(c.colorSurface, this);
            int b10 = b3.a.b(c.colorOnSurface, this);
            this.f3510g = new ColorStateList(B, new int[]{b3.a.f(b9, 1.0f, b8), b3.a.f(b9, 1.0f, b7), b3.a.f(b9, 0.54f, b10), b3.a.f(b9, 0.38f, b10), b3.a.f(b9, 0.38f, b10)});
        }
        return this.f3510g;
    }

    private ColorStateList getSuperButtonTintList() {
        ColorStateList colorStateList = this.f3518o;
        return colorStateList != null ? colorStateList : super.getButtonTintList() != null ? super.getButtonTintList() : getSupportButtonTintList();
    }

    public final void a() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        d dVar;
        this.f3515l = d3.a.b(this.f3515l, this.f3518o, androidx.core.widget.c.b(this));
        this.f3516m = d3.a.b(this.f3516m, this.f3519p, this.f3520q);
        if (this.f3517n) {
            f fVar = this.f3526w;
            if (fVar != null) {
                Drawable drawable = fVar.f7810a;
                l3.c cVar = this.f3527x;
                if (drawable != null) {
                    AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                    if (cVar.f7798a == null) {
                        cVar.f7798a = new b(cVar);
                    }
                    animatedVectorDrawable.unregisterAnimationCallback(cVar.f7798a);
                }
                ArrayList arrayList = fVar.f7808e;
                o1.d dVar2 = fVar.f7805b;
                if (arrayList != null && cVar != null) {
                    arrayList.remove(cVar);
                    if (fVar.f7808e.size() == 0 && (dVar = fVar.f7807d) != null) {
                        dVar2.f7800b.removeListener(dVar);
                        fVar.f7807d = null;
                    }
                }
                Drawable drawable2 = fVar.f7810a;
                if (drawable2 != null) {
                    AnimatedVectorDrawable animatedVectorDrawable2 = (AnimatedVectorDrawable) drawable2;
                    if (cVar.f7798a == null) {
                        cVar.f7798a = new b(cVar);
                    }
                    animatedVectorDrawable2.registerAnimationCallback(cVar.f7798a);
                } else if (cVar != null) {
                    if (fVar.f7808e == null) {
                        fVar.f7808e = new ArrayList();
                    }
                    if (!fVar.f7808e.contains(cVar)) {
                        fVar.f7808e.add(cVar);
                        if (fVar.f7807d == null) {
                            fVar.f7807d = new d(2, fVar);
                        }
                        dVar2.f7800b.addListener(fVar.f7807d);
                    }
                }
            }
            Drawable drawable3 = this.f3515l;
            if ((drawable3 instanceof AnimatedStateListDrawable) && fVar != null) {
                ((AnimatedStateListDrawable) drawable3).addTransition(g.checked, g.unchecked, fVar, false);
                ((AnimatedStateListDrawable) this.f3515l).addTransition(g.indeterminate, g.unchecked, fVar, false);
            }
        }
        Drawable drawable4 = this.f3515l;
        if (drawable4 != null && (colorStateList2 = this.f3518o) != null) {
            j0.b.h(drawable4, colorStateList2);
        }
        Drawable drawable5 = this.f3516m;
        if (drawable5 != null && (colorStateList = this.f3519p) != null) {
            j0.b.h(drawable5, colorStateList);
        }
        super.setButtonDrawable(d3.a.a(this.f3515l, this.f3516m, -1, -1));
        refreshDrawableState();
    }

    @Override // android.widget.CompoundButton
    public Drawable getButtonDrawable() {
        return this.f3515l;
    }

    public Drawable getButtonIconDrawable() {
        return this.f3516m;
    }

    public ColorStateList getButtonIconTintList() {
        return this.f3519p;
    }

    public PorterDuff.Mode getButtonIconTintMode() {
        return this.f3520q;
    }

    @Override // android.widget.CompoundButton
    public ColorStateList getButtonTintList() {
        return this.f3518o;
    }

    public int getCheckedState() {
        return this.f3521r;
    }

    public CharSequence getErrorAccessibilityLabel() {
        return this.f3514k;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final boolean isChecked() {
        return this.f3521r == 1;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f3511h && this.f3518o == null && this.f3519p == null) {
            setUseMaterialThemeColors(true);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i7 + 2);
        if (getCheckedState() == 2) {
            View.mergeDrawableStates(onCreateDrawableState, f3507z);
        }
        if (this.f3513j) {
            View.mergeDrawableStates(onCreateDrawableState, A);
        }
        this.f3522s = d3.a.c(onCreateDrawableState);
        return onCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        Drawable a7;
        if (!this.f3512i || !TextUtils.isEmpty(getText()) || (a7 = androidx.core.widget.d.a(this)) == null) {
            super.onDraw(canvas);
            return;
        }
        int width = ((getWidth() - a7.getIntrinsicWidth()) / 2) * (k0.f(this) ? -1 : 1);
        int save = canvas.save();
        canvas.translate(width, CropImageView.DEFAULT_ASPECT_RATIO);
        super.onDraw(canvas);
        canvas.restoreToCount(save);
        if (getBackground() != null) {
            Rect bounds = a7.getBounds();
            j0.b.f(getBackground(), bounds.left + width, bounds.top, bounds.right + width, bounds.bottom);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && this.f3513j) {
            accessibilityNodeInfo.setText(((Object) accessibilityNodeInfo.getText()) + ", " + ((Object) this.f3514k));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCheckedState(savedState.f3528a);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3528a = getCheckedState();
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(int i7) {
        setButtonDrawable(l6.f.A(getContext(), i7));
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        this.f3515l = drawable;
        this.f3517n = false;
        a();
    }

    public void setButtonIconDrawable(Drawable drawable) {
        this.f3516m = drawable;
        a();
    }

    public void setButtonIconDrawableResource(int i7) {
        setButtonIconDrawable(l6.f.A(getContext(), i7));
    }

    public void setButtonIconTintList(ColorStateList colorStateList) {
        if (this.f3519p == colorStateList) {
            return;
        }
        this.f3519p = colorStateList;
        a();
    }

    public void setButtonIconTintMode(PorterDuff.Mode mode) {
        if (this.f3520q == mode) {
            return;
        }
        this.f3520q = mode;
        a();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintList(ColorStateList colorStateList) {
        if (this.f3518o == colorStateList) {
            return;
        }
        this.f3518o = colorStateList;
        a();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintMode(PorterDuff.Mode mode) {
        setSupportButtonTintMode(mode);
        a();
    }

    public void setCenterIfNoTextEnabled(boolean z6) {
        this.f3512i = z6;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z6) {
        setCheckedState(z6 ? 1 : 0);
    }

    public void setCheckedState(int i7) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        if (this.f3521r != i7) {
            this.f3521r = i7;
            super.setChecked(i7 == 1);
            refreshDrawableState();
            if (Build.VERSION.SDK_INT >= 30 && this.f3524u == null) {
                super.setStateDescription(getButtonStateDescription());
            }
            if (this.f3523t) {
                return;
            }
            this.f3523t = true;
            LinkedHashSet linkedHashSet = this.f3509f;
            if (linkedHashSet != null) {
                Iterator it = linkedHashSet.iterator();
                if (it.hasNext()) {
                    a.a.u(it.next());
                    throw null;
                }
            }
            if (this.f3521r != 2 && (onCheckedChangeListener = this.f3525v) != null) {
                onCheckedChangeListener.onCheckedChanged(this, isChecked());
            }
            AutofillManager autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class);
            if (autofillManager != null) {
                autofillManager.notifyValueChanged(this);
            }
            this.f3523t = false;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z6) {
        super.setEnabled(z6);
    }

    public void setErrorAccessibilityLabel(CharSequence charSequence) {
        this.f3514k = charSequence;
    }

    public void setErrorAccessibilityLabelResource(int i7) {
        setErrorAccessibilityLabel(i7 != 0 ? getResources().getText(i7) : null);
    }

    public void setErrorShown(boolean z6) {
        if (this.f3513j == z6) {
            return;
        }
        this.f3513j = z6;
        refreshDrawableState();
        Iterator it = this.f3508e.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f3525v = onCheckedChangeListener;
    }

    @Override // android.widget.CompoundButton, android.view.View
    public void setStateDescription(CharSequence charSequence) {
        this.f3524u = charSequence;
        if (charSequence != null) {
            super.setStateDescription(charSequence);
        } else {
            if (Build.VERSION.SDK_INT < 30 || charSequence != null) {
                return;
            }
            super.setStateDescription(getButtonStateDescription());
        }
    }

    public void setUseMaterialThemeColors(boolean z6) {
        this.f3511h = z6;
        if (z6) {
            androidx.core.widget.c.c(this, getMaterialThemeColorsTintList());
        } else {
            androidx.core.widget.c.c(this, null);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final void toggle() {
        setChecked(!isChecked());
    }
}
