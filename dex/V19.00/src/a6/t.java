package a6;

import java.io.IOException;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class t extends w5.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f468e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ w f469f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f470g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f471h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(String str, w wVar, int i7, Object obj, int i8) {
        super(str, true);
        this.f468e = i8;
        this.f469f = wVar;
        this.f470g = i7;
        this.f471h = obj;
    }

    @Override // w5.a
    public final long a() {
        switch (this.f468e) {
            case 0:
                m0 m0Var = this.f469f.f488l;
                b bVar = (b) this.f471h;
                ((k0) m0Var).getClass();
                i5.a.g("errorCode", bVar);
                synchronized (this.f469f) {
                    this.f469f.A.remove(Integer.valueOf(this.f470g));
                }
                return -1L;
            case 1:
                w wVar = this.f469f;
                try {
                    int i7 = this.f470g;
                    b bVar2 = (b) this.f471h;
                    wVar.getClass();
                    i5.a.g("statusCode", bVar2);
                    wVar.f501y.F(i7, bVar2);
                } catch (IOException e7) {
                    wVar.B(e7);
                }
                return -1L;
            default:
                m0 m0Var2 = this.f469f.f488l;
                List list = (List) this.f471h;
                ((k0) m0Var2).getClass();
                i5.a.g("requestHeaders", list);
                try {
                    this.f469f.f501y.F(this.f470g, b.CANCEL);
                    synchronized (this.f469f) {
                        this.f469f.A.remove(Integer.valueOf(this.f470g));
                    }
                } catch (IOException unused) {
                }
                return -1L;
        }
    }
}
