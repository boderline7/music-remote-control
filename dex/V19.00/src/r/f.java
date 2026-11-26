package r;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import n1.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class f implements Future {

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f8501d = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* renamed from: e, reason: collision with root package name */
    public static final Logger f8502e = Logger.getLogger(f.class.getName());

    /* renamed from: f, reason: collision with root package name */
    public static final l6.f f8503f;

    /* renamed from: g, reason: collision with root package name */
    public static final Object f8504g;

    /* renamed from: a, reason: collision with root package name */
    public volatile Object f8505a;

    /* renamed from: b, reason: collision with root package name */
    public volatile c f8506b;

    /* renamed from: c, reason: collision with root package name */
    public volatile e f8507c;

    static {
        l6.f iVar;
        try {
            iVar = new d(AtomicReferenceFieldUpdater.newUpdater(e.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(e.class, e.class, "b"), AtomicReferenceFieldUpdater.newUpdater(f.class, e.class, "c"), AtomicReferenceFieldUpdater.newUpdater(f.class, c.class, "b"), AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "a"));
            th = null;
        } catch (Throwable th) {
            th = th;
            iVar = new i();
        }
        f8503f = iVar;
        if (th != null) {
            f8502e.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f8504g = new Object();
    }

    public static void b(f fVar) {
        e eVar;
        c cVar;
        do {
            eVar = fVar.f8507c;
        } while (!f8503f.l(fVar, eVar, e.f8498c));
        while (eVar != null) {
            Thread thread = eVar.f8499a;
            if (thread != null) {
                eVar.f8499a = null;
                LockSupport.unpark(thread);
            }
            eVar = eVar.f8500b;
        }
        do {
            cVar = fVar.f8506b;
        } while (!f8503f.j(fVar, cVar));
        c cVar2 = null;
        while (cVar != null) {
            c cVar3 = cVar.f8492a;
            cVar.f8492a = cVar2;
            cVar2 = cVar;
            cVar = cVar3;
        }
        while (cVar2 != null) {
            cVar2 = cVar2.f8492a;
            try {
                throw null;
            } catch (RuntimeException e7) {
                f8502e.log(Level.SEVERE, "RuntimeException while executing runnable null with executor null", (Throwable) e7);
            }
        }
    }

    public static Object c(Object obj) {
        if (obj instanceof a) {
            Throwable th = ((a) obj).f8490a;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof b) {
            ((b) obj).getClass();
            throw new ExecutionException((Throwable) null);
        }
        if (obj == f8504g) {
            return null;
        }
        return obj;
    }

    public final void a(StringBuilder sb) {
        Object obj;
        boolean z6 = false;
        while (true) {
            try {
                try {
                    obj = get();
                    break;
                } catch (InterruptedException unused) {
                    z6 = true;
                } catch (Throwable th) {
                    if (z6) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            } catch (CancellationException unused2) {
                sb.append("CANCELLED");
                return;
            } catch (RuntimeException e7) {
                sb.append("UNKNOWN, cause=[");
                sb.append(e7.getClass());
                sb.append(" thrown from get()]");
                return;
            } catch (ExecutionException e8) {
                sb.append("FAILURE, cause=[");
                sb.append(e8.getCause());
                sb.append("]");
                return;
            }
        }
        if (z6) {
            Thread.currentThread().interrupt();
        }
        sb.append("SUCCESS, result=[");
        sb.append(obj == this ? "this future" : String.valueOf(obj));
        sb.append("]");
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z6) {
        Object obj = this.f8505a;
        if (obj == null) {
            if (f8503f.k(this, obj, f8501d ? new a(z6, new CancellationException("Future.cancel() was called.")) : z6 ? a.f8488b : a.f8489c)) {
                b(this);
                return true;
            }
        }
        return false;
    }

    public final void d(e eVar) {
        eVar.f8499a = null;
        while (true) {
            e eVar2 = this.f8507c;
            if (eVar2 == e.f8498c) {
                return;
            }
            e eVar3 = null;
            while (eVar2 != null) {
                e eVar4 = eVar2.f8500b;
                if (eVar2.f8499a != null) {
                    eVar3 = eVar2;
                } else if (eVar3 != null) {
                    eVar3.f8500b = eVar4;
                    if (eVar3.f8499a == null) {
                        break;
                    }
                } else if (!f8503f.l(this, eVar2, eVar4)) {
                    break;
                }
                eVar2 = eVar4;
            }
            return;
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.f8505a;
        if ((obj2 != null) && true) {
            return c(obj2);
        }
        e eVar = this.f8507c;
        e eVar2 = e.f8498c;
        if (eVar != eVar2) {
            e eVar3 = new e();
            do {
                l6.f fVar = f8503f;
                fVar.m0(eVar3, eVar);
                if (fVar.l(this, eVar, eVar3)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            d(eVar3);
                            throw new InterruptedException();
                        }
                        obj = this.f8505a;
                    } while (!((obj != null) & true));
                    return c(obj);
                }
                eVar = this.f8507c;
            } while (eVar != eVar2);
        }
        return c(this.f8505a);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ac  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x009f -> B:33:0x006e). Please report as a decompilation issue!!! */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(long r20, java.util.concurrent.TimeUnit r22) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: r.f.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f8505a instanceof a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (this.f8505a != null) & true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.f8505a instanceof a) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                if (this instanceof ScheduledFuture) {
                    str = "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
                } else {
                    str = null;
                }
            } catch (RuntimeException e7) {
                str = "Exception thrown from implementation: " + e7.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
