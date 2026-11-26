package x5;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import n1.z;
import s5.h0;
import y.s;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final s5.g f9681a;

    /* renamed from: b, reason: collision with root package name */
    public volatile AtomicInteger f9682b = new AtomicInteger(0);

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ j f9683c;

    public g(j jVar, s5.g gVar) {
        this.f9683c = jVar;
        this.f9681a = gVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        s sVar;
        String str = "OkHttp " + ((h0) this.f9683c.f9687b.f1482b).f();
        j jVar = this.f9683c;
        Thread currentThread = Thread.currentThread();
        String name = currentThread.getName();
        currentThread.setName(str);
        try {
            jVar.f9691f.h();
            boolean z6 = false;
            try {
                try {
                    try {
                        this.f9681a.onResponse(jVar, jVar.h());
                        sVar = jVar.f9686a.f8842a;
                    } catch (IOException e7) {
                        e = e7;
                        z6 = true;
                        if (z6) {
                            b6.s.Companion.getClass();
                            b6.s sVar2 = b6.s.f3002a;
                            String str2 = "Callback failure for " + j.a(jVar);
                            sVar2.getClass();
                            b6.s.i(str2, 4, e);
                        } else {
                            this.f9681a.onFailure(jVar, e);
                        }
                        sVar = jVar.f9686a.f8842a;
                        sVar.g(this);
                    } catch (Throwable th) {
                        th = th;
                        z6 = true;
                        jVar.d();
                        if (!z6) {
                            IOException iOException = new IOException("canceled due to " + th);
                            z.e(iOException, th);
                            this.f9681a.onFailure(jVar, iOException);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    jVar.f9686a.f8842a.g(this);
                    throw th2;
                }
            } catch (IOException e8) {
                e = e8;
            } catch (Throwable th3) {
                th = th3;
            }
            sVar.g(this);
        } finally {
            currentThread.setName(name);
        }
    }
}
