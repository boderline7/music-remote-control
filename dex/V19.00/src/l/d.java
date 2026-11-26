package l;

import android.os.Handler;
import android.os.Looper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d {
    public static Handler a(Looper looper) {
        return Handler.createAsync(looper);
    }
}
