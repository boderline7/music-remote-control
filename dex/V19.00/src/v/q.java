package v;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q extends v {

    /* renamed from: k, reason: collision with root package name */
    public h f9358k;

    /* renamed from: l, reason: collision with root package name */
    public a f9359l;

    @Override // v.e
    public final void a(e eVar) {
        float f7;
        float f8;
        float f9;
        int i7;
        if (p.f9357a[this.f9385j.ordinal()] == 3) {
            u.i iVar = this.f9377b;
            l(iVar.K, iVar.M, 1);
            return;
        }
        i iVar2 = this.f9380e;
        if (iVar2.f9341c && !iVar2.f9348j && this.f9379d == u.h.MATCH_CONSTRAINT) {
            u.i iVar3 = this.f9377b;
            int i8 = iVar3.f9164s;
            if (i8 == 2) {
                u.i iVar4 = iVar3.V;
                if (iVar4 != null) {
                    if (iVar4.f9136e.f9380e.f9348j) {
                        iVar2.d((int) ((r1.f9345g * iVar3.f9174z) + 0.5f));
                    }
                }
            } else if (i8 == 3) {
                i iVar5 = iVar3.f9134d.f9380e;
                if (iVar5.f9348j) {
                    int i9 = iVar3.Z;
                    if (i9 == -1) {
                        f7 = iVar5.f9345g;
                        f8 = iVar3.Y;
                    } else if (i9 == 0) {
                        f9 = iVar5.f9345g * iVar3.Y;
                        i7 = (int) (f9 + 0.5f);
                        iVar2.d(i7);
                    } else if (i9 != 1) {
                        i7 = 0;
                        iVar2.d(i7);
                    } else {
                        f7 = iVar5.f9345g;
                        f8 = iVar3.Y;
                    }
                    f9 = f7 / f8;
                    i7 = (int) (f9 + 0.5f);
                    iVar2.d(i7);
                }
            }
        }
        h hVar = this.f9383h;
        if (hVar.f9341c) {
            h hVar2 = this.f9384i;
            if (hVar2.f9341c) {
                if (hVar.f9348j && hVar2.f9348j && iVar2.f9348j) {
                    return;
                }
                if (!iVar2.f9348j && this.f9379d == u.h.MATCH_CONSTRAINT) {
                    u.i iVar6 = this.f9377b;
                    if (iVar6.f9162r == 0 && !iVar6.A()) {
                        h hVar3 = (h) hVar.f9350l.get(0);
                        h hVar4 = (h) hVar2.f9350l.get(0);
                        int i10 = hVar3.f9345g + hVar.f9344f;
                        int i11 = hVar4.f9345g + hVar2.f9344f;
                        hVar.d(i10);
                        hVar2.d(i11);
                        iVar2.d(i11 - i10);
                        return;
                    }
                }
                if (!iVar2.f9348j && this.f9379d == u.h.MATCH_CONSTRAINT && this.f9376a == 1 && hVar.f9350l.size() > 0 && hVar2.f9350l.size() > 0) {
                    h hVar5 = (h) hVar.f9350l.get(0);
                    int i12 = (((h) hVar2.f9350l.get(0)).f9345g + hVar2.f9344f) - (hVar5.f9345g + hVar.f9344f);
                    int i13 = iVar2.f9351m;
                    if (i12 < i13) {
                        iVar2.d(i12);
                    } else {
                        iVar2.d(i13);
                    }
                }
                if (iVar2.f9348j && hVar.f9350l.size() > 0 && hVar2.f9350l.size() > 0) {
                    h hVar6 = (h) hVar.f9350l.get(0);
                    h hVar7 = (h) hVar2.f9350l.get(0);
                    int i14 = hVar6.f9345g;
                    int i15 = hVar.f9344f + i14;
                    int i16 = hVar7.f9345g;
                    int i17 = hVar2.f9344f + i16;
                    float f10 = this.f9377b.f9141g0;
                    if (hVar6 == hVar7) {
                        f10 = 0.5f;
                    } else {
                        i14 = i15;
                        i16 = i17;
                    }
                    hVar.d((int) ((((i16 - i14) - iVar2.f9345g) * f10) + i14 + 0.5f));
                    hVar2.d(hVar.f9345g + iVar2.f9345g);
                }
            }
        }
    }

    @Override // v.v
    public final void d() {
        u.i iVar;
        u.i iVar2;
        u.i iVar3;
        u.i iVar4;
        u.i iVar5 = this.f9377b;
        boolean z6 = iVar5.f9128a;
        i iVar6 = this.f9380e;
        if (z6) {
            iVar6.d(iVar5.m());
        }
        boolean z7 = iVar6.f9348j;
        h hVar = this.f9384i;
        h hVar2 = this.f9383h;
        if (!z7) {
            u.i iVar7 = this.f9377b;
            this.f9379d = iVar7.U[1];
            if (iVar7.E) {
                this.f9359l = new a(this);
            }
            u.h hVar3 = this.f9379d;
            if (hVar3 != u.h.MATCH_CONSTRAINT) {
                if (hVar3 == u.h.MATCH_PARENT && (iVar4 = this.f9377b.V) != null && iVar4.U[1] == u.h.FIXED) {
                    int m6 = (iVar4.m() - this.f9377b.K.e()) - this.f9377b.M.e();
                    v.b(hVar2, iVar4.f9136e.f9383h, this.f9377b.K.e());
                    v.b(hVar, iVar4.f9136e.f9384i, -this.f9377b.M.e());
                    iVar6.d(m6);
                    return;
                }
                if (hVar3 == u.h.FIXED) {
                    iVar6.d(this.f9377b.m());
                }
            }
        } else if (this.f9379d == u.h.MATCH_PARENT && (iVar2 = (iVar = this.f9377b).V) != null && iVar2.U[1] == u.h.FIXED) {
            v.b(hVar2, iVar2.f9136e.f9383h, iVar.K.e());
            v.b(hVar, iVar2.f9136e.f9384i, -this.f9377b.M.e());
            return;
        }
        boolean z8 = iVar6.f9348j;
        h hVar4 = this.f9358k;
        if (z8) {
            u.i iVar8 = this.f9377b;
            if (iVar8.f9128a) {
                u.f[] fVarArr = iVar8.R;
                u.f fVar = fVarArr[2];
                u.f fVar2 = fVar.f9121f;
                if (fVar2 != null && fVarArr[3].f9121f != null) {
                    if (iVar8.A()) {
                        hVar2.f9344f = this.f9377b.R[2].e();
                        hVar.f9344f = -this.f9377b.R[3].e();
                    } else {
                        h h7 = v.h(this.f9377b.R[2]);
                        if (h7 != null) {
                            v.b(hVar2, h7, this.f9377b.R[2].e());
                        }
                        h h8 = v.h(this.f9377b.R[3]);
                        if (h8 != null) {
                            v.b(hVar, h8, -this.f9377b.R[3].e());
                        }
                        hVar2.f9340b = true;
                        hVar.f9340b = true;
                    }
                    u.i iVar9 = this.f9377b;
                    if (iVar9.E) {
                        v.b(hVar4, hVar2, iVar9.f9133c0);
                        return;
                    }
                    return;
                }
                if (fVar2 != null) {
                    h h9 = v.h(fVar);
                    if (h9 != null) {
                        v.b(hVar2, h9, this.f9377b.R[2].e());
                        v.b(hVar, hVar2, iVar6.f9345g);
                        u.i iVar10 = this.f9377b;
                        if (iVar10.E) {
                            v.b(hVar4, hVar2, iVar10.f9133c0);
                            return;
                        }
                        return;
                    }
                    return;
                }
                u.f fVar3 = fVarArr[3];
                if (fVar3.f9121f != null) {
                    h h10 = v.h(fVar3);
                    if (h10 != null) {
                        v.b(hVar, h10, -this.f9377b.R[3].e());
                        v.b(hVar2, hVar, -iVar6.f9345g);
                    }
                    u.i iVar11 = this.f9377b;
                    if (iVar11.E) {
                        v.b(hVar4, hVar2, iVar11.f9133c0);
                        return;
                    }
                    return;
                }
                u.f fVar4 = fVarArr[4];
                if (fVar4.f9121f != null) {
                    h h11 = v.h(fVar4);
                    if (h11 != null) {
                        v.b(hVar4, h11, 0);
                        v.b(hVar2, hVar4, -this.f9377b.f9133c0);
                        v.b(hVar, hVar2, iVar6.f9345g);
                        return;
                    }
                    return;
                }
                if ((iVar8 instanceof u.o) || iVar8.V == null || iVar8.k(u.e.CENTER).f9121f != null) {
                    return;
                }
                u.i iVar12 = this.f9377b;
                v.b(hVar2, iVar12.V.f9136e.f9383h, iVar12.u());
                v.b(hVar, hVar2, iVar6.f9345g);
                u.i iVar13 = this.f9377b;
                if (iVar13.E) {
                    v.b(hVar4, hVar2, iVar13.f9133c0);
                    return;
                }
                return;
            }
        }
        if (z8 || this.f9379d != u.h.MATCH_CONSTRAINT) {
            iVar6.b(this);
        } else {
            u.i iVar14 = this.f9377b;
            int i7 = iVar14.f9164s;
            if (i7 == 2) {
                u.i iVar15 = iVar14.V;
                if (iVar15 != null) {
                    i iVar16 = iVar15.f9136e.f9380e;
                    iVar6.f9350l.add(iVar16);
                    iVar16.f9349k.add(iVar6);
                    iVar6.f9340b = true;
                    iVar6.f9349k.add(hVar2);
                    iVar6.f9349k.add(hVar);
                }
            } else if (i7 == 3 && !iVar14.A()) {
                u.i iVar17 = this.f9377b;
                if (iVar17.f9162r != 3) {
                    i iVar18 = iVar17.f9134d.f9380e;
                    iVar6.f9350l.add(iVar18);
                    iVar18.f9349k.add(iVar6);
                    iVar6.f9340b = true;
                    iVar6.f9349k.add(hVar2);
                    iVar6.f9349k.add(hVar);
                }
            }
        }
        u.i iVar19 = this.f9377b;
        u.f[] fVarArr2 = iVar19.R;
        u.f fVar5 = fVarArr2[2];
        u.f fVar6 = fVar5.f9121f;
        if (fVar6 != null && fVarArr2[3].f9121f != null) {
            if (iVar19.A()) {
                hVar2.f9344f = this.f9377b.R[2].e();
                hVar.f9344f = -this.f9377b.R[3].e();
            } else {
                h h12 = v.h(this.f9377b.R[2]);
                h h13 = v.h(this.f9377b.R[3]);
                if (h12 != null) {
                    h12.b(this);
                }
                if (h13 != null) {
                    h13.b(this);
                }
                this.f9385j = u.CENTER;
            }
            if (this.f9377b.E) {
                c(hVar4, hVar2, 1, this.f9359l);
            }
        } else if (fVar6 != null) {
            h h14 = v.h(fVar5);
            if (h14 != null) {
                v.b(hVar2, h14, this.f9377b.R[2].e());
                c(hVar, hVar2, 1, iVar6);
                if (this.f9377b.E) {
                    c(hVar4, hVar2, 1, this.f9359l);
                }
                u.h hVar5 = this.f9379d;
                u.h hVar6 = u.h.MATCH_CONSTRAINT;
                if (hVar5 == hVar6) {
                    u.i iVar20 = this.f9377b;
                    if (iVar20.Y > CropImageView.DEFAULT_ASPECT_RATIO) {
                        n nVar = iVar20.f9134d;
                        if (nVar.f9379d == hVar6) {
                            nVar.f9380e.f9349k.add(iVar6);
                            iVar6.f9350l.add(this.f9377b.f9134d.f9380e);
                            iVar6.f9339a = this;
                        }
                    }
                }
            }
        } else {
            u.f fVar7 = fVarArr2[3];
            if (fVar7.f9121f != null) {
                h h15 = v.h(fVar7);
                if (h15 != null) {
                    v.b(hVar, h15, -this.f9377b.R[3].e());
                    c(hVar2, hVar, -1, iVar6);
                    if (this.f9377b.E) {
                        c(hVar4, hVar2, 1, this.f9359l);
                    }
                }
            } else {
                u.f fVar8 = fVarArr2[4];
                if (fVar8.f9121f != null) {
                    h h16 = v.h(fVar8);
                    if (h16 != null) {
                        v.b(hVar4, h16, 0);
                        c(hVar2, hVar4, -1, this.f9359l);
                        c(hVar, hVar2, 1, iVar6);
                    }
                } else if (!(iVar19 instanceof u.o) && (iVar3 = iVar19.V) != null) {
                    v.b(hVar2, iVar3.f9136e.f9383h, iVar19.u());
                    c(hVar, hVar2, 1, iVar6);
                    if (this.f9377b.E) {
                        c(hVar4, hVar2, 1, this.f9359l);
                    }
                    u.h hVar7 = this.f9379d;
                    u.h hVar8 = u.h.MATCH_CONSTRAINT;
                    if (hVar7 == hVar8) {
                        u.i iVar21 = this.f9377b;
                        if (iVar21.Y > CropImageView.DEFAULT_ASPECT_RATIO) {
                            n nVar2 = iVar21.f9134d;
                            if (nVar2.f9379d == hVar8) {
                                nVar2.f9380e.f9349k.add(iVar6);
                                iVar6.f9350l.add(this.f9377b.f9134d.f9380e);
                                iVar6.f9339a = this;
                            }
                        }
                    }
                }
            }
        }
        if (iVar6.f9350l.size() == 0) {
            iVar6.f9341c = true;
        }
    }

    @Override // v.v
    public final void e() {
        h hVar = this.f9383h;
        if (hVar.f9348j) {
            this.f9377b.f9131b0 = hVar.f9345g;
        }
    }

    @Override // v.v
    public final void f() {
        this.f9378c = null;
        this.f9383h.c();
        this.f9384i.c();
        this.f9358k.c();
        this.f9380e.c();
        this.f9382g = false;
    }

    @Override // v.v
    public final boolean k() {
        return this.f9379d != u.h.MATCH_CONSTRAINT || this.f9377b.f9164s == 0;
    }

    public final void m() {
        this.f9382g = false;
        h hVar = this.f9383h;
        hVar.c();
        hVar.f9348j = false;
        h hVar2 = this.f9384i;
        hVar2.c();
        hVar2.f9348j = false;
        h hVar3 = this.f9358k;
        hVar3.c();
        hVar3.f9348j = false;
        this.f9380e.f9348j = false;
    }

    public final String toString() {
        return "VerticalRun " + this.f9377b.f9149k0;
    }
}
