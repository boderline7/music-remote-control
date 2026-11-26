package com.navi.tool.service;

import com.navi.tool.service.LogMonitorService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class y implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4943a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogMonitorService f4944b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f4945c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ LogMonitorService.PoiData f4946d;

    public /* synthetic */ y(LogMonitorService logMonitorService, String str, LogMonitorService.PoiData poiData, int i7) {
        this.f4943a = i7;
        this.f4944b = logMonitorService;
        this.f4945c = str;
        this.f4946d = poiData;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4943a;
        LogMonitorService logMonitorService = this.f4944b;
        LogMonitorService.PoiData poiData = this.f4946d;
        String str = this.f4945c;
        switch (i7) {
            case 0:
                logMonitorService.lambda$sendNavigationBroadcast$34(str, poiData);
                break;
            case 1:
                logMonitorService.lambda$sendNavigationBroadcast$36(str, poiData);
                break;
            case 2:
                logMonitorService.lambda$sendNavigationBroadcastForRouteTransfer$5(str, poiData);
                break;
            default:
                logMonitorService.lambda$sendNavigationBroadcastForRouteTransfer$7(str, poiData);
                break;
        }
    }
}
