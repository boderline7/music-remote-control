package s5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p {
    public static final p CLEARTEXT;
    public static final p COMPATIBLE_TLS;
    public static final o Companion = new o();
    public static final p MODERN_TLS;
    public static final p RESTRICTED_TLS;

    /* renamed from: a, reason: collision with root package name */
    public final boolean f8867a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f8868b;

    /* renamed from: c, reason: collision with root package name */
    public final String[] f8869c;

    /* renamed from: d, reason: collision with root package name */
    public final String[] f8870d;

    static {
        m mVar = m.TLS_AES_128_GCM_SHA256;
        m mVar2 = m.TLS_AES_256_GCM_SHA384;
        m mVar3 = m.TLS_CHACHA20_POLY1305_SHA256;
        m mVar4 = m.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
        m mVar5 = m.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
        m mVar6 = m.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;
        m mVar7 = m.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;
        m mVar8 = m.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256;
        m mVar9 = m.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256;
        m[] mVarArr = {mVar, mVar2, mVar3, mVar4, mVar5, mVar6, mVar7, mVar8, mVar9, m.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, m.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, m.TLS_RSA_WITH_AES_128_GCM_SHA256, m.TLS_RSA_WITH_AES_256_GCM_SHA384, m.TLS_RSA_WITH_AES_128_CBC_SHA, m.TLS_RSA_WITH_AES_256_CBC_SHA, m.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
        n nVar = new n();
        nVar.c((m[]) Arrays.copyOf(new m[]{mVar, mVar2, mVar3, mVar4, mVar5, mVar6, mVar7, mVar8, mVar9}, 9));
        d1 d1Var = d1.TLS_1_3;
        d1 d1Var2 = d1.TLS_1_2;
        nVar.f(d1Var, d1Var2);
        nVar.d();
        RESTRICTED_TLS = nVar.a();
        n nVar2 = new n();
        nVar2.c((m[]) Arrays.copyOf(mVarArr, 16));
        nVar2.f(d1Var, d1Var2);
        nVar2.d();
        MODERN_TLS = nVar2.a();
        n nVar3 = new n();
        nVar3.c((m[]) Arrays.copyOf(mVarArr, 16));
        nVar3.f(d1Var, d1Var2, d1.TLS_1_1, d1.TLS_1_0);
        nVar3.d();
        COMPATIBLE_TLS = nVar3.a();
        CLEARTEXT = new p(false, false, null, null);
    }

    public p(boolean z6, boolean z7, String[] strArr, String[] strArr2) {
        this.f8867a = z6;
        this.f8868b = z7;
        this.f8869c = strArr;
        this.f8870d = strArr2;
    }

    public final List a() {
        String[] strArr = this.f8869c;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(m.Companion.b(str));
        }
        return c5.p.I1(arrayList);
    }

    public final boolean b(SSLSocket sSLSocket) {
        if (!this.f8867a) {
            return false;
        }
        String[] strArr = this.f8870d;
        if (strArr != null) {
            String[] enabledProtocols = sSLSocket.getEnabledProtocols();
            d5.a aVar = d5.a.INSTANCE;
            i5.a.e("null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder> }", aVar);
            if (!t5.b.i(strArr, enabledProtocols, aVar)) {
                return false;
            }
        }
        String[] strArr2 = this.f8869c;
        if (strArr2 == null) {
            return true;
        }
        String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        m.Companion.getClass();
        return t5.b.i(strArr2, enabledCipherSuites, m.f8814b);
    }

    public final List c() {
        String[] strArr = this.f8870d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            d1.Companion.getClass();
            arrayList.add(c1.a(str));
        }
        return c5.p.I1(arrayList);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        p pVar = (p) obj;
        boolean z6 = pVar.f8867a;
        boolean z7 = this.f8867a;
        if (z7 != z6) {
            return false;
        }
        return !z7 || (Arrays.equals(this.f8869c, pVar.f8869c) && Arrays.equals(this.f8870d, pVar.f8870d) && this.f8868b == pVar.f8868b);
    }

    public final int hashCode() {
        if (!this.f8867a) {
            return 17;
        }
        String[] strArr = this.f8869c;
        int hashCode = (527 + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String[] strArr2 = this.f8870d;
        return ((hashCode + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31) + (!this.f8868b ? 1 : 0);
    }

    public final String toString() {
        if (!this.f8867a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + Objects.toString(a(), "[all enabled]") + ", tlsVersions=" + Objects.toString(c(), "[all enabled]") + ", supportsTlsExtensions=" + this.f8868b + ')';
    }
}
