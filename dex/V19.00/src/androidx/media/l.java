package androidx.media;

import android.os.IBinder;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2266a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.o f2267b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.o f2268c;

    public /* synthetic */ l(androidx.fragment.app.o oVar, androidx.fragment.app.o oVar2, int i7) {
        this.f2266a = i7;
        this.f2268c = oVar;
        this.f2267b = oVar2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f2266a;
        androidx.fragment.app.o oVar = this.f2268c;
        androidx.fragment.app.o oVar2 = this.f2267b;
        switch (i7) {
            case 0:
                e eVar = (e) ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.remove(oVar2.a());
                if (eVar != null) {
                    eVar.f2247d.a().unlinkToDeath(eVar, 0);
                    break;
                }
                break;
            default:
                IBinder a7 = oVar2.a();
                e eVar2 = (e) ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.remove(a7);
                if (eVar2 != null) {
                    a7.unlinkToDeath(eVar2, 0);
                    break;
                }
                break;
        }
    }
}
