package a6;

import java.io.IOException;
import java.net.SocketTimeoutException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f0 extends g6.f {

    /* renamed from: l, reason: collision with root package name */
    public final /* synthetic */ g0 f408l;

    public f0(g0 g0Var) {
        this.f408l = g0Var;
    }

    @Override // g6.f
    public final IOException j(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // g6.f
    public final void k() {
        this.f408l.e(b.CANCEL);
        w wVar = this.f408l.f412b;
        synchronized (wVar) {
            long j7 = wVar.f492p;
            long j8 = wVar.f491o;
            if (j7 < j8) {
                return;
            }
            wVar.f491o = j8 + 1;
            wVar.f493q = System.nanoTime() + w.DEGRADED_PONG_TIMEOUT_NS;
            wVar.f485i.c(new w5.b(2, wVar, a.a.i(new StringBuilder(), wVar.f480d, " ping")), 0L);
        }
    }

    public final void l() {
        if (i()) {
            throw j(null);
        }
    }
}
