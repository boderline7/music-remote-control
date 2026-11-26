package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r0 implements Application.ActivityLifecycleCallbacks {
    public static final q0 Companion = new q0();

    public static final void registerIn(Activity activity) {
        Companion.getClass();
        i5.a.g("activity", activity);
        activity.registerActivityLifecycleCallbacks(new r0());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        i5.a.g("activity", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        i5.a.g("activity", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        i5.a.g("activity", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostCreated(Activity activity, Bundle bundle) {
        i5.a.g("activity", activity);
        p0 p0Var = s0.Companion;
        q qVar = q.ON_CREATE;
        p0Var.getClass();
        p0.a(activity, qVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostResumed(Activity activity) {
        i5.a.g("activity", activity);
        p0 p0Var = s0.Companion;
        q qVar = q.ON_RESUME;
        p0Var.getClass();
        p0.a(activity, qVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostStarted(Activity activity) {
        i5.a.g("activity", activity);
        p0 p0Var = s0.Companion;
        q qVar = q.ON_START;
        p0Var.getClass();
        p0.a(activity, qVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreDestroyed(Activity activity) {
        i5.a.g("activity", activity);
        p0 p0Var = s0.Companion;
        q qVar = q.ON_DESTROY;
        p0Var.getClass();
        p0.a(activity, qVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPrePaused(Activity activity) {
        i5.a.g("activity", activity);
        p0 p0Var = s0.Companion;
        q qVar = q.ON_PAUSE;
        p0Var.getClass();
        p0.a(activity, qVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreStopped(Activity activity) {
        i5.a.g("activity", activity);
        p0 p0Var = s0.Companion;
        q qVar = q.ON_STOP;
        p0Var.getClass();
        p0.a(activity, qVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        i5.a.g("activity", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        i5.a.g("activity", activity);
        i5.a.g("bundle", bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        i5.a.g("activity", activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        i5.a.g("activity", activity);
    }
}
