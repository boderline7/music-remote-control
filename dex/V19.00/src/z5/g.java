package z5;

import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends c {

    /* renamed from: d, reason: collision with root package name */
    public long f10308d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ j f10309e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(j jVar, long j7) {
        super(jVar);
        this.f10309e = jVar;
        this.f10308d = j7;
        if (j7 == 0) {
            A();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f10299b) {
            return;
        }
        if (this.f10308d != 0 && !t5.b.g(this, TimeUnit.MILLISECONDS)) {
            this.f10309e.f10315b.k();
            A();
        }
        this.f10299b = true;
    }

    @Override // z5.c, g6.a0
    public final long k(g6.h hVar, long j7) {
        i5.a.g("sink", hVar);
        if (j7 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (!(!this.f10299b)) {
            throw new IllegalStateException("closed".toString());
        }
        long j8 = this.f10308d;
        if (j8 == 0) {
            return -1L;
        }
        long k7 = super.k(hVar, Math.min(j8, j7));
        if (k7 == -1) {
            this.f10309e.f10315b.k();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            A();
            throw protocolException;
        }
        long j9 = this.f10308d - k7;
        this.f10308d = j9;
        if (j9 == 0) {
            A();
        }
        return k7;
    }
}
