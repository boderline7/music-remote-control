package g6;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class f extends d0 {
    public static final b Companion = new b();

    /* renamed from: g, reason: collision with root package name */
    public static final ReentrantLock f6562g;

    /* renamed from: h, reason: collision with root package name */
    public static final Condition f6563h;

    /* renamed from: i, reason: collision with root package name */
    public static final long f6564i;

    /* renamed from: j, reason: collision with root package name */
    public static final long f6565j;

    /* renamed from: k, reason: collision with root package name */
    public static f f6566k;

    /* renamed from: d, reason: collision with root package name */
    public boolean f6567d;

    /* renamed from: e, reason: collision with root package name */
    public f f6568e;

    /* renamed from: f, reason: collision with root package name */
    public long f6569f;

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        f6562g = reentrantLock;
        Condition newCondition = reentrantLock.newCondition();
        i5.a.f("newCondition(...)", newCondition);
        f6563h = newCondition;
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f6564i = millis;
        f6565j = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    public final void h() {
        f fVar;
        long j7 = this.f6558c;
        boolean z6 = this.f6556a;
        if (j7 != 0 || z6) {
            Companion.getClass();
            ReentrantLock reentrantLock = f6562g;
            reentrantLock.lock();
            try {
                if (!(!this.f6567d)) {
                    throw new IllegalStateException("Unbalanced enter/exit".toString());
                }
                this.f6567d = true;
                if (f6566k == null) {
                    f6566k = new f();
                    c cVar = new c("Okio Watchdog");
                    cVar.setDaemon(true);
                    cVar.start();
                }
                long nanoTime = System.nanoTime();
                if (j7 != 0 && z6) {
                    this.f6569f = Math.min(j7, c() - nanoTime) + nanoTime;
                } else if (j7 != 0) {
                    this.f6569f = j7 + nanoTime;
                } else {
                    if (!z6) {
                        throw new AssertionError();
                    }
                    this.f6569f = c();
                }
                long j8 = this.f6569f - nanoTime;
                f fVar2 = f6566k;
                i5.a.d(fVar2);
                while (true) {
                    fVar = fVar2.f6568e;
                    if (fVar == null || j8 < fVar.f6569f - nanoTime) {
                        break;
                    } else {
                        fVar2 = fVar;
                    }
                }
                this.f6568e = fVar;
                fVar2.f6568e = this;
                if (fVar2 == f6566k) {
                    Companion.getClass();
                    f6563h.signal();
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
    }

    public final boolean i() {
        b bVar = Companion;
        bVar.getClass();
        bVar.getClass();
        ReentrantLock reentrantLock = f6562g;
        reentrantLock.lock();
        try {
            if (!this.f6567d) {
                return false;
            }
            this.f6567d = false;
            f fVar = f6566k;
            while (fVar != null) {
                f fVar2 = fVar.f6568e;
                if (fVar2 == this) {
                    fVar.f6568e = this.f6568e;
                    this.f6568e = null;
                    return false;
                }
                fVar = fVar2;
            }
            reentrantLock.unlock();
            return true;
        } finally {
            reentrantLock.unlock();
        }
    }

    public IOException j(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public void k() {
    }
}
