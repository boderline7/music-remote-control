package com.baidu.naviauto.imaplbs;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.baidu.naviauto.imaplbs.IEventListener;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface IMapAutoAPIService extends IInterface {
    public static final String DESCRIPTOR = "com.baidu.naviauto.imaplbs.IMapAutoAPIService";

    public static class Default implements IMapAutoAPIService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.baidu.naviauto.imaplbs.IMapAutoAPIService
        public void removeSDKEventListener(String str) {
        }

        @Override // com.baidu.naviauto.imaplbs.IMapAutoAPIService
        public String send(String str) {
            return null;
        }

        @Override // com.baidu.naviauto.imaplbs.IMapAutoAPIService
        public void setSDKEventListener(IEventListener iEventListener, String str) {
        }
    }

    public static abstract class Stub extends Binder implements IMapAutoAPIService {
        static final int TRANSACTION_removeSDKEventListener = 3;
        static final int TRANSACTION_send = 1;
        static final int TRANSACTION_setSDKEventListener = 2;

        public static class Proxy implements IMapAutoAPIService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMapAutoAPIService.DESCRIPTOR;
            }

            @Override // com.baidu.naviauto.imaplbs.IMapAutoAPIService
            public void removeSDKEventListener(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMapAutoAPIService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.baidu.naviauto.imaplbs.IMapAutoAPIService
            public String send(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMapAutoAPIService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.baidu.naviauto.imaplbs.IMapAutoAPIService
            public void setSDKEventListener(IEventListener iEventListener, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMapAutoAPIService.DESCRIPTOR);
                    obtain.writeStrongInterface(iEventListener);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IMapAutoAPIService.DESCRIPTOR);
        }

        public static IMapAutoAPIService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMapAutoAPIService.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMapAutoAPIService)) ? new Proxy(iBinder) : (IMapAutoAPIService) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
            if (i7 >= 1 && i7 <= 16777215) {
                parcel.enforceInterface(IMapAutoAPIService.DESCRIPTOR);
            }
            if (i7 == 1598968902) {
                parcel2.writeString(IMapAutoAPIService.DESCRIPTOR);
                return true;
            }
            if (i7 == 1) {
                String send = send(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(send);
            } else if (i7 == 2) {
                setSDKEventListener(IEventListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
            } else {
                if (i7 != 3) {
                    return super.onTransact(i7, parcel, parcel2, i8);
                }
                removeSDKEventListener(parcel.readString());
                parcel2.writeNoException();
            }
            return true;
        }
    }

    void removeSDKEventListener(String str);

    String send(String str);

    void setSDKEventListener(IEventListener iEventListener, String str);
}
