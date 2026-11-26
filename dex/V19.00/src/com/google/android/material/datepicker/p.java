package com.google.android.material.datepicker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.g2;
import androidx.recyclerview.widget.o1;
import java.util.Calendar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p extends o1 {

    /* renamed from: a, reason: collision with root package name */
    public final Calendar f3650a = j0.g(null);

    /* renamed from: b, reason: collision with root package name */
    public final Calendar f3651b = j0.g(null);

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ t f3652c;

    public p(t tVar) {
        this.f3652c = tVar;
    }

    @Override // androidx.recyclerview.widget.o1
    public final void onDraw(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
        Object obj;
        if ((recyclerView.getAdapter() instanceof m0) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            m0 m0Var = (m0) recyclerView.getAdapter();
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            t tVar = this.f3652c;
            for (o0.c cVar : tVar.f3659c.e()) {
                Object obj2 = cVar.f7788a;
                if (obj2 != null && (obj = cVar.f7789b) != null) {
                    long longValue = ((Long) obj2).longValue();
                    Calendar calendar = this.f3650a;
                    calendar.setTimeInMillis(longValue);
                    long longValue2 = ((Long) obj).longValue();
                    Calendar calendar2 = this.f3651b;
                    calendar2.setTimeInMillis(longValue2);
                    int i7 = calendar.get(1) - m0Var.f3648a.f3660d.f3564a.f3581c;
                    int i8 = calendar2.get(1) - m0Var.f3648a.f3660d.f3564a.f3581c;
                    View r6 = gridLayoutManager.r(i7);
                    View r7 = gridLayoutManager.r(i8);
                    int i9 = gridLayoutManager.F;
                    int i10 = i7 / i9;
                    int i11 = i8 / i9;
                    int i12 = i10;
                    while (i12 <= i11) {
                        if (gridLayoutManager.r(gridLayoutManager.F * i12) != null) {
                            canvas.drawRect((i12 != i10 || r6 == null) ? 0 : (r6.getWidth() / 2) + r6.getLeft(), ((Rect) ((androidx.appcompat.widget.w) tVar.f3664h.f555d).f1436c).top + r10.getTop(), (i12 != i11 || r7 == null) ? recyclerView.getWidth() : (r7.getWidth() / 2) + r7.getLeft(), r10.getBottom() - ((Rect) ((androidx.appcompat.widget.w) tVar.f3664h.f555d).f1436c).bottom, (Paint) tVar.f3664h.f559h);
                        }
                        i12++;
                    }
                }
            }
        }
    }
}
