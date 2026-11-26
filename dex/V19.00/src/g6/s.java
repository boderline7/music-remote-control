package g6;

import android.support.v4.media.session.PlaybackStateCompat;
import java.nio.ByteBuffer;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s implements i {

    /* renamed from: a, reason: collision with root package name */
    public final y f6590a;

    /* renamed from: b, reason: collision with root package name */
    public final h f6591b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f6592c;

    public s(y yVar) {
        i5.a.g("sink", yVar);
        this.f6590a = yVar;
        this.f6591b = new h();
    }

    public final i A() {
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        h hVar = this.f6591b;
        long A = hVar.A();
        if (A > 0) {
            this.f6590a.z(hVar, A);
        }
        return this;
    }

    public final long B(a0 a0Var) {
        long j7 = 0;
        while (true) {
            long k7 = ((e) a0Var).k(this.f6591b, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (k7 == -1) {
                return j7;
            }
            j7 += k7;
            A();
        }
    }

    @Override // g6.y
    public final d0 a() {
        return this.f6590a.a();
    }

    @Override // g6.i
    public final i b(byte[] bArr) {
        i5.a.g("source", bArr);
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        h hVar = this.f6591b;
        hVar.getClass();
        hVar.L(bArr, 0, bArr.length);
        A();
        return this;
    }

    @Override // g6.i
    public final i c(byte[] bArr, int i7, int i8) {
        i5.a.g("source", bArr);
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f6591b.L(bArr, i7, i8);
        A();
        return this;
    }

    @Override // g6.y, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        y yVar = this.f6590a;
        if (this.f6592c) {
            return;
        }
        try {
            h hVar = this.f6591b;
            long j7 = hVar.f6573b;
            if (j7 > 0) {
                yVar.z(hVar, j7);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            yVar.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.f6592c = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // g6.i
    public final i e(long j7) {
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f6591b.O(j7);
        A();
        return this;
    }

    @Override // g6.i, g6.y, java.io.Flushable
    public final void flush() {
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        h hVar = this.f6591b;
        long j7 = hVar.f6573b;
        y yVar = this.f6590a;
        if (j7 > 0) {
            yVar.z(hVar, j7);
        }
        yVar.flush();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f6592c;
    }

    @Override // g6.i
    public final i j(int i7) {
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f6591b.Q(i7);
        A();
        return this;
    }

    @Override // g6.i
    public final i l(l lVar) {
        i5.a.g("byteString", lVar);
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f6591b.K(lVar);
        A();
        return this;
    }

    @Override // g6.i
    public final i o(int i7) {
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f6591b.P(i7);
        A();
        return this;
    }

    @Override // g6.i
    public final i r(String str) {
        i5.a.g("string", str);
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f6591b.S(str);
        A();
        return this;
    }

    public final String toString() {
        return "buffer(" + this.f6590a + ')';
    }

    @Override // g6.i
    public final i u(int i7) {
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f6591b.N(i7);
        A();
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        i5.a.g("source", byteBuffer);
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        int write = this.f6591b.write(byteBuffer);
        A();
        return write;
    }

    @Override // g6.y
    public final void z(h hVar, long j7) {
        i5.a.g("source", hVar);
        if (!(!this.f6592c)) {
            throw new IllegalStateException("closed".toString());
        }
        this.f6591b.z(hVar, j7);
        A();
    }
}
