package s3;

import androidx.recyclerview.widget.b2;
import androidx.recyclerview.widget.h1;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public final TabLayout f8707a;

    /* renamed from: b, reason: collision with root package name */
    public final ViewPager2 f8708b;

    /* renamed from: c, reason: collision with root package name */
    public final h f8709c;

    /* renamed from: d, reason: collision with root package name */
    public h1 f8710d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f8711e;

    public k(TabLayout tabLayout, ViewPager2 viewPager2, h hVar) {
        this.f8707a = tabLayout;
        this.f8708b = viewPager2;
        this.f8709c = hVar;
    }

    public final void a() {
        if (this.f8711e) {
            throw new IllegalStateException("TabLayoutMediator is already attached");
        }
        ViewPager2 viewPager2 = this.f8708b;
        h1 adapter = viewPager2.getAdapter();
        this.f8710d = adapter;
        if (adapter == null) {
            throw new IllegalStateException("TabLayoutMediator attached before ViewPager2 has an adapter");
        }
        this.f8711e = true;
        TabLayout tabLayout = this.f8707a;
        ((List) viewPager2.f2870c.f2859b).add(new i(tabLayout));
        j jVar = new j(viewPager2, false);
        ArrayList arrayList = tabLayout.L;
        if (!arrayList.contains(jVar)) {
            arrayList.add(jVar);
        }
        this.f8710d.registerAdapterDataObserver(new b2(2, this));
        b();
        tabLayout.setScrollPosition(viewPager2.getCurrentItem(), CropImageView.DEFAULT_ASPECT_RATIO, true);
    }

    public final void b() {
        TabLayout tabLayout = this.f8707a;
        tabLayout.j();
        h1 h1Var = this.f8710d;
        if (h1Var != null) {
            int itemCount = h1Var.getItemCount();
            for (int i7 = 0; i7 < itemCount; i7++) {
                com.google.android.material.tabs.b i8 = tabLayout.i();
                this.f8709c.b(i8, i7);
                tabLayout.a(i8, false);
            }
            if (itemCount > 0) {
                int min = Math.min(this.f8708b.getCurrentItem(), tabLayout.getTabCount() - 1);
                if (min != tabLayout.getSelectedTabPosition()) {
                    tabLayout.k(tabLayout.h(min), true);
                }
            }
        }
    }
}
