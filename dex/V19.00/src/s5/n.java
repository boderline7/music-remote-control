package s5;

import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n {

    /* renamed from: a, reason: collision with root package name */
    public boolean f8837a = true;

    /* renamed from: b, reason: collision with root package name */
    public String[] f8838b;

    /* renamed from: c, reason: collision with root package name */
    public String[] f8839c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f8840d;

    public final p a() {
        return new p(this.f8837a, this.f8840d, this.f8838b, this.f8839c);
    }

    public final void b(String... strArr) {
        i5.a.g("cipherSuites", strArr);
        if (!this.f8837a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
        }
        if (!(!(strArr.length == 0))) {
            throw new IllegalArgumentException("At least one cipher suite is required".toString());
        }
        this.f8838b = (String[]) strArr.clone();
    }

    public final void c(m... mVarArr) {
        i5.a.g("cipherSuites", mVarArr);
        if (!this.f8837a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
        }
        ArrayList arrayList = new ArrayList(mVarArr.length);
        for (m mVar : mVarArr) {
            arrayList.add(mVar.f8816a);
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        b((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final void d() {
        if (!this.f8837a) {
            throw new IllegalArgumentException("no TLS extensions for cleartext connections".toString());
        }
        this.f8840d = true;
    }

    public final void e(String... strArr) {
        i5.a.g("tlsVersions", strArr);
        if (!this.f8837a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
        }
        if (!(!(strArr.length == 0))) {
            throw new IllegalArgumentException("At least one TLS version is required".toString());
        }
        this.f8839c = (String[]) strArr.clone();
    }

    public final void f(d1... d1VarArr) {
        if (!this.f8837a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
        }
        ArrayList arrayList = new ArrayList(d1VarArr.length);
        for (d1 d1Var : d1VarArr) {
            arrayList.add(d1Var.f8769a);
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        e((String[]) Arrays.copyOf(strArr, strArr.length));
    }
}
