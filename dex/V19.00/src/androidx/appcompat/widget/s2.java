package androidx.appcompat.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1409a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SearchView f1410b;

    public /* synthetic */ s2(SearchView searchView, int i7) {
        this.f1409a = i7;
        this.f1410b = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f1409a;
        SearchView searchView = this.f1410b;
        switch (i7) {
            case 0:
                searchView.u();
                break;
            default:
                t0.b bVar = searchView.P;
                if (bVar instanceof h3) {
                    bVar.b(null);
                    break;
                }
                break;
        }
    }
}
