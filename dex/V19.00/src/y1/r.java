package y1;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r extends a0 {

    /* renamed from: e, reason: collision with root package name */
    public final q f10106e;

    /* renamed from: f, reason: collision with root package name */
    public final c0 f10107f;

    /* renamed from: g, reason: collision with root package name */
    public final p f10108g;

    /* renamed from: h, reason: collision with root package name */
    public final int f10109h;

    public r(c0 c0Var) {
        super(4, 12);
        if (c0Var == null) {
            throw new NullPointerException("section == null");
        }
        this.f10106e = q.TYPE_MAP_LIST;
        this.f10107f = c0Var;
        this.f10108g = null;
        this.f10109h = 1;
    }

    public r(q qVar, c0 c0Var, p pVar, p pVar2, int i7) {
        super(4, 12);
        if (qVar == null) {
            throw new NullPointerException("type == null");
        }
        if (pVar == null) {
            throw new NullPointerException("firstItem == null");
        }
        if (pVar2 == null) {
            throw new NullPointerException("lastItem == null");
        }
        if (i7 <= 0) {
            throw new IllegalArgumentException("itemCount <= 0");
        }
        this.f10106e = qVar;
        this.f10107f = c0Var;
        this.f10108g = pVar;
        this.f10109h = i7;
    }

    public static void l(c0[] c0VarArr, z zVar) {
        if (c0VarArr == null) {
            throw new NullPointerException("sections == null");
        }
        if (zVar.f10117f.size() != 0) {
            throw new IllegalArgumentException("mapSection.items().size() != 0");
        }
        ArrayList arrayList = new ArrayList(50);
        for (c0 c0Var : c0VarArr) {
            q qVar = null;
            p pVar = null;
            p pVar2 = null;
            int i7 = 0;
            for (p pVar3 : c0Var.c()) {
                q b7 = pVar3.b();
                if (b7 != qVar) {
                    if (i7 != 0) {
                        arrayList.add(new r(qVar, c0Var, pVar, pVar2, i7));
                    }
                    pVar = pVar3;
                    qVar = b7;
                    i7 = 0;
                }
                i7++;
                pVar2 = pVar3;
            }
            if (i7 != 0) {
                arrayList.add(new r(qVar, c0Var, pVar, pVar2, i7));
            } else if (c0Var == zVar) {
                arrayList.add(new r(zVar));
            }
        }
        zVar.k(new i0(q.TYPE_MAP_LIST, arrayList));
    }

    @Override // y1.p
    public final void a(f fVar) {
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_MAP_ITEM;
    }

    @Override // y1.a0
    public final void k(f fVar, e2.c cVar) {
        q qVar = this.f10106e;
        int i7 = qVar.f10103a;
        c0 c0Var = this.f10107f;
        p pVar = this.f10108g;
        int b7 = pVar == null ? c0Var.b() : c0Var.a(pVar);
        boolean d7 = cVar.d();
        int i8 = this.f10109h;
        if (d7) {
            cVar.b(0, g() + ' ' + qVar.f10104b + " map");
            StringBuilder sb = new StringBuilder("  type:   ");
            sb.append(n1.z.l1(i7));
            sb.append(" // ");
            sb.append(qVar.toString());
            cVar.b(2, sb.toString());
            cVar.b(2, "  unused: 0");
            cVar.b(4, "  size:   ".concat(n1.z.m1(i8)));
            cVar.b(4, "  offset: ".concat(n1.z.m1(b7)));
        }
        cVar.k(i7);
        cVar.k(0);
        cVar.j(i8);
        cVar.j(b7);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(r.class.getName());
        sb.append('{');
        sb.append(this.f10107f.toString());
        sb.append(' ');
        sb.append(this.f10106e.f10105c);
        sb.append('}');
        return sb.toString();
    }
}
