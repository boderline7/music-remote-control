package x5;

import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final List f9647a;

    /* renamed from: b, reason: collision with root package name */
    public int f9648b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9649c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f9650d;

    public b(List list) {
        i5.a.g("connectionSpecs", list);
        this.f9647a = list;
    }

    public final s5.p a(SSLSocket sSLSocket) {
        s5.p pVar;
        int i7;
        boolean z6;
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        int i8 = this.f9648b;
        List list = this.f9647a;
        int size = list.size();
        while (true) {
            if (i8 >= size) {
                pVar = null;
                break;
            }
            pVar = (s5.p) list.get(i8);
            if (pVar.b(sSLSocket)) {
                this.f9648b = i8 + 1;
                break;
            }
            i8++;
        }
        if (pVar == null) {
            StringBuilder sb = new StringBuilder("Unable to find acceptable protocols. isFallback=");
            sb.append(this.f9650d);
            sb.append(", modes=");
            sb.append(list);
            sb.append(", supported protocols=");
            String[] enabledProtocols2 = sSLSocket.getEnabledProtocols();
            i5.a.d(enabledProtocols2);
            String arrays = Arrays.toString(enabledProtocols2);
            i5.a.f("toString(this)", arrays);
            sb.append(arrays);
            throw new UnknownServiceException(sb.toString());
        }
        int i9 = this.f9648b;
        int size2 = list.size();
        while (true) {
            i7 = 0;
            if (i9 >= size2) {
                z6 = false;
                break;
            }
            if (((s5.p) list.get(i9)).b(sSLSocket)) {
                z6 = true;
                break;
            }
            i9++;
        }
        this.f9649c = z6;
        boolean z7 = this.f9650d;
        String[] strArr = pVar.f8869c;
        if (strArr != null) {
            String[] enabledCipherSuites2 = sSLSocket.getEnabledCipherSuites();
            i5.a.f("sslSocket.enabledCipherSuites", enabledCipherSuites2);
            s5.m.Companion.getClass();
            enabledCipherSuites = t5.b.o(enabledCipherSuites2, strArr, s5.m.f8814b);
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        String[] strArr2 = pVar.f8870d;
        if (strArr2 != null) {
            String[] enabledProtocols3 = sSLSocket.getEnabledProtocols();
            i5.a.f("sslSocket.enabledProtocols", enabledProtocols3);
            d5.a aVar = d5.a.INSTANCE;
            i5.a.e("null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder> }", aVar);
            enabledProtocols = t5.b.o(enabledProtocols3, strArr2, aVar);
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        i5.a.f("supportedCipherSuites", supportedCipherSuites);
        s5.m.Companion.getClass();
        c0.g gVar = s5.m.f8814b;
        byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
        int length = supportedCipherSuites.length;
        while (true) {
            if (i7 >= length) {
                i7 = -1;
                break;
            }
            if (gVar.compare(supportedCipherSuites[i7], "TLS_FALLBACK_SCSV") == 0) {
                break;
            }
            i7++;
        }
        if (z7 && i7 != -1) {
            i5.a.f("cipherSuitesIntersection", enabledCipherSuites);
            String str = supportedCipherSuites[i7];
            i5.a.f("supportedCipherSuites[indexOfFallbackScsv]", str);
            Object[] copyOf = Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length + 1);
            i5.a.f("copyOf(this, newSize)", copyOf);
            enabledCipherSuites = (String[]) copyOf;
            enabledCipherSuites[enabledCipherSuites.length - 1] = str;
        }
        s5.n nVar = new s5.n();
        nVar.f8837a = pVar.f8867a;
        nVar.f8838b = strArr;
        nVar.f8839c = strArr2;
        nVar.f8840d = pVar.f8868b;
        i5.a.f("cipherSuitesIntersection", enabledCipherSuites);
        nVar.b((String[]) Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length));
        i5.a.f("tlsVersionsIntersection", enabledProtocols);
        nVar.e((String[]) Arrays.copyOf(enabledProtocols, enabledProtocols.length));
        s5.p a7 = nVar.a();
        if (a7.c() != null) {
            sSLSocket.setEnabledProtocols(a7.f8870d);
        }
        if (a7.a() != null) {
            sSLSocket.setEnabledCipherSuites(a7.f8869c);
        }
        return pVar;
    }
}
