package androidx.viewpager2.adapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f2855a;

    public b(g gVar) {
        this.f2855a = gVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        g gVar = this.f2855a;
        gVar.mIsInGracePeriod = false;
        gVar.gcFragments();
    }
}
