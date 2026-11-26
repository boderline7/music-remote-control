package p0;

import android.view.View;
import android.view.WindowInsets;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class s0 {
    public static l2 a(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        l2 h7 = l2.h(null, rootWindowInsets);
        j2 j2Var = h7.f8161a;
        j2Var.q(h7);
        j2Var.d(view.getRootView());
        return h7;
    }

    public static int b(View view) {
        return view.getScrollIndicators();
    }

    public static void c(View view, int i7) {
        view.setScrollIndicators(i7);
    }

    public static void d(View view, int i7, int i8) {
        view.setScrollIndicators(i7, i8);
    }
}
