package a6;

import java.io.IOException;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s extends w5.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ w f465e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f466f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ List f467g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(String str, w wVar, int i7, List list, boolean z6) {
        super(str, true);
        this.f465e = wVar;
        this.f466f = i7;
        this.f467g = list;
    }

    @Override // w5.a
    public final long a() {
        m0 m0Var = this.f465e.f488l;
        List list = this.f467g;
        ((k0) m0Var).getClass();
        i5.a.g("responseHeaders", list);
        try {
            this.f465e.f501y.F(this.f466f, b.CANCEL);
            synchronized (this.f465e) {
                this.f465e.A.remove(Integer.valueOf(this.f466f));
            }
            return -1L;
        } catch (IOException unused) {
            return -1L;
        }
    }
}
