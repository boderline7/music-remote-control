package androidx.recyclerview.widget;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m0 extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a, reason: collision with root package name */
    public boolean f2536a = true;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ p0 f2537b;

    public m0(p0 p0Var) {
        this.f2537b = p0Var;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final void onLongPress(MotionEvent motionEvent) {
        p0 p0Var;
        View k7;
        j2 J;
        if (this.f2536a && (k7 = (p0Var = this.f2537b).k(motionEvent)) != null && (J = p0Var.f2588r.J(k7)) != null && p0Var.f2583m.hasDragFlag(p0Var.f2588r, J)) {
            int pointerId = motionEvent.getPointerId(0);
            int i7 = p0Var.f2582l;
            if (pointerId == i7) {
                int findPointerIndex = motionEvent.findPointerIndex(i7);
                float x6 = motionEvent.getX(findPointerIndex);
                float y6 = motionEvent.getY(findPointerIndex);
                p0Var.f2574d = x6;
                p0Var.f2575e = y6;
                p0Var.f2579i = CropImageView.DEFAULT_ASPECT_RATIO;
                p0Var.f2578h = CropImageView.DEFAULT_ASPECT_RATIO;
                if (p0Var.f2583m.isLongPressDragEnabled()) {
                    p0Var.p(J, 2);
                }
            }
        }
    }
}
