package z3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {
    public static final a AZTEC_DATA_6;
    public static final a AZTEC_DATA_8;
    public static final a AZTEC_PARAM;
    public static final a DATA_MATRIX_FIELD_256;
    public static final a MAXICODE_FIELD_64;
    public static final a QR_CODE_FIELD_256;

    /* renamed from: a, reason: collision with root package name */
    public final int[] f10288a;

    /* renamed from: b, reason: collision with root package name */
    public final int[] f10289b;

    /* renamed from: c, reason: collision with root package name */
    public final b f10290c;

    /* renamed from: d, reason: collision with root package name */
    public final int f10291d;

    /* renamed from: e, reason: collision with root package name */
    public final int f10292e;

    /* renamed from: f, reason: collision with root package name */
    public final int f10293f;
    public static final a AZTEC_DATA_12 = new a(4201, 4096, 1);
    public static final a AZTEC_DATA_10 = new a(1033, 1024, 1);

    static {
        a aVar = new a(67, 64, 1);
        AZTEC_DATA_6 = aVar;
        AZTEC_PARAM = new a(19, 16, 1);
        QR_CODE_FIELD_256 = new a(285, 256, 0);
        a aVar2 = new a(301, 256, 1);
        DATA_MATRIX_FIELD_256 = aVar2;
        AZTEC_DATA_8 = aVar2;
        MAXICODE_FIELD_64 = aVar;
    }

    public a(int i7, int i8, int i9) {
        this.f10292e = i7;
        this.f10291d = i8;
        this.f10293f = i9;
        this.f10288a = new int[i8];
        this.f10289b = new int[i8];
        int i10 = 1;
        int i11 = 1;
        for (int i12 = 0; i12 < i8; i12++) {
            this.f10288a[i12] = i11;
            i11 *= 2;
            if (i11 >= i8) {
                i11 = (i11 ^ i7) & (i8 - 1);
            }
        }
        for (int i13 = 0; i13 < i8 - 1; i13++) {
            this.f10289b[this.f10288a[i13]] = i13;
        }
        this.f10290c = new b(this, new int[]{0});
        int[] iArr = {1};
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            return;
        }
        while (i10 < length && iArr[i10] == 0) {
            i10++;
        }
        if (i10 == length) {
            return;
        }
        int[] iArr2 = new int[length - i10];
        System.arraycopy(iArr, i10, iArr2, 0, iArr2.length);
    }

    public final int a(int i7, int i8) {
        if (i7 == 0 || i8 == 0) {
            return 0;
        }
        int[] iArr = this.f10289b;
        return this.f10288a[(iArr[i7] + iArr[i8]) % (this.f10291d - 1)];
    }

    public final String toString() {
        return "GF(0x" + Integer.toHexString(this.f10292e) + ',' + this.f10291d + ')';
    }
}
