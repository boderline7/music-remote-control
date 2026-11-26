package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class u1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5484a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantClockWeatherController f5485b;

    public /* synthetic */ u1(DesktopAssistantClockWeatherController desktopAssistantClockWeatherController, int i7) {
        this.f5484a = i7;
        this.f5485b = desktopAssistantClockWeatherController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5484a;
        DesktopAssistantClockWeatherController desktopAssistantClockWeatherController = this.f5485b;
        switch (i7) {
            case 0:
                desktopAssistantClockWeatherController.lambda$dispatchDeferredAlertRefreshIfNeeded$1();
                break;
            default:
                desktopAssistantClockWeatherController.lambda$new$0();
                break;
        }
    }
}
