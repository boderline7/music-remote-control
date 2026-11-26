package x3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends f {

    /* renamed from: c, reason: collision with root package name */
    public final int f9611c;

    /* renamed from: d, reason: collision with root package name */
    public final int f9612d;

    public a(f fVar, int i7, int i8) {
        super(fVar);
        this.f9611c = i7;
        this.f9612d = i8;
    }

    @Override // x3.f
    public final void a(y3.a aVar, byte[] bArr) {
        int i7 = 0;
        while (true) {
            int i8 = this.f9612d;
            if (i7 >= i8) {
                return;
            }
            if (i7 == 0 || (i7 == 31 && i8 <= 62)) {
                aVar.b(31, 5);
                if (i8 > 62) {
                    aVar.b(i8 - 31, 16);
                } else if (i7 == 0) {
                    aVar.b(Math.min(i8, 31), 5);
                } else {
                    aVar.b(i8 - 31, 5);
                }
            }
            aVar.b(bArr[this.f9611c + i7], 8);
            i7++;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(this.f9611c);
        sb.append("::");
        sb.append((r1 + this.f9612d) - 1);
        sb.append('>');
        return sb.toString();
    }
}
