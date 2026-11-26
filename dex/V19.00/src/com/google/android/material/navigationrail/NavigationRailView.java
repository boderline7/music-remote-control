package com.google.android.material.navigationrail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import q2.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class NavigationRailView extends NavigationBarView {

    /* renamed from: e, reason: collision with root package name */
    public final int f4017e;

    /* renamed from: f, reason: collision with root package name */
    public final View f4018f;

    /* renamed from: g, reason: collision with root package name */
    public final Boolean f4019g;

    /* renamed from: h, reason: collision with root package name */
    public final Boolean f4020h;

    /* renamed from: i, reason: collision with root package name */
    public final Boolean f4021i;

    public NavigationRailView(Context context) {
        this(context, null);
    }

    public NavigationRailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.navigationRailStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NavigationRailView(android.content.Context r10, android.util.AttributeSet r11, int r12) {
        /*
            r9 = this;
            int r4 = q2.l.Widget_MaterialComponents_NavigationRailView
            r9.<init>(r10, r11, r12, r4)
            r10 = 0
            r9.f4019g = r10
            r9.f4020h = r10
            r9.f4021i = r10
            android.content.res.Resources r0 = r9.getResources()
            int r1 = q2.e.mtrl_navigation_rail_margin
            int r6 = r0.getDimensionPixelSize(r1)
            r9.f4017e = r6
            android.content.Context r7 = r9.getContext()
            int[] r2 = q2.m.NavigationRailView
            r8 = 0
            int[] r5 = new int[r8]
            r0 = r7
            r1 = r11
            r3 = r12
            androidx.appcompat.app.g r11 = com.google.android.material.internal.e0.e(r0, r1, r2, r3, r4, r5)
            int r12 = q2.m.NavigationRailView_headerLayout
            int r12 = r11.N(r12, r8)
            r0 = 49
            if (r12 == 0) goto L56
            android.content.Context r1 = r9.getContext()
            android.view.LayoutInflater r1 = android.view.LayoutInflater.from(r1)
            android.view.View r12 = r1.inflate(r12, r9, r8)
            android.view.View r1 = r9.f4018f
            if (r1 == 0) goto L47
            r9.removeView(r1)
            r9.f4018f = r10
        L47:
            r9.f4018f = r12
            android.widget.FrameLayout$LayoutParams r10 = new android.widget.FrameLayout$LayoutParams
            r1 = -2
            r10.<init>(r1, r1)
            r10.gravity = r0
            r10.topMargin = r6
            r9.addView(r12, r8, r10)
        L56:
            int r10 = q2.m.NavigationRailView_menuGravity
            int r10 = r11.L(r10, r0)
            r9.setMenuGravity(r10)
            int r10 = q2.m.NavigationRailView_itemMinHeight
            boolean r10 = r11.R(r10)
            if (r10 == 0) goto L71
            int r10 = q2.m.NavigationRailView_itemMinHeight
            r12 = -1
            int r10 = r11.H(r10, r12)
            r9.setItemMinimumHeight(r10)
        L71:
            int r10 = q2.m.NavigationRailView_paddingTopSystemWindowInsets
            boolean r10 = r11.R(r10)
            if (r10 == 0) goto L85
            int r10 = q2.m.NavigationRailView_paddingTopSystemWindowInsets
            boolean r10 = r11.E(r10, r8)
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r9.f4019g = r10
        L85:
            int r10 = q2.m.NavigationRailView_paddingBottomSystemWindowInsets
            boolean r10 = r11.R(r10)
            if (r10 == 0) goto L99
            int r10 = q2.m.NavigationRailView_paddingBottomSystemWindowInsets
            boolean r10 = r11.E(r10, r8)
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r9.f4020h = r10
        L99:
            int r10 = q2.m.NavigationRailView_paddingStartSystemWindowInsets
            boolean r10 = r11.R(r10)
            if (r10 == 0) goto Lad
            int r10 = q2.m.NavigationRailView_paddingStartSystemWindowInsets
            boolean r10 = r11.E(r10, r8)
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r9.f4021i = r10
        Lad:
            android.content.res.Resources r10 = r9.getResources()
            int r12 = q2.e.m3_navigation_rail_item_padding_top_with_large_font
            int r10 = r10.getDimensionPixelOffset(r12)
            android.content.res.Resources r12 = r9.getResources()
            int r0 = q2.e.m3_navigation_rail_item_padding_bottom_with_large_font
            int r12 = r12.getDimensionPixelOffset(r0)
            android.content.res.Resources r0 = r7.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            float r0 = r0.fontScale
            r1 = 1065353216(0x3f800000, float:1.0)
            float r0 = r0 - r1
            r2 = 0
            r3 = 1050253722(0x3e99999a, float:0.3)
            float r0 = r2.a.b(r2, r1, r3, r1, r0)
            int r1 = r9.getItemPaddingTop()
            int r10 = r2.a.c(r1, r0, r10)
            float r10 = (float) r10
            int r1 = r9.getItemPaddingBottom()
            int r12 = r2.a.c(r1, r0, r12)
            float r12 = (float) r12
            int r10 = java.lang.Math.round(r10)
            r9.setItemPaddingTop(r10)
            int r10 = java.lang.Math.round(r12)
            r9.setItemPaddingBottom(r10)
            r11.V()
            v2.a r10 = new v2.a
            r11 = 10
            r10.<init>(r11, r9)
            com.google.android.material.internal.k0.b(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigationrail.NavigationRailView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private NavigationRailMenuView getNavigationRailMenuView() {
        return (NavigationRailMenuView) getMenuView();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public final NavigationBarMenuView a(Context context) {
        return new NavigationRailMenuView(context);
    }

    public View getHeaderView() {
        return this.f4018f;
    }

    public int getItemMinimumHeight() {
        return ((NavigationRailMenuView) getMenuView()).getItemMinimumHeight();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 7;
    }

    public int getMenuGravity() {
        return getNavigationRailMenuView().getMenuGravity();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        NavigationRailMenuView navigationRailMenuView = getNavigationRailMenuView();
        View view = this.f4018f;
        int i11 = 0;
        boolean z7 = (view == null || view.getVisibility() == 8) ? false : true;
        int i12 = this.f4017e;
        if (z7) {
            int bottom = this.f4018f.getBottom() + i12;
            int top = navigationRailMenuView.getTop();
            if (top < bottom) {
                i11 = bottom - top;
            }
        } else if ((navigationRailMenuView.I.gravity & z1.c.INVOKE_DIRECT) == 48) {
            i11 = i12;
        }
        if (i11 > 0) {
            navigationRailMenuView.layout(navigationRailMenuView.getLeft(), navigationRailMenuView.getTop() + i11, navigationRailMenuView.getRight(), navigationRailMenuView.getBottom() + i11);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        if (View.MeasureSpec.getMode(i7) != 1073741824 && suggestedMinimumWidth > 0) {
            i7 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i7), getPaddingRight() + getPaddingLeft() + suggestedMinimumWidth), v.c.EXACTLY);
        }
        super.onMeasure(i7, i8);
        View view = this.f4018f;
        if (view == null || view.getVisibility() == 8) {
            return;
        }
        measureChild(getNavigationRailMenuView(), i7, View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - this.f4018f.getMeasuredHeight()) - this.f4017e, Integer.MIN_VALUE));
    }

    public void setItemMinimumHeight(int i7) {
        ((NavigationRailMenuView) getMenuView()).setItemMinimumHeight(i7);
    }

    public void setMenuGravity(int i7) {
        getNavigationRailMenuView().setMenuGravity(i7);
    }
}
