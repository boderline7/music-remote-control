package s3;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements q1.i {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TabLayout f8694a;

    public b(TabLayout tabLayout) {
        this.f8694a = tabLayout;
    }

    @Override // q1.i
    public final void c(ViewPager viewPager) {
        TabLayout tabLayout = this.f8694a;
        if (tabLayout.O == viewPager) {
            tabLayout.j();
        }
    }
}
