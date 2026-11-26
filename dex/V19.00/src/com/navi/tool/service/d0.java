package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4856a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MobileHudService f4857b;

    public /* synthetic */ d0(MobileHudService mobileHudService, int i7) {
        this.f4856a = i7;
        this.f4857b = mobileHudService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4856a;
        MobileHudService mobileHudService = this.f4857b;
        switch (i7) {
            case 0:
                mobileHudService.syncExternalLightSource();
                break;
            case 1:
                mobileHudService.sendHudState();
                break;
            case 2:
                mobileHudService.handleTransitionWindowExpired();
                break;
            case 3:
                mobileHudService.sendPhoneNotificationConfig();
                break;
            case 4:
                mobileHudService.clearHudPhoneNotificationAndSend();
                break;
            case 5:
                mobileHudService.syncExternalLightSource();
                break;
            default:
                mobileHudService.syncExternalLightSource();
                break;
        }
    }
}
