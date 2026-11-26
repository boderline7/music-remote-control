package u;

import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends p {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;

    /* renamed from: x0, reason: collision with root package name */
    public int f9094x0 = 0;

    /* renamed from: y0, reason: collision with root package name */
    public boolean f9095y0 = true;

    /* renamed from: z0, reason: collision with root package name */
    public int f9096z0 = 0;
    public boolean A0 = false;

    @Override // u.i
    public final boolean C() {
        return this.A0;
    }

    @Override // u.i
    public final boolean D() {
        return this.A0;
    }

    public final boolean V() {
        int i7;
        int i8;
        int i9;
        boolean z6 = true;
        int i10 = 0;
        while (true) {
            i7 = this.f9211w0;
            if (i10 >= i7) {
                break;
            }
            i iVar = this.f9210v0[i10];
            if ((this.f9095y0 || iVar.d()) && ((((i8 = this.f9094x0) == 0 || i8 == 1) && !iVar.C()) || (((i9 = this.f9094x0) == 2 || i9 == 3) && !iVar.D()))) {
                z6 = false;
            }
            i10++;
        }
        if (!z6 || i7 <= 0) {
            return false;
        }
        int i11 = 0;
        boolean z7 = false;
        for (int i12 = 0; i12 < this.f9211w0; i12++) {
            i iVar2 = this.f9210v0[i12];
            if (this.f9095y0 || iVar2.d()) {
                if (!z7) {
                    int i13 = this.f9094x0;
                    if (i13 == 0) {
                        i11 = iVar2.k(e.LEFT).d();
                    } else if (i13 == 1) {
                        i11 = iVar2.k(e.RIGHT).d();
                    } else if (i13 == 2) {
                        i11 = iVar2.k(e.TOP).d();
                    } else if (i13 == 3) {
                        i11 = iVar2.k(e.BOTTOM).d();
                    }
                    z7 = true;
                }
                int i14 = this.f9094x0;
                if (i14 == 0) {
                    i11 = Math.min(i11, iVar2.k(e.LEFT).d());
                } else if (i14 == 1) {
                    i11 = Math.max(i11, iVar2.k(e.RIGHT).d());
                } else if (i14 == 2) {
                    i11 = Math.min(i11, iVar2.k(e.TOP).d());
                } else if (i14 == 3) {
                    i11 = Math.max(i11, iVar2.k(e.BOTTOM).d());
                }
            }
        }
        int i15 = i11 + this.f9096z0;
        int i16 = this.f9094x0;
        if (i16 == 0 || i16 == 1) {
            L(i15, i15);
        } else {
            M(i15, i15);
        }
        this.A0 = true;
        return true;
    }

    public final int W() {
        int i7 = this.f9094x0;
        if (i7 == 0 || i7 == 1) {
            return 0;
        }
        return (i7 == 2 || i7 == 3) ? 1 : -1;
    }

    @Override // u.i
    public final void c(s.d dVar, boolean z6) {
        boolean z7;
        int i7;
        int i8;
        f[] fVarArr = this.R;
        f fVar = this.J;
        fVarArr[0] = fVar;
        f fVar2 = this.K;
        int i9 = 2;
        fVarArr[2] = fVar2;
        f fVar3 = this.L;
        fVarArr[1] = fVar3;
        f fVar4 = this.M;
        fVarArr[3] = fVar4;
        for (f fVar5 : fVarArr) {
            fVar5.f9124i = dVar.k(fVar5);
        }
        int i10 = this.f9094x0;
        if (i10 < 0 || i10 >= 4) {
            return;
        }
        f fVar6 = fVarArr[i10];
        if (!this.A0) {
            V();
        }
        if (this.A0) {
            this.A0 = false;
            int i11 = this.f9094x0;
            if (i11 == 0 || i11 == 1) {
                dVar.d(fVar.f9124i, this.f9129a0);
                dVar.d(fVar3.f9124i, this.f9129a0);
                return;
            } else {
                if (i11 == 2 || i11 == 3) {
                    dVar.d(fVar2.f9124i, this.f9131b0);
                    dVar.d(fVar4.f9124i, this.f9131b0);
                    return;
                }
                return;
            }
        }
        for (int i12 = 0; i12 < this.f9211w0; i12++) {
            i iVar = this.f9210v0[i12];
            if ((this.f9095y0 || iVar.d()) && ((((i8 = this.f9094x0) == 0 || i8 == 1) && iVar.U[0] == h.MATCH_CONSTRAINT && iVar.J.f9121f != null && iVar.L.f9121f != null) || ((i8 == 2 || i8 == 3) && iVar.U[1] == h.MATCH_CONSTRAINT && iVar.K.f9121f != null && iVar.M.f9121f != null))) {
                z7 = true;
                break;
            }
        }
        z7 = false;
        boolean z8 = fVar.g() || fVar3.g();
        boolean z9 = fVar2.g() || fVar4.g();
        int i13 = (z7 || !(((i7 = this.f9094x0) == 0 && z8) || ((i7 == 2 && z9) || ((i7 == 1 && z8) || (i7 == 3 && z9))))) ? 4 : 5;
        int i14 = 0;
        while (i14 < this.f9211w0) {
            i iVar2 = this.f9210v0[i14];
            if (this.f9095y0 || iVar2.d()) {
                s.i k7 = dVar.k(iVar2.R[this.f9094x0]);
                int i15 = this.f9094x0;
                f fVar7 = iVar2.R[i15];
                fVar7.f9124i = k7;
                f fVar8 = fVar7.f9121f;
                int i16 = (fVar8 == null || fVar8.f9119d != this) ? 0 : fVar7.f9122g;
                if (i15 == 0 || i15 == i9) {
                    s.i iVar3 = fVar6.f9124i;
                    int i17 = this.f9096z0 - i16;
                    s.c l7 = dVar.l();
                    s.i m6 = dVar.m();
                    m6.f8640d = 0;
                    l7.c(iVar3, k7, m6, i17);
                    dVar.c(l7);
                } else {
                    s.i iVar4 = fVar6.f9124i;
                    int i18 = this.f9096z0 + i16;
                    s.c l8 = dVar.l();
                    s.i m7 = dVar.m();
                    m7.f8640d = 0;
                    l8.b(iVar4, k7, m7, i18);
                    dVar.c(l8);
                }
                dVar.e(fVar6.f9124i, k7, this.f9096z0 + i16, i13);
            }
            i14++;
            i9 = 2;
        }
        int i19 = this.f9094x0;
        if (i19 == 0) {
            dVar.e(fVar3.f9124i, fVar.f9124i, 0, 8);
            dVar.e(fVar.f9124i, this.V.L.f9124i, 0, 4);
            dVar.e(fVar.f9124i, this.V.J.f9124i, 0, 0);
            return;
        }
        if (i19 == 1) {
            dVar.e(fVar.f9124i, fVar3.f9124i, 0, 8);
            dVar.e(fVar.f9124i, this.V.J.f9124i, 0, 4);
            dVar.e(fVar.f9124i, this.V.L.f9124i, 0, 0);
        } else if (i19 == 2) {
            dVar.e(fVar4.f9124i, fVar2.f9124i, 0, 8);
            dVar.e(fVar2.f9124i, this.V.M.f9124i, 0, 4);
            dVar.e(fVar2.f9124i, this.V.K.f9124i, 0, 0);
        } else if (i19 == 3) {
            dVar.e(fVar2.f9124i, fVar4.f9124i, 0, 8);
            dVar.e(fVar2.f9124i, this.V.K.f9124i, 0, 4);
            dVar.e(fVar2.f9124i, this.V.M.f9124i, 0, 0);
        }
    }

    @Override // u.i
    public final boolean d() {
        return true;
    }

    @Override // u.p, u.i
    public final void h(i iVar, HashMap hashMap) {
        super.h(iVar, hashMap);
        a aVar = (a) iVar;
        this.f9094x0 = aVar.f9094x0;
        this.f9095y0 = aVar.f9095y0;
        this.f9096z0 = aVar.f9096z0;
    }

    @Override // u.i
    public final String toString() {
        String i7 = a.a.i(new StringBuilder("[Barrier] "), this.f9149k0, " {");
        for (int i8 = 0; i8 < this.f9211w0; i8++) {
            i iVar = this.f9210v0[i8];
            if (i8 > 0) {
                i7 = a.a.g(i7, ", ");
            }
            StringBuilder j7 = a.a.j(i7);
            j7.append(iVar.f9149k0);
            i7 = j7.toString();
        }
        return a.a.g(i7, "}");
    }
}
