package x2;

import android.animation.ValueAnimator;
import android.graphics.Color;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.f;
import com.google.android.material.textfield.i;
import com.navi.tool.view.CoverGradientBackgroundView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9583a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f9584b;

    public /* synthetic */ b(int i7, Object obj) {
        this.f9583a = i7;
        this.f9584b = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i7 = this.f9583a;
        Object obj = this.f9584b;
        switch (i7) {
            case 0:
                d dVar = (d) obj;
                dVar.getClass();
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                dVar.f9595j.setAlpha((int) (255.0f * floatValue));
                dVar.f9610y = floatValue;
                break;
            case 1:
                ((ClippableRoundedCornerLayout) obj).a(r3.getLeft(), r3.getTop(), r3.getRight(), r3.getBottom(), ((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 2:
                int i8 = com.google.android.material.navigation.a.f4008a;
                ((DrawerLayout) obj).setScrimColor(i0.a.e(-1728053248, r2.a.c(com.google.android.material.navigation.a.f4008a, valueAnimator.getAnimatedFraction(), 0)));
                break;
            case 3:
                ((f) obj).a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 4:
                ((g.d) obj).b(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 5:
                i iVar = (i) obj;
                iVar.getClass();
                iVar.f4383d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                CoverGradientBackgroundView coverGradientBackgroundView = (CoverGradientBackgroundView) obj;
                int i9 = CoverGradientBackgroundView.f5906l;
                coverGradientBackgroundView.getClass();
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (intValue != coverGradientBackgroundView.f5916j) {
                    coverGradientBackgroundView.f5916j = intValue;
                    float f7 = intValue / 6.0f;
                    int[] iArr = new int[3];
                    for (int i10 = 0; i10 < 3; i10++) {
                        int i11 = coverGradientBackgroundView.f5911e[i10];
                        int i12 = coverGradientBackgroundView.f5912f[i10];
                        iArr[i10] = Color.rgb((int) (((Color.red(i12) - Color.red(i11)) * f7) + Color.red(i11)), (int) (((Color.green(i12) - Color.green(i11)) * f7) + Color.green(i11)), (int) (((Color.blue(i12) - Color.blue(i11)) * f7) + Color.blue(i11)));
                    }
                    coverGradientBackgroundView.f5910d = iArr;
                    coverGradientBackgroundView.c();
                    coverGradientBackgroundView.invalidate();
                    break;
                }
                break;
        }
    }
}
