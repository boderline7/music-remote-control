package s2;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.appcompat.widget.u;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h extends j {

    /* renamed from: c, reason: collision with root package name */
    public android.support.v4.media.h f8675c;

    /* renamed from: d, reason: collision with root package name */
    public OverScroller f8676d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f8677e;

    /* renamed from: f, reason: collision with root package name */
    public int f8678f;

    /* renamed from: g, reason: collision with root package name */
    public int f8679g;

    /* renamed from: h, reason: collision with root package name */
    public int f8680h;

    /* renamed from: i, reason: collision with root package name */
    public VelocityTracker f8681i;

    public final void A(CoordinatorLayout coordinatorLayout, View view, int i7) {
        z(coordinatorLayout, view, i7, Integer.MIN_VALUE, u.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    @Override // c0.b
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        View view2;
        int findPointerIndex;
        if (this.f8680h < 0) {
            this.f8680h = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f8677e) {
            int i7 = this.f8678f;
            if (i7 == -1 || (findPointerIndex = motionEvent.findPointerIndex(i7)) == -1) {
                return false;
            }
            int y6 = (int) motionEvent.getY(findPointerIndex);
            if (Math.abs(y6 - this.f8679g) > this.f8680h) {
                this.f8679g = y6;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f8678f = -1;
            int x6 = (int) motionEvent.getX();
            int y7 = (int) motionEvent.getY();
            WeakReference weakReference = ((AppBarLayout.BaseBehavior) this).f3233n;
            boolean z6 = (weakReference == null || !((view2 = (View) weakReference.get()) == null || !view2.isShown() || view2.canScrollVertically(-1))) && coordinatorLayout.s(view, x6, y7);
            this.f8677e = z6;
            if (z6) {
                this.f8679g = y7;
                this.f8678f = motionEvent.getPointerId(0);
                if (this.f8681i == null) {
                    this.f8681i = VelocityTracker.obtain();
                }
                OverScroller overScroller = this.f8676d;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f8676d.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.f8681i;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00fc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ec  */
    @Override // c0.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean v(androidx.coordinatorlayout.widget.CoordinatorLayout r21, android.view.View r22, android.view.MotionEvent r23) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.h.v(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract int y();

    public abstract int z(CoordinatorLayout coordinatorLayout, View view, int i7, int i8, int i9);
}
