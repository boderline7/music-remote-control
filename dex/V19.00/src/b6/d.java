package b6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements f6.g {

    /* renamed from: a, reason: collision with root package name */
    public final X509TrustManager f2982a;

    /* renamed from: b, reason: collision with root package name */
    public final Method f2983b;

    public d(X509TrustManager x509TrustManager, Method method) {
        this.f2982a = x509TrustManager;
        this.f2983b = method;
    }

    @Override // f6.g
    public final X509Certificate a(X509Certificate x509Certificate) {
        try {
            Object invoke = this.f2983b.invoke(this.f2982a, x509Certificate);
            i5.a.e("null cannot be cast to non-null type java.security.cert.TrustAnchor", invoke);
            return ((TrustAnchor) invoke).getTrustedCert();
        } catch (IllegalAccessException e7) {
            throw new AssertionError("unable to get issues and signature", e7);
        } catch (InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return i5.a.c(this.f2982a, dVar.f2982a) && i5.a.c(this.f2983b, dVar.f2983b);
    }

    public final int hashCode() {
        return this.f2983b.hashCode() + (this.f2982a.hashCode() * 31);
    }

    public final String toString() {
        return "CustomTrustRootIndex(trustManager=" + this.f2982a + ", findByIssuerAndSignatureMethod=" + this.f2983b + ')';
    }
}
