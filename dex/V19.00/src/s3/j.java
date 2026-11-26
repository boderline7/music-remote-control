package s3;

import androidx.viewpager2.widget.ViewPager2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j implements d {

    /* renamed from: a, reason: collision with root package name */
    public final ViewPager2 f8705a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f8706b;

    public j(ViewPager2 viewPager2, boolean z6) {
        this.f8705a = viewPager2;
        this.f8706b = z6;
    }

    @Override // s3.c
    public final void a() {
    }

    @Override // s3.c
    public final void b() {
    }

    @Override // s3.c
    public final void c(com.google.android.material.tabs.b bVar) {
        this.f8705a.setCurrentItem(bVar.f4252d, this.f8706b);
    }
}
