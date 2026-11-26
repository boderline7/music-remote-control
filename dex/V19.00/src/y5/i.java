package y5;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i {
    public static final i INSTANCE = new i();

    public static String a(h0 h0Var) {
        i5.a.g("url", h0Var);
        String b7 = h0Var.b();
        String d7 = h0Var.d();
        if (d7 == null) {
            return b7;
        }
        return b7 + '?' + d7;
    }
}
