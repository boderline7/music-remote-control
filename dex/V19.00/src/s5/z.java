package s5;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Arrays;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class z {
    public static a0 a(SSLSession sSLSession) {
        Object obj;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null".toString());
        }
        if (i5.a.c(cipherSuite, "TLS_NULL_WITH_NULL_NULL") || i5.a.c(cipherSuite, "SSL_NULL_WITH_NULL_NULL")) {
            throw new IOException("cipherSuite == ".concat(cipherSuite));
        }
        m b7 = m.Companion.b(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null".toString());
        }
        if (i5.a.c("NONE", protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        d1.Companion.getClass();
        d1 a7 = c1.a(protocol);
        try {
            Certificate[] peerCertificates = sSLSession.getPeerCertificates();
            obj = peerCertificates != null ? t5.b.k(Arrays.copyOf(peerCertificates, peerCertificates.length)) : c5.r.INSTANCE;
        } catch (SSLPeerUnverifiedException unused) {
            obj = c5.r.INSTANCE;
        }
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new a0(a7, b7, localCertificates != null ? t5.b.k(Arrays.copyOf(localCertificates, localCertificates.length)) : c5.r.INSTANCE, new androidx.lifecycle.z0(1, obj));
    }
}
