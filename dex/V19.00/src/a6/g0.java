package a6;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayDeque;
import okhttp3.internal.http2.StreamResetException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g0 {
    public static final c0 Companion = new c0();
    public static final long EMIT_BUFFER_SIZE = 16384;

    /* renamed from: a, reason: collision with root package name */
    public final int f411a;

    /* renamed from: b, reason: collision with root package name */
    public final w f412b;

    /* renamed from: c, reason: collision with root package name */
    public long f413c;

    /* renamed from: d, reason: collision with root package name */
    public long f414d;

    /* renamed from: e, reason: collision with root package name */
    public long f415e;

    /* renamed from: f, reason: collision with root package name */
    public long f416f;

    /* renamed from: g, reason: collision with root package name */
    public final ArrayDeque f417g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f418h;

    /* renamed from: i, reason: collision with root package name */
    public final e0 f419i;

    /* renamed from: j, reason: collision with root package name */
    public final d0 f420j;

    /* renamed from: k, reason: collision with root package name */
    public final f0 f421k;

    /* renamed from: l, reason: collision with root package name */
    public final f0 f422l;

    /* renamed from: m, reason: collision with root package name */
    public b f423m;

    /* renamed from: n, reason: collision with root package name */
    public IOException f424n;

    public g0(int i7, w wVar, boolean z6, boolean z7, s5.d0 d0Var) {
        this.f411a = i7;
        this.f412b = wVar;
        this.f416f = wVar.f495s.a();
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f417g = arrayDeque;
        this.f419i = new e0(this, wVar.f494r.a(), z7);
        this.f420j = new d0(this, z6);
        this.f421k = new f0(this);
        this.f422l = new f0(this);
        if (d0Var == null) {
            if (!g()) {
                throw new IllegalStateException("remotely-initiated streams should have headers".toString());
            }
        } else {
            if (!(!g())) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet".toString());
            }
            arrayDeque.add(d0Var);
        }
    }

    public final void a() {
        boolean z6;
        boolean h7;
        if (t5.b.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            try {
                e0 e0Var = this.f419i;
                if (!e0Var.f394b && e0Var.f397e) {
                    d0 d0Var = this.f420j;
                    if (d0Var.f381a || d0Var.f383c) {
                        z6 = true;
                        h7 = h();
                    }
                }
                z6 = false;
                h7 = h();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z6) {
            c(b.CANCEL, null);
        } else {
            if (h7) {
                return;
            }
            this.f412b.D(this.f411a);
        }
    }

    public final void b() {
        d0 d0Var = this.f420j;
        if (d0Var.f383c) {
            throw new IOException("stream closed");
        }
        if (d0Var.f381a) {
            throw new IOException("stream finished");
        }
        if (this.f423m != null) {
            IOException iOException = this.f424n;
            if (iOException != null) {
                throw iOException;
            }
            b bVar = this.f423m;
            i5.a.d(bVar);
            throw new StreamResetException(bVar);
        }
    }

    public final void c(b bVar, IOException iOException) {
        i5.a.g("rstStatusCode", bVar);
        if (d(bVar, iOException)) {
            w wVar = this.f412b;
            wVar.getClass();
            wVar.f501y.F(this.f411a, bVar);
        }
    }

    public final boolean d(b bVar, IOException iOException) {
        if (t5.b.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        synchronized (this) {
            if (this.f423m != null) {
                return false;
            }
            this.f423m = bVar;
            this.f424n = iOException;
            notifyAll();
            if (this.f419i.f394b) {
                if (this.f420j.f381a) {
                    return false;
                }
            }
            this.f412b.D(this.f411a);
            return true;
        }
    }

    public final void e(b bVar) {
        i5.a.g("errorCode", bVar);
        if (d(bVar, null)) {
            this.f412b.H(this.f411a, bVar);
        }
    }

    public final d0 f() {
        synchronized (this) {
            if (!this.f418h && !g()) {
                throw new IllegalStateException("reply before requesting the sink".toString());
            }
        }
        return this.f420j;
    }

    public final boolean g() {
        return this.f412b.f477a == ((this.f411a & 1) == 1);
    }

    public final synchronized boolean h() {
        if (this.f423m != null) {
            return false;
        }
        e0 e0Var = this.f419i;
        if (e0Var.f394b || e0Var.f397e) {
            d0 d0Var = this.f420j;
            if (d0Var.f381a || d0Var.f383c) {
                if (this.f418h) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004e A[Catch: all -> 0x0043, TryCatch #0 {all -> 0x0043, blocks: (B:10:0x0035, B:14:0x003d, B:16:0x004e, B:17:0x0052, B:24:0x0045), top: B:9:0x0035 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void i(s5.d0 r3, boolean r4) {
        /*
            r2 = this;
            java.lang.String r0 = "headers"
            i5.a.g(r0, r3)
            boolean r0 = t5.b.assertionsEnabled
            if (r0 == 0) goto L34
            boolean r0 = java.lang.Thread.holdsLock(r2)
            if (r0 != 0) goto L10
            goto L34
        L10:
            java.lang.AssertionError r3 = new java.lang.AssertionError
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "Thread "
            r4.<init>(r0)
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.String r0 = r0.getName()
            r4.append(r0)
            java.lang.String r0 = " MUST NOT hold lock on "
            r4.append(r0)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L34:
            monitor-enter(r2)
            boolean r0 = r2.f418h     // Catch: java.lang.Throwable -> L43
            r1 = 1
            if (r0 == 0) goto L45
            if (r4 != 0) goto L3d
            goto L45
        L3d:
            a6.e0 r3 = r2.f419i     // Catch: java.lang.Throwable -> L43
            r3.getClass()     // Catch: java.lang.Throwable -> L43
            goto L4c
        L43:
            r3 = move-exception
            goto L64
        L45:
            r2.f418h = r1     // Catch: java.lang.Throwable -> L43
            java.util.ArrayDeque r0 = r2.f417g     // Catch: java.lang.Throwable -> L43
            r0.add(r3)     // Catch: java.lang.Throwable -> L43
        L4c:
            if (r4 == 0) goto L52
            a6.e0 r3 = r2.f419i     // Catch: java.lang.Throwable -> L43
            r3.f394b = r1     // Catch: java.lang.Throwable -> L43
        L52:
            boolean r3 = r2.h()     // Catch: java.lang.Throwable -> L43
            r2.notifyAll()     // Catch: java.lang.Throwable -> L43
            monitor-exit(r2)
            if (r3 != 0) goto L63
            a6.w r3 = r2.f412b
            int r4 = r2.f411a
            r3.D(r4)
        L63:
            return
        L64:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a6.g0.i(s5.d0, boolean):void");
    }

    public final synchronized void j(b bVar) {
        i5.a.g("errorCode", bVar);
        if (this.f423m == null) {
            this.f423m = bVar;
            notifyAll();
        }
    }

    public final void k() {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }
}
