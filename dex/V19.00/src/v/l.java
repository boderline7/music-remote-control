package v;

import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends v {
    @Override // v.e
    public final void a(e eVar) {
        u.a aVar = (u.a) this.f9377b;
        int i7 = aVar.f9094x0;
        h hVar = this.f9383h;
        Iterator it = hVar.f9350l.iterator();
        int i8 = 0;
        int i9 = -1;
        while (it.hasNext()) {
            int i10 = ((h) it.next()).f9345g;
            if (i9 == -1 || i10 < i9) {
                i9 = i10;
            }
            if (i8 < i10) {
                i8 = i10;
            }
        }
        if (i7 == 0 || i7 == 2) {
            hVar.d(i9 + aVar.f9096z0);
        } else {
            hVar.d(i8 + aVar.f9096z0);
        }
    }

    @Override // v.v
    public final void d() {
        u.i iVar = this.f9377b;
        if (iVar instanceof u.a) {
            h hVar = this.f9383h;
            hVar.f9340b = true;
            u.a aVar = (u.a) iVar;
            int i7 = aVar.f9094x0;
            boolean z6 = aVar.f9095y0;
            int i8 = 0;
            if (i7 == 0) {
                hVar.f9343e = g.LEFT;
                while (i8 < aVar.f9211w0) {
                    u.i iVar2 = aVar.f9210v0[i8];
                    if (z6 || iVar2.f9145i0 != 8) {
                        h hVar2 = iVar2.f9134d.f9383h;
                        hVar2.f9349k.add(hVar);
                        hVar.f9350l.add(hVar2);
                    }
                    i8++;
                }
                m(this.f9377b.f9134d.f9383h);
                m(this.f9377b.f9134d.f9384i);
                return;
            }
            if (i7 == 1) {
                hVar.f9343e = g.RIGHT;
                while (i8 < aVar.f9211w0) {
                    u.i iVar3 = aVar.f9210v0[i8];
                    if (z6 || iVar3.f9145i0 != 8) {
                        h hVar3 = iVar3.f9134d.f9384i;
                        hVar3.f9349k.add(hVar);
                        hVar.f9350l.add(hVar3);
                    }
                    i8++;
                }
                m(this.f9377b.f9134d.f9383h);
                m(this.f9377b.f9134d.f9384i);
                return;
            }
            if (i7 == 2) {
                hVar.f9343e = g.TOP;
                while (i8 < aVar.f9211w0) {
                    u.i iVar4 = aVar.f9210v0[i8];
                    if (z6 || iVar4.f9145i0 != 8) {
                        h hVar4 = iVar4.f9136e.f9383h;
                        hVar4.f9349k.add(hVar);
                        hVar.f9350l.add(hVar4);
                    }
                    i8++;
                }
                m(this.f9377b.f9136e.f9383h);
                m(this.f9377b.f9136e.f9384i);
                return;
            }
            if (i7 != 3) {
                return;
            }
            hVar.f9343e = g.BOTTOM;
            while (i8 < aVar.f9211w0) {
                u.i iVar5 = aVar.f9210v0[i8];
                if (z6 || iVar5.f9145i0 != 8) {
                    h hVar5 = iVar5.f9136e.f9384i;
                    hVar5.f9349k.add(hVar);
                    hVar.f9350l.add(hVar5);
                }
                i8++;
            }
            m(this.f9377b.f9136e.f9383h);
            m(this.f9377b.f9136e.f9384i);
        }
    }

    @Override // v.v
    public final void e() {
        u.i iVar = this.f9377b;
        if (iVar instanceof u.a) {
            int i7 = ((u.a) iVar).f9094x0;
            h hVar = this.f9383h;
            if (i7 == 0 || i7 == 1) {
                iVar.f9129a0 = hVar.f9345g;
            } else {
                iVar.f9131b0 = hVar.f9345g;
            }
        }
    }

    @Override // v.v
    public final void f() {
        this.f9378c = null;
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
