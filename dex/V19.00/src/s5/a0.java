package s5;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a0 {
    public static final z Companion = new z();

    /* renamed from: a, reason: collision with root package name */
    public final d1 f8758a;

    /* renamed from: b, reason: collision with root package name */
    public final m f8759b;

    /* renamed from: c, reason: collision with root package name */
    public final List f8760c;

    /* renamed from: d, reason: collision with root package name */
    public final b5.b f8761d;

    public a0(d1 d1Var, m mVar, List list, h5.a aVar) {
        i5.a.g("tlsVersion", d1Var);
        i5.a.g("cipherSuite", mVar);
        i5.a.g("localCertificates", list);
        this.f8758a = d1Var;
        this.f8759b = mVar;
        this.f8760c = list;
        this.f8761d = new b5.b(new androidx.lifecycle.z0(2, aVar));
    }

    public final List a() {
        return (List) this.f8761d.a();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a0) {
            a0 a0Var = (a0) obj;
            if (a0Var.f8758a == this.f8758a && i5.a.c(a0Var.f8759b, this.f8759b) && i5.a.c(a0Var.a(), a()) && i5.a.c(a0Var.f8760c, this.f8760c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f8760c.hashCode() + ((a().hashCode() + ((this.f8759b.hashCode() + ((this.f8758a.hashCode() + 527) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String type;
        String type2;
        List<Certificate> a7 = a();
        ArrayList arrayList = new ArrayList(c5.l.x1(a7));
        for (Certificate certificate : a7) {
            if (certificate instanceof X509Certificate) {
                type2 = ((X509Certificate) certificate).getSubjectDN().toString();
            } else {
                type2 = certificate.getType();
                i5.a.f("type", type2);
            }
            arrayList.add(type2);
        }
        String obj = arrayList.toString();
        StringBuilder sb = new StringBuilder("Handshake{tlsVersion=");
        sb.append(this.f8758a);
        sb.append(" cipherSuite=");
        sb.append(this.f8759b);
        sb.append(" peerCertificates=");
        sb.append(obj);
        sb.append(" localCertificates=");
        List<Certificate> list = this.f8760c;
        ArrayList arrayList2 = new ArrayList(c5.l.x1(list));
        for (Certificate certificate2 : list) {
            if (certificate2 instanceof X509Certificate) {
                type = ((X509Certificate) certificate2).getSubjectDN().toString();
            } else {
                type = certificate2.getType();
                i5.a.f("type", type);
            }
            arrayList2.add(type);
        }
        sb.append(arrayList2);
        sb.append('}');
        return sb.toString();
    }
}
