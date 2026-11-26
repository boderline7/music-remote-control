package s5;

import java.io.Closeable;
import java.nio.charset.Charset;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a1 implements Closeable {
    public static final z0 Companion = new z0();

    public final String A() {
        l0 l0Var;
        Charset charset;
        g6.j jVar = ((y0) this).f8928c;
        try {
            y0 y0Var = (y0) this;
            int i7 = y0Var.f8926a;
            Object obj = y0Var.f8929d;
            switch (i7) {
                case 0:
                    l0Var = (l0) obj;
                    break;
                default:
                    String str = (String) obj;
                    if (str == null) {
                        l0Var = null;
                        break;
                    } else {
                        l0.Companion.getClass();
                        l0Var = k0.b(str);
                        break;
                    }
            }
            if (l0Var == null || (charset = l0Var.a(o5.a.UTF_8)) == null) {
                charset = o5.a.UTF_8;
            }
            String w6 = jVar.w(t5.b.r(jVar, charset));
            n1.z.A(jVar, null);
            return w6;
        } finally {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        t5.b.c(((y0) this).f8928c);
    }
}
