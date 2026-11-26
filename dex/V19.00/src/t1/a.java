package t1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final char[] f9022a = new char[64];

    /* renamed from: b, reason: collision with root package name */
    public static final byte[] f9023b;
    public static final String key = "mobile";

    static {
        char c7 = 'A';
        int i7 = 0;
        while (c7 <= 'Z') {
            f9022a[i7] = c7;
            c7 = (char) (c7 + 1);
            i7++;
        }
        char c8 = 'a';
        while (c8 <= 'z') {
            f9022a[i7] = c8;
            c8 = (char) (c8 + 1);
            i7++;
        }
        char c9 = '0';
        while (c9 <= '9') {
            f9022a[i7] = c9;
            c9 = (char) (c9 + 1);
            i7++;
        }
        char[] cArr = f9022a;
        cArr[i7] = '+';
        cArr[i7 + 1] = '/';
        f9023b = new byte[128];
        int i8 = 0;
        while (true) {
            byte[] bArr = f9023b;
            if (i8 >= bArr.length) {
                break;
            }
            bArr[i8] = -1;
            i8++;
        }
        for (int i9 = 0; i9 < 64; i9++) {
            f9023b[f9022a[i9]] = (byte) i9;
        }
    }
}
