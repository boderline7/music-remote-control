package s;

import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d {
    public static final boolean DEBUG = false;
    public static final boolean FULL_DEBUG = false;
    public static final boolean MEASURE = false;

    /* renamed from: p, reason: collision with root package name */
    public static boolean f8610p = false;

    /* renamed from: q, reason: collision with root package name */
    public static int f8611q = 1000;

    /* renamed from: c, reason: collision with root package name */
    public final g f8614c;

    /* renamed from: f, reason: collision with root package name */
    public c[] f8617f;

    /* renamed from: l, reason: collision with root package name */
    public final j.h f8623l;

    /* renamed from: o, reason: collision with root package name */
    public c f8626o;

    /* renamed from: a, reason: collision with root package name */
    public boolean f8612a = false;

    /* renamed from: b, reason: collision with root package name */
    public int f8613b = 0;

    /* renamed from: d, reason: collision with root package name */
    public int f8615d = 32;

    /* renamed from: e, reason: collision with root package name */
    public int f8616e = 32;

    /* renamed from: g, reason: collision with root package name */
    public boolean f8618g = false;

    /* renamed from: h, reason: collision with root package name */
    public boolean[] f8619h = new boolean[32];

    /* renamed from: i, reason: collision with root package name */
    public int f8620i = 1;

    /* renamed from: j, reason: collision with root package name */
    public int f8621j = 0;

    /* renamed from: k, reason: collision with root package name */
    public int f8622k = 32;

    /* renamed from: m, reason: collision with root package name */
    public i[] f8624m = new i[f8611q];

    /* renamed from: n, reason: collision with root package name */
    public int f8625n = 0;

    public d() {
        this.f8617f = null;
        this.f8617f = new c[32];
        s();
        j.h hVar = new j.h(1);
        this.f8623l = hVar;
        g gVar = new g(hVar);
        gVar.f8632f = new i[128];
        gVar.f8633g = new i[128];
        gVar.f8634h = 0;
        gVar.f8635i = new androidx.appcompat.app.g(gVar, gVar);
        this.f8614c = gVar;
        this.f8626o = new c(hVar);
    }

    public static int n(u.f fVar) {
        i iVar = fVar.f9124i;
        if (iVar != null) {
            return (int) (iVar.f8641e + 0.5f);
        }
        return 0;
    }

    public final i a(h hVar) {
        i iVar = (i) ((e) this.f8623l.f6892c).a();
        if (iVar == null) {
            iVar = new i(hVar);
            iVar.f8645i = hVar;
        } else {
            iVar.c();
            iVar.f8645i = hVar;
        }
        int i7 = this.f8625n;
        int i8 = f8611q;
        if (i7 >= i8) {
            int i9 = i8 * 2;
            f8611q = i9;
            this.f8624m = (i[]) Arrays.copyOf(this.f8624m, i9);
        }
        i[] iVarArr = this.f8624m;
        int i10 = this.f8625n;
        this.f8625n = i10 + 1;
        iVarArr[i10] = iVar;
        return iVar;
    }

    public final void b(i iVar, i iVar2, int i7, float f7, i iVar3, i iVar4, int i8, int i9) {
        c l7 = l();
        if (iVar2 == iVar3) {
            l7.f8608d.i(iVar, 1.0f);
            l7.f8608d.i(iVar4, 1.0f);
            l7.f8608d.i(iVar2, -2.0f);
        } else if (f7 == 0.5f) {
            l7.f8608d.i(iVar, 1.0f);
            l7.f8608d.i(iVar2, -1.0f);
            l7.f8608d.i(iVar3, -1.0f);
            l7.f8608d.i(iVar4, 1.0f);
            if (i7 > 0 || i8 > 0) {
                l7.f8606b = (-i7) + i8;
            }
        } else if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            l7.f8608d.i(iVar, -1.0f);
            l7.f8608d.i(iVar2, 1.0f);
            l7.f8606b = i7;
        } else if (f7 >= 1.0f) {
            l7.f8608d.i(iVar4, -1.0f);
            l7.f8608d.i(iVar3, 1.0f);
            l7.f8606b = -i8;
        } else {
            float f8 = 1.0f - f7;
            l7.f8608d.i(iVar, f8 * 1.0f);
            l7.f8608d.i(iVar2, f8 * (-1.0f));
            l7.f8608d.i(iVar3, (-1.0f) * f7);
            l7.f8608d.i(iVar4, 1.0f * f7);
            if (i7 > 0 || i8 > 0) {
                l7.f8606b = (i8 * f7) + ((-i7) * f8);
            }
        }
        if (i9 != 8) {
            l7.a(this, i9);
        }
        c(l7);
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ba, code lost:
    
        if (r5.f8648l <= 1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00bd, code lost:
    
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00c7, code lost:
    
        if (r5.f8648l <= 1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00dc, code lost:
    
        if (r5.f8648l <= 1) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00df, code lost:
    
        r14 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00e9, code lost:
    
        if (r5.f8648l <= 1) goto L82;
     */
    /* JADX WARN: Removed duplicated region for block: B:129:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01ac A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(s.c r17) {
        /*
            Method dump skipped, instructions count: 433
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: s.d.c(s.c):void");
    }

    public final void d(i iVar, int i7) {
        int i8 = iVar.f8639c;
        if (i8 == -1) {
            iVar.d(this, i7);
            for (int i9 = 0; i9 < this.f8613b + 1; i9++) {
                i iVar2 = ((i[]) this.f8623l.f6893d)[i9];
            }
            return;
        }
        if (i8 == -1) {
            c l7 = l();
            l7.f8605a = iVar;
            float f7 = i7;
            iVar.f8641e = f7;
            l7.f8606b = f7;
            l7.f8609e = true;
            c(l7);
            return;
        }
        c cVar = this.f8617f[i8];
        if (cVar.f8609e) {
            cVar.f8606b = i7;
            return;
        }
        if (cVar.f8608d.k() == 0) {
            cVar.f8609e = true;
            cVar.f8606b = i7;
            return;
        }
        c l8 = l();
        if (i7 < 0) {
            l8.f8606b = i7 * (-1);
            l8.f8608d.i(iVar, 1.0f);
        } else {
            l8.f8606b = i7;
            l8.f8608d.i(iVar, -1.0f);
        }
        c(l8);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e(s.i r6, s.i r7, int r8, int r9) {
        /*
            r5 = this;
            r0 = 8
            if (r9 != r0) goto L15
            boolean r1 = r7.f8642f
            if (r1 == 0) goto L15
            int r1 = r6.f8639c
            r2 = -1
            if (r1 != r2) goto L15
            float r7 = r7.f8641e
            float r8 = (float) r8
            float r7 = r7 + r8
            r6.d(r5, r7)
            return
        L15:
            s.c r1 = r5.l()
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r8 == 0) goto L37
            if (r8 >= 0) goto L25
            int r8 = r8 * (-1)
            r4 = 1
            goto L26
        L25:
            r4 = 0
        L26:
            float r8 = (float) r8
            r1.f8606b = r8
            if (r4 != 0) goto L2c
            goto L37
        L2c:
            s.b r8 = r1.f8608d
            r8.i(r6, r2)
            s.b r6 = r1.f8608d
            r6.i(r7, r3)
            goto L41
        L37:
            s.b r8 = r1.f8608d
            r8.i(r6, r3)
            s.b r6 = r1.f8608d
            r6.i(r7, r2)
        L41:
            if (r9 == r0) goto L46
            r1.a(r5, r9)
        L46:
            r5.c(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: s.d.e(s.i, s.i, int, int):void");
    }

    public final void f(i iVar, i iVar2, int i7, int i8) {
        c l7 = l();
        i m6 = m();
        m6.f8640d = 0;
        l7.b(iVar, iVar2, m6, i7);
        if (i8 != 8) {
            l7.f8608d.i(j(i8), (int) (l7.f8608d.e(m6) * (-1.0f)));
        }
        c(l7);
    }

    public final void g(i iVar, i iVar2, int i7, int i8) {
        c l7 = l();
        i m6 = m();
        m6.f8640d = 0;
        l7.c(iVar, iVar2, m6, i7);
        if (i8 != 8) {
            l7.f8608d.i(j(i8), (int) (l7.f8608d.e(m6) * (-1.0f)));
        }
        c(l7);
    }

    public final void h(c cVar) {
        int i7;
        if (cVar.f8609e) {
            cVar.f8605a.d(this, cVar.f8606b);
        } else {
            c[] cVarArr = this.f8617f;
            int i8 = this.f8621j;
            cVarArr[i8] = cVar;
            i iVar = cVar.f8605a;
            iVar.f8639c = i8;
            this.f8621j = i8 + 1;
            iVar.e(this, cVar);
        }
        if (this.f8612a) {
            int i9 = 0;
            while (i9 < this.f8621j) {
                if (this.f8617f[i9] == null) {
                    System.out.println("WTF");
                }
                c cVar2 = this.f8617f[i9];
                if (cVar2 != null && cVar2.f8609e) {
                    cVar2.f8605a.d(this, cVar2.f8606b);
                    ((e) this.f8623l.f6891b).b(cVar2);
                    this.f8617f[i9] = null;
                    int i10 = i9 + 1;
                    int i11 = i10;
                    while (true) {
                        i7 = this.f8621j;
                        if (i10 >= i7) {
                            break;
                        }
                        c[] cVarArr2 = this.f8617f;
                        int i12 = i10 - 1;
                        c cVar3 = cVarArr2[i10];
                        cVarArr2[i12] = cVar3;
                        i iVar2 = cVar3.f8605a;
                        if (iVar2.f8639c == i10) {
                            iVar2.f8639c = i12;
                        }
                        i11 = i10;
                        i10++;
                    }
                    if (i11 < i7) {
                        this.f8617f[i11] = null;
                    }
                    this.f8621j = i7 - 1;
                    i9--;
                }
                i9++;
            }
            this.f8612a = false;
        }
    }

    public final void i() {
        for (int i7 = 0; i7 < this.f8621j; i7++) {
            c cVar = this.f8617f[i7];
            cVar.f8605a.f8641e = cVar.f8606b;
        }
    }

    public final i j(int i7) {
        if (this.f8620i + 1 >= this.f8616e) {
            o();
        }
        i a7 = a(h.ERROR);
        int i8 = this.f8613b + 1;
        this.f8613b = i8;
        this.f8620i++;
        a7.f8638b = i8;
        a7.f8640d = i7;
        ((i[]) this.f8623l.f6893d)[i8] = a7;
        g gVar = this.f8614c;
        gVar.f8635i.f714b = a7;
        float[] fArr = a7.f8644h;
        Arrays.fill(fArr, CropImageView.DEFAULT_ASPECT_RATIO);
        fArr[a7.f8640d] = 1.0f;
        gVar.j(a7);
        return a7;
    }

    public final i k(Object obj) {
        i iVar = null;
        if (obj == null) {
            return null;
        }
        if (this.f8620i + 1 >= this.f8616e) {
            o();
        }
        if (obj instanceof u.f) {
            u.f fVar = (u.f) obj;
            iVar = fVar.f9124i;
            if (iVar == null) {
                fVar.k();
                iVar = fVar.f9124i;
            }
            int i7 = iVar.f8638b;
            j.h hVar = this.f8623l;
            if (i7 == -1 || i7 > this.f8613b || ((i[]) hVar.f6893d)[i7] == null) {
                if (i7 != -1) {
                    iVar.c();
                }
                int i8 = this.f8613b + 1;
                this.f8613b = i8;
                this.f8620i++;
                iVar.f8638b = i8;
                iVar.f8645i = h.UNRESTRICTED;
                ((i[]) hVar.f6893d)[i8] = iVar;
            }
        }
        return iVar;
    }

    public final c l() {
        j.h hVar = this.f8623l;
        c cVar = (c) ((e) hVar.f6891b).a();
        if (cVar == null) {
            return new c(hVar);
        }
        cVar.f8605a = null;
        cVar.f8608d.clear();
        cVar.f8606b = CropImageView.DEFAULT_ASPECT_RATIO;
        cVar.f8609e = false;
        return cVar;
    }

    public final i m() {
        if (this.f8620i + 1 >= this.f8616e) {
            o();
        }
        i a7 = a(h.SLACK);
        int i7 = this.f8613b + 1;
        this.f8613b = i7;
        this.f8620i++;
        a7.f8638b = i7;
        ((i[]) this.f8623l.f6893d)[i7] = a7;
        return a7;
    }

    public final void o() {
        int i7 = this.f8615d * 2;
        this.f8615d = i7;
        this.f8617f = (c[]) Arrays.copyOf(this.f8617f, i7);
        j.h hVar = this.f8623l;
        hVar.f6893d = (i[]) Arrays.copyOf((i[]) hVar.f6893d, this.f8615d);
        int i8 = this.f8615d;
        this.f8619h = new boolean[i8];
        this.f8616e = i8;
        this.f8622k = i8;
    }

    public final void p() {
        g gVar = this.f8614c;
        if (gVar.e()) {
            i();
            return;
        }
        if (!this.f8618g) {
            q(gVar);
            return;
        }
        for (int i7 = 0; i7 < this.f8621j; i7++) {
            if (!this.f8617f[i7].f8609e) {
                q(gVar);
                return;
            }
        }
        i();
    }

    public final void q(g gVar) {
        int i7 = 0;
        while (true) {
            if (i7 >= this.f8621j) {
                break;
            }
            c cVar = this.f8617f[i7];
            if (cVar.f8605a.f8645i != h.UNRESTRICTED) {
                float f7 = cVar.f8606b;
                float f8 = CropImageView.DEFAULT_ASPECT_RATIO;
                if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
                    boolean z6 = false;
                    int i8 = 0;
                    while (!z6) {
                        i8++;
                        float f9 = Float.MAX_VALUE;
                        int i9 = -1;
                        int i10 = -1;
                        int i11 = 0;
                        int i12 = 0;
                        while (i11 < this.f8621j) {
                            c cVar2 = this.f8617f[i11];
                            if (cVar2.f8605a.f8645i != h.UNRESTRICTED && !cVar2.f8609e && cVar2.f8606b < f8) {
                                int k7 = cVar2.f8608d.k();
                                int i13 = 0;
                                while (i13 < k7) {
                                    i d7 = cVar2.f8608d.d(i13);
                                    float e7 = cVar2.f8608d.e(d7);
                                    if (e7 > f8) {
                                        for (int i14 = 0; i14 < 9; i14++) {
                                            float f10 = d7.f8643g[i14] / e7;
                                            if ((f10 < f9 && i14 == i12) || i14 > i12) {
                                                i12 = i14;
                                                i10 = d7.f8638b;
                                                i9 = i11;
                                                f9 = f10;
                                            }
                                        }
                                    }
                                    i13++;
                                    f8 = CropImageView.DEFAULT_ASPECT_RATIO;
                                }
                            }
                            i11++;
                            f8 = CropImageView.DEFAULT_ASPECT_RATIO;
                        }
                        if (i9 != -1) {
                            c cVar3 = this.f8617f[i9];
                            cVar3.f8605a.f8639c = -1;
                            cVar3.g(((i[]) this.f8623l.f6893d)[i10]);
                            i iVar = cVar3.f8605a;
                            iVar.f8639c = i9;
                            iVar.e(this, cVar3);
                        } else {
                            z6 = true;
                        }
                        if (i8 > this.f8620i / 2) {
                            z6 = true;
                        }
                        f8 = CropImageView.DEFAULT_ASPECT_RATIO;
                    }
                }
            }
            i7++;
        }
        r(gVar);
        i();
    }

    public final void r(c cVar) {
        for (int i7 = 0; i7 < this.f8620i; i7++) {
            this.f8619h[i7] = false;
        }
        boolean z6 = false;
        int i8 = 0;
        while (!z6) {
            i8++;
            if (i8 >= this.f8620i * 2) {
                return;
            }
            i iVar = cVar.f8605a;
            if (iVar != null) {
                this.f8619h[iVar.f8638b] = true;
            }
            i d7 = cVar.d(this.f8619h);
            if (d7 != null) {
                boolean[] zArr = this.f8619h;
                int i9 = d7.f8638b;
                if (zArr[i9]) {
                    return;
                } else {
                    zArr[i9] = true;
                }
            }
            if (d7 != null) {
                float f7 = Float.MAX_VALUE;
                int i10 = -1;
                for (int i11 = 0; i11 < this.f8621j; i11++) {
                    c cVar2 = this.f8617f[i11];
                    if (cVar2.f8605a.f8645i != h.UNRESTRICTED && !cVar2.f8609e && cVar2.f8608d.f(d7)) {
                        float e7 = cVar2.f8608d.e(d7);
                        if (e7 < CropImageView.DEFAULT_ASPECT_RATIO) {
                            float f8 = (-cVar2.f8606b) / e7;
                            if (f8 < f7) {
                                i10 = i11;
                                f7 = f8;
                            }
                        }
                    }
                }
                if (i10 > -1) {
                    c cVar3 = this.f8617f[i10];
                    cVar3.f8605a.f8639c = -1;
                    cVar3.g(d7);
                    i iVar2 = cVar3.f8605a;
                    iVar2.f8639c = i10;
                    iVar2.e(this, cVar3);
                }
            } else {
                z6 = true;
            }
        }
    }

    public final void s() {
        for (int i7 = 0; i7 < this.f8621j; i7++) {
            c cVar = this.f8617f[i7];
            if (cVar != null) {
                ((e) this.f8623l.f6891b).b(cVar);
            }
            this.f8617f[i7] = null;
        }
    }

    public final void t() {
        j.h hVar;
        int i7 = 0;
        while (true) {
            hVar = this.f8623l;
            i[] iVarArr = (i[]) hVar.f6893d;
            if (i7 >= iVarArr.length) {
                break;
            }
            i iVar = iVarArr[i7];
            if (iVar != null) {
                iVar.c();
            }
            i7++;
        }
        e eVar = (e) hVar.f6892c;
        i[] iVarArr2 = this.f8624m;
        int i8 = this.f8625n;
        eVar.getClass();
        if (i8 > iVarArr2.length) {
            i8 = iVarArr2.length;
        }
        for (int i9 = 0; i9 < i8; i9++) {
            i iVar2 = iVarArr2[i9];
            int i10 = eVar.f8629c;
            Object[] objArr = eVar.f8628b;
            if (i10 < objArr.length) {
                objArr[i10] = iVar2;
                eVar.f8629c = i10 + 1;
            }
        }
        this.f8625n = 0;
        Arrays.fill((i[]) hVar.f6893d, (Object) null);
        this.f8613b = 0;
        g gVar = this.f8614c;
        gVar.f8634h = 0;
        gVar.f8606b = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f8620i = 1;
        for (int i11 = 0; i11 < this.f8621j; i11++) {
            c cVar = this.f8617f[i11];
        }
        s();
        this.f8621j = 0;
        this.f8626o = new c(hVar);
    }
}
