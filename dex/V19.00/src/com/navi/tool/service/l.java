package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class l implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4887a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LionMediaBridgeService f4888b;

    public /* synthetic */ l(LionMediaBridgeService lionMediaBridgeService, int i7) {
        this.f4887a = i7;
        this.f4888b = lionMediaBridgeService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4887a;
        LionMediaBridgeService lionMediaBridgeService = this.f4888b;
        switch (i7) {
            case 0:
                lionMediaBridgeService.connectToLionMedia();
                break;
            case 1:
                lionMediaBridgeService.lambda$activateOrResume$2();
                break;
            case 2:
                lionMediaBridgeService.lambda$activateOrResume$3();
                break;
            default:
                lionMediaBridgeService.lambda$scheduleReconnect$1();
                break;
        }
    }
}
