package com.google.android.material.divider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import g0.d;
import g0.g;
import p0.d1;
import p0.m0;
import p3.j;
import q2.c;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialDivider extends View {

    /* renamed from: f, reason: collision with root package name */
    public static final int f3707f = l.Widget_MaterialComponents_MaterialDivider;

    /* renamed from: a, reason: collision with root package name */
    public final j f3708a;

    /* renamed from: b, reason: collision with root package name */
    public int f3709b;

    /* renamed from: c, reason: collision with root package name */
    public int f3710c;

    /* renamed from: d, reason: collision with root package name */
    public int f3711d;

    /* renamed from: e, reason: collision with root package name */
    public int f3712e;

    public MaterialDivider(Context context) {
        this(context, null);
    }

    public MaterialDivider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.materialDividerStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialDivider(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r4 = com.google.android.material.divider.MaterialDivider.f3707f
            android.content.Context r8 = t3.a.a(r8, r9, r10, r4)
            r7.<init>(r8, r9, r10)
            android.content.Context r8 = r7.getContext()
            p3.j r0 = new p3.j
            r0.<init>()
            r7.f3708a = r0
            int[] r2 = q2.m.MaterialDivider
            r6 = 0
            int[] r5 = new int[r6]
            r0 = r8
            r1 = r9
            r3 = r10
            android.content.res.TypedArray r9 = com.google.android.material.internal.e0.d(r0, r1, r2, r3, r4, r5)
            int r10 = q2.m.MaterialDivider_dividerThickness
            android.content.res.Resources r0 = r7.getResources()
            int r1 = q2.e.material_divider_thickness
            int r0 = r0.getDimensionPixelSize(r1)
            int r10 = r9.getDimensionPixelSize(r10, r0)
            r7.f3709b = r10
            int r10 = q2.m.MaterialDivider_dividerInsetStart
            int r10 = r9.getDimensionPixelOffset(r10, r6)
            r7.f3711d = r10
            int r10 = q2.m.MaterialDivider_dividerInsetEnd
            int r10 = r9.getDimensionPixelOffset(r10, r6)
            r7.f3712e = r10
            int r10 = q2.m.MaterialDivider_dividerColor
            android.content.res.ColorStateList r8 = l6.f.w(r8, r9, r10)
            int r8 = r8.getDefaultColor()
            r7.setDividerColor(r8)
            r9.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.divider.MaterialDivider.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public int getDividerColor() {
        return this.f3710c;
    }

    public int getDividerInsetEnd() {
        return this.f3712e;
    }

    public int getDividerInsetStart() {
        return this.f3711d;
    }

    public int getDividerThickness() {
        return this.f3709b;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int width;
        int i7;
        super.onDraw(canvas);
        int i8 = d1.OVER_SCROLL_ALWAYS;
        boolean z6 = m0.d(this) == 1;
        int i9 = z6 ? this.f3712e : this.f3711d;
        if (z6) {
            width = getWidth();
            i7 = this.f3711d;
        } else {
            width = getWidth();
            i7 = this.f3712e;
        }
        int i10 = width - i7;
        j jVar = this.f3708a;
        jVar.setBounds(i9, 0, i10, getBottom() - getTop());
        jVar.draw(canvas);
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        int mode = View.MeasureSpec.getMode(i8);
        int measuredHeight = getMeasuredHeight();
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int i9 = this.f3709b;
            if (i9 > 0 && measuredHeight != i9) {
                measuredHeight = i9;
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
    }

    public void setDividerColor(int i7) {
        if (this.f3710c != i7) {
            this.f3710c = i7;
            this.f3708a.o(ColorStateList.valueOf(i7));
            invalidate();
        }
    }

    public void setDividerColorResource(int i7) {
        Context context = getContext();
        Object obj = g.f6423a;
        setDividerColor(d.a(context, i7));
    }

    public void setDividerInsetEnd(int i7) {
        this.f3712e = i7;
    }

    public void setDividerInsetEndResource(int i7) {
        setDividerInsetEnd(getContext().getResources().getDimensionPixelOffset(i7));
    }

    public void setDividerInsetStart(int i7) {
        this.f3711d = i7;
    }

    public void setDividerInsetStartResource(int i7) {
        setDividerInsetStart(getContext().getResources().getDimensionPixelOffset(i7));
    }

    public void setDividerThickness(int i7) {
        if (this.f3709b != i7) {
            this.f3709b = i7;
            requestLayout();
        }
    }

    public void setDividerThicknessResource(int i7) {
        setDividerThickness(getContext().getResources().getDimensionPixelSize(i7));
    }
}
