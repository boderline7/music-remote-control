package androidx.lifecycle;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class z0 extends i5.h implements h5.a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2217a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2218b;

    public z0(int i7, Object obj) {
        this.f2217a = i7;
        this.f2218b = obj;
    }

    @Override // h5.a
    public final Object a() {
        switch (this.f2217a) {
        }
        return c();
    }

    public final List c() {
        int i7 = this.f2217a;
        Object obj = this.f2218b;
        switch (i7) {
            case 1:
                return (List) obj;
            case 2:
                try {
                    return (List) ((h5.a) obj).a();
                } catch (SSLPeerUnverifiedException unused) {
                    return c5.r.INSTANCE;
                }
            default:
                s5.a0 a0Var = ((x5.m) obj).f9707d;
                i5.a.d(a0Var);
                List<Certificate> a7 = a0Var.a();
                ArrayList arrayList = new ArrayList(c5.l.x1(a7));
                for (Certificate certificate : a7) {
                    i5.a.e("null cannot be cast to non-null type java.security.cert.X509Certificate", certificate);
                    arrayList.add((X509Certificate) certificate);
                }
                return arrayList;
        }
    }
}
