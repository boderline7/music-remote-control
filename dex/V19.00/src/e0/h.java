package e0;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a, reason: collision with root package name */
    public Object f6296a;

    /* renamed from: b, reason: collision with root package name */
    public Activity f6297b;

    /* renamed from: c, reason: collision with root package name */
    public final int f6298c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f6299d = false;

    /* renamed from: e, reason: collision with root package name */
    public boolean f6300e = false;

    /* renamed from: f, reason: collision with root package name */
    public boolean f6301f = false;

    public h(Activity activity) {
        this.f6297b = activity;
        this.f6298c = activity.hashCode();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (this.f6297b == activity) {
            this.f6297b = null;
            this.f6300e = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (!this.f6300e || this.f6301f || this.f6299d) {
            return;
        }
        Object obj = this.f6296a;
        try {
            Object obj2 = i.f6304c.get(activity);
            if (obj2 == obj && activity.hashCode() == this.f6298c) {
                i.f6308g.postAtFrontOfQueue(new android.support.v4.media.i(i.f6303b.get(activity), obj2, 5));
                this.f6301f = true;
                this.f6296a = null;
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        if (this.f6297b == activity) {
            this.f6299d = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
