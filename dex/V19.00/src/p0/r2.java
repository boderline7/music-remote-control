package p0;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r2 extends p3.f {

    /* renamed from: e, reason: collision with root package name */
    public final WindowInsetsController f8181e;

    /* renamed from: f, reason: collision with root package name */
    public Window f8182f;

    public r2(WindowInsetsController windowInsetsController) {
        super(2, 0);
        this.f8181e = windowInsetsController;
    }

    @Override // p3.f
    public final void E(boolean z6) {
        Window window = this.f8182f;
        WindowInsetsController windowInsetsController = this.f8181e;
        if (z6) {
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 16);
            }
            windowInsetsController.setSystemBarsAppearance(16, 16);
            return;
        }
        if (window != null) {
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-17));
        }
        windowInsetsController.setSystemBarsAppearance(0, 16);
    }

    @Override // p3.f
    public final void F(boolean z6) {
        Window window = this.f8182f;
        WindowInsetsController windowInsetsController = this.f8181e;
        if (z6) {
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
            }
            windowInsetsController.setSystemBarsAppearance(8, 8);
            return;
        }
        if (window != null) {
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-8193));
        }
        windowInsetsController.setSystemBarsAppearance(0, 8);
    }

    @Override // p3.f
    public final void H() {
        this.f8181e.setSystemBarsBehavior(2);
    }

    @Override // p3.f
    public final void J() {
        Window window = this.f8182f;
        if (window != null && Build.VERSION.SDK_INT < 32) {
            ((InputMethodManager) window.getContext().getSystemService("input_method")).isActive();
        }
        this.f8181e.show(8);
    }

    @Override // p3.f
    public final void y(int i7) {
        this.f8181e.hide(i7);
    }
}
