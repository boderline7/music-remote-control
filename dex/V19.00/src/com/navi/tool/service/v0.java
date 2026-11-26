package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class v0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4935a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ThemeMonitorService f4936b;

    public /* synthetic */ v0(ThemeMonitorService themeMonitorService, int i7) {
        this.f4935a = i7;
        this.f4936b = themeMonitorService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4935a;
        ThemeMonitorService themeMonitorService = this.f4936b;
        switch (i7) {
            case 0:
                themeMonitorService.lambda$startStrWakeLogMonitoring$3();
                break;
            case 1:
                themeMonitorService.lambda$initializeAutoProtocolManager$1();
                break;
            case 2:
                themeMonitorService.initializeDashboardIfNeeded();
                break;
            case 3:
                themeMonitorService.initializeDashboardIfNeeded();
                break;
            case 4:
                themeMonitorService.lambda$triggerStrWakeLogAutoStart$4();
                break;
            default:
                themeMonitorService.handleStrWakeHeartbeat();
                break;
        }
    }
}
