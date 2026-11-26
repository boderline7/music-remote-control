package y3;

import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    public int f10174a;

    /* renamed from: b, reason: collision with root package name */
    public int f10175b;

    /* renamed from: c, reason: collision with root package name */
    public int f10176c;

    /* renamed from: d, reason: collision with root package name */
    public int[] f10177d;

    public b(int i7, int i8) {
        if (i7 < 1 || i8 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.f10174a = i7;
        this.f10175b = i8;
        int i9 = (i7 + 31) / 32;
        this.f10176c = i9;
        this.f10177d = new int[i9 * i8];
    }

    public final boolean a(int i7, int i8) {
        return ((this.f10177d[(i7 / 32) + (i8 * this.f10176c)] >>> (i7 & 31)) & 1) != 0;
    }

    public final void b(int i7, int i8) {
        int i9 = (i7 / 32) + (i8 * this.f10176c);
        int[] iArr = this.f10177d;
        iArr[i9] = (1 << (i7 & 31)) | iArr[i9];
    }

    public final void c(int i7, int i8, int i9, int i10) {
        if (i8 < 0 || i7 < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i10 < 1 || i9 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int i11 = i9 + i7;
        int i12 = i10 + i8;
        if (i12 > this.f10175b || i11 > this.f10174a) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        while (i8 < i12) {
            int i13 = this.f10176c * i8;
            for (int i14 = i7; i14 < i11; i14++) {
                int i15 = (i14 / 32) + i13;
                int[] iArr = this.f10177d;
                iArr[i15] = iArr[i15] | (1 << (i14 & 31));
            }
            i8++;
        }
    }

    public final Object clone() {
        int[] iArr = (int[]) this.f10177d.clone();
        b bVar = new b();
        bVar.f10174a = this.f10174a;
        bVar.f10175b = this.f10175b;
        bVar.f10176c = this.f10176c;
        bVar.f10177d = iArr;
        return bVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f10174a == bVar.f10174a && this.f10175b == bVar.f10175b && this.f10176c == bVar.f10176c && Arrays.equals(this.f10177d, bVar.f10177d);
    }

    public final int hashCode() {
        int i7 = this.f10174a;
        return Arrays.hashCode(this.f10177d) + (((((((i7 * 31) + i7) * 31) + this.f10175b) * 31) + this.f10176c) * 31);
    }

    public final String toString() {
        int i7 = this.f10174a;
        int i8 = this.f10175b;
        StringBuilder sb = new StringBuilder((i7 + 1) * i8);
        for (int i9 = 0; i9 < i8; i9++) {
            for (int i10 = 0; i10 < i7; i10++) {
                sb.append(a(i10, i9) ? "X " : "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
