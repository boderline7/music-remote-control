package w5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public final g f9504a;

    /* renamed from: b, reason: collision with root package name */
    public final String f9505b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9506c;

    /* renamed from: d, reason: collision with root package name */
    public a f9507d;

    /* renamed from: e, reason: collision with root package name */
    public final ArrayList f9508e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f9509f;

    public c(g gVar, String str) {
        i5.a.g("taskRunner", gVar);
        i5.a.g("name", str);
        this.f9504a = gVar;
        this.f9505b = str;
        this.f9508e = new ArrayList();
    }

    public final void a() {
        if (t5.b.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        synchronized (this.f9504a) {
            if (b()) {
                this.f9504a.e(this);
            }
        }
    }

    public final boolean b() {
        a aVar = this.f9507d;
        if (aVar != null && aVar.f9499b) {
            this.f9509f = true;
        }
        ArrayList arrayList = this.f9508e;
        boolean z6 = false;
        for (int size = arrayList.size() - 1; -1 < size; size--) {
            if (((a) arrayList.get(size)).f9499b) {
                a aVar2 = (a) arrayList.get(size);
                g.Companion.getClass();
                if (g.f9512h.isLoggable(Level.FINE)) {
                    i5.a.a(aVar2, this, "canceled");
                }
                arrayList.remove(size);
                z6 = true;
            }
        }
        return z6;
    }

    public final void c(a aVar, long j7) {
        i5.a.g("task", aVar);
        synchronized (this.f9504a) {
            if (!this.f9506c) {
                if (d(aVar, j7, false)) {
                    this.f9504a.e(this);
                }
            } else if (aVar.f9499b) {
                g.Companion.getClass();
                if (g.f9512h.isLoggable(Level.FINE)) {
                    i5.a.a(aVar, this, "schedule canceled (queue is shutdown)");
                }
            } else {
                g.Companion.getClass();
                if (g.f9512h.isLoggable(Level.FINE)) {
                    i5.a.a(aVar, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
        }
    }

    public final boolean d(a aVar, long j7, boolean z6) {
        i5.a.g("task", aVar);
        c cVar = aVar.f9500c;
        if (cVar != this) {
            if (cVar != null) {
                throw new IllegalStateException("task is in multiple queues".toString());
            }
            aVar.f9500c = this;
        }
        this.f9504a.f9513a.getClass();
        long nanoTime = System.nanoTime();
        long j8 = nanoTime + j7;
        ArrayList arrayList = this.f9508e;
        int indexOf = arrayList.indexOf(aVar);
        if (indexOf != -1) {
            if (aVar.f9501d <= j8) {
                g.Companion.getClass();
                if (g.f9512h.isLoggable(Level.FINE)) {
                    i5.a.a(aVar, this, "already scheduled");
                }
                return false;
            }
            arrayList.remove(indexOf);
        }
        aVar.f9501d = j8;
        g.Companion.getClass();
        if (g.f9512h.isLoggable(Level.FINE)) {
            i5.a.a(aVar, this, z6 ? "run again after ".concat(i5.a.l(j8 - nanoTime)) : "scheduled after ".concat(i5.a.l(j8 - nanoTime)));
        }
        Iterator it = arrayList.iterator();
        int i7 = 0;
        while (true) {
            if (!it.hasNext()) {
                i7 = -1;
                break;
            }
            if (((a) it.next()).f9501d - nanoTime > j7) {
                break;
            }
            i7++;
        }
        if (i7 == -1) {
            i7 = arrayList.size();
        }
        arrayList.add(i7, aVar);
        return i7 == 0;
    }

    public final void e() {
        if (t5.b.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        synchronized (this.f9504a) {
            this.f9506c = true;
            if (b()) {
                this.f9504a.e(this);
            }
        }
    }

    public final String toString() {
        return this.f9505b;
    }
}
