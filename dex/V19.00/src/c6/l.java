package c6;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l implements t {
    public static final k Companion = new k();

    /* renamed from: a, reason: collision with root package name */
    public static final j f3091a = new j();

    @Override // c6.t
    public final boolean a(SSLSocket sSLSocket) {
        return sSLSocket instanceof BCSSLSocket;
    }

    @Override // c6.t
    public final String b(SSLSocket sSLSocket) {
        String applicationProtocol = ((BCSSLSocket) sSLSocket).getApplicationProtocol();
        if (applicationProtocol == null || i5.a.c(applicationProtocol, h0.FRAGMENT_ENCODE_SET)) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // c6.t
    public final boolean c() {
        b6.g.Companion.getClass();
        return b6.g.f2987d;
    }

    @Override // c6.t
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        if (a(sSLSocket)) {
            BCSSLSocket bCSSLSocket = (BCSSLSocket) sSLSocket;
            BCSSLParameters parameters = bCSSLSocket.getParameters();
            b6.s.Companion.getClass();
            parameters.setApplicationProtocols((String[]) b6.r.a(list).toArray(new String[0]));
            bCSSLSocket.setParameters(parameters);
        }
    }
}
