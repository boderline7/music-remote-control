package y1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends a0 {

    /* renamed from: e, reason: collision with root package name */
    public final b2.r f10065e;

    /* renamed from: f, reason: collision with root package name */
    public final w1.g f10066f;

    /* renamed from: g, reason: collision with root package name */
    public androidx.appcompat.widget.c0 f10067g;

    /* renamed from: h, reason: collision with root package name */
    public final boolean f10068h;

    /* renamed from: i, reason: collision with root package name */
    public final c2.e f10069i;

    public e(b2.r rVar, w1.g gVar, boolean z6, c2.b bVar) {
        super(4, -1);
        if (rVar == null) {
            throw new NullPointerException("ref == null");
        }
        if (gVar == null) {
            throw new NullPointerException("code == null");
        }
        if (bVar == null) {
            throw new NullPointerException("throwsList == null");
        }
        this.f10065e = rVar;
        this.f10066f = gVar;
        this.f10068h = z6;
        this.f10069i = bVar;
        this.f10067g = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // y1.p
    public final void a(f fVar) {
        z zVar = fVar.f10086o;
        w1.g gVar = this.f10066f;
        if (gVar.f9421a != 1) {
            gVar.f9422b.getClass();
        }
        gVar.f9422b.getClass();
        a2.c cVar = (a2.c) ((a2.q) gVar.f9423c.f714b).f249b;
        int length = cVar.f6354b.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                break;
            }
            if (((e2.d) ((a2.b) cVar.f(i7)).f222b.i().e()).f6354b.length != 0) {
                androidx.appcompat.app.g gVar2 = gVar.f9423c;
                gVar2.getClass();
                HashSet hashSet = new HashSet(20);
                a2.c cVar2 = (a2.c) ((a2.q) gVar2.f714b).f249b;
                int length2 = cVar2.f6354b.length;
                for (int i8 = 0; i8 < length2; i8++) {
                    c2.e e7 = ((a2.b) cVar2.f(i8)).f222b.i().e();
                    int length3 = ((e2.d) e7).f6354b.length;
                    for (int i9 = 0; i9 < length3; i9++) {
                        hashSet.add(e7.a(i9));
                    }
                }
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    fVar.f10078g.r((c2.c) it.next());
                }
                this.f10067g = new androidx.appcompat.widget.c0(gVar);
            } else {
                i7++;
            }
        }
        w1.t tVar = gVar.f9422b;
        tVar.getClass();
        HashSet hashSet2 = new HashSet(20);
        Iterator it2 = tVar.f9441c.iterator();
        while (it2.hasNext()) {
            w1.h hVar = (w1.h) it2.next();
            if (hVar instanceof w1.f) {
                hashSet2.add(((w1.f) hVar).f9418e);
            } else {
                if (hVar instanceof w1.r) {
                    ((w1.r) hVar).getClass();
                    throw null;
                }
                if (hVar instanceof w1.s) {
                    ((w1.s) hVar).getClass();
                }
            }
        }
        Iterator it3 = hashSet2.iterator();
        while (it3.hasNext()) {
            fVar.a((b2.a) it3.next());
        }
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_CODE_ITEM;
    }

    @Override // y1.a0
    public final void i(c0 c0Var, int i7) {
        int h7;
        f fVar = c0Var.f10052b;
        i6.i iVar = new i6.i(this, fVar, 12);
        w1.g gVar = this.f10066f;
        Iterator it = gVar.f9422b.f9441c.iterator();
        while (it.hasNext()) {
            w1.h hVar = (w1.h) it.next();
            if (hVar instanceof w1.f) {
                w1.f fVar2 = (w1.f) hVar;
                b2.a aVar = fVar2.f9418e;
                int h8 = iVar.h(aVar);
                if (h8 >= 0) {
                    fVar2.p(h8);
                }
                if ((aVar instanceof b2.q) && (h7 = iVar.h(((b2.q) aVar).f2936a)) >= 0) {
                    fVar2.o(h7);
                }
            }
        }
        androidx.appcompat.widget.c0 c0Var2 = this.f10067g;
        int i8 = 0;
        if (c0Var2 != null) {
            c0Var2.b();
            u uVar = fVar.f10078g;
            int length = c0Var2.f1200c.f6354b.length;
            c0Var2.f1202e = new TreeMap();
            for (int i9 = 0; i9 < length; i9++) {
                ((TreeMap) c0Var2.f1202e).put(((w1.c) c0Var2.f1200c.f(i9)).f9417c, null);
            }
            if (((TreeMap) c0Var2.f1202e).size() > 65535) {
                throw new UnsupportedOperationException("too many catch handlers");
            }
            e2.c cVar = new e2.c();
            c0Var2.f1198a = cVar.m(((TreeMap) c0Var2.f1202e).size());
            for (Map.Entry entry : ((TreeMap) c0Var2.f1202e).entrySet()) {
                w1.b bVar = (w1.b) entry.getKey();
                Object[] objArr = bVar.f6354b;
                int length2 = objArr.length;
                int length3 = objArr.length;
                boolean equals = length3 == 0 ? false : ((w1.a) bVar.f(length3 - 1)).f9413a.equals(b2.v.OBJECT);
                entry.setValue(Integer.valueOf(cVar.f6349c));
                if (equals) {
                    cVar.l(-(length2 - 1));
                    length2--;
                } else {
                    cVar.l(length2);
                }
                for (int i10 = 0; i10 < length2; i10++) {
                    w1.a aVar2 = (w1.a) bVar.f(i10);
                    cVar.m(uVar.n(aVar2.f9413a));
                    cVar.m(aVar2.f9414b);
                }
                if (equals) {
                    cVar.m(((w1.a) bVar.f(length2)).f9414b);
                }
            }
            int i11 = cVar.f6349c;
            byte[] bArr = new byte[i11];
            System.arraycopy(cVar.f6348b, 0, bArr, 0, i11);
            c0Var2.f1201d = bArr;
            androidx.appcompat.widget.c0 c0Var3 = this.f10067g;
            c0Var3.b();
            i8 = (c0Var3.f1200c.f6354b.length * 8) + ((byte[]) c0Var3.f1201d).length;
        }
        gVar.a();
        int i12 = gVar.f9425e.i();
        if ((i12 & 1) != 0) {
            i12++;
        }
        j((i12 * 2) + 16 + i8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    @Override // y1.a0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(y1.f r18, e2.c r19) {
        /*
            Method dump skipped, instructions count: 691
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: y1.e.k(y1.f, e2.c):void");
    }

    public final String toString() {
        return "CodeItem{" + this.f10065e.d() + "}";
    }
}
