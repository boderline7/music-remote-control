package f2;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements c {

    /* renamed from: a, reason: collision with root package name */
    public IBinder f6400a;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f6400a;
    }

    public final void h(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(c.DESCRIPTOR);
            obtain.writeString(str);
            obtain.writeString(str2);
            this.f6400a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
