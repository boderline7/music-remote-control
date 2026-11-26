package x3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends f {

    /* renamed from: c, reason: collision with root package name */
    public final short f9619c;

    /* renamed from: d, reason: collision with root package name */
    public final short f9620d;

    public d(f fVar, int i7, int i8) {
        super(fVar);
        this.f9619c = (short) i7;
        this.f9620d = (short) i8;
    }

    @Override // x3.f
    public final void a(y3.a aVar, byte[] bArr) {
        aVar.b(this.f9619c, this.f9620d);
    }

    public final String toString() {
        short s6 = this.f9620d;
        return "<" + Integer.toBinaryString((1 << s6) | (((1 << s6) - 1) & this.f9619c) | (1 << s6)).substring(1) + '>';
    }
}
