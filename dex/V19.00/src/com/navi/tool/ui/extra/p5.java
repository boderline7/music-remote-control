package com.navi.tool.ui.extra;

import android.graphics.Bitmap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class p5 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5405a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ QrCodeLoginDialog f5406b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Bitmap f5407c;

    public /* synthetic */ p5(QrCodeLoginDialog qrCodeLoginDialog, Bitmap bitmap, int i7) {
        this.f5405a = i7;
        this.f5406b = qrCodeLoginDialog;
        this.f5407c = bitmap;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5405a;
        Bitmap bitmap = this.f5407c;
        QrCodeLoginDialog qrCodeLoginDialog = this.f5406b;
        switch (i7) {
            case 0:
                qrCodeLoginDialog.lambda$startLogin$2(bitmap);
                break;
            default:
                qrCodeLoginDialog.lambda$startPolling$8(bitmap);
                break;
        }
    }
}
