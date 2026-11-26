package c6;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends f6.e {
    public static final c Companion = new c();

    /* renamed from: a, reason: collision with root package name */
    public final X509TrustManager f3080a;

    /* renamed from: b, reason: collision with root package name */
    public final X509TrustManagerExtensions f3081b;

    public d(X509TrustManager x509TrustManager, X509TrustManagerExtensions x509TrustManagerExtensions) {
        this.f3080a = x509TrustManager;
        this.f3081b = x509TrustManagerExtensions;
    }

    @Override // f6.e
    public final List a(List list, String str) {
        i5.a.g("chain", list);
        i5.a.g("hostname", str);
        try {
            List<X509Certificate> checkServerTrusted = this.f3081b.checkServerTrusted((X509Certificate[]) list.toArray(new X509Certificate[0]), "RSA", str);
            i5.a.f("x509TrustManagerExtensio…ficates, \"RSA\", hostname)", checkServerTrusted);
            return checkServerTrusted;
        } catch (CertificateException e7) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e7.getMessage());
            sSLPeerUnverifiedException.initCause(e7);
            throw sSLPeerUnverifiedException;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof d) && ((d) obj).f3080a == this.f3080a;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f3080a);
    }
}
