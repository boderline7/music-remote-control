package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class r5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5441a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ QrCodeLoginDialog f5442b;

    public /* synthetic */ r5(QrCodeLoginDialog qrCodeLoginDialog, int i7) {
        this.f5441a = i7;
        this.f5442b = qrCodeLoginDialog;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5441a;
        QrCodeLoginDialog qrCodeLoginDialog = this.f5442b;
        switch (i7) {
            case 0:
                qrCodeLoginDialog.lambda$startPolling$5();
                break;
            case 1:
                qrCodeLoginDialog.lambda$startPolling$6();
                break;
            case 2:
                qrCodeLoginDialog.lambda$startPolling$7();
                break;
            case 3:
                qrCodeLoginDialog.lambda$startLogin$4();
                break;
            default:
                qrCodeLoginDialog.lambda$startPolling$12();
                break;
        }
    }
}
