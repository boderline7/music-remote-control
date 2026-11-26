package u;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class s extends p {

    /* renamed from: x0, reason: collision with root package name */
    public int f9213x0 = 0;

    /* renamed from: y0, reason: collision with root package name */
    public int f9214y0 = 0;

    /* renamed from: z0, reason: collision with root package name */
    public int f9215z0 = 0;
    public int A0 = 0;
    public int B0 = 0;
    public int C0 = 0;
    public boolean D0 = false;
    public int E0 = 0;
    public int F0 = 0;
    public final v.b G0 = new v.b();
    public v.r H0 = null;

    public abstract void V(int i7, int i8, int i9, int i10);

    public final void W(i iVar, h hVar, int i7, h hVar2, int i8) {
        v.r rVar;
        i iVar2;
        while (true) {
            rVar = this.H0;
            if (rVar != null || (iVar2 = this.V) == null) {
                break;
            } else {
                this.H0 = ((j) iVar2).f9178z0;
            }
        }
        v.b bVar = this.G0;
        bVar.f9315a = hVar;
        bVar.f9316b = hVar2;
        bVar.f9317c = i7;
        bVar.f9318d = i8;
        rVar.b(iVar, bVar);
        iVar.Q(bVar.f9319e);
        iVar.N(bVar.f9320f);
        iVar.E = bVar.f9322h;
        iVar.K(bVar.f9321g);
    }

    @Override // u.p, u.o
    public final void a() {
        for (int i7 = 0; i7 < this.f9211w0; i7++) {
            i iVar = this.f9210v0[i7];
            if (iVar != null) {
                iVar.G = true;
            }
        }
    }
}
