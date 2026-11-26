package u;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r extends s {
    @Override // u.s
    public final void V(int i7, int i8, int i9, int i10) {
        int i11 = this.B0 + this.C0;
        int i12 = this.f9213x0 + this.f9214y0;
        if (this.f9211w0 > 0) {
            i11 += this.f9210v0[0].s();
            i12 += this.f9210v0[0].m();
        }
        int max = Math.max(this.f9135d0, i11);
        int max2 = Math.max(this.f9137e0, i12);
        if (i7 != 1073741824) {
            i8 = i7 == Integer.MIN_VALUE ? Math.min(max, i8) : i7 == 0 ? max : 0;
        }
        if (i9 != 1073741824) {
            i10 = i9 == Integer.MIN_VALUE ? Math.min(max2, i10) : i9 == 0 ? max2 : 0;
        }
        this.E0 = i8;
        this.F0 = i10;
        Q(i8);
        N(i10);
        this.D0 = this.f9211w0 > 0;
    }

    @Override // u.i
    public final void c(s.d dVar, boolean z6) {
        super.c(dVar, z6);
        if (this.f9211w0 > 0) {
            i iVar = this.f9210v0[0];
            iVar.F();
            iVar.f9141g0 = 0.5f;
            iVar.f9139f0 = 0.5f;
            e eVar = e.LEFT;
            iVar.f(eVar, this, eVar, 0);
            e eVar2 = e.RIGHT;
            iVar.f(eVar2, this, eVar2, 0);
            e eVar3 = e.TOP;
            iVar.f(eVar3, this, eVar3, 0);
            e eVar4 = e.BOTTOM;
            iVar.f(eVar4, this, eVar4, 0);
        }
    }
}
