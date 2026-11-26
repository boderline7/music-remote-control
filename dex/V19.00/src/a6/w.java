package a6;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w implements Closeable {
    public static final int AWAIT_PING = 3;
    public static final o0 B;
    public static final j Companion = new j();
    public static final int DEGRADED_PING = 2;
    public static final int DEGRADED_PONG_TIMEOUT_NS = 1000000000;
    public static final int INTERVAL_PING = 1;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    public final LinkedHashSet A;

    /* renamed from: a, reason: collision with root package name */
    public final boolean f477a;

    /* renamed from: b, reason: collision with root package name */
    public final m f478b;

    /* renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f479c;

    /* renamed from: d, reason: collision with root package name */
    public final String f480d;

    /* renamed from: e, reason: collision with root package name */
    public int f481e;

    /* renamed from: f, reason: collision with root package name */
    public int f482f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f483g;

    /* renamed from: h, reason: collision with root package name */
    public final w5.g f484h;

    /* renamed from: i, reason: collision with root package name */
    public final w5.c f485i;

    /* renamed from: j, reason: collision with root package name */
    public final w5.c f486j;

    /* renamed from: k, reason: collision with root package name */
    public final w5.c f487k;

    /* renamed from: l, reason: collision with root package name */
    public final m0 f488l;

    /* renamed from: m, reason: collision with root package name */
    public long f489m;

    /* renamed from: n, reason: collision with root package name */
    public long f490n;

    /* renamed from: o, reason: collision with root package name */
    public long f491o;

    /* renamed from: p, reason: collision with root package name */
    public long f492p;

    /* renamed from: q, reason: collision with root package name */
    public long f493q;

    /* renamed from: r, reason: collision with root package name */
    public final o0 f494r;

    /* renamed from: s, reason: collision with root package name */
    public o0 f495s;

    /* renamed from: t, reason: collision with root package name */
    public long f496t;

    /* renamed from: u, reason: collision with root package name */
    public long f497u;

    /* renamed from: v, reason: collision with root package name */
    public long f498v;

    /* renamed from: w, reason: collision with root package name */
    public long f499w;

    /* renamed from: x, reason: collision with root package name */
    public final Socket f500x;

    /* renamed from: y, reason: collision with root package name */
    public final i0 f501y;

    /* renamed from: z, reason: collision with root package name */
    public final q f502z;

    static {
        o0 o0Var = new o0();
        o0Var.c(7, 65535);
        o0Var.c(5, 16384);
        B = o0Var;
    }

    public w(i iVar) {
        boolean z6 = iVar.f428a;
        this.f477a = z6;
        this.f478b = iVar.f434g;
        this.f479c = new LinkedHashMap();
        String str = iVar.f431d;
        if (str == null) {
            i5.a.t("connectionName");
            throw null;
        }
        this.f480d = str;
        this.f482f = z6 ? 3 : 2;
        w5.g gVar = iVar.f429b;
        this.f484h = gVar;
        w5.c f7 = gVar.f();
        this.f485i = f7;
        this.f486j = gVar.f();
        this.f487k = gVar.f();
        this.f488l = iVar.f435h;
        o0 o0Var = new o0();
        if (z6) {
            o0Var.c(7, 16777216);
        }
        this.f494r = o0Var;
        this.f495s = B;
        this.f499w = r3.a();
        Socket socket = iVar.f430c;
        if (socket == null) {
            i5.a.t("socket");
            throw null;
        }
        this.f500x = socket;
        g6.i iVar2 = iVar.f433f;
        if (iVar2 == null) {
            i5.a.t("sink");
            throw null;
        }
        this.f501y = new i0(iVar2, z6);
        g6.j jVar = iVar.f432e;
        if (jVar == null) {
            i5.a.t("source");
            throw null;
        }
        this.f502z = new q(this, new b0(jVar, z6));
        this.A = new LinkedHashSet();
        int i7 = iVar.f436i;
        if (i7 != 0) {
            long nanos = TimeUnit.MILLISECONDS.toNanos(i7);
            f7.c(new u(str.concat(" ping"), this, nanos), nanos);
        }
    }

    public final void A(b bVar, b bVar2, IOException iOException) {
        int i7;
        Object[] objArr;
        i5.a.g("connectionCode", bVar);
        i5.a.g("streamCode", bVar2);
        if (t5.b.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        try {
            E(bVar);
        } catch (IOException unused) {
        }
        synchronized (this) {
            if (!this.f479c.isEmpty()) {
                objArr = this.f479c.values().toArray(new g0[0]);
                this.f479c.clear();
            } else {
                objArr = null;
            }
        }
        g0[] g0VarArr = (g0[]) objArr;
        if (g0VarArr != null) {
            for (g0 g0Var : g0VarArr) {
                try {
                    g0Var.c(bVar2, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.f501y.close();
        } catch (IOException unused3) {
        }
        try {
            this.f500x.close();
        } catch (IOException unused4) {
        }
        this.f485i.e();
        this.f486j.e();
        this.f487k.e();
    }

    public final void B(IOException iOException) {
        b bVar = b.PROTOCOL_ERROR;
        A(bVar, bVar, iOException);
    }

    public final synchronized g0 C(int i7) {
        return (g0) this.f479c.get(Integer.valueOf(i7));
    }

    public final synchronized g0 D(int i7) {
        g0 g0Var;
        g0Var = (g0) this.f479c.remove(Integer.valueOf(i7));
        notifyAll();
        return g0Var;
    }

    public final void E(b bVar) {
        i5.a.g("statusCode", bVar);
        synchronized (this.f501y) {
            synchronized (this) {
                if (this.f483g) {
                    return;
                }
                this.f483g = true;
                this.f501y.D(this.f481e, bVar, t5.b.EMPTY_BYTE_ARRAY);
            }
        }
    }

    public final synchronized void F(long j7) {
        long j8 = this.f496t + j7;
        this.f496t = j8;
        long j9 = j8 - this.f497u;
        if (j9 >= this.f494r.a() / 2) {
            I(0, j9);
            this.f497u += j9;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0035, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.f501y.f441d);
        r6 = r2;
        r8.f498v += r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void G(int r9, boolean r10, g6.h r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            a6.i0 r12 = r8.f501y
            r12.B(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L68
            monitor-enter(r8)
        L12:
            long r4 = r8.f498v     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            long r6 = r8.f499w     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L34
            java.util.LinkedHashMap r2 = r8.f479c     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            if (r2 == 0) goto L2c
            r8.wait()     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            goto L12
        L2a:
            r9 = move-exception
            goto L66
        L2c:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            throw r9     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
        L34:
            long r6 = r6 - r4
            long r4 = java.lang.Math.min(r12, r6)     // Catch: java.lang.Throwable -> L2a
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L2a
            a6.i0 r4 = r8.f501y     // Catch: java.lang.Throwable -> L2a
            int r4 = r4.f441d     // Catch: java.lang.Throwable -> L2a
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L2a
            long r4 = r8.f498v     // Catch: java.lang.Throwable -> L2a
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L2a
            long r4 = r4 + r6
            r8.f498v = r4     // Catch: java.lang.Throwable -> L2a
            monitor-exit(r8)
            long r12 = r12 - r6
            a6.i0 r4 = r8.f501y
            if (r10 == 0) goto L54
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L54
            r5 = 1
            goto L55
        L54:
            r5 = r3
        L55:
            r4.B(r5, r9, r11, r2)
            goto Ld
        L59:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L2a
            r9.interrupt()     // Catch: java.lang.Throwable -> L2a
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L2a
            r9.<init>()     // Catch: java.lang.Throwable -> L2a
            throw r9     // Catch: java.lang.Throwable -> L2a
        L66:
            monitor-exit(r8)
            throw r9
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a6.w.G(int, boolean, g6.h, long):void");
    }

    public final void H(int i7, b bVar) {
        i5.a.g("errorCode", bVar);
        this.f485i.c(new t(this.f480d + '[' + i7 + "] writeSynReset", this, i7, bVar, 1), 0L);
    }

    public final void I(int i7, long j7) {
        this.f485i.c(new v(this.f480d + '[' + i7 + "] windowUpdate", this, i7, j7), 0L);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        A(b.NO_ERROR, b.CANCEL, null);
    }

    public final void flush() {
        this.f501y.flush();
    }
}
