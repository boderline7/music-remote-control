package g6;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class t implements j {

    /* renamed from: a, reason: collision with root package name */
    public final a0 f6593a;

    /* renamed from: b, reason: collision with root package name */
    public final h f6594b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f6595c;

    public t(a0 a0Var) {
        i5.a.g("source", a0Var);
        this.f6593a = a0Var;
        this.f6594b = new h();
    }

    public final long A(byte b7, long j7, long j8) {
        if (!(!this.f6595c)) {
            throw new IllegalStateException("closed".toString());
        }
        long j9 = 0;
        if (0 > j8) {
            throw new IllegalArgumentException(("fromIndex=0 toIndex=" + j8).toString());
        }
        while (j9 < j8) {
            long D = this.f6594b.D(b7, j9, j8);
            if (D != -1) {
                return D;
            }
            h hVar = this.f6594b;
            long j10 = hVar.f6573b;
            if (j10 >= j8 || this.f6593a.k(hVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j9 = Math.max(j9, j10);
        }
        return -1L;
    }

    public final int B() {
        q(4L);
        int n6 = this.f6594b.n();
        return ((n6 & 255) << 24) | (((-16777216) & n6) >>> 24) | ((16711680 & n6) >>> 8) | ((65280 & n6) << 8);
    }

    public final boolean C(long j7) {
        h hVar;
        if (j7 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (!(!this.f6595c)) {
            throw new IllegalStateException("closed".toString());
        }
        do {
            hVar = this.f6594b;
            if (hVar.f6573b >= j7) {
                return true;
            }
        } while (this.f6593a.k(hVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1);
        return false;
    }

    @Override // g6.a0
    public final d0 a() {
        return this.f6593a.a();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() {
        if (this.f6595c) {
            return;
        }
        this.f6595c = true;
        this.f6593a.close();
        h hVar = this.f6594b;
        hVar.h(hVar.f6573b);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:?, code lost:
    
        return -1;
     */
    @Override // g6.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int d(g6.r r8) {
        /*
            r7 = this;
            java.lang.String r0 = "options"
            i5.a.g(r0, r8)
            boolean r0 = r7.f6595c
            r1 = 1
            r0 = r0 ^ r1
            if (r0 == 0) goto L36
        Lb:
            g6.h r0 = r7.f6594b
            int r2 = h6.a.b(r0, r8, r1)
            r3 = -2
            r4 = -1
            if (r2 == r3) goto L26
            if (r2 == r4) goto L24
            g6.l[] r8 = r8.f6588a
            r8 = r8[r2]
            int r8 = r8.c()
            long r3 = (long) r8
            r0.h(r3)
            goto L35
        L24:
            r2 = r4
            goto L35
        L26:
            g6.a0 r2 = r7.f6593a
            r5 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2.k(r0, r5)
            r5 = -1
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 != 0) goto Lb
            goto L24
        L35:
            return r2
        L36:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "closed"
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: g6.t.d(g6.r):int");
    }

    @Override // g6.j
    public final l f(long j7) {
        q(j7);
        return this.f6594b.f(j7);
    }

    @Override // g6.j
    public final String g(long j7) {
        if (j7 < 0) {
            throw new IllegalArgumentException(("limit < 0: " + j7).toString());
        }
        long j8 = j7 == Long.MAX_VALUE ? Long.MAX_VALUE : j7 + 1;
        long A = A((byte) 10, 0L, j8);
        h hVar = this.f6594b;
        if (A != -1) {
            return h6.a.a(hVar, A);
        }
        if (j8 < Long.MAX_VALUE && C(j8) && hVar.C(j8 - 1) == 13 && C(1 + j8) && hVar.C(j8) == 10) {
            return h6.a.a(hVar, j8);
        }
        h hVar2 = new h();
        hVar.B(hVar2, 0L, Math.min(32, hVar.f6573b));
        throw new EOFException("\\n not found: limit=" + Math.min(hVar.f6573b, j7) + " content=" + hVar2.f(hVar2.f6573b).d() + (char) 8230);
    }

    @Override // g6.j
    public final void h(long j7) {
        if (!(!this.f6595c)) {
            throw new IllegalStateException("closed".toString());
        }
        while (j7 > 0) {
            h hVar = this.f6594b;
            if (hVar.f6573b == 0 && this.f6593a.k(hVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j7, hVar.f6573b);
            hVar.h(min);
            j7 -= min;
        }
    }

    @Override // g6.j
    public final short i() {
        q(2L);
        return this.f6594b.i();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f6595c;
    }

    @Override // g6.a0
    public final long k(h hVar, long j7) {
        i5.a.g("sink", hVar);
        if (j7 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (!(!this.f6595c)) {
            throw new IllegalStateException("closed".toString());
        }
        h hVar2 = this.f6594b;
        if (hVar2.f6573b == 0 && this.f6593a.k(hVar2, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return hVar2.k(hVar, Math.min(j7, hVar2.f6573b));
    }

    @Override // g6.j
    public final long m(d dVar) {
        h hVar;
        long j7 = 0;
        while (true) {
            a0 a0Var = this.f6593a;
            hVar = this.f6594b;
            if (a0Var.k(hVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                break;
            }
            long A = hVar.A();
            if (A > 0) {
                j7 += A;
                dVar.z(hVar, A);
            }
        }
        long j8 = hVar.f6573b;
        if (j8 <= 0) {
            return j7;
        }
        long j9 = j7 + j8;
        dVar.z(hVar, j8);
        return j9;
    }

    @Override // g6.j
    public final int n() {
        q(4L);
        return this.f6594b.n();
    }

    @Override // g6.j
    public final String p() {
        return g(Long.MAX_VALUE);
    }

    @Override // g6.j
    public final void q(long j7) {
        if (!C(j7)) {
            throw new EOFException();
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        i5.a.g("sink", byteBuffer);
        h hVar = this.f6594b;
        if (hVar.f6573b == 0 && this.f6593a.k(hVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return hVar.read(byteBuffer);
    }

    @Override // g6.j
    public final h s() {
        return this.f6594b;
    }

    @Override // g6.j
    public final boolean t() {
        if (!(!this.f6595c)) {
            throw new IllegalStateException("closed".toString());
        }
        h hVar = this.f6594b;
        return hVar.t() && this.f6593a.k(hVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
    }

    public final String toString() {
        return "buffer(" + this.f6593a + ')';
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0031, code lost:
    
        if (r0 == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0034, code lost:
    
        i5.a.h(16);
        i5.a.h(16);
        r1 = java.lang.Integer.toString(r2, 16);
        i5.a.f("toString(this, checkRadix(radix))", r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0050, code lost:
    
        throw new java.lang.NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x".concat(r1));
     */
    @Override // g6.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long v() {
        /*
            r6 = this;
            r0 = 1
            r6.q(r0)
            r0 = 0
        L6:
            int r1 = r0 + 1
            long r2 = (long) r1
            boolean r2 = r6.C(r2)
            g6.h r3 = r6.f6594b
            if (r2 == 0) goto L51
            long r4 = (long) r0
            byte r2 = r3.C(r4)
            r4 = 48
            if (r2 < r4) goto L1e
            r4 = 57
            if (r2 <= r4) goto L2f
        L1e:
            r4 = 97
            if (r2 < r4) goto L26
            r4 = 102(0x66, float:1.43E-43)
            if (r2 <= r4) goto L2f
        L26:
            r4 = 65
            if (r2 < r4) goto L31
            r4 = 70
            if (r2 <= r4) goto L2f
            goto L31
        L2f:
            r0 = r1
            goto L6
        L31:
            if (r0 == 0) goto L34
            goto L51
        L34:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            r1 = 16
            i5.a.h(r1)
            i5.a.h(r1)
            java.lang.String r1 = java.lang.Integer.toString(r2, r1)
            java.lang.String r2 = "toString(this, checkRadix(radix))"
            i5.a.f(r2, r1)
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.String r1 = r2.concat(r1)
            r0.<init>(r1)
            throw r0
        L51:
            long r0 = r3.v()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: g6.t.v():long");
    }

    @Override // g6.j
    public final String w(Charset charset) {
        h hVar = this.f6594b;
        hVar.M(this.f6593a);
        return hVar.H(hVar.f6573b, charset);
    }

    @Override // g6.j
    public final g x() {
        return new g(this, 1);
    }

    @Override // g6.j
    public final byte y() {
        q(1L);
        return this.f6594b.y();
    }
}
