package y3;

import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Cloneable {

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f10171c = new int[0];

    /* renamed from: b, reason: collision with root package name */
    public int f10173b = 0;

    /* renamed from: a, reason: collision with root package name */
    public int[] f10172a = f10171c;

    public final void a(boolean z6) {
        c(this.f10173b + 1);
        if (z6) {
            int[] iArr = this.f10172a;
            int i7 = this.f10173b;
            int i8 = i7 / 32;
            iArr[i8] = (1 << (i7 & 31)) | iArr[i8];
        }
        this.f10173b++;
    }

    public final void b(int i7, int i8) {
        if (i8 < 0 || i8 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        int i9 = this.f10173b;
        c(i9 + i8);
        for (int i10 = i8 - 1; i10 >= 0; i10--) {
            if (((1 << i10) & i7) != 0) {
                int[] iArr = this.f10172a;
                int i11 = i9 / 32;
                iArr[i11] = iArr[i11] | (1 << (i9 & 31));
            }
            i9++;
        }
        this.f10173b = i9;
    }

    public final void c(int i7) {
        if (i7 > this.f10172a.length * 32) {
            int[] iArr = new int[(((int) Math.ceil(i7 / 0.75f)) + 31) / 32];
            int[] iArr2 = this.f10172a;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            this.f10172a = iArr;
        }
    }

    public final Object clone() {
        int[] iArr = (int[]) this.f10172a.clone();
        int i7 = this.f10173b;
        a aVar = new a();
        aVar.f10172a = iArr;
        aVar.f10173b = i7;
        return aVar;
    }

    public final boolean d(int i7) {
        return ((1 << (i7 & 31)) & this.f10172a[i7 / 32]) != 0;
    }

    public final int e() {
        return (this.f10173b + 7) / 8;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f10173b == aVar.f10173b && Arrays.equals(this.f10172a, aVar.f10172a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f10172a) + (this.f10173b * 31);
    }

    public final String toString() {
        int i7 = this.f10173b;
        StringBuilder sb = new StringBuilder((i7 / 8) + i7 + 1);
        for (int i8 = 0; i8 < this.f10173b; i8++) {
            if ((i8 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(d(i8) ? 'X' : '.');
        }
        return sb.toString();
    }
}
