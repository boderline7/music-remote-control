package u5;

import a6.k0;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.List;
import java.util.NoSuchElementException;
import s5.c;
import s5.h0;
import s5.v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements c {

    /* renamed from: a, reason: collision with root package name */
    public final v f9314a;

    public b() {
        v vVar = v.SYSTEM;
        i5.a.g("defaultDns", vVar);
        this.f9314a = vVar;
    }

    public static InetAddress b(Proxy proxy, h0 h0Var, v vVar) {
        Proxy.Type type = proxy.type();
        if (type == null || a.$EnumSwitchMapping$0[type.ordinal()] != 1) {
            SocketAddress address = proxy.address();
            i5.a.e("null cannot be cast to non-null type java.net.InetSocketAddress", address);
            InetAddress address2 = ((InetSocketAddress) address).getAddress();
            i5.a.f("address() as InetSocketAddress).address", address2);
            return address2;
        }
        List d7 = ((k0) vVar).d(h0Var.f8795d);
        i5.a.g("<this>", d7);
        if (d7.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return (InetAddress) d7.get(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x006d  */
    @Override // s5.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.appcompat.widget.y a(s5.b1 r21, s5.x0 r22) {
        /*
            Method dump skipped, instructions count: 424
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u5.b.a(s5.b1, s5.x0):androidx.appcompat.widget.y");
    }
}
