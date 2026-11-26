package w1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l extends h {
    @Override // w1.h
    public final int b() {
        return this.f9427b.f9434d.d();
    }

    @Override // w1.h
    public final String g() {
        j jVar = this.f9427b;
        n nVar = jVar.f9434d;
        nVar.getClass();
        String a7 = jVar.a();
        String g7 = nVar.g(this);
        String h7 = nVar.h(this);
        StringBuilder sb = new StringBuilder(100);
        sb.append(a7);
        if (g7.length() != 0) {
            sb.append(' ');
            sb.append(g7);
        }
        if (h7.length() != 0) {
            sb.append(" // ");
            sb.append(h7);
        }
        return sb.toString();
    }

    @Override // w1.h
    public final h k(int i7) {
        return l(this.f9429d.l(i7));
    }

    @Override // w1.h
    public final void m(e2.c cVar) {
        this.f9427b.f9434d.t(cVar, this);
    }
}
