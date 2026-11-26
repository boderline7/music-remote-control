package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m0 extends i {
    final /* synthetic */ o0 this$0;

    public m0(o0 o0Var) {
        this.this$0 = o0Var;
    }

    @Override // androidx.lifecycle.i, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        i5.a.g("activity", activity);
        if (Build.VERSION.SDK_INT < 29) {
            s0.Companion.getClass();
            Fragment findFragmentByTag = activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
            i5.a.e("null cannot be cast to non-null type androidx.lifecycle.ReportFragment", findFragmentByTag);
            ((s0) findFragmentByTag).f2202a = this.this$0.f2199h;
        }
    }

    @Override // androidx.lifecycle.i, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        i5.a.g("activity", activity);
        o0 o0Var = this.this$0;
        int i7 = o0Var.f2193b - 1;
        o0Var.f2193b = i7;
        if (i7 == 0) {
            Handler handler = o0Var.f2196e;
            i5.a.d(handler);
            handler.postDelayed(o0Var.f2198g, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        i5.a.g("activity", activity);
        j0.a(activity, new l0(this.this$0));
    }

    @Override // androidx.lifecycle.i, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        i5.a.g("activity", activity);
        o0 o0Var = this.this$0;
        int i7 = o0Var.f2192a - 1;
        o0Var.f2192a = i7;
        if (i7 == 0 && o0Var.f2194c) {
            o0Var.f2197f.e(q.ON_STOP);
            o0Var.f2195d = true;
        }
    }
}
