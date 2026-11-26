package v;

import com.yalantis.ucrop.view.CropImageView;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class j {

    /* renamed from: a, reason: collision with root package name */
    public static final b f9352a = new b();

    public static boolean a(u.i iVar) {
        u.h hVar;
        u.h hVar2;
        u.h[] hVarArr = iVar.U;
        u.h hVar3 = hVarArr[0];
        u.h hVar4 = hVarArr[1];
        u.i iVar2 = iVar.V;
        u.j jVar = iVar2 != null ? (u.j) iVar2 : null;
        if (jVar != null) {
            u.h hVar5 = jVar.U[0];
            u.h hVar6 = u.h.FIXED;
        }
        if (jVar != null) {
            u.h hVar7 = jVar.U[1];
            u.h hVar8 = u.h.FIXED;
        }
        u.h hVar9 = u.h.FIXED;
        boolean z6 = hVar3 == hVar9 || iVar.C() || hVar3 == u.h.WRAP_CONTENT || (hVar3 == (hVar2 = u.h.MATCH_CONSTRAINT) && iVar.f9162r == 0 && iVar.Y == CropImageView.DEFAULT_ASPECT_RATIO && iVar.v(0)) || (hVar3 == hVar2 && iVar.f9162r == 1 && iVar.w(0, iVar.s()));
        boolean z7 = hVar4 == hVar9 || iVar.D() || hVar4 == u.h.WRAP_CONTENT || (hVar4 == (hVar = u.h.MATCH_CONSTRAINT) && iVar.f9164s == 0 && iVar.Y == CropImageView.DEFAULT_ASPECT_RATIO && iVar.v(1)) || (hVar4 == hVar && iVar.f9164s == 1 && iVar.w(1, iVar.m()));
        if (iVar.Y <= CropImageView.DEFAULT_ASPECT_RATIO || !(z6 || z7)) {
            return z6 && z7;
        }
        return true;
    }

    public static void b(int i7, u.i iVar, r rVar, boolean z6) {
        u.f fVar;
        u.f fVar2;
        u.f fVar3;
        u.f fVar4;
        if (iVar.f9152m) {
            return;
        }
        if (!(iVar instanceof u.j) && iVar.B() && a(iVar)) {
            u.j.X(iVar, rVar, new b());
        }
        u.f k7 = iVar.k(u.e.LEFT);
        u.f k8 = iVar.k(u.e.RIGHT);
        int d7 = k7.d();
        int d8 = k8.d();
        HashSet hashSet = k7.f9116a;
        char c7 = 0;
        if (hashSet != null && k7.f9118c) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                u.f fVar5 = (u.f) it.next();
                u.i iVar2 = fVar5.f9119d;
                int i8 = i7 + 1;
                boolean a7 = a(iVar2);
                if (iVar2.B() && a7) {
                    u.j.X(iVar2, rVar, new b());
                }
                u.f fVar6 = iVar2.J;
                u.f fVar7 = iVar2.L;
                char c8 = ((fVar5 == fVar6 && (fVar4 = fVar7.f9121f) != null && fVar4.f9118c) || (fVar5 == fVar7 && (fVar3 = fVar6.f9121f) != null && fVar3.f9118c)) ? (char) 1 : c7;
                u.h hVar = iVar2.U[c7];
                u.h hVar2 = u.h.MATCH_CONSTRAINT;
                if (hVar != hVar2 || a7) {
                    if (!iVar2.B()) {
                        if (fVar5 == fVar6 && fVar7.f9121f == null) {
                            int e7 = fVar6.e() + d7;
                            iVar2.L(e7, iVar2.s() + e7);
                            b(i8, iVar2, rVar, z6);
                        } else if (fVar5 == fVar7 && fVar6.f9121f == null) {
                            int e8 = d7 - fVar7.e();
                            iVar2.L(e8 - iVar2.s(), e8);
                            b(i8, iVar2, rVar, z6);
                        } else if (c8 != 0 && !iVar2.z()) {
                            c(i8, iVar2, rVar, z6);
                        }
                    }
                } else if (hVar == hVar2 && iVar2.f9170v >= 0 && iVar2.f9168u >= 0 && ((iVar2.f9145i0 == 8 || (iVar2.f9162r == 0 && iVar2.Y == CropImageView.DEFAULT_ASPECT_RATIO)) && !iVar2.z() && !iVar2.G && c8 != 0 && !iVar2.z())) {
                    d(i8, iVar, rVar, iVar2, z6);
                }
                c7 = 0;
            }
        }
        if (iVar instanceof u.n) {
            return;
        }
        HashSet hashSet2 = k8.f9116a;
        if (hashSet2 != null && k8.f9118c) {
            Iterator it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                u.f fVar8 = (u.f) it2.next();
                u.i iVar3 = fVar8.f9119d;
                int i9 = i7 + 1;
                boolean a8 = a(iVar3);
                if (iVar3.B() && a8) {
                    u.j.X(iVar3, rVar, new b());
                }
                u.f fVar9 = iVar3.J;
                u.f fVar10 = iVar3.L;
                boolean z7 = (fVar8 == fVar9 && (fVar2 = fVar10.f9121f) != null && fVar2.f9118c) || (fVar8 == fVar10 && (fVar = fVar9.f9121f) != null && fVar.f9118c);
                u.h hVar3 = iVar3.U[0];
                u.h hVar4 = u.h.MATCH_CONSTRAINT;
                if (hVar3 != hVar4 || a8) {
                    if (!iVar3.B()) {
                        if (fVar8 == fVar9 && fVar10.f9121f == null) {
                            int e9 = fVar9.e() + d8;
                            iVar3.L(e9, iVar3.s() + e9);
                            b(i9, iVar3, rVar, z6);
                        } else if (fVar8 == fVar10 && fVar9.f9121f == null) {
                            int e10 = d8 - fVar10.e();
                            iVar3.L(e10 - iVar3.s(), e10);
                            b(i9, iVar3, rVar, z6);
                        } else if (z7 && !iVar3.z()) {
                            c(i9, iVar3, rVar, z6);
                        }
                    }
                } else if (hVar3 == hVar4 && iVar3.f9170v >= 0 && iVar3.f9168u >= 0) {
                    if (iVar3.f9145i0 != 8) {
                        if (iVar3.f9162r == 0) {
                            if (iVar3.Y == CropImageView.DEFAULT_ASPECT_RATIO) {
                            }
                        }
                    }
                    if (!iVar3.z() && !iVar3.G && z7 && !iVar3.z()) {
                        d(i9, iVar, rVar, iVar3, z6);
                    }
                }
            }
        }
        iVar.f9152m = true;
    }

    public static void c(int i7, u.i iVar, r rVar, boolean z6) {
        float f7 = iVar.f9139f0;
        u.f fVar = iVar.J;
        int d7 = fVar.f9121f.d();
        u.f fVar2 = iVar.L;
        int d8 = fVar2.f9121f.d();
        int e7 = fVar.e() + d7;
        int e8 = d8 - fVar2.e();
        if (d7 == d8) {
            f7 = 0.5f;
        } else {
            d7 = e7;
            d8 = e8;
        }
        int s6 = iVar.s();
        int i8 = (d8 - d7) - s6;
        if (d7 > d8) {
            i8 = (d7 - d8) - s6;
        }
        int i9 = ((int) (i8 > 0 ? (f7 * i8) + 0.5f : f7 * i8)) + d7;
        int i10 = i9 + s6;
        if (d7 > d8) {
            i10 = i9 - s6;
        }
        iVar.L(i9, i10);
        b(i7 + 1, iVar, rVar, z6);
    }

    public static void d(int i7, u.i iVar, r rVar, u.i iVar2, boolean z6) {
        float f7 = iVar2.f9139f0;
        u.f fVar = iVar2.J;
        int e7 = fVar.e() + fVar.f9121f.d();
        u.f fVar2 = iVar2.L;
        int d7 = fVar2.f9121f.d() - fVar2.e();
        if (d7 >= e7) {
            int s6 = iVar2.s();
            if (iVar2.f9145i0 != 8) {
                int i8 = iVar2.f9162r;
                if (i8 == 2) {
                    s6 = (int) (iVar2.f9139f0 * 0.5f * (iVar instanceof u.j ? iVar.s() : iVar.V.s()));
                } else if (i8 == 0) {
                    s6 = d7 - e7;
                }
                s6 = Math.max(iVar2.f9168u, s6);
                int i9 = iVar2.f9170v;
                if (i9 > 0) {
                    s6 = Math.min(i9, s6);
                }
            }
            int i10 = e7 + ((int) ((f7 * ((d7 - e7) - s6)) + 0.5f));
            iVar2.L(i10, s6 + i10);
            b(i7 + 1, iVar2, rVar, z6);
        }
    }

    public static void e(int i7, u.i iVar, r rVar) {
        float f7 = iVar.f9141g0;
        u.f fVar = iVar.K;
        int d7 = fVar.f9121f.d();
        u.f fVar2 = iVar.M;
        int d8 = fVar2.f9121f.d();
        int e7 = fVar.e() + d7;
        int e8 = d8 - fVar2.e();
        if (d7 == d8) {
            f7 = 0.5f;
        } else {
            d7 = e7;
            d8 = e8;
        }
        int m6 = iVar.m();
        int i8 = (d8 - d7) - m6;
        if (d7 > d8) {
            i8 = (d7 - d8) - m6;
        }
        int i9 = (int) (i8 > 0 ? (f7 * i8) + 0.5f : f7 * i8);
        int i10 = d7 + i9;
        int i11 = i10 + m6;
        if (d7 > d8) {
            i10 = d7 - i9;
            i11 = i10 - m6;
        }
        iVar.M(i10, i11);
        g(i7 + 1, iVar, rVar);
    }

    public static void f(int i7, u.i iVar, r rVar, u.i iVar2) {
        float f7 = iVar2.f9141g0;
        u.f fVar = iVar2.K;
        int e7 = fVar.e() + fVar.f9121f.d();
        u.f fVar2 = iVar2.M;
        int d7 = fVar2.f9121f.d() - fVar2.e();
        if (d7 >= e7) {
            int m6 = iVar2.m();
            if (iVar2.f9145i0 != 8) {
                int i8 = iVar2.f9164s;
                if (i8 == 2) {
                    m6 = (int) (f7 * 0.5f * (iVar instanceof u.j ? iVar.m() : iVar.V.m()));
                } else if (i8 == 0) {
                    m6 = d7 - e7;
                }
                m6 = Math.max(iVar2.f9172x, m6);
                int i9 = iVar2.f9173y;
                if (i9 > 0) {
                    m6 = Math.min(i9, m6);
                }
            }
            int i10 = e7 + ((int) ((f7 * ((d7 - e7) - m6)) + 0.5f));
            iVar2.M(i10, m6 + i10);
            g(i7 + 1, iVar2, rVar);
        }
    }

    public static void g(int i7, u.i iVar, r rVar) {
        u.f fVar;
        u.f fVar2;
        u.f fVar3;
        u.f fVar4;
        if (iVar.f9154n) {
            return;
        }
        if (!(iVar instanceof u.j) && iVar.B() && a(iVar)) {
            u.j.X(iVar, rVar, new b());
        }
        u.f k7 = iVar.k(u.e.TOP);
        u.f k8 = iVar.k(u.e.BOTTOM);
        int d7 = k7.d();
        int d8 = k8.d();
        HashSet hashSet = k7.f9116a;
        char c7 = 1;
        if (hashSet != null && k7.f9118c) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                u.f fVar5 = (u.f) it.next();
                u.i iVar2 = fVar5.f9119d;
                int i8 = i7 + 1;
                boolean a7 = a(iVar2);
                if (iVar2.B() && a7) {
                    u.j.X(iVar2, rVar, new b());
                }
                u.f fVar6 = iVar2.K;
                u.f fVar7 = iVar2.M;
                char c8 = ((fVar5 == fVar6 && (fVar4 = fVar7.f9121f) != null && fVar4.f9118c) || (fVar5 == fVar7 && (fVar3 = fVar6.f9121f) != null && fVar3.f9118c)) ? c7 : (char) 0;
                u.h hVar = iVar2.U[c7];
                u.h hVar2 = u.h.MATCH_CONSTRAINT;
                if (hVar != hVar2 || a7) {
                    if (!iVar2.B()) {
                        if (fVar5 == fVar6 && fVar7.f9121f == null) {
                            int e7 = fVar6.e() + d7;
                            iVar2.M(e7, iVar2.m() + e7);
                            g(i8, iVar2, rVar);
                        } else if (fVar5 == fVar7 && fVar6.f9121f == null) {
                            int e8 = d7 - fVar7.e();
                            iVar2.M(e8 - iVar2.m(), e8);
                            g(i8, iVar2, rVar);
                        } else if (c8 != 0 && !iVar2.A()) {
                            e(i8, iVar2, rVar);
                        }
                    }
                } else if (hVar == hVar2 && iVar2.f9173y >= 0 && iVar2.f9172x >= 0 && ((iVar2.f9145i0 == 8 || (iVar2.f9164s == 0 && iVar2.Y == CropImageView.DEFAULT_ASPECT_RATIO)) && !iVar2.A() && !iVar2.G && c8 != 0 && !iVar2.A())) {
                    f(i8, iVar, rVar, iVar2);
                }
                c7 = 1;
            }
        }
        if (iVar instanceof u.n) {
            return;
        }
        HashSet hashSet2 = k8.f9116a;
        if (hashSet2 != null && k8.f9118c) {
            Iterator it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                u.f fVar8 = (u.f) it2.next();
                u.i iVar3 = fVar8.f9119d;
                int i9 = i7 + 1;
                boolean a8 = a(iVar3);
                if (iVar3.B() && a8) {
                    u.j.X(iVar3, rVar, new b());
                }
                u.f fVar9 = iVar3.K;
                u.f fVar10 = iVar3.M;
                boolean z6 = (fVar8 == fVar9 && (fVar2 = fVar10.f9121f) != null && fVar2.f9118c) || (fVar8 == fVar10 && (fVar = fVar9.f9121f) != null && fVar.f9118c);
                u.h hVar3 = iVar3.U[1];
                u.h hVar4 = u.h.MATCH_CONSTRAINT;
                if (hVar3 != hVar4 || a8) {
                    if (!iVar3.B()) {
                        if (fVar8 == fVar9 && fVar10.f9121f == null) {
                            int e9 = fVar9.e() + d8;
                            iVar3.M(e9, iVar3.m() + e9);
                            g(i9, iVar3, rVar);
                        } else if (fVar8 == fVar10 && fVar9.f9121f == null) {
                            int e10 = d8 - fVar10.e();
                            iVar3.M(e10 - iVar3.m(), e10);
                            g(i9, iVar3, rVar);
                        } else if (z6 && !iVar3.A()) {
                            e(i9, iVar3, rVar);
                        }
                    }
                } else if (hVar3 == hVar4 && iVar3.f9173y >= 0 && iVar3.f9172x >= 0) {
                    if (iVar3.f9145i0 != 8) {
                        if (iVar3.f9164s == 0) {
                            if (iVar3.Y == CropImageView.DEFAULT_ASPECT_RATIO) {
                            }
                        }
                    }
                    if (!iVar3.A() && !iVar3.G && z6 && !iVar3.A()) {
                        f(i9, iVar, rVar, iVar3);
                    }
                }
            }
        }
        u.f k9 = iVar.k(u.e.BASELINE);
        if (k9.f9116a != null && k9.f9118c) {
            int d9 = k9.d();
            Iterator it3 = k9.f9116a.iterator();
            while (it3.hasNext()) {
                u.f fVar11 = (u.f) it3.next();
                u.i iVar4 = fVar11.f9119d;
                int i10 = i7 + 1;
                boolean a9 = a(iVar4);
                if (iVar4.B() && a9) {
                    u.j.X(iVar4, rVar, new b());
                }
                if (iVar4.U[1] != u.h.MATCH_CONSTRAINT || a9) {
                    if (!iVar4.B()) {
                        u.f fVar12 = iVar4.N;
                        if (fVar11 == fVar12) {
                            int e11 = fVar11.e() + d9;
                            if (iVar4.E) {
                                int i11 = e11 - iVar4.f9133c0;
                                int i12 = iVar4.X + i11;
                                iVar4.f9131b0 = i11;
                                iVar4.K.l(i11);
                                iVar4.M.l(i12);
                                fVar12.l(e11);
                                iVar4.f9150l = true;
                            }
                            g(i10, iVar4, rVar);
                        }
                    }
                }
            }
        }
        iVar.f9154n = true;
    }
}
