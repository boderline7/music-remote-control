package u;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public int f9179a;

    /* renamed from: d, reason: collision with root package name */
    public f f9182d;

    /* renamed from: e, reason: collision with root package name */
    public f f9183e;

    /* renamed from: f, reason: collision with root package name */
    public f f9184f;

    /* renamed from: g, reason: collision with root package name */
    public f f9185g;

    /* renamed from: h, reason: collision with root package name */
    public int f9186h;

    /* renamed from: i, reason: collision with root package name */
    public int f9187i;

    /* renamed from: j, reason: collision with root package name */
    public int f9188j;

    /* renamed from: k, reason: collision with root package name */
    public int f9189k;

    /* renamed from: q, reason: collision with root package name */
    public int f9195q;

    /* renamed from: r, reason: collision with root package name */
    public final /* synthetic */ l f9196r;

    /* renamed from: b, reason: collision with root package name */
    public i f9180b = null;

    /* renamed from: c, reason: collision with root package name */
    public int f9181c = 0;

    /* renamed from: l, reason: collision with root package name */
    public int f9190l = 0;

    /* renamed from: m, reason: collision with root package name */
    public int f9191m = 0;

    /* renamed from: n, reason: collision with root package name */
    public int f9192n = 0;

    /* renamed from: o, reason: collision with root package name */
    public int f9193o = 0;

    /* renamed from: p, reason: collision with root package name */
    public int f9194p = 0;

    public k(l lVar, int i7, f fVar, f fVar2, f fVar3, f fVar4, int i8) {
        this.f9196r = lVar;
        this.f9186h = 0;
        this.f9187i = 0;
        this.f9188j = 0;
        this.f9189k = 0;
        this.f9195q = 0;
        this.f9179a = i7;
        this.f9182d = fVar;
        this.f9183e = fVar2;
        this.f9184f = fVar3;
        this.f9185g = fVar4;
        this.f9186h = lVar.B0;
        this.f9187i = lVar.f9213x0;
        this.f9188j = lVar.C0;
        this.f9189k = lVar.f9214y0;
        this.f9195q = i8;
    }

    public final void a(i iVar) {
        int i7 = this.f9179a;
        l lVar = this.f9196r;
        if (i7 == 0) {
            int Y = lVar.Y(this.f9195q, iVar);
            if (iVar.U[0] == h.MATCH_CONSTRAINT) {
                this.f9194p++;
                Y = 0;
            }
            this.f9190l = Y + (iVar.f9145i0 != 8 ? lVar.U0 : 0) + this.f9190l;
            int X = lVar.X(this.f9195q, iVar);
            if (this.f9180b == null || this.f9181c < X) {
                this.f9180b = iVar;
                this.f9181c = X;
                this.f9191m = X;
            }
        } else {
            int Y2 = lVar.Y(this.f9195q, iVar);
            int X2 = lVar.X(this.f9195q, iVar);
            if (iVar.U[1] == h.MATCH_CONSTRAINT) {
                this.f9194p++;
                X2 = 0;
            }
            this.f9191m = X2 + (iVar.f9145i0 != 8 ? lVar.V0 : 0) + this.f9191m;
            if (this.f9180b == null || this.f9181c < Y2) {
                this.f9180b = iVar;
                this.f9181c = Y2;
                this.f9190l = Y2;
            }
        }
        this.f9193o++;
    }

    public final void b(int i7, boolean z6, boolean z7) {
        l lVar;
        int i8;
        int i9;
        int i10;
        i iVar;
        int i11;
        char c7;
        int i12;
        float f7;
        float f8;
        int i13;
        float f9;
        int i14;
        int i15 = this.f9193o;
        int i16 = 0;
        while (true) {
            lVar = this.f9196r;
            if (i16 >= i15 || (i14 = this.f9192n + i16) >= lVar.f9203g1) {
                break;
            }
            i iVar2 = lVar.f9202f1[i14];
            if (iVar2 != null) {
                iVar2.F();
            }
            i16++;
        }
        if (i15 == 0 || this.f9180b == null) {
            return;
        }
        boolean z8 = z7 && i7 == 0;
        int i17 = -1;
        int i18 = -1;
        for (int i19 = 0; i19 < i15; i19++) {
            int i20 = this.f9192n + (z6 ? (i15 - 1) - i19 : i19);
            if (i20 >= lVar.f9203g1) {
                break;
            }
            i iVar3 = lVar.f9202f1[i20];
            if (iVar3 != null && iVar3.f9145i0 == 0) {
                if (i17 == -1) {
                    i17 = i19;
                }
                i18 = i19;
            }
        }
        if (this.f9179a != 0) {
            i iVar4 = this.f9180b;
            iVar4.f9153m0 = lVar.I0;
            int i21 = this.f9186h;
            if (i7 > 0) {
                i21 += lVar.U0;
            }
            f fVar = iVar4.J;
            f fVar2 = iVar4.L;
            if (z6) {
                fVar2.a(this.f9184f, i21);
                if (z7) {
                    fVar.a(this.f9182d, this.f9188j);
                }
                if (i7 > 0) {
                    this.f9184f.f9119d.J.a(fVar2, 0);
                }
            } else {
                fVar.a(this.f9182d, i21);
                if (z7) {
                    fVar2.a(this.f9184f, this.f9188j);
                }
                if (i7 > 0) {
                    this.f9182d.f9119d.L.a(fVar, 0);
                }
            }
            i iVar5 = null;
            for (int i22 = 0; i22 < i15; i22++) {
                int i23 = this.f9192n + i22;
                if (i23 >= lVar.f9203g1) {
                    return;
                }
                i iVar6 = lVar.f9202f1[i23];
                if (iVar6 != null) {
                    f fVar3 = iVar6.K;
                    if (i22 == 0) {
                        iVar6.g(fVar3, this.f9183e, this.f9187i);
                        int i24 = lVar.J0;
                        float f10 = lVar.P0;
                        if (this.f9192n == 0) {
                            i10 = lVar.L0;
                            i8 = i24;
                            i9 = -1;
                            if (i10 != -1) {
                                f10 = lVar.R0;
                                iVar6.f9155n0 = i10;
                                iVar6.f9141g0 = f10;
                            }
                        } else {
                            i8 = i24;
                            i9 = -1;
                        }
                        if (!z7 || (i10 = lVar.N0) == i9) {
                            i10 = i8;
                        } else {
                            f10 = lVar.T0;
                        }
                        iVar6.f9155n0 = i10;
                        iVar6.f9141g0 = f10;
                    }
                    if (i22 == i15 - 1) {
                        iVar6.g(iVar6.M, this.f9185g, this.f9189k);
                    }
                    if (iVar5 != null) {
                        int i25 = lVar.V0;
                        f fVar4 = iVar5.M;
                        fVar3.a(fVar4, i25);
                        if (i22 == i17) {
                            int i26 = this.f9187i;
                            if (fVar3.h()) {
                                fVar3.f9123h = i26;
                            }
                        }
                        fVar4.a(fVar3, 0);
                        if (i22 == i18 + 1) {
                            int i27 = this.f9189k;
                            if (fVar4.h()) {
                                fVar4.f9123h = i27;
                            }
                        }
                    }
                    if (iVar6 != iVar4) {
                        f fVar5 = iVar6.L;
                        f fVar6 = iVar6.J;
                        if (z6) {
                            int i28 = lVar.W0;
                            if (i28 == 0) {
                                fVar5.a(fVar2, 0);
                            } else if (i28 == 1) {
                                fVar6.a(fVar, 0);
                            } else if (i28 == 2) {
                                fVar6.a(fVar, 0);
                                fVar5.a(fVar2, 0);
                            }
                        } else {
                            int i29 = lVar.W0;
                            if (i29 == 0) {
                                fVar6.a(fVar, 0);
                            } else if (i29 == 1) {
                                fVar5.a(fVar2, 0);
                            } else if (i29 == 2) {
                                if (z8) {
                                    fVar6.a(this.f9182d, this.f9186h);
                                    fVar5.a(this.f9184f, this.f9188j);
                                } else {
                                    fVar6.a(fVar, 0);
                                    fVar5.a(fVar2, 0);
                                }
                            }
                            iVar5 = iVar6;
                        }
                    }
                    iVar5 = iVar6;
                }
            }
            return;
        }
        i iVar7 = this.f9180b;
        iVar7.f9155n0 = lVar.J0;
        int i30 = this.f9187i;
        if (i7 > 0) {
            i30 += lVar.V0;
        }
        f fVar7 = this.f9183e;
        f fVar8 = iVar7.K;
        fVar8.a(fVar7, i30);
        f fVar9 = iVar7.M;
        if (z7) {
            fVar9.a(this.f9185g, this.f9189k);
        }
        if (i7 > 0) {
            this.f9183e.f9119d.M.a(fVar8, 0);
        }
        if (lVar.X0 == 3 && !iVar7.E) {
            for (int i31 = 0; i31 < i15; i31++) {
                int i32 = this.f9192n + (z6 ? (i15 - 1) - i31 : i31);
                if (i32 >= lVar.f9203g1) {
                    break;
                }
                iVar = lVar.f9202f1[i32];
                if (iVar.E) {
                    break;
                }
            }
        }
        iVar = iVar7;
        int i33 = 0;
        i iVar8 = null;
        while (i33 < i15) {
            int i34 = z6 ? (i15 - 1) - i33 : i33;
            int i35 = this.f9192n + i34;
            if (i35 >= lVar.f9203g1) {
                return;
            }
            i iVar9 = lVar.f9202f1[i35];
            if (iVar9 == null) {
                i11 = i15;
                c7 = 3;
            } else {
                f fVar10 = iVar9.J;
                if (i33 == 0) {
                    iVar9.g(fVar10, this.f9182d, this.f9186h);
                }
                if (i34 == 0) {
                    int i36 = lVar.I0;
                    if (z6) {
                        i12 = i36;
                        f7 = 1.0f - lVar.O0;
                    } else {
                        i12 = i36;
                        f7 = lVar.O0;
                    }
                    if (this.f9192n == 0) {
                        int i37 = lVar.K0;
                        f8 = f7;
                        if (i37 != -1) {
                            f9 = z6 ? 1.0f - lVar.Q0 : lVar.Q0;
                            i13 = i37;
                            iVar9.f9153m0 = i13;
                            iVar9.f9139f0 = f9;
                        }
                    } else {
                        f8 = f7;
                    }
                    if (!z7 || (i13 = lVar.M0) == -1) {
                        i13 = i12;
                        f9 = f8;
                    } else {
                        f9 = z6 ? 1.0f - lVar.S0 : lVar.S0;
                    }
                    iVar9.f9153m0 = i13;
                    iVar9.f9139f0 = f9;
                }
                if (i33 == i15 - 1) {
                    i11 = i15;
                    iVar9.g(iVar9.L, this.f9184f, this.f9188j);
                } else {
                    i11 = i15;
                }
                if (iVar8 != null) {
                    int i38 = lVar.U0;
                    f fVar11 = iVar8.L;
                    fVar10.a(fVar11, i38);
                    if (i33 == i17) {
                        int i39 = this.f9186h;
                        if (fVar10.h()) {
                            fVar10.f9123h = i39;
                        }
                    }
                    fVar11.a(fVar10, 0);
                    if (i33 == i18 + 1) {
                        int i40 = this.f9188j;
                        if (fVar11.h()) {
                            fVar11.f9123h = i40;
                        }
                    }
                }
                if (iVar9 != iVar7) {
                    int i41 = lVar.X0;
                    c7 = 3;
                    if (i41 == 3 && iVar.E && iVar9 != iVar && iVar9.E) {
                        iVar9.N.a(iVar.N, 0);
                    } else {
                        f fVar12 = iVar9.K;
                        if (i41 != 0) {
                            f fVar13 = iVar9.M;
                            if (i41 == 1) {
                                fVar13.a(fVar9, 0);
                            } else if (z8) {
                                fVar12.a(this.f9183e, this.f9187i);
                                fVar13.a(this.f9185g, this.f9189k);
                            } else {
                                fVar12.a(fVar8, 0);
                                fVar13.a(fVar9, 0);
                            }
                        } else {
                            fVar12.a(fVar8, 0);
                        }
                    }
                } else {
                    c7 = 3;
                }
                iVar8 = iVar9;
            }
            i33++;
            i15 = i11;
        }
    }

    public final int c() {
        return this.f9179a == 1 ? this.f9191m - this.f9196r.V0 : this.f9191m;
    }

    public final int d() {
        return this.f9179a == 0 ? this.f9190l - this.f9196r.U0 : this.f9190l;
    }

    public final void e(int i7) {
        int i8 = this.f9194p;
        if (i8 == 0) {
            return;
        }
        int i9 = this.f9193o;
        int i10 = i7 / i8;
        for (int i11 = 0; i11 < i9; i11++) {
            int i12 = this.f9192n;
            int i13 = i12 + i11;
            l lVar = this.f9196r;
            if (i13 >= lVar.f9203g1) {
                break;
            }
            i iVar = lVar.f9202f1[i12 + i11];
            if (this.f9179a == 0) {
                if (iVar != null) {
                    h[] hVarArr = iVar.U;
                    if (hVarArr[0] == h.MATCH_CONSTRAINT && iVar.f9162r == 0) {
                        lVar.W(iVar, h.FIXED, i10, hVarArr[1], iVar.m());
                    }
                }
            } else if (iVar != null) {
                h[] hVarArr2 = iVar.U;
                if (hVarArr2[1] == h.MATCH_CONSTRAINT && iVar.f9164s == 0) {
                    lVar.W(iVar, hVarArr2[0], iVar.s(), h.FIXED, i10);
                }
            }
        }
        this.f9190l = 0;
        this.f9191m = 0;
        this.f9180b = null;
        this.f9181c = 0;
        int i14 = this.f9193o;
        for (int i15 = 0; i15 < i14; i15++) {
            int i16 = this.f9192n + i15;
            l lVar2 = this.f9196r;
            if (i16 >= lVar2.f9203g1) {
                return;
            }
            i iVar2 = lVar2.f9202f1[i16];
            if (this.f9179a == 0) {
                int s6 = iVar2.s();
                int i17 = lVar2.U0;
                if (iVar2.f9145i0 == 8) {
                    i17 = 0;
                }
                this.f9190l = s6 + i17 + this.f9190l;
                int X = lVar2.X(this.f9195q, iVar2);
                if (this.f9180b == null || this.f9181c < X) {
                    this.f9180b = iVar2;
                    this.f9181c = X;
                    this.f9191m = X;
                }
            } else {
                int Y = lVar2.Y(this.f9195q, iVar2);
                int X2 = lVar2.X(this.f9195q, iVar2);
                int i18 = lVar2.V0;
                if (iVar2.f9145i0 == 8) {
                    i18 = 0;
                }
                this.f9191m = X2 + i18 + this.f9191m;
                if (this.f9180b == null || this.f9181c < Y) {
                    this.f9180b = iVar2;
                    this.f9181c = Y;
                    this.f9190l = Y;
                }
            }
        }
    }

    public final void f(int i7, f fVar, f fVar2, f fVar3, f fVar4, int i8, int i9, int i10, int i11, int i12) {
        this.f9179a = i7;
        this.f9182d = fVar;
        this.f9183e = fVar2;
        this.f9184f = fVar3;
        this.f9185g = fVar4;
        this.f9186h = i8;
        this.f9187i = i9;
        this.f9188j = i10;
        this.f9189k = i11;
        this.f9195q = i12;
    }
}
