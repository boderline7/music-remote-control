package com.navi.tool.amapflying.util.kuwo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import cn.kuwo.autosdk.m;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class KuwoAidlPositionHelper {
    private static final long BIND_RETRY_INTERVAL_MS = 3000;
    private static final String INTERFACE_DESCRIPTOR = "cn.kuwo.autosdk.IAutoSdkAidlInterface";
    private static final String SERVICE_CLASS = "cn.kuwo.autosdk.AutoSdkService";
    private static final String TAG = "KuwoAidlPosHelper";
    private static final String[] TARGET_PACKAGES = {m.PREFERENCE_PACKAGE, "cn.kuwo.kwmusicauto"};
    private static final int TRANSACTION_GET_CURRENT_POS = 5;
    private boolean mBinding;
    private boolean mBound;
    private String mBoundPackage;
    private final ServiceConnection mConnection = new ServiceConnection() { // from class: com.navi.tool.amapflying.util.kuwo.KuwoAidlPositionHelper.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            KuwoAidlPositionHelper.this.mRemoteBinder = iBinder;
            KuwoAidlPositionHelper.this.mBound = true;
            KuwoAidlPositionHelper.this.mBinding = false;
            KuwoAidlPositionHelper.this.mBoundPackage = componentName != null ? componentName.getPackageName() : null;
            Objects.toString(componentName);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Objects.toString(componentName);
            KuwoAidlPositionHelper.this.mRemoteBinder = null;
            KuwoAidlPositionHelper.this.mBound = false;
            KuwoAidlPositionHelper.this.mBinding = false;
            KuwoAidlPositionHelper.this.mBoundPackage = null;
        }
    };
    private final Context mContext;
    private long mLastBindAttemptMs;
    private IBinder mRemoteBinder;

    public KuwoAidlPositionHelper(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private void bindInternal() {
        this.mLastBindAttemptMs = SystemClock.elapsedRealtime();
        for (String str : TARGET_PACKAGES) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(str, SERVICE_CLASS));
            if (this.mContext.bindService(intent, this.mConnection, 1)) {
                this.mBinding = true;
                return;
            }
            continue;
        }
        this.mBinding = false;
    }

    public String getBoundPackage() {
        return this.mBoundPackage;
    }

    public int getCurrentPosition() {
        IBinder iBinder = this.mRemoteBinder;
        int i7 = -1;
        if (iBinder == null || !iBinder.isBinderAlive()) {
            start();
            return -1;
        }
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(INTERFACE_DESCRIPTOR);
            this.mRemoteBinder.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            i7 = obtain2.readInt();
        } catch (RemoteException | Exception unused) {
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
        obtain2.recycle();
        obtain.recycle();
        return i7;
    }

    public boolean isConnected() {
        IBinder iBinder;
        return this.mBound && (iBinder = this.mRemoteBinder) != null && iBinder.isBinderAlive();
    }

    public void start() {
        IBinder iBinder;
        if ((this.mBound && (iBinder = this.mRemoteBinder) != null && iBinder.isBinderAlive()) || this.mBinding || SystemClock.elapsedRealtime() - this.mLastBindAttemptMs < 3000) {
            return;
        }
        bindInternal();
    }

    public void stop() {
        if (!this.mBound && !this.mBinding) {
            this.mRemoteBinder = null;
            this.mBoundPackage = null;
            return;
        }
        try {
            this.mContext.unbindService(this.mConnection);
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.mRemoteBinder = null;
            this.mBound = false;
            this.mBinding = false;
            this.mBoundPackage = null;
            throw th;
        }
        this.mRemoteBinder = null;
        this.mBound = false;
        this.mBinding = false;
        this.mBoundPackage = null;
    }
}
