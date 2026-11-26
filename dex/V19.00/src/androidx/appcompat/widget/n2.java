package androidx.appcompat.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n2 {
    public static final int UNDEFINED = Integer.MIN_VALUE;

    /* renamed from: a, reason: collision with root package name */
    public int f1353a;

    /* renamed from: b, reason: collision with root package name */
    public int f1354b;

    /* renamed from: c, reason: collision with root package name */
    public int f1355c;

    /* renamed from: d, reason: collision with root package name */
    public int f1356d;

    /* renamed from: e, reason: collision with root package name */
    public int f1357e;

    /* renamed from: f, reason: collision with root package name */
    public int f1358f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1359g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f1360h;

    public final void a(int i7, int i8) {
        this.f1355c = i7;
        this.f1356d = i8;
        this.f1360h = true;
        if (this.f1359g) {
            if (i8 != Integer.MIN_VALUE) {
                this.f1353a = i8;
            }
            if (i7 != Integer.MIN_VALUE) {
                this.f1354b = i7;
                return;
            }
            return;
        }
        if (i7 != Integer.MIN_VALUE) {
            this.f1353a = i7;
        }
        if (i8 != Integer.MIN_VALUE) {
            this.f1354b = i8;
        }
    }
}
