package n2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends e {
    public static final int size = 6;

    public final int a() {
        return z.W0(this.f7734a, this.f7735b + 2);
    }

    public final int b() {
        return z.Y0(this.f7734a, this.f7735b) & 65535;
    }

    public final void c(int i7) {
        byte[] bArr = this.f7734a;
        int i8 = this.f7735b;
        byte b7 = bArr[i8];
        int i9 = i7 & 255;
        int i10 = ((b7 & 255) + i9) >>> 8;
        bArr[i8] = (byte) (b7 + i9);
        if (i10 > 0 || (65280 & i7) != 0) {
            int i11 = i8 + 1;
            bArr[i11] = (byte) (((i7 >>> 8) & 255) + i10 + bArr[i11]);
        }
    }

    public final String toString() {
        return "FreqData[\n  pos=" + this.f7735b + "\n  size=6\n  summFreq=" + b() + "\n  stats=" + a() + "\n]";
    }
}
