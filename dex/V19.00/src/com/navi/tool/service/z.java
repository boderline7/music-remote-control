package com.navi.tool.service;

import com.navi.tool.service.LogMonitorService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class z implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4947a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogMonitorService f4948b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ LogMonitorService.PoiData f4949c;

    public /* synthetic */ z(LogMonitorService logMonitorService, LogMonitorService.PoiData poiData, int i7) {
        this.f4947a = i7;
        this.f4948b = logMonitorService;
        this.f4949c = poiData;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4947a;
        LogMonitorService.PoiData poiData = this.f4949c;
        LogMonitorService logMonitorService = this.f4948b;
        switch (i7) {
            case 0:
                logMonitorService.lambda$sendNavigationBroadcast$37(poiData);
                break;
            default:
                logMonitorService.lambda$sendNavigationBroadcastForRouteTransfer$8(poiData);
                break;
        }
    }
}
