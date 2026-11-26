package q1;

import android.database.DataSetObserver;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends DataSetObserver implements j, i {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PagerTitleStrip f8433a;

    public c(PagerTitleStrip pagerTitleStrip) {
        this.f8433a = pagerTitleStrip;
    }

    @Override // q1.j
    public final void a(int i7, float f7) {
        if (f7 > 0.5f) {
            i7++;
        }
        this.f8433a.c(i7, f7, false);
    }

    @Override // q1.j
    public final void b(int i7) {
    }

    @Override // q1.i
    public final void c(ViewPager viewPager) {
        this.f8433a.a();
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        PagerTitleStrip pagerTitleStrip = this.f8433a;
        int currentItem = pagerTitleStrip.f2811a.getCurrentItem();
        pagerTitleStrip.f2811a.getAdapter();
        pagerTitleStrip.b(currentItem);
        float f7 = pagerTitleStrip.f2816f;
        if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
            f7 = 0.0f;
        }
        pagerTitleStrip.c(pagerTitleStrip.f2811a.getCurrentItem(), f7, true);
    }
}
