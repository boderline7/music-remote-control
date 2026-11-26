package a4;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class j {

    /* renamed from: i, reason: collision with root package name */
    public static final j[] f295i = {new j(false, 3, 5, 8, 8, 1, 3, 5), new j(false, 5, 7, 10, 10, 1, 5, 7), new j(true, 5, 7, 16, 6, 1, 5, 7), new j(false, 8, 10, 12, 12, 1, 8, 10), new j(true, 10, 11, 14, 6, 2, 10, 11), new j(false, 12, 12, 14, 14, 1, 12, 12), new j(true, 16, 14, 24, 10, 1, 16, 14), new j(false, 18, 14, 16, 16, 1, 18, 14), new j(false, 22, 18, 18, 18, 1, 22, 18), new j(true, 22, 18, 16, 10, 2, 22, 18), new j(false, 30, 20, 20, 20, 1, 30, 20), new j(true, 32, 24, 16, 14, 2, 32, 24), new j(false, 36, 24, 22, 22, 1, 36, 24), new j(false, 44, 28, 24, 24, 1, 44, 28), new j(true, 49, 28, 22, 14, 2, 49, 28), new j(false, 62, 36, 14, 14, 4, 62, 36), new j(false, 86, 42, 16, 16, 4, 86, 42), new j(false, z1.c.INVOKE_INTERFACE, 48, 18, 18, 4, z1.c.INVOKE_INTERFACE, 48), new j(false, z1.c.ADD_INT, 56, 20, 20, 4, z1.c.ADD_INT, 56), new j(false, z1.c.DIV_DOUBLE, 68, 22, 22, 4, z1.c.DIV_DOUBLE, 68), new j(false, z1.c.SUB_DOUBLE_2ADDR, 84, 24, 24, 4, z1.c.SGET_SHORT, 42), new j(false, 280, z1.c.INVOKE_DIRECT, 14, 14, 16, z1.c.DOUBLE_TO_FLOAT, 56), new j(false, 368, z1.c.ADD_INT, 16, 16, 16, 92, 36), new j(false, 456, z1.c.AND_LONG_2ADDR, 18, 18, 16, z1.c.INVOKE_INTERFACE, 48), new j(false, 576, z1.c.SHL_INT_LIT8, 20, 20, 16, z1.c.ADD_INT, 56), new j(false, 696, 272, 22, 22, 16, z1.c.DIV_DOUBLE, 68), new j(false, 816, 336, 24, 24, 16, z1.c.FLOAT_TO_LONG, 56), new j(false, 1050, 408, 18, 18, 36, z1.c.REM_DOUBLE, 68), new j(false, 1304, 496, 20, 20, 36, z1.c.SHL_LONG, 62), new a(false, 1558, 620, 22, 22, 36, -1, 62)};

    /* renamed from: a, reason: collision with root package name */
    public final boolean f296a;

    /* renamed from: b, reason: collision with root package name */
    public final int f297b;

    /* renamed from: c, reason: collision with root package name */
    public final int f298c;

    /* renamed from: d, reason: collision with root package name */
    public final int f299d;

    /* renamed from: e, reason: collision with root package name */
    public final int f300e;

    /* renamed from: f, reason: collision with root package name */
    public final int f301f;

    /* renamed from: g, reason: collision with root package name */
    public final int f302g;

    /* renamed from: h, reason: collision with root package name */
    public final int f303h;

    public j(boolean z6, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
        this.f296a = z6;
        this.f297b = i7;
        this.f298c = i8;
        this.f299d = i9;
        this.f300e = i10;
        this.f301f = i11;
        this.f302g = i12;
        this.f303h = i13;
    }

    public static j e(int i7, k kVar) {
        j[] jVarArr = f295i;
        for (int i8 = 0; i8 < 30; i8++) {
            j jVar = jVarArr[i8];
            if (!(kVar == k.FORCE_SQUARE && jVar.f296a) && ((kVar != k.FORCE_RECTANGLE || jVar.f296a) && i7 <= jVar.f297b)) {
                return jVar;
            }
        }
        throw new IllegalArgumentException(a.a.d("Can't find a symbol arrangement that matches the message. Data codewords: ", i7));
    }

    public int a(int i7) {
        return this.f302g;
    }

    public final int b() {
        int i7 = 1;
        int i8 = this.f301f;
        if (i8 != 1) {
            i7 = 2;
            if (i8 != 2 && i8 != 4) {
                if (i8 == 16) {
                    return 4;
                }
                if (i8 == 36) {
                    return 6;
                }
                throw new IllegalStateException("Cannot handle this number of data regions");
            }
        }
        return i7;
    }

    public int c() {
        return this.f297b / this.f302g;
    }

    public final int d() {
        int i7 = this.f301f;
        if (i7 == 1 || i7 == 2) {
            return 1;
        }
        if (i7 == 4) {
            return 2;
        }
        if (i7 == 16) {
            return 4;
        }
        if (i7 == 36) {
            return 6;
        }
        throw new IllegalStateException("Cannot handle this number of data regions");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f296a ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        int i7 = this.f299d;
        sb.append(i7);
        sb.append('x');
        int i8 = this.f300e;
        sb.append(i8);
        sb.append(", symbol size ");
        sb.append((b() * 2) + (b() * i7));
        sb.append('x');
        sb.append((d() * 2) + (d() * i8));
        sb.append(", symbol data size ");
        sb.append(b() * i7);
        sb.append('x');
        sb.append(d() * i8);
        sb.append(", codewords ");
        sb.append(this.f297b);
        sb.append('+');
        sb.append(this.f298c);
        return sb.toString();
    }
}
