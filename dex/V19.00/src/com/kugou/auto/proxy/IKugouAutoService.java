package com.kugou.auto.proxy;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.kugou.auto.proxy.IEventListener;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface IKugouAutoService extends IInterface {
    public static final String DESCRIPTOR = "com.kugou.auto.proxy.IKugouAutoService";

    public static class Default implements IKugouAutoService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.kugou.auto.proxy.IKugouAutoService
        public void registerListener(IEventListener iEventListener) {
        }

        @Override // com.kugou.auto.proxy.IKugouAutoService
        public Bundle sendCommand(String str, Bundle bundle) {
            return null;
        }

        @Override // com.kugou.auto.proxy.IKugouAutoService
        public void unregisterListener(IEventListener iEventListener) {
        }
    }

    public static abstract class Stub extends Binder implements IKugouAutoService {
        static final int TRANSACTION_registerListener = 2;
        static final int TRANSACTION_sendCommand = 1;
        static final int TRANSACTION_unregisterListener = 3;

        public static class Proxy implements IKugouAutoService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IKugouAutoService.DESCRIPTOR;
            }

            @Override // com.kugou.auto.proxy.IKugouAutoService
            public void registerListener(IEventListener iEventListener) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKugouAutoService.DESCRIPTOR);
                    obtain.writeStrongInterface(iEventListener);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.kugou.auto.proxy.IKugouAutoService
            public Bundle sendCommand(String str, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKugouAutoService.DESCRIPTOR);
                    obtain.writeString(str);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Bundle) _Parcel.readTypedObject(obtain2, Bundle.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.kugou.auto.proxy.IKugouAutoService
            public void unregisterListener(IEventListener iEventListener) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IKugouAutoService.DESCRIPTOR);
                    obtain.writeStrongInterface(iEventListener);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IKugouAutoService.DESCRIPTOR);
        }

        public static IKugouAutoService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IKugouAutoService.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IKugouAutoService)) ? new Proxy(iBinder) : (IKugouAutoService) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
            if (i7 >= 1 && i7 <= 16777215) {
                parcel.enforceInterface(IKugouAutoService.DESCRIPTOR);
            }
            if (i7 == 1598968902) {
                parcel2.writeString(IKugouAutoService.DESCRIPTOR);
                return true;
            }
            if (i7 == 1) {
                Bundle sendCommand = sendCommand(parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                _Parcel.writeTypedObject(parcel2, sendCommand, 1);
            } else if (i7 == 2) {
                registerListener(IEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else {
                if (i7 != 3) {
                    return super.onTransact(i7, parcel, parcel2, i8);
                }
                unregisterListener(IEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
            }
            return true;
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t6, int i7) {
            if (t6 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t6.writeToParcel(parcel, i7);
            }
        }
    }

    void registerListener(IEventListener iEventListener);

    Bundle sendCommand(String str, Bundle bundle);

    void unregisterListener(IEventListener iEventListener);
}
