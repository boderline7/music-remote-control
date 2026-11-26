package androidx.appcompat.widget;

import android.os.Handler;
import android.widget.AbsListView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c2 implements AbsListView.OnScrollListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e2 f1203a;

    public c2(e2 e2Var) {
        this.f1203a = e2Var;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i7, int i8, int i9) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i7) {
        if (i7 == 1) {
            e2 e2Var = this.f1203a;
            if (e2Var.f1237z.getInputMethodMode() == 2 || e2Var.f1237z.getContentView() == null) {
                return;
            }
            Handler handler = e2Var.f1233v;
            x1 x1Var = e2Var.f1229r;
            handler.removeCallbacks(x1Var);
            x1Var.run();
        }
    }
}
