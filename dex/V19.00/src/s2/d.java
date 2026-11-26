package s2;

import android.animation.ValueAnimator;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Iterator;
import p0.d1;
import p0.l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8670a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f8671b;

    public /* synthetic */ d(int i7, Object obj) {
        this.f8670a = i7;
        this.f8671b = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i7 = this.f8670a;
        Object obj = this.f8671b;
        switch (i7) {
            case 0:
                ((CollapsingToolbarLayout) obj).setScrimAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                break;
            case 1:
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                p3.j jVar = ((BottomSheetBehavior) obj).f3394i;
                if (jVar != null) {
                    jVar.p(floatValue);
                    break;
                }
                break;
            case 2:
                float floatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                com.google.android.material.slider.c cVar = (com.google.android.material.slider.c) obj;
                Iterator it = cVar.f4176k.iterator();
                while (it.hasNext()) {
                    u3.a aVar = (u3.a) it.next();
                    aVar.M = 1.2f;
                    aVar.K = floatValue2;
                    aVar.L = floatValue2;
                    aVar.N = r2.a.b(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 0.19f, 1.0f, floatValue2);
                    aVar.invalidateSelf();
                }
                int i8 = d1.OVER_SCROLL_ALWAYS;
                l0.k(cVar);
                break;
            case 3:
                ((TabLayout) obj).scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
                break;
            default:
                ((TextInputLayout) obj).f4310u0.p(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
        }
    }
}
