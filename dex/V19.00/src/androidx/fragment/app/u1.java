package androidx.fragment.app;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract /* synthetic */ class u1 {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f2060a;

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f2061b;

    static {
        int[] iArr = new int[w1.values().length];
        f2061b = iArr;
        try {
            iArr[w1.ADDING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f2061b[w1.REMOVING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f2061b[w1.NONE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        int[] iArr2 = new int[x1.values().length];
        f2060a = iArr2;
        try {
            iArr2[x1.REMOVED.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f2060a[x1.VISIBLE.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f2060a[x1.GONE.ordinal()] = 3;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            f2060a[x1.INVISIBLE.ordinal()] = 4;
        } catch (NoSuchFieldError unused7) {
        }
    }
}
