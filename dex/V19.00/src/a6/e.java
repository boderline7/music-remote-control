package a6;

import java.io.IOException;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: d, reason: collision with root package name */
    public final g6.t f388d;

    /* renamed from: g, reason: collision with root package name */
    public int f391g;

    /* renamed from: h, reason: collision with root package name */
    public int f392h;

    /* renamed from: a, reason: collision with root package name */
    public final int f385a = 4096;

    /* renamed from: b, reason: collision with root package name */
    public int f386b = 4096;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f387c = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    public d[] f389e = new d[8];

    /* renamed from: f, reason: collision with root package name */
    public int f390f = 7;

    public e(a0 a0Var) {
        this.f388d = new g6.t(a0Var);
    }

    public final int a(int i7) {
        int i8;
        int i9 = 0;
        if (i7 > 0) {
            int length = this.f389e.length;
            while (true) {
                length--;
                i8 = this.f390f;
                if (length < i8 || i7 <= 0) {
                    break;
                }
                d dVar = this.f389e[length];
                i5.a.d(dVar);
                int i10 = dVar.f380c;
                i7 -= i10;
                this.f392h -= i10;
                this.f391g--;
                i9++;
            }
            d[] dVarArr = this.f389e;
            System.arraycopy(dVarArr, i8 + 1, dVarArr, i8 + 1 + i9, this.f391g);
            this.f390f += i9;
        }
        return i9;
    }

    public final g6.l b(int i7) {
        if (i7 >= 0) {
            g gVar = g.INSTANCE;
            gVar.getClass();
            d[] dVarArr = g.f409a;
            if (i7 <= dVarArr.length - 1) {
                gVar.getClass();
                return dVarArr[i7].f378a;
            }
        }
        g.INSTANCE.getClass();
        int length = this.f390f + 1 + (i7 - g.f409a.length);
        if (length >= 0) {
            d[] dVarArr2 = this.f389e;
            if (length < dVarArr2.length) {
                d dVar = dVarArr2[length];
                i5.a.d(dVar);
                return dVar.f378a;
            }
        }
        throw new IOException("Header index too large " + (i7 + 1));
    }

    public final void c(d dVar) {
        this.f387c.add(dVar);
        int i7 = this.f386b;
        int i8 = dVar.f380c;
        if (i8 > i7) {
            d[] dVarArr = this.f389e;
            c5.l.C1(dVarArr, null, 0, dVarArr.length);
            this.f390f = this.f389e.length - 1;
            this.f391g = 0;
            this.f392h = 0;
            return;
        }
        a((this.f392h + i8) - i7);
        int i9 = this.f391g + 1;
        d[] dVarArr2 = this.f389e;
        if (i9 > dVarArr2.length) {
            d[] dVarArr3 = new d[dVarArr2.length * 2];
            System.arraycopy(dVarArr2, 0, dVarArr3, dVarArr2.length, dVarArr2.length);
            this.f390f = this.f389e.length - 1;
            this.f389e = dVarArr3;
        }
        int i10 = this.f390f;
        this.f390f = i10 - 1;
        this.f389e[i10] = dVar;
        this.f391g++;
        this.f392h += i8;
    }

    public final g6.l d() {
        g6.t tVar = this.f388d;
        byte y6 = tVar.y();
        byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
        int i7 = y6 & 255;
        int i8 = 0;
        boolean z6 = (y6 & 128) == 128;
        long e7 = e(i7, z1.c.NEG_FLOAT);
        if (!z6) {
            return tVar.f(e7);
        }
        g6.h hVar = new g6.h();
        j0.INSTANCE.getClass();
        i5.a.g("source", tVar);
        a1.b bVar = j0.f446c;
        a1.b bVar2 = bVar;
        int i9 = 0;
        for (long j7 = 0; j7 < e7; j7++) {
            byte y7 = tVar.y();
            byte[] bArr2 = t5.b.EMPTY_BYTE_ARRAY;
            i8 = (i8 << 8) | (y7 & 255);
            i9 += 8;
            while (i9 >= 8) {
                a1.b[] bVarArr = (a1.b[]) bVar2.f200d;
                i5.a.d(bVarArr);
                bVar2 = bVarArr[(i8 >>> (i9 - 8)) & 255];
                i5.a.d(bVar2);
                if (((a1.b[]) bVar2.f200d) == null) {
                    hVar.N(bVar2.f198b);
                    i9 -= bVar2.f199c;
                    bVar2 = bVar;
                } else {
                    i9 -= 8;
                }
            }
        }
        while (i9 > 0) {
            a1.b[] bVarArr2 = (a1.b[]) bVar2.f200d;
            i5.a.d(bVarArr2);
            a1.b bVar3 = bVarArr2[(i8 << (8 - i9)) & 255];
            i5.a.d(bVar3);
            if (((a1.b[]) bVar3.f200d) != null || bVar3.f199c > i9) {
                break;
            }
            hVar.N(bVar3.f198b);
            i9 -= bVar3.f199c;
            bVar2 = bVar;
        }
        return hVar.f(hVar.f6573b);
    }

    public final int e(int i7, int i8) {
        int i9 = i7 & i8;
        if (i9 < i8) {
            return i9;
        }
        int i10 = 0;
        while (true) {
            byte y6 = this.f388d.y();
            byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
            int i11 = y6 & 255;
            if ((y6 & 128) == 0) {
                return i8 + (i11 << i10);
            }
            i8 += (y6 & Byte.MAX_VALUE) << i10;
            i10 += 7;
        }
    }
}
