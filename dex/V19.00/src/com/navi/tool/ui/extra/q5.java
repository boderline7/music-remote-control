package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class q5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5423a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ QrCodeLoginDialog f5424b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Exception f5425c;

    public /* synthetic */ q5(QrCodeLoginDialog qrCodeLoginDialog, Exception exc, int i7) {
        this.f5423a = i7;
        this.f5424b = qrCodeLoginDialog;
        this.f5425c = exc;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5423a;
        Exception exc = this.f5425c;
        QrCodeLoginDialog qrCodeLoginDialog = this.f5424b;
        switch (i7) {
            case 0:
                qrCodeLoginDialog.lambda$startLogin$3(exc);
                break;
            case 1:
                qrCodeLoginDialog.lambda$startPolling$9(exc);
                break;
            default:
                qrCodeLoginDialog.lambda$startPolling$11(exc);
                break;
        }
    }
}
