package com.navi.tool.ui.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.g2;
import androidx.recyclerview.widget.o1;
import androidx.recyclerview.widget.s1;
import g0.d;
import g0.g;
import g4.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogListDividers {

    public static final class InsetDividerDecoration extends o1 {
        private final boolean drawLast;
        private final int endInsetPx;
        private final int heightPx;
        private final Paint paint;
        private final int startInsetPx;

        private InsetDividerDecoration(int i7, int i8, int i9, int i10, boolean z6) {
            Paint paint = new Paint(1);
            this.paint = paint;
            paint.setColor(i7);
            this.heightPx = Math.max(1, i8);
            this.startInsetPx = Math.max(0, i9);
            this.endInsetPx = Math.max(0, i10);
            this.drawLast = z6;
        }

        @Override // androidx.recyclerview.widget.o1
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, g2 g2Var) {
            recyclerView.getClass();
            int I = RecyclerView.I(view);
            if (I == -1) {
                rect.setEmpty();
            } else {
                rect.set(0, 0, 0, (this.drawLast || I < g2Var.b() + (-1)) ? this.heightPx : 0);
            }
        }

        @Override // androidx.recyclerview.widget.o1
        public void onDraw(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
            int paddingLeft;
            int width;
            int childCount = recyclerView.getChildCount();
            if (childCount != 0 && (width = (recyclerView.getWidth() - recyclerView.getPaddingRight()) - this.endInsetPx) > (paddingLeft = recyclerView.getPaddingLeft() + this.startInsetPx)) {
                for (int i7 = 0; i7 < childCount; i7++) {
                    View childAt = recyclerView.getChildAt(i7);
                    int I = RecyclerView.I(childAt);
                    if (I != -1 && (this.drawLast || I != g2Var.b() - 1)) {
                        canvas.drawRect(paddingLeft, childAt.getBottom() + ((ViewGroup.MarginLayoutParams) ((s1) childAt.getLayoutParams())).bottomMargin, width, this.heightPx + r4, this.paint);
                    }
                }
            }
        }
    }

    private DialogListDividers() {
    }

    private static void apply(RecyclerView recyclerView, int i7, int i8, boolean z6) {
        Context context = recyclerView.getContext();
        int i9 = m.divider_dark;
        Object obj = g.f6423a;
        recyclerView.g(new InsetDividerDecoration(d.a(context, i9), dpToPx(context, 1), dpToPx(context, i7), dpToPx(context, i8), z6));
    }

    public static void applyAppList(RecyclerView recyclerView) {
        apply(recyclerView, 16, 16, false);
    }

    private static int dpToPx(Context context, int i7) {
        return Math.round(i7 * context.getResources().getDisplayMetrics().density);
    }
}
