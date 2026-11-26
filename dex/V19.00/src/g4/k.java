package g4;

import com.navi.tool.service.LogMonitorService;
import com.navi.tool.utils.AccessibilityGuard;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6550a;

    public /* synthetic */ k(int i7) {
        this.f6550a = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f6550a) {
            case 0:
                AccessibilityGuard.rebindIfNeeded();
                break;
            case 1:
                AccessibilityGuard.rebindIfNeeded();
                break;
            case 2:
                LogMonitorService.lambda$startTencentHomeNavigation$44();
                break;
            case 3:
                LogMonitorService.lambda$sendAmapCommandBroadcast$38();
                break;
            case 4:
                LogMonitorService.lambda$startTencentCompanyNavigation$47();
                break;
            case 5:
                LogMonitorService.lambda$handleSteeringKeyAction4$22();
                break;
            case 6:
                LogMonitorService.lambda$startSteeringLogMonitoring$17();
                break;
            case 7:
                LogMonitorService.lambda$startSteeringLogMonitoring$18();
                break;
            case 8:
                LogMonitorService.lambda$sendAmapContinueNavigationBroadcast$63();
                break;
            case 9:
                LogMonitorService.lambda$sendAmapZoomBroadcast$57();
                break;
            case 10:
                LogMonitorService.lambda$sendBaiduExitBroadcast$41();
                break;
            case 11:
                LogMonitorService.lambda$sendAmapHomeCompanyManualBroadcast$50();
                break;
            case 12:
                LogMonitorService.lambda$sendAmapMuteBroadcast$54();
                break;
            case 13:
                LogMonitorService.lambda$sendAmapSwitchRoadBroadcast$60();
                break;
            case 14:
                LogMonitorService.lambda$launchEnabledAmapApps$53();
                break;
            default:
                AccessibilityGuard.lambda$rebindIfNeeded$0();
                break;
        }
    }
}
