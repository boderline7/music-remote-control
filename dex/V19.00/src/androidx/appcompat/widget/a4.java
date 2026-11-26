package androidx.appcompat.widget;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class a4 {

    /* renamed from: a, reason: collision with root package name */
    public static final Method f1188a;

    /* renamed from: b, reason: collision with root package name */
    public static final boolean f1189b;

    static {
        f1189b = Build.VERSION.SDK_INT >= 27;
        try {
            Method declaredMethod = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            f1188a = declaredMethod;
            if (declaredMethod.isAccessible()) {
                return;
            }
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException unused) {
        }
    }

    public static boolean a(View view) {
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        return p0.m0.d(view) == 1;
    }
}
