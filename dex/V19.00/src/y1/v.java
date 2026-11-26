package y1;

import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v extends s {
    @Override // y1.p
    public final void a(f fVar) {
        fVar.f10078g.q(this.f10100b);
        fVar.f10077f.p(this.f10110c.f2937b.f2938a);
        c2.a aVar = ((b2.d) this.f10110c).f2931c;
        u uVar = fVar.f10079h;
        synchronized (uVar) {
            if (aVar == null) {
                throw new NullPointerException("prototype == null");
            }
            uVar.g();
            if (((b0) ((TreeMap) uVar.f10112g).get(aVar)) == null) {
                ((TreeMap) uVar.f10112g).put(aVar, new b0(aVar));
            }
        }
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_METHOD_ID_ITEM;
    }

    @Override // y1.s
    public final int h(f fVar) {
        u uVar = fVar.f10079h;
        c2.a aVar = ((b2.d) this.f10110c).f2931c;
        if (aVar == null) {
            uVar.getClass();
            throw new NullPointerException("prototype == null");
        }
        uVar.f();
        b0 b0Var = (b0) ((TreeMap) uVar.f10112g).get(aVar);
        if (b0Var != null) {
            return b0Var.e();
        }
        throw new IllegalArgumentException("not found");
    }

    @Override // y1.s
    public final String i() {
        return "proto_idx";
    }
}
