package g6;

import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {
    public static f a() {
        f fVar = f.f6566k;
        i5.a.d(fVar);
        f fVar2 = fVar.f6568e;
        if (fVar2 == null) {
            long nanoTime = System.nanoTime();
            f.f6563h.await(f.f6564i, TimeUnit.MILLISECONDS);
            f fVar3 = f.f6566k;
            i5.a.d(fVar3);
            if (fVar3.f6568e != null || System.nanoTime() - nanoTime < f.f6565j) {
                return null;
            }
            return f.f6566k;
        }
        long nanoTime2 = fVar2.f6569f - System.nanoTime();
        if (nanoTime2 > 0) {
            f.f6563h.await(nanoTime2, TimeUnit.NANOSECONDS);
            return null;
        }
        f fVar4 = f.f6566k;
        i5.a.d(fVar4);
        fVar4.f6568e = fVar2.f6568e;
        fVar2.f6568e = null;
        return fVar2;
    }
}
