package o4;

import android.R;
import android.app.AlertDialog;
import android.media.ToneGenerator;
import com.navi.tool.carinfo.CarInfoService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7959a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ CarInfoService f7960b;

    public /* synthetic */ d(CarInfoService carInfoService, int i7) {
        this.f7959a = i7;
        this.f7960b = carInfoService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f7959a;
        CarInfoService carInfoService = this.f7960b;
        switch (i7) {
            case 0:
                int[] iArr = CarInfoService.f4686z;
                carInfoService.b();
                break;
            case 1:
                int[] iArr2 = CarInfoService.f4686z;
                carInfoService.e();
                break;
            case 2:
                int[] iArr3 = CarInfoService.f4686z;
                carInfoService.getClass();
                try {
                    carInfoService.f(new int[]{255, z1.c.CONST_METHOD_HANDLE, 1, 255});
                    Thread.sleep(50L);
                    carInfoService.f(new int[]{255, 10, 1, 255});
                    carInfoService.f(new int[]{255, 7, 1, 255});
                    carInfoService.f(new int[]{255, 8, 1, 255});
                    break;
                } catch (Exception unused) {
                    return;
                }
            case 3:
                int[] iArr4 = CarInfoService.f4686z;
                carInfoService.getClass();
                try {
                    carInfoService.c(327684, new int[]{z1.c.INVOKE_STATIC_RANGE, 68, 24, 99, 2, 62, 1035, 26, 15, 16, 17, 18, 19, 47, 71, 72, z1.c.INVOKE_INTERFACE_RANGE, 91, 1, 22, 23, 32, 33, 34, 35, 79, z1.c.INVOKE_SUPER_RANGE, 75, 80, z1.c.INVOKE_VIRTUAL, 96, 97, 95, 36, 37, 74, 28, 29, 13});
                    carInfoService.c(327681, new int[]{100, z1.c.SGET_CHAR, z1.c.SGET_SHORT, z1.c.SPUT, 10, 91, 5, 6, 44, 45, 52, 54, 93, 94, 95, 96, z1.c.INVOKE_INTERFACE, 115, z1.c.DOUBLE_TO_LONG, z1.c.INT_TO_BYTE, z1.c.USHR_LONG_2ADDR, z1.c.ADD_FLOAT_2ADDR, z1.c.MUL_FLOAT, z1.c.AND_LONG, z1.c.OR_LONG, z1.c.MUL_LONG_2ADDR, z1.c.DIV_LONG_2ADDR});
                    carInfoService.c(327685, new int[]{11});
                    carInfoService.c(327690, new int[]{10, 1, 12, 8, 4, 18, 1035, 62, 6, 3, 24, 80, 19, 61, 64, 66, 71, 48, 58, 59, 60});
                    carInfoService.c(327682, new int[]{10, 23, 24, 4, 25, 8});
                    break;
                } catch (Exception unused2) {
                    return;
                }
            case 4:
                AlertDialog alertDialog = carInfoService.f4702p;
                if (alertDialog == null || !alertDialog.isShowing()) {
                    if (!carInfoService.f4705s) {
                        carInfoService.f4705s = true;
                        Thread thread = new Thread(new d(carInfoService, 5));
                        carInfoService.f4704r = thread;
                        thread.start();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(carInfoService, R.style.Theme.DeviceDefault.Dialog.Alert);
                    builder.setTitle("⚠️ 香氛告警");
                    builder.setMessage("香氛弹余量不足，请及时更换！");
                    builder.setCancelable(false);
                    builder.setPositiveButton("确认", new g(carInfoService, 0));
                    AlertDialog create = builder.create();
                    carInfoService.f4702p = create;
                    create.getWindow().setType(2038);
                    carInfoService.f4702p.show();
                    break;
                }
                break;
            default:
                int[] iArr5 = CarInfoService.f4686z;
                carInfoService.getClass();
                try {
                    if (carInfoService.f4703q == null) {
                        carInfoService.f4703q = new ToneGenerator(4, 100);
                    }
                    while (carInfoService.f4705s) {
                        carInfoService.f4703q.startTone(93, 200);
                        Thread.sleep(500L);
                    }
                    break;
                } catch (Exception unused3) {
                }
        }
    }
}
