package o4;

import android.os.Bundle;
import com.navi.tool.carinfo.LionCarInfoService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7973a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LionCarInfoService f7974b;

    public /* synthetic */ k(LionCarInfoService lionCarInfoService, int i7) {
        this.f7973a = i7;
        this.f7974b = lionCarInfoService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f7973a;
        LionCarInfoService lionCarInfoService = this.f7974b;
        switch (i7) {
            case 0:
                int i8 = LionCarInfoService.CABIN_GEAR_DISPLAY_INFO;
                lionCarInfoService.getClass();
                try {
                    LionCarInfoService.g(LionCarInfoService.CABIN_GEAR_DISPLAY_INFO, lionCarInfoService.e(LionCarInfoService.CABIN_GEAR_DISPLAY_INFO, null));
                    LionCarInfoService.g(LionCarInfoService.CABIN_CAR_SPEED, lionCarInfoService.e(LionCarInfoService.CABIN_CAR_SPEED, null));
                    LionCarInfoService.g(LionCarInfoService.CABIN_ENGINE_STATE, lionCarInfoService.e(LionCarInfoService.CABIN_ENGINE_STATE, null));
                    LionCarInfoService.g(LionCarInfoService.CABIN_SKY_LIGHT_CONTROL, lionCarInfoService.e(LionCarInfoService.CABIN_SKY_LIGHT_CONTROL, null));
                    LionCarInfoService.g(1, lionCarInfoService.e(1, null));
                    LionCarInfoService.g(4, lionCarInfoService.e(4, null));
                    LionCarInfoService.g(LionCarInfoService.HVAC_AUTO, lionCarInfoService.e(LionCarInfoService.HVAC_AUTO, null));
                    LionCarInfoService.g(2, lionCarInfoService.e(2, null));
                    LionCarInfoService.g(25, lionCarInfoService.e(25, null));
                    LionCarInfoService.g(26, lionCarInfoService.e(26, null));
                    Bundle bundle = new Bundle();
                    bundle.putInt("data", 0);
                    LionCarInfoService.b(0, lionCarInfoService.e(LionCarInfoService.CABIN_DOORS_STATUS, bundle));
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("data", 1);
                    LionCarInfoService.b(1, lionCarInfoService.e(LionCarInfoService.CABIN_DOORS_STATUS, bundle2));
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("data", 2);
                    LionCarInfoService.b(2, lionCarInfoService.e(LionCarInfoService.CABIN_DOORS_STATUS, bundle3));
                    Bundle bundle4 = new Bundle();
                    bundle4.putInt("data", 3);
                    LionCarInfoService.b(3, lionCarInfoService.e(LionCarInfoService.CABIN_DOORS_STATUS, bundle4));
                    Bundle bundle5 = new Bundle();
                    bundle5.putInt("data", 4);
                    LionCarInfoService.b(4, lionCarInfoService.e(LionCarInfoService.CABIN_DOORS_STATUS, bundle5));
                    Bundle bundle6 = new Bundle();
                    bundle6.putInt("data", 5);
                    LionCarInfoService.b(5, lionCarInfoService.e(LionCarInfoService.CABIN_DOORS_STATUS, bundle6));
                    Bundle bundle7 = new Bundle();
                    bundle7.putInt("data", 1);
                    LionCarInfoService.c(1, lionCarInfoService.e(LionCarInfoService.VEHICLE_WINDOW_STATUS, bundle7));
                    Bundle bundle8 = new Bundle();
                    bundle8.putInt("data", 2);
                    LionCarInfoService.c(2, lionCarInfoService.e(LionCarInfoService.VEHICLE_WINDOW_STATUS, bundle8));
                    Bundle bundle9 = new Bundle();
                    bundle9.putInt("data", 3);
                    LionCarInfoService.c(3, lionCarInfoService.e(LionCarInfoService.VEHICLE_WINDOW_STATUS, bundle9));
                    Bundle bundle10 = new Bundle();
                    bundle10.putInt("data", 4);
                    LionCarInfoService.c(4, lionCarInfoService.e(LionCarInfoService.VEHICLE_WINDOW_STATUS, bundle10));
                    Bundle bundle11 = new Bundle();
                    bundle11.putInt("status", 1);
                    LionCarInfoService.g(LionCarInfoService.CABIN_SEAT_HEAT_STATE, lionCarInfoService.e(LionCarInfoService.CABIN_SEAT_HEAT_STATE, bundle11));
                    Bundle bundle12 = new Bundle();
                    bundle12.putInt("status", 2);
                    LionCarInfoService.g(LionCarInfoService.CABIN_SEAT_HEAT_STATE, lionCarInfoService.e(LionCarInfoService.CABIN_SEAT_HEAT_STATE, bundle12));
                    Bundle bundle13 = new Bundle();
                    bundle13.putInt("status", 1);
                    LionCarInfoService.g(LionCarInfoService.CABIN_SEAT_WIND_STATE, lionCarInfoService.e(LionCarInfoService.CABIN_SEAT_WIND_STATE, bundle13));
                    Bundle bundle14 = new Bundle();
                    bundle14.putInt("status", 2);
                    LionCarInfoService.g(LionCarInfoService.CABIN_SEAT_WIND_STATE, lionCarInfoService.e(LionCarInfoService.CABIN_SEAT_WIND_STATE, bundle14));
                    break;
                } catch (Exception unused) {
                    return;
                }
            default:
                int i9 = LionCarInfoService.CABIN_GEAR_DISPLAY_INFO;
                lionCarInfoService.d();
                break;
        }
    }
}
