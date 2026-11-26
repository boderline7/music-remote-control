package androidx.core.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import e0.o;
import e0.q;
import java.util.HashMap;

@Deprecated
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class JobIntentService extends Service {

    /* renamed from: a, reason: collision with root package name */
    public q f1739a;

    /* renamed from: b, reason: collision with root package name */
    public o f1740b;

    static {
        new HashMap();
    }

    public abstract void a();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        q qVar = this.f1739a;
        if (qVar != null) {
            return qVar.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.f1739a = new q(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        return 2;
    }
}
