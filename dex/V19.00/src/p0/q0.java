package p0;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q0 implements View.OnApplyWindowInsetsListener {

    /* renamed from: a, reason: collision with root package name */
    public l2 f8169a = null;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f8170b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ b0 f8171c;

    public q0(View view, b0 b0Var) {
        this.f8170b = view;
        this.f8171c = b0Var;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        l2 h7 = l2.h(view, windowInsets);
        int i7 = Build.VERSION.SDK_INT;
        b0 b0Var = this.f8171c;
        if (i7 < 30) {
            r0.a(windowInsets, this.f8170b);
            if (h7.equals(this.f8169a)) {
                return b0Var.e(view, h7).g();
            }
        }
        this.f8169a = h7;
        l2 e7 = b0Var.e(view, h7);
        if (i7 >= 30) {
            return e7.g();
        }
        int i8 = d1.OVER_SCROLL_ALWAYS;
        p0.c(view);
        return e7.g();
    }
}
