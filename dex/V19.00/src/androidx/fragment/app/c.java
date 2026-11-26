package androidx.fragment.app;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract /* synthetic */ class c {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f1923a;

    static {
        int[] iArr = new int[x1.values().length];
        f1923a = iArr;
        try {
            iArr[x1.GONE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f1923a[x1.INVISIBLE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f1923a[x1.REMOVED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f1923a[x1.VISIBLE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
