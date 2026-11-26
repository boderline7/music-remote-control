package a6;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e0 implements g6.a0 {

    /* renamed from: a, reason: collision with root package name */
    public final long f393a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f394b;

    /* renamed from: c, reason: collision with root package name */
    public final g6.h f395c = new g6.h();

    /* renamed from: d, reason: collision with root package name */
    public final g6.h f396d = new g6.h();

    /* renamed from: e, reason: collision with root package name */
    public boolean f397e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ g0 f398f;

    public e0(g0 g0Var, long j7, boolean z6) {
        this.f398f = g0Var;
        this.f393a = j7;
        this.f394b = z6;
    }

    public final void A(long j7) {
        boolean z6 = t5.b.assertionsEnabled;
        g0 g0Var = this.f398f;
        if (!z6 || !Thread.holdsLock(g0Var)) {
            g0Var.f412b.F(j7);
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + g0Var);
    }

    @Override // g6.a0
    public final g6.d0 a() {
        return this.f398f.f421k;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        long j7;
        g0 g0Var = this.f398f;
        synchronized (g0Var) {
            this.f397e = true;
            g6.h hVar = this.f396d;
            j7 = hVar.f6573b;
            hVar.h(j7);
            g0Var.notifyAll();
        }
        if (j7 > 0) {
            A(j7);
        }
        this.f398f.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x008a A[LOOP:0: B:3:0x0010->B:41:0x008a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008d A[SYNTHETIC] */
    @Override // g6.a0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long k(g6.h r16, long r17) {
        /*
            Method dump skipped, instructions count: 196
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a6.e0.k(g6.h, long):long");
    }
}
