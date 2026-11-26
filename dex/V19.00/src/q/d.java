package q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f8382a = new int[0];

    /* renamed from: b, reason: collision with root package name */
    public static final Object[] f8383b = new Object[0];

    public static int a(int i7, int i8, int[] iArr) {
        int i9 = i7 - 1;
        int i10 = 0;
        while (i10 <= i9) {
            int i11 = (i10 + i9) >>> 1;
            int i12 = iArr[i11];
            if (i12 < i8) {
                i10 = i11 + 1;
            } else {
                if (i12 <= i8) {
                    return i11;
                }
                i9 = i11 - 1;
            }
        }
        return ~i10;
    }

    public static int b(long[] jArr, int i7, long j7) {
        int i8 = i7 - 1;
        int i9 = 0;
        while (i9 <= i8) {
            int i10 = (i9 + i8) >>> 1;
            long j8 = jArr[i10];
            if (j8 < j7) {
                i9 = i10 + 1;
            } else {
                if (j8 <= j7) {
                    return i10;
                }
                i8 = i10 - 1;
            }
        }
        return ~i9;
    }
}
