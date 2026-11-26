package g0;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class e {
    public static Intent a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i7) {
        if ((i7 & 4) == 0 || str != null) {
            return context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i7 & 1);
        }
        Object obj = g.f6423a;
        String str2 = context.getPackageName() + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
        if (j.a(context, str2) == 0) {
            return context.registerReceiver(broadcastReceiver, intentFilter, str2, handler);
        }
        throw new RuntimeException(a.a.h("Permission ", str2, " is required by your application to receive broadcasts, please add it to your manifest"));
    }

    public static ComponentName b(Context context, Intent intent) {
        return context.startForegroundService(intent);
    }
}
