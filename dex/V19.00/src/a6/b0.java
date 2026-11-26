package a6;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b0 implements Closeable {
    public static final z Companion = new z();

    /* renamed from: e, reason: collision with root package name */
    public static final Logger f373e;

    /* renamed from: a, reason: collision with root package name */
    public final g6.j f374a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f375b;

    /* renamed from: c, reason: collision with root package name */
    public final a0 f376c;

    /* renamed from: d, reason: collision with root package name */
    public final e f377d;

    static {
        Logger logger = Logger.getLogger(h.class.getName());
        i5.a.f("getLogger(Http2::class.java.name)", logger);
        f373e = logger;
    }

    public b0(g6.j jVar, boolean z6) {
        this.f374a = jVar;
        this.f375b = z6;
        a0 a0Var = new a0(jVar);
        this.f376c = a0Var;
        this.f377d = new e(a0Var);
    }

    /* JADX WARN: Code restructure failed: missing block: B:143:0x0245, code lost:
    
        throw new java.io.IOException(a.a.d("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: ", r7));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean A(boolean r17, a6.q r18) {
        /*
            Method dump skipped, instructions count: 884
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a6.b0.A(boolean, a6.q):boolean");
    }

    public final void B(q qVar) {
        i5.a.g("handler", qVar);
        if (this.f375b) {
            if (!A(true, qVar)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            return;
        }
        g6.l lVar = h.CONNECTION_PREFACE;
        g6.l f7 = this.f374a.f(lVar.c());
        Level level = Level.FINE;
        Logger logger = f373e;
        if (logger.isLoggable(level)) {
            logger.fine(t5.b.h("<< CONNECTION " + f7.d(), new Object[0]));
        }
        if (!i5.a.c(lVar, f7)) {
            throw new IOException("Expected a connection header but was ".concat(f7.j()));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x0182, code lost:
    
        if (r8 == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0184, code lost:
    
        r3.i(t5.b.EMPTY_HEADERS, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void C(a6.q r19, int r20, int r21, int r22) {
        /*
            Method dump skipped, instructions count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a6.b0.C(a6.q, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f7, code lost:
    
        throw new java.io.IOException("Invalid dynamic table size update " + r4.f386b);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List D(int r4, int r5, int r6, int r7) {
        /*
            Method dump skipped, instructions count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a6.b0.D(int, int, int, int):java.util.List");
    }

    public final void E(q qVar, int i7, int i8, int i9) {
        int i10;
        if (i9 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        boolean z6 = false;
        int i11 = 1;
        boolean z7 = (i8 & 1) != 0;
        if ((i8 & 8) != 0) {
            byte y6 = this.f374a.y();
            byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
            i10 = y6 & 255;
        } else {
            i10 = 0;
        }
        if ((i8 & 32) != 0) {
            g6.j jVar = this.f374a;
            jVar.n();
            jVar.y();
            byte[] bArr2 = t5.b.EMPTY_BYTE_ARRAY;
            qVar.getClass();
            i7 -= 5;
        }
        Companion.getClass();
        List D = D(z.a(i7, i8, i10), i10, i8, i9);
        qVar.getClass();
        i5.a.g("headerBlock", D);
        qVar.f460b.getClass();
        if (i9 != 0 && (i9 & 1) == 0) {
            z6 = true;
        }
        if (z6) {
            w wVar = qVar.f460b;
            wVar.getClass();
            wVar.f486j.c(new s(wVar.f480d + '[' + i9 + "] onHeaders", wVar, i9, D, z7), 0L);
            return;
        }
        w wVar2 = qVar.f460b;
        synchronized (wVar2) {
            g0 C = wVar2.C(i9);
            if (C != null) {
                C.i(t5.b.v(D), z7);
                return;
            }
            if (wVar2.f483g) {
                return;
            }
            if (i9 <= wVar2.f481e) {
                return;
            }
            if (i9 % 2 == wVar2.f482f % 2) {
                return;
            }
            g0 g0Var = new g0(i9, wVar2, false, z7, t5.b.v(D));
            wVar2.f481e = i9;
            wVar2.f479c.put(Integer.valueOf(i9), g0Var);
            wVar2.f484h.f().c(new n(wVar2.f480d + '[' + i9 + "] onStream", wVar2, g0Var, i11), 0L);
        }
    }

    public final void F(q qVar, int i7, int i8, int i9) {
        int i10;
        if (i9 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        if ((i8 & 8) != 0) {
            byte y6 = this.f374a.y();
            byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
            i10 = y6 & 255;
        } else {
            i10 = 0;
        }
        int n6 = this.f374a.n() & androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
        Companion.getClass();
        List D = D(z.a(i7 - 4, i8, i10), i10, i8, i9);
        qVar.getClass();
        i5.a.g("requestHeaders", D);
        w wVar = qVar.f460b;
        wVar.getClass();
        synchronized (wVar) {
            if (wVar.A.contains(Integer.valueOf(n6))) {
                wVar.H(n6, b.PROTOCOL_ERROR);
                return;
            }
            wVar.A.add(Integer.valueOf(n6));
            wVar.f486j.c(new t(wVar.f480d + '[' + n6 + "] onRequest", wVar, n6, D, 2), 0L);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f374a.close();
    }
}
