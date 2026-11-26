package com.navi.tool.service;

import android.content.Intent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class t implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4923a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogMonitorService f4924b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Intent f4925c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ String f4926d;

    public /* synthetic */ t(LogMonitorService logMonitorService, Intent intent, String str, int i7) {
        this.f4923a = i7;
        this.f4924b = logMonitorService;
        this.f4925c = intent;
        this.f4926d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4923a;
        LogMonitorService logMonitorService = this.f4924b;
        String str = this.f4926d;
        Intent intent = this.f4925c;
        switch (i7) {
            case 0:
                logMonitorService.lambda$startTencentHomeNavigation$45(intent, str);
                break;
            case 1:
                logMonitorService.lambda$sendNavigationBroadcastForRouteTransfer$3(intent, str);
                break;
            case 2:
                logMonitorService.lambda$sendNavigationBroadcastForRouteTransfer$4(intent, str);
                break;
            case 3:
                logMonitorService.lambda$sendNavigationBroadcast$35(intent, str);
                break;
            case 4:
                logMonitorService.lambda$startTencentCompanyNavigation$48(intent, str);
                break;
            case 5:
                logMonitorService.lambda$sendNavigationBroadcastForRouteTransfer$6(intent, str);
                break;
            case 6:
                logMonitorService.lambda$sendNavigationBroadcast$32(intent, str);
                break;
            default:
                logMonitorService.lambda$sendNavigationBroadcast$33(intent, str);
                break;
        }
    }
}
