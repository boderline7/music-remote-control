package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class o1 {
    @Deprecated
    public void getItemOffsets(Rect rect, int i7, RecyclerView recyclerView) {
        rect.set(0, 0, 0, 0);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, g2 g2Var) {
        getItemOffsets(rect, ((s1) view.getLayoutParams()).f2652a.getLayoutPosition(), recyclerView);
    }

    @Deprecated
    public void onDraw(Canvas canvas, RecyclerView recyclerView) {
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
        onDraw(canvas, recyclerView);
    }

    @Deprecated
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
        onDrawOver(canvas, recyclerView);
    }
}
