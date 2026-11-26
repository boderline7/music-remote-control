package v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n extends v {

    /* renamed from: k, reason: collision with root package name */
    public static final int[] f9354k = new int[2];

    public static void m(int[] iArr, int i7, int i8, int i9, int i10, float f7, int i11) {
        int i12 = i8 - i7;
        int i13 = i10 - i9;
        if (i11 != -1) {
            if (i11 == 0) {
                iArr[0] = (int) ((i13 * f7) + 0.5f);
                iArr[1] = i13;
                return;
            } else {
                if (i11 != 1) {
                    return;
                }
                iArr[0] = i12;
                iArr[1] = (int) ((i12 * f7) + 0.5f);
                return;
            }
        }
        int i14 = (int) ((i13 * f7) + 0.5f);
        int i15 = (int) ((i12 / f7) + 0.5f);
        if (i14 <= i12) {
            iArr[0] = i14;
            iArr[1] = i13;
        } else if (i15 <= i13) {
            iArr[0] = i12;
            iArr[1] = i15;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x0249, code lost:
    
        if (r15 != 1) goto L128;
     */
    @Override // v.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(v.e r23) {
        /*
            Method dump skipped, instructions count: 914
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v.n.a(v.e):void");
    }

    @Override // v.v
    public final void d() {
        u.i iVar;
        u.i iVar2;
        u.h hVar;
        u.i iVar3;
        u.i iVar4;
        u.h hVar2;
        u.i iVar5 = this.f9377b;
        boolean z6 = iVar5.f9128a;
        i iVar6 = this.f9380e;
        if (z6) {
            iVar6.d(iVar5.s());
        }
        boolean z7 = iVar6.f9348j;
        h hVar3 = this.f9384i;
        h hVar4 = this.f9383h;
        if (z7) {
            u.h hVar5 = this.f9379d;
            u.h hVar6 = u.h.MATCH_PARENT;
            if (hVar5 == hVar6 && (iVar2 = (iVar = this.f9377b).V) != null && ((hVar = iVar2.U[0]) == u.h.FIXED || hVar == hVar6)) {
                v.b(hVar4, iVar2.f9134d.f9383h, iVar.J.e());
                v.b(hVar3, iVar2.f9134d.f9384i, -this.f9377b.L.e());
                return;
            }
        } else {
            u.i iVar7 = this.f9377b;
            u.h hVar7 = iVar7.U[0];
            this.f9379d = hVar7;
            if (hVar7 != u.h.MATCH_CONSTRAINT) {
                u.h hVar8 = u.h.MATCH_PARENT;
                if (hVar7 == hVar8 && (iVar4 = iVar7.V) != null && ((hVar2 = iVar4.U[0]) == u.h.FIXED || hVar2 == hVar8)) {
                    int s6 = (iVar4.s() - this.f9377b.J.e()) - this.f9377b.L.e();
                    v.b(hVar4, iVar4.f9134d.f9383h, this.f9377b.J.e());
                    v.b(hVar3, iVar4.f9134d.f9384i, -this.f9377b.L.e());
                    iVar6.d(s6);
                    return;
                }
                if (hVar7 == u.h.FIXED) {
                    iVar6.d(iVar7.s());
                }
            }
        }
        if (iVar6.f9348j) {
            u.i iVar8 = this.f9377b;
            if (iVar8.f9128a) {
                u.f[] fVarArr = iVar8.R;
                u.f fVar = fVarArr[0];
                u.f fVar2 = fVar.f9121f;
                if (fVar2 != null && fVarArr[1].f9121f != null) {
                    if (iVar8.z()) {
                        hVar4.f9344f = this.f9377b.R[0].e();
                        hVar3.f9344f = -this.f9377b.R[1].e();
                        return;
                    }
                    h h7 = v.h(this.f9377b.R[0]);
                    if (h7 != null) {
                        v.b(hVar4, h7, this.f9377b.R[0].e());
                    }
                    h h8 = v.h(this.f9377b.R[1]);
                    if (h8 != null) {
                        v.b(hVar3, h8, -this.f9377b.R[1].e());
                    }
                    hVar4.f9340b = true;
                    hVar3.f9340b = true;
                    return;
                }
                if (fVar2 != null) {
                    h h9 = v.h(fVar);
                    if (h9 != null) {
                        v.b(hVar4, h9, this.f9377b.R[0].e());
                        v.b(hVar3, hVar4, iVar6.f9345g);
                        return;
                    }
                    return;
                }
                u.f fVar3 = fVarArr[1];
                if (fVar3.f9121f != null) {
                    h h10 = v.h(fVar3);
                    if (h10 != null) {
                        v.b(hVar3, h10, -this.f9377b.R[1].e());
                        v.b(hVar4, hVar3, -iVar6.f9345g);
                        return;
                    }
                    return;
                }
                if ((iVar8 instanceof u.o) || iVar8.V == null || iVar8.k(u.e.CENTER).f9121f != null) {
                    return;
                }
                u.i iVar9 = this.f9377b;
                v.b(hVar4, iVar9.V.f9134d.f9383h, iVar9.t());
                v.b(hVar3, hVar4, iVar6.f9345g);
                return;
            }
        }
        if (this.f9379d == u.h.MATCH_CONSTRAINT) {
            u.i iVar10 = this.f9377b;
            int i7 = iVar10.f9162r;
            if (i7 == 2) {
                u.i iVar11 = iVar10.V;
                if (iVar11 != null) {
                    i iVar12 = iVar11.f9136e.f9380e;
                    iVar6.f9350l.add(iVar12);
                    iVar12.f9349k.add(iVar6);
                    iVar6.f9340b = true;
                    iVar6.f9349k.add(hVar4);
                    iVar6.f9349k.add(hVar3);
                }
            } else if (i7 == 3) {
                if (iVar10.f9164s == 3) {
                    hVar4.f9339a = this;
                    hVar3.f9339a = this;
                    q qVar = iVar10.f9136e;
                    qVar.f9383h.f9339a = this;
                    qVar.f9384i.f9339a = this;
                    iVar6.f9339a = this;
                    if (iVar10.A()) {
                        iVar6.f9350l.add(this.f9377b.f9136e.f9380e);
                        this.f9377b.f9136e.f9380e.f9349k.add(iVar6);
                        q qVar2 = this.f9377b.f9136e;
                        qVar2.f9380e.f9339a = this;
                        iVar6.f9350l.add(qVar2.f9383h);
                        iVar6.f9350l.add(this.f9377b.f9136e.f9384i);
                        this.f9377b.f9136e.f9383h.f9349k.add(iVar6);
                        this.f9377b.f9136e.f9384i.f9349k.add(iVar6);
                    } else if (this.f9377b.z()) {
                        this.f9377b.f9136e.f9380e.f9350l.add(iVar6);
                        iVar6.f9349k.add(this.f9377b.f9136e.f9380e);
                    } else {
                        this.f9377b.f9136e.f9380e.f9350l.add(iVar6);
                    }
                } else {
                    i iVar13 = iVar10.f9136e.f9380e;
                    iVar6.f9350l.add(iVar13);
                    iVar13.f9349k.add(iVar6);
                    this.f9377b.f9136e.f9383h.f9349k.add(iVar6);
                    this.f9377b.f9136e.f9384i.f9349k.add(iVar6);
                    iVar6.f9340b = true;
                    iVar6.f9349k.add(hVar4);
                    iVar6.f9349k.add(hVar3);
                    hVar4.f9350l.add(iVar6);
                    hVar3.f9350l.add(iVar6);
                }
            }
        }
        u.i iVar14 = this.f9377b;
        u.f[] fVarArr2 = iVar14.R;
        u.f fVar4 = fVarArr2[0];
        u.f fVar5 = fVar4.f9121f;
        if (fVar5 != null && fVarArr2[1].f9121f != null) {
            if (iVar14.z()) {
                hVar4.f9344f = this.f9377b.R[0].e();
                hVar3.f9344f = -this.f9377b.R[1].e();
                return;
            }
            h h11 = v.h(this.f9377b.R[0]);
            h h12 = v.h(this.f9377b.R[1]);
            if (h11 != null) {
                h11.b(this);
            }
            if (h12 != null) {
                h12.b(this);
            }
            this.f9385j = u.CENTER;
            return;
        }
        if (fVar5 != null) {
            h h13 = v.h(fVar4);
            if (h13 != null) {
                v.b(hVar4, h13, this.f9377b.R[0].e());
                c(hVar3, hVar4, 1, iVar6);
                return;
            }
            return;
        }
        u.f fVar6 = fVarArr2[1];
        if (fVar6.f9121f != null) {
            h h14 = v.h(fVar6);
            if (h14 != null) {
                v.b(hVar3, h14, -this.f9377b.R[1].e());
                c(hVar4, hVar3, -1, iVar6);
                return;
            }
            return;
        }
        if ((iVar14 instanceof u.o) || (iVar3 = iVar14.V) == null) {
            return;
        }
        v.b(hVar4, iVar3.f9134d.f9383h, iVar14.t());
        c(hVar3, hVar4, 1, iVar6);
    }

    @Override // v.v
    public final void e() {
        h hVar = this.f9383h;
        if (hVar.f9348j) {
            this.f9377b.f9129a0 = hVar.f9345g;
        }
    }

    @Override // v.v
    public final void f() {
        this.f9378c = null;
        this.f9383h.c();
        this.f9384i.c();
        this.f9380e.c();
        this.f9382g = false;
    }

    @Override // v.v
    public final boolean k() {
        return this.f9379d != u.h.MATCH_CONSTRAINT || this.f9377b.f9162r == 0;
    }

    public final void n() {
        this.f9382g = false;
        h hVar = this.f9383h;
        hVar.c();
        hVar.f9348j = false;
        h hVar2 = this.f9384i;
        hVar2.c();
        hVar2.f9348j = false;
        this.f9380e.f9348j = false;
    }

    public final String toString() {
        return "HorizontalRun " + this.f9377b.f9149k0;
    }
}
