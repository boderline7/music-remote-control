package y1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends h0 {

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f10037f;

    /* renamed from: g, reason: collision with root package name */
    public final TreeMap f10038g;

    /* renamed from: h, reason: collision with root package name */
    public Object f10039h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(f fVar, int i7) {
        super("call_site_ids", fVar, 4);
        this.f10037f = i7;
        if (i7 != 1) {
            this.f10038g = new TreeMap();
            this.f10039h = new TreeMap();
        } else {
            super("class_defs", fVar, 4);
            this.f10038g = new TreeMap();
            this.f10039h = null;
        }
    }

    @Override // y1.c0
    public final Collection c() {
        TreeMap treeMap = this.f10038g;
        switch (this.f10037f) {
            case 0:
                break;
            default:
                ArrayList arrayList = (ArrayList) this.f10039h;
                if (arrayList == null) {
                    break;
                }
                break;
        }
        return treeMap.values();
    }

    @Override // y1.h0
    public final void k() {
        TreeMap treeMap = this.f10038g;
        switch (this.f10037f) {
            case 0:
                Iterator it = treeMap.values().iterator();
                if (it.hasNext()) {
                    a.a.u(it.next());
                    throw null;
                }
                return;
            default:
                int size = treeMap.size();
                this.f10039h = new ArrayList(size);
                Iterator it2 = treeMap.keySet().iterator();
                int i7 = 0;
                while (it2.hasNext()) {
                    i7 = l((c2.c) it2.next(), i7, size - i7);
                }
                return;
        }
    }

    public final int l(c2.c cVar, int i7, int i8) {
        d dVar = (d) this.f10038g.get(cVar);
        if (dVar == null || dVar.f10101a >= 0) {
            return i7;
        }
        if (i8 < 0) {
            throw new RuntimeException("class circularity with " + cVar);
        }
        int i9 = i8 - 1;
        b2.v vVar = dVar.f10058d;
        if (vVar != null) {
            i7 = l(vVar.f2943a, i7, i9);
        }
        g0 g0Var = dVar.f10059e;
        c2.e eVar = g0Var == null ? c2.b.EMPTY : g0Var.f10092e;
        int length = ((e2.d) eVar).f6354b.length;
        for (int i10 = 0; i10 < length; i10++) {
            i7 = l(eVar.a(i10), i7, i9);
        }
        dVar.g(i7);
        ((ArrayList) this.f10039h).add(dVar);
        return i7 + 1;
    }
}
