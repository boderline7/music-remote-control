package y1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends o {
    @Override // y1.p
    public final void a(f fVar) {
    }

    @Override // y1.p
    public final q b() {
        return q.TYPE_HEADER_ITEM;
    }

    @Override // y1.p
    public final int c() {
        return z1.c.INVOKE_DIRECT;
    }

    @Override // y1.p
    public final void d(f fVar, e2.c cVar) {
        int b7 = fVar.f10075d.b();
        int b8 = fVar.f10073b.b();
        z zVar = fVar.f10075d;
        int b9 = zVar.b();
        zVar.f();
        int i7 = (b9 + zVar.f10120i) - b8;
        int i8 = fVar.f10072a.f9386a;
        String str = "039";
        if (i8 < 28 && i8 < 28) {
            str = i8 >= 26 ? "038" : i8 >= 24 ? "037" : "035";
        }
        String h7 = a.a.h("dex\n", str, "\u0000");
        if (cVar.d()) {
            cVar.b(8, "magic: " + new b2.u(h7).g());
            cVar.b(4, "checksum");
            cVar.b(20, "signature");
            int i9 = fVar.f10089r;
            if (i9 < 0) {
                throw new RuntimeException("file size not yet known");
            }
            cVar.b(4, "file_size:       ".concat(n1.z.m1(i9)));
            cVar.b(4, "header_size:     ".concat(n1.z.m1(z1.c.INVOKE_DIRECT)));
            cVar.b(4, "endian_tag:      ".concat(n1.z.m1(305419896)));
            cVar.b(4, "link_size:       0");
            cVar.b(4, "link_off:        0");
            cVar.b(4, "map_off:         ".concat(n1.z.m1(b7)));
        }
        for (int i10 = 0; i10 < 8; i10++) {
            cVar.i(h7.charAt(i10));
        }
        cVar.n(24);
        int i11 = fVar.f10089r;
        if (i11 < 0) {
            throw new RuntimeException("file size not yet known");
        }
        cVar.j(i11);
        cVar.j(z1.c.INVOKE_DIRECT);
        cVar.j(305419896);
        cVar.n(8);
        cVar.j(b7);
        fVar.f10077f.s(cVar);
        fVar.f10078g.s(cVar);
        fVar.f10079h.s(cVar);
        l lVar = fVar.f10080i;
        lVar.f();
        int size = lVar.f10099f.size();
        int b10 = size == 0 ? 0 : lVar.b();
        if (cVar.d()) {
            cVar.b(4, "field_ids_size:  ".concat(n1.z.m1(size)));
            cVar.b(4, "field_ids_off:   ".concat(n1.z.m1(b10)));
        }
        cVar.j(size);
        cVar.j(b10);
        w wVar = fVar.f10081j;
        wVar.f();
        int size2 = wVar.f10113f.size();
        int b11 = size2 == 0 ? 0 : wVar.b();
        if (cVar.d()) {
            cVar.b(4, "method_ids_size: ".concat(n1.z.m1(size2)));
            cVar.b(4, "method_ids_off:  ".concat(n1.z.m1(b11)));
        }
        cVar.j(size2);
        cVar.j(b11);
        b bVar = fVar.f10082k;
        bVar.f();
        int size3 = bVar.f10038g.size();
        int b12 = size3 != 0 ? bVar.b() : 0;
        if (cVar.d()) {
            cVar.b(4, "class_defs_size: ".concat(n1.z.m1(size3)));
            cVar.b(4, "class_defs_off:  ".concat(n1.z.m1(b12)));
        }
        cVar.j(size3);
        cVar.j(b12);
        if (cVar.d()) {
            cVar.b(4, "data_size:       ".concat(n1.z.m1(i7)));
            cVar.b(4, "data_off:        ".concat(n1.z.m1(b8)));
        }
        cVar.j(i7);
        cVar.j(b8);
    }
}
