package androidx.viewpager2.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f extends j {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2910a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ViewPager2 f2911b;

    public /* synthetic */ f(ViewPager2 viewPager2, int i7) {
        this.f2910a = i7;
        this.f2911b = viewPager2;
    }

    @Override // androidx.viewpager2.widget.j
    public final void onPageScrollStateChanged(int i7) {
        switch (this.f2910a) {
            case 0:
                if (i7 == 0) {
                    this.f2911b.d();
                    break;
                }
                break;
            default:
                super.onPageScrollStateChanged(i7);
                break;
        }
    }

    @Override // androidx.viewpager2.widget.j
    public final void onPageSelected(int i7) {
        int i8 = this.f2910a;
        ViewPager2 viewPager2 = this.f2911b;
        switch (i8) {
            case 0:
                if (viewPager2.f2871d != i7) {
                    viewPager2.f2871d = i7;
                    viewPager2.f2887t.k();
                    break;
                }
                break;
            default:
                viewPager2.clearFocus();
                if (viewPager2.hasFocus()) {
                    viewPager2.f2877j.requestFocus(2);
                    break;
                }
                break;
        }
    }
}
