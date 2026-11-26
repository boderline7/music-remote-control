package n2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends e {

    /* renamed from: c, reason: collision with root package name */
    public int f7723c;

    /* renamed from: d, reason: collision with root package name */
    public final b f7724d;

    /* renamed from: e, reason: collision with root package name */
    public final k f7725e;

    /* renamed from: f, reason: collision with root package name */
    public int f7726f;

    /* renamed from: g, reason: collision with root package name */
    public final k f7727g;

    /* renamed from: h, reason: collision with root package name */
    public final k f7728h;

    /* renamed from: i, reason: collision with root package name */
    public final k f7729i;

    /* renamed from: j, reason: collision with root package name */
    public final k f7730j;

    /* renamed from: k, reason: collision with root package name */
    public final k f7731k;

    /* renamed from: l, reason: collision with root package name */
    public d f7732l;

    /* renamed from: m, reason: collision with root package name */
    public final int[] f7733m;
    public static final int size = Math.max(6, 6) + 6;
    public static final int[] ExpEscape = {25, 14, 9, 7, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2};

    public d(byte[] bArr) {
        super(bArr);
        this.f7727g = new k(null);
        this.f7728h = new k(null);
        this.f7729i = new k(null);
        this.f7730j = new k(null);
        this.f7731k = new k(null);
        this.f7732l = null;
        this.f7733m = new int[256];
        this.f7725e = new k(bArr);
        this.f7724d = new b(bArr);
    }

    public final int a() {
        byte[] bArr = this.f7734a;
        if (bArr != null) {
            this.f7723c = z.Y0(bArr, this.f7735b) & 65535;
        }
        return this.f7723c;
    }

    public final int b() {
        byte[] bArr = this.f7734a;
        if (bArr != null) {
            this.f7726f = z.W0(bArr, this.f7735b + 8);
        }
        return this.f7726f;
    }

    public final d c(byte[] bArr) {
        if (this.f7732l == null) {
            this.f7732l = new d(null);
        }
        d dVar = this.f7732l;
        dVar.d(bArr);
        return dVar;
    }

    public final void d(byte[] bArr) {
        this.f7734a = bArr;
        this.f7735b = 0;
        k kVar = this.f7725e;
        kVar.f7734a = bArr;
        kVar.f7735b = 0;
        b bVar = this.f7724d;
        bVar.f7734a = bArr;
        bVar.f7735b = 0;
    }

    public final void e(c cVar) {
        b bVar;
        int i7;
        m mVar;
        int i8;
        int i9;
        c cVar2 = cVar;
        int a7 = a();
        int i10 = 1;
        int a8 = a() - 1;
        byte[] bArr = cVar2.f7717u.f7767m;
        k kVar = new k(bArr);
        k kVar2 = new k(bArr);
        kVar.f7735b = cVar2.f7701e.f7735b;
        while (true) {
            int i11 = kVar.f7735b;
            bVar = this.f7724d;
            i7 = 6;
            if (i11 == bVar.a()) {
                break;
            }
            kVar2.f7735b = kVar.f7735b - 6;
            k.e(kVar, kVar2);
            kVar.f7735b -= 6;
        }
        kVar2.f7735b = bVar.a();
        kVar2.d(4);
        bVar.c(4);
        int b7 = bVar.b() - kVar.a();
        int i12 = cVar2.f7704h != 0 ? 1 : 0;
        kVar.f((kVar.a() + i12) >>> 1);
        z.t1(bVar.f7735b, (short) kVar.a(), bVar.f7734a);
        while (true) {
            kVar.f7735b += i7;
            b7 -= kVar.a();
            kVar.f((kVar.a() + i12) >>> i10);
            bVar.c(kVar.a());
            kVar2.f7735b = kVar.f7735b - i7;
            int a9 = kVar.a();
            int a10 = kVar2.a();
            mVar = cVar2.f7717u;
            if (a9 > a10) {
                int i13 = kVar.f7735b;
                int i14 = bArr[i13 + 1] & 255 & 255;
                int W0 = z.W0(bArr, i13 + 2);
                int i15 = bArr[i13] & 255 & 255;
                byte[] bArr2 = mVar.f7767m;
                while (true) {
                    i8 = i12;
                    System.arraycopy(bArr2, i13 - 6, bArr, i13, 6);
                    i9 = i13 - 6;
                    if (i9 == bVar.a() || i14 <= (bArr2[i13 - 11] & 255)) {
                        break;
                    }
                    i13 = i9;
                    i12 = i8;
                }
                bArr[i9] = (byte) i15;
                bArr[i9 + 1] = (byte) i14;
                z.r1(bArr, i9 + 2, W0);
            } else {
                i8 = i12;
            }
            a8--;
            if (a8 == 0) {
                break;
            }
            cVar2 = cVar;
            i12 = i8;
            i10 = 1;
            i7 = 6;
        }
        if (kVar.a() == 0) {
            do {
                a8++;
                kVar.f7735b -= 6;
            } while (kVar.a() == 0);
            b7 += a8;
            g(a() - a8);
            if (a() == 1) {
                kVar2.f7735b = bVar.a();
                int a11 = kVar2.a() & 255;
                int b8 = kVar2.b();
                int c7 = kVar2.c() & 255;
                do {
                    a11 = (a11 - (a11 >>> 1)) & 255;
                    b7 >>>= 1;
                } while (b7 > 1);
                mVar.d(bVar.a(), mVar.f7757c[((a7 + 1) >>> 1) - 1]);
                k kVar3 = this.f7725e;
                kVar3.getClass();
                kVar3.f7734a[kVar3.f7735b] = (byte) c7;
                kVar3.f(a11);
                kVar3.g(b8);
                cVar.f7701e.f7735b = kVar3.f7735b;
                return;
            }
        }
        bVar.c(b7 - (b7 >>> 1));
        int i16 = (a7 + 1) >>> 1;
        int a12 = (a() + 1) >>> 1;
        if (i16 != a12) {
            int a13 = bVar.a();
            int[] iArr = mVar.f7757c;
            int i17 = iArr[i16 - 1];
            int i18 = iArr[a12 - 1];
            if (i17 != i18) {
                if (mVar.f7762h[i18].a() != 0) {
                    int e7 = mVar.e(i18);
                    byte[] bArr3 = mVar.f7767m;
                    System.arraycopy(bArr3, a13, bArr3, e7, m.UNIT_SIZE * a12);
                    mVar.d(a13, i17);
                    a13 = e7;
                } else {
                    mVar.f(a13, i17, i18);
                }
            }
            z.r1(bVar.f7734a, bVar.f7735b + 2, a13);
        }
        cVar.f7701e.f7735b = bVar.a();
    }

    public final void f(int i7) {
        this.f7735b = i7;
        int i8 = i7 + 2;
        this.f7725e.f7735b = i8;
        this.f7724d.f7735b = i8;
    }

    public final void g(int i7) {
        this.f7723c = 65535 & i7;
        byte[] bArr = this.f7734a;
        if (bArr != null) {
            z.t1(this.f7735b, (short) i7, bArr);
        }
    }

    public final String toString() {
        return "PPMContext[\n  pos=" + this.f7735b + "\n  size=" + size + "\n  numStats=" + a() + "\n  Suffix=" + b() + "\n  freqData=" + this.f7724d + "\n  oneState=" + this.f7725e + "\n]";
    }
}
