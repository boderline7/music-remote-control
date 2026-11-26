package s5;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b1 {

    /* renamed from: a, reason: collision with root package name */
    public final a f8764a;

    /* renamed from: b, reason: collision with root package name */
    public final Proxy f8765b;

    /* renamed from: c, reason: collision with root package name */
    public final InetSocketAddress f8766c;

    public b1(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        i5.a.g("address", aVar);
        i5.a.g("socketAddress", inetSocketAddress);
        this.f8764a = aVar;
        this.f8765b = proxy;
        this.f8766c = inetSocketAddress;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof b1) {
            b1 b1Var = (b1) obj;
            if (i5.a.c(b1Var.f8764a, this.f8764a) && i5.a.c(b1Var.f8765b, this.f8765b) && i5.a.c(b1Var.f8766c, this.f8766c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f8766c.hashCode() + ((this.f8765b.hashCode() + ((this.f8764a.hashCode() + 527) * 31)) * 31);
    }

    public final String toString() {
        return "Route{" + this.f8766c + '}';
    }
}
