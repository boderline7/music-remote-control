package l;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executors;
import l6.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends f {

    /* renamed from: d, reason: collision with root package name */
    public final Object f7267d = new Object();

    /* renamed from: e, reason: collision with root package name */
    public volatile Handler f7268e;

    public e() {
        Executors.newFixedThreadPool(4, new c());
    }

    public static Handler G0(Looper looper) {
        if (Build.VERSION.SDK_INT >= 28) {
            return d.a(looper);
        }
        try {
            return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException unused) {
            return new Handler(looper);
        } catch (InvocationTargetException unused2) {
            return new Handler(looper);
        }
    }

    public final boolean H0() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public final void I0(Runnable runnable) {
        if (this.f7268e == null) {
            synchronized (this.f7267d) {
                try {
                    if (this.f7268e == null) {
                        this.f7268e = G0(Looper.getMainLooper());
                    }
                } finally {
                }
            }
        }
        this.f7268e.post(runnable);
    }
}
