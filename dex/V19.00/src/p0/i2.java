package p0;

import android.graphics.Insets;
import android.view.View;
import android.view.WindowInsets;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i2 extends h2 {

    /* renamed from: q, reason: collision with root package name */
    public static final l2 f8148q;

    static {
        WindowInsets windowInsets;
        windowInsets = WindowInsets.CONSUMED;
        f8148q = l2.h(null, windowInsets);
    }

    public i2(l2 l2Var, WindowInsets windowInsets) {
        super(l2Var, windowInsets);
    }

    @Override // p0.e2, p0.j2
    public final void d(View view) {
    }

    @Override // p0.e2, p0.j2
    public i0.c f(int i7) {
        Insets insets;
        insets = this.f8127c.getInsets(k2.a(i7));
        return i0.c.c(insets);
    }

    @Override // p0.e2, p0.j2
    public boolean o(int i7) {
        boolean isVisible;
        isVisible = this.f8127c.isVisible(k2.a(i7));
        return isVisible;
    }
}
