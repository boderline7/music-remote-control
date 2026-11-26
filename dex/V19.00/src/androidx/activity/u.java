package androidx.activity;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import p0.p1;
import p0.p2;
import p0.r2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class u implements v {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.activity.v
    public void a(m0 m0Var, m0 m0Var2, Window window, View view, boolean z6, boolean z7) {
        p2 p2Var;
        WindowInsetsController insetsController;
        i5.a.g("statusBarStyle", m0Var);
        i5.a.g("navigationBarStyle", m0Var2);
        i5.a.g("window", window);
        i5.a.g("view", view);
        p1.a(window, false);
        window.setStatusBarColor(m0Var.f638c == 0 ? 0 : z6 ? m0Var.f637b : m0Var.f636a);
        int i7 = m0Var2.f638c;
        window.setNavigationBarColor(i7 == 0 ? 0 : z7 ? m0Var2.f637b : m0Var2.f636a);
        window.setStatusBarContrastEnforced(false);
        window.setNavigationBarContrastEnforced(i7 == 0);
        if (Build.VERSION.SDK_INT >= 30) {
            insetsController = window.getInsetsController();
            r2 r2Var = new r2(insetsController);
            r2Var.f8182f = window;
            p2Var = r2Var;
        } else {
            p2Var = new p2(window, view);
        }
        p2Var.F(!z6);
        p2Var.E(true ^ z7);
    }
}
