package y1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends a0 {

    /* renamed from: e, reason: collision with root package name */
    public final b2.v f10043e;

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f10044f;

    /* renamed from: g, reason: collision with root package name */
    public final HashMap f10045g;

    /* renamed from: h, reason: collision with root package name */
    public final ArrayList f10046h;

    /* renamed from: i, reason: collision with root package name */
    public final ArrayList f10047i;

    /* renamed from: j, reason: collision with root package name */
    public final ArrayList f10048j;

    /* renamed from: k, reason: collision with root package name */
    public b2.c f10049k;

    /* renamed from: l, reason: collision with root package name */
    public byte[] f10050l;

    public c(b2.v vVar) {
        super(1, -1);
        if (vVar == null) {
            throw new NullPointerException("thisClass == null");
        }
        this.f10043e = vVar;
        this.f10044f = new ArrayList(20);
        this.f10045g = new HashMap(40);
        this.f10046h = new ArrayList(20);
        this.f10047i = new ArrayList(20);
        this.f10048j = new ArrayList(20);
        this.f10049k = null;
    }

    public static void l(f fVar, e2.c cVar, String str, ArrayList arrayList) {
        int size = arrayList.size();
        if (size == 0) {
            return;
        }
        if (cVar.d()) {
            cVar.b(0, "  " + str + ":");
        }
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            i7 = ((i) arrayList.get(i8)).a(fVar, cVar, i7, i8);
        }
    }

    public static void n(e2.c cVar, String str, int i7) {
        if (cVar.d()) {
            cVar.c(String.format("  %-21s %08x", str.concat("_size:"), Integer.valueOf(i7)));
        }
        cVar.m(i7);
    }

    @Override // y1.p
    public final void a(f fVar) {
        ArrayList arrayList = this.f10044f;
        if (!arrayList.isEmpty()) {
            o();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                h hVar = (h) it.next();
                hVar.getClass();
                fVar.f10080i.m(hVar.f10093b);
            }
        }
        ArrayList arrayList2 = this.f10046h;
        if (!arrayList2.isEmpty()) {
            Collections.sort(arrayList2);
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                h hVar2 = (h) it2.next();
                hVar2.getClass();
                fVar.f10080i.m(hVar2.f10093b);
            }
        }
        ArrayList arrayList3 = this.f10047i;
        if (!arrayList3.isEmpty()) {
            Collections.sort(arrayList3);
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                j jVar = (j) it3.next();
                jVar.getClass();
                fVar.f10081j.m(jVar.f10097b);
                e eVar = jVar.f10098c;
                if (eVar != null) {
                    fVar.f10073b.k(eVar);
                }
            }
        }
        ArrayList arrayList4 = this.f10048j;
        if (arrayList4.isEmpty()) {
            return;
        }
        Collections.sort(arrayList4);
        Iterator it4 = arrayList4.iterator();
        while (it4.hasNext()) {
            j jVar2 = (j) it4.next();
            jVar2.getClass();
            fVar.f10081j.m(jVar2.f10097b);
            e eVar2 = jVar2.f10098c;
            if (eVar2 != null) {
                fVar.f10073b.k(eVar2);
            }
        }
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_CLASS_DATA_ITEM;
    }

    @Override // y1.a0
    public final void i(c0 c0Var, int i7) {
        e2.c cVar = new e2.c();
        m(c0Var.f10052b, cVar);
        int i8 = cVar.f6349c;
        byte[] bArr = new byte[i8];
        System.arraycopy(cVar.f6348b, 0, bArr, 0, i8);
        this.f10050l = bArr;
        j(i8);
    }

    @Override // y1.a0
    public final void k(f fVar, e2.c cVar) {
        if (cVar.d()) {
            m(fVar, cVar);
        } else {
            cVar.h(this.f10050l);
        }
    }

    public final void m(f fVar, e2.c cVar) {
        boolean d7 = cVar.d();
        if (d7) {
            cVar.b(0, g() + " class data for " + this.f10043e.f2943a.d());
        }
        ArrayList arrayList = this.f10044f;
        n(cVar, "static_fields", arrayList.size());
        ArrayList arrayList2 = this.f10046h;
        n(cVar, "instance_fields", arrayList2.size());
        ArrayList arrayList3 = this.f10047i;
        n(cVar, "direct_methods", arrayList3.size());
        ArrayList arrayList4 = this.f10048j;
        n(cVar, "virtual_methods", arrayList4.size());
        l(fVar, cVar, "static_fields", arrayList);
        l(fVar, cVar, "instance_fields", arrayList2);
        l(fVar, cVar, "direct_methods", arrayList3);
        l(fVar, cVar, "virtual_methods", arrayList4);
        if (d7) {
            cVar.e();
        }
    }

    public final b2.c o() {
        HashMap hashMap;
        b2.c cVar;
        b2.a aVar;
        if (this.f10049k == null) {
            ArrayList arrayList = this.f10044f;
            if (arrayList.size() != 0) {
                Collections.sort(arrayList);
                int size = arrayList.size();
                while (true) {
                    hashMap = this.f10045g;
                    if (size <= 0) {
                        break;
                    }
                    b2.a aVar2 = (b2.a) hashMap.get((h) arrayList.get(size - 1));
                    if (aVar2 instanceof b2.o) {
                        if (((b2.o) aVar2).i() != 0) {
                            break;
                        }
                        size--;
                    } else {
                        if (aVar2 != null) {
                            break;
                        }
                        size--;
                    }
                }
                if (size == 0) {
                    cVar = null;
                } else {
                    b2.b bVar = new b2.b(size);
                    for (int i7 = 0; i7 < size; i7++) {
                        h hVar = (h) arrayList.get(i7);
                        b2.a aVar3 = (b2.a) hashMap.get(hVar);
                        if (aVar3 == null) {
                            c2.c b7 = hVar.f10093b.b();
                            switch (b7.f3041b) {
                                case 1:
                                    aVar = b2.e.VALUE_FALSE;
                                    break;
                                case 2:
                                    aVar = b2.f.VALUE_0;
                                    break;
                                case 3:
                                    aVar = b2.g.VALUE_0;
                                    break;
                                case 4:
                                    aVar = b2.h.VALUE_0;
                                    break;
                                case 5:
                                    aVar = b2.j.VALUE_0;
                                    break;
                                case 6:
                                    aVar = b2.k.VALUE_0;
                                    break;
                                case 7:
                                    aVar = b2.p.VALUE_0;
                                    break;
                                case 8:
                                    aVar = b2.t.VALUE_0;
                                    break;
                                case 9:
                                    aVar = b2.l.THE_ONE;
                                    break;
                                default:
                                    throw new UnsupportedOperationException("no zero for type: " + b7.d());
                            }
                            aVar3 = aVar;
                        }
                        bVar.g(i7, aVar3);
                    }
                    bVar.f6364a = false;
                    cVar = new b2.c(bVar);
                }
                this.f10049k = cVar;
            }
        }
        return this.f10049k;
    }

    public final boolean p() {
        return this.f10044f.isEmpty() && this.f10046h.isEmpty() && this.f10047i.isEmpty() && this.f10048j.isEmpty();
    }
}
