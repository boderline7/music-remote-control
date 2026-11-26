package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class s implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4918a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogMonitorService f4919b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f4920c;

    public /* synthetic */ s(LogMonitorService logMonitorService, String str, int i7) {
        this.f4918a = i7;
        this.f4919b = logMonitorService;
        this.f4920c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4918a;
        String str = this.f4920c;
        LogMonitorService logMonitorService = this.f4919b;
        switch (i7) {
            case 0:
                logMonitorService.lambda$startBaiduNaviDelayed$43(str);
                break;
            default:
                logMonitorService.lambda$startSteeringLogMonitoring$19(str);
                break;
        }
    }
}
