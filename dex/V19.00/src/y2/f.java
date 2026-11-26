package y2;

import androidx.fragment.app.w0;
import com.google.android.material.carousel.CarouselLayoutManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends w0 {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f10139b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ CarouselLayoutManager f10140c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(int i7, CarouselLayoutManager carouselLayoutManager, int i8) {
        super(i7);
        this.f10139b = i8;
        this.f10140c = carouselLayoutManager;
    }

    public final int a() {
        int i7 = this.f10139b;
        CarouselLayoutManager carouselLayoutManager = this.f10140c;
        switch (i7) {
            case 0:
                return carouselLayoutManager.f2635o;
            default:
                return carouselLayoutManager.f2635o - carouselLayoutManager.G();
        }
    }

    public final int b() {
        switch (this.f10139b) {
            case 0:
                return this.f10140c.H();
            default:
                return 0;
        }
    }

    public final int c() {
        int i7 = this.f10139b;
        CarouselLayoutManager carouselLayoutManager = this.f10140c;
        switch (i7) {
            case 0:
                return carouselLayoutManager.f2634n - carouselLayoutManager.I();
            default:
                return carouselLayoutManager.f2634n;
        }
    }
}
