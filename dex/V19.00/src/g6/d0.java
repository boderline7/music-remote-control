package g6;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class d0 {
    public static final c0 Companion = new c0();
    public static final d0 NONE = new b0();

    /* renamed from: a, reason: collision with root package name */
    public boolean f6556a;

    /* renamed from: b, reason: collision with root package name */
    public long f6557b;

    /* renamed from: c, reason: collision with root package name */
    public long f6558c;

    public d0 a() {
        this.f6556a = false;
        return this;
    }

    public d0 b() {
        this.f6558c = 0L;
        return this;
    }

    public long c() {
        if (this.f6556a) {
            return this.f6557b;
        }
        throw new IllegalStateException("No deadline".toString());
    }

    public d0 d(long j7) {
        this.f6556a = true;
        this.f6557b = j7;
        return this;
    }

    public boolean e() {
        return this.f6556a;
    }

    public void f() {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        if (this.f6556a && this.f6557b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public d0 g(long j7, TimeUnit timeUnit) {
        i5.a.g("unit", timeUnit);
        if (j7 >= 0) {
            this.f6558c = timeUnit.toNanos(j7);
            return this;
        }
        throw new IllegalArgumentException(("timeout < 0: " + j7).toString());
    }
}
