package a6;

import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: b, reason: collision with root package name */
    public final g6.h f400b;

    /* renamed from: d, reason: collision with root package name */
    public boolean f402d;

    /* renamed from: h, reason: collision with root package name */
    public int f406h;

    /* renamed from: i, reason: collision with root package name */
    public int f407i;

    /* renamed from: a, reason: collision with root package name */
    public final boolean f399a = true;

    /* renamed from: c, reason: collision with root package name */
    public int f401c = androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;

    /* renamed from: e, reason: collision with root package name */
    public int f403e = 4096;

    /* renamed from: f, reason: collision with root package name */
    public d[] f404f = new d[8];

    /* renamed from: g, reason: collision with root package name */
    public int f405g = 7;

    public f(g6.h hVar) {
        this.f400b = hVar;
    }

    public final void a(int i7) {
        int i8;
        if (i7 > 0) {
            int length = this.f404f.length - 1;
            int i9 = 0;
            while (true) {
                i8 = this.f405g;
                if (length < i8 || i7 <= 0) {
                    break;
                }
                d dVar = this.f404f[length];
                i5.a.d(dVar);
                i7 -= dVar.f380c;
                int i10 = this.f407i;
                d dVar2 = this.f404f[length];
                i5.a.d(dVar2);
                this.f407i = i10 - dVar2.f380c;
                this.f406h--;
                i9++;
                length--;
            }
            d[] dVarArr = this.f404f;
            int i11 = i8 + 1;
            System.arraycopy(dVarArr, i11, dVarArr, i11 + i9, this.f406h);
            d[] dVarArr2 = this.f404f;
            int i12 = this.f405g + 1;
            Arrays.fill(dVarArr2, i12, i12 + i9, (Object) null);
            this.f405g += i9;
        }
    }

    public final void b(d dVar) {
        int i7 = this.f403e;
        int i8 = dVar.f380c;
        if (i8 > i7) {
            d[] dVarArr = this.f404f;
            c5.l.C1(dVarArr, null, 0, dVarArr.length);
            this.f405g = this.f404f.length - 1;
            this.f406h = 0;
            this.f407i = 0;
            return;
        }
        a((this.f407i + i8) - i7);
        int i9 = this.f406h + 1;
        d[] dVarArr2 = this.f404f;
        if (i9 > dVarArr2.length) {
            d[] dVarArr3 = new d[dVarArr2.length * 2];
            System.arraycopy(dVarArr2, 0, dVarArr3, dVarArr2.length, dVarArr2.length);
            this.f405g = this.f404f.length - 1;
            this.f404f = dVarArr3;
        }
        int i10 = this.f405g;
        this.f405g = i10 - 1;
        this.f404f[i10] = dVar;
        this.f406h++;
        this.f407i += i8;
    }

    public final void c(g6.l lVar) {
        i5.a.g("data", lVar);
        boolean z6 = this.f399a;
        g6.h hVar = this.f400b;
        if (z6) {
            j0.INSTANCE.getClass();
            int c7 = lVar.c();
            long j7 = 0;
            for (int i7 = 0; i7 < c7; i7++) {
                byte f7 = lVar.f(i7);
                byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
                j7 += j0.f445b[f7 & 255];
            }
            if (((int) ((j7 + 7) >> 3)) < lVar.c()) {
                g6.h hVar2 = new g6.h();
                j0.INSTANCE.getClass();
                int c8 = lVar.c();
                long j8 = 0;
                int i8 = 0;
                for (int i9 = 0; i9 < c8; i9++) {
                    byte f8 = lVar.f(i9);
                    byte[] bArr2 = t5.b.EMPTY_BYTE_ARRAY;
                    int i10 = f8 & 255;
                    int i11 = j0.f444a[i10];
                    byte b7 = j0.f445b[i10];
                    j8 = (j8 << b7) | i11;
                    i8 += b7;
                    while (i8 >= 8) {
                        i8 -= 8;
                        hVar2.N((int) (j8 >> i8));
                    }
                }
                if (i8 > 0) {
                    hVar2.N((int) ((255 >>> i8) | (j8 << (8 - i8))));
                }
                g6.l f9 = hVar2.f(hVar2.f6573b);
                e(f9.c(), z1.c.NEG_FLOAT, 128);
                hVar.K(f9);
                return;
            }
        }
        e(lVar.c(), z1.c.NEG_FLOAT, 0);
        hVar.K(lVar);
    }

    public final void d(ArrayList arrayList) {
        int i7;
        int i8;
        if (this.f402d) {
            int i9 = this.f401c;
            if (i9 < this.f403e) {
                e(i9, 31, 32);
            }
            this.f402d = false;
            this.f401c = androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
            e(this.f403e, 31, 32);
        }
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            d dVar = (d) arrayList.get(i10);
            g6.l i11 = dVar.f378a.i();
            g.INSTANCE.getClass();
            Integer num = (Integer) g.f410b.get(i11);
            g6.l lVar = dVar.f379b;
            if (num != null) {
                int intValue = num.intValue();
                i8 = intValue + 1;
                if (2 <= i8 && i8 < 8) {
                    d[] dVarArr = g.f409a;
                    if (i5.a.c(dVarArr[intValue].f379b, lVar)) {
                        i7 = i8;
                    } else if (i5.a.c(dVarArr[i8].f379b, lVar)) {
                        i8 = intValue + 2;
                        i7 = i8;
                    }
                }
                i7 = i8;
                i8 = -1;
            } else {
                i7 = -1;
                i8 = -1;
            }
            if (i8 == -1) {
                int i12 = this.f405g + 1;
                int length = this.f404f.length;
                while (true) {
                    if (i12 >= length) {
                        break;
                    }
                    d dVar2 = this.f404f[i12];
                    i5.a.d(dVar2);
                    if (i5.a.c(dVar2.f378a, i11)) {
                        d dVar3 = this.f404f[i12];
                        i5.a.d(dVar3);
                        if (i5.a.c(dVar3.f379b, lVar)) {
                            int i13 = i12 - this.f405g;
                            g.INSTANCE.getClass();
                            i8 = g.f409a.length + i13;
                            break;
                        } else if (i7 == -1) {
                            int i14 = i12 - this.f405g;
                            g.INSTANCE.getClass();
                            i7 = i14 + g.f409a.length;
                        }
                    }
                    i12++;
                }
            }
            if (i8 != -1) {
                e(i8, z1.c.NEG_FLOAT, 128);
            } else if (i7 == -1) {
                this.f400b.N(64);
                c(i11);
                c(lVar);
                b(dVar);
            } else {
                g6.l lVar2 = d.PSEUDO_PREFIX;
                i11.getClass();
                i5.a.g("prefix", lVar2);
                if (!i11.h(lVar2, lVar2.c()) || i5.a.c(d.TARGET_AUTHORITY, i11)) {
                    e(i7, 63, 64);
                    c(lVar);
                    b(dVar);
                } else {
                    e(i7, 15, 0);
                    c(lVar);
                }
            }
        }
    }

    public final void e(int i7, int i8, int i9) {
        g6.h hVar = this.f400b;
        if (i7 < i8) {
            hVar.N(i7 | i9);
            return;
        }
        hVar.N(i9 | i8);
        int i10 = i7 - i8;
        while (i10 >= 128) {
            hVar.N(128 | (i10 & z1.c.NEG_FLOAT));
            i10 >>>= 7;
        }
        hVar.N(i10);
    }
}
