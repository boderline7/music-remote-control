package g4;

import com.navi.tool.MainActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6525a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MainActivity f6526b;

    public /* synthetic */ b(MainActivity mainActivity, int i7) {
        this.f6525a = i7;
        this.f6526b = mainActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f6525a;
        MainActivity mainActivity = this.f6526b;
        switch (i7) {
            case 0:
                mainActivity.lambda$showNetworkToastOnce$31();
                break;
            case 1:
                mainActivity.updateMainShortcutButtonsVisibility();
                break;
            case 2:
                mainActivity.lambda$onCreate$3();
                break;
            case 3:
                mainActivity.lambda$initAccessGate$37();
                break;
            case 4:
                mainActivity.lambda$recheckLicense$33();
                break;
            case 5:
                mainActivity.lambda$onCreate$1();
                break;
            case 6:
                mainActivity.lambda$onCreate$2();
                break;
            case 7:
                mainActivity.lambda$onCreate$14();
                break;
            case 8:
                mainActivity.lambda$scheduleLicenseUiReload$32();
                break;
            case 9:
                mainActivity.lambda$onCreate$5();
                break;
            case 10:
                mainActivity.lambda$onCreate$6();
                break;
            default:
                mainActivity.lambda$recheckLicense$35();
                break;
        }
    }
}
