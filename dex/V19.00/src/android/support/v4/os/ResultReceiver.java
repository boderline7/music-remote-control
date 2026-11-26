package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.l;
import b.a;
import b.b;
import b.c;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new l(4);

    /* renamed from: a, reason: collision with root package name */
    public b f603a;

    public ResultReceiver(Parcel parcel) {
        b bVar;
        IBinder readStrongBinder = parcel.readStrongBinder();
        int i7 = c.f2924b;
        if (readStrongBinder == null) {
            bVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface(b.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof b)) {
                a aVar = new a();
                aVar.f2923a = readStrongBinder;
                bVar = aVar;
            } else {
                bVar = (b) queryLocalInterface;
            }
        }
        this.f603a = bVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public void l(int i7, Bundle bundle) {
    }

    public final void m(int i7, Bundle bundle) {
        b bVar = this.f603a;
        if (bVar != null) {
            try {
                bVar.f(i7, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        synchronized (this) {
            try {
                if (this.f603a == null) {
                    this.f603a = new c(this);
                }
                parcel.writeStrongBinder(this.f603a.asBinder());
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
