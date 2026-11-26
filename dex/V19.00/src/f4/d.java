package f4;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.lion.api.bus.sync.SyncBusEvent;
import com.navi.tool.carinfo.LionCarInfoService;
import o4.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d extends Binder implements IInterface {
    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
        if (i7 >= 1 && i7 <= 16777215) {
            parcel.enforceInterface("com.lion.api.bus.sync.ISyncBusCallBack");
        }
        if (i7 == 1598968902) {
            parcel2.writeString("com.lion.api.bus.sync.ISyncBusCallBack");
            return true;
        }
        if (i7 != 1) {
            return super.onTransact(i7, parcel, parcel2, i8);
        }
        SyncBusEvent createFromParcel = parcel.readInt() != 0 ? SyncBusEvent.CREATOR.createFromParcel(parcel) : null;
        l lVar = (l) this;
        if (createFromParcel != null) {
            Bundle bundle = createFromParcel.f4485c;
            int i9 = LionCarInfoService.CABIN_GEAR_DISPLAY_INFO;
            int i10 = createFromParcel.f4484b;
            lVar.f7975a.getClass();
            LionCarInfoService.g(i10, bundle);
        }
        parcel2.writeNoException();
        return true;
    }
}
