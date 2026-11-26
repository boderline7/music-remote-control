package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import java.util.ArrayList;
import k.o;
import p0.d1;
import p0.m0;
import q2.e;
import v.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class BottomNavigationMenuView extends NavigationBarMenuView {
    public final int H;
    public final int I;
    public final int J;
    public final int K;
    public boolean L;
    public final ArrayList M;

    public BottomNavigationMenuView(Context context) {
        super(context);
        this.M = new ArrayList();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        Resources resources = getResources();
        this.H = resources.getDimensionPixelSize(e.design_bottom_navigation_item_max_width);
        this.I = resources.getDimensionPixelSize(e.design_bottom_navigation_item_min_width);
        this.J = resources.getDimensionPixelSize(e.design_bottom_navigation_active_item_max_width);
        this.K = resources.getDimensionPixelSize(e.design_bottom_navigation_active_item_min_width);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    public final NavigationBarItemView e(Context context) {
        return new BottomNavigationItemView(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int childCount = getChildCount();
        int i11 = i9 - i7;
        int i12 = i10 - i8;
        int i13 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                int i15 = d1.OVER_SCROLL_ALWAYS;
                if (m0.d(this) == 1) {
                    int i16 = i11 - i13;
                    childAt.layout(i16 - childAt.getMeasuredWidth(), 0, i16, i12);
                } else {
                    childAt.layout(i13, 0, childAt.getMeasuredWidth() + i13, i12);
                }
                i13 += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        int i9;
        int i10;
        o menu = getMenu();
        int size = View.MeasureSpec.getSize(i7);
        int size2 = menu.l().size();
        int childCount = getChildCount();
        ArrayList arrayList = this.M;
        arrayList.clear();
        int size3 = View.MeasureSpec.getSize(i8);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size3, c.EXACTLY);
        boolean f7 = NavigationBarMenuView.f(getLabelVisibilityMode(), size2);
        int i11 = this.J;
        if (f7 && this.L) {
            View childAt = getChildAt(getSelectedItemPosition());
            int visibility = childAt.getVisibility();
            int i12 = this.K;
            if (visibility != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i11, Integer.MIN_VALUE), makeMeasureSpec);
                i12 = Math.max(i12, childAt.getMeasuredWidth());
            }
            int i13 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int min = Math.min(size - (this.I * i13), Math.min(i12, i11));
            int i14 = size - min;
            int min2 = Math.min(i14 / (i13 != 0 ? i13 : 1), this.H);
            int i15 = i14 - (i13 * min2);
            int i16 = 0;
            while (i16 < childCount) {
                if (getChildAt(i16).getVisibility() != 8) {
                    i10 = i16 == getSelectedItemPosition() ? min : min2;
                    if (i15 > 0) {
                        i10++;
                        i15--;
                    }
                } else {
                    i10 = 0;
                }
                arrayList.add(Integer.valueOf(i10));
                i16++;
            }
        } else {
            int min3 = Math.min(size / (size2 != 0 ? size2 : 1), i11);
            int i17 = size - (size2 * min3);
            for (int i18 = 0; i18 < childCount; i18++) {
                if (getChildAt(i18).getVisibility() == 8) {
                    i9 = 0;
                } else if (i17 > 0) {
                    i9 = min3 + 1;
                    i17--;
                } else {
                    i9 = min3;
                }
                arrayList.add(Integer.valueOf(i9));
            }
        }
        int i19 = 0;
        for (int i20 = 0; i20 < childCount; i20++) {
            View childAt2 = getChildAt(i20);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(((Integer) arrayList.get(i20)).intValue(), c.EXACTLY), makeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                i19 = childAt2.getMeasuredWidth() + i19;
            }
        }
        setMeasuredDimension(i19, size3);
    }

    public void setItemHorizontalTranslationEnabled(boolean z6) {
        this.L = z6;
    }
}
