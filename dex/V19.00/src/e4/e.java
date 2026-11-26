package e4;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract /* synthetic */ class e {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f6380a;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f6381b;

    static {
        int[] iArr = new int[d4.b.values().length];
        f6381b = iArr;
        try {
            iArr[d4.b.KANJI.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f6381b[d4.b.ALPHANUMERIC.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f6381b[d4.b.NUMERIC.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f6381b[d4.b.BYTE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f6381b[d4.b.ECI.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        int[] iArr2 = new int[h.values().length];
        f6380a = iArr2;
        try {
            iArr2[h.SMALL.ordinal()] = 1;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            f6380a[h.MEDIUM.ordinal()] = 2;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            f6380a[h.LARGE.ordinal()] = 3;
        } catch (NoSuchFieldError unused8) {
        }
    }
}
