package b6;

import java.security.KeyStore;
import java.security.Provider;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.conscrypt.Conscrypt;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends s {
    public static final h Companion = new h();

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f2989d;

    /* renamed from: c, reason: collision with root package name */
    public final Provider f2990c;

    static {
        boolean z6 = false;
        try {
            Class.forName("org.conscrypt.Conscrypt$Version", false, h.class.getClassLoader());
            if (Conscrypt.isAvailable()) {
                if (h.a()) {
                    z6 = true;
                }
            }
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
        }
        f2989d = z6;
    }

    public j() {
        Provider newProvider = Conscrypt.newProvider();
        i5.a.f("newProvider()", newProvider);
        this.f2990c = newProvider;
    }

    @Override // b6.s
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        if (!Conscrypt.isConscrypt(sSLSocket)) {
            super.d(sSLSocket, str, list);
            return;
        }
        Conscrypt.setUseSessionTickets(sSLSocket, true);
        s.Companion.getClass();
        Conscrypt.setApplicationProtocols(sSLSocket, (String[]) r.a(list).toArray(new String[0]));
    }

    @Override // b6.s
    public final String f(SSLSocket sSLSocket) {
        if (Conscrypt.isConscrypt(sSLSocket)) {
            return Conscrypt.getApplicationProtocol(sSLSocket);
        }
        return null;
    }

    @Override // b6.s
    public final SSLContext k() {
        SSLContext sSLContext = SSLContext.getInstance("TLS", this.f2990c);
        i5.a.f("getInstance(\"TLS\", provider)", sSLContext);
        return sSLContext;
    }

    @Override // b6.s
    public final SSLSocketFactory l(X509TrustManager x509TrustManager) {
        SSLContext k7 = k();
        k7.init(null, new TrustManager[]{x509TrustManager}, null);
        SSLSocketFactory socketFactory = k7.getSocketFactory();
        i5.a.f("newSSLContext().apply {\n…null)\n    }.socketFactory", socketFactory);
        return socketFactory;
    }

    @Override // b6.s
    public final X509TrustManager m() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        i5.a.d(trustManagers);
        if (trustManagers.length == 1) {
            TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager) {
                i5.a.e("null cannot be cast to non-null type javax.net.ssl.X509TrustManager", trustManager);
                X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                Conscrypt.setHostnameVerifier(x509TrustManager, i.INSTANCE);
                return x509TrustManager;
            }
        }
        String arrays = Arrays.toString(trustManagers);
        i5.a.f("toString(this)", arrays);
        throw new IllegalStateException("Unexpected default trust managers: ".concat(arrays).toString());
    }
}
