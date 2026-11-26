package androidx.viewpager2.widget;

import android.view.View;
import q0.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k implements w {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2912a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f2913b;

    public /* synthetic */ k(l lVar, int i7) {
        this.f2912a = i7;
        this.f2913b = lVar;
    }

    @Override // q0.w
    public final boolean g(View view) {
        int i7 = this.f2912a;
        l lVar = this.f2913b;
        switch (i7) {
            case 0:
                int currentItem = ((ViewPager2) view).getCurrentItem() + 1;
                ViewPager2 viewPager2 = lVar.f2917e;
                if (viewPager2.f2885r) {
                    viewPager2.c(currentItem, true);
                    break;
                }
                break;
            default:
                int currentItem2 = ((ViewPager2) view).getCurrentItem() - 1;
                ViewPager2 viewPager22 = lVar.f2917e;
                if (viewPager22.f2885r) {
                    viewPager22.c(currentItem2, true);
                    break;
                }
                break;
        }
        return true;
    }
}
