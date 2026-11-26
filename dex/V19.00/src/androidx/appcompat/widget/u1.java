package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class u1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1419a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ v1 f1420b;

    public /* synthetic */ u1(v1 v1Var, int i7) {
        this.f1419a = i7;
        this.f1420b = v1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f1419a;
        v1 v1Var = this.f1420b;
        switch (i7) {
            case 0:
                ViewParent parent = v1Var.f1427d.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
            default:
                v1Var.a();
                View view = v1Var.f1427d;
                if (view.isEnabled() && !view.isLongClickable() && v1Var.c()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                    view.onTouchEvent(obtain);
                    obtain.recycle();
                    v1Var.f1430g = true;
                    break;
                }
                break;
        }
    }
}
