package androidx.appcompat.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class b {
    public static final int DISPLAY_HOME_AS_UP = 4;
    public static final int DISPLAY_SHOW_CUSTOM = 16;
    public static final int DISPLAY_SHOW_HOME = 2;
    public static final int DISPLAY_SHOW_TITLE = 8;
    public static final int DISPLAY_USE_LOGO = 1;

    @Deprecated
    public static final int NAVIGATION_MODE_LIST = 1;

    @Deprecated
    public static final int NAVIGATION_MODE_STANDARD = 0;

    @Deprecated
    public static final int NAVIGATION_MODE_TABS = 2;

    public boolean a() {
        return false;
    }

    public abstract boolean b();

    public abstract void c(boolean z6);

    public abstract int d();

    public abstract Context e();

    public abstract void f();

    public boolean g() {
        return false;
    }

    public abstract void h();

    public void i() {
    }

    public abstract boolean j(int i7, KeyEvent keyEvent);

    public boolean k(KeyEvent keyEvent) {
        return false;
    }

    public boolean l() {
        return false;
    }

    public abstract void m(boolean z6);

    public abstract void n(boolean z6);

    public abstract void o();

    public abstract void p();

    public abstract void q(int i7);

    public abstract void r(Drawable drawable);

    public abstract void s(boolean z6);

    public abstract void t(String str);

    public abstract void u(CharSequence charSequence);

    public j.c v(a0 a0Var) {
        return null;
    }
}
