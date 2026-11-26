package x3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: f, reason: collision with root package name */
    public static final e f9621f = new e(f.f9627b, 0, 0, 0);

    /* renamed from: a, reason: collision with root package name */
    public final int f9622a;

    /* renamed from: b, reason: collision with root package name */
    public final f f9623b;

    /* renamed from: c, reason: collision with root package name */
    public final int f9624c;

    /* renamed from: d, reason: collision with root package name */
    public final int f9625d;

    /* renamed from: e, reason: collision with root package name */
    public final int f9626e;

    public e(f fVar, int i7, int i8, int i9) {
        this.f9623b = fVar;
        this.f9622a = i7;
        this.f9624c = i8;
        this.f9625d = i9;
        this.f9626e = i8 > 62 ? 21 : i8 > 31 ? 20 : i8 > 0 ? 10 : 0;
    }

    public final e a(int i7) {
        f fVar = this.f9623b;
        int i8 = this.f9622a;
        int i9 = this.f9625d;
        if (i8 == 4 || i8 == 2) {
            int[] iArr = c.f9615c[i8];
            i8 = 0;
            int i10 = iArr[0];
            int i11 = 65535 & i10;
            int i12 = i10 >> 16;
            fVar.getClass();
            i9 += i12;
            fVar = new d(fVar, i11, i12);
        }
        int i13 = this.f9624c;
        int i14 = (i13 == 0 || i13 == 31) ? 18 : i13 == 62 ? 9 : 8;
        int i15 = i13 + 1;
        e eVar = new e(fVar, i8, i15, i9 + i14);
        return i15 == 2078 ? eVar.b(i7 + 1) : eVar;
    }

    public final e b(int i7) {
        int i8 = this.f9624c;
        if (i8 == 0) {
            return this;
        }
        f fVar = this.f9623b;
        fVar.getClass();
        return new e(new a(fVar, i7 - i8, i8), this.f9622a, 0, this.f9625d);
    }

    public final boolean c(e eVar) {
        int i7 = this.f9625d + (c.f9615c[this.f9622a][eVar.f9622a] >> 16);
        int i8 = this.f9624c;
        int i9 = eVar.f9624c;
        if (i8 < i9) {
            i7 += eVar.f9626e - this.f9626e;
        } else if (i8 > i9 && i9 > 0) {
            i7 += 10;
        }
        return i7 <= eVar.f9625d;
    }

    public final e d(int i7, int i8) {
        int i9 = this.f9625d;
        f fVar = this.f9623b;
        int i10 = this.f9622a;
        if (i7 != i10) {
            int i11 = c.f9615c[i10][i7];
            int i12 = 65535 & i11;
            int i13 = i11 >> 16;
            fVar.getClass();
            i9 += i13;
            fVar = new d(fVar, i12, i13);
        }
        int i14 = i7 == 2 ? 4 : 5;
        fVar.getClass();
        return new e(new d(fVar, i8, i14), i7, 0, i9 + i14);
    }

    public final e e(int i7, int i8) {
        int i9 = this.f9622a;
        int i10 = i9 == 2 ? 4 : 5;
        int i11 = c.f9617e[i9][i7];
        f fVar = this.f9623b;
        fVar.getClass();
        return new e(new d(new d(fVar, i11, i10), i8, 5), i9, 0, this.f9625d + i10 + 5);
    }

    public final String toString() {
        return String.format("%s bits=%d bytes=%d", c.f9614b[this.f9622a], Integer.valueOf(this.f9625d), Integer.valueOf(this.f9624c));
    }
}
