package x;

import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends l {

    /* renamed from: f, reason: collision with root package name */
    public boolean f9533f;

    @Override // x.l
    public final void c(View view, float f7) {
        Method method;
        if (view instanceof MotionLayout) {
            ((MotionLayout) view).setProgress(a(f7));
            return;
        }
        if (this.f9533f) {
            return;
        }
        try {
            method = view.getClass().getMethod("setProgress", Float.TYPE);
        } catch (NoSuchMethodException unused) {
            this.f9533f = true;
            method = null;
        }
        if (method != null) {
            try {
                method.invoke(view, Float.valueOf(a(f7)));
            } catch (IllegalAccessException | InvocationTargetException unused2) {
            }
        }
    }
}
