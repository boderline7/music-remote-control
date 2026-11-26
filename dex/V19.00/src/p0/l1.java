package p0;

import android.view.View;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l1 {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f8160a;

    public l1(View view) {
        this.f8160a = new WeakReference(view);
    }

    public final void a(float f7) {
        View view = (View) this.f8160a.get();
        if (view != null) {
            view.animate().alpha(f7);
        }
    }

    public final void b() {
        View view = (View) this.f8160a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public final void c(long j7) {
        View view = (View) this.f8160a.get();
        if (view != null) {
            view.animate().setDuration(j7);
        }
    }

    public final void d(m1 m1Var) {
        View view = (View) this.f8160a.get();
        if (view != null) {
            if (m1Var != null) {
                view.animate().setListener(new j1(this, m1Var, view, 0));
            } else {
                view.animate().setListener(null);
            }
        }
    }

    public final void e(float f7) {
        View view = (View) this.f8160a.get();
        if (view != null) {
            view.animate().translationY(f7);
        }
    }
}
