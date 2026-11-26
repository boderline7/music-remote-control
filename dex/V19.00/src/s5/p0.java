package s5;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p0 {
    public static q0 a(String str) {
        q0 q0Var = q0.HTTP_1_0;
        if (!i5.a.c(str, q0Var.f8872a)) {
            q0Var = q0.HTTP_1_1;
            if (!i5.a.c(str, q0Var.f8872a)) {
                q0Var = q0.H2_PRIOR_KNOWLEDGE;
                if (!i5.a.c(str, q0Var.f8872a)) {
                    q0Var = q0.HTTP_2;
                    if (!i5.a.c(str, q0Var.f8872a)) {
                        q0Var = q0.SPDY_3;
                        if (!i5.a.c(str, q0Var.f8872a)) {
                            q0Var = q0.QUIC;
                            if (!i5.a.c(str, q0Var.f8872a)) {
                                throw new IOException("Unexpected protocol: ".concat(str));
                            }
                        }
                    }
                }
            }
        }
        return q0Var;
    }
}
