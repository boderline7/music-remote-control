package n1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends l6.f {
    @Override // l6.f
    public boolean j(r.f fVar, r.c cVar) {
        r.c cVar2 = r.c.f8491b;
        synchronized (fVar) {
            try {
                if (fVar.f8506b != cVar) {
                    return false;
                }
                fVar.f8506b = cVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // l6.f
    public boolean k(r.f fVar, Object obj, Object obj2) {
        synchronized (fVar) {
            try {
                if (fVar.f8505a != obj) {
                    return false;
                }
                fVar.f8505a = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // l6.f
    public boolean l(r.f fVar, r.e eVar, r.e eVar2) {
        synchronized (fVar) {
            try {
                if (fVar.f8507c != eVar) {
                    return false;
                }
                fVar.f8507c = eVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // l6.f
    public void m0(r.e eVar, r.e eVar2) {
        eVar.f8500b = eVar2;
    }

    @Override // l6.f
    public void n0(r.e eVar, Thread thread) {
        eVar.f8499a = thread;
    }
}
