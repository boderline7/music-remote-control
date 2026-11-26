package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class BaselineLayout extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    public int f3759a;

    public BaselineLayout(Context context) {
        super(context, null, 0);
        this.f3759a = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f3759a = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f3759a = -1;
    }

    @Override // android.view.View
    public int getBaseline() {
        return this.f3759a;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = ((i9 - i7) - getPaddingRight()) - paddingLeft;
        int paddingTop = getPaddingTop();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i12 = ((paddingRight - measuredWidth) / 2) + paddingLeft;
                int baseline = (this.f3759a == -1 || childAt.getBaseline() == -1) ? paddingTop : (this.f3759a + paddingTop) - childAt.getBaseline();
                childAt.layout(i12, baseline, measuredWidth + i12, measuredHeight + baseline);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        int childCount = getChildCount();
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = -1;
        int i13 = -1;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i7, i8);
                int baseline = childAt.getBaseline();
                if (baseline != -1) {
                    i12 = Math.max(i12, baseline);
                    i13 = Math.max(i13, childAt.getMeasuredHeight() - baseline);
                }
                i10 = Math.max(i10, childAt.getMeasuredWidth());
                i9 = Math.max(i9, childAt.getMeasuredHeight());
                i11 = View.combineMeasuredStates(i11, childAt.getMeasuredState());
            }
        }
        if (i12 != -1) {
            i9 = Math.max(i9, Math.max(i13, getPaddingBottom()) + i12);
            this.f3759a = i12;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(i10, getSuggestedMinimumWidth()), i7, i11), View.resolveSizeAndState(Math.max(i9, getSuggestedMinimumHeight()), i8, i11 << 16));
    }
}
