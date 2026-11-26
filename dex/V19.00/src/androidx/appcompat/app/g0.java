package androidx.appcompat.app;

import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.appcompat.widget.ContentFrameLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g0 extends ContentFrameLayout {

    /* renamed from: i, reason: collision with root package name */
    public final /* synthetic */ j0 f717i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(j0 j0Var, j.f fVar) {
        super(fVar);
        this.f717i = j0Var;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.f717i.v(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int x6 = (int) motionEvent.getX();
            int y6 = (int) motionEvent.getY();
            if (x6 < -5 || y6 < -5 || x6 > getWidth() + 5 || y6 > getHeight() + 5) {
                j0 j0Var = this.f717i;
                j0Var.t(j0Var.B(0), true);
                return true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public final void setBackgroundResource(int i7) {
        setBackgroundDrawable(l6.f.A(getContext(), i7));
    }
}
