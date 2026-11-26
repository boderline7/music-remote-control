package i6;

import java.util.logging.Level;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final i f6814a = new i(22);

    /* renamed from: b, reason: collision with root package name */
    public final e f6815b;

    /* renamed from: c, reason: collision with root package name */
    public volatile boolean f6816c;

    public a(e eVar) {
        this.f6815b = eVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        while (true) {
            try {
                try {
                    j u6 = this.f6814a.u();
                    if (u6 == null) {
                        synchronized (this) {
                            u6 = this.f6814a.t();
                            if (u6 == null) {
                                this.f6816c = false;
                                this.f6816c = false;
                                return;
                            }
                        }
                    }
                    this.f6815b.c(u6);
                } catch (InterruptedException e7) {
                    this.f6815b.f6840p.f(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e7);
                    this.f6816c = false;
                    return;
                }
            } catch (Throwable th) {
                this.f6816c = false;
                throw th;
            }
        }
    }
}
