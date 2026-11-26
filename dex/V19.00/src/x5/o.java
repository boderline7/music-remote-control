package x5;

import b6.s;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o {
    public static final n Companion = new n();

    /* renamed from: a, reason: collision with root package name */
    public final int f9720a;

    /* renamed from: b, reason: collision with root package name */
    public final long f9721b;

    /* renamed from: c, reason: collision with root package name */
    public final w5.c f9722c;

    /* renamed from: d, reason: collision with root package name */
    public final w5.b f9723d;

    /* renamed from: e, reason: collision with root package name */
    public final ConcurrentLinkedQueue f9724e;

    public o(w5.g gVar, TimeUnit timeUnit) {
        i5.a.g("taskRunner", gVar);
        i5.a.g("timeUnit", timeUnit);
        this.f9720a = 5;
        this.f9721b = timeUnit.toNanos(5L);
        this.f9722c = gVar.f();
        this.f9723d = new w5.b(this, a.a.i(new StringBuilder(), t5.b.okHttpName, " ConnectionPool"));
        this.f9724e = new ConcurrentLinkedQueue();
    }

    public final boolean a(s5.a aVar, j jVar, List list, boolean z6) {
        i5.a.g("address", aVar);
        i5.a.g("call", jVar);
        Iterator it = this.f9724e.iterator();
        while (it.hasNext()) {
            m mVar = (m) it.next();
            i5.a.f("connection", mVar);
            synchronized (mVar) {
                if (z6) {
                    if (mVar.f9709f == null) {
                        continue;
                    }
                }
                if (mVar.h(aVar, list)) {
                    jVar.b(mVar);
                    return true;
                }
            }
        }
        return false;
    }

    public final int b(m mVar, long j7) {
        if (t5.b.assertionsEnabled && !Thread.holdsLock(mVar)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + mVar);
        }
        ArrayList arrayList = mVar.f9718o;
        int i7 = 0;
        while (i7 < arrayList.size()) {
            Reference reference = (Reference) arrayList.get(i7);
            if (reference.get() != null) {
                i7++;
            } else {
                String str = "A connection to " + mVar.f9704a.f8764a.f8755i + " was leaked. Did you forget to close a response body?";
                s.Companion.getClass();
                s.f3002a.j(str, ((h) reference).f9684a);
                arrayList.remove(i7);
                mVar.f9712i = true;
                if (arrayList.isEmpty()) {
                    mVar.f9719p = j7 - this.f9721b;
                    return 0;
                }
            }
        }
        return arrayList.size();
    }

    public final void c(m mVar) {
        if (!t5.b.assertionsEnabled || Thread.holdsLock(mVar)) {
            this.f9724e.add(mVar);
            this.f9722c.c(this.f9723d, 0L);
        } else {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + mVar);
        }
    }
}
