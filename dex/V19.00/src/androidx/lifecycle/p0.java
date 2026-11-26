package androidx.lifecycle;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p0 {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, q qVar) {
        i5.a.g("activity", activity);
        i5.a.g("event", qVar);
        if (activity instanceof x) {
            s lifecycle = ((x) activity).getLifecycle();
            if (lifecycle instanceof a0) {
                ((a0) lifecycle).e(qVar);
            }
        }
    }

    public static void b(Activity activity) {
        i5.a.g("activity", activity);
        if (Build.VERSION.SDK_INT >= 29) {
            r0.Companion.getClass();
            activity.registerActivityLifecycleCallbacks(new r0());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new s0(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }
}
