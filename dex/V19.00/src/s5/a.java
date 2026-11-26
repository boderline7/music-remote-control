package s5;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public final v f8747a;

    /* renamed from: b, reason: collision with root package name */
    public final SocketFactory f8748b;

    /* renamed from: c, reason: collision with root package name */
    public final SSLSocketFactory f8749c;

    /* renamed from: d, reason: collision with root package name */
    public final HostnameVerifier f8750d;

    /* renamed from: e, reason: collision with root package name */
    public final j f8751e;

    /* renamed from: f, reason: collision with root package name */
    public final c f8752f;

    /* renamed from: g, reason: collision with root package name */
    public final Proxy f8753g;

    /* renamed from: h, reason: collision with root package name */
    public final ProxySelector f8754h;

    /* renamed from: i, reason: collision with root package name */
    public final h0 f8755i;

    /* renamed from: j, reason: collision with root package name */
    public final List f8756j;

    /* renamed from: k, reason: collision with root package name */
    public final List f8757k;

    public a(String str, int i7, v vVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, f6.f fVar, j jVar, c cVar, List list, List list2, ProxySelector proxySelector) {
        i5.a.g("uriHost", str);
        i5.a.g("dns", vVar);
        i5.a.g("socketFactory", socketFactory);
        i5.a.g("proxyAuthenticator", cVar);
        i5.a.g("protocols", list);
        i5.a.g("connectionSpecs", list2);
        i5.a.g("proxySelector", proxySelector);
        this.f8747a = vVar;
        this.f8748b = socketFactory;
        this.f8749c = sSLSocketFactory;
        this.f8750d = fVar;
        this.f8751e = jVar;
        this.f8752f = cVar;
        this.f8753g = null;
        this.f8754h = proxySelector;
        f0 f0Var = new f0();
        String str2 = sSLSocketFactory != null ? "https" : "http";
        if (o5.k.C(str2, "http")) {
            f0Var.f8783a = "http";
        } else {
            if (!o5.k.C(str2, "https")) {
                throw new IllegalArgumentException("unexpected scheme: ".concat(str2));
            }
            f0Var.f8783a = "https";
        }
        String w6 = i5.a.w(g0.d(h0.Companion, str, 0, 0, false, 7));
        if (w6 == null) {
            throw new IllegalArgumentException("unexpected host: ".concat(str));
        }
        f0Var.f8786d = w6;
        if (1 > i7 || i7 >= 65536) {
            throw new IllegalArgumentException(a.a.d("unexpected port: ", i7).toString());
        }
        f0Var.f8787e = i7;
        this.f8755i = f0Var.a();
        this.f8756j = t5.b.x(list);
        this.f8757k = t5.b.x(list2);
    }

    public final boolean a(a aVar) {
        i5.a.g("that", aVar);
        return i5.a.c(this.f8747a, aVar.f8747a) && i5.a.c(this.f8752f, aVar.f8752f) && i5.a.c(this.f8756j, aVar.f8756j) && i5.a.c(this.f8757k, aVar.f8757k) && i5.a.c(this.f8754h, aVar.f8754h) && i5.a.c(this.f8753g, aVar.f8753g) && i5.a.c(this.f8749c, aVar.f8749c) && i5.a.c(this.f8750d, aVar.f8750d) && i5.a.c(this.f8751e, aVar.f8751e) && this.f8755i.f8796e == aVar.f8755i.f8796e;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (i5.a.c(this.f8755i, aVar.f8755i) && a(aVar)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f8751e) + ((Objects.hashCode(this.f8750d) + ((Objects.hashCode(this.f8749c) + ((Objects.hashCode(this.f8753g) + ((this.f8754h.hashCode() + ((this.f8757k.hashCode() + ((this.f8756j.hashCode() + ((this.f8752f.hashCode() + ((this.f8747a.hashCode() + ((this.f8755i.f8799h.hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("Address{");
        h0 h0Var = this.f8755i;
        sb.append(h0Var.f8795d);
        sb.append(':');
        sb.append(h0Var.f8796e);
        sb.append(", ");
        Proxy proxy = this.f8753g;
        if (proxy != null) {
            str = "proxy=" + proxy;
        } else {
            str = "proxySelector=" + this.f8754h;
        }
        sb.append(str);
        sb.append('}');
        return sb.toString();
    }
}
