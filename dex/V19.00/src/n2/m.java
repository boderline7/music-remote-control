package n2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m {
    public static final int FIXED_UNIT_SIZE = 12;
    public static final int N1 = 4;
    public static final int N2 = 4;
    public static final int N3 = 4;
    public static final int N4 = 26;
    public static final int N_INDEXES = 38;
    public static final int UNIT_SIZE = Math.max(d.size, 12);

    /* renamed from: d, reason: collision with root package name */
    public int f7758d;

    /* renamed from: e, reason: collision with root package name */
    public int f7759e;

    /* renamed from: f, reason: collision with root package name */
    public int f7760f;

    /* renamed from: g, reason: collision with root package name */
    public int f7761g;

    /* renamed from: i, reason: collision with root package name */
    public int f7763i;

    /* renamed from: j, reason: collision with root package name */
    public int f7764j;

    /* renamed from: k, reason: collision with root package name */
    public int f7765k;

    /* renamed from: l, reason: collision with root package name */
    public int f7766l;

    /* renamed from: m, reason: collision with root package name */
    public byte[] f7767m;

    /* renamed from: n, reason: collision with root package name */
    public int f7768n;

    /* renamed from: o, reason: collision with root package name */
    public int f7769o;

    /* renamed from: b, reason: collision with root package name */
    public final int[] f7756b = new int[38];

    /* renamed from: c, reason: collision with root package name */
    public final int[] f7757c = new int[128];

    /* renamed from: h, reason: collision with root package name */
    public final i[] f7762h = new i[38];

    /* renamed from: p, reason: collision with root package name */
    public i f7770p = null;

    /* renamed from: q, reason: collision with root package name */
    public h f7771q = null;

    /* renamed from: r, reason: collision with root package name */
    public h f7772r = null;

    /* renamed from: s, reason: collision with root package name */
    public h f7773s = null;

    /* renamed from: a, reason: collision with root package name */
    public int f7755a = 0;

    public final int a() {
        int i7 = this.f7761g;
        if (i7 == this.f7760f) {
            return this.f7762h[0].a() != 0 ? e(0) : c(0);
        }
        int i8 = i7 - UNIT_SIZE;
        this.f7761g = i8;
        return i8;
    }

    public final int b(int i7) {
        int i8 = this.f7757c[i7 - 1];
        if (this.f7762h[i8].a() != 0) {
            return e(i8);
        }
        int i9 = this.f7760f;
        int i10 = this.f7756b[i8];
        int i11 = UNIT_SIZE;
        int i12 = (i11 * i10) + i9;
        this.f7760f = i12;
        if (i12 <= this.f7761g) {
            return i9;
        }
        this.f7760f = i12 - (i11 * i10);
        return c(i8);
    }

    public final int c(int i7) {
        int i8 = this.f7758d;
        int[] iArr = this.f7756b;
        i[] iVarArr = this.f7762h;
        if (i8 == 0) {
            this.f7758d = 255;
            h hVar = this.f7771q;
            int i9 = this.f7769o;
            hVar.f7735b = i9;
            h hVar2 = this.f7772r;
            h hVar3 = this.f7773s;
            int i10 = this.f7760f;
            if (i10 != this.f7761g) {
                this.f7767m[i10] = 0;
            }
            hVar.f(i9);
            hVar.e(hVar.f7735b);
            for (int i11 = 0; i11 < 38; i11++) {
                while (iVarArr[i11].a() != 0) {
                    hVar2.f7735b = e(i11);
                    byte[] bArr = hVar2.f7734a;
                    hVar2.f(hVar.f7735b);
                    int c7 = hVar2.c();
                    hVar2.e(bArr != null ? z.W0(bArr, c7 + 4) : 0);
                    int i12 = hVar2.f7735b;
                    if (bArr != null) {
                        z.r1(bArr, c7 + 4, i12);
                    }
                    int b7 = hVar2.b();
                    int i13 = hVar2.f7735b;
                    if (bArr != null) {
                        z.r1(bArr, b7 + 8, i13);
                    }
                    hVar2.f7744c = 65535;
                    byte[] bArr2 = hVar2.f7734a;
                    if (bArr2 != null) {
                        z.t1(hVar2.f7735b, (short) 65535, bArr2);
                    }
                    int i14 = iArr[i11];
                    hVar2.f7745d = i14 & 65535;
                    byte[] bArr3 = hVar2.f7734a;
                    if (bArr3 != null) {
                        z.t1(hVar2.f7735b + 2, (short) i14, bArr3);
                    }
                }
            }
            hVar2.f7735b = hVar.b();
            while (true) {
                int i15 = hVar2.f7735b;
                if (i15 == hVar.f7735b) {
                    break;
                }
                hVar3.f7735b = (UNIT_SIZE * hVar2.a()) + i15;
                while (true) {
                    byte[] bArr4 = hVar3.f7734a;
                    if (bArr4 != null) {
                        hVar3.f7744c = z.Y0(bArr4, hVar3.f7735b) & 65535;
                    }
                    if (hVar3.f7744c == 65535) {
                        if (hVar3.a() + hVar2.a() < 65536) {
                            hVar3.d();
                            int a7 = hVar3.a() + hVar2.a();
                            hVar2.f7745d = a7 & 65535;
                            byte[] bArr5 = hVar2.f7734a;
                            if (bArr5 != null) {
                                z.t1(hVar2.f7735b + 2, (short) a7, bArr5);
                            }
                            hVar3.f7735b = (UNIT_SIZE * hVar2.a()) + hVar2.f7735b;
                        }
                    }
                }
                hVar2.f7735b = hVar2.b();
            }
            hVar2.f7735b = hVar.b();
            while (hVar2.f7735b != hVar.f7735b) {
                hVar2.d();
                int a8 = hVar2.a();
                while (a8 > 128) {
                    d(hVar2.f7735b, 37);
                    a8 -= 128;
                    hVar2.f7735b = (UNIT_SIZE * 128) + hVar2.f7735b;
                }
                int i16 = this.f7757c[a8 - 1];
                if (iArr[i16] != a8) {
                    i16--;
                    int i17 = a8 - iArr[i16];
                    d((UNIT_SIZE * (a8 - i17)) + hVar2.f7735b, i17 - 1);
                }
                d(hVar2.f7735b, i16);
                hVar2.f7735b = hVar.b();
            }
            if (iVarArr[i7].a() != 0) {
                return e(i7);
            }
        }
        int i18 = i7;
        do {
            i18++;
            if (i18 == 38) {
                this.f7758d--;
                int i19 = iArr[i7];
                int i20 = UNIT_SIZE * i19;
                int i21 = i19 * 12;
                int i22 = this.f7766l;
                if (i22 - this.f7763i <= i21) {
                    return 0;
                }
                this.f7766l = i22 - i21;
                int i23 = this.f7764j - i20;
                this.f7764j = i23;
                return i23;
            }
        } while (iVarArr[i18].a() == 0);
        int e7 = e(i18);
        f(e7, i18, i7);
        return e7;
    }

    public final void d(int i7, int i8) {
        i iVar = this.f7770p;
        iVar.f7735b = i7;
        i[] iVarArr = this.f7762h;
        int a7 = iVarArr[i8].a();
        iVar.f7748c = a7;
        byte[] bArr = iVar.f7734a;
        if (bArr != null) {
            z.r1(bArr, iVar.f7735b, a7);
        }
        i iVar2 = iVarArr[i8];
        iVar2.getClass();
        int i9 = iVar.f7735b;
        iVar2.f7748c = i9;
        byte[] bArr2 = iVar2.f7734a;
        if (bArr2 != null) {
            z.r1(bArr2, iVar2.f7735b, i9);
        }
    }

    public final int e(int i7) {
        i[] iVarArr = this.f7762h;
        int a7 = iVarArr[i7].a();
        i iVar = this.f7770p;
        iVar.f7735b = a7;
        i iVar2 = iVarArr[i7];
        int a8 = iVar.a();
        iVar2.f7748c = a8;
        byte[] bArr = iVar2.f7734a;
        if (bArr != null) {
            z.r1(bArr, iVar2.f7735b, a8);
        }
        return a7;
    }

    public final void f(int i7, int i8, int i9) {
        int[] iArr = this.f7756b;
        int i10 = iArr[i8];
        int i11 = iArr[i9];
        int i12 = i10 - i11;
        int i13 = UNIT_SIZE;
        int i14 = (i11 * i13) + i7;
        int[] iArr2 = this.f7757c;
        int i15 = iArr2[i12 - 1];
        if (iArr[i15] != i12) {
            int i16 = i15 - 1;
            d(i14, i16);
            int i17 = iArr[i16];
            i14 += i13 * i17;
            i12 -= i17;
        }
        d(i14, iArr2[i12 - 1]);
    }

    public final void g() {
        if (this.f7755a != 0) {
            this.f7755a = 0;
            this.f7767m = null;
            this.f7759e = 1;
            this.f7770p = null;
            this.f7771q = null;
            this.f7772r = null;
            this.f7773s = null;
        }
    }

    public final String toString() {
        return "SubAllocator[\n  subAllocatorSize=" + this.f7755a + "\n  glueCount=" + this.f7758d + "\n  heapStart=" + this.f7759e + "\n  loUnit=" + this.f7760f + "\n  hiUnit=" + this.f7761g + "\n  pText=" + this.f7763i + "\n  unitsStart=" + this.f7764j + "\n]";
    }
}
