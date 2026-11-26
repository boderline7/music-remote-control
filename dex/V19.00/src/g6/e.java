package g6;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e implements a0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6559a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final Object f6560b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f6561c;

    public e(z zVar, e eVar) {
        this.f6560b = zVar;
        this.f6561c = eVar;
    }

    public e(InputStream inputStream, d0 d0Var) {
        i5.a.g("timeout", d0Var);
        this.f6560b = inputStream;
        this.f6561c = d0Var;
    }

    @Override // g6.a0
    public final d0 a() {
        switch (this.f6559a) {
            case 0:
                return (f) this.f6560b;
            default:
                return (d0) this.f6561c;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        int i7 = this.f6559a;
        Object obj = this.f6560b;
        switch (i7) {
            case 0:
                f fVar = (f) obj;
                a0 a0Var = (a0) this.f6561c;
                fVar.h();
                try {
                    a0Var.close();
                    if (fVar.i()) {
                        throw fVar.j(null);
                    }
                    return;
                } catch (IOException e7) {
                    if (!fVar.i()) {
                        throw e7;
                    }
                    throw fVar.j(e7);
                } finally {
                    fVar.i();
                }
            default:
                ((InputStream) obj).close();
                return;
        }
    }

    @Override // g6.a0
    public final long k(h hVar, long j7) {
        int i7 = this.f6559a;
        Object obj = this.f6560b;
        Object obj2 = this.f6561c;
        switch (i7) {
            case 0:
                i5.a.g("sink", hVar);
                f fVar = (f) obj;
                a0 a0Var = (a0) obj2;
                fVar.h();
                try {
                    long k7 = a0Var.k(hVar, j7);
                    if (fVar.i()) {
                        throw fVar.j(null);
                    }
                    return k7;
                } catch (IOException e7) {
                    if (fVar.i()) {
                        throw fVar.j(e7);
                    }
                    throw e7;
                } finally {
                    fVar.i();
                }
            default:
                i5.a.g("sink", hVar);
                if (j7 == 0) {
                    return 0L;
                }
                if (j7 < 0) {
                    throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
                }
                try {
                    ((d0) obj2).f();
                    v J = hVar.J(1);
                    int read = ((InputStream) obj).read(J.f6596a, J.f6598c, (int) Math.min(j7, 8192 - J.f6598c));
                    if (read == -1) {
                        if (J.f6597b == J.f6598c) {
                            hVar.f6572a = J.a();
                            w.a(J);
                        }
                        return -1L;
                    }
                    J.f6598c += read;
                    long j8 = read;
                    hVar.f6573b += j8;
                    return j8;
                } catch (AssertionError e8) {
                    if (i5.a.m(e8)) {
                        throw new IOException(e8);
                    }
                    throw e8;
                }
        }
    }

    public final String toString() {
        switch (this.f6559a) {
            case 0:
                return "AsyncTimeout.source(" + ((a0) this.f6561c) + ')';
            default:
                return "source(" + ((InputStream) this.f6560b) + ')';
        }
    }
}
