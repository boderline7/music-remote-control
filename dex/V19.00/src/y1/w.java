package y1;

import java.util.Collection;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w extends t {

    /* renamed from: f, reason: collision with root package name */
    public final TreeMap f10113f;

    public w(f fVar) {
        super("method_ids", fVar, 4);
        this.f10113f = new TreeMap();
    }

    @Override // y1.c0
    public final Collection c() {
        return this.f10113f.values();
    }

    public final int l(b2.d dVar) {
        if (dVar == null) {
            throw new NullPointerException("ref == null");
        }
        f();
        v vVar = (v) this.f10113f.get(dVar);
        if (vVar != null) {
            return vVar.e();
        }
        throw new IllegalArgumentException("not found");
    }

    public final synchronized void m(b2.d dVar) {
        if (dVar == null) {
            throw new NullPointerException("method == null");
        }
        g();
        if (((v) this.f10113f.get(dVar)) == null) {
            this.f10113f.put(dVar, new v(dVar));
        }
    }
}
