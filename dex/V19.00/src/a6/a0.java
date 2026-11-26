package a6;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a0 implements g6.a0 {

    /* renamed from: a, reason: collision with root package name */
    public final g6.j f365a;

    /* renamed from: b, reason: collision with root package name */
    public int f366b;

    /* renamed from: c, reason: collision with root package name */
    public int f367c;

    /* renamed from: d, reason: collision with root package name */
    public int f368d;

    /* renamed from: e, reason: collision with root package name */
    public int f369e;

    /* renamed from: f, reason: collision with root package name */
    public int f370f;

    public a0(g6.j jVar) {
        this.f365a = jVar;
    }

    @Override // g6.a0
    public final g6.d0 a() {
        return this.f365a.a();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // g6.a0
    public final long k(g6.h hVar, long j7) {
        int i7;
        int n6;
        i5.a.g("sink", hVar);
        do {
            int i8 = this.f369e;
            g6.j jVar = this.f365a;
            if (i8 != 0) {
                long k7 = jVar.k(hVar, Math.min(j7, i8));
                if (k7 == -1) {
                    return -1L;
                }
                this.f369e -= (int) k7;
                return k7;
            }
            jVar.h(this.f370f);
            this.f370f = 0;
            if ((this.f367c & 4) != 0) {
                return -1L;
            }
            i7 = this.f368d;
            int s6 = t5.b.s(jVar);
            this.f369e = s6;
            this.f366b = s6;
            int y6 = jVar.y() & 255;
            this.f367c = jVar.y() & 255;
            b0.Companion.getClass();
            Logger logger = b0.f373e;
            if (logger.isLoggable(Level.FINE)) {
                h hVar2 = h.INSTANCE;
                int i9 = this.f368d;
                int i10 = this.f366b;
                int i11 = this.f367c;
                hVar2.getClass();
                logger.fine(h.a(i9, i10, y6, i11, true));
            }
            n6 = jVar.n() & androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
            this.f368d = n6;
            if (y6 != 9) {
                throw new IOException(y6 + " != TYPE_CONTINUATION");
            }
        } while (n6 == i7);
        throw new IOException("TYPE_CONTINUATION streamId changed");
    }
}
