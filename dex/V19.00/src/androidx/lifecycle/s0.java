package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s0 extends Fragment {
    public static final p0 Companion = new p0();

    /* renamed from: a, reason: collision with root package name */
    public n0 f2202a;

    public final void a(q qVar) {
        if (Build.VERSION.SDK_INT < 29) {
            p0 p0Var = Companion;
            Activity activity = getActivity();
            i5.a.f("activity", activity);
            p0Var.getClass();
            p0.a(activity, qVar);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(q.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        a(q.ON_DESTROY);
        this.f2202a = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(q.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        n0 n0Var = this.f2202a;
        if (n0Var != null) {
            n0Var.f2190a.a();
        }
        a(q.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        n0 n0Var = this.f2202a;
        if (n0Var != null) {
            o0 o0Var = n0Var.f2190a;
            int i7 = o0Var.f2192a + 1;
            o0Var.f2192a = i7;
            if (i7 == 1 && o0Var.f2195d) {
                o0Var.f2197f.e(q.ON_START);
                o0Var.f2195d = false;
            }
        }
        a(q.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        a(q.ON_STOP);
    }
}
