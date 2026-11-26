package w5;

import java.util.logging.Level;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f9511a;

    public f(g gVar) {
        this.f9511a = gVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a c7;
        long j7;
        while (true) {
            g gVar = this.f9511a;
            synchronized (gVar) {
                c7 = gVar.c();
            }
            if (c7 == null) {
                return;
            }
            c cVar = c7.f9500c;
            i5.a.d(cVar);
            g gVar2 = this.f9511a;
            g.Companion.getClass();
            boolean isLoggable = g.f9512h.isLoggable(Level.FINE);
            if (isLoggable) {
                cVar.f9504a.f9513a.getClass();
                j7 = System.nanoTime();
                i5.a.a(c7, cVar, "starting");
            } else {
                j7 = -1;
            }
            try {
                g.a(gVar2, c7);
                if (isLoggable) {
                    cVar.f9504a.f9513a.getClass();
                    i5.a.a(c7, cVar, "finished run in ".concat(i5.a.l(System.nanoTime() - j7)));
                }
            } finally {
            }
        }
    }
}
