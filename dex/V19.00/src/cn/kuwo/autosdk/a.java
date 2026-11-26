package cn.kuwo.autosdk;

import android.os.IBinder;
import android.os.Parcel;
import cn.kuwo.base.bean.Music;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements c {

    /* renamed from: a, reason: collision with root package name */
    public IBinder f3098a;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f3098a;
    }

    public final int h() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("cn.kuwo.autosdk.IAutoSdkAidlInterface");
            this.f3098a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final Music i() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("cn.kuwo.autosdk.IAutoSdkAidlInterface");
            this.f3098a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? Music.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
