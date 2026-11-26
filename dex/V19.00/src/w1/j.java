package w1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    public final int f9431a;

    /* renamed from: b, reason: collision with root package name */
    public final int f9432b;

    /* renamed from: c, reason: collision with root package name */
    public final int f9433c;

    /* renamed from: d, reason: collision with root package name */
    public final n f9434d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f9435e;

    public j(int i7, int i8, int i9, n nVar, boolean z6) {
        if (!z1.c.a(i7)) {
            throw new IllegalArgumentException("bogus opcode");
        }
        if (!z1.c.a(i8)) {
            throw new IllegalArgumentException("bogus family");
        }
        if (!z1.c.a(i9)) {
            throw new IllegalArgumentException("bogus nextOpcode");
        }
        if (nVar == null) {
            throw new NullPointerException("format == null");
        }
        this.f9431a = i7;
        this.f9432b = i8;
        this.f9433c = i9;
        this.f9434d = nVar;
        this.f9435e = z6;
    }

    public final String a() {
        int i7 = this.f9431a;
        try {
            z1.a aVar = z1.b.f10250a[i7 + 1];
            if (aVar != null) {
                return aVar.f10249b;
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
        }
        throw new IllegalArgumentException("bogus opcode: ".concat(i7 == ((char) i7) ? n1.z.l1(i7) : n1.z.m1(i7)));
    }

    public final String toString() {
        return a();
    }
}
