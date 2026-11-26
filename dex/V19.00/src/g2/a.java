package g2;

import android.os.IBinder;
import android.os.Parcel;
import com.desaysv.ivi.vdb.event.VDEvent;
import o4.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements c {

    /* renamed from: a, reason: collision with root package name */
    public IBinder f6431a;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f6431a;
    }

    public final VDEvent h(VDEvent vDEvent) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(c.DESCRIPTOR);
            obtain.writeInt(1);
            vDEvent.writeToParcel(obtain, 0);
            this.f6431a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? VDEvent.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void i(VDEvent vDEvent) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(c.DESCRIPTOR);
            if (vDEvent != null) {
                obtain.writeInt(1);
                vDEvent.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f6431a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final void j(int[] iArr, int i7, String str, i iVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(c.DESCRIPTOR);
            obtain.writeIntArray(iArr);
            obtain.writeInt(i7);
            obtain.writeString(str);
            obtain.writeStrongInterface(iVar);
            this.f6431a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
