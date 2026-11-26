package androidx.recyclerview.widget;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i0 implements v1 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ p0 f2489a;

    public i0(p0 p0Var) {
        this.f2489a = p0Var;
    }

    @Override // androidx.recyclerview.widget.v1
    public final boolean a(MotionEvent motionEvent) {
        int findPointerIndex;
        p0 p0Var = this.f2489a;
        p0Var.f2595y.C(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        j0 j0Var = null;
        if (actionMasked == 0) {
            p0Var.f2582l = motionEvent.getPointerId(0);
            p0Var.f2574d = motionEvent.getX();
            p0Var.f2575e = motionEvent.getY();
            VelocityTracker velocityTracker = p0Var.f2590t;
            if (velocityTracker != null) {
                velocityTracker.recycle();
            }
            p0Var.f2590t = VelocityTracker.obtain();
            if (p0Var.f2573c == null) {
                ArrayList arrayList = p0Var.f2586p;
                if (!arrayList.isEmpty()) {
                    View k7 = p0Var.k(motionEvent);
                    int size = arrayList.size() - 1;
                    while (true) {
                        if (size < 0) {
                            break;
                        }
                        j0 j0Var2 = (j0) arrayList.get(size);
                        if (j0Var2.f2505e.itemView == k7) {
                            j0Var = j0Var2;
                            break;
                        }
                        size--;
                    }
                }
                if (j0Var != null) {
                    p0Var.f2574d -= j0Var.f2509i;
                    p0Var.f2575e -= j0Var.f2510j;
                    j2 j2Var = j0Var.f2505e;
                    p0Var.j(j2Var, true);
                    if (p0Var.f2571a.remove(j2Var.itemView)) {
                        p0Var.f2583m.clearView(p0Var.f2588r, j2Var);
                    }
                    p0Var.p(j2Var, j0Var.f2506f);
                    p0Var.q(p0Var.f2585o, 0, motionEvent);
                }
            }
        } else if (actionMasked == 3 || actionMasked == 1) {
            p0Var.f2582l = -1;
            p0Var.p(null, 0);
        } else {
            int i7 = p0Var.f2582l;
            if (i7 != -1 && (findPointerIndex = motionEvent.findPointerIndex(i7)) >= 0) {
                p0Var.h(actionMasked, findPointerIndex, motionEvent);
            }
        }
        VelocityTracker velocityTracker2 = p0Var.f2590t;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
        }
        return p0Var.f2573c != null;
    }

    @Override // androidx.recyclerview.widget.v1
    public final void c(MotionEvent motionEvent) {
        p0 p0Var = this.f2489a;
        p0Var.f2595y.C(motionEvent);
        VelocityTracker velocityTracker = p0Var.f2590t;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        if (p0Var.f2582l == -1) {
            return;
        }
        int actionMasked = motionEvent.getActionMasked();
        int findPointerIndex = motionEvent.findPointerIndex(p0Var.f2582l);
        if (findPointerIndex >= 0) {
            p0Var.h(actionMasked, findPointerIndex, motionEvent);
        }
        j2 j2Var = p0Var.f2573c;
        if (j2Var == null) {
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (findPointerIndex >= 0) {
                    p0Var.q(p0Var.f2585o, findPointerIndex, motionEvent);
                    p0Var.n(j2Var);
                    RecyclerView recyclerView = p0Var.f2588r;
                    z zVar = p0Var.f2589s;
                    recyclerView.removeCallbacks(zVar);
                    zVar.run();
                    p0Var.f2588r.invalidate();
                    return;
                }
                return;
            }
            if (actionMasked != 3) {
                if (actionMasked != 6) {
                    return;
                }
                int actionIndex = motionEvent.getActionIndex();
                if (motionEvent.getPointerId(actionIndex) == p0Var.f2582l) {
                    p0Var.f2582l = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                    p0Var.q(p0Var.f2585o, actionIndex, motionEvent);
                    return;
                }
                return;
            }
            VelocityTracker velocityTracker2 = p0Var.f2590t;
            if (velocityTracker2 != null) {
                velocityTracker2.clear();
            }
        }
        p0Var.p(null, 0);
        p0Var.f2582l = -1;
    }

    @Override // androidx.recyclerview.widget.v1
    public final void e(boolean z6) {
        if (z6) {
            this.f2489a.p(null, 0);
        }
    }
}
