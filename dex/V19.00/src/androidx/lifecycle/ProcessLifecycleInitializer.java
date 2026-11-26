package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class ProcessLifecycleInitializer implements k1.b {
    @Override // k1.b
    public final List a() {
        return c5.r.INSTANCE;
    }

    @Override // k1.b
    public final Object b(Context context) {
        i5.a.g("context", context);
        k1.a c7 = k1.a.c(context);
        i5.a.f("getInstance(context)", c7);
        if (!c7.f7207b.contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml".toString());
        }
        if (!u.f2205a.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            i5.a.e("null cannot be cast to non-null type android.app.Application", applicationContext);
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new t());
        }
        o0.Companion.getClass();
        o0 o0Var = o0.f2191i;
        o0Var.getClass();
        o0Var.f2196e = new Handler();
        o0Var.f2197f.e(q.ON_CREATE);
        Context applicationContext2 = context.getApplicationContext();
        i5.a.e("null cannot be cast to non-null type android.app.Application", applicationContext2);
        ((Application) applicationContext2).registerActivityLifecycleCallbacks(new m0(o0Var));
        return o0Var;
    }
}
