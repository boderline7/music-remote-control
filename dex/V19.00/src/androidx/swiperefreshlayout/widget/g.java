package androidx.swiperefreshlayout.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g extends Animation {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2792a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SwipeRefreshLayout f2793b;

    public /* synthetic */ g(SwipeRefreshLayout swipeRefreshLayout, int i7) {
        this.f2792a = i7;
        this.f2793b = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f7, Transformation transformation) {
        int i7 = this.f2792a;
        SwipeRefreshLayout swipeRefreshLayout = this.f2793b;
        switch (i7) {
            case 0:
                swipeRefreshLayout.setAnimationProgress(f7);
                break;
            case 1:
                swipeRefreshLayout.setAnimationProgress(1.0f - f7);
                break;
            case 2:
                int abs = !swipeRefreshLayout.J ? swipeRefreshLayout.f2751z - Math.abs(swipeRefreshLayout.f2750y) : swipeRefreshLayout.f2751z;
                swipeRefreshLayout.setTargetOffsetTopAndBottom((swipeRefreshLayout.f2748w + ((int) ((abs - r2) * f7))) - swipeRefreshLayout.f2746u.getTop());
                e eVar = swipeRefreshLayout.B;
                float f8 = 1.0f - f7;
                d dVar = eVar.f2784a;
                if (f8 != dVar.f2775p) {
                    dVar.f2775p = f8;
                }
                eVar.invalidateSelf();
                break;
            case 3:
                swipeRefreshLayout.k(f7);
                break;
            default:
                float f9 = swipeRefreshLayout.f2749x;
                swipeRefreshLayout.setAnimationProgress(((-f9) * f7) + f9);
                swipeRefreshLayout.k(f7);
                break;
        }
    }
}
