package u;

import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends s {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_CHAIN_NEW = 3;
    public static final int WRAP_NONE = 0;

    /* renamed from: f1, reason: collision with root package name */
    public i[] f9202f1;
    public int I0 = -1;
    public int J0 = -1;
    public int K0 = -1;
    public int L0 = -1;
    public int M0 = -1;
    public int N0 = -1;
    public float O0 = 0.5f;
    public float P0 = 0.5f;
    public float Q0 = 0.5f;
    public float R0 = 0.5f;
    public float S0 = 0.5f;
    public float T0 = 0.5f;
    public int U0 = 0;
    public int V0 = 0;
    public int W0 = 2;
    public int X0 = 2;
    public int Y0 = 0;
    public int Z0 = -1;

    /* renamed from: a1, reason: collision with root package name */
    public int f9197a1 = 0;

    /* renamed from: b1, reason: collision with root package name */
    public final ArrayList f9198b1 = new ArrayList();

    /* renamed from: c1, reason: collision with root package name */
    public i[] f9199c1 = null;

    /* renamed from: d1, reason: collision with root package name */
    public i[] f9200d1 = null;

    /* renamed from: e1, reason: collision with root package name */
    public int[] f9201e1 = null;

    /* renamed from: g1, reason: collision with root package name */
    public int f9203g1 = 0;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0759  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x076f  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x078d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x078f  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0772  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x075e  */
    @Override // u.s
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void V(int r37, int r38, int r39, int r40) {
        /*
            Method dump skipped, instructions count: 1939
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u.l.V(int, int, int, int):void");
    }

    public final int X(int i7, i iVar) {
        if (iVar == null) {
            return 0;
        }
        if (iVar.U[1] == h.MATCH_CONSTRAINT) {
            int i8 = iVar.f9164s;
            if (i8 == 0) {
                return 0;
            }
            if (i8 == 2) {
                int i9 = (int) (iVar.f9174z * i7);
                if (i9 != iVar.m()) {
                    iVar.f9140g = true;
                    W(iVar, iVar.U[0], iVar.s(), h.FIXED, i9);
                }
                return i9;
            }
            if (i8 == 1) {
                return iVar.m();
            }
            if (i8 == 3) {
                return (int) ((iVar.s() * iVar.Y) + 0.5f);
            }
        }
        return iVar.m();
    }

    public final int Y(int i7, i iVar) {
        if (iVar == null) {
            return 0;
        }
        if (iVar.U[0] == h.MATCH_CONSTRAINT) {
            int i8 = iVar.f9162r;
            if (i8 == 0) {
                return 0;
            }
            if (i8 == 2) {
                int i9 = (int) (iVar.f9171w * i7);
                if (i9 != iVar.s()) {
                    iVar.f9140g = true;
                    W(iVar, h.FIXED, i9, iVar.U[1], iVar.m());
                }
                return i9;
            }
            if (i8 == 1) {
                return iVar.s();
            }
            if (i8 == 3) {
                return (int) ((iVar.m() * iVar.Y) + 0.5f);
            }
        }
        return iVar.s();
    }

    @Override // u.i
    public final void c(s.d dVar, boolean z6) {
        i iVar;
        float f7;
        int i7;
        super.c(dVar, z6);
        i iVar2 = this.V;
        boolean z7 = iVar2 != null && ((j) iVar2).A0;
        int i8 = this.Y0;
        ArrayList arrayList = this.f9198b1;
        if (i8 != 0) {
            if (i8 == 1) {
                int size = arrayList.size();
                int i9 = 0;
                while (i9 < size) {
                    ((k) arrayList.get(i9)).b(i9, z7, i9 == size + (-1));
                    i9++;
                }
            } else if (i8 != 2) {
                if (i8 == 3) {
                    int size2 = arrayList.size();
                    int i10 = 0;
                    while (i10 < size2) {
                        ((k) arrayList.get(i10)).b(i10, z7, i10 == size2 + (-1));
                        i10++;
                    }
                }
            } else if (this.f9201e1 != null && this.f9200d1 != null && this.f9199c1 != null) {
                for (int i11 = 0; i11 < this.f9203g1; i11++) {
                    this.f9202f1[i11].F();
                }
                int[] iArr = this.f9201e1;
                int i12 = iArr[0];
                int i13 = iArr[1];
                float f8 = this.O0;
                i iVar3 = null;
                int i14 = 0;
                while (i14 < i12) {
                    if (z7) {
                        i7 = (i12 - i14) - 1;
                        f7 = 1.0f - this.O0;
                    } else {
                        f7 = f8;
                        i7 = i14;
                    }
                    i iVar4 = this.f9200d1[i7];
                    if (iVar4 != null && iVar4.f9145i0 != 8) {
                        f fVar = iVar4.J;
                        if (i14 == 0) {
                            iVar4.g(fVar, this.J, this.B0);
                            iVar4.f9153m0 = this.I0;
                            iVar4.f9139f0 = f7;
                        }
                        if (i14 == i12 - 1) {
                            iVar4.g(iVar4.L, this.L, this.C0);
                        }
                        if (i14 > 0 && iVar3 != null) {
                            int i15 = this.U0;
                            f fVar2 = iVar3.L;
                            iVar4.g(fVar, fVar2, i15);
                            iVar3.g(fVar2, fVar, 0);
                        }
                        iVar3 = iVar4;
                    }
                    i14++;
                    f8 = f7;
                }
                for (int i16 = 0; i16 < i13; i16++) {
                    i iVar5 = this.f9199c1[i16];
                    if (iVar5 != null && iVar5.f9145i0 != 8) {
                        f fVar3 = iVar5.K;
                        if (i16 == 0) {
                            iVar5.g(fVar3, this.K, this.f9213x0);
                            iVar5.f9155n0 = this.J0;
                            iVar5.f9141g0 = this.P0;
                        }
                        if (i16 == i13 - 1) {
                            iVar5.g(iVar5.M, this.M, this.f9214y0);
                        }
                        if (i16 > 0 && iVar3 != null) {
                            int i17 = this.V0;
                            f fVar4 = iVar3.M;
                            iVar5.g(fVar3, fVar4, i17);
                            iVar3.g(fVar4, fVar3, 0);
                        }
                        iVar3 = iVar5;
                    }
                }
                for (int i18 = 0; i18 < i12; i18++) {
                    for (int i19 = 0; i19 < i13; i19++) {
                        int i20 = (i19 * i12) + i18;
                        if (this.f9197a1 == 1) {
                            i20 = (i18 * i13) + i19;
                        }
                        i[] iVarArr = this.f9202f1;
                        if (i20 < iVarArr.length && (iVar = iVarArr[i20]) != null && iVar.f9145i0 != 8) {
                            i iVar6 = this.f9200d1[i18];
                            i iVar7 = this.f9199c1[i19];
                            if (iVar != iVar6) {
                                iVar.g(iVar.J, iVar6.J, 0);
                                iVar.g(iVar.L, iVar6.L, 0);
                            }
                            if (iVar != iVar7) {
                                iVar.g(iVar.K, iVar7.K, 0);
                                iVar.g(iVar.M, iVar7.M, 0);
                            }
                        }
                    }
                }
            }
        } else if (arrayList.size() > 0) {
            ((k) arrayList.get(0)).b(0, z7, true);
        }
        this.D0 = false;
    }

    @Override // u.p, u.i
    public final void h(i iVar, HashMap hashMap) {
        super.h(iVar, hashMap);
        l lVar = (l) iVar;
        this.I0 = lVar.I0;
        this.J0 = lVar.J0;
        this.K0 = lVar.K0;
        this.L0 = lVar.L0;
        this.M0 = lVar.M0;
        this.N0 = lVar.N0;
        this.O0 = lVar.O0;
        this.P0 = lVar.P0;
        this.Q0 = lVar.Q0;
        this.R0 = lVar.R0;
        this.S0 = lVar.S0;
        this.T0 = lVar.T0;
        this.U0 = lVar.U0;
        this.V0 = lVar.V0;
        this.W0 = lVar.W0;
        this.X0 = lVar.X0;
        this.Y0 = lVar.Y0;
        this.Z0 = lVar.Z0;
        this.f9197a1 = lVar.f9197a1;
    }
}
