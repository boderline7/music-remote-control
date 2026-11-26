package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class m0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5329a;

    public /* synthetic */ m0(int i7) {
        this.f5329a = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f5329a) {
            case 0:
                DesktopAssistantActivity.lambda$onCreate$28();
                break;
            case 1:
                DesktopAssistantCarControlHelper.lambda$refreshWirelessChargingState$65();
                break;
            default:
                DesktopAssistantCarControlHelper.lambda$updateSeatComfort$45();
                break;
        }
    }
}
