package g6;

import java.util.zip.Inflater;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o implements a0 {

    /* renamed from: a, reason: collision with root package name */
    public final j f6583a;

    /* renamed from: b, reason: collision with root package name */
    public final Inflater f6584b;

    /* renamed from: c, reason: collision with root package name */
    public int f6585c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f6586d;

    public o(t tVar, Inflater inflater) {
        this.f6583a = tVar;
        this.f6584b = inflater;
    }

    @Override // g6.a0
    public final d0 a() {
        return this.f6583a.a();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f6586d) {
            return;
        }
        this.f6584b.end();
        this.f6586d = true;
        this.f6583a.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008a A[SYNTHETIC] */
    @Override // g6.a0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long k(g6.h r11, long r12) {
        /*
            r10 = this;
            java.lang.String r0 = "sink"
            i5.a.g(r0, r11)
        L5:
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 < 0) goto Lbd
            boolean r3 = r10.f6586d
            r4 = 1
            r3 = r3 ^ r4
            if (r3 == 0) goto Lb1
            g6.j r3 = r10.f6583a
            java.util.zip.Inflater r5 = r10.f6584b
            if (r2 != 0) goto L1a
        L17:
            r8 = r0
            goto L86
        L1a:
            g6.v r2 = r11.J(r4)     // Catch: java.util.zip.DataFormatException -> L74
            int r4 = r2.f6598c     // Catch: java.util.zip.DataFormatException -> L74
            int r4 = 8192 - r4
            long r6 = (long) r4     // Catch: java.util.zip.DataFormatException -> L74
            long r6 = java.lang.Math.min(r12, r6)     // Catch: java.util.zip.DataFormatException -> L74
            int r4 = (int) r6     // Catch: java.util.zip.DataFormatException -> L74
            boolean r6 = r5.needsInput()     // Catch: java.util.zip.DataFormatException -> L74
            if (r6 != 0) goto L2f
            goto L4b
        L2f:
            boolean r6 = r3.t()     // Catch: java.util.zip.DataFormatException -> L74
            if (r6 == 0) goto L36
            goto L4b
        L36:
            g6.h r6 = r3.s()     // Catch: java.util.zip.DataFormatException -> L74
            g6.v r6 = r6.f6572a     // Catch: java.util.zip.DataFormatException -> L74
            i5.a.d(r6)     // Catch: java.util.zip.DataFormatException -> L74
            int r7 = r6.f6598c     // Catch: java.util.zip.DataFormatException -> L74
            int r8 = r6.f6597b     // Catch: java.util.zip.DataFormatException -> L74
            int r7 = r7 - r8
            r10.f6585c = r7     // Catch: java.util.zip.DataFormatException -> L74
            byte[] r6 = r6.f6596a     // Catch: java.util.zip.DataFormatException -> L74
            r5.setInput(r6, r8, r7)     // Catch: java.util.zip.DataFormatException -> L74
        L4b:
            byte[] r6 = r2.f6596a     // Catch: java.util.zip.DataFormatException -> L74
            int r7 = r2.f6598c     // Catch: java.util.zip.DataFormatException -> L74
            int r4 = r5.inflate(r6, r7, r4)     // Catch: java.util.zip.DataFormatException -> L74
            int r6 = r10.f6585c     // Catch: java.util.zip.DataFormatException -> L74
            if (r6 != 0) goto L58
            goto L66
        L58:
            int r7 = r5.getRemaining()     // Catch: java.util.zip.DataFormatException -> L74
            int r6 = r6 - r7
            int r7 = r10.f6585c     // Catch: java.util.zip.DataFormatException -> L74
            int r7 = r7 - r6
            r10.f6585c = r7     // Catch: java.util.zip.DataFormatException -> L74
            long r6 = (long) r6     // Catch: java.util.zip.DataFormatException -> L74
            r3.h(r6)     // Catch: java.util.zip.DataFormatException -> L74
        L66:
            if (r4 <= 0) goto L76
            int r6 = r2.f6598c     // Catch: java.util.zip.DataFormatException -> L74
            int r6 = r6 + r4
            r2.f6598c = r6     // Catch: java.util.zip.DataFormatException -> L74
            long r6 = r11.f6573b     // Catch: java.util.zip.DataFormatException -> L74
            long r8 = (long) r4     // Catch: java.util.zip.DataFormatException -> L74
            long r6 = r6 + r8
            r11.f6573b = r6     // Catch: java.util.zip.DataFormatException -> L74
            goto L86
        L74:
            r11 = move-exception
            goto Lab
        L76:
            int r4 = r2.f6597b     // Catch: java.util.zip.DataFormatException -> L74
            int r6 = r2.f6598c     // Catch: java.util.zip.DataFormatException -> L74
            if (r4 != r6) goto L17
            g6.v r4 = r2.a()     // Catch: java.util.zip.DataFormatException -> L74
            r11.f6572a = r4     // Catch: java.util.zip.DataFormatException -> L74
            g6.w.a(r2)     // Catch: java.util.zip.DataFormatException -> L74
            goto L17
        L86:
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 <= 0) goto L8b
            return r8
        L8b:
            boolean r0 = r5.finished()
            if (r0 != 0) goto La8
            boolean r0 = r5.needsDictionary()
            if (r0 == 0) goto L98
            goto La8
        L98:
            boolean r0 = r3.t()
            if (r0 != 0) goto La0
            goto L5
        La0:
            java.io.EOFException r11 = new java.io.EOFException
            java.lang.String r12 = "source exhausted prematurely"
            r11.<init>(r12)
            throw r11
        La8:
            r11 = -1
            return r11
        Lab:
            java.io.IOException r12 = new java.io.IOException
            r12.<init>(r11)
            throw r12
        Lb1:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "closed"
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        Lbd:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "byteCount < 0: "
            r11.<init>(r0)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r11 = r11.toString()
            r12.<init>(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: g6.o.k(g6.h, long):long");
    }
}
