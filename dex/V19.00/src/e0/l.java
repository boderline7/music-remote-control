package e0;

import android.os.Bundle;
import android.os.IBinder;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l {
    public static IBinder a(Bundle bundle, String str) {
        return bundle.getBinder(str);
    }

    public static void b(Bundle bundle, String str, IBinder iBinder) {
        bundle.putBinder(str, iBinder);
    }
}
