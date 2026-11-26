package s3;

import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends androidx.viewpager2.widget.j {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f8702a;

    /* renamed from: c, reason: collision with root package name */
    public int f8704c = 0;

    /* renamed from: b, reason: collision with root package name */
    public int f8703b = 0;

    public i(TabLayout tabLayout) {
        this.f8702a = new WeakReference(tabLayout);
    }

    @Override // androidx.viewpager2.widget.j
    public final void onPageScrollStateChanged(int i7) {
        this.f8703b = this.f8704c;
        this.f8704c = i7;
        TabLayout tabLayout = (TabLayout) this.f8702a.get();
        if (tabLayout != null) {
            tabLayout.S = this.f8704c;
        }
    }

    @Override // androidx.viewpager2.widget.j
    public final void onPageScrolled(int i7, float f7, int i8) {
        TabLayout tabLayout = (TabLayout) this.f8702a.get();
        if (tabLayout != null) {
            int i9 = this.f8704c;
            tabLayout.l(i7, f7, i9 != 2 || this.f8703b == 1, (i9 == 2 && this.f8703b == 0) ? false : true, false);
        }
    }

    @Override // androidx.viewpager2.widget.j
    public final void onPageSelected(int i7) {
        TabLayout tabLayout = (TabLayout) this.f8702a.get();
        if (tabLayout == null || tabLayout.getSelectedTabPosition() == i7 || i7 >= tabLayout.getTabCount()) {
            return;
        }
        int i8 = this.f8704c;
        tabLayout.k(tabLayout.h(i7), i8 == 0 || (i8 == 2 && this.f8703b == 0));
    }
}
