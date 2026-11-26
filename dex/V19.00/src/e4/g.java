package e4;

import u1.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public final d4.b f6388a;

    /* renamed from: b, reason: collision with root package name */
    public final int f6389b;

    /* renamed from: c, reason: collision with root package name */
    public final int f6390c;

    /* renamed from: d, reason: collision with root package name */
    public final int f6391d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.g f6392e;

    public g(androidx.appcompat.app.g gVar, d4.b bVar, int i7, int i8, int i9) {
        this.f6392e = gVar;
        this.f6388a = bVar;
        this.f6389b = i7;
        this.f6390c = i8;
        this.f6391d = i9;
    }

    public final int a() {
        d4.b bVar = d4.b.BYTE;
        d4.b bVar2 = this.f6388a;
        int i7 = this.f6391d;
        if (bVar2 != bVar) {
            return i7;
        }
        l lVar = (l) this.f6392e.f716d;
        y3.d dVar = (y3.d) lVar.f9280c;
        String str = (String) lVar.f9279b;
        int i8 = this.f6389b;
        return str.substring(i8, i7 + i8).getBytes(dVar.f10184a[this.f6390c].charset()).length;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        d4.b bVar = this.f6388a;
        sb.append(bVar);
        sb.append('(');
        d4.b bVar2 = d4.b.ECI;
        androidx.appcompat.app.g gVar = this.f6392e;
        if (bVar == bVar2) {
            sb.append(((y3.d) ((l) gVar.f716d).f9280c).f10184a[this.f6390c].charset().displayName());
        } else {
            String str = (String) ((l) gVar.f716d).f9279b;
            int i7 = this.f6389b;
            String substring = str.substring(i7, this.f6391d + i7);
            StringBuilder sb2 = new StringBuilder();
            for (int i8 = 0; i8 < substring.length(); i8++) {
                if (substring.charAt(i8) < ' ' || substring.charAt(i8) > '~') {
                    sb2.append('.');
                } else {
                    sb2.append(substring.charAt(i8));
                }
            }
            sb.append(sb2.toString());
        }
        sb.append(')');
        return sb.toString();
    }
}
