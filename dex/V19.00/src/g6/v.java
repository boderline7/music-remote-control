package g6;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v {
    public static final u Companion = new u();
    public static final int SHARE_MINIMUM = 1024;
    public static final int SIZE = 8192;

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f6596a;

    /* renamed from: b, reason: collision with root package name */
    public int f6597b;

    /* renamed from: c, reason: collision with root package name */
    public int f6598c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f6599d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f6600e;

    /* renamed from: f, reason: collision with root package name */
    public v f6601f;

    /* renamed from: g, reason: collision with root package name */
    public v f6602g;

    public v() {
        this.f6596a = new byte[8192];
        this.f6600e = true;
        this.f6599d = false;
    }

    public v(byte[] bArr, int i7, int i8, boolean z6) {
        i5.a.g("data", bArr);
        this.f6596a = bArr;
        this.f6597b = i7;
        this.f6598c = i8;
        this.f6599d = z6;
        this.f6600e = false;
    }

    public final v a() {
        v vVar = this.f6601f;
        if (vVar == this) {
            vVar = null;
        }
        v vVar2 = this.f6602g;
        i5.a.d(vVar2);
        vVar2.f6601f = this.f6601f;
        v vVar3 = this.f6601f;
        i5.a.d(vVar3);
        vVar3.f6602g = this.f6602g;
        this.f6601f = null;
        this.f6602g = null;
        return vVar;
    }

    public final void b(v vVar) {
        vVar.f6602g = this;
        vVar.f6601f = this.f6601f;
        v vVar2 = this.f6601f;
        i5.a.d(vVar2);
        vVar2.f6602g = vVar;
        this.f6601f = vVar;
    }

    public final v c() {
        this.f6599d = true;
        return new v(this.f6596a, this.f6597b, this.f6598c, true);
    }

    public final void d(v vVar, int i7) {
        if (!vVar.f6600e) {
            throw new IllegalStateException("only owner can write".toString());
        }
        int i8 = vVar.f6598c;
        int i9 = i8 + i7;
        byte[] bArr = vVar.f6596a;
        if (i9 > 8192) {
            if (vVar.f6599d) {
                throw new IllegalArgumentException();
            }
            int i10 = vVar.f6597b;
            if (i9 - i10 > 8192) {
                throw new IllegalArgumentException();
            }
            c5.l.y1(0, i10, i8, bArr, bArr);
            vVar.f6598c -= vVar.f6597b;
            vVar.f6597b = 0;
        }
        int i11 = vVar.f6598c;
        int i12 = this.f6597b;
        c5.l.y1(i11, i12, i12 + i7, this.f6596a, bArr);
        vVar.f6598c += i7;
        this.f6597b += i7;
    }
}
