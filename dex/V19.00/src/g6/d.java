package g6;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements y {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6553a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final Object f6554b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f6555c;

    public d(z zVar, d dVar) {
        this.f6554b = zVar;
        this.f6555c = dVar;
    }

    public d(OutputStream outputStream, d0 d0Var) {
        this.f6554b = outputStream;
        this.f6555c = d0Var;
    }

    @Override // g6.y
    public final d0 a() {
        switch (this.f6553a) {
            case 0:
                return (f) this.f6554b;
            default:
                return (d0) this.f6555c;
        }
    }

    @Override // g6.y, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        int i7 = this.f6553a;
        Object obj = this.f6554b;
        switch (i7) {
            case 0:
                f fVar = (f) obj;
                y yVar = (y) this.f6555c;
                fVar.h();
                try {
                    yVar.close();
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
                ((OutputStream) obj).close();
                return;
        }
    }

    @Override // g6.y, java.io.Flushable
    public final void flush() {
        int i7 = this.f6553a;
        Object obj = this.f6554b;
        switch (i7) {
            case 0:
                f fVar = (f) obj;
                y yVar = (y) this.f6555c;
                fVar.h();
                try {
                    yVar.flush();
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
                ((OutputStream) obj).flush();
                return;
        }
    }

    public final String toString() {
        switch (this.f6553a) {
            case 0:
                return "AsyncTimeout.sink(" + ((y) this.f6555c) + ')';
            default:
                return "sink(" + ((OutputStream) this.f6554b) + ')';
        }
    }

    @Override // g6.y
    public final void z(h hVar, long j7) {
        int i7 = this.f6553a;
        Object obj = this.f6554b;
        Object obj2 = this.f6555c;
        switch (i7) {
            case 0:
                i5.a.g("source", hVar);
                n1.z.w(hVar.f6573b, 0L, j7);
                while (j7 > 0) {
                    v vVar = hVar.f6572a;
                    i5.a.d(vVar);
                    long j8 = 0;
                    while (true) {
                        if (j8 < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                            j8 += vVar.f6598c - vVar.f6597b;
                            if (j8 >= j7) {
                                j8 = j7;
                            } else {
                                vVar = vVar.f6601f;
                                i5.a.d(vVar);
                            }
                        }
                    }
                    f fVar = (f) obj;
                    y yVar = (y) obj2;
                    fVar.h();
                    try {
                        yVar.z(hVar, j8);
                        if (fVar.i()) {
                            throw fVar.j(null);
                        }
                        j7 -= j8;
                    } catch (IOException e7) {
                        if (!fVar.i()) {
                            throw e7;
                        }
                        throw fVar.j(e7);
                    } finally {
                        fVar.i();
                    }
                }
                return;
            default:
                i5.a.g("source", hVar);
                n1.z.w(hVar.f6573b, 0L, j7);
                while (j7 > 0) {
                    ((d0) obj2).f();
                    v vVar2 = hVar.f6572a;
                    i5.a.d(vVar2);
                    int min = (int) Math.min(j7, vVar2.f6598c - vVar2.f6597b);
                    ((OutputStream) obj).write(vVar2.f6596a, vVar2.f6597b, min);
                    int i8 = vVar2.f6597b + min;
                    vVar2.f6597b = i8;
                    long j9 = min;
                    j7 -= j9;
                    hVar.f6573b -= j9;
                    if (i8 == vVar2.f6598c) {
                        hVar.f6572a = vVar2.a();
                        w.a(vVar2);
                    }
                }
                return;
        }
    }
}
