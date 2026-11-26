package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import k3.a;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class NavigationRailMenuView extends NavigationBarMenuView {
    public int H;
    public final FrameLayout.LayoutParams I;

    public NavigationRailMenuView(Context context) {
        super(context);
        this.H = -1;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        this.I = layoutParams;
        layoutParams.gravity = 49;
        setLayoutParams(layoutParams);
        setItemActiveIndicatorResizeable(true);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    public final NavigationBarItemView e(Context context) {
        return new a(context);
    }

    public final int g(View view, int i7, int i8, int i9) {
        int makeMeasureSpec;
        int i10;
        if (view == null) {
            int max = i8 / Math.max(1, i9);
            int i11 = this.H;
            if (i11 == -1) {
                i11 = View.MeasureSpec.getSize(i7);
            }
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(i11, max), 0);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0);
        }
        int childCount = getChildCount();
        int i12 = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt != view) {
                if (childAt.getVisibility() != 8) {
                    childAt.measure(i7, makeMeasureSpec);
                    i10 = childAt.getMeasuredHeight();
                } else {
                    i10 = 0;
                }
                i12 += i10;
            }
        }
        return i12;
    }

    public int getItemMinimumHeight() {
        return this.H;
    }

    public int getMenuGravity() {
        return this.I.gravity;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int childCount = getChildCount();
        int i11 = i9 - i7;
        int i12 = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                int measuredHeight = childAt.getMeasuredHeight() + i12;
                childAt.layout(0, i12, i11, measuredHeight);
                i12 = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        int g7;
        int i9;
        int size = View.MeasureSpec.getSize(i8);
        int size2 = getMenu().l().size();
        if (size2 <= 1 || !NavigationBarMenuView.f(getLabelVisibilityMode(), size2)) {
            g7 = g(null, i7, size, size2);
        } else {
            View childAt = getChildAt(getSelectedItemPosition());
            if (childAt != null) {
                int max = size / Math.max(1, size2);
                int i10 = this.H;
                if (i10 == -1) {
                    i10 = View.MeasureSpec.getSize(i7);
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(i10, max), 0);
                if (childAt.getVisibility() != 8) {
                    childAt.measure(i7, makeMeasureSpec);
                    i9 = childAt.getMeasuredHeight();
                } else {
                    i9 = 0;
                }
                size -= i9;
                size2--;
            } else {
                i9 = 0;
            }
            g7 = g(childAt, i7, size, size2) + i9;
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i7), View.resolveSizeAndState(g7, i8, 0));
    }

    public void setItemMinimumHeight(int i7) {
        if (this.H != i7) {
            this.H = i7;
            requestLayout();
        }
    }

    public void setMenuGravity(int i7) {
        FrameLayout.LayoutParams layoutParams = this.I;
        if (layoutParams.gravity != i7) {
            layoutParams.gravity = i7;
            setLayoutParams(layoutParams);
        }
    }
}
