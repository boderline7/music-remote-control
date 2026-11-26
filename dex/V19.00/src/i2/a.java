package i2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f6795a = new int[256];

    static {
        for (int i7 = 0; i7 < 256; i7++) {
            int i8 = i7;
            for (int i9 = 0; i9 < 8; i9++) {
                i8 = (i8 & 1) != 0 ? (i8 >>> 1) ^ (-306674912) : i8 >>> 1;
            }
            f6795a[i7] = i8;
        }
    }

    public static int a(int i7, byte[] bArr, int i8, int i9) {
        int min = Math.min(bArr.length - i8, i9);
        for (int i10 = 0; i10 < min; i10++) {
            i7 = (i7 >>> 8) ^ f6795a[(bArr[i8 + i10] ^ i7) & 255];
        }
        return i7;
    }
}
