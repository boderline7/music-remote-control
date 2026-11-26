package b6;

import com.navi.tool.service.ConfigBackupService;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class s {
    public static final r Companion = new r();
    public static final int INFO = 4;
    public static final int WARN = 5;

    /* renamed from: a, reason: collision with root package name */
    public static volatile s f3002a;

    /* renamed from: b, reason: collision with root package name */
    public static final Logger f3003b;

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
    
        if (r0 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00da, code lost:
    
        if (r0 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0100, code lost:
    
        if (r0 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0132, code lost:
    
        if (java.lang.Integer.parseInt(r3) >= 9) goto L65;
     */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0193  */
    static {
        /*
            Method dump skipped, instructions count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b6.s.<clinit>():void");
    }

    public static void i(String str, int i7, Throwable th) {
        i5.a.g(ConfigBackupService.EXTRA_MESSAGE, str);
        f3003b.log(i7 == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public void a(SSLSocket sSLSocket) {
    }

    public f6.e b(X509TrustManager x509TrustManager) {
        return new f6.b(c(x509TrustManager));
    }

    public f6.g c(X509TrustManager x509TrustManager) {
        X509Certificate[] acceptedIssuers = x509TrustManager.getAcceptedIssuers();
        i5.a.f("trustManager.acceptedIssuers", acceptedIssuers);
        return new f6.c((X509Certificate[]) Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
    }

    public void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
    }

    public void e(Socket socket, InetSocketAddress inetSocketAddress, int i7) {
        i5.a.g("address", inetSocketAddress);
        socket.connect(inetSocketAddress, i7);
    }

    public String f(SSLSocket sSLSocket) {
        return null;
    }

    public Object g() {
        if (f3003b.isLoggable(Level.FINE)) {
            return new Throwable("response.body().close()");
        }
        return null;
    }

    public boolean h(String str) {
        i5.a.g("hostname", str);
        return true;
    }

    public void j(String str, Object obj) {
        i5.a.g(ConfigBackupService.EXTRA_MESSAGE, str);
        if (obj == null) {
            str = str.concat(" To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
        }
        i(str, 5, (Throwable) obj);
    }

    public SSLContext k() {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        i5.a.f("getInstance(\"TLS\")", sSLContext);
        return sSLContext;
    }

    public SSLSocketFactory l(X509TrustManager x509TrustManager) {
        try {
            SSLContext k7 = k();
            k7.init(null, new TrustManager[]{x509TrustManager}, null);
            SSLSocketFactory socketFactory = k7.getSocketFactory();
            i5.a.f("newSSLContext().apply {\n…ll)\n      }.socketFactory", socketFactory);
            return socketFactory;
        } catch (GeneralSecurityException e7) {
            throw new AssertionError("No System TLS: " + e7, e7);
        }
    }

    public X509TrustManager m() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
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

    public final String toString() {
        return getClass().getSimpleName();
    }
}
