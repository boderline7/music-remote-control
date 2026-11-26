package o4;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7919a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ b f7920b;

    public /* synthetic */ a(b bVar, int i7) {
        this.f7919a = i7;
        this.f7920b = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f7919a;
        b bVar = this.f7920b;
        switch (i7) {
            case 0:
                if (bVar.f7926e) {
                    bVar.a();
                    break;
                }
                break;
            default:
                if (!bVar.f7925d && bVar.f7926e) {
                    try {
                        bVar.f7922a.unbindService(bVar.f7929h);
                    } catch (Exception unused) {
                    }
                    bVar.f7923b.postDelayed(bVar.f7930i, 5000L);
                    break;
                }
                break;
        }
    }
}
