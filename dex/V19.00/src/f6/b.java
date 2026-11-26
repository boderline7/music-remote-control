package f6;

import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends e {
    public static final a Companion = new a();

    /* renamed from: a, reason: collision with root package name */
    public final g f6404a;

    public b(g gVar) {
        i5.a.g("trustRootIndex", gVar);
        this.f6404a = gVar;
    }

    @Override // f6.e
    public final List a(List list, String str) {
        i5.a.g("chain", list);
        i5.a.g("hostname", str);
        ArrayDeque arrayDeque = new ArrayDeque(list);
        ArrayList arrayList = new ArrayList();
        Object removeFirst = arrayDeque.removeFirst();
        i5.a.f("queue.removeFirst()", removeFirst);
        arrayList.add(removeFirst);
        boolean z6 = false;
        for (int i7 = 0; i7 < 9; i7++) {
            Object obj = arrayList.get(arrayList.size() - 1);
            i5.a.e("null cannot be cast to non-null type java.security.cert.X509Certificate", obj);
            X509Certificate x509Certificate = (X509Certificate) obj;
            X509Certificate a7 = this.f6404a.a(x509Certificate);
            if (a7 == null) {
                Iterator it = arrayDeque.iterator();
                i5.a.f("queue.iterator()", it);
                while (it.hasNext()) {
                    Object next = it.next();
                    i5.a.e("null cannot be cast to non-null type java.security.cert.X509Certificate", next);
                    X509Certificate x509Certificate2 = (X509Certificate) next;
                    if (i5.a.c(x509Certificate.getIssuerDN(), x509Certificate2.getSubjectDN())) {
                        try {
                            x509Certificate.verify(x509Certificate2.getPublicKey());
                            it.remove();
                            arrayList.add(x509Certificate2);
                        } catch (GeneralSecurityException unused) {
                            continue;
                        }
                    }
                }
                if (z6) {
                    return arrayList;
                }
                throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + x509Certificate);
            }
            if (arrayList.size() > 1 || !i5.a.c(x509Certificate, a7)) {
                arrayList.add(a7);
            }
            if (i5.a.c(a7.getIssuerDN(), a7.getSubjectDN())) {
                try {
                    a7.verify(a7.getPublicKey());
                    return arrayList;
                } catch (GeneralSecurityException unused2) {
                }
            }
            z6 = true;
        }
        throw new SSLPeerUnverifiedException("Certificate chain too long: " + arrayList);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof b) && i5.a.c(((b) obj).f6404a, this.f6404a);
    }

    public final int hashCode() {
        return this.f6404a.hashCode();
    }
}
