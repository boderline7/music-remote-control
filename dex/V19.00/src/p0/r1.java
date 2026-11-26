package p0;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import java.util.Collections;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r1 implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ y1 f8176a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l2 f8177b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ l2 f8178c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f8179d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ View f8180e;

    public r1(y1 y1Var, l2 l2Var, l2 l2Var2, int i7, View view) {
        this.f8176a = y1Var;
        this.f8177b = l2Var;
        this.f8178c = l2Var2;
        this.f8179d = i7;
        this.f8180e = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        y1 y1Var = this.f8176a;
        y1Var.f8207a.d(animatedFraction);
        float b7 = y1Var.f8207a.b();
        int i7 = Build.VERSION.SDK_INT;
        l2 l2Var = this.f8177b;
        d2 c2Var = i7 >= 30 ? new c2(l2Var) : i7 >= 29 ? new b2(l2Var) : new a2(l2Var);
        for (int i8 = 1; i8 <= 256; i8 <<= 1) {
            if ((this.f8179d & i8) == 0) {
                c2Var.c(i8, l2Var.f8161a.f(i8));
            } else {
                i0.c f7 = l2Var.f8161a.f(i8);
                i0.c f8 = this.f8178c.f8161a.f(i8);
                float f9 = 1.0f - b7;
                c2Var.c(i8, l2.e(f7, (int) (((f7.f6773a - f8.f6773a) * f9) + 0.5d), (int) (((f7.f6774b - f8.f6774b) * f9) + 0.5d), (int) (((f7.f6775c - f8.f6775c) * f9) + 0.5d), (int) (((f7.f6776d - f8.f6776d) * f9) + 0.5d)));
            }
        }
        u1.g(this.f8180e, c2Var.b(), Collections.singletonList(y1Var));
    }
}
