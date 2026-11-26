package com.baidu.naviauto.imaplbs;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface IEventListener extends IInterface {
    public static final String DESCRIPTOR = "com.baidu.naviauto.imaplbs.IEventListener";

    public static class Default implements IEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.baidu.naviauto.imaplbs.IEventListener
        public void onEvent(String str) {
        }
    }

    public static abstract class Stub extends Binder implements IEventListener {
        static final int TRANSACTION_onEvent = 1;

        public static class Proxy implements IEventListener {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEventListener.DESCRIPTOR;
            }

            @Override // com.baidu.naviauto.imaplbs.IEventListener
            public void onEvent(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IEventListener.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IEventListener.DESCRIPTOR);
        }

        public static IEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IEventListener.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IEventListener)) ? new Proxy(iBinder) : (IEventListener) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
            if (i7 >= 1 && i7 <= 16777215) {
                parcel.enforceInterface(IEventListener.DESCRIPTOR);
            }
            if (i7 == 1598968902) {
                parcel2.writeString(IEventListener.DESCRIPTOR);
                return true;
            }
            if (i7 != 1) {
                return super.onTransact(i7, parcel, parcel2, i8);
            }
            onEvent(parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void onEvent(String str);
}
