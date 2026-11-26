package androidx.appcompat.app;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q0 implements Executor {

    /* renamed from: a, reason: collision with root package name */
    public final Object f847a = new Object();

    /* renamed from: b, reason: collision with root package name */
    public final ArrayDeque f848b = new ArrayDeque();

    /* renamed from: c, reason: collision with root package name */
    public final Executor f849c;

    /* renamed from: d, reason: collision with root package name */
    public Runnable f850d;

    public q0(r0 r0Var) {
        this.f849c = r0Var;
    }

    public final void b() {
        synchronized (this.f847a) {
            try {
                Runnable runnable = (Runnable) this.f848b.poll();
                this.f850d = runnable;
                if (runnable != null) {
                    this.f849c.execute(runnable);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        synchronized (this.f847a) {
            try {
                this.f848b.add(new p0(0, this, runnable));
                if (this.f850d == null) {
                    b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
