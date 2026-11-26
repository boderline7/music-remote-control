package androidx.swiperefreshlayout.widget;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.TextView;
import y2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2755a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f2756b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f2757c;

    public /* synthetic */ b(int i7, Object obj, Object obj2) {
        this.f2755a = i7;
        this.f2757c = obj;
        this.f2756b = obj2;
    }

    public b(l lVar, View[] viewArr) {
        this.f2755a = 1;
        this.f2756b = lVar;
        this.f2757c = viewArr;
    }

    public static b a(View... viewArr) {
        return new b(new l(3), viewArr);
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i7 = this.f2755a;
        Object obj = this.f2757c;
        Object obj2 = this.f2756b;
        switch (i7) {
            case 0:
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                e eVar = (e) obj;
                d dVar = (d) obj2;
                eVar.getClass();
                e.d(floatValue, dVar);
                eVar.a(floatValue, dVar, false);
                eVar.invalidateSelf();
                break;
            case 1:
                for (View view : (View[]) obj) {
                    switch (((l) ((com.google.android.material.internal.j) obj2)).f10167a) {
                        case 1:
                            Float f7 = (Float) valueAnimator.getAnimatedValue();
                            view.setScaleX(f7.floatValue());
                            view.setScaleY(f7.floatValue());
                            break;
                        case 2:
                            view.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        case 3:
                            view.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                        default:
                            view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                            break;
                    }
                }
                break;
            case 2:
                float floatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                TextView textView = (TextView) obj2;
                textView.setScaleX(floatValue2);
                textView.setScaleY(floatValue2);
                break;
            default:
                ((View) obj2).invalidate();
                break;
        }
    }
}
