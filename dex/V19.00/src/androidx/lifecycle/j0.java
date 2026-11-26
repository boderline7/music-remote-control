package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j0 {
    public static final j0 INSTANCE = new j0();

    public static final void a(Activity activity, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        i5.a.g("activity", activity);
        i5.a.g("callback", activityLifecycleCallbacks);
        activity.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }
}
