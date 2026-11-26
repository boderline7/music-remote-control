package s1;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements ServiceConnection {

    /* renamed from: c, reason: collision with root package name */
    public static a f8650c;

    /* renamed from: a, reason: collision with root package name */
    public cn.kuwo.autosdk.c f8651a;

    /* renamed from: b, reason: collision with root package name */
    public d f8652b;

    public static a a() {
        if (f8650c == null) {
            f8650c = new a();
        }
        return f8650c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [cn.kuwo.autosdk.c] */
    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        cn.kuwo.autosdk.a aVar;
        int i7 = cn.kuwo.autosdk.b.f3105a;
        if (iBinder == null) {
            aVar = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("cn.kuwo.autosdk.IAutoSdkAidlInterface");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof cn.kuwo.autosdk.c)) {
                cn.kuwo.autosdk.a aVar2 = new cn.kuwo.autosdk.a();
                aVar2.f3098a = iBinder;
                aVar = aVar2;
            } else {
                aVar = (cn.kuwo.autosdk.c) queryLocalInterface;
            }
        }
        this.f8651a = aVar;
        d dVar = this.f8652b;
        if (dVar != null) {
            dVar.onConnectChangeListener(true);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f8651a = null;
        d dVar = this.f8652b;
        if (dVar != null) {
            dVar.onConnectChangeListener(false);
        }
    }
}
