package a4;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract /* synthetic */ class e {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f280a;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f281b;

    static {
        int[] iArr = new int[k.values().length];
        f281b = iArr;
        try {
            iArr[k.FORCE_SQUARE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f281b[k.FORCE_RECTANGLE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        int[] iArr2 = new int[h.values().length];
        f280a = iArr2;
        try {
            iArr2[h.ASCII.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f280a[h.B256.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f280a[h.C40.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f280a[h.TEXT.ordinal()] = 4;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            f280a[h.X12.ordinal()] = 5;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            f280a[h.EDF.ordinal()] = 6;
        } catch (NoSuchFieldError unused8) {
        }
    }
}
