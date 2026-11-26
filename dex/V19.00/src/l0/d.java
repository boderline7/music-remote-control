package l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    public boolean f7269a;

    /* renamed from: b, reason: collision with root package name */
    public c f7270b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f7271c;

    public final void a() {
        synchronized (this) {
            try {
                if (this.f7269a) {
                    return;
                }
                this.f7269a = true;
                this.f7271c = true;
                c cVar = this.f7270b;
                if (cVar != null) {
                    try {
                        cVar.h();
                    } catch (Throwable th) {
                        synchronized (this) {
                            this.f7271c = false;
                            notifyAll();
                            throw th;
                        }
                    }
                }
                synchronized (this) {
                    this.f7271c = false;
                    notifyAll();
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public final void b(c cVar) {
        synchronized (this) {
            while (this.f7271c) {
                try {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (this.f7270b == cVar) {
                return;
            }
            this.f7270b = cVar;
            if (this.f7269a) {
                cVar.h();
            }
        }
    }
}
