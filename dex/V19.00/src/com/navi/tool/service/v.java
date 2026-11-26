package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class v implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4933a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogMonitorService f4934b;

    public /* synthetic */ v(LogMonitorService logMonitorService, int i7) {
        this.f4933a = i7;
        this.f4934b = logMonitorService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4933a;
        LogMonitorService logMonitorService = this.f4934b;
        switch (i7) {
            case 0:
                logMonitorService.lambda$onCreate$2();
                break;
            case 1:
                logMonitorService.lambda$handleSteeringKeyAction4$24();
                break;
            case 2:
                logMonitorService.lambda$handleSteeringKeyAction4$25();
                break;
            case 3:
                logMonitorService.lambda$handleSteeringKeyAction4$26();
                break;
            case 4:
                logMonitorService.lambda$handleSteeringKeyAction4$27();
                break;
            case 5:
                logMonitorService.lambda$handleSteeringKeyAction4$28();
                break;
            case 6:
                logMonitorService.lambda$handleSteeringKeyAction4$29();
                break;
            case 7:
                logMonitorService.lambda$handleSteeringKeyAction4$30();
                break;
            case 8:
                logMonitorService.lambda$handleSteeringKeyAction4$31();
                break;
            case 9:
                logMonitorService.lambda$startSteeringLogMonitoring$21();
                break;
            case 10:
                logMonitorService.lambda$new$0();
                break;
            case 11:
                logMonitorService.lambda$startLogcatThreads$11();
                break;
            default:
                logMonitorService.lambda$startLogcatThreads$14();
                break;
        }
    }
}
