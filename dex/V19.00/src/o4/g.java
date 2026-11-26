package o4;

import android.content.DialogInterface;
import com.navi.tool.carinfo.CarInfoService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7969a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CarInfoService f7970b;

    public /* synthetic */ g(CarInfoService carInfoService, int i7) {
        this.f7969a = i7;
        this.f7970b = carInfoService;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f7969a;
        CarInfoService carInfoService = this.f7970b;
        switch (i8) {
            case 0:
                int[] iArr = CarInfoService.f4686z;
                carInfoService.g();
                dialogInterface.dismiss();
                carInfoService.f4702p = null;
                break;
            default:
                int[] iArr2 = CarInfoService.f4686z;
                carInfoService.g();
                dialogInterface.dismiss();
                carInfoService.f4701o = null;
                break;
        }
    }
}
