package android.support.v4.media;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import android.os.RemoteException;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.carinfo.LionCarInfoService;
import com.navi.tool.utils.ToastHelper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j implements ServiceConnection {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f547a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f548b;

    public /* synthetic */ j(int i7, Object obj) {
        this.f547a = i7;
        this.f548b = obj;
    }

    public final boolean a() {
        a.a.u(this.f548b);
        throw null;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        int i7 = this.f547a;
        f2.c cVar = null;
        f4.c cVar2 = null;
        g2.c cVar3 = null;
        Object obj = this.f548b;
        switch (i7) {
            case 1:
                o4.b bVar = (o4.b) obj;
                int i8 = f2.b.f6401a;
                if (iBinder != null) {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface(f2.c.DESCRIPTOR);
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof f2.c)) {
                        f2.a aVar = new f2.a();
                        aVar.f6400a = iBinder;
                        cVar = aVar;
                    } else {
                        cVar = (f2.c) queryLocalInterface;
                    }
                }
                bVar.f7924c = cVar;
                bVar.f7925d = true;
                Handler handler = bVar.f7923b;
                handler.removeCallbacks(bVar.f7930i);
                handler.removeCallbacks(bVar.f7931j);
                o4.c.c().f7936d.i(Boolean.TRUE);
                bVar.c((String) o4.c.c().f7938f.d());
                break;
            case 2:
                CarInfoService carInfoService = (CarInfoService) obj;
                int i9 = g2.b.f6432a;
                if (iBinder != null) {
                    IInterface queryLocalInterface2 = iBinder.queryLocalInterface(g2.c.DESCRIPTOR);
                    if (queryLocalInterface2 == null || !(queryLocalInterface2 instanceof g2.c)) {
                        g2.a aVar2 = new g2.a();
                        aVar2.f6431a = iBinder;
                        cVar3 = aVar2;
                    } else {
                        cVar3 = (g2.c) queryLocalInterface2;
                    }
                }
                carInfoService.f4691e = cVar3;
                carInfoService.f4693g = false;
                try {
                    ((g2.a) ((CarInfoService) obj).f4691e).f6431a.linkToDeath(((CarInfoService) obj).f4694h, 0);
                    CarInfoService.a((CarInfoService) obj, 327684, CarInfoService.f4686z);
                    CarInfoService.a((CarInfoService) obj, 327681, CarInfoService.A);
                    CarInfoService.a((CarInfoService) obj, 327690, CarInfoService.B);
                    CarInfoService.a((CarInfoService) obj, 327685, CarInfoService.D);
                    CarInfoService.a((CarInfoService) obj, 327682, CarInfoService.C);
                    ((g2.a) ((CarInfoService) obj).f4691e).j(new int[]{0}, 29523, ((CarInfoService) obj).getPackageName(), ((CarInfoService) obj).f4698l);
                    ((g2.a) ((CarInfoService) obj).f4691e).j(new int[]{0}, 17668, ((CarInfoService) obj).getPackageName(), ((CarInfoService) obj).f4698l);
                    ((g2.a) ((CarInfoService) obj).f4691e).j(new int[]{0}, 31014, ((CarInfoService) obj).getPackageName(), ((CarInfoService) obj).f4698l);
                    o4.c.c().n(true);
                    ToastHelper.show(((CarInfoService) obj).getApplicationContext(), "车联服务已连接");
                    CarInfoService carInfoService2 = (CarInfoService) obj;
                    if (carInfoService2.f4691e != null) {
                        new Thread(new o4.d(carInfoService2, 2)).start();
                    }
                    CarInfoService carInfoService3 = (CarInfoService) obj;
                    if (carInfoService3.f4691e != null) {
                        new Thread(new o4.d(carInfoService3, 3)).start();
                        break;
                    } else {
                        break;
                    }
                } catch (RemoteException unused) {
                    return;
                }
                break;
            default:
                LionCarInfoService lionCarInfoService = (LionCarInfoService) obj;
                int i10 = f4.b.f6403a;
                if (iBinder != null) {
                    IInterface queryLocalInterface3 = iBinder.queryLocalInterface(f4.c.DESCRIPTOR);
                    if (queryLocalInterface3 == null || !(queryLocalInterface3 instanceof f4.c)) {
                        f4.a aVar3 = new f4.a();
                        aVar3.f6402a = iBinder;
                        cVar2 = aVar3;
                    } else {
                        cVar2 = (f4.c) queryLocalInterface3;
                    }
                }
                lionCarInfoService.f4713a = cVar2;
                lionCarInfoService.f4717e = false;
                try {
                    ((f4.a) ((LionCarInfoService) obj).f4713a).f6402a.linkToDeath(((LionCarInfoService) obj).f4720h, 0);
                    ((f4.a) ((LionCarInfoService) obj).f4713a).i(Process.myPid(), ((LionCarInfoService) obj).getPackageName(), ((LionCarInfoService) obj).f4723k);
                    o4.c.c().n(true);
                    ToastHelper.show(((LionCarInfoService) obj).getApplicationContext(), "雄狮同步服务已连接");
                    LionCarInfoService lionCarInfoService2 = (LionCarInfoService) obj;
                    lionCarInfoService2.getClass();
                    new Thread(new o4.k(lionCarInfoService2, 0)).start();
                    LionCarInfoService.a((LionCarInfoService) obj);
                    break;
                } catch (RemoteException unused2) {
                    return;
                }
                break;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        int i7 = this.f547a;
        Object obj = this.f548b;
        switch (i7) {
            case 1:
                o4.b bVar = (o4.b) obj;
                bVar.f7924c = null;
                bVar.f7925d = false;
                o4.c.c().f7936d.i(Boolean.FALSE);
                if (bVar.f7926e) {
                    bVar.f7923b.postDelayed(bVar.f7930i, 5000L);
                    break;
                }
                break;
            case 2:
                CarInfoService carInfoService = (CarInfoService) obj;
                carInfoService.f4691e = null;
                carInfoService.f4693g = false;
                o4.c.c().n(false);
                break;
            default:
                LionCarInfoService lionCarInfoService = (LionCarInfoService) obj;
                lionCarInfoService.f4713a = null;
                lionCarInfoService.f4717e = false;
                Handler handler = lionCarInfoService.f4716d;
                if (handler != null) {
                    handler.removeCallbacks(lionCarInfoService.f4722j);
                }
                HandlerThread handlerThread = lionCarInfoService.f4715c;
                if (handlerThread != null) {
                    handlerThread.quitSafely();
                    lionCarInfoService.f4715c = null;
                    lionCarInfoService.f4716d = null;
                }
                o4.c.c().n(false);
                break;
        }
    }
}
