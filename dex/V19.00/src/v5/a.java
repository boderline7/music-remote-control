package v5;

import o5.k;
import s5.w0;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {
    public static final x0 a(a aVar, x0 x0Var) {
        aVar.getClass();
        if ((x0Var != null ? x0Var.f8919g : null) == null) {
            return x0Var;
        }
        w0 C = x0Var.C();
        C.f8906g = null;
        return C.a();
    }

    public static boolean b(String str) {
        return (k.C("Connection", str) || k.C("Keep-Alive", str) || k.C("Proxy-Authenticate", str) || k.C("Proxy-Authorization", str) || k.C("TE", str) || k.C("Trailers", str) || k.C("Transfer-Encoding", str) || k.C("Upgrade", str)) ? false : true;
    }
}
