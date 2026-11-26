package a6;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r extends w5.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ w f461e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f462f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ g6.h f463g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f464h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(String str, w wVar, int i7, g6.h hVar, int i8, boolean z6) {
        super(str, true);
        this.f461e = wVar;
        this.f462f = i7;
        this.f463g = hVar;
        this.f464h = i8;
    }

    @Override // w5.a
    public final long a() {
        try {
            m0 m0Var = this.f461e.f488l;
            g6.h hVar = this.f463g;
            int i7 = this.f464h;
            ((k0) m0Var).getClass();
            i5.a.g("source", hVar);
            hVar.h(i7);
            this.f461e.f501y.F(this.f462f, b.CANCEL);
            synchronized (this.f461e) {
                this.f461e.A.remove(Integer.valueOf(this.f462f));
            }
            return -1L;
        } catch (IOException unused) {
            return -1L;
        }
    }
}
