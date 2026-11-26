package o4;

import android.app.Service;
import android.os.IBinder;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.carinfo.LionCarInfoService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h implements IBinder.DeathRecipient {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7971a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Service f7972b;

    public /* synthetic */ h(Service service, int i7) {
        this.f7971a = i7;
        this.f7972b = service;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        int i7 = this.f7971a;
        Service service = this.f7972b;
        switch (i7) {
            case 0:
                CarInfoService carInfoService = (CarInfoService) service;
                carInfoService.f4692f.removeCallbacks(carInfoService.f4696j);
                g2.c cVar = carInfoService.f4691e;
                if (cVar != null) {
                    ((g2.a) cVar).f6431a.unlinkToDeath(this, 0);
                    carInfoService.f4691e = null;
                }
                c.c().n(false);
                carInfoService.f4692f.postDelayed(carInfoService.f4695i, 5000L);
                break;
            default:
                LionCarInfoService lionCarInfoService = (LionCarInfoService) service;
                f4.c cVar2 = lionCarInfoService.f4713a;
                if (cVar2 != null) {
                    ((f4.a) cVar2).f6402a.unlinkToDeath(this, 0);
                    lionCarInfoService.f4713a = null;
                }
                c.c().n(false);
                lionCarInfoService.f4714b.postDelayed(lionCarInfoService.f4721i, 5000L);
                break;
        }
    }
}
