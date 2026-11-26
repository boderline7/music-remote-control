package x;

import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p extends q {

    /* renamed from: g, reason: collision with root package name */
    public boolean f9545g;

    @Override // x.q
    public final boolean c(float f7, long j7, View view, t.g gVar) {
        Method method;
        if (view instanceof MotionLayout) {
            ((MotionLayout) view).setProgress(b(f7, j7, view, gVar));
        } else {
            if (this.f9545g) {
                return false;
            }
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f9545g = true;
                method = null;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(b(f7, j7, view, gVar)));
                } catch (IllegalAccessException | InvocationTargetException unused2) {
                }
            }
        }
        return this.f9009d;
    }
}
