package androidx.appcompat.app;

import android.app.Activity;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class c0 {
    public static OnBackInvokedDispatcher a(Activity activity) {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        onBackInvokedDispatcher = activity.getOnBackInvokedDispatcher();
        return onBackInvokedDispatcher;
    }

    public static OnBackInvokedCallback b(Object obj, j0 j0Var) {
        Objects.requireNonNull(j0Var);
        androidx.activity.z zVar = new androidx.activity.z(1, j0Var);
        androidx.activity.p.f(obj).registerOnBackInvokedCallback(1000000, zVar);
        return zVar;
    }

    public static void c(Object obj, Object obj2) {
        androidx.activity.p.f(obj).unregisterOnBackInvokedCallback(androidx.activity.p.b(obj2));
    }
}
