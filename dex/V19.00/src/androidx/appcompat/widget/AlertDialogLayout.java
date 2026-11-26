package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(Context context) {
        super(context);
    }

    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static int l(View view) {
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        int d7 = p0.l0.d(view);
        if (d7 > 0) {
            return d7;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return l(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00a0  */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
        /*
            r9 = this;
            int r10 = r9.getPaddingLeft()
            int r13 = r13 - r11
            int r11 = r9.getPaddingRight()
            int r11 = r13 - r11
            int r13 = r13 - r10
            int r0 = r9.getPaddingRight()
            int r13 = r13 - r0
            int r0 = r9.getMeasuredHeight()
            int r1 = r9.getChildCount()
            int r2 = r9.getGravity()
            r3 = r2 & 112(0x70, float:1.57E-43)
            r4 = 8388615(0x800007, float:1.1754953E-38)
            r2 = r2 & r4
            r4 = 16
            if (r3 == r4) goto L39
            r4 = 80
            if (r3 == r4) goto L30
            int r12 = r9.getPaddingTop()
            goto L43
        L30:
            int r3 = r9.getPaddingTop()
            int r3 = r3 + r14
            int r3 = r3 - r12
            int r12 = r3 - r0
            goto L43
        L39:
            int r3 = r9.getPaddingTop()
            int r14 = r14 - r12
            int r14 = r14 - r0
            int r14 = r14 / 2
            int r12 = r14 + r3
        L43:
            android.graphics.drawable.Drawable r14 = r9.getDividerDrawable()
            r0 = 0
            if (r14 != 0) goto L4c
            r14 = r0
            goto L50
        L4c:
            int r14 = r14.getIntrinsicHeight()
        L50:
            if (r0 >= r1) goto Lb2
            android.view.View r3 = r9.getChildAt(r0)
            if (r3 == 0) goto Laf
            int r4 = r3.getVisibility()
            r5 = 8
            if (r4 == r5) goto Laf
            int r4 = r3.getMeasuredWidth()
            int r5 = r3.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r6 = r3.getLayoutParams()
            androidx.appcompat.widget.w1 r6 = (androidx.appcompat.widget.w1) r6
            int r7 = r6.gravity
            if (r7 >= 0) goto L73
            r7 = r2
        L73:
            int r8 = p0.d1.OVER_SCROLL_ALWAYS
            int r8 = p0.m0.d(r9)
            int r7 = android.view.Gravity.getAbsoluteGravity(r7, r8)
            r7 = r7 & 7
            r8 = 1
            if (r7 == r8) goto L8f
            r8 = 5
            if (r7 == r8) goto L89
            int r7 = r6.leftMargin
            int r7 = r7 + r10
            goto L9a
        L89:
            int r7 = r11 - r4
            int r8 = r6.rightMargin
        L8d:
            int r7 = r7 - r8
            goto L9a
        L8f:
            int r7 = r13 - r4
            int r7 = r7 / 2
            int r7 = r7 + r10
            int r8 = r6.leftMargin
            int r7 = r7 + r8
            int r8 = r6.rightMargin
            goto L8d
        L9a:
            boolean r8 = r9.k(r0)
            if (r8 == 0) goto La1
            int r12 = r12 + r14
        La1:
            int r8 = r6.topMargin
            int r12 = r12 + r8
            int r4 = r4 + r7
            int r8 = r12 + r5
            r3.layout(r7, r12, r4, r8)
            int r3 = r6.bottomMargin
            int r5 = r5 + r3
            int r5 = r5 + r12
            r12 = r5
        Laf:
            int r0 = r0 + 1
            goto L50
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AlertDialogLayout.onLayout(boolean, int, int, int, int):void");
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public final void onMeasure(int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        int childCount = getChildCount();
        View view = null;
        View view2 = null;
        View view3 = null;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                int id = childAt.getId();
                if (id == f.f.topPanel) {
                    view = childAt;
                } else if (id == f.f.buttonPanel) {
                    view2 = childAt;
                } else {
                    if ((id != f.f.contentPanel && id != f.f.customPanel) || view3 != null) {
                        super.onMeasure(i7, i8);
                        return;
                    }
                    view3 = childAt;
                }
            }
        }
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        int mode2 = View.MeasureSpec.getMode(i7);
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (view != null) {
            view.measure(i7, 0);
            paddingBottom += view.getMeasuredHeight();
            i9 = View.combineMeasuredStates(0, view.getMeasuredState());
        } else {
            i9 = 0;
        }
        if (view2 != null) {
            view2.measure(i7, 0);
            i10 = l(view2);
            i11 = view2.getMeasuredHeight() - i10;
            paddingBottom += i10;
            i9 = View.combineMeasuredStates(i9, view2.getMeasuredState());
        } else {
            i10 = 0;
            i11 = 0;
        }
        if (view3 != null) {
            view3.measure(i7, mode == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingBottom), mode));
            i12 = view3.getMeasuredHeight();
            paddingBottom += i12;
            i9 = View.combineMeasuredStates(i9, view3.getMeasuredState());
        } else {
            i12 = 0;
        }
        int i14 = size - paddingBottom;
        if (view2 != null) {
            int i15 = paddingBottom - i10;
            int min = Math.min(i14, i11);
            if (min > 0) {
                i14 -= min;
                i10 += min;
            }
            view2.measure(i7, View.MeasureSpec.makeMeasureSpec(i10, v.c.EXACTLY));
            paddingBottom = i15 + view2.getMeasuredHeight();
            i9 = View.combineMeasuredStates(i9, view2.getMeasuredState());
        }
        if (view3 != null && i14 > 0) {
            view3.measure(i7, View.MeasureSpec.makeMeasureSpec(i12 + i14, mode));
            paddingBottom = (paddingBottom - i12) + view3.getMeasuredHeight();
            i9 = View.combineMeasuredStates(i9, view3.getMeasuredState());
        }
        int i16 = 0;
        for (int i17 = 0; i17 < childCount; i17++) {
            View childAt2 = getChildAt(i17);
            if (childAt2.getVisibility() != 8) {
                i16 = Math.max(i16, childAt2.getMeasuredWidth());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(getPaddingRight() + getPaddingLeft() + i16, i7, i9), View.resolveSizeAndState(paddingBottom, i8, 0));
        if (mode2 != 1073741824) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), v.c.EXACTLY);
            for (int i18 = 0; i18 < childCount; i18++) {
                View childAt3 = getChildAt(i18);
                if (childAt3.getVisibility() != 8) {
                    w1 w1Var = (w1) childAt3.getLayoutParams();
                    if (((LinearLayout.LayoutParams) w1Var).width == -1) {
                        int i19 = ((LinearLayout.LayoutParams) w1Var).height;
                        ((LinearLayout.LayoutParams) w1Var).height = childAt3.getMeasuredHeight();
                        measureChildWithMargins(childAt3, makeMeasureSpec, 0, i8, 0);
                        ((LinearLayout.LayoutParams) w1Var).height = i19;
                    }
                }
            }
        }
    }
}
