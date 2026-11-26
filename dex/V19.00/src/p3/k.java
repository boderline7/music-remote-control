package p3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends f {

    /* renamed from: e, reason: collision with root package name */
    public final f f8286e;

    /* renamed from: f, reason: collision with root package name */
    public final float f8287f;

    public k(g gVar, float f7) {
        super(0, 0);
        this.f8286e = gVar;
        this.f8287f = f7;
    }

    @Override // p3.f
    public final boolean o() {
        return this.f8286e.o();
    }

    @Override // p3.f
    public final void p(float f7, float f8, float f9, z zVar) {
        this.f8286e.p(f7, f8 - this.f8287f, f9, zVar);
    }
}
