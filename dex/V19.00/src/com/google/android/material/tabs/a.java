package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.internal.k0;
import com.google.android.material.tabs.TabLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class a {
    public static RectF a(TabLayout tabLayout, View view) {
        if (view == null) {
            return new RectF();
        }
        if (tabLayout.E || !(view instanceof TabLayout.TabView)) {
            return new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        TabLayout.TabView tabView = (TabLayout.TabView) view;
        int contentWidth = tabView.getContentWidth();
        int contentHeight = tabView.getContentHeight();
        int c7 = (int) k0.c(tabView.getContext(), 24);
        if (contentWidth < c7) {
            contentWidth = c7;
        }
        int right = (tabView.getRight() + tabView.getLeft()) / 2;
        int bottom = (tabView.getBottom() + tabView.getTop()) / 2;
        int i7 = contentWidth / 2;
        return new RectF(right - i7, bottom - (contentHeight / 2), i7 + right, (right / 2) + bottom);
    }

    public void b(TabLayout tabLayout, View view, View view2, float f7, Drawable drawable) {
        RectF a7 = a(tabLayout, view);
        RectF a8 = a(tabLayout, view2);
        drawable.setBounds(r2.a.c((int) a7.left, f7, (int) a8.left), drawable.getBounds().top, r2.a.c((int) a7.right, f7, (int) a8.right), drawable.getBounds().bottom);
    }
}
