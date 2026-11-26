package androidx.core.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import e0.y;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class NotificationCompatSideChannelService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        intent.getAction().equals(y.ACTION_BIND_SIDE_CHANNEL);
        return null;
    }
}
