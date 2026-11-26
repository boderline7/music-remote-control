package p0;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s2 {
    public static final int BEHAVIOR_SHOW_BARS_BY_SWIPE = 1;
    public static final int BEHAVIOR_SHOW_BARS_BY_TOUCH = 0;
    public static final int BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE = 2;

    /* renamed from: a, reason: collision with root package name */
    public final p3.f f8190a;

    public s2(Window window, View view) {
        WindowInsetsController insetsController;
        if (Build.VERSION.SDK_INT < 30) {
            this.f8190a = new p2(window, view);
            return;
        }
        insetsController = window.getInsetsController();
        r2 r2Var = new r2(insetsController);
        r2Var.f8182f = window;
        this.f8190a = r2Var;
    }

    public s2(WindowInsetsController windowInsetsController) {
        this.f8190a = new r2(windowInsetsController);
    }
}
