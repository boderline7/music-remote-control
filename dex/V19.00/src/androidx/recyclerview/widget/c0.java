package androidx.recyclerview.widget;

import android.animation.ValueAnimator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c0 implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2391a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2392b;

    public /* synthetic */ c0(int i7, Object obj) {
        this.f2391a = i7;
        this.f2392b = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i7 = this.f2391a;
        Object obj = this.f2392b;
        switch (i7) {
            case 0:
                int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
                d0 d0Var = (d0) obj;
                d0Var.f2401c.setAlpha(floatValue);
                d0Var.f2402d.setAlpha(floatValue);
                d0Var.f2417s.invalidate();
                break;
            default:
                ((j0) obj).f2513m = valueAnimator.getAnimatedFraction();
                break;
        }
    }
}
