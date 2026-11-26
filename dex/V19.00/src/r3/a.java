package r3;

import android.os.Handler;
import android.os.Message;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Handler.Callback {
    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i7 = message.what;
        if (i7 == 0) {
            a.a.u(message.obj);
            throw null;
        }
        if (i7 != 1) {
            return false;
        }
        a.a.u(message.obj);
        throw null;
    }
}
