package b;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements b {

    /* renamed from: a, reason: collision with root package name */
    public IBinder f2923a;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f2923a;
    }

    @Override // b.b
    public final void f(int i7, Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(b.DESCRIPTOR);
            obtain.writeInt(i7);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f2923a.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
