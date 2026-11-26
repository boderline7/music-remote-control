package a6;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d0 implements g6.y {

    /* renamed from: a, reason: collision with root package name */
    public final boolean f381a;

    /* renamed from: b, reason: collision with root package name */
    public final g6.h f382b = new g6.h();

    /* renamed from: c, reason: collision with root package name */
    public boolean f383c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g0 f384d;

    public d0(g0 g0Var, boolean z6) {
        this.f384d = g0Var;
        this.f381a = z6;
    }

    public final void A(boolean z6) {
        long min;
        boolean z7;
        g0 g0Var = this.f384d;
        synchronized (g0Var) {
            g0Var.f422l.h();
            while (g0Var.f415e >= g0Var.f416f && !this.f381a && !this.f383c) {
                try {
                    synchronized (g0Var) {
                        b bVar = g0Var.f423m;
                        if (bVar != null) {
                            break;
                        } else {
                            g0Var.k();
                        }
                    }
                } catch (Throwable th) {
                    g0Var.f422l.l();
                    throw th;
                }
            }
            g0Var.f422l.l();
            g0Var.b();
            min = Math.min(g0Var.f416f - g0Var.f415e, this.f382b.f6573b);
            g0Var.f415e += min;
            z7 = z6 && min == this.f382b.f6573b;
        }
        this.f384d.f422l.h();
        try {
            g0 g0Var2 = this.f384d;
            g0Var2.f412b.G(g0Var2.f411a, z7, this.f382b, min);
        } finally {
            this.f384d.f422l.l();
        }
    }

    @Override // g6.y
    public final g6.d0 a() {
        return this.f384d.f422l;
    }

    @Override // g6.y, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        boolean z6;
        g0 g0Var = this.f384d;
        if (t5.b.assertionsEnabled && Thread.holdsLock(g0Var)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + g0Var);
        }
        g0 g0Var2 = this.f384d;
        synchronized (g0Var2) {
            if (this.f383c) {
                return;
            }
            synchronized (g0Var2) {
                z6 = g0Var2.f423m == null;
            }
            g0 g0Var3 = this.f384d;
            if (!g0Var3.f420j.f381a) {
                if (this.f382b.f6573b > 0) {
                    while (this.f382b.f6573b > 0) {
                        A(true);
                    }
                } else if (z6) {
                    g0Var3.f412b.G(g0Var3.f411a, true, null, 0L);
                }
            }
            synchronized (this.f384d) {
                this.f383c = true;
            }
            this.f384d.f412b.flush();
            this.f384d.a();
        }
    }

    @Override // g6.y, java.io.Flushable
    public final void flush() {
        g0 g0Var = this.f384d;
        if (t5.b.assertionsEnabled && Thread.holdsLock(g0Var)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + g0Var);
        }
        g0 g0Var2 = this.f384d;
        synchronized (g0Var2) {
            g0Var2.b();
        }
        while (this.f382b.f6573b > 0) {
            A(false);
            this.f384d.f412b.flush();
        }
    }

    @Override // g6.y
    public final void z(g6.h hVar, long j7) {
        i5.a.g("source", hVar);
        if (t5.b.assertionsEnabled) {
            g0 g0Var = this.f384d;
            if (Thread.holdsLock(g0Var)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + g0Var);
            }
        }
        g6.h hVar2 = this.f382b;
        hVar2.z(hVar, j7);
        while (hVar2.f6573b >= 16384) {
            A(false);
        }
    }
}
