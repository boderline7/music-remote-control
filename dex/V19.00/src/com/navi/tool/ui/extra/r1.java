package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class r1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5429a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantCarControlHelper f5430b;

    public /* synthetic */ r1(DesktopAssistantCarControlHelper desktopAssistantCarControlHelper, int i7) {
        this.f5429a = i7;
        this.f5430b = desktopAssistantCarControlHelper;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5429a;
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.f5430b;
        switch (i7) {
            case 0:
                desktopAssistantCarControlHelper.lambda$switchFragranceType$76();
                break;
            case 1:
                desktopAssistantCarControlHelper.lambda$handleWirelessChargingClick$63();
                break;
            case 2:
                desktopAssistantCarControlHelper.lambda$showWirelessChargingAnimation$66();
                break;
            case 3:
                desktopAssistantCarControlHelper.lambda$handleTrunkClick$64();
                break;
            default:
                desktopAssistantCarControlHelper.refreshWirelessChargingState();
                break;
        }
    }
}
