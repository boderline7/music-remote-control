package c6;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q implements t {
    public static final p Companion = new p();

    /* renamed from: a, reason: collision with root package name */
    public static final o f3095a = new o();

    @Override // c6.t
    public final boolean a(SSLSocket sSLSocket) {
        return Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // c6.t
    public final String b(SSLSocket sSLSocket) {
        if (a(sSLSocket)) {
            return Conscrypt.getApplicationProtocol(sSLSocket);
        }
        return null;
    }

    @Override // c6.t
    public final boolean c() {
        b6.j.Companion.getClass();
        return b6.j.f2989d;
    }

    @Override // c6.t
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        if (a(sSLSocket)) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            b6.s.Companion.getClass();
            Conscrypt.setApplicationProtocols(sSLSocket, (String[]) b6.r.a(list).toArray(new String[0]));
        }
    }
}
