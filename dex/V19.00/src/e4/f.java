package e4;

import u1.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public final d4.b f6382a;

    /* renamed from: b, reason: collision with root package name */
    public final int f6383b;

    /* renamed from: c, reason: collision with root package name */
    public final int f6384c;

    /* renamed from: d, reason: collision with root package name */
    public final int f6385d;

    /* renamed from: e, reason: collision with root package name */
    public final f f6386e;

    /* renamed from: f, reason: collision with root package name */
    public final int f6387f;

    public f(l lVar, d4.b bVar, int i7, int i8, int i9, f fVar, d4.c cVar) {
        this.f6382a = bVar;
        this.f6383b = i7;
        d4.b bVar2 = d4.b.BYTE;
        int i10 = (bVar == bVar2 || fVar == null) ? i8 : fVar.f6384c;
        this.f6384c = i10;
        this.f6385d = i9;
        this.f6386e = fVar;
        boolean z6 = false;
        int i11 = fVar != null ? fVar.f6387f : 0;
        if ((bVar == bVar2 && fVar == null && i10 != 0) || (fVar != null && i10 != fVar.f6384c)) {
            z6 = true;
        }
        i11 = (fVar == null || bVar != fVar.f6382a || z6) ? i11 + bVar.a(cVar) + 4 : i11;
        int i12 = e.f6381b[bVar.ordinal()];
        if (i12 == 1) {
            i11 += 13;
        } else if (i12 == 2) {
            i11 += i9 == 1 ? 6 : 11;
        } else if (i12 == 3) {
            i11 += i9 != 1 ? i9 == 2 ? 7 : 10 : 4;
        } else if (i12 == 4) {
            i11 += ((String) lVar.f9279b).substring(i7, i9 + i7).getBytes(((y3.d) lVar.f9280c).f10184a[i8].charset()).length * 8;
            if (z6) {
                i11 += 12;
            }
        }
        this.f6387f = i11;
    }
}
