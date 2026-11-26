package androidx.lifecycle;

import android.app.Activity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l0 extends i {
    final /* synthetic */ o0 this$0;

    public l0(o0 o0Var) {
        this.this$0 = o0Var;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostResumed(Activity activity) {
        i5.a.g("activity", activity);
        this.this$0.a();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostStarted(Activity activity) {
        i5.a.g("activity", activity);
        o0 o0Var = this.this$0;
        int i7 = o0Var.f2192a + 1;
        o0Var.f2192a = i7;
        if (i7 == 1 && o0Var.f2195d) {
            o0Var.f2197f.e(q.ON_START);
            o0Var.f2195d = false;
        }
    }
}
