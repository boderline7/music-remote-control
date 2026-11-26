package androidx.recyclerview.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class y extends o1 {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    /* renamed from: d, reason: collision with root package name */
    public static final int[] f2709d = {R.attr.listDivider};

    /* renamed from: a, reason: collision with root package name */
    public Drawable f2710a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2711b;

    /* renamed from: c, reason: collision with root package name */
    public final Rect f2712c = new Rect();

    public y(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f2709d);
        this.f2710a = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        this.f2711b = 1;
    }

    @Override // androidx.recyclerview.widget.o1
    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, g2 g2Var) {
        Drawable drawable = this.f2710a;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.f2711b == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    @Override // androidx.recyclerview.widget.o1
    public final void onDraw(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
        int height;
        int i7;
        int width;
        int i8;
        if (recyclerView.getLayoutManager() == null || this.f2710a == null) {
            return;
        }
        int i9 = this.f2711b;
        Rect rect = this.f2712c;
        int i10 = 0;
        if (i9 == 1) {
            canvas.save();
            if (recyclerView.getClipToPadding()) {
                i8 = recyclerView.getPaddingLeft();
                width = recyclerView.getWidth() - recyclerView.getPaddingRight();
                canvas.clipRect(i8, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
            } else {
                width = recyclerView.getWidth();
                i8 = 0;
            }
            int childCount = recyclerView.getChildCount();
            while (i10 < childCount) {
                View childAt = recyclerView.getChildAt(i10);
                RecyclerView.L(childAt, rect);
                int round = Math.round(childAt.getTranslationY()) + rect.bottom;
                this.f2710a.setBounds(i8, round - this.f2710a.getIntrinsicHeight(), width, round);
                this.f2710a.draw(canvas);
                i10++;
            }
            canvas.restore();
            return;
        }
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i7 = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), i7, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            i7 = 0;
        }
        int childCount2 = recyclerView.getChildCount();
        while (i10 < childCount2) {
            View childAt2 = recyclerView.getChildAt(i10);
            recyclerView.getLayoutManager().A(childAt2, rect);
            int round2 = Math.round(childAt2.getTranslationX()) + rect.right;
            this.f2710a.setBounds(round2 - this.f2710a.getIntrinsicWidth(), i7, round2, height);
            this.f2710a.draw(canvas);
            i10++;
        }
        canvas.restore();
    }
}
