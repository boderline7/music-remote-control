package y1;

import com.android.dex.DexIndexOverflowException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u extends h0 {

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f10111f;

    /* renamed from: g, reason: collision with root package name */
    public final Object f10112g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(f fVar, int i7) {
        super("method_handles", fVar, 8);
        this.f10111f = i7;
        if (i7 == 1) {
            super("proto_ids", fVar, 4);
            this.f10112g = new TreeMap();
            return;
        }
        if (i7 == 2) {
            super("string_ids", fVar, 4);
            this.f10112g = new TreeMap();
            return;
        }
        if (i7 == 3) {
            super("type_ids", fVar, 4);
            this.f10112g = new TreeMap();
        } else if (i7 != 4) {
            this.f10112g = new TreeMap();
        } else {
            super(null, fVar, 4);
            m mVar = new m();
            mVar.g(0);
            this.f10112g = Collections.singletonList(mVar);
        }
    }

    @Override // y1.c0
    public final Collection c() {
        int i7 = this.f10111f;
        Object obj = this.f10112g;
        switch (i7) {
            case 0:
                return ((TreeMap) obj).values();
            case 1:
                return ((TreeMap) obj).values();
            case 2:
                return ((TreeMap) obj).values();
            case 3:
                return ((TreeMap) obj).values();
            default:
                return (List) obj;
        }
    }

    @Override // y1.h0
    public final void k() {
        int i7 = this.f10111f;
        Object obj = this.f10112g;
        int i8 = 0;
        switch (i7) {
            case 0:
                Iterator it = ((TreeMap) obj).values().iterator();
                if (it.hasNext()) {
                    a.a.u(it.next());
                    throw null;
                }
                return;
            case 1:
                Iterator it2 = c().iterator();
                while (it2.hasNext()) {
                    ((b0) it2.next()).g(i8);
                    i8++;
                }
                return;
            case 2:
                Iterator it3 = ((TreeMap) obj).values().iterator();
                while (it3.hasNext()) {
                    ((e0) it3.next()).g(i8);
                    i8++;
                }
                return;
            case 3:
                Iterator it4 = c().iterator();
                while (it4.hasNext()) {
                    ((f0) it4.next()).g(i8);
                    i8++;
                }
                return;
            default:
                return;
        }
    }

    public final o l(b2.a aVar) {
        int i7 = this.f10111f;
        Object obj = this.f10112g;
        switch (i7) {
            case 0:
                if (aVar == null) {
                    throw new NullPointerException("cst == null");
                }
                f();
                a.a.u(aVar);
                o oVar = (o) ((TreeMap) obj).get(null);
                if (oVar != null) {
                    return oVar;
                }
                throw new IllegalArgumentException("not found");
            case 1:
                if (aVar != null) {
                    throw new IllegalArgumentException("cst not instance of CstProtoRef");
                }
                throw new NullPointerException("cst == null");
            case 2:
                if (aVar == null) {
                    throw new NullPointerException("cst == null");
                }
                f();
                o oVar2 = (o) ((TreeMap) obj).get((b2.u) aVar);
                if (oVar2 != null) {
                    return oVar2;
                }
                throw new IllegalArgumentException("not found");
            default:
                if (aVar == null) {
                    throw new NullPointerException("cst == null");
                }
                f();
                o oVar3 = (o) ((TreeMap) obj).get(((b2.v) aVar).f2943a);
                if (oVar3 != null) {
                    return oVar3;
                }
                throw new IllegalArgumentException("not found: " + aVar);
        }
    }

    public final int m(b2.u uVar) {
        if (uVar == null) {
            throw new NullPointerException("string == null");
        }
        f();
        e0 e0Var = (e0) ((TreeMap) this.f10112g).get(uVar);
        if (e0Var != null) {
            return e0Var.e();
        }
        throw new IllegalArgumentException("not found");
    }

    public final int n(b2.v vVar) {
        if (vVar != null) {
            return o(vVar.f2943a);
        }
        throw new NullPointerException("type == null");
    }

    public final int o(c2.c cVar) {
        if (cVar == null) {
            throw new NullPointerException("type == null");
        }
        f();
        f0 f0Var = (f0) ((TreeMap) this.f10112g).get(cVar);
        if (f0Var != null) {
            return f0Var.e();
        }
        throw new IllegalArgumentException("not found: " + cVar);
    }

    public final void p(b2.u uVar) {
        e0 e0Var = new e0(uVar);
        synchronized (this) {
            g();
            if (((e0) ((TreeMap) this.f10112g).get(uVar)) != null) {
                return;
            }
            ((TreeMap) this.f10112g).put(uVar, e0Var);
        }
    }

    public final synchronized void q(b2.v vVar) {
        if (vVar == null) {
            throw new NullPointerException("type == null");
        }
        g();
        c2.c cVar = vVar.f2943a;
        if (((f0) ((TreeMap) this.f10112g).get(cVar)) == null) {
            ((TreeMap) this.f10112g).put(cVar, new f0(vVar));
        }
    }

    public final synchronized void r(c2.c cVar) {
        if (cVar == null) {
            throw new NullPointerException("type == null");
        }
        g();
        if (((f0) ((TreeMap) this.f10112g).get(cVar)) == null) {
            ((TreeMap) this.f10112g).put(cVar, new f0(new b2.v(cVar)));
        }
    }

    public final void s(e2.a aVar) {
        int b7;
        int i7 = this.f10111f;
        Object obj = this.f10112g;
        switch (i7) {
            case 1:
                f();
                int size = ((TreeMap) obj).size();
                b7 = size != 0 ? b() : 0;
                if (size > 65536) {
                    throw new UnsupportedOperationException("too many proto ids");
                }
                e2.c cVar = (e2.c) aVar;
                if (cVar.d()) {
                    cVar.b(4, "proto_ids_size:  ".concat(n1.z.m1(size)));
                    cVar.b(4, "proto_ids_off:   ".concat(n1.z.m1(b7)));
                }
                cVar.j(size);
                cVar.j(b7);
                return;
            case 2:
                f();
                int size2 = ((TreeMap) obj).size();
                b7 = size2 != 0 ? b() : 0;
                e2.c cVar2 = (e2.c) aVar;
                if (cVar2.d()) {
                    cVar2.b(4, "string_ids_size: ".concat(n1.z.m1(size2)));
                    cVar2.b(4, "string_ids_off:  ".concat(n1.z.m1(b7)));
                }
                cVar2.j(size2);
                cVar2.j(b7);
                return;
            default:
                f();
                int size3 = ((TreeMap) obj).size();
                b7 = size3 != 0 ? b() : 0;
                if (size3 > 65536) {
                    throw new DexIndexOverflowException(String.format("Too many type identifiers to fit in one dex file: %1$d; max is %2$d.%nYou may try using multi-dex. If multi-dex is enabled then the list of classes for the main dex list is too large.", Integer.valueOf(c().size()), 65536), null);
                }
                e2.c cVar3 = (e2.c) aVar;
                if (cVar3.d()) {
                    cVar3.b(4, "type_ids_size:   ".concat(n1.z.m1(size3)));
                    cVar3.b(4, "type_ids_off:    ".concat(n1.z.m1(b7)));
                }
                cVar3.j(size3);
                cVar3.j(b7);
                return;
        }
    }
}
