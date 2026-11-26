package y1;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h0 extends c0 {
    @Override // y1.c0
    public final int a(p pVar) {
        o oVar = (o) pVar;
        int c7 = oVar.c() * oVar.e();
        if (c7 < 0) {
            throw new IllegalArgumentException("relative < 0");
        }
        int i7 = this.f10054d;
        if (i7 >= 0) {
            return i7 + c7;
        }
        throw new RuntimeException("fileOffset not yet set");
    }

    @Override // y1.c0
    public final void e() {
        k();
        Iterator it = c().iterator();
        while (it.hasNext()) {
            ((p) it.next()).a(this.f10052b);
        }
    }

    @Override // y1.c0
    public final int h() {
        Collection c7 = c();
        int size = c7.size();
        if (size == 0) {
            return 0;
        }
        return ((p) c7.iterator().next()).c() * size;
    }

    @Override // y1.c0
    public final void j(e2.c cVar) {
        Iterator it = c().iterator();
        while (it.hasNext()) {
            ((p) it.next()).d(this.f10052b, cVar);
            cVar.a(this.f10053c);
        }
    }

    public abstract void k();
}
