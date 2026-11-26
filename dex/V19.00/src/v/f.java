package v;

import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public u.j f9330a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f9331b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9332c;

    /* renamed from: d, reason: collision with root package name */
    public u.j f9333d;

    /* renamed from: e, reason: collision with root package name */
    public ArrayList f9334e;

    /* renamed from: f, reason: collision with root package name */
    public r f9335f;

    /* renamed from: g, reason: collision with root package name */
    public b f9336g;

    /* renamed from: h, reason: collision with root package name */
    public ArrayList f9337h;

    public final void a(h hVar, int i7, ArrayList arrayList, o oVar) {
        v vVar = hVar.f9342d;
        if (vVar.f9378c == null) {
            u.j jVar = this.f9330a;
            if (vVar == jVar.f9134d || vVar == jVar.f9136e) {
                return;
            }
            if (oVar == null) {
                oVar = new o();
                oVar.f9355a = null;
                oVar.f9356b = new ArrayList();
                oVar.f9355a = vVar;
                arrayList.add(oVar);
            }
            vVar.f9378c = oVar;
            oVar.f9356b.add(vVar);
            h hVar2 = vVar.f9383h;
            Iterator it = hVar2.f9349k.iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                if (eVar instanceof h) {
                    a((h) eVar, i7, arrayList, oVar);
                }
            }
            h hVar3 = vVar.f9384i;
            Iterator it2 = hVar3.f9349k.iterator();
            while (it2.hasNext()) {
                e eVar2 = (e) it2.next();
                if (eVar2 instanceof h) {
                    a((h) eVar2, i7, arrayList, oVar);
                }
            }
            if (i7 == 1 && (vVar instanceof q)) {
                Iterator it3 = ((q) vVar).f9358k.f9349k.iterator();
                while (it3.hasNext()) {
                    e eVar3 = (e) it3.next();
                    if (eVar3 instanceof h) {
                        a((h) eVar3, i7, arrayList, oVar);
                    }
                }
            }
            Iterator it4 = hVar2.f9350l.iterator();
            while (it4.hasNext()) {
                a((h) it4.next(), i7, arrayList, oVar);
            }
            Iterator it5 = hVar3.f9350l.iterator();
            while (it5.hasNext()) {
                a((h) it5.next(), i7, arrayList, oVar);
            }
            if (i7 == 1 && (vVar instanceof q)) {
                Iterator it6 = ((q) vVar).f9358k.f9350l.iterator();
                while (it6.hasNext()) {
                    a((h) it6.next(), i7, arrayList, oVar);
                }
            }
        }
    }

    public final void b(u.j jVar) {
        int i7;
        u.h hVar;
        u.h hVar2;
        u.h hVar3;
        Iterator it = jVar.f9216v0.iterator();
        while (it.hasNext()) {
            u.i iVar = (u.i) it.next();
            u.h[] hVarArr = iVar.U;
            u.h hVar4 = hVarArr[0];
            u.h hVar5 = hVarArr[1];
            if (iVar.f9145i0 == 8) {
                iVar.f9128a = true;
            } else {
                float f7 = iVar.f9171w;
                if (f7 < 1.0f && hVar4 == u.h.MATCH_CONSTRAINT) {
                    iVar.f9162r = 2;
                }
                float f8 = iVar.f9174z;
                if (f8 < 1.0f && hVar5 == u.h.MATCH_CONSTRAINT) {
                    iVar.f9164s = 2;
                }
                if (iVar.Y > CropImageView.DEFAULT_ASPECT_RATIO) {
                    u.h hVar6 = u.h.MATCH_CONSTRAINT;
                    if (hVar4 == hVar6 && (hVar5 == u.h.WRAP_CONTENT || hVar5 == u.h.FIXED)) {
                        iVar.f9162r = 3;
                    } else if (hVar5 == hVar6 && (hVar4 == u.h.WRAP_CONTENT || hVar4 == u.h.FIXED)) {
                        iVar.f9164s = 3;
                    } else if (hVar4 == hVar6 && hVar5 == hVar6) {
                        if (iVar.f9162r == 0) {
                            iVar.f9162r = 3;
                        }
                        if (iVar.f9164s == 0) {
                            iVar.f9164s = 3;
                        }
                    }
                }
                u.h hVar7 = u.h.MATCH_CONSTRAINT;
                u.f fVar = iVar.L;
                u.f fVar2 = iVar.J;
                if (hVar4 == hVar7 && iVar.f9162r == 1 && (fVar2.f9121f == null || fVar.f9121f == null)) {
                    hVar4 = u.h.WRAP_CONTENT;
                }
                u.f fVar3 = iVar.M;
                u.f fVar4 = iVar.K;
                if (hVar5 == hVar7 && iVar.f9164s == 1 && (fVar4.f9121f == null || fVar3.f9121f == null)) {
                    hVar5 = u.h.WRAP_CONTENT;
                }
                u.h hVar8 = hVar5;
                n nVar = iVar.f9134d;
                nVar.f9379d = hVar4;
                int i8 = iVar.f9162r;
                nVar.f9376a = i8;
                q qVar = iVar.f9136e;
                qVar.f9379d = hVar8;
                int i9 = iVar.f9164s;
                qVar.f9376a = i9;
                u.h hVar9 = u.h.MATCH_PARENT;
                if ((hVar4 == hVar9 || hVar4 == u.h.FIXED || hVar4 == u.h.WRAP_CONTENT) && (hVar8 == hVar9 || hVar8 == u.h.FIXED || hVar8 == u.h.WRAP_CONTENT)) {
                    int s6 = iVar.s();
                    if (hVar4 == hVar9) {
                        s6 = (jVar.s() - fVar2.f9122g) - fVar.f9122g;
                        hVar4 = u.h.FIXED;
                    }
                    int m6 = iVar.m();
                    if (hVar8 == hVar9) {
                        int m7 = (jVar.m() - fVar4.f9122g) - fVar3.f9122g;
                        hVar8 = u.h.FIXED;
                        i7 = m7;
                    } else {
                        i7 = m6;
                    }
                    f(iVar, hVar4, s6, hVar8, i7);
                    iVar.f9134d.f9380e.d(iVar.s());
                    iVar.f9136e.f9380e.d(iVar.m());
                    iVar.f9128a = true;
                } else {
                    u.f[] fVarArr = iVar.R;
                    if (hVar4 != hVar7 || (hVar8 != (hVar3 = u.h.WRAP_CONTENT) && hVar8 != u.h.FIXED)) {
                        hVar = hVar8;
                    } else if (i8 == 3) {
                        if (hVar8 == hVar3) {
                            f(iVar, hVar3, 0, hVar3, 0);
                        }
                        int m8 = iVar.m();
                        int i10 = (int) ((m8 * iVar.Y) + 0.5f);
                        u.h hVar10 = u.h.FIXED;
                        f(iVar, hVar10, i10, hVar10, m8);
                        iVar.f9134d.f9380e.d(iVar.s());
                        iVar.f9136e.f9380e.d(iVar.m());
                        iVar.f9128a = true;
                    } else if (i8 == 1) {
                        f(iVar, hVar3, 0, hVar8, 0);
                        iVar.f9134d.f9380e.f9351m = iVar.s();
                    } else {
                        hVar = hVar8;
                        if (i8 == 2) {
                            u.h hVar11 = jVar.U[0];
                            u.h hVar12 = u.h.FIXED;
                            if (hVar11 == hVar12 || hVar11 == hVar9) {
                                f(iVar, hVar12, (int) ((f7 * jVar.s()) + 0.5f), hVar, iVar.m());
                                iVar.f9134d.f9380e.d(iVar.s());
                                iVar.f9136e.f9380e.d(iVar.m());
                                iVar.f9128a = true;
                            }
                        } else if (fVarArr[0].f9121f == null || fVarArr[1].f9121f == null) {
                            f(iVar, hVar3, 0, hVar, 0);
                            iVar.f9134d.f9380e.d(iVar.s());
                            iVar.f9136e.f9380e.d(iVar.m());
                            iVar.f9128a = true;
                        }
                    }
                    if (hVar == hVar7 && (hVar4 == (hVar2 = u.h.WRAP_CONTENT) || hVar4 == u.h.FIXED)) {
                        if (i9 == 3) {
                            if (hVar4 == hVar2) {
                                f(iVar, hVar2, 0, hVar2, 0);
                            }
                            int s7 = iVar.s();
                            float f9 = iVar.Y;
                            if (iVar.Z == -1) {
                                f9 = 1.0f / f9;
                            }
                            u.h hVar13 = u.h.FIXED;
                            f(iVar, hVar13, s7, hVar13, (int) ((s7 * f9) + 0.5f));
                            iVar.f9134d.f9380e.d(iVar.s());
                            iVar.f9136e.f9380e.d(iVar.m());
                            iVar.f9128a = true;
                        } else if (i9 == 1) {
                            f(iVar, hVar4, 0, hVar2, 0);
                            iVar.f9136e.f9380e.f9351m = iVar.m();
                        } else if (i9 == 2) {
                            u.h hVar14 = jVar.U[1];
                            u.h hVar15 = u.h.FIXED;
                            if (hVar14 == hVar15 || hVar14 == hVar9) {
                                f(iVar, hVar4, iVar.s(), hVar15, (int) ((f8 * jVar.m()) + 0.5f));
                                iVar.f9134d.f9380e.d(iVar.s());
                                iVar.f9136e.f9380e.d(iVar.m());
                                iVar.f9128a = true;
                            }
                        } else if (fVarArr[2].f9121f == null || fVarArr[3].f9121f == null) {
                            f(iVar, hVar2, 0, hVar, 0);
                            iVar.f9134d.f9380e.d(iVar.s());
                            iVar.f9136e.f9380e.d(iVar.m());
                            iVar.f9128a = true;
                        }
                    }
                    if (hVar4 == hVar7 && hVar == hVar7) {
                        if (i8 == 1 || i9 == 1) {
                            u.h hVar16 = u.h.WRAP_CONTENT;
                            f(iVar, hVar16, 0, hVar16, 0);
                            iVar.f9134d.f9380e.f9351m = iVar.s();
                            iVar.f9136e.f9380e.f9351m = iVar.m();
                        } else if (i9 == 2 && i8 == 2) {
                            u.h[] hVarArr2 = jVar.U;
                            u.h hVar17 = hVarArr2[0];
                            u.h hVar18 = u.h.FIXED;
                            if (hVar17 == hVar18 && hVarArr2[1] == hVar18) {
                                f(iVar, hVar18, (int) ((f7 * jVar.s()) + 0.5f), hVar18, (int) ((f8 * jVar.m()) + 0.5f));
                                iVar.f9134d.f9380e.d(iVar.s());
                                iVar.f9136e.f9380e.d(iVar.m());
                                iVar.f9128a = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public final void c() {
        ArrayList arrayList = this.f9334e;
        arrayList.clear();
        u.j jVar = this.f9333d;
        jVar.f9134d.f();
        jVar.f9136e.f();
        arrayList.add(jVar.f9134d);
        arrayList.add(jVar.f9136e);
        Iterator it = jVar.f9216v0.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            u.i iVar = (u.i) it.next();
            if (iVar instanceof u.n) {
                k kVar = new k(iVar);
                iVar.f9134d.f();
                iVar.f9136e.f();
                kVar.f9381f = ((u.n) iVar).f9209z0;
                arrayList.add(kVar);
            } else {
                if (iVar.z()) {
                    if (iVar.f9130b == null) {
                        iVar.f9130b = new d(0, iVar);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(iVar.f9130b);
                } else {
                    arrayList.add(iVar.f9134d);
                }
                if (iVar.A()) {
                    if (iVar.f9132c == null) {
                        iVar.f9132c = new d(1, iVar);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(iVar.f9132c);
                } else {
                    arrayList.add(iVar.f9136e);
                }
                if (iVar instanceof u.p) {
                    arrayList.add(new l(iVar));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((v) it2.next()).f();
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            v vVar = (v) it3.next();
            if (vVar.f9377b != jVar) {
                vVar.d();
            }
        }
        ArrayList arrayList2 = this.f9337h;
        arrayList2.clear();
        u.j jVar2 = this.f9330a;
        e(jVar2.f9134d, 0, arrayList2);
        e(jVar2.f9136e, 1, arrayList2);
        this.f9331b = false;
    }

    public final int d(u.j jVar, int i7) {
        ArrayList arrayList;
        int i8;
        int i9;
        long max;
        float f7;
        u.j jVar2 = jVar;
        ArrayList arrayList2 = this.f9337h;
        int size = arrayList2.size();
        int i10 = 0;
        long j7 = 0;
        while (i10 < size) {
            v vVar = ((o) arrayList2.get(i10)).f9355a;
            if (!(vVar instanceof d) ? !(i7 != 0 ? (vVar instanceof q) : (vVar instanceof n)) : ((d) vVar).f9381f != i7) {
                h hVar = (i7 == 0 ? jVar2.f9134d : jVar2.f9136e).f9383h;
                h hVar2 = (i7 == 0 ? jVar2.f9134d : jVar2.f9136e).f9384i;
                boolean contains = vVar.f9383h.f9350l.contains(hVar);
                h hVar3 = vVar.f9384i;
                boolean contains2 = hVar3.f9350l.contains(hVar2);
                long j8 = vVar.j();
                h hVar4 = vVar.f9383h;
                if (contains && contains2) {
                    long b7 = o.b(hVar4, 0L);
                    ArrayList arrayList3 = arrayList2;
                    i8 = size;
                    long a7 = o.a(hVar3, 0L);
                    long j9 = b7 - j8;
                    int i11 = hVar3.f9344f;
                    arrayList = arrayList3;
                    i9 = i10;
                    if (j9 >= (-i11)) {
                        j9 += i11;
                    }
                    long j10 = (-a7) - j8;
                    long j11 = hVar4.f9344f;
                    long j12 = j10 - j11;
                    if (j12 >= j11) {
                        j12 -= j11;
                    }
                    u.i iVar = vVar.f9377b;
                    if (i7 == 0) {
                        f7 = iVar.f9139f0;
                    } else if (i7 == 1) {
                        f7 = iVar.f9141g0;
                    } else {
                        iVar.getClass();
                        f7 = -1.0f;
                    }
                    float f8 = f7 > CropImageView.DEFAULT_ASPECT_RATIO ? (long) ((j9 / (1.0f - f7)) + (j12 / f7)) : 0L;
                    max = (hVar4.f9344f + ((((long) ((f8 * f7) + 0.5f)) + j8) + ((long) a.a.a(1.0f, f7, f8, 0.5f)))) - hVar3.f9344f;
                } else {
                    arrayList = arrayList2;
                    i8 = size;
                    i9 = i10;
                    max = contains ? Math.max(o.b(hVar4, hVar4.f9344f), hVar4.f9344f + j8) : contains2 ? Math.max(-o.a(hVar3, hVar3.f9344f), (-hVar3.f9344f) + j8) : (vVar.j() + hVar4.f9344f) - hVar3.f9344f;
                }
            } else {
                arrayList = arrayList2;
                i8 = size;
                i9 = i10;
                max = 0;
            }
            j7 = Math.max(j7, max);
            i10 = i9 + 1;
            jVar2 = jVar;
            size = i8;
            arrayList2 = arrayList;
        }
        return (int) j7;
    }

    public final void e(v vVar, int i7, ArrayList arrayList) {
        h hVar;
        Iterator it = vVar.f9383h.f9349k.iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            hVar = vVar.f9384i;
            if (!hasNext) {
                break;
            }
            e eVar = (e) it.next();
            if (eVar instanceof h) {
                a((h) eVar, i7, arrayList, null);
            } else if (eVar instanceof v) {
                a(((v) eVar).f9383h, i7, arrayList, null);
            }
        }
        Iterator it2 = hVar.f9349k.iterator();
        while (it2.hasNext()) {
            e eVar2 = (e) it2.next();
            if (eVar2 instanceof h) {
                a((h) eVar2, i7, arrayList, null);
            } else if (eVar2 instanceof v) {
                a(((v) eVar2).f9384i, i7, arrayList, null);
            }
        }
        if (i7 == 1) {
            Iterator it3 = ((q) vVar).f9358k.f9349k.iterator();
            while (it3.hasNext()) {
                e eVar3 = (e) it3.next();
                if (eVar3 instanceof h) {
                    a((h) eVar3, i7, arrayList, null);
                }
            }
        }
    }

    public final void f(u.i iVar, u.h hVar, int i7, u.h hVar2, int i8) {
        b bVar = this.f9336g;
        bVar.f9315a = hVar;
        bVar.f9316b = hVar2;
        bVar.f9317c = i7;
        bVar.f9318d = i8;
        this.f9335f.b(iVar, bVar);
        iVar.Q(bVar.f9319e);
        iVar.N(bVar.f9320f);
        iVar.E = bVar.f9322h;
        iVar.K(bVar.f9321g);
    }

    public final void g() {
        a aVar;
        Iterator it = this.f9330a.f9216v0.iterator();
        while (it.hasNext()) {
            u.i iVar = (u.i) it.next();
            if (!iVar.f9128a) {
                u.h[] hVarArr = iVar.U;
                boolean z6 = false;
                u.h hVar = hVarArr[0];
                u.h hVar2 = hVarArr[1];
                int i7 = iVar.f9162r;
                int i8 = iVar.f9164s;
                u.h hVar3 = u.h.WRAP_CONTENT;
                boolean z7 = hVar == hVar3 || (hVar == u.h.MATCH_CONSTRAINT && i7 == 1);
                if (hVar2 == hVar3 || (hVar2 == u.h.MATCH_CONSTRAINT && i8 == 1)) {
                    z6 = true;
                }
                i iVar2 = iVar.f9134d.f9380e;
                boolean z8 = iVar2.f9348j;
                i iVar3 = iVar.f9136e.f9380e;
                boolean z9 = iVar3.f9348j;
                if (z8 && z9) {
                    u.h hVar4 = u.h.FIXED;
                    f(iVar, hVar4, iVar2.f9345g, hVar4, iVar3.f9345g);
                    iVar.f9128a = true;
                } else if (z8 && z6) {
                    f(iVar, u.h.FIXED, iVar2.f9345g, hVar3, iVar3.f9345g);
                    if (hVar2 == u.h.MATCH_CONSTRAINT) {
                        iVar.f9136e.f9380e.f9351m = iVar.m();
                    } else {
                        iVar.f9136e.f9380e.d(iVar.m());
                        iVar.f9128a = true;
                    }
                } else if (z9 && z7) {
                    f(iVar, hVar3, iVar2.f9345g, u.h.FIXED, iVar3.f9345g);
                    if (hVar == u.h.MATCH_CONSTRAINT) {
                        iVar.f9134d.f9380e.f9351m = iVar.s();
                    } else {
                        iVar.f9134d.f9380e.d(iVar.s());
                        iVar.f9128a = true;
                    }
                }
                if (iVar.f9128a && (aVar = iVar.f9136e.f9359l) != null) {
                    aVar.d(iVar.f9133c0);
                }
            }
        }
    }
}
