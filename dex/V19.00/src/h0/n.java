package h0;

import android.content.Intent;
import android.view.View;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;
import com.navi.tool.service.CarClimateManager;
import com.navi.tool.service.ConfigBackupService;
import com.navi.tool.service.MobileHudService;
import com.navi.tool.ui.extra.DesktopAssistantCarControlHelper;
import com.navi.tool.utils.AmapBroadcastConstants;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class n implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6636a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f6637b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f6638c;

    public /* synthetic */ n(int i7, int i8, Object obj) {
        this.f6636a = i8;
        this.f6638c = obj;
        this.f6637b = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f6636a;
        int i8 = this.f6637b;
        Object obj = this.f6638c;
        switch (i7) {
            case 0:
                ((l6.f) obj).a0(i8);
                break;
            case 1:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) obj;
                View view = (View) sideSheetBehavior.f4133p.get();
                if (view != null) {
                    sideSheetBehavior.z(view, i8, false);
                    break;
                }
                break;
            case 2:
                AmapBroadcastService amapBroadcastService = (AmapBroadcastService) obj;
                String str = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                try {
                    Intent intent = new Intent(AmapBroadcastConstants.DEFAULT_AMAP_BROADCAST);
                    intent.putExtra(AmapBroadcastConstants.EXTRA_SELF_BROADCAST, true);
                    intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, Auto3DBroadcastReceiver.KEY_TYPE_VALUE);
                    intent.putExtra(Auto3DBroadcastReceiver.EXTRA_STATE, i8);
                    amapBroadcastService.sendBroadcast(intent);
                    break;
                } catch (Throwable unused) {
                    return;
                }
            case 3:
                ((CarClimateManager) obj).lambda$applyFragranceType$4(i8);
                break;
            case 4:
                ((ConfigBackupService) obj).lambda$onStartCommand$1(i8);
                break;
            case 5:
                ((MobileHudService) obj).lambda$refreshManagedMusicState$3(i8);
                break;
            default:
                ((DesktopAssistantCarControlHelper) obj).lambda$switchFragranceType$77(i8);
                break;
        }
    }
}
