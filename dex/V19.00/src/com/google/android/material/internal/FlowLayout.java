package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class FlowLayout extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    public int f3767a;

    /* renamed from: b, reason: collision with root package name */
    public int f3768b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f3769c;

    /* renamed from: d, reason: collision with root package name */
    public int f3770d;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f3769c = false;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, q2.m.FlowLayout, 0, 0);
        this.f3767a = obtainStyledAttributes.getDimensionPixelSize(q2.m.FlowLayout_lineSpacing, 0);
        this.f3768b = obtainStyledAttributes.getDimensionPixelSize(q2.m.FlowLayout_itemSpacing, 0);
        obtainStyledAttributes.recycle();
    }

    public boolean a() {
        return this.f3769c;
    }

    public int getItemSpacing() {
        return this.f3768b;
    }

    public int getLineSpacing() {
        return this.f3767a;
    }

    public int getRowCount() {
        return this.f3770d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int i11;
        int i12;
        if (getChildCount() == 0) {
            this.f3770d = 0;
            return;
        }
        this.f3770d = 1;
        int i13 = d1.OVER_SCROLL_ALWAYS;
        boolean z7 = m0.d(this) == 1;
        int paddingRight = z7 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = z7 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int i14 = (i9 - i7) - paddingLeft;
        int i15 = paddingRight;
        int i16 = paddingTop;
        for (int i17 = 0; i17 < getChildCount(); i17++) {
            View childAt = getChildAt(i17);
            if (childAt.getVisibility() == 8) {
                childAt.setTag(q2.g.row_index_key, -1);
            } else {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i12 = p0.o.c(marginLayoutParams);
                    i11 = p0.o.b(marginLayoutParams);
                } else {
                    i11 = 0;
                    i12 = 0;
                }
                int measuredWidth = childAt.getMeasuredWidth() + i15 + i12;
                if (!this.f3769c && measuredWidth > i14) {
                    i16 = this.f3767a + paddingTop;
                    this.f3770d++;
                    i15 = paddingRight;
                }
                childAt.setTag(q2.g.row_index_key, Integer.valueOf(this.f3770d - 1));
                int i18 = i15 + i12;
                int measuredWidth2 = childAt.getMeasuredWidth() + i18;
                int measuredHeight = childAt.getMeasuredHeight() + i16;
                if (z7) {
                    childAt.layout(i14 - measuredWidth2, i16, (i14 - i15) - i12, measuredHeight);
                } else {
                    childAt.layout(i18, i16, measuredWidth2, measuredHeight);
                }
                i15 += childAt.getMeasuredWidth() + i12 + i11 + this.f3768b;
                paddingTop = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        int size = View.MeasureSpec.getSize(i7);
        int mode = View.MeasureSpec.getMode(i7);
        int size2 = View.MeasureSpec.getSize(i8);
        int mode2 = View.MeasureSpec.getMode(i8);
        int i13 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = i13 - getPaddingRight();
        int i14 = paddingTop;
        int i15 = 0;
        for (int i16 = 0; i16 < getChildCount(); i16++) {
            View childAt = getChildAt(i16);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i7, i8);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i11 = marginLayoutParams.leftMargin;
                    i10 = marginLayoutParams.rightMargin;
                } else {
                    i10 = 0;
                    i11 = 0;
                }
                int i17 = paddingLeft;
                if (childAt.getMeasuredWidth() + paddingLeft + i11 <= paddingRight || a()) {
                    i12 = i17;
                } else {
                    i12 = getPaddingLeft();
                    i14 = this.f3767a + paddingTop;
                }
                int measuredWidth = childAt.getMeasuredWidth() + i12 + i11;
                int measuredHeight = childAt.getMeasuredHeight() + i14;
                if (measuredWidth > i15) {
                    i15 = measuredWidth;
                }
                int measuredWidth2 = childAt.getMeasuredWidth() + i11 + i10 + this.f3768b + i12;
                if (i16 == getChildCount() - 1) {
                    i15 += i10;
                }
                paddingLeft = measuredWidth2;
                paddingTop = measuredHeight;
            }
        }
        int paddingRight2 = getPaddingRight() + i15;
        int paddingBottom = getPaddingBottom() + paddingTop;
        if (mode != Integer.MIN_VALUE) {
            i9 = v.c.EXACTLY;
            if (mode != 1073741824) {
                size = paddingRight2;
            }
        } else {
            i9 = v.c.EXACTLY;
            size = Math.min(paddingRight2, size);
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(paddingBottom, size2);
        } else if (mode2 != i9) {
            size2 = paddingBottom;
        }
        setMeasuredDimension(size, size2);
    }

    public void setItemSpacing(int i7) {
        this.f3768b = i7;
    }

    public void setLineSpacing(int i7) {
        this.f3767a = i7;
    }

    public void setSingleLine(boolean z6) {
        this.f3769c = z6;
    }
}
