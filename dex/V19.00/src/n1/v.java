package n1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v extends s {

    /* renamed from: a, reason: collision with root package name */
    public w f7678a;

    @Override // n1.s, n1.q
    public final void b() {
        w wVar = this.f7678a;
        if (wVar.A) {
            return;
        }
        wVar.F();
        wVar.A = true;
    }

    @Override // n1.q
    public final void d(r rVar) {
        w wVar = this.f7678a;
        int i7 = wVar.f7681z - 1;
        wVar.f7681z = i7;
        if (i7 == 0) {
            wVar.A = false;
            wVar.m();
        }
        rVar.v(this);
    }
}
