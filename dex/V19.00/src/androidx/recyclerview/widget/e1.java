package androidx.recyclerview.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e1 extends x0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f1 f2438a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e1(f1 f1Var, Context context) {
        super(context);
        this.f2438a = f1Var;
    }

    @Override // androidx.recyclerview.widget.x0
    public final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 100.0f / displayMetrics.densityDpi;
    }

    @Override // androidx.recyclerview.widget.x0
    public final int calculateTimeForScrolling(int i7) {
        return Math.min(100, super.calculateTimeForScrolling(i7));
    }

    @Override // androidx.recyclerview.widget.x0, androidx.recyclerview.widget.f2
    public final void onTargetFound(View view, g2 g2Var, d2 d2Var) {
        f1 f1Var = this.f2438a;
        int[] b7 = f1Var.b(f1Var.f2445a.getLayoutManager(), view);
        int i7 = b7[0];
        int i8 = b7[1];
        int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i7), Math.abs(i8)));
        if (calculateTimeForDeceleration > 0) {
            DecelerateInterpolator decelerateInterpolator = this.mDecelerateInterpolator;
            d2Var.f2428a = i7;
            d2Var.f2429b = i8;
            d2Var.f2430c = calculateTimeForDeceleration;
            d2Var.f2432e = decelerateInterpolator;
            d2Var.f2433f = true;
        }
    }
}
