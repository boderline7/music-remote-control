package a6;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o0 {
    public static final int COUNT = 10;
    public static final n0 Companion = new n0();
    public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
    public static final int ENABLE_PUSH = 2;
    public static final int HEADER_TABLE_SIZE = 1;
    public static final int INITIAL_WINDOW_SIZE = 7;
    public static final int MAX_CONCURRENT_STREAMS = 4;
    public static final int MAX_FRAME_SIZE = 5;
    public static final int MAX_HEADER_LIST_SIZE = 6;

    /* renamed from: a, reason: collision with root package name */
    public int f454a;

    /* renamed from: b, reason: collision with root package name */
    public final int[] f455b = new int[10];

    public final int a() {
        if ((this.f454a & 128) != 0) {
            return this.f455b[7];
        }
        return 65535;
    }

    public final void b(o0 o0Var) {
        i5.a.g("other", o0Var);
        for (int i7 = 0; i7 < 10; i7++) {
            if (((1 << i7) & o0Var.f454a) != 0) {
                c(i7, o0Var.f455b[i7]);
            }
        }
    }

    public final void c(int i7, int i8) {
        if (i7 >= 0) {
            int[] iArr = this.f455b;
            if (i7 >= iArr.length) {
                return;
            }
            this.f454a = (1 << i7) | this.f454a;
            iArr[i7] = i8;
        }
    }
}
