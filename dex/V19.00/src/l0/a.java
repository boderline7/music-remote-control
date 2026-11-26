package l0;

import android.os.Bundle;
import android.os.IBinder;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {
    public static final a INSTANCE = new a();

    public static final void a(Bundle bundle, String str, IBinder iBinder) {
        i5.a.g("bundle", bundle);
        i5.a.g("key", str);
        bundle.putBinder(str, iBinder);
    }
}
