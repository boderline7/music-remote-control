package v;

import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class i extends h {

    /* renamed from: m, reason: collision with root package name */
    public int f9351m;

    public i(v vVar) {
        super(vVar);
        if (vVar instanceof n) {
            this.f9343e = g.HORIZONTAL_DIMENSION;
        } else {
            this.f9343e = g.VERTICAL_DIMENSION;
        }
    }

    @Override // v.h
    public final void d(int i7) {
        if (this.f9348j) {
            return;
        }
        this.f9348j = true;
        this.f9345g = i7;
        Iterator it = this.f9349k.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            eVar.a(eVar);
        }
    }
}
