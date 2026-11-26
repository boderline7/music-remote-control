package b6;

import java.security.KeyStore;
import java.security.Provider;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends s {
    public static final f Companion;

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f2987d;

    /* renamed from: c, reason: collision with root package name */
    public final Provider f2988c = new BouncyCastleJsseProvider();

    static {
        f fVar = new f();
        Companion = fVar;
        boolean z6 = false;
        try {
            Class.forName("org.bouncycastle.jsse.provider.BouncyCastleJsseProvider", false, fVar.getClass().getClassLoader());
            z6 = true;
        } catch (ClassNotFoundException unused) {
        }
        f2987d = z6;
    }

    @Override // b6.s
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        if (!(sSLSocket instanceof BCSSLSocket)) {
            super.d(sSLSocket, str, list);
            return;
        }
        BCSSLSocket bCSSLSocket = (BCSSLSocket) sSLSocket;
        BCSSLParameters parameters = bCSSLSocket.getParameters();
        s.Companion.getClass();
        parameters.setApplicationProtocols((String[]) r.a(list).toArray(new String[0]));
        bCSSLSocket.setParameters(parameters);
    }

    @Override // b6.s
    public final String f(SSLSocket sSLSocket) {
        String applicationProtocol;
        if (!(sSLSocket instanceof BCSSLSocket) || (applicationProtocol = ((BCSSLSocket) sSLSocket).getApplicationProtocol()) == null || i5.a.c(applicationProtocol, h0.FRAGMENT_ENCODE_SET)) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // b6.s
    public final SSLContext k() {
        SSLContext sSLContext = SSLContext.getInstance("TLS", this.f2988c);
        i5.a.f("getInstance(\"TLS\", provider)", sSLContext);
        return sSLContext;
    }

    @Override // b6.s
    public final X509TrustManager m() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX", "BCJSSE");
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        i5.a.d(trustManagers);
        if (trustManagers.length == 1) {
            TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager) {
                i5.a.e("null cannot be cast to non-null type javax.net.ssl.X509TrustManager", trustManager);
                return (X509TrustManager) trustManager;
            }
        }
        String arrays = Arrays.toString(trustManagers);
        i5.a.f("toString(this)", arrays);
        throw new IllegalStateException("Unexpected default trust managers: ".concat(arrays).toString());
    }
}
