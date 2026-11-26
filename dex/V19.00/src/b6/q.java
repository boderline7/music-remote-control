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
import org.openjsse.javax.net.ssl.SSLParameters;
import org.openjsse.net.ssl.OpenJSSE;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q extends s {
    public static final p Companion;

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f3000d;

    /* renamed from: c, reason: collision with root package name */
    public final Provider f3001c = new OpenJSSE();

    static {
        p pVar = new p();
        Companion = pVar;
        boolean z6 = false;
        try {
            Class.forName("org.openjsse.net.ssl.OpenJSSE", false, pVar.getClass().getClassLoader());
            z6 = true;
        } catch (ClassNotFoundException unused) {
        }
        f3000d = z6;
    }

    @Override // b6.s
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        if (!(sSLSocket instanceof org.openjsse.javax.net.ssl.SSLSocket)) {
            super.d(sSLSocket, str, list);
            return;
        }
        org.openjsse.javax.net.ssl.SSLSocket sSLSocket2 = (org.openjsse.javax.net.ssl.SSLSocket) sSLSocket;
        SSLParameters sSLParameters = sSLSocket2.getSSLParameters();
        if (sSLParameters instanceof SSLParameters) {
            s.Companion.getClass();
            sSLParameters.setApplicationProtocols((String[]) r.a(list).toArray(new String[0]));
            sSLSocket2.setSSLParameters(sSLParameters);
        }
    }

    @Override // b6.s
    public final String f(SSLSocket sSLSocket) {
        String applicationProtocol;
        if (!(sSLSocket instanceof org.openjsse.javax.net.ssl.SSLSocket) || (applicationProtocol = ((org.openjsse.javax.net.ssl.SSLSocket) sSLSocket).getApplicationProtocol()) == null || i5.a.c(applicationProtocol, h0.FRAGMENT_ENCODE_SET)) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // b6.s
    public final SSLContext k() {
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.3", this.f3001c);
        i5.a.f("getInstance(\"TLSv1.3\", provider)", sSLContext);
        return sSLContext;
    }

    @Override // b6.s
    public final X509TrustManager m() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm(), this.f3001c);
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
