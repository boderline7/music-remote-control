package s5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class t0 extends v0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l0 f8895a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f8896b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ byte[] f8897c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f8898d;

    public t0(l0 l0Var, byte[] bArr, int i7, int i8) {
        this.f8895a = l0Var;
        this.f8896b = i7;
        this.f8897c = bArr;
        this.f8898d = i8;
    }

    @Override // s5.v0
    public final long contentLength() {
        return this.f8896b;
    }

    @Override // s5.v0
    public final l0 contentType() {
        return this.f8895a;
    }

    @Override // s5.v0
    public final void writeTo(g6.i iVar) {
        ((g6.s) iVar).c(this.f8897c, this.f8898d, this.f8896b);
    }
}
