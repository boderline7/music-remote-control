package androidx.viewpager2.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e extends h {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2908a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2909b;

    public e(int i7, Object obj) {
        this.f2908a = i7;
        this.f2909b = obj;
    }

    @Override // androidx.recyclerview.widget.j1
    public final void a() {
        int i7 = this.f2908a;
        Object obj = this.f2909b;
        switch (i7) {
            case 0:
                ViewPager2 viewPager2 = (ViewPager2) obj;
                viewPager2.f2872e = true;
                viewPager2.f2879l.f2906l = true;
                break;
            default:
                ((l) obj).k();
                break;
        }
    }
}
