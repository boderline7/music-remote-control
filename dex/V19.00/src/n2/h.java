package n2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h extends e {
    public static final int size = 12;

    /* renamed from: c, reason: collision with root package name */
    public int f7744c;

    /* renamed from: d, reason: collision with root package name */
    public int f7745d;

    /* renamed from: e, reason: collision with root package name */
    public int f7746e;

    /* renamed from: f, reason: collision with root package name */
    public int f7747f;

    public final int a() {
        byte[] bArr = this.f7734a;
        if (bArr != null) {
            this.f7745d = z.Y0(bArr, this.f7735b + 2) & 65535;
        }
        return this.f7745d;
    }

    public final int b() {
        byte[] bArr = this.f7734a;
        if (bArr != null) {
            this.f7746e = z.W0(bArr, this.f7735b + 4);
        }
        return this.f7746e;
    }

    public final int c() {
        byte[] bArr = this.f7734a;
        if (bArr != null) {
            this.f7747f = z.W0(bArr, this.f7735b + 8);
        }
        return this.f7747f;
    }

    public final void d() {
        h hVar = new h(this.f7734a);
        hVar.f7735b = c();
        hVar.e(b());
        hVar.f7735b = b();
        hVar.f(c());
    }

    public final void e(int i7) {
        this.f7746e = i7;
        byte[] bArr = this.f7734a;
        if (bArr != null) {
            z.r1(bArr, this.f7735b + 4, i7);
        }
    }

    public final void f(int i7) {
        this.f7747f = i7;
        byte[] bArr = this.f7734a;
        if (bArr != null) {
            z.r1(bArr, this.f7735b + 8, i7);
        }
    }
}
