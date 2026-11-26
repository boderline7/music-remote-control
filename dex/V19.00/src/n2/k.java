package n2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends e {
    public static final int size = 6;

    public static void e(k kVar, k kVar2) {
        byte[] bArr = kVar.f7734a;
        byte[] bArr2 = kVar2.f7734a;
        int i7 = kVar.f7735b;
        int i8 = kVar2.f7735b;
        int i9 = 0;
        while (i9 < 6) {
            byte b7 = bArr[i7];
            bArr[i7] = bArr2[i8];
            bArr2[i8] = b7;
            i9++;
            i7++;
            i8++;
        }
    }

    public final int a() {
        return this.f7734a[this.f7735b + 1] & 255;
    }

    public final int b() {
        return z.W0(this.f7734a, this.f7735b + 2);
    }

    public final int c() {
        return this.f7734a[this.f7735b] & 255;
    }

    public final void d(int i7) {
        byte[] bArr = this.f7734a;
        int i8 = this.f7735b + 1;
        bArr[i8] = (byte) (bArr[i8] + i7);
    }

    public final void f(int i7) {
        this.f7734a[this.f7735b + 1] = (byte) i7;
    }

    public final void g(int i7) {
        z.r1(this.f7734a, this.f7735b + 2, i7);
    }

    public final String toString() {
        return "State[\n  pos=" + this.f7735b + "\n  size=6\n  symbol=" + c() + "\n  freq=" + a() + "\n  successor=" + b() + "\n]";
    }
}
