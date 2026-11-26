package c6;

import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCSSLSocket;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j implements r {
    @Override // c6.r
    public final boolean a(SSLSocket sSLSocket) {
        b6.g.Companion.getClass();
        return b6.f.a() && (sSLSocket instanceof BCSSLSocket);
    }

    @Override // c6.r
    public final t b(SSLSocket sSLSocket) {
        return new l();
    }
}
