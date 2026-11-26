package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class x implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4941a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f4942b;

    public /* synthetic */ x(String str, int i7) {
        this.f4941a = i7;
        this.f4942b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4941a;
        String str = this.f4942b;
        switch (i7) {
            case 0:
                LogMonitorService.lambda$sendAmapCommandBroadcast$39(str);
                break;
            case 1:
                LogMonitorService.lambda$sendAmapCommandBroadcast$40(str);
                break;
            case 2:
                LogMonitorService.lambda$logAsync$16(str);
                break;
            case 3:
                LogMonitorService.lambda$sendAmapContinueNavigationBroadcast$64(str);
                break;
            case 4:
                LogMonitorService.lambda$sendAmapContinueNavigationBroadcast$65(str);
                break;
            case 5:
                LogMonitorService.lambda$sendAmapZoomBroadcast$58(str);
                break;
            case 6:
                LogMonitorService.lambda$sendAmapZoomBroadcast$59(str);
                break;
            case 7:
                LogMonitorService.lambda$sendBaiduExitBroadcast$42(str);
                break;
            case 8:
                LogMonitorService.lambda$sendAmapHomeCompanyManualBroadcast$51(str);
                break;
            case 9:
                LogMonitorService.lambda$sendAmapHomeCompanyManualBroadcast$52(str);
                break;
            case 10:
                LogMonitorService.lambda$sendAmapMuteBroadcast$55(str);
                break;
            case 11:
                LogMonitorService.lambda$sendAmapMuteBroadcast$56(str);
                break;
            case 12:
                LogMonitorService.lambda$sendAmapSwitchRoadBroadcast$61(str);
                break;
            default:
                LogMonitorService.lambda$sendAmapSwitchRoadBroadcast$62(str);
                break;
        }
    }
}
