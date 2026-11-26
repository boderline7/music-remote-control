package com.google.android.material.bottomappbar;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.k0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g implements View.OnLayoutChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar.Behavior f3372a;

    public g(BottomAppBar.Behavior behavior) {
        this.f3372a = behavior;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        int leftInset;
        int rightInset;
        int bottomInset;
        BottomAppBar.Behavior behavior = this.f3372a;
        BottomAppBar bottomAppBar = (BottomAppBar) behavior.f3352n.get();
        if (bottomAppBar == null || !((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton))) {
            view.removeOnLayoutChangeListener(this);
            return;
        }
        int height = view.getHeight();
        if (view instanceof FloatingActionButton) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            int measuredWidth = floatingActionButton.getMeasuredWidth();
            int measuredHeight = floatingActionButton.getMeasuredHeight();
            Rect rect = behavior.f3351m;
            rect.set(0, 0, measuredWidth, measuredHeight);
            floatingActionButton.j(rect);
            int height2 = rect.height();
            bottomAppBar.L(height2);
            bottomAppBar.setFabCornerSize(floatingActionButton.getShapeAppearanceModel().f8305e.a(new RectF(rect)));
            height = height2;
        }
        c0.e eVar = (c0.e) view.getLayoutParams();
        if (behavior.f3353o == 0) {
            if (bottomAppBar.f3333d0 == 1) {
                int dimensionPixelOffset = bottomAppBar.getResources().getDimensionPixelOffset(q2.e.mtrl_bottomappbar_fab_bottom_margin) - ((view.getMeasuredHeight() - height) / 2);
                bottomInset = bottomAppBar.getBottomInset();
                ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = bottomInset + dimensionPixelOffset;
            }
            leftInset = bottomAppBar.getLeftInset();
            ((ViewGroup.MarginLayoutParams) eVar).leftMargin = leftInset;
            rightInset = bottomAppBar.getRightInset();
            ((ViewGroup.MarginLayoutParams) eVar).rightMargin = rightInset;
            boolean f7 = k0.f(view);
            int i15 = bottomAppBar.f3334e0;
            if (f7) {
                ((ViewGroup.MarginLayoutParams) eVar).leftMargin += i15;
            } else {
                ((ViewGroup.MarginLayoutParams) eVar).rightMargin += i15;
            }
        }
        int i16 = BottomAppBar.f3327v0;
        bottomAppBar.K();
    }
}
