package a6;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n extends w5.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f448e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ w f449f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f450g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(String str, w wVar, Object obj, int i7) {
        super(str, true);
        this.f448e = i7;
        this.f449f = wVar;
        this.f450g = obj;
    }

    @Override // w5.a
    public final long a() {
        switch (this.f448e) {
            case 0:
                w wVar = this.f449f;
                wVar.f478b.a(wVar, ((i5.j) this.f450g).f6812a);
                break;
            default:
                try {
                    this.f449f.f478b.b((g0) this.f450g);
                    break;
                } catch (IOException e7) {
                    b6.s.Companion.getClass();
                    b6.s sVar = b6.s.f3002a;
                    String str = "Http2Connection.Listener failure for " + this.f449f.f480d;
                    sVar.getClass();
                    b6.s.i(str, 4, e7);
                    try {
                        ((g0) this.f450g).c(b.PROTOCOL_ERROR, e7);
                        break;
                    } catch (IOException unused) {
                        break;
                    }
                }
        }
        return -1L;
    }
}
