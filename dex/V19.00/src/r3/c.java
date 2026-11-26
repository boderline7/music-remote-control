package r3;

import android.os.Handler;
import android.os.Message;
import j.h;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements Handler.Callback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h f8516a;

    public c(h hVar) {
        this.f8516a = hVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        h hVar = this.f8516a;
        a.a.u(message.obj);
        synchronized (hVar.f6890a) {
            a.a.u(hVar.f6892c);
            throw null;
        }
    }
}
