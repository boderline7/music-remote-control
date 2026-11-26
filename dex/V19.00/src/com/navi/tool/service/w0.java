package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class w0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4939a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ WeatherAlertOverlayService f4940b;

    public /* synthetic */ w0(WeatherAlertOverlayService weatherAlertOverlayService, int i7) {
        this.f4939a = i7;
        this.f4940b = weatherAlertOverlayService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4939a;
        WeatherAlertOverlayService weatherAlertOverlayService = this.f4940b;
        switch (i7) {
            case 0:
                weatherAlertOverlayService.dismissOverlayAndStop();
                break;
            default:
                weatherAlertOverlayService.lambda$showAutoVoiceServiceNotConnectedToast$2();
                break;
        }
    }
}
