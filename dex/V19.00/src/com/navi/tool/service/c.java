package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4848a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CarLanDashboardService f4849b;

    public /* synthetic */ c(CarLanDashboardService carLanDashboardService, int i7) {
        this.f4848a = i7;
        this.f4849b = carLanDashboardService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4848a;
        CarLanDashboardService carLanDashboardService = this.f4849b;
        switch (i7) {
            case 0:
                carLanDashboardService.connectToCarLan();
                break;
            default:
                carLanDashboardService.lambda$scheduleReconnect$1();
                break;
        }
    }
}
