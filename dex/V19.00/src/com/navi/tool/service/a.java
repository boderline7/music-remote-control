package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4841a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CarClimateManager f4842b;

    public /* synthetic */ a(CarClimateManager carClimateManager, int i7) {
        this.f4841a = i7;
        this.f4842b = carClimateManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4841a;
        CarClimateManager carClimateManager = this.f4842b;
        switch (i7) {
            case 0:
                carClimateManager.lambda$executeSeatVentActions$2();
                break;
            case 1:
                carClimateManager.lambda$isSeatMemoryApplyAllowed$3();
                break;
            case 2:
                carClimateManager.lambda$checkAutoFragranceTrigger$0();
                break;
            default:
                carClimateManager.lambda$executeSeatHeatActions$1();
                break;
        }
    }
}
