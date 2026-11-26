package androidx.appcompat.widget;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d2 implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e2 f1206a;

    public d2(e2 e2Var) {
        this.f1206a = e2Var;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        d0 d0Var;
        int action = motionEvent.getAction();
        int x6 = (int) motionEvent.getX();
        int y6 = (int) motionEvent.getY();
        e2 e2Var = this.f1206a;
        if (action == 0 && (d0Var = e2Var.f1237z) != null && d0Var.isShowing() && x6 >= 0 && x6 < e2Var.f1237z.getWidth() && y6 >= 0 && y6 < e2Var.f1237z.getHeight()) {
            e2Var.f1233v.postDelayed(e2Var.f1229r, 250L);
            return false;
        }
        if (action != 1) {
            return false;
        }
        e2Var.f1233v.removeCallbacks(e2Var.f1229r);
        return false;
    }
}
