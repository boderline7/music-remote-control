package v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends v {
    @Override // v.e
    public final void a(e eVar) {
        h hVar = this.f9383h;
        if (hVar.f9341c && !hVar.f9348j) {
            hVar.d((int) ((((h) hVar.f9350l.get(0)).f9345g * ((u.n) this.f9377b).f9205v0) + 0.5f));
        }
    }

    @Override // v.v
    public final void d() {
        u.i iVar = this.f9377b;
        u.n nVar = (u.n) iVar;
        int i7 = nVar.f9206w0;
        int i8 = nVar.f9207x0;
        int i9 = nVar.f9209z0;
        h hVar = this.f9383h;
        if (i9 == 1) {
            if (i7 != -1) {
                hVar.f9350l.add(iVar.V.f9134d.f9383h);
                this.f9377b.V.f9134d.f9383h.f9349k.add(hVar);
                hVar.f9344f = i7;
            } else if (i8 != -1) {
                hVar.f9350l.add(iVar.V.f9134d.f9384i);
                this.f9377b.V.f9134d.f9384i.f9349k.add(hVar);
                hVar.f9344f = -i8;
            } else {
                hVar.f9340b = true;
                hVar.f9350l.add(iVar.V.f9134d.f9384i);
                this.f9377b.V.f9134d.f9384i.f9349k.add(hVar);
            }
            m(this.f9377b.f9134d.f9383h);
            m(this.f9377b.f9134d.f9384i);
            return;
        }
        if (i7 != -1) {
            hVar.f9350l.add(iVar.V.f9136e.f9383h);
            this.f9377b.V.f9136e.f9383h.f9349k.add(hVar);
            hVar.f9344f = i7;
        } else if (i8 != -1) {
            hVar.f9350l.add(iVar.V.f9136e.f9384i);
            this.f9377b.V.f9136e.f9384i.f9349k.add(hVar);
            hVar.f9344f = -i8;
        } else {
            hVar.f9340b = true;
            hVar.f9350l.add(iVar.V.f9136e.f9384i);
            this.f9377b.V.f9136e.f9384i.f9349k.add(hVar);
        }
        m(this.f9377b.f9136e.f9383h);
        m(this.f9377b.f9136e.f9384i);
    }

    @Override // v.v
    public final void e() {
        u.i iVar = this.f9377b;
        int i7 = ((u.n) iVar).f9209z0;
        h hVar = this.f9383h;
        if (i7 == 1) {
            iVar.f9129a0 = hVar.f9345g;
        } else {
            iVar.f9131b0 = hVar.f9345g;
        }
    }

    @Override // v.v
    public final void f() {
        this.f9383h.c();
    }

    @Override // v.v
    public final boolean k() {
        return false;
    }

    public final void m(h hVar) {
        h hVar2 = this.f9383h;
        hVar2.f9349k.add(hVar);
        hVar.f9350l.add(hVar2);
    }
}
