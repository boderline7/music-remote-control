package y1;

import java.util.Collection;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends t {

    /* renamed from: f, reason: collision with root package name */
    public final TreeMap f10099f;

    public l(f fVar) {
        super("field_ids", fVar, 4);
        this.f10099f = new TreeMap();
    }

    @Override // y1.c0
    public final Collection c() {
        return this.f10099f.values();
    }

    public final int l(b2.i iVar) {
        if (iVar == null) {
            throw new NullPointerException("ref == null");
        }
        f();
        k kVar = (k) this.f10099f.get(iVar);
        if (kVar != null) {
            return kVar.e();
        }
        throw new IllegalArgumentException("not found");
    }

    public final synchronized void m(b2.i iVar) {
        if (iVar == null) {
            throw new NullPointerException("field == null");
        }
        g();
        if (((k) this.f10099f.get(iVar)) == null) {
            this.f10099f.put(iVar, new k(iVar));
        }
    }
}
