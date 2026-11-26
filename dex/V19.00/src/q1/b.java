package q1;

import android.view.View;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8431a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PagerTabStrip f8432b;

    public /* synthetic */ b(PagerTabStrip pagerTabStrip, int i7) {
        this.f8431a = i7;
        this.f8432b = pagerTabStrip;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f8431a;
        PagerTabStrip pagerTabStrip = this.f8432b;
        switch (i7) {
            case 0:
                pagerTabStrip.f2811a.setCurrentItem(r2.getCurrentItem() - 1);
                break;
            default:
                ViewPager viewPager = pagerTabStrip.f2811a;
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
        }
    }
}
