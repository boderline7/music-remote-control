package f4;

import android.os.IBinder;
import android.os.Parcel;
import com.lion.api.bus.sync.SyncBusEvent;
import o4.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements c {

    /* renamed from: a, reason: collision with root package name */
    public IBinder f6402a;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f6402a;
    }

    public final SyncBusEvent h(SyncBusEvent syncBusEvent) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(c.DESCRIPTOR);
            obtain.writeInt(1);
            syncBusEvent.writeToParcel(obtain, 0);
            this.f6402a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? SyncBusEvent.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final boolean i(int i7, String str, l lVar) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(c.DESCRIPTOR);
            obtain.writeInt(i7);
            obtain.writeString(str);
            obtain.writeStrongInterface(lVar);
            this.f6402a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final SyncBusEvent j(SyncBusEvent syncBusEvent) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(c.DESCRIPTOR);
            obtain.writeInt(1);
            syncBusEvent.writeToParcel(obtain, 0);
            this.f6402a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? SyncBusEvent.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final boolean k(int i7) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(c.DESCRIPTOR);
            obtain.writeInt(i7);
            this.f6402a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
