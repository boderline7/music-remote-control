package b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.support.v4.os.ResultReceiver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends Binder implements b {

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f2924b = 0;

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ResultReceiver f2925a;

    public c(ResultReceiver resultReceiver) {
        this.f2925a = resultReceiver;
        attachInterface(this, b.DESCRIPTOR);
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // b.b
    public final void f(int i7, Bundle bundle) {
        ResultReceiver resultReceiver = this.f2925a;
        resultReceiver.getClass();
        resultReceiver.l(i7, bundle);
    }

    @Override // android.os.Binder
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
        if (i7 >= 1 && i7 <= 16777215) {
            parcel.enforceInterface(b.DESCRIPTOR);
        }
        if (i7 == 1598968902) {
            parcel2.writeString(b.DESCRIPTOR);
            return true;
        }
        if (i7 != 1) {
            return super.onTransact(i7, parcel, parcel2, i8);
        }
        f(parcel.readInt(), (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
        return true;
    }
}
