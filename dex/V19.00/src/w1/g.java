package w1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public final int f9421a;

    /* renamed from: b, reason: collision with root package name */
    public t f9422b;

    /* renamed from: c, reason: collision with root package name */
    public androidx.appcompat.app.g f9423c;

    /* renamed from: d, reason: collision with root package name */
    public d f9424d;

    /* renamed from: e, reason: collision with root package name */
    public i f9425e;

    public g(int i7, t tVar, androidx.appcompat.app.g gVar) {
        if (tVar == null) {
            throw new NullPointerException("unprocessedInsns == null");
        }
        this.f9421a = i7;
        this.f9422b = tVar;
        this.f9423c = gVar;
        this.f9424d = null;
        this.f9425e = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a() {
        int i7;
        int i8;
        j[] jVarArr;
        j jVar;
        int i9;
        a2.n nVar;
        m mVar;
        x xVar;
        boolean z6;
        d dVar;
        a2.c cVar;
        int[] iArr;
        int i10;
        b bVar;
        if (this.f9425e != null) {
            return;
        }
        t tVar = this.f9422b;
        if (tVar.f9442d >= 0) {
            throw new UnsupportedOperationException("already processed");
        }
        int size = tVar.f9441c.size();
        j[] jVarArr2 = new j[size];
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            jVarArr2[i12] = ((h) tVar.f9441c.get(i12)).f9427b;
        }
        tVar.f(jVarArr2);
        tVar.f9439a.getClass();
        do {
            int i13 = tVar.f9442d;
            i7 = tVar.f9440b;
            int i14 = i13 + i7 + tVar.f9443e;
            int i15 = tVar.f9444f;
            int i16 = i14 - i15;
            Iterator it = tVar.f9441c.iterator();
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            while (it.hasNext()) {
                a2.n nVar2 = ((h) it.next()).f9429d;
                for (int i21 = 0; i21 < nVar2.f6354b.length; i21++) {
                    a2.m mVar2 = (a2.m) nVar2.f(i21);
                    if (mVar2.g()) {
                        int i22 = mVar2.f237a;
                        boolean z7 = i22 >= i16;
                        if ((i22 & 1) == 0) {
                            if (z7) {
                                i18++;
                            } else {
                                i20++;
                            }
                        } else if (z7) {
                            i17++;
                        } else {
                            i19++;
                        }
                    }
                }
            }
            if (i17 > i18 && i19 > i20) {
                tVar.c();
            } else if (i17 <= i18) {
                if (i19 <= i20) {
                    break;
                }
                tVar.c();
                if (i15 != 0 && i18 > i17) {
                    tVar.b();
                }
            } else {
                tVar.b();
            }
        } while (tVar.f(jVarArr2));
        if (tVar.f9442d == 0) {
            int size2 = tVar.f9441c.size();
            for (int i23 = 0; i23 < size2; i23++) {
                h hVar = (h) tVar.f9441c.get(i23);
                j jVar2 = hVar.f9427b;
                j jVar3 = jVarArr2[i23];
                if (jVar2 != jVar3) {
                    tVar.f9441c.set(i23, hVar.j(jVar3));
                }
            }
        } else {
            int size3 = tVar.f9441c.size();
            ArrayList arrayList = new ArrayList(size3 * 2);
            ArrayList arrayList2 = new ArrayList();
            int i24 = 0;
            while (i24 < size3) {
                h hVar2 = (h) tVar.f9441c.get(i24);
                j jVar4 = hVar2.f9427b;
                j jVar5 = jVarArr2[i24];
                if (jVar5 != null) {
                    i8 = size3;
                    jVarArr = jVarArr2;
                    mVar = null;
                    xVar = null;
                } else {
                    j d7 = tVar.d(hVar2);
                    BitSet f7 = d7.f9434d.f(hVar2);
                    boolean z8 = f7.get(i11);
                    j jVar6 = hVar2.f9427b;
                    if (jVar6.f9435e) {
                        f7.set(i11);
                    }
                    a2.n nVar3 = hVar2.f9429d;
                    Object[] objArr = nVar3.f6354b;
                    i8 = size3;
                    int length = objArr.length - f7.cardinality();
                    if (length == 0) {
                        nVar = a2.n.EMPTY;
                        jVarArr = jVarArr2;
                        jVar = d7;
                        i9 = 0;
                    } else {
                        jVarArr = jVarArr2;
                        a2.n nVar4 = new a2.n(length);
                        jVar = d7;
                        int i25 = 0;
                        for (int i26 = 0; i26 < objArr.length; i26++) {
                            if (!f7.get(i26)) {
                                nVar4.g(i25, nVar3.f(i26));
                                i25++;
                            }
                        }
                        if (!nVar3.f6364a) {
                            i9 = 0;
                            nVar4.f6364a = false;
                        } else {
                            i9 = 0;
                        }
                        nVar = nVar4;
                    }
                    boolean z9 = jVar6.f9435e;
                    if (z9) {
                        f7.set(i9, z8);
                    }
                    int length2 = nVar.f6354b.length;
                    a2.s sVar = hVar2.f9428c;
                    mVar = length2 == 0 ? null : new m(sVar, nVar);
                    if (!z9 || f7.get(0)) {
                        xVar = null;
                    } else {
                        a2.m mVar3 = (a2.m) nVar3.f(0);
                        xVar = h.h(sVar, mVar3, mVar3.f237a == 0 ? mVar3 : a2.m.f(0, mVar3.f238b));
                    }
                    hVar2 = hVar2.l(nVar3.k(z9, f7));
                    jVar5 = jVar;
                }
                if (hVar2 instanceof e) {
                    ((e) hVar2).getClass();
                }
                if (mVar != null) {
                    arrayList.add(mVar);
                }
                if (!(hVar2 instanceof z) && arrayList2.size() > 0) {
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        arrayList.add((e) it2.next());
                    }
                    arrayList2.clear();
                }
                if (jVar5 != jVar4) {
                    hVar2 = hVar2.j(jVar5);
                }
                arrayList.add(hVar2);
                if (xVar != null) {
                    arrayList.add(xVar);
                }
                i24++;
                i11 = 0;
                size3 = i8;
                jVarArr2 = jVarArr;
            }
            tVar.f9441c = arrayList;
        }
        do {
            int size4 = tVar.f9441c.size();
            int i27 = 0;
            for (int i28 = 0; i28 < size4; i28++) {
                h hVar3 = (h) tVar.f9441c.get(i28);
                if (i27 < 0) {
                    hVar3.getClass();
                    throw new IllegalArgumentException("address < 0");
                }
                hVar3.f9426a = i27;
                i27 += hVar3.b();
            }
            int size5 = tVar.f9441c.size();
            int i29 = 0;
            z6 = false;
            while (i29 < size5) {
                h hVar4 = (h) tVar.f9441c.get(i29);
                if (hVar4 instanceof y) {
                    j jVar7 = hVar4.f9427b;
                    y yVar = (y) hVar4;
                    if (!jVar7.f9434d.b(yVar)) {
                        if (jVar7.f9432b == 40) {
                            j e7 = tVar.e(hVar4, jVar7);
                            if (e7 == null) {
                                throw new UnsupportedOperationException("method too long");
                            }
                            tVar.f9441c.set(i29, hVar4.j(e7));
                        } else {
                            try {
                                int i30 = i29 + 1;
                                e eVar = (e) tVar.f9441c.get(i30);
                                tVar.f9441c.set(i29, new y(k.GOTO, yVar.f9428c, a2.n.EMPTY, yVar.f9455e));
                                tVar.f9441c.add(i29, yVar.p(eVar));
                                size5++;
                                i29 = i30;
                            } catch (ClassCastException unused) {
                                throw new IllegalStateException("unpaired TargetInsn");
                            } catch (IndexOutOfBoundsException unused2) {
                                throw new IllegalStateException("unpaired TargetInsn (dangling)");
                            }
                        }
                        z6 = true;
                    }
                }
                i29++;
            }
        } while (z6);
        ArrayList arrayList3 = tVar.f9441c;
        int i31 = tVar.f9442d + i7 + tVar.f9443e;
        int size6 = arrayList3.size();
        i iVar = new i(size6, i31);
        for (int i32 = 0; i32 < size6; i32++) {
            iVar.g(i32, (h) arrayList3.get(i32));
        }
        iVar.f6364a = false;
        this.f9425e = iVar;
        u uVar = u.EMPTY;
        int i33 = this.f9421a;
        if (i33 == 1) {
            u uVar2 = u.EMPTY;
        } else {
            if (i33 != 2 && i33 != 3) {
                throw new IllegalArgumentException("bogus howMuch");
            }
            a2.s sVar2 = a2.s.NO_INFO;
            int length3 = iVar.f6354b.length;
            n1.z[] zVarArr = new n1.z[length3];
            for (int i34 = 0; i34 < length3; i34++) {
                h hVar5 = (h) iVar.f(i34);
                if (!(hVar5 instanceof e) && !hVar5.f9428c.equals(sVar2)) {
                    sVar2.getClass();
                }
            }
            new u(0).f6364a = false;
        }
        i iVar2 = this.f9425e;
        q qVar = q.EMPTY;
        int length4 = iVar2.f6354b.length;
        p0.g gVar = new p0.g(length4);
        for (int i35 = 0; i35 < length4; i35++) {
            h hVar6 = (h) iVar2.f(i35);
            if (hVar6 instanceof r) {
                hVar6.e();
                ((r) hVar6).getClass();
                throw null;
            }
            if (hVar6 instanceof s) {
                s sVar3 = (s) hVar6;
                int e8 = hVar6.e();
                sVar3.getClass();
                gVar.k(e8, null);
            }
        }
        gVar.f(androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED, 0);
        int size7 = ((ArrayList) gVar.f8134b).size();
        int i36 = size7 - gVar.f8135c;
        if (i36 == 0) {
            q qVar2 = q.EMPTY;
        } else {
            p[] pVarArr = new p[i36];
            if (size7 == i36) {
                ((ArrayList) gVar.f8134b).toArray(pVarArr);
            } else {
                Iterator it3 = ((ArrayList) gVar.f8134b).iterator();
                int i37 = 0;
                while (it3.hasNext()) {
                    p pVar = (p) it3.next();
                    if (pVar != null) {
                        pVarArr[i37] = pVar;
                        i37++;
                    }
                }
            }
            Arrays.sort(pVarArr);
            q qVar3 = new q(i36);
            for (int i38 = 0; i38 < i36; i38++) {
                qVar3.g(i38, pVarArr[i38]);
            }
            qVar3.f6364a = false;
        }
        androidx.appcompat.app.g gVar2 = this.f9423c;
        a2.q qVar4 = (a2.q) gVar2.f714b;
        int[] iArr2 = (int[]) gVar2.f715c;
        androidx.appcompat.app.g gVar3 = (androidx.appcompat.app.g) gVar2.f716d;
        int length5 = iArr2.length;
        a2.c cVar2 = (a2.c) qVar4.f249b;
        ArrayList arrayList4 = new ArrayList(length5);
        b bVar2 = b.EMPTY;
        int i39 = 0;
        a2.b bVar3 = null;
        a2.b bVar4 = null;
        while (i39 < length5) {
            a2.b j7 = cVar2.j(iArr2[i39]);
            a2.h hVar7 = j7.f222b;
            if (((e2.d) hVar7.i().f228a.f244d).f6354b.length != 0) {
                e2.f fVar = j7.f223c;
                int i40 = fVar.f6362c;
                c2.e e9 = hVar7.i().e();
                int length6 = ((e2.d) e9).f6354b.length;
                if (length6 == 0) {
                    bVar = b.EMPTY;
                    cVar = cVar2;
                    iArr = iArr2;
                    i10 = length5;
                } else {
                    cVar = cVar2;
                    int i41 = j7.f224d;
                    if ((i41 == -1 && i40 != length6) || (i41 != -1 && (i40 != length6 + 1 || i41 != fVar.g(length6)))) {
                        throw new RuntimeException("shouldn't happen: weird successors list");
                    }
                    int i42 = 0;
                    while (true) {
                        if (i42 >= length6) {
                            break;
                        }
                        if (e9.a(i42).equals(c2.c.OBJECT)) {
                            length6 = i42 + 1;
                            break;
                        }
                        i42++;
                    }
                    b bVar5 = new b(length6);
                    int i43 = 0;
                    while (i43 < length6) {
                        bVar5.g(i43, new a(new b2.v(e9.a(i43)), ((e[]) gVar3.f714b)[fVar.g(i43)].e()));
                        i43++;
                        iArr2 = iArr2;
                        length5 = length5;
                    }
                    iArr = iArr2;
                    i10 = length5;
                    bVar5.f6364a = false;
                    bVar = bVar5;
                }
                if (bVar2.f6354b.length != 0) {
                    if (bVar2.equals(bVar)) {
                        if (bVar3 == null) {
                            throw new NullPointerException("start == null");
                        }
                        if (((e[]) gVar3.f716d)[j7.f221a].e() - ((e[]) gVar3.f715c)[bVar3.f221a].e() <= 65535) {
                            bVar4 = j7;
                        }
                    }
                    if (bVar2.f6354b.length != 0) {
                        arrayList4.add(new c(((e[]) gVar3.f715c)[bVar3.f221a].e(), ((e[]) gVar3.f716d)[bVar4.f221a].e(), bVar2));
                    }
                }
                bVar3 = j7;
                bVar4 = bVar3;
                bVar2 = bVar;
            } else {
                cVar = cVar2;
                iArr = iArr2;
                i10 = length5;
            }
            i39++;
            cVar2 = cVar;
            iArr2 = iArr;
            length5 = i10;
        }
        if (bVar2.f6354b.length != 0) {
            arrayList4.add(new c(((e[]) gVar3.f715c)[bVar3.f221a].e(), ((e[]) gVar3.f716d)[bVar4.f221a].e(), bVar2));
        }
        int size8 = arrayList4.size();
        if (size8 == 0) {
            dVar = d.EMPTY;
        } else {
            d dVar2 = new d(size8);
            for (int i44 = 0; i44 < size8; i44++) {
                dVar2.g(i44, (c) arrayList4.get(i44));
            }
            dVar2.f6364a = false;
            dVar = dVar2;
        }
        this.f9424d = dVar;
        this.f9422b = null;
        this.f9423c = null;
    }
}
