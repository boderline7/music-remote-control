package g2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.desaysv.ivi.vdb.event.VDEvent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class e extends Binder implements IInterface {
    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
        if (i7 >= 1 && i7 <= 16777215) {
            parcel.enforceInterface("com.desaysv.ivi.vdb.IVDBusNotify");
        }
        if (i7 == 1598968902) {
            parcel2.writeString("com.desaysv.ivi.vdb.IVDBusNotify");
            return true;
        }
        if (i7 != 1) {
            return super.onTransact(i7, parcel, parcel2, i8);
        }
        VDEvent createFromParcel = parcel.readInt() != 0 ? VDEvent.CREATOR.createFromParcel(parcel) : null;
        if (createFromParcel != null) {
            o4.c.c().o(createFromParcel);
        }
        parcel2.writeNoException();
        return true;
    }
}
