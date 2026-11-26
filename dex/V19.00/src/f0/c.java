package f0;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import g0.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c extends Binder implements IInterface {
    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
        b bVar;
        if (i7 >= 1 && i7 <= 16777215) {
            parcel.enforceInterface("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService");
        }
        if (i7 == 1598968902) {
            parcel2.writeString("androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService");
            return true;
        }
        if (i7 != 1) {
            return super.onTransact(i7, parcel, parcel2, i8);
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            bVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface(b.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof b)) {
                a aVar = new a();
                aVar.f6395a = readStrongBinder;
                bVar = aVar;
            } else {
                bVar = (b) queryLocalInterface;
            }
        }
        k kVar = (k) this;
        if (bVar != null) {
            kVar.f6426a.a();
        }
        return true;
    }
}
