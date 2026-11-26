package g4;

import com.navi.tool.MainActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6537a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MainActivity f6538b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f6539c;

    public /* synthetic */ h(MainActivity mainActivity, boolean z6, int i7) {
        this.f6537a = i7;
        this.f6538b = mainActivity;
        this.f6539c = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f6537a;
        boolean z6 = this.f6539c;
        MainActivity mainActivity = this.f6538b;
        switch (i7) {
            case 0:
                mainActivity.lambda$recheckLicense$34(z6);
                break;
            default:
                mainActivity.lambda$initAccessGate$36(z6);
                break;
        }
    }
}
