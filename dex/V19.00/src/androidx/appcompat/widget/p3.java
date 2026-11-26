package androidx.appcompat.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class p3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1377a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1378b;

    public /* synthetic */ p3(Toolbar toolbar, int i7) {
        this.f1377a = i7;
        this.f1378b = toolbar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f1377a;
        Toolbar toolbar = this.f1378b;
        switch (i7) {
            case 0:
                toolbar.o();
                break;
            default:
                s3 s3Var = toolbar.M;
                k.q qVar = s3Var == null ? null : s3Var.f1412b;
                if (qVar != null) {
                    qVar.collapseActionView();
                    break;
                }
                break;
        }
    }
}
