package com.google.android.material.chip;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.internal.FlowLayout;
import com.google.android.material.internal.a;
import java.util.Iterator;
import java.util.List;
import q0.h;
import q2.c;
import q2.l;
import z2.g;
import z2.i;
import z2.j;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ChipGroup extends FlowLayout {

    /* renamed from: k, reason: collision with root package name */
    public static final int f3551k = l.Widget_MaterialComponents_ChipGroup;

    /* renamed from: e, reason: collision with root package name */
    public int f3552e;

    /* renamed from: f, reason: collision with root package name */
    public int f3553f;

    /* renamed from: g, reason: collision with root package name */
    public i f3554g;

    /* renamed from: h, reason: collision with root package name */
    public final a f3555h;

    /* renamed from: i, reason: collision with root package name */
    public final int f3556i;

    /* renamed from: j, reason: collision with root package name */
    public final j f3557j;

    public ChipGroup(Context context) {
        this(context, null);
    }

    public ChipGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.chipGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ChipGroup(android.content.Context r9, android.util.AttributeSet r10, int r11) {
        /*
            r8 = this;
            int r4 = com.google.android.material.chip.ChipGroup.f3551k
            android.content.Context r9 = t3.a.a(r9, r10, r11, r4)
            r8.<init>(r9, r10, r11)
            com.google.android.material.internal.a r9 = new com.google.android.material.internal.a
            r9.<init>()
            r8.f3555h = r9
            z2.j r6 = new z2.j
            r6.<init>(r8)
            r8.f3557j = r6
            android.content.Context r0 = r8.getContext()
            int[] r2 = q2.m.ChipGroup
            r7 = 0
            int[] r5 = new int[r7]
            r1 = r10
            r3 = r11
            android.content.res.TypedArray r10 = com.google.android.material.internal.e0.d(r0, r1, r2, r3, r4, r5)
            int r11 = q2.m.ChipGroup_chipSpacing
            int r11 = r10.getDimensionPixelOffset(r11, r7)
            int r0 = q2.m.ChipGroup_chipSpacingHorizontal
            int r0 = r10.getDimensionPixelOffset(r0, r11)
            r8.setChipSpacingHorizontal(r0)
            int r0 = q2.m.ChipGroup_chipSpacingVertical
            int r11 = r10.getDimensionPixelOffset(r0, r11)
            r8.setChipSpacingVertical(r11)
            int r11 = q2.m.ChipGroup_singleLine
            boolean r11 = r10.getBoolean(r11, r7)
            r8.setSingleLine(r11)
            int r11 = q2.m.ChipGroup_singleSelection
            boolean r11 = r10.getBoolean(r11, r7)
            r8.setSingleSelection(r11)
            int r11 = q2.m.ChipGroup_selectionRequired
            boolean r11 = r10.getBoolean(r11, r7)
            r8.setSelectionRequired(r11)
            int r11 = q2.m.ChipGroup_checkedChip
            r0 = -1
            int r11 = r10.getResourceId(r11, r0)
            r8.f3556i = r11
            r10.recycle()
            v2.a r10 = new v2.a
            r11 = 2
            r10.<init>(r11, r8)
            r9.f3793c = r10
            super.setOnHierarchyChangeListener(r6)
            int r9 = p0.d1.OVER_SCROLL_ALWAYS
            r9 = 1
            p0.l0.s(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.ChipGroup.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private int getVisibleChipCount() {
        int i7 = 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            if ((getChildAt(i8) instanceof Chip) && getChildAt(i8).getVisibility() == 0) {
                i7++;
            }
        }
        return i7;
    }

    @Override // com.google.android.material.internal.FlowLayout
    public final boolean a() {
        return this.f3769c;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof g);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new g(layoutParams);
    }

    public int getCheckedChipId() {
        return this.f3555h.c();
    }

    public List<Integer> getCheckedChipIds() {
        return this.f3555h.b(this);
    }

    public int getChipSpacingHorizontal() {
        return this.f3552e;
    }

    public int getChipSpacingVertical() {
        return this.f3553f;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i7 = this.f3556i;
        if (i7 != -1) {
            a aVar = this.f3555h;
            com.google.android.material.internal.i iVar = (com.google.android.material.internal.i) aVar.f3791a.get(Integer.valueOf(i7));
            if (iVar != null && aVar.a(iVar)) {
                aVar.d();
            }
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) h.a(getRowCount(), this.f3769c ? getVisibleChipCount() : -1, this.f3555h.f3794d ? 1 : 2).f8425a);
    }

    public void setChipSpacing(int i7) {
        setChipSpacingHorizontal(i7);
        setChipSpacingVertical(i7);
    }

    public void setChipSpacingHorizontal(int i7) {
        if (this.f3552e != i7) {
            this.f3552e = i7;
            setItemSpacing(i7);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int i7) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(i7));
    }

    public void setChipSpacingResource(int i7) {
        setChipSpacing(getResources().getDimensionPixelOffset(i7));
    }

    public void setChipSpacingVertical(int i7) {
        if (this.f3553f != i7) {
            this.f3553f = i7;
            setLineSpacing(i7);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int i7) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(i7));
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setDividerDrawableVertical(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setFlexWrap(int i7) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    @Deprecated
    public void setOnCheckedChangeListener(z2.h hVar) {
        if (hVar == null) {
            setOnCheckedStateChangeListener(null);
        } else {
            setOnCheckedStateChangeListener(new i6.i(this, hVar, 16));
        }
    }

    public void setOnCheckedStateChangeListener(i iVar) {
        this.f3554g = iVar;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f3557j.f10286a = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z6) {
        this.f3555h.f3795e = z6;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i7) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i7) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    public void setSingleLine(int i7) {
        setSingleLine(getResources().getBoolean(i7));
    }

    @Override // com.google.android.material.internal.FlowLayout
    public void setSingleLine(boolean z6) {
        super.setSingleLine(z6);
    }

    public void setSingleSelection(int i7) {
        setSingleSelection(getResources().getBoolean(i7));
    }

    public void setSingleSelection(boolean z6) {
        a aVar = this.f3555h;
        if (aVar.f3794d != z6) {
            aVar.f3794d = z6;
            boolean z7 = !aVar.f3792b.isEmpty();
            Iterator it = aVar.f3791a.values().iterator();
            while (it.hasNext()) {
                aVar.e((com.google.android.material.internal.i) it.next(), false);
            }
            if (z7) {
                aVar.d();
            }
        }
    }
}
