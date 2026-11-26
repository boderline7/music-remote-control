package z5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends c {

    /* renamed from: d, reason: collision with root package name */
    public boolean f10313d;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f10299b) {
            return;
        }
        if (!this.f10313d) {
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
        if (this.f10313d) {
            return -1L;
        }
        long k7 = super.k(hVar, j7);
        if (k7 != -1) {
            return k7;
        }
        this.f10313d = true;
        A();
        return -1L;
    }
}
