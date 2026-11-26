package s3;

import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f implements q1.j {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f8698a;

    /* renamed from: b, reason: collision with root package name */
    public int f8699b;

    /* renamed from: c, reason: collision with root package name */
    public int f8700c;

    public f(TabLayout tabLayout) {
        this.f8698a = new WeakReference(tabLayout);
    }

    @Override // q1.j
    public final void a(int i7, float f7) {
        TabLayout tabLayout = (TabLayout) this.f8698a.get();
        if (tabLayout != null) {
            int i8 = this.f8700c;
            tabLayout.l(i7, f7, i8 != 2 || this.f8699b == 1, (i8 == 2 && this.f8699b == 0) ? false : true, false);
        }
    }

    @Override // q1.j
    public final void b(int i7) {
        this.f8699b = this.f8700c;
        this.f8700c = i7;
        TabLayout tabLayout = (TabLayout) this.f8698a.get();
        if (tabLayout != null) {
            tabLayout.S = this.f8700c;
        }
    }
}
