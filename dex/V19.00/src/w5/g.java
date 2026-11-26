package w5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {
    public static final d Companion = new d();
    public static final g INSTANCE;

    /* renamed from: h, reason: collision with root package name */
    public static final Logger f9512h;

    /* renamed from: a, reason: collision with root package name */
    public final e f9513a;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9515c;

    /* renamed from: d, reason: collision with root package name */
    public long f9516d;

    /* renamed from: b, reason: collision with root package name */
    public int f9514b = 10000;

    /* renamed from: e, reason: collision with root package name */
    public final ArrayList f9517e = new ArrayList();

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f9518f = new ArrayList();

    /* renamed from: g, reason: collision with root package name */
    public final f f9519g = new f(this);

    static {
        String str = t5.b.okHttpName + " TaskRunner";
        i5.a.g("name", str);
        INSTANCE = new g(new e(new t5.a(str, true)));
        Logger logger = Logger.getLogger(g.class.getName());
        i5.a.f("getLogger(TaskRunner::class.java.name)", logger);
        f9512h = logger;
    }

    public g(e eVar) {
        this.f9513a = eVar;
    }

    public static final void a(g gVar, a aVar) {
        gVar.getClass();
        if (t5.b.assertionsEnabled && Thread.holdsLock(gVar)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + gVar);
        }
        Thread currentThread = Thread.currentThread();
        String name = currentThread.getName();
        currentThread.setName(aVar.f9498a);
        try {
            long a7 = aVar.a();
            synchronized (gVar) {
                gVar.b(aVar, a7);
            }
            currentThread.setName(name);
        } catch (Throwable th) {
            synchronized (gVar) {
                gVar.b(aVar, -1L);
                currentThread.setName(name);
                throw th;
            }
        }
    }

    public final void b(a aVar, long j7) {
        if (t5.b.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
        }
        c cVar = aVar.f9500c;
        i5.a.d(cVar);
        if (cVar.f9507d != aVar) {
            throw new IllegalStateException("Check failed.".toString());
        }
        boolean z6 = cVar.f9509f;
        cVar.f9509f = false;
        cVar.f9507d = null;
        this.f9517e.remove(cVar);
        if (j7 != -1 && !z6 && !cVar.f9506c) {
            cVar.d(aVar, j7, true);
        }
        if (!cVar.f9508e.isEmpty()) {
            this.f9518f.add(cVar);
        }
    }

    public final a c() {
        long j7;
        boolean z6;
        boolean z7;
        long j8;
        long j9;
        if (t5.b.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
        }
        while (true) {
            ArrayList arrayList = this.f9518f;
            if (arrayList.isEmpty()) {
                return null;
            }
            e eVar = this.f9513a;
            eVar.getClass();
            long nanoTime = System.nanoTime();
            Iterator it = arrayList.iterator();
            long j10 = Long.MAX_VALUE;
            a aVar = null;
            while (true) {
                if (!it.hasNext()) {
                    j7 = nanoTime;
                    z6 = false;
                    break;
                }
                a aVar2 = (a) ((c) it.next()).f9508e.get(0);
                j7 = nanoTime;
                long max = Math.max(0L, aVar2.f9501d - nanoTime);
                if (max > 0) {
                    j10 = Math.min(max, j10);
                } else {
                    if (aVar != null) {
                        z6 = true;
                        break;
                    }
                    aVar = aVar2;
                }
                nanoTime = j7;
            }
            if (aVar != null) {
                if (t5.b.assertionsEnabled && !Thread.holdsLock(this)) {
                    throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
                }
                aVar.f9501d = -1L;
                c cVar = aVar.f9500c;
                i5.a.d(cVar);
                cVar.f9508e.remove(aVar);
                arrayList.remove(cVar);
                cVar.f9507d = aVar;
                this.f9517e.add(cVar);
                if (z6 || (!this.f9515c && (!arrayList.isEmpty()))) {
                    f fVar = this.f9519g;
                    i5.a.g("runnable", fVar);
                    eVar.f9510a.execute(fVar);
                }
                return aVar;
            }
            if (this.f9515c) {
                if (j10 >= this.f9516d - j7) {
                    return null;
                }
                notify();
                return null;
            }
            this.f9515c = true;
            this.f9516d = j7 + j10;
            try {
                try {
                    j8 = j10 / 1000000;
                    j9 = j10 - (1000000 * j8);
                } catch (InterruptedException unused) {
                    d();
                    z7 = false;
                }
                if (j8 <= 0) {
                    if (j10 > 0) {
                    }
                    z7 = false;
                    this.f9515c = z7;
                }
                wait(j8, (int) j9);
                z7 = false;
                this.f9515c = z7;
            } finally {
                this.f9515c = false;
            }
        }
    }

    public final void d() {
        ArrayList arrayList = this.f9517e;
        int size = arrayList.size();
        while (true) {
            size--;
            if (-1 >= size) {
                break;
            } else {
                ((c) arrayList.get(size)).b();
            }
        }
        ArrayList arrayList2 = this.f9518f;
        for (int size2 = arrayList2.size() - 1; -1 < size2; size2--) {
            c cVar = (c) arrayList2.get(size2);
            cVar.b();
            if (cVar.f9508e.isEmpty()) {
                arrayList2.remove(size2);
            }
        }
    }

    public final void e(c cVar) {
        i5.a.g("taskQueue", cVar);
        if (t5.b.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
        }
        if (cVar.f9507d == null) {
            boolean z6 = !cVar.f9508e.isEmpty();
            ArrayList arrayList = this.f9518f;
            if (z6) {
                i5.a.g("<this>", arrayList);
                if (!arrayList.contains(cVar)) {
                    arrayList.add(cVar);
                }
            } else {
                arrayList.remove(cVar);
            }
        }
        boolean z7 = this.f9515c;
        e eVar = this.f9513a;
        if (z7) {
            eVar.getClass();
            notify();
        } else {
            eVar.getClass();
            f fVar = this.f9519g;
            i5.a.g("runnable", fVar);
            eVar.f9510a.execute(fVar);
        }
    }

    public final c f() {
        int i7;
        synchronized (this) {
            i7 = this.f9514b;
            this.f9514b = i7 + 1;
        }
        return new c(this, a.a.d("Q", i7));
    }
}
