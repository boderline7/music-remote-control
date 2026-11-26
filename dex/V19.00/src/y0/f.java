package y0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g f9970d;

    public f(g gVar) {
        this.f9970d = gVar;
    }

    @Override // l6.f
    public final void Y(Throwable th) {
        ((l) this.f9970d.f6290b).d(th);
    }

    @Override // l6.f
    public final void e0(j.h hVar) {
        g gVar = this.f9970d;
        gVar.f9972d = hVar;
        j.h hVar2 = gVar.f9972d;
        p3.f fVar = new p3.f(6, 0);
        Object obj = gVar.f6290b;
        e eVar = ((l) obj).f9985h;
        ((l) obj).getClass();
        gVar.f9971c = new s(hVar2, fVar, eVar);
        ((l) gVar.f6290b).e();
    }
}
