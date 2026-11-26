package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import q2.c;
import v2.b;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class BottomNavigationView extends NavigationBarView {
    public BottomNavigationView(Context context) {
        this(context, null);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.bottomNavigationStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BottomNavigationView(android.content.Context r7, android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            int r4 = q2.l.Widget_Design_BottomNavigationView
            r6.<init>(r7, r8, r9, r4)
            android.content.Context r0 = r6.getContext()
            int[] r2 = q2.m.BottomNavigationView
            r7 = 0
            int[] r5 = new int[r7]
            r1 = r8
            r3 = r9
            androidx.appcompat.app.g r8 = com.google.android.material.internal.e0.e(r0, r1, r2, r3, r4, r5)
            int r9 = q2.m.BottomNavigationView_itemHorizontalTranslationEnabled
            r0 = 1
            boolean r9 = r8.E(r9, r0)
            r6.setItemHorizontalTranslationEnabled(r9)
            int r9 = q2.m.BottomNavigationView_android_minHeight
            boolean r9 = r8.R(r9)
            if (r9 == 0) goto L2f
            int r9 = q2.m.BottomNavigationView_android_minHeight
            int r9 = r8.H(r9, r7)
            r6.setMinimumHeight(r9)
        L2f:
            int r9 = q2.m.BottomNavigationView_compatShadowEnabled
            r8.E(r9, r0)
            r8.V()
            v2.a r8 = new v2.a
            r8.<init>(r7, r6)
            com.google.android.material.internal.k0.b(r6, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomnavigation.BottomNavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public final NavigationBarMenuView a(Context context) {
        return new BottomNavigationMenuView(context);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 5;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        if (View.MeasureSpec.getMode(i8) != 1073741824 && suggestedMinimumHeight > 0) {
            i8 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i8), getPaddingBottom() + getPaddingTop() + suggestedMinimumHeight), v.c.EXACTLY);
        }
        super.onMeasure(i7, i8);
    }

    public void setItemHorizontalTranslationEnabled(boolean z6) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) getMenuView();
        if (bottomNavigationMenuView.L != z6) {
            bottomNavigationMenuView.setItemHorizontalTranslationEnabled(z6);
            getPresenter().m(false);
        }
    }

    @Deprecated
    public void setOnNavigationItemReselectedListener(b bVar) {
        setOnItemReselectedListener(bVar);
    }

    @Deprecated
    public void setOnNavigationItemSelectedListener(v2.c cVar) {
        setOnItemSelectedListener(cVar);
    }
}
