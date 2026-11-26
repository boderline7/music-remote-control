package c6;

import java.util.List;
import javax.net.ssl.SSLSocket;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s implements t {

    /* renamed from: a, reason: collision with root package name */
    public final r f3096a;

    /* renamed from: b, reason: collision with root package name */
    public t f3097b;

    public s(r rVar) {
        this.f3096a = rVar;
    }

    @Override // c6.t
    public final boolean a(SSLSocket sSLSocket) {
        return this.f3096a.a(sSLSocket);
    }

    @Override // c6.t
    public final String b(SSLSocket sSLSocket) {
        t e7 = e(sSLSocket);
        if (e7 != null) {
            return e7.b(sSLSocket);
        }
        return null;
    }

    @Override // c6.t
    public final boolean c() {
        return true;
    }

    @Override // c6.t
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        t e7 = e(sSLSocket);
        if (e7 != null) {
            e7.d(sSLSocket, str, list);
        }
    }

    public final synchronized t e(SSLSocket sSLSocket) {
        try {
            if (this.f3097b == null && this.f3096a.a(sSLSocket)) {
                this.f3097b = this.f3096a.b(sSLSocket);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f3097b;
    }
}
