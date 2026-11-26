package c6;

import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o implements r {
    @Override // c6.r
    public final boolean a(SSLSocket sSLSocket) {
        b6.j.Companion.getClass();
        return b6.h.b() && Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // c6.r
    public final t b(SSLSocket sSLSocket) {
        return new q();
    }
}
