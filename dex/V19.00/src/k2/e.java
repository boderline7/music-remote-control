package k2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends p {
    public static final short EAHeaderSize = 10;

    /* renamed from: q, reason: collision with root package name */
    public static final j6.a f7218q = j6.b.d(e.class);

    /* renamed from: m, reason: collision with root package name */
    public final int f7219m;

    /* renamed from: n, reason: collision with root package name */
    public final byte f7220n;

    /* renamed from: o, reason: collision with root package name */
    public final byte f7221o;

    /* renamed from: p, reason: collision with root package name */
    public final int f7222p;

    public e(p pVar, byte[] bArr) {
        super(pVar);
        this.f7219m = z.W0(bArr, 0);
        this.f7220n = (byte) (this.f7220n | (bArr[4] & 255));
        this.f7221o = (byte) (this.f7221o | (bArr[5] & 255));
        this.f7222p = z.W0(bArr, 6);
    }

    @Override // k2.p
    public final void d() {
        super.d();
        j6.a aVar = f7218q;
        if (aVar.c()) {
            aVar.d("unpSize: {}", Integer.valueOf(this.f7219m));
            aVar.d("unpVersion: {}", Byte.valueOf(this.f7220n));
            aVar.d("method: {}", Byte.valueOf(this.f7221o));
            aVar.d("EACRC: {}", Integer.valueOf(this.f7222p));
        }
    }
}
