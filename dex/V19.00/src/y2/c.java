package y2;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.r1;
import androidx.recyclerview.widget.x0;
import com.google.android.material.carousel.CarouselLayoutManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends x0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10131a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ r1 f10132b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(r1 r1Var, Context context, int i7) {
        super(context);
        this.f10131a = i7;
        this.f10132b = r1Var;
    }

    @Override // androidx.recyclerview.widget.x0
    public final int calculateDxToMakeVisible(View view, int i7) {
        switch (this.f10131a) {
            case 0:
                CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) this.f10132b;
                if (carouselLayoutManager.f3494u == null || !carouselLayoutManager.U0()) {
                    return 0;
                }
                int K = r1.K(view);
                return (int) (carouselLayoutManager.f3489p - carouselLayoutManager.R0(K, carouselLayoutManager.O0(K)));
            default:
                return super.calculateDxToMakeVisible(view, i7);
        }
    }

    @Override // androidx.recyclerview.widget.x0
    public final int calculateDyToMakeVisible(View view, int i7) {
        switch (this.f10131a) {
            case 0:
                CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) this.f10132b;
                if (carouselLayoutManager.f3494u == null || carouselLayoutManager.U0()) {
                    return 0;
                }
                int K = r1.K(view);
                return (int) (carouselLayoutManager.f3489p - carouselLayoutManager.R0(K, carouselLayoutManager.O0(K)));
            default:
                return super.calculateDyToMakeVisible(view, i7);
        }
    }

    @Override // androidx.recyclerview.widget.x0
    public final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        switch (this.f10131a) {
            case 1:
                return 100.0f / displayMetrics.densityDpi;
            default:
                return 25.0f / displayMetrics.densityDpi;
        }
    }

    @Override // androidx.recyclerview.widget.f2
    public final PointF computeScrollVectorForPosition(int i7) {
        switch (this.f10131a) {
            case 0:
                return ((CarouselLayoutManager) this.f10132b).a(i7);
            default:
                return super.computeScrollVectorForPosition(i7);
        }
    }
}
