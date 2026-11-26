package x5;

import java.io.IOException;
import okhttp3.internal.connection.RouteException;
import s5.j0;
import s5.o0;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements j0 {
    public static final a INSTANCE = new a();

    @Override // s5.j0
    public final x0 a(y5.h hVar) {
        j jVar = hVar.f10216a;
        jVar.getClass();
        synchronized (jVar) {
            try {
                if (!jVar.f9700o) {
                    throw new IllegalStateException("released".toString());
                }
                if (!(!jVar.f9699n)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                if (!(!jVar.f9698m)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        f fVar = jVar.f9694i;
        i5.a.d(fVar);
        o0 o0Var = jVar.f9686a;
        i5.a.g("client", o0Var);
        try {
            e eVar = new e(jVar, jVar.f9690e, fVar, fVar.a(hVar.f10221f, hVar.f10222g, hVar.f10223h, o0Var.f8847f, !i5.a.c((String) hVar.f10220e.f1483c, "GET")).j(o0Var, hVar));
            jVar.f9697l = eVar;
            jVar.f9702q = eVar;
            synchronized (jVar) {
                jVar.f9698m = true;
                jVar.f9699n = true;
            }
            if (jVar.f9701p) {
                throw new IOException("Canceled");
            }
            return y5.h.a(hVar, 0, eVar, null, 61).b(hVar.f10220e);
        } catch (IOException e7) {
            fVar.c(e7);
            throw new RouteException(e7);
        } catch (RouteException e8) {
            fVar.c(e8.f8067b);
            throw e8;
        }
    }
}
